package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaEJB.TipusCridada;
import org.fundaciobit.pinbaladmin.logic.utils.FileInfo;
import org.fundaciobit.pinbaladmin.logic.utils.PdfDownloader;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;
import org.fundaciobit.pluginsib.utils.commons.GregorianCalendars;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Articulos;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Consentimiento;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contacto;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Contactos;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DocumentoAutorizacion;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DocumentosAutorizacion;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Norma;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Normas;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Procedimiento;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Servicio;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Servicios;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud;

public class PinbalUtilsAlta extends PinbalUtilsCommon {

	public PinbalUtilsAlta() {
		// Constructor vacío
	}

	public Respuesta altaSolicitudApiPinbal(ScspTitular titular, ScspFuncionario funcionario, Solicitud solicitud)
			throws Exception {

		PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(
				getPinbalAdminSolicitudsConfiguration(TipusCridada.ALTA));
		Respuesta respuesta = api.altaSolicitudPinbalApi(solicitud, titular, funcionario);

		return respuesta;
	}

	public Solicitud getDadesSolicitudApiPinbal(SolicitudJPA soli, Properties prop) throws Exception {

		Solicitud solicitud = new Solicitud();

		String asunto = "Alta Servicios. Codigo Solicitud: " + soli.getProcedimentCodi();
		solicitud.setAsunto(asunto);

		Contactos contactos = getContactos(prop);
		solicitud.setContactos(contactos);

		Procedimiento proc = getProcedimiento(soli);
		solicitud.setProcedimiento(proc);
		return solicitud;
	}

	private Contactos getContactos(Properties prop) {

		String base = "FORMULARIO.DATOS_SOLICITUD.";

		Contactos contactos = new Contactos();

		// Contacto Aut
		String contactoAutApe1 = prop.getProperty(base + "APE1SECD");
		String contactoAutApe2 = prop.getProperty(base + "APE2SECD");
		String contactoAutMail = prop.getProperty(base + "MAILSECD");
		String contactoAutNombre = prop.getProperty(base + "NOMBRESECD");
		String contactoAutTelefon = prop.getProperty(base + "TELEFONOSECD");

		Contacto contactoAut = createContacto(contactoAutApe1, contactoAutApe2, contactoAutMail, contactoAutNombre,
				contactoAutTelefon);

		if (contactoAut != null) {
			contactos.getContacto().add(contactoAut);
		}

		// Contacto Aud
		String contactoAudApe1 = prop.getProperty(base + "APE1SECE");
		String contactoAudApe2 = prop.getProperty(base + "APE2SECE");
		String contactoAudMail = prop.getProperty(base + "MAILSECE");
		String contactoAudNombre = prop.getProperty(base + "NOMBRESECE");
		String contactoAudTelefon = prop.getProperty(base + "TELEFONOSECE");

		Contacto contactoAud = createContacto(contactoAudApe1, contactoAudApe2, contactoAudMail, contactoAudNombre,
				contactoAudTelefon);

		if (contactoAud != null) {
			contactos.getContacto().add(contactoAud);
		}

		// Contacto Tec
		String contactoTecApe1 = prop.getProperty(base + "APE1SECF");
		String contactoTecApe2 = prop.getProperty(base + "APE2SECF");
		String contactoTecMail = prop.getProperty(base + "MAILSECF");
		String contactoTecNombre = prop.getProperty(base + "NOMBRESECF");
		String contactoTecTelefon = prop.getProperty(base + "TELEFONOSECF");

		Contacto contactoTec = createContacto(contactoTecApe1, contactoTecApe2, contactoTecMail, contactoTecNombre,
				contactoTecTelefon);

		if (contactoTec != null) {
			contactos.getContacto().add(contactoTec);
		}

		return contactos;
	}

	private Contacto createContacto(String contactoApe1, String contactoApe2, String contactoMail,
			String contactoNombre, String contactoTelefono) {

		if (contactoApe1 != null && contactoMail != null && contactoNombre != null && contactoTelefono != null) {
			Contacto contacto = new Contacto();
			contacto.setApellido1(contactoApe1);
			if (contactoApe2 == null) {
				// contactoApe2 = "Apellido2";
			}

			contacto.setApellido2(contactoApe2);
			contacto.setEmail(contactoMail);
			contacto.setFax(null);
			contacto.setNombre(contactoNombre);
			contacto.setTelefono(contactoTelefono);
			return contacto;
		} else {
			return null;
		}
	}

