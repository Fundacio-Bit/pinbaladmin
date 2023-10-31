package org.fundaciobit.pinbaladmin.commons.utils;

/**
 *
 * @author anadal
 *
 */
public interface Constants {

    public static final String PINBALADMIN_PROPERTY_BASE = "org.fundaciobit.pinbaladmin.";
    public static final String PINBALADMIN_QUEESTICFENT_PROPERTY_BASE = PINBALADMIN_PROPERTY_BASE + "queesticfent.bd.";
    public static final String PINBALADMIN_PINBALAPI_PROPERTY_BASE = PINBALADMIN_PROPERTY_BASE + "pinbalapi.";

    public static final String SECURITY_DOMAIN = "seycon";

    public static final String MAIL_SERVICE = "java:/org.fundaciobit.pinbaladmin.mail";

    public static final String MAIL_QUEUE = "jms/PinbalAdminMailsQueue";

    public static final String PAD_ADMIN = "PAD_ADMIN";
    public static final String PAD_USER = "PAD_USER";
    public static final String PAD_WS = "PAD_WS";

    public static final String ROLE_EJB_FULL_ACCESS = PAD_ADMIN;
    public static final String ROLE_EJB_BASIC_ACCESS = PAD_USER;
    public static final String ROLE_EJB_WS_ACCESS = PAD_WS;

    // ROLE ADMIN
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    // ROLE USER
    public static final String ROLE_USER = "ROLE_USER";

    public static final int ESTATTIQUET_PENDENT = 1;
    public static final int ESTATTIQUET_ASSIGNAT = 2;
    public static final int ESTATTIQUET_PAUSAT = 3;
    public static final int ESTATTIQUET_ARREGLAT = 4;
    public static final int ESTATTIQUET_NO_ARREGLAT = 5;

    /**
     * Tipus de consentiment que poden acceptar els Serveis, o bé 'No oposició /
     * Llei' o bé 'Sí / Llei'
     */
    public static final int TIPUSCONSENTIMENT_NOOP_LLEI = 0;
    public static final int TIPUSCONSENTIMENT_SI_LLEI = 1;

    /**
     * Tipus EVENTs
     */
    public static final int EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT = -1; // PRIVAT_TRAMITADOR
    public static final int EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC = 1; // PUBLIC_TRAMITADOR
    public static final int EVENT_TIPUS_COMENTARI_CONTACTE = 2; // PUBLIC_CONTACTE
    public static final int EVENT_TIPUS_TIQUET_MINHAP = -2; // PRIVAT_TRAMITADOR
    public static final int EVENT_TIPUS_CONSULTA_A_CEDENT = -3; // PRIVAT_TRAMITADOR CAP A CEDENT
    public static final int EVENT_TIPUS_CEDENT_RESPOSTA = 3; // PUBLIC_RESPOSTA DE CEDENT

    /**
     * Estat de Incidències Tecniques
     */
    public static final int ESTAT_INCIDENCIA_OBERTA = 0;
    public static final int ESTAT_INCIDENCIA_PENDENT_DE_TERCER = 1;
    public static final int ESTAT_INCIDENCIA_TANCADA = 2;

    public static final int INCIDENCIA_TIPUS_TECNICA = 1; // "Tècnica"));
    public static final int INCIDENCIA_TIPUS_CONSULTA = 2;
    public static final int INCIDENCIA_TIPUS_INTEGRACIONS = 3;
    public static final int INCIDENCIA_TIPUS_ROLEPERMISOS = 4;

    public static final long SOLICITUD_ESTAT_SENSE_ESTAT = -1;
    public static final long SOLICITUD_ESTAT_PENDENT = 10;
    public static final long SOLICITUD_ESTAT_PENDENT_Firma_Director = 15;
    public static final long SOLICITUD_ESTAT_PENDENT_AUTORITZAR = 20;
    public static final long SOLICITUD_ESTAT_ESMENES = 30;
    public static final long SOLICITUD_ESTAT_AUTORITZAT = 40;
    public static final long SOLICITUD_ESTAT_PENDENT_PINFO = 50;
    public static final long SOLICITUD_ESTAT_TANCAT = 60;
    
    
    public static final int DOCUMENT_SOLICITUD_ALTRES = 0;
    public static final int DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF = 1;
    public static final int DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_ODT = 2;
    public static final int DOCUMENT_SOLICITUD_EXCEL_SERVEIS = 3;

}
