
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EstatServeiQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EstatServeiQueryPath() {
  }

  protected EstatServeiQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ESTATSERVEIID() {
    return new LongField(getQueryPath(), EstatServeiFields.ESTATSERVEIID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EstatServeiFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), EstatServeiFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EstatServeiFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ServeiQueryPath SERVEIS() {
    return new ServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EstatServeiQueryPath.this.getQueryPath() + "serveis" + ".";
      }
    });
  }
*/

}
