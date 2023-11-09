
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface DocumentFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "pad_document";


  public static final String _TABLE_MODEL = "document";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField DOCUMENTID = new LongField(_TABLE_MODEL, "documentID", "documentid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final LongField FITXERORIGINALID = new LongField(_TABLE_MODEL, "fitxerOriginalID", "fitxeroriginalid");
	 public static final LongField FITXERFIRMATID = new LongField(_TABLE_MODEL, "fitxerFirmatID", "fitxerfirmatid");
	 public static final StringField NOTES = new StringField(_TABLE_MODEL, "notes", "notes");
	 public static final LongField TIPUS = new LongField(_TABLE_MODEL, "tipus", "tipus");


  public static final Field<?>[] ALL_DOCUMENT_FIELDS = {
    DOCUMENTID,
    NOM,
    FITXERORIGINALID,
    FITXERFIRMATID,
    NOTES,
    TIPUS
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
DOCUMENTID
  };
}
