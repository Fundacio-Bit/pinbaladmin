
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

  public StringField CODISIACONV() {
    return new StringField(getQueryPath(), SolicitudFields.CODISIACONV);
  }

  public StringField PROCEDIMENTNOM() {
    return new StringField(getQueryPath(), SolicitudFields.PROCEDIMENTNOM);
  }

  public StringField PROCEDIMENTTIPUS() {
    return new StringField(getQueryPath(), SolicitudFields.PROCEDIMENTTIPUS);
  }

  public LongField ORGANID() {
    return new LongField(getQueryPath(), SolicitudFields.ORGANID);
  }

  public LongField ESTATSOLICITUD() {
    return new LongField(getQueryPath(), SolicitudFields.ESTATSOLICITUD);
  }

  public StringField EXPEDIENTPID() {
    return new StringField(getQueryPath(), SolicitudFields.EXPEDIENTPID);
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

  public LongField ESTATPINBAL() {
    return new LongField(getQueryPath(), SolicitudFields.ESTATPINBAL);
  }

  public StringField CONSENTIMENT() {
    return new StringField(getQueryPath(), SolicitudFields.CONSENTIMENT);
  }

  public StringField URLCONSENTIMENT() {
    return new StringField(getQueryPath(), SolicitudFields.URLCONSENTIMENT);
  }

  public StringField CONSENTIMENTADJUNT() {
    return new StringField(getQueryPath(), SolicitudFields.CONSENTIMENTADJUNT);
  }

  public LongField PORTAFIBID() {
    return new LongField(getQueryPath(), SolicitudFields.PORTAFIBID);
  }

  public LongField INFOMADRIDID() {
    return new LongField(getQueryPath(), SolicitudFields.INFOMADRIDID);
  }

  public TimestampField DATACADUCITAT() {
    return new TimestampField(getQueryPath(), SolicitudFields.DATACADUCITAT);
  }

  public LongField FITXERCONSENTIMENTID() {
    return new LongField(getQueryPath(), SolicitudFields.FITXERCONSENTIMENTID);
  }

  public LongField CONTACTETITULARID() {
    return new LongField(getQueryPath(), SolicitudFields.CONTACTETITULARID);
  }

  public LongField SOLICITUDFUSIONADAID() {
    return new LongField(getQueryPath(), SolicitudFields.SOLICITUDFUSIONADAID);
  }

  public LongField CONTACTEPERSONAID() {
    return new LongField(getQueryPath(), SolicitudFields.CONTACTEPERSONAID);
  }

  public LongField CONTACTERESPONSABLEID() {
    return new LongField(getQueryPath(), SolicitudFields.CONTACTERESPONSABLEID);
  }

  public LongField CONTACTESOLICITANTID() {
    return new LongField(getQueryPath(), SolicitudFields.CONTACTESOLICITANTID);
  }

  public LongField CONTACTEGESTAUTID() {
    return new LongField(getQueryPath(), SolicitudFields.CONTACTEGESTAUTID);
  }

  public LongField CONTACTEAUDITORIAID() {
    return new LongField(getQueryPath(), SolicitudFields.CONTACTEAUDITORIAID);
  }

  public LongField CONTACTETECNICID() {
    return new LongField(getQueryPath(), SolicitudFields.CONTACTETECNICID);
  }

  public StringField TITULARFIRMANIFOLD() {
    return new StringField(getQueryPath(), SolicitudFields.TITULARFIRMANIFOLD);
  }

  public StringField PERSONACONTACTEOLD() {
    return new StringField(getQueryPath(), SolicitudFields.PERSONACONTACTEOLD);
  }

  public StringField PERSONACONTACTEEMAILOLD() {
    return new StringField(getQueryPath(), SolicitudFields.PERSONACONTACTEEMAILOLD);
  }

  public StringField RESPONSABLEPROCNOMOLD() {
    return new StringField(getQueryPath(), SolicitudFields.RESPONSABLEPROCNOMOLD);
  }

  public StringField RESPONSABLEPROCEMAILOLD() {
    return new StringField(getQueryPath(), SolicitudFields.RESPONSABLEPROCEMAILOLD);
  }

  public StringField TITULARFIRMANOMOLD() {
    return new StringField(getQueryPath(), SolicitudFields.TITULARFIRMANOMOLD);
  }

  public StringField TITULARFIRMAEMAILOLD() {
    return new StringField(getQueryPath(), SolicitudFields.TITULARFIRMAEMAILOLD);
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

  public ModificacioSolicitudQueryPath MODIFICACIOSOLICITUDS() {
    return new ModificacioSolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "modificacioSolicituds" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PinfoDataQueryPath PINFODATAS() {
    return new PinfoDataQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "pinfoDatas" + ".";
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

  public OrganQueryPath ORGAN() {
    return new OrganQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "organ" + ".";
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

  public InfoMadridQueryPath INFOMADRID() {
    return new InfoMadridQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "infoMadrid" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERCONSENTIMENT() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "fitxerConsentiment" + ".";
      }
    });
  }

  public ContacteQueryPath CONTACTETITULAR() {
    return new ContacteQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "contacteTitular" + ".";
      }
    });
  }

  public ContacteQueryPath CONTACTEPERSONA() {
    return new ContacteQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "contactePersona" + ".";
      }
    });
  }

  public ContacteQueryPath CONTACTERESPONSABLE() {
    return new ContacteQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "contacteResponsable" + ".";
      }
    });
  }

  public ContacteQueryPath CONTACTESOLICITANT() {
    return new ContacteQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "contacteSolicitant" + ".";
      }
    });
  }

  public ContacteQueryPath CONTACTEGESTAUT() {
    return new ContacteQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "contacteGestAut" + ".";
      }
    });
  }

  public ContacteQueryPath CONTACTEAUDITORIA() {
    return new ContacteQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "contacteAuditoria" + ".";
      }
    });
  }

  public ContacteQueryPath CONTACTETECNIC() {
    return new ContacteQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudQueryPath.this.getQueryPath() + "contacteTecnic" + ".";
      }
    });
  }

}
