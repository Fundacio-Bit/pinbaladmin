
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


@Entity(name = "TramitBDadesSoliJPA")
@Table(name = "pad_tramit_b_dades_soli" , indexes = { 
        @Index(name="pad_tramit_b_dades_soli_pk_i", columnList = "dadessoliid"),
        @Index(name="pad_tramitb_tramitid_fk_i", columnList = "tramitid")})
@SequenceGenerator(name="TRAMITBDADESSOLI_SEQ", sequenceName="pad_tramit_b_dades_soli_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TramitBDadesSoliJPA implements TramitBDadesSoli {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRAMITBDADESSOLI_SEQ")
    @Column(name="dadessoliid",nullable = false,length = 19)
    long dadessoliid;

    @Column(name="tramitid",nullable = false,length = 19)
    long tramitid;

    @Column(name="tipussolicitud",nullable = false,length = 19)
    long tipussolicitud;

    @org.hibernate.annotations.ColumnDefault("false")
    @Column(name="entorn",nullable = false,length = 20)
    java.lang.String entorn = "false";



  /** Constructor Buit */
  public TramitBDadesSoliJPA() {
  }

  /** Constructor amb tots els camps  */
  public TramitBDadesSoliJPA(long dadessoliid , long tramitid , long tipussolicitud , java.lang.String entorn) {
    this.dadessoliid=dadessoliid;
    this.tramitid=tramitid;
    this.tipussolicitud=tipussolicitud;
    this.entorn=entorn;
}
  /** Constructor sense valors autoincrementals */
  public TramitBDadesSoliJPA(long tramitid , long tipussolicitud , java.lang.String entorn) {
    this.tramitid=tramitid;
    this.tipussolicitud=tipussolicitud;
    this.entorn=entorn;
}
  public TramitBDadesSoliJPA(TramitBDadesSoli __bean) {
    this.setDadessoliid(__bean.getDadessoliid());
    this.setTramitid(__bean.getTramitid());
    this.setTipussolicitud(__bean.getTipussolicitud());
    this.setEntorn(__bean.getEntorn());
	}

	public long getDadessoliid() {
		return(dadessoliid);
	};
	public void setDadessoliid(long _dadessoliid_) {
		this.dadessoliid = _dadessoliid_;
	};

	public long getTramitid() {
		return(tramitid);
	};
	public void setTramitid(long _tramitid_) {
		this.tramitid = _tramitid_;
	};

	public long getTipussolicitud() {
		return(tipussolicitud);
	};
	public void setTipussolicitud(long _tipussolicitud_) {
		this.tipussolicitud = _tipussolicitud_;
	};

	public java.lang.String getEntorn() {
		return(entorn);
	};
	public void setEntorn(java.lang.String _entorn_) {
		this.entorn = _entorn_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TramitBDadesSoli) {
      TramitBDadesSoli __instance = (TramitBDadesSoli)__obj;
      __result = true;
      __result = __result && (this.getDadessoliid() == __instance.getDadessoliid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:tramitid | Table: pad_tramit_a_pers_aut | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tramitid", referencedColumnName ="tramitid", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_tramitb_tramita_fk"))
    private TramitAPersAutJPA tramitAPersAut;

    public TramitAPersAutJPA getTramitAPersAut() {
    return this.tramitAPersAut;
  }

    public  void setTramitAPersAut(TramitAPersAutJPA tramitAPersAut) {
    this.tramitAPersAut = tramitAPersAut;
  }


 // ---------------  STATIC METHODS ------------------
  public static TramitBDadesSoliJPA toJPA(TramitBDadesSoli __bean) {
    if (__bean == null) { return null;}
    TramitBDadesSoliJPA __tmp = new TramitBDadesSoliJPA();
    __tmp.setDadessoliid(__bean.getDadessoliid());
    __tmp.setTramitid(__bean.getTramitid());
    __tmp.setTipussolicitud(__bean.getTipussolicitud());
    __tmp.setEntorn(__bean.getEntorn());
		return __tmp;
	}


  public static TramitBDadesSoliJPA copyJPA(TramitBDadesSoliJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TramitBDadesSoliJPA> copyJPA(java.util.Set<TramitBDadesSoliJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TramitBDadesSoliJPA> __tmpSet = (java.util.Set<TramitBDadesSoliJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TramitBDadesSoliJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TramitBDadesSoliJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TramitBDadesSoliJPA copyJPA(TramitBDadesSoliJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TramitBDadesSoliJPA __tmp = (TramitBDadesSoliJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"TramitAPersAutJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tramitAPersAut) || org.hibernate.Hibernate.isInitialized(__jpa.getTramitAPersAut()) ) ) {
      __tmp.setTramitAPersAut(TramitAPersAutJPA.copyJPA(__jpa.getTramitAPersAut(), __alreadyCopied,"TramitBDadesSoliJPA"));
    }

    return __tmp;
  }




}
