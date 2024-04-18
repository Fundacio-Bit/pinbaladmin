
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;


public class SolicitudServeiBean implements SolicitudServei {



	long id;// PK
	long solicitudID;
	long serveiID;
	java.lang.Long estatSolicitudServeiID;
	java.lang.String enllazNormaLegal;
	java.lang.String tipusConsentiment;
	java.lang.String consentiment;
	java.lang.String enllazConsentiment;
	java.lang.String notes;
	java.lang.String caduca;
	java.lang.String fechaCaduca;
	java.lang.String normaLegal;
	java.lang.Long fitxernormaID;
	java.lang.String articles;
	java.lang.String norma2;
	java.lang.Long fitxernorma2ID;
	java.lang.String articles2;
	java.lang.String norma3;
	java.lang.Long fitxernorma3ID;
	java.lang.String articles3;


  /** Constructor Buit */
  public SolicitudServeiBean() {
  }

  /** Constructor amb tots els camps  */
  public SolicitudServeiBean(long id , long solicitudID , long serveiID , java.lang.Long estatSolicitudServeiID , java.lang.String enllazNormaLegal , java.lang.String tipusConsentiment , java.lang.String consentiment , java.lang.String enllazConsentiment , java.lang.String notes , java.lang.String caduca , java.lang.String fechaCaduca , java.lang.String normaLegal , java.lang.Long fitxernormaID , java.lang.String articles , java.lang.String norma2 , java.lang.Long fitxernorma2ID , java.lang.String articles2 , java.lang.String norma3 , java.lang.Long fitxernorma3ID , java.lang.String articles3) {
    this.id=id;
    this.solicitudID=solicitudID;
    this.serveiID=serveiID;
    this.estatSolicitudServeiID=estatSolicitudServeiID;
    this.enllazNormaLegal=enllazNormaLegal;
    this.tipusConsentiment=tipusConsentiment;
    this.consentiment=consentiment;
    this.enllazConsentiment=enllazConsentiment;
    this.notes=notes;
    this.caduca=caduca;
    this.fechaCaduca=fechaCaduca;
    this.normaLegal=normaLegal;
    this.fitxernormaID=fitxernormaID;
    this.articles=articles;
    this.norma2=norma2;
    this.fitxernorma2ID=fitxernorma2ID;
    this.articles2=articles2;
    this.norma3=norma3;
    this.fitxernorma3ID=fitxernorma3ID;
    this.articles3=articles3;
}
  /** Constructor sense valors autoincrementals */
  public SolicitudServeiBean(long solicitudID , long serveiID , java.lang.Long estatSolicitudServeiID , java.lang.String enllazNormaLegal , java.lang.String tipusConsentiment , java.lang.String consentiment , java.lang.String enllazConsentiment , java.lang.String notes , java.lang.String caduca , java.lang.String fechaCaduca , java.lang.String normaLegal , java.lang.Long fitxernormaID , java.lang.String articles , java.lang.String norma2 , java.lang.Long fitxernorma2ID , java.lang.String articles2 , java.lang.String norma3 , java.lang.Long fitxernorma3ID , java.lang.String articles3) {
    this.solicitudID=solicitudID;
    this.serveiID=serveiID;
    this.estatSolicitudServeiID=estatSolicitudServeiID;
    this.enllazNormaLegal=enllazNormaLegal;
    this.tipusConsentiment=tipusConsentiment;
    this.consentiment=consentiment;
    this.enllazConsentiment=enllazConsentiment;
    this.notes=notes;
    this.caduca=caduca;
    this.fechaCaduca=fechaCaduca;
    this.normaLegal=normaLegal;
    this.fitxernormaID=fitxernormaID;
    this.articles=articles;
    this.norma2=norma2;
    this.fitxernorma2ID=fitxernorma2ID;
    this.articles2=articles2;
    this.norma3=norma3;
    this.fitxernorma3ID=fitxernorma3ID;
    this.articles3=articles3;
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
    this.setEnllazNormaLegal(__bean.getEnllazNormaLegal());
    this.setTipusConsentiment(__bean.getTipusConsentiment());
    this.setConsentiment(__bean.getConsentiment());
    this.setEnllazConsentiment(__bean.getEnllazConsentiment());
    this.setNotes(__bean.getNotes());
    this.setCaduca(__bean.getCaduca());
    this.setFechaCaduca(__bean.getFechaCaduca());
    this.setNormaLegal(__bean.getNormaLegal());
    this.setFitxernormaID(__bean.getFitxernormaID());
    this.setArticles(__bean.getArticles());
    this.setNorma2(__bean.getNorma2());
    this.setFitxernorma2ID(__bean.getFitxernorma2ID());
    this.setArticles2(__bean.getArticles2());
    this.setNorma3(__bean.getNorma3());
    this.setFitxernorma3ID(__bean.getFitxernorma3ID());
    this.setArticles3(__bean.getArticles3());
    // Fitxer
    this.setFitxernorma(FitxerBean.toBean(__bean.getFitxernorma()));
    // Fitxer
    this.setFitxernorma2(FitxerBean.toBean(__bean.getFitxernorma2()));
    // Fitxer
    this.setFitxernorma3(FitxerBean.toBean(__bean.getFitxernorma3()));
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

	public java.lang.String getEnllazNormaLegal() {
		return(enllazNormaLegal);
	};
	public void setEnllazNormaLegal(java.lang.String _enllazNormaLegal_) {
		this.enllazNormaLegal = _enllazNormaLegal_;
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

	public java.lang.String getNormaLegal() {
		return(normaLegal);
	};
	public void setNormaLegal(java.lang.String _normaLegal_) {
		this.normaLegal = _normaLegal_;
	};

	public java.lang.Long getFitxernormaID() {
		return(fitxernormaID);
	};
	public void setFitxernormaID(java.lang.Long _fitxernormaID_) {
		this.fitxernormaID = _fitxernormaID_;
	};

	public java.lang.String getArticles() {
		return(articles);
	};
	public void setArticles(java.lang.String _articles_) {
		this.articles = _articles_;
	};

	public java.lang.String getNorma2() {
		return(norma2);
	};
	public void setNorma2(java.lang.String _norma2_) {
		this.norma2 = _norma2_;
	};

	public java.lang.Long getFitxernorma2ID() {
		return(fitxernorma2ID);
	};
	public void setFitxernorma2ID(java.lang.Long _fitxernorma2ID_) {
		this.fitxernorma2ID = _fitxernorma2ID_;
	};

	public java.lang.String getArticles2() {
		return(articles2);
	};
	public void setArticles2(java.lang.String _articles2_) {
		this.articles2 = _articles2_;
	};

	public java.lang.String getNorma3() {
		return(norma3);
	};
	public void setNorma3(java.lang.String _norma3_) {
		this.norma3 = _norma3_;
	};

	public java.lang.Long getFitxernorma3ID() {
		return(fitxernorma3ID);
	};
	public void setFitxernorma3ID(java.lang.Long _fitxernorma3ID_) {
		this.fitxernorma3ID = _fitxernorma3ID_;
	};

	public java.lang.String getArticles3() {
		return(articles3);
	};
	public void setArticles3(java.lang.String _articles3_) {
		this.articles3 = _articles3_;
	};



  // ======================================

  public static SolicitudServeiBean toBean(SolicitudServei __bean) {
    if (__bean == null) { return null;}
    SolicitudServeiBean __tmp = new SolicitudServeiBean();
    __tmp.setId(__bean.getId());
    __tmp.setSolicitudID(__bean.getSolicitudID());
    __tmp.setServeiID(__bean.getServeiID());
    __tmp.setEstatSolicitudServeiID(__bean.getEstatSolicitudServeiID());
    __tmp.setEnllazNormaLegal(__bean.getEnllazNormaLegal());
    __tmp.setTipusConsentiment(__bean.getTipusConsentiment());
    __tmp.setConsentiment(__bean.getConsentiment());
    __tmp.setEnllazConsentiment(__bean.getEnllazConsentiment());
    __tmp.setNotes(__bean.getNotes());
    __tmp.setCaduca(__bean.getCaduca());
    __tmp.setFechaCaduca(__bean.getFechaCaduca());
    __tmp.setNormaLegal(__bean.getNormaLegal());
    __tmp.setFitxernormaID(__bean.getFitxernormaID());
    __tmp.setArticles(__bean.getArticles());
    __tmp.setNorma2(__bean.getNorma2());
    __tmp.setFitxernorma2ID(__bean.getFitxernorma2ID());
    __tmp.setArticles2(__bean.getArticles2());
    __tmp.setNorma3(__bean.getNorma3());
    __tmp.setFitxernorma3ID(__bean.getFitxernorma3ID());
    __tmp.setArticles3(__bean.getArticles3());
    // Fitxer
    __tmp.setFitxernorma(FitxerBean.toBean(__bean.getFitxernorma()));
    // Fitxer
    __tmp.setFitxernorma2(FitxerBean.toBean(__bean.getFitxernorma2()));
    // Fitxer
    __tmp.setFitxernorma3(FitxerBean.toBean(__bean.getFitxernorma3()));
		return __tmp;
	}

  protected FitxerBean fitxernorma;
  public FitxerBean getFitxernorma() {
    return fitxernorma;
  }
  public void setFitxernorma(FitxerBean __field) {
    this. fitxernorma = __field;
  }
  protected FitxerBean fitxernorma2;
  public FitxerBean getFitxernorma2() {
    return fitxernorma2;
  }
  public void setFitxernorma2(FitxerBean __field) {
    this. fitxernorma2 = __field;
  }
  protected FitxerBean fitxernorma3;
  public FitxerBean getFitxernorma3() {
    return fitxernorma3;
  }
  public void setFitxernorma3(FitxerBean __field) {
    this. fitxernorma3 = __field;
  }


}
