package org.fundaciobit.pinbaladmin.front.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pluginsib.login.api.LoginInfo;
import org.fundaciobit.pluginsib.login.springutils.PluginLoginController;
import org.fundaciobit.pluginsib.login.springutils.PluginLoginRequestCache;
import org.fundaciobit.pluginsib.login.springutils.PluginLoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author anadal
 *
 */
@Controller
public class InicioController {

	protected final Log log = LogFactory.getLog(getClass());

	public final static String CONTEXT_ARRAMCAR_AUTH = "/arrancarpinfoauth";
	public final static String CONTEXT_MODIFICAR_AUTH = "/modificarsolicitudauth";
	
	public final static String MAPPING_OK_LOGIN = "/okLogin";
	
	// Atributo de sesión para guardar el destino después del login
	private static final String SESSION_ATTR_POST_LOGIN_REDIRECT = "pinbaladmin.postLoginRedirect";

	@Autowired
	private PluginLoginRequestCache loginRequestCache;

	
	@RequestMapping(value = { "/"}, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView root(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		log.info("Entra a ROOT - intentando obtener la solicitud guardada");
		
		// PRIMERO: Intentar obtener de atributo de sesión (sobrevive a session fixation protection)
		String postLoginRedirect = (String) session.getAttribute(SESSION_ATTR_POST_LOGIN_REDIRECT);
		if (postLoginRedirect != null) {
			log.info("Destino encontrado en atributo de sesión: " + postLoginRedirect);
			session.removeAttribute(SESSION_ATTR_POST_LOGIN_REDIRECT);
			return new ModelAndView(new RedirectView(postLoginRedirect, true));
		}
		
		// SEGUNDO: Intentar obtener del PluginLoginRequestCache
		org.springframework.security.web.savedrequest.SavedRequest savedRequest = 
				loginRequestCache.getRequest(request, response);
		
		if (savedRequest != null) {
			String redirectUrl = savedRequest.getRedirectUrl();
			log.info("Solicitud guardada encontrada en cache: " + redirectUrl);
			loginRequestCache.removeRequest(request, response);
			return new ModelAndView(new RedirectView(redirectUrl, false));
		}
		
		// Si no hay solicitud guardada, redirigir a modificarsolicitud por defecto
		log.info("No hay solicitud guardada, redirigiendo a /modificarsolicitud por defecto");
		return new ModelAndView(new RedirectView("/modificarsolicitud", true));
	}
	
	@RequestMapping(value = {"/arrancarpinfo" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView inicio(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {

		log.info("entram a arrancarpinfo");
		
		Authentication i = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mav;
		log.info("auth: " + i);
		
		if (i == null || i.getPrincipal() == null || i.getPrincipal().equals("anonymousUser")) {
			// Usuario no autenticado - guardar destino en sesión y cache antes de redirigir a PRELOGIN
			// Guardamos en atributo de sesión porque sobrevive a session fixation protection
			session.setAttribute(SESSION_ATTR_POST_LOGIN_REDIRECT, CONTEXT_ARRAMCAR_AUTH);
			log.info("Destino guardado en sesión: " + CONTEXT_ARRAMCAR_AUTH);
			
			loginRequestCache.saveRequest(request, response);
			log.info("Solicitud guardada en el cache");
			
			String urlFront = Configuracio.getAppFrontUrl();
			URL url = new URL(urlFront);

			String urlbase = url.getProtocol() + "://" + url.getHost()
					+ (url.getPort() == -1 ? "" : (":" + url.getPort()));
			log.info("urlbase: " + urlbase);
			
			// Redirigir a PRELOGIN con urlbase
			mav = new ModelAndView(
					new RedirectView(PluginLoginController.MAPPING_PRELOGIN + "?urlbase=" + urlbase, true));
		} else {
			// Usuario autenticado - ir directamente a arrancarpinfoauth
			log.info("auth ppal: " + i.getPrincipal());
			mav = new ModelAndView(new RedirectView(CONTEXT_ARRAMCAR_AUTH, true));
		}
		return mav;
	}

	@RequestMapping(value = { "/modificarsolicitud" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarsolicitud(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		log.info("entram a modificarsolicitud");
		
		try {
			Authentication i = SecurityContextHolder.getContext().getAuthentication();
			log.info("auth: " + i);
			
			if (i == null || i.getPrincipal() == null || i.getPrincipal().equals("anonymousUser")) {
				// Usuario no autenticado - guardar destino en sesión y cache antes de redirigir a PRELOGIN
				try {
					// Guardamos en atributo de sesión porque sobrevive a session fixation protection
					session.setAttribute(SESSION_ATTR_POST_LOGIN_REDIRECT, CONTEXT_MODIFICAR_AUTH);
					log.info("Destino guardado en sesión: " + CONTEXT_MODIFICAR_AUTH);
					
					loginRequestCache.saveRequest(request, response);
					log.info("Solicitud guardada en el cache");
				} catch (Exception e) {
					log.error("Error al guardar el destino en sesión/cache: " + e.getMessage(), e);
					// Continuar aunque falle el guardado
				}
				
				String urlFront = Configuracio.getAppFrontUrl();
				log.info("URL Front obtenida de configuración: " + urlFront);
				
				URL url = new URL(urlFront);
				String urlbase = url.getProtocol() + "://" + url.getHost()
						+ (url.getPort() == -1 ? "" : (":" + url.getPort()));
				log.info("urlbase calculada: " + urlbase);
				
				return new ModelAndView(
						new RedirectView(PluginLoginController.MAPPING_PRELOGIN + "?urlbase=" + urlbase, true));
				
			} else {
				// Usuario autenticado - ir directamente a modificarsolicitudauth
				log.info("auth ppal: " + i.getPrincipal());
				return new ModelAndView(new RedirectView(CONTEXT_MODIFICAR_AUTH, true));
			}
			
		} catch (Exception e) {
			log.error("Error crítico en /modificarsolicitud: " + e.getMessage(), e);
			log.error("Tipo de excepción: " + e.getClass().getName());
			if (e.getCause() != null) {
				log.error("Causa: " + e.getCause().getMessage(), e.getCause());
			}
			// Mostrar página de error
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("error", "Error al procesar la solicitud de modificación: " + e.getMessage());
			return mav;
		}
	}

	@RequestMapping(value = { "/error" }, method = RequestMethod.GET)
	public ModelAndView error(HttpServletRequest request, HttpServletResponse response, Exception e) {

		log.error("========== ENTRAM A /error ==========");
		
		ModelAndView mav = new ModelAndView("error");
		
		try {
			if (e != null) {
				log.error("Excepción recibida: " + e.getMessage(), e);
				log.error("Tipo: " + e.getClass().getName());
				mav.addObject("error", e.getMessage());
			} else {
				log.error("No se recibió excepción en /error");
				mav.addObject("error", "Error desconocido");
			}
			
			// Log de atributos de request para debugging
			Object statusCode = request.getAttribute("javax.servlet.error.status_code");
			Object errorMessage = request.getAttribute("javax.servlet.error.message");
			if (statusCode != null) {
				log.error("Status code: " + statusCode);
			}
			if (errorMessage != null) {
				log.error("Error message attribute: " + errorMessage);
			}

		} catch (Throwable e1) {
			log.error("Error al procesar la página de error: " + e1.getMessage(), e1);
			mav.addObject("error", "Error interno del servidor");
		}

		log.error("========================================");
		return mav;
	}

//    @RequestMapping(value = { "/arrancarpinfo" }, method = { RequestMethod.GET, RequestMethod.POST })
//    public ModelAndView formularipinfo(HttpServletRequest request, HttpServletResponse response, Exception e)
//            throws Exception {
//    	
//    	log.info("arrancarpinfo arrancarpinfo");
//
//        ModelAndView mav = new ModelAndView("formularipinfo");
//        
//        return mav;
//    }

	@RequestMapping(value = { CONTEXT_ARRAMCAR_AUTH }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView formularipinfoauth(HttpServletRequest request, HttpServletResponse response) {

		log.info("arrancarpinfo auth");

		try {
			// añadir token
			UUID uuid = UUID.randomUUID();
			String token = uuid.toString();
			log.info("Token generado: " + token);

			// crear un fitxer amb el token i totes les propietats de Authentication.
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth == null) {
				log.error("Authentication es null en arrancarpinfoauth");
				return new ModelAndView(new RedirectView("/", true));
			}
			
			Object principal = auth.getPrincipal();
			log.info("Principal type: " + (principal != null ? principal.getClass().getName() : "null"));

			if (principal instanceof PluginLoginUserDetails) {
				PluginLoginUserDetails usuarioAutenticado = (PluginLoginUserDetails) principal;
				LoginInfo loginInfo = usuarioAutenticado.getUsuario();
				
				if (loginInfo != null) {
					log.info("LoginInfo obtenido para NIF: " + loginInfo.getAdministrationID());
					loginInfoToFile(token, loginInfo);
				} else {
					log.error("LoginInfo es null en PluginLoginUserDetails");
				}
			} else {
				log.warn("Principal no es instancia de PluginLoginUserDetails");
			}

			String backUrl = Configuracio.getAppBackUrl();
			log.info("Back URL obtenida: " + backUrl);
			
			String url = backUrl + "/public/incidenciapinfo" + "/new/" + token;
			log.info("Redirigiendo a: " + url);

			ModelAndView mav = new ModelAndView(new RedirectView(url));
			return mav;
			
		} catch (Exception e) {
			log.error("Error crítico en arrancarpinfoauth: " + e.getMessage(), e);
			log.error("Tipo de excepción: " + e.getClass().getName());
			if (e.getCause() != null) {
				log.error("Causa: " + e.getCause().getMessage(), e.getCause());
			}
			// Redirigir a página de error
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("error", "Error al iniciar el formulario PINFO: " + e.getMessage());
			return mav;
		}
	}

	private void loginInfoToFile(String token, LoginInfo loginInfo) {
		File file = null;
		FileWriter myWriter = null;
		
		try {
			file = new File(token + ".front");
			log.info("Creando fichero temporal: " + file.getAbsolutePath());

			// Escribir todos los datos en el fichero.
			String nif = loginInfo.getAdministrationID();
			String nom = loginInfo.getName();
			String ape1 = loginInfo.getSurname1();
			String ape2 = loginInfo.getSurname2();
			String authMethod = loginInfo.getAuthenticationMethod();
			String identityProvider = loginInfo.getIdentityProvider();
			String loginID = loginInfo.getLoginID();
			String username = loginInfo.getUsername();
			int qaa = loginInfo.getQaa();
			
			log.info("Datos de usuario - NIF: " + nif + ", Nombre: " + nom);

			// Escribir en el fichero.
			myWriter = new FileWriter(file);
			myWriter.write("NIF=" + nif + "\n");
			myWriter.write("Nom=" + nom + "\n");
			myWriter.write("Cognom1=" + ape1 + "\n");
			myWriter.write("Cognom2=" + ape2 + "\n");
			myWriter.write("AuthMethod=" + authMethod + "\n");
			myWriter.write("IdentityProvider=" + identityProvider + "\n");
			myWriter.write("LoginID=" + loginID + "\n");
			myWriter.write("Username=" + username + "\n");
			myWriter.write("QAA=" + qaa + "\n");
			myWriter.close();
			myWriter = null;

			File filesPath = FileSystemManager.getFilesPath();
			log.info("Files path: " + filesPath);
			
			File newFile = new File(filesPath, token + ".front");
			log.info("Copiando a: " + newFile.getAbsolutePath());
			
			FileSystemManager.copy(file, newFile);

			log.info("Fichero de token creado correctamente: " + token + ".front");
			
		} catch (IOException e) {
			log.error("Error de IO al escribir fichero de token: " + e.getMessage(), e);
			log.error("Fichero: " + (file != null ? file.getAbsolutePath() : "null"));
		} catch (I18NException e) {
			log.error("Error de I18N al obtener el path de ficheros: " + e.getMessage(), e);
		} catch (Exception e) {
			log.error("Error inesperado al escribir fichero de token: " + e.getMessage(), e);
			log.error("Tipo: " + e.getClass().getName());
		} finally {
			// Cerrar FileWriter si quedó abierto
			if (myWriter != null) {
				try {
					myWriter.close();
				} catch (IOException e) {
					log.error("Error al cerrar FileWriter: " + e.getMessage(), e);
				}
			}
		}
	}
	
	@RequestMapping(value = { CONTEXT_MODIFICAR_AUTH }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarsolicitudauth(HttpServletRequest request, HttpServletResponse response) {
		
		log.info("modificar solicitud auth");
		
		try {
			// añadir token
			UUID uuid = UUID.randomUUID();
			String token = uuid.toString();
			log.info("Token generado: " + token);
			
			// crear un fitxer amb el token i totes les propietats de Authentication.
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth == null) {
				log.error("Authentication es null en modificarsolicitudauth");
				return new ModelAndView(new RedirectView("/", true));
			}
			
			Object principal = auth.getPrincipal();
			log.info("Principal type: " + (principal != null ? principal.getClass().getName() : "null"));
			
			if (principal instanceof PluginLoginUserDetails) {
				PluginLoginUserDetails usuarioAutenticado = (PluginLoginUserDetails) principal;
				LoginInfo loginInfo = usuarioAutenticado.getUsuario();
				
				if (loginInfo != null) {
					log.info("LoginInfo obtenido para NIF: " + loginInfo.getAdministrationID());
					loginInfoToFile(token, loginInfo);
				} else {
					log.error("LoginInfo es null en PluginLoginUserDetails");
				}
			} else {
				log.warn("Principal no es instancia de PluginLoginUserDetails");
			}
			
			String backUrl = Configuracio.getAppBackUrl();
			log.info("Back URL obtenida: " + backUrl);
			
			String url = backUrl + "/public/modificarsolicitud" + "/new/" + token;
			log.info("Redirigiendo a: " + url);
			
			ModelAndView mav = new ModelAndView(new RedirectView(url));
			return mav;
			
		} catch (Exception e) {
			log.error("Error crítico en modificarsolicitudauth: " + e.getMessage(), e);
			log.error("Tipo de excepción: " + e.getClass().getName());
			if (e.getCause() != null) {
				log.error("Causa: " + e.getCause().getMessage(), e.getCause());
			}
			// Redirigir a página de error
			ModelAndView mav = new ModelAndView("error");
			mav.addObject("error", "Error al procesar la solicitud de modificación: " + e.getMessage());
			return mav;
		}
	}

}
