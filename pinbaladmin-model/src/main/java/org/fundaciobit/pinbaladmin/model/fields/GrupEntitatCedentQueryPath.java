
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class GrupEntitatCedentQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public GrupEntitatCedentQueryPath() {
  }

  protected GrupEntitatCedentQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField GRUPENTITATCEDENTID() {
    return new LongField(getQueryPath(), GrupEntitatCedentFields.GRUPENTITATCEDENTID);
  }

  public LongField GRUPENTITATID() {
    return new LongField(getQueryPath(), GrupEntitatCedentFields.GRUPENTITATID);
  }

  public LongField CEDENTID() {
    return new LongField(getQueryPath(), GrupEntitatCedentFields.CEDENTID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (GrupEntitatCedentFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public GrupEntitatQueryPath GRUPENTITAT() {
    return new GrupEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupEntitatCedentQueryPath.this.getQueryPath() + "grupEntitat" + ".";
      }
    });
  }

  public EntitatServeiQueryPath ENTITATSERVEI() {
    return new EntitatServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupEntitatCedentQueryPath.this.getQueryPath() + "entitatServei" + ".";
      }
    });
  }

}
