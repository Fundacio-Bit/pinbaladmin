package org.fundaciobit.pinbaladmin.model.entity;

public interface Solicitud extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getSolicitudID();
	public void setSolicitudID(long _solicitudID_);

	public java.lang.String getProcedimentCodi();
	public void setProcedimentCodi(java.lang.String _procedimentCodi_);

	public java.lang.String getCodiDescriptiu();
	public void setCodiDescriptiu(java.lang.String _codiDescriptiu_);

	public java.lang.String getProcedimentNom();
	public void setProcedimentNom(java.lang.String _procedimentNom_);

	public java.lang.Long getEstatID();
	public void setEstatID(java.lang.Long _estatID_);

	public java.lang.String getTicketAssociat();
	public void setTicketAssociat(java.lang.String _ticketAssociat_);

	public java.lang.String getTicketNumeroSeguiment();
	public void setTicketNumeroSeguiment(java.lang.String _ticketNumeroSeguiment_);

	public java.lang.Long getDepartamentID();
	public void setDepartamentID(java.lang.Long _departamentID_);

	public java.lang.String getEntitatEstatal();
	public void setEntitatEstatal(java.lang.String _entitatEstatal_);

	public java.lang.String getPinfo();
	public void setPinfo(java.lang.String _pinfo_);

	public java.sql.Timestamp getDataInici();
	public void setDataInici(java.sql.Timestamp _dataInici_);

	public java.sql.Timestamp getDataFi();
	public void setDataFi(java.sql.Timestamp _dataFi_);

	public java.lang.String getPersonaContacte();
	public void setPersonaContacte(java.lang.String _personaContacte_);

	public java.lang.String getPersonaContacteEmail();
	public void setPersonaContacteEmail(java.lang.String _personaContacteEmail_);

	public java.lang.String getNotes();
	public void setNotes(java.lang.String _notes_);

	public java.lang.Long getDocumentSolicitudID();
	public void setDocumentSolicitudID(java.lang.Long _documentSolicitudID_);

	public java.lang.Long getSolicitudXmlID();
	public void setSolicitudXmlID(java.lang.Long _solicitudXmlID_);

	public boolean isFirmatDocSolicitud();
	public void setFirmatDocSolicitud(boolean _firmatDocSolicitud_);

	public boolean isProduccio();
	public void setProduccio(boolean _produccio_);

	public java.lang.String getCreador();
	public void setCreador(java.lang.String _creador_);

  // Fitxer
  public <F extends Fitxer> F getDocumentSolicitud();
  // Fitxer
  public <F extends Fitxer> F getSolicitudXml();


  // ======================================

}
