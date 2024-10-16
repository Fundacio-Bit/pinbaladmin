package org.fundaciobit.pinbaladmin.model.entity;

public interface PINFO extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPinfoID();
	public void setPinfoID(long _PinfoID_);

	public java.lang.Long getIncidenciaID();
	public void setIncidenciaID(java.lang.Long _IncidenciaID_);

	public java.lang.Long getEstat();
	public void setEstat(java.lang.Long _estat_);

	public java.lang.Long getFitxerID();
	public void setFitxerID(java.lang.Long _fitxerID_);

	public java.lang.Long getFitxerfirmatID();
	public void setFitxerfirmatID(java.lang.Long _fitxerfirmatID_);

	public java.lang.String getPortafibid();
	public void setPortafibid(java.lang.String _portafibid_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();
  // Fitxer
  public <F extends Fitxer> F getFitxerfirmat();


  // ======================================

}
