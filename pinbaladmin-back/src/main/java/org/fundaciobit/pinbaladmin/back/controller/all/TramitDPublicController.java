package org.fundaciobit.pinbaladmin.back.controller.all;

import org.fundaciobit.pinbaladmin.back.controller.operador.TramitDOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitDPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitDCteAutForm.class, TramitDCteAutFilterForm.class })
public class TramitDPublicController extends TramitDOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitCPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitd";
    public static final String CONTEXT_WEB_NEXT = TramitEPublicController.CONTEXT_WEB;

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
        return "tramitDFormPublic";
    }
}
