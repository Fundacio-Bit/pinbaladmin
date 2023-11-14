package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.security.LoginInfo;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Consentimiento;
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
        String returnUrl = SolicitudFullViewOperadorController.CONTEXTWEB + "/view/" + soliID;

        SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);
        
        List<String> errors = new ArrayList<String>();
//        
//        if (soli.getEstatID() != Constants.SOLICITUD_ESTAT_AUTORITZAT
//                && soli.getEstatID() != Constants.SOLICITUD_ESTAT_TANCAT) {
//            errors.add("L'estat es " + SolicitudOperadorController.ESTATS_SOLICITUD.get(soli.getEstatID()));
//        }

        if (soli.getDataFi() != null && soli.getDataFi().before(new Timestamp(System.currentTimeMillis()))) {
            errors.add("La data de caducitat ha de ser posterior a avui");
        }
        
        String errorBase = "Error: No es pot donar d'alta la solicitud: ";
        if (errors.size() > 0) {
            for (String error : errors) {
                HtmlUtils.saveMessageWarning(request, errorBase + error);
            }
            return new ModelAndView(new RedirectView(returnUrl, true));
        }
        
        try {
            Long fitxerID = soli.getSolicitudXmlID();

            String contenidoXml = obtenerContenidoXml(fitxerID);
            Properties prop = ParserFormulariXML.getPropertiesFromFormulario(contenidoXml);

            ScspTitular titular = getTitular(prop);
            ScspFuncionario funcionario = getFuncionari();
            
            Solicitud solicitud = solicitudLogicaEjb.getDadesSolicitudApiPinbal(soliID, prop);

            request.getSession().setAttribute("titular", titular);
            request.getSession().setAttribute("funcionario", funcionario);
            request.getSession().setAttribute("solicitud", solicitud);

            request.getSession().setAttribute(RETURN_URL, returnUrl);

            ModelAndView mav = new ModelAndView("altasolicitudpinbal");

            mav.addObject("contexte", getContextWeb());

            mav.addObject("titular", titular);
            mav.addObject("funcionario", funcionario);
            mav.addObject("solicitud", solicitud);

//            HtmlUtils.saveMessageSuccess(request, "Dades obtingudes correctament");
            return mav;

        } catch (Exception e) {
            HtmlUtils.saveMessageError(request, e.getMessage());
            log.error(e.getMessage(), e);
            return new ModelAndView(new RedirectView(returnUrl, true));
        }
    }

    @RequestMapping(value = "/altasolicitud", method = RequestMethod.POST)
    public String altaSolicitud(HttpServletRequest request, HttpServletResponse response, @RequestParam("consentiment-file") MultipartFile fitxerConsentiment ) {

        String consulta = request.getParameter("consulta");

        ScspTitular titular = (ScspTitular) request.getSession().getAttribute("titular");
        ScspFuncionario funcionario = (ScspFuncionario) request.getSession().getAttribute("funcionario");
        Solicitud solicitud = (Solicitud) request.getSession().getAttribute("solicitud");

        solicitud.setConsulta(consulta);
        log.info("consulta: " + solicitud.getConsulta());

        try {
            if (fitxerConsentiment.getSize() > 0 ) {
                Consentimiento.Documento docConsentimiento = new Consentimiento.Documento();

                String nombre = fitxerConsentiment.getOriginalFilename();
                byte[] contenido = fitxerConsentiment.getBytes(); 
                String desc = "Fitxer consentiment: " + nombre + ". " + contenido.length + " bytes";

                log.info(desc);

                docConsentimiento.setNombre(nombre);
                docConsentimiento.setDescripcion(desc);
                docConsentimiento.setContenido(contenido);
                
                solicitud.getProcedimiento().getConsentimiento().setDocumento(docConsentimiento);
            }
           
            Respuesta resposta = solicitudLogicaEjb.altaSolicitudApiPinbal(titular, funcionario, solicitud);

            if (resposta.getErrores() == null) {
                System.out.println(" # Errors: 0");

                String mensaje = resposta.getEstado().getDescripcion();
//                Incidencia in = resposta.getIncidencia();
                HtmlUtils.saveMessageSuccess(request, "Ha anat be: " + mensaje);

            } else {
                for (es.caib.scsp.esquemas.SVDSCTFNWS01v3.peticion.datosespecificos.Error error : resposta.getErrores()
                        .getError()) {
                    String errorMsg = "PINBAL: " + error.getDescripcion() + " (Error " + error.getCodigo() + ")";
                    HtmlUtils.saveMessageError(request, errorMsg);
                }
            }
        } catch (Exception e) {
            HtmlUtils.saveMessageError(request, "Error fent la cridada a la API de PINBAL: " + e.getMessage());
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
        return new String(xmlData, StandardCharsets.UTF_8 );
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

    private ScspFuncionario getFuncionari() {

        ScspFuncionario funcionario = new ScspFuncionario();

        UserInfo ui = LoginInfo.getInstance().getUserInfo();
        
        String nif = ui.getAdministrationID();
        String fullName = ui.getFullName();

        funcionario.setNifFuncionario(nif);
        funcionario.setNombreCompletoFuncionario(fullName);
        return funcionario;
    }
}
