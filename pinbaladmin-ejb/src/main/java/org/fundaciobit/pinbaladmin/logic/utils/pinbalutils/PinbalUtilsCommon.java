package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import java.io.File;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsConfiguration;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.DocumentLogicaService;
import org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.EntitatLogicaService;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaEJB.TipusCridada;
import org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsCommon.DocAuthInfo;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.EntitatJPA;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;

public abstract class PinbalUtilsCommon {

	final static Logger log = Logger.getLogger(PinbalUtilsCommon.class);

//	public final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerLogicEjb;

	@EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
	protected SolicitudServeiLogicaService solicitudServeiLogicaEjb;

	@EJB(mappedName = ServeiLogicaService.JNDI_NAME)
	protected ServeiLogicaService serveiLogicaEjb;

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;

	@EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
	protected InfoMadridLogicaService infoMadridLogicaEjb;

	@EJB(mappedName = EventLogicaService.JNDI_NAME)
	protected EventLogicaService eventLogicaEjb;

	@EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
	protected DocumentSolicitudLogicaService documentSolicitudLogicaEjb;

	@EJB(mappedName = org.fundaciobit.pinbaladmin.logic.EntitatServeiLogicService.JNDI_NAME)
	protected org.fundaciobit.pinbaladmin.logic.EntitatServeiLogicService entitatServeiLogicEjb;

	@EJB(mappedName = DocumentLogicaService.JNDI_NAME)
	protected DocumentLogicaService documentLogicaEjb;

	@EJB(mappedName = PinbalUtilsConsultaLogicaService.JNDI_NAME)
	protected PinbalUtilsConsultaLogicaService pinbalConsultaLogicaEjb;
	
	@EJB(mappedName = EntitatLogicaService.JNDI_NAME)
	protected EntitatLogicaService entitatLogicaEjb;

	final String PINBAL_CONSENTIMENT_LLEI = "Ley";
	final String PINBAL_CONSENTIMENT_SI = "Si";
	final String PINBAL_CONSENTIMENT_NOOP = "NoOpo";
	
	final int MAX_NORMES_SERVEI = 3;

	public PinbalUtilsCommon() {
	}

	public class DocAuthInfo {

		private FitxerJPA fitxer;
		private String descripcio;
		private String tipo;

		public FitxerJPA getFitxer() {
			return fitxer;
		}

		public String getDescripcio() {
			return descripcio;
		}

		public String getTipo() {
			return tipo;
		}

		public DocAuthInfo(FitxerJPA fitxer, String descripcio, String tipo) {
			super();
			this.fitxer = fitxer;
			this.descripcio = descripcio;
			this.tipo = tipo;
		}
	}

	public class InfoContacte {
		private String nom;
		private String ape1;
		private String ape2;
		private String mail;
		private String telefon;

		public InfoContacte() {
			this.ape1 = "Pilar";
			this.ape2 = "Vico";
			this.mail = "pinbal@fundaciobit.org";
			this.nom = "Govern Digital - Fundació BIT";
			this.telefon = "971176529";
		}

		public String getNom() {
			return nom;
		}

		public String getApe1() {
			return ape1;
		}

		public String getApe2() {
			return ape2;
		}

		public String getMail() {
			return mail;
		}

		public String getTelefon() {
			return telefon;
		}

	}

