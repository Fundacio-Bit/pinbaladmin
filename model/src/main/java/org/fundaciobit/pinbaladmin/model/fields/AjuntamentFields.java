
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface AjuntamentFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_ajuntament";


  public static final String _TABLE_MODEL = "ajuntament";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField AJUNTAMENTID = new StringField(_TABLE_MODEL, "ajuntamentID", "ajuntamentid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField SOLICITUD = new StringField(_TABLE_MODEL, "solicitud", "solicitud");
	 public static final StringField PERSONACONTACTE = new StringField(_TABLE_MODEL, "personaContacte", "personacontacte");
	 public static final StringField ESTAT = new StringField(_TABLE_MODEL, "estat", "estat");


  public static final Field<?>[] ALL_AJUNTAMENT_FIELDS = {
    AJUNTAMENTID,
    NOM,
    SOLICITUD,
    PERSONACONTACTE,
    ESTAT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
AJUNTAMENTID
  };
}
