
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "ContacteJPA")
@Table(name = "pad_contacte" , indexes = { 
        @Index(name="pad_contacte_pk_i", columnList = "contacteid")})
@SequenceGenerator(name="CONTACTE_SEQ", sequenceName="pad_contacte_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class ContacteJPA implements Contacte {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CONTACTE_SEQ")
    @Column(name="contacteid",nullable = false,length = 19)
    long ContacteID;

    @Column(name="nif",nullable = false,length = 30)
    java.lang.String nif;

    @Column(name="nom",nullable = false,length = 60)
    java.lang.String nom;

    @Column(name="llinatge1",length = 60)
    java.lang.String llinatge1;

    @Column(name="llinatge2",length = 60)
    java.lang.String llinatge2;

    @Column(name="carrec",length = 120)
    java.lang.String carrec;

    @Column(name="telefon",length = 12)
    java.lang.String telefon;

    @Column(name="mail",nullable = false,length = 120)
    java.lang.String mail;



  /** Constructor Buit */
  public ContacteJPA() {
  }

  /** Constructor amb tots els camps  */
  public ContacteJPA(long ContacteID , java.lang.String nif , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String carrec , java.lang.String telefon , java.lang.String mail) {
    this.ContacteID=ContacteID;
    this.nif=nif;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.carrec=carrec;
    this.telefon=telefon;
    this.mail=mail;
}
  /** Constructor sense valors autoincrementals */
  public ContacteJPA(java.lang.String nif , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String carrec , java.lang.String telefon , java.lang.String mail) {
    this.nif=nif;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.carrec=carrec;
    this.telefon=telefon;
    this.mail=mail;
}
  /** Constructor dels valors Not Null */
  public ContacteJPA(long ContacteID , java.lang.String nif , java.lang.String nom , java.lang.String mail) {
    this.ContacteID=ContacteID;
    this.nif=nif;
    this.nom=nom;
    this.mail=mail;
}
  public ContacteJPA(Contacte __bean) {
    this.setContacteID(__bean.getContacteID());
    this.setNif(__bean.getNif());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setCarrec(__bean.getCarrec());
    this.setTelefon(__bean.getTelefon());
    this.setMail(__bean.getMail());
	}

	public long getContacteID() {
		return(ContacteID);
	};
	public void setContacteID(long _ContacteID_) {
		this.ContacteID = _ContacteID_;
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
        if (__obj != null && __obj instanceof Contacte) {
            Contacte __instance = (Contacte)__obj;
            __result = true;
            __result = __result && (this.getContacteID() == __instance.getContacteID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// EXP  Field:contactetitularid | Table: pad_solicitud | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contacte")
    private Set<SolicitudJPA> solicituds = new HashSet<SolicitudJPA>(0);
    public  Set<SolicitudJPA> getSolicituds() {
    return this.solicituds;
  }

    public void setSolicituds(Set<SolicitudJPA> solicituds) {
      this.solicituds = solicituds;
    }



 // ---------------  STATIC METHODS ------------------
  public static ContacteJPA toJPA(Contacte __bean) {
    if (__bean == null) { return null;}
    ContacteJPA __tmp = new ContacteJPA();
    __tmp.setContacteID(__bean.getContacteID());
    __tmp.setNif(__bean.getNif());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setCarrec(__bean.getCarrec());
    __tmp.setTelefon(__bean.getTelefon());
    __tmp.setMail(__bean.getMail());
		return __tmp;
	}


  public static ContacteJPA copyJPA(ContacteJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<ContacteJPA> copyJPA(java.util.Set<ContacteJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<ContacteJPA> __tmpSet = (java.util.Set<ContacteJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<ContacteJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (ContacteJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static ContacteJPA copyJPA(ContacteJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    ContacteJPA __tmp = (ContacteJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"SolicitudJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicituds) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicituds())) ) {
      __tmp.setSolicituds(SolicitudJPA.copyJPA(__jpa.getSolicituds(), __alreadyCopied,"ContacteJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
