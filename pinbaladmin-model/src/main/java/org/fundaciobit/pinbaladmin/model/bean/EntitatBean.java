
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Entitat;


public class EntitatBean implements Entitat {



	long entitatID;// PK
	java.lang.String nom;
	java.lang.String personaContacte;
	java.lang.String CIF;
	long grupEntitatID;
	boolean convenipmsbae;
	java.lang.String dir3;


  /** Constructor Buit */
  public EntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public EntitatBean(long entitatID , java.lang.String nom , java.lang.String personaContacte , java.lang.String CIF , long grupEntitatID , boolean convenipmsbae , java.lang.String dir3) {
    this.entitatID=entitatID;
    this.nom=nom;
    this.personaContacte=personaContacte;
    this.CIF=CIF;
    this.grupEntitatID=grupEntitatID;
    this.convenipmsbae=convenipmsbae;
    this.dir3=dir3;
}
  /** Constructor sense valors autoincrementals */
  public EntitatBean(java.lang.String nom , java.lang.String personaContacte , java.lang.String CIF , long grupEntitatID , boolean convenipmsbae , java.lang.String dir3) {
    this.nom=nom;
    this.personaContacte=personaContacte;
    this.CIF=CIF;
    this.grupEntitatID=grupEntitatID;
    this.convenipmsbae=convenipmsbae;
    this.dir3=dir3;
}
  /** Constructor dels valors Not Null */
  public EntitatBean(long entitatID , java.lang.String nom , java.lang.String CIF , long grupEntitatID , boolean convenipmsbae) {
    this.entitatID=entitatID;
    this.nom=nom;
    this.CIF=CIF;
    this.grupEntitatID=grupEntitatID;
    this.convenipmsbae=convenipmsbae;
}
  public EntitatBean(Entitat __bean) {
    this.setEntitatID(__bean.getEntitatID());
    this.setNom(__bean.getNom());
    this.setPersonaContacte(__bean.getPersonaContacte());
    this.setCIF(__bean.getCIF());
    this.setGrupEntitatID(__bean.getGrupEntitatID());
    this.setConvenipmsbae(__bean.isConvenipmsbae());
    this.setDir3(__bean.getDir3());
	}

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getPersonaContacte() {
		return(personaContacte);
	};
	public void setPersonaContacte(java.lang.String _personaContacte_) {
		this.personaContacte = _personaContacte_;
	};

	public java.lang.String getCIF() {
		return(CIF);
	};
	public void setCIF(java.lang.String _CIF_) {
		this.CIF = _CIF_;
	};

	public long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
	};

	public boolean isConvenipmsbae() {
		return(convenipmsbae);
	};
	public void setConvenipmsbae(boolean _convenipmsbae_) {
		this.convenipmsbae = _convenipmsbae_;
	};

	public java.lang.String getDir3() {
		return(dir3);
	};
	public void setDir3(java.lang.String _dir3_) {
		this.dir3 = _dir3_;
	};



  // ======================================

  public static EntitatBean toBean(Entitat __bean) {
    if (__bean == null) { return null;}
    EntitatBean __tmp = new EntitatBean();
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setNom(__bean.getNom());
    __tmp.setPersonaContacte(__bean.getPersonaContacte());
    __tmp.setCIF(__bean.getCIF());
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
    __tmp.setConvenipmsbae(__bean.isConvenipmsbae());
    __tmp.setDir3(__bean.getDir3());
		return __tmp;
	}



}
