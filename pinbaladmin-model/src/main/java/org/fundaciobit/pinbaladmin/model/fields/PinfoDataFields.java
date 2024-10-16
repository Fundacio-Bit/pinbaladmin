
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PinfoDataFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_pinfodata";


  public static final String _TABLE_MODEL = "pinfoData";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PINFODATAID = new LongField(_TABLE_MODEL, "pinfodataID", "pinfodataid");  // PK
	 public static final LongField PINFOID = new LongField(_TABLE_MODEL, "pinfoID", "pinfoid");
	 public static final LongField ESTAT = new LongField(_TABLE_MODEL, "estat", "estat");
	 public static final StringField USUARIID = new StringField(_TABLE_MODEL, "usuariid", "usuariid");
	 public static final LongField PROCEDIMENTID = new LongField(_TABLE_MODEL, "procedimentID", "procedimentid");
	 public static final LongField SERVEIID = new LongField(_TABLE_MODEL, "serveiID", "serveiid");
	 public static final LongField ALTA = new LongField(_TABLE_MODEL, "alta", "alta");


  public static final Field<?>[] ALL_PINFODATA_FIELDS = {
    PINFODATAID,
    PINFOID,
    ESTAT,
    USUARIID,
    PROCEDIMENTID,
    SERVEIID,
    ALTA
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PINFODATAID
  };
}
