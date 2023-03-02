
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EntitatServeiFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_entitatservei";


  public static final String _TABLE_MODEL = "entitatServei";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ENTITATSERVEIID = new LongField(_TABLE_MODEL, "entitatServeiID", "entitatserveiid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final BooleanField BALEARS = new BooleanField(_TABLE_MODEL, "balears", "balears");


  public static final Field<?>[] ALL_ENTITATSERVEI_FIELDS = {
    ENTITATSERVEIID,
    NOM,
    DESCRIPCIO,
    BALEARS
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ENTITATSERVEIID
  };
}
