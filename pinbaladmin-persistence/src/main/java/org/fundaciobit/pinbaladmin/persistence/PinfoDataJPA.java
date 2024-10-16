
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


@Entity(name = "PinfoDataJPA")
@Table(name = "pad_pinfodata" , indexes = { 
        @Index(name="pad_pinfodata_pk_i", columnList = "pinfodataid"),
        @Index(name="pad_pinfodata_pinfoid_fk_i", columnList = "pinfoid"),
        @Index(name="pad_pinfodata_soli_fk_i", columnList = "procedimentid"),
        @Index(name="pad_pinfodata_serveiid_fk_i", columnList = "serveiid")})
@SequenceGenerator(name="PINFODATA_SEQ", sequenceName="pad_pinfodata_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PinfoDataJPA implements PinfoData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINFODATA_SEQ")
    @Column(name="pinfodataid",nullable = false,length = 19)
    long pinfodataID;

    @Column(name="pinfoid",length = 19)
    java.lang.Long pinfoID;

    @Column(name="estat",length = 19)
    java.lang.Long estat;

    @Column(name="usuariid",length = 200)
    java.lang.String usuariid;

    @Column(name="procedimentid",length = 19)
    java.lang.Long procedimentID;

    @Column(name="serveiid",length = 19)
    java.lang.Long serveiID;

    @Column(name="alta",length = 19)
    java.lang.Long alta;



  /** Constructor Buit */
  public PinfoDataJPA() {
  }

  /** Constructor amb tots els camps  */
  public PinfoDataJPA(long pinfodataID , java.lang.Long pinfoID , java.lang.Long estat , java.lang.String usuariid , java.lang.Long procedimentID , java.lang.Long serveiID , java.lang.Long alta) {
    this.pinfodataID=pinfodataID;
    this.pinfoID=pinfoID;
    this.estat=estat;
    this.usuariid=usuariid;
    this.procedimentID=procedimentID;
    this.serveiID=serveiID;
    this.alta=alta;
}
  /** Constructor sense valors autoincrementals */
  public PinfoDataJPA(java.lang.Long pinfoID , java.lang.Long estat , java.lang.String usuariid , java.lang.Long procedimentID , java.lang.Long serveiID , java.lang.Long alta) {
    this.pinfoID=pinfoID;
    this.estat=estat;
    this.usuariid=usuariid;
    this.procedimentID=procedimentID;
    this.serveiID=serveiID;
    this.alta=alta;
}
  /** Constructor dels valors Not Null */
  public PinfoDataJPA(long pinfodataID) {
    this.pinfodataID=pinfodataID;
}
  public PinfoDataJPA(PinfoData __bean) {
    this.setPinfodataID(__bean.getPinfodataID());
    this.setPinfoID(__bean.getPinfoID());
    this.setEstat(__bean.getEstat());
    this.setUsuariid(__bean.getUsuariid());
    this.setProcedimentID(__bean.getProcedimentID());
    this.setServeiID(__bean.getServeiID());
    this.setAlta(__bean.getAlta());
	}

	public long getPinfodataID() {
		return(pinfodataID);
	};
	public void setPinfodataID(long _pinfodataID_) {
		this.pinfodataID = _pinfodataID_;
	};

	public java.lang.Long getPinfoID() {
		return(pinfoID);
	};
	public void setPinfoID(java.lang.Long _pinfoID_) {
		this.pinfoID = _pinfoID_;
	};

	public java.lang.Long getEstat() {
		return(estat);
	};
	public void setEstat(java.lang.Long _estat_) {
		this.estat = _estat_;
	};

	public java.lang.String getUsuariid() {
		return(usuariid);
	};
	public void setUsuariid(java.lang.String _usuariid_) {
		this.usuariid = _usuariid_;
	};

	public java.lang.Long getProcedimentID() {
		return(procedimentID);
	};
	public void setProcedimentID(java.lang.Long _procedimentID_) {
		this.procedimentID = _procedimentID_;
	};

	public java.lang.Long getServeiID() {
		return(serveiID);
	};
	public void setServeiID(java.lang.Long _serveiID_) {
		this.serveiID = _serveiID_;
	};

	public java.lang.Long getAlta() {
		return(alta);
	};
	public void setAlta(java.lang.Long _alta_) {
		this.alta = _alta_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PinfoData) {
      PinfoData __instance = (PinfoData)__obj;
      __result = true;
      __result = __result && (this.getPinfodataID() == __instance.getPinfodataID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:pinfoid | Table: pad_pinfo | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pinfoid", referencedColumnName ="PinfoID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_pfdat_pinfo_pinfoid_fk"))
    private PINFOJPA pINFO;

    public PINFOJPA getPINFO() {
    return this.pINFO;
  }

    public  void setPINFO(PINFOJPA pINFO) {
    this.pINFO = pINFO;
  }

// IMP Field:solicitudid | Table: pad_solicitud | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procedimentid", referencedColumnName ="solicitudID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_pfdat_solicitud_id_fk"))
    private SolicitudJPA solicitud;

    public SolicitudJPA getSolicitud() {
    return this.solicitud;
  }

    public  void setSolicitud(SolicitudJPA solicitud) {
    this.solicitud = solicitud;
  }

// IMP Field:serveiid | Table: pad_servei | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serveiid", referencedColumnName ="serveiID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_pfdat_servei_serveiid_fk"))
    private ServeiJPA servei;

    public ServeiJPA getServei() {
    return this.servei;
  }

    public  void setServei(ServeiJPA servei) {
    this.servei = servei;
  }


 // ---------------  STATIC METHODS ------------------
  public static PinfoDataJPA toJPA(PinfoData __bean) {
    if (__bean == null) { return null;}
    PinfoDataJPA __tmp = new PinfoDataJPA();
    __tmp.setPinfodataID(__bean.getPinfodataID());
    __tmp.setPinfoID(__bean.getPinfoID());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setUsuariid(__bean.getUsuariid());
    __tmp.setProcedimentID(__bean.getProcedimentID());
    __tmp.setServeiID(__bean.getServeiID());
    __tmp.setAlta(__bean.getAlta());
		return __tmp;
	}


  public static PinfoDataJPA copyJPA(PinfoDataJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PinfoDataJPA> copyJPA(java.util.Set<PinfoDataJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PinfoDataJPA> __tmpSet = (java.util.Set<PinfoDataJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PinfoDataJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PinfoDataJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PinfoDataJPA copyJPA(PinfoDataJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PinfoDataJPA __tmp = (PinfoDataJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"SolicitudJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitud) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitud()) ) ) {
      __tmp.setSolicitud(SolicitudJPA.copyJPA(__jpa.getSolicitud(), __alreadyCopied,"PinfoDataJPA"));
    }
    if(!"PINFOJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pINFO) || org.hibernate.Hibernate.isInitialized(__jpa.getPINFO()) ) ) {
      __tmp.setPINFO(PINFOJPA.copyJPA(__jpa.getPINFO(), __alreadyCopied,"PinfoDataJPA"));
    }
    if(!"ServeiJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.servei) || org.hibernate.Hibernate.isInitialized(__jpa.getServei()) ) ) {
      __tmp.setServei(ServeiJPA.copyJPA(__jpa.getServei(), __alreadyCopied,"PinfoDataJPA"));
    }

    return __tmp;
  }




}
