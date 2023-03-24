package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.controller.webdb.GrupEntitatCedentController;
import org.fundaciobit.pinbaladmin.back.form.webdb.GrupEntitatCedentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.GrupEntitatCedentForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/operador/borrarcedentdegrup")
@SessionAttributes(types = { GrupEntitatCedentForm.class, GrupEntitatCedentFilterForm.class })
public class DescartarCedentsDeGrupsOperadorController extends GrupEntitatCedentController {

    @Override
    public String getTileForm() {
        return "grupEntitatCedentFormOperador";
    }

    @Override
    public String getTileList() {
        return "grupEntitatCedentListOperador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "GrupEntitatCedentOperador_FilterForm";
    }

    @Override
    public GrupEntitatCedentFilterForm getGrupEntitatCedentFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        GrupEntitatCedentFilterForm grupEntitatCedentFilterForm;

        grupEntitatCedentFilterForm = super.getGrupEntitatCedentFilterForm(pagina, mav, request);

        if (grupEntitatCedentFilterForm.isNou()) {

            grupEntitatCedentFilterForm.setSubTitleCode(
                    "=Llistat de cedents que s'eliminen de certs grups (utilitzat en Domini de Sistra)");

        }

        return grupEntitatCedentFilterForm;
    }

}
