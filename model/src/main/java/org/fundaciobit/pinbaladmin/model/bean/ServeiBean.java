
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Servei;


public class ServeiBean implements Servei {



private static final long serialVersionUID = 158688810L;

	long serveiID;// PK
	java.lang.String codi;
	java.lang.String nom;
	java.lang.String descripcio;
	java.lang.Long formulariID;
	java.lang.Long entitatServeiID;
	java.lang.Long estatServeiID;
	int tipusConsentiment;
	boolean ocult;


  /** Constructor Buit */
  public ServeiBean() {
  }

  /** Constructor amb tots els camps  */
  public ServeiBean(long serveiID , java.lang.String codi , java.lang.String nom , java.lang.String descripcio , java.lang.Long formulariID , java.lang.Long entitatServeiID , java.lang.Long estatServeiID , int tipusConsentiment , boolean ocult) {
    this.serveiID=serveiID;
    this.codi=codi;
    this.nom=nom;
    this.descripcio=descripcio;
    this.formulariID=formulariID;
    this.entitatServeiID=entitatServeiID;
    this.estatServeiID=estatServeiID;
    this.tipusConsentiment=tipusConsentiment;
    this.ocult=ocult;
}
  /** Constructor sense valors autoincrementals */
  public ServeiBean(java.lang.String codi , java.lang.String nom , java.lang.String descripcio , java.lang.Long formulariID , java.lang.Long entitatServeiID , java.lang.Long estatServeiID , int tipusConsentiment , boolean ocult) {
    this.codi=codi;
    this.nom=nom;
    this.descripcio=descripcio;
    this.formulariID=formulariID;
    this.entitatServeiID=entitatServeiID;
    this.estatServeiID=estatServeiID;
    this.tipusConsentiment=tipusConsentiment;
    this.ocult=ocult;
}
  /** Constructor dels valors Not Null */
  public ServeiBean(long serveiID , java.lang.String codi , java.lang.String nom , java.lang.Long entitatServeiID , java.lang.Long estatServeiID , int tipusConsentiment , boolean ocult) {
    this.serveiID=serveiID;
    this.codi=codi;
    this.nom=nom;
    this.entitatServeiID=entitatServeiID;
    this.estatServeiID=estatServeiID;
    this.tipusConsentiment=tipusConsentiment;
    this.ocult=ocult;
}
  public ServeiBean(Servei __bean) {
    this.setServeiID(__bean.getServeiID());
    this.setCodi(__bean.getCodi());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setFormulariID(__bean.getFormulariID());
    this.setEntitatServeiID(__bean.getEntitatServeiID());
    this.setEstatServeiID(__bean.getEstatServeiID());
    this.setTipusConsentiment(__bean.getTipusConsentiment());
    this.setOcult(__bean.isOcult());
	}

	public long getServeiID() {
		return(serveiID);
	};
	public void setServeiID(long _serveiID_) {
		this.serveiID = _serveiID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.Long getFormulariID() {
		return(formulariID);
	};
	public void setFormulariID(java.lang.Long _formulariID_) {
		this.formulariID = _formulariID_;
	};

	public java.lang.Long getEntitatServeiID() {
		return(entitatServeiID);
	};
	public void setEntitatServeiID(java.lang.Long _entitatServeiID_) {
		this.entitatServeiID = _entitatServeiID_;
	};

	public java.lang.Long getEstatServeiID() {
		return(estatServeiID);
	};
	public void setEstatServeiID(java.lang.Long _estatServeiID_) {
		this.estatServeiID = _estatServeiID_;
	};

	public int getTipusConsentiment() {
		return(tipusConsentiment);
	};
	public void setTipusConsentiment(int _tipusConsentiment_) {
		this.tipusConsentiment = _tipusConsentiment_;
	};

	public boolean isOcult() {
		return(ocult);
	};
	public void setOcult(boolean _ocult_) {
		this.ocult = _ocult_;
	};



  // ======================================

  public static ServeiBean toBean(Servei __bean) {
    if (__bean == null) { return null;}
    ServeiBean __tmp = new ServeiBean();
    __tmp.setServeiID(__bean.getServeiID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setFormulariID(__bean.getFormulariID());
    __tmp.setEntitatServeiID(__bean.getEntitatServeiID());
    __tmp.setEstatServeiID(__bean.getEstatServeiID());
    __tmp.setTipusConsentiment(__bean.getTipusConsentiment());
    __tmp.setOcult(__bean.isOcult());
		return __tmp;
	}



}
