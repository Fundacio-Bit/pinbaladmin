package org.fundaciobit.pinbaladmin.back.controller.admin;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.FitxerController;
import org.fundaciobit.pinbaladmin.back.form.webdb.FitxerFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.FitxerForm;
import org.fundaciobit.pinbaladmin.ejb.DocumentCedentService;
import org.fundaciobit.pinbaladmin.ejb.DocumentEntitatService;
import org.fundaciobit.pinbaladmin.ejb.DocumentService;
import org.fundaciobit.pinbaladmin.ejb.EventService;
import org.fundaciobit.pinbaladmin.ejb.FormulariService;
import org.fundaciobit.pinbaladmin.ejb.ModificacioSoliServService;
import org.fundaciobit.pinbaladmin.ejb.ModificacioSolicitudService;
import org.fundaciobit.pinbaladmin.ejb.PinfoService;
import org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService;
import org.fundaciobit.pinbaladmin.ejb.SolicitudService;
import org.fundaciobit.pinbaladmin.ejb.TiquetService;
import org.fundaciobit.pinbaladmin.ejb.TramitIServService;
import org.fundaciobit.pinbaladmin.ejb.TramitJConsentService;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.fields.DocumentCedentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentEntitatFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.FitxerFields;
import org.fundaciobit.pinbaladmin.model.fields.FormulariFields;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSoliServFields;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.PinfoFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.TiquetFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author anadal
 * @author areus
 */
@Controller
@RequestMapping(value = "/admin/fitxer")
@SessionAttributes(types = { FitxerForm.class, FitxerFilterForm.class })
public class FitxerAdminController extends FitxerController {

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
    protected FitxerPublicLogicaService fitxerLogicaEjb;

	
	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @EJB(mappedName = DocumentService.JNDI_NAME)
    protected DocumentService documentEjb;

    @EJB(mappedName = DocumentCedentService.JNDI_NAME)
    protected DocumentCedentService documentCedentEjb;

    @EJB(mappedName = DocumentEntitatService.JNDI_NAME)
    protected DocumentEntitatService documentEntitatEjb;

    @EJB(mappedName = EventService.JNDI_NAME)
    protected EventService eventEjb;

    @EJB(mappedName = FormulariService.JNDI_NAME)
    protected FormulariService formulariEjb;

    @EJB(mappedName = ModificacioSolicitudService.JNDI_NAME)
    protected ModificacioSolicitudService modificacioSolicitudEjb;

    @EJB(mappedName = ModificacioSoliServService.JNDI_NAME)
    protected ModificacioSoliServService modificacioSoliServEjb;

    @EJB(mappedName = PinfoService.JNDI_NAME)
    protected PinfoService pinfoEjb;

    @EJB(mappedName = SolicitudService.JNDI_NAME)
    protected SolicitudService solicitudEjb;

    @EJB(mappedName = SolicitudServeiService.JNDI_NAME)
    protected SolicitudServeiService solicitudServeiEjb;

    @EJB(mappedName = TiquetService.JNDI_NAME)
    protected TiquetService tiquetEjb;

    @EJB(mappedName = TramitIServService.JNDI_NAME)
    protected TramitIServService tramitIServEjb;

    @EJB(mappedName = TramitJConsentService.JNDI_NAME)
    protected TramitJConsentService tramitJConsentEjb;
    
    /*
     * Aparicions de taula fitxer:
     * 
     * pad_document
     *  - fitxeroriginalid
     *  - fitxerfirmatid
     *  
     * pad_documentcedent
     *  - fitxerid
     * 
     * pad_documententitat
     *  - fitxerid
     *  
     *  pad_event
     *    - fitxerid
     *    
     * pad_formulari
     *  - fitxerid
     *  
     *pad_mod_solicitud
     * - docconsentiment 
     * 
     * pad_mod_soliserv
     *  - fitxernorma1id
     *  - fitxernorma2id
     *  - fitxernorma3id
     *  
     *  pad_pinfo
     *   - fitxerid
     *   - fitxerfirmatid
     *   
     * pad_solicitud
     *  - fitxerconsentimentid
     *  - documentsolicitudid
     *  - solicitudxmlid
     *  
     * 
     * pad_solicitudservei
     *  - fitxernormaid
     *  - fitxernorma2id
     *  - fitxernorma3id
     *   
     * pad_tiquet  
     *  - adjunt1id
     *  - adjunt2id
     *   
     * pad_tramit_i_serv  
     *  - fitxernormaid
     *  - fitxernorma2id
     *  - fitxernorma3id 
     *   
     * pad_tramit_j_consent
     * - adjuntid
     * 
     * 
     * 
     */
    
    
    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    	
      Where parent = super.getAdditionalCondition(request);

