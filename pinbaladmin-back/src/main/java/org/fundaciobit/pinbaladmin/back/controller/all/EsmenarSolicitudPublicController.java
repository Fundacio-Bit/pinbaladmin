package org.fundaciobit.pinbaladmin.back.controller.all;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.entity.ModificacioSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = EsmenarSolicitudPublicController.CONTEXT_WEB)
@SessionAttributes(types = { ModificacioSolicitudForm.class, ModificacioSolicitudFilterForm.class })
public class EsmenarSolicitudPublicController extends ModificarSolicitudPublicController {
	
	public static final String CONTEXT_WEB = "/public/esmenarSolicitud";
	public static final String MOD_SOLI_ID = "modsoliID";
	public static final String SOLICITUD_ID = "solicitudID";

	@EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
	protected InfoMadridLogicaService infoMadridLogicaEjb;

	@Override
	public boolean isEsmena() {
		return true;
	}

	@RequestMapping(value = "/tramitEsmena/{solicitudID}", method = RequestMethod.GET)
	public String obtenirDadesFitxerToken(HttpServletRequest request, HttpServletRequest response,
			@PathVariable("solicitudID") java.lang.Long solicitudID) throws I18NException {

		log.info("tramitEsmena solicitudID: " + solicitudID);
		// Crear la modificacion, asignar todos los parametros, y redirigir a edit.

		ModificacioSolicitudJPA modSolicitud = new ModificacioSolicitudJPA();

		assignarSolicitudAModificacio(modSolicitud, solicitudID);

		Solicitud soli = solicitudLogicaEjb.findByPrimaryKey(solicitudID);

		modSolicitud.setContactenom(soli.getPersonaContacte());
		modSolicitud.setContactemail(soli.getPersonaContacteEmail());
		modSolicitud.setEsmena(isEsmena()); // Es una esmena

		modSolicitud.setEstatModificacio(Constants.ESTAT_MODIFICACIO_SOLICITUD_CREACIO); // o el campo correcto

		modSolicitud.setSolicitantNif(null);
		modSolicitud.setSolicitantNom(soli.getPersonaContacte());
		modSolicitud.setSolicitantUsername(null);

		request.getSession().setAttribute("usuariNom", soli.getPersonaContacte());
		
		ModificacioSolicitud mod = modificacioSolicitudLogicaEjb.create(modSolicitud);

		return "redirect:" + getContextWeb() + "/" + mod.getModsoliID() + "/edit";
	}

