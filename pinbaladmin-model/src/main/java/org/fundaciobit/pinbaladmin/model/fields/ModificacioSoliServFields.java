
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface ModificacioSoliServFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_mod_soliserv";


  public static final String _TABLE_MODEL = "modificacioSoliServ";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField MODSOLISERVID = new LongField(_TABLE_MODEL, "modsoliservid", "modsoliservid");  // PK
	 public static final LongField SOLISERVID = new LongField(_TABLE_MODEL, "soliServID", "soliservid");
	 public static final LongField MODSOLIID = new LongField(_TABLE_MODEL, "modSoliID", "modsoliid");
	 public static final StringField ESTAT = new StringField(_TABLE_MODEL, "estat", "estat");
	 public static final StringField NORMA1 = new StringField(_TABLE_MODEL, "norma1", "norma1");
	 public static final StringField ARTICLES1 = new StringField(_TABLE_MODEL, "articles1", "articles1");
	 public static final LongField FITXERNORMA1ID = new LongField(_TABLE_MODEL, "fitxerNorma1ID", "fitxernorma1id");
	 public static final StringField NORMA2 = new StringField(_TABLE_MODEL, "norma2", "norma2");
	 public static final StringField ARTICLES2 = new StringField(_TABLE_MODEL, "articles2", "articles2");
	 public static final LongField FITXERNORMA2ID = new LongField(_TABLE_MODEL, "fitxerNorma2ID", "fitxernorma2id");
	 public static final StringField NORMA3 = new StringField(_TABLE_MODEL, "norma3", "norma3");
	 public static final StringField ARTICLES3 = new StringField(_TABLE_MODEL, "articles3", "articles3");
	 public static final LongField FITXERNORMA3ID = new LongField(_TABLE_MODEL, "fitxerNorma3ID", "fitxernorma3id");


  public static final Field<?>[] ALL_MODIFICACIOSOLISERV_FIELDS = {
    MODSOLISERVID,
    SOLISERVID,
    MODSOLIID,
    ESTAT,
    NORMA1,
    ARTICLES1,
    FITXERNORMA1ID,
    NORMA2,
    ARTICLES2,
    FITXERNORMA2ID,
    NORMA3,
    ARTICLES3,
    FITXERNORMA3ID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
MODSOLISERVID
  };
}