	public Procedimiento getProcedimiento(SolicitudJPA soli) throws Exception {

		String _Automatizado = "N";// soli.getAutomatizado();
		String _Periodico = "N"; // soli.getPeriodico();

		String petsDia = "40"; // soli.getPetsDia();
		Integer _PeticionesEstimadas = Integer.parseInt(petsDia);

		String tipusProc = soli.getProcedimentTipus();
		Integer _ClaseTramite = getIdentificadorNuevoPorId(tipusProc);

		String _Codigo = soli.getProcedimentCodi();
		String _Nombre = soli.getProcedimentNom();
		String _Descripcion = soli.getCodiDescriptiu();
		if (_Nombre.equals(_Descripcion)) {
			_Descripcion = "_" + _Descripcion;
		}
		String _Observaciones = null;// soli.getNotes();

		Timestamp dataCaducitat = soli.getDataFi();
		XMLGregorianCalendar _FechaCaducidad = GregorianCalendars.timestampToXMLGregorianCalendar(dataCaducitat); // parseTimestampToXMLGregorian(dataCaducitat);

		Fitxer fitxerConsentiment = null;
		// Aquí son el excel de servicios y el documento PDF del Director General.
		Set<DocAuthInfo> docsAuth = new HashSet<DocAuthInfo>();

		for (DocumentSolicitudJPA document : soli.getDocumentSolicituds()) {

			Long tipus = document.getDocument().getTipus();

			if (tipus == Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF) {
				FitxerJPA fitxer = document.getDocument().getFitxerFirmat();
				if (fitxer != null) {
					String desc = "Formulari PDF firmat per el director";
					String tipo = "FORMULARIO DE AUTORIZACION";
					docsAuth.add(new DocAuthInfo(fitxer, desc, tipo));
				} else {
					log.info("Fa falta el formulari firmat per el DG");
				}

			} else if (tipus == Constants.DOCUMENT_SOLICITUD_EXCEL_SERVEIS) {
//                FitxerJPA fitxer = document.getDocument().getFitxerOriginal();
//                String desc = "Excel de serveis i procediments";
//                String tipo = "EXCEL DE SERVICIOS";
//                docsAuth.add(new DocAuthInfo(fitxer, desc, tipo)); 

			} else if (tipus == Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI
					|| tipus == Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_NOOP) {
				fitxerConsentiment = document.getDocument().getFitxerOriginal(); // Document consentiment
			} else {
				FitxerJPA original = document.getDocument().getFitxerOriginal();
				if (original.getMime().equals("application/pdf")) {
					FitxerJPA fitxer = original;
					String desc = "Fitxer PDF associat al procediment";
					String tipo = "DOC AUTORITZACÓ";
					docsAuth.add(new DocAuthInfo(fitxer, desc, tipo));
				}
			}
		}

		Procedimiento proc = new Procedimiento();
		proc.setAutomatizado(_Automatizado);
		proc.setClaseTramite(_ClaseTramite);
		proc.setCodigo(_Codigo);
		proc.setDescripcion(_Descripcion);
		proc.setNombre(_Nombre);
		proc.setObservaciones(_Observaciones);
		proc.setPeriodico(_Periodico);
		proc.setPeticionesEstimadas(_PeticionesEstimadas);
		proc.setFechaCaducidad(_FechaCaducidad);

		DocumentosAutorizacion _DocumentosAutorizacion = getDocsAutorizacion(docsAuth);
		Consentimiento _Consentimiento = getConsentimientoFromSoli(soli, fitxerConsentiment);

		proc.setDocumentosAutorizacion(_DocumentosAutorizacion);
		proc.setConsentimiento(_Consentimiento);

		if (_DocumentosAutorizacion == null || _Consentimiento == null) {
			return proc;
		}

		Servicios _Servicios = getServicios(soli);
		proc.setServicios(_Servicios);

		return proc;
	}

