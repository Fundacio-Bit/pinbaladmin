
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TramitFCteTecFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_tramit_f_cte_tec";


  public static final String _TABLE_MODEL = "tramitFCteTec";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField CTETECID = new LongField(_TABLE_MODEL, "ctetecid", "ctetecid");  // PK
	 public static final LongField TRAMITID = new LongField(_TABLE_MODEL, "tramitid", "tramitid");
	 public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif");
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField LLINATGE1 = new StringField(_TABLE_MODEL, "llinatge1", "llinatge1");
	 public static final StringField LLINATGE2 = new StringField(_TABLE_MODEL, "llinatge2", "llinatge2");
	 public static final StringField CARREC = new StringField(_TABLE_MODEL, "carrec", "carrec");
	 public static final StringField TELEFON = new StringField(_TABLE_MODEL, "telefon", "telefon");
	 public static final StringField MAIL = new StringField(_TABLE_MODEL, "mail", "mail");


  public static final Field<?>[] ALL_TRAMITFCTETEC_FIELDS = {
    CTETECID,
    TRAMITID,
    NIF,
    NOM,
    LLINATGE1,
    LLINATGE2,
    CARREC,
    TELEFON,
    MAIL
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
CTETECID
  };
}
