
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EmailFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_email";


  public static final String _TABLE_MODEL = "email";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField EMAILID = new LongField(_TABLE_MODEL, "emailID", "emailid");  // PK
	 public static final TimestampField DATAENVIAMENT = new TimestampField(_TABLE_MODEL, "dataEnviament", "dataenviament");
	 public static final StringField ENVIADOR = new StringField(_TABLE_MODEL, "enviador", "enviador");
	 public static final StringField DESTINATARIS = new StringField(_TABLE_MODEL, "destinataris", "destinataris");
	 public static final StringField SUBJECT = new StringField(_TABLE_MODEL, "subject", "subject");
	 public static final StringField MESSAGE = new StringField(_TABLE_MODEL, "message", "message");


  public static final Field<?>[] ALL_EMAIL_FIELDS = {
    EMAILID,
    DATAENVIAMENT,
    ENVIADOR,
    DESTINATARIS,
    SUBJECT,
    MESSAGE
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
EMAILID
  };
}
