package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.DocumentCedentController;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentCedentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentCedentForm;
import org.fundaciobit.pinbaladmin.persistence.DocumentCedentJPA;
import org.fundaciobit.pinbaladmin.model.fields.EntitatServeiFields;
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
@RequestMapping(value = "/operador/documentcedent")
@SessionAttributes(types = { DocumentCedentForm.class, DocumentCedentFilterForm.class })
public class DocumentCedentOperatorController extends DocumentCedentController {

    public static final String CEDENTID_SESSION_PROPERTY = "CEDENTID_SESSION_PROPERTY";

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatServeiService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.EntitatServeiService entitatServeiEjb;

    public long getCedent(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute(CEDENTID_SESSION_PROPERTY);
    }

    @Override
    public String getTileForm() {
        return "documentCedentForm_operator";
    }

    @Override
    public String getTileList() {
        return "documentCedentList_operator";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "DocumentCedentOperator_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return ENTITATSERVEIID.equal(getCedent(request));
    }

    @Override
    public DocumentCedentFilterForm getDocumentCedentFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        DocumentCedentFilterForm documentCedentFilterForm;
        documentCedentFilterForm = super.getDocumentCedentFilterForm(pagina, mav, request);
        if (documentCedentFilterForm.isNou()) {

            documentCedentFilterForm.addHiddenField(DESCRIPCIO);
            documentCedentFilterForm.addHiddenField(ENTITATSERVEIID);
            documentCedentFilterForm.addHiddenField(DOCUMENTCEDENTID);

            String nom = entitatServeiEjb.executeQueryOne(EntitatServeiFields.NOM,
                    EntitatServeiFields.ENTITATSERVEIID.equal(getCedent(request)));

            documentCedentFilterForm.setTitleCode("cedent.list");
            documentCedentFilterForm.setTitleParam(nom);

            documentCedentFilterForm.setVisibleMultipleSelection(false);
            documentCedentFilterForm.setDeleteSelectedButtonVisible(false);

            List<Field<?>> groupby = new ArrayList<Field<?>>(documentCedentFilterForm.getDefaultGroupByFields());
            groupby.remove(ENTITATSERVEIID);

            documentCedentFilterForm.setGroupByFields(groupby);

            documentCedentFilterForm.addAdditionalButton(
                    new AdditionalButton(IconUtils.ICON_ARROW_LEFT, "tornar", "/operador/cedent/list", "btn-info"));
        }
        return documentCedentFilterForm;
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    public DocumentCedentForm getDocumentCedentForm(DocumentCedentJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        DocumentCedentForm documentCedentForm = super.getDocumentCedentForm(_jpa, __isView, request, mav);

        String nom = entitatServeiEjb.executeQueryOne(EntitatServeiFields.NOM,
                EntitatServeiFields.ENTITATSERVEIID.equal(getCedent(request)));

        if (documentCedentForm.isNou()) {
            // new
            documentCedentForm.setTitleCode("cedent.nou");

            documentCedentForm.getDocumentCedent().setDataCreacio(new Timestamp(System.currentTimeMillis()));
            documentCedentForm.getDocumentCedent().setEntitatServeiID(getCedent(request));
        } else {
            // edit
            documentCedentForm.setTitleCode("cedent.editar");
        }

        documentCedentForm.setTitleParam(nom);

        documentCedentForm.addReadOnlyField(DATACREACIO);
        documentCedentForm.addHiddenField(ENTITATSERVEIID);

        return documentCedentForm;
    }

}
