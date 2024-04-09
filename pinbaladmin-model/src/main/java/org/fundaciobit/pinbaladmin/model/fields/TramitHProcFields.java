
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TramitHProcFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_tramit_h_proc";


  public static final String _TABLE_MODEL = "tramitHProc";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PROCID = new LongField(_TABLE_MODEL, "procid", "procid");  // PK
	 public static final LongField TRAMITID = new LongField(_TABLE_MODEL, "tramitid", "tramitid");
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi");
	 public static final StringField TIPUS = new StringField(_TABLE_MODEL, "tipus", "tipus");
	 public static final StringField URLSEU = new StringField(_TABLE_MODEL, "urlseu", "urlseu");
	 public static final BooleanField CADUCITAT = new BooleanField(_TABLE_MODEL, "caducitat", "caducitat");
	 public static final TimestampField CADUCITATDATA = new TimestampField(_TABLE_MODEL, "caducitatdata", "caducitatdata");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final LongField PETICIONSALDIA = new LongField(_TABLE_MODEL, "peticionsaldia", "peticionsaldia");
	 public static final LongField PETICIONSALMES = new LongField(_TABLE_MODEL, "peticionsalmes", "peticionsalmes");
	 public static final BooleanField PERIODICO = new BooleanField(_TABLE_MODEL, "periodico", "periodico");
	 public static final BooleanField AUTOMATIZADO = new BooleanField(_TABLE_MODEL, "automatizado", "automatizado");


  public static final Field<?>[] ALL_TRAMITHPROC_FIELDS = {
    PROCID,
    TRAMITID,
    NOM,
    CODI,
    TIPUS,
    URLSEU,
    CADUCITAT,
    CADUCITATDATA,
    DESCRIPCIO,
    PETICIONSALDIA,
    PETICIONSALMES,
    PERIODICO,
    AUTOMATIZADO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PROCID
  };
}
