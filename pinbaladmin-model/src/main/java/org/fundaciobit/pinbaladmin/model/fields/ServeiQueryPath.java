
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class ServeiQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public ServeiQueryPath() {
  }

  protected ServeiQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField SERVEIID() {
    return new LongField(getQueryPath(), ServeiFields.SERVEIID);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), ServeiFields.CODI);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), ServeiFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), ServeiFields.DESCRIPCIO);
  }

  public LongField FORMULARIID() {
    return new LongField(getQueryPath(), ServeiFields.FORMULARIID);
  }

  public LongField ENTITATSERVEIID() {
    return new LongField(getQueryPath(), ServeiFields.ENTITATSERVEIID);
  }

  public LongField ESTATSERVEIID() {
    return new LongField(getQueryPath(), ServeiFields.ESTATSERVEIID);
  }

  public IntegerField TIPUSCONSENTIMENT() {
    return new IntegerField(getQueryPath(), ServeiFields.TIPUSCONSENTIMENT);
  }

  public BooleanField OCULT() {
    return new BooleanField(getQueryPath(), ServeiFields.OCULT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (ServeiFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public SolicitudServeiQueryPath SOLICITUDSERVEIS() {
    return new SolicitudServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ServeiQueryPath.this.getQueryPath() + "solicitudServeis" + ".";
      }
    });
  }
*/

  public FormulariQueryPath FORMULARI() {
    return new FormulariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ServeiQueryPath.this.getQueryPath() + "formulari" + ".";
      }
    });
  }

  public EntitatServeiQueryPath ENTITATSERVEI() {
    return new EntitatServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ServeiQueryPath.this.getQueryPath() + "entitatServei" + ".";
      }
    });
  }

}
