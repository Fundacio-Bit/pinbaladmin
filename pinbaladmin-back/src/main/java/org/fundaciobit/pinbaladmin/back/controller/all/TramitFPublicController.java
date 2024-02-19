package org.fundaciobit.pinbaladmin.back.controller.all;

import org.fundaciobit.pinbaladmin.back.controller.operador.TramitFOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitFPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitFCteTecForm.class, TramitFCteTecFilterForm.class })
public class TramitFPublicController extends TramitFOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitEPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitf";
    public static final String CONTEXT_WEB_NEXT = TramitGPublicController.CONTEXT_WEB;;
   
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
        return "tramitFFormPublic";
    }

}