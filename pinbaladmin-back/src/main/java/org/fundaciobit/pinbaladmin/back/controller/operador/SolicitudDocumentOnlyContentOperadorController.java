package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentForm;
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
@RequestMapping(value = "/operador/solicituddocumentonlycontent")
@SessionAttributes(types = { DocumentForm.class, DocumentFilterForm.class })
public class SolicitudDocumentOnlyContentOperadorController extends SolicitudDocumentOperadorController {

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return true;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public String getTileList() {
        return "solicitudDocumentListWebDB_onlycontent_operador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "SolicitudDocumentWebDB_FilterForm_OnlyContent_Operador";
    }

    @Override
    public DocumentFilterForm getDocumentFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {

        DocumentFilterForm solicitudDocumentFilterForm = super.getDocumentFilterForm(pagina, mav, request);

        if (solicitudDocumentFilterForm.isNou()) {
            solicitudDocumentFilterForm.setItemsPerPage(-1);
        }

        return solicitudDocumentFilterForm;
    }

}
