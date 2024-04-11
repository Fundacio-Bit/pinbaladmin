
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


@Entity(name = "TramitDCteAutJPA")
@Table(name = "pad_tramit_d_cte_aut" , indexes = { 
        @Index(name="pad_tramit_d_cte_aut_pk_i", columnList = "cteautid"),
        @Index(name="pad_tramitd_tramitid_fk_i", columnList = "tramitid")})
@SequenceGenerator(name="TRAMITDCTEAUT_SEQ", sequenceName="pad_tramit_d_cte_aut_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TramitDCteAutJPA implements TramitDCteAut {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAMITDCTEAUT_SEQ")
    @Column(name="cteautid",nullable = false,length = 19)
    long cteautid;

    @Column(name="tramitid",nullable = false,length = 19)
    long tramitid;

    @Column(name="nif",nullable = false,length = 30)
    java.lang.String nif;

    @Column(name="nom",nullable = false,length = 40)
    java.lang.String nom;

    @Column(name="llinatge1",nullable = false,length = 40)
    java.lang.String llinatge1;

    @Column(name="llinatge2",nullable = false,length = 40)
    java.lang.String llinatge2;

    @Column(name="carrec",nullable = false,length = 100)
    java.lang.String carrec;

    @Column(name="telefon",nullable = false,length = 10)
    java.lang.String telefon;

    @Column(name="mail",nullable = false,length = 100)
    java.lang.String mail;



  /** Constructor Buit */
  public TramitDCteAutJPA() {
  }

  /** Constructor amb tots els camps  */
  public TramitDCteAutJPA(long cteautid , long tramitid , java.lang.String nif , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String carrec , java.lang.String telefon , java.lang.String mail) {
    this.cteautid=cteautid;
    this.tramitid=tramitid;
    this.nif=nif;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.carrec=carrec;
    this.telefon=telefon;
    this.mail=mail;
}
  /** Constructor sense valors autoincrementals */
  public TramitDCteAutJPA(long tramitid , java.lang.String nif , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String carrec , java.lang.String telefon , java.lang.String mail) {
    this.tramitid=tramitid;
    this.nif=nif;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.carrec=carrec;
    this.telefon=telefon;
    this.mail=mail;
}
  public TramitDCteAutJPA(TramitDCteAut __bean) {
    this.setCteautid(__bean.getCteautid());
    this.setTramitid(__bean.getTramitid());
    this.setNif(__bean.getNif());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setCarrec(__bean.getCarrec());
    this.setTelefon(__bean.getTelefon());
    this.setMail(__bean.getMail());
	}

	public long getCteautid() {
		return(cteautid);
	};
	public void setCteautid(long _cteautid_) {
		this.cteautid = _cteautid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
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

	public java.lang.String getCarrec() {
		return(carrec);
	};
	public void setCarrec(java.lang.String _carrec_) {
		this.carrec = _carrec_;
	};

	public java.lang.String getTelefon() {
		return(telefon);
	};
	public void setTelefon(java.lang.String _telefon_) {
		this.telefon = _telefon_;
	};

	public java.lang.String getMail() {
		return(mail);
	};
	public void setMail(java.lang.String _mail_) {
		this.mail = _mail_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TramitDCteAut) {
      TramitDCteAut __instance = (TramitDCteAut)__obj;
      __result = true;
      __result = __result && (this.getCteautid() == __instance.getCteautid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:tramitid | Table: pad_tramit_a_pers_aut | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tramitid", referencedColumnName ="tramitid", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_tramitd_tramita_fk"))
    private TramitAPersAutJPA tramitAPersAut;

    public TramitAPersAutJPA getTramitAPersAut() {
    return this.tramitAPersAut;
  }

    public  void setTramitAPersAut(TramitAPersAutJPA tramitAPersAut) {
    this.tramitAPersAut = tramitAPersAut;
  }


 // ---------------  STATIC METHODS ------------------
  public static TramitDCteAutJPA toJPA(TramitDCteAut __bean) {
    if (__bean == null) { return null;}
    TramitDCteAutJPA __tmp = new TramitDCteAutJPA();
    __tmp.setCteautid(__bean.getCteautid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setNif(__bean.getNif());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setCarrec(__bean.getCarrec());
    __tmp.setTelefon(__bean.getTelefon());
    __tmp.setMail(__bean.getMail());
		return __tmp;
	}


  public static TramitDCteAutJPA copyJPA(TramitDCteAutJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TramitDCteAutJPA> copyJPA(java.util.Set<TramitDCteAutJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TramitDCteAutJPA> __tmpSet = (java.util.Set<TramitDCteAutJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TramitDCteAutJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TramitDCteAutJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TramitDCteAutJPA copyJPA(TramitDCteAutJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TramitDCteAutJPA __tmp = (TramitDCteAutJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TramitAPersAutJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitAPersAut) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitAPersAut()) ) ) {
      __tmp.setTramitAPersAut(TramitAPersAutJPA.copyJPA(__jpa.getTramitAPersAut(), __alreadyCopied,"TramitDCteAutJPA"));
    }

    return __tmp;
  }




}