      // pad_document
      Where wDocument1 = FitxerFields.FITXERID.notIn(documentEjb.getSubQuery(DocumentFields.FITXERORIGINALID, DocumentFields.FITXERORIGINALID.isNotNull()));
      Where wDocument2 = FitxerFields.FITXERID.notIn(documentEjb.getSubQuery(DocumentFields.FITXERFIRMATID, DocumentFields.FITXERFIRMATID.isNotNull()));
      Where wDocument = Where.AND(wDocument1, wDocument2);
      
      // pad_documentcedent
      Where wDocumentCedent = FitxerFields.FITXERID.notIn(documentCedentEjb.getSubQuery(DocumentCedentFields.FITXERID, DocumentCedentFields.FITXERID.isNotNull()));
      
      // pad_documententitat
      Where wDocumentEntitat = FitxerFields.FITXERID.notIn(documentEntitatEjb.getSubQuery(DocumentEntitatFields.FITXERID, DocumentEntitatFields.FITXERID.isNotNull()));
      
      // pad_event
      Where wEvent = FitxerFields.FITXERID.notIn(eventEjb.getSubQuery(EventFields.FITXERID, EventFields.FITXERID.isNotNull()));

      // pad_formulari
      Where wFormulari = FitxerFields.FITXERID.notIn(formulariEjb.getSubQuery(FormulariFields.FITXERID, FormulariFields.FITXERID.isNotNull()));
      
      // pad_mod_solicitud
      Where wModificacioSolicitud = FitxerFields.FITXERID.notIn(modificacioSolicitudEjb.getSubQuery(ModificacioSolicitudFields.DOCCONSENTIMENTID, ModificacioSolicitudFields.DOCCONSENTIMENTID.isNotNull()));

      // pad_mod_soliserv
      Where wModSoliServ1 = FitxerFields.FITXERID.notIn(modificacioSoliServEjb.getSubQuery(ModificacioSoliServFields.FITXERNORMA1ID, ModificacioSoliServFields.FITXERNORMA1ID.isNotNull()));
      Where wModSoliServ2 = FitxerFields.FITXERID.notIn(modificacioSoliServEjb.getSubQuery(ModificacioSoliServFields.FITXERNORMA2ID, ModificacioSoliServFields.FITXERNORMA2ID.isNotNull()));
      Where wModSoliServ3 = FitxerFields.FITXERID.notIn(modificacioSoliServEjb.getSubQuery(ModificacioSoliServFields.FITXERNORMA3ID, ModificacioSoliServFields.FITXERNORMA3ID.isNotNull()));
      Where wModificacioSoliServ = Where.AND(wModSoliServ1, wModSoliServ2, wModSoliServ3);

      // pad_pinfo
      Where wPinfo1 = FitxerFields.FITXERID.notIn(pinfoEjb.getSubQuery(PinfoFields.FITXERID, PinfoFields.FITXERID.isNotNull()));
      Where wPinfo2 = FitxerFields.FITXERID.notIn(pinfoEjb.getSubQuery(PinfoFields.FITXERFIRMATID, PinfoFields.FITXERFIRMATID.isNotNull()));
      Where wPinfo = Where.AND(wPinfo1, wPinfo2);
      
      // pad_solicitud
      Where wSolicitud1 = FitxerFields.FITXERID.notIn(solicitudEjb.getSubQuery(SolicitudFields.FITXERCONSENTIMENTID, SolicitudFields.FITXERCONSENTIMENTID.isNotNull()));
      Where wSolicitud2 = FitxerFields.FITXERID.notIn(solicitudEjb.getSubQuery(SolicitudFields.DOCUMENTSOLICITUDID, SolicitudFields.DOCUMENTSOLICITUDID.isNotNull()));
      Where wSolicitud3 = FitxerFields.FITXERID.notIn(solicitudEjb.getSubQuery(SolicitudFields.SOLICITUDXMLID, SolicitudFields.SOLICITUDXMLID.isNotNull()));
      Where wSolicitud = Where.AND(wSolicitud1, wSolicitud2, wSolicitud3);

