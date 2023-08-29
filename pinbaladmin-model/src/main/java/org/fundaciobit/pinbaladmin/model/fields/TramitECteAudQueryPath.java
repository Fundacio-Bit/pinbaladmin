
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TramitECteAudQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TramitECteAudQueryPath() {
  }

  protected TramitECteAudQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField CTEAUDID() {
    return new LongField(getQueryPath(), TramitECteAudFields.CTEAUDID);
  }

  public LongField TRAMITID() {
    return new LongField(getQueryPath(), TramitECteAudFields.TRAMITID);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), TramitECteAudFields.NIF);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TramitECteAudFields.NOM);
  }

  public StringField LLINATGE1() {
    return new StringField(getQueryPath(), TramitECteAudFields.LLINATGE1);
  }

  public StringField LLINATGE2() {
    return new StringField(getQueryPath(), TramitECteAudFields.LLINATGE2);
  }

  public StringField CARREC() {
    return new StringField(getQueryPath(), TramitECteAudFields.CARREC);
  }

  public StringField TELEFON() {
    return new StringField(getQueryPath(), TramitECteAudFields.TELEFON);
  }

  public StringField MAIL() {
    return new StringField(getQueryPath(), TramitECteAudFields.MAIL);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TramitECteAudFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TramitAPersAutQueryPath TRAMITAPERSAUT() {
    return new TramitAPersAutQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitECteAudQueryPath.this.getQueryPath() + "tramitAPersAut" + ".";
      }
    });
  }

}
