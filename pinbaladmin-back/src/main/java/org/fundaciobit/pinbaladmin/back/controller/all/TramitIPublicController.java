package org.fundaciobit.pinbaladmin.back.controller.all;

import org.fundaciobit.pinbaladmin.back.controller.operador.TramitIOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitIPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitIServForm.class, TramitIServFilterForm.class })
public class TramitIPublicController extends TramitIOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitHPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramiti";
    public static final String CONTEXT_WEB_NEXT = TramitJPublicController.CONTEXT_WEB;
   
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
        return "tramitIFormPublic";
    }

    @Override
    public String getTileList() {
        return "tramitIListPublic";
    }

}