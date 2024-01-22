
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TramitIServQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TramitIServQueryPath() {
  }

  protected TramitIServQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField SERVID() {
    return new LongField(getQueryPath(), TramitIServFields.SERVID);
  }

  public LongField TRAMITID() {
    return new LongField(getQueryPath(), TramitIServFields.TRAMITID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TramitIServFields.NOM);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), TramitIServFields.CODI);
  }

  public StringField NORMA() {
    return new StringField(getQueryPath(), TramitIServFields.NORMA);
  }

  public StringField URLNORMA() {
    return new StringField(getQueryPath(), TramitIServFields.URLNORMA);
  }

  public StringField ARTICLES() {
    return new StringField(getQueryPath(), TramitIServFields.ARTICLES);
  }

  public StringField CONSENTIMENT() {
    return new StringField(getQueryPath(), TramitIServFields.CONSENTIMENT);
  }

  public StringField CONSENTIMENTPUBLICAT() {
    return new StringField(getQueryPath(), TramitIServFields.CONSENTIMENTPUBLICAT);
  }

  public StringField URLCONSENTIMENT() {
    return new StringField(getQueryPath(), TramitIServFields.URLCONSENTIMENT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TramitIServFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TramitAPersAutQueryPath TRAMITAPERSAUT() {
    return new TramitAPersAutQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitIServQueryPath.this.getQueryPath() + "tramitAPersAut" + ".";
      }
    });
  }

}