	public PinbalAdminSolicitudsConfiguration getPinbalAdminSolicitudsConfiguration(TipusCridada tipus, String cif)
			throws Exception {

		PinbalAdminSolicitudsConfiguration config = new PinbalAdminSolicitudsConfiguration();

		config.setUrlBase(Configuracio.getApiPinbalUrl());
		config.setUsername(Configuracio.getApiPinbalUsername());
		config.setPassword(Configuracio.getApiPinbalPassword());

		config.setFinalidad("Solicitar autorización procedimiento");
		
		Entitat entitat = entitatLogicaEjb.findByCif(cif);
		
		log.info("Configurant cridada Pinbal per a l'entitat: " + entitat.getNom() + " - CIF: " + entitat.getCIF());
		config.setIdentificadorSolicitante(entitat.getCIF());
		config.setUnidadTramitadora(entitat.getNom());

		config.setCodProcedimiento(Configuracio.getApiPinbalCodiProcediment());

		/**
		 * SVDPIDSOLAUTWS01 | Solicitud de autorizaciones en PID SVDPIDESTADOAUTWS01 |
		 * Servicio de estado de las autorizaciones en PID SVDPIDACTPROCWS01 | Servicio
		 * de actualización de un procedimiento ya dado de alta en PID
		 */

		String codigoCertificado;
		switch (tipus) {
		case ALTA:
			codigoCertificado = "SVDPIDSOLAUTWS01";
			break;
		case CONSULTA:
			codigoCertificado = "SVDPIDESTADOAUTWS01";
			break;
		case MODIFICACIO:
			codigoCertificado = "SVDPIDACTPROCWS01";
			break;
		default:
			throw new Exception("El tipus de cridada no es conegut: ]" + tipus.toString() + "[");
		}

		config.setCodigoCertificado(codigoCertificado);

		return config;
	}

	public int getIdentificadorNuevoPorId(String tipoProcedimiento) {
		// tipoProcedimiento es un numero en string "5"

		int idTipoProcedimiento = Integer.valueOf(tipoProcedimiento); // TipusProcediments.getIdentificadorTipoProcedimiento(tipoProcedimiento);

		// Mapeo de identificadores en la lista actual a identificadores en la nueva
		// lista
		Map<Integer, Integer> mapeoIdentificadores = new HashMap<>();

		// Mapeo de identificadores en la lista actual a identificadores en la nueva
		// lista
		mapeoIdentificadores.put(1, 34); // Aduanero
		mapeoIdentificadores.put(2, 19); // Afiliación y cotización a la Seguridad Social
		mapeoIdentificadores.put(3, 20); // Autorizaciones, licencias, concesiones y homologaciones
		mapeoIdentificadores.put(4, 21); // Ayudas, Becas y Subvenciones
		mapeoIdentificadores.put(5, 22); // Certificados
		mapeoIdentificadores.put(6, 23); // Contratación pública
		mapeoIdentificadores.put(7, 24); // Convenios de Colaboración y Comunicaciones administrativas
		mapeoIdentificadores.put(8, 25); // Gestión Económica y Patrimonial
		mapeoIdentificadores.put(9, 26); // Declaraciones y comunicaciones de los interesados
		mapeoIdentificadores.put(10, 27); // Inspectora
		mapeoIdentificadores.put(11, 28); // Premios
		mapeoIdentificadores.put(12, 29); // Prestaciones
		mapeoIdentificadores.put(13, 2); // Recursos Humanos
		mapeoIdentificadores.put(14, 30); // Registros y Censos
		mapeoIdentificadores.put(15, 31); // Responsabilidad patrimonial y otras solicitudes de indemnización
		mapeoIdentificadores.put(16, 32); // Revisión de Actos administrativos y Recursos
		mapeoIdentificadores.put(17, 14); // Sancionador
		mapeoIdentificadores.put(18, 33); // Sugerencias, Quejas, Denuncias e Información a los ciudadanos
		mapeoIdentificadores.put(19, 3); // Tributario

		// Busca el identificador en el nuevo mapeo
		Integer identificadorNuevo = mapeoIdentificadores.get(idTipoProcedimiento);

		// Si se encuentra, devuelve el identificador en la nueva lista
		if (identificadorNuevo != null) {
			return identificadorNuevo;
		}

		// Si no se encuentra se devolverá 0 (Pruebas) para indicar que no se encontró
		// ningún mapeo correspondiente en la nueva lista.
		return 0;
	}

