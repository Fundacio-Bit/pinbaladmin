
package org.fundaciobit.pinbaladmin.jpa;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;


@Entity
@Table(name = "pad_estatservei" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class EstatServeiJPA implements EstatServei {



private static final long serialVersionUID = 163200552L;

	@Id
	@Index(name="pad_estatservei_pk_i")
	@Column(name="estatserveiid",nullable = false,length = 19)
	long estatServeiID;

	@Column(name="nom",nullable = false,length = 100)
	java.lang.String nom;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;



  /** Constructor Buit */
  public EstatServeiJPA() {
  }

  /** Constructor amb tots els camps  */
  public EstatServeiJPA(long estatServeiID , java.lang.String nom , java.lang.String descripcio) {
    this.estatServeiID=estatServeiID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public EstatServeiJPA(long estatServeiID , java.lang.String nom) {
    this.estatServeiID=estatServeiID;
    this.nom=nom;
}
  public EstatServeiJPA(EstatServei __bean) {
    this.setEstatServeiID(__bean.getEstatServeiID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getEstatServeiID() {
		return(estatServeiID);
	};
	public void setEstatServeiID(long _estatServeiID_) {
		this.estatServeiID = _estatServeiID_;
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
    if (__obj != null && __obj instanceof EstatServei) {
      EstatServei __instance = (EstatServei)__obj;
      __result = true;
      __result = __result && (this.getEstatServeiID() == __instance.getEstatServeiID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:estatserveiid | Table: pad_servei | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estatServei")
	private Set<ServeiJPA> serveis = new HashSet<ServeiJPA>(0);
	public  Set<ServeiJPA> getServeis() {
    return this.serveis;
  }

	public void setServeis(Set<ServeiJPA> serveis) {
	  this.serveis = serveis;
	}



 // ---------------  STATIC METHODS ------------------
  public static EstatServeiJPA toJPA(EstatServei __bean) {
    if (__bean == null) { return null;}
    EstatServeiJPA __tmp = new EstatServeiJPA();
    __tmp.setEstatServeiID(__bean.getEstatServeiID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}


  public static EstatServeiJPA copyJPA(EstatServeiJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EstatServeiJPA> copyJPA(java.util.Set<EstatServeiJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EstatServeiJPA> __tmpSet = (java.util.Set<EstatServeiJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EstatServeiJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EstatServeiJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EstatServeiJPA copyJPA(EstatServeiJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EstatServeiJPA __tmp = (EstatServeiJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"ServeiJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.serveis) || org.hibernate.Hibernate.isInitialized(__jpa.getServeis())) ) {
      __tmp.setServeis(ServeiJPA.copyJPA(__jpa.getServeis(), __alreadyCopied,"EstatServeiJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
