
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class CampSolicitudQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public CampSolicitudQueryPath() {
  }

  protected CampSolicitudQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField CAMPSOLICITUDID() {
    return new LongField(getQueryPath(), CampSolicitudFields.CAMPSOLICITUDID);
  }

  public LongField CAMPFORMULARIID() {
    return new LongField(getQueryPath(), CampSolicitudFields.CAMPFORMULARIID);
  }

  public LongField SOLICITUDSERVEIID() {
    return new LongField(getQueryPath(), CampSolicitudFields.SOLICITUDSERVEIID);
  }

  public StringField VALOR() {
    return new StringField(getQueryPath(), CampSolicitudFields.VALOR);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (CampSolicitudFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public CampFormulariQueryPath CAMPFORMULARI() {
    return new CampFormulariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CampSolicitudQueryPath.this.getQueryPath() + "campFormulari" + ".";
      }
    });
  }

  public SolicitudServeiQueryPath SOLICITUDSERVEI() {
    return new SolicitudServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return CampSolicitudQueryPath.this.getQueryPath() + "solicitudServei" + ".";
      }
    });
  }

}
