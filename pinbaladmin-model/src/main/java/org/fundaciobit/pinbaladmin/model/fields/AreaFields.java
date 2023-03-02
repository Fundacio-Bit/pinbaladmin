
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface AreaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_area";


  public static final String _TABLE_MODEL = "area";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField AREAID = new LongField(_TABLE_MODEL, "areaID", "areaid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");


  public static final Field<?>[] ALL_AREA_FIELDS = {
    AREAID,
    NOM,
    ENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
AREAID
  };
}
