
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

  public LongField FITXERNORMAID() {
    return new LongField(getQueryPath(), TramitIServFields.FITXERNORMAID);
  }

  public StringField URLNORMA() {
    return new StringField(getQueryPath(), TramitIServFields.URLNORMA);
  }

  public StringField ARTICLES() {
    return new StringField(getQueryPath(), TramitIServFields.ARTICLES);
  }

  public StringField NORMA2() {
    return new StringField(getQueryPath(), TramitIServFields.NORMA2);
  }

  public LongField FITXERNORMA2ID() {
    return new LongField(getQueryPath(), TramitIServFields.FITXERNORMA2ID);
  }

  public StringField ARTICLES2() {
    return new StringField(getQueryPath(), TramitIServFields.ARTICLES2);
  }

  public StringField NORMA3() {
    return new StringField(getQueryPath(), TramitIServFields.NORMA3);
  }

  public LongField FITXERNORMA3ID() {
    return new LongField(getQueryPath(), TramitIServFields.FITXERNORMA3ID);
  }

  public StringField ARTICLES3() {
    return new StringField(getQueryPath(), TramitIServFields.ARTICLES3);
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

  public FitxerQueryPath FITXERNORMA() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitIServQueryPath.this.getQueryPath() + "fitxernorma" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERNORMA2() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitIServQueryPath.this.getQueryPath() + "fitxernorma2" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERNORMA3() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitIServQueryPath.this.getQueryPath() + "fitxernorma3" + ".";
      }
    });
  }

}
