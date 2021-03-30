
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_entitat";


  public static final String _TABLE_MODEL = "entitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ENTITATID = new LongField(_TABLE_MODEL, "entitatID", "entitatid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField PERSONACONTACTE = new StringField(_TABLE_MODEL, "personaContacte", "personacontacte");
	 public static final StringField CIF = new StringField(_TABLE_MODEL, "CIF", "cif");
	 public static final LongField GRUPENTITATID = new LongField(_TABLE_MODEL, "grupEntitatID", "grupentitatid");


  public static final Field<?>[] ALL_ENTITAT_FIELDS = {
    ENTITATID,
    NOM,
    PERSONACONTACTE,
    CIF,
    GRUPENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ENTITATID
  };
}
