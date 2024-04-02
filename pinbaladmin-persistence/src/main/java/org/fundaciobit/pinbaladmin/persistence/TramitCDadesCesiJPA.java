
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


@Entity(name = "TramitCDadesCesiJPA")
@Table(name = "pad_tramit_c_dades_cesi" , indexes = { 
        @Index(name="pad_tramit_c_dades_cesi_pk_i", columnList = "dadescesiid"),
        @Index(name="pad_tramitc_tramitid_fk_i", columnList = "tramitid"),
        @Index(name="pad_tramitc_organid_fk_i", columnList = "organid"),
        @Index(name="pad_tramitc_organ_resp_fk_i", columnList = "organresponsableid")})
@SequenceGenerator(name="TRAMITCDADESCESI_SEQ", sequenceName="pad_tramit_c_dades_cesi_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TramitCDadesCesiJPA implements TramitCDadesCesi {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAMITCDADESCESI_SEQ")
    @Column(name="dadescesiid",nullable = false,length = 19)
    long dadescesiid;

    @Column(name="tramitid",nullable = false,length = 19)
    long tramitid;

    @Column(name="organid",nullable = false,length = 19)
    java.lang.Long organID;

    @Column(name="organresponsableid",nullable = false,length = 19)
    java.lang.Long organArrelID;

    @Column(name="denominacio",length = 240)
    java.lang.String denominacio;

    @Column(name="nif",length = 30)
    java.lang.String nif;

    @Column(name="responsable",length = 240)
    java.lang.String responsable;

    @Column(name="dir3responsable",length = 30)
    java.lang.String dir3responsable;

    @Column(name="dir3arrel",length = 30)
    java.lang.String dir3arrel;

    @Column(name="direccio",nullable = false,length = 240)
    java.lang.String direccio;

    @Column(name="codipostal",nullable = false,length = 10)
    java.lang.String codipostal;

    @Column(name="municipi",nullable = false,length = 40)
    java.lang.String municipi;



  /** Constructor Buit */
  public TramitCDadesCesiJPA() {
  }

  /** Constructor amb tots els camps  */
  public TramitCDadesCesiJPA(long dadescesiid , long tramitid , java.lang.Long organID , java.lang.Long organArrelID , java.lang.String denominacio , java.lang.String nif , java.lang.String responsable , java.lang.String dir3responsable , java.lang.String dir3arrel , java.lang.String direccio , java.lang.String codipostal , java.lang.String municipi) {
    this.dadescesiid=dadescesiid;
    this.tramitid=tramitid;
    this.organID=organID;
    this.organArrelID=organArrelID;
    this.denominacio=denominacio;
    this.nif=nif;
    this.responsable=responsable;
    this.dir3responsable=dir3responsable;
    this.dir3arrel=dir3arrel;
    this.direccio=direccio;
    this.codipostal=codipostal;
    this.municipi=municipi;
}
  /** Constructor sense valors autoincrementals */
  public TramitCDadesCesiJPA(long tramitid , java.lang.Long organID , java.lang.Long organArrelID , java.lang.String denominacio , java.lang.String nif , java.lang.String responsable , java.lang.String dir3responsable , java.lang.String dir3arrel , java.lang.String direccio , java.lang.String codipostal , java.lang.String municipi) {
    this.tramitid=tramitid;
    this.organID=organID;
    this.organArrelID=organArrelID;
    this.denominacio=denominacio;
    this.nif=nif;
    this.responsable=responsable;
    this.dir3responsable=dir3responsable;
    this.dir3arrel=dir3arrel;
    this.direccio=direccio;
    this.codipostal=codipostal;
    this.municipi=municipi;
}
  /** Constructor dels valors Not Null */
  public TramitCDadesCesiJPA(long dadescesiid , long tramitid , java.lang.Long organID , java.lang.Long organArrelID , java.lang.String direccio , java.lang.String codipostal , java.lang.String municipi) {
    this.dadescesiid=dadescesiid;
    this.tramitid=tramitid;
    this.organID=organID;
    this.organArrelID=organArrelID;
    this.direccio=direccio;
    this.codipostal=codipostal;
    this.municipi=municipi;
}
  public TramitCDadesCesiJPA(TramitCDadesCesi __bean) {
    this.setDadescesiid(__bean.getDadescesiid());
    this.setTramitid(__bean.getTramitid());
    this.setOrganID(__bean.getOrganID());
    this.setOrganArrelID(__bean.getOrganArrelID());
    this.setDenominacio(__bean.getDenominacio());
    this.setNif(__bean.getNif());
    this.setResponsable(__bean.getResponsable());
    this.setDir3responsable(__bean.getDir3responsable());
    this.setDir3arrel(__bean.getDir3arrel());
    this.setDireccio(__bean.getDireccio());
    this.setCodipostal(__bean.getCodipostal());
    this.setMunicipi(__bean.getMunicipi());
	}

	public long getDadescesiid() {
		return(dadescesiid);
	};
	public void setDadescesiid(long _dadescesiid_) {
		this.dadescesiid = _dadescesiid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public java.lang.Long getOrganID() {
		return(organID);
	};
	public void setOrganID(java.lang.Long _organID_) {
		this.organID = _organID_;
	};

	public java.lang.Long getOrganArrelID() {
		return(organArrelID);
	};
	public void setOrganArrelID(java.lang.Long _organArrelID_) {
		this.organArrelID = _organArrelID_;
	};

	public java.lang.String getDenominacio() {
		return(denominacio);
	};
	public void setDenominacio(java.lang.String _denominacio_) {
		this.denominacio = _denominacio_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getResponsable() {
		return(responsable);
	};
	public void setResponsable(java.lang.String _responsable_) {
		this.responsable = _responsable_;
	};

	public java.lang.String getDir3responsable() {
		return(dir3responsable);
	};
	public void setDir3responsable(java.lang.String _dir3responsable_) {
		this.dir3responsable = _dir3responsable_;
	};

	public java.lang.String getDir3arrel() {
		return(dir3arrel);
	};
	public void setDir3arrel(java.lang.String _dir3arrel_) {
		this.dir3arrel = _dir3arrel_;
	};

	public java.lang.String getDireccio() {
		return(direccio);
	};
	public void setDireccio(java.lang.String _direccio_) {
		this.direccio = _direccio_;
	};

	public java.lang.String getCodipostal() {
		return(codipostal);
	};
	public void setCodipostal(java.lang.String _codipostal_) {
		this.codipostal = _codipostal_;
	};

	public java.lang.String getMunicipi() {
		return(municipi);
	};
	public void setMunicipi(java.lang.String _municipi_) {
		this.municipi = _municipi_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TramitCDadesCesi) {
      TramitCDadesCesi __instance = (TramitCDadesCesi)__obj;
      __result = true;
      __result = __result && (this.getDadescesiid() == __instance.getDadescesiid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:tramitid | Table: pad_tramit_a_pers_aut | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tramitid", referencedColumnName ="tramitid", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_tramitc_tramita_fk"))
    private TramitAPersAutJPA tramitAPersAut;

    public TramitAPersAutJPA getTramitAPersAut() {
    return this.tramitAPersAut;
  }

    public  void setTramitAPersAut(TramitAPersAutJPA tramitAPersAut) {
    this.tramitAPersAut = tramitAPersAut;
  }


 // ---------------  STATIC METHODS ------------------
  public static TramitCDadesCesiJPA toJPA(TramitCDadesCesi __bean) {
    if (__bean == null) { return null;}
    TramitCDadesCesiJPA __tmp = new TramitCDadesCesiJPA();
    __tmp.setDadescesiid(__bean.getDadescesiid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setOrganID(__bean.getOrganID());
    __tmp.setOrganArrelID(__bean.getOrganArrelID());
    __tmp.setDenominacio(__bean.getDenominacio());
    __tmp.setNif(__bean.getNif());
    __tmp.setResponsable(__bean.getResponsable());
    __tmp.setDir3responsable(__bean.getDir3responsable());
    __tmp.setDir3arrel(__bean.getDir3arrel());
    __tmp.setDireccio(__bean.getDireccio());
    __tmp.setCodipostal(__bean.getCodipostal());
    __tmp.setMunicipi(__bean.getMunicipi());
		return __tmp;
	}


  public static TramitCDadesCesiJPA copyJPA(TramitCDadesCesiJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TramitCDadesCesiJPA> copyJPA(java.util.Set<TramitCDadesCesiJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TramitCDadesCesiJPA> __tmpSet = (java.util.Set<TramitCDadesCesiJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TramitCDadesCesiJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TramitCDadesCesiJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TramitCDadesCesiJPA copyJPA(TramitCDadesCesiJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TramitCDadesCesiJPA __tmp = (TramitCDadesCesiJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TramitAPersAutJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitAPersAut) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitAPersAut()) ) ) {
      __tmp.setTramitAPersAut(TramitAPersAutJPA.copyJPA(__jpa.getTramitAPersAut(), __alreadyCopied,"TramitCDadesCesiJPA"));
    }

    return __tmp;
  }




}
