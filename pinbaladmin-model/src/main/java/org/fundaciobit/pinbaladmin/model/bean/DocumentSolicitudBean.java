
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;


public class DocumentSolicitudBean implements DocumentSolicitud {



	long documentSolicitudID;// PK
	long documentID;
	long solicitudID;


  /** Constructor Buit */
  public DocumentSolicitudBean() {
  }

  /** Constructor amb tots els camps  */
  public DocumentSolicitudBean(long documentSolicitudID , long documentID , long solicitudID) {
    this.documentSolicitudID=documentSolicitudID;
    this.documentID=documentID;
    this.solicitudID=solicitudID;
}
  /** Constructor sense valors autoincrementals */
  public DocumentSolicitudBean(long documentID , long solicitudID) {
    this.documentID=documentID;
    this.solicitudID=solicitudID;
}
  public DocumentSolicitudBean(DocumentSolicitud __bean) {
    this.setDocumentSolicitudID(__bean.getDocumentSolicitudID());
    this.setDocumentID(__bean.getDocumentID());
    this.setSolicitudID(__bean.getSolicitudID());
	}

	public long getDocumentSolicitudID() {
		return(documentSolicitudID);
	};
	public void setDocumentSolicitudID(long _documentSolicitudID_) {
		this.documentSolicitudID = _documentSolicitudID_;
	};

	public long getDocumentID() {
		return(documentID);
	};
	public void setDocumentID(long _documentID_) {
		this.documentID = _documentID_;
	};

	public long getSolicitudID() {
		return(solicitudID);
	};
	public void setSolicitudID(long _solicitudID_) {
		this.solicitudID = _solicitudID_;
	};



  // ======================================

  public static DocumentSolicitudBean toBean(DocumentSolicitud __bean) {
    if (__bean == null) { return null;}
    DocumentSolicitudBean __tmp = new DocumentSolicitudBean();
    __tmp.setDocumentSolicitudID(__bean.getDocumentSolicitudID());
    __tmp.setDocumentID(__bean.getDocumentID());
    __tmp.setSolicitudID(__bean.getSolicitudID());
		return __tmp;
	}



}
