package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.EntitatController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatForm;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.ejb.DocumentEntitatService;
import org.fundaciobit.pinbaladmin.logic.EntitatLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.pinbaladmin.model.fields.DocumentEntitatFields;
import org.fundaciobit.pinbaladmin.model.fields.EntitatFields;
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

import es.caib.pinbal.client.comu.LogLevel;
import es.caib.pinbal.client.procediments.ClaseTramite;
import es.caib.pinbal.client.procediments.Procediment;
import es.caib.pinbal.client.procediments.ProcedimentClient;
import es.caib.pinbal.client.recobriment.v2.ClientRecobriment;

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

	// public static final int SOLICITUDS = 1;
	public static final int DOCS = 1;
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

	// @RequestMapping(value = "/crearEntitatPinbal/{id}", method =
	// RequestMethod.GET)
	// public ModelAndView crearEntitatPinbal(@PathVariable("id") Long id,
	// HttpServletRequest request, HttpServletResponse response) {
	// try {
	// Entitat entitatLocal = entitatLogicaEjb.findByPrimaryKey(id);
	//
	// final String baseUrl = Configuracio.getApiPinbalClientUrl();
	// final String username = Configuracio.getApiPinbalClientUsername();
	// final String password = Configuracio.getApiPinbalClientPassword();
	// final LogLevel logLevel = LogLevel.INFO;
	//
	// ClientRecobriment clientRecobriment = new ClientRecobriment(baseUrl,
	// username, password, logLevel);
	//
	// es.caib.pinbal.client.recobriment.v2.Entitat entitatPinbal = new
	// es.caib.pinbal.client.recobriment.v2.Entitat();
	// entitatPinbal.setCodi(entitatLocal.getDir3());
	// entitatPinbal.setNom(entitatLocal.getNom());
	// entitatPinbal.setCif(entitatLocal.getCIF());
	//
	// // clientRecobriment.createEntitat(entitatPinbal);
	//
	// HtmlUtils.saveMessageInfo(request, "Entitat creada correctament a Pinbal: " +
	// entitatLocal.getNom());
	// } catch (Exception e) {
	// log.error("Error creant entitat a Pinbal", e);
	// HtmlUtils.saveMessageError(request, "Error creant entitat a Pinbal: " +
	// e.getMessage());
	// }
	// return new ModelAndView("redirect:" + getContextWeb() + "/list");
	// }

	@RequestMapping(value = "/crearPreAltasEntitat/{id}", method = RequestMethod.GET)
	public ModelAndView crearPreAltasEntitat(@PathVariable("id") Long id, HttpServletRequest request,
			HttpServletResponse response) {
		log.info("Iniciant crearPreAltasEntitat per id: " + id);
		try {

			EntitatJPA entitatLocal = entitatLogicaEjb.findByPrimaryKey(id);
			String CIF = entitatLocal.getCIF();
			String dir3Local = entitatLocal.getDir3();

			log.info("Entitat local recuperada: " + entitatLocal.getNom() + ", CIF: " + CIF + ", Dir3: " + dir3Local);

			ClientRecobriment clientRecobriment = getClientRecobriment();
			// Obtener las entidades de Pinbal, y recuperar la de CIF.
			log.info("Recuperant entitats de Pinbal...");
			List<es.caib.pinbal.client.recobriment.v2.Entitat> entitatsPinbal = clientRecobriment.getEntitats();
			log.info("Entitats de Pinbal recuperades: " + (entitatsPinbal != null ? entitatsPinbal.size() : "null"));

			es.caib.pinbal.client.recobriment.v2.Entitat entitatPinbalFound = null;
			if (entitatsPinbal != null) {
				for (es.caib.pinbal.client.recobriment.v2.Entitat entitatPinbal : entitatsPinbal) {
					if (CIF != null && CIF.equalsIgnoreCase(entitatPinbal.getCif())) {
						entitatPinbalFound = entitatPinbal;
						log.info("Entitat Pinbal trobada per CIF " + CIF + ": Codi=" + entitatPinbal.getCodi());
						break;
					}
				}
			}

			if (entitatPinbalFound == null) {
				log.warn("No s'ha trobat cap entitat a Pinbal amb CIF: " + CIF);
				HtmlUtils.saveMessageError(request, "No s'ha trobat cap entitat a Pinbal amb CIF: " + CIF
						+ ". No es pot crear el procediment PREALTAS.");
				return new ModelAndView("redirect:" + getContextWeb() + "/list");
			}

			ProcedimentClient procedimentClient = getProcedimentClient();

			Long procedimentID = null;
			try {
				Procediment existing = procedimentClient.getProcediment(PREALTAS, entitatPinbalFound.getCodi());
				if (existing != null) {
					procedimentID = existing.getId();
					log.info("El procediment " + PREALTAS + " ja existeix. ID: " + procedimentID);
					HtmlUtils.saveMessageInfo(request,
							"El procediment " + PREALTAS + " ja existia per a l'entitat: " + entitatLocal.getNom());
				}
			} catch (Exception e) {
				log.info("No s'ha trobat procediment " + PREALTAS + " existent (o error al comprovar): " + e.getMessage());
			}

			if (procedimentID == null) {
				Procediment procediment = crearProcPreAlta(entitatPinbalFound.getCodi(), dir3Local);

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

				HtmlUtils.saveMessageInfo(request,
						"Procediment " + PREALTAS + " creat correctament a Pinbal per a l'entitat: " + entitatLocal.getNom());

				try {
					Procediment created = procedimentClient.getProcediment(PREALTAS, entitatPinbalFound.getCodi());
					if (created != null) {
						procedimentID = created.getId();
					}
				} catch (Exception e) {
					log.error("Error recuperant ID del procediment creat", e);
				}
			}

			if (procedimentID != null) {
				autoritzarServeisPreAltaProcediment(procedimentID);
			}
		} catch (Exception e) {
			log.error("Error creant procediment a Pinbal", e);
			HtmlUtils.saveMessageError(request, "Error creant procediment a Pinbal: " + e.getMessage());
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

			// Optimització: Carregar totes les entitats locals en memòria per evitar
			// consultes per cada iteració
			log.info("Carregant entitats locals per mapar CIF -> DIR3...");
			List<Entitat> entitatsLocals = entitatLogicaEjb.select();
			Map<String, String> cifToDir3Map = new HashMap<String, String>();
			if (entitatsLocals != null) {
				for (Entitat ent : entitatsLocals) {
					if (ent.getCIF() != null) {
						cifToDir3Map.put(ent.getCIF().toUpperCase(), ent.getDir3());
					}
				}
			}
			log.info("Entitats locals carregades: " + cifToDir3Map.size());

			log.info("Recuperant entitats de Pinbal...");
			List<es.caib.pinbal.client.recobriment.v2.Entitat> entitatsPinbal = clientRecobriment.getEntitats();

			if (entitatsPinbal != null) {
				total = entitatsPinbal.size();
				log.info("Total entitats Pinbal a processar: " + total);

				for (es.caib.pinbal.client.recobriment.v2.Entitat entitatPinbal : entitatsPinbal) {
					String nomEntitat = entitatPinbal.getNom();
					String codiEntitat = entitatPinbal.getCodi();
					String cifEntitat = entitatPinbal.getCif();

					log.info(">>> Processant entitat Pinbal: " + nomEntitat + " (Codi: " + codiEntitat + ", CIF: "
							+ cifEntitat + ")");

					try {
						// Recuperar DIR3 del mapa
						String dir3 = null;
						if (cifEntitat != null) {
							dir3 = cifToDir3Map.get(cifEntitat.toUpperCase());
						}

						// Si dir3 val null, vol dir que no tenim entitat local per aquest CIF, i per
						// tant no tenim DIR3.
						// Sense el dir3 no podem crear el procediment.

						if (dir3 == null || dir3.isEmpty()) {
							log.warn("   No s'ha trobat DIR3 local per al CIF: " + cifEntitat
									+ ". No es pot crear el procediment PREALTAS.");
							errors++;
							continue;
						} else {
							log.info("   DIR3 local trobat: " + dir3);
						}

						Long procedimentID = null;
						try {
							Procediment existing = procedimentClient.getProcediment(PREALTAS, codiEntitat);
							if (existing != null) {
								procedimentID = existing.getId();
								log.info("   El procediment " + PREALTAS + " ja existeix. ID: " + procedimentID);
							}
						} catch (Exception e) {
							log.info("   No s'ha trobat procediment " + PREALTAS + " existent (o error al comprovar): "
									+ e.getMessage());
						}

						if (procedimentID == null) {
							Procediment procediment = crearProcPreAlta(codiEntitat, dir3);

							log.info("   Creant procediment " + PREALTAS + " a Pinbal...");
							log.info("      Dades del procediment a crear:");
							log.info("      - ID: " + procediment.getId());
							log.info("      - Codi: " + procediment.getCodi());
							log.info("      - Nom: " + procediment.getNom());
							log.info("      - Departament: " + procediment.getDepartament());
							log.info("      - EntitatCodi: " + procediment.getEntitatCodi());
							log.info("      - OrganGestorDir3: " + procediment.getOrganGestorDir3());
							log.info("      - Actiu: " + procediment.isActiu());
							log.info("      - CodiSia: " + procediment.getCodiSia());
							log.info("      - ClaseTramite: " + procediment.getValorCampClaseTramite());
							log.info("      - Automatizado: " + procediment.getValorCampAutomatizado());
							
							try {
								procedimentClient.createProcediment(procediment);
								log.info("   Procediment creat.");
								creats++;

								// Obtenim ID del procediment creat.
								log.info("   Recuperant ID del procediment creat...");
								Procediment procedimentCreat = procedimentClient.getProcediment(PREALTAS,
										entitatPinbal.getCodi());

								if (procedimentCreat != null) {
									procedimentID = procedimentCreat.getId();
								}
							} catch (Exception ex) {
								log.error("   ERROR al crear el procediment al servidor Pinbal. " +
										"Això és un error del servidor (500), no del client. " +
										"Possible causa: validació fallida al servidor, DIR3 invàlid, " +
										"o conflicte amb dades existents.", ex);
								errors++;
								continue;
							}
						}

						if (procedimentID != null) {
							log.info("   Procediment ID: " + procedimentID);

							// Autoritzem serveis per al procediment creat.
							autoritzarServeisPreAltaProcediment(procedimentID);
						} else {
							log.error("   No s'ha pogut recuperar ni crear el procediment " + PREALTAS + " per a l'entitat: "
									+ codiEntitat);
							errors++;
						}

					} catch (Exception e) {
						log.error("   Error processant entitat Pinbal: " + nomEntitat, e);
						errors++;
					}
				}
			} else {
				log.warn("No s'han recuperat entitats de Pinbal.");
			}

			long endTime = System.currentTimeMillis();
			long duration = endTime - startTime;
			log.info("Finalitzat crearPreAltasEntitatAll. Temps total: " + duration + "ms. Creats: " + creats
					+ ". Errors: " + errors + ". Total: " + total);

			HtmlUtils.saveMessageInfo(request, "Procés finalitzat. Creats: " + creats + ", Errors: " + errors
					+ ", Total: " + total + ". Temps: " + duration + "ms.");
		} catch (Exception e) {
			log.error("Error global a crearPreAltasEntitatAll", e);
			HtmlUtils.saveMessageError(request, "Error global: " + e.getMessage());
		}
		return new ModelAndView("redirect:" + getContextWeb() + "/list");
	}

	public Procediment crearProcPreAlta(String codiEntitat, String dir3) {
		Procediment procediment = new Procediment();
		procediment.setId(null); // Assegurem que el ID sigui null per a nous procediments
		procediment.setCodi(PREALTAS);
		procediment.setNom("Gestió de peticions autorització PID");
		procediment.setDepartament(null); // Camp opcional segons API
		procediment.setActiu(true);
		procediment.setCodiSia(null);
		procediment.setValorCampClaseTramite(ClaseTramite.AUTORIZ_LICEN_CONCES_HOMOLOG);
		procediment.setValorCampAutomatizado(false);

		procediment.setEntitatCodi(codiEntitat);
		procediment.setOrganGestorDir3(dir3);

		return procediment;

	}

	public void autoritzarServeisPreAltaProcediment(Long procedimentID) {
		log.info("   Autoritzant serveis per al procediment ID: " + procedimentID);
		ProcedimentClient procedimentClient = getProcedimentClient();

		try {
			String consulta = "SVDPIDESTADOAUTWS01";
			String alta = "SVDPIDSOLAUTWS01";
			String modificacio = "SVDPIDACTPROCWS01";

			// CONSULTA
			log.info("      Autoritzant " + consulta + "...");
			procedimentClient.enableServeiToProcediment(procedimentID, consulta);

			// ALTA
			log.info("      Autoritzant " + alta + "...");
			procedimentClient.enableServeiToProcediment(procedimentID, alta);

			// MODIFICACIÓ
			log.info("      Autoritzant " + modificacio + "...");
			procedimentClient.enableServeiToProcediment(procedimentID, modificacio);

			log.info("   Serveis autoritzats correctament.");

		} catch (Exception e) {
			log.error("   Error autoritzant serveis per al procediment PREALTAS amb ID: " + procedimentID, e);
		}

	}

}
