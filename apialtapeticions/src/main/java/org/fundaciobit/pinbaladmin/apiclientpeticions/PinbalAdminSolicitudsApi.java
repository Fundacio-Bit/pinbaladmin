package org.fundaciobit.pinbaladmin.apiclientpeticions;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.pinbal.client.recobriment.ClientGeneric;
import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspRespuesta;
import es.caib.pinbal.client.recobriment.model.ScspSolicitante.ScspConsentimiento;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.Solicitud;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.DatosEspecificos;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Incidencia;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Respuesta;

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

    /**
     * 
     * @param soliIncidencia
     * @param titular
     * @param funcionario
     * @return
     * @throws Exception
     */
    public Respuesta crearSolicitud(
            es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Solicitud soliIncidencia,
            ScspTitular titular, ScspFuncionario funcionario) throws Exception {

        DatosEspecificos de = new DatosEspecificos();
        de.setSolicitud(soliIncidencia);

        SolicitudPinbalAdmin solicitud = new SolicitudPinbalAdmin(de);

        {
            solicitud.setIdentificadorSolicitante(this.configuracio.getIdentificadorSolicitante());
            solicitud.setUnidadTramitadora(this.configuracio.getUnidadTramitadora());
            solicitud.setCodigoProcedimiento(this.configuracio.getCodProcedimiento());
            solicitud.setFinalidad(this.configuracio.getFinalidad());
            solicitud.setConsentimiento(ScspConsentimiento.Si);
            solicitud.setFuncionario(funcionario);
            solicitud.setTitular(titular);
        }

        ScspRespuesta resposta;

        /*
         * Petició a PINBAL i processament de la resposta XML
         */

        resposta = getConnexio(List.of(solicitud));

        String datosEspecificos = resposta.getTransmisiones().get(0).getDatosEspecificos();
        
        datosEspecificos = datosEspecificos.replace("DatosEspecificos", "datosEspecificos");
        datosEspecificos = datosEspecificos.replace("<datosEspecificos>",
                "<datosEspecificos xmlns=\"http://intermediacion.redsara.es/scsp/esquemas/datosespecificos\">");
        datosEspecificos = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + datosEspecificos;

        System.out.println(datosEspecificos);
        JAXBContext contexto = JAXBContext.newInstance(DatosEspecificos.class);

        Unmarshaller datosEspecificosItem = contexto.createUnmarshaller();

        DatosEspecificos dte = (DatosEspecificos) datosEspecificosItem.unmarshal(new StringReader(datosEspecificos));

        Respuesta respuesta = dte.getRespuesta();

        String estado = respuesta.getEstado().getCodigoEstado();
        System.out.println(" # Estado: " + estado);

        return respuesta;
        
//        if (respuesta.getErrores() == null) {
//            System.out.println(" # Errors: 0");
//
//            Incidencia in = respuesta.getIncidencia();
//
////            log.info("Email: " + in.getEmail());
////            log.info("NumeroIncidencia: " + in.getNumeroIncidencia());
////            log.info("getNumeroSeguimiento: " + in.getNumeroSeguimiento());
//
//            return in;
//
//        } else {
//            
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//            ObjectWriter prettyWriter = objectMapper.writerWithDefaultPrettyPrinter();
//            String errorJson = prettyWriter.writeValueAsString(respuesta);
//            
////            String errorMsg = " # Errors: " + respuesta.getErrores().getError().size();
////
////            for (es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Error error : respuesta.getErrores()
////                    .getError()) {
////                errorMsg += "\t" + error.getCodigo() + ": " + error.getDescripcion();
////            }
////
////            String error = "Error amb codi d'estat " + estado + " i descripció "
////                    + respuesta.getEstado().getDescripcion();
//
//            Exception ex = new Exception(errorJson);
//            log.error(errorJson, ex);
//
//            throw ex;
//        }

    }

    /**
     * // CONNEXIÓ 
     * @param solicituds
     * @return
     * @throws Exception
     */
    protected ScspRespuesta getConnexio(List<Solicitud> solicituds) throws Exception {

        ScspRespuesta resposta;

        final String baseUrl = this.configuracio.getUrlBase();
        final String username = this.configuracio.getUsername();
        final String password = this.configuracio.getPassword();

        final String codigoCertificado = this.configuracio.getCodigoCertificado();

        ClientGeneric clientRest = new ClientGeneric(baseUrl, username, password);

        resposta = clientRest.peticionSincrona(codigoCertificado, solicituds);

        return resposta;
    }

    /**
     * 
     * @author anadal
     *
     */
    protected static class SolicitudPinbalAdmin extends Solicitud {

        protected Logger log = Logger.getLogger(this.getClass());

        final DatosEspecificos datosEspecificos;

        public SolicitudPinbalAdmin(DatosEspecificos datosEspecificos) {
            super();
            this.datosEspecificos = datosEspecificos;
        }

        @Override
        public String getDatosEspecificos() { // xml

            StringWriter sw = new StringWriter();
            try {

                JAXBContext contexto = JAXBContext.newInstance(DatosEspecificos.class);

                Marshaller marshaller = contexto.createMarshaller();

                marshaller.marshal(this.datosEspecificos, sw);

                return sw.toString();

            } catch (JAXBException e) {
                // TODO Auto-generated catch block
                log.error(e.getMessage(), e);

                throw new RuntimeException(e);
            }
        }
    }

}
