package org.fundaciobit.pinbaladmin.utils;


/**
 *
 * @author anadal
 *
 */
public interface Constants {

  public static final String PINBALADMIN_PROPERTY_BASE = "org.fundaciobit.pinbaladmin.";

  public static final String SECURITY_DOMAIN = "seycon";

  public static final String MAIL_SERVICE = "java:/org.fundaciobit.pinbaladmin.mail";

  public static final String MAIL_QUEUE = "jms/PinbalAdminMailsQueue";
  
  public static final String PAD_ADMIN = "PAD_ADMIN";

  public static final String PAD_USER = "PAD_USER";
  
  // ROLE ADMIN 
  public static final String ROLE_ADMIN = "ROLE_ADMIN";
  // ROLE USER
  public static final String ROLE_USER = "ROLE_USER";  
  

  public static final int ESTATTIQUET_PENDENT = 1;
  public static final int ESTATTIQUET_ASSIGNAT =2;
  public static final int ESTATTIQUET_PAUSAT = 3;
  public static final int ESTATTIQUET_ARREGLAT =4;
  public static final int ESTATTIQUET_NO_ARREGLAT =5;

  /**
   * Tipus de consentiment que poden acceptar els Serveis, o bé 'No oposició / Llei' o bé 'Sí / Llei'
   */
  public static final int TIPUSCONSENTIMENT_NOOP_LLEI = 0;
  public static final int TIPUSCONSENTIMENT_SI_LLEI = 1;
  
  
  /**
   * Tipus EVENTs
   */  
  public static final int EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT = -1; // PRIVAT - TRAMITADOR
  public static final int EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC = 1;  // PUBLIC - TRAMITADOR
  public static final int EVENT_TIPUS_COMENTARI_CONTACTE = 2; // PUBLIC - CONTACTE
  public static final int EVENT_TIPUS_TIQUET_MINHAP = -2; // PRIVAT - TRAMITADOR
  
  
  /**
   * Estat de Incidències Tecniques
   */  
  public static final int ESTAT_INCIDENCIA_TECNICA_OBERTA = 0;
  public static final int ESTAT_INCIDENCIA_TECNICA_PENDENT_DE_TERCER = 1;
  public static final int ESTAT_INCIDENCIA_TECNICA_TANCADA = 2;

  
  
  
}
