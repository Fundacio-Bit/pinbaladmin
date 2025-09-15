
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class InfoMadridQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public InfoMadridQueryPath() {
  }

  protected InfoMadridQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField INFOMADRIDID() {
    return new LongField(getQueryPath(), InfoMadridFields.INFOMADRIDID);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), InfoMadridFields.CODI);
  }

  public LongField ESTATPROCEDIMENT() {
    return new LongField(getQueryPath(), InfoMadridFields.ESTATPROCEDIMENT);
  }

  public LongField ESTATAUTORITZACIO() {
    return new LongField(getQueryPath(), InfoMadridFields.ESTATAUTORITZACIO);
  }

  public StringField MISSATGE() {
    return new StringField(getQueryPath(), InfoMadridFields.MISSATGE);
  }

  public StringField CONSULTA() {
    return new StringField(getQueryPath(), InfoMadridFields.CONSULTA);
  }

  public StringField TITULARNOM() {
    return new StringField(getQueryPath(), InfoMadridFields.TITULARNOM);
  }

  public StringField TITULARNIF() {
    return new StringField(getQueryPath(), InfoMadridFields.TITULARNIF);
  }

  public TimestampField DATAAUTORITZACIO() {
    return new TimestampField(getQueryPath(), InfoMadridFields.DATAAUTORITZACIO);
  }

  public TimestampField DATAENVIAMENT() {
    return new TimestampField(getQueryPath(), InfoMadridFields.DATAENVIAMENT);
  }

  public LongField INTENTS() {
    return new LongField(getQueryPath(), InfoMadridFields.INTENTS);
  }

  public TimestampField DATACONSULTA() {
    return new TimestampField(getQueryPath(), InfoMadridFields.DATACONSULTA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (InfoMadridFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public SolicitudQueryPath SOLICITUDS() {
    return new SolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return InfoMadridQueryPath.this.getQueryPath() + "solicituds" + ".";
      }
    });
  }
*/

}
