
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.TramitBDadesSoli;


public class TramitBDadesSoliBean implements TramitBDadesSoli {



	long dadessoliid;// PK
	long tramitid;
	long tipussolicitud;
	java.lang.String entorn;


  /** Constructor Buit */
  public TramitBDadesSoliBean() {
  }

  /** Constructor amb tots els camps  */
  public TramitBDadesSoliBean(long dadessoliid , long tramitid , long tipussolicitud , java.lang.String entorn) {
    this.dadessoliid=dadessoliid;
    this.tramitid=tramitid;
    this.tipussolicitud=tipussolicitud;
    this.entorn=entorn;
}
  /** Constructor sense valors autoincrementals */
  public TramitBDadesSoliBean(long tramitid , long tipussolicitud , java.lang.String entorn) {
    this.tramitid=tramitid;
    this.tipussolicitud=tipussolicitud;
    this.entorn=entorn;
}
  public TramitBDadesSoliBean(TramitBDadesSoli __bean) {
    this.setDadessoliid(__bean.getDadessoliid());
    this.setTramitid(__bean.getTramitid());
    this.setTipussolicitud(__bean.getTipussolicitud());
    this.setEntorn(__bean.getEntorn());
	}

	public long getDadessoliid() {
		return(dadessoliid);
	};
	public void setDadessoliid(long _dadessoliid_) {
		this.dadessoliid = _dadessoliid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public long getTipussolicitud() {
		return(tipussolicitud);
	};
	public void setTipussolicitud(long _tipussolicitud_) {
		this.tipussolicitud = _tipussolicitud_;
	};

	public java.lang.String getEntorn() {
		return(entorn);
	};
	public void setEntorn(java.lang.String _entorn_) {
		this.entorn = _entorn_;
	};



  // ======================================

  public static TramitBDadesSoliBean toBean(TramitBDadesSoli __bean) {
    if (__bean == null) { return null;}
    TramitBDadesSoliBean __tmp = new TramitBDadesSoliBean();
    __tmp.setDadessoliid(__bean.getDadessoliid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setTipussolicitud(__bean.getTipussolicitud());
    __tmp.setEntorn(__bean.getEntorn());
		return __tmp;
	}



}
