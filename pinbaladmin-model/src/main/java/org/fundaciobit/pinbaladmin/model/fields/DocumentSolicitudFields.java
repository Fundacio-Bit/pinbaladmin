
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface DocumentSolicitudFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_documentsolicitud";


  public static final String _TABLE_MODEL = "documentSolicitud";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField DOCUMENTSOLICITUDID = new LongField(_TABLE_MODEL, "documentSolicitudID", "documentsolicitudid");  // PK
	 public static final LongField DOCUMENTID = new LongField(_TABLE_MODEL, "documentID", "documentid");
	 public static final LongField SOLICITUDID = new LongField(_TABLE_MODEL, "solicitudID", "solicitudid");


  public static final Field<?>[] ALL_DOCUMENTSOLICITUD_FIELDS = {
    DOCUMENTSOLICITUDID,
    DOCUMENTID,
    SOLICITUDID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
DOCUMENTSOLICITUDID
  };
}