	private Consentimiento getConsentimientoFromSoli(SolicitudJPA soli, Fitxer fitxerConsentiment)
			throws Exception {
		Consentimiento cons = new Consentimiento();

		final String PINBAL_CONSENTIMENT_LLEI = "Ley";
		final String PINBAL_CONSENTIMENT_SI = "Si";
		final String PINBAL_CONSENTIMENT_NOOP = "NoOpo";

		String consentiment = soli.getConsentiment(); // si, llei, noop

		switch (consentiment) {
		case Constants.CONSENTIMENT_TIPUS_LLEI:
			cons.setTipo(PINBAL_CONSENTIMENT_LLEI);
			return cons;
		case Constants.CONSENTIMENT_TIPUS_SI:
			cons.setTipo(PINBAL_CONSENTIMENT_SI);
			break;
		case Constants.CONSENTIMENT_TIPUS_NOOP:
			cons.setTipo(PINBAL_CONSENTIMENT_NOOP);
			break;
		default:
			log.info("CONS: No tenim fitxer de consentiment.");
			return null;
		}

		// Si esta aqui es perque el consentiment es de tipus SI o NOOP, i per tant
		// necessita un fitxer
		Consentimiento.Documento doc = new Consentimiento.Documento();

		String nom = null;
		String descripcio = null;
		byte[] contingut = null;

		try {
			if (soli.getConsentimentadjunt().equals(Constants.CONSENTIMENT_PUBLICAT)) {
				String enlace = soli.getUrlconsentiment();
				if (enlace != null && enlace.trim().length() != 0) {
					cons.setEnlace(enlace);

					FileInfo fileInfo = PdfDownloader.downloadPDFFromBoeBoibUrl(enlace, false);

					nom = fileInfo.getFileName();
					descripcio = "Fitxer de consentiment. Enllaç: " + enlace;
					contingut = fileInfo.getContent();
				}

			} else if (soli.getConsentimentadjunt().equals(Constants.CONSENTIMENT_ADJUNT)) {
				if (fitxerConsentiment != null) {
					Long consentimentID = fitxerConsentiment.getFitxerID();

					nom = fitxerConsentiment.getNom();
					descripcio = "Fitxer de consentiment. IDFitxer: " + consentimentID;

					File fileConsentiment = FileSystemManager.getFile(consentimentID);
					contingut = FileUtils.readFromFile(fileConsentiment);
				}
			}
		} catch (Exception e) {
			String msg = "CONS: Error obtenint el PDF de Consentiment: " + e.getMessage();
			log.error(msg);
			throw new Exception(msg);
		}

		log.info("CONS: " + nom + " : " + contingut.length + " bytes");

		doc.setNombre(nom);
		doc.setDescripcion(descripcio);
		doc.setContenido(contingut);

		cons.setDocumento(doc);
		return cons;
	}

	private DocumentosAutorizacion getDocsAutorizacion(Set<DocAuthInfo> documents) throws Exception {

		log.info("Documents de la solicitud: " + documents.size());

		if (documents.size() == 0) {
			return null;
		}

		DocumentosAutorizacion docs = new DocumentosAutorizacion();

		for (DocAuthInfo docInfo : documents) {
			FitxerJPA fitxer = docInfo.getFitxer();

			String nom = fitxer.getNom();
			String descripcio = docInfo.getDescripcio();
			String tipo = docInfo.getTipo();

			Long fitxerID = fitxer.getFitxerID();
			File file = FileSystemManager.getFile(fitxerID);
			byte[] contingut = FileUtils.readFromFile(file);

			DocumentoAutorizacion docAut = new DocumentoAutorizacion();
			// AUT - FORMULARIO AUTORIZACION: FicherFirmart09.pdf (124562 bytes)
			log.info("AUT - " + tipo + ": " + nom + " (" + contingut.length + " bytes)");

			docAut.setNombre(nom);
			docAut.setDescripcion(descripcio);
			docAut.setTipo(tipo);
			docAut.setContenido(contingut);

			docs.getDocumentoAutorizacion().add(docAut);
		}
		return docs;
	}

