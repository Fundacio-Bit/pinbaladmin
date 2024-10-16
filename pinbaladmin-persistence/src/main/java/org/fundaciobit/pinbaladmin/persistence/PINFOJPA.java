
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


@Entity(name = "PINFOJPA")
@Table(name = "pad_pinfo" , indexes = { 
        @Index(name="pad_pinfo_pk_i", columnList = "pinfoid"),
        @Index(name="pad_pinfo_incidenciaid_fk_i", columnList = "incidenciaid"),
        @Index(name="pad_pinfo_fitxerid_fk_i", columnList = "fitxerid"),
        @Index(name="pad_pinfo_fitxerfirmatid_fk_i", columnList = "fitxerfirmatid")})
@SequenceGenerator(name="PINFO_SEQ", sequenceName="pad_pinfo_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PINFOJPA implements PINFO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINFO_SEQ")
    @Column(name="pinfoid",nullable = false,length = 19)
    long PinfoID;

    @Column(name="incidenciaid",length = 19)
    java.lang.Long IncidenciaID;

    @Column(name="estat",length = 19)
    java.lang.Long estat;

    @Column(name="fitxerid",length = 19)
    java.lang.Long fitxerID;

    @Column(name="fitxerfirmatid",length = 19)
    java.lang.Long fitxerfirmatID;

    @Column(name="portafibid",length = 50)
    java.lang.String portafibid;



  /** Constructor Buit */
  public PINFOJPA() {
  }

  /** Constructor amb tots els camps  */
  public PINFOJPA(long PinfoID , java.lang.Long IncidenciaID , java.lang.Long estat , java.lang.Long fitxerID , java.lang.Long fitxerfirmatID , java.lang.String portafibid) {
    this.PinfoID=PinfoID;
    this.IncidenciaID=IncidenciaID;
    this.estat=estat;
    this.fitxerID=fitxerID;
    this.fitxerfirmatID=fitxerfirmatID;
    this.portafibid=portafibid;
}
  /** Constructor sense valors autoincrementals */
  public PINFOJPA(java.lang.Long IncidenciaID , java.lang.Long estat , java.lang.Long fitxerID , java.lang.Long fitxerfirmatID , java.lang.String portafibid) {
    this.IncidenciaID=IncidenciaID;
    this.estat=estat;
    this.fitxerID=fitxerID;
    this.fitxerfirmatID=fitxerfirmatID;
    this.portafibid=portafibid;
}
  /** Constructor dels valors Not Null */
  public PINFOJPA(long PinfoID) {
    this.PinfoID=PinfoID;
}
  public PINFOJPA(PINFO __bean) {
    this.setPinfoID(__bean.getPinfoID());
    this.setIncidenciaID(__bean.getIncidenciaID());
    this.setEstat(__bean.getEstat());
    this.setFitxerID(__bean.getFitxerID());
    this.setFitxerfirmatID(__bean.getFitxerfirmatID());
    this.setPortafibid(__bean.getPortafibid());
    // Fitxer
    this.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
    // Fitxer
    this.setFitxerfirmat(FitxerJPA.toJPA(__bean.getFitxerfirmat()));
	}

	public long getPinfoID() {
		return(PinfoID);
	};
	public void setPinfoID(long _PinfoID_) {
		this.PinfoID = _PinfoID_;
	};

	public java.lang.Long getIncidenciaID() {
		return(IncidenciaID);
	};
	public void setIncidenciaID(java.lang.Long _IncidenciaID_) {
		this.IncidenciaID = _IncidenciaID_;
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof PINFO) {
      PINFO __instance = (PINFO)__obj;
      __result = true;
      __result = __result && (this.getPinfoID() == __instance.getPinfoID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:pinfoid | Table: pad_pinfodata | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pINFO")
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
  public static PINFOJPA toJPA(PINFO __bean) {
    if (__bean == null) { return null;}
    PINFOJPA __tmp = new PINFOJPA();
    __tmp.setPinfoID(__bean.getPinfoID());
    __tmp.setIncidenciaID(__bean.getIncidenciaID());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setFitxerfirmatID(__bean.getFitxerfirmatID());
    __tmp.setPortafibid(__bean.getPortafibid());
    // Fitxer
    __tmp.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
    // Fitxer
    __tmp.setFitxerfirmat(FitxerJPA.toJPA(__bean.getFitxerfirmat()));
		return __tmp;
	}


  public static PINFOJPA copyJPA(PINFOJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PINFOJPA> copyJPA(java.util.Set<PINFOJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PINFOJPA> __tmpSet = (java.util.Set<PINFOJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PINFOJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PINFOJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PINFOJPA copyJPA(PINFOJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PINFOJPA __tmp = (PINFOJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PinfoDataJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.pinfoDatas) || org.hibernate.Hibernate.isInitialized(__jpa.getPinfoDatas())) ) {
      __tmp.setPinfoDatas(PinfoDataJPA.copyJPA(__jpa.getPinfoDatas(), __alreadyCopied,"PINFOJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"IncidenciaTecnicaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.incidenciaTecnica) || org.hibernate.Hibernate.isInitialized(__jpa.getIncidenciaTecnica()) ) ) {
      __tmp.setIncidenciaTecnica(IncidenciaTecnicaJPA.copyJPA(__jpa.getIncidenciaTecnica(), __alreadyCopied,"PINFOJPA"));
    }

    return __tmp;
  }




}
