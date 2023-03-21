package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.controller.webdb.AreaController;
import org.fundaciobit.pinbaladmin.back.form.webdb.AreaFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.AreaForm;
import org.fundaciobit.pinbaladmin.persistence.AreaJPA;
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
@RequestMapping(value = "/operador/area")
@SessionAttributes(types = { AreaForm.class, AreaFilterForm.class })
public class AreaOperadorController extends AreaController {

    @Override
    public String getTileForm() {
        return "areaFormOperador";
    }

    @Override
    public String getTileList() {
        return "areaListOperador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "AreaOperador_FilterForm";
    }

    @Override
    public AreaForm getAreaForm(AreaJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        AreaForm areaForm = super.getAreaForm(_jpa, __isView, request, mav);

        if (areaForm.isNou()) {
            // Nou
        } else {
            areaForm.addReadOnlyField(ENTITATID);
        }

        return areaForm;
    }

}
