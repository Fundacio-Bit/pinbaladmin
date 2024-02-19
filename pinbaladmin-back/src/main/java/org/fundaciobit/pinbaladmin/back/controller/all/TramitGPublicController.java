package org.fundaciobit.pinbaladmin.back.controller.all;

import org.fundaciobit.pinbaladmin.back.controller.operador.TramitGOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitGPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitGDadesTitForm.class, TramitGDadesTitFilterForm.class })
public class TramitGPublicController extends TramitGOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitFPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitg";
    public static final String CONTEXT_WEB_NEXT = TramitHPublicController.CONTEXT_WEB;
   
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
        return "tramitGFormPublic";
    }

}