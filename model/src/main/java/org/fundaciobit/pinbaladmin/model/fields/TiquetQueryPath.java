
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TiquetQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TiquetQueryPath() {
  }

  protected TiquetQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TIQUETID() {
    return new LongField(getQueryPath(), TiquetFields.TIQUETID);
  }

  public StringField TITOL() {
    return new StringField(getQueryPath(), TiquetFields.TITOL);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), TiquetFields.DESCRIPCIO);
  }

  public StringField INFORMADOR() {
    return new StringField(getQueryPath(), TiquetFields.INFORMADOR);
  }

  public TimestampField DATAALTA() {
    return new TimestampField(getQueryPath(), TiquetFields.DATAALTA);
  }

  public LongField ESTATTIQUETID() {
    return new LongField(getQueryPath(), TiquetFields.ESTATTIQUETID);
  }

  public LongField TIPUSTIQUETID() {
    return new LongField(getQueryPath(), TiquetFields.TIPUSTIQUETID);
  }

  public StringField VERSIOPINBAL() {
    return new StringField(getQueryPath(), TiquetFields.VERSIOPINBAL);
  }

  public TimestampField DATAINICI() {
    return new TimestampField(getQueryPath(), TiquetFields.DATAINICI);
  }

  public TimestampField DATAINCIDENCIA() {
    return new TimestampField(getQueryPath(), TiquetFields.DATAINCIDENCIA);
  }

  public StringField SOLUCIONATPER() {
    return new StringField(getQueryPath(), TiquetFields.SOLUCIONATPER);
  }

  public TimestampField DATAFI() {
    return new TimestampField(getQueryPath(), TiquetFields.DATAFI);
  }

  public StringField NOTES() {
    return new StringField(getQueryPath(), TiquetFields.NOTES);
  }

  public IntegerField ENTORN() {
    return new IntegerField(getQueryPath(), TiquetFields.ENTORN);
  }

  public LongField ADJUNT1ID() {
    return new LongField(getQueryPath(), TiquetFields.ADJUNT1ID);
  }

  public LongField ADJUNT2ID() {
    return new LongField(getQueryPath(), TiquetFields.ADJUNT2ID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TiquetFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public EstatTiquetQueryPath ESTATTIQUET() {
    return new EstatTiquetQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TiquetQueryPath.this.getQueryPath() + "estatTiquet" + ".";
      }
    });
  }

  public TipusTiquetQueryPath TIPUSTIQUET() {
    return new TipusTiquetQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TiquetQueryPath.this.getQueryPath() + "tipusTiquet" + ".";
      }
    });
  }

  public FitxerQueryPath ADJUNT1() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TiquetQueryPath.this.getQueryPath() + "adjunt1" + ".";
      }
    });
  }

  public FitxerQueryPath ADJUNT2() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TiquetQueryPath.this.getQueryPath() + "adjunt2" + ".";
      }
    });
  }

}
