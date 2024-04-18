
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TramitIServFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_tramit_i_serv";


  public static final String _TABLE_MODEL = "tramitIServ";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField SERVID = new LongField(_TABLE_MODEL, "servid", "servid");  // PK
	 public static final LongField TRAMITID = new LongField(_TABLE_MODEL, "tramitid", "tramitid");
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField CODI = new StringField(_TABLE_MODEL, "codi", "codi");
	 public static final StringField NORMA = new StringField(_TABLE_MODEL, "norma", "norma");
	 public static final LongField FITXERNORMAID = new LongField(_TABLE_MODEL, "fitxernormaID", "fitxernormaid");
	 public static final StringField URLNORMA = new StringField(_TABLE_MODEL, "urlnorma", "urlnorma");
	 public static final StringField ARTICLES = new StringField(_TABLE_MODEL, "articles", "articles");
	 public static final StringField NORMA2 = new StringField(_TABLE_MODEL, "norma2", "norma2");
	 public static final LongField FITXERNORMA2ID = new LongField(_TABLE_MODEL, "fitxernorma2ID", "fitxernorma2id");
	 public static final StringField ARTICLES2 = new StringField(_TABLE_MODEL, "articles2", "articles2");
	 public static final StringField NORMA3 = new StringField(_TABLE_MODEL, "norma3", "norma3");
	 public static final LongField FITXERNORMA3ID = new LongField(_TABLE_MODEL, "fitxernorma3ID", "fitxernorma3id");
	 public static final StringField ARTICLES3 = new StringField(_TABLE_MODEL, "articles3", "articles3");


  public static final Field<?>[] ALL_TRAMITISERV_FIELDS = {
    SERVID,
    TRAMITID,
    NOM,
    CODI,
    NORMA,
    FITXERNORMAID,
    URLNORMA,
    ARTICLES,
    NORMA2,
    FITXERNORMA2ID,
    ARTICLES2,
    NORMA3,
    FITXERNORMA3ID,
    ARTICLES3
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
SERVID
  };
}
