
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Contacte;


public class ContacteBean implements Contacte {



	long ContacteID;// PK
	java.lang.String nif;
	java.lang.String nom;
	java.lang.String llinatge1;
	java.lang.String llinatge2;
	java.lang.String carrec;
	java.lang.String telefon;
	java.lang.String mail;
	java.lang.String username;
	java.lang.String nombrecompleto;


  /** Constructor Buit */
  public ContacteBean() {
  }

  /** Constructor amb tots els camps  */
  public ContacteBean(long ContacteID , java.lang.String nif , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String carrec , java.lang.String telefon , java.lang.String mail , java.lang.String username , java.lang.String nombrecompleto) {
    this.ContacteID=ContacteID;
    this.nif=nif;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.carrec=carrec;
    this.telefon=telefon;
    this.mail=mail;
    this.username=username;
    this.nombrecompleto=nombrecompleto;
}
  /** Constructor sense valors autoincrementals */
  public ContacteBean(java.lang.String nif , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String carrec , java.lang.String telefon , java.lang.String mail , java.lang.String username , java.lang.String nombrecompleto) {
    this.nif=nif;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.carrec=carrec;
    this.telefon=telefon;
    this.mail=mail;
    this.username=username;
    this.nombrecompleto=nombrecompleto;
}
  /** Constructor dels valors Not Null */
  public ContacteBean(long ContacteID) {
    this.ContacteID=ContacteID;
}
  public ContacteBean(Contacte __bean) {
    this.setContacteID(__bean.getContacteID());
    this.setNif(__bean.getNif());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setCarrec(__bean.getCarrec());
    this.setTelefon(__bean.getTelefon());
    this.setMail(__bean.getMail());
    this.setUsername(__bean.getUsername());
    this.setNombrecompleto(__bean.getNombrecompleto());
	}

	public long getContacteID() {
		return(ContacteID);
	};
	public void setContacteID(long _ContacteID_) {
		this.ContacteID = _ContacteID_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getLlinatge1() {
		return(llinatge1);
	};
	public void setLlinatge1(java.lang.String _llinatge1_) {
		this.llinatge1 = _llinatge1_;
	};

	public java.lang.String getLlinatge2() {
		return(llinatge2);
	};
	public void setLlinatge2(java.lang.String _llinatge2_) {
		this.llinatge2 = _llinatge2_;
	};

	public java.lang.String getCarrec() {
		return(carrec);
	};
	public void setCarrec(java.lang.String _carrec_) {
		this.carrec = _carrec_;
	};

	public java.lang.String getTelefon() {
		return(telefon);
	};
	public void setTelefon(java.lang.String _telefon_) {
		this.telefon = _telefon_;
	};

	public java.lang.String getMail() {
		return(mail);
	};
	public void setMail(java.lang.String _mail_) {
		this.mail = _mail_;
	};

	public java.lang.String getUsername() {
		return(username);
	};
	public void setUsername(java.lang.String _username_) {
		this.username = _username_;
	};

	public java.lang.String getNombrecompleto() {
		return(nombrecompleto);
	};
	public void setNombrecompleto(java.lang.String _nombrecompleto_) {
		this.nombrecompleto = _nombrecompleto_;
	};



  // ======================================

  public static ContacteBean toBean(Contacte __bean) {
    if (__bean == null) { return null;}
    ContacteBean __tmp = new ContacteBean();
    __tmp.setContacteID(__bean.getContacteID());
    __tmp.setNif(__bean.getNif());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setCarrec(__bean.getCarrec());
    __tmp.setTelefon(__bean.getTelefon());
    __tmp.setMail(__bean.getMail());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setNombrecompleto(__bean.getNombrecompleto());
		return __tmp;
	}



}
