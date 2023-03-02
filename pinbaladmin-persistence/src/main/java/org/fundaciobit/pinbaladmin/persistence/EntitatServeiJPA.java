
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "EntitatServeiJPA")
@Table(name = "pad_entitatservei" , indexes = { 
        @Index(name="pad_entitatservei_pk_i", columnList = "entitatserveiid")})
@SequenceGenerator(name="ENTITATSERVEI_SEQ", sequenceName="pad_entitatservei_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EntitatServeiJPA implements EntitatServei {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ENTITATSERVEI_SEQ")
    @Column(name="entitatserveiid",nullable = false,length = 19)
    long entitatServeiID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;

    @Column(name="balears",nullable = false,length = 1)
    boolean balears = false;



  /** Constructor Buit */
  public EntitatServeiJPA() {
  }

  /** Constructor amb tots els camps  */
  public EntitatServeiJPA(long entitatServeiID , java.lang.String nom , java.lang.String descripcio , boolean balears) {
    this.entitatServeiID=entitatServeiID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.balears=balears;
}
  /** Constructor sense valors autoincrementals */
  public EntitatServeiJPA(java.lang.String nom , java.lang.String descripcio , boolean balears) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.balears=balears;
}
  public EntitatServeiJPA(EntitatServei __bean) {
    this.setEntitatServeiID(__bean.getEntitatServeiID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setBalears(__bean.isBalears());
	}

	public long getEntitatServeiID() {
		return(entitatServeiID);
	};
	public void setEntitatServeiID(long _entitatServeiID_) {
		this.entitatServeiID = _entitatServeiID_;
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

	public boolean isBalears() {
		return(balears);
	};
	public void setBalears(boolean _balears_) {
		this.balears = _balears_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof EntitatServei) {
      EntitatServei __instance = (EntitatServei)__obj;
      __result = true;
      __result = __result && (this.getEntitatServeiID() == __instance.getEntitatServeiID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:entitatserveiid | Table: pad_documentcedent | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitatServei")
    private Set<DocumentCedentJPA> documentCedents = new HashSet<DocumentCedentJPA>(0);
    public  Set<DocumentCedentJPA> getDocumentCedents() {
    return this.documentCedents;
  }

    public void setDocumentCedents(Set<DocumentCedentJPA> documentCedents) {
      this.documentCedents = documentCedents;
    }


// EXP  Field:cedentid | Table: pad_grupentitatcedent | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitatServei")
    private Set<GrupEntitatCedentJPA> grupEntitatCedents = new HashSet<GrupEntitatCedentJPA>(0);
    public  Set<GrupEntitatCedentJPA> getGrupEntitatCedents() {
    return this.grupEntitatCedents;
  }

    public void setGrupEntitatCedents(Set<GrupEntitatCedentJPA> grupEntitatCedents) {
      this.grupEntitatCedents = grupEntitatCedents;
    }


// EXP  Field:entitatserveiid | Table: pad_servei | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitatServei")
    private Set<ServeiJPA> serveis = new HashSet<ServeiJPA>(0);
    public  Set<ServeiJPA> getServeis() {
    return this.serveis;
  }

    public void setServeis(Set<ServeiJPA> serveis) {
      this.serveis = serveis;
    }



 // ---------------  STATIC METHODS ------------------
  public static EntitatServeiJPA toJPA(EntitatServei __bean) {
    if (__bean == null) { return null;}
    EntitatServeiJPA __tmp = new EntitatServeiJPA();
    __tmp.setEntitatServeiID(__bean.getEntitatServeiID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setBalears(__bean.isBalears());
		return __tmp;
	}


  public static EntitatServeiJPA copyJPA(EntitatServeiJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EntitatServeiJPA> copyJPA(java.util.Set<EntitatServeiJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EntitatServeiJPA> __tmpSet = (java.util.Set<EntitatServeiJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EntitatServeiJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EntitatServeiJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EntitatServeiJPA copyJPA(EntitatServeiJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EntitatServeiJPA __tmp = (EntitatServeiJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"ServeiJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.serveis) || org.hibernate.Hibernate.isInitialized(__jpa.getServeis())) ) {
      __tmp.setServeis(ServeiJPA.copyJPA(__jpa.getServeis(), __alreadyCopied,"EntitatServeiJPA"));
    }
    if(!"GrupEntitatCedentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupEntitatCedents) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupEntitatCedents())) ) {
      __tmp.setGrupEntitatCedents(GrupEntitatCedentJPA.copyJPA(__jpa.getGrupEntitatCedents(), __alreadyCopied,"EntitatServeiJPA"));
    }
    if(!"DocumentCedentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.documentCedents) || org.hibernate.Hibernate.isInitialized(__jpa.getDocumentCedents())) ) {
      __tmp.setDocumentCedents(DocumentCedentJPA.copyJPA(__jpa.getDocumentCedents(), __alreadyCopied,"EntitatServeiJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
