
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EstatServeiFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_estatservei";


  public static final String _TABLE_MODEL = "estatServei";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ESTATSERVEIID = new LongField(_TABLE_MODEL, "estatServeiID", "estatserveiid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_ESTATSERVEI_FIELDS = {
    ESTATSERVEIID,
    NOM,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ESTATSERVEIID
  };
}
