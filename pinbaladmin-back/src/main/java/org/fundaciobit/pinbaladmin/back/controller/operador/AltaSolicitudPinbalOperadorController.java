package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.File;
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
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;

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

    @RequestMapping(value = "/vistaprevia/{tipus}/{soliID}", method = RequestMethod.GET)
    public ModelAndView vistaPrevia(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String tipus, @PathVariable Long soliID) {

        log.info("Entra a vistaprevia amb soliID = " + soliID);
        String returnUrl = SolicitudFullViewOperadorController.CONTEXTWEB + "/view/" + soliID;

        try {
            SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);

            List<String> errors = new ArrayList<String>();

            if (soli.getDataFi() != null && soli.getDataFi().before(new Timestamp(System.currentTimeMillis()))) {
                errors.add("La data de caducitat ha de ser posterior a avui");
            }

            Long fitxerID = soli.getSolicitudXmlID();
            Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);

            ScspTitular titular = getTitular(prop);
            ScspFuncionario funcionario = getFuncionari();

            request.getSession().setAttribute("titular", titular);
            request.getSession().setAttribute("funcionario", funcionario);

            request.getSession().setAttribute(RETURN_URL, returnUrl);

            ModelAndView mav;
            if (tipus.equals("alta")) {
                es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud solicitudA = solicitudLogicaEjb
                        .getDadesAltaSolicitudApiPinbal(soliID, prop);

                if (solicitudA.getProcedimiento().getConsentimiento() == null) {
                    errors.add("Fa falta un document de consentiment");
                }

                if (solicitudA.getProcedimiento().getDocumentosAutorizacion() == null) {
                    errors.add("Fa falta un document d'autorització");
                }

                if (solicitudA.getProcedimiento().getServicios().getServicio().size() == 0) {
                    errors.add("No hi ha serveis pendents d'autoritzar");
                }
                
                request.getSession().setAttribute("solicitud", solicitudA);
                mav = new ModelAndView("altasolicitudpinbal");
                mav.addObject("solicitud", solicitudA);
            } else {
                es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud solicitudM = solicitudLogicaEjb
                        .getDadesModificarSolicitudApiPinbal(soliID, prop);
                
                if (solicitudM.getProcedimiento().getServicios().getServicio().size() == 0) {
                    errors.add("No hi ha serveis pendents d'autoritzar");
                }

                request.getSession().setAttribute("solicitud", solicitudM);
                mav = new ModelAndView("modificaciosolicitudpinbal");
                mav.addObject("solicitud", solicitudM);
            }

            String errorBase = "Error: No es pot donar d'alta la solicitud: ";
            if (errors.size() > 0) {
                for (String error : errors) {
                    HtmlUtils.saveMessageWarning(request, errorBase + error);
                }
                return new ModelAndView(new RedirectView(returnUrl, true));
            }

            mav.addObject("contexte", getContextWeb());
            mav.addObject("titular", titular);
            mav.addObject("funcionario", funcionario);
            mav.addObject("soliID", soliID);

            //            HtmlUtils.saveMessageSuccess(request, "Dades obtingudes correctament");
            return mav;

        } catch (Exception e) {
            HtmlUtils.saveMessageError(request, e.getMessage());
            log.error(e.getMessage(), e);
            return new ModelAndView(new RedirectView(returnUrl, true));
        }
    }

    @RequestMapping(value = "/altasolicitud", method = RequestMethod.POST)
    public String altaSolicitud(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("soliID") Long soliID) {

        String consulta = request.getParameter("consulta");

        ScspTitular titular = (ScspTitular) request.getSession().getAttribute("titular");
        ScspFuncionario funcionario = (ScspFuncionario) request.getSession().getAttribute("funcionario");
        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud solicitud = (es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud) request
                .getSession().getAttribute("solicitud");

        solicitud.setConsulta(consulta);
        log.info("consulta: " + solicitud.getConsulta());

        try {
            es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta resposta = solicitudLogicaEjb
                    .altaSolicitudApiPinbal(titular, funcionario, solicitud);

            if (resposta.getErrores() == null) {
                System.out.println(" # Errors: 0");

                //Si ha anat be, i hem afegit fitxer manualment, afegir-ho als documents d'una sol.licitud

                String mensaje = resposta.getEstado().getDescripcion();
                HtmlUtils.saveMessageSuccess(request, "Ha anat be: " + mensaje);

                log.info("Actualitzam solicitud amb ID= " + soliID);

                solicitudLogicaEjb.update(SolicitudFields.ESTATPINBAL, Constants.ESTAT_PINBAL_PENDENT_TRAMITAR,
                        SolicitudFields.SOLICITUDID.equal(soliID));
            } else {
                solicitudLogicaEjb.update(SolicitudFields.ESTATPINBAL, Constants.ESTAT_PINBAL_ERROR,
                        SolicitudFields.SOLICITUDID.equal(soliID));
                for (es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Error error : resposta.getErrores()
                        .getError()) {

                    if (error.getCodigo().equals("01")) {
                        //Ja esta donaada d'alta la solicitud. Cercam el seu estat i l'assignam
                        Consulta consultaEstat = new Consulta();
                        consultaEstat.setCodigoProcedimiento(solicitud.getProcedimiento().getCodigo());

                        Retorno retorno = solicitudLogicaEjb.consultaEstatApiPinbal(titular, funcionario,
                                consultaEstat);

                        int estatPinbal = retorno.getProcedimiento().getEstadoProcedimiento().getEstado();
                        log.info("estado procedimiento: " + estatPinbal + " - "
                                + retorno.getProcedimiento().getEstadoProcedimiento().getDescripcion());

                        solicitudLogicaEjb.update(SolicitudFields.ESTATPINBAL, estatPinbal,
                                SolicitudFields.SOLICITUDID.equal(soliID));

                        HtmlUtils.saveMessageInfo(request, "Actualitzat l'estat PINBAL de la solicitud: " + soliID);
                    }
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

    @RequestMapping(value = "/consultaestado/{soliID}", method = RequestMethod.GET)
    public ModelAndView consultaEstado(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long soliID) {

        log.info("Entra a consultaestado amb soliID = " + soliID);

        SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);

        try {
            Long fitxerID = soli.getSolicitudXmlID();
            Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);

            ScspTitular titular = getTitular(prop);
            ScspFuncionario funcionario = getFuncionari();

            Consulta consulta = new Consulta();
            consulta.setCodigoProcedimiento(soli.getProcedimentCodi());

            Retorno retorno = solicitudLogicaEjb.consultaEstatApiPinbal(titular, funcionario, consulta);

            ModelAndView mav = new ModelAndView("consultaestatpinbal");
            mav.addObject("retorno", retorno);
            log.info("context:: " + getContextWeb());
            mav.addObject("returnUrl",
                    "/pinbaladmin" + SolicitudFullViewOperadorController.CONTEXTWEB + "/view/" + soliID);

            int estatPinbal = retorno.getProcedimiento().getEstadoProcedimiento().getEstado();
            log.info("estado procedimiento: " + estatPinbal + " - "
                    + retorno.getProcedimiento().getEstadoProcedimiento().getDescripcion());

            solicitudLogicaEjb.update(SolicitudFields.ESTATPINBAL, estatPinbal,
                    SolicitudFields.SOLICITUDID.equal(soliID));

            HtmlUtils.saveMessageSuccess(request, "Dades de la consutla:");
            return mav;
        } catch (Exception e) {
            HtmlUtils.saveMessageError(request, "Error fent la cridada a la API de PINBAL: " + e.getMessage());

            log.error(e.getMessage(), e);

            String returnUrl = SolicitudFullViewOperadorController.CONTEXTWEB + "/view/" + soliID;
            return new ModelAndView(new RedirectView(returnUrl, true));
        }
    }

    @RequestMapping(value = "/modificaciosolicitud", method = RequestMethod.POST)
    public String modificacioSolicitud(HttpServletRequest request, HttpServletResponse response,
            @RequestParam("soliID") Long soliID) {

        ScspTitular titular = (ScspTitular) request.getSession().getAttribute("titular");
        ScspFuncionario funcionario = (ScspFuncionario) request.getSession().getAttribute("funcionario");
        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud solicitud = (es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud) request
                .getSession().getAttribute("solicitud");

        try {
            es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta resposta = solicitudLogicaEjb
                    .modificacioSolicitudApiPinbal(titular, funcionario, solicitud);

            if (resposta.getErrores() == null) {
                System.out.println(" # Errors: 0");

                //Quan hem enviat la modificació, hem de veure quin es el nou estat a Pinbal.
                Consulta consultaEstat = new Consulta();
                consultaEstat.setCodigoProcedimiento(solicitud.getProcedimiento().getCodigo());

                Retorno retorno = solicitudLogicaEjb.consultaEstatApiPinbal(titular, funcionario,
                        consultaEstat);

                int estatPinbal = retorno.getProcedimiento().getEstadoProcedimiento().getEstado();
                log.info("estado procedimiento: " + estatPinbal + " - "
                        + retorno.getProcedimiento().getEstadoProcedimiento().getDescripcion());

                solicitudLogicaEjb.update(SolicitudFields.ESTATPINBAL, estatPinbal,
                        SolicitudFields.SOLICITUDID.equal(soliID));

                HtmlUtils.saveMessageInfo(request, "Actualitzat l'estat PINBAL de la solicitud: " + soliID);

            } else {
                for (es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Error error : resposta
                        .getErrores().getError()) {

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
        return new String(xmlData, StandardCharsets.UTF_8);
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

        String nif = "45186147W";
        String fullName = "Juan Pablo Trias Segura";
        
        if (ui != null) {
            nif = ui.getAdministrationID();
            fullName = ui.getFullName();
        }

        funcionario.setNifFuncionario(nif);
        funcionario.setNombreCompletoFuncionario(fullName);
        return funcionario;
    }
}
