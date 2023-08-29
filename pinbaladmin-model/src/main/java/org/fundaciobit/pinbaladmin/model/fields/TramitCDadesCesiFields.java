
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TramitCDadesCesiFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_tramit_c_dades_cesi";


  public static final String _TABLE_MODEL = "tramitCDadesCesi";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField DADESCESIID = new LongField(_TABLE_MODEL, "dadescesiid", "dadescesiid");  // PK
	 public static final LongField TRAMITID = new LongField(_TABLE_MODEL, "tramitid", "tramitid");
	 public static final StringField DENOMINACIO = new StringField(_TABLE_MODEL, "denominacio", "denominacio");
	 public static final StringField NIF = new StringField(_TABLE_MODEL, "nif", "nif");
	 public static final StringField RESPONSABLE = new StringField(_TABLE_MODEL, "responsable", "responsable");
	 public static final StringField DIR3RESPONSABLE = new StringField(_TABLE_MODEL, "dir3responsable", "dir3responsable");
	 public static final StringField DIR3ARREL = new StringField(_TABLE_MODEL, "dir3arrel", "dir3arrel");
	 public static final StringField DIRECCIO = new StringField(_TABLE_MODEL, "direccio", "direccio");
	 public static final StringField CODIPOSTAL = new StringField(_TABLE_MODEL, "codipostal", "codipostal");
	 public static final StringField MUNICIPI = new StringField(_TABLE_MODEL, "municipi", "municipi");


  public static final Field<?>[] ALL_TRAMITCDADESCESI_FIELDS = {
    DADESCESIID,
    TRAMITID,
    DENOMINACIO,
    NIF,
    RESPONSABLE,
    DIR3RESPONSABLE,
    DIR3ARREL,
    DIRECCIO,
    CODIPOSTAL,
    MUNICIPI
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
DADESCESIID
  };
}
