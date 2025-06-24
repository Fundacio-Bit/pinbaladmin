
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.ModificacioSoliServ;


public class ModificacioSoliServBean implements ModificacioSoliServ {



	long modsoliservid;// PK
	long soliServID;
	long modSoliID;
	java.lang.String estat;
	java.lang.String norma1;
	java.lang.String articles1;
	java.lang.Long fitxerNorma1ID;
	java.lang.String norma2;
	java.lang.String articles2;
	java.lang.Long fitxerNorma2ID;
	java.lang.String norma3;
	java.lang.String articles3;
	java.lang.Long fitxerNorma3ID;


  /** Constructor Buit */
  public ModificacioSoliServBean() {
  }

  /** Constructor amb tots els camps  */
  public ModificacioSoliServBean(long modsoliservid , long soliServID , long modSoliID , java.lang.String estat , java.lang.String norma1 , java.lang.String articles1 , java.lang.Long fitxerNorma1ID , java.lang.String norma2 , java.lang.String articles2 , java.lang.Long fitxerNorma2ID , java.lang.String norma3 , java.lang.String articles3 , java.lang.Long fitxerNorma3ID) {
    this.modsoliservid=modsoliservid;
    this.soliServID=soliServID;
    this.modSoliID=modSoliID;
    this.estat=estat;
    this.norma1=norma1;
    this.articles1=articles1;
    this.fitxerNorma1ID=fitxerNorma1ID;
    this.norma2=norma2;
    this.articles2=articles2;
    this.fitxerNorma2ID=fitxerNorma2ID;
    this.norma3=norma3;
    this.articles3=articles3;
    this.fitxerNorma3ID=fitxerNorma3ID;
}
  /** Constructor sense valors autoincrementals */
  public ModificacioSoliServBean(long soliServID , long modSoliID , java.lang.String estat , java.lang.String norma1 , java.lang.String articles1 , java.lang.Long fitxerNorma1ID , java.lang.String norma2 , java.lang.String articles2 , java.lang.Long fitxerNorma2ID , java.lang.String norma3 , java.lang.String articles3 , java.lang.Long fitxerNorma3ID) {
    this.soliServID=soliServID;
    this.modSoliID=modSoliID;
    this.estat=estat;
    this.norma1=norma1;
    this.articles1=articles1;
    this.fitxerNorma1ID=fitxerNorma1ID;
    this.norma2=norma2;
    this.articles2=articles2;
    this.fitxerNorma2ID=fitxerNorma2ID;
    this.norma3=norma3;
    this.articles3=articles3;
    this.fitxerNorma3ID=fitxerNorma3ID;
}
  /** Constructor dels valors Not Null */
  public ModificacioSoliServBean(long modsoliservid , long soliServID , long modSoliID) {
    this.modsoliservid=modsoliservid;
    this.soliServID=soliServID;
    this.modSoliID=modSoliID;
}
  public ModificacioSoliServBean(ModificacioSoliServ __bean) {
    this.setModsoliservid(__bean.getModsoliservid());
    this.setSoliServID(__bean.getSoliServID());
    this.setModSoliID(__bean.getModSoliID());
    this.setEstat(__bean.getEstat());
    this.setNorma1(__bean.getNorma1());
    this.setArticles1(__bean.getArticles1());
    this.setFitxerNorma1ID(__bean.getFitxerNorma1ID());
    this.setNorma2(__bean.getNorma2());
    this.setArticles2(__bean.getArticles2());
    this.setFitxerNorma2ID(__bean.getFitxerNorma2ID());
    this.setNorma3(__bean.getNorma3());
    this.setArticles3(__bean.getArticles3());
    this.setFitxerNorma3ID(__bean.getFitxerNorma3ID());
    // Fitxer
    this.setFitxerNorma1(FitxerBean.toBean(__bean.getFitxerNorma1()));
    // Fitxer
    this.setFitxerNorma2(FitxerBean.toBean(__bean.getFitxerNorma2()));
    // Fitxer
    this.setFitxerNorma3(FitxerBean.toBean(__bean.getFitxerNorma3()));
	}

	public long getModsoliservid() {
		return(modsoliservid);
	};
	public void setModsoliservid(long _modsoliservid_) {
		this.modsoliservid = _modsoliservid_;
	};

	public long getSoliServID() {
		return(soliServID);
	};
	public void setSoliServID(long _soliServID_) {
		this.soliServID = _soliServID_;
	};