      // pad_solicitudservei
      Where wSolicitudServei1 = FitxerFields.FITXERID.notIn(solicitudServeiEjb.getSubQuery(SolicitudServeiFields.FITXERNORMAID, SolicitudServeiFields.FITXERNORMAID.isNotNull()));
      Where wSolicitudServei2 = FitxerFields.FITXERID.notIn(solicitudServeiEjb.getSubQuery(SolicitudServeiFields.FITXERNORMA2ID, SolicitudServeiFields.FITXERNORMA2ID.isNotNull()));
      Where wSolicitudServei3 = FitxerFields.FITXERID.notIn(solicitudServeiEjb.getSubQuery(SolicitudServeiFields.FITXERNORMA3ID, SolicitudServeiFields.FITXERNORMA3ID.isNotNull()));
      Where wSolicitudServei = Where.AND(wSolicitudServei1, wSolicitudServei2, wSolicitudServei3);

      // pad_tiquet
      Where wTiquet1 = FitxerFields.FITXERID.notIn(tiquetEjb.getSubQuery(TiquetFields.ADJUNT1ID, TiquetFields.ADJUNT1ID.isNotNull()));
      Where wTiquet2 = FitxerFields.FITXERID.notIn(tiquetEjb.getSubQuery(TiquetFields.ADJUNT2ID, TiquetFields.ADJUNT2ID.isNotNull()));
      Where wTiquet = Where.AND(wTiquet1, wTiquet2);
      
      // pad_tramit_i_serv
      Where wTramitIServ1 = FitxerFields.FITXERID.notIn(tramitIServEjb.getSubQuery(TramitIServFields.FITXERNORMAID, TramitIServFields.FITXERNORMAID.isNotNull()));
      Where wTramitIServ2 = FitxerFields.FITXERID.notIn(tramitIServEjb.getSubQuery(TramitIServFields.FITXERNORMA2ID, TramitIServFields.FITXERNORMA2ID.isNotNull()));
      Where wTramitIServ3 = FitxerFields.FITXERID.notIn(tramitIServEjb.getSubQuery(TramitIServFields.FITXERNORMA3ID, TramitIServFields.FITXERNORMA3ID.isNotNull()));
      Where wTramitIServ = Where.AND(wTramitIServ1, wTramitIServ2, wTramitIServ3);