	public String generarMissatgeEsmena(SolicitudJPA solicitud, String respostaMadrid) {

//		InfoMadridJPA infoMad = infoMadridLogicaEjb.findByPrimaryKey(solicitud.getInfomadridid());

//		String respostaMadrid = infoMad.getMissatge();
		respostaMadrid = respostaMadrid.replace("\n", "<br>");
//		String missatge = "Bon dia, <br> desde el Ministeri ens han DESESTIMAT la solicitud amb codi "
//				+ solicitud.getProcedimentCodi() + " .<br>" + "El missatge rebut és el següent: <br><br><i>" + respostaMadrid
//				+ "</i><br><br>" + "Per poder tramitar aquesta esmena, si us plau, accedeixi al següent enllaç: "
//				+ "<a href='" + generarUrlEsmena(solicitud) + "'>" + "esmenar solicitud</a>" + "<br><br>" + 
//				"Salutacions.";

		String missatge = "<div style=\"margin: .5rem;\">" +

				"Bon dia,<br>" + "<br>"
				+ "Des de la Plataforma Estatal d'Interoperabilitat (PID) han DESESTIMAT la sol·liciud d'autorització del procediment amb codi "
				+ solicitud.getProcedimentCodi() + " . <br>" + "<br>" + "Ens indiquen el següent motiu:" +

				"<div style=\"margin: 1rem 2rem;font-style: italic;\">" + respostaMadrid + "</div>" +

				"Per poder tramitar aquesta esmena, podeu accedir al segënt enllaç:<br>" +

				"<button style=\"background-color: #4CAF50; /* Green */\n" + "  border: none;\n" + "  color: white;\n"
				+ "  padding: 12px 24px;\n" +

				" font-size: 16px;\n" + "  margin: 1rem 2rem;\n" + "  border-radius: 4px;\n" + "  cursor: pointer;\">\n"
				+ "<a href='" + generarUrlEsmena(solicitud)
				+ "' style=\"color: white; text-decoration: none;\">Esmenar solicitud</a>\n" +

				"</button>" +

				"</div>";

//		String missatge = "La seva sol·licitud amb codi " + solicitud.getProcedimentCodi()
//				+ " necessita esmenes. Per tramitar aquesta esmena, si us plau, accedeixi al següent enllaç: "
//				+ "<a href='" + generarUrlEsmena(solicitud) + "'>" + "esmenar solicitud</a>";

		return missatge;
	}

	private String generarUrlEsmena(SolicitudJPA solicitud) {

		// http://ptrias:8080/pinbaladmin/public/esmenarSolicitud/tramitEsmena/39990

		String url = Configuracio.getAppBackUrl() + "/public/esmenarSolicitud/tramitEsmena/"
				+ solicitud.getSolicitudID();

		return url;
	}

	public void enviarMissatgeAlSolicitant(SolicitudJPA solicitud, String asumpte, String missatge)
			throws I18NException {
		final Timestamp data = new Timestamp(System.currentTimeMillis());
		final String caidIdentificadorConsulta = null;
		final String caidNumeroSeguiment = null;

		Long _fitxerID_ = null;
		boolean _noLlegit_ = false;

		EventJPA event = new EventJPA();
		event.setSolicitudID(solicitud.getSolicitudID());
		event.setIncidenciaTecnicaID(null);
		event.setDataEvent(data);
		event.setTipus(Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC);
		event.setFitxerID(_fitxerID_);
		event.setNoLlegit(_noLlegit_);
		event.setCaidIdentificadorConsulta(caidIdentificadorConsulta);
		event.setCaidNumeroSeguiment(caidNumeroSeguiment);

		event.setPersona("PinbalAdmin");
		event.setAsumpte(asumpte);
		event.setComentari(missatge);

		// Es un comentari de contacte, no te destinatari.
		event.setDestinatari(solicitud.getPersonaContacte());
		event.setDestinatarimail(solicitud.getPersonaContacteEmail());

		eventLogicaEjb.create(event);
	}

	public String adaptarNomProcedimentOld(String nom) {
		// Pasarlo a maysculas y quitar acentos.

		String nomAdaptat = nom.toUpperCase();
		nomAdaptat = nomAdaptat.replace("À", "A");
		nomAdaptat = nomAdaptat.replace("È", "E");
		nomAdaptat = nomAdaptat.replace("É", "E");
		nomAdaptat = nomAdaptat.replace("Í", "I");
		nomAdaptat = nomAdaptat.replace("Ó", "O");
		nomAdaptat = nomAdaptat.replace("Ò", "O");
		nomAdaptat = nomAdaptat.replace("Ú", "U");
		nomAdaptat = nomAdaptat.replace("Ü", "U");
		nomAdaptat = nomAdaptat.replace("Ç", "C");
		return nomAdaptat;

	}

