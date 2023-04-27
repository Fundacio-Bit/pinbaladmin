
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EstatSolicitudQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EstatSolicitudQueryPath() {
  }

  protected EstatSolicitudQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ESTATSOLICITUDID() {
    return new LongField(getQueryPath(), EstatSolicitudFields.ESTATSOLICITUDID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EstatSolicitudFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), EstatSolicitudFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EstatSolicitudFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
