package org.fundaciobit.pinbaladmin.back.controller.all;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.IncidenciaTecnicaController;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaForm;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.EntitatLogicaService;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.PinfoDataLogicaService;
import org.fundaciobit.pinbaladmin.logic.PinfoLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager.TipusPluginUserInfo;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Pinfo;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.persistence.EntitatJPA;
import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.pinbal.client.comu.LogLevel;
import es.caib.pinbal.client.procediments.ProcedimentClient;
import es.caib.pinbal.client.recobriment.v2.ClientRecobriment;
import es.caib.pinbal.client.recobriment.v2.Entitat;
import es.caib.pinbal.client.serveis.ServeiClient;
import es.caib.pinbal.client.usuaris.UsuariClient;
import es.caib.pinbal.client.usuaris.UsuariEntitat;

/**
 * 
 * @author ptrias 16 oct 2024 10:11:28
 */

@Controller
@RequestMapping(value = IncidenciaPinfoPublicController.CONTEXT_WEB)
@SessionAttributes(types = { IncidenciaTecnicaForm.class, IncidenciaTecnicaFilterForm.class })
public class IncidenciaPinfoPublicController extends IncidenciaTecnicaController {

	public static final String CONTEXT_WEB = "/public/incidenciapinfo";

	@EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
	protected IncidenciaTecnicaLogicaService incidenciaTecnicaLogicaEjb;

	@EJB(mappedName = PinfoLogicaService.JNDI_NAME)
	protected PinfoLogicaService pinfoLogicEjb;

	@EJB(mappedName = PinfoDataLogicaService.JNDI_NAME)
	protected PinfoDataLogicaService pinfoDataLogicEjb;
	
	@EJB(mappedName = OrganLogicaService.JNDI_NAME)
	protected OrganLogicaService organLogicEjb;
	
	@EJB(mappedName = EntitatLogicaService.JNDI_NAME)
	protected EntitatLogicaService entitatLogicEjb;
	
	@Override
	public String getTileForm() {
		return "incidenciaTecnicaPinfoFormOperador";
	}

	@Override
	public String getTileList() {
		return "incidenciaTecnicaPinfoListOperador";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return this.getClass().getName() + "_FilterForm";
	}

