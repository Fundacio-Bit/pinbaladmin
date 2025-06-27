package org.fundaciobit.pinbaladmin.front.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pluginsib.login.api.LoginInfo;
import org.fundaciobit.pluginsib.login.springutils.PluginLoginController;
import org.fundaciobit.pluginsib.login.springutils.PluginLoginUserDetails;
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

	
	@RequestMapping(value = { "/"}, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView root(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		log.info("Entra a ROOT");
		
		return new ModelAndView(new RedirectView("/arrancarpinfo", true));
		
	}
	
	@RequestMapping(value = {"/arrancarpinfo" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView inicio(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {

		log.info("entram a arrancarpinfo");
//		session.setAttribute(CONTEXT_ARRAMCAR_AUTH, session)
		
		Authentication i = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mav;
		log.info("auth: " + i);
		if (i == null || i.getPrincipal() == null || i.getPrincipal().equals("anonymousUser")) {

			String urlFront = Configuracio.getAppFrontUrl();
			URL url = new URL(urlFront);

			String urlbase = url.getProtocol() + "://" + url.getHost()
					+ (url.getPort() == -1 ? "" : (":" + url.getPort()));
			log.info("urlbase: " + urlbase);
			String urlRedirect = urlbase + request.getContextPath() + CONTEXT_ARRAMCAR_AUTH;

			request.getSession().setAttribute(PluginLoginController.SESSION_RETURN_URL_POST_LOGIN, urlRedirect);
			mav = new ModelAndView(
					new RedirectView(PluginLoginController.MAPPING_PRELOGIN + "?urlbase=" + urlbase, true));
//			mav = new ModelAndView("inici");
		} else {
			log.info("auth ppal: " + i.getPrincipal());
			mav = new ModelAndView(new RedirectView(CONTEXT_ARRAMCAR_AUTH, true));

		}

		return mav;
	}

	@RequestMapping(value = { "/modificarsolicitud" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarsolicitud(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			{
		
		log.info("entram a modificarsolicitud");
		
		try {

			Authentication i = SecurityContextHolder.getContext().getAuthentication();
			ModelAndView mav;
			log.info("auth: " + i);
			if (i == null || i.getPrincipal() == null || i.getPrincipal().equals("anonymousUser")) {

				String urlFront = Configuracio.getAppFrontUrl();
				URL url = new URL(urlFront);

				String urlbase = url.getProtocol() + "://" + url.getHost()
						+ (url.getPort() == -1 ? "" : (":" + url.getPort()));
				log.info("urlbase: " + urlbase);
				String urlRedirect = urlbase + request.getContextPath() + CONTEXT_MODIFICAR_AUTH;

				request.getSession().setAttribute(PluginLoginController.SESSION_RETURN_URL_POST_LOGIN, urlRedirect);
				mav = new ModelAndView(
						new RedirectView(PluginLoginController.MAPPING_PRELOGIN + "?urlbase=" + urlbase, true));
//			mav = new ModelAndView("inici");
			} else {
				log.info("auth ppal: " + i.getPrincipal());
				mav = new ModelAndView(new RedirectView(CONTEXT_MODIFICAR_AUTH, true));

			}

			return mav;
		} catch (Exception e) {
			String error = "Error a /modificarsolicitud " + e.getMessage();
			log.error(error, e);
			return new ModelAndView(new RedirectView("https://www.google.com"));
		}
		
	}

	@RequestMapping(value = { "/error" }, method = RequestMethod.GET)
	public ModelAndView error(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {

		ModelAndView mav = new ModelAndView("error");

		log.info("XXXXXXX ENTRAM A /error");
		
		try {

			mav.addObject("error", e.getMessage());

		} catch (Throwable e1) {
			log.error("Error: " + e1.getMessage(), e1);
		}

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
	public ModelAndView formularipinfoauth(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info("arrancarpinfo auth");

		// añadir token
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();

		// crear un fitxer amb el token i totes les propietats de Authentication.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();

		if (principal instanceof PluginLoginUserDetails) {

			PluginLoginUserDetails usuarioAutenticado = (PluginLoginUserDetails) principal;
			LoginInfo loginInfo = usuarioAutenticado.getUsuario();

			loginInfoToFile(token, loginInfo);
		}

		String url = Configuracio.getAppBackUrl() + "/public/incidenciapinfo" + "/new/" + token;

		ModelAndView mav = new ModelAndView(new RedirectView(url));
		return mav;
	}

	private void loginInfoToFile(String token, LoginInfo loginInfo) throws I18NException {
		File file = new File(token + ".front");

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

		// Escribir en el fichero.

		try {
			FileWriter myWriter = new FileWriter(file);
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

			File newFile = new File(FileSystemManager.getFilesPath(), token + ".front");
			FileSystemManager.copy(file, newFile);

			log.info("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = { CONTEXT_MODIFICAR_AUTH }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView modificarsolicitudauth(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.info("modificar solicitud auth");
		
		// añadir token
		
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		
		// crear un fitxer amb el token i totes les propietats de Authentication.
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		
		if (principal instanceof PluginLoginUserDetails) {
			
			PluginLoginUserDetails usuarioAutenticado = (PluginLoginUserDetails) principal;
			LoginInfo loginInfo = usuarioAutenticado.getUsuario();
			
			
			
			// Escribir en el fichero.
			
			loginInfoToFile(token, loginInfo);
			
			
		}
		
		String url = Configuracio.getAppBackUrl() + "/public/modificarsolicitud" + "/new/" + token;
		
		ModelAndView mav = new ModelAndView(new RedirectView(url));
		return mav;
	}

}
