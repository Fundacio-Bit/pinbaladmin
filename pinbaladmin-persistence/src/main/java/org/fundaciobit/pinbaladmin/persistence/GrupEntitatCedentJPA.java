
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "GrupEntitatCedentJPA")
@Table(name = "pad_grupentitatcedent" , indexes = { 
        @Index(name="pad_grupentitatcedent_pk_i", columnList = "grupentitatcedentid"),
        @Index(name="pad_grupentced_grpentid_fk_i", columnList = "grupentitatid"),
        @Index(name="pad_grupentced_cedentid_fk_i", columnList = "cedentid")},
           uniqueConstraints = {
            @UniqueConstraint(name="pad_grupentced_grp_ced_uk", columnNames={"grupentitatid","cedentid"}) } )
@SequenceGenerator(name="GRUPENTITATCEDENT_SEQ", sequenceName="pad_grupentitatcedent_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class GrupEntitatCedentJPA implements GrupEntitatCedent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GRUPENTITATCEDENT_SEQ")
    @Column(name="grupentitatcedentid",nullable = false,length = 19)
    long grupEntitatCedentID;

    @Column(name="grupentitatid",nullable = false,length = 19)
    long grupEntitatID;

    @Column(name="cedentid",nullable = false,length = 19)
    long cedentID;



  /** Constructor Buit */
  public GrupEntitatCedentJPA() {
  }

  /** Constructor amb tots els camps  */
  public GrupEntitatCedentJPA(long grupEntitatCedentID , long grupEntitatID , long cedentID) {
    this.grupEntitatCedentID=grupEntitatCedentID;
    this.grupEntitatID=grupEntitatID;
    this.cedentID=cedentID;
}
  /** Constructor sense valors autoincrementals */
  public GrupEntitatCedentJPA(long grupEntitatID , long cedentID) {
    this.grupEntitatID=grupEntitatID;
    this.cedentID=cedentID;
}
  public GrupEntitatCedentJPA(GrupEntitatCedent __bean) {
    this.setGrupEntitatCedentID(__bean.getGrupEntitatCedentID());
    this.setGrupEntitatID(__bean.getGrupEntitatID());
    this.setCedentID(__bean.getCedentID());
	}

	public long getGrupEntitatCedentID() {
		return(grupEntitatCedentID);
	};
	public void setGrupEntitatCedentID(long _grupEntitatCedentID_) {
		this.grupEntitatCedentID = _grupEntitatCedentID_;
	};

	public long getGrupEntitatID() {
		return(grupEntitatID);
	};
	public void setGrupEntitatID(long _grupEntitatID_) {
		this.grupEntitatID = _grupEntitatID_;
	};

	public long getCedentID() {
		return(cedentID);
	};
	public void setCedentID(long _cedentID_) {
		this.cedentID = _cedentID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof GrupEntitatCedent) {
      GrupEntitatCedent __instance = (GrupEntitatCedent)__obj;
      __result = true;
      __result = __result && (this.getGrupEntitatCedentID() == __instance.getGrupEntitatCedentID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:grupentitatid | Table: pad_grupentitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupentitatid", referencedColumnName ="grupEntitatID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_grupentced_grupent_fk"))
    private GrupEntitatJPA grupEntitat;

    public GrupEntitatJPA getGrupEntitat() {
    return this.grupEntitat;
  }

    public  void setGrupEntitat(GrupEntitatJPA grupEntitat) {
    this.grupEntitat = grupEntitat;
  }

// IMP Field:entitatserveiid | Table: pad_entitatservei | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cedentid", referencedColumnName ="entitatServeiID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_grupentced_entiservei_fk"))
    private EntitatServeiJPA entitatServei;

    public EntitatServeiJPA getEntitatServei() {
    return this.entitatServei;
  }

    public  void setEntitatServei(EntitatServeiJPA entitatServei) {
    this.entitatServei = entitatServei;
  }


 // ---------------  STATIC METHODS ------------------
  public static GrupEntitatCedentJPA toJPA(GrupEntitatCedent __bean) {
    if (__bean == null) { return null;}
    GrupEntitatCedentJPA __tmp = new GrupEntitatCedentJPA();
    __tmp.setGrupEntitatCedentID(__bean.getGrupEntitatCedentID());
    __tmp.setGrupEntitatID(__bean.getGrupEntitatID());
    __tmp.setCedentID(__bean.getCedentID());
		return __tmp;
	}


  public static GrupEntitatCedentJPA copyJPA(GrupEntitatCedentJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<GrupEntitatCedentJPA> copyJPA(java.util.Set<GrupEntitatCedentJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<GrupEntitatCedentJPA> __tmpSet = (java.util.Set<GrupEntitatCedentJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<GrupEntitatCedentJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (GrupEntitatCedentJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static GrupEntitatCedentJPA copyJPA(GrupEntitatCedentJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    GrupEntitatCedentJPA __tmp = (GrupEntitatCedentJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"GrupEntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupEntitat) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupEntitat()) ) ) {
      __tmp.setGrupEntitat(GrupEntitatJPA.copyJPA(__jpa.getGrupEntitat(), __alreadyCopied,"GrupEntitatCedentJPA"));
    }
    if(!"EntitatServeiJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitatServei) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitatServei()) ) ) {
      __tmp.setEntitatServei(EntitatServeiJPA.copyJPA(__jpa.getEntitatServei(), __alreadyCopied,"GrupEntitatCedentJPA"));
    }

    return __tmp;
  }




}