package org.fundaciobit.pinbaladmin.back.controller.all;

import org.fundaciobit.pinbaladmin.back.controller.operador.TramitEOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitEPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitECteAudForm.class, TramitECteAudFilterForm.class })
public class TramitEPublicController extends TramitEOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitDPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramite";
    public static final String CONTEXT_WEB_NEXT = TramitFPublicController.CONTEXT_WEB;
   
    @Override
    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }

    @Override
    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }

    @Override
    public String getTileForm() {
        return "tramitEFormPublic";
    }

}