package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import java.util.Base64;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaEJB.TipusCridada;
import org.fundaciobit.pinbaladmin.logic.utils.FileInfo;
import org.fundaciobit.pinbaladmin.logic.utils.PdfDownloader;
import org.fundaciobit.pinbaladmin.model.entity.EntitatServei;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Articulos;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.AutorizacionDelegada;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Consentimiento;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contacto;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contactos;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DocumentoAutorizacion;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DocumentosAutorizacion;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Norma;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Normas;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Procedimiento;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Servicio;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Servicios;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud;

@Stateless(name = "PinbalUtilsModificacioLogicaEJB")
public class PinbalUtilsModificacioLogicaEJB extends PinbalUtilsCommon implements PinbalUtilsModificacioLogicaService {

	public PinbalUtilsModificacioLogicaEJB() {
		// Constructor vacío
	}

	@Override
	public Respuesta modificacioSolicitudApiPinbal(ScspTitular titular, ScspFuncionario funcionario,
			Solicitud solicitud, String CIF) throws Exception {

		PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(
				getPinbalAdminSolicitudsConfiguration(TipusCridada.MODIFICACIO, CIF));
		Respuesta respuesta = api.modificacioSolicitudPinbalApi(solicitud, titular, funcionario);

		return respuesta;
	}

	@Override
	public Solicitud getDadesSolicitudApiPinbalMod(SolicitudJPA soli) throws Exception {

		Solicitud solicitud = new Solicitud();

		Contactos contactos = getContactos();
		solicitud.setContactos(contactos);

		AutorizacionDelegada autDelegada = getAutorizacionDelegada(soli);
	//	solicitud.setAutorizacionDelegada(autDelegada);

		Procedimiento proc = getProcedimiento(soli);
		solicitud.setProcedimiento(proc);
		return solicitud;
	}

	// ACTUALIZAR SOLICITUD Y COMPLETAR INFOMADRID.
	@Override
	public void processarRespostaPinbalModificacio(org.fundaciobit.pinbaladmin.model.entity.Solicitud solicitud,
			Respuesta resposta, ScspTitular titular, ScspFuncionario funcionario, InfoMadridJPA infoMadrid)
			throws Exception {

		final String ESTAT_REGISTRADA_OK = "0";
		final String ESTAT_NO_REGISTRADA = "1";
		final String ESTAT_REGISTRADA_SUBSANAR = "2";
		final String ESTAT_VALIDACION_KO = "0228";

		String codiEstat = resposta.getEstado().getCodigoEstado();
		String descripcioEstat = resposta.getEstado().getDescripcion();

		log.info("Resposta MODIFICACIÓ: codi=" + codiEstat + ", descripció=" + descripcioEstat);

		Long estatSoli;
		Long estatAuth;
		String respostaMadrid;

		switch (codiEstat) {
		case ESTAT_REGISTRADA_OK:

			estatSoli = Constants.SOLI_ESTAT_PENDENT_AUTORITZAR;
			estatAuth = Constants.ESTAT_PINBAL_PENDENT_TRAMITAR;

			solicitud.setEstatSolicitud(estatSoli);
			solicitud.setEstatpinbal(estatAuth);

			respostaMadrid = "Modificació Enviada a Madrid correctament: " + descripcioEstat;

			afegirEventSolicitudEnviada(solicitud, respostaMadrid, "Modificació");
			break;

		case ESTAT_REGISTRADA_SUBSANAR:
		case ESTAT_NO_REGISTRADA:
		case ESTAT_VALIDACION_KO:

			String msg = "Errores: ";

			if (resposta.getErrores() != null) {
				for (es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Error error : resposta
						.getErrores().getError()) {
					msg += "\n " + error.getDescripcion() + " (Error " + error.getCodigo() + ")";
				}
			} else {
				log.error("Errores debería no ser null");
			}

			if (codiEstat.equals(ESTAT_VALIDACION_KO)) {
				// Si es un problema de validació, la podem desestimar directament.

				estatSoli = Constants.SOLI_ESTAT_ESMENES;
				estatAuth = Constants.ESTAT_PINBAL_DESESTIMAT;
				infoMadrid.setEstatAutoritzacio(Constants.SOLI_ESTAT_ESMENA_PENDENT_CONTACTE);
			} else {
				// Si es qualsevol altre error. Marcam com error

				estatSoli = Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID;
				estatAuth = Constants.ESTAT_PINBAL_ERROR;
			}

			respostaMadrid = msg;

			if (estatSoli.equals(Constants.SOLI_ESTAT_ESMENES)) {
				avisarContacteSolicitudDesestimada((SolicitudJPA) solicitud, respostaMadrid, "MODIFICACIÓ");
			}
			solicitud.setEstatSolicitud(estatSoli);

			break;

		default:
			log.warn("Codi d'estat no controlat: " + codiEstat);

			estatSoli = Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID;
			estatAuth = Constants.ESTAT_PINBAL_ERROR;

			solicitud.setEstatpinbal(estatAuth);
			solicitud.setEstatSolicitud(estatSoli);

			respostaMadrid = "Error no controlat: " + "Codi d'estat no controlat: " + codiEstat;
			break;
		}

		actualizarInfoMadrid(solicitud, infoMadrid, estatSoli, estatAuth, respostaMadrid);
	}

