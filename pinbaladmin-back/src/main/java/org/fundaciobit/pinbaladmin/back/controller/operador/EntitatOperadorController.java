package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.EntitatController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatForm;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.ejb.DocumentEntitatService;
import org.fundaciobit.pinbaladmin.logic.EntitatLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.pinbaladmin.model.fields.DocumentEntitatFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudQueryPath;
import org.fundaciobit.pinbaladmin.persistence.EntitatJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

import es.caib.pinbal.client.comu.LogLevel;
import es.caib.pinbal.client.procediments.ClaseTramite;
import es.caib.pinbal.client.procediments.Procediment;
import es.caib.pinbal.client.procediments.ProcedimentClient;
import es.caib.pinbal.client.recobriment.v2.ClientRecobriment;
import es.caib.pinbal.client.serveis.ServeiBasic;
import es.caib.pinbal.client.usuaris.UsuariClient;
import es.caib.pinbal.client.usuaris.UsuariEntitat;

import org.fundaciobit.genapp.common.web.HtmlUtils;

@Controller
@RequestMapping(value = "/operador/entitat")
@SessionAttributes(types = { EntitatForm.class, EntitatFilterForm.class })
public class EntitatOperadorController extends EntitatController {

	@EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;

	@EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentEntitatService.JNDI_NAME)
	protected DocumentEntitatService documentEntitatEjb;

	@EJB(mappedName = EntitatLogicaService.JNDI_NAME)
	protected EntitatLogicaService entitatLogicaEjb;

	private ClientRecobriment clientRecobriment;
	private ProcedimentClient procedimentClient;
	private UsuariClient usuariClient;

	final static String CODI_CONSULTA = "SVDPIDESTADOAUTWS01";
	final static String CODI_ALTA = "SVDPIDSOLAUTWS01";
	final static String CODI_MODIFICACIO = "SVDPIDACTPROCWS01";

	private ClientRecobriment getClientRecobriment() {
		if (clientRecobriment == null) {
			final String baseUrl = Configuracio.getApiPinbalClientUrl();
			final String username = Configuracio.getApiPinbalClientUsername();
			final String password = Configuracio.getApiPinbalClientPassword();
			final LogLevel logLevel = LogLevel.INFO;
			log.info("Inicialitzant ClientRecobriment. URL: " + baseUrl + ", User: " + username);
			clientRecobriment = new ClientRecobriment(baseUrl, username, password, logLevel);
		}
		return clientRecobriment;
	}

	private ProcedimentClient getProcedimentClient() {
		if (procedimentClient == null) {
			final String baseUrl = Configuracio.getApiPinbalClientUrl();
			final String username = Configuracio.getApiPinbalClientUsername();
			final String password = Configuracio.getApiPinbalClientPassword();
			final LogLevel logLevel = LogLevel.INFO;
			log.info("Inicialitzant ProcedimentClient. URL: " + baseUrl + ", User: " + username);
			procedimentClient = new ProcedimentClient(baseUrl, username, password, logLevel);
		}
		return procedimentClient;
	}
	
	private UsuariClient getUsuariClient() {
		if (usuariClient == null) {
			final String baseUrl = Configuracio.getApiPinbalClientUrl();
			final String username = Configuracio.getApiPinbalClientUsername();
			final String password = Configuracio.getApiPinbalClientPassword();
			final LogLevel logLevel = LogLevel.INFO;
			log.info("Inicialitzant usuariClient . URL: " + baseUrl + ", User: " + username);
			usuariClient = new UsuariClient(baseUrl, username, password, logLevel);
		}
		return usuariClient;
	}
	

	// public static final int SOLICITUDS = 1;
	public static final int DOCS = 1;