	private Servicios getServicios(SolicitudJPA soli) throws Exception {

		Servicios servicios = new Servicios();
		Set<SolicitudServeiJPA> serveisDeLaSolicitud = soli.getSolicitudServeis();

		int MAX_NORMES_SERVEI = 3;
		int serveisPerAfegir = 0;
		int serveisAfegits = 0;

		for (SolicitudServeiJPA ss : serveisDeLaSolicitud) {
			boolean balear = ss.getServei().getEntitatServei().isBalears();
			boolean estatPendentMadrid = ss
					.getEstatSolicitudServeiID() == Constants.ESTAT_SOLICITUD_SERVEI_PENDENT_AUTORITZAR;
			estatPendentMadrid |= ss.getEstatSolicitudServeiID() == Constants.ESTAT_SOLICITUD_SERVEI_REBUT;

			//ja que alta també s'utilitza per fer subsanacions, pot haver serveis autoritzats que s'hagin de tornar a enviar
			estatPendentMadrid = true;
			serveisPerAfegir++;
			if (!balear && estatPendentMadrid) {

				Servicio servicio = new Servicio();
				Normas normas = new Normas();

				for (int i = 0; i < MAX_NORMES_SERVEI; i++) {
					String normaLegal = null;
					String articulosNorma = null;
					Long fitxerNormaID = null;
					FitxerJPA fitxer = null;
					String enlace = null;

					switch (i) {
					case 0:
						normaLegal = ss.getNormaLegal();
						articulosNorma = ss.getArticles();
						fitxerNormaID = ss.getFitxernormaID();
						fitxer = ss.getFitxernorma();
						enlace = ss.getEnllazNormaLegal();

						break;
					case 1:
						normaLegal = ss.getNorma2();
						articulosNorma = ss.getArticles2();
						fitxerNormaID = ss.getFitxernorma2ID();
						fitxer = ss.getFitxernorma2();
						break;
					case 2:
						normaLegal = ss.getNorma3();
						articulosNorma = ss.getArticles3();
						fitxerNormaID = ss.getFitxernorma3ID();
						fitxer = ss.getFitxernorma3();
						break;
					}

					// Si no hay normaLegal, o no enlace, no se añade la norma
					if (normaLegal == null || normaLegal.trim().length() == 0) {
						if (enlace == null || enlace.trim().length() == 0) {
							log.info("No hi ha norma legal ni enllaç " + i + ". No s'afegirà la norma " + i + ".");
							continue;
						}
					}

					Norma norma = new Norma();
					Norma.Documento docNorma = new Norma.Documento();

					String nom = null;
					String descripcio = null;
					byte[] contingut = null;

					log.info("PRE-NORMA " + i + " - " + normaLegal + ": " + fitxerNormaID + " - " + enlace);
					if (fitxerNormaID != null) {
						File normaFile = FileSystemManager.getFile(fitxerNormaID);
//						FitxerJPA fitxer = fitxerLogicEjb.findByPrimaryKey(fitxerNormaID);

						nom = fitxer.getNom();
						contingut = FileUtils.readFromFile(normaFile);

					} else if (enlace != null && enlace.trim().length() > 0) {
						boolean debug = false;
						FileInfo normaFileInfo = PdfDownloader.downloadPDFFromBoeBoibUrl(enlace, debug);

						if (normaFileInfo != null) {
							nom = normaFileInfo.getFileName();
							contingut = normaFileInfo.getContent();
						}
					}

					if (contingut == null) {
						log.info("No s'ha pogut obtenir el fitxer de la norma. No s'afegirà la norma.");
						continue;
					}

					log.info("POST-NORMA " + i + " - " + normaLegal + ": " + nom + " (" + contingut.length + " bytes)");
					descripcio = "Norma Legal " + i + " - " + ss.getServei().getNom();
					// descripcio = "Norma del servicio: " + ss.getServei().getNom();

					docNorma.setNombre(nom);
					docNorma.setDescripcion(descripcio);
					docNorma.setContenido(contingut);
					docNorma.setEnlace(enlace);

					String[] articulosArray = articulosNorma.split(",");

					Articulos articulos = new Articulos();

					for (String articulo : articulosArray) {
						articulos.getArticulo().add(articulo);
					}

					norma.setNormaLegal(normaLegal);
					norma.setDocumento(docNorma);
					norma.setArticulos(articulos);

					normas.getNorma().add(norma);
				}

				if (normas.getNorma().size() == 0) {
                    log.info("No s'ha pogut afegir cap norma al servei " + ss.getServei().getCodi());
                    continue;
				}
				
				String codigoCertificado = ss.getServei().getCodi();

				servicio.setCodigoCertificado(codigoCertificado);
				servicio.setNormas(normas);

				servicios.getServicio().add(servicio);
				serveisAfegits++;
			}
		}
		
		// Si no hay servicios para añadir
		if (serveisPerAfegir == 0) {
			log.info("No hi ha serveis per afegir.");
		}else {
			if (serveisAfegits == 0) {
				log.info("No s'han afegit serveis a la solicitud. Problemes amb normes o fitxers.");
			}else {
                log.info("S'han afegit " + serveisAfegits + " serveis a la solicitud.");
			}
		}
		
		return servicios;
	}

}
