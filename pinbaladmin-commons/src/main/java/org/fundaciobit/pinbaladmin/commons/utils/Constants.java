package org.fundaciobit.pinbaladmin.commons.utils;

/**
 *
 * @author anadal
 *
 */
public interface Constants {

    public static final String PINBALADMIN_PROPERTY_BASE = "org.fundaciobit.pinbaladmin.";
    public static final String PINBALADMIN_QUEESTICFENT_PROPERTY_BASE = PINBALADMIN_PROPERTY_BASE + "queesticfent.";
    public static final String PINBALADMIN_PINBALAPI_PROPERTY_BASE = PINBALADMIN_PROPERTY_BASE + "pinbalapi.";

    public static final String SECURITY_DOMAIN = "seycon";

    public static final String MAIL_SERVICE = "java:/org.fundaciobit.pinbaladmin.mail";

    public static final String MAIL_QUEUE = "jms/PinbalAdminMailsQueue";

    // NOMBRES DE SISTEMAS
    public static final String SISTEMA_PINBALADMIN = "PinbalAdmin";
    public static final String SISTEMA_PORTAFIB = "PortaFIB";

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
    public static final int EVENT_TIPUS_COMENTARI_SUPORT = -2; // PUBLIC SUPORT
    public static final int EVENT_TIPUS_CONSULTA_A_CEDENT = -3; // PRIVAT_TRAMITADOR CAP A CEDENT
    public static final int EVENT_TIPUS_CEDENT_RESPOSTA = 3; // PUBLIC_RESPOSTA DE CEDENT

    // Estat de Incidències Tecniques
    public static final int ESTAT_INCIDENCIA_OBERTA = 0;
    public static final int ESTAT_INCIDENCIA_PENDENT_DE_TERCER = 1;
    public static final int ESTAT_INCIDENCIA_TANCADA = 2;

    public static final int INCIDENCIA_TIPUS_TECNICA = 1; // "Tècnica"));
    public static final int INCIDENCIA_TIPUS_CONSULTA = 2;
    public static final int INCIDENCIA_TIPUS_INTEGRACIONS = 3;
    public static final int INCIDENCIA_TIPUS_ROLEPERMISOS = 4;

	public static final int[] TIPUS_INCIDENCIES = { INCIDENCIA_TIPUS_TECNICA, INCIDENCIA_TIPUS_CONSULTA,
			INCIDENCIA_TIPUS_INTEGRACIONS, INCIDENCIA_TIPUS_ROLEPERMISOS };

    // Estats de una solicitud
    public static final long SOLI_ESTAT_SENSE_ESTAT = -1;
    public static final long SOLI_ESTAT_FUSIONADA_REVISAR = -4;
    public static final long SOLI_ESTAT_FUSIONADA = -5;
    
    public static final long SOLI_ESTAT_PENDENT_DISTRIBUCIO = 5;
    
 //   public static final long SOLI_ESTAT_PENDENT = 10;
    public static final long SOLI_ESTAT_PENDENT_Enviar_Director = 11;
    public static final long SOLI_ESTAT_PENDENT_Enviar_Cedents = 12;
    
    public static final long SOLI_ESTAT_PENDENT_Firma_Director = 15;
    public static final long SOLI_ESTAT_PENDENT_Firma_Cedent = 16;

    public static final long SOLI_ESTAT_PENDENT_ENVIAR_MADRID = 19;

    //-------  ENVIAM ALTA A MADRID --------------
    public static final long SOLI_ESTAT_PENDENT_AUTORITZAR = 20;
    public static final long SOLI_ESTAT_ERROR_ENVIANT_MADRID = 44;

    public static final long SOLI_ESTAT_PENDENT_AUTORITZAR_Manual = 21;

    //PENDENT ESMENAR ALTA
    //PENDENT REVISAR ESMENA ALTA
    public static final long SOLI_ESTAT_AUTORITZAT = 40;
    public static final long SOLI_ESTAT_AUTORITZAT_Manual = 41;
    public static final long SOLI_ESTAT_AUTORITZAT_Parcial = 39;
    
    //------ ENVIAM MODIFICACIO A MADRID --------
//    public static final long SOLI_ESTAT_PENDENT_REVISAR_MODIFICACIO = 70;
//    public static final long SOLI_ESTAT_PENDENT_ENVIAR_MODIFICACIO_MADRID = 80;
//    public static final long SOLI_ESTAT_PENDENT_AUTORITZAR_MODIFICACIO= 90;
//    public static final long SOLI_ESTAT_AUTORITZAT_ERROR_ENVIANT_MADRID= 92;
    //PENDENT ESMENAR MODIFICACIO
    //PENDENT REVISAR ESMENA MODIFICACIO
    
