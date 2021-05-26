
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EventFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_event";


  public static final String _TABLE_MODEL = "event";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField EVENTID = new LongField(_TABLE_MODEL, "eventID", "eventid");  // PK
	 public static final LongField SOLICITUDID = new LongField(_TABLE_MODEL, "solicitudID", "solicitudid");
	 public static final LongField TASCATECNICAID = new LongField(_TABLE_MODEL, "tascaTecnicaID", "tascatecnicaid");
	 public static final TimestampField DATAEVENT = new TimestampField(_TABLE_MODEL, "dataEvent", "dataevent");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final StringField PERSONA = new StringField(_TABLE_MODEL, "persona", "persona");
	 public static final StringField COMENTARI = new StringField(_TABLE_MODEL, "comentari", "comentari");
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");
	 public static final BooleanField NOLLEGIT = new BooleanField(_TABLE_MODEL, "noLlegit", "nollegit");


  public static final Field<?>[] ALL_EVENT_FIELDS = {
    EVENTID,
    SOLICITUDID,
    TASCATECNICAID,
    DATAEVENT,
    TIPUS,
    PERSONA,
    COMENTARI,
    FITXERID,
    NOLLEGIT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
EVENTID
  };
}
