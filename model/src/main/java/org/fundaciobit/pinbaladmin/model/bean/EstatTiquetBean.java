
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.EstatTiquet;


public class EstatTiquetBean implements EstatTiquet {



private static final long serialVersionUID = -692519279L;

	long estatTiquetID;// PK
	java.lang.String nom;


  /** Constructor Buit */
  public EstatTiquetBean() {
  }

  /** Constructor amb tots els camps  */
  public EstatTiquetBean(long estatTiquetID , java.lang.String nom) {
    this.estatTiquetID=estatTiquetID;
    this.nom=nom;
}
  public EstatTiquetBean(EstatTiquet __bean) {
    this.setEstatTiquetID(__bean.getEstatTiquetID());
    this.setNom(__bean.getNom());
	}

	public long getEstatTiquetID() {
		return(estatTiquetID);
	};
	public void setEstatTiquetID(long _estatTiquetID_) {
		this.estatTiquetID = _estatTiquetID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};



  // ======================================

  public static EstatTiquetBean toBean(EstatTiquet __bean) {
    if (__bean == null) { return null;}
    EstatTiquetBean __tmp = new EstatTiquetBean();
    __tmp.setEstatTiquetID(__bean.getEstatTiquetID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}



}
