
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TramitBDadesSoliFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_tramit_b_dades_soli";


  public static final String _TABLE_MODEL = "tramitBDadesSoli";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField DADESSOLIID = new LongField(_TABLE_MODEL, "dadessoliid", "dadessoliid");  // PK
	 public static final LongField TRAMITID = new LongField(_TABLE_MODEL, "tramitid", "tramitid");
	 public static final LongField TIPUSSOLICITUD = new LongField(_TABLE_MODEL, "tipussolicitud", "tipussolicitud");
	 public static final StringField ENTORN = new StringField(_TABLE_MODEL, "entorn", "entorn");


  public static final Field<?>[] ALL_TRAMITBDADESSOLI_FIELDS = {
    DADESSOLIID,
    TRAMITID,
    TIPUSSOLICITUD,
    ENTORN
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
DADESSOLIID
  };
}
