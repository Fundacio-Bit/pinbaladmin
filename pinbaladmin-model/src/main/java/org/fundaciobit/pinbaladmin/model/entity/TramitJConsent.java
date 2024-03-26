package org.fundaciobit.pinbaladmin.model.entity;

public interface TramitJConsent extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getConsentid();
	public void setConsentid(long _consentid_);

	public long getTramitid();
	public void setTramitid(long _tramitid_);

	public java.lang.String getConsentiment();
	public void setConsentiment(java.lang.String _consentiment_);

	public java.lang.String getConsentimentadjunt();
	public void setConsentimentadjunt(java.lang.String _consentimentadjunt_);

	public java.lang.String getUrlconsentiment();
	public void setUrlconsentiment(java.lang.String _urlconsentiment_);

	public java.lang.Long getAdjuntID();
	public void setAdjuntID(java.lang.Long _adjuntID_);

  // Fitxer
  public <F extends Fitxer> F getAdjunt();


  // ======================================

}
