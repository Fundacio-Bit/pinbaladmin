
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EntitatServeiQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EntitatServeiQueryPath() {
  }

  protected EntitatServeiQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ENTITATSERVEIID() {
    return new LongField(getQueryPath(), EntitatServeiFields.ENTITATSERVEIID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EntitatServeiFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), EntitatServeiFields.DESCRIPCIO);
  }

  public BooleanField BALEARS() {
    return new BooleanField(getQueryPath(), EntitatServeiFields.BALEARS);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EntitatServeiFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public DocumentCedentQueryPath DOCUMENTCEDENTS() {
    return new DocumentCedentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatServeiQueryPath.this.getQueryPath() + "documentCedents" + ".";
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
          return EntitatServeiQueryPath.this.getQueryPath() + "grupEntitatCedents" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ServeiQueryPath SERVEIS() {
    return new ServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatServeiQueryPath.this.getQueryPath() + "serveis" + ".";
      }
    });
  }
*/

}
