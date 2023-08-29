
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TramitDCteAutQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TramitDCteAutQueryPath() {
  }

  protected TramitDCteAutQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField CTEAUTID() {
    return new LongField(getQueryPath(), TramitDCteAutFields.CTEAUTID);
  }

  public LongField TRAMITID() {
    return new LongField(getQueryPath(), TramitDCteAutFields.TRAMITID);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), TramitDCteAutFields.NIF);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TramitDCteAutFields.NOM);
  }

  public StringField LLINATGE1() {
    return new StringField(getQueryPath(), TramitDCteAutFields.LLINATGE1);
  }

  public StringField LLINATGE2() {
    return new StringField(getQueryPath(), TramitDCteAutFields.LLINATGE2);
  }

  public StringField CARREC() {
    return new StringField(getQueryPath(), TramitDCteAutFields.CARREC);
  }

  public StringField TELEFON() {
    return new StringField(getQueryPath(), TramitDCteAutFields.TELEFON);
  }

  public StringField MAIL() {
    return new StringField(getQueryPath(), TramitDCteAutFields.MAIL);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TramitDCteAutFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TramitAPersAutQueryPath TRAMITAPERSAUT() {
    return new TramitAPersAutQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitDCteAutQueryPath.this.getQueryPath() + "tramitAPersAut" + ".";
      }
    });
  }

}
