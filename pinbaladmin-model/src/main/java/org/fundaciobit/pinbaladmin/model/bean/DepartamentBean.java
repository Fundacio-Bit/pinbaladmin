
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Departament;


public class DepartamentBean implements Departament {



	long departamentID;// PK
	java.lang.String nom;
	long areaID;


  /** Constructor Buit */
  public DepartamentBean() {
  }

  /** Constructor amb tots els camps  */
  public DepartamentBean(long departamentID , java.lang.String nom , long areaID) {
    this.departamentID=departamentID;
    this.nom=nom;
    this.areaID=areaID;
}
  /** Constructor sense valors autoincrementals */
  public DepartamentBean(java.lang.String nom , long areaID) {
    this.nom=nom;
    this.areaID=areaID;
}
  public DepartamentBean(Departament __bean) {
    this.setDepartamentID(__bean.getDepartamentID());
    this.setNom(__bean.getNom());
    this.setAreaID(__bean.getAreaID());
	}

	public long getDepartamentID() {
		return(departamentID);
	};
	public void setDepartamentID(long _departamentID_) {
		this.departamentID = _departamentID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public long getAreaID() {
		return(areaID);
	};
	public void setAreaID(long _areaID_) {
		this.areaID = _areaID_;
	};



  // ======================================

  public static DepartamentBean toBean(Departament __bean) {
    if (__bean == null) { return null;}
    DepartamentBean __tmp = new DepartamentBean();
    __tmp.setDepartamentID(__bean.getDepartamentID());
    __tmp.setNom(__bean.getNom());
    __tmp.setAreaID(__bean.getAreaID());
		return __tmp;
	}



}
