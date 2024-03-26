
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


@Entity(name = "TramitJConsentJPA")
@Table(name = "pad_tramit_j_consent" , indexes = { 
        @Index(name="pad_tramit_j_consent_pk_i", columnList = "consentid"),
        @Index(name="pad_tramitj_tramitid_fk_i", columnList = "tramitid"),
        @Index(name="pad_tramitj_adjuntid_fk_i", columnList = "adjuntid")})
@SequenceGenerator(name="TRAMITJCONSENT_SEQ", sequenceName="pad_tramit_j_consent_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TramitJConsentJPA implements TramitJConsent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAMITJCONSENT_SEQ")
    @Column(name="consentid",nullable = false,length = 19)
    long consentid;

    @Column(name="tramitid",nullable = false,length = 19)
    long tramitid;

    @Column(name="consentiment",nullable = false,length = 80)
    java.lang.String consentiment;

    @Column(name="consentimentadjunt",length = 200)
    java.lang.String consentimentadjunt;

    @Column(name="urlconsentiment",length = 255)
    java.lang.String urlconsentiment;

    @Column(name="adjuntid",length = 19)
    java.lang.Long adjuntID;



  /** Constructor Buit */
  public TramitJConsentJPA() {
  }

  /** Constructor amb tots els camps  */
  public TramitJConsentJPA(long consentid , long tramitid , java.lang.String consentiment , java.lang.String consentimentadjunt , java.lang.String urlconsentiment , java.lang.Long adjuntID) {
    this.consentid=consentid;
    this.tramitid=tramitid;
    this.consentiment=consentiment;
    this.consentimentadjunt=consentimentadjunt;
    this.urlconsentiment=urlconsentiment;
    this.adjuntID=adjuntID;
}
  /** Constructor sense valors autoincrementals */
  public TramitJConsentJPA(long tramitid , java.lang.String consentiment , java.lang.String consentimentadjunt , java.lang.String urlconsentiment , java.lang.Long adjuntID) {
    this.tramitid=tramitid;
    this.consentiment=consentiment;
    this.consentimentadjunt=consentimentadjunt;
    this.urlconsentiment=urlconsentiment;
    this.adjuntID=adjuntID;
}
  /** Constructor dels valors Not Null */
  public TramitJConsentJPA(long consentid , long tramitid , java.lang.String consentiment) {
    this.consentid=consentid;
    this.tramitid=tramitid;
    this.consentiment=consentiment;
}
  public TramitJConsentJPA(TramitJConsent __bean) {
    this.setConsentid(__bean.getConsentid());
    this.setTramitid(__bean.getTramitid());
    this.setConsentiment(__bean.getConsentiment());
    this.setConsentimentadjunt(__bean.getConsentimentadjunt());
    this.setUrlconsentiment(__bean.getUrlconsentiment());
    this.setAdjuntID(__bean.getAdjuntID());
    // Fitxer
    this.setAdjunt(FitxerJPA.toJPA(__bean.getAdjunt()));
	}

	public long getConsentid() {
		return(consentid);
	};
	public void setConsentid(long _consentid_) {
		this.consentid = _consentid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public java.lang.String getConsentiment() {
		return(consentiment);
	};
	public void setConsentiment(java.lang.String _consentiment_) {
		this.consentiment = _consentiment_;
	};

	public java.lang.String getConsentimentadjunt() {
		return(consentimentadjunt);
	};
	public void setConsentimentadjunt(java.lang.String _consentimentadjunt_) {
		this.consentimentadjunt = _consentimentadjunt_;
	};

	public java.lang.String getUrlconsentiment() {
		return(urlconsentiment);
	};
	public void setUrlconsentiment(java.lang.String _urlconsentiment_) {
		this.urlconsentiment = _urlconsentiment_;
	};

	public java.lang.Long getAdjuntID() {
		return(adjuntID);
	};
	public void setAdjuntID(java.lang.Long _adjuntID_) {
		this.adjuntID = _adjuntID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TramitJConsent) {
      TramitJConsent __instance = (TramitJConsent)__obj;
      __result = true;
      __result = __result && (this.getConsentid() == __instance.getConsentid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:tramitid | Table: pad_tramit_a_pers_aut | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tramitid", referencedColumnName ="tramitid", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_tramitj_tramita_fk"))
    private TramitAPersAutJPA tramitAPersAut;

    public TramitAPersAutJPA getTramitAPersAut() {
    return this.tramitAPersAut;
  }

    public  void setTramitAPersAut(TramitAPersAutJPA tramitAPersAut) {
    this.tramitAPersAut = tramitAPersAut;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adjuntid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_tramitj_fitxer_adj_fk"))
    private FitxerJPA adjunt;

    public FitxerJPA getAdjunt() {
    return this.adjunt;
  }

    public  void setAdjunt(FitxerJPA adjunt) {
    this.adjunt = adjunt;
  }


 // ---------------  STATIC METHODS ------------------
  public static TramitJConsentJPA toJPA(TramitJConsent __bean) {
    if (__bean == null) { return null;}
    TramitJConsentJPA __tmp = new TramitJConsentJPA();
    __tmp.setConsentid(__bean.getConsentid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setConsentiment(__bean.getConsentiment());
    __tmp.setConsentimentadjunt(__bean.getConsentimentadjunt());
    __tmp.setUrlconsentiment(__bean.getUrlconsentiment());
    __tmp.setAdjuntID(__bean.getAdjuntID());
    // Fitxer
    __tmp.setAdjunt(FitxerJPA.toJPA(__bean.getAdjunt()));
		return __tmp;
	}


  public static TramitJConsentJPA copyJPA(TramitJConsentJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TramitJConsentJPA> copyJPA(java.util.Set<TramitJConsentJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TramitJConsentJPA> __tmpSet = (java.util.Set<TramitJConsentJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TramitJConsentJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TramitJConsentJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TramitJConsentJPA copyJPA(TramitJConsentJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TramitJConsentJPA __tmp = (TramitJConsentJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TramitAPersAutJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitAPersAut) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitAPersAut()) ) ) {
      __tmp.setTramitAPersAut(TramitAPersAutJPA.copyJPA(__jpa.getTramitAPersAut(), __alreadyCopied,"TramitJConsentJPA"));
    }

    return __tmp;
  }




}
