package org.fundaciobit.pinbaladmin.model.entity;

public interface SolicitudServei extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getId();
	public void setId(long _id_);

	public long getSolicitudID();
	public void setSolicitudID(long _solicitudID_);

	public long getServeiID();
	public void setServeiID(long _serveiID_);

	public java.lang.Long getEstatSolicitudServeiID();
	public void setEstatSolicitudServeiID(java.lang.Long _estatSolicitudServeiID_);

	public java.lang.String getNormaLegal();
	public void setNormaLegal(java.lang.String _normaLegal_);

	public java.lang.String getEnllazNormaLegal();
	public void setEnllazNormaLegal(java.lang.String _enllazNormaLegal_);

	public java.lang.String getArticles();
	public void setArticles(java.lang.String _articles_);

	public java.lang.String getTipusConsentiment();
	public void setTipusConsentiment(java.lang.String _tipusConsentiment_);

	public java.lang.String getConsentiment();
	public void setConsentiment(java.lang.String _consentiment_);

	public java.lang.String getEnllazConsentiment();
	public void setEnllazConsentiment(java.lang.String _enllazConsentiment_);

	public java.lang.String getNotes();
	public void setNotes(java.lang.String _notes_);

	public java.lang.String getCaduca();
	public void setCaduca(java.lang.String _caduca_);

	public java.lang.String getFechaCaduca();
	public void setFechaCaduca(java.lang.String _fechaCaduca_);



  // ======================================

}
