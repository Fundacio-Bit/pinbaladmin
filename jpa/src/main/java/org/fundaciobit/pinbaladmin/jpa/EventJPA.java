
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
@Table(name = "pad_event" )
@SequenceGenerator(name="PINBALADMIN_SEQ", sequenceName="pad_pinbaladmin_seq", allocationSize=1)
@javax.xml.bind.annotation.XmlRootElement
public class EventJPA implements Event {



private static final long serialVersionUID = 342741761L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PINBALADMIN_SEQ")
	@Index(name="pad_event_pk_i")
	@Column(name="eventid",nullable = false,length = 19)
	long eventID;

	@Index(name="pad_event_solicitudid_fk_i")
	@Column(name="solicitudid",length = 19)
	java.lang.Long solicitudID;

	@Index(name="pad_event_inctecnicaid_fk_i")
	@Column(name="incidenciatecnicaid",length = 19)
	java.lang.Long incidenciaTecnicaID;

	@Column(name="dataevent",nullable = false,length = 29,precision = 6)
	java.sql.Timestamp dataEvent;

	@Column(name="tipus",nullable = false,length = 10)
	int tipus;

	@Column(name="persona",nullable = false,length = 255)
	java.lang.String persona;

	@Column(name="comentari",length = 4000)
	java.lang.String comentari;

	@Index(name="pad_event_fitxerid_fk_i")
	@Column(name="fitxerid",length = 19)
	java.lang.Long fitxerID;

	@Column(name="nollegit",nullable = false,length = 1)
	boolean noLlegit;



  /** Constructor Buit */
  public EventJPA() {
  }

  /** Constructor amb tots els camps  */
  public EventJPA(long eventID , java.lang.Long solicitudID , java.lang.Long incidenciaTecnicaID , java.sql.Timestamp dataEvent , int tipus , java.lang.String persona , java.lang.String comentari , java.lang.Long fitxerID , boolean noLlegit) {
    this.eventID=eventID;
    this.solicitudID=solicitudID;
    this.incidenciaTecnicaID=incidenciaTecnicaID;
    this.dataEvent=dataEvent;
    this.tipus=tipus;
    this.persona=persona;
    this.comentari=comentari;
    this.fitxerID=fitxerID;
    this.noLlegit=noLlegit;
}
  /** Constructor sense valors autoincrementals */
  public EventJPA(java.lang.Long solicitudID , java.lang.Long incidenciaTecnicaID , java.sql.Timestamp dataEvent , int tipus , java.lang.String persona , java.lang.String comentari , java.lang.Long fitxerID , boolean noLlegit) {
    this.solicitudID=solicitudID;
    this.incidenciaTecnicaID=incidenciaTecnicaID;
    this.dataEvent=dataEvent;
    this.tipus=tipus;
    this.persona=persona;
    this.comentari=comentari;
    this.fitxerID=fitxerID;
    this.noLlegit=noLlegit;
}
  /** Constructor dels valors Not Null */
  public EventJPA(long eventID , java.sql.Timestamp dataEvent , int tipus , java.lang.String persona , boolean noLlegit) {
    this.eventID=eventID;
    this.dataEvent=dataEvent;
    this.tipus=tipus;
    this.persona=persona;
    this.noLlegit=noLlegit;
}
  public EventJPA(Event __bean) {
    this.setEventID(__bean.getEventID());
    this.setSolicitudID(__bean.getSolicitudID());
    this.setIncidenciaTecnicaID(__bean.getIncidenciaTecnicaID());
    this.setDataEvent(__bean.getDataEvent());
    this.setTipus(__bean.getTipus());
    this.setPersona(__bean.getPersona());
    this.setComentari(__bean.getComentari());
    this.setFitxerID(__bean.getFitxerID());
    this.setNoLlegit(__bean.isNoLlegit());
    // Fitxer
    this.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
	}

	public long getEventID() {
		return(eventID);
	};
	public void setEventID(long _eventID_) {
		this.eventID = _eventID_;
	};

	public java.lang.Long getSolicitudID() {
		return(solicitudID);
	};
	public void setSolicitudID(java.lang.Long _solicitudID_) {
		this.solicitudID = _solicitudID_;
	};

