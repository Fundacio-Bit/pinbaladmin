package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitJConsentController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentForm;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitJConsentLogicaService;
import org.fundaciobit.pinbaladmin.persistence.TramitJConsentJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitJConsentJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitJOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitJConsentForm.class, TramitJConsentFilterForm.class })
public class TramitJOperadorController extends TramitJConsentController {


    public static final String CONTEXT_WEB = "/operador/tramitj";

    @EJB(mappedName = TramitJConsentLogicaService.JNDI_NAME)
    protected TramitJConsentLogicaService tramitJConsentLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @Override
    public String getTileForm() {
        return "tramitJFormOperador";
    }

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public boolean isActiveFormNew() {
        return true;
    }

    @Override
    public boolean isActiveFormEdit() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitJConsentForm tramitJConsentForm) {
        Long tramitId = tramitJConsentForm.getTramitJConsent().getTramitid();

        //Al form del seguent, getParameter del tramitid, i utilitzar-ho per crear el tramitJ
        return "redirect:" + TramitAOperadorController.CONTEXT_WEB + "/generaxml/" + tramitId;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long _ID_) {
        try {
            Long tramitID = (Long) request.getSession().getAttribute("tramitid");
            log.info("Estamos en J, vamos a borrar. TramitID=" + tramitID);

            tramitAPersAutLogicEjb.deleteFull(tramitID);
            request.getSession().removeAttribute("tramitid");

            HtmlUtils.saveMessageError(request, "Tramit Cancelat (taules borrades");
        } catch (I18NException e) {
            HtmlUtils.saveMessageError(request, "Error esborrant les taules del tramit sistra");
        }
        return "redirect:" + TramitAOperadorController.RETURN_URL;
    }

    @Override
    public TramitJConsentForm getTramitJConsentForm(TramitJConsentJPA _jpa,
            boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitJConsentForm tramitForm = super.getTramitJConsentForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.j");

        
        if (__isView) {

        } else if (tramitForm.isNou()) {
            TramitJConsentJPA tramitJ = tramitForm.getTramitJConsent();

            String tramitIDStr = request.getParameter("tramitid");
            Long tramitID = Long.parseLong(tramitIDStr);
            tramitJ.setTramitid(tramitID);
            tramitForm.addHiddenField(TRAMITID);
            request.getSession().setAttribute("tramitid", tramitID);

        //    tramitJ.setEntorn("Pro");
        } else {

        }
        return tramitForm;
    }


    @Override
    public TramitJConsentJPA create(HttpServletRequest request, TramitJConsentJPA tramitJConsent)
            throws I18NException, I18NValidationException {
        return (TramitJConsentJPA) tramitJConsentLogicEjb.create(tramitJConsent);
    }


    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitJConsentForm tramitJConsentForm,
            Throwable __e) {
        if (__e == null) {
            return "redirect:" + TramitAOperadorController.CONTEXT_WEB + "/list";
        } else {
            return getTileForm();
        }
    }

}
