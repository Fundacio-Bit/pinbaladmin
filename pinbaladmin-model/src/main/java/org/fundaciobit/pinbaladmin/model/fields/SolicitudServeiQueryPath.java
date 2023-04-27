
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class SolicitudServeiQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public SolicitudServeiQueryPath() {
  }

  protected SolicitudServeiQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ID() {
    return new LongField(getQueryPath(), SolicitudServeiFields.ID);
  }

  public LongField SOLICITUDID() {
    return new LongField(getQueryPath(), SolicitudServeiFields.SOLICITUDID);
  }

  public LongField SERVEIID() {
    return new LongField(getQueryPath(), SolicitudServeiFields.SERVEIID);
  }

  public LongField ESTATSOLICITUDSERVEIID() {
    return new LongField(getQueryPath(), SolicitudServeiFields.ESTATSOLICITUDSERVEIID);
  }

  public StringField NORMALEGAL() {
    return new StringField(getQueryPath(), SolicitudServeiFields.NORMALEGAL);
  }

  public StringField ENLLAZNORMALEGAL() {
    return new StringField(getQueryPath(), SolicitudServeiFields.ENLLAZNORMALEGAL);
  }

  public StringField ARTICLES() {
    return new StringField(getQueryPath(), SolicitudServeiFields.ARTICLES);
  }

  public StringField TIPUSCONSENTIMENT() {
    return new StringField(getQueryPath(), SolicitudServeiFields.TIPUSCONSENTIMENT);
  }

  public StringField CONSENTIMENT() {
    return new StringField(getQueryPath(), SolicitudServeiFields.CONSENTIMENT);
  }

  public StringField ENLLAZCONSENTIMENT() {
    return new StringField(getQueryPath(), SolicitudServeiFields.ENLLAZCONSENTIMENT);
  }

  public StringField NOTES() {
    return new StringField(getQueryPath(), SolicitudServeiFields.NOTES);
  }

  public StringField CADUCA() {
    return new StringField(getQueryPath(), SolicitudServeiFields.CADUCA);
  }

  public StringField FECHACADUCA() {
    return new StringField(getQueryPath(), SolicitudServeiFields.FECHACADUCA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (SolicitudServeiFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public CampSolicitudQueryPath CAMPSOLICITUDS() {
    return new CampSolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudServeiQueryPath.this.getQueryPath() + "campSolicituds" + ".";
      }
    });
  }
*/

  public SolicitudQueryPath SOLICITUD() {
    return new SolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudServeiQueryPath.this.getQueryPath() + "solicitud" + ".";
      }
    });
  }

  public ServeiQueryPath SERVEI() {
    return new ServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return SolicitudServeiQueryPath.this.getQueryPath() + "servei" + ".";
      }
    });
  }

}
