package org.fundaciobit.pinbaladmin.model.entity;

public interface Servei extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getServeiID();
	public void setServeiID(long _serveiID_);

	public java.lang.String getCodi();
	public void setCodi(java.lang.String _codi_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public java.lang.Long getFormulariID();
	public void setFormulariID(java.lang.Long _formulariID_);

	public java.lang.Long getEntitatServeiID();
	public void setEntitatServeiID(java.lang.Long _entitatServeiID_);

	public java.lang.Long getEstatServeiID();
	public void setEstatServeiID(java.lang.Long _estatServeiID_);

	public int getTipusConsentiment();
	public void setTipusConsentiment(int _tipusConsentiment_);

	public boolean isOcult();
	public void setOcult(boolean _ocult_);



  // ======================================

}
