
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


@Entity(name = "TramitIServJPA")
@Table(name = "pad_tramit_i_serv" , indexes = { 
        @Index(name="pad_tramit_i_serv_pk_i", columnList = "servid"),
        @Index(name="pad_tramiti_tramitid_fk_i", columnList = "tramitid")})
@SequenceGenerator(name="TRAMITISERV_SEQ", sequenceName="pad_tramit_i_serv_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TramitIServJPA implements TramitIServ {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAMITISERV_SEQ")
    @Column(name="servid",nullable = false,length = 19)
    long servid;

    @Column(name="tramitid",nullable = false,length = 19)
    long tramitid;

    @Column(name="nom",nullable = false,length = 240)
    java.lang.String nom;

    @Column(name="codi",nullable = false,length = 100)
    java.lang.String codi;

    @Column(name="norma",nullable = false,length = 240)
    java.lang.String norma;

    @Column(name="articles",nullable = false,length = 60)
    java.lang.String articles;

    @Column(name="consentiment",nullable = false,length = 30)
    java.lang.String consentiment;

    @Column(name="urlnorma",nullable = false,length = 240)
    java.lang.String urlnorma;

    @Column(name="consentimentpublicat",nullable = false,length = 30)
    java.lang.String consentimentpublicat;

    @Column(name="urlconsentiment",nullable = false,length = 240)
    java.lang.String urlconsentiment;



  /** Constructor Buit */
  public TramitIServJPA() {
  }

  /** Constructor amb tots els camps  */
  public TramitIServJPA(long servid , long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.String articles , java.lang.String consentiment , java.lang.String urlnorma , java.lang.String consentimentpublicat , java.lang.String urlconsentiment) {
    this.servid=servid;
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.articles=articles;
    this.consentiment=consentiment;
    this.urlnorma=urlnorma;
    this.consentimentpublicat=consentimentpublicat;
    this.urlconsentiment=urlconsentiment;
}
  /** Constructor sense valors autoincrementals */
  public TramitIServJPA(long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.String articles , java.lang.String consentiment , java.lang.String urlnorma , java.lang.String consentimentpublicat , java.lang.String urlconsentiment) {
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.articles=articles;
    this.consentiment=consentiment;
    this.urlnorma=urlnorma;
    this.consentimentpublicat=consentimentpublicat;
    this.urlconsentiment=urlconsentiment;
}
  public TramitIServJPA(TramitIServ __bean) {
    this.setServid(__bean.getServid());
    this.setTramitid(__bean.getTramitid());
    this.setNom(__bean.getNom());
    this.setCodi(__bean.getCodi());
    this.setNorma(__bean.getNorma());
    this.setArticles(__bean.getArticles());
    this.setConsentiment(__bean.getConsentiment());
    this.setUrlnorma(__bean.getUrlnorma());
    this.setConsentimentpublicat(__bean.getConsentimentpublicat());
    this.setUrlconsentiment(__bean.getUrlconsentiment());
	}

	public long getServid() {
		return(servid);
	};
	public void setServid(long _servid_) {
		this.servid = _servid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getCodi() {
		return(codi);
	};
	public void setCodi(java.lang.String _codi_) {
		this.codi = _codi_;
	};

	public java.lang.String getNorma() {
		return(norma);
	};
	public void setNorma(java.lang.String _norma_) {
		this.norma = _norma_;
	};

	public java.lang.String getArticles() {
		return(articles);
	};
	public void setArticles(java.lang.String _articles_) {
		this.articles = _articles_;
	};

	public java.lang.String getConsentiment() {
		return(consentiment);
	};
	public void setConsentiment(java.lang.String _consentiment_) {
		this.consentiment = _consentiment_;
	};

	public java.lang.String getUrlnorma() {
		return(urlnorma);
	};
	public void setUrlnorma(java.lang.String _urlnorma_) {
		this.urlnorma = _urlnorma_;
	};

	public java.lang.String getConsentimentpublicat() {
		return(consentimentpublicat);
	};
	public void setConsentimentpublicat(java.lang.String _consentimentpublicat_) {
		this.consentimentpublicat = _consentimentpublicat_;
	};

	public java.lang.String getUrlconsentiment() {
		return(urlconsentiment);
	};
	public void setUrlconsentiment(java.lang.String _urlconsentiment_) {
		this.urlconsentiment = _urlconsentiment_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TramitIServ) {
      TramitIServ __instance = (TramitIServ)__obj;
      __result = true;
      __result = __result && (this.getServid() == __instance.getServid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:tramitid | Table: pad_tramit_a_pers_aut | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tramitid", referencedColumnName ="tramitid", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_tramiti_tramita_fk"))
    private TramitAPersAutJPA tramitAPersAut;

    public TramitAPersAutJPA getTramitAPersAut() {
    return this.tramitAPersAut;
  }

    public  void setTramitAPersAut(TramitAPersAutJPA tramitAPersAut) {
    this.tramitAPersAut = tramitAPersAut;
  }


 // ---------------  STATIC METHODS ------------------
  public static TramitIServJPA toJPA(TramitIServ __bean) {
    if (__bean == null) { return null;}
    TramitIServJPA __tmp = new TramitIServJPA();
    __tmp.setServid(__bean.getServid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setNom(__bean.getNom());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setNorma(__bean.getNorma());
    __tmp.setArticles(__bean.getArticles());
    __tmp.setConsentiment(__bean.getConsentiment());
    __tmp.setUrlnorma(__bean.getUrlnorma());
    __tmp.setConsentimentpublicat(__bean.getConsentimentpublicat());
    __tmp.setUrlconsentiment(__bean.getUrlconsentiment());
		return __tmp;
	}


  public static TramitIServJPA copyJPA(TramitIServJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TramitIServJPA> copyJPA(java.util.Set<TramitIServJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TramitIServJPA> __tmpSet = (java.util.Set<TramitIServJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TramitIServJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TramitIServJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TramitIServJPA copyJPA(TramitIServJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TramitIServJPA __tmp = (TramitIServJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TramitAPersAutJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitAPersAut) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitAPersAut()) ) ) {
      __tmp.setTramitAPersAut(TramitAPersAutJPA.copyJPA(__jpa.getTramitAPersAut(), __alreadyCopied,"TramitIServJPA"));
    }

    return __tmp;
  }




}