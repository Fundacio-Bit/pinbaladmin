
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.EntitatServei;


public class EntitatServeiBean implements EntitatServei {



	long entitatServeiID;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	boolean balears;


  /** Constructor Buit */
  public EntitatServeiBean() {
  }

  /** Constructor amb tots els camps  */
  public EntitatServeiBean(long entitatServeiID , java.lang.String nom , java.lang.String descripcio , boolean balears) {
    this.entitatServeiID=entitatServeiID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.balears=balears;
}
  /** Constructor sense valors autoincrementals */
  public EntitatServeiBean(java.lang.String nom , java.lang.String descripcio , boolean balears) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.balears=balears;
}
  public EntitatServeiBean(EntitatServei __bean) {
    this.setEntitatServeiID(__bean.getEntitatServeiID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setBalears(__bean.isBalears());
	}

	public long getEntitatServeiID() {
		return(entitatServeiID);
	};
	public void setEntitatServeiID(long _entitatServeiID_) {
		this.entitatServeiID = _entitatServeiID_;
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

	public boolean isBalears() {
		return(balears);
	};
	public void setBalears(boolean _balears_) {
		this.balears = _balears_;
	};



  // ======================================

  public static EntitatServeiBean toBean(EntitatServei __bean) {
    if (__bean == null) { return null;}
    EntitatServeiBean __tmp = new EntitatServeiBean();
    __tmp.setEntitatServeiID(__bean.getEntitatServeiID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setBalears(__bean.isBalears());
		return __tmp;
	}



}
