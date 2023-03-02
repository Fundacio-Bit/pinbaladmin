
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "EntitatJPA")
@Table(name = "pad_entitat" , indexes = { 
        @Index(name="pad_entitat_pk_i", columnList = "entitatid"),
        @Index(name="pad_entitat_grupentitatid_fk_i", columnList = "grupentitatid")})
@SequenceGenerator(name="ENTITAT_SEQ", sequenceName="pad_entitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EntitatJPA implements Entitat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ENTITAT_SEQ")
    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;

    @Column(name="nom",nullable = false,length = 500)
    java.lang.String nom;

    @Column(name="personacontacte",length = 255)
    java.lang.String personaContacte;

    @Column(name="cif",nullable = false,length = 10)
    java.lang.String CIF;

    @org.hibernate.annotations.ColumnDefault("0")
    @Column(name="grupentitatid",nullable = false,length = 19)
    long grupEntitatID = 0;

    @Column(name="convenipmsbae",nullable = false,length = 1)
    boolean convenipmsbae = false;



  /** Constructor Buit */
  public EntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public EntitatJPA(long entitatID , java.lang.String nom , java.lang.String personaContacte , java.lang.String CIF , long grupEntitatID , boolean convenipmsbae) {
    this.entitatID=entitatID;
    this.nom=nom;
    this.personaContacte=personaContacte;
    this.CIF=CIF;
    this.grupEntitatID=grupEntitatID;
    this.convenipmsbae=convenipmsbae;
}
  /** Constructor sense valors autoincrementals */
  public EntitatJPA(java.lang.String nom , java.lang.String personaContacte , java.lang.String CIF , long grupEntitatID , boolean convenipmsbae) {
    this.nom=nom;
    this.personaContacte=personaContacte;
    this.CIF=CIF;
    this.grupEntitatID=grupEntitatID;
    this.convenipmsbae=convenipmsbae;
}
  public EntitatJPA(Entitat __bean) {
    this.setEntitatID(__bean.getEntitatID());
    this.setNom(__bean.getNom());
    this.setPersonaContacte(__bean.getPersonaContacte());
    this.setCIF(__bean.getCIF());
    this.setGrupEntitatID(__bean.getGrupEntitatID());
    this.setConvenipmsbae(__bean.isConvenipmsbae());
	}

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getPersonaContacte() {
		return(personaContacte);
	};
	public void setPersonaContacte(java.lang.String _personaContacte_) {
		this.personaContacte = _personaContacte_;
	};

	public java.lang.String getCIF() {
		return(CIF);
	};
	public void setCIF(java.lang.String _CIF_) {
		this.CIF = _CIF_;
	};

	public long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
	};

	public boolean isConvenipmsbae() {
		return(convenipmsbae);
	};
	public void setConvenipmsbae(boolean _convenipmsbae_) {
		this.convenipmsbae = _convenipmsbae_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Entitat) {
      Entitat __instance = (Entitat)__obj;
      __result = true;
      __result = __result && (this.getEntitatID() == __instance.getEntitatID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:entitatid | Table: pad_area | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<AreaJPA> areas = new HashSet<AreaJPA>(0);
    public  Set<AreaJPA> getAreas() {
    return this.areas;
  }

    public void setAreas(Set<AreaJPA> areas) {
      this.areas = areas;
    }


// EXP  Field:entitatid | Table: pad_documententitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<DocumentEntitatJPA> documentEntitats = new HashSet<DocumentEntitatJPA>(0);
    public  Set<DocumentEntitatJPA> getDocumentEntitats() {
    return this.documentEntitats;
  }

    public void setDocumentEntitats(Set<DocumentEntitatJPA> documentEntitats) {
      this.documentEntitats = documentEntitats;
    }


// IMP Field:grupentitatid | Table: pad_grupentitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupentitatid", referencedColumnName ="grupEntitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_entitat_grupent_fk"))
    private GrupEntitatJPA grupEntitat;

    public GrupEntitatJPA getGrupEntitat() {
    return this.grupEntitat;
  }

    public  void setGrupEntitat(GrupEntitatJPA grupEntitat) {
    this.grupEntitat = grupEntitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static EntitatJPA toJPA(Entitat __bean) {
    if (__bean == null) { return null;}
    EntitatJPA __tmp = new EntitatJPA();
    __tmp.setEntitatID(__bean.getEntitatID());
    __tmp.setNom(__bean.getNom());
    __tmp.setPersonaContacte(__bean.getPersonaContacte());
    __tmp.setCIF(__bean.getCIF());
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
    __tmp.setConvenipmsbae(__bean.isConvenipmsbae());
		return __tmp;
	}


  public static EntitatJPA copyJPA(EntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EntitatJPA> copyJPA(java.util.Set<EntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EntitatJPA> __tmpSet = (java.util.Set<EntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EntitatJPA copyJPA(EntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EntitatJPA __tmp = (EntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"DocumentEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.documentEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getDocumentEntitats())) ) {
      __tmp.setDocumentEntitats(DocumentEntitatJPA.copyJPA(__jpa.getDocumentEntitats(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"AreaJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.areas) || org.hibernate.Hibernate.isInitialized(__jpa.getAreas())) ) {
      __tmp.setAreas(AreaJPA.copyJPA(__jpa.getAreas(), __alreadyCopied,"EntitatJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"GrupEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupEntitat()) ) ) {
      __tmp.setGrupEntitat(GrupEntitatJPA.copyJPA(__jpa.getGrupEntitat(), __alreadyCopied,"EntitatJPA"));
    }

    return __tmp;
  }




}