	// pad_tramit_j_consent
      Where wTramitJConsent = FitxerFields.FITXERID.notIn(tramitJConsentEjb.getSubQuery(TramitJConsentFields.ADJUNTID, TramitJConsentFields.ADJUNTID.isNotNull()));
      
      
      return Where.AND(parent, 
              wDocument, 
              wDocumentCedent, 
              wDocumentEntitat, 
              wEvent, 
              wFormulari, 
              wModificacioSolicitud, 
              wModificacioSoliServ, 
              wPinfo, 
              wSolicitud, 
              wSolicitudServei, 
              wTiquet, 
              wTramitIServ, 
              wTramitJConsent);
    }
    
    public void delete(HttpServletRequest request, Fitxer fitxer) throws I18NException {
    	fitxerLogicaEjb.deleteFull(fitxer);
    }

    
	@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
	public String deleteSelected(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute FitxerFilterForm filterForm) throws Exception {

		if (!isActiveDelete()) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		String[] seleccionats = filterForm.getSelectedItems();
		Set<Long> files = new HashSet<>();
		
		if (seleccionats != null && seleccionats.length != 0) {
			for (int i = 0; i < seleccionats.length; i++) {
		        files.add(stringToPK(seleccionats[i]));
			}
			
			try {
				fitxerLogicaEjb.deleteFullMultiple(files);
				HtmlUtils.saveMessageInfo(request, "Els fitxers seleccionats s'han eliminat correctament: " + files.size()  + " fitxers eliminats.");
			} catch (I18NException e) {
				log.error("Error al eliminar els fitxers seleccionats. " + I18NUtils.getMessage(e), e);
				throw e;
			}
		}

		return getRedirectWhenDelete(request, null, null);
	}

    @Override
    public FitxerForm getFitxerForm(FitxerJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        FitxerForm fitxerForm = super.getFitxerForm(_jpa, __isView, request, mav);

        if (fitxerForm.isNou()) {

        }
        return fitxerForm;
    }

    @Override
    public FitxerFilterForm getFitxerFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		FitxerFilterForm fitxerFilterForm = super.getFitxerFilterForm(pagina, mav, request);

		if (fitxerFilterForm.isNou()) {
			log.info("Creant nou FitxerFilterForm per a operador");

			String icon = IconUtils.ICON_TRASH;
			String text = "esborrarfitxers.fisicsensebd";
			String link = getContextWeb() + "/eliminarFitxersFisic";

			fitxerFilterForm.addAdditionalButton(new AdditionalButton(icon, text, link, AdditionalButtonStyle.SUCCESS));
			
			icon  =IconUtils.ICON_TRASH;
			text = "esborrarfitxers.bdsensefisic";
			link = getContextWeb() + "/eliminarFitxersBD";
			
			fitxerFilterForm.addAdditionalButton(new AdditionalButton(icon, text, link, AdditionalButtonStyle.SUCCESS));
		}

		return fitxerFilterForm;
	}

	@RequestMapping(value = "/eliminarFitxersFisic")
	public String elimimnarFitxerFisic(HttpServletRequest request, ModelAndView mav) throws I18NException {
		log.info("eliminarFitxersFisic");

		Map<Long, File> allFiles = FileSystemManager.getAllFiles();

		Set<Long> fisicsPerEsborrar = new HashSet<>();

		// Afegirem fisics que no tenen referencia en BD
		for (Map.Entry<Long, File> entry : allFiles.entrySet()) {
			Long fileId = entry.getKey();

			FitxerJPA fitxer = fitxerLogicaEjb.findByPrimaryKey(fileId);

			if (fitxer == null) {
				fisicsPerEsborrar.add(fileId);
			}
		}

		if (!fisicsPerEsborrar.isEmpty()) {
			log.info("S'han trobat " + fisicsPerEsborrar.size()
					+ " fitxers físics sense referència a BD. S'eliminaran del sistema.");
			FileSystemManager.eliminarArxius(fisicsPerEsborrar);
			HtmlUtils.saveMessageInfo(request,
					"S'han eliminat " + fisicsPerEsborrar.size() + " fitxers físics sense referència a BD.");
		} else {
			log.info("No s'han trobat fitxers físics sense referència a BD.");
		}

		return "redirect:" + getContextWeb() + "/list/1";
	}
    
	@RequestMapping(value = "/eliminarFitxersBD")
	public String eliminarFitxerBD(HttpServletRequest request, ModelAndView mav) throws I18NException {
		log.info("eliminarFitxersFisic");

		Map<Long, File> allFiles = FileSystemManager.getAllFiles();
		List<Long> fitxersBBDD = fitxerLogicaEjb.executeQuery(FITXERID, new OrderBy(FITXERID));

		Set<Long> fitxersEsborrarBBDD = new HashSet<>();
		
		for (Long fID : fitxersBBDD) {
			if (!allFiles.containsKey(fID)) {
				fitxersEsborrarBBDD.add(fID);
			}
		}

		log.info("S'han trobat " + fitxersEsborrarBBDD.size()
				+ " fitxers a BD sense fitxer físic associat. S'eliminaran de BD.");
		fitxerLogicaEjb.delete(FitxerFields.FITXERID.in(fitxersEsborrarBBDD));
		HtmlUtils.saveMessageInfo(request,
				"S'han eliminat " + fitxersEsborrarBBDD.size() + " fitxers a BD sense fitxer físic associat.");
		
		return "redirect:" + getContextWeb() + "/list/1";
	}
    
	
	
	
    @Override
    public String getTileForm() {
        return "fitxerForm_admin";
    }

    @Override
    public String getTileList() {
        return "fitxerList_admin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "FitxerWebDB_FilterForm_operador";
    }
 
}
