
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Area;


public class AreaBean implements Area {



	long areaID;// PK
	java.lang.String nom;
	long entitatID;


  /** Constructor Buit */
  public AreaBean() {
  }

  /** Constructor amb tots els camps  */
  public AreaBean(long areaID , java.lang.String nom , long entitatID) {
    this.areaID=areaID;
    this.nom=nom;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public AreaBean(java.lang.String nom , long entitatID) {
    this.nom=nom;
    this.entitatID=entitatID;
}
  public AreaBean(Area __bean) {
    this.setAreaID(__bean.getAreaID());
    this.setNom(__bean.getNom());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getAreaID() {
		return(areaID);
	};
	public void setAreaID(long _areaID_) {
		this.areaID = _areaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  // ======================================

  public static AreaBean toBean(Area __bean) {
    if (__bean == null) { return null;}
    AreaBean __tmp = new AreaBean();
    __tmp.setAreaID(__bean.getAreaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}



}
