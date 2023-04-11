
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class IncidenciaTecnicaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public IncidenciaTecnicaQueryPath() {
  }

  protected IncidenciaTecnicaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField INCIDENCIATECNICAID() {
    return new LongField(getQueryPath(), IncidenciaTecnicaFields.INCIDENCIATECNICAID);
  }

  public StringField TITOL() {
    return new StringField(getQueryPath(), IncidenciaTecnicaFields.TITOL);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), IncidenciaTecnicaFields.DESCRIPCIO);
  }

  public TimestampField DATAINICI() {
    return new TimestampField(getQueryPath(), IncidenciaTecnicaFields.DATAINICI);
  }

  public TimestampField DATAFI() {
    return new TimestampField(getQueryPath(), IncidenciaTecnicaFields.DATAFI);
  }

  public IntegerField ESTAT() {
    return new IntegerField(getQueryPath(), IncidenciaTecnicaFields.ESTAT);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), IncidenciaTecnicaFields.TIPUS);
  }

  public StringField NOMENTITAT() {
    return new StringField(getQueryPath(), IncidenciaTecnicaFields.NOMENTITAT);
  }

  public StringField CONTACTENOM() {
    return new StringField(getQueryPath(), IncidenciaTecnicaFields.CONTACTENOM);
  }

  public StringField CONTACTEEMAIL() {
    return new StringField(getQueryPath(), IncidenciaTecnicaFields.CONTACTEEMAIL);
  }

  public StringField CONTACTETELEFON() {
    return new StringField(getQueryPath(), IncidenciaTecnicaFields.CONTACTETELEFON);
  }

  public StringField CAIDIDENTIFICADORCONSULTA() {
    return new StringField(getQueryPath(), IncidenciaTecnicaFields.CAIDIDENTIFICADORCONSULTA);
  }

  public StringField CAIDNUMEROSEGUIMENT() {
    return new StringField(getQueryPath(), IncidenciaTecnicaFields.CAIDNUMEROSEGUIMENT);
  }

  public StringField CREADOR() {
    return new StringField(getQueryPath(), IncidenciaTecnicaFields.CREADOR);
  }

  public StringField OPERADOR() {
    return new StringField(getQueryPath(), IncidenciaTecnicaFields.OPERADOR);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (IncidenciaTecnicaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EventQueryPath EVENTS() {
    return new EventQueryPath(new QueryPath() {
      public String getQueryPath() {
          return IncidenciaTecnicaQueryPath.this.getQueryPath() + "events" + ".";
      }
    });
  }
*/

}
