
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class GrupEntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public GrupEntitatQueryPath() {
  }

  protected GrupEntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField GRUPENTITATID() {
    return new LongField(getQueryPath(), GrupEntitatFields.GRUPENTITATID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), GrupEntitatFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), GrupEntitatFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (GrupEntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EntitatQueryPath ENTITATS() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupEntitatQueryPath.this.getQueryPath() + "entitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public GrupEntitatCedentQueryPath GRUPENTITATCEDENTS() {
    return new GrupEntitatCedentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupEntitatQueryPath.this.getQueryPath() + "grupEntitatCedents" + ".";
      }
    });
  }
*/

}
