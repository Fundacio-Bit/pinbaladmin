
package org.fundaciobit.pinbaladmin.persistence;
import org.fundaciobit.pinbaladmin.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity(name = "EventJPA")
@Table(name = "pad_event" , indexes = { 
        @Index(name="pad_event_pk_i", columnList = "eventid"),
        @Index(name="pad_event_solicitudid_fk_i", columnList = "solicitudid"),
        @Index(name="pad_event_inctecnicaid_fk_i", columnList = "incidenciatecnicaid"),
        @Index(name="pad_event_fitxerid_fk_i", columnList = "fitxerid")})
@SequenceGenerator(name="EVENT_SEQ", sequenceName="pad_event_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EventJPA implements Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="EVENT_SEQ")
    @Column(name="eventid",nullable = false,length = 19)
    long eventID;

    @Column(name="solicitudid",length = 19)
    java.lang.Long solicitudID;

    @Column(name="incidenciatecnicaid",length = 19)
    java.lang.Long incidenciaTecnicaID;

    @Column(name="dataevent",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataEvent;

    @Column(name="tipus",nullable = false,length = 10)
    int tipus;

    @Column(name="persona",nullable = false,length = 255)
    java.lang.String persona;

    @Column(name="destinatari",length = 255)
    java.lang.String destinatari;

    @Column(name="destinatarimail",length = 255)
    java.lang.String destinatarimail;

    @Column(name="comentari",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String comentari;

    @Column(name="fitxerid",length = 19)
    java.lang.Long fitxerID;

    @Column(name="nollegit",nullable = false,length = 1)
    boolean noLlegit = false;

    @Column(name="caididentificadorconsulta",length = 100)
    java.lang.String caidIdentificadorConsulta;

    @Column(name="caidnumeroseguiment",length = 100)
    java.lang.String caidNumeroSeguiment;



  /** Constructor Buit */
  public EventJPA() {
  }

  /** Constructor amb tots els camps  */
  public EventJPA(long eventID , java.lang.Long solicitudID , java.lang.Long incidenciaTecnicaID , java.sql.Timestamp dataEvent , int tipus , java.lang.String persona , java.lang.String destinatari , java.lang.String destinatarimail , java.lang.String comentari , java.lang.Long fitxerID , boolean noLlegit , java.lang.String caidIdentificadorConsulta , java.lang.String caidNumeroSeguiment) {
    this.eventID=eventID;
    this.solicitudID=solicitudID;
    this.incidenciaTecnicaID=incidenciaTecnicaID;
    this.dataEvent=dataEvent;
    this.tipus=tipus;
    this.persona=persona;
    this.destinatari=destinatari;
    this.destinatarimail=destinatarimail;
    this.comentari=comentari;
    this.fitxerID=fitxerID;
    this.noLlegit=noLlegit;
    this.caidIdentificadorConsulta=caidIdentificadorConsulta;
    this.caidNumeroSeguiment=caidNumeroSeguiment;
}
  /** Constructor sense valors autoincrementals */
  public EventJPA(java.lang.Long solicitudID , java.lang.Long incidenciaTecnicaID , java.sql.Timestamp dataEvent , int tipus , java.lang.String persona , java.lang.String destinatari , java.lang.String destinatarimail , java.lang.String comentari , java.lang.Long fitxerID , boolean noLlegit , java.lang.String caidIdentificadorConsulta , java.lang.String caidNumeroSeguiment) {
    this.solicitudID=solicitudID;
    this.incidenciaTecnicaID=incidenciaTecnicaID;
    this.dataEvent=dataEvent;
    this.tipus=tipus;
    this.persona=persona;
    this.destinatari=destinatari;
    this.destinatarimail=destinatarimail;
    this.comentari=comentari;
    this.fitxerID=fitxerID;
    this.noLlegit=noLlegit;
    this.caidIdentificadorConsulta=caidIdentificadorConsulta;
    this.caidNumeroSeguiment=caidNumeroSeguiment;
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
    this.setDestinatari(__bean.getDestinatari());
    this.setDestinatarimail(__bean.getDestinatarimail());
    this.setComentari(__bean.getComentari());
    this.setFitxerID(__bean.getFitxerID());
    this.setNoLlegit(__bean.isNoLlegit());
    this.setCaidIdentificadorConsulta(__bean.getCaidIdentificadorConsulta());
    this.setCaidNumeroSeguiment(__bean.getCaidNumeroSeguiment());
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

	public java.lang.String getDestinatari() {
		return(destinatari);
	};
	public void setDestinatari(java.lang.String _destinatari_) {
		this.destinatari = _destinatari_;
	};

	public java.lang.String getDestinatarimail() {
		return(destinatarimail);
	};
	public void setDestinatarimail(java.lang.String _destinatarimail_) {
		this.destinatarimail = _destinatarimail_;
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

	public java.lang.String getCaidIdentificadorConsulta() {
		return(caidIdentificadorConsulta);
	};
	public void setCaidIdentificadorConsulta(java.lang.String _caidIdentificadorConsulta_) {
		this.caidIdentificadorConsulta = _caidIdentificadorConsulta_;
	};

	public java.lang.String getCaidNumeroSeguiment() {
		return(caidNumeroSeguiment);
	};
	public void setCaidNumeroSeguiment(java.lang.String _caidNumeroSeguiment_) {
		this.caidNumeroSeguiment = _caidNumeroSeguiment_;
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
    @JoinColumn(name = "solicitudid", referencedColumnName ="solicitudID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_event_solicitud_soli_fk"))
    private SolicitudJPA solicitud;

    public SolicitudJPA getSolicitud() {
    return this.solicitud;
  }

    public  void setSolicitud(SolicitudJPA solicitud) {
    this.solicitud = solicitud;
  }

// IMP Field:incidenciatecnicaid | Table: pad_incidenciatecnica | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidenciatecnicaid", referencedColumnName ="incidenciaTecnicaID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_event_inctecnica_it_fk"))
    private IncidenciaTecnicaJPA incidenciaTecnica;

    public IncidenciaTecnicaJPA getIncidenciaTecnica() {
    return this.incidenciaTecnica;
  }

    public  void setIncidenciaTecnica(IncidenciaTecnicaJPA incidenciaTecnica) {
    this.incidenciaTecnica = incidenciaTecnica;
  }

// IMP Field:fitxerid | Table: pad_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxerid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="pad_event_fitxer_fitxer_fk"))
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
    __tmp.setDestinatari(__bean.getDestinatari());
    __tmp.setDestinatarimail(__bean.getDestinatarimail());
    __tmp.setComentari(__bean.getComentari());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setNoLlegit(__bean.isNoLlegit());
    __tmp.setCaidIdentificadorConsulta(__bean.getCaidIdentificadorConsulta());
    __tmp.setCaidNumeroSeguiment(__bean.getCaidNumeroSeguiment());
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
    if(!"SolicitudJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.solicitud) || org.hibernate.Hibernate.isInitialized(__jpa.getSolicitud()) ) ) {
      __tmp.setSolicitud(SolicitudJPA.copyJPA(__jpa.getSolicitud(), __alreadyCopied,"EventJPA"));
    }
    if(!"IncidenciaTecnicaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.incidenciaTecnica) || org.hibernate.Hibernate.isInitialized(__jpa.getIncidenciaTecnica()) ) ) {
      __tmp.setIncidenciaTecnica(IncidenciaTecnicaJPA.copyJPA(__jpa.getIncidenciaTecnica(), __alreadyCopied,"EventJPA"));
    }

    return __tmp;
  }




}
