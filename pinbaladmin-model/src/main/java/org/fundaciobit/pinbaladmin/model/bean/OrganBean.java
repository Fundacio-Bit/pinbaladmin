
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Organ;


public class OrganBean implements Organ {



	long organid;// PK
	java.lang.String nom;
	java.lang.String dir3;
	java.lang.String dir3pare;
	java.lang.Long entitatid;


  /** Constructor Buit */
  public OrganBean() {
  }

  /** Constructor amb tots els camps  */
  public OrganBean(long organid , java.lang.String nom , java.lang.String dir3 , java.lang.String dir3pare , java.lang.Long entitatid) {
    this.organid=organid;
    this.nom=nom;
    this.dir3=dir3;
    this.dir3pare=dir3pare;
    this.entitatid=entitatid;
}
  /** Constructor sense valors autoincrementals */
  public OrganBean(java.lang.String nom , java.lang.String dir3 , java.lang.String dir3pare , java.lang.Long entitatid) {
    this.nom=nom;
    this.dir3=dir3;
    this.dir3pare=dir3pare;
    this.entitatid=entitatid;
}
  /** Constructor dels valors Not Null */
  public OrganBean(long organid , java.lang.String nom , java.lang.String dir3) {
    this.organid=organid;
    this.nom=nom;
    this.dir3=dir3;
}
  public OrganBean(Organ __bean) {
    this.setOrganid(__bean.getOrganid());
    this.setNom(__bean.getNom());
    this.setDir3(__bean.getDir3());
    this.setDir3pare(__bean.getDir3pare());
    this.setEntitatid(__bean.getEntitatid());
	}

	public long getOrganid() {
		return(organid);
	};
	public void setOrganid(long _organid_) {
		this.organid = _organid_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDir3() {
		return(dir3);
	};
	public void setDir3(java.lang.String _dir3_) {
		this.dir3 = _dir3_;
	};

	public java.lang.String getDir3pare() {
		return(dir3pare);
	};
	public void setDir3pare(java.lang.String _dir3pare_) {
		this.dir3pare = _dir3pare_;
	};

	public java.lang.Long getEntitatid() {
		return(entitatid);
	};
	public void setEntitatid(java.lang.Long _entitatid_) {
		this.entitatid = _entitatid_;
	};



  // ======================================

  public static OrganBean toBean(Organ __bean) {
    if (__bean == null) { return null;}
    OrganBean __tmp = new OrganBean();
    __tmp.setOrganid(__bean.getOrganid());
    __tmp.setNom(__bean.getNom());
    __tmp.setDir3(__bean.getDir3());
    __tmp.setDir3pare(__bean.getDir3pare());
    __tmp.setEntitatid(__bean.getEntitatid());
		return __tmp;
	}



}
