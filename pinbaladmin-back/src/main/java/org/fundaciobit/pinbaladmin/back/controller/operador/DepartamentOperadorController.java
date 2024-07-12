package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.DepartamentController;
import org.fundaciobit.pinbaladmin.back.form.webdb.DepartamentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.DepartamentForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/departament")
@SessionAttributes(types = { DepartamentForm.class, DepartamentFilterForm.class })
public class DepartamentOperadorController extends DepartamentController {

    @Override
    public String getTileForm() {
        return "departamentFormOperador";
    }

    @Override
    public String getTileList() {
        return "departamentListOperador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "DepartamentOperador_FilterForm";
    }

    @Override
    public DepartamentFilterForm getDepartamentFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        DepartamentFilterForm departamentFilterForm;
        departamentFilterForm = super.getDepartamentFilterForm(pagina, mav, request);
        if (departamentFilterForm.isNou()) {
            departamentFilterForm.addHiddenField(DEPARTAMENTID);

            departamentFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_EYE,
                    "genapp.viewtitle", "/operador/solicitudlocal/list/1?departamentIDDesde={0}&departamentIDFins={0}",
                    AdditionalButtonStyle.INFO));

            // /pinbaladmin

        }
        return departamentFilterForm;
    }

}
