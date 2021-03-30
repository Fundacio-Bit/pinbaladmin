
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class DocumentCedentQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public DocumentCedentQueryPath() {
  }

  protected DocumentCedentQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField DOCUMENTCEDENTID() {
    return new LongField(getQueryPath(), DocumentCedentFields.DOCUMENTCEDENTID);
  }

  public StringField TITOL() {
    return new StringField(getQueryPath(), DocumentCedentFields.TITOL);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), DocumentCedentFields.DESCRIPCIO);
  }

  public LongField ENTITATSERVEIID() {
    return new LongField(getQueryPath(), DocumentCedentFields.ENTITATSERVEIID);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), DocumentCedentFields.DATACREACIO);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), DocumentCedentFields.FITXERID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (DocumentCedentFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EntitatServeiQueryPath ENTITATSERVEI() {
    return new EntitatServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return DocumentCedentQueryPath.this.getQueryPath() + "entitatServei" + ".";
      }
    });
  }

  public FitxerQueryPath FITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return DocumentCedentQueryPath.this.getQueryPath() + "fitxer" + ".";
      }
    });
  }

}
