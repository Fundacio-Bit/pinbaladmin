package org.fundaciobit.pinbaladmin.model.entity;

public interface IncidenciaTecnica extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getIncidenciaTecnicaID();
	public void setIncidenciaTecnicaID(long _incidenciaTecnicaID_);

	public java.lang.String getTitol();
	public void setTitol(java.lang.String _titol_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public java.sql.Timestamp getDataInici();
	public void setDataInici(java.sql.Timestamp _dataInici_);

	public java.sql.Timestamp getDataFi();
	public void setDataFi(java.sql.Timestamp _dataFi_);

	public int getEstat();
	public void setEstat(int _estat_);

	public java.lang.String getCreador();
	public void setCreador(java.lang.String _creador_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.String getNomEntitat();
	public void setNomEntitat(java.lang.String _nomEntitat_);

	public java.lang.String getContacteNom();
	public void setContacteNom(java.lang.String _contacteNom_);

	public java.lang.String getContacteEmail();
	public void setContacteEmail(java.lang.String _contacteEmail_);

	public java.lang.String getContacteTelefon();
	public void setContacteTelefon(java.lang.String _contacteTelefon_);

	public java.lang.String getCaidIdentificadorConsulta();
	public void setCaidIdentificadorConsulta(java.lang.String _caidIdentificadorConsulta_);

	public java.lang.String getCaidNumeroSeguiment();
	public void setCaidNumeroSeguiment(java.lang.String _caidNumeroSeguiment_);



  // ======================================

}
