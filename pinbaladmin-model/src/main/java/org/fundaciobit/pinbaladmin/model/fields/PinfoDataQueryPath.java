
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PinfoDataQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PinfoDataQueryPath() {
  }

  protected PinfoDataQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PINFODATAID() {
    return new LongField(getQueryPath(), PinfoDataFields.PINFODATAID);
  }

  public LongField PINFOID() {
    return new LongField(getQueryPath(), PinfoDataFields.PINFOID);
  }

  public LongField ESTAT() {
    return new LongField(getQueryPath(), PinfoDataFields.ESTAT);
  }

  public StringField USUARIID() {
    return new StringField(getQueryPath(), PinfoDataFields.USUARIID);
  }

  public LongField PROCEDIMENTID() {
    return new LongField(getQueryPath(), PinfoDataFields.PROCEDIMENTID);
  }

  public LongField SERVEIID() {
    return new LongField(getQueryPath(), PinfoDataFields.SERVEIID);
  }

  public LongField ALTA() {
    return new LongField(getQueryPath(), PinfoDataFields.ALTA);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PinfoDataFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public PINFOQueryPath PINFO() {
    return new PINFOQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PinfoDataQueryPath.this.getQueryPath() + "pINFO" + ".";
      }
    });
  }

  public SolicitudQueryPath SOLICITUD() {
    return new SolicitudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PinfoDataQueryPath.this.getQueryPath() + "solicitud" + ".";
      }
    });
  }

  public ServeiQueryPath SERVEI() {
    return new ServeiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PinfoDataQueryPath.this.getQueryPath() + "servei" + ".";
      }
    });
  }

}