	private Contactos getContactos() {

		InfoContacte pilar = new InfoContacte();

		Contactos contactos = new Contactos();

		String Ape1 = pilar.getApe1();
		String Ape2 = pilar.getApe2();
		String Mail = pilar.getMail();
		String Nombre = pilar.getNom();
		String Telefon = pilar.getTelefon();

		Contacto contactoPinbalAdmin = createContacto(Ape1, Ape2, Mail, Nombre, Telefon);
		contactos.getContacto().add(contactoPinbalAdmin);

		return contactos;
	}

	private Contacto createContacto(String contactoApe1, String contactoApe2, String contactoMail,
			String contactoNombre, String contactoTelefono) {

		if (validarDatosContacto(contactoApe1, contactoApe2, contactoMail, contactoNombre, contactoTelefono)) {
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

	public AutorizacionDelegada getAutorizacionDelegada(SolicitudJPA soli) throws Exception {

		AutorizacionDelegada autDelegada = new AutorizacionDelegada();

		byte[] consentimentBytes = getCertificadoX509();
		autDelegada.setCertificadoX509(consentimentBytes);

//		return autDelegada;
		return null;
	}

	public Procedimiento getProcedimiento(SolicitudJPA soli) throws Exception {

		String _Automatizado = "N";// soli.getAutomatizado();
		String _Periodico = "N"; // soli.getPeriodico();

		String petsDia = "40"; // soli.getPetsDia();
		Integer _PeticionesEstimadas = Integer.parseInt(petsDia);

		String tipusProc = soli.getProcedimentTipus();
		Integer _ClaseTramite = getIdentificadorNuevoPorId(tipusProc);

		String _Codigo = soli.getProcedimentCodi();
		String _Nombre = adaptarNomProcediment(soli.getProcedimentNom());

		String _Descripcion = soli.getCodiDescriptiu();
		if (_Nombre.equals(_Descripcion)) {
			_Descripcion = "_" + _Descripcion;
		}
		String _Observaciones = null;// soli.getNotes();

		XMLGregorianCalendar _FechaCaducidad = convertirDateAXMLGregorianCalendar(soli.getDataCaducitat());

		// Aquí es el PDF Firmado del DG y cualquier PDF adicional.
		Set<DocAuthInfo> docsAuth = getDocumentsAuth(soli);

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
		Consentimiento _Consentimiento = getConsentimientoFromSoli(soli);

		proc.setDocumentosAutorizacion(_DocumentosAutorizacion);
		proc.setConsentimiento(_Consentimiento);

		if (_DocumentosAutorizacion == null || _Consentimiento == null) {
			return proc;
		}

		Servicios _Servicios = getServicios(soli);
		proc.setServicios(_Servicios);

		return proc;
	}

	private Consentimiento getConsentimientoFromSoli(SolicitudJPA soli) throws Exception {
		Consentimiento cons = new Consentimiento();

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

		String nom;
		String descripcio;
		byte[] contingut;

		try {
			Long consentimentID = soli.getFitxerConsentimentID();

			if (consentimentID != null) {
				Fitxer fitxerConsentiment = fitxerLogicEjb.findByPrimaryKey(consentimentID);

				contingut = obtenerContenidoFitxer(consentimentID);
				nom = fitxerConsentiment.getNom();
				String desc = fitxerConsentiment.getDescripcio();

				descripcio = "Fitxer de consentiment. IDFitxer: " + consentimentID + ".";
				if (desc != null && desc.trim().length() != 0) {
					descripcio += "<br> Descripció: " + desc;

				}
			} else {
				String enlace = soli.getUrlconsentiment();
				if (enlace != null && enlace.trim().length() != 0) {
					cons.setEnlace(enlace);

					FileInfo fileInfo = PdfDownloader.downloadPDFFromBoeBoibUrl(enlace, false);

					nom = fileInfo.getFileName();
					descripcio = "Fitxer de consentiment. Enllaç: " + enlace;
					contingut = fileInfo.getContent();
				} else {
					throw new Exception("Falta el document de consentiment.");
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

		if (contingut != null) {
			System.out.println("Tamaño del contenido (bytes): " + contingut.length);
			System.out.println("Base64 length: " + Base64.getEncoder().encodeToString(contingut).length());
		}
		doc.setContenido(contingut);

		cons.setDocumento(doc);
		return cons;
	}

	private DocumentosAutorizacion getDocsAutorizacion(Set<DocAuthInfo> documents) throws Exception {

		log.info("Documents de la solicitud: " + documents.size());

		if (documents.size() == 0) {
			log.info("No hi ha documents d'autorització per enviar a Madrid.");
			throw new Exception("No hi ha documents d'autorització per enviar a Madrid.");
		}

		DocumentosAutorizacion docs = new DocumentosAutorizacion();

		for (DocAuthInfo docInfo : documents) {
			FitxerJPA fitxer = docInfo.getFitxer();

			String nom = fitxer.getNom();
			String descripcio = docInfo.getDescripcio();
			String tipo = docInfo.getTipo();

			Long fitxerID = fitxer.getFitxerID();
			byte[] contingut = obtenerContenidoFitxer(fitxerID);

			DocumentoAutorizacion docAut = new DocumentoAutorizacion();
			log.info("AUT - " + tipo + ": " + nom + " (" + contingut.length + " bytes)");

			docAut.setNombre(nom);
			docAut.setDescripcion(descripcio);
			docAut.setTipo(tipo);
			docAut.setContenido(contingut);

			docs.getDocumentoAutorizacion().add(docAut);

			if (contingut != null) {
				System.out.println("Tamaño del contenido (bytes): " + contingut.length);
				System.out.println("Base64 length: " + Base64.getEncoder().encodeToString(contingut).length());
			}
		}
		return docs;
	}

	private Servicios getServicios(SolicitudJPA soli) throws Exception {

		Servicios servicios = new Servicios();
		List<SolicitudServei> serveisDeLaSolicitud = solicitudServeiLogicaEjb
				.select(SolicitudServeiFields.SOLICITUDID.equal(soli.getSolicitudID()));

		int serveisPerAfegir = 0;
		int serveisAfegits = 0;

		for (SolicitudServei ss : serveisDeLaSolicitud) {
			Servei servei = serveiLogicaEjb.findByPrimaryKey(ss.getServeiID());

			EntitatServei es = entitatServeiLogicEjb.findByPrimaryKey(servei.getEntitatServeiID());
			boolean balear = es.isBalears();

			boolean estatPendentMadrid = ss
					.getEstatSolicitudServeiID() == Constants.ESTAT_SOLICITUD_SERVEI_PENDENT_AUTORITZAR;
			estatPendentMadrid |= ss.getEstatSolicitudServeiID() == Constants.ESTAT_SOLICITUD_SERVEI_REBUT;

			// ja que alta també s'utilitza per fer subsanacions, pot haver serveis
			// autoritzats que s'hagin de tornar a enviar
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

						nom = fitxer.getNom();
						contingut = obtenerContenidoFitxer(fitxerNormaID);

					} else if (enlace != null && enlace.trim().length() > 0) {
						boolean debug = false;
						FileInfo normaFileInfo = null;
						try {
							normaFileInfo = PdfDownloader.downloadPDFFromBoeBoibUrl(enlace, debug);
						} catch (Throwable t) {
							log.error("No hem aconseguit el fitxer de la url '" + enlace + "': " + t.getMessage());
						}

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
					descripcio = "Norma Legal " + i + " - Servei: " + servei.getNom();

					docNorma.setNombre(servei.getCodi() + "_" + nom);
					docNorma.setDescripcion(descripcio);

					if (contingut != null) {
						System.out.println("Tamaño del contenido (bytes): " + contingut.length);
						System.out.println("Base64 length: " + Base64.getEncoder().encodeToString(contingut).length());
					}
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
					log.info("No s'ha pogut afegir cap norma al servei " + servei.getCodi());
					continue;
				}

				String codigoCertificado = servei.getCodi();

				servicio.setCodigoCertificado(codigoCertificado);
				servicio.setNormas(normas);

				servicios.getServicio().add(servicio);
				serveisAfegits++;
			}
		}

		// Si no hay servicios para añadir
		if (serveisPerAfegir == 0) {
			log.info("No hi ha serveis per afegir.");
		} else {
			if (serveisAfegits == 0) {
				log.info("No s'han afegit serveis a la solicitud. Problemes amb normes o fitxers.");
			} else {
				log.info("S'han afegit " + serveisAfegits + " serveis a la solicitud.");
			}
		}

		return servicios;
	}
}
