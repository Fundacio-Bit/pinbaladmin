package org.fundaciobit.pinbaladmin.back.controller.all;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitAOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutForm;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitAPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitAPersAutForm.class, TramitAPersAutFilterForm.class })
public class TramitAPublicController extends TramitAOperadorController {

    public static final String CONTEXT_WEB = "/public/tramita";
    public static final String RETURN_URL = "/public/tramita/list";
    public static final String RETURN_TO_SISTRA = RETURN_URL;

    @Override
    public String getTileForm() {
        return "tramitAFormPublic";
    }

    @Override
    public String getTileList() {
        return "tramitSistraListPublic";
    }

//    @RequestMapping(value = "/finalitzarTramit1/{uuid}", method = RequestMethod.GET)
//    public String getRedirectToFinish1(HttpServletRequest request, @PathVariable("uuid") java.lang.String uuid) {
//        
//        try {
//            SolicitudJPA soli = crearSolicitudAmbTramitID(uuid);
//            
//            String urlCallbackSistra = (String) request.getSession().getAttribute("urlCallbackSistra");
//            
//            
//            request.setAttribute("ticket", uuid);
//            
//            return "redirect:" + Configuracio.getUrlRetornSistra();
//        }catch(Exception e) {
//            log.error(e);
//            HtmlUtils.saveMessageError(request, "Error creant solicitud: " + e.getMessage());
//            return "redirect:" + TramitAPublicController.RETURN_URL;
//        }
//    }
    
    @RequestMapping(value = "/finalitzarTramit/{uuid}", method = RequestMethod.GET)
    public ModelAndView getRedirectToFinish(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("uuid") java.lang.String uuid) {
    
        ModelAndView mav = new ModelAndView("returnToSistraPost");
        try {
            Long tramitID = HibernateFileUtil.decryptFileID(uuid);

            SolicitudJPA soli = crearSolicitudAmbTramitID(tramitID);
            TramitAPersAut tramitA = tramitAPersAutLogicEjb.findByPrimaryKey(tramitID);
        
            String urlCallbackSistra = "https://se.caib.es/sistramitfront/asistente/retornoGestorFormularioExterno.html";
            String idSesionFormulario = tramitA.getMail();
            String ticketGFE = uuid;
            String ticket = idSesionFormulario + ":" + ticketGFE;
            
            mav.addObject("urlCallbackSistra", urlCallbackSistra);
            mav.addObject("ticket", ticket);
            
            log.info("URL Callback: " + urlCallbackSistra);
            log.info("ticket:" + ticket);
            
        } catch (Exception e) {
            log.error(e);
            HtmlUtils.saveMessageError(request, "Error creant incidencia a Sistra: " + e.getMessage());
        }
        return mav;
    }

    @PostMapping("/performPostRedirect")
    public String performPostRedirect(HttpServletRequest request) {
        // Get the POST parameters to be sent
        String ticket = (String) request.getAttribute("ticket");
        String urlCallbackSistra = (String) request.getSession().getAttribute("urlCallbackSistra");
        
        // Build a form dynamically using JavaScript and submit it
        String htmlResponse = "<html><body onload=\"document.forms[0].submit()\">" +
                              "<form action=\"" + urlCallbackSistra + "\" method=\"post\">" +
                              "<input type=\"hidden\" name=\"ticket\" value=\"" + ticket + "\">" +
                              "</form></body></html>";
        
        return htmlResponse;
    }
    
//    @RequestMapping(value = "/finalitzarTramit/{uuid}", method = RequestMethod.GET)
//   public void getRedirectToFinish(HttpServletRequest request, @PathVariable("uuid") String uuid) {
//        try {
//            SolicitudJPA soli = crearSolicitudAmbTramitID(uuid);
//            // Obtener la URL de callback
//            String urlCallbackSistra = (String) request.getSession().getAttribute("urlCallbackSistra");
//            
//            log.info("ReturnURL: " + urlCallbackSistra); 
//            // Preparar los parámetros POST
//            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//            map.add("ticket", uuid);
//            
//            // Configurar las cabeceras
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//            
//            // Crear la entidad de la solicitud
//            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
//            
//            // Realizar la solicitud POST a la URL de callback
//            restTemplate.postForEntity(urlCallbackSistra, requestEntity, String.class);
//
//        } catch(Exception e) {
//            log.error(e);
//            HtmlUtils.saveMessageError(request, "Error creant solicitud: " + e.getMessage());
//            // Redirigir al usuario a alguna otra página en caso de error
//            // Ejemplo: response.sendRedirect("/pagina-error.html");
//        }
//    }

    
    public SolicitudJPA crearSolicitudAmbTramitID(Long tramitID) throws Exception {
        log.info("Generador del fitxer XML amb tramitID=" + tramitID);

        SolicitudJPA soli = tramitAPersAutLogicEjb.crearSolicitudAmbTramit(tramitID);
        log.info("Solicitud Creada a BBDD: " + soli.getSolicitudID());
        
        Long fitxerID = soli.getSolicitudXmlID();
        Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);

        generarDocumentSolicitudAmbXML(soli, prop);
        generarDocumentsSolicitud(soli, prop);
        generarExcelDeServeis(soli);

        log.info("Generat");
        return soli;
    }
}
