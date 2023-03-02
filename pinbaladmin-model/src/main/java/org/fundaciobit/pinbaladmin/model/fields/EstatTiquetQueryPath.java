
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EstatTiquetQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EstatTiquetQueryPath() {
  }

  protected EstatTiquetQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ESTATTIQUETID() {
    return new LongField(getQueryPath(), EstatTiquetFields.ESTATTIQUETID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EstatTiquetFields.NOM);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EstatTiquetFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TiquetQueryPath TIQUETS() {
    return new TiquetQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EstatTiquetQueryPath.this.getQueryPath() + "tiquets" + ".";
      }
    });
  }
*/

}
