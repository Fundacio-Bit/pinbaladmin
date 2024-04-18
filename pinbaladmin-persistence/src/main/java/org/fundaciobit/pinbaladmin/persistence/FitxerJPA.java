
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "FitxerJPA")
@Table(name = "pad_fitxer" , indexes = { 
        @Index(name="pad_fitxer_pk_i", columnList = "fitxerid")})
@SequenceGenerator(name="FITXER_SEQ", sequenceName="pad_fitxer_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class FitxerJPA implements Fitxer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FITXER_SEQ")
    @Column(name="fitxerid",nullable = false,length = 19)
    long fitxerID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="tamany",nullable = false,length = 19)
    long tamany;

    @Column(name="mime",nullable = false,length = 100)
    java.lang.String mime;

    @Column(name="descripcio",length = 1000)
    java.lang.String descripcio;



  /** Constructor Buit */
  public FitxerJPA() {
  }

  /** Constructor amb tots els camps  */
  public FitxerJPA(long fitxerID , java.lang.String nom , long tamany , java.lang.String mime , java.lang.String descripcio) {
    this.fitxerID=fitxerID;
    this.nom=nom;
    this.tamany=tamany;
    this.mime=mime;
    this.descripcio=descripcio;
}
  /** Constructor sense valors autoincrementals */
  public FitxerJPA(java.lang.String nom , long tamany , java.lang.String mime , java.lang.String descripcio) {
    this.nom=nom;
    this.tamany=tamany;
    this.mime=mime;
    this.descripcio=descripcio;
}
  public FitxerJPA(Fitxer __bean) {
    this.setFitxerID(__bean.getFitxerID());
    this.setNom(__bean.getNom());
    this.setTamany(__bean.getTamany());
    this.setMime(__bean.getMime());
    this.setDescripcio(__bean.getDescripcio());
    // DataHandler
    this.setData(__bean.getData());
    // EncryptedFileID
    this.setEncryptedFileID(__bean.getEncryptedFileID());
	}

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public long getTamany() {
		return(tamany);
	};
	public void setTamany(long _tamany_) {
		this.tamany = _tamany_;
	};

	public java.lang.String getMime() {
		return(mime);
	};
	public void setMime(java.lang.String _mime_) {
		this.mime = _mime_;
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
    if (__obj != null && __obj instanceof Fitxer) {
      Fitxer __instance = (Fitxer)__obj;
      __result = true;
      __result = __result && (this.getFitxerID() == __instance.getFitxerID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:fitxerfirmatid | Table: pad_document | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerFirmatID")
    private Set<DocumentJPA> document_fitxerfirmatids = new HashSet<DocumentJPA>(0);
    public  Set<DocumentJPA> getDocument_fitxerfirmatids() {
    return this.document_fitxerfirmatids;
  }

    public void setDocument_fitxerfirmatids(Set<DocumentJPA> document_fitxerfirmatids) {
      this.document_fitxerfirmatids = document_fitxerfirmatids;
    }


// EXP  Field:fitxeroriginalid | Table: pad_document | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerOriginalID")
    private Set<DocumentJPA> document_fitxeroriginalids = new HashSet<DocumentJPA>(0);
    public  Set<DocumentJPA> getDocument_fitxeroriginalids() {
    return this.document_fitxeroriginalids;
  }

    public void setDocument_fitxeroriginalids(Set<DocumentJPA> document_fitxeroriginalids) {
      this.document_fitxeroriginalids = document_fitxeroriginalids;
    }


// EXP  Field:fitxerid | Table: pad_documentcedent | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerID")
    private Set<DocumentCedentJPA> documentCedents = new HashSet<DocumentCedentJPA>(0);
    public  Set<DocumentCedentJPA> getDocumentCedents() {
    return this.documentCedents;
  }

    public void setDocumentCedents(Set<DocumentCedentJPA> documentCedents) {
      this.documentCedents = documentCedents;
    }


// EXP  Field:fitxerid | Table: pad_documententitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerID")
    private Set<DocumentEntitatJPA> documentEntitats = new HashSet<DocumentEntitatJPA>(0);
    public  Set<DocumentEntitatJPA> getDocumentEntitats() {
    return this.documentEntitats;
  }

    public void setDocumentEntitats(Set<DocumentEntitatJPA> documentEntitats) {
      this.documentEntitats = documentEntitats;
    }


// EXP  Field:fitxerid | Table: pad_event | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerID")
    private Set<EventJPA> events = new HashSet<EventJPA>(0);
    public  Set<EventJPA> getEvents() {
    return this.events;
  }

    public void setEvents(Set<EventJPA> events) {
      this.events = events;
    }


// EXP  Field:fitxerid | Table: pad_formulari | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerID")
    private Set<FormulariJPA> formularis = new HashSet<FormulariJPA>(0);
    public  Set<FormulariJPA> getFormularis() {
    return this.formularis;
  }

    public void setFormularis(Set<FormulariJPA> formularis) {
      this.formularis = formularis;
    }


// EXP  Field:documentsolicitudid | Table: pad_solicitud | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "documentSolicitudID")
    private Set<SolicitudJPA> solicitud_documentsolicitudids = new HashSet<SolicitudJPA>(0);
    public  Set<SolicitudJPA> getSolicitud_documentsolicitudids() {
    return this.solicitud_documentsolicitudids;
  }

    public void setSolicitud_documentsolicitudids(Set<SolicitudJPA> solicitud_documentsolicitudids) {
      this.solicitud_documentsolicitudids = solicitud_documentsolicitudids;
    }


// EXP  Field:solicitudxmlid | Table: pad_solicitud | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitudXmlID")
    private Set<SolicitudJPA> solicitud_solicitudxmlids = new HashSet<SolicitudJPA>(0);
    public  Set<SolicitudJPA> getSolicitud_solicitudxmlids() {
    return this.solicitud_solicitudxmlids;
  }

    public void setSolicitud_solicitudxmlids(Set<SolicitudJPA> solicitud_solicitudxmlids) {
      this.solicitud_solicitudxmlids = solicitud_solicitudxmlids;
    }


// EXP  Field:fitxernormaid | Table: pad_solicitudservei | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxernormaID")
    private Set<SolicitudServeiJPA> solicitudServei_fitxernormaids = new HashSet<SolicitudServeiJPA>(0);
    public  Set<SolicitudServeiJPA> getSolicitudServei_fitxernormaids() {
    return this.solicitudServei_fitxernormaids;
  }

    public void setSolicitudServei_fitxernormaids(Set<SolicitudServeiJPA> solicitudServei_fitxernormaids) {
      this.solicitudServei_fitxernormaids = solicitudServei_fitxernormaids;
    }


// EXP  Field:fitxernorma2id | Table: pad_solicitudservei | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxernorma2ID")
    private Set<SolicitudServeiJPA> solicitudServei_fitxernorma2ids = new HashSet<SolicitudServeiJPA>(0);
    public  Set<SolicitudServeiJPA> getSolicitudServei_fitxernorma2ids() {
    return this.solicitudServei_fitxernorma2ids;
  }

    public void setSolicitudServei_fitxernorma2ids(Set<SolicitudServeiJPA> solicitudServei_fitxernorma2ids) {
      this.solicitudServei_fitxernorma2ids = solicitudServei_fitxernorma2ids;
    }


// EXP  Field:fitxernorma3id | Table: pad_solicitudservei | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxernorma3ID")
    private Set<SolicitudServeiJPA> solicitudServei_fitxernorma3ids = new HashSet<SolicitudServeiJPA>(0);
    public  Set<SolicitudServeiJPA> getSolicitudServei_fitxernorma3ids() {
    return this.solicitudServei_fitxernorma3ids;
  }

    public void setSolicitudServei_fitxernorma3ids(Set<SolicitudServeiJPA> solicitudServei_fitxernorma3ids) {
      this.solicitudServei_fitxernorma3ids = solicitudServei_fitxernorma3ids;
    }


// EXP  Field:adjunt1id | Table: pad_tiquet | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "adjunt1ID")
    private Set<TiquetJPA> tiquet_adjunt1ids = new HashSet<TiquetJPA>(0);
    public  Set<TiquetJPA> getTiquet_adjunt1ids() {
    return this.tiquet_adjunt1ids;
  }

    public void setTiquet_adjunt1ids(Set<TiquetJPA> tiquet_adjunt1ids) {
      this.tiquet_adjunt1ids = tiquet_adjunt1ids;
    }


// EXP  Field:adjunt2id | Table: pad_tiquet | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "adjunt2ID")
    private Set<TiquetJPA> tiquet_adjunt2ids = new HashSet<TiquetJPA>(0);
    public  Set<TiquetJPA> getTiquet_adjunt2ids() {
    return this.tiquet_adjunt2ids;
  }

    public void setTiquet_adjunt2ids(Set<TiquetJPA> tiquet_adjunt2ids) {
      this.tiquet_adjunt2ids = tiquet_adjunt2ids;
    }


// EXP  Field:fitxernorma2id | Table: pad_tramit_i_serv | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxernorma2ID")
    private Set<TramitIServJPA> tramitIServ_fitxernorma2ids = new HashSet<TramitIServJPA>(0);
    public  Set<TramitIServJPA> getTramitIServ_fitxernorma2ids() {
    return this.tramitIServ_fitxernorma2ids;
  }

    public void setTramitIServ_fitxernorma2ids(Set<TramitIServJPA> tramitIServ_fitxernorma2ids) {
      this.tramitIServ_fitxernorma2ids = tramitIServ_fitxernorma2ids;
    }


// EXP  Field:fitxernorma3id | Table: pad_tramit_i_serv | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxernorma3ID")
    private Set<TramitIServJPA> tramitIServ_fitxernorma3ids = new HashSet<TramitIServJPA>(0);
    public  Set<TramitIServJPA> getTramitIServ_fitxernorma3ids() {
    return this.tramitIServ_fitxernorma3ids;
  }

    public void setTramitIServ_fitxernorma3ids(Set<TramitIServJPA> tramitIServ_fitxernorma3ids) {
      this.tramitIServ_fitxernorma3ids = tramitIServ_fitxernorma3ids;
    }


// EXP  Field:fitxernormaid | Table: pad_tramit_i_serv | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxernormaID")
    private Set<TramitIServJPA> tramitIServ_fitxernormaids = new HashSet<TramitIServJPA>(0);
    public  Set<TramitIServJPA> getTramitIServ_fitxernormaids() {
    return this.tramitIServ_fitxernormaids;
  }

    public void setTramitIServ_fitxernormaids(Set<TramitIServJPA> tramitIServ_fitxernormaids) {
      this.tramitIServ_fitxernormaids = tramitIServ_fitxernormaids;
    }


// EXP  Field:adjuntid | Table: pad_tramit_j_consent | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "adjuntID")
    private Set<TramitJConsentJPA> tramitJConsents = new HashSet<TramitJConsentJPA>(0);
    public  Set<TramitJConsentJPA> getTramitJConsents() {
    return this.tramitJConsents;
  }

    public void setTramitJConsents(Set<TramitJConsentJPA> tramitJConsents) {
      this.tramitJConsents = tramitJConsents;
    }



  @javax.persistence.Transient
  javax.activation.DataHandler data;

  public javax.activation.DataHandler getData() {
    return data;
  }

  public void setData(javax.activation.DataHandler data) {
    this.data = data;
  }

  @javax.persistence.Transient
  String encryptedFileID;

  public String getEncryptedFileID() {
    return encryptedFileID;
  }

  public void setEncryptedFileID(String encryptedFileID) {
    this.encryptedFileID = encryptedFileID;
  }


  final static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

  public static void enableEncryptedFileIDGeneration() {
    threadLocal.set("");
  }

  public static void disableEncryptedFileIDGeneration() {
    threadLocal.remove();
  }

  @javax.persistence.PostPersist
  @javax.persistence.PostLoad
  void postLoad() {
    if (threadLocal.get() != null) {
      this.encryptedFileID = org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil.encryptFileID(fitxerID);
    }
  }


 // ---------------  STATIC METHODS ------------------
  public static FitxerJPA toJPA(Fitxer __bean) {
    if (__bean == null) { return null;}
    FitxerJPA __tmp = new FitxerJPA();
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setNom(__bean.getNom());
    __tmp.setTamany(__bean.getTamany());
    __tmp.setMime(__bean.getMime());
    __tmp.setDescripcio(__bean.getDescripcio());
    // DataHandler
    __tmp.setData(__bean.getData());
    // EncryptedFileID
    __tmp.setEncryptedFileID(__bean.getEncryptedFileID());
		return __tmp;
	}


  public static FitxerJPA copyJPA(FitxerJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<FitxerJPA> copyJPA(java.util.Set<FitxerJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<FitxerJPA> __tmpSet = (java.util.Set<FitxerJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<FitxerJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (FitxerJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static FitxerJPA copyJPA(FitxerJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    FitxerJPA __tmp = (FitxerJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"TramitIServJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitIServ_fitxernormaids) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitIServ_fitxernormaids())) ) {
      __tmp.setTramitIServ_fitxernormaids(TramitIServJPA.copyJPA(__jpa.getTramitIServ_fitxernormaids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"DocumentEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.documentEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getDocumentEntitats())) ) {
      __tmp.setDocumentEntitats(DocumentEntitatJPA.copyJPA(__jpa.getDocumentEntitats(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"FormulariJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.formularis) || org.hibernate.Hibernate.isInitialized(__jpa.getFormularis())) ) {
      __tmp.setFormularis(FormulariJPA.copyJPA(__jpa.getFormularis(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"SolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitud_solicitudxmlids) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitud_solicitudxmlids())) ) {
      __tmp.setSolicitud_solicitudxmlids(SolicitudJPA.copyJPA(__jpa.getSolicitud_solicitudxmlids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"SolicitudServeiJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitudServei_fitxernorma2ids) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitudServei_fitxernorma2ids())) ) {
      __tmp.setSolicitudServei_fitxernorma2ids(SolicitudServeiJPA.copyJPA(__jpa.getSolicitudServei_fitxernorma2ids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"SolicitudServeiJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitudServei_fitxernorma3ids) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitudServei_fitxernorma3ids())) ) {
      __tmp.setSolicitudServei_fitxernorma3ids(SolicitudServeiJPA.copyJPA(__jpa.getSolicitudServei_fitxernorma3ids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"DocumentCedentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.documentCedents) || org.hibernate.Hibernate.isInitialized(__jpa.getDocumentCedents())) ) {
      __tmp.setDocumentCedents(DocumentCedentJPA.copyJPA(__jpa.getDocumentCedents(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"DocumentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.document_fitxeroriginalids) || org.hibernate.Hibernate.isInitialized(__jpa.getDocument_fitxeroriginalids())) ) {
      __tmp.setDocument_fitxeroriginalids(DocumentJPA.copyJPA(__jpa.getDocument_fitxeroriginalids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"TramitJConsentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitJConsents) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitJConsents())) ) {
      __tmp.setTramitJConsents(TramitJConsentJPA.copyJPA(__jpa.getTramitJConsents(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"SolicitudServeiJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitudServei_fitxernormaids) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitudServei_fitxernormaids())) ) {
      __tmp.setSolicitudServei_fitxernormaids(SolicitudServeiJPA.copyJPA(__jpa.getSolicitudServei_fitxernormaids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"DocumentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.document_fitxerfirmatids) || org.hibernate.Hibernate.isInitialized(__jpa.getDocument_fitxerfirmatids())) ) {
      __tmp.setDocument_fitxerfirmatids(DocumentJPA.copyJPA(__jpa.getDocument_fitxerfirmatids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"TramitIServJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitIServ_fitxernorma2ids) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitIServ_fitxernorma2ids())) ) {
      __tmp.setTramitIServ_fitxernorma2ids(TramitIServJPA.copyJPA(__jpa.getTramitIServ_fitxernorma2ids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"TramitIServJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitIServ_fitxernorma3ids) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitIServ_fitxernorma3ids())) ) {
      __tmp.setTramitIServ_fitxernorma3ids(TramitIServJPA.copyJPA(__jpa.getTramitIServ_fitxernorma3ids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"SolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitud_documentsolicitudids) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitud_documentsolicitudids())) ) {
      __tmp.setSolicitud_documentsolicitudids(SolicitudJPA.copyJPA(__jpa.getSolicitud_documentsolicitudids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"EventJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.events) || org.hibernate.Hibernate.isInitialized(__jpa.getEvents())) ) {
      __tmp.setEvents(EventJPA.copyJPA(__jpa.getEvents(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"TiquetJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tiquet_adjunt1ids) || org.hibernate.Hibernate.isInitialized(__jpa.getTiquet_adjunt1ids())) ) {
      __tmp.setTiquet_adjunt1ids(TiquetJPA.copyJPA(__jpa.getTiquet_adjunt1ids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"TiquetJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tiquet_adjunt2ids) || org.hibernate.Hibernate.isInitialized(__jpa.getTiquet_adjunt2ids())) ) {
      __tmp.setTiquet_adjunt2ids(TiquetJPA.copyJPA(__jpa.getTiquet_adjunt2ids(), __alreadyCopied,"FitxerJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
