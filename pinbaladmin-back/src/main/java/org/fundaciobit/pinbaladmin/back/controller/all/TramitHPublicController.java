package org.fundaciobit.pinbaladmin.back.controller.all;

import org.fundaciobit.pinbaladmin.back.controller.operador.TramitHOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitHPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitHProcForm.class, TramitHProcFilterForm.class })
public class TramitHPublicController extends TramitHOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitGPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramith";
    public static final String CONTEXT_WEB_NEXT = TramitIPublicController.CONTEXT_WEB;
   
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
        return "tramitHFormPublic";
    }

}