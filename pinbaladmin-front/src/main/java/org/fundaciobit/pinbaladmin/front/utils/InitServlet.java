package org.fundaciobit.pinbaladmin.front.utils;

import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.fundaciobit.pluginsib.login.api.IPluginLogin;
import org.fundaciobit.pluginsib.login.springutils.PluginLoginManager;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;

import javax.annotation.security.RunAs;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.util.Properties;
import java.util.Set;

/**
 * Servlet emprat per inicialitzar el Front
 * 
 * @author anadal
 * 
 */
@RunAs(Constants.PAD_ADMIN)
public class InitServlet extends HttpServlet {

    protected final Logger log = Logger.getLogger(getClass());

	public static enum TipusPluginLogin{
		LOGINIB, MOCK
	}

    public static final String LOGIN_PLUGIN_KEY = Constants.PINBALADMIN_PROPERTY_BASE + "pluginsib.login";

    public static IPluginLogin loginPluginLoginIB= null;
    public static IPluginLogin loginPluginMock= null;

    @Override
    public void init(ServletConfig config) throws ServletException {

        // Sistema de Traduccions WEB
        try {
            ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
            String[] basenames = { "classpath:/missatges" };
            ms.setDefaultEncoding("UTF-8");
            ms.setBasenames(basenames);
            I18NUtils.setMessageSource(ms);
        } catch (Throwable th) {
            log.error("Error inicialitzant el sistema de traduccions web: " + th.getMessage(), th);
        }

		try {
//			boolean debug = true;
//			TipusPluginLogin loginib = TipusPluginLogin.LOGINIB;
//			IPluginLogin pl = (IPluginLogin) getPluginLogin(debug, loginib);

			IPluginLogin pl = (IPluginLogin) getPluginLoginOld();
			PluginLoginManager.setPluginLogin(pl);
			
		} catch (ServletException e) {
			log.error("Error carregant el plugin de login: " + e.getMessage(), e);
			throw new ServletException("Error carregant el plugin de login: " + e.getMessage(), e);
		}

    }

    /**
     * 
     * @return
     * @throws Exception
     */
    private IPluginLogin getPluginLoginOld() throws ServletException {

        Properties properties = new Properties();
        properties.putAll(Configuracio.getSystemAndFileProperties());

        final String baseProps = Constants.PINBALADMIN_PROPERTY_BASE;

        final String propClass = baseProps + IPluginLogin.PLUGIN_LOGIN_PROPERTY_BASE + "class";

        String pluginClassName = properties.getProperty(propClass);

        // Carregant la classe ja que els plugins es troben en el WAR de FRONT
        log.info("Carregant classe " + pluginClassName + " ...");
        Class<?> pluginClass;
        try {
            pluginClass = Class.forName(pluginClassName.trim());
        } catch (Exception ex) {
            final String msg = "Error carregant la classe " + pluginClassName + " associada a un plugin:"
                    + ex.getMessage();
            log.error(msg, ex);
            throw new ServletException(msg, ex);
        }

        if (pluginClass == null) {
            throw new ServletException(
                    "No s'ha definit la propietat ´" + propClass + "´ dins dels fitxers de propietats de l'aplicació.");
        } else {
            Object obj = PluginsManager.instancePluginByClass(pluginClass, baseProps, properties);

            if (obj == null) {
                throw new ServletException("Per alguna raó desconeguda no s'ha pogut carregar la classe " + pluginClass
                        + ". Consulti el log de l'aplicació per obtenir més detalls del problema.");
            }

            return (IPluginLogin) obj;
        }
    }
//    
//    private IPluginLogin getPluginLogin(boolean debug, TipusPluginLogin tipusPlugin) throws I18NException {
//    	
//    	
//		log.info("Plugin Login: tipusPlugin=" + tipusPlugin);
//		
//		IPluginLogin loginPlugin;
//		switch (tipusPlugin) {
//			case LOGINIB:
//				loginPlugin = loginPluginLoginIB;
//				break;
//			case MOCK:
//				loginPlugin = loginPluginMock;
//				break;
//			default:
//				throw new I18NException("plugin.tipus.desconegut");
//		}
//		
//		log.info("loginPlugin inicial: " + loginPlugin);
//		
//        if (loginPlugin == null) {
////            final String propertyPlugin = LOGIN_PLUGIN_KEY;
//
//            Properties propTmp = Configuracio.getSystemAndFileProperties();
//
//			if (debug) {
//				log.info("Propietats de sistema i fitxer de configuració:");
//				Set<Object> set = propTmp.keySet();
//				for (Object object : set) {
//					String key = (String) object;
//					String value = propTmp.getProperty(key);
//					log.info(key + ": " + value);
//				}
//			}
//            
//			String propertySuffix;
//			switch (tipusPlugin) {
//				case LOGINIB:
//					propertySuffix = ".loginib";
//					break;
//				case MOCK:
//					propertySuffix = ".mock";
//					break;
//				default:
//					throw new I18NException("plugin.tipus.desconegut");
//			}
//			
//			String clName = LOGIN_PLUGIN_KEY + propertySuffix;
//			log.info("Property plugin class name: " + clName);
//			
//			String className = propTmp.getProperty(clName);
//            
//            log.info("className: " + className);
//            Object pluginInstance = PluginsManager.instancePluginByClassName(className,
//                    Constants.PINBALADMIN_PROPERTY_BASE, propTmp);
//            
//            
//
////            Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin,
////                    Constants.PINBALADMIN_PROPERTY_BASE, propTmp);
//
//            if (pluginInstance == null) {
//            	log.error("No s'ha pogut instanciar el plugin de login amb la classe: " + className);
//                throw new I18NException("plugin.donotinstantiateplugin.userinfo");
//            }
//            
//            switch (tipusPlugin) {
//				case LOGINIB:
//					loginPluginLoginIB = (IPluginLogin) pluginInstance;
//					loginPlugin = loginPluginLoginIB;
//					break;
//				case MOCK:
//					loginPluginMock = (IPluginLogin) pluginInstance;
//					loginPlugin = loginPluginMock;
//					break;
//				default:
//					throw new I18NException("plugin.tipus.desconegut");
//			}
//        }else {
//			log.info("loginPlugin ja existeix. " + loginPlugin.getClass().getName());
//        }
//        return loginPlugin;    	
//    	
//    	
//    	
//    }


}
