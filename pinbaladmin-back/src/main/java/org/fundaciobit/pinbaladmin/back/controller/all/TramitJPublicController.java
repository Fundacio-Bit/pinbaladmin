package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesFormManager;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitEOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitJOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitJConsentController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitJConsentLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;
import org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields;
import org.fundaciobit.pinbaladmin.persistence.TramitJConsentJPA;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitJPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitJConsentForm.class, TramitJConsentFilterForm.class })
public class TramitJPublicController extends TramitJOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitIPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitj";
   
    @EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
    protected FitxerPublicLogicaService fitxerPublicLogicaEjb;

    @Override
    protected FilesFormManager<Fitxer> getFilesFormManager() {
        return new PinbalAdminFilesFormManager(fitxerPublicLogicaEjb);
    }
 
    @Override
    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }

    @Override
    public String getTileForm() {
        return "tramitJFormPublic";
    }

}