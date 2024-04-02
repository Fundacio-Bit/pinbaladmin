
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


@Entity(name = "OrganJPA")
@Table(name = "pad_organ" , indexes = { 
        @Index(name="pad_organ_pk_i", columnList = "organid"),
        @Index(name="pad_organ_entitatid_fk_i", columnList = "entitatid")})
@SequenceGenerator(name="ORGAN_SEQ", sequenceName="pad_organ_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class OrganJPA implements Organ {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ORGAN_SEQ")
    @Column(name="organid",nullable = false,length = 19)
    long organid;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="dir3",nullable = false,length = 255)
    java.lang.String dir3;

    @Column(name="dir3pare",length = 255)
    java.lang.String dir3pare;

    @Column(name="entitatid",length = 19)
    java.lang.Long entitatid;

    @Column(name="cif",length = 30)
    java.lang.String cif;



  /** Constructor Buit */
  public OrganJPA() {
  }

  /** Constructor amb tots els camps  */
  public OrganJPA(long organid , java.lang.String nom , java.lang.String dir3 , java.lang.String dir3pare , java.lang.Long entitatid , java.lang.String cif) {
    this.organid=organid;
    this.nom=nom;
    this.dir3=dir3;
    this.dir3pare=dir3pare;
    this.entitatid=entitatid;
    this.cif=cif;
}
  /** Constructor sense valors autoincrementals */
  public OrganJPA(java.lang.String nom , java.lang.String dir3 , java.lang.String dir3pare , java.lang.Long entitatid , java.lang.String cif) {
    this.nom=nom;
    this.dir3=dir3;
    this.dir3pare=dir3pare;
    this.entitatid=entitatid;
    this.cif=cif;
}
  /** Constructor dels valors Not Null */
  public OrganJPA(long organid , java.lang.String nom , java.lang.String dir3) {
    this.organid=organid;
    this.nom=nom;
    this.dir3=dir3;
}
  public OrganJPA(Organ __bean) {
    this.setOrganid(__bean.getOrganid());
    this.setNom(__bean.getNom());
    this.setDir3(__bean.getDir3());
    this.setDir3pare(__bean.getDir3pare());
    this.setEntitatid(__bean.getEntitatid());
    this.setCif(__bean.getCif());
	}

	public long getOrganid() {
		return(organid);
	};
	public void setOrganid(long _organid_) {
		this.organid = _organid_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDir3() {
		return(dir3);
	};
	public void setDir3(java.lang.String _dir3_) {
		this.dir3 = _dir3_;
	};

	public java.lang.String getDir3pare() {
		return(dir3pare);
	};
	public void setDir3pare(java.lang.String _dir3pare_) {
		this.dir3pare = _dir3pare_;
	};

	public java.lang.Long getEntitatid() {
		return(entitatid);
	};
	public void setEntitatid(java.lang.Long _entitatid_) {
		this.entitatid = _entitatid_;
	};

	public java.lang.String getCif() {
		return(cif);
	};
	public void setCif(java.lang.String _cif_) {
		this.cif = _cif_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Organ) {
      Organ __instance = (Organ)__obj;
      __result = true;
      __result = __result && (this.getOrganid() == __instance.getOrganid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:organid | Table: pad_solicitud | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organ")
    private Set<SolicitudJPA> solicituds = new HashSet<SolicitudJPA>(0);
    public  Set<SolicitudJPA> getSolicituds() {
    return this.solicituds;
  }

    public void setSolicituds(Set<SolicitudJPA> solicituds) {
      this.solicituds = solicituds;
    }


// IMP Field:entitatid | Table: pad_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_organ_entitat_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static OrganJPA toJPA(Organ __bean) {
    if (__bean == null) { return null;}
    OrganJPA __tmp = new OrganJPA();
    __tmp.setOrganid(__bean.getOrganid());
    __tmp.setNom(__bean.getNom());
    __tmp.setDir3(__bean.getDir3());
    __tmp.setDir3pare(__bean.getDir3pare());
    __tmp.setEntitatid(__bean.getEntitatid());
    __tmp.setCif(__bean.getCif());
		return __tmp;
	}


  public static OrganJPA copyJPA(OrganJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<OrganJPA> copyJPA(java.util.Set<OrganJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<OrganJPA> __tmpSet = (java.util.Set<OrganJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<OrganJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (OrganJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static OrganJPA copyJPA(OrganJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    OrganJPA __tmp = (OrganJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"SolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicituds())) ) {
      __tmp.setSolicituds(SolicitudJPA.copyJPA(__jpa.getSolicituds(), __alreadyCopied,"OrganJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"OrganJPA"));
    }

    return __tmp;
  }




}
