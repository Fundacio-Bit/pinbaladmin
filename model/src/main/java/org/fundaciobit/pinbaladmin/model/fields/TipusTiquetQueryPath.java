
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TipusTiquetQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TipusTiquetQueryPath() {
  }

  protected TipusTiquetQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TIPUSTIQUETID() {
    return new LongField(getQueryPath(), TipusTiquetFields.TIPUSTIQUETID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TipusTiquetFields.NOM);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TipusTiquetFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TiquetQueryPath TIQUETS() {
    return new TiquetQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TipusTiquetQueryPath.this.getQueryPath() + "tiquets" + ".";
      }
    });
  }
*/

}
