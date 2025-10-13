
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface ModificacioSolicitudFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_mod_solicitud";


  public static final String _TABLE_MODEL = "modificacioSolicitud";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField MODSOLIID = new LongField(_TABLE_MODEL, "modsoliID", "modsoliid");  // PK
	 public static final LongField SOLICITUDID = new LongField(_TABLE_MODEL, "solicitudID", "solicitudid");
	 public static final StringField PROCEDIMENTCODI = new StringField(_TABLE_MODEL, "procedimentCodi", "procedimentcodi");
	 public static final StringField PROCEDIMENTNOM = new StringField(_TABLE_MODEL, "procedimentNom", "procedimentnom");
	 public static final StringField CODISIANOU = new StringField(_TABLE_MODEL, "codiSiaNou", "codisianou");
	 public static final LongField ESTATID = new LongField(_TABLE_MODEL, "estatID", "estatid");
	 public static final TimestampField DATAINICI = new TimestampField(_TABLE_MODEL, "dataInici", "datainici");
	 public static final TimestampField DATAFI = new TimestampField(_TABLE_MODEL, "dataFi", "datafi");
	 public static final StringField PROCEDIMENTTIPUS = new StringField(_TABLE_MODEL, "procedimentTipus", "procedimenttipus");
	 public static final StringField NOTES = new StringField(_TABLE_MODEL, "notes", "notes");
	 public static final LongField ORGANID = new LongField(_TABLE_MODEL, "organID", "organid");
	 public static final StringField RESPONSABLEPROCNOM = new StringField(_TABLE_MODEL, "responsableProcNom", "responsableprocnom");
	 public static final StringField RESPONSABLEPROCEMAIL = new StringField(_TABLE_MODEL, "responsableProceMail", "responsableprocemail");
	 public static final StringField CONSENTIMENT = new StringField(_TABLE_MODEL, "consentiment", "consentiment");
	 public static final LongField DOCCONSENTIMENTID = new LongField(_TABLE_MODEL, "doCconsentimentID", "docconsentiment");
	 public static final StringField SOLICITANTNOM = new StringField(_TABLE_MODEL, "solicitantNom", "solicitantnom");
	 public static final StringField SOLICITANTNIF = new StringField(_TABLE_MODEL, "solicitantNif", "solicitantnif");
	 public static final StringField SOLICITANTMAIL = new StringField(_TABLE_MODEL, "solicitantMail", "solicitantmail");
	 public static final StringField SOLICITANTUSERNAME = new StringField(_TABLE_MODEL, "solicitantUsername", "solicitantusername");
	 public static final LongField ESTATMODIFICACIO = new LongField(_TABLE_MODEL, "estatModificacio", "estatmodificacio");
	 public static final StringField CONTACTENOM = new StringField(_TABLE_MODEL, "contactenom", "contactenom");
	 public static final StringField CONTACTEMAIL = new StringField(_TABLE_MODEL, "contactemail", "contactemail");
	 public static final BooleanField ESMENA = new BooleanField(_TABLE_MODEL, "esmena", "esmena");


  public static final Field<?>[] ALL_MODIFICACIOSOLICITUD_FIELDS = {
    MODSOLIID,
    SOLICITUDID,
    PROCEDIMENTCODI,
    PROCEDIMENTNOM,
    CODISIANOU,
    ESTATID,
    DATAINICI,
    DATAFI,
    PROCEDIMENTTIPUS,
    NOTES,
    ORGANID,
    RESPONSABLEPROCNOM,
    RESPONSABLEPROCEMAIL,
    CONSENTIMENT,
    DOCCONSENTIMENTID,
    SOLICITANTNOM,
    SOLICITANTNIF,
    SOLICITANTMAIL,
    SOLICITANTUSERNAME,
    ESTATMODIFICACIO,
    CONTACTENOM,
    CONTACTEMAIL,
    ESMENA
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
MODSOLIID
  };
}
