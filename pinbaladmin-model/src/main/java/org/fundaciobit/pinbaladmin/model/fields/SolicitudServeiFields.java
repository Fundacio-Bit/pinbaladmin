
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface SolicitudServeiFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_solicitudservei";


  public static final String _TABLE_MODEL = "solicitudServei";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ID = new LongField(_TABLE_MODEL, "id", "id");  // PK
	 public static final LongField SOLICITUDID = new LongField(_TABLE_MODEL, "solicitudID", "solicitudid");
	 public static final LongField SERVEIID = new LongField(_TABLE_MODEL, "serveiID", "serveiid");
	 public static final LongField ESTATSOLICITUDSERVEIID = new LongField(_TABLE_MODEL, "estatSolicitudServeiID", "estatsolicitudserveiid");
	 public static final StringField ENLLAZNORMALEGAL = new StringField(_TABLE_MODEL, "enllazNormaLegal", "enllaznormalegal");
	 public static final StringField TIPUSCONSENTIMENT = new StringField(_TABLE_MODEL, "tipusConsentiment", "tipusconsentiment");
	 public static final StringField CONSENTIMENT = new StringField(_TABLE_MODEL, "consentiment", "consentiment");
	 public static final StringField ENLLAZCONSENTIMENT = new StringField(_TABLE_MODEL, "enllazConsentiment", "enllazconsentiment");
	 public static final StringField NOTES = new StringField(_TABLE_MODEL, "notes", "notes");
	 public static final StringField CADUCA = new StringField(_TABLE_MODEL, "caduca", "caduca");
	 public static final StringField FECHACADUCA = new StringField(_TABLE_MODEL, "fechaCaduca", "fechacaduca");
	 public static final StringField NORMALEGAL = new StringField(_TABLE_MODEL, "normaLegal", "normalegal");
	 public static final LongField FITXERNORMAID = new LongField(_TABLE_MODEL, "fitxernormaID", "fitxernormaid");
	 public static final StringField ARTICLES = new StringField(_TABLE_MODEL, "articles", "articles");
	 public static final StringField NORMA2 = new StringField(_TABLE_MODEL, "norma2", "norma2");
	 public static final LongField FITXERNORMA2ID = new LongField(_TABLE_MODEL, "fitxernorma2ID", "fitxernorma2id");
	 public static final StringField ARTICLES2 = new StringField(_TABLE_MODEL, "articles2", "articles2");
	 public static final StringField NORMA3 = new StringField(_TABLE_MODEL, "norma3", "norma3");
	 public static final LongField FITXERNORMA3ID = new LongField(_TABLE_MODEL, "fitxernorma3ID", "fitxernorma3id");
	 public static final StringField ARTICLES3 = new StringField(_TABLE_MODEL, "articles3", "articles3");


  public static final Field<?>[] ALL_SOLICITUDSERVEI_FIELDS = {
    ID,
    SOLICITUDID,
    SERVEIID,
    ESTATSOLICITUDSERVEIID,
    ENLLAZNORMALEGAL,
    TIPUSCONSENTIMENT,
    CONSENTIMENT,
    ENLLAZCONSENTIMENT,
    NOTES,
    CADUCA,
    FECHACADUCA,
    NORMALEGAL,
    FITXERNORMAID,
    ARTICLES,
    NORMA2,
    FITXERNORMA2ID,
    ARTICLES2,
    NORMA3,
    FITXERNORMA3ID,
    ARTICLES3
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ID
  };
}
