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
import org.fundaciobit.pinbaladmin.back.controller.webdb.DocumentEntitatController;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentCedentFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentCedentForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentEntitatFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentEntitatForm;
import org.fundaciobit.pinbaladmin.persistence.DocumentEntitatJPA;
import org.fundaciobit.pinbaladmin.model.fields.EntitatFields;
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
@RequestMapping(value = "/operador/documententitatlocal")
@SessionAttributes(types = { DocumentCedentForm.class, DocumentCedentFilterForm.class })
public class DocumentEntitatLocalOperatorController extends DocumentEntitatController {

    public static final String ENTITATLOCALID_SESSION_PROPERTY = "ENTITATLOCALID_SESSION_PROPERTY";

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.EntitatService entitatEjb;

    public long getCedent(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute(ENTITATLOCALID_SESSION_PROPERTY);
    }

    @Override
    public String getTileForm() {
        return "documentEntitatForm_operator";
    }

    @Override
    public String getTileList() {
        return "documentEntitatList_operator";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "DocumentEntitatLocalOperator_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        return ENTITATID.equal(getCedent(request));
    }

    @Override
    public DocumentEntitatFilterForm getDocumentEntitatFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        DocumentEntitatFilterForm documentCedentFilterForm;
        documentCedentFilterForm = super.getDocumentEntitatFilterForm(pagina, mav, request);
        if (documentCedentFilterForm.isNou()) {

            documentCedentFilterForm.addHiddenField(DESCRIPCIO);
            documentCedentFilterForm.addHiddenField(ENTITATID);
            documentCedentFilterForm.addHiddenField(DOCUMENTENTITATID);

            String nom = entitatEjb.executeQueryOne(EntitatFields.NOM,
                    EntitatFields.ENTITATID.equal(getCedent(request)));

            documentCedentFilterForm.setTitleCode("entitatlocal.list");
            documentCedentFilterForm.setTitleParam(nom);

            documentCedentFilterForm.setVisibleMultipleSelection(false);
            documentCedentFilterForm.setDeleteSelectedButtonVisible(false);

            List<Field<?>> groupby = new ArrayList<Field<?>>(documentCedentFilterForm.getDefaultGroupByFields());
            groupby.remove(ENTITATID);

            documentCedentFilterForm.setGroupByFields(groupby);

            documentCedentFilterForm.addAdditionalButton(
                    new AdditionalButton(IconUtils.ICON_ARROW_LEFT, "tornar", "/operador/entitat/list", "btn-info"));
        }
        return documentCedentFilterForm;
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    public DocumentEntitatForm getDocumentEntitatForm(DocumentEntitatJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        DocumentEntitatForm documentCedentForm = super.getDocumentEntitatForm(_jpa, __isView, request, mav);

        String nom = entitatEjb.executeQueryOne(EntitatFields.NOM, EntitatFields.ENTITATID.equal(getCedent(request)));

        if (documentCedentForm.isNou()) {
            // new
            documentCedentForm.setTitleCode("entitatlocal.nou");

            documentCedentForm.getDocumentEntitat().setDataAlta(new Timestamp(System.currentTimeMillis()));
            documentCedentForm.getDocumentEntitat().setEntitatID(getCedent(request));
        } else {
            // edit
            documentCedentForm.setTitleCode("entitatlocal.editar");
        }

        documentCedentForm.setTitleParam(nom);

        documentCedentForm.addReadOnlyField(DATAALTA);
        documentCedentForm.addHiddenField(ENTITATID);

        return documentCedentForm;
    }

}
