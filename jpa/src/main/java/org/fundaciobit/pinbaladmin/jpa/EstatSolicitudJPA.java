
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
@Table(name = "pad_estatsolicitud" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class EstatSolicitudJPA implements EstatSolicitud {



private static final long serialVersionUID = 168264016L;

	@Id
	@Index(name="pad_estatsolicitud_pk_i")
	@Column(name="estatsolicitudid",nullable = false,length = 19)
	long estatSolicitudID;

	@Column(name="nom",nullable = false,length = 100)
	java.lang.String nom;

	@Column(name="descripcio",length = 255)
	java.lang.String descripcio;



  /** Constructor Buit */
  public EstatSolicitudJPA() {
  }

  /** Constructor amb tots els camps  */
  public EstatSolicitudJPA(long estatSolicitudID , java.lang.String nom , java.lang.String descripcio) {
    this.estatSolicitudID=estatSolicitudID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public EstatSolicitudJPA(long estatSolicitudID , java.lang.String nom) {
    this.estatSolicitudID=estatSolicitudID;
    this.nom=nom;
}
  public EstatSolicitudJPA(EstatSolicitud __bean) {
    this.setEstatSolicitudID(__bean.getEstatSolicitudID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getEstatSolicitudID() {
		return(estatSolicitudID);
	};
	public void setEstatSolicitudID(long _estatSolicitudID_) {
		this.estatSolicitudID = _estatSolicitudID_;
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
    if (__obj != null && __obj instanceof EstatSolicitud) {
      EstatSolicitud __instance = (EstatSolicitud)__obj;
      __result = true;
      __result = __result && (this.getEstatSolicitudID() == __instance.getEstatSolicitudID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:estatid | Table: pad_solicitud | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estatSolicitud")
	private Set<SolicitudJPA> solicituds = new HashSet<SolicitudJPA>(0);
	public  Set<SolicitudJPA> getSolicituds() {
    return this.solicituds;
  }

	public void setSolicituds(Set<SolicitudJPA> solicituds) {
	  this.solicituds = solicituds;
	}



 // ---------------  STATIC METHODS ------------------
  public static EstatSolicitudJPA toJPA(EstatSolicitud __bean) {
    if (__bean == null) { return null;}
    EstatSolicitudJPA __tmp = new EstatSolicitudJPA();
    __tmp.setEstatSolicitudID(__bean.getEstatSolicitudID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}


  public static EstatSolicitudJPA copyJPA(EstatSolicitudJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EstatSolicitudJPA> copyJPA(java.util.Set<EstatSolicitudJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EstatSolicitudJPA> __tmpSet = (java.util.Set<EstatSolicitudJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EstatSolicitudJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EstatSolicitudJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EstatSolicitudJPA copyJPA(EstatSolicitudJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EstatSolicitudJPA __tmp = (EstatSolicitudJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"SolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicituds())) ) {
      __tmp.setSolicituds(SolicitudJPA.copyJPA(__jpa.getSolicituds(), __alreadyCopied,"EstatSolicitudJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
