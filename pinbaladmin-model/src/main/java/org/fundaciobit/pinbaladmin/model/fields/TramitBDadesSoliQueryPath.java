
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TramitBDadesSoliQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TramitBDadesSoliQueryPath() {
  }

  protected TramitBDadesSoliQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField DADESSOLIID() {
    return new LongField(getQueryPath(), TramitBDadesSoliFields.DADESSOLIID);
  }

  public LongField TRAMITID() {
    return new LongField(getQueryPath(), TramitBDadesSoliFields.TRAMITID);
  }

  public LongField TIPUSSOLICITUD() {
    return new LongField(getQueryPath(), TramitBDadesSoliFields.TIPUSSOLICITUD);
  }

  public StringField ENTORN() {
    return new StringField(getQueryPath(), TramitBDadesSoliFields.ENTORN);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TramitBDadesSoliFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TramitAPersAutQueryPath TRAMITAPERSAUT() {
    return new TramitAPersAutQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitBDadesSoliQueryPath.this.getQueryPath() + "tramitAPersAut" + ".";
      }
    });
  }

}
