
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "EstatSolicitudServeiJPA")
@Table(name = "pad_estatsolicitudservei" , indexes = { 
        @Index(name="pad_estatsolicitudservei_pk_i", columnList = "estatsolicitudserveiid")})
@SequenceGenerator(name="ESTATSOLICITUDSERVEI_SEQ", sequenceName="pad_estatsolicitudservei_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EstatSolicitudServeiJPA implements EstatSolicitudServei {

    @Id
    @Column(name="estatsolicitudserveiid",nullable = false,length = 19)
    long estatSolicitudServeiID;

    @Column(name="nom",nullable = false,length = 100)
    java.lang.String nom;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;



  /** Constructor Buit */
  public EstatSolicitudServeiJPA() {
  }

  /** Constructor amb tots els camps  */
  public EstatSolicitudServeiJPA(long estatSolicitudServeiID , java.lang.String nom , java.lang.String descripcio) {
    this.estatSolicitudServeiID=estatSolicitudServeiID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor dels valors Not Null */
  public EstatSolicitudServeiJPA(long estatSolicitudServeiID , java.lang.String nom) {
    this.estatSolicitudServeiID=estatSolicitudServeiID;
    this.nom=nom;
}
  public EstatSolicitudServeiJPA(EstatSolicitudServei __bean) {
    this.setEstatSolicitudServeiID(__bean.getEstatSolicitudServeiID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getEstatSolicitudServeiID() {
		return(estatSolicitudServeiID);
	};
	public void setEstatSolicitudServeiID(long _estatSolicitudServeiID_) {
		this.estatSolicitudServeiID = _estatSolicitudServeiID_;
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
    if (__obj != null && __obj instanceof EstatSolicitudServei) {
      EstatSolicitudServei __instance = (EstatSolicitudServei)__obj;
      __result = true;
      __result = __result && (this.getEstatSolicitudServeiID() == __instance.getEstatSolicitudServeiID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:estatsolicitudserveiid | Table: pad_solicitudservei | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estatSolicitudServei")
    private Set<SolicitudServeiJPA> solicitudServeis = new HashSet<SolicitudServeiJPA>(0);
    public  Set<SolicitudServeiJPA> getSolicitudServeis() {
    return this.solicitudServeis;
  }

    public void setSolicitudServeis(Set<SolicitudServeiJPA> solicitudServeis) {
      this.solicitudServeis = solicitudServeis;
    }



 // ---------------  STATIC METHODS ------------------
  public static EstatSolicitudServeiJPA toJPA(EstatSolicitudServei __bean) {
    if (__bean == null) { return null;}
    EstatSolicitudServeiJPA __tmp = new EstatSolicitudServeiJPA();
    __tmp.setEstatSolicitudServeiID(__bean.getEstatSolicitudServeiID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}


  public static EstatSolicitudServeiJPA copyJPA(EstatSolicitudServeiJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EstatSolicitudServeiJPA> copyJPA(java.util.Set<EstatSolicitudServeiJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EstatSolicitudServeiJPA> __tmpSet = (java.util.Set<EstatSolicitudServeiJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EstatSolicitudServeiJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EstatSolicitudServeiJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EstatSolicitudServeiJPA copyJPA(EstatSolicitudServeiJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EstatSolicitudServeiJPA __tmp = (EstatSolicitudServeiJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"SolicitudServeiJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitudServeis) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitudServeis())) ) {
      __tmp.setSolicitudServeis(SolicitudServeiJPA.copyJPA(__jpa.getSolicitudServeis(), __alreadyCopied,"EstatSolicitudServeiJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
