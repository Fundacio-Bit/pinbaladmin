
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class DepartamentQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public DepartamentQueryPath() {
  }

  protected DepartamentQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField DEPARTAMENTID() {
    return new LongField(getQueryPath(), DepartamentFields.DEPARTAMENTID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), DepartamentFields.NOM);
  }

  public LongField AREAID() {
    return new LongField(getQueryPath(), DepartamentFields.AREAID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (DepartamentFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public AreaQueryPath AREA() {
    return new AreaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return DepartamentQueryPath.this.getQueryPath() + "area" + ".";
      }
    });
  }

}
