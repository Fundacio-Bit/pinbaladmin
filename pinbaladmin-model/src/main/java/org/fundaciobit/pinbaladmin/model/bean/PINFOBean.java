
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.PINFO;


public class PINFOBean implements PINFO {



	long PinfoID;// PK
	java.lang.Long IncidenciaID;
	java.lang.Long estat;
	java.lang.Long fitxerID;
	java.lang.Long fitxerfirmatID;
	java.lang.String portafibid;


  /** Constructor Buit */
  public PINFOBean() {
  }

  /** Constructor amb tots els camps  */
  public PINFOBean(long PinfoID , java.lang.Long IncidenciaID , java.lang.Long estat , java.lang.Long fitxerID , java.lang.Long fitxerfirmatID , java.lang.String portafibid) {
    this.PinfoID=PinfoID;
    this.IncidenciaID=IncidenciaID;
    this.estat=estat;
    this.fitxerID=fitxerID;
    this.fitxerfirmatID=fitxerfirmatID;
    this.portafibid=portafibid;
}
  /** Constructor sense valors autoincrementals */
  public PINFOBean(java.lang.Long IncidenciaID , java.lang.Long estat , java.lang.Long fitxerID , java.lang.Long fitxerfirmatID , java.lang.String portafibid) {
    this.IncidenciaID=IncidenciaID;
    this.estat=estat;
    this.fitxerID=fitxerID;
    this.fitxerfirmatID=fitxerfirmatID;
    this.portafibid=portafibid;
}
  /** Constructor dels valors Not Null */
  public PINFOBean(long PinfoID) {
    this.PinfoID=PinfoID;
}
  public PINFOBean(PINFO __bean) {
    this.setPinfoID(__bean.getPinfoID());
    this.setIncidenciaID(__bean.getIncidenciaID());
    this.setEstat(__bean.getEstat());
    this.setFitxerID(__bean.getFitxerID());
    this.setFitxerfirmatID(__bean.getFitxerfirmatID());
    this.setPortafibid(__bean.getPortafibid());
    // Fitxer
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
    // Fitxer
    this.setFitxerfirmat(FitxerBean.toBean(__bean.getFitxerfirmat()));
	}

	public long getPinfoID() {
		return(PinfoID);
	};
	public void setPinfoID(long _PinfoID_) {
		this.PinfoID = _PinfoID_;
	};

	public java.lang.Long getIncidenciaID() {
		return(IncidenciaID);
	};
	public void setIncidenciaID(java.lang.Long _IncidenciaID_) {
		this.IncidenciaID = _IncidenciaID_;
	};

	public java.lang.Long getEstat() {
		return(estat);
	};
	public void setEstat(java.lang.Long _estat_) {
		this.estat = _estat_;
	};

	public java.lang.Long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(java.lang.Long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public java.lang.Long getFitxerfirmatID() {
		return(fitxerfirmatID);
	};
	public void setFitxerfirmatID(java.lang.Long _fitxerfirmatID_) {
		this.fitxerfirmatID = _fitxerfirmatID_;
	};

	public java.lang.String getPortafibid() {
		return(portafibid);
	};
	public void setPortafibid(java.lang.String _portafibid_) {
		this.portafibid = _portafibid_;
	};



  // ======================================

  public static PINFOBean toBean(PINFO __bean) {
    if (__bean == null) { return null;}
    PINFOBean __tmp = new PINFOBean();
    __tmp.setPinfoID(__bean.getPinfoID());
    __tmp.setIncidenciaID(__bean.getIncidenciaID());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setFitxerfirmatID(__bean.getFitxerfirmatID());
    __tmp.setPortafibid(__bean.getPortafibid());
    // Fitxer
    __tmp.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
    // Fitxer
    __tmp.setFitxerfirmat(FitxerBean.toBean(__bean.getFitxerfirmat()));
		return __tmp;
	}

  protected FitxerBean fitxer;
  public FitxerBean getFitxer() {
    return fitxer;
  }
  public void setFitxer(FitxerBean __field) {
    this. fitxer = __field;
  }
  protected FitxerBean fitxerfirmat;
  public FitxerBean getFitxerfirmat() {
    return fitxerfirmat;
  }
  public void setFitxerfirmat(FitxerBean __field) {
    this. fitxerfirmat = __field;
  }


}
