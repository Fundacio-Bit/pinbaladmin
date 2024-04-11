
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TramitAPersAutFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_tramit_a_pers_aut";


  public static final String _TABLE_MODEL = "tramitAPersAut";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PERSAUTID = new LongField(_TABLE_MODEL, "persautid", "persautid");  // PK
	 public static final LongField TRAMITID = new LongField(_TABLE_MODEL, "tramitid", "tramitid");
	 public static final TimestampField DATATRAMIT = new TimestampField(_TABLE_MODEL, "datatramit", "datatramit");
	 public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif");
	 public static final StringField MAIL = new StringField(_TABLE_MODEL, "mail", "mail");
	 public static final StringField TELEFON = new StringField(_TABLE_MODEL, "telefon", "telefon");
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField LLINATGE1 = new StringField(_TABLE_MODEL, "llinatge1", "llinatge1");
	 public static final StringField LLINATGE2 = new StringField(_TABLE_MODEL, "llinatge2", "llinatge2");
	 public static final StringField URLSISTRA = new StringField(_TABLE_MODEL, "urlsistra", "urlsistra");
	 public static final StringField IDSESIONFORMULARIO = new StringField(_TABLE_MODEL, "idsesionformulario", "idsesionformulario");
	 public static final StringField IDSESIONTRAMITE = new StringField(_TABLE_MODEL, "idsesiontramite", "idsesiontramite");


  public static final Field<?>[] ALL_TRAMITAPERSAUT_FIELDS = {
    PERSAUTID,
    TRAMITID,
    DATATRAMIT,
    NIF,
    MAIL,
    TELEFON,
    NOM,
    LLINATGE1,
    LLINATGE2,
    URLSISTRA,
    IDSESIONFORMULARIO,
    IDSESIONTRAMITE
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PERSAUTID
  };
}
