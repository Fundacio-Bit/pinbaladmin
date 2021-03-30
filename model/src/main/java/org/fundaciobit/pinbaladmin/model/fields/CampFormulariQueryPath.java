
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class CampFormulariQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public CampFormulariQueryPath() {
  }

  protected CampFormulariQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField CAMPFORMULARIID() {
    return new LongField(getQueryPath(), CampFormulariFields.CAMPFORMULARIID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), CampFormulariFields.NOM);
  }

  public StringField CAMPPDF() {
    return new StringField(getQueryPath(), CampFormulariFields.CAMPPDF);
  }

  public LongField FORMULARIID() {
    return new LongField(getQueryPath(), CampFormulariFields.FORMULARIID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (CampFormulariFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public CampSolicitudQueryPath CAMPSOLICITUDS() {
    return new CampSolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CampFormulariQueryPath.this.getQueryPath() + "campSolicituds" + ".";
      }
    });
  }
*/

  public FormulariQueryPath FORMULARI() {
    return new FormulariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CampFormulariQueryPath.this.getQueryPath() + "formulari" + ".";
      }
    });
  }

}