	@Override
	public String[] getMissatgePerSolicitant(ModificacioSolicitudJPA modificacio) {

		String asumpte = "PROCÉS AUTORITZACIÓ PROCEDIMENT " + modificacio.getProcedimentCodi() + ". Esmena rebuda.";
		String missatge = "<div>Bon dia, <br> Hem rebut la seva esmena, li respondrem al més aviat possible.</div>";

		
		return new String[] { missatge, asumpte };
	}
	
	
	@Override
	public ModificacioSolicitudForm getModificacioSolicitudForm(ModificacioSolicitudJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {
		ModificacioSolicitudForm form = super.getModificacioSolicitudForm(_jpa, __isView, request, mav);
		
		
		if (!form.isNou() && !__isView) {
			Long solicitudID = form.getModificacioSolicitud().getSolicitudID();
			SolicitudJPA solicitud = solicitudLogicaEjb.findByPrimaryKey(solicitudID);
			Long infoMadridID = solicitud.getInfomadridid();
			if (infoMadridID != null) {
				InfoMadrid infoMadrid = infoMadridLogicaEjb.findByPrimaryKey(infoMadridID);
				String missatge = infoMadrid.getMissatge();
				
				missatge = missatge.replace("\n", "<br>");
				
				mav.addObject("instructions", missatge);
			}
			
			
			
		}
		
		
		
		return form;
	}
	
	
//	@RequestMapping(value = "/new/{token}", method = RequestMethod.GET)
//	public String obtenirDadesFitxerToken(HttpServletRequest request, HttpServletRequest response,
//			@PathVariable("token") java.lang.String token) {
//
//		log.info("obtenirDadesFitxerToken token: " + token);
//		File file = new File(FileSystemManager.getFilesPath(), token + ".front");
//
//		Properties properties = new Properties();
//		try {
//			properties.load(new FileInputStream(file));
//		} catch (IOException e) {
//			log.error("An error occurred." + e.getMessage(), e);
//			e.printStackTrace();
//		}
//
//		request.getSession().setAttribute("properties", properties);
//		request.getSession().setAttribute("token", token);
//		return "redirect:" + CONTEXT_WEB + "/seleccionarProcediment";
//	}
//
//	@RequestMapping(value = "/seleccionarProcediment", method = RequestMethod.GET)
//	public ModelAndView seleccionarProcedimentGet(HttpServletRequest request, HttpServletResponse response)
//			throws I18NException {
//		log.info("Entra a seleccionarProcediment GET");
//		ModelAndView mav = new ModelAndView("seleccionarProcediment");
//
//		Properties properties = (Properties) request.getSession().getAttribute("properties");
//		String nomComplet = properties.getProperty("Nom") + " " + properties.getProperty("Cognom1") + " "
//				+ properties.getProperty("Cognom2");
//
//		String usuariNIF = properties.getProperty("NIF");
//		String username = properties.getProperty("Username");
//
//		request.getSession().setAttribute("usuariNIF", usuariNIF);
//		request.getSession().setAttribute("usuariNom", nomComplet);
//		request.getSession().setAttribute("usuariUsername", username);
//
//		return mav;
//	}

//	@RequestMapping(value = "/seleccionarProcediment", method = RequestMethod.POST)
//	public String seleccionarProcedimentPost(HttpServletRequest request, HttpServletResponse response)
//			throws I18NException {
//		log.info("Entra a seleccionarProcediment POST");
//		ModelAndView mav = new ModelAndView("editarProcedimentAll");
//
//		Long solicitudID = Long.valueOf(request.getParameter("solicitudID"));
//
//		String nomContacte = request.getParameter("nomContacte");
//		String mailContacte = request.getParameter("mailContacte");
//
//		log.info("nom: " + nomContacte);
//		log.info("mail: " + mailContacte);
//
//		// Aqui creamos el objeto de ModifSoli con los datos de la solicitud, y luego
//		// redirigimos a edit.
//
//		ModificacioSolicitudJPA modSolicitud = new ModificacioSolicitudJPA();
//		modSolicitud.setContactenom(nomContacte);
//		modSolicitud.setContactemail(mailContacte);
//		modSolicitud.setEsmena(false); // Es una modificacio
//
//		modSolicitud.setEstatModificacio(Constants.ESTAT_MODIFICACIO_SOLICITUD_CREACIO); // o el campo correcto
//
//		modSolicitud.setSolicitantNif((String) request.getSession().getAttribute("usuariNIF"));
//		modSolicitud.setSolicitantNom((String) request.getSession().getAttribute("usuariNom"));
//		modSolicitud.setSolicitantUsername((String) request.getSession().getAttribute("usuariUsername"));
//
//		assignarSolicitudAModificacio(modSolicitud, solicitudID);
//
//		ModificacioSolicitud mod = modificacioSolicitudLogicaEjb.create(modSolicitud);
//
//		return "redirect:" + getContextWeb() + "/" + mod.getModsoliID() + "/edit";
//	}

	public void assignarSolicitudAModificacio(ModificacioSolicitudJPA mod, Long solicitudID) throws I18NException {
		
		log.info("Assignant la solicitudID " + solicitudID + " a la modificacio.");
		
		log.info("solicitudLogicaEjb = " + solicitudLogicaEjb);
		
		SolicitudJPA solicitud = solicitudLogicaEjb.findByPrimaryKey(solicitudID);
		
		log.info("solicitud = " + solicitud);
		
		mod.setSolicitudID(solicitud.getSolicitudID());
		mod.setProcedimentCodi(solicitud.getProcedimentCodi());
		mod.setProcedimentNom(solicitud.getProcedimentNom());
		mod.setEstatID(solicitud.getEstatSolicitud());
		mod.setDataInici(solicitud.getDataInici());
		mod.setDataFi(solicitud.getDataFi());
		mod.setNotes(solicitud.getPinfo()); // o el campo correcto
		mod.setProcedimentTipus(solicitud.getProcedimentTipus());

		mod.setOrganID(solicitud.getOrganid());
		mod.setResponsableProcNom(solicitud.getResponsableProcNom());
		mod.setResponsableProceMail(solicitud.getResponsableProcEmail());
		mod.setConsentiment(solicitud.getConsentiment());

		List<Long> listDocumentsSolicitud = documentSolicitudLogicaEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(solicitudID));

		List<Long> tipusDocuments = new ArrayList<Long>();
		tipusDocuments.add(Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_NOOP);
		tipusDocuments.add(Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI);

		List<Document> documents = documentLogicaEjb.select(Where
				.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud), DocumentFields.TIPUS.in(tipusDocuments)));

		log.info("Tenim " + documents + " posibles documents de consentiment.");
		for (Document document : documents) {
			mod.setDoCconsentimentID(document.getFitxerOriginalID());
			break;
		}

	}

	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, Long modsoliID) {
		return "https://www.google.com/?hl=es";
//		return "redirect:" + ModificarSolicitudPublicController.CONTEXT_WEB + "/seleccionarProcediment";
//		return "redirect:" + ModificarSolicitudPublicController.CONTEXT_WEB + "/" + modsoliID + "/edit";
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, ModificacioSolicitudForm modificacioSolicitudForm,
			Throwable __e) {

		// Redirigir al llistat d'events de la Solicitut que es modifica.

		Long soliID = modificacioSolicitudForm.getModificacioSolicitud().getSolicitudID();
//		String destinatari = "Margarita Munar Florit";
		String destinatari = modificacioSolicitudForm.getModificacioSolicitud().getSolicitantNom();
		String cadenaDestinatari = "CONTACTE|" + destinatari;

		String id = HibernateFileUtil.encryptFileID(soliID);
		String dest = destinatari == null ? "" : ("/" + HibernateFileUtil.encryptString(cadenaDestinatari));

		String url = EventSolicitudPublicController.CONTEXT_PATH + "/veureevents/" + id + dest;

		log.info("redirectToEventsPinfo: " + url);
		return "redirect:" + url;
	}

}
