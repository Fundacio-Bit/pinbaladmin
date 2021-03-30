
package org.fundaciobit.pinbaladmin.jpa;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pad_documententitat" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class DocumentEntitatJPA implements DocumentEntitat {



private static final long serialVersionUID = -1521032019L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINBALADMIN_SEQ")
	@Index(name="pad_documententitat_pk_i")
	@Column(name="documententitatid",nullable = false,length = 19)
	long documentEntitatID;

	@Column(name="titol",nullable = false,length = 255)
	java.lang.String titol;

	@Column(name="descripcio",length = 2147483647)
  @Lob
	java.lang.String descripcio;

	@Index(name="pad_docent_entitat_fk_i")
	@Column(name="entitatid",nullable = false,length = 19)
	long entitatID;

	@Index(name="pad_docent_fitxerid_fk_i")
	@Column(name="fitxerid",length = 19)
	java.lang.Long fitxerID;

	@Column(name="dataalta",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataAlta;



  /** Constructor Buit */
  public DocumentEntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public DocumentEntitatJPA(long documentEntitatID , java.lang.String titol , java.lang.String descripcio , long entitatID , java.lang.Long fitxerID , java.sql.Timestamp dataAlta) {
    this.documentEntitatID=documentEntitatID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
    this.fitxerID=fitxerID;
    this.dataAlta=dataAlta;
}
  /** Constructor sense valors autoincrementals */
  public DocumentEntitatJPA(java.lang.String titol , java.lang.String descripcio , long entitatID , java.lang.Long fitxerID , java.sql.Timestamp dataAlta) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.entitatID=entitatID;
    this.fitxerID=fitxerID;
    this.dataAlta=dataAlta;
}
  /** Constructor dels valors Not Null */
  public DocumentEntitatJPA(long documentEntitatID , java.lang.String titol , long entitatID , java.sql.Timestamp dataAlta) {
    this.documentEntitatID=documentEntitatID;
    this.titol=titol;
    this.entitatID=entitatID;
    this.dataAlta=dataAlta;
}
  public DocumentEntitatJPA(DocumentEntitat __bean) {
    this.setDocumentEntitatID(__bean.getDocumentEntitatID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setEntitatID(__bean.getEntitatID());
    this.setFitxerID(__bean.getFitxerID());
    this.setDataAlta(__bean.getDataAlta());
    // Fitxer
    this.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof DocumentEntitat) {
      DocumentEntitat __instance = (DocumentEntitat)__obj;
      __result = true;
      __result = __result && (this.getDocumentEntitatID() == __instance.getDocumentEntitatID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:entitatid | Table: pad_entitat | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_docent_entitat_fk")
	@JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false)
	private EntitatJPA entitat;

	public EntitatJPA getEntitat() {
    return this.entitat;
  }

	public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pad_docent_fitxer_fitxe_fk")
	@JoinColumn(name = "fitxerid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA fitxer;

	public FitxerJPA getFitxer() {
    return this.fitxer;
  }

	public  void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }


 // ---------------  STATIC METHODS ------------------
  public static DocumentEntitatJPA toJPA(DocumentEntitat __bean) {
    if (__bean == null) { return null;}
    DocumentEntitatJPA __tmp = new DocumentEntitatJPA();
    __tmp.setDocumentEntitatID(__bean.getDocumentEntitatID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setDataAlta(__bean.getDataAlta());
    // Fitxer
    __tmp.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
		return __tmp;
	}


  public static DocumentEntitatJPA copyJPA(DocumentEntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<DocumentEntitatJPA> copyJPA(java.util.Set<DocumentEntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<DocumentEntitatJPA> __tmpSet = (java.util.Set<DocumentEntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<DocumentEntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (DocumentEntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static DocumentEntitatJPA copyJPA(DocumentEntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    DocumentEntitatJPA __tmp = (DocumentEntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"DocumentEntitatJPA"));
    }

    return __tmp;
  }




}
