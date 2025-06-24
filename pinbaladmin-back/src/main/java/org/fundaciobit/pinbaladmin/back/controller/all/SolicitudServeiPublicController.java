package org.fundaciobit.pinbaladmin.back.controller.all;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.form.Section;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.FileDownloadController;
import org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesFormManager;
import org.fundaciobit.pinbaladmin.back.controller.all.ModificarSolicitudPublicController.ServeiInfo;
import org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudServeiOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudsServeiOnlyContentOperadorControlador;
import org.fundaciobit.pinbaladmin.back.controller.webdb.SolicitudServeiController;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSoliServFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.ModificacioSoliServForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.ModificacioSoliServ;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSoliServFields;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(SolicitudServeiPublicController.CONTEXT_WEB)
@SessionAttributes(types = { SolicitudServeiForm.class, SolicitudServeiFilterForm.class })
public class SolicitudServeiPublicController extends SolicitudsServeiOnlyContentOperadorControlador {

	public static final String CONTEXT_WEB = "/public/solicitudservei";

	
    public static final int NORMESFIELD = 1;

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerPublicLogicaEjb;
	
	@EJB(mappedName = ServeiLogicaService.JNDI_NAME)
	protected ServeiLogicaService serveiLogicaEjb;
	
    @Override
    protected FilesFormManager<Fitxer> getFilesFormManager() {
        return new PinbalAdminFilesFormManager(fitxerPublicLogicaEjb);
    }
    
	@Override
	public String getTileForm() {
		return "solicitudServeiFormPublic";
	}

	@Override
	public String getTileList() {
		return "solicitudServeiListPublic";
	}


    @Override
    public String getSessionAttributeFilterForm() {
        return "SolicitudServeiPublic";
    }
	
    @Override
    public boolean isPublic() {
    	return true;
    }

    
	@Override
	public SolicitudServeiFilterForm getSolicitudServeiFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {

		SolicitudServeiFilterForm filterForm = super.getSolicitudServeiFilterForm(pagina, mav, request);

		filterForm.getAdditionalButtons().clear();
		filterForm.getAdditionalButtonsByPK().clear();
		
		log.info("Pasam per aqui, bones");
		if (filterForm.isNou()) {
			log.info("Si, es nou");

			filterForm.setAddButtonVisible(true);
			filterForm.setFooterListVisible(false);
			filterForm.setVisibleExportList(false);
			
			Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
					Arrays.asList(SolicitudServeiFields.ALL_SOLICITUDSERVEI_FIELDS));

			filterForm.setHiddenFields(hiddenFields);
			
			//TODO: Afegir una columna amb totes les normes 
            AdditionalField<Long, String> normesField = new AdditionalField<Long, String>();
            normesField.setCodeName("=Normes");
            normesField.setPosition(NORMESFIELD);
            normesField.setValueMap(new HashMap<Long, String>());
            normesField.setEscapeXml(false);
            filterForm.addAdditionalField(normesField);
			
			filterForm.setAttachedAdditionalJspCode(true);
		}
		
		filterForm.setSubTitleCode("=");
		filterForm.setTitleCode("tramit.modificacions.modificacionsfetes");
		mav.addObject("isPublic", true);

