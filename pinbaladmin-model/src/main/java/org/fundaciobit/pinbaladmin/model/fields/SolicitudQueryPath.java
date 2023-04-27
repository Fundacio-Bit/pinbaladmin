
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class SolicitudQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public SolicitudQueryPath() {
  }

  protected SolicitudQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField SOLICITUDID() {
    return new LongField(getQueryPath(), SolicitudFields.SOLICITUDID);
  }

  public StringField PROCEDIMENTCODI() {
    return new StringField(getQueryPath(), SolicitudFields.PROCEDIMENTCODI);
  }

  public StringField CODIDESCRIPTIU() {
    return new StringField(getQueryPath(), SolicitudFields.CODIDESCRIPTIU);
  }

  public StringField PROCEDIMENTNOM() {
    return new StringField(getQueryPath(), SolicitudFields.PROCEDIMENTNOM);
  }

  public StringField PROCEDIMENTTIPUS() {
    return new StringField(getQueryPath(), SolicitudFields.PROCEDIMENTTIPUS);
  }

  public LongField ESTATID() {
    return new LongField(getQueryPath(), SolicitudFields.ESTATID);
  }

  public StringField TICKETASSOCIAT() {
    return new StringField(getQueryPath(), SolicitudFields.TICKETASSOCIAT);
  }

  public StringField TICKETNUMEROSEGUIMENT() {
    return new StringField(getQueryPath(), SolicitudFields.TICKETNUMEROSEGUIMENT);
  }

  public LongField DEPARTAMENTID() {
    return new LongField(getQueryPath(), SolicitudFields.DEPARTAMENTID);
  }

  public StringField ENTITATESTATAL() {
    return new StringField(getQueryPath(), SolicitudFields.ENTITATESTATAL);
  }

  public StringField PINFO() {
    return new StringField(getQueryPath(), SolicitudFields.PINFO);
  }

  public TimestampField DATAINICI() {
    return new TimestampField(getQueryPath(), SolicitudFields.DATAINICI);
  }

  public TimestampField DATAFI() {
    return new TimestampField(getQueryPath(), SolicitudFields.DATAFI);
  }

  public StringField PERSONACONTACTE() {
    return new StringField(getQueryPath(), SolicitudFields.PERSONACONTACTE);
  }

  public StringField PERSONACONTACTEEMAIL() {
    return new StringField(getQueryPath(), SolicitudFields.PERSONACONTACTEEMAIL);
  }

  public StringField RESPONSABLEPROCNOM() {
    return new StringField(getQueryPath(), SolicitudFields.RESPONSABLEPROCNOM);
  }

  public StringField RESPONSABLEPROCEMAIL() {
    return new StringField(getQueryPath(), SolicitudFields.RESPONSABLEPROCEMAIL);
  }

  public StringField NOTES() {
    return new StringField(getQueryPath(), SolicitudFields.NOTES);
  }

  public LongField DOCUMENTSOLICITUDID() {
    return new LongField(getQueryPath(), SolicitudFields.DOCUMENTSOLICITUDID);
  }

  public LongField SOLICITUDXMLID() {
    return new LongField(getQueryPath(), SolicitudFields.SOLICITUDXMLID);
  }

  public BooleanField FIRMATDOCSOLICITUD() {
    return new BooleanField(getQueryPath(), SolicitudFields.FIRMATDOCSOLICITUD);
  }

  public BooleanField PRODUCCIO() {
    return new BooleanField(getQueryPath(), SolicitudFields.PRODUCCIO);
  }

  public StringField DENOMINACIO() {
    return new StringField(getQueryPath(), SolicitudFields.DENOMINACIO);
  }

  public StringField DIR3() {
    return new StringField(getQueryPath(), SolicitudFields.DIR3);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), SolicitudFields.NIF);
  }

  public StringField CREADOR() {
    return new StringField(getQueryPath(), SolicitudFields.CREADOR);
  }

  public StringField OPERADOR() {
    return new StringField(getQueryPath(), SolicitudFields.OPERADOR);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (SolicitudFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public DocumentSolicitudQueryPath DOCUMENTSOLICITUDS() {
    return new DocumentSolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "documentSolicituds" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public EventQueryPath EVENTS() {
    return new EventQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "events" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public SolicitudServeiQueryPath SOLICITUDSERVEIS() {
    return new SolicitudServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "solicitudServeis" + ".";
      }
    });
  }
*/

  public DepartamentQueryPath DEPARTAMENT() {
    return new DepartamentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "departament" + ".";
      }
    });
  }

  public FitxerQueryPath DOCUMENTSOLICITUD() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "documentSolicitud" + ".";
      }
    });
  }

  public FitxerQueryPath SOLICITUDXML() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "solicitudXml" + ".";
      }
    });
  }

}
