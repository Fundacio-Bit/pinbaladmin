
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TipusTiquetFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_tipustiquet";


  public static final String _TABLE_MODEL = "tipusTiquet";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField TIPUSTIQUETID = new LongField(_TABLE_MODEL, "tipusTiquetID", "tipustiquetid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");


  public static final Field<?>[] ALL_TIPUSTIQUET_FIELDS = {
    TIPUSTIQUETID,
    NOM
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
TIPUSTIQUETID
  };
}
