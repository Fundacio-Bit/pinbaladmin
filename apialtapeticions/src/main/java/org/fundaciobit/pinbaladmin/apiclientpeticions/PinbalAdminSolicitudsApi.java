//package org.fundaciobit.pinbaladmin.apiclientpeticions;
//
//import java.io.StringReader;
//import java.io.StringWriter;
//import java.util.List;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
//
//import org.apache.log4j.Logger;
//
//import es.caib.pinbal.client.recobriment.ClientGeneric;
//import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
//import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
//import es.caib.pinbal.client.recobriment.model.ScspSolicitante.ScspConsentimiento;
//import es.caib.pinbal.client.recobriment.model.ScspTitular;
//import es.caib.pinbal.client.recobriment.model.Solicitud;
//
//public class PinbalAdminSolicitudsApi {
//
//	private final Logger log = Logger.getLogger(this.getClass());
//	private final PinbalAdminSolicitudsConfiguration configuracio;
//
//	public PinbalAdminSolicitudsApi(PinbalAdminSolicitudsConfiguration configuracio) {
//		this.configuracio = configuracio;
//	}
//
//	public <T, R> R procesarSolicitud(T datosEspecificos, ScspTitular titular, ScspFuncionario funcionario,
//			Class<T> datosEspecificosClass, Class<R> respuestaClass, Solicitud solicitud) throws Exception {
//		configurarSolicitud(solicitud, titular, funcionario);
//		ScspRespuesta resposta = cridadaSincronaPINBAL(solicitud);
//
//		String datosEspecificosXml = parseDatosEspecificosXML(resposta.getTransmisiones().get(0).getDatosEspecificos());
//		JAXBContext contexto = JAXBContext.newInstance(datosEspecificosClass);
//		Unmarshaller unmarshaller = contexto.createUnmarshaller();
//		T datosEspecificosResponse = datosEspecificosClass
//				.cast(unmarshaller.unmarshal(new StringReader(datosEspecificosXml)));
//
//		if (respuestaClass.isInstance(datosEspecificosResponse)) {
//			return respuestaClass.cast(datosEspecificosResponse);
//		} else {
//			throw new IllegalArgumentException("La respuesta no es del tipo esperado: " + respuestaClass.getName());
//		}
//	}
//
//	public es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta altaSolicitudPinbalApi(
//			es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud soliIncidencia, ScspTitular titular,
//			ScspFuncionario funcionario) throws Exception {
//
//		var datosEspecificos = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DatosEspecificos();
//		datosEspecificos.setSolicitud(soliIncidencia);
//		Solicitud solicitud = new SolicitudPinbal(datosEspecificos);
//
//		return procesarSolicitud(datosEspecificos, titular, funcionario,
//				es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DatosEspecificos.class,
//				es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta.class, solicitud);
//	}
//
//	public es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno consultaEstatPinbalApi(
//			es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta consulta, ScspTitular titular,
//			ScspFuncionario funcionario) throws Exception {
//
//		var datosEspecificos = new es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.DatosEspecificos();
//		datosEspecificos.setConsulta(consulta);
//		Solicitud solicitud = new SolicitudPinbal(datosEspecificos);
//
//		return procesarSolicitud(datosEspecificos, titular, funcionario,
//				es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.DatosEspecificos.class,
//				es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno.class, solicitud);
//	}
//
//	public es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta modificacioSolicitudPinbalApi(
//			es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud soliIncidencia,
//			ScspTitular titular, ScspFuncionario funcionario) throws Exception {
//
//		var datosEspecificos = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DatosEspecificos();
//		datosEspecificos.setSolicitud(soliIncidencia);
//		Solicitud solicitud = new SolicitudPinbal(datosEspecificos);
//
//		return procesarSolicitud(datosEspecificos, titular, funcionario,
//				es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DatosEspecificos.class,
//				es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta.class, solicitud);
//	}
//
//	private void configurarSolicitud(Solicitud solicitud, ScspTitular titular, ScspFuncionario funcionario) {
//		solicitud.setIdentificadorSolicitante(configuracio.getIdentificadorSolicitante());
//		solicitud.setUnidadTramitadora(configuracio.getUnidadTramitadora());
//		solicitud.setCodigoProcedimiento(configuracio.getCodProcedimiento());
//		solicitud.setFinalidad(configuracio.getFinalidad());
//		solicitud.setConsentimiento(ScspConsentimiento.Ley);
//		solicitud.setFuncionario(funcionario);
//		solicitud.setTitular(titular);
//	}
//
//	private String parseDatosEspecificosXML(String datosEspecificos) {
//		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
//				+ datosEspecificos.replace("DatosEspecificos", "datosEspecificos").replace("<datosEspecificos>",
//						"<datosEspecificos xmlns=\"http://intermediacion.redsara.es/scsp/esquemas/datosespecificos\">");
//	}
//
//	private ScspRespuesta cridadaSincronaPINBAL(Solicitud solicitud) throws Exception {
//      ScspRespuesta resposta;
//
//      final String baseUrl = this.configuracio.getUrlBase();
//      final String username = this.configuracio.getUsername();
//      final String password = this.configuracio.getPassword();
//
//      final String codigoCertificado = this.configuracio.getCodigoCertificado();
//
//      ClientGeneric clientRest = new ClientGeneric(baseUrl, username, password);
//
//      resposta = clientRest.peticionSincrona(codigoCertificado, List.of(solicitud));
//      return resposta;
//  }
//	
//	
//	protected class SolicitudPinbal extends Solicitud {
//
//		private final Object datosEspecificos;
//
//		public SolicitudPinbal(Object datosEspecificos) {
//			this.datosEspecificos = datosEspecificos;
//		}
//
//		@Override
//		public String getDatosEspecificos() {
//			StringWriter sw = new StringWriter();
//			try {
//				JAXBContext contexto = JAXBContext.newInstance(datosEspecificos.getClass());
//				Marshaller marshaller = contexto.createMarshaller();
//				marshaller.marshal(datosEspecificos, sw);
//				return sw.toString();
//			} catch (JAXBException e) {
//				log.error(e.getMessage(), e);
//				throw new RuntimeException(e);
//			}
//		}
//	}
//}
package org.fundaciobit.pinbaladmin.apiclientpeticions;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import es.caib.pinbal.client.recobriment.ClientGeneric;
import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante.ScspConsentimiento;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.Solicitud;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;

