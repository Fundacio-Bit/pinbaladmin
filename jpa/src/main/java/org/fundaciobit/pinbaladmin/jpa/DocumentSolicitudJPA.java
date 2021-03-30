
package org.fundaciobit.pinbaladmin.jpa;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pad_documentsolicitud" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class DocumentSolicitudJPA implements DocumentSolicitud {



private static final long serialVersionUID = -853166222L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINBALADMIN_SEQ")
	@Index(name="pad_documentsolicitud_pk_i")
	@Column(name="documentsolicitudid",nullable = false,length = 19)
	long documentSolicitudID;

	@Index(name="pad_docsoli_documentid_fk_i")
	@Column(name="documentid",nullable = false,length = 19)
	long documentID;

	@Index(name="pad_docsoli_solicitudid_fk_i")
	@Column(name="solicitudid",nullable = false,length = 19)
	long solicitudID;



  /** Constructor Buit */
  public DocumentSolicitudJPA() {
  }

  /** Constructor amb tots els camps  */
  public DocumentSolicitudJPA(long documentSolicitudID , long documentID , long solicitudID) {
    this.documentSolicitudID=documentSolicitudID;
    this.documentID=documentID;
    this.solicitudID=solicitudID;
}
  /** Constructor sense valors autoincrementals */
  public DocumentSolicitudJPA(long documentID , long solicitudID) {
    this.documentID=documentID;
    this.solicitudID=solicitudID;
}
  public DocumentSolicitudJPA(DocumentSolicitud __bean) {
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof DocumentSolicitud) {
      DocumentSolicitud __instance = (DocumentSolicitud)__obj;
      __result = true;
      __result = __result && (this.getDocumentSolicitudID() == __instance.getDocumentSolicitudID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:documentid | Table: pad_document | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_docsoli_document_fk")
	@JoinColumn(name = "documentid", referencedColumnName ="documentID", nullable = false, insertable=false, updatable=false)
	private DocumentJPA document;

	public DocumentJPA getDocument() {
    return this.document;
  }

	public  void setDocument(DocumentJPA document) {
    this.document = document;
  }

// IMP Field:solicitudid | Table: pad_solicitud | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_docsoli_solicitud_fk")
	@JoinColumn(name = "solicitudid", referencedColumnName ="solicitudID", nullable = false, insertable=false, updatable=false)
	private SolicitudJPA solicitud;

	public SolicitudJPA getSolicitud() {
    return this.solicitud;
  }

	public  void setSolicitud(SolicitudJPA solicitud) {
    this.solicitud = solicitud;
  }


 // ---------------  STATIC METHODS ------------------
  public static DocumentSolicitudJPA toJPA(DocumentSolicitud __bean) {
    if (__bean == null) { return null;}
    DocumentSolicitudJPA __tmp = new DocumentSolicitudJPA();
    __tmp.setDocumentSolicitudID(__bean.getDocumentSolicitudID());
    __tmp.setDocumentID(__bean.getDocumentID());
    __tmp.setSolicitudID(__bean.getSolicitudID());
		return __tmp;
	}


  public static DocumentSolicitudJPA copyJPA(DocumentSolicitudJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<DocumentSolicitudJPA> copyJPA(java.util.Set<DocumentSolicitudJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<DocumentSolicitudJPA> __tmpSet = (java.util.Set<DocumentSolicitudJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<DocumentSolicitudJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (DocumentSolicitudJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static DocumentSolicitudJPA copyJPA(DocumentSolicitudJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    DocumentSolicitudJPA __tmp = (DocumentSolicitudJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"DocumentJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.document) || org.hibernate.Hibernate.isInitialized(__jpa.getDocument()) ) ) {
      __tmp.setDocument(DocumentJPA.copyJPA(__jpa.getDocument(), __alreadyCopied,"DocumentSolicitudJPA"));
    }
    if(!"SolicitudJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitud) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitud()) ) ) {
      __tmp.setSolicitud(SolicitudJPA.copyJPA(__jpa.getSolicitud(), __alreadyCopied,"DocumentSolicitudJPA"));
    }

    return __tmp;
  }




}
