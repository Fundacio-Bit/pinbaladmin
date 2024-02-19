package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesFormManager;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitJOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitJConsentForm;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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