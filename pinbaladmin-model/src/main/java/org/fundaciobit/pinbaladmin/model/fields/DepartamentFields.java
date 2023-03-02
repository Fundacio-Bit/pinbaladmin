
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface DepartamentFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_departament";


  public static final String _TABLE_MODEL = "departament";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField DEPARTAMENTID = new LongField(_TABLE_MODEL, "departamentID", "departamentid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final LongField AREAID = new LongField(_TABLE_MODEL, "areaID", "areaid");


  public static final Field<?>[] ALL_DEPARTAMENT_FIELDS = {
    DEPARTAMENTID,
    NOM,
    AREAID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
DEPARTAMENTID
  };
}
