
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;


public class TramitIServBean implements TramitIServ {



	long servid;// PK
	long tramitid;
	java.lang.String nom;
	java.lang.String codi;
	java.lang.String norma;
	java.lang.String urlnorma;
	java.lang.String articles;
	java.lang.String norma2;
	java.lang.String urlnorma2;
	java.lang.String articles2;
	java.lang.Long fitxernormaID;
	java.lang.Long fitxernorma2ID;


  /** Constructor Buit */
  public TramitIServBean() {
  }

  /** Constructor amb tots els camps  */
  public TramitIServBean(long servid , long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.String urlnorma , java.lang.String articles , java.lang.String norma2 , java.lang.String urlnorma2 , java.lang.String articles2 , java.lang.Long fitxernormaID , java.lang.Long fitxernorma2ID) {
    this.servid=servid;
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.urlnorma=urlnorma;
    this.articles=articles;
    this.norma2=norma2;
    this.urlnorma2=urlnorma2;
    this.articles2=articles2;
    this.fitxernormaID=fitxernormaID;
    this.fitxernorma2ID=fitxernorma2ID;
}
  /** Constructor sense valors autoincrementals */
  public TramitIServBean(long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.String urlnorma , java.lang.String articles , java.lang.String norma2 , java.lang.String urlnorma2 , java.lang.String articles2 , java.lang.Long fitxernormaID , java.lang.Long fitxernorma2ID) {
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.urlnorma=urlnorma;
    this.articles=articles;
    this.norma2=norma2;
    this.urlnorma2=urlnorma2;
    this.articles2=articles2;
    this.fitxernormaID=fitxernormaID;
    this.fitxernorma2ID=fitxernorma2ID;
}
  /** Constructor dels valors Not Null */
  public TramitIServBean(long servid , long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.String urlnorma , java.lang.String articles , java.lang.Long fitxernormaID) {
    this.servid=servid;
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.urlnorma=urlnorma;
    this.articles=articles;
    this.fitxernormaID=fitxernormaID;
}
  public TramitIServBean(TramitIServ __bean) {
    this.setServid(__bean.getServid());
    this.setTramitid(__bean.getTramitid());
    this.setNom(__bean.getNom());
    this.setCodi(__bean.getCodi());
    this.setNorma(__bean.getNorma());
    this.setUrlnorma(__bean.getUrlnorma());
    this.setArticles(__bean.getArticles());
    this.setNorma2(__bean.getNorma2());
    this.setUrlnorma2(__bean.getUrlnorma2());
    this.setArticles2(__bean.getArticles2());
    this.setFitxernormaID(__bean.getFitxernormaID());
    this.setFitxernorma2ID(__bean.getFitxernorma2ID());
    // Fitxer
    this.setFitxernorma(FitxerBean.toBean(__bean.getFitxernorma()));
    // Fitxer
    this.setFitxernorma2(FitxerBean.toBean(__bean.getFitxernorma2()));
	}

	public long getServid() {
		return(servid);
	};
	public void setServid(long _servid_) {
		this.servid = _servid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
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

	public java.lang.String getNorma() {
		return(norma);
	};
	public void setNorma(java.lang.String _norma_) {
		this.norma = _norma_;
	};

	public java.lang.String getUrlnorma() {
		return(urlnorma);
	};
	public void setUrlnorma(java.lang.String _urlnorma_) {
		this.urlnorma = _urlnorma_;
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

	public java.lang.String getUrlnorma2() {
		return(urlnorma2);
	};
	public void setUrlnorma2(java.lang.String _urlnorma2_) {
		this.urlnorma2 = _urlnorma2_;
	};

	public java.lang.String getArticles2() {
		return(articles2);
	};
	public void setArticles2(java.lang.String _articles2_) {
		this.articles2 = _articles2_;
	};

	public java.lang.Long getFitxernormaID() {
		return(fitxernormaID);
	};
	public void setFitxernormaID(java.lang.Long _fitxernormaID_) {
		this.fitxernormaID = _fitxernormaID_;
	};

	public java.lang.Long getFitxernorma2ID() {
		return(fitxernorma2ID);
	};
	public void setFitxernorma2ID(java.lang.Long _fitxernorma2ID_) {
		this.fitxernorma2ID = _fitxernorma2ID_;
	};



  // ======================================

  public static TramitIServBean toBean(TramitIServ __bean) {
    if (__bean == null) { return null;}
    TramitIServBean __tmp = new TramitIServBean();
    __tmp.setServid(__bean.getServid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setNom(__bean.getNom());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setNorma(__bean.getNorma());
    __tmp.setUrlnorma(__bean.getUrlnorma());
    __tmp.setArticles(__bean.getArticles());
    __tmp.setNorma2(__bean.getNorma2());
    __tmp.setUrlnorma2(__bean.getUrlnorma2());
    __tmp.setArticles2(__bean.getArticles2());
    __tmp.setFitxernormaID(__bean.getFitxernormaID());
    __tmp.setFitxernorma2ID(__bean.getFitxernorma2ID());
    // Fitxer
    __tmp.setFitxernorma(FitxerBean.toBean(__bean.getFitxernorma()));
    // Fitxer
    __tmp.setFitxernorma2(FitxerBean.toBean(__bean.getFitxernorma2()));
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


}
