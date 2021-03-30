
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EmailQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EmailQueryPath() {
  }

  protected EmailQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField EMAILID() {
    return new LongField(getQueryPath(), EmailFields.EMAILID);
  }

  public StringField DESTINATARIS() {
    return new StringField(getQueryPath(), EmailFields.DESTINATARIS);
  }

  public StringField SUBJECT() {
    return new StringField(getQueryPath(), EmailFields.SUBJECT);
  }

  public StringField MESSAGE() {
    return new StringField(getQueryPath(), EmailFields.MESSAGE);
  }

  public TimestampField DATAENVIAMENT() {
    return new TimestampField(getQueryPath(), EmailFields.DATAENVIAMENT);
  }

  public StringField ENVIADOR() {
    return new StringField(getQueryPath(), EmailFields.ENVIADOR);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EmailFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
