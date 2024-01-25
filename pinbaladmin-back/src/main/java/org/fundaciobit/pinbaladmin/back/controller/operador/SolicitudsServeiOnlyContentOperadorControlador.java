package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiForm;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.EntitatServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
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
@RequestMapping(value = "/operador/solicitudserveionlycontent")
@SessionAttributes(types = { SolicitudServeiForm.class, SolicitudServeiFilterForm.class })
public class SolicitudsServeiOnlyContentOperadorControlador extends SolicitudServeiOperadorController {

    @Override
    public boolean isActiveFormNew() {
        return true;
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
        return "solicitudServeiListWebDB_onlycontent_operador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "SolicitudServeiWebDB_FilterForm_OnlyContent_Operador";
    }

    public static final int SOLSERID = -3;
    public static final int CODISERVEI = -2;
    public static final int NOMSERVEI = -1;

    @Override
    public SolicitudServeiFilterForm getSolicitudServeiFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        SolicitudServeiFilterForm solicitudServeiFilterForm = super.getSolicitudServeiFilterForm(pagina, mav, request);

        if (solicitudServeiFilterForm.isNou()) {
            solicitudServeiFilterForm.setItemsPerPage(-1);
            solicitudServeiFilterForm.addHiddenField(ARTICLES);
            solicitudServeiFilterForm.addHiddenField(CONSENTIMENT);
            solicitudServeiFilterForm.addHiddenField(ENLLAZCONSENTIMENT);
            solicitudServeiFilterForm.addHiddenField(TIPUSCONSENTIMENT);
            solicitudServeiFilterForm.addHiddenField(ENLLAZNORMALEGAL);

            solicitudServeiFilterForm.addHiddenField(SolicitudServeiFields.ID);
            solicitudServeiFilterForm.addHiddenField(SolicitudServeiFields.SERVEIID);

            AdditionalField<Long, String> SolSerIDField = new AdditionalField<Long, String>();
            SolSerIDField.setCodeName("=ID");
            SolSerIDField.setPosition(SOLSERID);
            SolSerIDField.setValueMap(new HashMap<Long, String>());
            SolSerIDField.setEscapeXml(false);
            solicitudServeiFilterForm.addAdditionalField(SolSerIDField);

            AdditionalField<Long, String> codiServeiField = new AdditionalField<Long, String>();
            codiServeiField.setCodeName("=Codi Servei");
            codiServeiField.setPosition(CODISERVEI);
            codiServeiField.setValueMap(new HashMap<Long, String>());
            codiServeiField.setEscapeXml(false);
            solicitudServeiFilterForm.addAdditionalField(codiServeiField);

            AdditionalField<Long, String> nomServeiField = new AdditionalField<Long, String>();
            nomServeiField.setCodeName("=Nom Servei");
            nomServeiField.setPosition(NOMSERVEI);
            nomServeiField.setValueMap(new HashMap<Long, String>());
            nomServeiField.setEscapeXml(false);
            solicitudServeiFilterForm.addAdditionalField(nomServeiField);

        }

        return solicitudServeiFilterForm;
    }

    @Override
    public List<StringKeyValue> getReferenceListForServeiID(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        
        log.info("XXXXXXXXXXXXXXX Where: " + where.toSQL());
        
        String separator = "|";
        OrderBy order = new OrderBy(ServeiFields.SERVEIID);

        Select<?>[] valueSelects = { ServeiFields.CODI.select, ServeiFields.NOM.select };

        Select<StringKeyValue> select = new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(
                ServeiFields.SERVEIID.select, separator, valueSelects);
        List<StringKeyValue> list = serveiEjb.executeQuery(select, where, order);
        
        return list;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, SolicitudServeiFilterForm filterForm,
            List<SolicitudServei> list) throws I18NException {

        super.postList(request, mav, filterForm, list);
        
        Map<Long, String> mapSolSerID;
        mapSolSerID = (Map<Long, String>) filterForm.getAdditionalField(SOLSERID).getValueMap();
        mapSolSerID.clear();

        Map<Long, String> mapCodiServei;
        mapCodiServei = (Map<Long, String>) filterForm.getAdditionalField(CODISERVEI).getValueMap();
        mapCodiServei.clear();

        Map<Long, String> mapNomServei;
        mapNomServei = (Map<Long, String>) filterForm.getAdditionalField(NOMSERVEI).getValueMap();
        mapNomServei.clear();

        for (SolicitudServei solicitudServei : list) {
            Long SolSerID = solicitudServei.getId();

            Servei servei = serveiEjb.findByPrimaryKey(solicitudServei.getServeiID());

            String codiServei = servei.getCodi();
            String nomServei = servei.getNom();

            mapSolSerID.put(SolSerID, String.valueOf(SolSerID));
            mapCodiServei.put(SolSerID, codiServei);
            mapNomServei.put(SolSerID, nomServei);
        }
    }
}
