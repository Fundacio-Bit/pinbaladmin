
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface OrganFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_organ";


  public static final String _TABLE_MODEL = "organ";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ORGANID = new LongField(_TABLE_MODEL, "organid", "organid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DIR3 = new StringField(_TABLE_MODEL, "dir3", "dir3");
	 public static final StringField DIR3PARE = new StringField(_TABLE_MODEL, "dir3pare", "dir3pare");
	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatid", "entitatid");
	 public static final StringField CIF = new StringField(_TABLE_MODEL, "cif", "cif");


  public static final Field<?>[] ALL_ORGAN_FIELDS = {
    ORGANID,
    NOM,
    DIR3,
    DIR3PARE,
    ENTITATID,
    CIF
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ORGANID
  };
}
