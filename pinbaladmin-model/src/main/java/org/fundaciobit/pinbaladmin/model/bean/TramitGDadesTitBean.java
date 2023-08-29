
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.TramitGDadesTit;


public class TramitGDadesTitBean implements TramitGDadesTit {



	long dadestitid;// PK
	long tramitid;
	java.lang.String nif;
	java.lang.String nom;
	java.lang.String llinatge1;
	java.lang.String llinatge2;
	java.lang.String carrec;


  /** Constructor Buit */
  public TramitGDadesTitBean() {
  }

  /** Constructor amb tots els camps  */
  public TramitGDadesTitBean(long dadestitid , long tramitid , java.lang.String nif , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String carrec) {
    this.dadestitid=dadestitid;
    this.tramitid=tramitid;
    this.nif=nif;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.carrec=carrec;
}
  /** Constructor sense valors autoincrementals */
  public TramitGDadesTitBean(long tramitid , java.lang.String nif , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String carrec) {
    this.tramitid=tramitid;
    this.nif=nif;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.carrec=carrec;
}
  public TramitGDadesTitBean(TramitGDadesTit __bean) {
    this.setDadestitid(__bean.getDadestitid());
    this.setTramitid(__bean.getTramitid());
    this.setNif(__bean.getNif());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setCarrec(__bean.getCarrec());
	}

	public long getDadestitid() {
		return(dadestitid);
	};
	public void setDadestitid(long _dadestitid_) {
		this.dadestitid = _dadestitid_;
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



  // ======================================

  public static TramitGDadesTitBean toBean(TramitGDadesTit __bean) {
    if (__bean == null) { return null;}
    TramitGDadesTitBean __tmp = new TramitGDadesTitBean();
    __tmp.setDadestitid(__bean.getDadestitid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setNif(__bean.getNif());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setCarrec(__bean.getCarrec());
		return __tmp;
	}



}
