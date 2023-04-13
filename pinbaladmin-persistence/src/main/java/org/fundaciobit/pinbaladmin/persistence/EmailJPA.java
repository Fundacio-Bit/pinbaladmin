
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;


@Entity(name = "EmailJPA")
@Table(name = "pad_email" , indexes = { 
        @Index(name="pad_email_pk_i", columnList = "emailid")})
@SequenceGenerator(name="EMAIL_SEQ", sequenceName="pad_email_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EmailJPA implements Email {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EMAIL_SEQ")
    @Column(name="emailid",nullable = false,length = 19)
    long emailID;

    @Column(name="dataenviament",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataEnviament;

    @Column(name="enviador",nullable = false,length = 255)
    java.lang.String enviador;

    @Column(name="destinataris",nullable = false,length = 6000)
    java.lang.String destinataris;

    @Column(name="subject",nullable = false,length = 255)
    java.lang.String subject;

    @Column(name="message",nullable = false,length = 6000)
    java.lang.String message;



  /** Constructor Buit */
  public EmailJPA() {
  }

  /** Constructor amb tots els camps  */
  public EmailJPA(long emailID , java.sql.Timestamp dataEnviament , java.lang.String enviador , java.lang.String destinataris , java.lang.String subject , java.lang.String message) {
    this.emailID=emailID;
    this.dataEnviament=dataEnviament;
    this.enviador=enviador;
    this.destinataris=destinataris;
    this.subject=subject;
    this.message=message;
}
  /** Constructor sense valors autoincrementals */
  public EmailJPA(java.sql.Timestamp dataEnviament , java.lang.String enviador , java.lang.String destinataris , java.lang.String subject , java.lang.String message) {
    this.dataEnviament=dataEnviament;
    this.enviador=enviador;
    this.destinataris=destinataris;
    this.subject=subject;
    this.message=message;
}
  public EmailJPA(Email __bean) {
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Email) {
      Email __instance = (Email)__obj;
      __result = true;
      __result = __result && (this.getEmailID() == __instance.getEmailID()) ;
    } else {
      __result = false;
    }
    return __result;
  }


 // ---------------  STATIC METHODS ------------------
  public static EmailJPA toJPA(Email __bean) {
    if (__bean == null) { return null;}
    EmailJPA __tmp = new EmailJPA();
    __tmp.setEmailID(__bean.getEmailID());
    __tmp.setDataEnviament(__bean.getDataEnviament());
    __tmp.setEnviador(__bean.getEnviador());
    __tmp.setDestinataris(__bean.getDestinataris());
    __tmp.setSubject(__bean.getSubject());
    __tmp.setMessage(__bean.getMessage());
		return __tmp;
	}


  public static EmailJPA copyJPA(EmailJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EmailJPA> copyJPA(java.util.Set<EmailJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EmailJPA> __tmpSet = (java.util.Set<EmailJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EmailJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EmailJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EmailJPA copyJPA(EmailJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EmailJPA __tmp = (EmailJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}