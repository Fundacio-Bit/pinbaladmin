
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;


public class SolicitudServeiBean implements SolicitudServei {



	long id;// PK
	long solicitudID;
	long serveiID;
	java.lang.Long estatSolicitudServeiID;
	java.lang.String normaLegal;
	java.lang.String enllazNormaLegal;
	java.lang.String articles;
	java.lang.String tipusConsentiment;
	java.lang.String consentiment;
	java.lang.String enllazConsentiment;
	java.lang.String notes;
	java.lang.String caduca;
	java.lang.String fechaCaduca;


  /** Constructor Buit */
  public SolicitudServeiBean() {
  }

  /** Constructor amb tots els camps  */
  public SolicitudServeiBean(long id , long solicitudID , long serveiID , java.lang.Long estatSolicitudServeiID , java.lang.String normaLegal , java.lang.String enllazNormaLegal , java.lang.String articles , java.lang.String tipusConsentiment , java.lang.String consentiment , java.lang.String enllazConsentiment , java.lang.String notes , java.lang.String caduca , java.lang.String fechaCaduca) {
    this.id=id;
    this.solicitudID=solicitudID;
    this.serveiID=serveiID;
    this.estatSolicitudServeiID=estatSolicitudServeiID;
    this.normaLegal=normaLegal;
    this.enllazNormaLegal=enllazNormaLegal;
    this.articles=articles;
    this.tipusConsentiment=tipusConsentiment;
    this.consentiment=consentiment;
    this.enllazConsentiment=enllazConsentiment;
    this.notes=notes;
    this.caduca=caduca;
    this.fechaCaduca=fechaCaduca;
}
  /** Constructor sense valors autoincrementals */
  public SolicitudServeiBean(long solicitudID , long serveiID , java.lang.Long estatSolicitudServeiID , java.lang.String normaLegal , java.lang.String enllazNormaLegal , java.lang.String articles , java.lang.String tipusConsentiment , java.lang.String consentiment , java.lang.String enllazConsentiment , java.lang.String notes , java.lang.String caduca , java.lang.String fechaCaduca) {
    this.solicitudID=solicitudID;
    this.serveiID=serveiID;
    this.estatSolicitudServeiID=estatSolicitudServeiID;
    this.normaLegal=normaLegal;
    this.enllazNormaLegal=enllazNormaLegal;
    this.articles=articles;
    this.tipusConsentiment=tipusConsentiment;
    this.consentiment=consentiment;
    this.enllazConsentiment=enllazConsentiment;
    this.notes=notes;
    this.caduca=caduca;
    this.fechaCaduca=fechaCaduca;
}
  /** Constructor dels valors Not Null */
  public SolicitudServeiBean(long id , long solicitudID , long serveiID , java.lang.Long estatSolicitudServeiID , java.lang.String consentiment) {
    this.id=id;
    this.solicitudID=solicitudID;
    this.serveiID=serveiID;
    this.estatSolicitudServeiID=estatSolicitudServeiID;
    this.consentiment=consentiment;
}
  public SolicitudServeiBean(SolicitudServei __bean) {
    this.setId(__bean.getId());
    this.setSolicitudID(__bean.getSolicitudID());
    this.setServeiID(__bean.getServeiID());
    this.setEstatSolicitudServeiID(__bean.getEstatSolicitudServeiID());
    this.setNormaLegal(__bean.getNormaLegal());
    this.setEnllazNormaLegal(__bean.getEnllazNormaLegal());
    this.setArticles(__bean.getArticles());
    this.setTipusConsentiment(__bean.getTipusConsentiment());
    this.setConsentiment(__bean.getConsentiment());
    this.setEnllazConsentiment(__bean.getEnllazConsentiment());
    this.setNotes(__bean.getNotes());
    this.setCaduca(__bean.getCaduca());
    this.setFechaCaduca(__bean.getFechaCaduca());
	}

	public long getId() {
		return(id);
	};
	public void setId(long _id_) {
		this.id = _id_;
	};

	public long getSolicitudID() {
		return(solicitudID);
	};
	public void setSolicitudID(long _solicitudID_) {
		this.solicitudID = _solicitudID_;
	};

	public long getServeiID() {
		return(serveiID);
	};
	public void setServeiID(long _serveiID_) {
		this.serveiID = _serveiID_;
	};

	public java.lang.Long getEstatSolicitudServeiID() {
		return(estatSolicitudServeiID);
	};
	public void setEstatSolicitudServeiID(java.lang.Long _estatSolicitudServeiID_) {
		this.estatSolicitudServeiID = _estatSolicitudServeiID_;
	};

	public java.lang.String getNormaLegal() {
		return(normaLegal);
	};
	public void setNormaLegal(java.lang.String _normaLegal_) {
		this.normaLegal = _normaLegal_;
	};

	public java.lang.String getEnllazNormaLegal() {
		return(enllazNormaLegal);
	};
	public void setEnllazNormaLegal(java.lang.String _enllazNormaLegal_) {
		this.enllazNormaLegal = _enllazNormaLegal_;
	};

	public java.lang.String getArticles() {
		return(articles);
	};
	public void setArticles(java.lang.String _articles_) {
		this.articles = _articles_;
	};

	public java.lang.String getTipusConsentiment() {
		return(tipusConsentiment);
	};
	public void setTipusConsentiment(java.lang.String _tipusConsentiment_) {
		this.tipusConsentiment = _tipusConsentiment_;
	};

	public java.lang.String getConsentiment() {
		return(consentiment);
	};
	public void setConsentiment(java.lang.String _consentiment_) {
		this.consentiment = _consentiment_;
	};

	public java.lang.String getEnllazConsentiment() {
		return(enllazConsentiment);
	};
	public void setEnllazConsentiment(java.lang.String _enllazConsentiment_) {
		this.enllazConsentiment = _enllazConsentiment_;
	};

	public java.lang.String getNotes() {
		return(notes);
	};
	public void setNotes(java.lang.String _notes_) {
		this.notes = _notes_;
	};

	public java.lang.String getCaduca() {
		return(caduca);
	};
	public void setCaduca(java.lang.String _caduca_) {
		this.caduca = _caduca_;
	};

	public java.lang.String getFechaCaduca() {
		return(fechaCaduca);
	};
	public void setFechaCaduca(java.lang.String _fechaCaduca_) {
		this.fechaCaduca = _fechaCaduca_;
	};



  // ======================================

  public static SolicitudServeiBean toBean(SolicitudServei __bean) {
    if (__bean == null) { return null;}
    SolicitudServeiBean __tmp = new SolicitudServeiBean();
    __tmp.setId(__bean.getId());
    __tmp.setSolicitudID(__bean.getSolicitudID());
    __tmp.setServeiID(__bean.getServeiID());
    __tmp.setEstatSolicitudServeiID(__bean.getEstatSolicitudServeiID());
    __tmp.setNormaLegal(__bean.getNormaLegal());
    __tmp.setEnllazNormaLegal(__bean.getEnllazNormaLegal());
    __tmp.setArticles(__bean.getArticles());
    __tmp.setTipusConsentiment(__bean.getTipusConsentiment());
    __tmp.setConsentiment(__bean.getConsentiment());
    __tmp.setEnllazConsentiment(__bean.getEnllazConsentiment());
    __tmp.setNotes(__bean.getNotes());
    __tmp.setCaduca(__bean.getCaduca());
    __tmp.setFechaCaduca(__bean.getFechaCaduca());
		return __tmp;
	}



}
