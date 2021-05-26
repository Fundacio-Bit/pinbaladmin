
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
@Table(name = "pad_formulari" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class FormulariJPA implements Formulari {



private static final long serialVersionUID = 178013342L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINBALADMIN_SEQ")
	@Index(name="pad_formulari_pk_i")
	@Column(name="formulariid",nullable = false,length = 19)
	long formulariid;

	@Column(name="nom",nullable = false,length = 255)
	java.lang.String nom;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;

	@Index(name="pad_formulari_fitxerid_fk_i")
	@Column(name="fitxerid",nullable = false,length = 19)
	java.lang.Long fitxerID;



  /** Constructor Buit */
  public FormulariJPA() {
  }

  /** Constructor amb tots els camps  */
  public FormulariJPA(long formulariid , java.lang.String nom , java.lang.String descripcio , java.lang.Long fitxerID) {
    this.formulariid=formulariid;
    this.nom=nom;
    this.descripcio=descripcio;
    this.fitxerID=fitxerID;
}
  /** Constructor sense valors autoincrementals */
  public FormulariJPA(java.lang.String nom , java.lang.String descripcio , java.lang.Long fitxerID) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.fitxerID=fitxerID;
}
  public FormulariJPA(Formulari __bean) {
    this.setFormulariid(__bean.getFormulariid());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setFitxerID(__bean.getFitxerID());
    // Fitxer
    this.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
	}

	public long getFormulariid() {
		return(formulariid);
	};
	public void setFormulariid(long _formulariid_) {
		this.formulariid = _formulariid_;
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

	public java.lang.Long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(java.lang.Long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Formulari) {
      Formulari __instance = (Formulari)__obj;
      __result = true;
      __result = __result && (this.getFormulariid() == __instance.getFormulariid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:formulariid | Table: pad_campformulari | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formulari")
	private Set<CampFormulariJPA> campFormularis = new HashSet<CampFormulariJPA>(0);
	public  Set<CampFormulariJPA> getCampFormularis() {
    return this.campFormularis;
  }

	public void setCampFormularis(Set<CampFormulariJPA> campFormularis) {
	  this.campFormularis = campFormularis;
	}


// EXP  Field:formulariid | Table: pad_servei | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formulari")
	private Set<ServeiJPA> serveis = new HashSet<ServeiJPA>(0);
	public  Set<ServeiJPA> getServeis() {
    return this.serveis;
  }

	public void setServeis(Set<ServeiJPA> serveis) {
	  this.serveis = serveis;
	}


// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pad_formulari_fitxer_fk")
	@JoinColumn(name = "fitxerid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false)
	private FitxerJPA fitxer;

	public FitxerJPA getFitxer() {
    return this.fitxer;
  }

	public  void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }


 // ---------------  STATIC METHODS ------------------
  public static FormulariJPA toJPA(Formulari __bean) {
    if (__bean == null) { return null;}
    FormulariJPA __tmp = new FormulariJPA();
    __tmp.setFormulariid(__bean.getFormulariid());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setFitxerID(__bean.getFitxerID());
    // Fitxer
    __tmp.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
		return __tmp;
	}


  public static FormulariJPA copyJPA(FormulariJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<FormulariJPA> copyJPA(java.util.Set<FormulariJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<FormulariJPA> __tmpSet = (java.util.Set<FormulariJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<FormulariJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (FormulariJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static FormulariJPA copyJPA(FormulariJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    FormulariJPA __tmp = (FormulariJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"CampFormulariJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.campFormularis) || org.hibernate.Hibernate.isInitialized(__jpa.getCampFormularis())) ) {
      __tmp.setCampFormularis(CampFormulariJPA.copyJPA(__jpa.getCampFormularis(), __alreadyCopied,"FormulariJPA"));
    }
    if(!"ServeiJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.serveis) || org.hibernate.Hibernate.isInitialized(__jpa.getServeis())) ) {
      __tmp.setServeis(ServeiJPA.copyJPA(__jpa.getServeis(), __alreadyCopied,"FormulariJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
