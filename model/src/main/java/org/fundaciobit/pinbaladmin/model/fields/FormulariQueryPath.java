
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class FormulariQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public FormulariQueryPath() {
  }

  protected FormulariQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField FORMULARIID() {
    return new LongField(getQueryPath(), FormulariFields.FORMULARIID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), FormulariFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), FormulariFields.DESCRIPCIO);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), FormulariFields.FITXERID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (FormulariFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public CampFormulariQueryPath CAMPFORMULARIS() {
    return new CampFormulariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FormulariQueryPath.this.getQueryPath() + "campFormularis" + ".";
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
          return FormulariQueryPath.this.getQueryPath() + "serveis" + ".";
      }
    });
  }
*/

  public FitxerQueryPath FITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FormulariQueryPath.this.getQueryPath() + "fitxer" + ".";
      }
    });
  }

}
