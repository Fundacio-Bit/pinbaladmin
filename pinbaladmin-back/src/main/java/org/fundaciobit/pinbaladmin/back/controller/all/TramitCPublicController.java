package org.fundaciobit.pinbaladmin.back.controller.all;

import org.fundaciobit.pinbaladmin.back.controller.operador.TramitCOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitCPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitCDadesCesiForm.class, TramitCDadesCesiFilterForm.class })
public class TramitCPublicController extends TramitCOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitBPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitc";
    public static final String CONTEXT_WEB_NEXT = TramitDPublicController.CONTEXT_WEB;

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
        return "tramitCFormPublic";
    }
}
