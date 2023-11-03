package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.File;
import java.util.Base64;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Incidencia;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Respuesta;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Solicitud;

/**
 * 
 * @author ptrias
 *
 */

@Controller
@RequestMapping(value = "/operador/altapinbal")
public class AltaSolicitudPinbalOperadorController {

    protected static final Logger log = Logger.getLogger(AltaSolicitudPinbalOperadorController.class);

    private static final String RETURN_URL = "returnUrl";

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @RequestMapping(value = "/vistaprevia/{soliID}", method = RequestMethod.GET)
    public ModelAndView vistaPrevia(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long soliID) {

        log.info("Entra a vistaprevia amb soliID = " + soliID);

        SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);
        Long fitxerID = soli.getSolicitudXmlID();

        try {
            String contenidoXml = obtenerContenidoXml(fitxerID);
            Properties prop = ParserFormulariXML.getPropertiesFromFormulario(contenidoXml);

            ScspTitular titular = getTitular(prop);
            ScspFuncionario funcionario = getFuncionari(prop, "ptrias");
            Solicitud solicitud = solicitudLogicaEjb.getDadesSolicitudApiPinbal(soliID, prop);

            request.getSession().setAttribute("titular", titular);
            request.getSession().setAttribute("funcionario", funcionario);
            request.getSession().setAttribute("solicitud", solicitud);

            request.getSession().setAttribute(RETURN_URL,
                    SolicitudFullViewOperadorController.CONTEXTWEB + "/view/" + soliID);

            ModelAndView mav = new ModelAndView("altasolicitudpinbal");

            mav.addObject("contexte", getContextWeb());

            mav.addObject("titular", titular);
            mav.addObject("funcionario", funcionario);
            mav.addObject("solicitud", solicitud);

            HtmlUtils.saveMessageSuccess(request, "Dades obtingudes correctament");
            return mav;

        } catch (Exception e) {
            HtmlUtils.saveMessageError(request, e.getMessage());
            //XYZ TODO Ha de tornar a la vista full de la solicitud
            return new ModelAndView(new RedirectView("/operador/solicitudlocal/list", true));
        }
    }

    @RequestMapping(value = "/altasolicitud", method = RequestMethod.POST)
    public String altaSolicitud(HttpServletRequest request, HttpServletResponse response) {

        String consulta = request.getParameter("consulta");

        ScspTitular titular = (ScspTitular) request.getSession().getAttribute("titular");
        ScspFuncionario funcionario = (ScspFuncionario) request.getSession().getAttribute("funcionario");
        Solicitud solicitud = (Solicitud) request.getSession().getAttribute("solicitud");

        solicitud.setConsulta(consulta);
        log.info("consulta: " + solicitud.getConsulta());

        try {
            Respuesta resposta = solicitudLogicaEjb.altaSolicitudApiPinbal(titular, funcionario, solicitud);

            if (resposta.getErrores() == null) {
                System.out.println(" # Errors: 0");

                Incidencia in = resposta.getIncidencia();
                HtmlUtils.saveMessageSuccess(request, "Ha anat be. NumIncidencia: " + in.getNumeroIncidencia());

            } else {
                for (es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Error error : resposta.getErrores()
                        .getError()) {
                    HtmlUtils.saveMessageError(request,
                            "PINBAL: " + error.getDescripcion() + " (Error " + error.getCodigo() + ")");
                }

            }
        } catch (Exception e) {
            HtmlUtils.saveMessageError(request, "Error fent la cridada a la API de PINBAL" + e.getMessage());
        }

        String returnUrl = (String) request.getSession().getAttribute(RETURN_URL);
        log.info("returnUrl :" + returnUrl);
        return "redirect:" + returnUrl;
    }

    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        return rm.value()[0];
    }

    private String obtenerContenidoXml(Long fitxerID) throws Exception {
        File f = FileSystemManager.getFile(fitxerID);
        byte[] xmlData = FileUtils.readFromFile(f);
        return new String(xmlData);
    }

    private ScspTitular getTitular(Properties prop) {

        ScspTitular titular = new ScspTitular();

        ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.NIF;
        String documentacion = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NIFSECE");
        String nombre = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBRESECE");
        String ape1 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE1SECE");
        String ape2 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE2SECE");
        String fullName = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMOCULSECE");

        titular.setTipoDocumentacion(tipoDocumentacion);
        titular.setDocumentacion(documentacion);
        titular.setNombre(nombre);
        titular.setApellido1(ape1);
        titular.setApellido2(ape2);
        titular.setNombreCompleto(fullName);

        return titular;
    }

    private ScspFuncionario getFuncionari(Properties prop, String username) {

        ScspFuncionario funcionario = new ScspFuncionario();

        String nif;
        String fullName;
        //        UserInfo info = LoginInfo.getInstance().getUserInfo();

        switch (username) {
            case "pvico":
                nif = "";
                fullName = "María Pilar Vico Hervas";
            break;
            case "ptrias":
                nif = "45186147W";
                fullName = "Juan Pablo Trias Segura";
            break;
            default:
                nif = prop.getProperty("FORMULARIO.DATOS_REGISTRO.NIF");
                fullName = prop.getProperty("FORMULARIO.DATOS_REGISTRO.NOMBRECOMPLETO");
            break;
        }

        funcionario.setNifFuncionario(nif);
        funcionario.setNombreCompletoFuncionario(fullName);
        return funcionario;
    }
}
