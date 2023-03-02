
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.CampSolicitud;


public class CampSolicitudBean implements CampSolicitud {



	long campSolicitudID;// PK
	long campFormulariID;
	long solicitudServeiID;
	java.lang.String valor;


  /** Constructor Buit */
  public CampSolicitudBean() {
  }

  /** Constructor amb tots els camps  */
  public CampSolicitudBean(long campSolicitudID , long campFormulariID , long solicitudServeiID , java.lang.String valor) {
    this.campSolicitudID=campSolicitudID;
    this.campFormulariID=campFormulariID;
    this.solicitudServeiID=solicitudServeiID;
    this.valor=valor;
}
  /** Constructor sense valors autoincrementals */
  public CampSolicitudBean(long campFormulariID , long solicitudServeiID , java.lang.String valor) {
    this.campFormulariID=campFormulariID;
    this.solicitudServeiID=solicitudServeiID;
    this.valor=valor;
}
  public CampSolicitudBean(CampSolicitud __bean) {
    this.setCampSolicitudID(__bean.getCampSolicitudID());
    this.setCampFormulariID(__bean.getCampFormulariID());
    this.setSolicitudServeiID(__bean.getSolicitudServeiID());
    this.setValor(__bean.getValor());
	}

	public long getCampSolicitudID() {
		return(campSolicitudID);
	};
	public void setCampSolicitudID(long _campSolicitudID_) {
		this.campSolicitudID = _campSolicitudID_;
	};

	public long getCampFormulariID() {
		return(campFormulariID);
	};
	public void setCampFormulariID(long _campFormulariID_) {
		this.campFormulariID = _campFormulariID_;
	};

	public long getSolicitudServeiID() {
		return(solicitudServeiID);
	};
	public void setSolicitudServeiID(long _solicitudServeiID_) {
		this.solicitudServeiID = _solicitudServeiID_;
	};

	public java.lang.String getValor() {
		return(valor);
	};
	public void setValor(java.lang.String _valor_) {
		this.valor = _valor_;
	};



  // ======================================

  public static CampSolicitudBean toBean(CampSolicitud __bean) {
    if (__bean == null) { return null;}
    CampSolicitudBean __tmp = new CampSolicitudBean();
    __tmp.setCampSolicitudID(__bean.getCampSolicitudID());
    __tmp.setCampFormulariID(__bean.getCampFormulariID());
    __tmp.setSolicitudServeiID(__bean.getSolicitudServeiID());
    __tmp.setValor(__bean.getValor());
		return __tmp;
	}



}
