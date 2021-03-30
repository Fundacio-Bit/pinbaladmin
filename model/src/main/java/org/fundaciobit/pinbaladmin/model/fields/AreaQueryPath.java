
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AreaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AreaQueryPath() {
  }

  protected AreaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField AREAID() {
    return new LongField(getQueryPath(), AreaFields.AREAID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), AreaFields.NOM);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), AreaFields.ENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AreaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public DepartamentQueryPath DEPARTAMENTS() {
    return new DepartamentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AreaQueryPath.this.getQueryPath() + "departaments" + ".";
      }
    });
  }
*/

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return AreaQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
