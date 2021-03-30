
package org.fundaciobit.pinbaladmin.jpa;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pad_grupentitat" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class GrupEntitatJPA implements GrupEntitat {



private static final long serialVersionUID = -1606199656L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINBALADMIN_SEQ")
	@Index(name="pad_grupentitat_pk_i")
	@Column(name="grupentitatid",nullable = false,length = 19)
	long grupEntitatID;

	@Column(name="nom",nullable = false,length = 256)
	java.lang.String nom;

	@Column(name="descripcio",length = 256)
	java.lang.String descripcio;



  /** Constructor Buit */
  public GrupEntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public GrupEntitatJPA(long grupEntitatID , java.lang.String nom , java.lang.String descripcio) {
    this.grupEntitatID=grupEntitatID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor sense valors autoincrementals */
  public GrupEntitatJPA(java.lang.String nom , java.lang.String descripcio) {
    this.nom=nom;
    this.descripcio=descripcio;
}
  public GrupEntitatJPA(GrupEntitat __bean) {
    this.setGrupEntitatID(__bean.getGrupEntitatID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof GrupEntitat) {
      GrupEntitat __instance = (GrupEntitat)__obj;
      __result = true;
      __result = __result && (this.getGrupEntitatID() == __instance.getGrupEntitatID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:grupentitatid | Table: pad_entitat | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupEntitat")
	private Set<EntitatJPA> entitats = new HashSet<EntitatJPA>(0);
	public  Set<EntitatJPA> getEntitats() {
    return this.entitats;
  }

	public void setEntitats(Set<EntitatJPA> entitats) {
	  this.entitats = entitats;
	}


// EXP  Field:grupentitatid | Table: pad_grupentitatcedent | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "grupEntitat")
	private Set<GrupEntitatCedentJPA> grupEntitatCedents = new HashSet<GrupEntitatCedentJPA>(0);
	public  Set<GrupEntitatCedentJPA> getGrupEntitatCedents() {
    return this.grupEntitatCedents;
  }

	public void setGrupEntitatCedents(Set<GrupEntitatCedentJPA> grupEntitatCedents) {
	  this.grupEntitatCedents = grupEntitatCedents;
	}



 // ---------------  STATIC METHODS ------------------
  public static GrupEntitatJPA toJPA(GrupEntitat __bean) {
    if (__bean == null) { return null;}
    GrupEntitatJPA __tmp = new GrupEntitatJPA();
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}


  public static GrupEntitatJPA copyJPA(GrupEntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<GrupEntitatJPA> copyJPA(java.util.Set<GrupEntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<GrupEntitatJPA> __tmpSet = (java.util.Set<GrupEntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<GrupEntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (GrupEntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static GrupEntitatJPA copyJPA(GrupEntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    GrupEntitatJPA __tmp = (GrupEntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"GrupEntitatCedentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupEntitatCedents) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupEntitatCedents())) ) {
      __tmp.setGrupEntitatCedents(GrupEntitatCedentJPA.copyJPA(__jpa.getGrupEntitatCedents(), __alreadyCopied,"GrupEntitatJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitats) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitats())) ) {
      __tmp.setEntitats(EntitatJPA.copyJPA(__jpa.getEntitats(), __alreadyCopied,"GrupEntitatJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
