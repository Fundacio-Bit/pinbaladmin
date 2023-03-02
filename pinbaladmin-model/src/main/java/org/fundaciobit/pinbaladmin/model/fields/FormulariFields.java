
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface FormulariFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_formulari";


  public static final String _TABLE_MODEL = "formulari";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField FORMULARIID = new LongField(_TABLE_MODEL, "formulariid", "formulariid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");


  public static final Field<?>[] ALL_FORMULARI_FIELDS = {
    FORMULARIID,
    NOM,
    DESCRIPCIO,
    FITXERID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
FORMULARIID
  };
}
