
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface OperadorFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_operador";


  public static final String _TABLE_MODEL = "operador";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField OPERADORID = new LongField(_TABLE_MODEL, "operadorID", "operadorid");  // PK
	 public static final StringField USERNAME = new StringField(_TABLE_MODEL, "username", "username");
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField EMAIL = new StringField(_TABLE_MODEL, "email", "email");


  public static final Field<?>[] ALL_OPERADOR_FIELDS = {
    OPERADORID,
    USERNAME,
    NOM,
    EMAIL
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
OPERADORID
  };
}
