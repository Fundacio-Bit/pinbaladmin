
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


@Entity(name = "DepartamentJPA")
@Table(name = "pad_departament" , indexes = { 
        @Index(name="pad_depart_pk_i", columnList = "departamentid"),
        @Index(name="pad_departament_areaid_fk_i", columnList = "areaid")})
@SequenceGenerator(name="DEPARTAMENT_SEQ", sequenceName="pad_departament_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class DepartamentJPA implements Departament {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="DEPARTAMENT_SEQ")
    @Column(name="departamentid",nullable = false,length = 19)
    long departamentID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="areaid",nullable = false,length = 19)
    long areaID;



  /** Constructor Buit */
  public DepartamentJPA() {
  }

  /** Constructor amb tots els camps  */
  public DepartamentJPA(long departamentID , java.lang.String nom , long areaID) {
    this.departamentID=departamentID;
    this.nom=nom;
    this.areaID=areaID;
}
  /** Constructor sense valors autoincrementals */
  public DepartamentJPA(java.lang.String nom , long areaID) {
    this.nom=nom;
    this.areaID=areaID;
}
  public DepartamentJPA(Departament __bean) {
    this.setDepartamentID(__bean.getDepartamentID());
    this.setNom(__bean.getNom());
    this.setAreaID(__bean.getAreaID());
	}

	public long getDepartamentID() {
		return(departamentID);
	};
	public void setDepartamentID(long _departamentID_) {
		this.departamentID = _departamentID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public long getAreaID() {
		return(areaID);
	};
	public void setAreaID(long _areaID_) {
		this.areaID = _areaID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Departament) {
      Departament __instance = (Departament)__obj;
      __result = true;
      __result = __result && (this.getDepartamentID() == __instance.getDepartamentID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:departamentid | Table: pad_solicitud | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "departament")
    private Set<SolicitudJPA> solicituds = new HashSet<SolicitudJPA>(0);
    public  Set<SolicitudJPA> getSolicituds() {
    return this.solicituds;
  }

    public void setSolicituds(Set<SolicitudJPA> solicituds) {
      this.solicituds = solicituds;
    }


// IMP Field:areaid | Table: pad_area | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "areaid", referencedColumnName ="areaID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_depart_area_fk"))
    private AreaJPA area;

    public AreaJPA getArea() {
    return this.area;
  }

    public  void setArea(AreaJPA area) {
    this.area = area;
  }


 // ---------------  STATIC METHODS ------------------
  public static DepartamentJPA toJPA(Departament __bean) {
    if (__bean == null) { return null;}
    DepartamentJPA __tmp = new DepartamentJPA();
    __tmp.setDepartamentID(__bean.getDepartamentID());
    __tmp.setNom(__bean.getNom());
    __tmp.setAreaID(__bean.getAreaID());
		return __tmp;
	}


  public static DepartamentJPA copyJPA(DepartamentJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<DepartamentJPA> copyJPA(java.util.Set<DepartamentJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<DepartamentJPA> __tmpSet = (java.util.Set<DepartamentJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<DepartamentJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (DepartamentJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static DepartamentJPA copyJPA(DepartamentJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    DepartamentJPA __tmp = (DepartamentJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"SolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicituds())) ) {
      __tmp.setSolicituds(SolicitudJPA.copyJPA(__jpa.getSolicituds(), __alreadyCopied,"DepartamentJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"AreaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.area) || org.hibernate.Hibernate.isInitialized(__jpa.getArea()) ) ) {
      __tmp.setArea(AreaJPA.copyJPA(__jpa.getArea(), __alreadyCopied,"DepartamentJPA"));
    }

    return __tmp;
  }




}
