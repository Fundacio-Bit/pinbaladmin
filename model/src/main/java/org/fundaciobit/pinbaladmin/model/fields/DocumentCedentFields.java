
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface DocumentCedentFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_documentcedent";


  public static final String _TABLE_MODEL = "documentCedent";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField DOCUMENTCEDENTID = new LongField(_TABLE_MODEL, "documentCedentID", "documentcedentid");  // PK
	 public static final StringField TITOL = new StringField(_TABLE_MODEL, "titol", "titol");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final LongField ENTITATSERVEIID = new LongField(_TABLE_MODEL, "entitatServeiID", "entitatserveiid");
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "dataalta");
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");


  public static final Field<?>[] ALL_DOCUMENTCEDENT_FIELDS = {
    DOCUMENTCEDENTID,
    TITOL,
    DESCRIPCIO,
    ENTITATSERVEIID,
    DATACREACIO,
    FITXERID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
DOCUMENTCEDENTID
  };
}
