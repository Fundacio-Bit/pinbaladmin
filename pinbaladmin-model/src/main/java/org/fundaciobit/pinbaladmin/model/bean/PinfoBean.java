
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Pinfo;


public class PinfoBean implements Pinfo {



	long pinfoID;// PK
	java.lang.Long incidenciaID;
	java.lang.String entitat;
	java.lang.String solicitantNIF;
	java.lang.Long estat;
	java.lang.Long fitxerID;
	java.lang.Long fitxerfirmatID;
	java.lang.String portafibid;
	java.lang.String destinatariNIF;
	java.lang.String destinatariNom;
	java.lang.String missatgePinbal;


  /** Constructor Buit */
  public PinfoBean() {
  }

  /** Constructor amb tots els camps  */
  public PinfoBean(long pinfoID , java.lang.Long incidenciaID , java.lang.String entitat , java.lang.String solicitantNIF , java.lang.Long estat , java.lang.Long fitxerID , java.lang.Long fitxerfirmatID , java.lang.String portafibid , java.lang.String destinatariNIF , java.lang.String destinatariNom , java.lang.String missatgePinbal) {
    this.pinfoID=pinfoID;
    this.incidenciaID=incidenciaID;
    this.entitat=entitat;
    this.solicitantNIF=solicitantNIF;
    this.estat=estat;
    this.fitxerID=fitxerID;
    this.fitxerfirmatID=fitxerfirmatID;
    this.portafibid=portafibid;
    this.destinatariNIF=destinatariNIF;
    this.destinatariNom=destinatariNom;
    this.missatgePinbal=missatgePinbal;
}
  /** Constructor sense valors autoincrementals */
  public PinfoBean(java.lang.Long incidenciaID , java.lang.String entitat , java.lang.String solicitantNIF , java.lang.Long estat , java.lang.Long fitxerID , java.lang.Long fitxerfirmatID , java.lang.String portafibid , java.lang.String destinatariNIF , java.lang.String destinatariNom , java.lang.String missatgePinbal) {
    this.incidenciaID=incidenciaID;
    this.entitat=entitat;
    this.solicitantNIF=solicitantNIF;
    this.estat=estat;
    this.fitxerID=fitxerID;
    this.fitxerfirmatID=fitxerfirmatID;
    this.portafibid=portafibid;
    this.destinatariNIF=destinatariNIF;
    this.destinatariNom=destinatariNom;
    this.missatgePinbal=missatgePinbal;
}
  /** Constructor dels valors Not Null */
  public PinfoBean(long pinfoID) {
    this.pinfoID=pinfoID;
}
  public PinfoBean(Pinfo __bean) {
    this.setPinfoID(__bean.getPinfoID());
    this.setIncidenciaID(__bean.getIncidenciaID());
    this.setEntitat(__bean.getEntitat());
    this.setSolicitantNIF(__bean.getSolicitantNIF());
    this.setEstat(__bean.getEstat());
    this.setFitxerID(__bean.getFitxerID());
    this.setFitxerfirmatID(__bean.getFitxerfirmatID());
    this.setPortafibid(__bean.getPortafibid());
    this.setDestinatariNIF(__bean.getDestinatariNIF());
    this.setDestinatariNom(__bean.getDestinatariNom());
    this.setMissatgePinbal(__bean.getMissatgePinbal());
    // Fitxer
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
    // Fitxer
    this.setFitxerfirmat(FitxerBean.toBean(__bean.getFitxerfirmat()));
	}

	public long getPinfoID() {
		return(pinfoID);
	};
	public void setPinfoID(long _pinfoID_) {
		this.pinfoID = _pinfoID_;
	};

	public java.lang.Long getIncidenciaID() {
		return(incidenciaID);
	};
	public void setIncidenciaID(java.lang.Long _incidenciaID_) {
		this.incidenciaID = _incidenciaID_;
	};

	public java.lang.String getEntitat() {
		return(entitat);
	};
	public void setEntitat(java.lang.String _entitat_) {
		this.entitat = _entitat_;
	};

	public java.lang.String getSolicitantNIF() {
		return(solicitantNIF);
	};
	public void setSolicitantNIF(java.lang.String _solicitantNIF_) {
		this.solicitantNIF = _solicitantNIF_;
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

	public java.lang.String getDestinatariNIF() {
		return(destinatariNIF);
	};
	public void setDestinatariNIF(java.lang.String _destinatariNIF_) {
		this.destinatariNIF = _destinatariNIF_;
	};

	public java.lang.String getDestinatariNom() {
		return(destinatariNom);
	};
	public void setDestinatariNom(java.lang.String _destinatariNom_) {
		this.destinatariNom = _destinatariNom_;
	};

	public java.lang.String getMissatgePinbal() {
		return(missatgePinbal);
	};
	public void setMissatgePinbal(java.lang.String _missatgePinbal_) {
		this.missatgePinbal = _missatgePinbal_;
	};



  // ======================================

  public static PinfoBean toBean(Pinfo __bean) {
    if (__bean == null) { return null;}
    PinfoBean __tmp = new PinfoBean();
    __tmp.setPinfoID(__bean.getPinfoID());
    __tmp.setIncidenciaID(__bean.getIncidenciaID());
    __tmp.setEntitat(__bean.getEntitat());
    __tmp.setSolicitantNIF(__bean.getSolicitantNIF());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setFitxerfirmatID(__bean.getFitxerfirmatID());
    __tmp.setPortafibid(__bean.getPortafibid());
    __tmp.setDestinatariNIF(__bean.getDestinatariNIF());
    __tmp.setDestinatariNom(__bean.getDestinatariNom());
    __tmp.setMissatgePinbal(__bean.getMissatgePinbal());
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