	public java.lang.Long getIncidenciaTecnicaID() {
		return(incidenciaTecnicaID);
	};
	public void setIncidenciaTecnicaID(java.lang.Long _incidenciaTecnicaID_) {
		this.incidenciaTecnicaID = _incidenciaTecnicaID_;
	};

	public java.sql.Timestamp getDataEvent() {
		return(dataEvent);
	};
	public void setDataEvent(java.sql.Timestamp _dataEvent_) {
		this.dataEvent = _dataEvent_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getPersona() {
		return(persona);
	};
	public void setPersona(java.lang.String _persona_) {
		this.persona = _persona_;
	};

	public java.lang.String getComentari() {
		return(comentari);
	};
	public void setComentari(java.lang.String _comentari_) {
		this.comentari = _comentari_;
	};

	public java.lang.Long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(java.lang.Long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public boolean isNoLlegit() {
		return(noLlegit);
	};
	public void setNoLlegit(boolean _noLlegit_) {
		this.noLlegit = _noLlegit_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Event) {
      Event __instance = (Event)__obj;
      __result = true;
      __result = __result && (this.getEventID() == __instance.getEventID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:solicitudid | Table: pad_solicitud | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_event_solicitud_soli_fk")
	@JoinColumn(name = "solicitudid", referencedColumnName ="solicitudID", nullable = true, insertable=false, updatable=false)
	private SolicitudJPA solicitud;

	public SolicitudJPA getSolicitud() {
    return this.solicitud;
  }

	public  void setSolicitud(SolicitudJPA solicitud) {
    this.solicitud = solicitud;
  }

// IMP Field:incidenciatecnicaid | Table: pad_incidenciatecnica | Type: 1  

	@ManyToOne(fetch = FetchType.LAZY)
	@ForeignKey(name="pad_event_inctecnica_it_fk")
	@JoinColumn(name = "incidenciatecnicaid", referencedColumnName ="incidenciaTecnicaID", nullable = true, insertable=false, updatable=false)
	private IncidenciaTecnicaJPA incidenciaTecnica;

	public IncidenciaTecnicaJPA getIncidenciaTecnica() {
    return this.incidenciaTecnica;
  }

	public  void setIncidenciaTecnica(IncidenciaTecnicaJPA incidenciaTecnica) {
    this.incidenciaTecnica = incidenciaTecnica;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name="pad_event_fitxer_fitxer_fk")
	@JoinColumn(name = "fitxerid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false)
	private FitxerJPA fitxer;

	public FitxerJPA getFitxer() {
    return this.fitxer;
  }

	public  void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }


 // ---------------  STATIC METHODS ------------------
  public static EventJPA toJPA(Event __bean) {
    if (__bean == null) { return null;}
    EventJPA __tmp = new EventJPA();
    __tmp.setEventID(__bean.getEventID());
    __tmp.setSolicitudID(__bean.getSolicitudID());
    __tmp.setIncidenciaTecnicaID(__bean.getIncidenciaTecnicaID());
    __tmp.setDataEvent(__bean.getDataEvent());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setPersona(__bean.getPersona());
    __tmp.setComentari(__bean.getComentari());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setNoLlegit(__bean.isNoLlegit());
    // Fitxer
    __tmp.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
		return __tmp;
	}


  public static EventJPA copyJPA(EventJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EventJPA> copyJPA(java.util.Set<EventJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EventJPA> __tmpSet = (java.util.Set<EventJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EventJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EventJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EventJPA copyJPA(EventJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EventJPA __tmp = (EventJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"IncidenciaTecnicaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.incidenciaTecnica) || org.hibernate.Hibernate.isInitialized(__jpa.getIncidenciaTecnica()) ) ) {
      __tmp.setIncidenciaTecnica(IncidenciaTecnicaJPA.copyJPA(__jpa.getIncidenciaTecnica(), __alreadyCopied,"EventJPA"));
    }
    if(!"SolicitudJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitud) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitud()) ) ) {
      __tmp.setSolicitud(SolicitudJPA.copyJPA(__jpa.getSolicitud(), __alreadyCopied,"EventJPA"));
    }

    return __tmp;
  }




}