    //Antic estat esmenes. Solicitants han d'arreglar coses.
    public static final long SOLI_ESTAT_ESMENES = 30;
    public static final long SOLI_ESTAT_ESMENA_PENDENT_CONTACTE = 31;
    public static final long SOLI_ESTAT_ESMENA_AVISAR_CONTACTE= 32;
    public static final long SOLI_ESTAT_CANVI_PENDENT_REVISAR = 33;
//    public static final long SOLI_ESTAT_AUTORITZAT_ESMENES = 42;
    
    public static final long SOLI_ESTAT_REVISIO = 100;
    public static final long SOLI_ESTAT_DENEGADA = 101;
    
    //Estats que no s'utilitzen.
//    public static final long SOLI_ESTAT_PENDENT_PINFO = 50;
    public static final long SOLI_ESTAT_TANCAT = 60;
    
    
//    
//	public static final long[] ESTATS_SOLI = { SOLI_ESTAT_SENSE_ESTAT, SOLI_ESTAT_PENDENT_DISTRIBUCIO,
//			SOLI_ESTAT_PENDENT_Enviar_Director, SOLI_ESTAT_PENDENT_Enviar_Cedents, SOLI_ESTAT_PENDENT_Firma_Director,
//			SOLI_ESTAT_PENDENT_Firma_Cedent, SOLI_ESTAT_PENDENT_ENVIAR_MADRID, SOLI_ESTAT_PENDENT_AUTORITZAR, SOLI_ESTAT_PENDENT_AUTORITZAR_Manual,
//			SOLI_ESTAT_ESMENES, SOLI_ESTAT_ESMENA_PENDENT_CONTACTE, SOLI_ESTAT_ESMENA_AVISAR_CONTACTE, SOLI_ESTAT_ESMENA_PENDENT_REVISAR, SOLI_ESTAT_AUTORITZAT, SOLI_ESTAT_AUTORITZAT_Manual, SOLI_ESTAT_AUTORITZAT_Parcial, SOLI_ESTAT_AUTORITZAT_ESMENES, SOLI_ESTAT_ERROR_ENVIANT_MADRID,
//			SOLI_ESTAT_PENDENT_PINFO, SOLI_ESTAT_TANCAT, SOLI_ESTAT_PENDENT_REVISAR_MODIFICACIO,
//			SOLI_ESTAT_PENDENT_ENVIAR_MODIFICACIO_MADRID, SOLI_ESTAT_PENDENT_AUTORITZAR_MODIFICACIO,
//			SOLI_ESTAT_AUTORITZAT_ERROR_ENVIANT_MADRID, SOLI_ESTAT_REVISIO, SOLI_ESTAT_DENEGADA };
    
	public static final long[] ESTATS_SOLI = { SOLI_ESTAT_SENSE_ESTAT, SOLI_ESTAT_FUSIONADA_REVISAR, SOLI_ESTAT_FUSIONADA, SOLI_ESTAT_PENDENT_DISTRIBUCIO,
			SOLI_ESTAT_PENDENT_Enviar_Director, SOLI_ESTAT_PENDENT_Enviar_Cedents, SOLI_ESTAT_PENDENT_Firma_Director,
			SOLI_ESTAT_PENDENT_Firma_Cedent, SOLI_ESTAT_PENDENT_ENVIAR_MADRID, SOLI_ESTAT_PENDENT_AUTORITZAR, SOLI_ESTAT_PENDENT_AUTORITZAR_Manual,
			SOLI_ESTAT_ESMENES, SOLI_ESTAT_ESMENA_PENDENT_CONTACTE, SOLI_ESTAT_ESMENA_AVISAR_CONTACTE, SOLI_ESTAT_CANVI_PENDENT_REVISAR, SOLI_ESTAT_AUTORITZAT, SOLI_ESTAT_AUTORITZAT_Manual, SOLI_ESTAT_AUTORITZAT_Parcial,  SOLI_ESTAT_ERROR_ENVIANT_MADRID,
			 SOLI_ESTAT_TANCAT, SOLI_ESTAT_REVISIO, SOLI_ESTAT_DENEGADA };
    
