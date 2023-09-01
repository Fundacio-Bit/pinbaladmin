package org.fundaciobit.pinbaladmin.model.entity;

public interface Entitat extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEntitatID();
	public void setEntitatID(long _entitatID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getPersonaContacte();
	public void setPersonaContacte(java.lang.String _personaContacte_);

	public java.lang.String getCIF();
	public void setCIF(java.lang.String _CIF_);

	public long getGrupEntitatID();
	public void setGrupEntitatID(long _grupEntitatID_);

	public boolean isConvenipmsbae();
	public void setConvenipmsbae(boolean _convenipmsbae_);

	public java.lang.String getDir3();
	public void setDir3(java.lang.String _dir3_);



  // ======================================

}