	@Override
	public IncidenciaTecnicaForm getIncidenciaTecnicaForm(IncidenciaTecnicaJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {
		IncidenciaTecnicaForm form = super.getIncidenciaTecnicaForm(_jpa, __isView, request, mav);
        request.setAttribute("desplegableOrgans", true);

		if (form.isNou()) {
			form.setTitleCode("pinfo.create");
			
			IncidenciaTecnicaJPA incidencia = form.getIncidenciaTecnica();

			incidencia.setTipus(Constants.INCIDENCIA_TIPUS_ROLEPERMISOS);
			form.addHiddenField(IncidenciaTecnicaFields.TIPUS);

			incidencia.setEstat(Constants.ESTAT_INCIDENCIA_OBERTA);
			form.addHiddenField(IncidenciaTecnicaFields.ESTAT);

			incidencia.setDataInici(new Timestamp(System.currentTimeMillis()));
			form.addHiddenField(IncidenciaTecnicaFields.DATAINICI);
			form.addHiddenField(IncidenciaTecnicaFields.DATAFI);

			
			
			incidencia.setOperador("pinbaladmin");
			incidencia.setCreador("pinbladmin");
			form.addHiddenField(IncidenciaTecnicaFields.OPERADOR);
			form.addHiddenField(IncidenciaTecnicaFields.CREADOR);

			form.addHiddenField(IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT);
			form.addHiddenField(IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA);

			
//			String token = (String) request.getSession().getAttribute("token");
//			incidencia.setDescripcio("El meu Token es: " + token);
//			incidencia.setNomEntitat(token);
			
			Properties properties = (Properties) request.getSession().getAttribute("properties");
			String nomComplet = properties.getProperty("Nom") + " " + properties.getProperty("Cognom1") + " " + properties.getProperty("Cognom2");
			incidencia.setContacteNom(nomComplet);
			form.addReadOnlyField(IncidenciaTecnicaFields.CONTACTENOM);

			form.addLabel(IncidenciaTecnicaFields.NOMENTITAT, "departament.departament");
			setDadesTest(incidencia);

			String usuariNIF = properties.getProperty("NIF");
			String username = properties.getProperty("Username");

			request.getSession().setAttribute("usuariNIF", usuariNIF);
			request.getSession().setAttribute("usuariNom", nomComplet);
			request.getSession().setAttribute("usuariUsername", username);

			String dir3Solicitant = getCodiDIR3FromNif(usuariNIF);
			Long organID = organLogicEjb.executeQueryOne(OrganFields.ORGANID, OrganFields.DIR3.equal(dir3Solicitant));
			if (organID != null) {
				incidencia.setOrganid(organID);
				form.addReadOnlyField(IncidenciaTecnicaFields.ORGANID);
			}
			
			request.getSession().setAttribute("usuariData", usuariNIF + " - " + username);
//DEL			request.getSession().setAttribute("entitats", pinfoLogicEjb.getEntitats());

			form.setAttachedAdditionalJspCode(true);
			mav.addObject("isPinfo", true);
		}

		return form;
	}

	private void setDadesTest(IncidenciaTecnicaJPA incidencia){
		incidencia.setDescripcio("Descripció de test");
		incidencia.setContacteTelefon("971971971");
		incidencia.setContacteEmail("ptrias@fundaciobit.org");
		
		incidencia.setNomEntitat("Govern Digital");
		incidencia.setTitol("Titol de test");
	}
	
	public String getCodiDIR3FromNif(String nif) throws I18NException {
    	boolean debug = false;

    	IUserInformationPlugin plugin = PinbalAdminPluginsManager.getUserInformationPluginInstance(debug, TipusPluginUserInfo.LDAP);

		UserInfo userInfo = null;
		try {
			userInfo = plugin.getUserInfoByAdministrationID(nif);
			log.info("UserInfo obtingut de NIF " + nif + ": " + userInfo);
		} catch (Exception e) {
			throw new I18NException("error.plugin.userinformation.userinfonotfound", e.getMessage());
		}

		log.info("UserInfo de NIF " + nif + ": " + userInfo);
		if (userInfo == null) {
			throw new I18NException("error.plugin.userinformation.userinfonotfound", "NIF: " + nif);
		}
		
		String dir3 = userInfo.getDir3();
		log.info("DIR3 de NIF " + nif + " es: " + dir3);
		
		if (dir3 != null && dir3.trim().length() > 0) {
			log.info("Codi DIR3 de NIF " + nif + " es: " + dir3);
			return dir3;
		}else {
			String username = userInfo.getUsername();
			return getCodiDIR3(username);
		}
	}
	
    public String getCodiDIR3(String username) throws I18NException {

    	boolean debug = false;
    	boolean caib = true;
    	
    	
        IEstructuraOrganitzativaPlugin instance = PinbalAdminPluginsManager.getEstructuraOrganitzativaPlugin(debug, caib);
        
        log.info("Obtenint codi DIR3 de l'usuari: " + username + " amb plugin Estr. Org.: " + instance);
        
        
        String codiDIR3;
        try {
            codiDIR3 = instance.getDir3DepartamentDireccioGeneral(username);
            
            if (codiDIR3 != null && codiDIR3.trim().length() > 0) {
				log.info("Codi DIR3 de " + username + " es: " + codiDIR3);
                return codiDIR3;
            }else {
                throw new Exception ("El codi DIR3 de l'usuari " + username + " es null o buit ]" + codiDIR3 + "[");
            }

        } catch (Exception e) {
            log.error("Error obtenint codi DIR3 de l'usuari " + username + ": " + e.getMessage(), e);
//            throw new I18NException("error.plugin.estructuraorganitzativa.dir3notfound", username);
            throw new I18NException("genapp.comodi", e.getMessage());
        }
    }

	@Override
	public IncidenciaTecnicaFilterForm getIncidenciaTecnicaFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {
		IncidenciaTecnicaFilterForm filterForm = super.getIncidenciaTecnicaFilterForm(pagina, mav, request);

		log.info("getIncidenciaTecnicaFilterForm: " + filterForm);

		return filterForm;
	}

	@Override
	public IncidenciaTecnicaJPA create(HttpServletRequest request, IncidenciaTecnicaJPA incidenciaTecnica)
			throws I18NException, I18NValidationException {

		IncidenciaTecnicaJPA it;
		it = (IncidenciaTecnicaJPA) incidenciaTecnicaLogicaEjb.create(incidenciaTecnica);

		//Hem de crear el PINFO amb les dades de la incidencia
		Long incidenciaID = it.getIncidenciaTecnicaID();
		Long estat = Constants.ESTAT_PINFO_CREANT;
		String solicitantNIF = (String) request.getSession().getAttribute("usuariNIF");
		
		// String entitat = request.getParameter("incidenciaTecnica.entitatid");
		// log.info("Entitat: " + entitat);
		
		Long organID = it.getOrganid();
		Organ organ = organLogicEjb.findByPrimaryKey(organID);
		
		String entitat = getEntiatPinfoFromOrgan(organ);
		
		log.info("Entitat per Pinfo: " + entitat);
		
		Long fitxerID = null;
		Long fitxerFirmatID = null;
		String portafibid = null;
		String destinatariNIF = null;
		String destinatariNom = null;
		String missatgePinbal = null;
		
		PinfoJPA pinfo = new PinfoJPA(incidenciaID, entitat, solicitantNIF, estat, fitxerID, fitxerFirmatID, portafibid, destinatariNIF, destinatariNom, missatgePinbal);
		Pinfo Pinfo = pinfoLogicEjb.create(pinfo);
		
		log.info("Creant Pinfo " + Pinfo.getPinfoID());
		
		return it;
	}
	
	private String objectToJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return mapper.writeValueAsString(obj);
	}
	
