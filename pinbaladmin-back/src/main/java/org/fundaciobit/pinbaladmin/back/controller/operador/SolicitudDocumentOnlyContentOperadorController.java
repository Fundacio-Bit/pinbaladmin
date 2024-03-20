package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentForm;
import org.fundaciobit.pinbaladmin.back.security.LoginInfo;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/solicituddocumentonlycontent")
@SessionAttributes(types = { DocumentForm.class, DocumentFilterForm.class })
public class SolicitudDocumentOnlyContentOperadorController extends SolicitudDocumentOperadorController {

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EventService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.EventService eventEjb;
    
    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return true;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public String getTileList() {
        return "solicitudDocumentListWebDB_onlycontent_operador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "SolicitudDocumentWebDB_FilterForm_OnlyContent_Operador";
    }

    @Override
    public DocumentFilterForm getDocumentFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {

        DocumentFilterForm solicitudDocumentFilterForm = super.getDocumentFilterForm(pagina, mav, request);

        if (solicitudDocumentFilterForm.isNou()) {
            solicitudDocumentFilterForm.setItemsPerPage(-1);
        }

        return solicitudDocumentFilterForm;
    }

    private boolean TENIM_FIRMAT = false;
    
    @Override
    public DocumentForm getDocumentForm(DocumentJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {

        DocumentForm  form = super.getDocumentForm(_jpa, __isView, request, mav);
        
        if (!__isView && !form.isNou()) {
            if (form.getDocument().getFitxerFirmatID() == null) {
                TENIM_FIRMAT = false;
            }else {
                TENIM_FIRMAT = true;
            }
        }
        return form;
    }    
    
    @Override
    public String getRedirectWhenModified(HttpServletRequest request, DocumentForm docForm, Throwable __e) {
    	
		Long tipusDoc = docForm.getDocument().getTipus();
    	updateConsentiment(request, tipusDoc);
    	
        Long fitxerFirmat = docForm.getDocument().getFitxerFirmatID();
        if (!TENIM_FIRMAT && fitxerFirmat != null && tipusDoc == Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF) {
            //Abans no teniem firmat, i ara si. 

            try {
                Long solicitudID = getSolicitudID(request);
                
                java.lang.Long _solicitudID_ = solicitudID;
                java.lang.Long _incidenciaTecnicaID_ = null;
                java.sql.Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());
                int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
                java.lang.String _persona_ = LoginInfo.getInstance().getUsername();
                java.lang.String _comentari_ = "S'ha afegit el fitxer firmat per el Director General";
                java.lang.Long _fitxerID_ = docForm.getDocument().getFitxerFirmatID();
                boolean _noLlegit_ = false;
                java.lang.String _destinatari_ = null;
                java.lang.String _destinatariMail_ = null;

                eventEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_, _destinatari_,
                        _destinatariMail_, _comentari_, _fitxerID_, _noLlegit_, null, null);
            } catch (Throwable th) {
                log.error("Error creant l'event fitxer firmat afegit a la solicitud: " + th.getMessage(), th);
            }
            
            HtmlUtils.saveMessageInfo(request, "S'ha afegit el fitxer firmat per el Director General");
        }

        
        return super.getRedirectWhenModified(request, docForm, __e);
    }    


}
