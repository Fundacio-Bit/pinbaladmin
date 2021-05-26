
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
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pad_solicitudservei"  , uniqueConstraints = {
            @UniqueConstraint( columnNames={"serveiid","solicitudid"}) } )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class SolicitudServeiJPA implements SolicitudServei {



private static final long serialVersionUID = 1197229085L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINBALADMIN_SEQ")
	@Index(name="pad_solicitudservei_pk_i")
	@Column(name="id",nullable = false,length = 19)
	long id;

	@Index(name="pad_soliservei_soliid_fk_i")
	@Column(name="solicitudid",nullable = false,length = 19)
	long solicitudID;

	@Index(name="pad_soliservei_serveiid_fk_i")
	@Column(name="serveiid",nullable = false,length = 19)
	long serveiID;

	@Index(name="pad_soliservei_estsolserv_fk_i")
	@Column(name="estatsolicitudserveiid",nullable = false,length = 19)
	java.lang.Long estatSolicitudServeiID;

	@Column(name="normalegal",length = 3000)
	java.lang.String normaLegal;

	@Column(name="enllaznormalegal",length = 255)
	java.lang.String enllazNormaLegal;

	@Column(name="articles",length = 255)
	java.lang.String articles;

	@Column(name="tipusconsentiment",length = 255)
	java.lang.String tipusConsentiment;

	@Column(name="consentiment",length = 255)
	java.lang.String consentiment;

	@Column(name="enllazconsentiment",length = 255)
	java.lang.String enllazConsentiment;

	@Column(name="notes",length = 2000)
	java.lang.String notes;



  /** Constructor Buit */
  public SolicitudServeiJPA() {
  }

  /** Constructor amb tots els camps  */
  public SolicitudServeiJPA(long id , long solicitudID , long serveiID , java.lang.Long estatSolicitudServeiID , java.lang.String normaLegal , java.lang.String enllazNormaLegal , java.lang.String articles , java.lang.String tipusConsentiment , java.lang.String consentiment , java.lang.String enllazConsentiment , java.lang.String notes) {
    this.id=id;
    this.solicitudID=solicitudID;
    this.serveiID=serveiID;
    this.estatSolicitudServeiID=estatSolicitudServeiID;
    this.normaLegal=normaLegal;
    this.enllazNormaLegal=enllazNormaLegal;
    this.articles=articles;
    this.tipusConsentiment=tipusConsentiment;
    this.consentiment=consentiment;
    this.enllazConsentiment=enllazConsentiment;
    this.notes=notes;
}
  /** Constructor sense valors autoincrementals */
  public SolicitudServeiJPA(long solicitudID , long serveiID , java.lang.Long estatSolicitudServeiID , java.lang.String normaLegal , java.lang.String enllazNormaLegal , java.lang.String articles , java.lang.String tipusConsentiment , java.lang.String consentiment , java.lang.String enllazConsentiment , java.lang.String notes) {
    this.solicitudID=solicitudID;
    this.serveiID=serveiID;
    this.estatSolicitudServeiID=estatSolicitudServeiID;
    this.normaLegal=normaLegal;
    this.enllazNormaLegal=enllazNormaLegal;
    this.articles=articles;
    this.tipusConsentiment=tipusConsentiment;
    this.consentiment=consentiment;
    this.enllazConsentiment=enllazConsentiment;
    this.notes=notes;
}
  /** Constructor dels valors Not Null */
  public SolicitudServeiJPA(long id , long solicitudID , long serveiID , java.lang.Long estatSolicitudServeiID) {
    this.id=id;
    this.solicitudID=solicitudID;
    this.serveiID=serveiID;
    this.estatSolicitudServeiID=estatSolicitudServeiID;
}
  public SolicitudServeiJPA(SolicitudServei __bean) {
    this.setId(__bean.getId());
    this.setSolicitudID(__bean.getSolicitudID());
    this.setServeiID(__bean.getServeiID());
    this.setEstatSolicitudServeiID(__bean.getEstatSolicitudServeiID());
    this.setNormaLegal(__bean.getNormaLegal());
    this.setEnllazNormaLegal(__bean.getEnllazNormaLegal());
    this.setArticles(__bean.getArticles());
    this.setTipusConsentiment(__bean.getTipusConsentiment());
    this.setConsentiment(__bean.getConsentiment());
    this.setEnllazConsentiment(__bean.getEnllazConsentiment());
    this.setNotes(__bean.getNotes());
	}

	public long getId() {
		return(id);
	};
	public void setId(long _id_) {
		this.id = _id_;
	};

	public long getSolicitudID() {
		return(solicitudID);
	};
	public void setSolicitudID(long _solicitudID_) {
		this.solicitudID = _solicitudID_;
	};

	public long getServeiID() {
		return(serveiID);
	};
	public void setServeiID(long _serveiID_) {
		this.serveiID = _serveiID_;
	};

	public java.lang.Long getEstatSolicitudServeiID() {
		return(estatSolicitudServeiID);
	};
	public void setEstatSolicitudServeiID(java.lang.Long _estatSolicitudServeiID_) {
		this.estatSolicitudServeiID = _estatSolicitudServeiID_;
	};

	public java.lang.String getNormaLegal() {
		return(normaLegal);
	};
	public void setNormaLegal(java.lang.String _normaLegal_) {
		this.normaLegal = _normaLegal_;
	};

	public java.lang.String getEnllazNormaLegal() {
		return(enllazNormaLegal);
	};
	public void setEnllazNormaLegal(java.lang.String _enllazNormaLegal_) {
		this.enllazNormaLegal = _enllazNormaLegal_;
	};

	public java.lang.String getArticles() {
		return(articles);
	};
	public void setArticles(java.lang.String _articles_) {
		this.articles = _articles_;
	};

	public java.lang.String getTipusConsentiment() {
		return(tipusConsentiment);
	};
	public void setTipusConsentiment(java.lang.String _tipusConsentiment_) {
		this.tipusConsentiment = _tipusConsentiment_;
	};

	public java.lang.String getConsentiment() {
		return(consentiment);
	};
	public void setConsentiment(java.lang.String _consentiment_) {
		this.consentiment = _consentiment_;
	};

	public java.lang.String getEnllazConsentiment() {
		return(enllazConsentiment);
	};
	public void setEnllazConsentiment(java.lang.String _enllazConsentiment_) {
		this.enllazConsentiment = _enllazConsentiment_;
	};

	public java.lang.String getNotes() {
		return(notes);
	};
	public void setNotes(java.lang.String _notes_) {
		this.notes = _notes_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof SolicitudServei) {
      SolicitudServei __instance = (SolicitudServei)__obj;
      __result = true;
      __result = __result && (this.getId() == __instance.getId()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:solicitudserveiid | Table: pad_campsolicitud | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitudServei")
	private Set<CampSolicitudJPA> campSolicituds = new HashSet<CampSolicitudJPA>(0);
	public  Set<CampSolicitudJPA> getCampSolicituds() {
    return this.campSolicituds;
  }

	public void setCampSolicituds(Set<CampSolicitudJPA> campSolicituds) {
	  this.campSolicituds = campSolicituds;
	}


// IMP Field:solicitudid | Table: pad_solicitud | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_soliservei_solicitud_fk")
	@JoinColumn(name = "solicitudid", referencedColumnName ="solicitudID", nullable = false, insertable=false, updatable=false)
	private SolicitudJPA solicitud;

	public SolicitudJPA getSolicitud() {
    return this.solicitud;
  }

	public  void setSolicitud(SolicitudJPA solicitud) {
    this.solicitud = solicitud;
  }

// IMP Field:serveiid | Table: pad_servei | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_soliservei_servei_fk")
	@JoinColumn(name = "serveiid", referencedColumnName ="serveiID", nullable = false, insertable=false, updatable=false)
	private ServeiJPA servei;

	public ServeiJPA getServei() {
    return this.servei;
  }

	public  void setServei(ServeiJPA servei) {
    this.servei = servei;
  }

// IMP Field:estatsolicitudserveiid | Table: pad_estatsolicitudservei | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_soliservei_estsolserv_fk")
	@JoinColumn(name = "estatsolicitudserveiid", referencedColumnName ="estatSolicitudServeiID", nullable = false, insertable=false, updatable=false)
	private EstatSolicitudServeiJPA estatSolicitudServei;

	public EstatSolicitudServeiJPA getEstatSolicitudServei() {
    return this.estatSolicitudServei;
  }

	public  void setEstatSolicitudServei(EstatSolicitudServeiJPA estatSolicitudServei) {
    this.estatSolicitudServei = estatSolicitudServei;
  }


 // ---------------  STATIC METHODS ------------------
  public static SolicitudServeiJPA toJPA(SolicitudServei __bean) {
    if (__bean == null) { return null;}
    SolicitudServeiJPA __tmp = new SolicitudServeiJPA();
    __tmp.setId(__bean.getId());
    __tmp.setSolicitudID(__bean.getSolicitudID());
    __tmp.setServeiID(__bean.getServeiID());
    __tmp.setEstatSolicitudServeiID(__bean.getEstatSolicitudServeiID());
    __tmp.setNormaLegal(__bean.getNormaLegal());
    __tmp.setEnllazNormaLegal(__bean.getEnllazNormaLegal());
    __tmp.setArticles(__bean.getArticles());
    __tmp.setTipusConsentiment(__bean.getTipusConsentiment());
    __tmp.setConsentiment(__bean.getConsentiment());
    __tmp.setEnllazConsentiment(__bean.getEnllazConsentiment());
    __tmp.setNotes(__bean.getNotes());
		return __tmp;
	}


  public static SolicitudServeiJPA copyJPA(SolicitudServeiJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<SolicitudServeiJPA> copyJPA(java.util.Set<SolicitudServeiJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<SolicitudServeiJPA> __tmpSet = (java.util.Set<SolicitudServeiJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<SolicitudServeiJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (SolicitudServeiJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static SolicitudServeiJPA copyJPA(SolicitudServeiJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    SolicitudServeiJPA __tmp = (SolicitudServeiJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"CampSolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.campSolicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getCampSolicituds())) ) {
      __tmp.setCampSolicituds(CampSolicitudJPA.copyJPA(__jpa.getCampSolicituds(), __alreadyCopied,"SolicitudServeiJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"ServeiJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.servei) || org.hibernate.Hibernate.isInitialized(__jpa.getServei()) ) ) {
      __tmp.setServei(ServeiJPA.copyJPA(__jpa.getServei(), __alreadyCopied,"SolicitudServeiJPA"));
    }
    if(!"SolicitudJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitud) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitud()) ) ) {
      __tmp.setSolicitud(SolicitudJPA.copyJPA(__jpa.getSolicitud(), __alreadyCopied,"SolicitudServeiJPA"));
    }
    if(!"EstatSolicitudServeiJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.estatSolicitudServei) || org.hibernate.Hibernate.isInitialized(__jpa.getEstatSolicitudServei()) ) ) {
      __tmp.setEstatSolicitudServei(EstatSolicitudServeiJPA.copyJPA(__jpa.getEstatSolicitudServei(), __alreadyCopied,"SolicitudServeiJPA"));
    }

    return __tmp;
  }




}
