
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


@Entity(name = "TramitHProcJPA")
@Table(name = "pad_tramit_h_proc" , indexes = { 
        @Index(name="pad_tramit_h_proc_pk_i", columnList = "procid"),
        @Index(name="pad_tramith_tramitid_fk_i", columnList = "tramitid")})
@SequenceGenerator(name="TRAMITHPROC_SEQ", sequenceName="pad_tramit_h_proc_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TramitHProcJPA implements TramitHProc {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAMITHPROC_SEQ")
    @Column(name="procid",nullable = false,length = 19)
    long procid;

    @Column(name="tramitid",nullable = false,length = 19)
    long tramitid;

    @Column(name="tipus",nullable = false,length = 240)
    java.lang.String tipus;

    @Column(name="nom",nullable = false,length = 240)
    java.lang.String nom;

    @Column(name="codi",nullable = false,length = 30)
    java.lang.String codi;

    @Column(name="urlseu",nullable = false,length = 240)
    java.lang.String urlseu;

    @Column(name="caducitat",nullable = false,length = 1)
    boolean caducitat;

    @Column(name="caducitatdata",length = 29,precision = 6)
    java.sql.Timestamp caducitatdata;

    @Column(name="descripcio",nullable = false,length = 240)
    java.lang.String descripcio;

    @Column(name="peticionsaldia",nullable = false,length = 19)
    long peticionsaldia;

    @Column(name="peticionsalmes",nullable = false,length = 19)
    long peticionsalmes;

    @Column(name="periodico",nullable = false,length = 1)
    boolean periodico;

    @Column(name="automatizado",nullable = false,length = 1)
    boolean automatizado;



  /** Constructor Buit */
  public TramitHProcJPA() {
  }

  /** Constructor amb tots els camps  */
  public TramitHProcJPA(long procid , long tramitid , java.lang.String tipus , java.lang.String nom , java.lang.String codi , java.lang.String urlseu , boolean caducitat , java.sql.Timestamp caducitatdata , java.lang.String descripcio , long peticionsaldia , long peticionsalmes , boolean periodico , boolean automatizado) {
    this.procid=procid;
    this.tramitid=tramitid;
    this.tipus=tipus;
    this.nom=nom;
    this.codi=codi;
    this.urlseu=urlseu;
    this.caducitat=caducitat;
    this.caducitatdata=caducitatdata;
    this.descripcio=descripcio;
    this.peticionsaldia=peticionsaldia;
    this.peticionsalmes=peticionsalmes;
    this.periodico=periodico;
    this.automatizado=automatizado;
}
  /** Constructor sense valors autoincrementals */
  public TramitHProcJPA(long tramitid , java.lang.String tipus , java.lang.String nom , java.lang.String codi , java.lang.String urlseu , boolean caducitat , java.sql.Timestamp caducitatdata , java.lang.String descripcio , long peticionsaldia , long peticionsalmes , boolean periodico , boolean automatizado) {
    this.tramitid=tramitid;
    this.tipus=tipus;
    this.nom=nom;
    this.codi=codi;
    this.urlseu=urlseu;
    this.caducitat=caducitat;
    this.caducitatdata=caducitatdata;
    this.descripcio=descripcio;
    this.peticionsaldia=peticionsaldia;
    this.peticionsalmes=peticionsalmes;
    this.periodico=periodico;
    this.automatizado=automatizado;
}
  public TramitHProcJPA(TramitHProc __bean) {
    this.setProcid(__bean.getProcid());
    this.setTramitid(__bean.getTramitid());
    this.setTipus(__bean.getTipus());
    this.setNom(__bean.getNom());
    this.setCodi(__bean.getCodi());
    this.setUrlseu(__bean.getUrlseu());
    this.setCaducitat(__bean.isCaducitat());
    this.setCaducitatdata(__bean.getCaducitatdata());
    this.setDescripcio(__bean.getDescripcio());
    this.setPeticionsaldia(__bean.getPeticionsaldia());
    this.setPeticionsalmes(__bean.getPeticionsalmes());
    this.setPeriodico(__bean.isPeriodico());
    this.setAutomatizado(__bean.isAutomatizado());
	}

	public long getProcid() {
		return(procid);
	};
	public void setProcid(long _procid_) {
		this.procid = _procid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public java.lang.String getTipus() {
		return(tipus);
	};
	public void setTipus(java.lang.String _tipus_) {
		this.tipus = _tipus_;
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

	public java.lang.String getUrlseu() {
		return(urlseu);
	};
	public void setUrlseu(java.lang.String _urlseu_) {
		this.urlseu = _urlseu_;
	};

	public boolean isCaducitat() {
		return(caducitat);
	};
	public void setCaducitat(boolean _caducitat_) {
		this.caducitat = _caducitat_;
	};

	public java.sql.Timestamp getCaducitatdata() {
		return(caducitatdata);
	};
	public void setCaducitatdata(java.sql.Timestamp _caducitatdata_) {
		this.caducitatdata = _caducitatdata_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public long getPeticionsaldia() {
		return(peticionsaldia);
	};
	public void setPeticionsaldia(long _peticionsaldia_) {
		this.peticionsaldia = _peticionsaldia_;
	};

	public long getPeticionsalmes() {
		return(peticionsalmes);
	};
	public void setPeticionsalmes(long _peticionsalmes_) {
		this.peticionsalmes = _peticionsalmes_;
	};

	public boolean isPeriodico() {
		return(periodico);
	};
	public void setPeriodico(boolean _periodico_) {
		this.periodico = _periodico_;
	};

	public boolean isAutomatizado() {
		return(automatizado);
	};
	public void setAutomatizado(boolean _automatizado_) {
		this.automatizado = _automatizado_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TramitHProc) {
      TramitHProc __instance = (TramitHProc)__obj;
      __result = true;
      __result = __result && (this.getProcid() == __instance.getProcid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:tramitid | Table: pad_tramit_a_pers_aut | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tramitid", referencedColumnName ="tramitid", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_tramith_tramita_fk"))
    private TramitAPersAutJPA tramitAPersAut;

    public TramitAPersAutJPA getTramitAPersAut() {
    return this.tramitAPersAut;
  }

    public  void setTramitAPersAut(TramitAPersAutJPA tramitAPersAut) {
    this.tramitAPersAut = tramitAPersAut;
  }


 // ---------------  STATIC METHODS ------------------
  public static TramitHProcJPA toJPA(TramitHProc __bean) {
    if (__bean == null) { return null;}
    TramitHProcJPA __tmp = new TramitHProcJPA();
    __tmp.setProcid(__bean.getProcid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setNom(__bean.getNom());
    __tmp.setCodi(__bean.getCodi());
    __tmp.setUrlseu(__bean.getUrlseu());
    __tmp.setCaducitat(__bean.isCaducitat());
    __tmp.setCaducitatdata(__bean.getCaducitatdata());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setPeticionsaldia(__bean.getPeticionsaldia());
    __tmp.setPeticionsalmes(__bean.getPeticionsalmes());
    __tmp.setPeriodico(__bean.isPeriodico());
    __tmp.setAutomatizado(__bean.isAutomatizado());
		return __tmp;
	}


  public static TramitHProcJPA copyJPA(TramitHProcJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TramitHProcJPA> copyJPA(java.util.Set<TramitHProcJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TramitHProcJPA> __tmpSet = (java.util.Set<TramitHProcJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TramitHProcJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TramitHProcJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TramitHProcJPA copyJPA(TramitHProcJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TramitHProcJPA __tmp = (TramitHProcJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TramitAPersAutJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitAPersAut) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitAPersAut()) ) ) {
      __tmp.setTramitAPersAut(TramitAPersAutJPA.copyJPA(__jpa.getTramitAPersAut(), __alreadyCopied,"TramitHProcJPA"));
    }

    return __tmp;
  }




}
