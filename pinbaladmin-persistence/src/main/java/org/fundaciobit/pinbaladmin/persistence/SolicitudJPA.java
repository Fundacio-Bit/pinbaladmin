
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.Type;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "SolicitudJPA")
@Table(name = "pad_solicitud" , indexes = { 
        @Index(name="pad_solicitud_pk_i", columnList = "solicitudid"),
        @Index(name="pad_solicitud_estatid_fk_i", columnList = "estatid"),
        @Index(name="pad_solicitud_organid_fk_i", columnList = "organid"),
        @Index(name="pad_solicitud_docsoli_fk_i", columnList = "documentsolicitudid"),
        @Index(name="pad_solicitud_solixml_fk_i", columnList = "solicitudxmlid")})
@SequenceGenerator(name="SOLICITUD_SEQ", sequenceName="pad_solicitud_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class SolicitudJPA implements Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SOLICITUD_SEQ")
    @Column(name="solicitudid",nullable = false,length = 19)
    long solicitudID;

    @Column(name="procedimentcodi",nullable = false,length = 255)
    java.lang.String procedimentCodi;

    @Column(name="codidescriptiu",length = 256)
    java.lang.String codiDescriptiu;

    @Column(name="procedimentnom",nullable = false,length = 2000)
    java.lang.String procedimentNom;

    @Column(name="procedimenttipus",length = 255)
    java.lang.String procedimentTipus;

    @Column(name="expedientpid",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String expedientPid;

    @Column(name="estatid",nullable = false,length = 19)
    java.lang.Long estatID;

    @Column(name="organid",length = 19)
    java.lang.Long organid;

    @Column(name="entitatestatal",length = 255)
    java.lang.String entitatEstatal;

    @Column(name="pinfo",length = 255)
    java.lang.String pinfo;

    @Column(name="datainici",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataInici;

    @Column(name="datafi",length = 29,precision = 6)
    java.sql.Timestamp dataFi;

    @Column(name="personacontacte",length = 255)
    java.lang.String personaContacte;

    @Column(name="personacontacteemail",length = 100)
    java.lang.String personaContacteEmail;

    @Column(name="responsableprocnom",length = 255)
    java.lang.String responsableProcNom;

    @Column(name="responsableprocemail",length = 255)
    java.lang.String responsableProcEmail;

    @Column(name="estat",length = 2550)
    java.lang.String notes;

    @Column(name="documentsolicitudid",length = 19)
    java.lang.Long documentSolicitudID;

    @Column(name="solicitudxmlid",length = 19)
    java.lang.Long solicitudXmlID;

    @Column(name="firmatdocsolicitud",nullable = false,length = 1)
    boolean firmatDocSolicitud = false;

    @Column(name="produccio",nullable = false,length = 1)
    boolean produccio = true;

  /** És l'entitat */
    @Column(name="denominacio",length = 255)
    java.lang.String denominacio;

    @Column(name="dir3",length = 50)
    java.lang.String dir3;

    @Column(name="nif",length = 40)
    java.lang.String nif;

    @Column(name="creador",nullable = false,length = 100)
    java.lang.String creador;

    @Column(name="operador",nullable = false,length = 100)
    java.lang.String operador;

    @Column(name="estatpinbal",length = 10)
    java.lang.Integer estatpinbal;

    @Column(name="consentiment",length = 80)
    java.lang.String consentiment;

    @Column(name="urlconsentiment",length = 255)
    java.lang.String urlconsentiment;

    @Column(name="consentimentadjunt",length = 200)
    java.lang.String consentimentadjunt;

    @Column(name="portafibid",length = 19)
    java.lang.Long portafibID;



  /** Constructor Buit */
  public SolicitudJPA() {
  }

  /** Constructor amb tots els camps  */
  public SolicitudJPA(long solicitudID , java.lang.String procedimentCodi , java.lang.String codiDescriptiu , java.lang.String procedimentNom , java.lang.String procedimentTipus , java.lang.String expedientPid , java.lang.Long estatID , java.lang.Long organid , java.lang.String entitatEstatal , java.lang.String pinfo , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String personaContacte , java.lang.String personaContacteEmail , java.lang.String responsableProcNom , java.lang.String responsableProcEmail , java.lang.String notes , java.lang.Long documentSolicitudID , java.lang.Long solicitudXmlID , boolean firmatDocSolicitud , boolean produccio , java.lang.String denominacio , java.lang.String dir3 , java.lang.String nif , java.lang.String creador , java.lang.String operador , java.lang.Integer estatpinbal , java.lang.String consentiment , java.lang.String urlconsentiment , java.lang.String consentimentadjunt , java.lang.Long portafibID) {
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.codiDescriptiu=codiDescriptiu;
    this.procedimentNom=procedimentNom;
    this.procedimentTipus=procedimentTipus;
    this.expedientPid=expedientPid;
    this.estatID=estatID;
    this.organid=organid;
    this.entitatEstatal=entitatEstatal;
    this.pinfo=pinfo;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.personaContacte=personaContacte;
    this.personaContacteEmail=personaContacteEmail;
    this.responsableProcNom=responsableProcNom;
    this.responsableProcEmail=responsableProcEmail;
    this.notes=notes;
    this.documentSolicitudID=documentSolicitudID;
    this.solicitudXmlID=solicitudXmlID;
    this.firmatDocSolicitud=firmatDocSolicitud;
    this.produccio=produccio;
    this.denominacio=denominacio;
    this.dir3=dir3;
    this.nif=nif;
    this.creador=creador;
    this.operador=operador;
    this.estatpinbal=estatpinbal;
    this.consentiment=consentiment;
    this.urlconsentiment=urlconsentiment;
    this.consentimentadjunt=consentimentadjunt;
    this.portafibID=portafibID;
}
  /** Constructor sense valors autoincrementals */
  public SolicitudJPA(java.lang.String procedimentCodi , java.lang.String codiDescriptiu , java.lang.String procedimentNom , java.lang.String procedimentTipus , java.lang.String expedientPid , java.lang.Long estatID , java.lang.Long organid , java.lang.String entitatEstatal , java.lang.String pinfo , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String personaContacte , java.lang.String personaContacteEmail , java.lang.String responsableProcNom , java.lang.String responsableProcEmail , java.lang.String notes , java.lang.Long documentSolicitudID , java.lang.Long solicitudXmlID , boolean firmatDocSolicitud , boolean produccio , java.lang.String denominacio , java.lang.String dir3 , java.lang.String nif , java.lang.String creador , java.lang.String operador , java.lang.Integer estatpinbal , java.lang.String consentiment , java.lang.String urlconsentiment , java.lang.String consentimentadjunt , java.lang.Long portafibID) {
    this.procedimentCodi=procedimentCodi;
    this.codiDescriptiu=codiDescriptiu;
    this.procedimentNom=procedimentNom;
    this.procedimentTipus=procedimentTipus;
    this.expedientPid=expedientPid;
    this.estatID=estatID;
    this.organid=organid;
    this.entitatEstatal=entitatEstatal;
    this.pinfo=pinfo;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.personaContacte=personaContacte;
    this.personaContacteEmail=personaContacteEmail;
    this.responsableProcNom=responsableProcNom;
    this.responsableProcEmail=responsableProcEmail;
    this.notes=notes;
    this.documentSolicitudID=documentSolicitudID;
    this.solicitudXmlID=solicitudXmlID;
    this.firmatDocSolicitud=firmatDocSolicitud;
    this.produccio=produccio;
    this.denominacio=denominacio;
    this.dir3=dir3;
    this.nif=nif;
    this.creador=creador;
    this.operador=operador;
    this.estatpinbal=estatpinbal;
    this.consentiment=consentiment;
    this.urlconsentiment=urlconsentiment;
    this.consentimentadjunt=consentimentadjunt;
    this.portafibID=portafibID;
}
  /** Constructor dels valors Not Null */
  public SolicitudJPA(long solicitudID , java.lang.String procedimentCodi , java.lang.String procedimentNom , java.lang.Long estatID , java.sql.Timestamp dataInici , boolean firmatDocSolicitud , boolean produccio , java.lang.String creador , java.lang.String operador) {
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.procedimentNom=procedimentNom;
    this.estatID=estatID;
    this.dataInici=dataInici;
    this.firmatDocSolicitud=firmatDocSolicitud;
    this.produccio=produccio;
    this.creador=creador;
    this.operador=operador;
}
  public SolicitudJPA(Solicitud __bean) {
    this.setSolicitudID(__bean.getSolicitudID());
    this.setProcedimentCodi(__bean.getProcedimentCodi());
    this.setCodiDescriptiu(__bean.getCodiDescriptiu());
    this.setProcedimentNom(__bean.getProcedimentNom());
    this.setProcedimentTipus(__bean.getProcedimentTipus());
    this.setExpedientPid(__bean.getExpedientPid());
    this.setEstatID(__bean.getEstatID());
    this.setOrganid(__bean.getOrganid());
    this.setEntitatEstatal(__bean.getEntitatEstatal());
    this.setPinfo(__bean.getPinfo());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setPersonaContacte(__bean.getPersonaContacte());
    this.setPersonaContacteEmail(__bean.getPersonaContacteEmail());
    this.setResponsableProcNom(__bean.getResponsableProcNom());
    this.setResponsableProcEmail(__bean.getResponsableProcEmail());
    this.setNotes(__bean.getNotes());
    this.setDocumentSolicitudID(__bean.getDocumentSolicitudID());
    this.setSolicitudXmlID(__bean.getSolicitudXmlID());
    this.setFirmatDocSolicitud(__bean.isFirmatDocSolicitud());
    this.setProduccio(__bean.isProduccio());
    this.setDenominacio(__bean.getDenominacio());
    this.setDir3(__bean.getDir3());
    this.setNif(__bean.getNif());
    this.setCreador(__bean.getCreador());
    this.setOperador(__bean.getOperador());
    this.setEstatpinbal(__bean.getEstatpinbal());
    this.setConsentiment(__bean.getConsentiment());
    this.setUrlconsentiment(__bean.getUrlconsentiment());
    this.setConsentimentadjunt(__bean.getConsentimentadjunt());
    this.setPortafibID(__bean.getPortafibID());
    // Fitxer
    this.setDocumentSolicitud(FitxerJPA.toJPA(__bean.getDocumentSolicitud()));
    // Fitxer
    this.setSolicitudXml(FitxerJPA.toJPA(__bean.getSolicitudXml()));
	}

	public long getSolicitudID() {
		return(solicitudID);
	};
	public void setSolicitudID(long _solicitudID_) {
		this.solicitudID = _solicitudID_;
	};

	public java.lang.String getProcedimentCodi() {
		return(procedimentCodi);
	};
	public void setProcedimentCodi(java.lang.String _procedimentCodi_) {
		this.procedimentCodi = _procedimentCodi_;
	};

	public java.lang.String getCodiDescriptiu() {
		return(codiDescriptiu);
	};
	public void setCodiDescriptiu(java.lang.String _codiDescriptiu_) {
		this.codiDescriptiu = _codiDescriptiu_;
	};

	public java.lang.String getProcedimentNom() {
		return(procedimentNom);
	};
	public void setProcedimentNom(java.lang.String _procedimentNom_) {
		this.procedimentNom = _procedimentNom_;
	};

	public java.lang.String getProcedimentTipus() {
		return(procedimentTipus);
	};
	public void setProcedimentTipus(java.lang.String _procedimentTipus_) {
		this.procedimentTipus = _procedimentTipus_;
	};

	public java.lang.String getExpedientPid() {
		return(expedientPid);
	};
	public void setExpedientPid(java.lang.String _expedientPid_) {
		this.expedientPid = _expedientPid_;
	};

	public java.lang.Long getEstatID() {
		return(estatID);
	};
	public void setEstatID(java.lang.Long _estatID_) {
		this.estatID = _estatID_;
	};

	public java.lang.Long getOrganid() {
		return(organid);
	};
	public void setOrganid(java.lang.Long _organid_) {
		this.organid = _organid_;
	};

	public java.lang.String getEntitatEstatal() {
		return(entitatEstatal);
	};
	public void setEntitatEstatal(java.lang.String _entitatEstatal_) {
		this.entitatEstatal = _entitatEstatal_;
	};

	public java.lang.String getPinfo() {
		return(pinfo);
	};
	public void setPinfo(java.lang.String _pinfo_) {
		this.pinfo = _pinfo_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Timestamp getDataFi() {
		return(dataFi);
	};
	public void setDataFi(java.sql.Timestamp _dataFi_) {
		this.dataFi = _dataFi_;
	};

	public java.lang.String getPersonaContacte() {
		return(personaContacte);
	};
	public void setPersonaContacte(java.lang.String _personaContacte_) {
		this.personaContacte = _personaContacte_;
	};

	public java.lang.String getPersonaContacteEmail() {
		return(personaContacteEmail);
	};
	public void setPersonaContacteEmail(java.lang.String _personaContacteEmail_) {
		this.personaContacteEmail = _personaContacteEmail_;
	};

	public java.lang.String getResponsableProcNom() {
		return(responsableProcNom);
	};
	public void setResponsableProcNom(java.lang.String _responsableProcNom_) {
		this.responsableProcNom = _responsableProcNom_;
	};

	public java.lang.String getResponsableProcEmail() {
		return(responsableProcEmail);
	};
	public void setResponsableProcEmail(java.lang.String _responsableProcEmail_) {
		this.responsableProcEmail = _responsableProcEmail_;
	};

	public java.lang.String getNotes() {
		return(notes);
	};
	public void setNotes(java.lang.String _notes_) {
		this.notes = _notes_;
	};

	public java.lang.Long getDocumentSolicitudID() {
		return(documentSolicitudID);
	};
	public void setDocumentSolicitudID(java.lang.Long _documentSolicitudID_) {
		this.documentSolicitudID = _documentSolicitudID_;
	};

	public java.lang.Long getSolicitudXmlID() {
		return(solicitudXmlID);
	};
	public void setSolicitudXmlID(java.lang.Long _solicitudXmlID_) {
		this.solicitudXmlID = _solicitudXmlID_;
	};

	public boolean isFirmatDocSolicitud() {
		return(firmatDocSolicitud);
	};
	public void setFirmatDocSolicitud(boolean _firmatDocSolicitud_) {
		this.firmatDocSolicitud = _firmatDocSolicitud_;
	};

	public boolean isProduccio() {
		return(produccio);
	};
	public void setProduccio(boolean _produccio_) {
		this.produccio = _produccio_;
	};

	public java.lang.String getDenominacio() {
		return(denominacio);
	};
	public void setDenominacio(java.lang.String _denominacio_) {
		this.denominacio = _denominacio_;
	};

	public java.lang.String getDir3() {
		return(dir3);
	};
	public void setDir3(java.lang.String _dir3_) {
		this.dir3 = _dir3_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getCreador() {
		return(creador);
	};
	public void setCreador(java.lang.String _creador_) {
		this.creador = _creador_;
	};

	public java.lang.String getOperador() {
		return(operador);
	};
	public void setOperador(java.lang.String _operador_) {
		this.operador = _operador_;
	};

	public java.lang.Integer getEstatpinbal() {
		return(estatpinbal);
	};
	public void setEstatpinbal(java.lang.Integer _estatpinbal_) {
		this.estatpinbal = _estatpinbal_;
	};

	public java.lang.String getConsentiment() {
		return(consentiment);
	};
	public void setConsentiment(java.lang.String _consentiment_) {
		this.consentiment = _consentiment_;
	};

	public java.lang.String getUrlconsentiment() {
		return(urlconsentiment);
	};
	public void setUrlconsentiment(java.lang.String _urlconsentiment_) {
		this.urlconsentiment = _urlconsentiment_;
	};

	public java.lang.String getConsentimentadjunt() {
		return(consentimentadjunt);
	};
	public void setConsentimentadjunt(java.lang.String _consentimentadjunt_) {
		this.consentimentadjunt = _consentimentadjunt_;
	};

	public java.lang.Long getPortafibID() {
		return(portafibID);
	};
	public void setPortafibID(java.lang.Long _portafibID_) {
		this.portafibID = _portafibID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Solicitud) {
      Solicitud __instance = (Solicitud)__obj;
      __result = true;
      __result = __result && (this.getSolicitudID() == __instance.getSolicitudID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:solicitudid | Table: pad_documentsolicitud | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitud")
    private Set<DocumentSolicitudJPA> documentSolicituds = new HashSet<DocumentSolicitudJPA>(0);
    public  Set<DocumentSolicitudJPA> getDocumentSolicituds() {
    return this.documentSolicituds;
  }

    public void setDocumentSolicituds(Set<DocumentSolicitudJPA> documentSolicituds) {
      this.documentSolicituds = documentSolicituds;
    }


// EXP  Field:solicitudid | Table: pad_event | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitud")
    private Set<EventJPA> events = new HashSet<EventJPA>(0);
    public  Set<EventJPA> getEvents() {
    return this.events;
  }

    public void setEvents(Set<EventJPA> events) {
      this.events = events;
    }


// EXP  Field:procedimentid | Table: pad_pinfodata | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitud")
    private Set<PinfoDataJPA> pinfoDatas = new HashSet<PinfoDataJPA>(0);
    public  Set<PinfoDataJPA> getPinfoDatas() {
    return this.pinfoDatas;
  }

    public void setPinfoDatas(Set<PinfoDataJPA> pinfoDatas) {
      this.pinfoDatas = pinfoDatas;
    }


// EXP  Field:solicitudid | Table: pad_solicitudservei | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitud")
    private Set<SolicitudServeiJPA> solicitudServeis = new HashSet<SolicitudServeiJPA>(0);
    public  Set<SolicitudServeiJPA> getSolicitudServeis() {
    return this.solicitudServeis;
  }

    public void setSolicitudServeis(Set<SolicitudServeiJPA> solicitudServeis) {
      this.solicitudServeis = solicitudServeis;
    }


// IMP Field:organid | Table: pad_organ | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organid", referencedColumnName ="organid", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_organ_fk"))
    private OrganJPA organ;

    public OrganJPA getOrgan() {
    return this.organ;
  }

    public  void setOrgan(OrganJPA organ) {
    this.organ = organ;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "documentsolicitudid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_fitxer_fk"))
    private FitxerJPA documentSolicitud;

    public FitxerJPA getDocumentSolicitud() {
    return this.documentSolicitud;
  }

    public  void setDocumentSolicitud(FitxerJPA documentSolicitud) {
    this.documentSolicitud = documentSolicitud;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "solicitudxmlid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_fitxer_xml_fk"))
    private FitxerJPA solicitudXml;

    public FitxerJPA getSolicitudXml() {
    return this.solicitudXml;
  }

    public  void setSolicitudXml(FitxerJPA solicitudXml) {
    this.solicitudXml = solicitudXml;
  }


 // ---------------  STATIC METHODS ------------------
  public static SolicitudJPA toJPA(Solicitud __bean) {
    if (__bean == null) { return null;}
    SolicitudJPA __tmp = new SolicitudJPA();
    __tmp.setSolicitudID(__bean.getSolicitudID());
    __tmp.setProcedimentCodi(__bean.getProcedimentCodi());
    __tmp.setCodiDescriptiu(__bean.getCodiDescriptiu());
    __tmp.setProcedimentNom(__bean.getProcedimentNom());
    __tmp.setProcedimentTipus(__bean.getProcedimentTipus());
    __tmp.setExpedientPid(__bean.getExpedientPid());
    __tmp.setEstatID(__bean.getEstatID());
    __tmp.setOrganid(__bean.getOrganid());
    __tmp.setEntitatEstatal(__bean.getEntitatEstatal());
    __tmp.setPinfo(__bean.getPinfo());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setPersonaContacte(__bean.getPersonaContacte());
    __tmp.setPersonaContacteEmail(__bean.getPersonaContacteEmail());
    __tmp.setResponsableProcNom(__bean.getResponsableProcNom());
    __tmp.setResponsableProcEmail(__bean.getResponsableProcEmail());
    __tmp.setNotes(__bean.getNotes());
    __tmp.setDocumentSolicitudID(__bean.getDocumentSolicitudID());
    __tmp.setSolicitudXmlID(__bean.getSolicitudXmlID());
    __tmp.setFirmatDocSolicitud(__bean.isFirmatDocSolicitud());
    __tmp.setProduccio(__bean.isProduccio());
    __tmp.setDenominacio(__bean.getDenominacio());
    __tmp.setDir3(__bean.getDir3());
    __tmp.setNif(__bean.getNif());
    __tmp.setCreador(__bean.getCreador());
    __tmp.setOperador(__bean.getOperador());
    __tmp.setEstatpinbal(__bean.getEstatpinbal());
    __tmp.setConsentiment(__bean.getConsentiment());
    __tmp.setUrlconsentiment(__bean.getUrlconsentiment());
    __tmp.setConsentimentadjunt(__bean.getConsentimentadjunt());
    __tmp.setPortafibID(__bean.getPortafibID());
    // Fitxer
    __tmp.setDocumentSolicitud(FitxerJPA.toJPA(__bean.getDocumentSolicitud()));
    // Fitxer
    __tmp.setSolicitudXml(FitxerJPA.toJPA(__bean.getSolicitudXml()));
		return __tmp;
	}


  public static SolicitudJPA copyJPA(SolicitudJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<SolicitudJPA> copyJPA(java.util.Set<SolicitudJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<SolicitudJPA> __tmpSet = (java.util.Set<SolicitudJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<SolicitudJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (SolicitudJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static SolicitudJPA copyJPA(SolicitudJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    SolicitudJPA __tmp = (SolicitudJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"EventJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.events) || org.hibernate.Hibernate.isInitialized(__jpa.getEvents())) ) {
      __tmp.setEvents(EventJPA.copyJPA(__jpa.getEvents(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"SolicitudServeiJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitudServeis) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitudServeis())) ) {
      __tmp.setSolicitudServeis(SolicitudServeiJPA.copyJPA(__jpa.getSolicitudServeis(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"PinfoDataJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pinfoDatas) || org.hibernate.Hibernate.isInitialized(__jpa.getPinfoDatas())) ) {
      __tmp.setPinfoDatas(PinfoDataJPA.copyJPA(__jpa.getPinfoDatas(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"DocumentSolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.documentSolicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getDocumentSolicituds())) ) {
      __tmp.setDocumentSolicituds(DocumentSolicitudJPA.copyJPA(__jpa.getDocumentSolicituds(), __alreadyCopied,"SolicitudJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"OrganJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.organ) || org.hibernate.Hibernate.isInitialized(__jpa.getOrgan()) ) ) {
      __tmp.setOrgan(OrganJPA.copyJPA(__jpa.getOrgan(), __alreadyCopied,"SolicitudJPA"));
    }

    return __tmp;
  }




}
