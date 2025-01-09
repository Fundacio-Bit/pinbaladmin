package org.fundaciobit.pinbaladmin.logic.utils.pinbalutils;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;

import org.apache.log4j.Logger;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsConfiguration;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaEJB.TipusCridada;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;

public class PinbalUtilsCommon {

	final static Logger log = Logger.getLogger(PinbalUtilsCommon.class);

	@EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
	protected FitxerPublicLogicaService fitxerLogicEjb;

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

	public PinbalAdminSolicitudsConfiguration getPinbalAdminSolicitudsConfiguration(TipusCridada tipus)
			throws Exception {

		PinbalAdminSolicitudsConfiguration config = new PinbalAdminSolicitudsConfiguration();

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
		int idTipoProcedimiento = TipusProcediments.getIdentificadorTipoProcedimiento(tipoProcedimiento);

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

}
