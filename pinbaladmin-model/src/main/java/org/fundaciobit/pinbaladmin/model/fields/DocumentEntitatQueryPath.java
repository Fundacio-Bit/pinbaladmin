
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class DocumentEntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public DocumentEntitatQueryPath() {
  }

  protected DocumentEntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField DOCUMENTENTITATID() {
    return new LongField(getQueryPath(), DocumentEntitatFields.DOCUMENTENTITATID);
  }

  public StringField TITOL() {
    return new StringField(getQueryPath(), DocumentEntitatFields.TITOL);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), DocumentEntitatFields.DESCRIPCIO);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), DocumentEntitatFields.ENTITATID);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), DocumentEntitatFields.FITXERID);
  }

  public TimestampField DATAALTA() {
    return new TimestampField(getQueryPath(), DocumentEntitatFields.DATAALTA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (DocumentEntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return DocumentEntitatQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

  public FitxerQueryPath FITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return DocumentEntitatQueryPath.this.getQueryPath() + "fitxer" + ".";
      }
    });
  }

}
