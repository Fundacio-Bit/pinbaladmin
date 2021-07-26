package org.fundaciobit.pinbaladmin.utils;

import java.io.File;

/**
 * 
 * @author anadal
 * 
 */
public class Configuracio implements Constants {

  public static boolean isCAIB() {
    return Boolean.getBoolean(PINBALADMIN_PROPERTY_BASE + "iscaib");
  }

  public static File getFilesDirectory() {
    String path = System.getProperty(PINBALADMIN_PROPERTY_BASE + "filesdirectory");
    return new File(path);
  }

  public static boolean isDesenvolupament() {
    return Boolean.getBoolean(PINBALADMIN_PROPERTY_BASE + "development");
  }

  public static String getVersioPinbal() {
    return System.getProperty(PINBALADMIN_PROPERTY_BASE + "versiopinbal");
  }

  public static String getAppUrl() {
    return System.getProperty(PINBALADMIN_PROPERTY_BASE + "url");
  }

  public static String getAppEmail() {
    return System.getProperty(PINBALADMIN_PROPERTY_BASE + "email.from");
  }

  public static String getAppName() {
    return System.getProperty(PINBALADMIN_PROPERTY_BASE + "name", "PinbalAdmin");
  }

  public static String getDefaultLanguage() {
    return System.getProperty(PINBALADMIN_PROPERTY_BASE + "defaultlanguage", "ca");
  }

  public static byte[] getEncryptKey() {
    return System.getProperty(PINBALADMIN_PROPERTY_BASE + "encryptkey", "0123456789123456")
        .getBytes();
  }

  public static Long getMaxUploadSizeInBytes() {
    return Long.getLong(PINBALADMIN_PROPERTY_BASE + "maxuploadsizeinbytes");
  }

  public static Long getMaxFitxerAdaptatSizeInBytes() {
    return Long.getLong(PINBALADMIN_PROPERTY_BASE + "maxfitxeradaptatsizeinbytes");
  }

  public static boolean isTipusTramitador(int tipus) {
    switch (tipus) {

    case EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT: // PRIVAT - TRAMITADOR
      return true;
    case EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC: // PUBLIC - TRAMITADOR
      return true;
    default:
    case EVENT_TIPUS_COMENTARI_CONTACTE: // PUBLIC - CONTACTE
      return false;
    case EVENT_TIPUS_TIQUET_MINHAP: // PRIVAT - TRAMITADOR
      return true;
    }

  }

  public static boolean isTipusEventPublic(int tipus) {
    return tipus > 0;
  }
  
  
  public static String getTemplateFormulari() {
    return System.getProperty(PINBALADMIN_PROPERTY_BASE + "template_formulari");
  }
  
  
  public static String getTemplateServeisExcel() {
    return System.getProperty(PINBALADMIN_PROPERTY_BASE + "template_serveis_excel");
  }

  public static String getCAIDSeleniumUrl() {
    return System.getProperty(PINBALADMIN_PROPERTY_BASE + "caidseleniumurl");
  }
  
}
