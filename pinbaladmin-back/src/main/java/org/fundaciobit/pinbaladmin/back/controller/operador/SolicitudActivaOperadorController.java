package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.back.security.LoginInfo;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments.TipusProcediment;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitHProcLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitJConsentLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.FileInfo;
import org.fundaciobit.pinbaladmin.logic.utils.PdfDownloader;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.InfoMadridFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitHProcFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.EstadoProcedimiento;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = SolicitudActivaOperadorController.CONTEXTWEB)
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudActivaOperadorController extends SolicitudOperadorController {

	public static final String CONTEXTWEB = "/operador/solicitudactiva";

    @EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
    protected InfoMadridLogicaService infoMadridLogicaEjb;
    
    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;
    
    @EJB(mappedName = TramitHProcLogicaService.JNDI_NAME)
    protected TramitHProcLogicaService tramitHLogicaEjb;
    
    @EJB(mappedName = TramitJConsentLogicaService.JNDI_NAME)
    protected TramitJConsentLogicaService tramitJLogicaEjb;
    
	
    @EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
    protected FitxerPublicLogicaService fitxerLogicEjb;
    
	@Override
	public Where getAdditionalConditionFine(HttpServletRequest request) throws I18NException {
		return super.getAdditionaConditionAdvancedFilter(request);
//		return SolicitudFields.ESTATSOLICITUD.lessThan(60L); // 60 == ESTAT TANCAT
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "SolicitudWebDB_Activa_FilterForm_Operador";
	}

	@Override
	public String getEntityNameCode() {
		return "solicitud.solicitudactiva";
	}

	@Override
	public String getEntityNameCodePlural() {
		return "solicitud.solicitudactiva.plural";
	}

	@Override
	public Boolean isEstatal() {
		return null; // Significa que gestiona els dos tipus
	}

	@Override
	public boolean showAdvancedFilter() {
		return true;
	}

	@Override
	public SolicitudFilterForm getSolicitudFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		// TODO Auto-generated method stub
		SolicitudFilterForm solicitudFilterForm = super.getSolicitudFilterForm(pagina, mav, request);

		if (solicitudFilterForm.isNou()) {
//			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_BELL, "solicitud.actualitzarestats",
//					getContextWeb() + "/actualitzarEstats", AdditionalButtonStyle.WARNING));
//			
			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_BELL, "Actualiza Tipo Procedimeitno y Fecha Caducidad",
					getContextWeb() + "/updateSoli", AdditionalButtonStyle.WARNING));

//			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_BELL, "Crear Info Madrid",
//					getContextWeb() + "/crearInfoMadrid", AdditionalButtonStyle.WARNING));