	public String adaptarNomProcediment(String nom) {
		if (nom == null)
			return null;

		// Pasar a mayúsculas
		String nomAdaptat = nom.toUpperCase();

		// Quitar acentos y diacríticos
		nomAdaptat = Normalizer.normalize(nomAdaptat, Normalizer.Form.NFD);
		nomAdaptat = nomAdaptat.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

		// Reemplazar la ç manualmente (no la quita el normalizer)
		nomAdaptat = nomAdaptat.replace("Ç", "C");

		return nomAdaptat;
	}

	public byte[] getCertificadoX509() {

		String certificado = "MIIB8TCCAZegAwIBAgIUJ7s5b0e4a5EKeosFVYpY8R4jtv8wCgYIKoZIzj0EAwIwEzERMA8GA1UEAwwIVGVzdENlcnQwHhcNMjUxMjAxMTAwMDAwWhcNMjYxMjAxMTAwMDAwWjATMREwDwYDVQQDDAhUZXN0Q2VydDBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABMX0cS5Fmw7fq0g3MzZ0MsnMd+2C7IeEhuIAH4JlTT1kkp97JQnWWhY1uCEkDp3NzVP8Lzz0JcTsPv4f9NqqWFSjUzBRMB0GA1UdDgQWBBTFo9GhlZJAew34r5TjAmdxhqS93TAfBgNVHSMEGDAWgBTFo9GhlZJAew34r5TjAmdxhqS93TAPBgNVHRMBAf8EBTADAQH/MAoGCCqGSM49BAMCA0kAMEYCIQCjQGcr/5Znd7XK9LdJxgC5rmUtBk8oun3nH0D7mYYTIwIhAIpw1o6v9EYawcy+HUildGL9TEZ17KJTudYbuvVKCBKt";

		byte[] consentimentBytes = certificado.getBytes();
//		return consentimentBytes;
		return null;
	}	
	
	/**
	 * Valida que los datos de contacto sean completos
	 */
	protected boolean validarDatosContacto(String contactoApe1, String contactoApe2, String contactoMail,
			String contactoNombre, String contactoTelefono) {
		return contactoApe1 != null && contactoMail != null && contactoNombre != null && contactoTelefono != null;
	}

	
	/**
	 * Convierte un Date a XMLGregorianCalendar para la fecha de caducidad
	 */
	protected XMLGregorianCalendar convertirDateAXMLGregorianCalendar(Date dataCaducitat) throws Exception {
		XMLGregorianCalendar _FechaCaducidad = null;

		if (dataCaducitat != null) {
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(dataCaducitat);
			_FechaCaducidad = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gc.get(GregorianCalendar.YEAR),
					gc.get(GregorianCalendar.MONTH) + 1, gc.get(GregorianCalendar.DAY_OF_MONTH),
					DatatypeConstants.FIELD_UNDEFINED // sin timezone
			);
		}