	private String getEntiatPinfoFromOrgan(Organ organ) {
		
    	final String baseUrl = Configuracio.getApiPinbalClientUrl();
    	final String username = Configuracio.getApiPinbalClientUsername();
    	final String password = Configuracio.getApiPinbalClientPassword();
    	final LogLevel logLevel = LogLevel.INFO;

        log.info("Creant Clients");

        UsuariClient usuariClient = new UsuariClient(baseUrl, username, password, logLevel);
		ServeiClient serveiClient = new ServeiClient(baseUrl, username, password, logLevel);
		ProcedimentClient procedimentClient = new ProcedimentClient(baseUrl, username, password, logLevel);
		
		ClientRecobriment clientRecobriment = new ClientRecobriment(baseUrl, username, password, logLevel);
		
		try {
			serveiClient.enableLogginFilter();
			
			usuariClient.enableLogginFilter();
			UsuariEntitat usuari = usuariClient.getUsuari("e45186147w", "GOVERN");
			log.info("-> Usuari Pinbal: " + objectToJsonString(usuari));
			
			
			procedimentClient.enableLogginFilter();
			
			clientRecobriment.enableLogginFilter();
			List<Entitat> entitats = clientRecobriment.getEntitats();
			
			for (Entitat entitat : entitats) {
				log.info("-> Entitat Pinbal: " + objectToJsonString(entitat));
			}

		} catch (IOException e) {
			
			log.error("Error obteniendo estadistiques d'usuaris: " + e.getMessage(), e);
			
		}

		
		
		
		log.info("Clients creats");
		
		
		
		if (organ == null || organ.getEntitatid() == null) {
			return null;
		}
		
		try {
			Long entitatID = organ.getEntitatid();
			EntitatJPA entitat = entitatLogicEjb.findByPrimaryKey(entitatID);
			
			if (entitat == null || entitat.getCIF() == null) {
				return null;
			}
			
			String cif = entitat.getCIF().trim().toUpperCase();
			
			switch (cif) {
				case "S0711001H":
					return "GOVERN";
					
				case "Q0700494H":
					return "FOGAIBA";
					
					
				default:
					return null;
			}
		} catch (Exception e) {
			log.error("Error obteniendo entitat de organ: " + organ.getOrganid(), e);
			return null;
		}
	}