//			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_BELL, "Recuperar Consentimiento",
//					getContextWeb() + "/recuperarConsentimiento", AdditionalButtonStyle.WARNING));
//			
//			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_OK_CIRCLE, "ReBuscar Consentimiento",
//					getContextWeb() + "/rebuscarUrlConsentimiento", AdditionalButtonStyle.INFO));
			
			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_OK_CIRCLE, "Corregir tipo y url Consentimiento",
					getContextWeb() + "/corregirUrlConsentimento", AdditionalButtonStyle.INFO));

			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_BELL, "Actualizar Titulares",
					getContextWeb() + "/actualizarTitulares", AdditionalButtonStyle.WARNING));
			
			solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_BELL, "Actualizar Caducidad",
					getContextWeb() + "/actualizarCaducidad", AdditionalButtonStyle.PRIMARY));
			
		}

		return solicitudFilterForm;
	}
	
	
	
	
	@RequestMapping(value = "/corregirUrlConsentimento", method = RequestMethod.GET)
	public String corregirUrlConsentimento(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		consentimientoDefinitivo();
		
		HtmlUtils.saveMessageSuccess(request, "Consentiments actualitzats correctament.");
		return "redirect:" + getContextWeb() + "/list";
	}
	
	
	public class InfoConsentiment{
		String tipus;
		String url;
		Fitxer fitxer;
		Long fitxerID;
		
		public InfoConsentiment(String tipus, String url, Fitxer fitxer, Long fitxerID) {
			super();
			this.tipus = tipus;
			this.url = url;
			this.fitxer = fitxer;
			this.fitxerID = fitxerID;
		}
		
		public String getTipus() {
			return tipus;
		}

		public String getUrl() {
			return url;
		}

		public Fitxer getFitxer() {
			return fitxer;
		}

		public Long getFitxerID() {
			return fitxerID;
		}
		
		
		
	}
	
	List<String> urlsAmbError = new java.util.ArrayList<>();
	
	int noTeXML = 0;
	int noTeConsentiment = 0;
	int errorsDescarga = 0;
	int consentimentNoRecuperat = 0;
	
	public void consentimientoDefinitivo() throws Exception {
		noTeXML = 0;
		noTeConsentiment = 0;
		errorsDescarga = 0;
		consentimentNoRecuperat = 0;
		
		//Obtener todas las solicitudes locales.
		urlsAmbError = new java.util.ArrayList<>();
		//Para cada solicitud, primero buscaremos el consentimiento en el tramite.
		//Si no lo tenemos, buscamos en los documentos.
		//Si no aparece ahí, buscamos en las URLs de los servicios.
		//Si no aparece ahí, buscamos en el fitchero xml.
		
		List<Solicitud> solicituds = getSolicitudesLocales();

		int total = solicituds.size();
		int idx = 0;
		
		int consentimientosRecuperados = 0;
		int tramitH = 0;
		int docs = 0;
		int serveis = 0;
		int perLlei = 0;
		int formulariXML = 0;
		int XMLperLlei = 0;
//		int noConsentiment = 0;
		
		for (Solicitud solicitud : solicituds) {
			Long soliID = solicitud.getSolicitudID();
			idx++;

			String cadenaInfo = idx + "/" + total + " - SoliID: " + soliID + ": ";

			InfoConsentiment infoCons = null;

			infoCons = buscarConsentimientoTramit(solicitud);

			if (infoCons != null) {
				log.info(cadenaInfo + "Trobat en TramitH. Consent: " + infoCons.getTipus());
				tramitH++;
			} else {
				log.info(cadenaInfo + "No te tramitJ. Cercant documents...");

				infoCons = buscarConsentimientoDocuments(soliID);
				//Este no puede devolver consentimiento por ley.
				if (infoCons != null) {
					log.info(cadenaInfo + "Trobat en Documents.");
					docs++;
				} else {
					log.info(cadenaInfo + "No te documents. Cercant serveis...");
					infoCons = buscarConsentimientoUrlServeis(soliID);

					// Buscar en formulario XML.
					if (infoCons != null) {
						log.info(cadenaInfo + "Trobat en Serveis.");
						if (infoCons.getTipus().equals("llei")) {
							log.info(cadenaInfo + " Es per Llei.");
							perLlei++;
						}else {
							log.info(cadenaInfo + " Tenim document amb URL.");
							serveis++;
						}
						
					} else {
						log.info(cadenaInfo + "No te Serveis. Cercant Formulari XML...");
						infoCons = buscarConsentimientoFormularioXML(solicitud);
						if (infoCons != null) {
							log.info(cadenaInfo + "Trobat en Formulari XML.");
							
							if (infoCons.getTipus().equals("llei")) {
								log.info(cadenaInfo + " Es per Llei.");
								XMLperLlei++;
							} else {
								log.info(cadenaInfo + " Tenim document amb URL.");
								formulariXML++;
							}
						}
					}
				}
			}

			if (infoCons != null) {
				//eliminarFitxerConsentimentAnterior(solicitud);
				assignarConsentimentSoli(solicitud, infoCons);
				consentimientosRecuperados++;
			} else {
				log.info("No s'ha pogut recuperar el consentiment per a la SoliID: " + soliID);
			}
			
//			if (idx == 500) {
//				break;
//			}
		}
		
		log.info("----- RESUM DE L'EXECUCIÓ -----");
		log.info("--------------------------------");
		log.info("Solicituds processades: " + total);
		log.info("Consentimientos recuperados: " + consentimientosRecuperados);
		log.info(" - De TramitH: " + tramitH);
		log.info(" - De Documents: " + docs);
		log.info(" - Serveis (llei): " + perLlei);
		log.info(" - Serveis (url): " + serveis);
		log.info(" - Sense fitxer XML: " + noTeXML);
		log.info(" - Formulari XML (llei): " + XMLperLlei);
		log.info(" - Formulari XML (url): " + formulariXML);
		log.info(" - XML no es llei ni te URLs: " + noTeConsentiment);
		log.info(" - XML te URLs pero no recuperat: " + consentimentNoRecuperat);
		
		int conResultado = tramitH + docs + perLlei + serveis + XMLperLlei + formulariXML + noTeConsentiment + consentimentNoRecuperat + noTeXML;
		log.info("Consentiments amb resultat: " + conResultado + "/" + total);
		
//		log.info(" - Sense consentiment: " + noConsentiment);
		
		log.info("Llistat d'URLs amb problemes: " + urlsAmbError.size());
		//Ordenar les URLs
		Collections.sort(urlsAmbError);
		String res = "URLs amb problemes:\n\n";
		for (String url : urlsAmbError) {
			res += url + "\n";
		}
		log.info(res);
		
		
		/*
		 * 
2025-11-07 15:11:08,945 INFO  [org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudActivaOperadorController] (default task-61) Consentimientos recuperados: 796
2025-11-07 15:11:08,945 INFO  [org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudActivaOperadorController] (default task-61)  - De TramitH: 152
2025-11-07 15:11:08,946 INFO  [org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudActivaOperadorController] (default task-61)  - De Documents: 342
2025-11-07 15:11:08,946 INFO  [org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudActivaOperadorController] (default task-61)  - Serveis (llei): 111
2025-11-07 15:11:08,947 INFO  [org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudActivaOperadorController] (default task-61)  - Serveis (url): 185
2025-11-07 15:11:08,948 INFO  [org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudActivaOperadorController] (default task-61)  - Formulari XML (url): 6
2025-11-07 15:11:08,949 INFO  [org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudActivaOperadorController] (default task-61)  - Sense consentiment: 849


		 */
		
		
		
		
	}
	
	public List<Solicitud> getSolicitudesLocales() {
		Where wLocals = SolicitudFields.ORGANID.isNotNull();
		List<Solicitud> solicituds = null;
		try {
			solicituds = solicitudLogicaEjb.select(Where.AND(wLocals));
		} catch (I18NException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return solicituds;
	}
	
	public InfoConsentiment buscarConsentimientoTramit(Solicitud soli) throws I18NException {
		//Obtener tramitID con tramitH y buscar el tramitJ con tramitID.
		
		List<TramitHProc> tramitsH = tramitHLogicaEjb.select(TramitHProcFields.CODI.equal(soli.getProcedimentCodi()));
		if (tramitsH.size() == 0) {
			return null;
		}
		
		Long tramitID = tramitsH.get(0).getTramitid();
		
		List<TramitJConsent> consents = tramitJLogicaEjb.select(TramitJConsentFields.TRAMITID.equal(tramitID));		
		if (consents.size() == 0) {
			return null;
		}
		
		TramitJConsent consentiment = consents.get(0);

		String tipus = consentiment.getConsentiment();
		String url = consentiment.getUrlconsentiment();
		Long fitxerID = consentiment.getAdjuntID();
		Fitxer fitxer = null;
		if (fitxerID != null) {
			fitxer = fitxerLogicEjb.findByPrimaryKey(fitxerID);
		}
		
		InfoConsentiment infoConsentiment = new InfoConsentiment(tipus, url, fitxer, fitxerID);
		
		return infoConsentiment;
	}
	
	public InfoConsentiment buscarConsentimientoDocuments(Long soliID) throws I18NException {
		// Obtener documentos de la solicitud de tipo Consentiment.

		Long[] tipusConsentimentArray = new Long[] { Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_NOOP,
				Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI };

		List<Long> documentsSoli = documentSolicitudEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(soliID));

		List<Document> documents = documentEjb.select(Where.AND(DocumentFields.DOCUMENTID.in(documentsSoli),
				DocumentFields.TIPUS.in(tipusConsentimentArray)));

		if (documents.size() == 0) {
			return null;
		}

		Document consentiment = documents.get(documents.size() - 1); // Agafam l'ultim com a vàlid.)

		String tipus = consentiment.getTipus().equals(Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI) ? "si" : "noop";
		String url = null;
		
		Long fitxerID = consentiment.getFitxerOriginalID();
		Fitxer fitxer = fitxerLogicEjb.findByPrimaryKey(fitxerID);
		
		InfoConsentiment infoConsentiment = new InfoConsentiment(tipus, url, fitxer, fitxerID);
		return infoConsentiment;
	}
	
	public InfoConsentiment buscarConsentimientoUrlServeis(Long soliID) throws I18NException {
		// Buscar en las URLs de los servicios asociados a la solicitud.

		List<SolicitudServei> soliServs = solicitudServeiEjb.select(SolicitudServeiFields.SOLICITUDID.equal(soliID));
		
		List<String> tipus = new java.util.ArrayList<>();
		List<String> urls = new java.util.ArrayList<>();
		
		// Saber si tots els serveis tenen el mateix consentiment, i despres comparar
		for (SolicitudServei solSer : soliServs) {
			String consentiment = solSer.getConsentiment();
			String urlSer = solSer.getEnllazConsentiment();

			if (consentiment != null && !consentiment.isEmpty()) {
				if (!tipus.contains(consentiment)) {
					tipus.add(consentiment);
				}
			}

			if (urlSer != null && !urlSer.isEmpty()) {
				if (isValidURL(urlSer)) {
					if (!urls.contains(urlSer)) {
						urls.add(urlSer);
					}
				}
			}
		}
		
		return cercarConsentimentAmbDadesServeis(soliServs, tipus, urls);
	}


	public InfoConsentiment buscarConsentimientoFormularioXML(Solicitud soli) throws Exception {

		Long fitxerID = soli.getSolicitudXmlID();

		if (fitxerID == null) {
			noTeXML++;
			return null;
		}

		Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
		if (prop == null) {
			noTeXML++;
			return null;
		}
		
		//Aqui pueden ser dos cosas. Que sea formulario antiguo, o nuevo.
		
		//En el nuevo se obtiene directamente. En el antiguo, hay que revisar todos los servicios.
		
		/*
		 * Nuevo
		 * 
		 * <VALOR codigo="CONSENTIMIENTO">noop</VALOR>
<VALOR codigo="CONSADJ">Modelo solicitud 1860-REV1.pdf</VALOR>
<VALOR codigo="CONSURL">---</VALOR>
		 * 
		 * 
		 * 
		 * Antiguo
		 * 
		 * 
		 * <LELSERVICIOS>
<ID1>
<NOMSERVEI indice="SVDCCAACPASWS01">(SVDCCAACPASWS01) Corriente de pago para ayudas y subvenciones</NOMSERVEI>
<CODISERV>SVDCCAACPASWS01</CODISERV>
<NORMALEGAL>Resolución por la que se aprueba la convocatoria de ayudas para la emisión de bonos digitales para colectivos vulnerables</NORMALEGAL>
<ARTICULOS>7.5</ARTICULOS>
<CONSENTIMIENTO indice="noop">No oposició</CONSENTIMIENTO>
<ENLACENOR>https://www.caib.es/seucaib/ca/arxiuServlet?id=5398122</ENLACENOR>
<LDECONSENTIMIENTO indice="1">Publicat</LDECONSENTIMIENTO>
<ENLACECON>https://www.caib.es/seucaib/ca/arxiuServlet?id=5398153</ENLACECON>
</ID1>
<ID2>
<NOMSERVEI indice="Q2827003ATGSS001">(Q2827003ATGSS001) Estar al Corriente de Pago con la Seguridad Social</NOMSERVEI>
<CODISERV>Q2827003ATGSS001</CODISERV>
<NORMALEGAL>Resolución por la que se aprueba la convocatoria de ayudas para la emisión de bonos digitales para colectivos vulnerables</NORMALEGAL>
<ARTICULOS>7.5</ARTICULOS>
<CONSENTIMIENTO indice="noop">No oposició</CONSENTIMIENTO>
<ENLACENOR>https://www.caib.es/seucaib/ca/arxiuServlet?id=5398122</ENLACENOR>
<LDECONSENTIMIENTO indice="1">Publicat</LDECONSENTIMIENTO>
<ENLACECON>https://www.caib.es/seucaib/ca/arxiuServlet?id=5398153</ENLACECON>
</ID2>

		 * 
		 */

		
		String tipus = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CONSENTIMIENTO");
		tipus = normalizarTipusConsentiment(tipus);

		String url = null;
		
		if (tipus != null && !tipus.isEmpty()) {
			Fitxer fitxer = null;
			Long fitxerConsentimentID = null;
			if (tipus.equals("si") || tipus.equals("noop")) {
				//Cercam url:
				url = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.ENLACENOR");
				log.info("\t\tURL de consentiment del formulari XML: " + url);
				
				if (url != null && !url.isEmpty() && isValidURL(url)) {
					Fitxer file = crearFitxerConsentimentFromUR2L(url);

					if (file != null) {
						fitxer = file;
						fitxerConsentimentID = fitxer.getFitxerID();
					}
				}
				
			}
			InfoConsentiment infoConsentiment = new InfoConsentiment(tipus, url, fitxer, fitxerConsentimentID);
			return infoConsentiment;
		} 
		
		//Si no es encuentra ahí, puede ser antiguo.
		log.info("No hem trobat el consentiment. Falta provar amb l'antic format.");
		
//		
//		//Antiguo.
//		//Recorremos todos los servicios directamente en el fichero.
		
		List<String> tipusList = new java.util.ArrayList<>();
		List<String> urlsList = new java.util.ArrayList<>();
		
		
		
		int idx = 1;
		while (true) {
			String prefix = "FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + idx + ".";
			String tipusServ = prop.getProperty(prefix + "CONSENTIMIENTO");
			tipusServ = normalizarTipusConsentiment(tipusServ);
			
			if (tipusServ == null) {
				break;
			}
			String urlServ = prop.getProperty(prefix + "ENLACECON");
			
			
			if (tipusServ != null && !tipusServ.isEmpty()) {
				if (!tipusList.contains(tipusServ)) {
					tipusList.add(tipusServ);
				}
			}
			
			if (urlServ != null && !urlServ.isEmpty() && isValidURL(urlServ)) {
				if (!urlsList.contains(urlServ)) {
					urlsList.add(urlServ);
				}
			}
			
			log.info("\t\t  Serveis ID" + idx + ": Tipus consentiment: " + tipusServ + ", URL: " + urlServ);
			
			idx++;
		}
		
//		log.info("\t\tProvant " + urlsList.size() + " URLs de consentiment dels serveis del formulari XML...");
//		log.info("\t\tAmb " + tipusList.size() + " tipus diferents de consentiment.");
		
		InfoConsentiment infoConsentiment = cercarConsentimentAmbDadesServeis(null, tipusList, urlsList);
		
		if (infoConsentiment == null) {
			
			if (urlsList.size() == 0) {
				//Si no te URLs, es que no te consentiment.
				noTeConsentiment++;
			}else {
				// Si te URLs i es null, no se ha podido recuperar.
				consentimentNoRecuperat++;
			}
		}
		
		
		return infoConsentiment;
	}
	
	private InfoConsentiment cercarConsentimentAmbDadesServeis(List<SolicitudServei> soliServs, List<String> tipus,
			List<String> urls) {
		//El problema es quan hi ha diferents tipus de consentiment o diferents URLs.
		
		//Si todos son ley, devolvemos ley. Sin fichero ni URL.
		
		//Si hay alguno de ley, pero todos los demás son iguales, devolvemos ese.
		
		// Si no hay ley, pero todos son iguales, devolvemos ese.
		
		// Si no hay ley, y hay diferentes tipos, elegimos uno cualquiera.
		
		// Si el final no es ley, tenemos que buscar el consentimiento en alguna de las URLs.
		
		if (tipus.contains("llei") && tipus.size() == 1) {
            log.info("\t\tTots els serveis tenen consentiment per llei.");
            // Tots els serveis tenen consentiment per llei.
            InfoConsentiment infoConsentiment = new InfoConsentiment("llei", null, null, null);
            return infoConsentiment;
        }
			
		// A partir de aqui, sabemos que hay alguno que no es ley.
		// Si hay mas de uno, uno no es ley y hay que buscar consentimiento.
		// y si solo hay uno, sabemos que no es ley.
		
		//Si hay ley, hay que descartarla
		if (tipus.contains("llei")) {
			tipus.remove("llei");
		}
		
		//Aqui tenemos solo los que no son ley. (si i noop) Buscamos procedimiento con URLs, i asignamos el tipo de la URL que haya funcionado.
		if (urls.size() == 0) {
			log.info("\t\tNo hi ha URLs de consentiment als serveis.");
            return null;
		}

		log.info("\t\tProvant " + urls.size() + " URLs de consentiment dels serveis...");
		for (String url : urls) {
			log.info("\t\tProvant URL de consentiment: " + url);
			Fitxer fitxerConsentiment = crearFitxerConsentimentFromUR2L(url);
			Long fitxerID = null;
			if (fitxerConsentiment != null) {
				fitxerID = fitxerConsentiment.getFitxerID();
				log.info("\t\tFitxer de consentiment recuperat i guardat. FitxerID: " + fitxerID);
				// Asignar este tipo.
				String tipusFinal = null;
				
				if (soliServs == null) {
					// No tenim els serveis. Agafem el primer tipus.
					tipusFinal = tipus.get(0);
					InfoConsentiment infoConsentiment = new InfoConsentiment(tipusFinal, url, fitxerConsentiment,
							fitxerID);
					return infoConsentiment;
					
				}
				
				for (SolicitudServei solSer : soliServs) {
					String urlSer = solSer.getEnllazConsentiment();
					if (urlSer != null && urlSer.equals(url) && solSer.getConsentiment() != null && !solSer.getConsentiment().equals("llei")) {
						tipusFinal = solSer.getConsentiment();
						break;
					}
				}
				if (tipusFinal == null) {
					tipusFinal = tipus.get(0); // Agafem el primer.
				}
				InfoConsentiment infoConsentiment = new InfoConsentiment(tipusFinal, url, fitxerConsentiment, fitxerID);
				return infoConsentiment;
			}
		}
		
		//Si llega hasta aquí, no hemos podido descargar ningún fichero de las URLs.
		return null;
	}
	
	
	private String normalizarTipusConsentiment(String tipus) {
		if (tipus == null) {
			return null;
		}
		tipus = tipus.toLowerCase();
		tipus = Normalizer.normalize(tipus, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		if (tipus.startsWith("no")) {
			return "noop";
		} else if (tipus.startsWith("si")) {
			return "si";
		} else if (tipus.startsWith("l")) {
			return "llei";
		}
		return tipus;
	}
	
	public void assignarConsentimentSoli(Solicitud solicitud, InfoConsentiment infoConsentiment) throws I18NException {

		// Obtenir el consentiment anterior, i mostrar quin serà el nou.
		try {

			Long fitxerID = infoConsentiment.getFitxerID();
			if (fitxerID == null) {
				// No tenim fitxer. Assignam directament.
				solicitud.setFitxerConsentimentID(null);
			} else {
				// Tenim fitxer. Fem copia.
				Long fitxerCopiaID = ferCopiaFitxer(fitxerID);
				solicitud.setFitxerConsentimentID(fitxerCopiaID);
			}
			solicitud.setUrlconsentiment(infoConsentiment.getUrl());
			solicitud.setConsentiment(infoConsentiment.getTipus());

//			solicitudLogicaEjb.update(solicitud);
			
			if (!solicitud.getConsentiment().equals(infoConsentiment.getTipus())) {
				log.warn("\t\tATENCIO! El tipus de consentiment a assignar no coincideix amb el de la solicitud. ");
				log.warn("\t\t\tSolicitud: "  + solicitud.getSolicitudID() + " - "+ solicitud.getConsentiment() + ", A assignar: " + infoConsentiment.getTipus());
				
			}
			
			
			log.info("\t\tAssignant a la SoliID: " + solicitud.getSolicitudID() + " el consentiment: "
					+ infoConsentiment.getTipus() + ", FitxerID: " + solicitud.getFitxerConsentimentID());

			log.info("\t\tFitxer de consentiment assignat correctament a SoliID: " + solicitud.getSolicitudID());

		} catch (Exception e) {
			log.error("Error assignant fitxer de consentiment a SoliID: " + solicitud.getSolicitudID(), e);
		}
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/rebuscarUrlConsentimiento", method = RequestMethod.GET)
	public String rebuscarUrlConsentimiento(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Coger todas las solicitudes sin consentimineto y buscar en las URLs de los
		// servicios que tiene.

		Where wLocals = SolicitudFields.ORGANID.isNotNull();
		Where wSenseFitxerConsent = SolicitudFields.FITXERCONSENTIMENTID.isNull();
		
		List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wLocals, wSenseFitxerConsent));
		
		int consentimientosRecuperados = 0;
		
		int total = solicituds.size();
		int idx = 0;
		
		//Lista de URLs con problemas.
		List<String> urlsProblema = new java.util.ArrayList<>();
		
		for (Solicitud solicitud : solicituds) {
			Long soliID = solicitud.getSolicitudID();
			idx++;

			List<SolicitudServei> soliServs = solicitudServeiEjb.select(SolicitudServeiFields.SOLICITUDID.equal(soliID));

			Fitxer fitxerConsentiment = null;
			String urlFinal = null;
			for (SolicitudServei solSer : soliServs) {

				String urlConsentiment = solSer.getEnllazConsentiment();
				
				if (fitxerConsentiment == null && urlConsentiment != null && !urlConsentiment.isEmpty()) {
					
					if (urlConsentiment.contains("www.caib.es")) {
						//Sustituir www por intranet.
						urlConsentiment = urlConsentiment.replace("www.caib.es", "intranet.caib.es");
						
					}

					log.info(idx + "/" + total + " - SolicitudID: " + soliID + ". Provant URL: " + urlConsentiment);
					// Ahora hay que ver si podemos obtener un fichero de alguna de las URLs de
					// consentimiento.

					if (urlsProblema.contains(urlConsentiment)) {
						log.info("SolicitudID: " + soliID + ". Saltant URL repetida: " + urlConsentiment);
						continue;
					}

					if (!isValidURL(urlConsentiment)) {
						log.info("SolicitudID: " + soliID + ". URL NO VALIDA: " + urlConsentiment);
						continue;
					}

					log.info("SolicitudID: " + soliID + ". URL VALIDA: " + urlConsentiment);

					Fitxer file = crearFitxerConsentimentFromUR2L(urlConsentiment);

					if (file == null) {
						urlsProblema.add(urlConsentiment);

						log.info("SolicitudID: " + soliID + ". Error recuperant fitxer amb URL: " + urlConsentiment);
						continue;
					}

					fitxerConsentiment = file;
					urlFinal = urlConsentiment;
					consentimientosRecuperados++;

					log.info("SolicitudID: " + soliID + ". Fitxer consentiment recuperat i guardat. FitxerID: "
							+ file.getFitxerID());
					break;

				}
			}
			if (fitxerConsentiment == null) {
//					log.info("\t\tNo s'ha pogut recuperar el consentiment per a la SoliID: " + soliID);
				continue;
			}
			assignarConsentimentSoli(solicitud, fitxerConsentiment);
			
			log.info("SolicitudID: " + soliID + ". Tenim el consentiment per URL: " + urlFinal);

			solicitud.setUrlconsentiment(urlFinal);
			solicitudLogicaEjb.update(solicitud);
		}
		
		log.info("Consentimientos recuperados: " + consentimientosRecuperados);
		log.info("Errors recuperant fitxers: " + urlsProblema.size());
		
		log.info("URLs con problemas:");
		Collections.sort(urlsProblema);

		for (String s : urlsProblema) {
			log.info(s);
		}
		

		HtmlUtils.saveMessageSuccess(request, "Consentiments actualitzats correctament.");
		return "redirect:" + getContextWeb() + "/list";
	}
	
	@RequestMapping(value = "/recuperarConsentimiento", method = RequestMethod.GET)
	public String recuperarConsentimiento(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Where wLocals = SolicitudFields.ORGANID.isNotNull();
		Where wConsentNoNull = SolicitudFields.FITXERCONSENTIMENTID.isNull();
		List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wLocals, wConsentNoNull));

		int idx = 0;
		int total = solicituds.size();
		int consentimientosRecuperados = 0;
		int docsConsentiment = 0;
		int errors = 0;
		int ambUrl = 0;
		int senseConsentiment = 0;

		Long[] tipusConsentimentArray = new Long[] { Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_NOOP,
				Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI };
		
		for (Solicitud solicitud : solicituds) {
			idx++;
			// Obtener documentos de la solicitud de tipo Consentiment.

			Long soliID = solicitud.getSolicitudID();

			Fitxer fitxerConsentiment = null;

			List<Long> documentsSoli = documentSolicitudEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
					DocumentSolicitudFields.SOLICITUDID.equal(soliID));

			List<Document> documents = documentEjb.select(Where.AND(DocumentFields.DOCUMENTID.in(documentsSoli),
					DocumentFields.TIPUS.in(tipusConsentimentArray)));

			log.info(idx + "/" + total + " - SolicitudID: " + soliID + ". Consentiments: " + documents.size());
			if (documents.size() != 0) {
				docsConsentiment++;
				consentimientosRecuperados++;

				Document consentiment = documents.get(documents.size() - 1); // Agafam l'ultim com a vàlid.)
//				consentiment.getFitxerOriginal();
				consentiment.getFitxerOriginalID();

				fitxerConsentiment = fitxerLogicEjb.findByPrimaryKey(consentiment.getFitxerOriginalID());

			} else {
				// No tenim el consentiment adjunt. Provar amb url.
				String url = solicitud.getUrlconsentiment();

				if (url != null && !url.isEmpty()) {
					log.info("SolicitudID: " + soliID + ". Te URL de consentiment: " + url);
					
					// Validar URL
					if (!isValidURL(url)) {
						errors++;
						log.info("SolicitudID: " + soliID + ". URL de consentiment no vàlida: " + url);
						continue;
					}
					
					
					// Descarregar fitxer de la URL i guardar-lo a fitxerLogicEjb
					try {
						log.info("SolicitudID: " + soliID + ". Recuperant consentiment de URL: " + url);

						Fitxer file = crearFitxerConsentimentFromUR2L(url);

						if (file != null) {

							fitxerConsentiment = file;
							ambUrl++;
							consentimientosRecuperados++;
							log.info("SolicitudID: " + soliID + ". Fitxer consentiment recuperat i guardat. FitxerID: "
									+ file.getFitxerID());
						} else {
							errors++;

							log.info("SolicitudID: " + soliID
									+ ". No s'ha pogut recuperar el fitxer de consentiment de la URL: " + url);
						}

					} catch (Exception ex) {
						errors++;
						log.error("Error descarregant fitxer de consentiment de la URL: " + url, ex);
					}
				} else {
					log.info("SolicitudID: " + soliID + ". No te consentiment ni URL.");
					senseConsentiment++;
				}
			}

			if (fitxerConsentiment == null) {
//				log.info("\t\tNo s'ha pogut recuperar el consentiment per a la SoliID: " + soliID);
				continue;
			}
			assignarConsentimentSoli(solicitud, fitxerConsentiment);
		}

		log.info("Consentimientos recuperados: " + consentimientosRecuperados);
		log.info("Solicitudes con documento de consentiment: " + docsConsentiment);
		log.info("Consentimientos recuperados amb URL: " + ambUrl);
		log.info("Errors recuperant consentimientos: " + errors);
		log.info("Solicitudes sense consentiment ni URL: " + senseConsentiment);

		HtmlUtils.saveMessageSuccess(request, "Consentiments actualitzats correctament.");
		return "redirect:" + getContextWeb() + "/list";
	}

	public boolean isValidURL(String url) {
	    try {
	        new java.net.URL(url).toURI(); // Valida tanto sintaxis como formato
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	public Fitxer crearFitxerConsentimentFromURL(String url) {
		try {
			final boolean debug = false;
			FileInfo fileInfo = PdfDownloader.downloadPDFFromBoeBoibUrl(url, debug);

			String nom = fileInfo.getFileName();
			long tamany = fileInfo.getSize();
			String mime = "application/pdf";
			String descripcio = "Fitxer de consentiment descarregat de URL: " + url;

			Fitxer fitxer = fitxerEjb.create(nom, tamany, mime, descripcio);

			Long fitxerID = fitxer.getFitxerID();

			FileSystemManager.crearFitxer(new ByteArrayInputStream(fileInfo.getContent()), fitxerID);

			return fitxer;
		} catch (Exception e) {
			String errorMsg;
			if (e instanceof I18NException) {
				errorMsg = I18NUtils.getMessage((I18NException) e);
			}else {
				errorMsg = e.getMessage();
			}
			errorMsg = "Error creant fitxer de consentiment des de URL [" + url + "]: " + errorMsg;
			log.warn(errorMsg, e);
			return null;
		}
	}


	private void assignarConsentimentSoli(Solicitud solicitud, Fitxer fitxerConsentiment) {
		
		log.info("\t\tConsentiment a SoliID: " + solicitud.getSolicitudID() + ". FitxerID: "
				+ (fitxerConsentiment != null ? fitxerConsentiment.getFitxerID() : "null"));
		
		//Crear copia del fitxer per a la solicitud, assignar a la solicitud, i actualitzar.
		
		try {
			Long fitxerCopiaID = ferCopiaFitxer(fitxerConsentiment.getFitxerID());

			solicitud.setFitxerConsentimentID(fitxerCopiaID);

			solicitudLogicaEjb.update(solicitud);

			log.info("\t\tFitxer de consentiment assignat correctament a SoliID: " + solicitud.getSolicitudID());
			

		} catch (Exception e) {
			log.error("Error assignant fitxer de consentiment a SoliID: " + solicitud.getSolicitudID(), e);
		}
		
		

	}

	   private Long ferCopiaFitxer(Long fileOriginalID) throws I18NException {
	    	
	    	FitxerJPA fitxerOriginal = fitxerLogicEjb.findByPrimaryKey(fileOriginalID);
	    	File fileOriginal = FileSystemManager.getFile(fileOriginalID);

	    	FitxerJPA fitxerCopia = new FitxerJPA(fitxerOriginal.getNom(), fitxerOriginal.getTamany(), fitxerOriginal.getMime(), fitxerOriginal.getDescripcio());
	    	Fitxer nou = fitxerLogicEjb.create(fitxerCopia);

	    	File fileCopia = FileSystemManager.getFile(nou.getFitxerID());
	    	FileSystemManager.copy(fileOriginal, fileCopia);
	    	
	    	return fitxerCopia.getFitxerID();
	    }
	    
	
	
	public Fitxer crearFitxerConsentimentFromUR2L(String url) {
		
		if (urlsAmbError.contains(url)) {
			log.info("URL amb error conegut: " + url);
			return null;
		}
		
		
		try {
			if (url.contains("www.caib.es")) {
				//Sustituir www por intranet.
				url = url.replace("www.caib.es", "intranet.caib.es");
				
			}
			byte[] data = descargarPdf(url);
				
			if (data == null) {
				urlsAmbError.add(url);
                throw new I18NException("No s'ha pogut descarregar el fitxer de la URL: " + url);
			}
			
			String nom = "consentiment.pdf";
			String mime = "application/pdf";
			String descripcio = "Fitxer de consentiment descarregat de URL: " + url;
			Fitxer fitxer = fitxerEjb.create(nom, data.length, mime, descripcio);

			Long fitxerID = fitxer.getFitxerID();

			FileSystemManager.crearFitxer(new ByteArrayInputStream(data), fitxerID);
			
			return fitxer;
			
		} catch (Exception e) {
			
			urlsAmbError.add(url);
			
			String errorMsg;
			if (e instanceof I18NException) {
				errorMsg = I18NUtils.getMessage((I18NException) e);
			} else {
				errorMsg = e.getMessage();
			}
			errorMsg = "Error creant fitxer de consentiment des de URL [" + url + "]: " + errorMsg;
			log.warn(errorMsg, e);
			return null;
		}
		
		
		
	}

	public byte[] descargarPdf(String urlPdf) throws IOException {
        byte[] bytesPdf = null;
        try {
            URL url = new URL(urlPdf);
            log.info("Descarregant PDF de URL: " + urlPdf);
            URLConnection connection = url.openConnection();
            log.info("Connexió oberta.");
            InputStream in = connection.getInputStream();
            log.info("InputStream obtingut.");
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            log.info("ByteArrayOutputStream creat.");

            int nRead;
            byte[] data = new byte[1024];

            while ((nRead = in.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            
            log.info("Dades llegides del InputStream.");

            buffer.flush();
            bytesPdf = buffer.toByteArray();

            in.close();
            buffer.close();

        } catch (IOException e) {
            // Manejar la excepción, por ejemplo, lanzar un error personalizado o registrar
            System.err.println("Error al descargar el PDF: " + e.getMessage());
            throw e;
		} catch (Exception e) {
			System.err.println("Error inesperado al descargar el PDF: " + e.getMessage());
			throw new IOException("Error inesperado al descargar el PDF", e);
		}
        return bytesPdf;
    }

	
	
	@RequestMapping(value = "/updateSoli", method = RequestMethod.GET)
	public String updateSoli(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Where wLocals = SolicitudFields.ORGANID.isNotNull();
		List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wLocals)); // , wEstatPinbal , wEstatSoli));
		int idx = 0;
		int tipusActualitzats = 0;
		int caducitatsActualitzades = 0;
		
		List<String> updatedTipus = new java.util.ArrayList<>();
		List<String> updatedCaducitats = new java.util.ArrayList<>();
		
		for (Solicitud soli : solicituds) {
			Long fitxerID = soli.getSolicitudXmlID();

			if (fitxerID == null) {
				continue;
			}

			Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
			if (prop == null) {
				continue;
			}

			String tp = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.TIPOPROCEDIMIENTO");
			log.info("Soli: " + soli.getSolicitudID() +  " - TIPOPROCEDIMIENTO: " + tp);

			// tp puede ser un numero, o un texto.
			Long tipusDocCorrecte = null;
			try {
				tipusDocCorrecte = Long.parseLong(tp);

			} catch (NumberFormatException nfe) {
				// No es un numero, es un text.
				tipusDocCorrecte = getTipusDocIDFromText(tp);
			}

			if (tipusDocCorrecte != null) {
				String tipusProc = String.valueOf(tipusDocCorrecte);
				
				String msg = soli.getProcedimentTipus() + " -> " + tipusProc + ". SoliID = " + soli.getSolicitudID();
				updatedTipus.add(msg);
				
//				log.info("Actualitzant tipus. " + soli.getProcedimentTipus() + " -> " + tipusProc + ". SoliID = " + soli.getSolicitudID());
				if (!tipusProc.equals(soli.getProcedimentTipus())) {
					tipusActualitzats++;
					soli.setProcedimentTipus(tipusProc);
//					solicitudLogicaEjb.update(soli);
				}
				
				
//            	soli.setProcedimentTipus(tp);
			}else {
				log.info("No hem trobat tipus de procediment per SoliID = " + soli.getSolicitudID() + ". Valor llegit: " + tp);
			}

			if (soli.getDataCaducitat() == null) {
				String caduca = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CADUCA");
				log.info("CADUCA: " + caduca);

				Timestamp dataCad;
				if (caduca.equals("Caduca")) {
					String dataCaduca = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.FECHACAD");
					log.info("FECHACAD: " + dataCaduca);
					// FECHACAD: 31/10/2024

					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date parsed = sdf.parse(dataCaduca);
					dataCad = new Timestamp(parsed.getTime());

				} else {
					dataCad = null;
				}

				String msgCad = soli.getDataCaducitat() + " -> " + dataCad + ". SoliID = " + soli.getSolicitudID();
				updatedCaducitats.add(msgCad);

				if ((soli.getDataCaducitat() == null && dataCad != null)
						|| (soli.getDataCaducitat() != null && !soli.getDataCaducitat().equals(dataCad))) {
					caducitatsActualitzades++;
					soli.setDataCaducitat(dataCad);
				}
			} else {
				log.info("Caducitat actual: " + soli.getDataCaducitat().toString());
			}
			
			solicitudLogicaEjb.update(soli);
			
//			log.info("Actualitzant caducitat. " + soli.getDataCaducitat() + " -> " + dataCad + ". SoliID = " + soli.getSolicitudID());

//			if (idx == 100) {
//				break;
//			}

			idx++;
		}
		
		log.info("Tipus actualitzats detalls: ");
		for (String s : updatedTipus) {
			log.info(s);
		}
		log.info("Caducitats actualitzades detalls: ");
		for (String s : updatedCaducitats) {
			log.info(s);
		}
		
		
		
		log.info("Tipus actualitzats: " + tipusActualitzats);
		log.info("Caducitats actualitzades: " + caducitatsActualitzades);
		
		HtmlUtils.saveMessageSuccess(request, "Sol·licituds actualitzades correctament.");
		return "redirect:" + getContextWeb() + "/list";
	}
	
	
	// actualizarCaducidad
	@RequestMapping(value = "/actualizarCaducidad", method = RequestMethod.GET)
	public String actualizarCaducidad(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Where wLocals = SolicitudFields.ORGANID.isNotNull();
		Where wNoCaducitat = SolicitudFields.DATACADUCITAT.isNull();
		List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wLocals, wNoCaducitat)); // , wEstatPinbal , wEstatSoli));
		int actualitzades = 0;
		for (Solicitud soli : solicituds) {
			
			//Buscar la caducidad con tramitH.
			//Si no la tenemos, buscarla con los servicios.
			//Si no tiene, es que no caduca.
			
			Where wProcCodi = TramitHProcFields.CODI.equal(soli.getProcedimentCodi());
			
			List<TramitHProc> tramitH = tramitHLogicaEjb.select(wProcCodi);
			if (tramitH.size() == 0) {
//				log.info("No trobat TramitH per a " + soli.getProcedimentCodi());
			} else {
				TramitHProc proc = tramitH.get(0);
				if (proc.getCaducitatdata() != null) {
					soli.setDataCaducitat(proc.getCaducitatdata());
					log.info("[TRAMIT] Actualitzada caducitat " + soli.getSolicitudID() + " a " + proc.getCaducitatdata());
					solicitudLogicaEjb.update(soli);
					actualitzades++;
					continue;
				}
			}

			// Si arribam aqui es que no ho tenim amb tramitH. Cercam amb els serveis.
			List<SolicitudServei> serveis = solicitudServeiEjb.select(SolicitudServeiFields.SOLICITUDID.equal(soli.getSolicitudID()));
			if (serveis.size() == 0) {
//				log.info("No trobat Serveis per a " + soli.getSolicitudID());
			} else {
				Timestamp caducitat = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

				for (SolicitudServei ss : serveis) {
					String dataFi = ss.getFechaCaduca(); // por ejemplo "15/12/2022 11:33:35"

					if (dataFi != null && !dataFi.isEmpty()) {
						try {
							// Si la fecha no tiene hora, se la añadimos
							if (dataFi.length() <= 10) {
								dataFi = dataFi.substring(0, 10) + " 23:59:59";
							}

							Date parsed = sdf.parse(dataFi);
							Timestamp data = new Timestamp(parsed.getTime());

							if (caducitat == null || caducitat.after(data)) {
								caducitat = data;
							}

						} catch (Exception e) {
							log.error("Error convertint data caducitat " + dataFi + " de servei " + ss.getId(), e);
						}
					}

				}
				if (caducitat != null) {
					soli.setDataCaducitat(caducitat);
					log.info("[SERVEI] Actualitzada caducitat " + soli.getSolicitudID() + " a " + caducitat);
					solicitudLogicaEjb.update(soli);
					actualitzades++;
					continue;
				}
			}
			
			// Si arribam aqui es que no te caducitat.
			log.info("[NOCADU] No te caducitat " + soli.getSolicitudID());
			
			

		
		}
		
		

		HtmlUtils.saveMessageSuccess(request, "Sol·licituds actualitzades correctament.");
		return "redirect:" + getContextWeb() + "/list";
	}
	
	
	
	// crearInfoMadrid
	@RequestMapping(value = "/actualizarTitulares", method = RequestMethod.GET)
	public String actualizarTitulares(HttpServletRequest request, HttpServletResponse response) throws Exception {

		actualizarTitulares();
		
		HtmlUtils.saveMessageSuccess(request, "Titulars actualitzats correctament.");
		return "redirect:" + getContextWeb() + "/list";
	}
	
	// crearInfoMadrid
	@RequestMapping(value = "/crearInfoMadrid", method = RequestMethod.GET)
	public String crearInfoMadrid(HttpServletRequest request, HttpServletResponse response) throws Exception {

		version2CrearInfoMad();
		
		HtmlUtils.saveMessageSuccess(request, "Tots els InfoMadrid creats correctament.");
		return "redirect:" + getContextWeb() + "/list";
	}

	
	private void actualizarTitulares() throws Exception{
		
		Where wLocals = SolicitudFields.ORGANID.isNotNull();
		Where wInfoMad= SolicitudFields.INFOMADRIDID.isNotNull();

		List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wLocals, wInfoMad)); // , wEstatPinbal , wEstatSoli));
		
		log.info("Solicituds: " + solicituds.size());
		
		
		for (Solicitud soli : solicituds) {
			InfoMadridJPA infoMad = infoMadridLogicaEjb.findByPrimaryKey(soli.getInfomadridid());
			
			log.info("Solicitud: " + soli.getSolicitudID());
			
			Long fitxerID = soli.getSolicitudXmlID();

    		if (fitxerID == null) {
    			log.info("fitxerID: " + fitxerID);
    			continue;
    		}

    		Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
    		if (prop == null) {
    			log.info("prop: " + prop);
    			continue;
    		}

    		ScspTitular titular =  getTitularFromProperties(prop);
			if (titular == null) {
				continue;
			}

			infoMad.setTitularNif(titular.getDocumentacion());
			infoMad.setTitularNom(titular.getNombre() + "|" + titular.getApellido1() + "|" + titular.getApellido2());
			
			infoMadridLogicaEjb.update(infoMad);
		}
	}
	
	private void version2CrearInfoMad() throws Exception {

		Where wLocals = SolicitudFields.ORGANID.isNotNull();
//		Where wInfoMad = SolicitudFields.INFOMADRIDID.isNull();
		List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wLocals));// ,, wInfoMad));  wEstatPinbal , wEstatSoli));

		log.info("Solicituds: " + solicituds.size());

		for (Solicitud soli : solicituds) {

			log.info("Solicitud: " + soli.getSolicitudID());

			
			// Revisam la darrera Consulta d'aquesta solicitud. 
			// Si te infoMadrid i s'ha consultat fa menys d'una hora, botarse-la. 
			// Sino, indicar que la darreraConsulta es ara.
			Long infoMadId = soli.getInfomadridid();

			if (infoMadId != null) {
			    InfoMadrid infoMad = infoMadridLogicaEjb.findByPrimaryKey(infoMadId);
			    Timestamp dataConsulta = infoMad.getDataConsulta();

			    if (dataConsulta != null) {
			        // Calcular fa una hora
			        Timestamp faTresHora = new Timestamp(System.currentTimeMillis() - 3600 * 1000 * 3);

			        if (dataConsulta.after(faTresHora)) {
			            // S'ha consultat fa menys d'una hora -> botar-se-la
			        	log.info("Solicitud consultada recent");
			            continue;
			        }
			    }

			    // Sino, indicar que la darreraConsulta es ara
			    log.info("Solicitud consultada fa temps. Tornar a consultar.");
			    infoMad.setDataConsulta(new Timestamp(System.currentTimeMillis()));
			    infoMadridLogicaEjb.update(infoMad);
			}

			
			if (soli.getProcedimentCodi().length() > 20) {
				log.info("Procediment Llarg. Descartat");
				continue;
			}

			long reintents = 0;
			int estatAutNou = -1000;

			boolean crear;
			Timestamp dataEnviament;
			String missatge;
			Timestamp dataAuth;

			int estatID = Long.valueOf(soli.getEstatSolicitud()).intValue();

			if (estatID == Constants.SOLI_ESTAT_SENSE_ESTAT || estatID == Constants.SOLI_ESTAT_PENDENT_DISTRIBUCIO
					|| estatID == Constants.SOLI_ESTAT_PENDENT_Enviar_Director
					|| estatID == Constants.SOLI_ESTAT_PENDENT_Firma_Director) {
				crear = false;
				dataEnviament = null;
				dataAuth = null;
				missatge = null;

			} else if (estatID == Constants.SOLI_ESTAT_PENDENT_ENVIAR_MADRID) {
				// Encara no s'han fet modificacions. pendent d'enviar es que no s'ha enviat.
				crear = false;
				dataEnviament = null;
				dataAuth = null;
				missatge = null;

			} else if (estatID == Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_Manual) {
				crear = true;

				// Crear amb dades manuals.
				dataEnviament = null;
				dataAuth = null;
				missatge = "Solicitud Enviada a Madrid manualment.";

			} else if (estatID == Constants.SOLI_ESTAT_PENDENT_AUTORITZAR) {
				crear = true;
				// Cridar CONSULTA per saber si ha estat manual o no.

				Retorno ret = consulta(soli);

				if (ret == null) {
					// Ha sido Manual.
					dataEnviament = null;
					dataAuth = null;
					missatge = "Solicitud Enviada a Madrid manualment.";
				} else {
					dataEnviament = new Timestamp(System.currentTimeMillis());
					missatge = ret.getProcedimiento().getEstadoProcedimiento().getObservaciones();
					estatAutNou = ret.getProcedimiento().getEstadoProcedimiento().getEstado();
					if (estatAutNou == Constants.ESTAT_PINBAL_AUTORITZAT) {
						dataAuth = new Timestamp(System.currentTimeMillis());
					} else {
						dataAuth = null;
					}
				}

			} else if (estatID == Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID) {
				crear = true;

				dataEnviament = null;
				dataAuth = null;
				reintents = 1;
				missatge = null;

			} else if (estatID == Constants.SOLI_ESTAT_AUTORITZAT || estatID == Constants.SOLI_ESTAT_TANCAT) {
				crear = true;

				// Cridar CONSULTA per saber si ha estat manual o no.
				Retorno ret = consulta(soli);

				if (ret == null) {
					// Ha sido Manual.
					dataEnviament = null;
					dataAuth = soli.getDataFi();
					missatge = "Solicitud Enviada a Madrid manualment, i AUTORITZADA.";
				} else {
					dataEnviament = soli.getDataFi();
					dataAuth = soli.getDataFi();
					missatge = ret.getProcedimiento().getEstadoProcedimiento().getObservaciones();
					estatAutNou = ret.getProcedimiento().getEstadoProcedimiento().getEstado();
				}

			} else if (estatID == Constants.SOLI_ESTAT_AUTORITZAT_Manual) {
				crear = true;

				dataEnviament = null;
				dataAuth = soli.getDataFi();
				missatge = "Solicitud Enviada a Madrid manualment, i AUTORITZADA.";
			} else if (estatID == Constants.SOLI_ESTAT_AUTORITZAT_Parcial) {
				crear = true;

				dataEnviament = null;
				dataAuth = soli.getDataFi();
				missatge = "Solicitud Enviada a Madrid manualment, i autoritzada PARCIALMENT.";
			} else if (estatID == Constants.SOLI_ESTAT_ESMENES || estatID == Constants.SOLI_ESTAT_ESMENA_PENDENT_CONTACTE
					|| estatID == Constants.SOLI_ESTAT_ESMENA_PENDENT_CONTACTE
					|| estatID == Constants.SOLI_ESTAT_CANVI_PENDENT_REVISAR) {
				crear = true;

				// En tots els casos de esmenes, cridar consulta per si hi algun missatge, i
				// guardar estat real.
				// Cridar CONSULTA
				Retorno ret = consulta(soli);

				if (ret == null) {
					// Ha sido Manual.
					dataEnviament = null;
					dataAuth = null;
					missatge = "Solicitud Enviada a Madrid manualment. Pendent Esmena";
				} else {
					dataEnviament = null;
					dataAuth = null;
					missatge = ret.getProcedimiento().getEstadoProcedimiento().getObservaciones();
					estatAutNou = ret.getProcedimiento().getEstadoProcedimiento().getEstado();
				}

			} else if (estatID == Constants.SOLI_ESTAT_REVISIO) {
				crear = false;
				dataEnviament = null;
				dataAuth = null;
				missatge = null;
			} else if (estatID == Constants.SOLI_ESTAT_DENEGADA) {
				crear = false;
				dataEnviament = null;
				dataAuth = null;
				missatge = null;
			} else {
				crear = false;
				dataEnviament = null;
				dataAuth = null;
				missatge = null;
			}

			log.info("Missatge: " + missatge);

			log.info("EstatSoli: " + estatID + ". crear=" + crear + ". InfoMadrid: " + soli.getInfomadridid());

			if (crear) {
				String codi = soli.getProcedimentCodi();
				if (soli.getInfomadridid() == null) {

					log.info("Intentarem Crear InfoMad");

					String consultaTexto = "Buenos días,\n"
							+ "Enviamos solicitud para dar servicios de alta en el procedimiento " + codi + "\n\n"
							+ "Quedamos a la espera de su respuesta.\n" + "Un saludo.";

					long estatProc = estatID;

					String titularNom = null;
					String titularNif = null;

					String[] datosTitular = dadesTitular(soli);
					log.info("Datos Titular: " + datosTitular);
					if (datosTitular != null) {

						titularNom = datosTitular[0];
						titularNif = datosTitular[1];
					}

					long estatAut;

					if (estatAutNou == -1000) {
						if (soli.getEstatpinbal() != null) {
							estatAut = soli.getEstatpinbal();
						} else {
							estatAut = Constants.ESTAT_PINBAL_null;
						}
					} else {
						estatAut = (long) estatAutNou;
					}

					Timestamp dataConsulta = new Timestamp(System.currentTimeMillis());

				    
					InfoMadridJPA infoMadJPA = new InfoMadridJPA(codi, estatProc, estatAut, missatge, consultaTexto,
							titularNom, titularNif, dataAuth, dataEnviament, reintents, dataConsulta);
 
					log.info("CREAM InfoMad per solicitud: " + codi);
					InfoMadrid infoMad = infoMadridLogicaEjb.create(infoMadJPA);

					log.info("Creat InfoMad amb id = " + infoMad.getInfoMadridID());
					soli.setInfomadridid(infoMad.getInfoMadridID());
					solicitudLogicaEjb.update(soli);

				} else {
					log.info("Ja tenim InfoMadrid. Asssignam a tots els procediments d'aquest codi. " + codi);
					List<Solicitud> solicitudIguals = solicitudLogicaEjb
							.select(SolicitudFields.PROCEDIMENTCODI.equal(codi));

					if (solicitudIguals.size() > 0) {
						log.info("SOLICITUD REPETIDA " + solicitudIguals.size() + " VEGADES");

//						for (Solicitud solicitud : solicitudIguals) {
//							solicitud.setInfomadridid(soli.getInfomadridid());
//							log.info("UPDATE soli " + solicitud.getSolicitudID());
//							solicitudLogicaEjb.update(solicitud);
//						}

					}

				}
			}
		}
	}
	
	private String[] dadesTitular(Solicitud soli) throws Exception {
		
		ScspTitular titular = getTitular(soli);

		if (titular == null) {
			return null;
		}
		
		String[] datosTitular = new String[2];
		
//		datosTitular[0] = titular.getNombreCompleto();
		datosTitular[0] = titular.getNombre() + "|" + titular.getApellido1() + "|" + titular.getApellido2() == null ? ""
				: titular.getApellido2();
		datosTitular[1] = titular.getDocumentacion();
		
		return datosTitular;
	}
	
	private Retorno consulta(Solicitud soli) {
		final String SOLICITUD_TROBADA = "0";
		final String SOLICITUD_ENVIADA_MANUALMENTE = "2";

		ScspTitular titular = null;

		try {
			titular = getTitular(soli);
		} catch (Exception e) {
			log.error("ERROR OBTENINT TITULAR: " + soli.getSolicitudID() + ". " + e.getMessage());
		}
		if (titular == null) {
			log.info("titular: " + titular);
			return null;
		}

		ScspFuncionario funcionario = getFuncionari();
		if (funcionario == null) {
			log.info("funcionario: " + funcionario);
			return null;
		}

		Consulta consulta = new Consulta();
		consulta.setCodigoProcedimiento(soli.getProcedimentCodi());

		log.info("Cridam métode CONSULTA");

		Retorno retorno;
		try {
			retorno = solicitudLogicaEjb.consultaEstatApiPinbal(titular, funcionario, soli.getSolicitudID());
		} catch (Exception e) {
			log.error("ERROR CRIDANT CONSULTA: " + soli.getSolicitudID() + ". " + e.getMessage());
			return null;
		}

		if (retorno.getEstado().getCodigoEstado().equals(SOLICITUD_TROBADA)) {
			return retorno;

		} else if (retorno.getEstado().getCodigoEstado().equals(SOLICITUD_ENVIADA_MANUALMENTE)) {
			log.info("Solicitud no trobada. Enviada Manual (" + soli.getProcedimentCodi() + ")");
			return null;
		} else {
			return null;
		}

	}
	
	
	
	
	private void version1CrearInfoMad() throws Exception {
		// Actualitzar estat de les sol·licituds
		log.info("crearInfoMadrid:: HOLA");
		final String SOLICITUD_TROBADA = "0";
		final String SOLICITUD_ENVIADA_MANUALMENTE = "2";

		Where wLocals = SolicitudFields.ORGANID.isNotNull();
		Where wEstatPinbal = Where.AND(SolicitudFields.ESTATPINBAL.isNotNull(),
				SolicitudFields.ESTATPINBAL.notEqual(Constants.ESTAT_PINBAL_NO_SOLICITAT));

//			Long[] estatsSoliOk =  {Constants.soli_estat_};
//			Where wEstatSoli = SolicitudFields.ESTATSOLICITUD.in(estatsSoliOk );
//			Where wEstatSoli = SolicitudFields.ESTATSOLICITUD.notEqual(Constants.SOLI_ESTAT_REVISIO);

		List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wLocals)); // , wEstatPinbal , wEstatSoli));

		log.info(solicituds.size() + " solicituds");

		int solis = 0;
		for (Solicitud soli : solicituds) {

			log.info("Volem procesar la solicitud " + soli.getProcedimentCodi() + " [" + soli.getSolicitudID() + "]");

			if (soli.getEstatpinbal() == Constants.ESTAT_PINBAL_MANUAL) {
				log.info("Fichada Manual (" + soli.getProcedimentCodi() + ")");
				continue;
			}

			if (soli.getEstatpinbal() == Constants.ESTAT_PINBAL_null) {
				log.info("Fichada Null (" + soli.getProcedimentCodi() + ")");
				continue;
			}

			if (soli.getProcedimentCodi().length() > 20) {
				log.info("Procediment Llarg. Descartat");
				soli.setEstatpinbal(Constants.ESTAT_PINBAL_null);
				solicitudLogicaEjb.update(soli);
				continue;
			}

			Long solisProc = infoMadridLogicaEjb.count(InfoMadridFields.CODI.equal(soli.getProcedimentCodi()));
			if (solisProc > 0) {
				log.info("Solicitud ja te un InfoMad");
				continue;
			}

			// Si llega aquí la vamos a procesar.

			Long fitxerID = soli.getSolicitudXmlID();

			if (fitxerID == null) {
				log.info("fitxerID: " + fitxerID);
				soli.setEstatpinbal(Constants.ESTAT_PINBAL_null);
				solicitudLogicaEjb.update(soli);
				continue;
			}

			Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
			if (prop == null) {
				log.info("prop: " + prop);
				soli.setEstatpinbal(Constants.ESTAT_PINBAL_null);
				solicitudLogicaEjb.update(soli);
				continue;
			}

			ScspTitular titular = getTitular(soli);
			if (titular == null) {
				log.info("titular: " + titular);
				soli.setEstatpinbal(Constants.ESTAT_PINBAL_null);
				solicitudLogicaEjb.update(soli);
				continue;
			}
			ScspFuncionario funcionario = getFuncionari();
			if (funcionario == null) {
				log.info("funcionario: " + funcionario);
				soli.setEstatpinbal(Constants.ESTAT_PINBAL_null);
				solicitudLogicaEjb.update(soli);
				continue;
			}

			Consulta consulta = new Consulta();
			consulta.setCodigoProcedimiento(soli.getProcedimentCodi());

			log.info("Cridam métode CONSULTA");

			Retorno retorno = solicitudLogicaEjb.consultaEstatApiPinbal(titular, funcionario, soli.getSolicitudID());
			if (retorno.getEstado().getCodigoEstado().equals(SOLICITUD_ENVIADA_MANUALMENTE)) {
				log.info("Solicitud no trobada. Enviada Manual (" + soli.getProcedimentCodi() + ")");
				continue;
			} else if (retorno.getEstado().getCodigoEstado().equals(SOLICITUD_TROBADA)) {
				log.info("Solicitud Trobada. S'hauria d'haver creat InfoMad");
			}

//				String codi = soli.getProcedimentCodi();
//				long estatProc = soli.getEstatSolicitud();
			//
//				EstadoProcedimiento estadoProc = retorno.getProcedimiento().getEstadoProcedimiento();
//				
//				int estatAut = estadoProc.getEstado();
//				String missatge = estadoProc.getObservaciones();
//				
//				String consultaTexto = "Buenos días,\n"
//						+ "Enviamos solicitud para dar servicios de alta en el procedimiento "
//						+ codi + "\n\n" + "Quedamos a la espera de su respuesta.\n"
//						+ "Un saludo.";
//				
//				String titularNom = titular.getNombreCompleto();
//				String titularNif = titular.getDocumentacion();
			//
//				Timestamp now = new Timestamp(System.currentTimeMillis());
//				
//				Timestamp dataAuth = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_AUTORITZAT ? now : null;
//				Timestamp dataEnviament = now;
//				
//				long reintents = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR ? 1 : 0;
//				
//				InfoMadridJPA infoMadJPA = new InfoMadridJPA(codi, estatProc, estatAut, missatge, consultaTexto, titularNom,
//						titularNif, dataAuth, dataEnviament, reintents);
			//
//				log.info("Crearem InfoMad");
//				
//				InfoMadrid infoMad =  infoMadridLogicaEjb.create(infoMadJPA);
//				log.info("InfoMad creado: " + infoMad.getInfoMadridID());
			//
//				soli.setInfomadridid(infoMad.getInfoMadridID());
//				solicitudLogicaEjb.update(soli);
//				
			solis++;

			log.info("Final");
//				if (solis == 5) {
//					break;
		}
	}
	
    private ScspTitular getTitular(Solicitud soli) throws Exception {

    	Long infoMadridID = soli.getInfomadridid();
    	
    	if (infoMadridID == null) {
    		Long fitxerID = soli.getSolicitudXmlID();

    		if (fitxerID == null) {
    			log.info("fitxerID: " + fitxerID);
    			return null;
    		}

    		Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
    		if (prop == null) {
    			log.info("prop: " + prop);
    			return null;
    		}

            return getTitularFromProperties(prop);
		}
    	
    	InfoMadridJPA infoMad = infoMadridLogicaEjb.findByPrimaryKey(infoMadridID);
    	
    	ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.NIF;
    	
    	String documentacion = infoMad.getTitularNif();
    	String titularNom = infoMad.getTitularNom();
    	
    	if (documentacion == null || titularNom == null) {
			return null;
		}
    	

    	String[] fullName = titularNom.split("|");
    	
        ScspTitular titular = new ScspTitular();

        String nombre = fullName[0];
        String ape1 = fullName[1];
        String ape2 = fullName[2];
        
        if (ape2 == null) {
			ape2 = "---";
		}
        
        String nombreCompleto = toFullName(nombre, ape1, ape2);

        titular.setTipoDocumentacion(tipoDocumentacion);
        titular.setDocumentacion(documentacion);
        titular.setNombre(nombre);
        titular.setApellido1(ape1);
        titular.setApellido2(ape2);
        titular.setNombreCompleto(nombreCompleto);

        return titular;
    }
    
	  private ScspTitular getTitularFromProperties(Properties prop) {

	        ScspTitular titular = new ScspTitular();

	        ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.NIF;
	        String documentacion = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NIFSECE");
	        String nombre = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBRESECE");
	        String ape1 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE1SECE");
	        String ape2 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE2SECE");
	        
	        if (ape2 == null) {
				ape2 = "---";
			}
	        
	        String fullName = toFullName(nombre, ape1, ape2);

	        titular.setTipoDocumentacion(tipoDocumentacion);
	        titular.setDocumentacion(documentacion);
	        titular.setNombre(nombre);
	        titular.setApellido1(ape1);
	        titular.setApellido2(ape2);
	        titular.setNombreCompleto(fullName);

	        return titular;
	    }

	    private ScspFuncionario getFuncionari() {

	        ScspFuncionario funcionario = new ScspFuncionario();

//	        UserInfo ui = LoginInfo.getInstance().getUserInfo();

			String nif = null;
			String fullName = null;
	        
//	        if (ui != null) {
//	            nif = ui.getAdministrationID();
//	            fullName = ui.getFullName();
//	        	if (fullName == null) {
//					fullName = ui.getName() + " " + ui.getSurname1() + " " + ui.getSurname2();
//				}
//	        }else {
//	        	String username = LoginInfo.getInstance().getUsername();
//	        	
//	        	switch (username) {
//	        	case "ptrias":
//	        		nif = "45186147W";
//	        		fullName = "Juan Pablo Trias";
//	        		break;
//	        	case "pvico":
//	        		nif = "43084402C";
//	        		fullName = "Pilar Vico Hervas";
//	        		break;
//	        	case "atrobat":
//	        		nif = "43120476F";
//	        		fullName = "Toni Trobat Obrador";
//	        		break;
//				default:
//					nif = "00000000T";
//					fullName = "Usuari Anonim 00000000T";
//	        	}
//	        	
//	        }
	        
	    	String username = LoginInfo.getInstance().getUsername();
	    	switch (username) {
	    	case "ptrias":
	    		nif = "45186147W";
	    		fullName = "Juan Pablo Trias";
	    		break;
	    	case "pvico":
	    		nif = "43084402C";
	    		fullName = "Pilar Vico Hervas";
	    		break;
	    	case "atrobat":
	    		nif = "43120476F";
	    		fullName = "Toni Trobat Obrador";
	    		break;
			default:
				nif = "00000000T";
				fullName = "Usuari Anonim 00000000T";
	    	}

	        
	        log.info("NIF: " + nif);
	        log.info("Nombre completo: " + fullName);

	        funcionario.setNifFuncionario(nif);
	        funcionario.setNombreCompletoFuncionario(fullName);
	        return funcionario;
	    }
	    
	    private String toFullName(String nom, String l1, String l2) {
	        String fullName = nom + " " + l1 + (l2 == "" ? "" : " " + l2);
	        return fullName;
	    }

	    
	    
