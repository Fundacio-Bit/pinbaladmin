
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class ContacteQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public ContacteQueryPath() {
  }

  protected ContacteQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField CONTACTEID() {
    return new LongField(getQueryPath(), ContacteFields.CONTACTEID);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), ContacteFields.NIF);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), ContacteFields.NOM);
  }

  public StringField LLINATGE1() {
    return new StringField(getQueryPath(), ContacteFields.LLINATGE1);
  }

  public StringField LLINATGE2() {
    return new StringField(getQueryPath(), ContacteFields.LLINATGE2);
  }

  public StringField CARREC() {
    return new StringField(getQueryPath(), ContacteFields.CARREC);
  }

  public StringField TELEFON() {
    return new StringField(getQueryPath(), ContacteFields.TELEFON);
  }

  public StringField MAIL() {
    return new StringField(getQueryPath(), ContacteFields.MAIL);
  }

  public StringField USERNAME() {
    return new StringField(getQueryPath(), ContacteFields.USERNAME);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (ContacteFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public SolicitudQueryPath SOLICITUD_CONTACTEPERSONAIDS() {
    return new SolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ContacteQueryPath.this.getQueryPath() + "solicitud_contactepersonaids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public SolicitudQueryPath SOLICITUD_CONTACTERESPONSABLEIDS() {
    return new SolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ContacteQueryPath.this.getQueryPath() + "solicitud_contacteresponsableids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public SolicitudQueryPath SOLICITUD_CONTACTETITULARIDS() {
    return new SolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ContacteQueryPath.this.getQueryPath() + "solicitud_contactetitularids" + ".";
      }
    });
  }
*/

}
