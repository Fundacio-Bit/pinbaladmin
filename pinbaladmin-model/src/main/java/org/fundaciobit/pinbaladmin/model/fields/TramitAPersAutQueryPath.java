
package org.fundaciobit.pinbaladmin.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TramitAPersAutQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TramitAPersAutQueryPath() {
  }

  protected TramitAPersAutQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField PERSAUTID() {
    return new LongField(getQueryPath(), TramitAPersAutFields.PERSAUTID);
  }

  public LongField TRAMITID() {
    return new LongField(getQueryPath(), TramitAPersAutFields.TRAMITID);
  }

  public TimestampField DATATRAMIT() {
    return new TimestampField(getQueryPath(), TramitAPersAutFields.DATATRAMIT);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), TramitAPersAutFields.NIF);
  }

  public StringField MAIL() {
    return new StringField(getQueryPath(), TramitAPersAutFields.MAIL);
  }

  public StringField TELEFON() {
    return new StringField(getQueryPath(), TramitAPersAutFields.TELEFON);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), TramitAPersAutFields.NOM);
  }

  public StringField LLINATGE1() {
    return new StringField(getQueryPath(), TramitAPersAutFields.LLINATGE1);
  }

  public StringField LLINATGE2() {
    return new StringField(getQueryPath(), TramitAPersAutFields.LLINATGE2);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TramitAPersAutFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TramitBDadesSoliQueryPath TRAMITBDADESSOLIS() {
    return new TramitBDadesSoliQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitAPersAutQueryPath.this.getQueryPath() + "tramitBDadesSolis" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TramitCDadesCesiQueryPath TRAMITCDADESCESIS() {
    return new TramitCDadesCesiQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitAPersAutQueryPath.this.getQueryPath() + "tramitCDadesCesis" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TramitDCteAutQueryPath TRAMITDCTEAUTS() {
    return new TramitDCteAutQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitAPersAutQueryPath.this.getQueryPath() + "tramitDCteAuts" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TramitECteAudQueryPath TRAMITECTEAUDS() {
    return new TramitECteAudQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitAPersAutQueryPath.this.getQueryPath() + "tramitECteAuds" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TramitFCteTecQueryPath TRAMITFCTETECS() {
    return new TramitFCteTecQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitAPersAutQueryPath.this.getQueryPath() + "tramitFCteTecs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TramitGDadesTitQueryPath TRAMITGDADESTITS() {
    return new TramitGDadesTitQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitAPersAutQueryPath.this.getQueryPath() + "tramitGDadesTits" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TramitHProcQueryPath TRAMITHPROCS() {
    return new TramitHProcQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitAPersAutQueryPath.this.getQueryPath() + "tramitHProcs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TramitIServQueryPath TRAMITISERVS() {
    return new TramitIServQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitAPersAutQueryPath.this.getQueryPath() + "tramitIServs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public TramitJConsentQueryPath TRAMITJCONSENTS() {
    return new TramitJConsentQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TramitAPersAutQueryPath.this.getQueryPath() + "tramitJConsents" + ".";
      }
    });
  }
*/

}
