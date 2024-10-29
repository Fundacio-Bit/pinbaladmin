
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PinfoFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_pinfo";


  public static final String _TABLE_MODEL = "pinfo";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PINFOID = new LongField(_TABLE_MODEL, "pinfoID", "pinfoid");  // PK
	 public static final LongField INCIDENCIAID = new LongField(_TABLE_MODEL, "incidenciaID", "incidenciaid");
	 public static final StringField SOLICITANTNIF = new StringField(_TABLE_MODEL, "solicitantNIF", "solicitantnif");
	 public static final LongField ESTAT = new LongField(_TABLE_MODEL, "estat", "estat");
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");
	 public static final LongField FITXERFIRMATID = new LongField(_TABLE_MODEL, "fitxerfirmatID", "fitxerfirmatid");
	 public static final StringField PORTAFIBID = new StringField(_TABLE_MODEL, "portafibid", "portafibid");
	 public static final StringField DESTINATARINIF = new StringField(_TABLE_MODEL, "destinatariNIF", "destinatarinif");


  public static final Field<?>[] ALL_PINFO_FIELDS = {
    PINFOID,
    INCIDENCIAID,
    SOLICITANTNIF,
    ESTAT,
    FITXERID,
    FITXERFIRMATID,
    PORTAFIBID,
    DESTINATARINIF
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PINFOID
  };
}
