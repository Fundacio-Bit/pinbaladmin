
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.DocumentEntitat;


public class DocumentEntitatBean implements DocumentEntitat {



private static final long serialVersionUID = 92430622L;

	long documentEntitatID;// PK
	java.lang.String titol;
	java.lang.String descripcio;
	long entitatID;
	java.lang.Long fitxerID;
	java.sql.Timestamp dataAlta;


  /** Constructor Buit */
  public DocumentEntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public DocumentEntitatBean(long documentEntitatID , java.lang.String titol , java.lang.String descripcio , long entitatID , java.lang.Long fitxerID , java.sql.Timestamp dataAlta) {
    this.documentEntitatID=documentEntitatID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
    this.fitxerID=fitxerID;
    this.dataAlta=dataAlta;
}
  /** Constructor sense valors autoincrementals */
  public DocumentEntitatBean(java.lang.String titol , java.lang.String descripcio , long entitatID , java.lang.Long fitxerID , java.sql.Timestamp dataAlta) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
    this.fitxerID=fitxerID;
    this.dataAlta=dataAlta;
}
  /** Constructor dels valors Not Null */
  public DocumentEntitatBean(long documentEntitatID , java.lang.String titol , long entitatID , java.sql.Timestamp dataAlta) {
    this.documentEntitatID=documentEntitatID;
    this.titol=titol;
    this.entitatID=entitatID;
    this.dataAlta=dataAlta;
}
  public DocumentEntitatBean(DocumentEntitat __bean) {
    this.setDocumentEntitatID(__bean.getDocumentEntitatID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setEntitatID(__bean.getEntitatID());
    this.setFitxerID(__bean.getFitxerID());
    this.setDataAlta(__bean.getDataAlta());
    // Fitxer
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
	}

	public long getDocumentEntitatID() {
		return(documentEntitatID);
	};
	public void setDocumentEntitatID(long _documentEntitatID_) {
		this.documentEntitatID = _documentEntitatID_;
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

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.Long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(java.lang.Long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public java.sql.Timestamp getDataAlta() {
		return(dataAlta);
	};
	public void setDataAlta(java.sql.Timestamp _dataAlta_) {
		this.dataAlta = _dataAlta_;
	};



  // ======================================

  public static DocumentEntitatBean toBean(DocumentEntitat __bean) {
    if (__bean == null) { return null;}
    DocumentEntitatBean __tmp = new DocumentEntitatBean();
    __tmp.setDocumentEntitatID(__bean.getDocumentEntitatID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setDataAlta(__bean.getDataAlta());
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
