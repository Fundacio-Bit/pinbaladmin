
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "EstatTiquetJPA")
@Table(name = "pad_estattiquet" , indexes = { 
        @Index(name="pad_estattiquet_pk_i", columnList = "estattiquetid")})
@SequenceGenerator(name="ESTATTIQUET_SEQ", sequenceName="pad_estattiquet_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EstatTiquetJPA implements EstatTiquet {

    @Id
    @Column(name="estattiquetid",nullable = false,length = 19)
    long estatTiquetID;

    @Column(name="nom",nullable = false,length = 100)
    java.lang.String nom;



  /** Constructor Buit */
  public EstatTiquetJPA() {
  }

  /** Constructor amb tots els camps  */
  public EstatTiquetJPA(long estatTiquetID , java.lang.String nom) {
    this.estatTiquetID=estatTiquetID;
    this.nom=nom;
}
  public EstatTiquetJPA(EstatTiquet __bean) {
    this.setEstatTiquetID(__bean.getEstatTiquetID());
    this.setNom(__bean.getNom());
	}

	public long getEstatTiquetID() {
		return(estatTiquetID);
	};
	public void setEstatTiquetID(long _estatTiquetID_) {
		this.estatTiquetID = _estatTiquetID_;
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
    if (__obj != null && __obj instanceof EstatTiquet) {
      EstatTiquet __instance = (EstatTiquet)__obj;
      __result = true;
      __result = __result && (this.getEstatTiquetID() == __instance.getEstatTiquetID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:estattiquetid | Table: pad_tiquet | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estatTiquet")
    private Set<TiquetJPA> tiquets = new HashSet<TiquetJPA>(0);
    public  Set<TiquetJPA> getTiquets() {
    return this.tiquets;
  }

    public void setTiquets(Set<TiquetJPA> tiquets) {
      this.tiquets = tiquets;
    }



 // ---------------  STATIC METHODS ------------------
  public static EstatTiquetJPA toJPA(EstatTiquet __bean) {
    if (__bean == null) { return null;}
    EstatTiquetJPA __tmp = new EstatTiquetJPA();
    __tmp.setEstatTiquetID(__bean.getEstatTiquetID());
    __tmp.setNom(__bean.getNom());
		return __tmp;
	}


  public static EstatTiquetJPA copyJPA(EstatTiquetJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EstatTiquetJPA> copyJPA(java.util.Set<EstatTiquetJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EstatTiquetJPA> __tmpSet = (java.util.Set<EstatTiquetJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EstatTiquetJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EstatTiquetJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EstatTiquetJPA copyJPA(EstatTiquetJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EstatTiquetJPA __tmp = (EstatTiquetJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"TiquetJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.tiquets) || org.hibernate.Hibernate.isInitialized(__jpa.getTiquets())) ) {
      __tmp.setTiquets(TiquetJPA.copyJPA(__jpa.getTiquets(), __alreadyCopied,"EstatTiquetJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
