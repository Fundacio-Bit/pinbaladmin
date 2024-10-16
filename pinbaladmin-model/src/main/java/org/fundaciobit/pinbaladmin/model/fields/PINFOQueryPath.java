
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PINFOQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PINFOQueryPath() {
  }

  protected PINFOQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PINFOID() {
    return new LongField(getQueryPath(), PINFOFields.PINFOID);
  }

  public LongField INCIDENCIAID() {
    return new LongField(getQueryPath(), PINFOFields.INCIDENCIAID);
  }

  public LongField ESTAT() {
    return new LongField(getQueryPath(), PINFOFields.ESTAT);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), PINFOFields.FITXERID);
  }

  public LongField FITXERFIRMATID() {
    return new LongField(getQueryPath(), PINFOFields.FITXERFIRMATID);
  }

  public StringField PORTAFIBID() {
    return new StringField(getQueryPath(), PINFOFields.PORTAFIBID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PINFOFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PinfoDataQueryPath PINFODATAS() {
    return new PinfoDataQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PINFOQueryPath.this.getQueryPath() + "pinfoDatas" + ".";
      }
    });
  }
*/

  public IncidenciaTecnicaQueryPath INCIDENCIATECNICA() {
    return new IncidenciaTecnicaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PINFOQueryPath.this.getQueryPath() + "incidenciaTecnica" + ".";
      }
    });
  }

  public FitxerQueryPath FITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PINFOQueryPath.this.getQueryPath() + "fitxer" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERFIRMAT() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PINFOQueryPath.this.getQueryPath() + "fitxerfirmat" + ".";
      }
    });
  }

}
