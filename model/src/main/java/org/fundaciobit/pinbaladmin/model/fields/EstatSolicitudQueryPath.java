
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EstatSolicitudQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EstatSolicitudQueryPath() {
  }

  protected EstatSolicitudQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ESTATSOLICITUDID() {
    return new LongField(getQueryPath(), EstatSolicitudFields.ESTATSOLICITUDID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EstatSolicitudFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), EstatSolicitudFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EstatSolicitudFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public SolicitudQueryPath SOLICITUDS() {
    return new SolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EstatSolicitudQueryPath.this.getQueryPath() + "solicituds" + ".";
      }
    });
  }
*/

}
