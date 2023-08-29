
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;


public class TramitHProcBean implements TramitHProc {



	long procid;// PK
	long tramitid;
	java.lang.String tipus;
	java.lang.String nom;
	java.lang.String codi;
	java.lang.String urlseu;
	boolean caducitat;
	java.sql.Timestamp caducitatdata;
	java.lang.String descripcio;
	long peticionsaldia;
	long peticionsalmes;
	boolean periodico;
	boolean automatizado;


  /** Constructor Buit */
  public TramitHProcBean() {
  }

  /** Constructor amb tots els camps  */
  public TramitHProcBean(long procid , long tramitid , java.lang.String tipus , java.lang.String nom , java.lang.String codi , java.lang.String urlseu , boolean caducitat , java.sql.Timestamp caducitatdata , java.lang.String descripcio , long peticionsaldia , long peticionsalmes , boolean periodico , boolean automatizado) {
    this.procid=procid;
    this.tramitid=tramitid;
    this.tipus=tipus;
    this.nom=nom;
    this.codi=codi;
    this.urlseu=urlseu;
    this.caducitat=caducitat;
    this.caducitatdata=caducitatdata;
    this.descripcio=descripcio;
    this.peticionsaldia=peticionsaldia;
    this.peticionsalmes=peticionsalmes;
    this.periodico=periodico;
    this.automatizado=automatizado;
}
  /** Constructor sense valors autoincrementals */
  public TramitHProcBean(long tramitid , java.lang.String tipus , java.lang.String nom , java.lang.String codi , java.lang.String urlseu , boolean caducitat , java.sql.Timestamp caducitatdata , java.lang.String descripcio , long peticionsaldia , long peticionsalmes , boolean periodico , boolean automatizado) {
    this.tramitid=tramitid;
    this.tipus=tipus;
    this.nom=nom;
    this.codi=codi;
    this.urlseu=urlseu;
    this.caducitat=caducitat;
    this.caducitatdata=caducitatdata;
    this.descripcio=descripcio;
    this.peticionsaldia=peticionsaldia;
    this.peticionsalmes=peticionsalmes;
    this.periodico=periodico;
    this.automatizado=automatizado;
}
  public TramitHProcBean(TramitHProc __bean) {
    this.setProcid(__bean.getProcid());
    this.setTramitid(__bean.getTramitid());
    this.setTipus(__bean.getTipus());
    this.setNom(__bean.getNom());
    this.setCodi(__bean.getCodi());
    this.setUrlseu(__bean.getUrlseu());
    this.setCaducitat(__bean.isCaducitat());
    this.setCaducitatdata(__bean.getCaducitatdata());
    this.setDescripcio(__bean.getDescripcio());
    this.setPeticionsaldia(__bean.getPeticionsaldia());
    this.setPeticionsalmes(__bean.getPeticionsalmes());
    this.setPeriodico(__bean.isPeriodico());
    this.setAutomatizado(__bean.isAutomatizado());
	}

	public long getProcid() {
		return(procid);
	};
	public void setProcid(long _procid_) {
		this.procid = _procid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public java.lang.String getTipus() {
		return(tipus);
	};
	public void setTipus(java.lang.String _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.lang.String getUrlseu() {
		return(urlseu);
	};
	public void setUrlseu(java.lang.String _urlseu_) {
		this.urlseu = _urlseu_;
	};

	public boolean isCaducitat() {
		return(caducitat);
	};
	public void setCaducitat(boolean _caducitat_) {
		this.caducitat = _caducitat_;
	};

	public java.sql.Timestamp getCaducitatdata() {
		return(caducitatdata);
	};
	public void setCaducitatdata(java.sql.Timestamp _caducitatdata_) {
		this.caducitatdata = _caducitatdata_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public long getPeticionsaldia() {
		return(peticionsaldia);
	};
	public void setPeticionsaldia(long _peticionsaldia_) {
		this.peticionsaldia = _peticionsaldia_;
	};

	public long getPeticionsalmes() {
		return(peticionsalmes);
	};
	public void setPeticionsalmes(long _peticionsalmes_) {
		this.peticionsalmes = _peticionsalmes_;
	};

	public boolean isPeriodico() {
		return(periodico);
	};
	public void setPeriodico(boolean _periodico_) {
		this.periodico = _periodico_;
	};

	public boolean isAutomatizado() {
		return(automatizado);
	};
	public void setAutomatizado(boolean _automatizado_) {
		this.automatizado = _automatizado_;
	};



  // ======================================

  public static TramitHProcBean toBean(TramitHProc __bean) {
    if (__bean == null) { return null;}
    TramitHProcBean __tmp = new TramitHProcBean();
    __tmp.setProcid(__bean.getProcid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setNom(__bean.getNom());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setUrlseu(__bean.getUrlseu());
    __tmp.setCaducitat(__bean.isCaducitat());
    __tmp.setCaducitatdata(__bean.getCaducitatdata());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setPeticionsaldia(__bean.getPeticionsaldia());
    __tmp.setPeticionsalmes(__bean.getPeticionsalmes());
    __tmp.setPeriodico(__bean.isPeriodico());
    __tmp.setAutomatizado(__bean.isAutomatizado());
		return __tmp;
	}



}
