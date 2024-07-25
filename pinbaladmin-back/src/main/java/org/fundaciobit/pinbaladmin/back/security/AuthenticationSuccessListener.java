package org.fundaciobit.pinbaladmin.back.security;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
//import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
//import org.fundaciobit.pinbaladmin.back.security.LoginInfo;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
//import org.fundaciobit.pinbaladmin.back.security.LoginInfo;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

//import org.fundaciobit.pinbaladmin.commons.utils.Constants;
//import org.fundaciobit.pinbaladmin.back.security.LoginException;

/**
 * 
 * @author anadal
 * 
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    protected final static Logger log = Logger.getLogger(AuthenticationSuccessListener.class);
    
    public static final String LOGIN_PLUGIN_KEY = Constants.PINBALADMIN_PROPERTY_BASE + "userinformationplugin";

    public static IUserInformationPlugin loginPlugin = null;


    @Override
    public synchronized void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {

        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication au = sc.getAuthentication();

        if (au == null) {
            // TODO traduccio
            throw new LoginException("NO PUC ACCEDIR A LA INFORMACIO de AUTENTICACIO");
        }

        User user = (User) au.getPrincipal();

        String username = user.getUsername();
        log.info(" =================================================================");
        log.info(" ============ Login Usuari: " + username);

        // Cercam si té el ROLE_USER o ROLE_ADMIN
        Collection<GrantedAuthority> realAuthorities = user.getAuthorities();
        boolean containsRoleUser = false;
        boolean containsRoleAdmin = false;
        for (GrantedAuthority grantedAuthority : realAuthorities) {
            String rol = grantedAuthority.getAuthority();
            log.info("Rol REAL : " + rol);
            if (Constants.ROLE_USER.equals(rol)) {
                containsRoleUser = true;
            }
            if (Constants.ROLE_ADMIN.equals(rol)) {
                containsRoleAdmin = true;
            }
        }
        UserInfo info = null;
        // Si no interessa gestionar informació personal d'usuari, comentar aquest bloc.
        try {
            IUserInformationPlugin plugin = getUserInformationPluginInstance();
            info = plugin.getUserInfoByUserName(username);
        } catch (Throwable e) {
            String msg;
            if (e instanceof I18NException) {
                msg = I18NUtils.getMessage((I18NException) e);
            } else if (e instanceof I18NValidationException) {
                msg = I18NUtils.getMessage((I18NValidationException) e);
            } else {
                msg = e.getMessage();
            }
            msg = "Error llegint informació del plugin de UserInformation: " + msg;
            log.error(msg, e);
        }

        if (log.isDebugEnabled()) {
            log.debug("containsRoleUser: " + containsRoleUser);
            log.debug("containsRoleAdmin: " + containsRoleAdmin);
        }

        // TODO GENAPP Obtenir idioma de l'usuari. Null = idioma per defecte.
        String language = null;

        // TODO GenApp Afegir el codi oportu despres del login

        LoginInfo loginInfo;
        // create a new authentication token
        loginInfo = new LoginInfo(user, username, new HashSet<GrantedAuthority>(realAuthorities), language, info);

        // and set the authentication of the current Session context
        SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());

        log.info(">>>>>> Final del Process d'autenticació.");
        log.info(" =================================================================");

    }
    
    public static IUserInformationPlugin getUserInformationPluginInstance() throws I18NException {
        if (loginPlugin == null) {
//            final String propertyPlugin = LOGIN_PLUGIN_KEY;

            Properties propTmp = Configuracio.getSystemAndFileProperties();

            Set<Object> set = propTmp.keySet();
            for (Object object : set) {
                String key = (String) object;
                String value = propTmp.getProperty(key);
                log.info(key + ": " + value);
            }
            
            String className = propTmp.getProperty(LOGIN_PLUGIN_KEY);
            
            log.info("className: " + className);
            Object pluginInstance = PluginsManager.instancePluginByClassName(className,
                    Constants.PINBALADMIN_PROPERTY_BASE, propTmp);

//            Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin,
//                    Constants.PINBALADMIN_PROPERTY_BASE, propTmp);

            if (pluginInstance == null) {
                throw new I18NException("plugin.donotinstantiateplugin.userinfo");
            }
            loginPlugin = (IUserInformationPlugin) pluginInstance;
        }
        return loginPlugin;
    }

    public static final Comparator<GrantedAuthority> GRANTEDAUTHORITYCOMPARATOR=new Comparator<GrantedAuthority>(){@Override public int compare(GrantedAuthority o1,GrantedAuthority o2){return-o1.getAuthority().compareTo(o2.getAuthority());}};

}
