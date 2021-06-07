
package org.fundaciobit.pinbaladmin.jpa;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import java.util.Set;
import org.hibernate.annotations.Index;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;


@Entity
@Table(name = "pad_incidenciatecnica" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class IncidenciaTecnicaJPA implements IncidenciaTecnica {



private static final long serialVersionUID = 979554313L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINBALADMIN_SEQ")
	@Index(name="pad_incidenciatecnica_pk_i")
	@Column(name="incidenciatecnicaid",nullable = false,length = 19)
	long incidenciaTecnicaID;

	@Column(name="titol",nullable = false,length = 255)
	java.lang.String titol;

	@Column(name="descripcio",nullable = false,length = 4000)
	java.lang.String descripcio;

	@Column(name="datainici",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataInici;

	@Column(name="estat",nullable = false,length = 10)
	int estat;

	@Column(name="nomentitat",length = 255)
	java.lang.String nomEntitat;

	@Column(name="contactenom",nullable = false,length = 255)
	java.lang.String contacteNom;

	@Column(name="contacteemail",nullable = false,length = 100)
	java.lang.String contacteEmail;

	@Column(name="contactetelefon",length = 100)
	java.lang.String contacteTelefon;



  /** Constructor Buit */
  public IncidenciaTecnicaJPA() {
  }

  /** Constructor amb tots els camps  */
  public IncidenciaTecnicaJPA(long incidenciaTecnicaID , java.lang.String titol , java.lang.String descripcio , java.sql.Timestamp dataInici , int estat , java.lang.String nomEntitat , java.lang.String contacteNom , java.lang.String contacteEmail , java.lang.String contacteTelefon) {
    this.incidenciaTecnicaID=incidenciaTecnicaID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.estat=estat;
    this.nomEntitat=nomEntitat;
    this.contacteNom=contacteNom;
    this.contacteEmail=contacteEmail;
    this.contacteTelefon=contacteTelefon;
}
  /** Constructor sense valors autoincrementals */
  public IncidenciaTecnicaJPA(java.lang.String titol , java.lang.String descripcio , java.sql.Timestamp dataInici , int estat , java.lang.String nomEntitat , java.lang.String contacteNom , java.lang.String contacteEmail , java.lang.String contacteTelefon) {
    this.titol=titol;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.estat=estat;
    this.nomEntitat=nomEntitat;
    this.contacteNom=contacteNom;
    this.contacteEmail=contacteEmail;
    this.contacteTelefon=contacteTelefon;
}
  /** Constructor dels valors Not Null */
  public IncidenciaTecnicaJPA(long incidenciaTecnicaID , java.lang.String titol , java.lang.String descripcio , java.sql.Timestamp dataInici , int estat , java.lang.String contacteNom , java.lang.String contacteEmail) {
    this.incidenciaTecnicaID=incidenciaTecnicaID;
    this.titol=titol;
    this.descripcio=descripcio;
    this.dataInici=dataInici;
    this.estat=estat;
    this.contacteNom=contacteNom;
    this.contacteEmail=contacteEmail;
}
  public IncidenciaTecnicaJPA(IncidenciaTecnica __bean) {
    this.setIncidenciaTecnicaID(__bean.getIncidenciaTecnicaID());
    this.setTitol(__bean.getTitol());
    this.setDescripcio(__bean.getDescripcio());
    this.setDataInici(__bean.getDataInici());
    this.setEstat(__bean.getEstat());
    this.setNomEntitat(__bean.getNomEntitat());
    this.setContacteNom(__bean.getContacteNom());
    this.setContacteEmail(__bean.getContacteEmail());
    this.setContacteTelefon(__bean.getContacteTelefon());
	}

	public long getIncidenciaTecnicaID() {
		return(incidenciaTecnicaID);
	};
	public void setIncidenciaTecnicaID(long _incidenciaTecnicaID_) {
		this.incidenciaTecnicaID = _incidenciaTecnicaID_;
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

	public java.sql.Timestamp getDataInici() {
		return(dataInici);
	};
	public void setDataInici(java.sql.Timestamp _dataInici_) {
		this.dataInici = _dataInici_;
	};

	public int getEstat() {
		return(estat);
	};
	public void setEstat(int _estat_) {
		this.estat = _estat_;
	};

	public java.lang.String getNomEntitat() {
		return(nomEntitat);
	};
	public void setNomEntitat(java.lang.String _nomEntitat_) {
		this.nomEntitat = _nomEntitat_;
	};

	public java.lang.String getContacteNom() {
		return(contacteNom);
	};
	public void setContacteNom(java.lang.String _contacteNom_) {
		this.contacteNom = _contacteNom_;
	};

	public java.lang.String getContacteEmail() {
		return(contacteEmail);
	};
	public void setContacteEmail(java.lang.String _contacteEmail_) {
		this.contacteEmail = _contacteEmail_;
	};

	public java.lang.String getContacteTelefon() {
		return(contacteTelefon);
	};
	public void setContacteTelefon(java.lang.String _contacteTelefon_) {
		this.contacteTelefon = _contacteTelefon_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof IncidenciaTecnica) {
      IncidenciaTecnica __instance = (IncidenciaTecnica)__obj;
      __result = true;
      __result = __result && (this.getIncidenciaTecnicaID() == __instance.getIncidenciaTecnicaID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:incidenciatecnicaid | Table: pad_event | Type: 0  

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "incidenciaTecnica")
	private Set<EventJPA> events = new HashSet<EventJPA>(0);
	public  Set<EventJPA> getEvents() {
    return this.events;
  }

	public void setEvents(Set<EventJPA> events) {
	  this.events = events;
	}



 // ---------------  STATIC METHODS ------------------
  public static IncidenciaTecnicaJPA toJPA(IncidenciaTecnica __bean) {
    if (__bean == null) { return null;}
    IncidenciaTecnicaJPA __tmp = new IncidenciaTecnicaJPA();
    __tmp.setIncidenciaTecnicaID(__bean.getIncidenciaTecnicaID());
    __tmp.setTitol(__bean.getTitol());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setDataInici(__bean.getDataInici());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setNomEntitat(__bean.getNomEntitat());
    __tmp.setContacteNom(__bean.getContacteNom());
    __tmp.setContacteEmail(__bean.getContacteEmail());
    __tmp.setContacteTelefon(__bean.getContacteTelefon());
		return __tmp;
	}


  public static IncidenciaTecnicaJPA copyJPA(IncidenciaTecnicaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<IncidenciaTecnicaJPA> copyJPA(java.util.Set<IncidenciaTecnicaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<IncidenciaTecnicaJPA> __tmpSet = (java.util.Set<IncidenciaTecnicaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<IncidenciaTecnicaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (IncidenciaTecnicaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static IncidenciaTecnicaJPA copyJPA(IncidenciaTecnicaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    IncidenciaTecnicaJPA __tmp = (IncidenciaTecnicaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"EventJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.events) || org.hibernate.Hibernate.isInitialized(__jpa.getEvents())) ) {
      __tmp.setEvents(EventJPA.copyJPA(__jpa.getEvents(), __alreadyCopied,"IncidenciaTecnicaJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