	@RequestMapping(value = "/new/{token}", method = RequestMethod.GET)
	public String obtenirDadesFitxerToken(HttpServletRequest request, HttpServletRequest response, @PathVariable("token") java.lang.String token) {
		
		log.info("obtenirDadesFitxerToken token: " + token);
		File file = new File(FileSystemManager.getFilesPath(), token + ".front");		
		
		 Properties properties= new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			log.error("An error occurred." + e.getMessage(), e);
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("properties", properties);
		request.getSession().setAttribute("token", token);
        return "redirect:" + CONTEXT_WEB + "/new";
	}

	@Override
	public void preValidate(HttpServletRequest request, IncidenciaTecnicaForm incidenciaTecnicaForm,
			BindingResult result) throws I18NException {
		super.preValidate(request, incidenciaTecnicaForm, result);
		
		{
			// Fer que els camps siguin obligatoris
			IncidenciaTecnicaJPA incidenciaTecnica = incidenciaTecnicaForm.getIncidenciaTecnica();
						
			if (incidenciaTecnica.getContacteNom() == null || incidenciaTecnica.getContacteNom().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(result, get(CONTACTENOM), "genapp.validation.required",
                        new Object[] { I18NUtils.tradueix(CONTACTENOM.fullName) });
			}
			if (incidenciaTecnica.getContacteTelefon() == null || incidenciaTecnica.getContacteTelefon().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(result, get(CONTACTETELEFON), "genapp.validation.required",
						new Object[] { I18NUtils.tradueix(CONTACTETELEFON.fullName) });
			}
			if (incidenciaTecnica.getNomEntitat() == null || incidenciaTecnica.getNomEntitat().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(result, get(ORGANID), "genapp.validation.required",
						new Object[] { I18NUtils.tradueix(ORGANID.fullName) });
			}
		}
	}
	
	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, IncidenciaTecnicaForm incidenciaTecnicaForm) {
		// Despres de crear la incidencia, ha de crear el PINFO, i redirigir a la pagina per afegir pinfodatas.
		log.info("getRedirectWhenCreated");
		
		//guardar inciencicaid a sessio
		request.getSession().setAttribute("incidenciaId", incidenciaTecnicaForm.getIncidenciaTecnica().getIncidenciaTecnicaID());
		
		return "redirect:" + PinfoDataPublicController.CONTEXT_WEB + "/list";
	}
	
    @Override
    public List<StringKeyValue> getReferenceListForOrganid(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        
        if (where != null) {
        }
        
        List<Organ> organs = organLogicEjb.select(where);

        for (Organ organ : organs) {

            Organ aux = organ;
            List<String> jerarquia = new ArrayList<String>();
//            log.info("Organ Gestor: " + "(" + aux.getDir3() + ") " + aux.getNom());
            jerarquia.add("(" + aux.getDir3() + ") " + aux.getNom());

            if (where != null) {
                while (aux.getCif() == null && aux.getDir3pare() != null) {
                    List<Organ> listAux = organLogicEjb.select(OrganFields.DIR3.equal(aux.getDir3pare()));
                    aux = listAux.get(0);
//                    log.info("pare: " + "(" + aux.getDir3() + ") " + aux.getNom());
                    jerarquia.add("(" + aux.getDir3() + ") " + aux.getNom());
                }
            }
            String str = String.join("|", jerarquia);

            __tmp.add(new StringKeyValue(String.valueOf(organ.getOrganid()), str));
        }

        return __tmp;
        //        return organRefList.getReferenceList(OrganFields.ORGANID, where);
    }    
    
}
