
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class ModificacioSolicitudQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public ModificacioSolicitudQueryPath() {
  }

  protected ModificacioSolicitudQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField MODSOLIID() {
    return new LongField(getQueryPath(), ModificacioSolicitudFields.MODSOLIID);
  }

  public LongField SOLICITUDID() {
    return new LongField(getQueryPath(), ModificacioSolicitudFields.SOLICITUDID);
  }

  public StringField PROCEDIMENTCODI() {
    return new StringField(getQueryPath(), ModificacioSolicitudFields.PROCEDIMENTCODI);
  }

  public StringField PROCEDIMENTNOM() {
    return new StringField(getQueryPath(), ModificacioSolicitudFields.PROCEDIMENTNOM);
  }

  public StringField CODISIANOU() {
    return new StringField(getQueryPath(), ModificacioSolicitudFields.CODISIANOU);
  }

  public LongField ESTATID() {
    return new LongField(getQueryPath(), ModificacioSolicitudFields.ESTATID);
  }

  public TimestampField DATAINICI() {
    return new TimestampField(getQueryPath(), ModificacioSolicitudFields.DATAINICI);
  }

  public TimestampField DATAFI() {
    return new TimestampField(getQueryPath(), ModificacioSolicitudFields.DATAFI);
  }

  public StringField NOTES() {
    return new StringField(getQueryPath(), ModificacioSolicitudFields.NOTES);
  }

  public LongField ORGANID() {
    return new LongField(getQueryPath(), ModificacioSolicitudFields.ORGANID);
  }

  public StringField RESPONSABLEPROCNOM() {
    return new StringField(getQueryPath(), ModificacioSolicitudFields.RESPONSABLEPROCNOM);
  }

  public StringField RESPONSABLEPROCEMAIL() {
    return new StringField(getQueryPath(), ModificacioSolicitudFields.RESPONSABLEPROCEMAIL);
  }

  public StringField CONSENTIMENT() {
    return new StringField(getQueryPath(), ModificacioSolicitudFields.CONSENTIMENT);
  }

  public LongField DOCCONSENTIMENTID() {
    return new LongField(getQueryPath(), ModificacioSolicitudFields.DOCCONSENTIMENTID);
  }

  public StringField SOLICITANTNOM() {
    return new StringField(getQueryPath(), ModificacioSolicitudFields.SOLICITANTNOM);
  }

  public StringField SOLICITANTNIF() {
    return new StringField(getQueryPath(), ModificacioSolicitudFields.SOLICITANTNIF);
  }

  public StringField SOLICITANTMAIL() {
    return new StringField(getQueryPath(), ModificacioSolicitudFields.SOLICITANTMAIL);
  }

  public StringField SOLICITANTUSERNAME() {
    return new StringField(getQueryPath(), ModificacioSolicitudFields.SOLICITANTUSERNAME);
  }

  public LongField ESTATMODIFICACIO() {
    return new LongField(getQueryPath(), ModificacioSolicitudFields.ESTATMODIFICACIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (ModificacioSolicitudFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public ModificacioSoliServQueryPath MODIFICACIOSOLISERVS() {
    return new ModificacioSoliServQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModificacioSolicitudQueryPath.this.getQueryPath() + "modificacioSoliServs" + ".";
      }
    });
  }
*/

  public SolicitudQueryPath SOLICITUD() {
    return new SolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModificacioSolicitudQueryPath.this.getQueryPath() + "solicitud" + ".";
      }
    });
  }

  public OrganQueryPath ORGAN() {
    return new OrganQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModificacioSolicitudQueryPath.this.getQueryPath() + "organ" + ".";
      }
    });
  }

  public FitxerQueryPath DOCCONSENTIMENT() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return ModificacioSolicitudQueryPath.this.getQueryPath() + "doCconsentiment" + ".";
      }
    });
  }

}
