
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Formulari;


public class FormulariBean implements Formulari {



	long formulariid;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	java.lang.Long fitxerID;


  /** Constructor Buit */
  public FormulariBean() {
  }

  /** Constructor amb tots els camps  */
  public FormulariBean(long formulariid , java.lang.String nom , java.lang.String descripcio , java.lang.Long fitxerID) {
    this.formulariid=formulariid;
    this.nom=nom;
    this.descripcio=descripcio;
    this.fitxerID=fitxerID;
}
  /** Constructor sense valors autoincrementals */
  public FormulariBean(java.lang.String nom , java.lang.String descripcio , java.lang.Long fitxerID) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.fitxerID=fitxerID;
}
  public FormulariBean(Formulari __bean) {
    this.setFormulariid(__bean.getFormulariid());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setFitxerID(__bean.getFitxerID());
    // Fitxer
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
	}

	public long getFormulariid() {
		return(formulariid);
	};
	public void setFormulariid(long _formulariid_) {
		this.formulariid = _formulariid_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.Long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(java.lang.Long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};



  // ======================================

  public static FormulariBean toBean(Formulari __bean) {
    if (__bean == null) { return null;}
    FormulariBean __tmp = new FormulariBean();
    __tmp.setFormulariid(__bean.getFormulariid());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setFitxerID(__bean.getFitxerID());
    // Fitxer
    __tmp.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
		return __tmp;
	}

  protected FitxerBean fitxer;
  public FitxerBean getFitxer() {
    return fitxer;
  }
  public void setFitxer(FitxerBean __field) {
    this. fitxer = __field;
  }


}
