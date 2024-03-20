
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;


public class TramitJConsentBean implements TramitJConsent {



	long consentid;// PK
	long tramitid;
	java.lang.Long adjuntID;
	java.lang.String consentiment;
	java.lang.String urlconsentiment;
	java.lang.String consentimentadjunt;


  /** Constructor Buit */
  public TramitJConsentBean() {
  }

  /** Constructor amb tots els camps  */
  public TramitJConsentBean(long consentid , long tramitid , java.lang.Long adjuntID , java.lang.String consentiment , java.lang.String urlconsentiment , java.lang.String consentimentadjunt) {
    this.consentid=consentid;
    this.tramitid=tramitid;
    this.adjuntID=adjuntID;
    this.consentiment=consentiment;
    this.urlconsentiment=urlconsentiment;
    this.consentimentadjunt=consentimentadjunt;
}
  /** Constructor sense valors autoincrementals */
  public TramitJConsentBean(long tramitid , java.lang.Long adjuntID , java.lang.String consentiment , java.lang.String urlconsentiment , java.lang.String consentimentadjunt) {
    this.tramitid=tramitid;
    this.adjuntID=adjuntID;
    this.consentiment=consentiment;
    this.urlconsentiment=urlconsentiment;
    this.consentimentadjunt=consentimentadjunt;
}
  /** Constructor dels valors Not Null */
  public TramitJConsentBean(long consentid , long tramitid , java.lang.Long adjuntID) {
    this.consentid=consentid;
    this.tramitid=tramitid;
    this.adjuntID=adjuntID;
}
  public TramitJConsentBean(TramitJConsent __bean) {
    this.setConsentid(__bean.getConsentid());
    this.setTramitid(__bean.getTramitid());
    this.setAdjuntID(__bean.getAdjuntID());
    this.setConsentiment(__bean.getConsentiment());
    this.setUrlconsentiment(__bean.getUrlconsentiment());
    this.setConsentimentadjunt(__bean.getConsentimentadjunt());
    // Fitxer
    this.setAdjunt(FitxerBean.toBean(__bean.getAdjunt()));
	}

	public long getConsentid() {
		return(consentid);
	};
	public void setConsentid(long _consentid_) {
		this.consentid = _consentid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public java.lang.Long getAdjuntID() {
		return(adjuntID);
	};
	public void setAdjuntID(java.lang.Long _adjuntID_) {
		this.adjuntID = _adjuntID_;
	};

	public java.lang.String getConsentiment() {
		return(consentiment);
	};
	public void setConsentiment(java.lang.String _consentiment_) {
		this.consentiment = _consentiment_;
	};

	public java.lang.String getUrlconsentiment() {
		return(urlconsentiment);
	};
	public void setUrlconsentiment(java.lang.String _urlconsentiment_) {
		this.urlconsentiment = _urlconsentiment_;
	};

	public java.lang.String getConsentimentadjunt() {
		return(consentimentadjunt);
	};
	public void setConsentimentadjunt(java.lang.String _consentimentadjunt_) {
		this.consentimentadjunt = _consentimentadjunt_;
	};



  // ======================================

  public static TramitJConsentBean toBean(TramitJConsent __bean) {
    if (__bean == null) { return null;}
    TramitJConsentBean __tmp = new TramitJConsentBean();
    __tmp.setConsentid(__bean.getConsentid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setAdjuntID(__bean.getAdjuntID());
    __tmp.setConsentiment(__bean.getConsentiment());
    __tmp.setUrlconsentiment(__bean.getUrlconsentiment());
    __tmp.setConsentimentadjunt(__bean.getConsentimentadjunt());
    // Fitxer
    __tmp.setAdjunt(FitxerBean.toBean(__bean.getAdjunt()));
		return __tmp;
	}

  protected FitxerBean adjunt;
  public FitxerBean getAdjunt() {
    return adjunt;
  }
  public void setAdjunt(FitxerBean __field) {
    this. adjunt = __field;
  }


}
