
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EstatSolicitudServeiFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_estatsolicitudservei";


  public static final String _TABLE_MODEL = "estatSolicitudServei";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ESTATSOLICITUDSERVEIID = new LongField(_TABLE_MODEL, "estatSolicitudServeiID", "estatsolicitudserveiid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_ESTATSOLICITUDSERVEI_FIELDS = {
    ESTATSOLICITUDSERVEIID,
    NOM,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ESTATSOLICITUDSERVEIID
  };
}
