
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;


public class TramitJConsentBean implements TramitJConsent {



	long consentid;// PK
	long tramitid;
	java.lang.String consentiment;
	java.lang.String consentimentadjunt;
	java.lang.String urlconsentiment;
	java.lang.Long adjuntID;


  /** Constructor Buit */
  public TramitJConsentBean() {
  }

  /** Constructor amb tots els camps  */
  public TramitJConsentBean(long consentid , long tramitid , java.lang.String consentiment , java.lang.String consentimentadjunt , java.lang.String urlconsentiment , java.lang.Long adjuntID) {
    this.consentid=consentid;
    this.tramitid=tramitid;
    this.consentiment=consentiment;
    this.consentimentadjunt=consentimentadjunt;
    this.urlconsentiment=urlconsentiment;
    this.adjuntID=adjuntID;
}
  /** Constructor sense valors autoincrementals */
  public TramitJConsentBean(long tramitid , java.lang.String consentiment , java.lang.String consentimentadjunt , java.lang.String urlconsentiment , java.lang.Long adjuntID) {
    this.tramitid=tramitid;
    this.consentiment=consentiment;
    this.consentimentadjunt=consentimentadjunt;
    this.urlconsentiment=urlconsentiment;
    this.adjuntID=adjuntID;
}
  /** Constructor dels valors Not Null */
  public TramitJConsentBean(long consentid , long tramitid , java.lang.String consentiment) {
    this.consentid=consentid;
    this.tramitid=tramitid;
    this.consentiment=consentiment;
}
  public TramitJConsentBean(TramitJConsent __bean) {
    this.setConsentid(__bean.getConsentid());
    this.setTramitid(__bean.getTramitid());
    this.setConsentiment(__bean.getConsentiment());
    this.setConsentimentadjunt(__bean.getConsentimentadjunt());
    this.setUrlconsentiment(__bean.getUrlconsentiment());
    this.setAdjuntID(__bean.getAdjuntID());
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

	public java.lang.String getConsentiment() {
		return(consentiment);
	};
	public void setConsentiment(java.lang.String _consentiment_) {
		this.consentiment = _consentiment_;
	};

	public java.lang.String getConsentimentadjunt() {
		return(consentimentadjunt);
	};
	public void setConsentimentadjunt(java.lang.String _consentimentadjunt_) {
		this.consentimentadjunt = _consentimentadjunt_;
	};

	public java.lang.String getUrlconsentiment() {
		return(urlconsentiment);
	};
	public void setUrlconsentiment(java.lang.String _urlconsentiment_) {
		this.urlconsentiment = _urlconsentiment_;
	};

	public java.lang.Long getAdjuntID() {
		return(adjuntID);
	};
	public void setAdjuntID(java.lang.Long _adjuntID_) {
		this.adjuntID = _adjuntID_;
	};



  // ======================================

  public static TramitJConsentBean toBean(TramitJConsent __bean) {
    if (__bean == null) { return null;}
    TramitJConsentBean __tmp = new TramitJConsentBean();
    __tmp.setConsentid(__bean.getConsentid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setConsentiment(__bean.getConsentiment());
    __tmp.setConsentimentadjunt(__bean.getConsentimentadjunt());
    __tmp.setUrlconsentiment(__bean.getUrlconsentiment());
    __tmp.setAdjuntID(__bean.getAdjuntID());
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
