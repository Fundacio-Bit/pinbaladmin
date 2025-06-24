
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class ModificacioSoliServQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public ModificacioSoliServQueryPath() {
  }

  protected ModificacioSoliServQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField MODSOLISERVID() {
    return new LongField(getQueryPath(), ModificacioSoliServFields.MODSOLISERVID);
  }

  public LongField SOLISERVID() {
    return new LongField(getQueryPath(), ModificacioSoliServFields.SOLISERVID);
  }

  public LongField MODSOLIID() {
    return new LongField(getQueryPath(), ModificacioSoliServFields.MODSOLIID);
  }

  public StringField ESTAT() {
    return new StringField(getQueryPath(), ModificacioSoliServFields.ESTAT);
  }

  public StringField NORMA1() {
    return new StringField(getQueryPath(), ModificacioSoliServFields.NORMA1);
  }

  public StringField ARTICLES1() {
    return new StringField(getQueryPath(), ModificacioSoliServFields.ARTICLES1);
  }

  public LongField FITXERNORMA1ID() {
    return new LongField(getQueryPath(), ModificacioSoliServFields.FITXERNORMA1ID);
  }

  public StringField NORMA2() {
    return new StringField(getQueryPath(), ModificacioSoliServFields.NORMA2);
  }

  public StringField ARTICLES2() {
    return new StringField(getQueryPath(), ModificacioSoliServFields.ARTICLES2);
  }

  public LongField FITXERNORMA2ID() {
    return new LongField(getQueryPath(), ModificacioSoliServFields.FITXERNORMA2ID);
  }

  public StringField NORMA3() {
    return new StringField(getQueryPath(), ModificacioSoliServFields.NORMA3);
  }

  public StringField ARTICLES3() {
    return new StringField(getQueryPath(), ModificacioSoliServFields.ARTICLES3);
  }

  public LongField FITXERNORMA3ID() {
    return new LongField(getQueryPath(), ModificacioSoliServFields.FITXERNORMA3ID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (ModificacioSoliServFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public SolicitudServeiQueryPath SOLICITUDSERVEI() {
    return new SolicitudServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModificacioSoliServQueryPath.this.getQueryPath() + "solicitudServei" + ".";
      }
    });
  }

  public ModificacioSolicitudQueryPath MODIFICACIOSOLICITUD() {
    return new ModificacioSolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModificacioSoliServQueryPath.this.getQueryPath() + "modificacioSolicitud" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERNORMA1() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModificacioSoliServQueryPath.this.getQueryPath() + "fitxerNorma1" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERNORMA2() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModificacioSoliServQueryPath.this.getQueryPath() + "fitxerNorma2" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERNORMA3() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModificacioSoliServQueryPath.this.getQueryPath() + "fitxerNorma3" + ".";
      }
    });
  }

}
