
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TramitCDadesCesiQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TramitCDadesCesiQueryPath() {
  }

  protected TramitCDadesCesiQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField DADESCESIID() {
    return new LongField(getQueryPath(), TramitCDadesCesiFields.DADESCESIID);
  }

  public LongField TRAMITID() {
    return new LongField(getQueryPath(), TramitCDadesCesiFields.TRAMITID);
  }

  public LongField ORGANID() {
    return new LongField(getQueryPath(), TramitCDadesCesiFields.ORGANID);
  }

  public LongField ORGANARRELID() {
    return new LongField(getQueryPath(), TramitCDadesCesiFields.ORGANARRELID);
  }

  public StringField DENOMINACIO() {
    return new StringField(getQueryPath(), TramitCDadesCesiFields.DENOMINACIO);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), TramitCDadesCesiFields.NIF);
  }

  public StringField RESPONSABLE() {
    return new StringField(getQueryPath(), TramitCDadesCesiFields.RESPONSABLE);
  }

  public StringField DIR3RESPONSABLE() {
    return new StringField(getQueryPath(), TramitCDadesCesiFields.DIR3RESPONSABLE);
  }

  public StringField DIR3ARREL() {
    return new StringField(getQueryPath(), TramitCDadesCesiFields.DIR3ARREL);
  }

  public StringField DIRECCIO() {
    return new StringField(getQueryPath(), TramitCDadesCesiFields.DIRECCIO);
  }

  public StringField CODIPOSTAL() {
    return new StringField(getQueryPath(), TramitCDadesCesiFields.CODIPOSTAL);
  }

  public StringField MUNICIPI() {
    return new StringField(getQueryPath(), TramitCDadesCesiFields.MUNICIPI);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TramitCDadesCesiFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TramitAPersAutQueryPath TRAMITAPERSAUT() {
    return new TramitAPersAutQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitCDadesCesiQueryPath.this.getQueryPath() + "tramitAPersAut" + ".";
      }
    });
  }

  public OrganQueryPath ORGAN() {
    return new OrganQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitCDadesCesiQueryPath.this.getQueryPath() + "organ" + ".";
      }
    });
  }

  public OrganQueryPath ORGANARREL() {
    return new OrganQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitCDadesCesiQueryPath.this.getQueryPath() + "organArrel" + ".";
      }
    });
  }

}
