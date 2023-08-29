
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TramitGDadesTitQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TramitGDadesTitQueryPath() {
  }

  protected TramitGDadesTitQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField DADESTITID() {
    return new LongField(getQueryPath(), TramitGDadesTitFields.DADESTITID);
  }

  public LongField TRAMITID() {
    return new LongField(getQueryPath(), TramitGDadesTitFields.TRAMITID);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), TramitGDadesTitFields.NIF);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TramitGDadesTitFields.NOM);
  }

  public StringField LLINATGE1() {
    return new StringField(getQueryPath(), TramitGDadesTitFields.LLINATGE1);
  }

  public StringField LLINATGE2() {
    return new StringField(getQueryPath(), TramitGDadesTitFields.LLINATGE2);
  }

  public StringField CARREC() {
    return new StringField(getQueryPath(), TramitGDadesTitFields.CARREC);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TramitGDadesTitFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TramitAPersAutQueryPath TRAMITAPERSAUT() {
    return new TramitAPersAutQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitGDadesTitQueryPath.this.getQueryPath() + "tramitAPersAut" + ".";
      }
    });
  }

}
