package org.fundaciobit.pinbaladmin.logic.utils;

import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;

public class PinbalAdminPluginsManager {

	public static enum TipusPluginUserInfo {
		OTAE, LDAP, SOFFID
	}
	
    public static final String USERINFO_PLUGIN_KEY = Constants.PINBALADMIN_PROPERTY_BASE + "userinformationplugin";
    public static final String ESTRUCTURAORG_PLUGIN_KEY = Constants.PINBALADMIN_PROPERTY_BASE + "estructuraorganitzativa";

    public static IUserInformationPlugin userInfoPluginOtae = null;
    public static IUserInformationPlugin userInfoPluginCaibLdap = null;
    public static IUserInformationPlugin userInfoPluginCaibSoffid = null;

    public static IEstructuraOrganitzativaPlugin estructuraOrgPluginOtae = null;
    public static IEstructuraOrganitzativaPlugin estructuraOrgPluginCaib = null;
    
    protected final static Logger log = Logger.getLogger(PinbalAdminPluginsManager.class);


	public static IUserInformationPlugin getUserInformationPluginInstance(boolean debug, TipusPluginUserInfo tipusPlugin) throws I18NException {

		log.info("Plugin UserInformation: tipusPlugin=" + tipusPlugin);
		
		IUserInformationPlugin userInfoPlugin;
		switch (tipusPlugin) {
			case OTAE:
				userInfoPlugin = userInfoPluginOtae;
				break;
			case LDAP:
				userInfoPlugin = userInfoPluginCaibLdap;
				break;
			case SOFFID:
				userInfoPlugin = userInfoPluginCaibSoffid;
				break;
			default:
				throw new I18NException("plugin.tipus.desconegut");
		}
		
		log.info("userInfoPlugin inicial: " + userInfoPlugin);
		
        if (userInfoPlugin == null) {
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
            
			String propertySuffix;
			switch (tipusPlugin) {
				case OTAE:
					propertySuffix = ".otae";
					break;
				case LDAP:
					propertySuffix = ".ldap";
					break;
				case SOFFID:
					propertySuffix = ".soffid";
					break;
				default:
					throw new I18NException("plugin.tipus.desconegut");
			}
			
			String clName = USERINFO_PLUGIN_KEY + propertySuffix;
			log.info("Property plugin class name: " + clName);
			
			String className = propTmp.getProperty(clName);
            
            log.info("className: " + className);
            Object pluginInstance = PluginsManager.instancePluginByClassName(className,
                    Constants.PINBALADMIN_PROPERTY_BASE, propTmp);

//            Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin,
//                    Constants.PINBALADMIN_PROPERTY_BASE, propTmp);

            if (pluginInstance == null) {
                throw new I18NException("plugin.donotinstantiateplugin.userinfo");
            }
            
            switch (tipusPlugin) {
				case OTAE:
					userInfoPluginOtae = (IUserInformationPlugin) pluginInstance;
					userInfoPlugin = userInfoPluginOtae;
					break;
				case LDAP:
					userInfoPluginCaibLdap = (IUserInformationPlugin) pluginInstance;
					userInfoPlugin = userInfoPluginCaibLdap;
					break;
				case SOFFID:
					userInfoPluginCaibSoffid = (IUserInformationPlugin) pluginInstance;
					userInfoPlugin = userInfoPluginCaibSoffid;
					break;
				default:
					throw new I18NException("plugin.tipus.desconegut");
			}
        }else {
			log.info("userInfoPlugin ja existeix. " + userInfoPlugin.getClass().getName());
        }
        return userInfoPlugin;
    }
	
	
	public static IEstructuraOrganitzativaPlugin getEstructuraOrganitzativaPlugin(boolean debug, boolean caib)
			throws I18NException {
		log.info("Plugin EstructuraOrganitzativa: caib=" + caib);

		IEstructuraOrganitzativaPlugin estructuraOrgPlugin = caib ? estructuraOrgPluginCaib : estructuraOrgPluginOtae;

		log.info("estructuraOrgPlugin inicial: " + estructuraOrgPlugin);

		if (estructuraOrgPlugin == null) {
			
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
			
			String className = propTmp.getProperty(ESTRUCTURAORG_PLUGIN_KEY + (caib ? ".caib" : ".otae"));
			log.info("className: " + className);
			Object pluginInstance = PluginsManager.instancePluginByClassName(className,
					Constants.PINBALADMIN_PROPERTY_BASE, propTmp);
			
			if (pluginInstance == null) {
				throw new I18NException("plugin.donotinstantiateplugin.estructuraorganitzativa");
			}
			
			if (caib) {
				estructuraOrgPluginCaib = (IEstructuraOrganitzativaPlugin) pluginInstance;
				estructuraOrgPlugin = estructuraOrgPluginCaib;
			} else {
				estructuraOrgPluginOtae = (IEstructuraOrganitzativaPlugin) pluginInstance;
				estructuraOrgPlugin = estructuraOrgPluginOtae;
			}

		}

		return estructuraOrgPlugin;
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
