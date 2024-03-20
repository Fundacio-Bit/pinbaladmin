
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


  /** Constructor Buit */
  public TramitIServBean() {
  }

  /** Constructor amb tots els camps  */
  public TramitIServBean(long servid , long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.String urlnorma , java.lang.String articles) {
    this.servid=servid;
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.urlnorma=urlnorma;
    this.articles=articles;
}
  /** Constructor sense valors autoincrementals */
  public TramitIServBean(long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.String urlnorma , java.lang.String articles) {
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.urlnorma=urlnorma;
    this.articles=articles;
}
  public TramitIServBean(TramitIServ __bean) {
    this.setServid(__bean.getServid());
    this.setTramitid(__bean.getTramitid());
    this.setNom(__bean.getNom());
    this.setCodi(__bean.getCodi());
    this.setNorma(__bean.getNorma());
    this.setUrlnorma(__bean.getUrlnorma());
    this.setArticles(__bean.getArticles());
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
		return __tmp;
	}



}