//	public static final int PREALTAS_OK = 2;
	
	public static final String PREALTAS = "PREALTAS";

	@Override
	public String getTileForm() {
		return "entitatFormWebDB_operador";
	}

	@Override
	public String getTileList() {
		return "entitatListWebDB_operador";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "EntitatWebDB_FilterForm_operador";
	}

	@Override
	public EntitatFilterForm getEntitatFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {

		EntitatFilterForm entitatFilterForm = super.getEntitatFilterForm(pagina, mav, request);

		/*
		 * AdditionalField<Long,String> adfield4 = new AdditionalField<Long,String>();
		 * adfield4.setCodeName("solicitud.solicitud.plural");
		 * adfield4.setPosition(SOLICITUDS); // Els valors s'ompliran al mètode
		 * postList() adfield4.setValueMap(new HashMap<Long, String>());
		 * adfield4.setEscapeXml(false);
		 * 
		 * entitatFilterForm.addAdditionalField(adfield4);
		 */

		if (entitatFilterForm.isNou()) {

			entitatFilterForm.setVisibleMultipleSelection(false);

			entitatFilterForm
					.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_LIST, "solicitud.llistat",
							"javascript:$('#modal_infosoli_{0}').modal('show');", AdditionalButtonStyle.INFO));

			AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
			adfield4.setCodeName("=Docs.");
			adfield4.setPosition(DOCS);
			// Els valors s'ompliran al mètode postList()
			adfield4.setValueMap(new HashMap<Long, String>());
			adfield4.setEscapeXml(false);
			entitatFilterForm.addAdditionalField(adfield4);

