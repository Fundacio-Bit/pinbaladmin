
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EstatSolicitudFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_estatsolicitud";


  public static final String _TABLE_MODEL = "estatSolicitud";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ESTATSOLICITUDID = new LongField(_TABLE_MODEL, "estatSolicitudID", "estatsolicitudid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_ESTATSOLICITUD_FIELDS = {
    ESTATSOLICITUDID,
    NOM,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ESTATSOLICITUDID
  };
}
