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
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.security.LoginInfo;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;
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

    
    @EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
    protected InfoMadridLogicaService infoMadridLogicaEjb;

    
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

            ScspTitular titular = getTitular(soli);
            ScspFuncionario funcionario = getFuncionari();

            request.getSession().setAttribute("titular", titular);
            request.getSession().setAttribute("funcionario", funcionario);

            request.getSession().setAttribute(RETURN_URL, returnUrl);

            ModelAndView mav;
            if (tipus.equals("alta")) {
                es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud solicitudA = solicitudLogicaEjb
                        .getDadesSolicitudApiPinbalAlta(soli);

                if (solicitudA.getProcedimiento().getConsentimiento() == null) {
                    errors.add("Fa falta un document de consentiment");
                }

                if (solicitudA.getProcedimiento().getDocumentosAutorizacion() == null) {
                    errors.add("Fa falta un document d'autorització");
                }

                //Si servicios es null, no hay servicios pendientes de autorizar. Si el tamaño es 0, es hay problemas con las normas.
                if (solicitudA.getProcedimiento().getServicios() == null
                        || solicitudA.getProcedimiento().getServicios().getServicio().size() == 0) {
                    errors.add("No hi ha serveis pendents d'autoritzar");
                }
                
                
                request.getSession().setAttribute("solicitud", solicitudA);
                mav = new ModelAndView("altasolicitudpinbal");
                mav.addObject("solicitud", solicitudA);
            } else {
                es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud solicitudM = solicitudLogicaEjb
                        .getDadesModificarSolicitudApiPinbal(soliID);
                
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
                                @RequestParam("soliID") Long soliID) throws I18NException {
        String consulta = request.getParameter("consulta");

        ScspTitular titular = (ScspTitular) request.getSession().getAttribute("titular");
        ScspFuncionario funcionario = (ScspFuncionario) request.getSession().getAttribute("funcionario");

        es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud solicitud =
            (es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud)
            request.getSession().getAttribute("solicitud");

        solicitud.setConsulta(consulta);
        log.info("consulta: " + solicitud.getConsulta());

        // Obtener JPA
        SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);
        try {
            // 1. Enviar a PINBAL
            es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta resposta =
                solicitudLogicaEjb.altaSolicitudApiPinbal(titular, funcionario, solicitud);

            // 2. CREAR INFO MADRID BASIC
            InfoMadridJPA infoMad = crearInfoMadrid(consulta, consulta, titular);
            
            // 3. Procesar respuesta: ACTUALIZAR SOLI + CREAR INFO MADRID
            solicitudLogicaEjb.processarRespostaPinbalAlta(soli, resposta, titular, funcionario, infoMad);
            
            //4. mensajes usuario
            mostrarMissatgesUsuariAlta(request, resposta, soliID);


        } catch (Exception e) {
        	soli.setEstatSolicitud(Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID);
            log.error("Error fent la cridada a la API de PINBAL", e);
            HtmlUtils.saveMessageError(request, "Error fent la cridada a la API de PINBAL: " + e.getMessage());
        }
        
        // 4. Guardar
        solicitudLogicaEjb.update(soli);

        String returnUrl = (String) request.getSession().getAttribute(RETURN_URL);
        log.info("returnUrl :" + returnUrl);
        return "redirect:" + returnUrl;
    }

    @RequestMapping(value = "/modificaciosolicitud", method = RequestMethod.POST)
    public String modificaSolicitud(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam("soliID") Long soliID) {
        ScspTitular titular = (ScspTitular) request.getSession().getAttribute("titular");
        ScspFuncionario funcionario = (ScspFuncionario) request.getSession().getAttribute("funcionario");

        es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud solicitud =
            (es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud)
            request.getSession().getAttribute("solicitud");

        try {
            // 1. Enviar a PINBAL
            es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta resposta =
                solicitudLogicaEjb.modificacioSolicitudApiPinbal(titular, funcionario, solicitud);

            // 2. Obtener JPA
            SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);

            // 3. Procesar respuesta + mensajes usuario
            solicitudLogicaEjb.processarRespostaPinbalModificacio(soli, resposta, titular, funcionario);
            mostrarMissatgesUsuariModificacio(request, resposta, soliID);

            // 4. Guardar
            solicitudLogicaEjb.update(soli);

        } catch (Exception e) {
            log.error("Error fent la cridada a la API de PINBAL (modificació)", e);
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
            ScspTitular titular = getTitular(soli);
            ScspFuncionario funcionario = getFuncionari();

            Consulta consulta = new Consulta();
            consulta.setCodigoProcedimiento(soli.getProcedimentCodi());

            Retorno retorno = solicitudLogicaEjb.consultaEstatApiPinbal(titular, funcionario, soliID);

            ModelAndView mav = new ModelAndView("consultaestatpinbal");
            
            mav.addObject("retorno", retorno);
            
            log.info("context:: " + getContextWeb());
            
			String returnUrl = "/pinbaladmin" + SolicitudFullViewOperadorController.CONTEXTWEB + "/view/" + soliID;
            mav.addObject("returnUrl",returnUrl);

            HtmlUtils.saveMessageSuccess(request, "Dades de la consutla:");
            return mav;
        } catch (Exception e) {
            HtmlUtils.saveMessageError(request, "Error fent la cridada a la API de PINBAL: " + e.getMessage());

            log.error(e.getMessage(), e);

            String returnUrl = SolicitudFullViewOperadorController.CONTEXTWEB + "/view/" + soliID;
            return new ModelAndView(new RedirectView(returnUrl, true));
        }
    }

	private void mostrarMissatgesUsuariAlta(HttpServletRequest request,
			es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta resposta, Long solicitudId) {

		if (resposta.getErrores() == null) {
			HtmlUtils.saveMessageSuccess(request, "Ha anat bé: " + resposta.getEstado().getDescripcion());
			return;
		}

		boolean duplicat = false;
		for (var error : resposta.getErrores().getError()) {
			HtmlUtils.saveMessageError(request,
					"MADRID: " + error.getDescripcion() + " (Error " + error.getCodigo() + ")");
			if ("01".equals(error.getCodigo()))
				duplicat = true;
		}

		if (duplicat) {
			HtmlUtils.saveMessageWarning(request, "La solicitud ja està donada d’alta. Consultat estat actual.");
			HtmlUtils.saveMessageInfo(request, "Estat PINBAL actualitzat per a la solicitud: " + solicitudId);
		}
	}

	private void mostrarMissatgesUsuariModificacio(HttpServletRequest request,
			es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta resposta, Long solicitudId) {

		if (resposta.getErrores() == null) {
			HtmlUtils.saveMessageSuccess(request,
					"Modificació realitzada correctament: " + resposta.getEstado().getDescripcion());
			return;
		}

		boolean duplicat = false;
		for (var error : resposta.getErrores().getError()) {
			HtmlUtils.saveMessageError(request,
					"PINBAL: " + error.getDescripcion() + " (Error " + error.getCodigo() + ")");
			if ("01".equals(error.getCodigo()))
				duplicat = true;
		}

		if (duplicat) {
			HtmlUtils.saveMessageWarning(request, "El procediment ja estava modificat. Estat PINBAL consultat.");
			HtmlUtils.saveMessageInfo(request, "Estat PINBAL actualitzat per a la solicitud: " + solicitudId);
		}
	}
	    
	
	
    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        return rm.value()[0];
    }

    protected String obtenerContenidoXml(Long fitxerID) throws Exception {
        File f = FileSystemManager.getFile(fitxerID);
        byte[] xmlData = FileUtils.readFromFile(f);
        return new String(xmlData, StandardCharsets.UTF_8);
    }

    private ScspTitular getTitularFromProperties(Properties prop) {

        ScspTitular titular = new ScspTitular();

        ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.NIF;
        String documentacion = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NIFSECE");
        String nombre = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBRESECE");
        String ape1 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE1SECE");
        String ape2 = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.APE2SECE");
        String fullName = toFullName(nombre, ape1, ape2);

        titular.setTipoDocumentacion(tipoDocumentacion);
        titular.setDocumentacion(documentacion);
        titular.setNombre(nombre);
        titular.setApellido1(ape1);
        titular.setApellido2(ape2);
        titular.setNombreCompleto(fullName);

        return titular;
    }
    
    private ScspTitular getTitular(Solicitud soli) throws Exception {

    	Long infoMadridID = soli.getInfomadridid();
    	
    	if (infoMadridID == null) {
            Long fitxerID = soli.getSolicitudXmlID();
            Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
            
            return getTitularFromProperties(prop);
		}
    	
    	InfoMadridJPA infoMad = infoMadridLogicaEjb.findByPrimaryKey(infoMadridID);
    	
    	ScspTipoDocumentacion tipoDocumentacion = ScspTipoDocumentacion.NIF;
    	String documentacion = infoMad.getTitularNif();

		log.info("Titular Nom: " + infoMad.getTitularNom());
    	
		String[] fullName = infoMad.getTitularNom().split("\\|");
    	log.info("FullName: " + fullName);
    	
        ScspTitular titular = new ScspTitular();

        String nombre = fullName[0];
        log.info("nombre: " + nombre);
        String ape1 = fullName[1];
        log.info("ape1: " + ape1);
        String ape2 = fullName[2];
        log.info("ape2: " + ape2);
        String nombreCompleto = toFullName(nombre, ape1, ape2);
        log.info("nombreCompleto: " + nombreCompleto);

        titular.setTipoDocumentacion(tipoDocumentacion);
        titular.setDocumentacion(documentacion);
        titular.setNombre(nombre);
        titular.setApellido1(ape1);
        titular.setApellido2(ape2);
        titular.setNombreCompleto(nombreCompleto);

        return titular;
    }
    

    private ScspFuncionario getFuncionari() {

        ScspFuncionario funcionario = new ScspFuncionario();

//        UserInfo ui = LoginInfo.getInstance().getUserInfo();

		String nif = null;
		String fullName = null;
        
//        if (ui != null) {
//            nif = ui.getAdministrationID();
//            fullName = ui.getFullName();
//        	if (fullName == null) {
//				fullName = ui.getName() + " " + ui.getSurname1() + " " + ui.getSurname2();
//			}
//        }else {
//        	String username = LoginInfo.getInstance().getUsername();
//        	
//        	switch (username) {
//        	case "ptrias":
//        		nif = "45186147W";
//        		fullName = "Juan Pablo Trias";
//        		break;
//        	case "pvico":
//        		nif = "43084402C";
//        		fullName = "Pilar Vico Hervas";
//        		break;
//        	case "atrobat":
//        		nif = "43120476F";
//        		fullName = "Toni Trobat Obrador";
//        		break;
//			default:
//				nif = "00000000T";
//				fullName = "Usuari Anonim 00000000T";
//        	}
//        	
//        }
        
    	String username = LoginInfo.getInstance().getUsername();
    	switch (username) {
    	case "ptrias":
    		nif = "45186147W";
    		fullName = "Juan Pablo Trias";
    		break;
    	case "pvico":
    		nif = "43084402C";
    		fullName = "Pilar Vico Hervas";
    		break;
    	case "atrobat":
    		nif = "43120476F";
    		fullName = "Toni Trobat Obrador";
    		break;
		default:
			nif = "00000000T";
			fullName = "Usuari Anonim 00000000T";
    	}

        
        log.info("NIF: " + nif);
        log.info("Nombre completo: " + fullName);

        funcionario.setNifFuncionario(nif);
        funcionario.setNombreCompletoFuncionario(fullName);
        return funcionario;
    }
    
    private String toFullName(String nom, String l1, String l2) {
        String fullName = nom + " " + l1 + (l2 == "" ? "" : " " + l2);
        return fullName;
    }
    
    
    private InfoMadridJPA crearInfoMadrid(String codi, String consulta, ScspTitular titular) {
    	
		Long estatSoli = null;
		Long estatAuth = null;
		String resposta = null;
		
	    Timestamp ahora = new Timestamp(System.currentTimeMillis());

	    Timestamp dataAuth = null;
	    Timestamp dataEnviament = ahora;
	    Timestamp dataConsulta = ahora;
	    
		
    	InfoMadridJPA infoMadJpa = new InfoMadridJPA(
    			codi,
    			estatSoli,                // Estado interno de la solicitud
    			estatAuth,                // Estado que devuelve Madrid
    			resposta,         // Mensaje de Madrid
    			consulta, // Texto enviado
                titular.getNombreCompleto(),
                titular.getDocumentacion(),
                dataAuth,
                dataEnviament,                                   // Fecha de envío
                0,
                dataEnviament
        );
    	
    	return infoMadJpa;
    	
    }

}
