
package org.fundaciobit.pinbaladmin.jpa;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.ForeignKey;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pad_tiquet" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class TiquetJPA implements Tiquet {



private static final long serialVersionUID = 1759255763L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINBALADMIN_SEQ")
	@Index(name="pad_tiquet_pk_i")
	@Column(name="tiquetid",nullable = false,length = 19)
	long tiquetID;

	@Column(name="titol",nullable = false,length = 256)
	java.lang.String titol;

	@Column(name="descripcio",nullable = false,length = 3000)
	java.lang.String descripcio;

	@Column(name="informador",nullable = false,length = 100)
	java.lang.String informador;

	@Column(name="dataalta",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataAlta;

	@Index(name="pad_tiquet_estattiquetid_fk_i")
	@Column(name="estattiquetid",nullable = false,length = 19)
	long estatTiquetID;

	@Index(name="pad_tiquet_tipustiquetid_fk_i")
	@Column(name="tipustiquetid",nullable = false,length = 19)
	long tipusTiquetID;

	@Column(name="versiopinbal",nullable = false,length = 100)
	java.lang.String versioPinbal;

	@Column(name="datainici",length = 29,precision = 6)
	java.sql.Timestamp dataInici;

	@Column(name="dataincidencia",length = 29,precision = 6)
	java.sql.Timestamp dataIncidencia;

	@Column(name="solucionatper",length = 100)
	java.lang.String solucionatPer;

	@Column(name="datafi",length = 29,precision = 6)
	java.sql.Timestamp datafi;

	@Column(name="notes",length = 1000)
	java.lang.String notes;

	@Column(name="entorn",nullable = false,length = 10)
	int entorn;

	@Index(name="pad_tiquet_adjunt1id_fk_i")
	@Column(name="adjunt1id",length = 19)
	java.lang.Long adjunt1ID;

	@Index(name="pad_tiquet_adjunt2id_fk_i")
	@Column(name="adjunt2id",length = 19)
	java.lang.Long adjunt2ID;



  /** Constructor Buit */
  public TiquetJPA() {
  }

  /** Constructor amb tots els camps  */
  public TiquetJPA(long tiquetID , java.lang.String titol , java.lang.String descripcio , java.lang.String informador , java.sql.Timestamp dataAlta , long estatTiquetID , long tipusTiquetID , java.lang.String versioPinbal , java.sql.Timestamp dataInici , java.sql.Timestamp dataIncidencia , java.lang.String solucionatPer , java.sql.Timestamp datafi , java.lang.String notes , int entorn , java.lang.Long adjunt1ID , java.lang.Long adjunt2ID) {
    this.tiquetID=tiquetID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.informador=informador;
    this.dataAlta=dataAlta;
    this.estatTiquetID=estatTiquetID;
    this.tipusTiquetID=tipusTiquetID;
    this.versioPinbal=versioPinbal;
    this.dataInici=dataInici;
    this.dataIncidencia=dataIncidencia;
    this.solucionatPer=solucionatPer;
    this.datafi=datafi;
    this.notes=notes;
    this.entorn=entorn;
    this.adjunt1ID=adjunt1ID;
    this.adjunt2ID=adjunt2ID;
}
  /** Constructor sense valors autoincrementals */
  public TiquetJPA(java.lang.String titol , java.lang.String descripcio , java.lang.String informador , java.sql.Timestamp dataAlta , long estatTiquetID , long tipusTiquetID , java.lang.String versioPinbal , java.sql.Timestamp dataInici , java.sql.Timestamp dataIncidencia , java.lang.String solucionatPer , java.sql.Timestamp datafi , java.lang.String notes , int entorn , java.lang.Long adjunt1ID , java.lang.Long adjunt2ID) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.informador=informador;
    this.dataAlta=dataAlta;
    this.estatTiquetID=estatTiquetID;
    this.tipusTiquetID=tipusTiquetID;
    this.versioPinbal=versioPinbal;
    this.dataInici=dataInici;
    this.dataIncidencia=dataIncidencia;
    this.solucionatPer=solucionatPer;
    this.datafi=datafi;
    this.notes=notes;
    this.entorn=entorn;
    this.adjunt1ID=adjunt1ID;
    this.adjunt2ID=adjunt2ID;
}
  /** Constructor dels valors Not Null */
  public TiquetJPA(long tiquetID , java.lang.String titol , java.lang.String descripcio , java.lang.String informador , java.sql.Timestamp dataAlta , long estatTiquetID , long tipusTiquetID , java.lang.String versioPinbal , int entorn) {
    this.tiquetID=tiquetID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.informador=informador;
    this.dataAlta=dataAlta;
    this.estatTiquetID=estatTiquetID;
    this.tipusTiquetID=tipusTiquetID;
    this.versioPinbal=versioPinbal;
    this.entorn=entorn;
}
  public TiquetJPA(Tiquet __bean) {
    this.setTiquetID(__bean.getTiquetID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setInformador(__bean.getInformador());
    this.setDataAlta(__bean.getDataAlta());
    this.setEstatTiquetID(__bean.getEstatTiquetID());
    this.setTipusTiquetID(__bean.getTipusTiquetID());
    this.setVersioPinbal(__bean.getVersioPinbal());
    this.setDataInici(__bean.getDataInici());
    this.setDataIncidencia(__bean.getDataIncidencia());
    this.setSolucionatPer(__bean.getSolucionatPer());
    this.setDatafi(__bean.getDatafi());
    this.setNotes(__bean.getNotes());
    this.setEntorn(__bean.getEntorn());
    this.setAdjunt1ID(__bean.getAdjunt1ID());
    this.setAdjunt2ID(__bean.getAdjunt2ID());
    // Fitxer
    this.setAdjunt1(FitxerJPA.toJPA(__bean.getAdjunt1()));
    // Fitxer
    this.setAdjunt2(FitxerJPA.toJPA(__bean.getAdjunt2()));
	}

	public long getTiquetID() {
		return(tiquetID);
	};
	public void setTiquetID(long _tiquetID_) {
		this.tiquetID = _tiquetID_;
	};

	public java.lang.String getTitol() {
		return(titol);
	};
	public void setTitol(java.lang.String _titol_) {
		this.titol = _titol_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getInformador() {
		return(informador);
	};
	public void setInformador(java.lang.String _informador_) {
		this.informador = _informador_;
	};

	public java.sql.Timestamp getDataAlta() {
		return(dataAlta);
	};
	public void setDataAlta(java.sql.Timestamp _dataAlta_) {
		this.dataAlta = _dataAlta_;
	};

	public long getEstatTiquetID() {
		return(estatTiquetID);
	};
	public void setEstatTiquetID(long _estatTiquetID_) {
		this.estatTiquetID = _estatTiquetID_;
	};

	public long getTipusTiquetID() {
		return(tipusTiquetID);
	};
	public void setTipusTiquetID(long _tipusTiquetID_) {
		this.tipusTiquetID = _tipusTiquetID_;
	};

	public java.lang.String getVersioPinbal() {
		return(versioPinbal);
	};
	public void setVersioPinbal(java.lang.String _versioPinbal_) {
		this.versioPinbal = _versioPinbal_;
	};

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public java.sql.Timestamp getDataIncidencia() {
		return(dataIncidencia);
	};
	public void setDataIncidencia(java.sql.Timestamp _dataIncidencia_) {
		this.dataIncidencia = _dataIncidencia_;
	};

	public java.lang.String getSolucionatPer() {
		return(solucionatPer);
	};
	public void setSolucionatPer(java.lang.String _solucionatPer_) {
		this.solucionatPer = _solucionatPer_;
	};

	public java.sql.Timestamp getDatafi() {
		return(datafi);
	};
	public void setDatafi(java.sql.Timestamp _datafi_) {
		this.datafi = _datafi_;
	};

	public java.lang.String getNotes() {
		return(notes);
	};
	public void setNotes(java.lang.String _notes_) {
		this.notes = _notes_;
	};

	public int getEntorn() {
		return(entorn);
	};
	public void setEntorn(int _entorn_) {
		this.entorn = _entorn_;
	};

	public java.lang.Long getAdjunt1ID() {
		return(adjunt1ID);
	};
	public void setAdjunt1ID(java.lang.Long _adjunt1ID_) {
		this.adjunt1ID = _adjunt1ID_;
	};

	public java.lang.Long getAdjunt2ID() {
		return(adjunt2ID);
	};
	public void setAdjunt2ID(java.lang.Long _adjunt2ID_) {
		this.adjunt2ID = _adjunt2ID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Tiquet) {
      Tiquet __instance = (Tiquet)__obj;
      __result = true;
      __result = __result && (this.getTiquetID() == __instance.getTiquetID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:estattiquetid | Table: pad_estattiquet | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_tiquet_estattique_estat_fk")
	@JoinColumn(name = "estattiquetid", referencedColumnName ="estatTiquetID", nullable = false, insertable=false, updatable=false)
	private EstatTiquetJPA estatTiquet;

	public EstatTiquetJPA getEstatTiquet() {
    return this.estatTiquet;
  }

	public  void setEstatTiquet(EstatTiquetJPA estatTiquet) {
    this.estatTiquet = estatTiquet;
  }

// IMP Field:tipustiquetid | Table: pad_tipustiquet | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_tiquet_tiptiquet_fk")
	@JoinColumn(name = "tipustiquetid", referencedColumnName ="tipusTiquetID", nullable = false, insertable=false, updatable=false)
	private TipusTiquetJPA tipusTiquet;

	public TipusTiquetJPA getTipusTiquet() {
    return this.tipusTiquet;
  }

	public  void setTipusTiquet(TipusTiquetJPA tipusTiquet) {
    this.tipusTiquet = tipusTiquet;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pad_tiquet_fitxer_adj1_fk")
	@JoinColumn(name = "adjunt1id", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA adjunt1;

	public FitxerJPA getAdjunt1() {
    return this.adjunt1;
  }

	public  void setAdjunt1(FitxerJPA adjunt1) {
    this.adjunt1 = adjunt1;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pad_tiquet_fitxer_adj2_fk")
	@JoinColumn(name = "adjunt2id", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA adjunt2;

	public FitxerJPA getAdjunt2() {
    return this.adjunt2;
  }

	public  void setAdjunt2(FitxerJPA adjunt2) {
    this.adjunt2 = adjunt2;
  }


 // ---------------  STATIC METHODS ------------------
  public static TiquetJPA toJPA(Tiquet __bean) {
    if (__bean == null) { return null;}
    TiquetJPA __tmp = new TiquetJPA();
    __tmp.setTiquetID(__bean.getTiquetID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setInformador(__bean.getInformador());
    __tmp.setDataAlta(__bean.getDataAlta());
    __tmp.setEstatTiquetID(__bean.getEstatTiquetID());
    __tmp.setTipusTiquetID(__bean.getTipusTiquetID());
    __tmp.setVersioPinbal(__bean.getVersioPinbal());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setDataIncidencia(__bean.getDataIncidencia());
    __tmp.setSolucionatPer(__bean.getSolucionatPer());
    __tmp.setDatafi(__bean.getDatafi());
    __tmp.setNotes(__bean.getNotes());
    __tmp.setEntorn(__bean.getEntorn());
    __tmp.setAdjunt1ID(__bean.getAdjunt1ID());
    __tmp.setAdjunt2ID(__bean.getAdjunt2ID());
    // Fitxer
    __tmp.setAdjunt1(FitxerJPA.toJPA(__bean.getAdjunt1()));
    // Fitxer
    __tmp.setAdjunt2(FitxerJPA.toJPA(__bean.getAdjunt2()));
		return __tmp;
	}


  public static TiquetJPA copyJPA(TiquetJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TiquetJPA> copyJPA(java.util.Set<TiquetJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TiquetJPA> __tmpSet = (java.util.Set<TiquetJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TiquetJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TiquetJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TiquetJPA copyJPA(TiquetJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TiquetJPA __tmp = (TiquetJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TipusTiquetJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tipusTiquet) || org.hibernate.Hibernate.isInitialized(__jpa.getTipusTiquet()) ) ) {
      __tmp.setTipusTiquet(TipusTiquetJPA.copyJPA(__jpa.getTipusTiquet(), __alreadyCopied,"TiquetJPA"));
    }
    if(!"EstatTiquetJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.estatTiquet) || org.hibernate.Hibernate.isInitialized(__jpa.getEstatTiquet()) ) ) {
      __tmp.setEstatTiquet(EstatTiquetJPA.copyJPA(__jpa.getEstatTiquet(), __alreadyCopied,"TiquetJPA"));
    }

    return __tmp;
  }




}
