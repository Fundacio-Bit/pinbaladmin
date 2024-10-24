package org.fundaciobit.pinbaladmin.back.controller.all;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.IncidenciaTecnicaController;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.PINFOLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.PINFO;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.persistence.PINFOJPA;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias 16 oct 2024 10:11:28
 */

@Controller
@RequestMapping(value = IncidenciaPinfoPublicController.CONTEXT_WEB)
@SessionAttributes(types = { IncidenciaTecnicaForm.class, IncidenciaTecnicaFilterForm.class })
public class IncidenciaPinfoPublicController extends IncidenciaTecnicaController {

	public static final String CONTEXT_WEB = "/public/incidenciapinfo";

	@EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
	protected IncidenciaTecnicaLogicaService incidenciaTecnicaLogicaEjb;

	@EJB(mappedName = PINFOLogicaService.JNDI_NAME)
	protected PINFOLogicaService pinfoLogicEjb;
	
	@Override
	public String getTileForm() {
		return "incidenciaTecnicaPinfoFormOperador";
	}

	@Override
	public String getTileList() {
		return "incidenciaTecnicaPinfoListOperador";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return this.getClass().getName() + "_FilterForm";
	}

	@Override
	public IncidenciaTecnicaForm getIncidenciaTecnicaForm(IncidenciaTecnicaJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {
		IncidenciaTecnicaForm form = super.getIncidenciaTecnicaForm(_jpa, __isView, request, mav);

		if (form.isNou()) {
			IncidenciaTecnicaJPA incidencia = form.getIncidenciaTecnica();

			incidencia.setTipus(Constants.INCIDENCIA_TIPUS_ROLEPERMISOS);
			form.addHiddenField(IncidenciaTecnicaFields.TIPUS);

			incidencia.setEstat(Constants.ESTAT_INCIDENCIA_OBERTA);
			form.addHiddenField(IncidenciaTecnicaFields.ESTAT);

			incidencia.setDataInici(new Timestamp(System.currentTimeMillis()));
			form.addHiddenField(IncidenciaTecnicaFields.DATAINICI);
			form.addHiddenField(IncidenciaTecnicaFields.DATAFI);

			incidencia.setOperador("pinbaladmin");
			incidencia.setCreador("pinbladmin");
			form.addHiddenField(IncidenciaTecnicaFields.OPERADOR);
			form.addHiddenField(IncidenciaTecnicaFields.CREADOR);

			form.addHiddenField(IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT);

			form.addLabel(IncidenciaTecnicaFields.NOMENTITAT, "departament.departament");
			
			String token = (String) request.getSession().getAttribute("token");
//			incidencia.setDescripcio("El meu Token es: " + token);
//			incidencia.setNomEntitat(token);
			
			Properties properties = (Properties) request.getSession().getAttribute("properties");
			String nomComplet = properties.getProperty("Nom") + " " + properties.getProperty("Cognom1") + " " + properties.getProperty("Cognom2");
			incidencia.setContacteNom(nomComplet);
			form.addReadOnlyField(IncidenciaTecnicaFields.CONTACTENOM);

			incidencia.setDescripcio("Descripció de test");
			incidencia.setContacteTelefon("971971971");
			incidencia.setContacteEmail("ptrias@fundaciobit.org");
			incidencia.setNomEntitat("La meva entitat: Fundació BIT");
			
			incidencia.setTitol("Titol de test");
			
			String usuariNIF = properties.getProperty("NIF");
			String usuariNom = properties.getProperty("Username");
			
			request.getSession().setAttribute("usuariData", usuariNIF + " - " + usuariNom);
			
			form.setAttachedAdditionalJspCode(true);
			mav.addObject("isPinfo", true);
			
			form.addHelpToField(NOMENTITAT, "MISSATGE PER VEURE HELP");
		}

		return form;
	}

	@Override
	public IncidenciaTecnicaFilterForm getIncidenciaTecnicaFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {
		IncidenciaTecnicaFilterForm filterForm = super.getIncidenciaTecnicaFilterForm(pagina, mav, request);

		log.info("getIncidenciaTecnicaFilterForm: " + filterForm);

		return filterForm;
	}

	@Override
	public IncidenciaTecnicaJPA create(HttpServletRequest request, IncidenciaTecnicaJPA incidenciaTecnica)
			throws I18NException, I18NValidationException {

		IncidenciaTecnicaJPA it;
		it = (IncidenciaTecnicaJPA) incidenciaTecnicaLogicaEjb.create(incidenciaTecnica);

		//Hem de crear el PINFO amb les dades de la incidencia
		Long incidenciaID = it.getIncidenciaTecnicaID();
		Long estat = 0L;
		Long fitxerID = null;
		Long fitxerFirmatID = null;
		String portafibid = null;
		
		PINFOJPA pinfo = new PINFOJPA(incidenciaID, estat, fitxerID, fitxerFirmatID, portafibid);
		PINFO PINFO = pinfoLogicEjb.create(pinfo);
		
		return it;
	}
	
	@RequestMapping(value = "/new/{token}", method = RequestMethod.GET)
	public String obtenirDadesFitxerToken(HttpServletRequest request, HttpServletRequest response, @PathVariable("token") java.lang.String token) {
		
		log.info("obtenirDadesFitxerToken token: " + token);
		File file = new File(FileSystemManager.getFilesPath(), token + ".front");		
		
		 Properties properties= new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (IOException e) {
			log.error("An error occurred." + e.getMessage(), e);
			e.printStackTrace();
		}
		
		request.getSession().setAttribute("properties", properties);
		request.getSession().setAttribute("token", token);
        return "redirect:" + CONTEXT_WEB + "/new";
	}

	@Override
	public void preValidate(HttpServletRequest request, IncidenciaTecnicaForm incidenciaTecnicaForm,
			BindingResult result) throws I18NException {
		super.preValidate(request, incidenciaTecnicaForm, result);
		
		{
			// Fer que els camps siguin obligatoris
			IncidenciaTecnicaJPA incidenciaTecnica = incidenciaTecnicaForm.getIncidenciaTecnica();
						
			if (incidenciaTecnica.getContacteNom() == null || incidenciaTecnica.getContacteNom().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(result, get(CONTACTENOM), "genapp.validation.required",
                        new Object[] { I18NUtils.tradueix(CONTACTENOM.fullName) });
			}
			if (incidenciaTecnica.getContacteTelefon() == null || incidenciaTecnica.getContacteTelefon().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(result, get(CONTACTETELEFON), "genapp.validation.required",
						new Object[] { I18NUtils.tradueix(CONTACTETELEFON.fullName) });
			}
			if (incidenciaTecnica.getNomEntitat() == null || incidenciaTecnica.getNomEntitat().isEmpty()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(result, get(NOMENTITAT), "genapp.validation.required",
						new Object[] { I18NUtils.tradueix(NOMENTITAT.fullName) });
			}
		}
	}
	
	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, IncidenciaTecnicaForm incidenciaTecnicaForm) {
		// Despres de crear la incidencia, ha de crear el PINFO, i redirigir a la pagina per afegir pinfodatas.
		log.info("getRedirectWhenCreated");
		
		//guardar inciencicaid a sessio
		request.getSession().setAttribute("incidenciaId", incidenciaTecnicaForm.getIncidenciaTecnica().getIncidenciaTecnicaID());
		
		return "redirect:" + PinfoDataPublicController.CONTEXT_WEB + "/list";
	}
	
	
}
