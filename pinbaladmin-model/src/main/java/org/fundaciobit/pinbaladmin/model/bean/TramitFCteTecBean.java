
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.TramitFCteTec;


public class TramitFCteTecBean implements TramitFCteTec {



	long ctetecid;// PK
	long tramitid;
	java.lang.String nif;
	java.lang.String nom;
	java.lang.String llinatge1;
	java.lang.String llinatge2;
	java.lang.String carrec;
	java.lang.String telefon;
	java.lang.String mail;


  /** Constructor Buit */
  public TramitFCteTecBean() {
  }

  /** Constructor amb tots els camps  */
  public TramitFCteTecBean(long ctetecid , long tramitid , java.lang.String nif , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String carrec , java.lang.String telefon , java.lang.String mail) {
    this.ctetecid=ctetecid;
    this.tramitid=tramitid;
    this.nif=nif;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.carrec=carrec;
    this.telefon=telefon;
    this.mail=mail;
}
  /** Constructor sense valors autoincrementals */
  public TramitFCteTecBean(long tramitid , java.lang.String nif , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String carrec , java.lang.String telefon , java.lang.String mail) {
    this.tramitid=tramitid;
    this.nif=nif;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.carrec=carrec;
    this.telefon=telefon;
    this.mail=mail;
}
  /** Constructor dels valors Not Null */
  public TramitFCteTecBean(long ctetecid , long tramitid , java.lang.String nif , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2) {
    this.ctetecid=ctetecid;
    this.tramitid=tramitid;
    this.nif=nif;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
}
  public TramitFCteTecBean(TramitFCteTec __bean) {
    this.setCtetecid(__bean.getCtetecid());
    this.setTramitid(__bean.getTramitid());
    this.setNif(__bean.getNif());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setCarrec(__bean.getCarrec());
    this.setTelefon(__bean.getTelefon());
    this.setMail(__bean.getMail());
	}

	public long getCtetecid() {
		return(ctetecid);
	};
	public void setCtetecid(long _ctetecid_) {
		this.ctetecid = _ctetecid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
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



  // ======================================

  public static TramitFCteTecBean toBean(TramitFCteTec __bean) {
    if (__bean == null) { return null;}
    TramitFCteTecBean __tmp = new TramitFCteTecBean();
    __tmp.setCtetecid(__bean.getCtetecid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setNif(__bean.getNif());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setCarrec(__bean.getCarrec());
    __tmp.setTelefon(__bean.getTelefon());
    __tmp.setMail(__bean.getMail());
		return __tmp;
	}



}
