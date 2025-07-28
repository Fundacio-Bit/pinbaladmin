
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface InfoMadridFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_infomadrid";


  public static final String _TABLE_MODEL = "infoMadrid";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField INFOMADRIDID = new LongField(_TABLE_MODEL, "infoMadridID", "infomadridid");  // PK
	 public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi");
	 public static final LongField ESTATPROCEDIMENT = new LongField(_TABLE_MODEL, "estatProcediment", "estatprocediment");
	 public static final LongField ESTATAUTORITZACIO = new LongField(_TABLE_MODEL, "estatAutoritzacio", "estatautoritzacio");
	 public static final StringField MISSATGE = new StringField(_TABLE_MODEL, "missatge", "missatge");
	 public static final StringField CONSULTA = new StringField(_TABLE_MODEL, "consulta", "consulta");
	 public static final StringField TITULARNOM = new StringField(_TABLE_MODEL, "titularNom", "titularnom");
	 public static final StringField TITULARNIF = new StringField(_TABLE_MODEL, "titularNif", "titularnif");
	 public static final TimestampField DATAAUTORITZACIO = new TimestampField(_TABLE_MODEL, "dataAutoritzacio", "dataautoritzacio");
	 public static final TimestampField DATAENVIAMENT = new TimestampField(_TABLE_MODEL, "dataEnviament", "dataenviament");
	 public static final LongField INTENTS = new LongField(_TABLE_MODEL, "intents", "intents");


  public static final Field<?>[] ALL_INFOMADRID_FIELDS = {
    INFOMADRIDID,
    CODI,
    ESTATPROCEDIMENT,
    ESTATAUTORITZACIO,
    MISSATGE,
    CONSULTA,
    TITULARNOM,
    TITULARNIF,
    DATAAUTORITZACIO,
    DATAENVIAMENT,
    INTENTS
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
INFOMADRIDID
  };
}
