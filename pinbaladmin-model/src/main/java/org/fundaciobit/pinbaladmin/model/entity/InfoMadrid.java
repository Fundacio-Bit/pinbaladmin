package org.fundaciobit.pinbaladmin.model.entity;

public interface InfoMadrid extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getInfoMadridID();
	public void setInfoMadridID(long _infoMadridID_);

	public java.lang.String getCodi();
	public void setCodi(java.lang.String _codi_);

	public long getEstatProcediment();
	public void setEstatProcediment(long _estatProcediment_);

	public long getEstatAutoritzacio();
	public void setEstatAutoritzacio(long _estatAutoritzacio_);

	public java.lang.String getMissatge();
	public void setMissatge(java.lang.String _missatge_);

	public java.lang.String getConsulta();
	public void setConsulta(java.lang.String _consulta_);

	public java.lang.String getTitularNom();
	public void setTitularNom(java.lang.String _titularNom_);

	public java.lang.String getTitularNif();
	public void setTitularNif(java.lang.String _titularNif_);

	public java.sql.Timestamp getDataAutoritzacio();
	public void setDataAutoritzacio(java.sql.Timestamp _dataAutoritzacio_);

	public java.sql.Timestamp getDataEnviament();
	public void setDataEnviament(java.sql.Timestamp _dataEnviament_);

	public long getIntents();
	public void setIntents(long _intents_);



  // ======================================

}
