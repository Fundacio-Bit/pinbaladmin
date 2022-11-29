
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class OperadorQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public OperadorQueryPath() {
  }

  protected OperadorQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField OPERADORID() {
    return new LongField(getQueryPath(), OperadorFields.OPERADORID);
  }

  public StringField USERNAME() {
    return new StringField(getQueryPath(), OperadorFields.USERNAME);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), OperadorFields.NOM);
  }

  public StringField EMAIL() {
    return new StringField(getQueryPath(), OperadorFields.EMAIL);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (OperadorFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
