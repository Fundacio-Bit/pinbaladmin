
package org.fundaciobit.pinbaladmin.jpa;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;


@Entity
@Table(name = "pad_tipustiquet" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class TipusTiquetJPA implements TipusTiquet {



private static final long serialVersionUID = -2137273190L;

	@Id
	@Index(name="pad_tipustiquet_pk_i")
	@Column(name="tipustiquetid",nullable = false,length = 19)
	long tipusTiquetID;

	@Column(name="nom",nullable = false,length = 100)
	java.lang.String nom;



  /** Constructor Buit */
  public TipusTiquetJPA() {
  }

  /** Constructor amb tots els camps  */
  public TipusTiquetJPA(long tipusTiquetID , java.lang.String nom) {
    this.tipusTiquetID=tipusTiquetID;
    this.nom=nom;
}
  public TipusTiquetJPA(TipusTiquet __bean) {
    this.setTipusTiquetID(__bean.getTipusTiquetID());
    this.setNom(__bean.getNom());
	}

	public long getTipusTiquetID() {
		return(tipusTiquetID);
	};
	public void setTipusTiquetID(long _tipusTiquetID_) {
		this.tipusTiquetID = _tipusTiquetID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof TipusTiquet) {
      TipusTiquet __instance = (TipusTiquet)__obj;
      __result = true;
      __result = __result && (this.getTipusTiquetID() == __instance.getTipusTiquetID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:tipustiquetid | Table: pad_tiquet | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipusTiquet")
	private Set<TiquetJPA> tiquets = new HashSet<TiquetJPA>(0);
	public  Set<TiquetJPA> getTiquets() {
    return this.tiquets;
  }

	public void setTiquets(Set<TiquetJPA> tiquets) {
	  this.tiquets = tiquets;
	}



 // ---------------  STATIC METHODS ------------------
  public static TipusTiquetJPA toJPA(TipusTiquet __bean) {
    if (__bean == null) { return null;}
    TipusTiquetJPA __tmp = new TipusTiquetJPA();
    __tmp.setTipusTiquetID(__bean.getTipusTiquetID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}


  public static TipusTiquetJPA copyJPA(TipusTiquetJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TipusTiquetJPA> copyJPA(java.util.Set<TipusTiquetJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TipusTiquetJPA> __tmpSet = (java.util.Set<TipusTiquetJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TipusTiquetJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TipusTiquetJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TipusTiquetJPA copyJPA(TipusTiquetJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TipusTiquetJPA __tmp = (TipusTiquetJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"TiquetJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tiquets) || org.hibernate.Hibernate.isInitialized(__jpa.getTiquets())) ) {
      __tmp.setTiquets(TiquetJPA.copyJPA(__jpa.getTiquets(), __alreadyCopied,"TipusTiquetJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
