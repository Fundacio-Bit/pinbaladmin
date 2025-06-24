
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


@Entity(name = "ModificacioSolicitudJPA")
@Table(name = "pad_mod_solicitud" , indexes = { 
        @Index(name="pad_mod_solicitud_pk_i", columnList = "modsoliid"),
        @Index(name="pad_modsoli_solicitudid_fk_i", columnList = "solicitudid"),
        @Index(name="pad_mod_solicitud_organid_fk_i", columnList = "organid"),
        @Index(name="pad_modsoli_docconsent_fk_i", columnList = "docconsentiment")})
@SequenceGenerator(name="MODIFICACIOSOLICITUD_SEQ", sequenceName="pad_mod_solicitud_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class ModificacioSolicitudJPA implements ModificacioSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MODIFICACIOSOLICITUD_SEQ")
    @Column(name="modsoliid",nullable = false,length = 19)
    long modsoliID;

    @Column(name="solicitudid",nullable = false,length = 19)
    long solicitudID;

    @Column(name="procedimentcodi",length = 255)
    java.lang.String procedimentCodi;

    @Column(name="procedimentnom",length = 2000)
    java.lang.String procedimentNom;

    @Column(name="codisianou",length = 255)
    java.lang.String codiSiaNou;

    @Column(name="estatid",length = 19)
    java.lang.Long estatID;

    @Column(name="datainici",length = 29,precision = 6)
    java.sql.Timestamp dataInici;

    @Column(name="datafi",length = 29,precision = 6)
    java.sql.Timestamp dataFi;

    @Column(name="notes",length = 2000)
    java.lang.String notes;

    @Column(name="organid",length = 19)
    java.lang.Long organID;

    @Column(name="responsableprocnom",length = 255)
    java.lang.String responsableProcNom;

    @Column(name="responsableprocemail",length = 255)
    java.lang.String responsableProceMail;

    @Column(name="consentiment",length = 80)
    java.lang.String consentiment;

    @Column(name="docconsentiment",length = 19)
    java.lang.Long doCconsentimentID;

    @Column(name="solicitantnom",length = 255)
    java.lang.String solicitantNom;

    @Column(name="solicitantnif",length = 255)
    java.lang.String solicitantNif;

    @Column(name="solicitantmail",length = 255)
    java.lang.String solicitantMail;

    @Column(name="solicitantusername",length = 255)
    java.lang.String solicitantUsername;

    @Column(name="estatmodificacio",length = 19)
    java.lang.Long estatModificacio;



  /** Constructor Buit */
  public ModificacioSolicitudJPA() {
  }

  /** Constructor amb tots els camps  */
  public ModificacioSolicitudJPA(long modsoliID , long solicitudID , java.lang.String procedimentCodi , java.lang.String procedimentNom , java.lang.String codiSiaNou , java.lang.Long estatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String notes , java.lang.Long organID , java.lang.String responsableProcNom , java.lang.String responsableProceMail , java.lang.String consentiment , java.lang.Long doCconsentimentID , java.lang.String solicitantNom , java.lang.String solicitantNif , java.lang.String solicitantMail , java.lang.String solicitantUsername , java.lang.Long estatModificacio) {
    this.modsoliID=modsoliID;
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.procedimentNom=procedimentNom;
    this.codiSiaNou=codiSiaNou;
    this.estatID=estatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.notes=notes;
    this.organID=organID;
    this.responsableProcNom=responsableProcNom;
    this.responsableProceMail=responsableProceMail;
    this.consentiment=consentiment;
    this.doCconsentimentID=doCconsentimentID;
    this.solicitantNom=solicitantNom;
    this.solicitantNif=solicitantNif;
    this.solicitantMail=solicitantMail;
    this.solicitantUsername=solicitantUsername;
    this.estatModificacio=estatModificacio;
}
  /** Constructor sense valors autoincrementals */
  public ModificacioSolicitudJPA(long solicitudID , java.lang.String procedimentCodi , java.lang.String procedimentNom , java.lang.String codiSiaNou , java.lang.Long estatID , java.sql.Timestamp dataInici , java.sql.Timestamp dataFi , java.lang.String notes , java.lang.Long organID , java.lang.String responsableProcNom , java.lang.String responsableProceMail , java.lang.String consentiment , java.lang.Long doCconsentimentID , java.lang.String solicitantNom , java.lang.String solicitantNif , java.lang.String solicitantMail , java.lang.String solicitantUsername , java.lang.Long estatModificacio) {
    this.solicitudID=solicitudID;
    this.procedimentCodi=procedimentCodi;
    this.procedimentNom=procedimentNom;
    this.codiSiaNou=codiSiaNou;
    this.estatID=estatID;
    this.dataInici=dataInici;
    this.dataFi=dataFi;
    this.notes=notes;
    this.organID=organID;
    this.responsableProcNom=responsableProcNom;
    this.responsableProceMail=responsableProceMail;
    this.consentiment=consentiment;
    this.doCconsentimentID=doCconsentimentID;
    this.solicitantNom=solicitantNom;
    this.solicitantNif=solicitantNif;
    this.solicitantMail=solicitantMail;
    this.solicitantUsername=solicitantUsername;
    this.estatModificacio=estatModificacio;
}
  /** Constructor dels valors Not Null */
  public ModificacioSolicitudJPA(long modsoliID , long solicitudID) {
    this.modsoliID=modsoliID;
    this.solicitudID=solicitudID;
}
  public ModificacioSolicitudJPA(ModificacioSolicitud __bean) {
    this.setModsoliID(__bean.getModsoliID());
    this.setSolicitudID(__bean.getSolicitudID());
    this.setProcedimentCodi(__bean.getProcedimentCodi());
    this.setProcedimentNom(__bean.getProcedimentNom());
    this.setCodiSiaNou(__bean.getCodiSiaNou());
    this.setEstatID(__bean.getEstatID());
    this.setDataInici(__bean.getDataInici());
    this.setDataFi(__bean.getDataFi());
    this.setNotes(__bean.getNotes());
    this.setOrganID(__bean.getOrganID());
    this.setResponsableProcNom(__bean.getResponsableProcNom());
    this.setResponsableProceMail(__bean.getResponsableProceMail());
    this.setConsentiment(__bean.getConsentiment());
    this.setDoCconsentimentID(__bean.getDoCconsentimentID());
    this.setSolicitantNom(__bean.getSolicitantNom());
    this.setSolicitantNif(__bean.getSolicitantNif());
    this.setSolicitantMail(__bean.getSolicitantMail());
    this.setSolicitantUsername(__bean.getSolicitantUsername());
    this.setEstatModificacio(__bean.getEstatModificacio());
    // Fitxer
    this.setDoCconsentiment(FitxerJPA.toJPA(__bean.getDoCconsentiment()));
	}

	public long getModsoliID() {
		return(modsoliID);
	};
	public void setModsoliID(long _modsoliID_) {
		this.modsoliID = _modsoliID_;
	};

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

	public java.lang.String getProcedimentNom() {
		return(procedimentNom);
	};
	public void setProcedimentNom(java.lang.String _procedimentNom_) {
		this.procedimentNom = _procedimentNom_;
	};

	public java.lang.String getCodiSiaNou() {
		return(codiSiaNou);
	};
	public void setCodiSiaNou(java.lang.String _codiSiaNou_) {
		this.codiSiaNou = _codiSiaNou_;
	};

	public java.lang.Long getEstatID() {
		return(estatID);
	};
	public void setEstatID(java.lang.Long _estatID_) {
		this.estatID = _estatID_;
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

	public java.lang.String getNotes() {
		return(notes);
	};
	public void setNotes(java.lang.String _notes_) {
		this.notes = _notes_;
	};

	public java.lang.Long getOrganID() {
		return(organID);
	};
	public void setOrganID(java.lang.Long _organID_) {
		this.organID = _organID_;
	};

	public java.lang.String getResponsableProcNom() {
		return(responsableProcNom);
	};
	public void setResponsableProcNom(java.lang.String _responsableProcNom_) {
		this.responsableProcNom = _responsableProcNom_;
	};

	public java.lang.String getResponsableProceMail() {
		return(responsableProceMail);
	};
	public void setResponsableProceMail(java.lang.String _responsableProceMail_) {
		this.responsableProceMail = _responsableProceMail_;
	};

	public java.lang.String getConsentiment() {
		return(consentiment);
	};
	public void setConsentiment(java.lang.String _consentiment_) {
		this.consentiment = _consentiment_;
	};

	public java.lang.Long getDoCconsentimentID() {
		return(doCconsentimentID);
	};
	public void setDoCconsentimentID(java.lang.Long _doCconsentimentID_) {
		this.doCconsentimentID = _doCconsentimentID_;
	};

	public java.lang.String getSolicitantNom() {
		return(solicitantNom);
	};
	public void setSolicitantNom(java.lang.String _solicitantNom_) {
		this.solicitantNom = _solicitantNom_;
	};

	public java.lang.String getSolicitantNif() {
		return(solicitantNif);
	};
	public void setSolicitantNif(java.lang.String _solicitantNif_) {
		this.solicitantNif = _solicitantNif_;
	};

	public java.lang.String getSolicitantMail() {
		return(solicitantMail);
	};
	public void setSolicitantMail(java.lang.String _solicitantMail_) {
		this.solicitantMail = _solicitantMail_;
	};

	public java.lang.String getSolicitantUsername() {
		return(solicitantUsername);
	};
	public void setSolicitantUsername(java.lang.String _solicitantUsername_) {
		this.solicitantUsername = _solicitantUsername_;
	};

	public java.lang.Long getEstatModificacio() {
		return(estatModificacio);
	};
	public void setEstatModificacio(java.lang.Long _estatModificacio_) {
		this.estatModificacio = _estatModificacio_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof ModificacioSolicitud) {
            ModificacioSolicitud __instance = (ModificacioSolicitud)__obj;
            __result = true;
            __result = __result && (this.getModsoliID() == __instance.getModsoliID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:modsoliid | Table: pad_mod_soliserv | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "modificacioSolicitud")
    private Set<ModificacioSoliServJPA> modificacioSoliServs = new HashSet<ModificacioSoliServJPA>(0);
    public  Set<ModificacioSoliServJPA> getModificacioSoliServs() {
    return this.modificacioSoliServs;
  }

    public void setModificacioSoliServs(Set<ModificacioSoliServJPA> modificacioSoliServs) {
      this.modificacioSoliServs = modificacioSoliServs;
    }


// IMP Field:solicitudid | Table: pad_solicitud | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitudid", referencedColumnName ="solicitudID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_modsoli_solicitud_solic_fk"))
    private SolicitudJPA solicitud;

    public SolicitudJPA getSolicitud() {
    return this.solicitud;
  }

    public  void setSolicitud(SolicitudJPA solicitud) {
    this.solicitud = solicitud;
  }

// IMP Field:organid | Table: pad_organ | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organid", referencedColumnName ="organid", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_modsoli_organ_organid_fk"))
    private OrganJPA organ;

    public OrganJPA getOrgan() {
    return this.organ;
  }

    public  void setOrgan(OrganJPA organ) {
    this.organ = organ;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "docconsentiment", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_modsoli_fitxer_docconse_fk"))
    private FitxerJPA doCconsentiment;

    public FitxerJPA getDoCconsentiment() {
    return this.doCconsentiment;
  }

    public  void setDoCconsentiment(FitxerJPA doCconsentiment) {
    this.doCconsentiment = doCconsentiment;
  }


 // ---------------  STATIC METHODS ------------------
  public static ModificacioSolicitudJPA toJPA(ModificacioSolicitud __bean) {
    if (__bean == null) { return null;}
    ModificacioSolicitudJPA __tmp = new ModificacioSolicitudJPA();
    __tmp.setModsoliID(__bean.getModsoliID());
    __tmp.setSolicitudID(__bean.getSolicitudID());
    __tmp.setProcedimentCodi(__bean.getProcedimentCodi());
    __tmp.setProcedimentNom(__bean.getProcedimentNom());
    __tmp.setCodiSiaNou(__bean.getCodiSiaNou());
    __tmp.setEstatID(__bean.getEstatID());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataFi(__bean.getDataFi());
    __tmp.setNotes(__bean.getNotes());
    __tmp.setOrganID(__bean.getOrganID());
    __tmp.setResponsableProcNom(__bean.getResponsableProcNom());
    __tmp.setResponsableProceMail(__bean.getResponsableProceMail());
    __tmp.setConsentiment(__bean.getConsentiment());
    __tmp.setDoCconsentimentID(__bean.getDoCconsentimentID());
    __tmp.setSolicitantNom(__bean.getSolicitantNom());
    __tmp.setSolicitantNif(__bean.getSolicitantNif());
    __tmp.setSolicitantMail(__bean.getSolicitantMail());
    __tmp.setSolicitantUsername(__bean.getSolicitantUsername());
    __tmp.setEstatModificacio(__bean.getEstatModificacio());
    // Fitxer
    __tmp.setDoCconsentiment(FitxerJPA.toJPA(__bean.getDoCconsentiment()));
		return __tmp;
	}


  public static ModificacioSolicitudJPA copyJPA(ModificacioSolicitudJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<ModificacioSolicitudJPA> copyJPA(java.util.Set<ModificacioSolicitudJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<ModificacioSolicitudJPA> __tmpSet = (java.util.Set<ModificacioSolicitudJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<ModificacioSolicitudJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (ModificacioSolicitudJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static ModificacioSolicitudJPA copyJPA(ModificacioSolicitudJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    ModificacioSolicitudJPA __tmp = (ModificacioSolicitudJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"ModificacioSoliServJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.modificacioSoliServs) || org.hibernate.Hibernate.isInitialized(__jpa.getModificacioSoliServs())) ) {
      __tmp.setModificacioSoliServs(ModificacioSoliServJPA.copyJPA(__jpa.getModificacioSoliServs(), __alreadyCopied,"ModificacioSolicitudJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"SolicitudJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitud) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitud()) ) ) {
      __tmp.setSolicitud(SolicitudJPA.copyJPA(__jpa.getSolicitud(), __alreadyCopied,"ModificacioSolicitudJPA"));
    }
    if(!"OrganJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.organ) || org.hibernate.Hibernate.isInitialized(__jpa.getOrgan()) ) ) {
      __tmp.setOrgan(OrganJPA.copyJPA(__jpa.getOrgan(), __alreadyCopied,"ModificacioSolicitudJPA"));
    }

    return __tmp;
  }




}
