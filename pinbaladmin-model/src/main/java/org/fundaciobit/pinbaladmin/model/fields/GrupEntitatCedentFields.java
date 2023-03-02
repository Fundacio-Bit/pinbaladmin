
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface GrupEntitatCedentFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_grupentitatcedent";


  public static final String _TABLE_MODEL = "grupEntitatCedent";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField GRUPENTITATCEDENTID = new LongField(_TABLE_MODEL, "grupEntitatCedentID", "grupentitatcedentid");  // PK
	 public static final LongField GRUPENTITATID = new LongField(_TABLE_MODEL, "grupEntitatID", "grupentitatid");
	 public static final LongField CEDENTID = new LongField(_TABLE_MODEL, "cedentID", "cedentid");


  public static final Field<?>[] ALL_GRUPENTITATCEDENT_FIELDS = {
    GRUPENTITATCEDENTID,
    GRUPENTITATID,
    CEDENTID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
GRUPENTITATCEDENTID
  };
}
