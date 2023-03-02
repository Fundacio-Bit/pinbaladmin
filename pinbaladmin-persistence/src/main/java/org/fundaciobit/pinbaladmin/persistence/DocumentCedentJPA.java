
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity(name = "DocumentCedentJPA")
@Table(name = "pad_documentcedent" , indexes = { 
        @Index(name="pad_documentcedent_pk_i", columnList = "documentcedentid"),
        @Index(name="pad_doccedent_entservei_fk_i", columnList = "entitatserveiid"),
        @Index(name="pad_doccedent_fitxerid_fk_i", columnList = "fitxerid")})
@SequenceGenerator(name="DOCUMENTCEDENT_SEQ", sequenceName="pad_documentcedent_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class DocumentCedentJPA implements DocumentCedent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DOCUMENTCEDENT_SEQ")
    @Column(name="documentcedentid",nullable = false,length = 19)
    long documentCedentID;

    @Column(name="titol",nullable = false,length = 255)
    java.lang.String titol;

    @Column(name="descripcio",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String descripcio;

    @Column(name="entitatserveiid",nullable = false,length = 19)
    long entitatServeiID;

    @Column(name="dataalta",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="fitxerid",length = 19)
    java.lang.Long fitxerID;



  /** Constructor Buit */
  public DocumentCedentJPA() {
  }

  /** Constructor amb tots els camps  */
  public DocumentCedentJPA(long documentCedentID , java.lang.String titol , java.lang.String descripcio , long entitatServeiID , java.sql.Timestamp dataCreacio , java.lang.Long fitxerID) {
    this.documentCedentID=documentCedentID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.entitatServeiID=entitatServeiID;
    this.dataCreacio=dataCreacio;
    this.fitxerID=fitxerID;
}
  /** Constructor sense valors autoincrementals */
  public DocumentCedentJPA(java.lang.String titol , java.lang.String descripcio , long entitatServeiID , java.sql.Timestamp dataCreacio , java.lang.Long fitxerID) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.entitatServeiID=entitatServeiID;
    this.dataCreacio=dataCreacio;
    this.fitxerID=fitxerID;
}
  /** Constructor dels valors Not Null */
  public DocumentCedentJPA(long documentCedentID , java.lang.String titol , long entitatServeiID , java.sql.Timestamp dataCreacio) {
    this.documentCedentID=documentCedentID;
    this.titol=titol;
    this.entitatServeiID=entitatServeiID;
    this.dataCreacio=dataCreacio;
}
  public DocumentCedentJPA(DocumentCedent __bean) {
    this.setDocumentCedentID(__bean.getDocumentCedentID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setEntitatServeiID(__bean.getEntitatServeiID());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setFitxerID(__bean.getFitxerID());
    // Fitxer
    this.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof DocumentCedent) {
      DocumentCedent __instance = (DocumentCedent)__obj;
      __result = true;
      __result = __result && (this.getDocumentCedentID() == __instance.getDocumentCedentID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:entitatserveiid | Table: pad_entitatservei | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatserveiid", referencedColumnName ="entitatServeiID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_doccedent_entiservei_fk"))
    private EntitatServeiJPA entitatServei;

    public EntitatServeiJPA getEntitatServei() {
    return this.entitatServei;
  }

    public  void setEntitatServei(EntitatServeiJPA entitatServei) {
    this.entitatServei = entitatServei;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxerid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_doccedent_fitxer_fitxe_fk"))
    private FitxerJPA fitxer;

    public FitxerJPA getFitxer() {
    return this.fitxer;
  }

    public  void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }


 // ---------------  STATIC METHODS ------------------
  public static DocumentCedentJPA toJPA(DocumentCedent __bean) {
    if (__bean == null) { return null;}
    DocumentCedentJPA __tmp = new DocumentCedentJPA();
    __tmp.setDocumentCedentID(__bean.getDocumentCedentID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setEntitatServeiID(__bean.getEntitatServeiID());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setFitxerID(__bean.getFitxerID());
    // Fitxer
    __tmp.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
		return __tmp;
	}


  public static DocumentCedentJPA copyJPA(DocumentCedentJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<DocumentCedentJPA> copyJPA(java.util.Set<DocumentCedentJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<DocumentCedentJPA> __tmpSet = (java.util.Set<DocumentCedentJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<DocumentCedentJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (DocumentCedentJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static DocumentCedentJPA copyJPA(DocumentCedentJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    DocumentCedentJPA __tmp = (DocumentCedentJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"EntitatServeiJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitatServei) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitatServei()) ) ) {
      __tmp.setEntitatServei(EntitatServeiJPA.copyJPA(__jpa.getEntitatServei(), __alreadyCopied,"DocumentCedentJPA"));
    }

    return __tmp;
  }




}
