
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PinfoQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PinfoQueryPath() {
  }

  protected PinfoQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PINFOID() {
    return new LongField(getQueryPath(), PinfoFields.PINFOID);
  }

  public LongField INCIDENCIAID() {
    return new LongField(getQueryPath(), PinfoFields.INCIDENCIAID);
  }

  public StringField ENTITAT() {
    return new StringField(getQueryPath(), PinfoFields.ENTITAT);
  }

  public StringField SOLICITANTNIF() {
    return new StringField(getQueryPath(), PinfoFields.SOLICITANTNIF);
  }

  public StringField SOLICITANTNOM() {
    return new StringField(getQueryPath(), PinfoFields.SOLICITANTNOM);
  }

  public LongField ESTAT() {
    return new LongField(getQueryPath(), PinfoFields.ESTAT);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), PinfoFields.FITXERID);
  }

  public LongField FITXERFIRMATID() {
    return new LongField(getQueryPath(), PinfoFields.FITXERFIRMATID);
  }

  public StringField PORTAFIBID() {
    return new StringField(getQueryPath(), PinfoFields.PORTAFIBID);
  }

  public StringField DESTINATARINIF() {
    return new StringField(getQueryPath(), PinfoFields.DESTINATARINIF);
  }

  public StringField DESTINATARINOM() {
    return new StringField(getQueryPath(), PinfoFields.DESTINATARINOM);
  }

  public StringField MISSATGEPINBAL() {
    return new StringField(getQueryPath(), PinfoFields.MISSATGEPINBAL);
  }

  public StringField LOGPPNBAL() {
    return new StringField(getQueryPath(), PinfoFields.LOGPPNBAL);
  }

  public StringField MISSATGESOLICITANT() {
    return new StringField(getQueryPath(), PinfoFields.MISSATGESOLICITANT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PinfoFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PinfoDataQueryPath PINFODATAS() {
    return new PinfoDataQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PinfoQueryPath.this.getQueryPath() + "pinfoDatas" + ".";
      }
    });
  }
*/

  public IncidenciaTecnicaQueryPath INCIDENCIATECNICA() {
    return new IncidenciaTecnicaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PinfoQueryPath.this.getQueryPath() + "incidenciaTecnica" + ".";
      }
    });
  }

  public FitxerQueryPath FITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PinfoQueryPath.this.getQueryPath() + "fitxer" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERFIRMAT() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PinfoQueryPath.this.getQueryPath() + "fitxerfirmat" + ".";
      }
    });
  }

}
