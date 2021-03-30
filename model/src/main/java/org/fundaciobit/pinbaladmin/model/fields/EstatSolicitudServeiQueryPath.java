
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EstatSolicitudServeiQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EstatSolicitudServeiQueryPath() {
  }

  protected EstatSolicitudServeiQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ESTATSOLICITUDSERVEIID() {
    return new LongField(getQueryPath(), EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EstatSolicitudServeiFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), EstatSolicitudServeiFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EstatSolicitudServeiFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public SolicitudServeiQueryPath SOLICITUDSERVEIS() {
    return new SolicitudServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EstatSolicitudServeiQueryPath.this.getQueryPath() + "solicitudServeis" + ".";
      }
    });
  }
*/

}
