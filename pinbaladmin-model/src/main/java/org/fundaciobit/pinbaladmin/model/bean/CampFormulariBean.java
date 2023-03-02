
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.CampFormulari;


public class CampFormulariBean implements CampFormulari {



	long campFormulariID;// PK
	java.lang.String nom;
	java.lang.String campPDF;
	long formulariID;


  /** Constructor Buit */
  public CampFormulariBean() {
  }

  /** Constructor amb tots els camps  */
  public CampFormulariBean(long campFormulariID , java.lang.String nom , java.lang.String campPDF , long formulariID) {
    this.campFormulariID=campFormulariID;
    this.nom=nom;
    this.campPDF=campPDF;
    this.formulariID=formulariID;
}
  /** Constructor sense valors autoincrementals */
  public CampFormulariBean(java.lang.String nom , java.lang.String campPDF , long formulariID) {
    this.nom=nom;
    this.campPDF=campPDF;
    this.formulariID=formulariID;
}
  public CampFormulariBean(CampFormulari __bean) {
    this.setCampFormulariID(__bean.getCampFormulariID());
    this.setNom(__bean.getNom());
    this.setCampPDF(__bean.getCampPDF());
    this.setFormulariID(__bean.getFormulariID());
	}

	public long getCampFormulariID() {
		return(campFormulariID);
	};
	public void setCampFormulariID(long _campFormulariID_) {
		this.campFormulariID = _campFormulariID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getCampPDF() {
		return(campPDF);
	};
	public void setCampPDF(java.lang.String _campPDF_) {
		this.campPDF = _campPDF_;
	};

	public long getFormulariID() {
		return(formulariID);
	};
	public void setFormulariID(long _formulariID_) {
		this.formulariID = _formulariID_;
	};



  // ======================================

  public static CampFormulariBean toBean(CampFormulari __bean) {
    if (__bean == null) { return null;}
    CampFormulariBean __tmp = new CampFormulariBean();
    __tmp.setCampFormulariID(__bean.getCampFormulariID());
    __tmp.setNom(__bean.getNom());
    __tmp.setCampPDF(__bean.getCampPDF());
    __tmp.setFormulariID(__bean.getFormulariID());
		return __tmp;
	}



}
