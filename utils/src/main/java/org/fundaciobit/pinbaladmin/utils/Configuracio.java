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
    return System.getProperty(PINBALADMIN_PROPERTY_BASE + "encryptkey", "0123456789123456").getBytes();
  }

  public static Long getMaxUploadSizeInBytes() {
    return Long.getLong(PINBALADMIN_PROPERTY_BASE + "maxuploadsizeinbytes");
  }

  public static Long getMaxFitxerAdaptatSizeInBytes() {
    return Long.getLong(PINBALADMIN_PROPERTY_BASE + "maxfitxeradaptatsizeinbytes");
  }

}
