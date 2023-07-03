package org.fundaciobit.pinbaladmin.apiclientpeticions.test;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;

import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Contacto;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Contactos;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Incidencia;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Procedimiento;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Solicitud;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsConfiguration;
import org.junit.Test;

/**
 * 
 * @author anadal
 *
 */
public class TestPinbalAdminFormulari {

    public static void main(String[] args) {

        new TestPinbalAdminFormulari().test();

    }

    @Test
    public void test() {

        try {

            // Titular
            ScspTitular titular = new ScspTitular();

            final String apellido1 = "JAUME";
            final String apellido2 = "";
            final String documentacion = "41107605G";
            final String nombre = "";

            // ScspTitular.ScspTipoDocumentacion.DNI, ScspTitular.ScspTipoDocumentacion.NIE
            ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.DNI;

            titular.setTipoDocumentacion(tipoDocumentacion);
            titular.setDocumentacion(documentacion);
            titular.setNombre(nombre);
            titular.setApellido1(apellido1);
            titular.setApellido2(apellido2);

            // Mateix Titular
            final ScspFuncionario funcionario = new ScspFuncionario();
            funcionario.setNifFuncionario(documentacion);
            funcionario.setNombreCompletoFuncionario(nombre + " " + apellido1);

            Solicitud solicitud = generarSolicitudIncidencia();

            PinbalAdminSolicitudsApi api = new PinbalAdminSolicitudsApi(getPinbalAdminSolicitudsConfiguration());

            Incidencia in = api.crearSolicitud(solicitud, titular, funcionario);

            System.out.println("Email: " + in.getEmail());

            System.out.println("NumeroIncidencia: " + in.getNumeroIncidencia());

            System.out.println("getNumeroSeguimiento: " + in.getNumeroSeguimiento());

        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 
     * @return
     * @throws Exception
     */
    protected PinbalAdminSolicitudsConfiguration getPinbalAdminSolicitudsConfiguration() throws Exception {

        PinbalAdminSolicitudsConfiguration config = new PinbalAdminSolicitudsConfiguration();
        
        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("test.properties")));
        

        config.setUrlBase(prop.getProperty("urlbase")); 
        config.setUsername(prop.getProperty("username"));
        config.setPassword(prop.getProperty("password"));

        config.setFinalidad("Baremacions per el proces d'escolaritzacio"); // getPropertyRequired(propertyBase + "finalitat"); // "Baremacions per el proces d'escolaritzacio";
        config.setIdentificadorSolicitante("S0711001H"); // getPropertyRequired(propertyBase + "identificadorsolicitant"); // "S0711001H";
        config.setUnidadTramitadora("Servei d'escolarització"); // getPropertyRequired(propertyBase + "unitattramitadora"); // "Servei d'escolarització";

        config.setCodProcedimiento("INCIDENCIES_CAID"); // getPropertyRequired(propertyBase + "codiprocediment"); // "CODSVDR_GBA_20121107";

        
        
        // TODO crec que això hauria d'estar dins de PinbalAdminSolicitudsApi
        /**
         * SVDPIDSOLAUTWS01 | Solicitud de autorizaciones en PID
        SVDPIDESTADOAUTWS01 | Servicio de estado de las autorizaciones en PID 
        SVDPIDACTPROCWS01 | Servicio de actualización de un procedimiento ya dado de alta en PID
         */
        config.setCodigoCertificado("SVDPIDSOLAUTWS01");

        return config;

    }

    protected Solicitud generarSolicitudIncidencia() {
        es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Solicitud soliIncidencia;
        soliIncidencia = new es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Solicitud();

        soliIncidencia.setAsunto("Prova d'Enviament");
        soliIncidencia.setConsulta("Adjunto archivo para soluicitud e bla bla bla");
        {
            Contacto contacto = new Contacto();
            contacto.setNombre("Pilar");
            contacto.setApellido1("Vico");
            contacto.setApellido2("Huertas");
            contacto.setEmail("pvico");

            Contactos contactos = new Contactos();
            contactos.getContacto().add(contacto);

            soliIncidencia.setContactos(contactos);
        }

        {
            Procedimiento proc = new Procedimiento();

            proc.setAutomatizado("si");

            // XYZ ZZZ Falta

            soliIncidencia.setProcedimiento(proc);
        }

        return soliIncidencia;
    }

}