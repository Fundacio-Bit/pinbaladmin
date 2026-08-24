package org.fundaciobit.pinbaladmin.logic.utils;

import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager.TipusPluginUserInfo;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.jboss.logging.Logger;

/**
 * 
 * @author anadal (u80067)
 * 24 ago 2026 12:17:11
 */
public class UsersWithPfiUserCache {
    
    private static final Logger log = Logger.getLogger(UsersWithPfiUserCache.class);

    private static List<UserInfo> userInfoListCache = null;

    private static List<Responsable> responsablesListCache = null;

    private static long lastCacheTime = 0;

    public static synchronized List<Responsable> getLlistaResponsables()
            throws I18NException {

        // Comprovar si fa manco de 24 hores que s'ha actualitzat la cache. Si és així, retornar la cache.
        long currentTime = System.currentTimeMillis();
        long cacheDuration = 24 * 60 * 60 * 1000; //  24 hores en mil·lisegons

        if ((currentTime - lastCacheTime) < cacheDuration) {
            log.info("Retornant responsables de la cache. Temps des de l'última actualització: "
                    + (currentTime - lastCacheTime) + " ms");
            return responsablesListCache;
        }

        initResponsablesAndUserInfoLists();

        return responsablesListCache;
    }

    public static synchronized List<UserInfo> getLlistaUserInfo() throws I18NException {

        // Comprovar si fa manco de 24 hores que s'ha actualitzat la cache. Si és així, retornar la cache.
        long currentTime = System.currentTimeMillis();
        long cacheDuration = 24 * 60 * 60 * 1000; //  24 hores en mil·lisegons

        if ((currentTime - lastCacheTime) < cacheDuration) {
            log.info("Retornant userInfo de la cache. Temps des de l'última actualització: "
                    + (currentTime - lastCacheTime) + " ms");
            return userInfoListCache;
        }

        initResponsablesAndUserInfoLists();

        return userInfoListCache;
    }

    private static void initResponsablesAndUserInfoLists() {
        final String rol = "PFI_USER";
        try {

            List<Responsable> responsablesList = new java.util.ArrayList<Responsable>();
            List<UserInfo> userInfoList = new java.util.ArrayList<UserInfo>();

            final boolean debug = false;
            // boolean caib = true;
            IUserInformationPlugin pluginUserInfo = PinbalAdminPluginsManager.getUserInformationPluginInstance(debug,
                    TipusPluginUserInfo.LDAP);

            // String rol = "usuari-tipus-I";

            UserInfo[] userInfo = pluginUserInfo.getUserInfoByRol(rol);

            log.info("Usuaris amb rol " + rol + ": " + userInfo.length);

            for (UserInfo ui : userInfo) {

                String username = ui.getUsername();

                if (username == null || username.isEmpty()) {
                    log.info("L'usuari null o '' amb rol " + rol
                            + " no té username. No l'afegim a la llista de responsables.");
                    continue;
                }

                // Eliminar usuaris que no són persones
                if (username.startsWith("$") || username.startsWith("e") || username.startsWith("x")) {
                    continue;
                }

                String nif = ui.getAdministrationID();

                if (nif == null || nif.isEmpty()) {
                    log.info("L'usuari " + username + " no té NIF. No l'afegim a les llistes de cache");
                    continue;
                }

                if (!isValidNIF(nif)) {
                    log.info("L'usuari " + username + " té un NIF invàlid (" + nif
                            + "). No l'afegim a les llistes de cache");

                    continue;
                }

                String nom = ui.getName();
                String ape1 = ui.getSurname1();
                String ape2 = ui.getSurname2();
                String telefon = ui.getPhoneNumber();
                String mail = ui.getEmail();
                String nomOcult = ui.getFullName();

                //log.info(nif + " - " + nom + " " + ape1 + " " + ape2 + " - " + username + " - " + mail + " - "
                //        + nomOcult);

                Responsable responsable = new Responsable(nif, nom, ape1, ape2, rol, telefon, mail, nomOcult, username);

                responsablesList.add(responsable);
                userInfoList.add(ui);
            }

            responsablesListCache = responsablesList;
            userInfoListCache = userInfoList;
            lastCacheTime = System.currentTimeMillis();

            log.info("Total responsables/userInfo " + rol + ": " + responsablesList.size());
        } catch (Exception e) {
            log.error("Error cercant usuaris amb rol " + rol + ": " + e.getMessage());
        }
    }

    public static boolean isValidNIF(String nif) {
        // RegEx para saber si el NIF es valido.
        String nifRegex = "^[0-9]{8}[A-Za-z]$";
        return nif.matches(nifRegex);
    }
}
