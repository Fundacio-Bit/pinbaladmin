
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface DocumentEntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_documententitat";


  public static final String _TABLE_MODEL = "documentEntitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField DOCUMENTENTITATID = new LongField(_TABLE_MODEL, "documentEntitatID", "documententitatid");  // PK
	 public static final StringField TITOL = new StringField(_TABLE_MODEL, "titol", "titol");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");
	 public static final TimestampField DATAALTA = new TimestampField(_TABLE_MODEL, "dataAlta", "dataalta");


  public static final Field<?>[] ALL_DOCUMENTENTITAT_FIELDS = {
    DOCUMENTENTITATID,
    TITOL,
    DESCRIPCIO,
    ENTITATID,
    FITXERID,
    DATAALTA
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
DOCUMENTENTITATID
  };
}
