
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EntitatPublicaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EntitatPublicaQueryPath() {
  }

  protected EntitatPublicaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public StringField ENTITATPUBLICAID() {
    return new StringField(getQueryPath(), EntitatPublicaFields.ENTITATPUBLICAID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EntitatPublicaFields.NOM);
  }

  public StringField SOLICITUD() {
    return new StringField(getQueryPath(), EntitatPublicaFields.SOLICITUD);
  }

  public StringField PERSONACONTACTE() {
    return new StringField(getQueryPath(), EntitatPublicaFields.PERSONACONTACTE);
  }

  public StringField ESTAT() {
    return new StringField(getQueryPath(), EntitatPublicaFields.ESTAT);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EntitatPublicaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
