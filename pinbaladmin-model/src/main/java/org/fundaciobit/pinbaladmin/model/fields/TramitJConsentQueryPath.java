
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TramitJConsentQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TramitJConsentQueryPath() {
  }

  protected TramitJConsentQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField CONSENTID() {
    return new LongField(getQueryPath(), TramitJConsentFields.CONSENTID);
  }

  public LongField TRAMITID() {
    return new LongField(getQueryPath(), TramitJConsentFields.TRAMITID);
  }

  public LongField ADJUNTID() {
    return new LongField(getQueryPath(), TramitJConsentFields.ADJUNTID);
  }

  public StringField CONSENTIMENT() {
    return new StringField(getQueryPath(), TramitJConsentFields.CONSENTIMENT);
  }

  public StringField URLCONSENTIMENT() {
    return new StringField(getQueryPath(), TramitJConsentFields.URLCONSENTIMENT);
  }

  public StringField CONSENTIMENTADJUNT() {
    return new StringField(getQueryPath(), TramitJConsentFields.CONSENTIMENTADJUNT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TramitJConsentFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TramitAPersAutQueryPath TRAMITAPERSAUT() {
    return new TramitAPersAutQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitJConsentQueryPath.this.getQueryPath() + "tramitAPersAut" + ".";
      }
    });
  }

  public FitxerQueryPath ADJUNT() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitJConsentQueryPath.this.getQueryPath() + "adjunt" + ".";
      }
    });
  }

}
