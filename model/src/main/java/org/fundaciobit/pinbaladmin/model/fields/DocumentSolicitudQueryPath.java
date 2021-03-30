
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class DocumentSolicitudQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public DocumentSolicitudQueryPath() {
  }

  protected DocumentSolicitudQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField DOCUMENTSOLICITUDID() {
    return new LongField(getQueryPath(), DocumentSolicitudFields.DOCUMENTSOLICITUDID);
  }

  public LongField DOCUMENTID() {
    return new LongField(getQueryPath(), DocumentSolicitudFields.DOCUMENTID);
  }

  public LongField SOLICITUDID() {
    return new LongField(getQueryPath(), DocumentSolicitudFields.SOLICITUDID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (DocumentSolicitudFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public DocumentQueryPath DOCUMENT() {
    return new DocumentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return DocumentSolicitudQueryPath.this.getQueryPath() + "document" + ".";
      }
    });
  }

  public SolicitudQueryPath SOLICITUD() {
    return new SolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return DocumentSolicitudQueryPath.this.getQueryPath() + "solicitud" + ".";
      }
    });
  }

}
