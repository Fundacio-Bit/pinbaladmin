
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "CampSolicitudJPA")
@Table(name = "pad_campsolicitud" , indexes = { 
        @Index(name="pad_campsolicitud_pk_i", columnList = "campsolicitudid"),
        @Index(name="pad_campsoli_campformid_fk_i", columnList = "campformulariid"),
        @Index(name="pad_campsoli_soliservid_fk_i", columnList = "solicitudserveiid")})
@SequenceGenerator(name="CAMPSOLICITUD_SEQ", sequenceName="pad_campsolicitud_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class CampSolicitudJPA implements CampSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CAMPSOLICITUD_SEQ")
    @Column(name="campsolicitudid",nullable = false,length = 19)
    long campSolicitudID;

    @Column(name="campformulariid",nullable = false,length = 19)
    long campFormulariID;

    @Column(name="solicitudserveiid",nullable = false,length = 19)
    long solicitudServeiID;

    @Column(name="valor",nullable = false,length = 2000)
    java.lang.String valor;



  /** Constructor Buit */
  public CampSolicitudJPA() {
  }

  /** Constructor amb tots els camps  */
  public CampSolicitudJPA(long campSolicitudID , long campFormulariID , long solicitudServeiID , java.lang.String valor) {
    this.campSolicitudID=campSolicitudID;
    this.campFormulariID=campFormulariID;
    this.solicitudServeiID=solicitudServeiID;
    this.valor=valor;
}
  /** Constructor sense valors autoincrementals */
  public CampSolicitudJPA(long campFormulariID , long solicitudServeiID , java.lang.String valor) {
    this.campFormulariID=campFormulariID;
    this.solicitudServeiID=solicitudServeiID;
    this.valor=valor;
}
  public CampSolicitudJPA(CampSolicitud __bean) {
    this.setCampSolicitudID(__bean.getCampSolicitudID());
    this.setCampFormulariID(__bean.getCampFormulariID());
    this.setSolicitudServeiID(__bean.getSolicitudServeiID());
    this.setValor(__bean.getValor());
	}

	public long getCampSolicitudID() {
		return(campSolicitudID);
	};
	public void setCampSolicitudID(long _campSolicitudID_) {
		this.campSolicitudID = _campSolicitudID_;
	};

	public long getCampFormulariID() {
		return(campFormulariID);
	};
	public void setCampFormulariID(long _campFormulariID_) {
		this.campFormulariID = _campFormulariID_;
	};

	public long getSolicitudServeiID() {
		return(solicitudServeiID);
	};
	public void setSolicitudServeiID(long _solicitudServeiID_) {
		this.solicitudServeiID = _solicitudServeiID_;
	};

	public java.lang.String getValor() {
		return(valor);
	};
	public void setValor(java.lang.String _valor_) {
		this.valor = _valor_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof CampSolicitud) {
      CampSolicitud __instance = (CampSolicitud)__obj;
      __result = true;
      __result = __result && (this.getCampSolicitudID() == __instance.getCampSolicitudID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:campformulariid | Table: pad_campformulari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campformulariid", referencedColumnName ="campFormulariID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_campsoli_campform_fk"))
    private CampFormulariJPA campFormulari;

    public CampFormulariJPA getCampFormulari() {
    return this.campFormulari;
  }

    public  void setCampFormulari(CampFormulariJPA campFormulari) {
    this.campFormulari = campFormulari;
  }

// IMP Field:id | Table: pad_solicitudservei | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitudserveiid", referencedColumnName ="id", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_campsoli_soliservei_fk"))
    private SolicitudServeiJPA solicitudServei;

    public SolicitudServeiJPA getSolicitudServei() {
    return this.solicitudServei;
  }

    public  void setSolicitudServei(SolicitudServeiJPA solicitudServei) {
    this.solicitudServei = solicitudServei;
  }


 // ---------------  STATIC METHODS ------------------
  public static CampSolicitudJPA toJPA(CampSolicitud __bean) {
    if (__bean == null) { return null;}
    CampSolicitudJPA __tmp = new CampSolicitudJPA();
    __tmp.setCampSolicitudID(__bean.getCampSolicitudID());
    __tmp.setCampFormulariID(__bean.getCampFormulariID());
    __tmp.setSolicitudServeiID(__bean.getSolicitudServeiID());
    __tmp.setValor(__bean.getValor());
		return __tmp;
	}


  public static CampSolicitudJPA copyJPA(CampSolicitudJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<CampSolicitudJPA> copyJPA(java.util.Set<CampSolicitudJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<CampSolicitudJPA> __tmpSet = (java.util.Set<CampSolicitudJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<CampSolicitudJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (CampSolicitudJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static CampSolicitudJPA copyJPA(CampSolicitudJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    CampSolicitudJPA __tmp = (CampSolicitudJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"CampFormulariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.campFormulari) || org.hibernate.Hibernate.isInitialized(__jpa.getCampFormulari()) ) ) {
      __tmp.setCampFormulari(CampFormulariJPA.copyJPA(__jpa.getCampFormulari(), __alreadyCopied,"CampSolicitudJPA"));
    }
    if(!"SolicitudServeiJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitudServei) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitudServei()) ) ) {
      __tmp.setSolicitudServei(SolicitudServeiJPA.copyJPA(__jpa.getSolicitudServei(), __alreadyCopied,"CampSolicitudJPA"));
    }

    return __tmp;
  }




}
