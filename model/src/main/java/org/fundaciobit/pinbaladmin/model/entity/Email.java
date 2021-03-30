package org.fundaciobit.pinbaladmin.model.entity;

public interface Email extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEmailID();
	public void setEmailID(long _emailID_);

	public java.lang.String getDestinataris();
	public void setDestinataris(java.lang.String _destinataris_);

	public java.lang.String getSubject();
	public void setSubject(java.lang.String _subject_);

	public java.lang.String getMessage();
	public void setMessage(java.lang.String _message_);

	public java.sql.Timestamp getDataEnviament();
	public void setDataEnviament(java.sql.Timestamp _dataEnviament_);

	public java.lang.String getEnviador();
	public void setEnviador(java.lang.String _enviador_);



  // ======================================

}
