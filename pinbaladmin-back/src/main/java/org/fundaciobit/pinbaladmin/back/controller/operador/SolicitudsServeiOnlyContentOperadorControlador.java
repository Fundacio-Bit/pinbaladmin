package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiForm;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.EntitatServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
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
            solicitudServeiFilterForm.addHiddenField(ENLLAZNORMALEGAL);

            //XXX CONSENT: Esborrar camps
            solicitudServeiFilterForm.addHiddenField(CONSENTIMENT);
            solicitudServeiFilterForm.addHiddenField(ENLLAZCONSENTIMENT);
            solicitudServeiFilterForm.addHiddenField(TIPUSCONSENTIMENT);

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
    
    @Override
    public SolicitudServeiForm getSolicitudServeiForm(SolicitudServeiJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {

		SolicitudServeiForm solicitudServeiForm = super.getSolicitudServeiForm(_jpa, __isView, request, mav);

		SolicitudServei solicitudServei = solicitudServeiForm.getSolicitudServei();

		Long normesAfegides = 1L;
		if (solicitudServei.getFitxernormaID() != null) {
			if (solicitudServei.getFitxernorma2ID() != null) {
				normesAfegides++;
				if (solicitudServei.getFitxernorma3ID() != null) {
					normesAfegides++;
				}
			}
		}
		request.setAttribute("normesAfegides", normesAfegides);
		solicitudServeiForm.setAttachedAdditionalJspCode(true);
		
		return solicitudServeiForm;
	}
    
    @Override
    public String crearSolicitudServeiPost(SolicitudServeiForm solicitudServeiForm, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		SolicitudServei solSer = solicitudServeiForm.getSolicitudServei();
		String ret = super.crearSolicitudServeiPost(solicitudServeiForm, result, request, response);

		if (result.hasErrors()) {
			Long normesAfegides = 1L;
			if (solSer.getFitxernormaID() != null) {
				if (solSer.getFitxernorma2ID() != null) {
					normesAfegides++;
					if (solSer.getFitxernorma3ID() != null) {
						normesAfegides++;
					}
				}
			}
			request.setAttribute("normesAfegides", normesAfegides);
		}
		return ret;
	}
    
    @Override
    public String editarSolicitudServeiPost(SolicitudServeiForm solicitudServeiForm, BindingResult result,
    		SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws I18NException {

		SolicitudServei solSer = solicitudServeiForm.getSolicitudServei();
		String ret = super.editarSolicitudServeiPost(solicitudServeiForm, result, status, request, response);

		if (result.hasErrors()) {
			Long normesAfegides = 1L;
			if (solSer.getFitxernormaID() != null) {
				if (solSer.getFitxernorma2ID() != null) {
					normesAfegides++;
					if (solSer.getFitxernorma3ID() != null) {
						normesAfegides++;
					}
				}
			}
			request.setAttribute("normesAfegides", normesAfegides);
		}
		return ret;
    }
    
    @Override
    public void preValidate(HttpServletRequest request, SolicitudServeiForm solicitudServeiForm, BindingResult result)
    		throws I18NException {
    	super.preValidate(request, solicitudServeiForm, result);
    	
		SolicitudServeiJPA solSer = solicitudServeiForm.getSolicitudServei();
		
		// Si el campo norma vale "none", poner sus campos a null y no marcar error
		if (solSer.getNorma2() != null && solSer.getNorma2().equals("none")) {
			solSer.setFitxernorma2ID(null);
			solSer.setFitxernorma2(null);
			solSer.setNorma2(null);
			solSer.setArticles2(null);
		}
		
		if (solSer.getNorma3() != null && solSer.getNorma3().equals("none")) {
			solSer.setFitxernorma3ID(null);
			solSer.setFitxernorma3(null);
			solSer.setNorma3(null);
			solSer.setArticles3(null);
		}
    }
    
    
    @Override
    public void postValidate(HttpServletRequest request, SolicitudServeiForm solicitudServeiForm, BindingResult result)
    		throws I18NException {
    	super.postValidate(request, solicitudServeiForm, result);
    	
		SolicitudServeiJPA solSer = solicitudServeiForm.getSolicitudServei();

		if (solSer.getFitxernorma() != null || solSer.getNormaLegal() != null || solSer.getArticles() != null) {
			if (solSer.getFitxernorma() == null || solSer.getNormaLegal() == null || solSer.getArticles() == null) {
				// Marcamos error y dejamos nulos todos los campos de la norma
				solSer.setFitxernormaID(null);
				solSer.setFitxernorma(null);
				solSer.setNormaLegal(null);
				solSer.setArticles(null);

				result.rejectValue(get(NORMALEGAL), "genapp.validation.malformed",
						new String[] { I18NUtils.tradueix(NORMALEGAL.fullName) }, null);
			}
		}

		if (solSer.getFitxernorma2ID() != null || solSer.getNorma2() != null || solSer.getArticles2() != null) {
			if (solSer.getFitxernorma2ID() == null || solSer.getNorma2() == null || solSer.getArticles2() == null) {
				// Marcamos error y dejamos nulos todos los campos de la norma
				solSer.setFitxernorma2ID(null);
				solSer.setFitxernorma2(null);
				solSer.setNorma2(null);
				solSer.setArticles2(null);

				result.rejectValue(get(NORMA2), "genapp.validation.malformed",
						new String[] { I18NUtils.tradueix(NORMA2.fullName) }, null);
				
			}
		}

		if (solSer.getFitxernorma3ID() != null || solSer.getNorma3() != null || solSer.getArticles3() != null) {
			if (solSer.getFitxernorma3ID() == null || solSer.getNorma3() == null || solSer.getArticles3() == null) {
				// Marcamos error y dejamos nulos todos los campos de la norma
				solSer.setFitxernorma3ID(null);
				solSer.setFitxernorma3(null);
				solSer.setNorma3(null);
				solSer.setArticles3(null);

				result.rejectValue(get(NORMA3), "genapp.validation.malformed",
						new String[] { I18NUtils.tradueix(NORMA3.fullName) }, null);
			}
		}
    	
    	
    }
}