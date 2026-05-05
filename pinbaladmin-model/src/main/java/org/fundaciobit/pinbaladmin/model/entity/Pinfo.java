package org.fundaciobit.pinbaladmin.model.entity;

public interface Pinfo extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPinfoID();
	public void setPinfoID(long _pinfoID_);

	public java.lang.Long getIncidenciaID();
	public void setIncidenciaID(java.lang.Long _incidenciaID_);

	public java.lang.String getEntitat();
	public void setEntitat(java.lang.String _entitat_);

	public java.lang.String getSolicitantNIF();
	public void setSolicitantNIF(java.lang.String _solicitantNIF_);

	public java.lang.String getSolicitantNom();
	public void setSolicitantNom(java.lang.String _solicitantNom_);

	public java.lang.Long getEstat();
	public void setEstat(java.lang.Long _estat_);

	public java.lang.Long getFitxerID();
	public void setFitxerID(java.lang.Long _fitxerID_);

	public java.lang.Long getFitxerfirmatID();
	public void setFitxerfirmatID(java.lang.Long _fitxerfirmatID_);

	public java.lang.String getPortafibid();
	public void setPortafibid(java.lang.String _portafibid_);

	public java.lang.String getDestinatariNIF();
	public void setDestinatariNIF(java.lang.String _destinatariNIF_);

	public java.lang.String getDestinatariNom();
	public void setDestinatariNom(java.lang.String _destinatariNom_);

	public java.lang.String getMissatgePinbal();
	public void setMissatgePinbal(java.lang.String _missatgePinbal_);

	public java.lang.String getLogpPnbal();
	public void setLogpPnbal(java.lang.String _logpPnbal_);

	public java.lang.String getMissatgeSolicitant();
	public void setMissatgeSolicitant(java.lang.String _missatgeSolicitant_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();
  // Fitxer
  public <F extends Fitxer> F getFitxerfirmat();


  // ======================================

}
