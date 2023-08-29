package org.fundaciobit.pinbaladmin.back.controller.operador;

import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = TramitSistraOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitAPersAutForm.class, TramitAPersAutFilterForm.class })
public class TramitSistraOperadorController extends TramitAOperadorController {

    public static final String CONTEXT_WEB = "/operador/tramit/start";
    
    @Override
    public String getTileForm() {
        return "tramitSistraOperador";
    }
    

}