	public long getModSoliID() {
		return(modSoliID);
	};
	public void setModSoliID(long _modSoliID_) {
		this.modSoliID = _modSoliID_;
	};

	public java.lang.String getEstat() {
		return(estat);
	};
	public void setEstat(java.lang.String _estat_) {
		this.estat = _estat_;
	};

	public java.lang.String getNorma1() {
		return(norma1);
	};
	public void setNorma1(java.lang.String _norma1_) {
		this.norma1 = _norma1_;
	};

	public java.lang.String getArticles1() {
		return(articles1);
	};
	public void setArticles1(java.lang.String _articles1_) {
		this.articles1 = _articles1_;
	};

	public java.lang.Long getFitxerNorma1ID() {
		return(fitxerNorma1ID);
	};
	public void setFitxerNorma1ID(java.lang.Long _fitxerNorma1ID_) {
		this.fitxerNorma1ID = _fitxerNorma1ID_;
	};

	public java.lang.String getNorma2() {
		return(norma2);
	};
	public void setNorma2(java.lang.String _norma2_) {
		this.norma2 = _norma2_;
	};

	public java.lang.String getArticles2() {
		return(articles2);
	};
	public void setArticles2(java.lang.String _articles2_) {
		this.articles2 = _articles2_;
	};

	public java.lang.Long getFitxerNorma2ID() {
		return(fitxerNorma2ID);
	};
	public void setFitxerNorma2ID(java.lang.Long _fitxerNorma2ID_) {
		this.fitxerNorma2ID = _fitxerNorma2ID_;
	};

	public java.lang.String getNorma3() {
		return(norma3);
	};
	public void setNorma3(java.lang.String _norma3_) {
		this.norma3 = _norma3_;
	};

	public java.lang.String getArticles3() {
		return(articles3);
	};
	public void setArticles3(java.lang.String _articles3_) {
		this.articles3 = _articles3_;
	};

	public java.lang.Long getFitxerNorma3ID() {
		return(fitxerNorma3ID);
	};
	public void setFitxerNorma3ID(java.lang.Long _fitxerNorma3ID_) {
		this.fitxerNorma3ID = _fitxerNorma3ID_;
	};



  // ======================================

  public static ModificacioSoliServBean toBean(ModificacioSoliServ __bean) {
    if (__bean == null) { return null;}
    ModificacioSoliServBean __tmp = new ModificacioSoliServBean();
    __tmp.setModsoliservid(__bean.getModsoliservid());
    __tmp.setSoliServID(__bean.getSoliServID());
    __tmp.setModSoliID(__bean.getModSoliID());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setNorma1(__bean.getNorma1());
    __tmp.setArticles1(__bean.getArticles1());
    __tmp.setFitxerNorma1ID(__bean.getFitxerNorma1ID());
    __tmp.setNorma2(__bean.getNorma2());
    __tmp.setArticles2(__bean.getArticles2());
    __tmp.setFitxerNorma2ID(__bean.getFitxerNorma2ID());
    __tmp.setNorma3(__bean.getNorma3());
    __tmp.setArticles3(__bean.getArticles3());
    __tmp.setFitxerNorma3ID(__bean.getFitxerNorma3ID());
    // Fitxer
    __tmp.setFitxerNorma1(FitxerBean.toBean(__bean.getFitxerNorma1()));
    // Fitxer
    __tmp.setFitxerNorma2(FitxerBean.toBean(__bean.getFitxerNorma2()));
    // Fitxer
    __tmp.setFitxerNorma3(FitxerBean.toBean(__bean.getFitxerNorma3()));
		return __tmp;
	}

  protected FitxerBean fitxerNorma1;
  public FitxerBean getFitxerNorma1() {
    return fitxerNorma1;
  }
  public void setFitxerNorma1(FitxerBean __field) {
    this. fitxerNorma1 = __field;
  }
  protected FitxerBean fitxerNorma2;
  public FitxerBean getFitxerNorma2() {
    return fitxerNorma2;
  }
  public void setFitxerNorma2(FitxerBean __field) {
    this. fitxerNorma2 = __field;
  }
  protected FitxerBean fitxerNorma3;
  public FitxerBean getFitxerNorma3() {
    return fitxerNorma3;
  }
  public void setFitxerNorma3(FitxerBean __field) {
    this. fitxerNorma3 = __field;
  }


}
