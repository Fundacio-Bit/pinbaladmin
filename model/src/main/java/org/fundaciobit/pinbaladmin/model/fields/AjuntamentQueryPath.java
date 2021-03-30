
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AjuntamentQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AjuntamentQueryPath() {
  }

  protected AjuntamentQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public StringField AJUNTAMENTID() {
    return new StringField(getQueryPath(), AjuntamentFields.AJUNTAMENTID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), AjuntamentFields.NOM);
  }

  public StringField SOLICITUD() {
    return new StringField(getQueryPath(), AjuntamentFields.SOLICITUD);
  }

  public StringField PERSONACONTACTE() {
    return new StringField(getQueryPath(), AjuntamentFields.PERSONACONTACTE);
  }

  public StringField ESTAT() {
    return new StringField(getQueryPath(), AjuntamentFields.ESTAT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AjuntamentFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
