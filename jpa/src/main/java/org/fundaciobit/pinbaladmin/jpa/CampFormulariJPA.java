
package org.fundaciobit.pinbaladmin.jpa;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pad_campformulari" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class CampFormulariJPA implements CampFormulari {



private static final long serialVersionUID = -1532081729L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINBALADMIN_SEQ")
	@Index(name="pad_campformulari_pk_i")
	@Column(name="campformulariid",nullable = false,length = 19)
	long campFormulariID;

	@Column(name="nom",nullable = false,length = 100)
	java.lang.String nom;

	@Column(name="camppdf",nullable = false,length = 100)
	java.lang.String campPDF;

	@Index(name="pad_campform_formulariid_fk_i")
	@Column(name="formulariid",nullable = false,length = 19)
	long formulariID;



  /** Constructor Buit */
  public CampFormulariJPA() {
  }

  /** Constructor amb tots els camps  */
  public CampFormulariJPA(long campFormulariID , java.lang.String nom , java.lang.String campPDF , long formulariID) {
    this.campFormulariID=campFormulariID;
    this.nom=nom;
    this.campPDF=campPDF;
    this.formulariID=formulariID;
}
  /** Constructor sense valors autoincrementals */
  public CampFormulariJPA(java.lang.String nom , java.lang.String campPDF , long formulariID) {
    this.nom=nom;
    this.campPDF=campPDF;
    this.formulariID=formulariID;
}
  public CampFormulariJPA(CampFormulari __bean) {
    this.setCampFormulariID(__bean.getCampFormulariID());
    this.setNom(__bean.getNom());
    this.setCampPDF(__bean.getCampPDF());
    this.setFormulariID(__bean.getFormulariID());
	}

	public long getCampFormulariID() {
		return(campFormulariID);
	};
	public void setCampFormulariID(long _campFormulariID_) {
		this.campFormulariID = _campFormulariID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getCampPDF() {
		return(campPDF);
	};
	public void setCampPDF(java.lang.String _campPDF_) {
		this.campPDF = _campPDF_;
	};

	public long getFormulariID() {
		return(formulariID);
	};
	public void setFormulariID(long _formulariID_) {
		this.formulariID = _formulariID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof CampFormulari) {
      CampFormulari __instance = (CampFormulari)__obj;
      __result = true;
      __result = __result && (this.getCampFormulariID() == __instance.getCampFormulariID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:campformulariid | Table: pad_campsolicitud | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "campFormulari")
	private Set<CampSolicitudJPA> campSolicituds = new HashSet<CampSolicitudJPA>(0);
	public  Set<CampSolicitudJPA> getCampSolicituds() {
    return this.campSolicituds;
  }

	public void setCampSolicituds(Set<CampSolicitudJPA> campSolicituds) {
	  this.campSolicituds = campSolicituds;
	}


// IMP Field:formulariid | Table: pad_formulari | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_campform_formulari_fk")
	@JoinColumn(name = "formulariid", referencedColumnName ="formulariid", nullable = false, insertable=false, updatable=false)
	private FormulariJPA formulari;

	public FormulariJPA getFormulari() {
    return this.formulari;
  }

	public  void setFormulari(FormulariJPA formulari) {
    this.formulari = formulari;
  }


 // ---------------  STATIC METHODS ------------------
  public static CampFormulariJPA toJPA(CampFormulari __bean) {
    if (__bean == null) { return null;}
    CampFormulariJPA __tmp = new CampFormulariJPA();
    __tmp.setCampFormulariID(__bean.getCampFormulariID());
    __tmp.setNom(__bean.getNom());
    __tmp.setCampPDF(__bean.getCampPDF());
    __tmp.setFormulariID(__bean.getFormulariID());
		return __tmp;
	}


  public static CampFormulariJPA copyJPA(CampFormulariJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<CampFormulariJPA> copyJPA(java.util.Set<CampFormulariJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<CampFormulariJPA> __tmpSet = (java.util.Set<CampFormulariJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<CampFormulariJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (CampFormulariJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static CampFormulariJPA copyJPA(CampFormulariJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    CampFormulariJPA __tmp = (CampFormulariJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"CampSolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.campSolicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getCampSolicituds())) ) {
      __tmp.setCampSolicituds(CampSolicitudJPA.copyJPA(__jpa.getCampSolicituds(), __alreadyCopied,"CampFormulariJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"FormulariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.formulari) || org.hibernate.Hibernate.isInitialized(__jpa.getFormulari()) ) ) {
      __tmp.setFormulari(FormulariJPA.copyJPA(__jpa.getFormulari(), __alreadyCopied,"CampFormulariJPA"));
    }

    return __tmp;
  }




}
