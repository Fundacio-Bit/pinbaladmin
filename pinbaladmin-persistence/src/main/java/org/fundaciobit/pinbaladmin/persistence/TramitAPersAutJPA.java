
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity(name = "TramitAPersAutJPA")
@Table(name = "pad_tramit_a_pers_aut" , indexes = { 
        @Index(name="pad_tramit_a_pers_aut_pk_i", columnList = "persautid")})
@SequenceGenerator(name="TRAMITAPERSAUT_SEQ", sequenceName="pad_tramit_a_pers_aut_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TramitAPersAutJPA implements TramitAPersAut {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAMITAPERSAUT_SEQ")
    @Column(name="persautid",nullable = false,length = 19)
    long persautid;

    @Column(name="tramitid",nullable = false,unique = true,length = 19)
    long tramitid;

    @Column(name="datatramit",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp datatramit;

    @Column(name="nif",nullable = false,length = 30)
    java.lang.String nif;

    @Column(name="mail",nullable = false,length = 100)
    java.lang.String mail;

    @Column(name="telefon",nullable = false,length = 10)
    java.lang.String telefon;

    @Column(name="nom",nullable = false,length = 40)
    java.lang.String nom;

    @Column(name="llinatge1",nullable = false,length = 40)
    java.lang.String llinatge1;

    @Column(name="llinatge2",length = 40)
    java.lang.String llinatge2;

    @Column(name="urlsistra",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String urlsistra;

    @Column(name="idsesionformulario",length = 100)
    java.lang.String idsesionformulario;

    @Column(name="idsesiontramite",length = 255)
    java.lang.String idsesiontramite;



  /** Constructor Buit */
  public TramitAPersAutJPA() {
  }

  /** Constructor amb tots els camps  */
  public TramitAPersAutJPA(long persautid , long tramitid , java.sql.Timestamp datatramit , java.lang.String nif , java.lang.String mail , java.lang.String telefon , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String urlsistra , java.lang.String idsesionformulario , java.lang.String idsesiontramite) {
    this.persautid=persautid;
    this.tramitid=tramitid;
    this.datatramit=datatramit;
    this.nif=nif;
    this.mail=mail;
    this.telefon=telefon;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.urlsistra=urlsistra;
    this.idsesionformulario=idsesionformulario;
    this.idsesiontramite=idsesiontramite;
}
  /** Constructor sense valors autoincrementals */
  public TramitAPersAutJPA(long tramitid , java.sql.Timestamp datatramit , java.lang.String nif , java.lang.String mail , java.lang.String telefon , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String urlsistra , java.lang.String idsesionformulario , java.lang.String idsesiontramite) {
    this.tramitid=tramitid;
    this.datatramit=datatramit;
    this.nif=nif;
    this.mail=mail;
    this.telefon=telefon;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.urlsistra=urlsistra;
    this.idsesionformulario=idsesionformulario;
    this.idsesiontramite=idsesiontramite;
}
  /** Constructor dels valors Not Null */
  public TramitAPersAutJPA(long persautid , long tramitid , java.sql.Timestamp datatramit , java.lang.String nif , java.lang.String mail , java.lang.String telefon , java.lang.String nom , java.lang.String llinatge1) {
    this.persautid=persautid;
    this.tramitid=tramitid;
    this.datatramit=datatramit;
    this.nif=nif;
    this.mail=mail;
    this.telefon=telefon;
    this.nom=nom;
    this.llinatge1=llinatge1;
}
  public TramitAPersAutJPA(TramitAPersAut __bean) {
    this.setPersautid(__bean.getPersautid());
    this.setTramitid(__bean.getTramitid());
    this.setDatatramit(__bean.getDatatramit());
    this.setNif(__bean.getNif());
    this.setMail(__bean.getMail());
    this.setTelefon(__bean.getTelefon());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setUrlsistra(__bean.getUrlsistra());
    this.setIdsesionformulario(__bean.getIdsesionformulario());
    this.setIdsesiontramite(__bean.getIdsesiontramite());
	}

	public long getPersautid() {
		return(persautid);
	};
	public void setPersautid(long _persautid_) {
		this.persautid = _persautid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public java.sql.Timestamp getDatatramit() {
		return(datatramit);
	};
	public void setDatatramit(java.sql.Timestamp _datatramit_) {
		this.datatramit = _datatramit_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getMail() {
		return(mail);
	};
	public void setMail(java.lang.String _mail_) {
		this.mail = _mail_;
	};

	public java.lang.String getTelefon() {
		return(telefon);
	};
	public void setTelefon(java.lang.String _telefon_) {
		this.telefon = _telefon_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getLlinatge1() {
		return(llinatge1);
	};
	public void setLlinatge1(java.lang.String _llinatge1_) {
		this.llinatge1 = _llinatge1_;
	};

	public java.lang.String getLlinatge2() {
		return(llinatge2);
	};
	public void setLlinatge2(java.lang.String _llinatge2_) {
		this.llinatge2 = _llinatge2_;
	};

	public java.lang.String getUrlsistra() {
		return(urlsistra);
	};
	public void setUrlsistra(java.lang.String _urlsistra_) {
		this.urlsistra = _urlsistra_;
	};

	public java.lang.String getIdsesionformulario() {
		return(idsesionformulario);
	};
	public void setIdsesionformulario(java.lang.String _idsesionformulario_) {
		this.idsesionformulario = _idsesionformulario_;
	};

	public java.lang.String getIdsesiontramite() {
		return(idsesiontramite);
	};
	public void setIdsesiontramite(java.lang.String _idsesiontramite_) {
		this.idsesiontramite = _idsesiontramite_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TramitAPersAut) {
      TramitAPersAut __instance = (TramitAPersAut)__obj;
      __result = true;
      __result = __result && (this.getPersautid() == __instance.getPersautid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:tramitid | Table: pad_tramit_b_dades_soli | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tramitAPersAut")
    private Set<TramitBDadesSoliJPA> tramitBDadesSolis = new HashSet<TramitBDadesSoliJPA>(0);
    public  Set<TramitBDadesSoliJPA> getTramitBDadesSolis() {
    return this.tramitBDadesSolis;
  }

    public void setTramitBDadesSolis(Set<TramitBDadesSoliJPA> tramitBDadesSolis) {
      this.tramitBDadesSolis = tramitBDadesSolis;
    }


// EXP  Field:tramitid | Table: pad_tramit_c_dades_cesi | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tramitAPersAut")
    private Set<TramitCDadesCesiJPA> tramitCDadesCesis = new HashSet<TramitCDadesCesiJPA>(0);
    public  Set<TramitCDadesCesiJPA> getTramitCDadesCesis() {
    return this.tramitCDadesCesis;
  }

    public void setTramitCDadesCesis(Set<TramitCDadesCesiJPA> tramitCDadesCesis) {
      this.tramitCDadesCesis = tramitCDadesCesis;
    }


// EXP  Field:tramitid | Table: pad_tramit_d_cte_aut | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tramitAPersAut")
    private Set<TramitDCteAutJPA> tramitDCteAuts = new HashSet<TramitDCteAutJPA>(0);
    public  Set<TramitDCteAutJPA> getTramitDCteAuts() {
    return this.tramitDCteAuts;
  }

    public void setTramitDCteAuts(Set<TramitDCteAutJPA> tramitDCteAuts) {
      this.tramitDCteAuts = tramitDCteAuts;
    }


// EXP  Field:tramitid | Table: pad_tramit_e_cte_aud | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tramitAPersAut")
    private Set<TramitECteAudJPA> tramitECteAuds = new HashSet<TramitECteAudJPA>(0);
    public  Set<TramitECteAudJPA> getTramitECteAuds() {
    return this.tramitECteAuds;
  }

    public void setTramitECteAuds(Set<TramitECteAudJPA> tramitECteAuds) {
      this.tramitECteAuds = tramitECteAuds;
    }


// EXP  Field:tramitid | Table: pad_tramit_f_cte_tec | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tramitAPersAut")
    private Set<TramitFCteTecJPA> tramitFCteTecs = new HashSet<TramitFCteTecJPA>(0);
    public  Set<TramitFCteTecJPA> getTramitFCteTecs() {
    return this.tramitFCteTecs;
  }

    public void setTramitFCteTecs(Set<TramitFCteTecJPA> tramitFCteTecs) {
      this.tramitFCteTecs = tramitFCteTecs;
    }


// EXP  Field:tramitid | Table: pad_tramit_g_dades_tit | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tramitAPersAut")
    private Set<TramitGDadesTitJPA> tramitGDadesTits = new HashSet<TramitGDadesTitJPA>(0);
    public  Set<TramitGDadesTitJPA> getTramitGDadesTits() {
    return this.tramitGDadesTits;
  }

    public void setTramitGDadesTits(Set<TramitGDadesTitJPA> tramitGDadesTits) {
      this.tramitGDadesTits = tramitGDadesTits;
    }


// EXP  Field:tramitid | Table: pad_tramit_h_proc | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tramitAPersAut")
    private Set<TramitHProcJPA> tramitHProcs = new HashSet<TramitHProcJPA>(0);
    public  Set<TramitHProcJPA> getTramitHProcs() {
    return this.tramitHProcs;
  }

    public void setTramitHProcs(Set<TramitHProcJPA> tramitHProcs) {
      this.tramitHProcs = tramitHProcs;
    }


// EXP  Field:tramitid | Table: pad_tramit_i_serv | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tramitAPersAut")
    private Set<TramitIServJPA> tramitIServs = new HashSet<TramitIServJPA>(0);
    public  Set<TramitIServJPA> getTramitIServs() {
    return this.tramitIServs;
  }

    public void setTramitIServs(Set<TramitIServJPA> tramitIServs) {
      this.tramitIServs = tramitIServs;
    }


// EXP  Field:tramitid | Table: pad_tramit_j_consent | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tramitAPersAut")
    private Set<TramitJConsentJPA> tramitJConsents = new HashSet<TramitJConsentJPA>(0);
    public  Set<TramitJConsentJPA> getTramitJConsents() {
    return this.tramitJConsents;
  }

    public void setTramitJConsents(Set<TramitJConsentJPA> tramitJConsents) {
      this.tramitJConsents = tramitJConsents;
    }



 // ---------------  STATIC METHODS ------------------
  public static TramitAPersAutJPA toJPA(TramitAPersAut __bean) {
    if (__bean == null) { return null;}
    TramitAPersAutJPA __tmp = new TramitAPersAutJPA();
    __tmp.setPersautid(__bean.getPersautid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setDatatramit(__bean.getDatatramit());
    __tmp.setNif(__bean.getNif());
    __tmp.setMail(__bean.getMail());
    __tmp.setTelefon(__bean.getTelefon());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setUrlsistra(__bean.getUrlsistra());
    __tmp.setIdsesionformulario(__bean.getIdsesionformulario());
    __tmp.setIdsesiontramite(__bean.getIdsesiontramite());
		return __tmp;
	}


  public static TramitAPersAutJPA copyJPA(TramitAPersAutJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TramitAPersAutJPA> copyJPA(java.util.Set<TramitAPersAutJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TramitAPersAutJPA> __tmpSet = (java.util.Set<TramitAPersAutJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TramitAPersAutJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TramitAPersAutJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TramitAPersAutJPA copyJPA(TramitAPersAutJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TramitAPersAutJPA __tmp = (TramitAPersAutJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"TramitGDadesTitJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitGDadesTits) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitGDadesTits())) ) {
      __tmp.setTramitGDadesTits(TramitGDadesTitJPA.copyJPA(__jpa.getTramitGDadesTits(), __alreadyCopied,"TramitAPersAutJPA"));
    }
    if(!"TramitIServJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitIServs) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitIServs())) ) {
      __tmp.setTramitIServs(TramitIServJPA.copyJPA(__jpa.getTramitIServs(), __alreadyCopied,"TramitAPersAutJPA"));
    }
    if(!"TramitECteAudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitECteAuds) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitECteAuds())) ) {
      __tmp.setTramitECteAuds(TramitECteAudJPA.copyJPA(__jpa.getTramitECteAuds(), __alreadyCopied,"TramitAPersAutJPA"));
    }
    if(!"TramitBDadesSoliJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitBDadesSolis) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitBDadesSolis())) ) {
      __tmp.setTramitBDadesSolis(TramitBDadesSoliJPA.copyJPA(__jpa.getTramitBDadesSolis(), __alreadyCopied,"TramitAPersAutJPA"));
    }
    if(!"TramitCDadesCesiJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitCDadesCesis) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitCDadesCesis())) ) {
      __tmp.setTramitCDadesCesis(TramitCDadesCesiJPA.copyJPA(__jpa.getTramitCDadesCesis(), __alreadyCopied,"TramitAPersAutJPA"));
    }
    if(!"TramitFCteTecJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitFCteTecs) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitFCteTecs())) ) {
      __tmp.setTramitFCteTecs(TramitFCteTecJPA.copyJPA(__jpa.getTramitFCteTecs(), __alreadyCopied,"TramitAPersAutJPA"));
    }
    if(!"TramitDCteAutJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitDCteAuts) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitDCteAuts())) ) {
      __tmp.setTramitDCteAuts(TramitDCteAutJPA.copyJPA(__jpa.getTramitDCteAuts(), __alreadyCopied,"TramitAPersAutJPA"));
    }
    if(!"TramitHProcJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitHProcs) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitHProcs())) ) {
      __tmp.setTramitHProcs(TramitHProcJPA.copyJPA(__jpa.getTramitHProcs(), __alreadyCopied,"TramitAPersAutJPA"));
    }
    if(!"TramitJConsentJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitJConsents) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitJConsents())) ) {
      __tmp.setTramitJConsents(TramitJConsentJPA.copyJPA(__jpa.getTramitJConsents(), __alreadyCopied,"TramitAPersAutJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
