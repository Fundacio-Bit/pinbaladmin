package org.fundaciobit.pinbaladmin.logic.utils;

import java.io.File;
import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager.TipusPluginUserInfo;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.jboss.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

            // Miram de llegir la llista d'usuaris emprant fitxers JSON de cache. 
            // Si no es poden llegir, llavors fem la consulta al UserInformation.
            boolean errorLlegintFitxers = llegirUsuarisDeFitxers(responsablesList, userInfoList);
            if (!errorLlegintFitxers) {                
                return;
            }
                  
            
            
            
            
            
            

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
            
            
            guardarResultatAFitxer(responsablesList, userInfoList);
      
            
            
            
            

            log.info("Total responsables/userInfo " + rol + ": " + responsablesList.size());
        } catch (Exception e) {
            log.error("Error cercant usuaris amb rol " + rol + ": " + e.getMessage());
        }
    }

    public static boolean llegirUsuarisDeFitxers(List<Responsable> responsablesList, List<UserInfo> userInfoList) {
        
        boolean errorLlegintFitxers = false;
        String dirCache = Configuracio.getCacheUsuarisDir();
        if (dirCache != null && !dirCache.isEmpty() && new File(dirCache).exists()) {
            // Llegir responsablesList i userInfoList de fitxers JSON si existeixen
            String responsablesFilePath = dirCache + "/" + RESPONSABLES_FILENAME;
            String userInfoFilePath = dirCache + "/" + USERSINFO_FILENAME;

            Gson gson = new Gson();
            
            

            // Llegir responsablesList
            File responsablesFile = new File(responsablesFilePath);
            if (responsablesFile.exists()) {
                try (java.io.FileReader reader = new java.io.FileReader(responsablesFile)) {
                    Responsable[] responsablesArray = gson.fromJson(reader, Responsable[].class);
                    if (responsablesArray != null) {
                        for (Responsable r : responsablesArray) {
                            responsablesList.add(r);
                        }
                    }
                    log.info("Llista de responsables carregada des de " + responsablesFilePath);
                } catch (Exception e) {
                    log.error("Error llegint la llista de responsables des de " + responsablesFilePath + ": "
                            + e.getMessage());
                    errorLlegintFitxers = true;
                }
            } else {
                errorLlegintFitxers = true;
            }

            // Llegir userInfoList
            File userInfoFile = new File(userInfoFilePath);
            if (userInfoFile.exists()) {
                try (java.io.FileReader reader = new java.io.FileReader(userInfoFile)) {
                    UserInfo[] userInfoArray = gson.fromJson(reader, UserInfo[].class);
                    if (userInfoArray != null) {
                        for (UserInfo ui : userInfoArray) {
                            userInfoList.add(ui);
                        }
                    }
                    log.info("Llista de userInfo carregada des de " + userInfoFilePath);
                } catch (Exception e) {
                    log.error("Error llegint la llista de userInfo des de " + userInfoFilePath + ": "
                            + e.getMessage());
                    errorLlegintFitxers = true;
                }
            } else {
                errorLlegintFitxers = true;
            }
            
            
            if (!errorLlegintFitxers && !responsablesList.isEmpty() && !userInfoList.isEmpty()) {
                responsablesListCache = responsablesList;
                userInfoListCache = userInfoList;
                lastCacheTime = System.currentTimeMillis();
                log.info("Llistes carregades correctament des de fitxers. No cal consultar el UserInformation.");
                
            } else {
                log.info("No s'han pogut carregar correctament les llistes des de fitxers. Es procedirà a consultar el UserInformation.");
                errorLlegintFitxers = true;
            }
        }
        
        return errorLlegintFitxers;
    }

    public static void guardarResultatAFitxer(List<Responsable> responsablesList, List<UserInfo> userInfoList) {
        String dirCache = Configuracio.getCacheUsuarisDir();
        if (dirCache != null && !dirCache.isEmpty() && new File(dirCache).exists()) {
            
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            
            Gson gson = gsonBuilder.create();
            
            String responsablesFilePath = dirCache + "/" + RESPONSABLES_FILENAME;
            String userInfoFilePath = dirCache + "/" + USERSINFO_FILENAME;

            // Guardar responsablesList a un fitxer JSON
            try (java.io.FileWriter writer = new java.io.FileWriter(responsablesFilePath)) {
                gson.toJson(responsablesList, writer);
                log.info("Llista de responsables guardada a " + responsablesFilePath);
            } catch (Exception e) {
                log.error("Error guardant la llista de responsables a " + responsablesFilePath + ": " + e.getMessage());
            }

            // Guardar userInfoList a un fitxer JSON
            try (java.io.FileWriter writer = new java.io.FileWriter(userInfoFilePath)) {
                gson.toJson(userInfoList, writer);
                log.info("Llista de userInfo guardada a " + userInfoFilePath);
            } catch (Exception e) {
                log.error("Error guardant la llista de userInfo a " + userInfoFilePath + ": " + e.getMessage());
            }
        } else {
            log.warn("No s'ha definit el directori de cache. No es guardaran les llistes a fitxers.");
        }
    }
    
    
    
    public static final String RESPONSABLES_FILENAME = "responsables.json";
    public static final String USERSINFO_FILENAME = "usersInfo.json";
    
    

    public static boolean isValidNIF(String nif) {
        // RegEx para saber si el NIF es valido.
        String nifRegex = "^[0-9]{8}[A-Za-z]$";
        return nif.matches(nifRegex);
    }
}
