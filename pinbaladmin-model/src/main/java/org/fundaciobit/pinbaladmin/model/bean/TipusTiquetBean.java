
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.TipusTiquet;


public class TipusTiquetBean implements TipusTiquet {



	long tipusTiquetID;// PK
	java.lang.String nom;


  /** Constructor Buit */
  public TipusTiquetBean() {
  }

  /** Constructor amb tots els camps  */
  public TipusTiquetBean(long tipusTiquetID , java.lang.String nom) {
    this.tipusTiquetID=tipusTiquetID;
    this.nom=nom;
}
  public TipusTiquetBean(TipusTiquet __bean) {
    this.setTipusTiquetID(__bean.getTipusTiquetID());
    this.setNom(__bean.getNom());
	}

	public long getTipusTiquetID() {
		return(tipusTiquetID);
	};
	public void setTipusTiquetID(long _tipusTiquetID_) {
		this.tipusTiquetID = _tipusTiquetID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};



  // ======================================

  public static TipusTiquetBean toBean(TipusTiquet __bean) {
    if (__bean == null) { return null;}
    TipusTiquetBean __tmp = new TipusTiquetBean();
    __tmp.setTipusTiquetID(__bean.getTipusTiquetID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}



}
