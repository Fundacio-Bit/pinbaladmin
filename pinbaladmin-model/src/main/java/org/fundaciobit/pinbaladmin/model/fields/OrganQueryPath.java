
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class OrganQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public OrganQueryPath() {
  }

  protected OrganQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ORGANID() {
    return new LongField(getQueryPath(), OrganFields.ORGANID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), OrganFields.NOM);
  }

  public StringField DIR3() {
    return new StringField(getQueryPath(), OrganFields.DIR3);
  }

  public StringField DIR3PARE() {
    return new StringField(getQueryPath(), OrganFields.DIR3PARE);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), OrganFields.ENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (OrganFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public SolicitudQueryPath SOLICITUDS() {
    return new SolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return OrganQueryPath.this.getQueryPath() + "solicituds" + ".";
      }
    });
  }
*/

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return OrganQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
