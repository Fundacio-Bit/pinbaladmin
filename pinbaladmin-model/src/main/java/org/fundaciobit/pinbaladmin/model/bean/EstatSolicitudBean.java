
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.EstatSolicitud;


public class EstatSolicitudBean implements EstatSolicitud {



	long estatSolicitudID;// PK
	java.lang.String nom;
	java.lang.String descripcio;


  /** Constructor Buit */
  public EstatSolicitudBean() {
  }

  /** Constructor amb tots els camps  */
  public EstatSolicitudBean(long estatSolicitudID , java.lang.String nom , java.lang.String descripcio) {
    this.estatSolicitudID=estatSolicitudID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public EstatSolicitudBean(long estatSolicitudID , java.lang.String nom) {
    this.estatSolicitudID=estatSolicitudID;
    this.nom=nom;
}
  public EstatSolicitudBean(EstatSolicitud __bean) {
    this.setEstatSolicitudID(__bean.getEstatSolicitudID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getEstatSolicitudID() {
		return(estatSolicitudID);
	};
	public void setEstatSolicitudID(long _estatSolicitudID_) {
		this.estatSolicitudID = _estatSolicitudID_;
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

  public static EstatSolicitudBean toBean(EstatSolicitud __bean) {
    if (__bean == null) { return null;}
    EstatSolicitudBean __tmp = new EstatSolicitudBean();
    __tmp.setEstatSolicitudID(__bean.getEstatSolicitudID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
