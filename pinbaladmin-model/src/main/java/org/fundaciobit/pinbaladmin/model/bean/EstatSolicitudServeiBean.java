
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.EstatSolicitudServei;


public class EstatSolicitudServeiBean implements EstatSolicitudServei {



	long estatSolicitudServeiID;// PK
	java.lang.String nom;
	java.lang.String descripcio;


  /** Constructor Buit */
  public EstatSolicitudServeiBean() {
  }

  /** Constructor amb tots els camps  */
  public EstatSolicitudServeiBean(long estatSolicitudServeiID , java.lang.String nom , java.lang.String descripcio) {
    this.estatSolicitudServeiID=estatSolicitudServeiID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public EstatSolicitudServeiBean(long estatSolicitudServeiID , java.lang.String nom) {
    this.estatSolicitudServeiID=estatSolicitudServeiID;
    this.nom=nom;
}
  public EstatSolicitudServeiBean(EstatSolicitudServei __bean) {
    this.setEstatSolicitudServeiID(__bean.getEstatSolicitudServeiID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getEstatSolicitudServeiID() {
		return(estatSolicitudServeiID);
	};
	public void setEstatSolicitudServeiID(long _estatSolicitudServeiID_) {
		this.estatSolicitudServeiID = _estatSolicitudServeiID_;
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

  public static EstatSolicitudServeiBean toBean(EstatSolicitudServei __bean) {
    if (__bean == null) { return null;}
    EstatSolicitudServeiBean __tmp = new EstatSolicitudServeiBean();
    __tmp.setEstatSolicitudServeiID(__bean.getEstatSolicitudServeiID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