//			AdditionalField<Long, String> adfieldPreAltasOk = new AdditionalField<Long, String>();
//			adfieldPreAltasOk.setCodeName("=PREALTAS OK.");
//			adfieldPreAltasOk.setPosition(PREALTAS_OK);
//			// Els valors s'ompliran al mètode postList()
//			adfieldPreAltasOk.setValueMap(new HashMap<Long, String>());
//			adfieldPreAltasOk.setEscapeXml(false);
//			entitatFilterForm.addAdditionalField(adfieldPreAltasOk);
			
			// Boto per afegir, editar o modificar documents de l'entitat
			entitatFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_FILE,
					"documentsentitatlocal", "/operador/entitat/documents/{0}", AdditionalButtonStyle.SUCCESS));

			// entitatFilterForm.addAdditionalButtonForEachItem(new
			// AdditionalButton(IconUtils.ICON_CHECK,
			// "crearEntitatPinbal", getContextWeb() + "/crearEntitatPinbal/{0}",
			// AdditionalButtonStyle.SUCCESS));

			entitatFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_OK_CIRCLE,
					"crearPreAltasEntitat", getContextWeb() + "/crearPreAltasEntitat/{0}", AdditionalButtonStyle.INFO));

			// Crear procediment a totes les entitats.
			entitatFilterForm
					.addAdditionalButton(new AdditionalButton(IconUtils.ICON_OK_CIRCLE, "crearPreAltasEntitat.all",
							getContextWeb() + "/crearPreAltasEntitatAll", AdditionalButtonStyle.INFO));

			entitatFilterForm.setAttachedAdditionalJspCode(true);
		}

		return entitatFilterForm;
	}

	HashMap<Long, List<ServeiBasic>> mapEntitatsPinbal = new HashMap<Long, List<ServeiBasic>>();
	

	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, EntitatFilterForm filterForm, List<Entitat> list)
			throws I18NException {

		/*
		 * Map<Long, String> map; map = (Map<Long,
		 * String>)filterForm.getAdditionalField(SOLICITUDS).getValueMap(); map.clear();
		 */
		Long key;
		// String value;

		// String llistat = I18NUtils.tradueix("solicitud.llistat");
		Map<Long, String> divSolicituds = new HashMap<Long, String>();

		Map<Long, String> map;
		map = (Map<Long, String>) filterForm.getAdditionalField(DOCS).getValueMap();
		map.clear();

//		Map<Long, String> mapPreAltas;
//		mapPreAltas = (Map<Long, String>) filterForm.getAdditionalField(PREALTAS_OK).getValueMap();
//		mapPreAltas.clear();
//		
//		clientRecobriment = getClientRecobriment();
//		List<es.caib.pinbal.client.recobriment.v2.Entitat> entitatsPinbal = null;
//		
//		try {
//			log.info("Recuperant entitats de Pinbal per comprovar PREALTAS...");
//			entitatsPinbal = clientRecobriment.getEntitats();
//			log.info("Entitats de Pinbal recuperades: " + (entitatsPinbal != null ? entitatsPinbal.size() : "null"));
//		} catch (Exception e) {
//			log.error("Error recuperant entitats de Pinbal per comprovar PREALTAS: " + e.getMessage());
//		}
//
//		if (entitatsPinbal != null) {
//			if (entitatsPinbal.size() == mapEntitatsPinbal.size()) {
//				log.info("Les entitats de Pinbal ja estaven carregades prèviament. No es tornen a carregar.");
//			} else {
//				for (Entitat entitat : list) {
//					String codiPinbal = entitat.getCodiPinbal();
//					for (es.caib.pinbal.client.recobriment.v2.Entitat entitatPinbal : entitatsPinbal) {
//						if (entitatPinbal.getCodi().equals(codiPinbal)) {
//							//Entitat trobada a Pinbal. Recuperam els serveis.
//							try {
//								List<ServeiBasic> serveisEntitat = clientRecobriment.getServeisPerEntitat(codiPinbal);
//								mapEntitatsPinbal.put(entitat.getEntitatID(), serveisEntitat);
//
//								log.info("Serveis de l'entitat  " + codiPinbal + ": "
//										+ (serveisEntitat != null ? serveisEntitat.size() : "null"));
//							} catch (Exception e) {
//								log.error("Error recuperant serveis de l'entitat Pinbal " + codiPinbal + ": "
//										+ e.getMessage());
//							}
//							break;
//						}
//					}
//				}
//			}
//		}
//		
//		for (Entitat entitat : list) {
//			
//			boolean usuariCreat = false; //Si está a la llista d'entitats de pinbal, l'usuari està creat.
//			boolean serveisActius = false; //Si tots els serveis estan actius. Es podrá crear PRE-ALTAS.
//			boolean procCreat = false; // Si el procediment PREALTAS ja està creat a l'entitat
//			
//			List<ServeiBasic> serveisEntitat = mapEntitatsPinbal.get(entitat.getEntitatID());
//			if (serveisEntitat != null) {
//				usuariCreat = true;
//				int actius = 0;
//				for (ServeiBasic servei : serveisEntitat) {
//					String codi = servei.getCodi();
//					if (codi.equals(CODI_CONSULTA) || codi.equals(CODI_ALTA) || codi.equals(CODI_MODIFICACIO)) {
//						if (servei.getActiu()) {
//							actius++;
//						} else {
//							log.warn("Servei NO actiu a l'entitat Pinbal: " + codi);
//						}
//					}
//				}
//
//				if (actius == 3) {
//					serveisActius = true;
//					
//					//Comprovam si el procediment PREALTAS ja està creat.
//					ProcedimentClient procedimentClient = getProcedimentClient();
//					try {
//						log.info("Comprovant si el procediment " + PREALTAS + " ja està creat a l'entitat: "
//								+ entitat.getNom());
//						Procediment existing = procedimentClient.getProcediment(PREALTAS, entitat.getCodiPinbal());
//						if (existing != null) {
//							procCreat = true;
//							log.info("El procediment " + PREALTAS + " ja existia per a l'entitat: " + entitat.getNom()
//									+ ". ID: " + existing.getId());
//						}
//					} catch (Exception e) {
//						log.info("No s'ha trobat procediment " + PREALTAS + " existent (o error al comprovar): ");
//					}
//				}
//				
//			}
//			
//			
//			String strPreAltas = "";
//			
//			if (usuariCreat) {
//				strPreAltas = "Usuari Creat";
//
//				if (serveisActius) {
//					strPreAltas += "<br> (Ok Serveis)";
//					
//					if (procCreat) {
//						strPreAltas += "<br> (Proc. Creat)";
//					} else {
////						strPreAltas += "<br> (Ok Serveis)";
//					}
//				} else {
//					strPreAltas += "<br> (Serveis NO Actius)";
//				}
//			} else {
//				strPreAltas = "No";
//			}
//			
//			mapPreAltas.put(entitat.getEntitatID(), strPreAltas);
//		}
//		
		
//		for (Entitat entitat : list) {
//
//			// PREALTAS
//
//			String codiPinbal = null;
//			if (entitat instanceof EntitatJPA) {
//				codiPinbal = ((EntitatJPA) entitat).getCodiPinbal();
//			}
//			boolean preAltasOk = false;
//			boolean serveisActius = false;
//			if (entitatsPinbal != null && codiPinbal != null) {
//				for (es.caib.pinbal.client.recobriment.v2.Entitat entitatPinbal : entitatsPinbal) {
//					if (codiPinbal.equals(entitatPinbal.getCodi())) {
//						preAltasOk = true;
//						
//						// Comprovam que els serveis estiguin actius
//						List<ServeiBasic> serveisEntitat = null;
//						try {
//							serveisEntitat = clientRecobriment.getServeisPerEntitat(codiPinbal);
//							mapEntitatsPinbal.put(entitat.getEntitatID(), serveisEntitat);
//							
//							log.info("Serveis de l'entitat  " + codiPinbal + ": "
//									+ (serveisEntitat != null ? serveisEntitat.size() : "null"));
//						} catch (Exception e) {
//							log.error("Error recuperant serveis de l'entitat Pinbal " + codiPinbal + ": "
//									+ e.getMessage());
//						}
//						
//						int actius = 0;
//						if (serveisEntitat != null) {
//							for (ServeiBasic servei : serveisEntitat) {
//								String codi = servei.getCodi();
//								if (codi.equals(CODI_CONSULTA) || codi.equals(CODI_ALTA)
//										|| codi.equals(CODI_MODIFICACIO)) {
//									if (servei.getActiu()) {
//										actius++;
//									} else {
//										log.warn("Servei NO actiu a l'entitat Pinbal: " + codi);
//									}
//								}
//							}
//						}
//						
//						if (actius == 3) {
//							serveisActius = true;
//						}
//						
//						break;
//					}
//				}
//			}
//			
//			String strPreAltas = "";
//			
//			if (preAltasOk) {
//				strPreAltas = "Si";
//				
//				if (serveisActius) {
//					strPreAltas += " (Ok Serveis)";
//				} else {
//					strPreAltas += " (Serveis NO Actius)";
//				}
//			} else {
//				strPreAltas = "No";
//			}
//			
//			mapPreAltas.put(entitat.getEntitatID(), strPreAltas);
//		}
//		
		

		
		for (Entitat entitat : list) {

			// DOCUMENTS

			long count = documentEntitatEjb.count(DocumentEntitatFields.ENTITATID.equal(entitat.getEntitatID()));

			if (count == 0) {
				map.put(entitat.getEntitatID(), "");
			} else {
				map.put(entitat.getEntitatID(), String.valueOf(count));
			}

			// SOLICITUDS

			SelectMultipleStringKeyValue smskv;
			smskv = new SelectMultipleStringKeyValue(SolicitudFields.SOLICITUDID.select, "*",
					SolicitudFields.PROCEDIMENTCODI.select, SolicitudFields.PROCEDIMENTNOM.select);

			// TODO XYZ
			SolicitudQueryPath sqp = new SolicitudQueryPath();
			// ORIG XXXX List<StringKeyValue> id_nom_Solis =
			// solicitudEjb.executeQuery(smskv,SolicitudFields.ENTITATLOCALID.equal(entitat.getEntitatID()));
			List<StringKeyValue> id_nom_Solis = solicitudEjb.executeQuery(smskv, sqp.DIR3().equal(entitat.getDir3()));
			// log.info(" - solis NOMS: ]" + Arrays.toString(id_nom_Solis.toArray()) + "[");

			key = entitat.getEntitatID();

			// value = "";
			String valuediv = "";
			if (id_nom_Solis.size() != 0) {

				valuediv = "<ul>";
				for (StringKeyValue skv : id_nom_Solis) {
					int pos = skv.value.indexOf('*');
					String code = skv.value.substring(0, pos);
					String nom = skv.value.substring(pos + 1);

					// NOTA: Les entitats locals tenen sol3licituds locals !!!!
					valuediv = valuediv + "<li><a href=\"" + request.getContextPath() + "/operador/solicitudlocal/"
							+ skv.key + "/edit\" >" + code + ": " + nom + "</a></li>";

				}
				valuediv += "</ul>";

			}

			divSolicituds.put(key, valuediv);

			// value = StringUtils.join(nomSolis.toArray()) + "<br/><a
			// href=\"www.google.cat\">GOOGLE</a>";

			// map.put(key, value);
		}

		mav.addObject("divSolicituds", divSolicituds);

	}

	@RequestMapping(value = "/documents/{entitatLocalID}", method = RequestMethod.GET)
	public ModelAndView veureDocumentCedentGet(@PathVariable("entitatLocalID") java.lang.Long entitatLocalID,
			HttpServletRequest request, HttpServletResponse response) throws I18NException {

		request.getSession().setAttribute(DocumentEntitatLocalOperatorController.ENTITATLOCALID_SESSION_PROPERTY,
				entitatLocalID);

		return new ModelAndView(new RedirectView("/operador/documententitatlocal/list", true));

	}

	public UsuariEntitat crearUsuariPinbalAdminEntitat(EntitatJPA entitat, UsuariClient usuariClient) {
		
		String codi = Configuracio.getApiPinbalUsername();
		String entitatCodi = entitat.getCodiPinbal();
		
		try {
			
			
			UsuariEntitat usuari = usuariClient.getUsuari(codi, entitatCodi);
			if (usuari != null) {
				log.info(
						"L'usuari " + codi + " ja existeix a l'entitat Pinbal " + entitatCodi + ". No es crea de nou.");
				return usuari;
			}
			
			
			
			//Camps per crear l'usuari a Pinbal

			String nif = null;
			String nom = null;
			String departament = "PinbalAdmin";

			boolean representatn = false;
			boolean delegat = false;
			boolean auditor = false;
			boolean aplicacio = true;
			boolean actiu = true;
			
			UsuariEntitat usuariEntitat = new UsuariEntitat(entitatCodi, codi, nif, nom, departament, representatn, delegat, auditor, aplicacio, actiu);

			usuariClient.createOrUpdateUsuari(usuariEntitat);
			
			UsuariEntitat created = usuariClient.getUsuari(codi, entitatCodi);
			log.info("Usuari " + codi + " creat correctament a l'entitat Pinbal " + entitatCodi + ".");
			return created;
			
		} catch (UniformInterfaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientHandlerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void crearPreAltaPerEntitatJPA(EntitatJPA entitat, ClientRecobriment clientRecobriment,
			ProcedimentClient procedimentClient) throws Exception {

		String dir3Local = entitat.getDir3();
		String codiPinbal = entitat.getCodiPinbal();
		String nomEntitat = entitat.getNom();

		log.info("[PREALTAS] Inici procés per a l'entitat: " + nomEntitat + " (" + codiPinbal + ")");

		// 1. Recuperar serveis de l'entitat
		log.info("[PREALTAS] Recuperant serveis de l'entitat a Pinbal: " + codiPinbal);
		List<ServeiBasic> serveisEntitat = clientRecobriment.getServeisPerEntitat(codiPinbal);

		if (serveisEntitat == null) {
			log.error("[PREALTAS] L'entitat no te serveis, o no s'han pogut recuperar. Entitat=" + codiPinbal);
			throw new Exception("L'entitat no te serveis, o no s'han pogut recuperar.");
		}

		log.info("[PREALTAS] Serveis recuperats: " + serveisEntitat.size());

		// 2. Comprovar serveis actius necessaris
		int serveisActius = 0;

		for (ServeiBasic servei : serveisEntitat) {
			String codiServei = servei.getCodi();

			if (CODI_CONSULTA.equals(codiServei) || CODI_ALTA.equals(codiServei)
					|| CODI_MODIFICACIO.equals(codiServei)) {

				if (servei.getActiu()) {
					serveisActius++;
					log.info("[PREALTAS] Servei actiu: " + codiServei);
				} else {
					log.warn("[PREALTAS] Servei NO actiu: " + codiServei);
				}
			}
		}

		if (serveisActius < 3) {
			log.warn("[PREALTAS] Serveis actius insuficients (" + serveisActius + "/3). Entitat=" + codiPinbal);
			throw new Exception("L'entitat no té actius tots els serveis necessaris.");
		}

		// 3. Comprovar si el procediment ja existeix
		Long procedimentID = null;

		try {
			Procediment existing = procedimentClient.getProcediment(PREALTAS, codiPinbal);
			if (existing != null) {
				procedimentID = existing.getId();
				log.info("[PREALTAS] Procediment ja existent. ID=" + procedimentID);
			}
		} catch (Exception e) {
			log.info("[PREALTAS] No s'ha trobat procediment existent. Es crearà un de nou.");
		}

		// 4. Crear procediment si no existeix
		if (procedimentID == null) {
			log.info("[PREALTAS] Creant nou procediment PREALTAS");

			Procediment procediment = crearProcPreAlta(codiPinbal, dir3Local);

			log.info("[PREALTAS] Dades del procediment:");
			log.info("   - ID: " + procediment.getId());
			log.info("   - Codi: " + procediment.getCodi());
			log.info("   - Nom: " + procediment.getNom());
			log.info("   - Departament: " + procediment.getDepartament());
			log.info("   - EntitatCodi: " + procediment.getEntitatCodi());
			log.info("   - OrganGestorDir3: " + procediment.getOrganGestorDir3());
			log.info("   - Actiu: " + procediment.isActiu());
			log.info("   - CodiSia: " + procediment.getCodiSia());
			log.info("   - ClaseTramite: " + procediment.getValorCampClaseTramite());
			log.info("   - Automatizado: " + procediment.getValorCampAutomatizado());

			procedimentClient.createProcediment(procediment);
			log.info("[PREALTAS] Procediment creat correctament");

			// Recuperar ID del procediment creat
			Procediment created = procedimentClient.getProcediment(PREALTAS, codiPinbal);
			if (created == null) {
				log.error("[PREALTAS] Procediment creat però no recuperable");
				throw new Exception("Error recuperant el procediment creat.");
			}

			procedimentID = created.getId();
			log.info("[PREALTAS] ID del procediment creat: " + procedimentID);
		}

		// 5. Autoritzar serveis
		if (procedimentID != null) {
			log.info("[PREALTAS] Autoritzant serveis al procediment ID=" + procedimentID);
			autoritzarServeisPreAltaProcediment(procedimentID, procedimentClient);
			log.info("[PREALTAS] Procés finalitzat correctament");
		} else {
			log.error("[PREALTAS] No s'ha pogut obtenir l'ID del procediment");
			throw new Exception("No s'ha pogut crear ni recuperar el procediment PREALTAS.");
		}

	}

	public void crearPreAltaPerEntitatJPAOld(EntitatJPA entitat, ClientRecobriment clientRecobriment,
			ProcedimentClient procedimentClient) throws I18NException {

		// 1. Obtenim el cif i el dir3 de l'entitat.
		// 2. Recuperem les entitats de Pinbal, i busquem la que té el mateix CIF.
		// 3. Si la trobam, obtenim el codi de l'entitat a Pinbal, sino, sortim
		// 4. Comprovam si els serveis estan actius a l'entitat a Pinbal.
		// 5. Si estan actius, hem de autoritzar els serveis al procediment PREALTAS.
		// 6. Necessitam un procedimentId. Comprovam si ja existeix, i l'obtenim.
		// 7. Si no existeix, el cream, i el cercam per obtenir l'ID.
		// 8. Un cop tenim l'ID, autoritzam els serveis.

		try {
			String dir3Local = entitat.getDir3();
			String codiPinbal = entitat.getCodiPinbal();
			String nomEntitat = entitat.getNom();

			// Abans de continuar, hem de comprovar que els serveis están actius a l'entitat
			// a Pinbal.
			log.info("Comprovant serveis actius a l'entitat Pinbal: " + codiPinbal);
			List<ServeiBasic> serveisEntitat = clientRecobriment.getServeisPerEntitat(codiPinbal);
			log.info("Serveis recuperats: " + (serveisEntitat != null ? serveisEntitat.size() : "null"));
			int actius = 0;
			if (serveisEntitat == null) {
				log.error("No s'han pogut recuperar els serveis de l'entitat Pinbal: " + codiPinbal
						+ ". No es pot crear el procediment PREALTAS.");
				throw new Exception("No s'han pogut recuperar els serveis de l'entitat Pinbal: " + codiPinbal
						+ ". No es pot crear el procediment PREALTAS.");
			}

			for (ServeiBasic servei : serveisEntitat) {
				String codi = servei.getCodi();
				if (codi.equals(CODI_CONSULTA) || codi.equals(CODI_ALTA) || codi.equals(CODI_MODIFICACIO)) {
					if (servei.getActiu()) {
						actius++;
						log.info("Servei actiu a l'entitat Pinbal: " + codi);
					} else {
						log.warn("Servei NO actiu a l'entitat Pinbal: " + codi);
					}
				}
			}

			if (actius == 0) {
				log.error("No s'han pogut recuperar els serveis de l'entitat Pinbal: " + codiPinbal
						+ ". No es pot crear el procediment PREALTAS.");
			}else if (actius < 3) {
				log.error("No tots els serveis necessaris estan actius a l'entitat Pinbal. "
						+ "No es pot crear el procediment PREALTAS.");
//				HtmlUtils.saveMessageError(request, "No tots els serveis necessaris estan actius a l'entitat Pinbal. "
//						+ "No es pot crear el procediment PREALTAS.");
				throw new Exception("No tots els serveis necessaris estan actius a l'entitat Pinbal. "
						+ "No es pot crear el procediment PREALTAS.");
//				return new ModelAndView("redirect:" + getContextWeb() + "/list");
			}

			Long procedimentID = null;
			try {
				Procediment existing = procedimentClient.getProcediment(PREALTAS, codiPinbal);
				if (existing != null) {
					procedimentID = existing.getId();
					log.info("El procediment " + PREALTAS + " ja existia per a l'entitat: " + nomEntitat + ". ID: "
							+ procedimentID);

//					HtmlUtils.saveMessageInfo(request,
//							"El procediment " + PREALTAS + " ja existia per a l'entitat: " + entitatLocal.getNom());
				}
			} catch (Exception e) {
				log.info("No s'ha trobat procediment " + PREALTAS + " existent (o error al comprovar): "
						+ e.getMessage());
			}

			if (procedimentID == null) {
				Procediment procediment = crearProcPreAlta(codiPinbal, dir3Local);

				log.info("Creant procediment a Pinbal...");
				log.info("   Dades del procediment a crear:");
				log.info("   - ID: " + procediment.getId());
				log.info("   - Codi: " + procediment.getCodi());
				log.info("   - Nom: " + procediment.getNom());
				log.info("   - Departament: " + procediment.getDepartament());
				log.info("   - EntitatCodi: " + procediment.getEntitatCodi());
				log.info("   - OrganGestorDir3: " + procediment.getOrganGestorDir3());
				log.info("   - Actiu: " + procediment.isActiu());
				log.info("   - CodiSia: " + procediment.getCodiSia());
				log.info("   - ClaseTramite: " + procediment.getValorCampClaseTramite());
				log.info("   - Automatizado: " + procediment.getValorCampAutomatizado());

				procedimentClient.createProcediment(procediment);
				log.info("Procediment creat correctament.");

				log.info("Procediment " + PREALTAS + " creat correctament a Pinbal per a l'entitat: " + nomEntitat);

//				HtmlUtils.saveMessageInfo(request,
//						"Procediment " + PREALTAS + " creat correctament a Pinbal per a l'entitat: " + entitatLocal.getNom());

				// Obtenim ID del procediment creat, per autoritzar serveis.
				try {
					Procediment created = procedimentClient.getProcediment(PREALTAS, codiPinbal);
					if (created != null) {
						procedimentID = created.getId();
					} else {
						log.error(
								"Error recuperant ID del procediment creat: el procediment no existeix després de crear-lo.");
						throw new Exception(
								"Error recuperant ID del procediment creat: el procediment no existeix després de crear-lo.");
					}
				} catch (Exception e) {
					log.error("Error recuperant ID del procediment creat", e);
					throw new Exception("Error recuperant ID del procediment creat: " + e.getMessage());
				}
				
			} else {
				log.info("No cal crear el procediment PREALTAS, ja existeix.");
				// Autoritzem els serveis directament.
			}

			if (procedimentID != null) {
				autoritzarServeisPreAltaProcediment(procedimentID, procedimentClient);
			} else {
				log.error("No s'ha pogut crear ni recuperar el procediment PREALTAS per a l'entitat: " + nomEntitat);
				throw new Exception(
						"No s'ha pogut crear ni recuperar el procediment PREALTAS per a l'entitat: " + nomEntitat);
			}

		} catch (Exception e) {
			log.error("Error creant procediment a Pinbal", e);
			throw new I18NException("genapp.comodi",new I18NArgumentString( "Error creant procediment a Pinbal: " + e.getMessage()));
//			HtmlUtils.saveMessageError(request, "Error creant procediment a Pinbal: " + e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/crearPreAltasEntitat/{id}", method = RequestMethod.GET)
	public ModelAndView crearPreAltasEntitat(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) {

		log.info("[PREALTAS] Iniciant creació per a l'entitat amb id: " + id);

		try {
			ClientRecobriment clientRecobriment = getClientRecobriment();
			ProcedimentClient procedimentClient = getProcedimentClient();
			UsuariClient usuariClient = getUsuariClient();

			EntitatJPA entitatLocal = entitatLogicaEjb.findByPrimaryKey(id);

			if (entitatLocal == null) {
				log.error("[PREALTAS] No s'ha trobat l'entitat amb id: " + id);
				HtmlUtils.saveMessageError(request, "No s'ha trobat l'entitat indicada.");
				return new ModelAndView("redirect:" + getContextWeb() + "/list");
			}

			log.info("[PREALTAS] Entitat trobada: " + entitatLocal.getNom());

			// Crear o recuperar usuari Pinbal
			UsuariEntitat usuari = crearUsuariPinbalAdminEntitat(entitatLocal, usuariClient);

			if (usuari == null) {
				log.error("[PREALTAS] No s'ha pogut crear l'usuari Pinbal per a l'entitat: " + entitatLocal.getNom());
				HtmlUtils.saveMessageError(request, "No s'ha pogut crear l'usuari necessari per a l'entitat.");
				return new ModelAndView("redirect:" + getContextWeb() + "/list");
			}

			log.info("[PREALTAS] Usuari Pinbal creat o existent per a l'entitat");

			// Crear procediment PREALTAS
			crearPreAltaPerEntitatJPA(entitatLocal, clientRecobriment, procedimentClient);

			HtmlUtils.saveMessageInfo(request,
					"El procediment PREALTAS s'ha creat correctament per a l'entitat " + entitatLocal.getNom());

			log.info("[PREALTAS] Procés finalitzat correctament per a l'entitat: " + entitatLocal.getNom());

		} catch (Exception e) {
			log.error("[PREALTAS] Error durant la creació del procediment: " + e.getMessage(), e);

			HtmlUtils.saveMessageError(request, "Error:" + e.getMessage());
		}

		return new ModelAndView("redirect:" + getContextWeb() + "/list");
	}

	@RequestMapping(value = "/crearPreAltasEntitatAll", method = RequestMethod.GET)
	public ModelAndView crearPreAltasEntitatAll(HttpServletRequest request, HttpServletResponse response) {
		long startTime = System.currentTimeMillis();
		log.info("Iniciant crearPreAltasEntitatAll...");
		int total = 0;
		int creats = 0;
		int errors = 0;
		try {
			ClientRecobriment clientRecobriment = getClientRecobriment();
			ProcedimentClient procedimentClient = getProcedimentClient();

			List<Entitat> entitatsLocals = entitatLogicaEjb.select();
			total = entitatsLocals.size();
			log.info("Entitats locals recuperades: " + total);

			for (Entitat entitatLocal : entitatsLocals) {
				EntitatJPA entitatLocalJPA = (EntitatJPA) entitatLocal;
				try {
					crearPreAltaPerEntitatJPA(entitatLocalJPA, clientRecobriment, procedimentClient);
					creats++;
				} catch (I18NException e) {
					errors++;
					log.error("Error creant PREALTAS per a l'entitat: " + entitatLocal.getNom(), e);
				}
			}

			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			log.info("crearPreAltasEntitatAll finalitzat. Total entitats: " + total + ", Creats: " + creats
					+ ", Errors: " + errors + ". Temps total: " + duration + " ms.");

			HtmlUtils.saveMessageInfo(request, "Operació finalitzada. Total entitats: " + total + ", Creats: " + creats
					+ ", Errors: " + errors + ". Temps total: " + duration + " ms.");
		} catch (Exception e) {
			log.error("Error global a crearPreAltasEntitatAll", e);
			HtmlUtils.saveMessageError(request, "Error global: " + e.getMessage());
		}
		return new ModelAndView("redirect:" + getContextWeb() + "/list");
	}

	public Procediment crearProcPreAlta(String codiEntitat, String dir3) {

		Long id = null;
		String codi = PREALTAS;
		String nom = "Gestió de peticions autorització PID";
		String deprt = "FBIT";
		boolean aciu = true;
		String entitat = codiEntitat;
		String organGestorDir3 = dir3;
		String codiSia = codi;
		boolean automatizado = false;
		ClaseTramite tramite = ClaseTramite.AUTORIZ_LICEN_CONCES_HOMOLOG;

		Procediment nou = new Procediment(
		    id, codi, nom, deprt, aciu,
		    entitat, organGestorDir3,
		    codiSia, automatizado, tramite
		);

		
		return nou;
	}

	public void autoritzarServeisPreAltaProcediment(Long procedimentID, ProcedimentClient procedimentClient)  throws Exception {
		log.info("   Autoritzant serveis per al procediment ID: " + procedimentID);

		try {

			// CONSULTA
			log.info("      Autoritzant " + CODI_CONSULTA + "...");
			procedimentClient.enableServeiToProcediment(procedimentID, CODI_CONSULTA);

			// ALTA
			log.info("      Autoritzant " + CODI_ALTA + "...");
			procedimentClient.enableServeiToProcediment(procedimentID, CODI_ALTA);

			// MODIFICACIÓ
			log.info("      Autoritzant " + CODI_MODIFICACIO + "...");
			procedimentClient.enableServeiToProcediment(procedimentID, CODI_MODIFICACIO);

			log.info("   Serveis autoritzats correctament.");

		} catch (Exception e) {
			log.error("   Error autoritzant serveis per al procediment PREALTAS amb ID: " + procedimentID);
			throw e;
		}

	}

}
