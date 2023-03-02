
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EstatTiquetFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_estattiquet";


  public static final String _TABLE_MODEL = "estatTiquet";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ESTATTIQUETID = new LongField(_TABLE_MODEL, "estatTiquetID", "estattiquetid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");


  public static final Field<?>[] ALL_ESTATTIQUET_FIELDS = {
    ESTATTIQUETID,
    NOM
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ESTATTIQUETID
  };
}
