package org.fundaciobit.pinbaladmin.back.controller.admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudLocalOperadorController.SolicitudConEventos;
import org.fundaciobit.pinbaladmin.back.controller.webdb.SolicitudController;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.OperadorService;
import org.fundaciobit.pinbaladmin.logic.ContacteLogicaService;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager.TipusPluginUserInfo;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.entity.Operador;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.ContacteFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.ContacteJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.SearchUsersResult;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

/**
 * Controlador de administración para Solicitudes Activas.
 * Funcionalidades:
 * - Migración de fusiones históricas (notas → solicitudFusionadaID)
 * - Migración de contactos (titulares, responsables, personas de contacto)
 * desde XML
 * - Completar datos faltantes con plugin UserInfo LDAP
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = SolicitudActivaAdminController.CONTEXTWEB)
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudActivaAdminController extends SolicitudController {

    // ============================== CONSTANTES ==============================

    public static final String CONTEXTWEB = "/admin/solicitudactiva";

    // ============================== SERVICIOS EJB ==============================

    @EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
    protected InfoMadridLogicaService infoMadridLogicaEjb;

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @EJB(mappedName = OperadorService.JNDI_NAME)
    protected OperadorService operadorEjb;

    @EJB(mappedName = OrganLogicaService.JNDI_NAME)
    protected OrganLogicaService organLogicaEjb;

    @EJB(mappedName = ContacteLogicaService.JNDI_NAME)
    protected ContacteLogicaService contacteLogicaEjb;

    // ============================== CACHÉ ==============================

    // Caché de UserInfo por NIF para evitar consultas repetidas al plugin LDAP
    private Map<String, UserInfo> cacheUserInfo = new HashMap<>();

    // ============================== OVERRIDES DEL PADRE
    // ==============================

    @Override
    public String getTileForm() {
        return "solicitudFormWebDB_admin";
    }

    @Override
    public String getTileList() {
        return "solicitudListWebDB_admin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "SolicitudWebDB_FilterForm_admin";
    }

    @Override
    public String getEntityNameCode() {
        return "solicitud.solicitudactiva";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "solicitud.solicitudactiva.plural";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        Where wSuper = super.getAdditionalCondition(request);

        Where wLocals = SolicitudFields.ORGANID.isNotNull();
        Where wNoFusionadas = SolicitudFields.ESTATSOLICITUD.notEqual(Constants.SOLI_ESTAT_FUSIONADA);

        return Where.AND(wSuper, wLocals, wNoFusionadas);
    }

    @Override
    public List<StringKeyValue> getReferenceListForEstatSolicitud(HttpServletRequest request,
            ModelAndView mav, Where where) throws I18NException {
        List<StringKeyValue> list = new ArrayList<>();
        for (long estat : Constants.ESTATS_SOLI) {
            String key = String.valueOf(estat);
            list.add(new StringKeyValue(key, I18NUtils.tradueix("solicitud.estat." + key)));
        }
        return list;
    }

    @Override
    public List<StringKeyValue> getReferenceListForOperador(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> list = new ArrayList<>();
        List<Operador> operadores = operadorEjb.select();
        for (Operador operador : operadores) {
            list.add(new StringKeyValue(operador.getUsername(), operador.getNom()));
        }
        return list;
    }

    @Override
    public List<StringKeyValue> getReferenceListForOrganid(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> list = new ArrayList<>();
        List<Organ> organids = organLogicaEjb.select();
        for (Organ organ : organids) {
            list.add(new StringKeyValue(organ.getOrganid() + "", organ.getNom()));
        }
        return list;
    }

    @Override
    public SolicitudFilterForm getSolicitudFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {

        SolicitudFilterForm solicitudFilterForm = super.getSolicitudFilterForm(pagina, mav, request);

        if (solicitudFilterForm.isNou()) {
            solicitudFilterForm.setDeleteSelectedButtonVisible(false);
            solicitudFilterForm.setAddButtonVisible(false);
            solicitudFilterForm.setDeleteButtonVisible(false);

            // Campos visibles en el listado
            Set<Field<?>> hiddenFields = new HashSet<>(Arrays.asList(SolicitudFields.ALL_SOLICITUD_FIELDS));
            hiddenFields.remove(SolicitudFields.PROCEDIMENTCODI);
            hiddenFields.remove(SolicitudFields.PROCEDIMENTNOM);
            hiddenFields.remove(SolicitudFields.ESTATSOLICITUD);
            hiddenFields.remove(SolicitudFields.DATAINICI);
            hiddenFields.remove(SolicitudFields.ORGANID);
            solicitudFilterForm.setHiddenFields(hiddenFields);

            // Botones personalizados
            solicitudFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
                    IconUtils.ICON_EYE,
                    "veure.complet",
                    "/operador/solicitudfullview/view/{0}",
                    AdditionalButtonStyle.PRIMARY));
            solicitudFilterForm.addAdditionalButton(new AdditionalButton(
                    IconUtils.ICON_FILE,
                    "solicitud.admin.test.button",
                    "javascript:alert('Botón de prueba')",
                    AdditionalButtonStyle.SUCCESS));

            solicitudFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
                    "fas fa-flask",
                    "solicitud.admin.probar.accion",
                    "javascript:console.log('Solicitud ID: {0}'); alert('Probando con ID: {0}');",
                    AdditionalButtonStyle.WARNING));

            // Botones de migración masiva
            solicitudFilterForm.addAdditionalButton(new AdditionalButton(
                    "fas fa-database",
                    "solicitud.admin.migrar.fusiones.historicas",
                    "javascript:if(confirm('ATENCIÓN: Migración masiva.\\nRellenará solicitudFusionadaID desde notas.\\n¿Continuar?')) { window.location.href='/pinbaladmin"
                            + CONTEXTWEB
                            + "/migrarFusionesHistoricas'; }",
                    AdditionalButtonStyle.DANGER));

            // Botón migración completa de todos los contactos
            solicitudFilterForm.addAdditionalButton(new AdditionalButton(
                    "fas fa-users",
                    "solicitud.admin.migrar.contactos.completo",
                    "javascript:if(confirm('ATENCIÓN: Migración completa de contactos.\\n\\n" +
                            "Se migrarán 5 tipos de contactos desde el XML:\\n" +
                            "- Solicitante (persona autenticada)\\n" +
                            "- Gestión y Autorización\\n" +
                            "- Auditorías\\n" +
                            "- Técnico\\n" +
                            "- Titular\\n\\n" +
                            "¿Continuar?')) { window.location.href='/pinbaladmin" + CONTEXTWEB
                            + "/migrarContactosCompleto'; }",
                    AdditionalButtonStyle.SUCCESS));

            // Botón para actualizar titulares no migrados
            solicitudFilterForm.addAdditionalButton(new AdditionalButton(
                    "fas fa-user-check",
                    "solicitud.admin.actualizar.titulares.no.migrados",
                    "javascript:if(confirm('ATENCIÓN: Actualización de titulares.\\n\\n" +
                            "Se actualizarán solicitudes con NIF de titular pero sin contacto asociado.\\n" +
                            "Se buscará el contacto existente o se creará uno nuevo.\\n\\n" +
                            "¿Continuar?')) { window.location.href='/pinbaladmin" + CONTEXTWEB
                            + "/actualizarTitularesNoMigrados'; }",
                    AdditionalButtonStyle.INFO));

//            solicitudFilterForm.addAdditionalButton(new AdditionalButton(
//                    "fas fa-user-check",
//                    "solicitud.admin.actualizar.contactos",
//                    "javascript:if(confirm('ATENCIÓN: Actualizar Datos de Contactos.\\n\\n" +
//                            "Se actualizarán los contactos existentes para reducir los que tenemos.\\n" +
//                            "Se buscarán los contactos con datos basicos iguales, y se fusionaran.\\n\\n" +
//                            "¿Continuar?')) { window.location.href='/pinbaladmin" + CONTEXTWEB
//                            + "/actualizarDatosContactos'; }",
//                    AdditionalButtonStyle.INFO));

            solicitudFilterForm
            .addAdditionalButton(new AdditionalButton(IconUtils.ICON_CHECK,"solicitud.admin.actualizar.contactos",
                    "javascript:iniciarFusiones()", AdditionalButtonStyle.INFO));

            
            solicitudFilterForm.setOrderBy(SolicitudFields.DATAINICI.fullName);
            solicitudFilterForm.setOrderAsc(false);
            solicitudFilterForm.setVisibleMultipleSelection(true);
            solicitudFilterForm.setAttachedAdditionalJspCode(true);
            solicitudFilterForm.setFilterByFields(null);
            solicitudFilterForm.setGroupByFields(null);
        }

        return solicitudFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, SolicitudFilterForm filterForm,
            List<Solicitud> list) throws I18NException {
        super.postList(request, mav, filterForm, list);
        mav.addObject("customMessage", "Zona de pruebas - Solicitudes Activas");
        mav.addObject("totalSolicituds", list.size());

        filterForm.getAdditionalButtonsByPK().clear(); // Limpiar botones anteriores para evitar duplicados en cada
                                                       // carga

        for (Solicitud solicitud : list) {
            // log.info("Solicitud ID: " + solicitud.getSolicitudID() + ", Procediment: " +
            // solicitud.getProcedimentCodi()
            // + ", Estat: " + solicitud.getEstatSolicitud() + ", OrganID: " +
            // solicitud.getOrganid());

            filterForm.addAdditionalButtonByPK(solicitud.getSolicitudID(), new AdditionalButton("fas fa-sync-alt",
                    "solicitud.admin.actualizar.contactos",
                    "javascript:if(confirm('¿Actualizar contactos desde XML para esta solicitud?')) { window.location.href='/pinbaladmin"
                            + CONTEXTWEB + "/migrarContacto/" + solicitud.getSolicitudID() + "'; }",
                    AdditionalButtonStyle.INFO));
        }

    }

    @RequestMapping(value = "/actualizarDatosContactos", method = RequestMethod.GET)
	public String actualizarDatosContactos(HttpServletRequest request, HttpServletResponse response) {
    	
    	HtmlUtils.saveMessageInfo(request, "Funcionalidad en desarrollo. Próximamente...");
    	return "redirect:" + CONTEXTWEB + "/list";
    }
    
    public class ContacteDuplicatGroup {

        private String nif;
        private String mail;
        private List<ContacteDTO> contactos;

        public ContacteDuplicatGroup(String nif, String mail, List<ContacteDTO> contactos) {
            this.nif = nif;
            this.mail = mail;
            this.contactos = contactos;
        }

        public String getNif() { return nif; }
        public String getMail() { return mail; }
        public List<ContacteDTO> getContactos() { return contactos; }
    }
    
    public class FusionContacteDTO {
        private Long masterId;
        private List<Long> mergeIds;

        public Long getMasterId() { return masterId; }
        public List<Long> getMergeIds() { return mergeIds; }
    }
    
    public class ContacteDTO {

        private Long contacteID;
        private String nif;
        private String nom;
        private String llinatge1;
        private String llinatge2;
        private String carrec;
        private String telefon;
        private String mail;
        private String username;
        private String nombrecompleto;
        private Long numSolicituds;

        public ContacteDTO() {}

        public ContacteDTO(Contacte c) {
            this.contacteID = c.getContacteID();
            this.nif = c.getNif();
            this.nom = c.getNom();
            this.llinatge1 = c.getLlinatge1();
            this.llinatge2 = c.getLlinatge2();
            this.carrec = c.getCarrec();
            this.telefon = c.getTelefon();
            this.mail = c.getMail();
            this.username = c.getUsername();
            this.nombrecompleto = c.getNombrecompleto();
        }

        public Long getContacteID() { return contacteID; }
        public String getNif() { return nif; }
        public String getNom() { return nom; }
        public String getLlinatge1() { return llinatge1; }
        public String getLlinatge2() { return llinatge2; }
        public String getCarrec() { return carrec; }
        public String getTelefon() { return telefon; }
        public String getMail() { return mail; }
        public String getUsername() { return username; }
        public String getNombrecompleto() { return nombrecompleto; }
        public Long getNumSolicituds() { return numSolicituds; }
        
        public void setContacteID(Long contacteID) { this.contacteID = contacteID; }
        public void setNif(String nif) { this.nif = nif; }
        public void setNom(String nom) { this.nom = nom; }
        public void setLlinatge1(String llinatge1) { this.llinatge1 = llinatge1; }
        public void setLlinatge2(String llinatge2) { this.llinatge2 = llinatge2; }
        public void setCarrec(String carrec) { this.carrec = carrec; }
        public void setTelefon(String telefon) { this.telefon = telefon; }
        public void setMail(String mail) { this.mail = mail; }
        public void setUsername(String username) { this.username = username; }
        public void setNombrecompleto(String nombrecompleto) { this.nombrecompleto = nombrecompleto; }
		public void setNumSolicituds(Long numSolicituds) { this.numSolicituds = numSolicituds; }
        
    }
    
    @RequestMapping(value = "/contactos/duplicados", method = RequestMethod.GET)
    public void jsonDuplicados(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("jsonDuplicados: INICIO");

        
        OrderBy orderBy = new OrderBy(ContacteFields.NIF);
        
		List<Contacte> contactes = contacteLogicaEjb.select(orderBy);

        Map<String, List<ContacteDTO>> grups = new HashMap<>();

        for (Contacte c : contactes) {

            if (c.getNif() == null || c.getMail() == null)
                continue;

            String key = c.getNif().trim().toUpperCase() + "|" + c.getMail().trim().toLowerCase();

            Long contacteID = c.getContacteID();
            
            ContacteDTO dto = new ContacteDTO();
            dto.setContacteID(contacteID);
            dto.setNif(c.getNif());
            dto.setNom(c.getNom());
            dto.setLlinatge1(c.getLlinatge1());
            dto.setLlinatge2(c.getLlinatge2());
            dto.setCarrec(c.getCarrec());
            dto.setTelefon(c.getTelefon());
            dto.setMail(c.getMail());
            dto.setUsername(c.getUsername());
            dto.setNombrecompleto(c.getNombrecompleto());
            
			Long numSolicituds = solicitudLogicaEjb
					.count(Where.OR(SolicitudFields.CONTACTETITULARID.equal(contacteID),
							SolicitudFields.CONTACTEAUDITORIAID.equal(contacteID),
							SolicitudFields.CONTACTESOLICITANTID.equal(contacteID),
							SolicitudFields.CONTACTETECNICID.equal(contacteID),
							SolicitudFields.CONTACTEGESTAUTID.equal(contacteID)));
			dto.setNumSolicituds(numSolicituds);

            grups.computeIfAbsent(key, k -> new ArrayList<>()).add(dto);
        }

        List<ContacteDuplicatGroup> result = new ArrayList<>();

        for (Map.Entry<String, List<ContacteDTO>> e : grups.entrySet()) {

            if (e.getValue().size() > 1) {
                String[] parts = e.getKey().split("\\|");

                result.add(new ContacteDuplicatGroup(
                    parts[0],
                    parts[1],
                    e.getValue()
                ));
                
                log.info("Encontrados " + e.getValue().size() + " contactos con NIF " + parts[0] + " y mail " + parts[1]);
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().print(new Gson().toJson(result));
    }
    
    @RequestMapping(value = "/contactes/fusionar", method = RequestMethod.POST)
    public void fusionarContactes(HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.info("fusionarContactes: INICIO");

        String body = request.getReader().lines().reduce("", (a, b) -> a + b);

        Gson g = new Gson();
        FusionContacteDTO[] fusiones = g.fromJson(body, FusionContacteDTO[].class);

        for (FusionContacteDTO f : fusiones) {

            Long masterId = f.getMasterId();

            for (Long id : f.getMergeIds()) {

                log.info("Fusionando contacto " + id + " -> " + masterId);

                updateSoliContacte(SolicitudFields.CONTACTETITULARID, id, masterId);
                updateSoliContacte(SolicitudFields.CONTACTEAUDITORIAID, id, masterId);
                updateSoliContacte(SolicitudFields.CONTACTESOLICITANTID, id, masterId);
                updateSoliContacte(SolicitudFields.CONTACTETECNICID, id, masterId);
                updateSoliContacte(SolicitudFields.CONTACTEGESTAUTID, id, masterId);

                contacteLogicaEjb.delete(id);
            }
        }

        response.setContentType("application/json");
        response.getWriter().print("{\"status\":\"OK\"}");
    }
//	@RequestMapping(value = "/actualizarDatosContactos", method = RequestMethod.GET)
//	public String actualizarDatosContactos(HttpServletRequest request, HttpServletResponse response)
//			throws I18NException {
//
//		log.info("=== INICIO ACTUALIZACIÓN DE DATOS DE CONTACTOS EXISTENTES ===");
//
//		int totalSolicitudes = 0;
//		int contactosActualizados = 0;
//		int errores = 0;
//
//		try {
//			IUserInformationPlugin plugin = PinbalAdminPluginsManager.getUserInformationPluginInstance(false,
//					TipusPluginUserInfo.LDAP);
//			
//			List<Contacte> contactes = contacteLogicaEjb.select();
//			
//			//Hacer un mapa de NIF y cuantos contactos hay con ese NIF. 
//			Map<String, List<Contacte>> contactosPorNif = new HashMap<>();
//			for (Contacte contacte : contactes) {
//				if (contacte.getNif() != null) {
//					String nif = contacte.getNif().toUpperCase().trim();
//					
//					// Si el NIF no existe en el mapa, lo añadimos con una nueva lista. Si ya existe, añadimos el contacto a la lista existente.
//					if (!contactosPorNif.containsKey(nif)) {
//						List<Contacte> lista = new ArrayList<>();
//						lista.add(contacte);
//						contactosPorNif.put(nif, lista);
//					}else {
//						contactosPorNif.get(nif).add(contacte);
//					}
//				}
//			}
//			
//			// Cuando llegamos aqui, tenemos un mapa con el NIF como clave, y una lista de contactos que tienen ese NIF como valor.
//			// Ahora podremos procesar las posibles fusiones.
//			
//			for (Map.Entry<String, List<Contacte>> entry : contactosPorNif.entrySet()) {
//				String nif = entry.getKey();
//				List<Contacte> contactosConMismoNif = entry.getValue();
//
//				if (contactosConMismoNif.size() > 1) {
//				//	log.info("Encontrados " + contactosConMismoNif.size() + " contactos con NIF " + nif);
//					
//					// Suponemos que los contactos con mismo NIF y mismo mail, son la misma persona. Estos se pueden fusionar.
//					// Primero haremos un listado de los que se pueden fusionar.
//					
//					Map<String, List<Contacte>> contactosPorMail = new HashMap<>();
//					
//					for (Contacte contacte : contactosConMismoNif) {
//						if (contacte.getMail() != null) {
//                            String mail = contacte.getMail().toLowerCase().trim();
//                            
//                            if (!contactosPorMail.containsKey(mail)) {
//                                List<Contacte> lista = new ArrayList<>();
//                                lista.add(contacte);
//                                contactosPorMail.put(mail, lista);
//                            }else {
//                                contactosPorMail.get(mail).add(contacte);
//                            }
//                        }
//                    }
//					
//					// Cuando llegamos aqui, tenemos una lista de contactos con el mismo NIF y mismo mail. Estos se pueden fusionar.
//					for (Map.Entry<String, List<Contacte>> entryMail : contactosPorMail.entrySet()) {
//						String mail = entryMail.getKey();
//						List<Contacte> contactosConMismoNifYMail = entryMail.getValue();
//
//						if (contactosConMismoNifYMail.size() > 1) {
//					//		log.info("Encontrados " + contactosConMismoNifYMail.size() + " contactos con NIF " + nif + " y mail " + mail);
//							comprovacioFusioDeContactes(contactosConMismoNifYMail);
//							contactosActualizados += contactosConMismoNifYMail.size() - 1; 
//														// Si se han fusionado 3 contactos, se han actualizado 2 (los que se han eliminado)
//							
//						}else {
//							// Si solo hay un contacto para ese NIF y mail, no hay duplicados.
//						}
//					}
//						
//						
//				}else {
//					// Si solo hay un contacto para ese NIF, no hay duplicados.
//				}
//			}
//			
//		} catch (Exception e) {
//			log.error("Error durante actualización de datos de contactos: " + e.getMessage(), e);
//			HtmlUtils.saveMessageError(request, "Error: " + e.getMessage());
//		}
//
//		log.info("Actualización de datos de contactos finalizada. Total solicitudes: " + totalSolicitudes + ", contactos actualizados (fusionados): " + contactosActualizados + ", errores: " + errores);
//		
//		return "redirect:" + CONTEXTWEB + "/list";
//	}
//    
//	private void comprovacioFusioDeContactes(List<Contacte> contactos) {
//		// Suponemos que los contactos de la lista tienen el mismo NIF y mail, y por tanto son la misma persona.
//		
////		log.info("Fusionando " + contactos.size() + " contactos con NIF " + contactos.get(0).getNif() + " y mail " + contactos.get(0).getMail());		
//		//SELECT * FROM pad_contacte WHERE nif = '43051734N' AND mail = 'llbernat@a-soller.es';
//		log.info("SELECT * FROM pad_contacte WHERE nif = '" + contactos.get(0).getNif() + "' AND mail = '" + contactos.get(0).getMail() + "';");
//
//		List<String> nombres = new ArrayList<>();
//		List<String> telefonos = new ArrayList<>();
//		List<String> apellidos1 = new ArrayList<>();
//		List<String> apellidos2 = new ArrayList<>();
//		List<String> cargos = new ArrayList<>();
//		List<String> usernames = new ArrayList<>();
//		
//		for (Contacte contacte : contactos) {
//			testCampValor(nombres, contacte.getNom());
//			testCampValor(telefonos, contacte.getTelefon());
//			testCampValor(apellidos1, contacte.getLlinatge1());
//			testCampValor(apellidos2, contacte.getLlinatge2());
//			testCampValor(cargos, contacte.getCarrec());
//			testCampValor(usernames, contacte.getUsername());
//		}
//		
//		// Ahora tenemos una lista de los valores distintos que hay en cada campo. Eliminando los nulos y vacíos, y sin repetir.
//		// Solo así ya nos podriamos quitar algunos contactos.
//
//		//Vamos a fusionar los que sean casos de nulos que hemos solucionado.
//		
//		if (nombres.size() == 1 && telefonos.size() == 1 && apellidos1.size() == 1 && apellidos2.size() == 1
//				&& cargos.size() == 1 && usernames.size() == 1) {
//			log.info("Fusionamos " + contactos.size() + " en uno solo");
//			String nif = contactos.get(0).getNif();
//			String nom = nombres.get(0);
//			String telefon = telefonos.get(0);
//			String llinatge1 = apellidos1.get(0);
//			String llinatge2 = apellidos2.get(0);
//			String carrec = cargos.get(0);
//			String username = contactos.get(0).getUsername();
//			String mail = contactos.get(0).getMail();
//			
//			String nomComplet = nom + " " + llinatge1 + " " + llinatge2;
//			nomComplet = nomComplet.replaceAll("\\s+", " ").trim(); // Limpiar espacios extras
//			
//			ContacteJPA contacteFusionat = new ContacteJPA(nif, nom, llinatge1, llinatge2, carrec, telefon, mail, username, nomComplet);
//			try {
//				Contacte fusionat = contacteLogicaEjb.create(contacteFusionat);
//				fusionarContactes(contactos, fusionat );
//			} catch (I18NException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		
//	}
//	
//	private void fusionarContactes(List<Contacte> contactos, Contacte contacteFusionat ) {
//        // Aquí se implementaría la lógica para fusionar los contactos en uno solo, manteniendo el ID del contacto que queremos conservar, y eliminando los demás.
//        // Antes de eliminar, se deberían actualizar las referencias a los contactos eliminados para que apunten al contacto que conservamos.
//		
//		// Creamos el contacto nuevo, con los datos anteriores, y actulizamos los IDs anteriores.
//		Long fusionatID = contacteFusionat.getContacteID();
//		
//		List<Long> contacteIDs = new ArrayList<>();
//		for (Contacte contacte : contactos) {
//			Long anteriorID = contacte.getContacteID();
//			
//			contacteIDs.add(contacte.getContacteID());
//
//			updateSoliContacte(SolicitudFields.CONTACTETITULARID, anteriorID, fusionatID);
//			updateSoliContacte(SolicitudFields.CONTACTEAUDITORIAID, anteriorID, fusionatID);
//			updateSoliContacte(SolicitudFields.CONTACTESOLICITANTID, anteriorID, fusionatID);
//			updateSoliContacte(SolicitudFields.CONTACTETECNICID, anteriorID, fusionatID);
//			updateSoliContacte(SolicitudFields.CONTACTEGESTAUTID, anteriorID, fusionatID);
//			
////			contacteLogicaEjb.delete(contacte.getContacteID());
//		}
//    }
//	
	private void updateSoliContacte(LongField field, Long contacteIDAnterior, Long contacteIDNuevo) {
		log.info("UPDATE pad_solicitud SET " + field.getSqlName() + " = " + contacteIDNuevo + " WHERE " + field.getSqlName() + " = " + contacteIDAnterior);
		try {
			solicitudLogicaEjb.update(field, contacteIDNuevo, field.equal(contacteIDAnterior));
		} catch (I18NException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
//	private void testCampValor(List<String> llista, String valor) {
//		if (valor != null && !valor.isEmpty() && !llista.contains(valor)) {
//			llista.add(valor);
//		}
//	}
//    
    
    
    // =================== MIGRACIÓN FUSIONES ==========================

    /**
     * Migra campo solicitudFusionadaID desde notas históricas.
     * Ejemplo de nota: "Procediment fusionat de : 50074, 50630, 50077"
     * Resuelve conflictos automáticamente (prioriza ID mayor) y valida antes de
     * actualizar.
     */
    @RequestMapping(value = "/migrarFusionesHistoricas", method = RequestMethod.GET)
    public String migrarFusionesHistoricas(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {

        log.info("=== INICIO MIGRACIÓN FUSIONES HISTÓRICAS ===");

        try {
            // Buscar solicitudes con información de fusión en las notas
            Pattern pattern = Pattern.compile("Procediment fusionat de\\s*:\\s*([0-9,\\s]+)", Pattern.CASE_INSENSITIVE);

            Where whereFusionada = Where.AND(
                    SolicitudFields.NOTES.isNotNull(),
                    SolicitudFields.NOTES.like("%Procediment fusionat de%"));

            List<Solicitud> solicitudesConFusiones = solicitudLogicaEjb.select(whereFusionada);

            log.info("Encontradas " + solicitudesConFusiones.size() + " solicitudes con fusiones");

            if (solicitudesConFusiones.isEmpty()) {
                HtmlUtils.saveMessageInfo(request,
                        "No se encontraron solicitudes con información de fusiones históricas.");
                return "redirect:" + CONTEXTWEB + "/list";
            }

            // Estructuras para procesar las relaciones
            Map<Long, Long> relacionesEncontradas = new HashMap<>(); // originalID -> destinoID
            List<String> vistaPreviaActualizaciones = new ArrayList<>();

            // Contadores
            int totalSolicitudesProcesadas = 0;
            int totalRelacionesEncontradas = 0;
            int totalActualizadas = 0;
            int totalConflictos = 0;
            int totalIdsInexistentes = 0;
            int totalAutoreferencias = 0;
            int totalYaRellenadas = 0;

            // FASE 1: Recolectar relaciones desde las notas
            log.info("=== FASE 1: Recolectando relaciones ===");

            for (Solicitud solicitud : solicitudesConFusiones) {
                totalSolicitudesProcesadas++;

                Long destinoId = solicitud.getSolicitudID();
                String notas = solicitud.getNotes();

                if (notas == null || notas.trim().isEmpty()) {
                    continue;
                }

                Matcher matcher = pattern.matcher(notas);

                while (matcher.find()) {
                    String idsStr = matcher.group(1);
                    String[] ids = idsStr.split(",");

                    for (String idStr : ids) {
                        idStr = idStr.trim();

                        if (idStr.isEmpty()) {
                            continue;
                        }

                        try {
                            Long originalId = Long.parseLong(idStr);
                            totalRelacionesEncontradas++;

                            // Validar autoreferencia
                            if (originalId.equals(destinoId)) {
                                log.warn("Autoreferencia: " + originalId + " se fusiona en sí misma");
                                totalAutoreferencias++;
                                continue;
                            }

                            // Detectar conflictos: si un ID ya está en el mapa, priorizar el ID más grande
                            // (más reciente)
                            if (relacionesEncontradas.containsKey(originalId)) {
                                Long destinoAnterior = relacionesEncontradas.get(originalId);
                                if (!destinoAnterior.equals(destinoId)) {
                                    Long destinoFinal = Math.max(destinoAnterior, destinoId);

                                    log.warn("CONFLICTO: Solicitud " + originalId +
                                            " fusionada en múltiples destinos. Manteniendo ID mayor: " + destinoFinal);
                                    totalConflictos++;

                                    relacionesEncontradas.put(originalId, destinoFinal);
                                    continue;
                                }
                            } else {
                                relacionesEncontradas.put(originalId, destinoId);
                            }

                        } catch (NumberFormatException e) {
                            log.warn("ID no válido en notas de solicitud " + destinoId + ": '" + idStr + "'");
                        }
                    }
                }
            }

            log.info("Fase 1 completada. Relaciones válidas: " + relacionesEncontradas.size());

            // FASE 2: Validar y preparar actualizaciones
            log.info("=== FASE 2: Validando y preparando actualizaciones ===");

            for (Map.Entry<Long, Long> relacion : relacionesEncontradas.entrySet()) {
                Long originalId = relacion.getKey();
                Long destinoId = relacion.getValue();

                try {
                    SolicitudJPA solicitudOriginal = solicitudLogicaEjb.findByPrimaryKey(originalId);

                    if (solicitudOriginal == null) {
                        log.warn("Solicitud original " + originalId + " no existe. Ignorando.");
                        totalIdsInexistentes++;
                        continue;
                    }

                    if (solicitudOriginal.getSolicitudFusionadaID() != null) {
                        log.info("Solicitud " + originalId + " ya tiene solicitudFusionadaID. No se sobrescribe.");
                        totalYaRellenadas++;
                        continue;
                    }

                    SolicitudJPA solicitudDestino = solicitudLogicaEjb.findByPrimaryKey(destinoId);

                    if (solicitudDestino == null) {
                        log.warn("Solicitud destino " + destinoId + " no existe. Ignorando relación.");
                        totalIdsInexistentes++;
                        continue;
                    }

                    Long estadoOriginal = solicitudOriginal.getEstatSolicitud();
                    boolean esFusionada = estadoOriginal != null
                            && estadoOriginal.equals(Constants.SOLI_ESTAT_FUSIONADA);

                    String estadoTexto = esFusionada ? "[FUSIONADA=-5]" : "[Estado=" + estadoOriginal + "]";
                    String avisoEstado = esFusionada ? "" : " ⚠️ NO está en estado FUSIONADA";

                    String infoDetallada = String.format("  %d %s → %d%s",
                            originalId, estadoTexto, destinoId, avisoEstado);

                    vistaPreviaActualizaciones.add(infoDetallada);

                    // Actualizar
                    solicitudOriginal.setSolicitudFusionadaID(destinoId);
                    solicitudLogicaEjb.update(solicitudOriginal);

                    log.info("✓ ACTUALIZADO: solicitud " + originalId + " " + estadoTexto + " → " + destinoId
                            + avisoEstado);
                    totalActualizadas++;

                } catch (Exception e) {
                    log.error("Error validando solicitud " + originalId + " → " + destinoId + ": " + e.getMessage(),
                            e);
                }
            }

            // Verificación adicional
            Where whereFusionadasSinRelacion = Where.AND(
                    SolicitudFields.ESTATSOLICITUD.equal(Constants.SOLI_ESTAT_FUSIONADA),
                    SolicitudFields.SOLICITUDFUSIONADAID.isNull());

            List<Solicitud> fusionadasSinRelacion = solicitudLogicaEjb.select(whereFusionadasSinRelacion);

            log.info("Total FUSIONADAS sin solicitudFusionadaID: " + fusionadasSinRelacion.size());
            log.info("Después de migración quedarían: " + (fusionadasSinRelacion.size() - totalActualizadas));

            // Mensaje final
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("=== RESUMEN MIGRACIÓN ===\\n");
            mensaje.append(String.format("Solicitudes procesadas: %d\\n", totalSolicitudesProcesadas));
            mensaje.append(String.format("Relaciones encontradas: %d\\n", totalRelacionesEncontradas));
            mensaje.append(String.format("ACTUALIZADAS: %d\\n", totalActualizadas));
            mensaje.append(String.format("Ya rellenadas: %d\\n", totalYaRellenadas));
            mensaje.append(String.format("Conflictos: %d\\n", totalConflictos));
            mensaje.append(String.format("Autoreferencias: %d\\n", totalAutoreferencias));
            mensaje.append(String.format("IDs inexistentes: %d\\n\\n", totalIdsInexistentes));

            mensaje.append("FUSIONADAS sin rellenar ANTES: " + fusionadasSinRelacion.size() + "\\n");
            mensaje.append("FUSIONADAS sin rellenar DESPUÉS: "
                    + (fusionadasSinRelacion.size() - totalActualizadas) + "\\n\\n");

            if (!vistaPreviaActualizaciones.isEmpty()) {
                mensaje.append("=== PRIMERAS 20 ACTUALIZADAS ===\\n");
                int limite = Math.min(20, vistaPreviaActualizaciones.size());
                for (int i = 0; i < limite; i++) {
                    mensaje.append(vistaPreviaActualizaciones.get(i)).append("\\n");
                }
                if (vistaPreviaActualizaciones.size() > 20) {
                    mensaje.append(String.format("... y %d más\\n", vistaPreviaActualizaciones.size() - 20));
                }
                mensaje.append("\\nVer log para detalles completos");
            }

            HtmlUtils.saveMessageSuccess(request, mensaje.toString());

        } catch (Exception e) {
            log.error("Error durante migración fusiones: " + e.getMessage(), e);
            HtmlUtils.saveMessageError(request, "Error: " + e.getMessage());
        }

        return "redirect:" + CONTEXTWEB + "/list";
    }

    // ============================== MIGRACIÓN CONTACTOS
    // ==============================

    /**
     * Migración de contactos para una solicitud individual.
     */
    @RequestMapping(value = "/migrarContacto/{solicitudID}", method = RequestMethod.GET)
    public String migrarContacto(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("solicitudID") Long solicitudID) throws I18NException {

        Solicitud solicitud = solicitudLogicaEjb.findByPrimaryKey(solicitudID);
        if (solicitud == null) {
            HtmlUtils.saveMessageError(request, "Solicitud con ID " + solicitudID + " no encontrada.");
            return "redirect:" + CONTEXTWEB + "/list";
        }

        try {
            IUserInformationPlugin plugin = PinbalAdminPluginsManager.getUserInformationPluginInstance(false,
                    TipusPluginUserInfo.LDAP);

            Map<TipoContacto, Integer> resultado = migrarContactosSolicitud(solicitud, plugin);

            int totalCreados = resultado.values().stream().mapToInt(Integer::intValue).sum();

            if (totalCreados > 0) {
                HtmlUtils.saveMessageSuccess(request,
                        String.format("Migrados %d contactos para solicitud %d", totalCreados, solicitudID));
            } else {
                HtmlUtils.saveMessageWarning(request,
                        "No se encontraron contactos para migrar en la solicitud " + solicitudID);
            }

        } catch (Exception e) {
            log.error("Error durante migración de solicitud " + solicitudID + ": " + e.getMessage(), e);
            HtmlUtils.saveMessageError(request, "Error: " + e.getMessage());
        }

        return "redirect:" + CONTEXTWEB + "/list";
    }

    /**
     * Migración completa de todos los contactos desde XML a BD.
     * Optimizado: parsea el XML una sola vez por solicitud.
     */
    @RequestMapping(value = "/migrarContactosCompleto", method = RequestMethod.GET)
    public String migrarContactosCompleto(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {

        log.info("=== INICIO MIGRACIÓN COMPLETA DE CONTACTOS ===");

        int totalSolicitudes = 0;
        int solicitantesCreados = 0;
        int gestAutCreados = 0;
        int auditoriasCreados = 0;
        int tecnicsCreados = 0;
        int titularesCreados = 0;
        int errores = 0;
        int sinXML = 0;

        try {
            IUserInformationPlugin plugin = PinbalAdminPluginsManager.getUserInformationPluginInstance(false,
                    TipusPluginUserInfo.LDAP);

            // Todas las solicitudes
            Where where = SolicitudFields.ORGANID.isNotNull();
            // where = SolicitudFields.SOLICITUDID.equal(18539L);

            List<Solicitud> solicitudes = solicitudLogicaEjb.select(where);

            log.info("Encontradas " + solicitudes.size() + " solicitudes");

            for (Solicitud solicitud : solicitudes) {
                totalSolicitudes++;

                try {
                    Map<TipoContacto, Integer> resultado = migrarContactosSolicitud(solicitud, plugin);

                    if (resultado.isEmpty()) {
                        sinXML++;
                    } else {
                        solicitantesCreados += resultado.getOrDefault(TipoContacto.SOLICITANT, 0);
                        gestAutCreados += resultado.getOrDefault(TipoContacto.GEST_AUT, 0);
                        auditoriasCreados += resultado.getOrDefault(TipoContacto.AUDITORIA, 0);
                        tecnicsCreados += resultado.getOrDefault(TipoContacto.TECNIC, 0);
                        titularesCreados += resultado.getOrDefault(TipoContacto.TITULAR, 0);
                    }

                    if (totalSolicitudes % 100 == 0) {
                        log.info("Procesadas " + totalSolicitudes + " de " + solicitudes.size());
                    }

                } catch (Exception e) {
                    errores++;
                    log.error("Error procesando solicitud " + solicitud.getSolicitudID() + ": " + e.getMessage(), e);
                }
            }

            String mensaje = String.format(
                    "Migración completa finalizada.%n%n" +
                            "Solicitudes procesadas: %d%n%n" +
                            "Contactos creados/actualizados:%n" +
                            "- Solicitantes: %d%n" +
                            "- Gestión/Autorización: %d%n" +
                            "- Auditorías: %d%n" +
                            "- Técnicos: %d%n" +
                            "- Titulares: %d%n%n" +
                            "Errores: %d%n" +
                            "Solicitudes sin XML válido: %d",
                    totalSolicitudes, solicitantesCreados, gestAutCreados,
                    auditoriasCreados, tecnicsCreados, titularesCreados, errores, sinXML);

            log.info(mensaje.replace("%n", "\n"));

            if (errores > 0) {
                HtmlUtils.saveMessageWarning(request, mensaje);
            } else {
                HtmlUtils.saveMessageSuccess(request, mensaje);
            }

        } catch (Exception e) {
            log.error("Error durante migración completa: " + e.getMessage(), e);
            HtmlUtils.saveMessageError(request, "Error: " + e.getMessage());
        }

        return "redirect:" + CONTEXTWEB + "/list";
    }

    /**
     * Actualiza titulares que tienen NIF pero no tienen contacto asociado.
     * Busca el contacto por NIF y si hay más de uno, usa el email para elegir el correcto.
     * Si no existe contacto, lo crea.
     */
    @RequestMapping(value = "/actualizarTitularesNoMigrados", method = RequestMethod.GET)
    public String actualizarTitularesNoMigrados(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {

        log.info("=== INICIO ACTUALIZACIÓN TITULARES NO MIGRADOS ===");

        int totalSolicitudes = 0;
        int titularesActualizados = 0;
        int titularesCreados = 0;
        int titularesCreatedConLDAP = 0;
        int errores = 0;
        int sinNif = 0;
        List<String> detalles = new ArrayList<>();

        try {
            // Obtener plugin de UserInfo (LDAP)
            IUserInformationPlugin plugin = PinbalAdminPluginsManager.getUserInformationPluginInstance(false,
                    TipusPluginUserInfo.LDAP);

            // Buscar solicitudes con NIF de titular pero sin contacto asociado
            Where where = Where.AND(
                    SolicitudFields.TITULARFIRMANIFOLD.isNotNull(),
                    SolicitudFields.CONTACTETITULARID.isNull());

            List<Solicitud> solicitudes = solicitudLogicaEjb.select(where);
            totalSolicitudes = solicitudes.size();

            log.info("Encontradas " + totalSolicitudes + " solicitudes con titular sin migrar");

            for (Solicitud solicitud : solicitudes) {
                try {
                    String nif = solicitud.getTitularFirmaNifOld();
                    String email = solicitud.getTitularfirmaemailold();
                    String nombre = solicitud.getTitularfirmanomold();

                    if (nif == null || nif.trim().isEmpty()) {
                        sinNif++;
                        log.warn("Solicitud " + solicitud.getSolicitudID() + " tiene NIF vacío");
                        continue;
                    }

                    nif = nif.toUpperCase().trim();

                    // Buscar contactos con ese NIF
                    Where whereContacte = ContacteFields.NIF.equal(nif);
                    List<Contacte> contactes = contacteLogicaEjb.select(whereContacte);

                    Contacte contacteSeleccionado = null;

                    if (contactes != null && !contactes.isEmpty()) {
                        if (contactes.size() == 1) {
                            // Solo hay uno, lo usamos directamente
                            contacteSeleccionado = contactes.get(0);
                            log.info("Solicitud " + solicitud.getSolicitudID() + ": encontrado 1 contacto con NIF " + nif);
                        } else {
                            // Hay más de uno, intentar filtrar por email
                            log.info("Solicitud " + solicitud.getSolicitudID() + ": encontrados " + contactes.size() + " contactos con NIF " + nif);
                            
                            if (email != null && !email.trim().isEmpty()) {
                                String emailNormalizado = email.toLowerCase().trim();
                                for (Contacte c : contactes) {
                                    if (c.getMail() != null && c.getMail().equalsIgnoreCase(emailNormalizado)) {
                                        contacteSeleccionado = c;
                                        log.info("Solicitud " + solicitud.getSolicitudID() + ": seleccionado contacto por email " + email);
                                        break;
                                    }
                                }
                            }
                            
                            // Si no encontramos por email, usar el primero
                            if (contacteSeleccionado == null) {
                                contacteSeleccionado = contactes.get(0);
                                log.info("Solicitud " + solicitud.getSolicitudID() + ": usando primer contacto encontrado (no se pudo filtrar por email)");
                            }
                        }

                        // Actualizar la solicitud con el contacto existente
                        solicitud.setContacteTitularID(contacteSeleccionado.getContacteID());
                        solicitudLogicaEjb.update(solicitud);
                        titularesActualizados++;
                        
                        String detalle = String.format("Solicitud %d: asociado contacto existente ID=%d (NIF=%s)",
                                solicitud.getSolicitudID(), contacteSeleccionado.getContacteID(), nif);
                        detalles.add(detalle);
                        log.info(detalle);

                    } else {
                        // No existe contacto, buscar primero en LDAP y luego crearlo
                        log.info("Solicitud " + solicitud.getSolicitudID() + ": no existe contacto con NIF " + nif + ", buscando en LDAP...");
                        
                        String nom = null;
                        String llinatge1 = null;
                        String llinatge2 = null;
                        String telefon = null;
                        String username = null;
                        String nombreCompleto = nombre;
                        boolean encontradoEnLDAP = false;

                        // Buscar en LDAP por NIF
                        UserInfo userInfo = obtenerUserInfoConCache(nif, plugin);
                        if (userInfo != null) {
                            log.info("Solicitud " + solicitud.getSolicitudID() + ": encontrado en LDAP - " + userInfo.getFullName());
                            nom = userInfo.getName();
                            llinatge1 = userInfo.getSurname1();
                            llinatge2 = userInfo.getSurname2();
                            telefon = userInfo.getPhoneNumber();
                            username = userInfo.getUsername();
                            nombreCompleto = userInfo.getFullName();
                            // Si LDAP tiene email, usarlo (más fiable que el de la solicitud)
                            if (userInfo.getEmail() != null && !userInfo.getEmail().trim().isEmpty()) {
                                email = userInfo.getEmail();
                            }
                            encontradoEnLDAP = true;
                            titularesCreatedConLDAP++;
                        } else {
                            log.info("Solicitud " + solicitud.getSolicitudID() + ": no encontrado en LDAP, usando datos de la solicitud");
                            // Usar datos de la solicitud (ya asignados a variables nom, email, etc.) Para no perder datos..

                            nom = solicitud.getTitularfirmanomold();
                            llinatge1 = null;
                            llinatge2 = null;
                            email = solicitud.getTitularfirmaemailold();
                        }

                        // Crear el contacto con los datos obtenidos (de LDAP o de la solicitud)
                        Contacte nuevoContacte = contacteLogicaEjb.buscarOCrearContacte(
                                nif, nom, llinatge1, llinatge2, null, telefon, email, username, nombreCompleto);

                        if (nuevoContacte != null) {
                            solicitud.setContacteTitularID(nuevoContacte.getContacteID());
                            solicitudLogicaEjb.update(solicitud);
                            titularesCreados++;
                            
                            String fuente = encontradoEnLDAP ? "LDAP" : "Solicitud";
                            String detalle = String.format("Solicitud %d: creado nuevo contacto ID=%d (NIF=%s, Fuente=%s, Nombre=%s)",
                                    solicitud.getSolicitudID(), nuevoContacte.getContacteID(), nif, fuente, nombreCompleto);
                            detalles.add(detalle);
                            log.info(detalle);
                        } else {
                            errores++;
                            log.error("No se pudo crear contacto para solicitud " + solicitud.getSolicitudID());
                        }
                    }

                } catch (Exception e) {
                    errores++;
                    log.error("Error procesando solicitud " + solicitud.getSolicitudID() + ": " + e.getMessage(), e);
                }
            }

            String mensaje = String.format(
                    "Actualización de titulares finalizada.%n%n" +
                            "Solicitudes procesadas: %d%n%n" +
                            "Titulares actualizados (contacto existente): %d%n" +
                            "Titulares creados (contacto nuevo): %d%n" +
                            "  - Creados con datos de LDAP: %d%n" +
                            "  - Creados con datos de solicitud: %d%n" +
                            "Errores: %d%n" +
                            "Sin NIF válido: %d",
                    totalSolicitudes, titularesActualizados, titularesCreados, 
                    titularesCreatedConLDAP, (titularesCreados - titularesCreatedConLDAP), errores, sinNif);

            log.info(mensaje.replace("%n", "\n"));

            // Mostrar primeros detalles
            if (!detalles.isEmpty()) {
                StringBuilder mensajeDetallado = new StringBuilder(mensaje);
                mensajeDetallado.append("%n%n=== Primeras 20 actualizaciones ===%n");
                int limite = Math.min(20, detalles.size());
                for (int i = 0; i < limite; i++) {
                    mensajeDetallado.append(detalles.get(i)).append("%n");
                }
                if (detalles.size() > 20) {
                    mensajeDetallado.append(String.format("... y %d más%n", detalles.size() - 20));
                }
                mensajeDetallado.append("%nVer log para detalles completos");
                mensaje = mensajeDetallado.toString();
            }

            if (errores > 0) {
                HtmlUtils.saveMessageWarning(request, mensaje);
            } else {
                HtmlUtils.saveMessageSuccess(request, mensaje);
            }

            log.info("=== FIN ACTUALIZACIÓN TITULARES NO MIGRADOS ===");
            log.info(mensaje.replace("%n", "\n"));



        } catch (Exception e) {
            log.error("Error durante actualización de titulares: " + e.getMessage(), e);
            HtmlUtils.saveMessageError(request, "Error: " + e.getMessage());
        }

        return "redirect:" + CONTEXTWEB + "/list";
    }

    /**
     * Migra todos los contactos de una solicitud.
     * 
     * @return Map con contador de contactos creados por tipo
     */
    private Map<TipoContacto, Integer> migrarContactosSolicitud(Solicitud solicitud,
            IUserInformationPlugin plugin) throws I18NException {

        Map<TipoContacto, Integer> resultado = new HashMap<>();
        log.info("Procesando solicitud ID: " + solicitud.getSolicitudID());

        Properties xmlProps = obtenerPropertiesXML(solicitud);
        if (xmlProps == null) {
            log.warn("Solicitud " + solicitud.getSolicitudID() + " no tiene XML válido");
            return resultado;
        }

        Map<TipoContacto, DatosContacto> todosContactos = extraerTodosContactosDesdeXML(xmlProps);
        log.info("Extraídos " + todosContactos.size() + " tipos de contactos del XML");

        for (TipoContacto tipo : TipoContacto.values()) {
            if (migrarContactoConDatos(solicitud, tipo, todosContactos.get(tipo), plugin)) {
                resultado.put(tipo, 1);
                log.info("✓ Contacto " + tipo + " migrado para solicitud " + solicitud.getSolicitudID());
            }
        }

        if (resultado.isEmpty()) {
            log.info("No se migraron contactos para solicitud " + solicitud.getSolicitudID());
        }

        return resultado;
    }

    /**
     * Migra un contacto individual usando datos ya extraídos del XML.
     * 
     * @return true si se creó/actualizó el contacto, false si no había datos
     */
    private boolean migrarContactoConDatos(Solicitud solicitud, TipoContacto tipo, DatosContacto datosXML,
            IUserInformationPlugin plugin) throws I18NException {

        if (datosXML == null) {
            log.info("No hay datos XML para contacto " + tipo + " en solicitud " + solicitud.getSolicitudID());
            return false;
        }

        log.info("Procesando contacto " + tipo + ": NIF=" + datosXML.nif + ", Nombre=" + datosXML.nom);

        DatosContacto datos = completarConLDAP(datosXML, plugin);

        if (!validarDatosMinimos(datos)) {
            log.warn("Datos mínimos no válidos para contacto " + tipo + " en solicitud " + solicitud.getSolicitudID());
            return false;
        }

        Contacte contacte = contacteLogicaEjb.buscarOCrearContacte(
                datos.nif, datos.nom, datos.llinatge1, datos.llinatge2,
                datos.carrec, datos.telefon, datos.mail, datos.username, datos.nombreCompleto);

        if (contacte != null && contacte.getContacteID() > 0) {
            actualizarContactoSolicitud(tipo, solicitud, contacte);
            log.info("Contacte ID " + contacte.getContacteID() + " asignado como " + tipo);
            return true;
        }

        log.error("No se pudo crear/buscar contacto " + tipo + " para solicitud " + solicitud.getSolicitudID());
        return false;
    }

    // ============================== CLASES AUXILIARES
    // ==============================

    private enum TipoContacto {
        SOLICITANT, // DATOS_REGISTRO - Persona autenticada
        GEST_AUT, // SECD - Gestión y Autorización
        AUDITORIA, // SECE - Auditorías
        TECNIC, // SECF - Técnico
        TITULAR // SECG - Titular
    }

    private static class DatosContacto {
        String nif;
        String nom;
        String llinatge1;
        String llinatge2;
        String carrec;
        String telefon;
        String mail;
        String username;
        String nombreCompleto;

        public DatosContacto(String nif, String nom, String llinatge1, String llinatge2,
                String carrec, String telefon, String mail, String username, String nombreCompleto) {

            // Normalizar datos finales
            this.nif = nif != null ? nif.toUpperCase() : null;
            this.nom = toNombrePropio(nom);
            this.llinatge1 = toNombrePropio(llinatge1);
            this.llinatge2 = toNombrePropio(llinatge2);
            this.carrec = toNombrePropio(carrec);
            this.telefon = normalizarTelefono(telefon);
            this.mail = mail != null ? mail.toLowerCase() : null;
            this.username = username != null ? username.toLowerCase() : null;
            this.nombreCompleto = toNombrePropio(nombreCompleto);
        }
    }

    /**
     * Convierte texto a formato Nombre Propio (primera letra mayúscula).
     */
    public static String toNombrePropio(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return texto;
        }

        String[] palabras = texto.toLowerCase().trim().split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                resultado.append(Character.toUpperCase(palabra.charAt(0)))
                        .append(palabra.substring(1))
                        .append(" ");
            }
        }

        return resultado.toString().trim();
    }

    /**
     * Normaliza y valida números de teléfono españoles.
     * - Elimina espacios, puntos, guiones
     * - Valida formato: 9 dígitos numéricos
     * - Valida prefijo: debe empezar por 6, 7, 8 o 9
     * 
     * @return Número normalizado o null si no es válido
     */
    public static String normalizarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return null;
        }

        // Eliminar espacios, puntos, guiones y otros caracteres no numéricos
        String limpio = telefono.replaceAll("[^0-9]", "");
                
        return limpio;
    }

    // ============================== EXTRACCIÓN DE DATOS
    // ==============================

    /**
     * Extrae todos los contactos desde Properties XML de una sola vez.
     * 
     * @param xmlProps Properties parseadas del XML (puede ser null)
     * @return Map con datos de cada tipo de contacto (pueden ser null si no hay
     *         datos)
     */
    private Map<TipoContacto, DatosContacto> extraerTodosContactosDesdeXML(Properties xmlProps) {
        Map<TipoContacto, DatosContacto> resultado = new HashMap<>();

        for (TipoContacto tipo : TipoContacto.values()) {
            DatosContacto datos = extraerContactoDesdeProps(tipo, xmlProps);
            resultado.put(tipo, datos);
        }

        return resultado;
    }

    /**
     * Extrae un contacto específico desde Properties ya parseadas.
     */
    private DatosContacto extraerContactoDesdeProps(TipoContacto tipoContacto, Properties prop) {
        if (prop == null) {
            return null;
        }

        String sufijo = getSufijoXML(tipoContacto);
        String prefijo = getPrefijoXML(tipoContacto);
        if (sufijo == null || prefijo == null) {
            return null;
        }

        String nif, nom, llinatge1, llinatge2, carrec, telefon, mail, nombreCompleto;

        // SOLICITANT usa nombres de campos diferentes en DATOS_REGISTRO
        if (tipoContacto == TipoContacto.SOLICITANT) {
            nif = prop.getProperty(prefijo + "NIF");
            nom = prop.getProperty(prefijo + "NOMBRE");
            llinatge1 = prop.getProperty(prefijo + "APELLIDO1");
            llinatge2 = prop.getProperty(prefijo + "APELLIDO2");
            carrec = null;
            telefon = prop.getProperty(prefijo + "TELEFONO");
            mail = prop.getProperty(prefijo + "EMAIL");
            nombreCompleto = prop.getProperty(prefijo + "NOMBRECOMPLETO");
        } else {
            // GEST_AUT, AUDITORIA, TECNIC, TITULAR usan estructura estándar con sufijo
            nif = prop.getProperty(prefijo + "NIF" + sufijo);
            nom = prop.getProperty(prefijo + "NOMBRE" + sufijo);
            llinatge1 = prop.getProperty(prefijo + "APE1" + sufijo);
            llinatge2 = prop.getProperty(prefijo + "APE2" + sufijo);
            carrec = prop.getProperty(prefijo + "CARGO" + sufijo);
            telefon = prop.getProperty(prefijo + "TELEFONO" + sufijo);
            mail = prop.getProperty(prefijo + "MAIL" + sufijo);
            nombreCompleto = prop.getProperty(prefijo + "NOMOCUL" + sufijo);
        }

        if (nom == null || nom.trim().isEmpty()) {
            return null;
        }

        return new DatosContacto(nif, nom, llinatge1, llinatge2, carrec, telefon, mail, null, nombreCompleto);
    }

    // ============================== VALIDACIÓN Y COMPLETADO
    // ==============================

    private boolean validarDatosMinimos(DatosContacto datos) {
        return datos != null && datos.nom != null && !datos.nom.trim().isEmpty();
    }

    /**
     * Extrae datos de los campos directos de la solicitud.
     * Solo TITULAR tiene campos dedicados en la entidad Solicitud.
     */
    private DatosContacto extraerDatosContactoDesdeSolicitud(TipoContacto tipoContacto, Solicitud solicitud) {
        String nif = null;
        String nom = null;
        String llinatge1 = null;
        String llinatge2 = null;
        String carrec = null;
        String telefon = null;
        String mail = null;
        String username = null;

        switch (tipoContacto) {
            case TITULAR:
//                nif = solicitud.getTitularFirmaNif();
//                nom = solicitud.getTitularFirmaNom();
//                llinatge1 = solicitud.getTitularFirmaLlinatges();
//                mail = solicitud.getTitularFirmaEmail();
                break;

            case GEST_AUT:
//                nom = solicitud.getResponsableProcNom();
//                mail = solicitud.getResponsableProcEmail();
                break;

            case SOLICITANT:
//                nom = solicitud.getPersonaContacte();
//                mail = solicitud.getPersonaContacteEmail();
                break;

            default:
                // AUDITORIA y TECNIC no tienen campos en Solicitud
                break;
        }

        // Los campos de solicitud no incluyen nombreCompleto
        return new DatosContacto(nif, nom, llinatge1, llinatge2, carrec, telefon, mail, username, null);
    }

    /**
     * Completa datos desde LDAP cuando faltan campos.
     * Prioridad: datos XML > datos LDAP
     */
    private DatosContacto completarConLDAP(DatosContacto datosXML, IUserInformationPlugin plugin) {
        if (datosXML == null) {
            return null;
        }

        String nif = datosXML.nif;
        String nom = datosXML.nom;
        String llinatge1 = datosXML.llinatge1;
        String llinatge2 = datosXML.llinatge2;
        String carrec = datosXML.carrec;
        String telefon = datosXML.telefon;
        String mail = datosXML.mail;
        String username = datosXML.username;
        String nombreCompleto = datosXML.nombreCompleto;

        // Intentar completar con LDAP si tenemos NIF
        if (nif != null && !nif.trim().isEmpty()) {
            log.info("Buscando en LDAP por NIF: " + nif);
            UserInfo userInfo = obtenerUserInfoConCache(nif, plugin);
            if (userInfo != null) {
                log.info("Datos encontrados en LDAP para NIF " + nif + ": " + userInfo.getFullName());
                nom = elegirMejorDato(nom, userInfo.getName());
                llinatge1 = elegirMejorDato(llinatge1, userInfo.getSurname1());
                llinatge2 = elegirMejorDato(llinatge2, userInfo.getSurname2());
                telefon = elegirMejorDato(telefon, userInfo.getPhoneNumber());
                mail = elegirMejorDato(mail, userInfo.getEmail());
                username = userInfo.getUsername();
                nombreCompleto = elegirMejorDato(nombreCompleto, userInfo.getFullName());
            } else {
                log.info("No se encontró información en LDAP para NIF: " + nif);
            }
        }
        // Si no hay NIF pero hay email, buscar por email
        else if (mail != null && !mail.trim().isEmpty()) {
            log.info("Buscando en LDAP por email: " + mail);
            UserInfo userInfo = buscarPorEmail(mail, plugin);
            if (userInfo != null) {
                log.info("Usuario encontrado en LDAP por email " + mail + ": NIF=" + userInfo.getAdministrationID());
                nif = elegirMejorDato(nif, userInfo.getAdministrationID());
                nom = elegirMejorDato(nom, userInfo.getName());
                llinatge1 = elegirMejorDato(llinatge1, userInfo.getSurname1());
                llinatge2 = elegirMejorDato(llinatge2, userInfo.getSurname2());
                telefon = elegirMejorDato(telefon, userInfo.getPhoneNumber());
                username = userInfo.getUsername();
                nombreCompleto = elegirMejorDato(nombreCompleto, userInfo.getFullName());
            }
        }

        return new DatosContacto(nif, nom, llinatge1, llinatge2, carrec, telefon, mail, username, nombreCompleto);
    }

    /**
     * Busca usuario por email en LDAP.
     */
    private UserInfo buscarPorEmail(String mail, IUserInformationPlugin plugin) {
        try {
            SearchUsersResult searchResult = plugin.getUsersByPartialEmail(mail);
            List<UserInfo> users = searchResult.getUsers();
            if (users != null && !users.isEmpty()) {
                return users.get(0);
            }
        } catch (Exception e) {
            log.info("No se pudo buscar usuario por email: " + e.getMessage());
        }
        return null;
    }

    /**
     * Elige el mejor dato: no null, no vacío.
     */
    private String elegirMejorDato(String datoXML, String datoUserInfo) {
        if (datoXML != null && !datoXML.trim().isEmpty()) {
            return datoXML;
        }
        return datoUserInfo;
    }

    // ============================== MÉTODOS AUXILIARES
    // ==============================

    private Properties obtenerPropertiesXML(Solicitud solicitud) {
        try {
            Long fitxerID = solicitud.getSolicitudXmlID();
            if (fitxerID == null) {
                log.warn("Solicitud " + solicitud.getSolicitudID() + " no tiene XML asociado");
                return null;
            }

            Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
            if (prop == null) {
                log.warn("No se pudo parsear XML de solicitud " + solicitud.getSolicitudID());
                return null;
            }

            return prop;
        } catch (Exception e) {
            log.error("Error obteniendo Properties del XML: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * Mapea tipo de contacto a sufijo XML.
     * SOLICITANT="" (sin sufijo, DATOS_REGISTRO)
     * GEST_AUT=SECD, AUDITORIA=SECE, TECNIC=SECF, TITULAR=SECG
     */
    private String getSufijoXML(TipoContacto tipoContacto) {
        switch (tipoContacto) {
            case SOLICITANT:
                return ""; // DATOS_REGISTRO sin sufijo
            case GEST_AUT:
                return "SECD";
            case AUDITORIA:
                return "SECE";
            case TECNIC:
                return "SECF";
            case TITULAR:
                return "SECG";
            default:
                return null;
        }
    }

    private String getPrefijoXML(TipoContacto tipoContacto) {
        switch (tipoContacto) {
            case SOLICITANT:
                return "FORMULARIO.DATOS_REGISTRO.";
            case GEST_AUT:
            case AUDITORIA:
            case TECNIC:
            case TITULAR:
                return "FORMULARIO.DATOS_SOLICITUD.";
            default:
                return null;
        }
    }

    /**
     * Obtiene UserInfo del plugin con caché para evitar consultas repetidas.
     */
    private UserInfo obtenerUserInfoConCache(String nif, IUserInformationPlugin plugin) {
        if (nif == null || nif.trim().isEmpty()) {
            return null;
        }

        nif = nif.trim();

        if (cacheUserInfo.containsKey(nif)) {
            return cacheUserInfo.get(nif);
        }

        try {
            UserInfo userInfo = plugin.getUserInfoByAdministrationID(nif);
            cacheUserInfo.put(nif, userInfo);
            return userInfo;
        } catch (Exception e) {
            log.error("Error obteniendo UserInfo para NIF " + nif + ": " + e.getMessage());
            return null;
        }
    }

    private void actualizarContactoSolicitud(TipoContacto tipoContacto, Solicitud soli, Contacte contacto)
            throws I18NException {
        switch (tipoContacto) {
            case SOLICITANT:
                soli.setContacteSolicitantID(contacto.getContacteID());
                break;

            case GEST_AUT:
                soli.setContacteGestAutID(contacto.getContacteID());
                break;

            case AUDITORIA:
                soli.setContacteAuditoriaID(contacto.getContacteID());
                break;

            case TECNIC:
                soli.setContacteTecnicID(contacto.getContacteID());
                break;

            case TITULAR:
                soli.setContacteTitularID(contacto.getContacteID());
                break;

            default:
                break;
        }
        solicitudLogicaEjb.update(soli);
    }
}
