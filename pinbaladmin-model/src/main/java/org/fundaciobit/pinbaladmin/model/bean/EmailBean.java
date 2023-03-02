
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Email;


public class EmailBean implements Email {



	long emailID;// PK
	java.sql.Timestamp dataEnviament;
	java.lang.String enviador;
	java.lang.String destinataris;
	java.lang.String subject;
	java.lang.String message;


  /** Constructor Buit */
  public EmailBean() {
  }

  /** Constructor amb tots els camps  */
  public EmailBean(long emailID , java.sql.Timestamp dataEnviament , java.lang.String enviador , java.lang.String destinataris , java.lang.String subject , java.lang.String message) {
    this.emailID=emailID;
    this.dataEnviament=dataEnviament;
    this.enviador=enviador;
    this.destinataris=destinataris;
    this.subject=subject;
    this.message=message;
}
  /** Constructor sense valors autoincrementals */
  public EmailBean(java.sql.Timestamp dataEnviament , java.lang.String enviador , java.lang.String destinataris , java.lang.String subject , java.lang.String message) {
    this.dataEnviament=dataEnviament;
    this.enviador=enviador;
    this.destinataris=destinataris;
    this.subject=subject;
    this.message=message;
}
  public EmailBean(Email __bean) {
    this.setEmailID(__bean.getEmailID());
    this.setDataEnviament(__bean.getDataEnviament());
    this.setEnviador(__bean.getEnviador());
    this.setDestinataris(__bean.getDestinataris());
    this.setSubject(__bean.getSubject());
    this.setMessage(__bean.getMessage());
	}

	public long getEmailID() {
		return(emailID);
	};
	public void setEmailID(long _emailID_) {
		this.emailID = _emailID_;
	};

	public java.sql.Timestamp getDataEnviament() {
		return(dataEnviament);
	};
	public void setDataEnviament(java.sql.Timestamp _dataEnviament_) {
		this.dataEnviament = _dataEnviament_;
	};

	public java.lang.String getEnviador() {
		return(enviador);
	};
	public void setEnviador(java.lang.String _enviador_) {
		this.enviador = _enviador_;
	};

	public java.lang.String getDestinataris() {
		return(destinataris);
	};
	public void setDestinataris(java.lang.String _destinataris_) {
		this.destinataris = _destinataris_;
	};

	public java.lang.String getSubject() {
		return(subject);
	};
	public void setSubject(java.lang.String _subject_) {
		this.subject = _subject_;
	};

	public java.lang.String getMessage() {
		return(message);
	};
	public void setMessage(java.lang.String _message_) {
		this.message = _message_;
	};



  // ======================================

  public static EmailBean toBean(Email __bean) {
    if (__bean == null) { return null;}
    EmailBean __tmp = new EmailBean();
    __tmp.setEmailID(__bean.getEmailID());
    __tmp.setDataEnviament(__bean.getDataEnviament());
    __tmp.setEnviador(__bean.getEnviador());
    __tmp.setDestinataris(__bean.getDestinataris());
    __tmp.setSubject(__bean.getSubject());
    __tmp.setMessage(__bean.getMessage());
		return __tmp;
	}



}
