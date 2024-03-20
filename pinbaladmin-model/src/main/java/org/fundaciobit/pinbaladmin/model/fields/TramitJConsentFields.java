
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TramitJConsentFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_tramit_j_consent";


  public static final String _TABLE_MODEL = "tramitJConsent";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField CONSENTID = new LongField(_TABLE_MODEL, "consentid", "consentid");  // PK
	 public static final LongField TRAMITID = new LongField(_TABLE_MODEL, "tramitid", "tramitid");
	 public static final LongField ADJUNTID = new LongField(_TABLE_MODEL, "adjuntID", "adjuntid");
	 public static final StringField CONSENTIMENT = new StringField(_TABLE_MODEL, "consentiment", "consentiment");
	 public static final StringField URLCONSENTIMENT = new StringField(_TABLE_MODEL, "urlconsentiment", "urlconsentiment");
	 public static final StringField CONSENTIMENTADJUNT = new StringField(_TABLE_MODEL, "consentimentadjunt", "consentimentadjunt");


  public static final Field<?>[] ALL_TRAMITJCONSENT_FIELDS = {
    CONSENTID,
    TRAMITID,
    ADJUNTID,
    CONSENTIMENT,
    URLCONSENTIMENT,
    CONSENTIMENTADJUNT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
CONSENTID
  };
}
