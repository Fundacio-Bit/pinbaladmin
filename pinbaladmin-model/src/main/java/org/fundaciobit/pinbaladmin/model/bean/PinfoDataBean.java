
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.PinfoData;


public class PinfoDataBean implements PinfoData {



	long pinfodataID;// PK
	java.lang.Long pinfoID;
	java.lang.Long estat;
	java.lang.String usuariid;
	java.lang.Long procedimentID;
	java.lang.Long serveiID;
	java.lang.Long alta;


  /** Constructor Buit */
  public PinfoDataBean() {
  }

  /** Constructor amb tots els camps  */
  public PinfoDataBean(long pinfodataID , java.lang.Long pinfoID , java.lang.Long estat , java.lang.String usuariid , java.lang.Long procedimentID , java.lang.Long serveiID , java.lang.Long alta) {
    this.pinfodataID=pinfodataID;
    this.pinfoID=pinfoID;
    this.estat=estat;
    this.usuariid=usuariid;
    this.procedimentID=procedimentID;
    this.serveiID=serveiID;
    this.alta=alta;
}
  /** Constructor sense valors autoincrementals */
  public PinfoDataBean(java.lang.Long pinfoID , java.lang.Long estat , java.lang.String usuariid , java.lang.Long procedimentID , java.lang.Long serveiID , java.lang.Long alta) {
    this.pinfoID=pinfoID;
    this.estat=estat;
    this.usuariid=usuariid;
    this.procedimentID=procedimentID;
    this.serveiID=serveiID;
    this.alta=alta;
}
  /** Constructor dels valors Not Null */
  public PinfoDataBean(long pinfodataID) {
    this.pinfodataID=pinfodataID;
}
  public PinfoDataBean(PinfoData __bean) {
    this.setPinfodataID(__bean.getPinfodataID());
    this.setPinfoID(__bean.getPinfoID());
    this.setEstat(__bean.getEstat());
    this.setUsuariid(__bean.getUsuariid());
    this.setProcedimentID(__bean.getProcedimentID());
    this.setServeiID(__bean.getServeiID());
    this.setAlta(__bean.getAlta());
	}

	public long getPinfodataID() {
		return(pinfodataID);
	};
	public void setPinfodataID(long _pinfodataID_) {
		this.pinfodataID = _pinfodataID_;
	};

	public java.lang.Long getPinfoID() {
		return(pinfoID);
	};
	public void setPinfoID(java.lang.Long _pinfoID_) {
		this.pinfoID = _pinfoID_;
	};

	public java.lang.Long getEstat() {
		return(estat);
	};
	public void setEstat(java.lang.Long _estat_) {
		this.estat = _estat_;
	};

	public java.lang.String getUsuariid() {
		return(usuariid);
	};
	public void setUsuariid(java.lang.String _usuariid_) {
		this.usuariid = _usuariid_;
	};

	public java.lang.Long getProcedimentID() {
		return(procedimentID);
	};
	public void setProcedimentID(java.lang.Long _procedimentID_) {
		this.procedimentID = _procedimentID_;
	};

	public java.lang.Long getServeiID() {
		return(serveiID);
	};
	public void setServeiID(java.lang.Long _serveiID_) {
		this.serveiID = _serveiID_;
	};

	public java.lang.Long getAlta() {
		return(alta);
	};
	public void setAlta(java.lang.Long _alta_) {
		this.alta = _alta_;
	};



  // ======================================

  public static PinfoDataBean toBean(PinfoData __bean) {
    if (__bean == null) { return null;}
    PinfoDataBean __tmp = new PinfoDataBean();
    __tmp.setPinfodataID(__bean.getPinfodataID());
    __tmp.setPinfoID(__bean.getPinfoID());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setUsuariid(__bean.getUsuariid());
    __tmp.setProcedimentID(__bean.getProcedimentID());
    __tmp.setServeiID(__bean.getServeiID());
    __tmp.setAlta(__bean.getAlta());
		return __tmp;
	}



}
