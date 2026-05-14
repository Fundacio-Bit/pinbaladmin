
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface SolicitudFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_solicitud";


  public static final String _TABLE_MODEL = "solicitud";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField SOLICITUDID = new LongField(_TABLE_MODEL, "solicitudID", "solicitudid");  // PK
	 public static final StringField PROCEDIMENTCODI = new StringField(_TABLE_MODEL, "procedimentCodi", "procedimentcodi");
	 public static final StringField CODIDESCRIPTIU = new StringField(_TABLE_MODEL, "codiDescriptiu", "codidescriptiu");
	 public static final StringField CODISIACONV = new StringField(_TABLE_MODEL, "codiSiaConv", "codisiaconv");
	 public static final StringField PROCEDIMENTNOM = new StringField(_TABLE_MODEL, "procedimentNom", "procedimentnom");
	 public static final StringField PROCEDIMENTTIPUS = new StringField(_TABLE_MODEL, "procedimentTipus", "procedimenttipus");
	 public static final LongField ORGANID = new LongField(_TABLE_MODEL, "organid", "organid");
	 public static final LongField ESTATSOLICITUD = new LongField(_TABLE_MODEL, "estatSolicitud", "estatid");
	 public static final StringField EXPEDIENTPID = new StringField(_TABLE_MODEL, "expedientPid", "expedientpid");
	 public static final StringField ENTITATESTATAL = new StringField(_TABLE_MODEL, "entitatEstatal", "entitatestatal");
	 public static final StringField PINFO = new StringField(_TABLE_MODEL, "pinfo", "pinfo");
	 public static final TimestampField DATAINICI = new TimestampField(_TABLE_MODEL, "dataInici", "datainici");
	 public static final TimestampField DATAFI = new TimestampField(_TABLE_MODEL, "dataFi", "datafi");
	 public static final StringField PERSONACONTACTE = new StringField(_TABLE_MODEL, "personaContacte", "personacontacte");
	 public static final StringField PERSONACONTACTEEMAIL = new StringField(_TABLE_MODEL, "personaContacteEmail", "personacontacteemail");
	 public static final StringField RESPONSABLEPROCNOM = new StringField(_TABLE_MODEL, "responsableProcNom", "responsableprocnom");
	 public static final StringField RESPONSABLEPROCEMAIL = new StringField(_TABLE_MODEL, "responsableProcEmail", "responsableprocemail");
	 public static final StringField NOTES = new StringField(_TABLE_MODEL, "notes", "estat");
	 public static final LongField DOCUMENTSOLICITUDID = new LongField(_TABLE_MODEL, "documentSolicitudID", "documentsolicitudid");
	 public static final LongField SOLICITUDXMLID = new LongField(_TABLE_MODEL, "solicitudXmlID", "solicitudxmlid");
	 public static final BooleanField FIRMATDOCSOLICITUD = new BooleanField(_TABLE_MODEL, "firmatDocSolicitud", "firmatdocsolicitud");
	 public static final BooleanField PRODUCCIO = new BooleanField(_TABLE_MODEL, "produccio", "produccio");
	 public static final StringField DENOMINACIO = new StringField(_TABLE_MODEL, "denominacio", "denominacio");
	 public static final StringField DIR3 = new StringField(_TABLE_MODEL, "dir3", "dir3");
	 public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif");
	 public static final StringField CREADOR = new StringField(_TABLE_MODEL, "creador", "creador");
	 public static final StringField OPERADOR = new StringField(_TABLE_MODEL, "operador", "operador");
	 public static final LongField ESTATPINBAL = new LongField(_TABLE_MODEL, "estatpinbal", "estatpinbal");
	 public static final StringField CONSENTIMENT = new StringField(_TABLE_MODEL, "consentiment", "consentiment");
	 public static final StringField URLCONSENTIMENT = new StringField(_TABLE_MODEL, "urlconsentiment", "urlconsentiment");
	 public static final StringField CONSENTIMENTADJUNT = new StringField(_TABLE_MODEL, "consentimentadjunt", "consentimentadjunt");
	 public static final LongField PORTAFIBID = new LongField(_TABLE_MODEL, "portafibID", "portafibid");
	 public static final LongField INFOMADRIDID = new LongField(_TABLE_MODEL, "infomadridid", "infomadridid");
	 public static final TimestampField DATACADUCITAT = new TimestampField(_TABLE_MODEL, "dataCaducitat", "datacaducitat");
	 public static final LongField FITXERCONSENTIMENTID = new LongField(_TABLE_MODEL, "fitxerConsentimentID", "fitxerconsentimentid");
	 public static final LongField CONTACTETITULARID = new LongField(_TABLE_MODEL, "contacteTitularID", "contactetitularid");
	 public static final StringField TITULARFIRMANIF = new StringField(_TABLE_MODEL, "titularFirmaNif", "titularfirmanif");
	 public static final StringField TITULARFIRMANOM = new StringField(_TABLE_MODEL, "titularFirmaNom", "titularfirmanom");
	 public static final StringField TITULARFIRMALLINATGES = new StringField(_TABLE_MODEL, "titularFirmaLlinatges", "titularfirmallinatges");
	 public static final StringField TITULARFIRMAEMAIL = new StringField(_TABLE_MODEL, "titularFirmaEmail", "titularfirmaemail");
	 public static final LongField SOLICITUDFUSIONADAID = new LongField(_TABLE_MODEL, "solicitudFusionadaID", "solicitudfusionadaid");


  public static final Field<?>[] ALL_SOLICITUD_FIELDS = {
    SOLICITUDID,
    PROCEDIMENTCODI,
    CODIDESCRIPTIU,
    CODISIACONV,
    PROCEDIMENTNOM,
    PROCEDIMENTTIPUS,
    ORGANID,
    ESTATSOLICITUD,
    EXPEDIENTPID,
    ENTITATESTATAL,
    PINFO,
    DATAINICI,
    DATAFI,
    PERSONACONTACTE,
    PERSONACONTACTEEMAIL,
    RESPONSABLEPROCNOM,
    RESPONSABLEPROCEMAIL,
    NOTES,
    DOCUMENTSOLICITUDID,
    SOLICITUDXMLID,
    FIRMATDOCSOLICITUD,
    PRODUCCIO,
    DENOMINACIO,
    DIR3,
    NIF,
    CREADOR,
    OPERADOR,
    ESTATPINBAL,
    CONSENTIMENT,
    URLCONSENTIMENT,
    CONSENTIMENTADJUNT,
    PORTAFIBID,
    INFOMADRIDID,
    DATACADUCITAT,
    FITXERCONSENTIMENTID,
    CONTACTETITULARID,
    TITULARFIRMANIF,
    TITULARFIRMANOM,
    TITULARFIRMALLINATGES,
    TITULARFIRMAEMAIL,
    SOLICITUDFUSIONADAID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
SOLICITUDID
  };
}
