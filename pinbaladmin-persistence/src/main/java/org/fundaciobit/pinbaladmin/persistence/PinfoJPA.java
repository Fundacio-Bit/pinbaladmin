
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


@Entity(name = "PinfoJPA")
@Table(name = "pad_pinfo" , indexes = { 
        @Index(name="pad_pinfo_pk_i", columnList = "pinfoid"),
        @Index(name="pad_pinfo_incidenciaid_fk_i", columnList = "incidenciaid"),
        @Index(name="pad_pinfo_fitxerid_fk_i", columnList = "fitxerid"),
        @Index(name="pad_pinfo_fitxerfirmatid_fk_i", columnList = "fitxerfirmatid")})
@SequenceGenerator(name="PINFO_SEQ", sequenceName="pad_pinfo_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PinfoJPA implements Pinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINFO_SEQ")
    @Column(name="pinfoid",nullable = false,length = 19)
    long pinfoID;

    @Column(name="incidenciaid",length = 19)
    java.lang.Long incidenciaID;

    @Column(name="entitat",length = 50)
    java.lang.String entitat;

    @Column(name="solicitantnif",length = 100)
    java.lang.String solicitantNIF;

    @Column(name="estat",length = 19)
    java.lang.Long estat;

    @Column(name="fitxerid",length = 19)
    java.lang.Long fitxerID;

    @Column(name="fitxerfirmatid",length = 19)
    java.lang.Long fitxerfirmatID;

    @Column(name="portafibid",length = 50)
    java.lang.String portafibid;

    @Column(name="destinatarinif",length = 100)
    java.lang.String destinatariNIF;

    @Column(name="destinatarinom",length = 255)
    java.lang.String destinatariNom;

    @Column(name="missatgepinbal",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String missatgePinbal;

    @Column(name="logpinbal",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String logpPnbal;

    @Column(name="missatgesolicitant",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String missatgeSolicitant;



  /** Constructor Buit */
  public PinfoJPA() {
  }

  /** Constructor amb tots els camps  */
  public PinfoJPA(long pinfoID , java.lang.Long incidenciaID , java.lang.String entitat , java.lang.String solicitantNIF , java.lang.Long estat , java.lang.Long fitxerID , java.lang.Long fitxerfirmatID , java.lang.String portafibid , java.lang.String destinatariNIF , java.lang.String destinatariNom , java.lang.String missatgePinbal , java.lang.String logpPnbal , java.lang.String missatgeSolicitant) {
    this.pinfoID=pinfoID;
    this.incidenciaID=incidenciaID;
    this.entitat=entitat;
    this.solicitantNIF=solicitantNIF;
    this.estat=estat;
    this.fitxerID=fitxerID;
    this.fitxerfirmatID=fitxerfirmatID;
    this.portafibid=portafibid;
    this.destinatariNIF=destinatariNIF;
    this.destinatariNom=destinatariNom;
    this.missatgePinbal=missatgePinbal;
    this.logpPnbal=logpPnbal;
    this.missatgeSolicitant=missatgeSolicitant;
}
  /** Constructor sense valors autoincrementals */
  public PinfoJPA(java.lang.Long incidenciaID , java.lang.String entitat , java.lang.String solicitantNIF , java.lang.Long estat , java.lang.Long fitxerID , java.lang.Long fitxerfirmatID , java.lang.String portafibid , java.lang.String destinatariNIF , java.lang.String destinatariNom , java.lang.String missatgePinbal , java.lang.String logpPnbal , java.lang.String missatgeSolicitant) {
    this.incidenciaID=incidenciaID;
    this.entitat=entitat;
    this.solicitantNIF=solicitantNIF;
    this.estat=estat;
    this.fitxerID=fitxerID;
    this.fitxerfirmatID=fitxerfirmatID;
    this.portafibid=portafibid;
    this.destinatariNIF=destinatariNIF;
    this.destinatariNom=destinatariNom;
    this.missatgePinbal=missatgePinbal;
    this.logpPnbal=logpPnbal;
    this.missatgeSolicitant=missatgeSolicitant;
}
  /** Constructor dels valors Not Null */
  public PinfoJPA(long pinfoID) {
    this.pinfoID=pinfoID;
}
  public PinfoJPA(Pinfo __bean) {
    this.setPinfoID(__bean.getPinfoID());
    this.setIncidenciaID(__bean.getIncidenciaID());
    this.setEntitat(__bean.getEntitat());
    this.setSolicitantNIF(__bean.getSolicitantNIF());
    this.setEstat(__bean.getEstat());
    this.setFitxerID(__bean.getFitxerID());
    this.setFitxerfirmatID(__bean.getFitxerfirmatID());
    this.setPortafibid(__bean.getPortafibid());
    this.setDestinatariNIF(__bean.getDestinatariNIF());
    this.setDestinatariNom(__bean.getDestinatariNom());
    this.setMissatgePinbal(__bean.getMissatgePinbal());
    this.setLogpPnbal(__bean.getLogpPnbal());
    this.setMissatgeSolicitant(__bean.getMissatgeSolicitant());
    // Fitxer
    this.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
    // Fitxer
    this.setFitxerfirmat(FitxerJPA.toJPA(__bean.getFitxerfirmat()));
	}

	public long getPinfoID() {
		return(pinfoID);
	};
	public void setPinfoID(long _pinfoID_) {
		this.pinfoID = _pinfoID_;
	};

	public java.lang.Long getIncidenciaID() {
		return(incidenciaID);
	};
	public void setIncidenciaID(java.lang.Long _incidenciaID_) {
		this.incidenciaID = _incidenciaID_;
	};

	public java.lang.String getEntitat() {
		return(entitat);
	};
	public void setEntitat(java.lang.String _entitat_) {
		this.entitat = _entitat_;
	};

	public java.lang.String getSolicitantNIF() {
		return(solicitantNIF);
	};
	public void setSolicitantNIF(java.lang.String _solicitantNIF_) {
		this.solicitantNIF = _solicitantNIF_;
	};

	public java.lang.Long getEstat() {
		return(estat);
	};
	public void setEstat(java.lang.Long _estat_) {
		this.estat = _estat_;
	};

	public java.lang.Long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(java.lang.Long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public java.lang.Long getFitxerfirmatID() {
		return(fitxerfirmatID);
	};
	public void setFitxerfirmatID(java.lang.Long _fitxerfirmatID_) {
		this.fitxerfirmatID = _fitxerfirmatID_;
	};

	public java.lang.String getPortafibid() {
		return(portafibid);
	};
	public void setPortafibid(java.lang.String _portafibid_) {
		this.portafibid = _portafibid_;
	};

	public java.lang.String getDestinatariNIF() {
		return(destinatariNIF);
	};
	public void setDestinatariNIF(java.lang.String _destinatariNIF_) {
		this.destinatariNIF = _destinatariNIF_;
	};

	public java.lang.String getDestinatariNom() {
		return(destinatariNom);
	};
	public void setDestinatariNom(java.lang.String _destinatariNom_) {
		this.destinatariNom = _destinatariNom_;
	};

	public java.lang.String getMissatgePinbal() {
		return(missatgePinbal);
	};
	public void setMissatgePinbal(java.lang.String _missatgePinbal_) {
		this.missatgePinbal = _missatgePinbal_;
	};

	public java.lang.String getLogpPnbal() {
		return(logpPnbal);
	};
	public void setLogpPnbal(java.lang.String _logpPnbal_) {
		this.logpPnbal = _logpPnbal_;
	};

	public java.lang.String getMissatgeSolicitant() {
		return(missatgeSolicitant);
	};
	public void setMissatgeSolicitant(java.lang.String _missatgeSolicitant_) {
		this.missatgeSolicitant = _missatgeSolicitant_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Pinfo) {
            Pinfo __instance = (Pinfo)__obj;
            __result = true;
            __result = __result && (this.getPinfoID() == __instance.getPinfoID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:pinfoid | Table: pad_pinfodata | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pinfo")
    private Set<PinfoDataJPA> pinfoDatas = new HashSet<PinfoDataJPA>(0);
    public  Set<PinfoDataJPA> getPinfoDatas() {
    return this.pinfoDatas;
  }

    public void setPinfoDatas(Set<PinfoDataJPA> pinfoDatas) {
      this.pinfoDatas = pinfoDatas;
    }


// IMP Field:incidenciatecnicaid | Table: pad_incidenciatecnica | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidenciaid", referencedColumnName ="incidenciaTecnicaID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_pinfo_inctecnica_incide_fk"))
    private IncidenciaTecnicaJPA incidenciaTecnica;

    public IncidenciaTecnicaJPA getIncidenciaTecnica() {
    return this.incidenciaTecnica;
  }

    public  void setIncidenciaTecnica(IncidenciaTecnicaJPA incidenciaTecnica) {
    this.incidenciaTecnica = incidenciaTecnica;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxerid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_pinfo_fitxer_fitxerid_fk"))
    private FitxerJPA fitxer;

    public FitxerJPA getFitxer() {
    return this.fitxer;
  }

    public  void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxerfirmatid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_pinfo_fitxer_fitxerfirm_fk"))
    private FitxerJPA fitxerfirmat;

    public FitxerJPA getFitxerfirmat() {
    return this.fitxerfirmat;
  }

    public  void setFitxerfirmat(FitxerJPA fitxerfirmat) {
    this.fitxerfirmat = fitxerfirmat;
  }


 // ---------------  STATIC METHODS ------------------
  public static PinfoJPA toJPA(Pinfo __bean) {
    if (__bean == null) { return null;}
    PinfoJPA __tmp = new PinfoJPA();
    __tmp.setPinfoID(__bean.getPinfoID());
    __tmp.setIncidenciaID(__bean.getIncidenciaID());
    __tmp.setEntitat(__bean.getEntitat());
    __tmp.setSolicitantNIF(__bean.getSolicitantNIF());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setFitxerfirmatID(__bean.getFitxerfirmatID());
    __tmp.setPortafibid(__bean.getPortafibid());
    __tmp.setDestinatariNIF(__bean.getDestinatariNIF());
    __tmp.setDestinatariNom(__bean.getDestinatariNom());
    __tmp.setMissatgePinbal(__bean.getMissatgePinbal());
    __tmp.setLogpPnbal(__bean.getLogpPnbal());
    __tmp.setMissatgeSolicitant(__bean.getMissatgeSolicitant());
    // Fitxer
    __tmp.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
    // Fitxer
    __tmp.setFitxerfirmat(FitxerJPA.toJPA(__bean.getFitxerfirmat()));
		return __tmp;
	}


  public static PinfoJPA copyJPA(PinfoJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PinfoJPA> copyJPA(java.util.Set<PinfoJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PinfoJPA> __tmpSet = (java.util.Set<PinfoJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PinfoJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PinfoJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PinfoJPA copyJPA(PinfoJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PinfoJPA __tmp = (PinfoJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PinfoDataJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pinfoDatas) || org.hibernate.Hibernate.isInitialized(__jpa.getPinfoDatas())) ) {
      __tmp.setPinfoDatas(PinfoDataJPA.copyJPA(__jpa.getPinfoDatas(), __alreadyCopied,"PinfoJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"IncidenciaTecnicaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.incidenciaTecnica) || org.hibernate.Hibernate.isInitialized(__jpa.getIncidenciaTecnica()) ) ) {
      __tmp.setIncidenciaTecnica(IncidenciaTecnicaJPA.copyJPA(__jpa.getIncidenciaTecnica(), __alreadyCopied,"PinfoJPA"));
    }

    return __tmp;
  }




}
