
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.EstatServei;


public class EstatServeiBean implements EstatServei {



	long estatServeiID;// PK
	java.lang.String nom;
	java.lang.String descripcio;


  /** Constructor Buit */
  public EstatServeiBean() {
  }

  /** Constructor amb tots els camps  */
  public EstatServeiBean(long estatServeiID , java.lang.String nom , java.lang.String descripcio) {
    this.estatServeiID=estatServeiID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public EstatServeiBean(long estatServeiID , java.lang.String nom) {
    this.estatServeiID=estatServeiID;
    this.nom=nom;
}
  public EstatServeiBean(EstatServei __bean) {
    this.setEstatServeiID(__bean.getEstatServeiID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getEstatServeiID() {
		return(estatServeiID);
	};
	public void setEstatServeiID(long _estatServeiID_) {
		this.estatServeiID = _estatServeiID_;
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



  // ======================================

  public static EstatServeiBean toBean(EstatServei __bean) {
    if (__bean == null) { return null;}
    EstatServeiBean __tmp = new EstatServeiBean();
    __tmp.setEstatServeiID(__bean.getEstatServeiID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
