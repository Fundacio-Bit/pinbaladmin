package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsConfiguration;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaEJB.TipusCridada;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Contacto;

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

	public PinbalAdminSolicitudsConfiguration getPinbalAdminSolicitudsConfiguration(TipusCridada tipus)
			throws Exception {

		PinbalAdminSolicitudsConfiguration config = new PinbalAdminSolicitudsConfiguration();

		String url = Configuracio.getApiPinbalUrl();
		String username = Configuracio.getApiPinbalUsername();
		String password = Configuracio.getApiPinbalPassword();

		// log.info("URL: ]" + url + "[ Username: ]" + username + "[ Password: ]" +
		// password + "[");

		config.setUrlBase(Configuracio.getApiPinbalUrl());
		config.setUsername(Configuracio.getApiPinbalUsername());
		config.setPassword(Configuracio.getApiPinbalPassword());

		config.setFinalidad("Solicitar autorización procedimiento");
		config.setIdentificadorSolicitante("S0711001H");
		config.setUnidadTramitadora("Fundacio BIT");

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
}
