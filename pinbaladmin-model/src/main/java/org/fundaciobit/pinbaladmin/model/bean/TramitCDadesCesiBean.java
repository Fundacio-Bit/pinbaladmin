
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;


public class TramitCDadesCesiBean implements TramitCDadesCesi {



	long dadescesiid;// PK
	long tramitid;
	java.lang.String denominacio;
	java.lang.String nif;
	java.lang.String responsable;
	java.lang.String dir3responsable;
	java.lang.String dir3arrel;
	java.lang.String direccio;
	java.lang.String codipostal;
	java.lang.String municipi;


  /** Constructor Buit */
  public TramitCDadesCesiBean() {
  }

  /** Constructor amb tots els camps  */
  public TramitCDadesCesiBean(long dadescesiid , long tramitid , java.lang.String denominacio , java.lang.String nif , java.lang.String responsable , java.lang.String dir3responsable , java.lang.String dir3arrel , java.lang.String direccio , java.lang.String codipostal , java.lang.String municipi) {
    this.dadescesiid=dadescesiid;
    this.tramitid=tramitid;
    this.denominacio=denominacio;
    this.nif=nif;
    this.responsable=responsable;
    this.dir3responsable=dir3responsable;
    this.dir3arrel=dir3arrel;
    this.direccio=direccio;
    this.codipostal=codipostal;
    this.municipi=municipi;
}
  /** Constructor sense valors autoincrementals */
  public TramitCDadesCesiBean(long tramitid , java.lang.String denominacio , java.lang.String nif , java.lang.String responsable , java.lang.String dir3responsable , java.lang.String dir3arrel , java.lang.String direccio , java.lang.String codipostal , java.lang.String municipi) {
    this.tramitid=tramitid;
    this.denominacio=denominacio;
    this.nif=nif;
    this.responsable=responsable;
    this.dir3responsable=dir3responsable;
    this.dir3arrel=dir3arrel;
    this.direccio=direccio;
    this.codipostal=codipostal;
    this.municipi=municipi;
}
  public TramitCDadesCesiBean(TramitCDadesCesi __bean) {
    this.setDadescesiid(__bean.getDadescesiid());
    this.setTramitid(__bean.getTramitid());
    this.setDenominacio(__bean.getDenominacio());
    this.setNif(__bean.getNif());
    this.setResponsable(__bean.getResponsable());
    this.setDir3responsable(__bean.getDir3responsable());
    this.setDir3arrel(__bean.getDir3arrel());
    this.setDireccio(__bean.getDireccio());
    this.setCodipostal(__bean.getCodipostal());
    this.setMunicipi(__bean.getMunicipi());
	}

	public long getDadescesiid() {
		return(dadescesiid);
	};
	public void setDadescesiid(long _dadescesiid_) {
		this.dadescesiid = _dadescesiid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public java.lang.String getDenominacio() {
		return(denominacio);
	};
	public void setDenominacio(java.lang.String _denominacio_) {
		this.denominacio = _denominacio_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getResponsable() {
		return(responsable);
	};
	public void setResponsable(java.lang.String _responsable_) {
		this.responsable = _responsable_;
	};

	public java.lang.String getDir3responsable() {
		return(dir3responsable);
	};
	public void setDir3responsable(java.lang.String _dir3responsable_) {
		this.dir3responsable = _dir3responsable_;
	};

	public java.lang.String getDir3arrel() {
		return(dir3arrel);
	};
	public void setDir3arrel(java.lang.String _dir3arrel_) {
		this.dir3arrel = _dir3arrel_;
	};

	public java.lang.String getDireccio() {
		return(direccio);
	};
	public void setDireccio(java.lang.String _direccio_) {
		this.direccio = _direccio_;
	};

	public java.lang.String getCodipostal() {
		return(codipostal);
	};
	public void setCodipostal(java.lang.String _codipostal_) {
		this.codipostal = _codipostal_;
	};

	public java.lang.String getMunicipi() {
		return(municipi);
	};
	public void setMunicipi(java.lang.String _municipi_) {
		this.municipi = _municipi_;
	};



  // ======================================

  public static TramitCDadesCesiBean toBean(TramitCDadesCesi __bean) {
    if (__bean == null) { return null;}
    TramitCDadesCesiBean __tmp = new TramitCDadesCesiBean();
    __tmp.setDadescesiid(__bean.getDadescesiid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setDenominacio(__bean.getDenominacio());
    __tmp.setNif(__bean.getNif());
    __tmp.setResponsable(__bean.getResponsable());
    __tmp.setDir3responsable(__bean.getDir3responsable());
    __tmp.setDir3arrel(__bean.getDir3arrel());
    __tmp.setDireccio(__bean.getDireccio());
    __tmp.setCodipostal(__bean.getCodipostal());
    __tmp.setMunicipi(__bean.getMunicipi());
		return __tmp;
	}



}
