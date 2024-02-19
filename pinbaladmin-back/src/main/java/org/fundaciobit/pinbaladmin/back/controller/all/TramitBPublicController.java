package org.fundaciobit.pinbaladmin.back.controller.all;

import org.fundaciobit.pinbaladmin.back.controller.operador.TramitBOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitBPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitBDadesSoliForm.class, TramitBDadesSoliFilterForm.class })
public class TramitBPublicController extends TramitBOperadorController {

    public static final String CONTEXT_WEB = "/public/tramitb";
    public static final String CONTEXT_WEB_NEXT = TramitCPublicController.CONTEXT_WEB;

    @Override
    public String getTileForm() {
        return "tramitBFormPublic";
    }
    
    @Override
    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }
    
}
