
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.DocumentCedent;


public class DocumentCedentBean implements DocumentCedent {



	long documentCedentID;// PK
	java.lang.String titol;
	java.lang.String descripcio;
	long entitatServeiID;
	java.sql.Timestamp dataCreacio;
	java.lang.Long fitxerID;


  /** Constructor Buit */
  public DocumentCedentBean() {
  }

  /** Constructor amb tots els camps  */
  public DocumentCedentBean(long documentCedentID , java.lang.String titol , java.lang.String descripcio , long entitatServeiID , java.sql.Timestamp dataCreacio , java.lang.Long fitxerID) {
    this.documentCedentID=documentCedentID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.entitatServeiID=entitatServeiID;
    this.dataCreacio=dataCreacio;
    this.fitxerID=fitxerID;
}
  /** Constructor sense valors autoincrementals */
  public DocumentCedentBean(java.lang.String titol , java.lang.String descripcio , long entitatServeiID , java.sql.Timestamp dataCreacio , java.lang.Long fitxerID) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.entitatServeiID=entitatServeiID;
    this.dataCreacio=dataCreacio;
    this.fitxerID=fitxerID;
}
  /** Constructor dels valors Not Null */
  public DocumentCedentBean(long documentCedentID , java.lang.String titol , long entitatServeiID , java.sql.Timestamp dataCreacio) {
    this.documentCedentID=documentCedentID;
    this.titol=titol;
    this.entitatServeiID=entitatServeiID;
    this.dataCreacio=dataCreacio;
}
  public DocumentCedentBean(DocumentCedent __bean) {
    this.setDocumentCedentID(__bean.getDocumentCedentID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setEntitatServeiID(__bean.getEntitatServeiID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setFitxerID(__bean.getFitxerID());
    // Fitxer
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
	}

	public long getDocumentCedentID() {
		return(documentCedentID);
	};
	public void setDocumentCedentID(long _documentCedentID_) {
		this.documentCedentID = _documentCedentID_;
	};

	public java.lang.String getTitol() {
		return(titol);
	};
	public void setTitol(java.lang.String _titol_) {
		this.titol = _titol_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public long getEntitatServeiID() {
		return(entitatServeiID);
	};
	public void setEntitatServeiID(long _entitatServeiID_) {
		this.entitatServeiID = _entitatServeiID_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.lang.Long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(java.lang.Long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};



  // ======================================

  public static DocumentCedentBean toBean(DocumentCedent __bean) {
    if (__bean == null) { return null;}
    DocumentCedentBean __tmp = new DocumentCedentBean();
    __tmp.setDocumentCedentID(__bean.getDocumentCedentID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEntitatServeiID(__bean.getEntitatServeiID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
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
