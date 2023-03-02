
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


@Entity(name = "AreaJPA")
@Table(name = "pad_area" , indexes = { 
        @Index(name="pad_area_pk_i", columnList = "areaid"),
        @Index(name="pad_area_entitatid_fk_i", columnList = "entitatid")})
@SequenceGenerator(name="AREA_SEQ", sequenceName="pad_area_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class AreaJPA implements Area {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AREA_SEQ")
    @Column(name="areaid",nullable = false,length = 19)
    long areaID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="entitatid",nullable = false,length = 19)
    long entitatID;



  /** Constructor Buit */
  public AreaJPA() {
  }

  /** Constructor amb tots els camps  */
  public AreaJPA(long areaID , java.lang.String nom , long entitatID) {
    this.areaID=areaID;
    this.nom=nom;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public AreaJPA(java.lang.String nom , long entitatID) {
    this.nom=nom;
    this.entitatID=entitatID;
}
  public AreaJPA(Area __bean) {
    this.setAreaID(__bean.getAreaID());
    this.setNom(__bean.getNom());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getAreaID() {
		return(areaID);
	};
	public void setAreaID(long _areaID_) {
		this.areaID = _areaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public long getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(long _entitatID_) {
		this.entitatID = _entitatID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Area) {
      Area __instance = (Area)__obj;
      __result = true;
      __result = __result && (this.getAreaID() == __instance.getAreaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:areaid | Table: pad_departament | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
    private Set<DepartamentJPA> departaments = new HashSet<DepartamentJPA>(0);
    public  Set<DepartamentJPA> getDepartaments() {
    return this.departaments;
  }

    public void setDepartaments(Set<DepartamentJPA> departaments) {
      this.departaments = departaments;
    }


// IMP Field:entitatid | Table: pad_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_area_entitat_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static AreaJPA toJPA(Area __bean) {
    if (__bean == null) { return null;}
    AreaJPA __tmp = new AreaJPA();
    __tmp.setAreaID(__bean.getAreaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setEntitatID(__bean.getEntitatID());
		return __tmp;
	}


  public static AreaJPA copyJPA(AreaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<AreaJPA> copyJPA(java.util.Set<AreaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<AreaJPA> __tmpSet = (java.util.Set<AreaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<AreaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (AreaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static AreaJPA copyJPA(AreaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    AreaJPA __tmp = (AreaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"DepartamentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.departaments) || org.hibernate.Hibernate.isInitialized(__jpa.getDepartaments())) ) {
      __tmp.setDepartaments(DepartamentJPA.copyJPA(__jpa.getDepartaments(), __alreadyCopied,"AreaJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"AreaJPA"));
    }

    return __tmp;
  }




}
