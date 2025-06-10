package org.fundaciobit.pinbaladmin.logic.utils;

import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;

public class PinbalAdminPluginsManager {

    public static final String LOGIN_PLUGIN_KEY = Constants.PINBALADMIN_PROPERTY_BASE + "userinformationplugin";

    public static IUserInformationPlugin loginPluginOtae = null;
    public static IUserInformationPlugin loginPluginCaib = null;

    protected final static Logger log = Logger.getLogger(PinbalAdminPluginsManager.class);


	public static IUserInformationPlugin getUserInformationPluginInstance(boolean debug, boolean caib) throws I18NException {

		log.info("Plugin UserInformation: caib=" + caib);
		IUserInformationPlugin loginPlugin = caib ? loginPluginCaib : loginPluginOtae;
		
        if (loginPlugin == null) {
//            final String propertyPlugin = LOGIN_PLUGIN_KEY;

            Properties propTmp = Configuracio.getSystemAndFileProperties();

			if (debug) {
				log.info("Propietats de sistema i fitxer de configuració:");
				Set<Object> set = propTmp.keySet();
				for (Object object : set) {
					String key = (String) object;
					String value = propTmp.getProperty(key);
					log.info(key + ": " + value);
				}
			}
            
			String className = propTmp.getProperty(LOGIN_PLUGIN_KEY + (caib ? ".caib" : ".otae"));
            
            log.info("className: " + className);
            Object pluginInstance = PluginsManager.instancePluginByClassName(className,
                    Constants.PINBALADMIN_PROPERTY_BASE, propTmp);

//            Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin,
//                    Constants.PINBALADMIN_PROPERTY_BASE, propTmp);

            if (pluginInstance == null) {
                throw new I18NException("plugin.donotinstantiateplugin.userinfo");
            }
            loginPlugin = (IUserInformationPlugin) pluginInstance;
        }else {
			log.info("loginPlugin ja existeix. " + loginPlugin.getClass().getName());
        }
        return loginPlugin;
    }
	
	
//    public static IUserInformationPlugin getUserInformationPluginInstance(boolean debug) throws I18NException {
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
//            String className = propTmp.getProperty(LOGIN_PLUGIN_KEY);
//            
//            log.info("className: " + className);
//            Object pluginInstance = PluginsManager.instancePluginByClassName(className,
//                    Constants.PINBALADMIN_PROPERTY_BASE, propTmp);
//
////            Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin,
////                    Constants.PINBALADMIN_PROPERTY_BASE, propTmp);
//
//            if (pluginInstance == null) {
//                throw new I18NException("plugin.donotinstantiateplugin.userinfo");
//            }
//            loginPlugin = (IUserInformationPlugin) pluginInstance;
//        }else {
//			log.info("loginPlugin ja existeix. " + loginPlugin.getClass().getName());
//        }
//        return loginPlugin;
//    }

}
