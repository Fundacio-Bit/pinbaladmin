
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TramitFCteTecQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TramitFCteTecQueryPath() {
  }

  protected TramitFCteTecQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField CTETECID() {
    return new LongField(getQueryPath(), TramitFCteTecFields.CTETECID);
  }

  public LongField TRAMITID() {
    return new LongField(getQueryPath(), TramitFCteTecFields.TRAMITID);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), TramitFCteTecFields.NIF);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TramitFCteTecFields.NOM);
  }

  public StringField LLINATGE1() {
    return new StringField(getQueryPath(), TramitFCteTecFields.LLINATGE1);
  }

  public StringField LLINATGE2() {
    return new StringField(getQueryPath(), TramitFCteTecFields.LLINATGE2);
  }

  public StringField CARREC() {
    return new StringField(getQueryPath(), TramitFCteTecFields.CARREC);
  }

  public StringField TELEFON() {
    return new StringField(getQueryPath(), TramitFCteTecFields.TELEFON);
  }

  public StringField MAIL() {
    return new StringField(getQueryPath(), TramitFCteTecFields.MAIL);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TramitFCteTecFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TramitAPersAutQueryPath TRAMITAPERSAUT() {
    return new TramitAPersAutQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitFCteTecQueryPath.this.getQueryPath() + "tramitAPersAut" + ".";
      }
    });
  }

}
