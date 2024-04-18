
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.TramitIServ;


public class TramitIServBean implements TramitIServ {



	long servid;// PK
	long tramitid;
	java.lang.String nom;
	java.lang.String codi;
	java.lang.String norma;
	java.lang.Long fitxernormaID;
	java.lang.String urlnorma;
	java.lang.String articles;
	java.lang.String norma2;
	java.lang.Long fitxernorma2ID;
	java.lang.String articles2;
	java.lang.String norma3;
	java.lang.Long fitxernorma3ID;
	java.lang.String articles3;


  /** Constructor Buit */
  public TramitIServBean() {
  }

  /** Constructor amb tots els camps  */
  public TramitIServBean(long servid , long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.Long fitxernormaID , java.lang.String urlnorma , java.lang.String articles , java.lang.String norma2 , java.lang.Long fitxernorma2ID , java.lang.String articles2 , java.lang.String norma3 , java.lang.Long fitxernorma3ID , java.lang.String articles3) {
    this.servid=servid;
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.fitxernormaID=fitxernormaID;
    this.urlnorma=urlnorma;
    this.articles=articles;
    this.norma2=norma2;
    this.fitxernorma2ID=fitxernorma2ID;
    this.articles2=articles2;
    this.norma3=norma3;
    this.fitxernorma3ID=fitxernorma3ID;
    this.articles3=articles3;
}
  /** Constructor sense valors autoincrementals */
  public TramitIServBean(long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.Long fitxernormaID , java.lang.String urlnorma , java.lang.String articles , java.lang.String norma2 , java.lang.Long fitxernorma2ID , java.lang.String articles2 , java.lang.String norma3 , java.lang.Long fitxernorma3ID , java.lang.String articles3) {
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.fitxernormaID=fitxernormaID;
    this.urlnorma=urlnorma;
    this.articles=articles;
    this.norma2=norma2;
    this.fitxernorma2ID=fitxernorma2ID;
    this.articles2=articles2;
    this.norma3=norma3;
    this.fitxernorma3ID=fitxernorma3ID;
    this.articles3=articles3;
}
  /** Constructor dels valors Not Null */
  public TramitIServBean(long servid , long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.Long fitxernormaID , java.lang.String articles) {
    this.servid=servid;
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.fitxernormaID=fitxernormaID;
    this.articles=articles;
}
  public TramitIServBean(TramitIServ __bean) {
    this.setServid(__bean.getServid());
    this.setTramitid(__bean.getTramitid());
    this.setNom(__bean.getNom());
    this.setCodi(__bean.getCodi());
    this.setNorma(__bean.getNorma());
    this.setFitxernormaID(__bean.getFitxernormaID());
    this.setUrlnorma(__bean.getUrlnorma());
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

	public java.lang.Long getFitxernormaID() {
		return(fitxernormaID);
	};
	public void setFitxernormaID(java.lang.Long _fitxernormaID_) {
		this.fitxernormaID = _fitxernormaID_;
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

  public static TramitIServBean toBean(TramitIServ __bean) {
    if (__bean == null) { return null;}
    TramitIServBean __tmp = new TramitIServBean();
    __tmp.setServid(__bean.getServid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setNom(__bean.getNom());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setNorma(__bean.getNorma());
    __tmp.setFitxernormaID(__bean.getFitxernormaID());
    __tmp.setUrlnorma(__bean.getUrlnorma());
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