//	    @RequestMapping(value = "/consultaMadrid", method = RequestMethod.GET)
//		public String consultaMadrid(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//			// Actualitzar estat de les sol·licituds
//			log.info("crearInfoMadrid:: HOLA");
//			final String SOLICITUD_TROBADA = "0";
//			final String SOLICITUD_ENVIADA_MANUALMENTE = "2";
//			
//			Where wLocals = SolicitudFields.ORGANID.isNotNull();
//			Where wEstatPinbal = Where.AND(SolicitudFields.ESTATPINBAL.isNotNull(),
//					SolicitudFields.ESTATPINBAL.notEqual(Constants.ESTAT_PINBAL_NO_SOLICITAT));
//			
//			
////			Long[] estatsSoliOk =  {Constants.soli_estat_};
////			Where wEstatSoli = SolicitudFields.ESTATSOLICITUD.in(estatsSoliOk );
//			Where wEstatSoli = SolicitudFields.ESTATSOLICITUD.notEqual(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_Manual);
//			
//			
//			List<Solicitud> solicituds = solicitudLogicaEjb.select(Where.AND(wLocals, wEstatPinbal, wEstatSoli));
//
//			log.info(solicituds.size() + " solicituds");
//			
//			
//			int solis = 0;
//			for (Solicitud soli : solicituds) {
//				
//				Long solisProc = infoMadridLogicaEjb.count(InfoMadridFields.CODI.equal(soli.getProcedimentCodi()));
//				if (solisProc > 0) {
//					log.info("Solicitud ja te un InfoMad");
//					continue;
//				}
//				
//				
//				Long fitxerID = soli.getSolicitudXmlID();
//				Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
//
//				ScspTitular titular = getTitular(prop);
//				ScspFuncionario funcionario = getFuncionari();
//
//				Consulta consulta = new Consulta();
//				consulta.setCodigoProcedimiento(soli.getProcedimentCodi());
//
//				Retorno retorno = solicitudLogicaEjb.consultaEstatApiPinbal(titular, funcionario, soli.getSolicitudID());
//				if (retorno.getEstado().getCodigoEstado().equals(SOLICITUD_ENVIADA_MANUALMENTE)) {
//					log.info("Solicitud no trobada. Enviada Manual (" + soli.getProcedimentCodi() + ")");
//					continue;
//				}
//				
//				String codi = soli.getProcedimentCodi();
//				long estatProc = soli.getEstatSolicitud();
//
//				EstadoProcedimiento estadoProc = retorno.getProcedimiento().getEstadoProcedimiento();
//				
//				int estatAut = estadoProc.getEstado();
//				String missatge = estadoProc.getObservaciones();
//				
//				String consultaTexto = "Buenos días,\n"
//						+ "Enviamos solicitud para dar servicios de alta en el procedimiento "
//						+ codi + "\n\n" + "Quedamos a la espera de su respuesta.\n"
//						+ "Un saludo.";
//				
//				String titularNom = titular.getNombreCompleto();
//				String titularNif = titular.getDocumentacion();
//
//				Timestamp now = new Timestamp(System.currentTimeMillis());
//				
//				Timestamp dataAuth = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_AUTORITZAT ? now : null;
//				Timestamp dataEnviament = now;
//				
//				long reintents = soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR ? 1 : 0;
//				
//				InfoMadridJPA infoMadJPA = new InfoMadridJPA(codi, estatProc, estatAut, missatge, consultaTexto, titularNom,
//						titularNif, dataAuth, dataEnviament, reintents);
//
//				log.info("Crearem InfoMad");
//				
//				InfoMadrid infoMad =  infoMadridLogicaEjb.create(infoMadJPA);
//				log.info("InfoMad creado: " + infoMad.getInfoMadridID());
//
//				soli.setInfomadridid(infoMad.getInfoMadridID());
//				solicitudLogicaEjb.update(soli);
//				
//				solis++;
//				
//				log.info("Final");
////				if (solis == 5) {
////					break;
////				}
//			}
//			
//			HtmlUtils.saveMessageSuccess(request, "Estat de les sol·licituds actualitzat correctament.");
//			return "redirect:" + getContextWeb() + "/list";
//
//		}
	    
	    
////  /actualitzarEstats
//	@RequestMapping(value = "/actualitzarEstats", method = RequestMethod.GET)
//	public String actualitzarEstats(HttpServletRequest request, HttpServletResponse response) throws I18NException {
//
//		// Actualitzar estat de les sol·licituds
//		log.info("Actualizaremos el estado de las solicitudes pendientes");
//
//		long anticEstatPendent = 10;
//
//		Long[] estatsAProcesar = {Constants.SOLICITUD_ESTAT_PENDENT_DISTRIBUCIO, Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Director,
//				Constants.SOLICITUD_ESTAT_PENDENT_ENVIAR_MADRID, Constants.SOLICITUD_ESTAT_PENDENT_Firma_Director,
//				Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR, Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Cedents,
//				Constants.SOLICITUD_ESTAT_PENDENT_Firma_Cedent, anticEstatPendent };
//
//		List<Solicitud> list = solicitudLogicaEjb.select(SolicitudFields.ESTATID.in(estatsAProcesar));
//
//		log.info("Sol·licituds a actualitzar: " + list.size());
//		int updates = 0;
//		for (Solicitud soli : list) {
//			Long nouEstat;
//			Long soliID = soli.getSolicitudID();
//			if (soli.getOrganid() != null) {
//				// Si es local, veure si está pendent d'enviar a DG, pendent de DG, pendent
//				// d'enviar a Madrid, o pendent d'autoritzar
//
//				if (isFirmatPelDirector(soli.getSolicitudID())) {
//					// Vuere si ja s'ha enviat a Madrid. Utilitzar l'estat Pinbal
//					Integer estatPinbal = soli.getEstatpinbal();
//					if (estatPinbal == null) {
//						soli.setEstatpinbal(Constants.ESTAT_PINBAL_NO_SOLICITAT);
//					}
//
//					if (soli.getEstatpinbal() == Constants.ESTAT_PINBAL_NO_SOLICITAT) {
//						log.info("LOCAL - SoliID :" + soliID + " firmada director i no enviada a Madrid");
//						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_ENVIAR_MADRID;
//					} else if (soli.getEstatpinbal() == Constants.ESTAT_PINBAL_ERROR) {
//						log.info("LOCAL - SoliID :" + soliID + " enviada a Madrid amb ERROR");
//						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR;
//					} else {
//						log.info("LOCAL - SoliID :" + soliID + " firmada director i enviada a Madrid");
//						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR;
//					}
//				} else {
//					// No te el document firmat. Comprovar si s'ha enviat o no.
//					if (isEnviatAFirmar(soliID)) {
//						log.info("LOCAL - SoliID :" + soliID + " no firmada director, pero enviada a firmar");
//						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Firma_Director;
//					} else {
//						log.info("LOCAL - SoliID :" + soliID + " no enviada a firmar al director");
//						nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Director;
//					}
//				}
//
//			} else {
//				// Solicituts estatals
//				// S'ha de veure si s'han enviat correus de consulta a cedents. Si no n'hi ha,
//				// pendent d'enviar a cedents. Si n'hi ha, pendent firma_cedents. Si hi ha
//				// tantes consultes a cedents com respostes, penent autoritzar
//
//				List<Event> eventsSoliEstatal = eventLogicaEjb.select(EventFields.SOLICITUDID.equal(soliID));
//				int numConsultes = 0;
//				int numRespostes = 0;
//
//				for (Event event : eventsSoliEstatal) {
//					if (event.getTipus() == Constants.EVENT_TIPUS_CONSULTA_A_CEDENT) {
//						numConsultes++;
//					} else if (event.getTipus() == Constants.EVENT_TIPUS_CEDENT_RESPOSTA) {
//						numRespostes++;
//					}
//				}
//
//				if (numConsultes == 0) {
//					log.info("ESTATAL - SoliID :" + soliID + " no hi ha consultes a cedents");
//					nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Cedents;
//				} else if (numConsultes == numRespostes) {
//					log.info("ESTATAL - SoliID :" + soliID + " Totes les consultes a cedents respostes (" + numRespostes
//							+ "/" + numConsultes + ")");
//					nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR;
//				} else {
//					log.info("ESTATAL - SoliID :" + soliID + " Consultes a cedents pendents de resposta ("
//							+ numRespostes + "/" + numConsultes + ")");
//					nouEstat = Constants.SOLICITUD_ESTAT_PENDENT_Firma_Cedent;
//				}
//			}
//
//			if (nouEstat != soli.getEstatID()) {
//				updates ++;
//                soli.setEstatID(nouEstat);
//                solicitudLogicaEjb.update(soli);
//			}
//		}
//
//		HtmlUtils.saveMessageSuccess(request, "Estat de les " + updates + " sol·licituds actualitzat correctament.");
//		return "redirect:" + getContextWeb() + "/list";
//
//	}

	@EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService documentSolicitudEjb;

	@EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.ejb.DocumentService documentEjb;

	public boolean isFirmatPelDirector(Long soliID) throws I18NException {

		List<Long> listDocumentsSolicitud = documentSolicitudEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(soliID));

		List<Document> documentsPDF = documentEjb.select(Where.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud),
				DocumentFields.TIPUS.equal(Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF)));

		for (Document document : documentsPDF) {
			if (document.getFitxerFirmatID() != null) {
				return true;
			}
		}

		return false;
	}

	public boolean isEnviatAFirmar(Long soliID) throws I18NException {

		List<Long> listDocumentsSolicitud = documentSolicitudEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(soliID));

		List<Document> documentsPDF = documentEjb.select(Where.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud),
				DocumentFields.TIPUS.equal(Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF)));

		for (Document document : documentsPDF) {
			if (document.getNotes() != null && document.getNotes().trim().length() > 0) {
				return true;
			}
		}

		return false;
	}
	
	
	private String getTipusDocFromID(String id) {
		log.info("getTipusDocFromID: " + id);
		
		if (id == null) {
			return null;
		}
		
		String lang = "ca";        
        List<TipusProcediment> tipus = TipusProcediments.getAllTipusProcediments();
		for (TipusProcediment tp : tipus) {
			if (tp.id == Long.valueOf(id)) {
				String text;
				if (lang.equals("es")) {
					text = tp.castella;
				} else {
					text = tp.catala;
				}
				return text;
			}
		}
        return null;
        
	}
	
	private Long getTipusDocIDFromText(String text) {
		log.info("getTipusDocIDFromText: " + text);

		if (text == null) {
			return null;
		}

		List<TipusProcediment> tipus = TipusProcediments.getAllTipusProcediments();
		for (TipusProcediment tp : tipus) {
			
			if (tp.catala.equals(text) || tp.castella.equals(text)) {
				return tp.id;
			}
		}
		return null;

	}
	
}
