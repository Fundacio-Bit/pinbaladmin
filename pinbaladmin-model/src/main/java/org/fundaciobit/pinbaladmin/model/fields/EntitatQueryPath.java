
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EntitatQueryPath() {
  }

  protected EntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ENTITATID() {
    return new LongField(getQueryPath(), EntitatFields.ENTITATID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EntitatFields.NOM);
  }

  public StringField PERSONACONTACTE() {
    return new StringField(getQueryPath(), EntitatFields.PERSONACONTACTE);
  }

  public StringField CIF() {
    return new StringField(getQueryPath(), EntitatFields.CIF);
  }

  public LongField GRUPENTITATID() {
    return new LongField(getQueryPath(), EntitatFields.GRUPENTITATID);
  }

  public BooleanField CONVENIPMSBAE() {
    return new BooleanField(getQueryPath(), EntitatFields.CONVENIPMSBAE);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public AreaQueryPath AREAS() {
    return new AreaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "areas" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public DocumentEntitatQueryPath DOCUMENTENTITATS() {
    return new DocumentEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "documentEntitats" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public OrganQueryPath ORGANS() {
    return new OrganQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "organs" + ".";
      }
    });
  }
*/

  public GrupEntitatQueryPath GRUPENTITAT() {
    return new GrupEntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "grupEntitat" + ".";
      }
    });
  }

}