	//Estats Soliciud Servei
    public static final long ESTAT_SOLICITUD_SERVEI_SENSE_ESTAT = -1L;
    public static final long ESTAT_SOLICITUD_SERVEI_PENDENT_ESMENES = 1L;
    public static final long ESTAT_SOLICITUD_SERVEI_REBUT = 10L;
    public static final long ESTAT_SOLICITUD_SERVEI_PASSAT_A_FIRMA = 20L;
    public static final long ESTAT_SOLICITUD_SERVEI_FIRMAT = 30L;
    public static final long ESTAT_SOLICITUD_SERVEI_PENDENT_AUTORITZAR = 40L;
    public static final long ESTAT_SOLICITUD_SERVEI_AUTORITZAT = 50L;
    public static final long ESTAT_SOLICITUD_SERVEI_DESESTIMAT = 60L;
    public static final long ESTAT_SOLICITUD_SERVEI_DISCONTINUAT = 80L;
    public static final long ESTAT_SOLICITUD_SERVEI_NO_DISPONIBLE = 90L;
    
    public static final long ESTAT_SOLICITUD_SERVEI_MODIFICACIO_NORMA = 100L;
    public static final long ESTAT_SOLICITUD_SERVEI_MODIFICACIO_SERVEI = 105L;
    public static final long ESTAT_SOLICITUD_SERVEI_PENDENENT_ENVIAR_MODIFICACIO_MADRID = 110;
    public static final long ESTAT_SOLICITUD_SERVEI_PENDENT_MODIFICACIO_MADRID  = 115L;
   
    
	public static final long[] ESTATS_SOLICITUD_SERVEI = { ESTAT_SOLICITUD_SERVEI_SENSE_ESTAT,
			ESTAT_SOLICITUD_SERVEI_PENDENT_ESMENES, ESTAT_SOLICITUD_SERVEI_REBUT, ESTAT_SOLICITUD_SERVEI_PASSAT_A_FIRMA,
			ESTAT_SOLICITUD_SERVEI_FIRMAT, ESTAT_SOLICITUD_SERVEI_PENDENT_AUTORITZAR, ESTAT_SOLICITUD_SERVEI_AUTORITZAT,
			ESTAT_SOLICITUD_SERVEI_DESESTIMAT, ESTAT_SOLICITUD_SERVEI_DISCONTINUAT,
			ESTAT_SOLICITUD_SERVEI_NO_DISPONIBLE, ESTAT_SOLICITUD_SERVEI_MODIFICACIO_NORMA,
			ESTAT_SOLICITUD_SERVEI_MODIFICACIO_SERVEI, ESTAT_SOLICITUD_SERVEI_PENDENENT_ENVIAR_MODIFICACIO_MADRID,
			ESTAT_SOLICITUD_SERVEI_PENDENT_MODIFICACIO_MADRID };	
	
    public static final Long DOCUMENT_SOLICITUD_ALTRES = 0L;
    public static final Long DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF = 1L;
    public static final Long DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_ODT = 2L;
    public static final Long DOCUMENT_SOLICITUD_EXCEL_SERVEIS = 3L;
    public static final Long DOCUMENT_SOLICITUD_CONSENTIMENT = 4L;
//    public static final Long DOCUMENT_SOLICITUD_CONSENTIMENT_NOOP = 4L;
//    public static final Long DOCUMENT_SOLICITUD_CONSENTIMENT_SI = 5L;
    public static final Long DOCUMENT_SOLICITUD_ADUNJT = 6L;
    public static final Long DOCUMENT_SOLICITUD_XML_SOLI = 7L;
    

    /**
     * Estat de Solicituds a PINBAL
     */
    public static final Long ESTAT_PINBAL_null= -4L;
    public static final Long ESTAT_PINBAL_MANUAL= -3L;
    public static final Long ESTAT_PINBAL_ERROR= -2L;
    public static final Long ESTAT_PINBAL_NO_SOLICITAT = -1L;
    public static final Long ESTAT_PINBAL_PENDENT_TRAMITAR = 0L;
    public static final Long ESTAT_PINBAL_DESISTIT= 1L;
    public static final Long ESTAT_PINBAL_APROVAT= 2L;
    public static final Long ESTAT_PINBAL_NO_APROVAT= 3L;
    public static final Long ESTAT_PINBAL_PENDENT_SUBSANACIO= 4L;
    public static final Long ESTAT_PINBAL_SUBSANAT= 5L;
    public static final Long ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT= 6L;
    public static final Long ESTAT_PINBAL_AUTORITZAT= 7L;
    public static final Long ESTAT_PINBAL_DESESTIMAT= 8L;
    public static final Long ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO= 9L;