/**
 * 
 * @author anadal
 *
 */
public class PinbalAdminSolicitudsApi {

    protected Logger log = Logger.getLogger(this.getClass());

    final PinbalAdminSolicitudsConfiguration configuracio;

    public PinbalAdminSolicitudsApi(PinbalAdminSolicitudsConfiguration configuracio) {
        super();
        this.configuracio = configuracio;
    }

    public es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta altaSolicitudPinbalApi(
            es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud soliIncidencia,
            ScspTitular titular, ScspFuncionario funcionario) throws Exception {

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DatosEspecificos de = new es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DatosEspecificos();
        de.setSolicitud(soliIncidencia);

        SolicitudPinbalAdminAlta solicitud = new SolicitudPinbalAdminAlta(de);

        {
            solicitud.setIdentificadorSolicitante(this.configuracio.getIdentificadorSolicitante());
            solicitud.setUnidadTramitadora(this.configuracio.getUnidadTramitadora());
            solicitud.setCodigoProcedimiento(this.configuracio.getCodProcedimiento());
            solicitud.setFinalidad(this.configuracio.getFinalidad());
            solicitud.setConsentimiento(ScspConsentimiento.Ley);
            solicitud.setFuncionario(funcionario);
            solicitud.setTitular(titular);
        }

        /*
         * Petició a PINBAL i processament de la resposta XML
         */
        ScspRespuesta resposta = cridadaSincronaPINBAL(solicitud);

        String datosEspecificos = resposta.getTransmisiones().get(0).getDatosEspecificos();
        datosEspecificos = parseDatosEspecificosXML(datosEspecificos);

        JAXBContext contexto = JAXBContext
                .newInstance(es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DatosEspecificos.class);

        Unmarshaller datosEspecificosItem = contexto.createUnmarshaller();

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DatosEspecificos dte;
        dte = (es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DatosEspecificos) datosEspecificosItem
                .unmarshal(new StringReader(datosEspecificos));

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta respuesta = dte.getRespuesta();

        return respuesta;
    }

    public Retorno consultaEstatPinbalApi(Consulta consulta, ScspTitular titular, ScspFuncionario funcionario)
            throws Exception {

        es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.DatosEspecificos de = new es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.DatosEspecificos();
        de.setConsulta(consulta);

        SolicitudPinbalAdminConsulta solicitud = new SolicitudPinbalAdminConsulta(de);
        {
            solicitud.setIdentificadorSolicitante(this.configuracio.getIdentificadorSolicitante());
            solicitud.setUnidadTramitadora(this.configuracio.getUnidadTramitadora());
            solicitud.setCodigoProcedimiento(this.configuracio.getCodProcedimiento());
            solicitud.setFinalidad(this.configuracio.getFinalidad());
            solicitud.setConsentimiento(ScspConsentimiento.Ley);
            solicitud.setFuncionario(funcionario);
            solicitud.setTitular(titular);
        }

        /*
         * Petició a PINBAL i processament de la resposta XML
         */
        ScspRespuesta resposta = cridadaSincronaPINBAL(solicitud);

        String datosEspecificos = resposta.getTransmisiones().get(0).getDatosEspecificos();
        datosEspecificos = parseDatosEspecificosXML(datosEspecificos);

        JAXBContext contexto = JAXBContext.newInstance(
                es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.DatosEspecificos.class);

        Unmarshaller datosEspecificosItem = contexto.createUnmarshaller();

        es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.DatosEspecificos dte;
        dte = (es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.DatosEspecificos) datosEspecificosItem
                .unmarshal(new StringReader(datosEspecificos));

        Retorno retorno = dte.getRetorno();
        return retorno;
    }

