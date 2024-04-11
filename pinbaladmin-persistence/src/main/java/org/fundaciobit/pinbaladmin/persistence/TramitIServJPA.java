
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
        @Index(name="pad_tramiti_tramitid_fk_i", columnList = "tramitid"),
        @Index(name="pad_tramiti_fitxernormaid_fk_i", columnList = "fitxernormaid"),
        @Index(name="pad_tramiti_fitxer2id_fk_i", columnList = "fitxernorma2id")})
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

    @Column(name="urlnorma",nullable = false,length = 240)
    java.lang.String urlnorma;

    @Column(name="articles",nullable = false,length = 60)
    java.lang.String articles;

    @Column(name="norma2",length = 240)
    java.lang.String norma2;

    @Column(name="urlnorma2",length = 240)
    java.lang.String urlnorma2;

    @Column(name="articles2",length = 60)
    java.lang.String articles2;

    @Column(name="fitxernormaid",nullable = false,length = 19)
    java.lang.Long fitxernormaID;

    @Column(name="fitxernorma2id",length = 19)
    java.lang.Long fitxernorma2ID;



  /** Constructor Buit */
  public TramitIServJPA() {
  }

  /** Constructor amb tots els camps  */
  public TramitIServJPA(long servid , long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.String urlnorma , java.lang.String articles , java.lang.String norma2 , java.lang.String urlnorma2 , java.lang.String articles2 , java.lang.Long fitxernormaID , java.lang.Long fitxernorma2ID) {
    this.servid=servid;
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.urlnorma=urlnorma;
    this.articles=articles;
    this.norma2=norma2;
    this.urlnorma2=urlnorma2;
    this.articles2=articles2;
    this.fitxernormaID=fitxernormaID;
    this.fitxernorma2ID=fitxernorma2ID;
}
  /** Constructor sense valors autoincrementals */
  public TramitIServJPA(long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.String urlnorma , java.lang.String articles , java.lang.String norma2 , java.lang.String urlnorma2 , java.lang.String articles2 , java.lang.Long fitxernormaID , java.lang.Long fitxernorma2ID) {
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.urlnorma=urlnorma;
    this.articles=articles;
    this.norma2=norma2;
    this.urlnorma2=urlnorma2;
    this.articles2=articles2;
    this.fitxernormaID=fitxernormaID;
    this.fitxernorma2ID=fitxernorma2ID;
}
  /** Constructor dels valors Not Null */
  public TramitIServJPA(long servid , long tramitid , java.lang.String nom , java.lang.String codi , java.lang.String norma , java.lang.String urlnorma , java.lang.String articles , java.lang.Long fitxernormaID) {
    this.servid=servid;
    this.tramitid=tramitid;
    this.nom=nom;
    this.codi=codi;
    this.norma=norma;
    this.urlnorma=urlnorma;
    this.articles=articles;
    this.fitxernormaID=fitxernormaID;
}
  public TramitIServJPA(TramitIServ __bean) {
    this.setServid(__bean.getServid());
    this.setTramitid(__bean.getTramitid());
    this.setNom(__bean.getNom());
    this.setCodi(__bean.getCodi());
    this.setNorma(__bean.getNorma());
    this.setUrlnorma(__bean.getUrlnorma());
    this.setArticles(__bean.getArticles());
    this.setNorma2(__bean.getNorma2());
    this.setUrlnorma2(__bean.getUrlnorma2());
    this.setArticles2(__bean.getArticles2());
    this.setFitxernormaID(__bean.getFitxernormaID());
    this.setFitxernorma2ID(__bean.getFitxernorma2ID());
    // Fitxer
    this.setFitxernorma(FitxerJPA.toJPA(__bean.getFitxernorma()));
    // Fitxer
    this.setFitxernorma2(FitxerJPA.toJPA(__bean.getFitxernorma2()));
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

	public java.lang.String getUrlnorma() {
		return(urlnorma);
	};
	public void setUrlnorma(java.lang.String _urlnorma_) {
		this.urlnorma = _urlnorma_;
	};

	public java.lang.String getArticles() {
		return(articles);
	};
	public void setArticles(java.lang.String _articles_) {
		this.articles = _articles_;
	};

	public java.lang.String getNorma2() {
		return(norma2);
	};
	public void setNorma2(java.lang.String _norma2_) {
		this.norma2 = _norma2_;
	};

	public java.lang.String getUrlnorma2() {
		return(urlnorma2);
	};
	public void setUrlnorma2(java.lang.String _urlnorma2_) {
		this.urlnorma2 = _urlnorma2_;
	};

	public java.lang.String getArticles2() {
		return(articles2);
	};
	public void setArticles2(java.lang.String _articles2_) {
		this.articles2 = _articles2_;
	};

	public java.lang.Long getFitxernormaID() {
		return(fitxernormaID);
	};
	public void setFitxernormaID(java.lang.Long _fitxernormaID_) {
		this.fitxernormaID = _fitxernormaID_;
	};

	public java.lang.Long getFitxernorma2ID() {
		return(fitxernorma2ID);
	};
	public void setFitxernorma2ID(java.lang.Long _fitxernorma2ID_) {
		this.fitxernorma2ID = _fitxernorma2ID_;
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

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxernormaid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_tramiti_fitxer_norma_fk"))
    private FitxerJPA fitxernorma;

    public FitxerJPA getFitxernorma() {
    return this.fitxernorma;
  }

    public  void setFitxernorma(FitxerJPA fitxernorma) {
    this.fitxernorma = fitxernorma;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxernorma2id", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_tramiti_fitxer_norma2_fk"))
    private FitxerJPA fitxernorma2;

    public FitxerJPA getFitxernorma2() {
    return this.fitxernorma2;
  }

    public  void setFitxernorma2(FitxerJPA fitxernorma2) {
    this.fitxernorma2 = fitxernorma2;
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
    __tmp.setUrlnorma(__bean.getUrlnorma());
    __tmp.setArticles(__bean.getArticles());
    __tmp.setNorma2(__bean.getNorma2());
    __tmp.setUrlnorma2(__bean.getUrlnorma2());
    __tmp.setArticles2(__bean.getArticles2());
    __tmp.setFitxernormaID(__bean.getFitxernormaID());
    __tmp.setFitxernorma2ID(__bean.getFitxernorma2ID());
    // Fitxer
    __tmp.setFitxernorma(FitxerJPA.toJPA(__bean.getFitxernorma()));
    // Fitxer
    __tmp.setFitxernorma2(FitxerJPA.toJPA(__bean.getFitxernorma2()));
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
