
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface CampFormulariFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_campformulari";


  public static final String _TABLE_MODEL = "campFormulari";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField CAMPFORMULARIID = new LongField(_TABLE_MODEL, "campFormulariID", "campformulariid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField CAMPPDF = new StringField(_TABLE_MODEL, "campPDF", "camppdf");
	 public static final LongField FORMULARIID = new LongField(_TABLE_MODEL, "formulariID", "formulariid");


  public static final Field<?>[] ALL_CAMPFORMULARI_FIELDS = {
    CAMPFORMULARIID,
    NOM,
    CAMPPDF,
    FORMULARIID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
CAMPFORMULARIID
  };
}
