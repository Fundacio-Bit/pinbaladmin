
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TiquetFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_tiquet";


  public static final String _TABLE_MODEL = "tiquet";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField TIQUETID = new LongField(_TABLE_MODEL, "tiquetID", "tiquetid");  // PK
	 public static final StringField TITOL = new StringField(_TABLE_MODEL, "titol", "titol");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final StringField INFORMADOR = new StringField(_TABLE_MODEL, "informador", "informador");
	 public static final TimestampField DATAALTA = new TimestampField(_TABLE_MODEL, "dataAlta", "dataalta");
	 public static final LongField ESTATTIQUETID = new LongField(_TABLE_MODEL, "estatTiquetID", "estattiquetid");
	 public static final LongField TIPUSTIQUETID = new LongField(_TABLE_MODEL, "tipusTiquetID", "tipustiquetid");
	 public static final StringField VERSIOPINBAL = new StringField(_TABLE_MODEL, "versioPinbal", "versiopinbal");
	 public static final TimestampField DATAINICI = new TimestampField(_TABLE_MODEL, "dataInici", "datainici");
	 public static final TimestampField DATAINCIDENCIA = new TimestampField(_TABLE_MODEL, "dataIncidencia", "dataincidencia");
	 public static final StringField SOLUCIONATPER = new StringField(_TABLE_MODEL, "solucionatPer", "solucionatper");
	 public static final TimestampField DATAFI = new TimestampField(_TABLE_MODEL, "datafi", "datafi");
	 public static final StringField NOTES = new StringField(_TABLE_MODEL, "notes", "notes");
	 public static final IntegerField ENTORN = new IntegerField(_TABLE_MODEL, "entorn", "entorn");
	 public static final LongField ADJUNT1ID = new LongField(_TABLE_MODEL, "adjunt1ID", "adjunt1id");
	 public static final LongField ADJUNT2ID = new LongField(_TABLE_MODEL, "adjunt2ID", "adjunt2id");


  public static final Field<?>[] ALL_TIQUET_FIELDS = {
    TIQUETID,
    TITOL,
    DESCRIPCIO,
    INFORMADOR,
    DATAALTA,
    ESTATTIQUETID,
    TIPUSTIQUETID,
    VERSIOPINBAL,
    DATAINICI,
    DATAINCIDENCIA,
    SOLUCIONATPER,
    DATAFI,
    NOTES,
    ENTORN,
    ADJUNT1ID,
    ADJUNT2ID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
TIQUETID
  };
}
