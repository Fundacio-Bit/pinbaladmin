
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface CampSolicitudFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_campsolicitud";


  public static final String _TABLE_MODEL = "campSolicitud";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField CAMPSOLICITUDID = new LongField(_TABLE_MODEL, "campSolicitudID", "campsolicitudid");  // PK
	 public static final LongField CAMPFORMULARIID = new LongField(_TABLE_MODEL, "campFormulariID", "campformulariid");
	 public static final LongField SOLICITUDSERVEIID = new LongField(_TABLE_MODEL, "solicitudServeiID", "solicitudserveiid");
	 public static final StringField VALOR = new StringField(_TABLE_MODEL, "valor", "valor");


  public static final Field<?>[] ALL_CAMPSOLICITUD_FIELDS = {
    CAMPSOLICITUDID,
    CAMPFORMULARIID,
    SOLICITUDSERVEIID,
    VALOR
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
CAMPSOLICITUDID
  };
}
