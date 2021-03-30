
package org.fundaciobit.pinbaladmin.jpa;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.ForeignKey;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pad_servei" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class ServeiJPA implements Servei {



private static final long serialVersionUID = 1806241313L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINBALADMIN_SEQ")
	@Index(name="pad_servei_pk_i")
	@Column(name="serveiid",nullable = false,length = 19)
	long serveiID;

	@Column(name="codi",nullable = false,unique = true,length = 255)
	java.lang.String codi;

	@Column(name="nom",nullable = false,length = 1000)
	java.lang.String nom;

	@Column(name="descripcio",length = 1000)
	java.lang.String descripcio;

	@Index(name="pad_servei_formulariid_fk_i")
	@Column(name="formulariid",length = 19)
	java.lang.Long formulariID;

	@Index(name="pad_servei_entservid_fk_i")
	@Column(name="entitatserveiid",nullable = false,length = 19)
	java.lang.Long entitatServeiID;

	@Index(name="pad_servei_estatserveiid_fk_i")
	@Column(name="estatserveiid",nullable = false,length = 19)
	java.lang.Long estatServeiID;

	@Column(name="tipusconsentiment",nullable = false,length = 10)
	int tipusConsentiment;

	@Column(name="ocult",nullable = false,length = 1)
	boolean ocult;



  /** Constructor Buit */
  public ServeiJPA() {
  }

  /** Constructor amb tots els camps  */
  public ServeiJPA(long serveiID , java.lang.String codi , java.lang.String nom , java.lang.String descripcio , java.lang.Long formulariID , java.lang.Long entitatServeiID , java.lang.Long estatServeiID , int tipusConsentiment , boolean ocult) {
    this.serveiID=serveiID;
    this.codi=codi;
    this.nom=nom;
    this.descripcio=descripcio;
    this.formulariID=formulariID;
    this.entitatServeiID=entitatServeiID;
    this.estatServeiID=estatServeiID;
    this.tipusConsentiment=tipusConsentiment;
    this.ocult=ocult;
}
  /** Constructor sense valors autoincrementals */
  public ServeiJPA(java.lang.String codi , java.lang.String nom , java.lang.String descripcio , java.lang.Long formulariID , java.lang.Long entitatServeiID , java.lang.Long estatServeiID , int tipusConsentiment , boolean ocult) {
    this.codi=codi;
    this.nom=nom;
    this.descripcio=descripcio;
    this.formulariID=formulariID;
    this.entitatServeiID=entitatServeiID;
    this.estatServeiID=estatServeiID;
    this.tipusConsentiment=tipusConsentiment;
    this.ocult=ocult;
}
  /** Constructor dels valors Not Null */
  public ServeiJPA(long serveiID , java.lang.String codi , java.lang.String nom , java.lang.Long entitatServeiID , java.lang.Long estatServeiID , int tipusConsentiment , boolean ocult) {
    this.serveiID=serveiID;
    this.codi=codi;
    this.nom=nom;
    this.entitatServeiID=entitatServeiID;
    this.estatServeiID=estatServeiID;
    this.tipusConsentiment=tipusConsentiment;
    this.ocult=ocult;
}
  public ServeiJPA(Servei __bean) {
    this.setServeiID(__bean.getServeiID());
    this.setCodi(__bean.getCodi());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setFormulariID(__bean.getFormulariID());
    this.setEntitatServeiID(__bean.getEntitatServeiID());
    this.setEstatServeiID(__bean.getEstatServeiID());
    this.setTipusConsentiment(__bean.getTipusConsentiment());
    this.setOcult(__bean.isOcult());
	}

	public long getServeiID() {
		return(serveiID);
	};
	public void setServeiID(long _serveiID_) {
		this.serveiID = _serveiID_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
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

	public java.lang.Long getFormulariID() {
		return(formulariID);
	};
	public void setFormulariID(java.lang.Long _formulariID_) {
		this.formulariID = _formulariID_;
	};

	public java.lang.Long getEntitatServeiID() {
		return(entitatServeiID);
	};
	public void setEntitatServeiID(java.lang.Long _entitatServeiID_) {
		this.entitatServeiID = _entitatServeiID_;
	};

	public java.lang.Long getEstatServeiID() {
		return(estatServeiID);
	};
	public void setEstatServeiID(java.lang.Long _estatServeiID_) {
		this.estatServeiID = _estatServeiID_;
	};

	public int getTipusConsentiment() {
		return(tipusConsentiment);
	};
	public void setTipusConsentiment(int _tipusConsentiment_) {
		this.tipusConsentiment = _tipusConsentiment_;
	};

	public boolean isOcult() {
		return(ocult);
	};
	public void setOcult(boolean _ocult_) {
		this.ocult = _ocult_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Servei) {
      Servei __instance = (Servei)__obj;
      __result = true;
      __result = __result && (this.getServeiID() == __instance.getServeiID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:serveiid | Table: pad_solicitudservei | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "servei")
	private Set<SolicitudServeiJPA> solicitudServeis = new HashSet<SolicitudServeiJPA>(0);
	public  Set<SolicitudServeiJPA> getSolicitudServeis() {
    return this.solicitudServeis;
  }

	public void setSolicitudServeis(Set<SolicitudServeiJPA> solicitudServeis) {
	  this.solicitudServeis = solicitudServeis;
	}


// IMP Field:formulariid | Table: pad_formulari | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_servei_formulari_fk")
	@JoinColumn(name = "formulariid", referencedColumnName ="formulariid", nullable = true, insertable=false, updatable=false)
	private FormulariJPA formulari;

	public FormulariJPA getFormulari() {
    return this.formulari;
  }

	public  void setFormulari(FormulariJPA formulari) {
    this.formulari = formulari;
  }

// IMP Field:entitatserveiid | Table: pad_entitatservei | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_servei_entiservei_fk")
	@JoinColumn(name = "entitatserveiid", referencedColumnName ="entitatServeiID", nullable = false, insertable=false, updatable=false)
	private EntitatServeiJPA entitatServei;

	public EntitatServeiJPA getEntitatServei() {
    return this.entitatServei;
  }

	public  void setEntitatServei(EntitatServeiJPA entitatServei) {
    this.entitatServei = entitatServei;
  }

// IMP Field:estatserveiid | Table: pad_estatservei | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_servei_estatserv_fk")
	@JoinColumn(name = "estatserveiid", referencedColumnName ="estatServeiID", nullable = false, insertable=false, updatable=false)
	private EstatServeiJPA estatServei;

	public EstatServeiJPA getEstatServei() {
    return this.estatServei;
  }

	public  void setEstatServei(EstatServeiJPA estatServei) {
    this.estatServei = estatServei;
  }


 // ---------------  STATIC METHODS ------------------
  public static ServeiJPA toJPA(Servei __bean) {
    if (__bean == null) { return null;}
    ServeiJPA __tmp = new ServeiJPA();
    __tmp.setServeiID(__bean.getServeiID());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setFormulariID(__bean.getFormulariID());
    __tmp.setEntitatServeiID(__bean.getEntitatServeiID());
    __tmp.setEstatServeiID(__bean.getEstatServeiID());
    __tmp.setTipusConsentiment(__bean.getTipusConsentiment());
    __tmp.setOcult(__bean.isOcult());
		return __tmp;
	}


  public static ServeiJPA copyJPA(ServeiJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<ServeiJPA> copyJPA(java.util.Set<ServeiJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<ServeiJPA> __tmpSet = (java.util.Set<ServeiJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<ServeiJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (ServeiJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static ServeiJPA copyJPA(ServeiJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    ServeiJPA __tmp = (ServeiJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"SolicitudServeiJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitudServeis) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitudServeis())) ) {
      __tmp.setSolicitudServeis(SolicitudServeiJPA.copyJPA(__jpa.getSolicitudServeis(), __alreadyCopied,"ServeiJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"FormulariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.formulari) || org.hibernate.Hibernate.isInitialized(__jpa.getFormulari()) ) ) {
      __tmp.setFormulari(FormulariJPA.copyJPA(__jpa.getFormulari(), __alreadyCopied,"ServeiJPA"));
    }
    if(!"EstatServeiJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.estatServei) || org.hibernate.Hibernate.isInitialized(__jpa.getEstatServei()) ) ) {
      __tmp.setEstatServei(EstatServeiJPA.copyJPA(__jpa.getEstatServei(), __alreadyCopied,"ServeiJPA"));
    }
    if(!"EntitatServeiJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitatServei) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitatServei()) ) ) {
      __tmp.setEntitatServei(EntitatServeiJPA.copyJPA(__jpa.getEntitatServei(), __alreadyCopied,"ServeiJPA"));
    }

    return __tmp;
  }




}
