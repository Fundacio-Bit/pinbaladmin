
package org.fundaciobit.pinbaladmin.jpa;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.ForeignKey;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pad_document" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class DocumentJPA implements Document {



private static final long serialVersionUID = -2120442720L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINBALADMIN_SEQ")
	@Index(name="pad_document_pk_i")
	@Column(name="documentid",nullable = false,length = 19)
	long documentID;

	@Column(name="nom",nullable = false,length = 255)
	java.lang.String nom;

	@Index(name="pad_document_fitxer_orig_fk_i")
	@Column(name="fitxeroriginalid",nullable = false,length = 19)
	long fitxerOriginalID;

	@Index(name="pad_document_fitxer_firm_fk_i")
	@Column(name="fitxerfirmatid",length = 19)
	java.lang.Long fitxerFirmatID;

	@Column(name="notes",length = 255)
	java.lang.String notes;



  /** Constructor Buit */
  public DocumentJPA() {
  }

  /** Constructor amb tots els camps  */
  public DocumentJPA(long documentID , java.lang.String nom , long fitxerOriginalID , java.lang.Long fitxerFirmatID , java.lang.String notes) {
    this.documentID=documentID;
    this.nom=nom;
    this.fitxerOriginalID=fitxerOriginalID;
    this.fitxerFirmatID=fitxerFirmatID;
    this.notes=notes;
}
  /** Constructor sense valors autoincrementals */
  public DocumentJPA(java.lang.String nom , long fitxerOriginalID , java.lang.Long fitxerFirmatID , java.lang.String notes) {
    this.nom=nom;
    this.fitxerOriginalID=fitxerOriginalID;
    this.fitxerFirmatID=fitxerFirmatID;
    this.notes=notes;
}
  /** Constructor dels valors Not Null */
  public DocumentJPA(long documentID , java.lang.String nom , long fitxerOriginalID) {
    this.documentID=documentID;
    this.nom=nom;
    this.fitxerOriginalID=fitxerOriginalID;
}
  public DocumentJPA(Document __bean) {
    this.setDocumentID(__bean.getDocumentID());
    this.setNom(__bean.getNom());
    this.setFitxerOriginalID(__bean.getFitxerOriginalID());
    this.setFitxerFirmatID(__bean.getFitxerFirmatID());
    this.setNotes(__bean.getNotes());
    // Fitxer
    this.setFitxerOriginal(FitxerJPA.toJPA(__bean.getFitxerOriginal()));
    // Fitxer
    this.setFitxerFirmat(FitxerJPA.toJPA(__bean.getFitxerFirmat()));
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Document) {
      Document __instance = (Document)__obj;
      __result = true;
      __result = __result && (this.getDocumentID() == __instance.getDocumentID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:documentid | Table: pad_documentsolicitud | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "document")
	private Set<DocumentSolicitudJPA> documentSolicituds = new HashSet<DocumentSolicitudJPA>(0);
	public  Set<DocumentSolicitudJPA> getDocumentSolicituds() {
    return this.documentSolicituds;
  }

	public void setDocumentSolicituds(Set<DocumentSolicitudJPA> documentSolicituds) {
	  this.documentSolicituds = documentSolicituds;
	}


// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pad_document_fitxer_orig_fk")
	@JoinColumn(name = "fitxeroriginalid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false)
	private FitxerJPA fitxerOriginal;

	public FitxerJPA getFitxerOriginal() {
    return this.fitxerOriginal;
  }

	public  void setFitxerOriginal(FitxerJPA fitxerOriginal) {
    this.fitxerOriginal = fitxerOriginal;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pad_document_fitxer_firm_fk")
	@JoinColumn(name = "fitxerfirmatid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA fitxerFirmat;

	public FitxerJPA getFitxerFirmat() {
    return this.fitxerFirmat;
  }

	public  void setFitxerFirmat(FitxerJPA fitxerFirmat) {
    this.fitxerFirmat = fitxerFirmat;
  }


 // ---------------  STATIC METHODS ------------------
  public static DocumentJPA toJPA(Document __bean) {
    if (__bean == null) { return null;}
    DocumentJPA __tmp = new DocumentJPA();
    __tmp.setDocumentID(__bean.getDocumentID());
    __tmp.setNom(__bean.getNom());
    __tmp.setFitxerOriginalID(__bean.getFitxerOriginalID());
    __tmp.setFitxerFirmatID(__bean.getFitxerFirmatID());
    __tmp.setNotes(__bean.getNotes());
    // Fitxer
    __tmp.setFitxerOriginal(FitxerJPA.toJPA(__bean.getFitxerOriginal()));
    // Fitxer
    __tmp.setFitxerFirmat(FitxerJPA.toJPA(__bean.getFitxerFirmat()));
		return __tmp;
	}


  public static DocumentJPA copyJPA(DocumentJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<DocumentJPA> copyJPA(java.util.Set<DocumentJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<DocumentJPA> __tmpSet = (java.util.Set<DocumentJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<DocumentJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (DocumentJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static DocumentJPA copyJPA(DocumentJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    DocumentJPA __tmp = (DocumentJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"DocumentSolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.documentSolicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getDocumentSolicituds())) ) {
      __tmp.setDocumentSolicituds(DocumentSolicitudJPA.copyJPA(__jpa.getDocumentSolicituds(), __alreadyCopied,"DocumentJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