    public es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta modificacioSolicitudPinbalApi(
            es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud soliIncidencia,
            ScspTitular titular, ScspFuncionario funcionario) throws Exception {

        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DatosEspecificos de = new es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DatosEspecificos();
        de.setSolicitud(soliIncidencia);

        SolicitudPinbalAdminModificacio solicitud = new SolicitudPinbalAdminModificacio(de);

        {
            solicitud.setIdentificadorSolicitante(this.configuracio.getIdentificadorSolicitante());
            solicitud.setUnidadTramitadora(this.configuracio.getUnidadTramitadora());
            solicitud.setCodigoProcedimiento(this.configuracio.getCodProcedimiento());
            solicitud.setFinalidad(this.configuracio.getFinalidad());
            solicitud.setConsentimiento(ScspConsentimiento.Ley);
            solicitud.setFuncionario(funcionario);
            solicitud.setTitular(titular);
        }

        /*
         * Petició a PINBAL i processament de la resposta XML
         */
        ScspRespuesta resposta = cridadaSincronaPINBAL(solicitud);

        String datosEspecificos = resposta.getTransmisiones().get(0).getDatosEspecificos();
        datosEspecificos = parseDatosEspecificosXML(datosEspecificos);

        JAXBContext contexto = JAXBContext
                .newInstance(es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DatosEspecificos.class);

        Unmarshaller datosEspecificosItem = contexto.createUnmarshaller();

        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DatosEspecificos dte;
        dte = (es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DatosEspecificos) datosEspecificosItem
                .unmarshal(new StringReader(datosEspecificos));

        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta respuesta = dte.getRespuesta();

        return respuesta;
    }
    
    private String parseDatosEspecificosXML(String datosEspecificos) {
        datosEspecificos = datosEspecificos.replace("DatosEspecificos", "datosEspecificos");
        datosEspecificos = datosEspecificos.replace("<datosEspecificos>",
                "<datosEspecificos xmlns=\"http://intermediacion.redsara.es/scsp/esquemas/datosespecificos\">");
        datosEspecificos = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + datosEspecificos;

//        System.out.println(datosEspecificos);

        return datosEspecificos;
    }

    /**
     * // CONNEXIÓ 
     * @param solicituds
     * @return
     * @throws Exception
     */
    protected ScspRespuesta cridadaSincronaPINBAL(Solicitud solicitud) throws Exception {

        ScspRespuesta resposta;

        final String baseUrl = this.configuracio.getUrlBase();
        final String username = this.configuracio.getUsername();
        final String password = this.configuracio.getPassword();

        final String codigoCertificado = this.configuracio.getCodigoCertificado();

        
        log.info("CODIGO CERTIFICAFDO: " + codigoCertificado);
        
        ClientGeneric clientRest = new ClientGeneric(baseUrl, username, password);

        resposta = clientRest.peticionSincrona(codigoCertificado, List.of(solicitud));
        return resposta;
    }


    protected class SolicitudPinbalAdminAlta extends Solicitud {

        final es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DatosEspecificos datosEspecificos;

        public SolicitudPinbalAdminAlta(
                es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.DatosEspecificos datosEspecificos) {
            super();
            this.datosEspecificos = datosEspecificos;
        }

        @Override
        public String getDatosEspecificos() { // xml

            StringWriter sw = new StringWriter();
            try {

                JAXBContext contexto = JAXBContext.newInstance(this.datosEspecificos.getClass());

                Marshaller marshaller = contexto.createMarshaller();

                marshaller.marshal(this.datosEspecificos, sw);

                return sw.toString();

            } catch (JAXBException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

    protected class SolicitudPinbalAdminConsulta extends Solicitud {

        final es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.DatosEspecificos datosEspecificos;

        public SolicitudPinbalAdminConsulta(
                es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.DatosEspecificos datosEspecificos) {
            super();
            this.datosEspecificos = datosEspecificos;
        }

        @Override
        public String getDatosEspecificos() { // xml

            StringWriter sw = new StringWriter();
            try {

                JAXBContext contexto = JAXBContext.newInstance(this.datosEspecificos.getClass());

                Marshaller marshaller = contexto.createMarshaller();

                marshaller.marshal(this.datosEspecificos, sw);

                return sw.toString();

            } catch (JAXBException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }
    
    protected class SolicitudPinbalAdminModificacio extends Solicitud {

        final es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DatosEspecificos datosEspecificos;

        public SolicitudPinbalAdminModificacio(
                es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.DatosEspecificos datosEspecificos) {
            super();
            this.datosEspecificos = datosEspecificos;
        }

        @Override
        public String getDatosEspecificos() { // xml

            StringWriter sw = new StringWriter();
            try {

                JAXBContext contexto = JAXBContext.newInstance(this.datosEspecificos.getClass());

                Marshaller marshaller = contexto.createMarshaller();

                marshaller.marshal(this.datosEspecificos, sw);

                return sw.toString();

            } catch (JAXBException e) {
                log.error(e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }
    }

}
