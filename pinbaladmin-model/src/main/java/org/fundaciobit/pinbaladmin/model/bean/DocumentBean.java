
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Document;


public class DocumentBean implements Document {



	long documentID;// PK
	java.lang.String nom;
	long fitxerOriginalID;
	java.lang.Long fitxerFirmatID;
	java.lang.String notes;


  /** Constructor Buit */
  public DocumentBean() {
  }

  /** Constructor amb tots els camps  */
  public DocumentBean(long documentID , java.lang.String nom , long fitxerOriginalID , java.lang.Long fitxerFirmatID , java.lang.String notes) {
    this.documentID=documentID;
    this.nom=nom;
    this.fitxerOriginalID=fitxerOriginalID;
    this.fitxerFirmatID=fitxerFirmatID;
    this.notes=notes;
}
  /** Constructor sense valors autoincrementals */
  public DocumentBean(java.lang.String nom , long fitxerOriginalID , java.lang.Long fitxerFirmatID , java.lang.String notes) {
    this.nom=nom;
    this.fitxerOriginalID=fitxerOriginalID;
    this.fitxerFirmatID=fitxerFirmatID;
    this.notes=notes;
}
  /** Constructor dels valors Not Null */
  public DocumentBean(long documentID , java.lang.String nom , long fitxerOriginalID) {
    this.documentID=documentID;
    this.nom=nom;
    this.fitxerOriginalID=fitxerOriginalID;
}
  public DocumentBean(Document __bean) {
    this.setDocumentID(__bean.getDocumentID());
    this.setNom(__bean.getNom());
    this.setFitxerOriginalID(__bean.getFitxerOriginalID());
    this.setFitxerFirmatID(__bean.getFitxerFirmatID());
    this.setNotes(__bean.getNotes());
    // Fitxer
    this.setFitxerOriginal(FitxerBean.toBean(__bean.getFitxerOriginal()));
    // Fitxer
    this.setFitxerFirmat(FitxerBean.toBean(__bean.getFitxerFirmat()));
	}

	public long getDocumentID() {
		return(documentID);
	};
	public void setDocumentID(long _documentID_) {
		this.documentID = _documentID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public long getFitxerOriginalID() {
		return(fitxerOriginalID);
	};
	public void setFitxerOriginalID(long _fitxerOriginalID_) {
		this.fitxerOriginalID = _fitxerOriginalID_;
	};

	public java.lang.Long getFitxerFirmatID() {
		return(fitxerFirmatID);
	};
	public void setFitxerFirmatID(java.lang.Long _fitxerFirmatID_) {
		this.fitxerFirmatID = _fitxerFirmatID_;
	};

	public java.lang.String getNotes() {
		return(notes);
	};
	public void setNotes(java.lang.String _notes_) {
		this.notes = _notes_;
	};



  // ======================================

  public static DocumentBean toBean(Document __bean) {
    if (__bean == null) { return null;}
    DocumentBean __tmp = new DocumentBean();
    __tmp.setDocumentID(__bean.getDocumentID());
    __tmp.setNom(__bean.getNom());
    __tmp.setFitxerOriginalID(__bean.getFitxerOriginalID());
    __tmp.setFitxerFirmatID(__bean.getFitxerFirmatID());
    __tmp.setNotes(__bean.getNotes());
    // Fitxer
    __tmp.setFitxerOriginal(FitxerBean.toBean(__bean.getFitxerOriginal()));
    // Fitxer
    __tmp.setFitxerFirmat(FitxerBean.toBean(__bean.getFitxerFirmat()));
		return __tmp;
	}

  protected FitxerBean fitxerOriginal;
  public FitxerBean getFitxerOriginal() {
    return fitxerOriginal;
  }
  public void setFitxerOriginal(FitxerBean __field) {
    this. fitxerOriginal = __field;
  }
  protected FitxerBean fitxerFirmat;
  public FitxerBean getFitxerFirmat() {
    return fitxerFirmat;
  }
  public void setFitxerFirmat(FitxerBean __field) {
    this. fitxerFirmat = __field;
  }


}