		return filterForm;
	}

	@Override
	public SolicitudServeiForm getSolicitudServeiForm(SolicitudServeiJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {
		SolicitudServeiForm form = super.getSolicitudServeiForm(_jpa, __isView, request, mav);

		if (form.isNou()) {
			SolicitudServei solSer = form.getSolicitudServei();

			Long solicitudID = (Long) request.getSession()
					.getAttribute(ModificarSolicitudPublicController.SOLICITUD_ID);
			log.info("solicitudID: " + solicitudID);
			solSer.setSolicitudID(solicitudID);
			solSer.setCaduca("No Caduca");
			
			form.getSolicitudServei().setEstatSolicitudServeiID(Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_SERVEI);
		}
		form.addHiddenField(SolicitudServeiFields.ID);
		form.addHiddenField(SolicitudServeiFields.SOLICITUDID);
		form.addHiddenField(SolicitudServeiFields.NOTES);
		form.addHiddenField(SolicitudServeiFields.ESTATSOLICITUDSERVEIID);
		
		form.setAttachedAdditionalJspCode(true);
		mav.addObject("isPublic", true);

		return form;
	}

	@Override
	public Long getSolicitudID(HttpServletRequest request) {
		log.info("XXXXXXXXXX: Estamos usando getSolicitudID de PUBLIC");

		Long soliID;
		soliID = (Long) request.getSession().getAttribute(ModificarSolicitudPublicController.SOLICITUD_ID);
		log.info("Get parameter [" + ModificarSolicitudPublicController.SOLICITUD_ID + "] = " + soliID);

		if (soliID == null) {
			log.warn("SoliID es NULL!!");
		}

		return soliID;
	}

	@Override
	public SolicitudServeiJPA create(HttpServletRequest request, SolicitudServeiJPA solicitudServei)
			throws I18NException, I18NValidationException {
		return (SolicitudServeiJPA) solicitudServeiLogicaEjb.create(solicitudServei);
	}
	
	@Override
	public String getRedirectWhenCancel(HttpServletRequest request, Long id) {
		return getRedirectToModificarSolicitud(request);
	}
	
	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, SolicitudServeiForm solicitudServeiForm) {
		return getRedirectToModificarSolicitud(request);
	}
	
	@Override
	public String getRedirectWhenModified(HttpServletRequest request, SolicitudServeiForm solicitudServeiForm,
			Throwable __e) {
		return getRedirectToModificarSolicitud(request);
	}

	@Override
	public String getRedirectWhenDelete(HttpServletRequest request, Long id, Throwable __e) {
		return getRedirectToModificarSolicitud(request);
	}

	private String getRedirectToModificarSolicitud(HttpServletRequest request) {
		Long modSoliID = (Long) request.getSession().getAttribute(ModificarSolicitudPublicController.MOD_SOLI_ID);
		return "redirect:" + ModificarSolicitudPublicController.CONTEXT_WEB + "/" + modSoliID + "/edit";
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
		// TODO Auto-generated method stub

		Long[] estats_modificacio = { Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_NORMA,
				Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_SERVEI };
		Where wEstatPerAfegir = SolicitudServeiFields.ESTATSOLICITUDSERVEIID.in(estats_modificacio);

		return Where.AND(super.getAdditionalCondition(request), wEstatPerAfegir);
	}

	@Override
	public SolicitudServeiJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
		return (SolicitudServeiJPA) solicitudServeiLogicaEjb.findByPrimaryKey(id);
	}

	@Override
	public SolicitudServeiJPA update(HttpServletRequest request, SolicitudServeiJPA solicitudServei)
			throws I18NException, I18NValidationException {
		return (SolicitudServeiJPA) solicitudServeiLogicaEjb.update(solicitudServei);
	}
	
	public List<StringKeyValue> getReferenceListForServeiID(HttpServletRequest request, ModelAndView mav, Where where)
			throws I18NException {
		
		List<StringKeyValue> tmp = new java.util.ArrayList<StringKeyValue>();

        Long ESTAT_SERVEI_PRODUCCIO = 20L;

        
		Where wEstatOk = ServeiFields.ESTATSERVEIID.equal(ESTAT_SERVEI_PRODUCCIO);
		
		Long soliID = getSolicitudID(request);
		List<Long> serveisID = solicitudServeiLogicaEjb.executeQuery(SolicitudServeiFields.SERVEIID, SolicitudServeiFields.SOLICITUDID.equal(soliID));
		
		Where wNotInSoliServ = ServeiFields.SERVEIID.notIn(serveisID);
		log.info("Serveis: " + serveisID.size());
		log.info(serveisID);
		
		Where wVisible = ServeiFields.OCULT.equal(false);
		
		List<Servei> serveis = serveiLogicaEjb.select(Where.AND(wEstatOk, wNotInSoliServ, wVisible));
		
		for (Servei servei : serveis) {
            String key =String.valueOf(servei.getServeiID());
//            EntitatServei entitatServei = entitatLogicaEjb.findByPrimaryKey(servei.getEntitatServeiID());
//            String value = "(" + entitatServei.getNom()+ ") " + servei.getNom();
            String value = servei.getCodi();
            
            tmp.add(new StringKeyValue(key, value));
		}
		return tmp;
	}
	
	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, SolicitudServeiFilterForm filterForm,
			List<SolicitudServei> list) throws I18NException {
		// TODO Auto-generated method stub
		super.postList(request, mav, filterForm, list);

		Map<Long, String> mapNormes;
        mapNormes = (Map<Long, String>) filterForm.getAdditionalField(NORMESFIELD).getValueMap();
        mapNormes.clear();

        //		Constants.ESTAT_SOLICITUD_SERVEI_AUTORITZAT

        for (SolicitudServei ss : list) {

			List<String> normes = new ArrayList<String>();

			if (ss.getFitxernorma() != null) {
				normes.add(normaToHref(ss.getFitxernorma(), ss.getNormaLegal()));
			} else if (ss.getEnllazNormaLegal() != null && isValidURL(ss.getEnllazNormaLegal())) {
				normes.add("<a href=\"" + ss.getEnllazNormaLegal() + "\">" + ss.getNormaLegal() + "</a>");

			}
			if (ss.getFitxernorma2() != null) {
				normes.add(normaToHref(ss.getFitxernorma2(), ss.getNorma2()));
			}
			if (ss.getFitxernorma3() != null) {
				normes.add(normaToHref(ss.getFitxernorma3(), ss.getNorma3()));
			}

			mapNormes.put(ss.getId(), String.join("<br>", normes));

		}
        
        filterForm.getHiddenFields().add(SolicitudServeiFields.FITXERNORMAID);
		filterForm.getHiddenFields().add(SolicitudServeiFields.FITXERNORMA2ID);
		filterForm.getHiddenFields().add(SolicitudServeiFields.FITXERNORMA3ID);
        
        
//log.info("Test serveis Solicitud " + solicitudID);
//		
//		List<ServeiInfo> serveis = new ArrayList<ModificarSolicitudPublicController.ServeiInfo>();
//		Where wSoliID = SolicitudServeiFields.SOLICITUDID.equal(solicitudID);
//		
//		Where wEstatNoPerAfegir = SolicitudServeiFields.ESTATSOLICITUDSERVEIID.notEqual(Constants.ESTAT_SOLICITUD_SERVEI_DISCONTINUAT);
//		
//		List<SolicitudServei> solicitudServeiList = solicitudServeiLogicaEjb.select(Where.AND(wSoliID, wEstatNoPerAfegir));
//
//		for (SolicitudServei ss : solicitudServeiList) {
//			Servei servei = serveiLogicaEjb.findByPrimaryKey(ss.getServeiID());
//
//			if (servei != null) {
//
//				String estat = I18NUtils.tradueix("estat.solicitudservei." + ss.getEstatSolicitudServeiID());
//
//				List<String> normes = new ArrayList<String>();
//
//				if (ss.getFitxernorma() != null) {
//					normes.add(normaToHref(ss.getFitxernorma(), ss.getNormaLegal()));
//				} else if (ss.getEnllazNormaLegal() != null && isValidURL(ss.getEnllazNormaLegal())) {
//					normes.add("<a href=\"" + ss.getEnllazNormaLegal() + "\">" + ss.getNormaLegal() + "</a>");
//
//				}
//				if (ss.getFitxernorma2() != null) {
//					normes.add(normaToHref(ss.getFitxernorma2(), ss.getNorma2()));
//				}
//				if (ss.getFitxernorma3() != null) {
//					normes.add(normaToHref(ss.getFitxernorma3(), ss.getNorma3()));
//				}
//
//				ServeiInfo sInfo = new ServeiInfo(ss.getId(), servei.getCodi(), servei.getNom(), estat,
//						String.join("<br>", normes));
//
//				serveis.add(sInfo);
//			}
//		}
	}
	
	private String normaToHref(Fitxer f, String norma) {
		String url = "/pinbaladmin" + FileDownloadController.fileUrl(f);
		log.info(url);

		return "<a href=\"" + url + "\">" + norma + "</a>";

	}
	boolean isValidURL(String url) {
		try {
			new URL(url).toURI();
			return true;
		} catch (MalformedURLException e) {
			return false;
		} catch (URISyntaxException e) {
			return false;
		}
	}
}
