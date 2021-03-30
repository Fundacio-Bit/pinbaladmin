
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface ServeiFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_servei";


  public static final String _TABLE_MODEL = "servei";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField SERVEIID = new LongField(_TABLE_MODEL, "serveiID", "serveiid");  // PK
	 public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi");
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final LongField FORMULARIID = new LongField(_TABLE_MODEL, "formulariID", "formulariid");
	 public static final LongField ENTITATSERVEIID = new LongField(_TABLE_MODEL, "entitatServeiID", "entitatserveiid");
	 public static final LongField ESTATSERVEIID = new LongField(_TABLE_MODEL, "estatServeiID", "estatserveiid");
	 public static final IntegerField TIPUSCONSENTIMENT = new IntegerField(_TABLE_MODEL, "tipusConsentiment", "tipusconsentiment");
	 public static final BooleanField OCULT = new BooleanField(_TABLE_MODEL, "ocult", "ocult");


  public static final Field<?>[] ALL_SERVEI_FIELDS = {
    SERVEIID,
    CODI,
    NOM,
    DESCRIPCIO,
    FORMULARIID,
    ENTITATSERVEIID,
    ESTATSERVEIID,
    TIPUSCONSENTIMENT,
    OCULT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
SERVEIID
  };
}
