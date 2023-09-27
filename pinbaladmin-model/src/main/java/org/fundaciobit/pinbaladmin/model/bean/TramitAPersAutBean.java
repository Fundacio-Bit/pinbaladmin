
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;


public class TramitAPersAutBean implements TramitAPersAut {



	long persautid;// PK
	long tramitid;
	java.sql.Timestamp datatramit;
	java.lang.String nif;
	java.lang.String mail;
	java.lang.String telefon;
	java.lang.String nom;
	java.lang.String llinatge1;
	java.lang.String llinatge2;


  /** Constructor Buit */
  public TramitAPersAutBean() {
  }

  /** Constructor amb tots els camps  */
  public TramitAPersAutBean(long persautid , long tramitid , java.sql.Timestamp datatramit , java.lang.String nif , java.lang.String mail , java.lang.String telefon , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2) {
    this.persautid=persautid;
    this.tramitid=tramitid;
    this.datatramit=datatramit;
    this.nif=nif;
    this.mail=mail;
    this.telefon=telefon;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
}
  /** Constructor sense valors autoincrementals */
  public TramitAPersAutBean(long tramitid , java.sql.Timestamp datatramit , java.lang.String nif , java.lang.String mail , java.lang.String telefon , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2) {
    this.tramitid=tramitid;
    this.datatramit=datatramit;
    this.nif=nif;
    this.mail=mail;
    this.telefon=telefon;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
}
  public TramitAPersAutBean(TramitAPersAut __bean) {
    this.setPersautid(__bean.getPersautid());
    this.setTramitid(__bean.getTramitid());
    this.setDatatramit(__bean.getDatatramit());
    this.setNif(__bean.getNif());
    this.setMail(__bean.getMail());
    this.setTelefon(__bean.getTelefon());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
	}

	public long getPersautid() {
		return(persautid);
	};
	public void setPersautid(long _persautid_) {
		this.persautid = _persautid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public java.sql.Timestamp getDatatramit() {
		return(datatramit);
	};
	public void setDatatramit(java.sql.Timestamp _datatramit_) {
		this.datatramit = _datatramit_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getMail() {
		return(mail);
	};
	public void setMail(java.lang.String _mail_) {
		this.mail = _mail_;
	};

	public java.lang.String getTelefon() {
		return(telefon);
	};
	public void setTelefon(java.lang.String _telefon_) {
		this.telefon = _telefon_;
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



  // ======================================

  public static TramitAPersAutBean toBean(TramitAPersAut __bean) {
    if (__bean == null) { return null;}
    TramitAPersAutBean __tmp = new TramitAPersAutBean();
    __tmp.setPersautid(__bean.getPersautid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setDatatramit(__bean.getDatatramit());
    __tmp.setNif(__bean.getNif());
    __tmp.setMail(__bean.getMail());
    __tmp.setTelefon(__bean.getTelefon());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
		return __tmp;
	}



}