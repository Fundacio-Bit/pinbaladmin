
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
	 public static final StringField ARTICLES = new StringField(_TABLE_MODEL, "articles", "articles");
	 public static final StringField CONSENTIMENT = new StringField(_TABLE_MODEL, "consentiment", "consentiment");
	 public static final StringField URLNORMA = new StringField(_TABLE_MODEL, "urlnorma", "urlnorma");
	 public static final StringField CONSENTIMENTPUBLICAT = new StringField(_TABLE_MODEL, "consentimentpublicat", "consentimentpublicat");
	 public static final StringField URLCONSENTIMENT = new StringField(_TABLE_MODEL, "urlconsentiment", "urlconsentiment");


  public static final Field<?>[] ALL_TRAMITISERV_FIELDS = {
    SERVID,
    TRAMITID,
    NOM,
    CODI,
    NORMA,
    ARTICLES,
    CONSENTIMENT,
    URLNORMA,
    CONSENTIMENTPUBLICAT,
    URLCONSENTIMENT
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
SERVID
  };
}