    public static final Long[] ESTATS_PINBAL = { ESTAT_PINBAL_null, ESTAT_PINBAL_MANUAL, ESTAT_PINBAL_ERROR, ESTAT_PINBAL_NO_SOLICITAT,
            ESTAT_PINBAL_PENDENT_TRAMITAR, ESTAT_PINBAL_DESISTIT, ESTAT_PINBAL_APROVAT, ESTAT_PINBAL_NO_APROVAT,
            ESTAT_PINBAL_PENDENT_SUBSANACIO, ESTAT_PINBAL_SUBSANAT, ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT,
            ESTAT_PINBAL_AUTORITZAT, ESTAT_PINBAL_DESESTIMAT, ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO };    
    
    
    public static final String CONSENTIMENT_TIPUS_SI = "si";
    public static final String CONSENTIMENT_TIPUS_LLEI = "llei";
    public static final String CONSENTIMENT_TIPUS_NOOP = "noop";
    
    public static final String[] CONSENTIMENTS_TIPUS = { CONSENTIMENT_TIPUS_SI, CONSENTIMENT_TIPUS_LLEI, CONSENTIMENT_TIPUS_NOOP };

    public static final String CONSENTIMENT_PUBLICAT = "publicat";
    public static final String CONSENTIMENT_ADJUNT = "adjunt";
    
    public static final String[] CONSENTIMENTS = { CONSENTIMENT_PUBLICAT, CONSENTIMENT_ADJUNT };
    
    
    public static final Long PINFO_ALTA = 1L;
    public static final Long PINFO_BAIXA = 0L;

    public static final Long ESTAT_PINFO_INICIANT = -2L;
    public static final Long ESTAT_PINFO_ERROR = -1L;
    public static final Long ESTAT_PINFO_CREANT = 0L;
    public static final Long ESTAT_PINFO_PENDENT_FIRMA = 1L;
    public static final Long ESTAT_PINFO_PENDENT_TRAMITAR = 2L;
    public static final Long ESTAT_PINFO_TRAMITAT = 3L;
    public static final Long ESTAT_PINFO_NOTIFICAT= 4L;
    
    public static final Long ESTAT_INCIDENCIA_PINFO_PENDENT_FIRMA = 10 + ESTAT_PINFO_PENDENT_FIRMA;
    public static final Long ESTAT_INCIDENCIA_PINFO_PENDENT_TRAMITAR = 10 + ESTAT_PINFO_PENDENT_TRAMITAR;
    public static final Long ESTAT_INCIDENCIA_PINFO_TRAMITAT = 10 + ESTAT_PINFO_TRAMITAT;
    public static final Long ESTAT_INCIDENCIA_PINFO_NOTIFICAT = 10 + ESTAT_PINFO_NOTIFICAT;
    
    public static final Long[] ESTATS_PINFO = { ESTAT_PINFO_ERROR, ESTAT_PINFO_CREANT, ESTAT_PINFO_PENDENT_FIRMA, ESTAT_PINFO_PENDENT_TRAMITAR, ESTAT_PINFO_TRAMITAT, ESTAT_PINFO_NOTIFICAT };

	public static final Long ESTAT_MODIFICACIO_SOLICITUD_CREACIO = 0L;
	public static final Long ESTAT_MODIFICACIO_SOLICITUD_ENVIADA = 1L;
	public static final Long ESTAT_MODIFICACIO_SOLICITUD_ACEPTADA = 2L;
	public static final Long ESTAT_MODIFICACIO_SOLICITUD_REBUTJADA = 3L;

	public static final Long[] ESTATS_MODIFICACIO_SOLICITUD = { ESTAT_MODIFICACIO_SOLICITUD_CREACIO,
			ESTAT_MODIFICACIO_SOLICITUD_ENVIADA, ESTAT_MODIFICACIO_SOLICITUD_ACEPTADA,
			ESTAT_MODIFICACIO_SOLICITUD_REBUTJADA };
    
    public static final int ESTAT_AUTORITZACIO_ALTA_SOLICITADA = 1;
    public static final int ESTAT_AUTORITZACIO_ESMENAR_ALTA= 2;
    public static final int ESTAT_AUTORITZACIO_AUTORITZAT = 3;
    public static final int ESTAT_AUTORITZACIO_MODIFICACIO_SOLICITADA = 4;
    public static final int ESTAT_AUTORITZACIO_ESMENAR_MODIFICACIO= 5;
    
	public static final int[] ESTATS_AUTORITZACIO = { ESTAT_AUTORITZACIO_ALTA_SOLICITADA,
			ESTAT_AUTORITZACIO_ESMENAR_ALTA, ESTAT_AUTORITZACIO_AUTORITZAT, ESTAT_AUTORITZACIO_MODIFICACIO_SOLICITADA,
			ESTAT_AUTORITZACIO_ESMENAR_MODIFICACIO };
	
	
	
}
