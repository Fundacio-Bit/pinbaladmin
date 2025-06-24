
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


@Entity(name = "ModificacioSoliServJPA")
@Table(name = "pad_mod_soliserv" , indexes = { 
        @Index(name="pad_mod_soliserv_pk_i", columnList = "modsoliservid"),
        @Index(name="pad_modsolser_soliservid_fk_i", columnList = "soliservid"),
        @Index(name="pad_modsolser_modsoliid_fk_i", columnList = "modsoliid"),
        @Index(name="pad_modsolser_fitxern1id_fk_i", columnList = "fitxernorma1id"),
        @Index(name="pad_modsolser_fitxern2id_fk_i", columnList = "fitxernorma2id"),
        @Index(name="pad_modsolser_fitxern3id_fk_i", columnList = "fitxernorma3id")})
@SequenceGenerator(name="MODIFICACIOSOLISERV_SEQ", sequenceName="pad_mod_soliserv_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class ModificacioSoliServJPA implements ModificacioSoliServ {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MODIFICACIOSOLISERV_SEQ")
    @Column(name="modsoliservid",nullable = false,length = 19)
    long modsoliservid;

    @Column(name="soliservid",nullable = false,length = 19)
    long soliServID;

    @Column(name="modsoliid",nullable = false,length = 19)
    long modSoliID;

    @Column(name="estat",length = 2550)
    java.lang.String estat;

    @Column(name="norma1",length = 240)
    java.lang.String norma1;

    @Column(name="articles1",length = 255)
    java.lang.String articles1;

    @Column(name="fitxernorma1id",length = 19)
    java.lang.Long fitxerNorma1ID;

    @Column(name="norma2",length = 240)
    java.lang.String norma2;

    @Column(name="articles2",length = 60)
    java.lang.String articles2;

    @Column(name="fitxernorma2id",length = 19)
    java.lang.Long fitxerNorma2ID;

    @Column(name="norma3",length = 240)
    java.lang.String norma3;

    @Column(name="articles3",length = 60)
    java.lang.String articles3;

    @Column(name="fitxernorma3id",length = 19)
    java.lang.Long fitxerNorma3ID;



  /** Constructor Buit */
  public ModificacioSoliServJPA() {
  }

  /** Constructor amb tots els camps  */
  public ModificacioSoliServJPA(long modsoliservid , long soliServID , long modSoliID , java.lang.String estat , java.lang.String norma1 , java.lang.String articles1 , java.lang.Long fitxerNorma1ID , java.lang.String norma2 , java.lang.String articles2 , java.lang.Long fitxerNorma2ID , java.lang.String norma3 , java.lang.String articles3 , java.lang.Long fitxerNorma3ID) {
    this.modsoliservid=modsoliservid;
    this.soliServID=soliServID;
    this.modSoliID=modSoliID;
    this.estat=estat;
    this.norma1=norma1;
    this.articles1=articles1;
    this.fitxerNorma1ID=fitxerNorma1ID;
    this.norma2=norma2;
    this.articles2=articles2;
    this.fitxerNorma2ID=fitxerNorma2ID;
    this.norma3=norma3;
    this.articles3=articles3;
    this.fitxerNorma3ID=fitxerNorma3ID;
}
  /** Constructor sense valors autoincrementals */
  public ModificacioSoliServJPA(long soliServID , long modSoliID , java.lang.String estat , java.lang.String norma1 , java.lang.String articles1 , java.lang.Long fitxerNorma1ID , java.lang.String norma2 , java.lang.String articles2 , java.lang.Long fitxerNorma2ID , java.lang.String norma3 , java.lang.String articles3 , java.lang.Long fitxerNorma3ID) {
    this.soliServID=soliServID;
    this.modSoliID=modSoliID;
    this.estat=estat;
    this.norma1=norma1;
    this.articles1=articles1;
    this.fitxerNorma1ID=fitxerNorma1ID;
    this.norma2=norma2;
    this.articles2=articles2;
    this.fitxerNorma2ID=fitxerNorma2ID;
    this.norma3=norma3;
    this.articles3=articles3;
    this.fitxerNorma3ID=fitxerNorma3ID;
}
  /** Constructor dels valors Not Null */
  public ModificacioSoliServJPA(long modsoliservid , long soliServID , long modSoliID) {
    this.modsoliservid=modsoliservid;
    this.soliServID=soliServID;
    this.modSoliID=modSoliID;
}
  public ModificacioSoliServJPA(ModificacioSoliServ __bean) {
    this.setModsoliservid(__bean.getModsoliservid());
    this.setSoliServID(__bean.getSoliServID());
    this.setModSoliID(__bean.getModSoliID());
    this.setEstat(__bean.getEstat());
    this.setNorma1(__bean.getNorma1());
    this.setArticles1(__bean.getArticles1());
    this.setFitxerNorma1ID(__bean.getFitxerNorma1ID());
    this.setNorma2(__bean.getNorma2());
    this.setArticles2(__bean.getArticles2());
    this.setFitxerNorma2ID(__bean.getFitxerNorma2ID());
    this.setNorma3(__bean.getNorma3());
    this.setArticles3(__bean.getArticles3());
    this.setFitxerNorma3ID(__bean.getFitxerNorma3ID());
    // Fitxer
    this.setFitxerNorma1(FitxerJPA.toJPA(__bean.getFitxerNorma1()));
    // Fitxer
    this.setFitxerNorma2(FitxerJPA.toJPA(__bean.getFitxerNorma2()));
    // Fitxer
    this.setFitxerNorma3(FitxerJPA.toJPA(__bean.getFitxerNorma3()));
	}

	public long getModsoliservid() {
		return(modsoliservid);
	};
	public void setModsoliservid(long _modsoliservid_) {
		this.modsoliservid = _modsoliservid_;
	};

	public long getSoliServID() {
		return(soliServID);
	};
	public void setSoliServID(long _soliServID_) {
		this.soliServID = _soliServID_;
	};

	public long getModSoliID() {
		return(modSoliID);
	};
	public void setModSoliID(long _modSoliID_) {
		this.modSoliID = _modSoliID_;
	};

	public java.lang.String getEstat() {
		return(estat);
	};
	public void setEstat(java.lang.String _estat_) {
		this.estat = _estat_;
	};

	public java.lang.String getNorma1() {
		return(norma1);
	};
	public void setNorma1(java.lang.String _norma1_) {
		this.norma1 = _norma1_;
	};

	public java.lang.String getArticles1() {
		return(articles1);
	};
	public void setArticles1(java.lang.String _articles1_) {
		this.articles1 = _articles1_;
	};

	public java.lang.Long getFitxerNorma1ID() {
		return(fitxerNorma1ID);
	};
	public void setFitxerNorma1ID(java.lang.Long _fitxerNorma1ID_) {
		this.fitxerNorma1ID = _fitxerNorma1ID_;
	};

	public java.lang.String getNorma2() {
		return(norma2);
	};
	public void setNorma2(java.lang.String _norma2_) {
		this.norma2 = _norma2_;
	};

	public java.lang.String getArticles2() {
		return(articles2);
	};
	public void setArticles2(java.lang.String _articles2_) {
		this.articles2 = _articles2_;
	};

	public java.lang.Long getFitxerNorma2ID() {
		return(fitxerNorma2ID);
	};
	public void setFitxerNorma2ID(java.lang.Long _fitxerNorma2ID_) {
		this.fitxerNorma2ID = _fitxerNorma2ID_;
	};

	public java.lang.String getNorma3() {
		return(norma3);
	};
	public void setNorma3(java.lang.String _norma3_) {
		this.norma3 = _norma3_;
	};

	public java.lang.String getArticles3() {
		return(articles3);
	};
	public void setArticles3(java.lang.String _articles3_) {
		this.articles3 = _articles3_;
	};

	public java.lang.Long getFitxerNorma3ID() {
		return(fitxerNorma3ID);
	};
	public void setFitxerNorma3ID(java.lang.Long _fitxerNorma3ID_) {
		this.fitxerNorma3ID = _fitxerNorma3ID_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof ModificacioSoliServ) {
            ModificacioSoliServ __instance = (ModificacioSoliServ)__obj;
            __result = true;
            __result = __result && (this.getModsoliservid() == __instance.getModsoliservid()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// IMP Field:id | Table: pad_solicitudservei | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soliservid", referencedColumnName ="id", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_modsolser_soliservei_id_fk"))
    private SolicitudServeiJPA solicitudServei;

    public SolicitudServeiJPA getSolicitudServei() {
    return this.solicitudServei;
  }

    public  void setSolicitudServei(SolicitudServeiJPA solicitudServei) {
    this.solicitudServei = solicitudServei;
  }

// IMP Field:modsoliid | Table: pad_mod_solicitud | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modsoliid", referencedColumnName ="modsoliID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_modsolser_modsoli_modso_fk"))
    private ModificacioSolicitudJPA modificacioSolicitud;

    public ModificacioSolicitudJPA getModificacioSolicitud() {
    return this.modificacioSolicitud;
  }

    public  void setModificacioSolicitud(ModificacioSolicitudJPA modificacioSolicitud) {
    this.modificacioSolicitud = modificacioSolicitud;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxernorma1id", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_modsolser_fitxer_n1_fk"))
    private FitxerJPA fitxerNorma1;

    public FitxerJPA getFitxerNorma1() {
    return this.fitxerNorma1;
  }

    public  void setFitxerNorma1(FitxerJPA fitxerNorma1) {
    this.fitxerNorma1 = fitxerNorma1;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxernorma2id", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_modsolser_fitxer_n2_fk"))
    private FitxerJPA fitxerNorma2;

    public FitxerJPA getFitxerNorma2() {
    return this.fitxerNorma2;
  }

    public  void setFitxerNorma2(FitxerJPA fitxerNorma2) {
    this.fitxerNorma2 = fitxerNorma2;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxernorma3id", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_modsolser_fitxer_n3_fk"))
    private FitxerJPA fitxerNorma3;

    public FitxerJPA getFitxerNorma3() {
    return this.fitxerNorma3;
  }

    public  void setFitxerNorma3(FitxerJPA fitxerNorma3) {
    this.fitxerNorma3 = fitxerNorma3;
  }


 // ---------------  STATIC METHODS ------------------
  public static ModificacioSoliServJPA toJPA(ModificacioSoliServ __bean) {
    if (__bean == null) { return null;}
    ModificacioSoliServJPA __tmp = new ModificacioSoliServJPA();
    __tmp.setModsoliservid(__bean.getModsoliservid());
    __tmp.setSoliServID(__bean.getSoliServID());
    __tmp.setModSoliID(__bean.getModSoliID());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setNorma1(__bean.getNorma1());
    __tmp.setArticles1(__bean.getArticles1());
    __tmp.setFitxerNorma1ID(__bean.getFitxerNorma1ID());
    __tmp.setNorma2(__bean.getNorma2());
    __tmp.setArticles2(__bean.getArticles2());
    __tmp.setFitxerNorma2ID(__bean.getFitxerNorma2ID());
    __tmp.setNorma3(__bean.getNorma3());
    __tmp.setArticles3(__bean.getArticles3());
    __tmp.setFitxerNorma3ID(__bean.getFitxerNorma3ID());
    // Fitxer
    __tmp.setFitxerNorma1(FitxerJPA.toJPA(__bean.getFitxerNorma1()));
    // Fitxer
    __tmp.setFitxerNorma2(FitxerJPA.toJPA(__bean.getFitxerNorma2()));
    // Fitxer
    __tmp.setFitxerNorma3(FitxerJPA.toJPA(__bean.getFitxerNorma3()));
		return __tmp;
	}


  public static ModificacioSoliServJPA copyJPA(ModificacioSoliServJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<ModificacioSoliServJPA> copyJPA(java.util.Set<ModificacioSoliServJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<ModificacioSoliServJPA> __tmpSet = (java.util.Set<ModificacioSoliServJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<ModificacioSoliServJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (ModificacioSoliServJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static ModificacioSoliServJPA copyJPA(ModificacioSoliServJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    ModificacioSoliServJPA __tmp = (ModificacioSoliServJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"ModificacioSolicitudJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.modificacioSolicitud) || org.hibernate.Hibernate.isInitialized(__jpa.getModificacioSolicitud()) ) ) {
      __tmp.setModificacioSolicitud(ModificacioSolicitudJPA.copyJPA(__jpa.getModificacioSolicitud(), __alreadyCopied,"ModificacioSoliServJPA"));
    }
    if(!"SolicitudServeiJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitudServei) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitudServei()) ) ) {
      __tmp.setSolicitudServei(SolicitudServeiJPA.copyJPA(__jpa.getSolicitudServei(), __alreadyCopied,"ModificacioSoliServJPA"));
    }

    return __tmp;
  }




}
