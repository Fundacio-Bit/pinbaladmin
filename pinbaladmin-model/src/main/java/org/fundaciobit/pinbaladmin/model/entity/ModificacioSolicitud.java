package org.fundaciobit.pinbaladmin.model.entity;

public interface ModificacioSolicitud extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getModsoliID();
	public void setModsoliID(long _modsoliID_);

	public long getSolicitudID();
	public void setSolicitudID(long _solicitudID_);

	public java.lang.String getProcedimentCodi();
	public void setProcedimentCodi(java.lang.String _procedimentCodi_);

	public java.lang.String getProcedimentNom();
	public void setProcedimentNom(java.lang.String _procedimentNom_);

	public java.lang.String getCodiSiaNou();
	public void setCodiSiaNou(java.lang.String _codiSiaNou_);

	public java.lang.Long getEstatID();
	public void setEstatID(java.lang.Long _estatID_);

	public java.sql.Timestamp getDataInici();
	public void setDataInici(java.sql.Timestamp _dataInici_);

	public java.sql.Timestamp getDataFi();
	public void setDataFi(java.sql.Timestamp _dataFi_);

	public java.lang.String getNotes();
	public void setNotes(java.lang.String _notes_);

	public java.lang.Long getOrganID();
	public void setOrganID(java.lang.Long _organID_);

	public java.lang.String getResponsableProcNom();
	public void setResponsableProcNom(java.lang.String _responsableProcNom_);

	public java.lang.String getResponsableProceMail();
	public void setResponsableProceMail(java.lang.String _responsableProceMail_);

	public java.lang.String getConsentiment();
	public void setConsentiment(java.lang.String _consentiment_);

	public java.lang.Long getDoCconsentimentID();
	public void setDoCconsentimentID(java.lang.Long _doCconsentimentID_);

	public java.lang.String getSolicitantNom();
	public void setSolicitantNom(java.lang.String _solicitantNom_);

	public java.lang.String getSolicitantNif();
	public void setSolicitantNif(java.lang.String _solicitantNif_);

	public java.lang.String getSolicitantMail();
	public void setSolicitantMail(java.lang.String _solicitantMail_);

	public java.lang.String getSolicitantUsername();
	public void setSolicitantUsername(java.lang.String _solicitantUsername_);

	public java.lang.Long getEstatModificacio();
	public void setEstatModificacio(java.lang.Long _estatModificacio_);

  // Fitxer
  public <F extends Fitxer> F getDoCconsentiment();


  // ======================================

}
