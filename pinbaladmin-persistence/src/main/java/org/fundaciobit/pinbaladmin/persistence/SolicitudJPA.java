
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
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
        @Index(name="pad_solicitud_departid_fk_i", columnList = "departamentid"),
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

    @Column(name="estatid",nullable = false,length = 19)
    java.lang.Long estatID;

    @Column(name="ticketassociat",length = 255)
    java.lang.String ticketAssociat;

    @Column(name="ticketnumeroseguiment",length = 255)
    java.lang.String ticketNumeroSeguiment;

    @Column(name="departamentid",length = 19)
    java.lang.Long departamentID;

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

    @Column(name="creador",length = 100)
    java.lang.String creador;

  /** És l'entitat */
    @Column(name="denominacio",length = 255)
    java.lang.String denominacio;

    @Column(name="dir3",length = 50)
    java.lang.String dir3;

    @Column(name="nif",length = 40)
    java.lang.String nif;



  /** Constructor Buit */
  public SolicitudJPA() {
  }

  /** Constructor amb tots els camps  */
  public SolicitudJPA(long solicitudID , java.lang.String procedimentCodi , java.lang.String codiDescriptiu , java.lang.String procedimentNom , java.lang.String procedimentTipus , java.lang.Long estatID , java.lang.String ticketAssociat , java.lang.String ticketNumeroSeguiment , java.lang.Long departamentID , java.lang.String entitatEstatal , java.lang.String pinfo , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String personaContacte , java.lang.String personaContacteEmail , java.lang.String responsableProcNom , java.lang.String responsableProcEmail , java.lang.String notes , java.lang.Long documentSolicitudID , java.lang.Long solicitudXmlID , boolean firmatDocSolicitud , boolean produccio , java.lang.String creador , java.lang.String denominacio , java.lang.String dir3 , java.lang.String nif) {
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.codiDescriptiu=codiDescriptiu;
    this.procedimentNom=procedimentNom;
    this.procedimentTipus=procedimentTipus;
    this.estatID=estatID;
    this.ticketAssociat=ticketAssociat;
    this.ticketNumeroSeguiment=ticketNumeroSeguiment;
    this.departamentID=departamentID;
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
    this.creador=creador;
    this.denominacio=denominacio;
    this.dir3=dir3;
    this.nif=nif;
}
  /** Constructor sense valors autoincrementals */
  public SolicitudJPA(java.lang.String procedimentCodi , java.lang.String codiDescriptiu , java.lang.String procedimentNom , java.lang.String procedimentTipus , java.lang.Long estatID , java.lang.String ticketAssociat , java.lang.String ticketNumeroSeguiment , java.lang.Long departamentID , java.lang.String entitatEstatal , java.lang.String pinfo , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String personaContacte , java.lang.String personaContacteEmail , java.lang.String responsableProcNom , java.lang.String responsableProcEmail , java.lang.String notes , java.lang.Long documentSolicitudID , java.lang.Long solicitudXmlID , boolean firmatDocSolicitud , boolean produccio , java.lang.String creador , java.lang.String denominacio , java.lang.String dir3 , java.lang.String nif) {
    this.procedimentCodi=procedimentCodi;
    this.codiDescriptiu=codiDescriptiu;
    this.procedimentNom=procedimentNom;
    this.procedimentTipus=procedimentTipus;
    this.estatID=estatID;
    this.ticketAssociat=ticketAssociat;
    this.ticketNumeroSeguiment=ticketNumeroSeguiment;
    this.departamentID=departamentID;
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
    this.creador=creador;
    this.denominacio=denominacio;
    this.dir3=dir3;
    this.nif=nif;
}
  /** Constructor dels valors Not Null */
  public SolicitudJPA(long solicitudID , java.lang.String procedimentCodi , java.lang.String procedimentNom , java.lang.Long estatID , java.sql.Timestamp dataInici , boolean firmatDocSolicitud , boolean produccio) {
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.procedimentNom=procedimentNom;
    this.estatID=estatID;
    this.dataInici=dataInici;
    this.firmatDocSolicitud=firmatDocSolicitud;
    this.produccio=produccio;
}
  public SolicitudJPA(Solicitud __bean) {
    this.setSolicitudID(__bean.getSolicitudID());
    this.setProcedimentCodi(__bean.getProcedimentCodi());
    this.setCodiDescriptiu(__bean.getCodiDescriptiu());
    this.setProcedimentNom(__bean.getProcedimentNom());
    this.setProcedimentTipus(__bean.getProcedimentTipus());
    this.setEstatID(__bean.getEstatID());
    this.setTicketAssociat(__bean.getTicketAssociat());
    this.setTicketNumeroSeguiment(__bean.getTicketNumeroSeguiment());
    this.setDepartamentID(__bean.getDepartamentID());
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
    this.setCreador(__bean.getCreador());
    this.setDenominacio(__bean.getDenominacio());
    this.setDir3(__bean.getDir3());
    this.setNif(__bean.getNif());
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

	public java.lang.Long getEstatID() {
		return(estatID);
	};
	public void setEstatID(java.lang.Long _estatID_) {
		this.estatID = _estatID_;
	};

	public java.lang.String getTicketAssociat() {
		return(ticketAssociat);
	};
	public void setTicketAssociat(java.lang.String _ticketAssociat_) {
		this.ticketAssociat = _ticketAssociat_;
	};

	public java.lang.String getTicketNumeroSeguiment() {
		return(ticketNumeroSeguiment);
	};
	public void setTicketNumeroSeguiment(java.lang.String _ticketNumeroSeguiment_) {
		this.ticketNumeroSeguiment = _ticketNumeroSeguiment_;
	};

	public java.lang.Long getDepartamentID() {
		return(departamentID);
	};
	public void setDepartamentID(java.lang.Long _departamentID_) {
		this.departamentID = _departamentID_;
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

	public java.lang.String getCreador() {
		return(creador);
	};
	public void setCreador(java.lang.String _creador_) {
		this.creador = _creador_;
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


// EXP  Field:solicitudid | Table: pad_solicitudservei | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "solicitud")
    private Set<SolicitudServeiJPA> solicitudServeis = new HashSet<SolicitudServeiJPA>(0);
    public  Set<SolicitudServeiJPA> getSolicitudServeis() {
    return this.solicitudServeis;
  }

    public void setSolicitudServeis(Set<SolicitudServeiJPA> solicitudServeis) {
      this.solicitudServeis = solicitudServeis;
    }


// IMP Field:estatsolicitudid | Table: pad_estatsolicitud | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estatid", referencedColumnName ="estatSolicitudID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_estatsoli_fk"))
    private EstatSolicitudJPA estatSolicitud;

    public EstatSolicitudJPA getEstatSolicitud() {
    return this.estatSolicitud;
  }

    public  void setEstatSolicitud(EstatSolicitudJPA estatSolicitud) {
    this.estatSolicitud = estatSolicitud;
  }

// IMP Field:departamentid | Table: pad_departament | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamentid", referencedColumnName ="departamentID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_solicitud_depart_fk"))
    private DepartamentJPA departament;

    public DepartamentJPA getDepartament() {
    return this.departament;
  }

    public  void setDepartament(DepartamentJPA departament) {
    this.departament = departament;
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
    __tmp.setEstatID(__bean.getEstatID());
    __tmp.setTicketAssociat(__bean.getTicketAssociat());
    __tmp.setTicketNumeroSeguiment(__bean.getTicketNumeroSeguiment());
    __tmp.setDepartamentID(__bean.getDepartamentID());
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
    __tmp.setCreador(__bean.getCreador());
    __tmp.setDenominacio(__bean.getDenominacio());
    __tmp.setDir3(__bean.getDir3());
    __tmp.setNif(__bean.getNif());
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
    if(!"DocumentSolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.documentSolicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getDocumentSolicituds())) ) {
      __tmp.setDocumentSolicituds(DocumentSolicitudJPA.copyJPA(__jpa.getDocumentSolicituds(), __alreadyCopied,"SolicitudJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"DepartamentJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.departament) || org.hibernate.Hibernate.isInitialized(__jpa.getDepartament()) ) ) {
      __tmp.setDepartament(DepartamentJPA.copyJPA(__jpa.getDepartament(), __alreadyCopied,"SolicitudJPA"));
    }
    if(!"EstatSolicitudJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.estatSolicitud) || org.hibernate.Hibernate.isInitialized(__jpa.getEstatSolicitud()) ) ) {
      __tmp.setEstatSolicitud(EstatSolicitudJPA.copyJPA(__jpa.getEstatSolicitud(), __alreadyCopied,"SolicitudJPA"));
    }

    return __tmp;
  }




}
