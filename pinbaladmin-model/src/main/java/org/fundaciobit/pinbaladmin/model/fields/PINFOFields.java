
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PINFOFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_pinfo";


  public static final String _TABLE_MODEL = "pINFO";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PINFOID = new LongField(_TABLE_MODEL, "PinfoID", "pinfoid");  // PK
	 public static final LongField INCIDENCIAID = new LongField(_TABLE_MODEL, "IncidenciaID", "incidenciaid");
	 public static final LongField ESTAT = new LongField(_TABLE_MODEL, "estat", "estat");
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");
	 public static final LongField FITXERFIRMATID = new LongField(_TABLE_MODEL, "fitxerfirmatID", "fitxerfirmatid");
	 public static final StringField PORTAFIBID = new StringField(_TABLE_MODEL, "portafibid", "portafibid");


  public static final Field<?>[] ALL_PINFO_FIELDS = {
    PINFOID,
    INCIDENCIAID,
    ESTAT,
    FITXERID,
    FITXERFIRMATID,
    PORTAFIBID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PINFOID
  };
}