		return _FechaCaducidad;
	}
	
	public Set<DocAuthInfo> getDocumentsAuth(SolicitudJPA soli) throws I18NException {
		Set<DocAuthInfo> docsAuth = new HashSet<DocAuthInfo>();

		List<DocumentSolicitud> listDocumentsSolicitud = documentSolicitudLogicaEjb
				.select(DocumentSolicitudFields.SOLICITUDID.equal(soli.getSolicitudID()));

		for (DocumentSolicitud docSoli : listDocumentsSolicitud) {
			Document document = documentLogicaEjb.findByPrimaryKey(docSoli.getDocumentID());

			Long tipus = document.getTipus();

			if (tipus == Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF) {
				FitxerJPA fitxer = fitxerLogicEjb.findByPrimaryKey(document.getFitxerFirmatID());
				if (fitxer != null) {
					String desc = "Formulari PDF firmat per el director";
					String tipo = "FORMULARIO DE AUTORIZACION";
					docsAuth.add(new DocAuthInfo(fitxer, desc, tipo));
				} else {
					log.info("Fa falta el formulari firmat per el DG");
				}

			} else if (tipus == Constants.DOCUMENT_SOLICITUD_EXCEL_SERVEIS) {
				// No se envía

			} else {
				FitxerJPA original = (FitxerJPA) fitxerLogicEjb.findByPrimaryKey(document.getFitxerOriginalID());
				if (original.getMime().equals("application/pdf")) {
					FitxerJPA fitxer = original;
					String desc = "Fitxer PDF associat al procediment";
					String tipo = "FORMULARIO DE AUTORIZACION";
					docsAuth.add(new DocAuthInfo(fitxer, desc, tipo));
				}
			}
		}
		return docsAuth;
	}

	/**
	 * Obtiene el contenido de un fichero por su ID
	 */
	protected byte[] obtenerContenidoFitxer(Long fitxerID) throws Exception {
		File file = FileSystemManager.getFile(fitxerID);
		return FileUtils.readFromFile(file);
	}

	/**
	 * Crea una descripción para una norma legal
	 */
	protected String crearDescripcionNorma(int numeroNorma, String nombreServei) {
		return "Norma Legal " + numeroNorma + " - Servei: " + nombreServei;
	}

	/**
	 * Actualiza la información de Madrid para una solicitud
	 */
	protected void actualizarInfoMadrid(org.fundaciobit.pinbaladmin.model.entity.Solicitud soli,
			InfoMadridJPA infoMadJpa, Long estadoSoli, Long estadoAuth, String respuesta) throws I18NException {

		Long infoMadridIDOld = soli.getInfomadridid();
		if (infoMadridIDOld != null) {
			InfoMadrid infoMadridOld = infoMadridLogicaEjb.findByPrimaryKey(infoMadridIDOld);
			infoMadJpa.setIntents(infoMadridOld.getIntents() + 1);
		}

		infoMadJpa.setEstatProcediment(estadoSoli);
		infoMadJpa.setEstatAutoritzacio(estadoAuth);
		infoMadJpa.setMissatge(respuesta);

		InfoMadrid infoMad = infoMadridLogicaEjb.create(infoMadJpa);
		Long infoMadID = infoMad.getInfoMadridID();

		soli.setInfomadridid(infoMadID);
		solicitudLogicaEjb.update(soli);
	}

	/**
	 * Añade un evento de solicitud enviada a Madrid
	 */
	protected void afegirEventSolicitudEnviada(org.fundaciobit.pinbaladmin.model.entity.Solicitud soli, String mensaje,
			String tipusOperacio) {

		final Timestamp data = new Timestamp(System.currentTimeMillis());
		int tipus = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
		String persona = "pinbaladmin - " + soli.getOperador();
		String subject = tipusOperacio + " enviada a MADRID. " + soli.getProcedimentCodi();
		String msg = "S'ha enviat la " + tipusOperacio.toLowerCase() + " a MADRID. " + mensaje;

		EventJPA event = new EventJPA();
		event.setSolicitudID(soli.getSolicitudID());
		event.setIncidenciaTecnicaID(null);
		event.setDataEvent(data);
		event.setTipus(tipus);
		event.setPersona(persona);
		event.setDestinatari(null);
		event.setDestinatarimail(null);
		event.setAsumpte(subject);
		event.setComentari(msg);
		event.setFitxerID(null);
		event.setNoLlegit(true);
		event.setCaidIdentificadorConsulta(null);
		event.setCaidNumeroSeguiment(null);

		try {
			eventLogicaEjb.create(event);
		} catch (I18NException e) {
			log.error("No s'ha pogut crear l'event de " + tipusOperacio.toLowerCase() + " enviada: " + e.getMessage(),
					e);
		}
	}

	/**
	 * Avisa al contacto de una solicitud desestimada que requiere esmenas
	 */
	protected void avisarContacteSolicitudDesestimada(SolicitudJPA solicitud, String respostaMadrid,
			String tipusProces) {
		String asumpte = "PROCÉS " + tipusProces + " PROCEDIMENT " + solicitud.getProcedimentCodi()
				+ ". Requereix esmenes.";
		String missatge = generarMissatgeEsmena(solicitud, respostaMadrid);

		try {
			enviarMissatgeAlSolicitant(solicitud, asumpte, missatge);
		} catch (I18NException e) {
			log.error("Error enviant missatge al sol·licitant: " + e.getMessage(), e);
		}
	}

}
