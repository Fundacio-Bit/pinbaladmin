package org.fundaciobit.pinbaladmin.model.entity;

public interface TramitJConsent extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getConsentid();
	public void setConsentid(long _consentid_);

	public long getTramitid();
	public void setTramitid(long _tramitid_);

	public java.lang.Long getAdjuntID();
	public void setAdjuntID(java.lang.Long _adjuntID_);

  // Fitxer
  public <F extends Fitxer> F getAdjunt();


  // ======================================

}
