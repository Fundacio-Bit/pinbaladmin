
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TramitHProcQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TramitHProcQueryPath() {
  }

  protected TramitHProcQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PROCID() {
    return new LongField(getQueryPath(), TramitHProcFields.PROCID);
  }

  public LongField TRAMITID() {
    return new LongField(getQueryPath(), TramitHProcFields.TRAMITID);
  }

  public StringField TIPUS() {
    return new StringField(getQueryPath(), TramitHProcFields.TIPUS);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TramitHProcFields.NOM);
  }

  public StringField CODI() {
    return new StringField(getQueryPath(), TramitHProcFields.CODI);
  }

  public StringField URLSEU() {
    return new StringField(getQueryPath(), TramitHProcFields.URLSEU);
  }

  public BooleanField CADUCITAT() {
    return new BooleanField(getQueryPath(), TramitHProcFields.CADUCITAT);
  }

  public TimestampField CADUCITATDATA() {
    return new TimestampField(getQueryPath(), TramitHProcFields.CADUCITATDATA);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), TramitHProcFields.DESCRIPCIO);
  }

  public LongField PETICIONSALDIA() {
    return new LongField(getQueryPath(), TramitHProcFields.PETICIONSALDIA);
  }

  public LongField PETICIONSALMES() {
    return new LongField(getQueryPath(), TramitHProcFields.PETICIONSALMES);
  }

  public BooleanField PERIODICO() {
    return new BooleanField(getQueryPath(), TramitHProcFields.PERIODICO);
  }

  public BooleanField AUTOMATIZADO() {
    return new BooleanField(getQueryPath(), TramitHProcFields.AUTOMATIZADO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TramitHProcFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TramitAPersAutQueryPath TRAMITAPERSAUT() {
    return new TramitAPersAutQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitHProcQueryPath.this.getQueryPath() + "tramitAPersAut" + ".";
      }
    });
  }

}
