
package org.fundaciobit.pinbaladmin.model.bean;

import org.fundaciobit.pinbaladmin.model.entity.Event;


public class EventBean implements Event {



	long eventID;// PK
	java.lang.Long solicitudID;
	java.lang.Long incidenciaTecnicaID;
	java.sql.Timestamp dataEvent;
	int tipus;
	java.lang.String persona;
	java.lang.String destinatari;
	java.lang.String destinatarimail;
	java.lang.String comentari;
	java.lang.Long fitxerID;
	boolean noLlegit;
	java.lang.String caidIdentificadorConsulta;
	java.lang.String caidNumeroSeguiment;


  /** Constructor Buit */
  public EventBean() {
  }

  /** Constructor amb tots els camps  */
  public EventBean(long eventID , java.lang.Long solicitudID , java.lang.Long incidenciaTecnicaID , java.sql.Timestamp dataEvent , int tipus , java.lang.String persona , java.lang.String destinatari , java.lang.String destinatarimail , java.lang.String comentari , java.lang.Long fitxerID , boolean noLlegit , java.lang.String caidIdentificadorConsulta , java.lang.String caidNumeroSeguiment) {
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
  public EventBean(java.lang.Long solicitudID , java.lang.Long incidenciaTecnicaID , java.sql.Timestamp dataEvent , int tipus , java.lang.String persona , java.lang.String destinatari , java.lang.String destinatarimail , java.lang.String comentari , java.lang.Long fitxerID , boolean noLlegit , java.lang.String caidIdentificadorConsulta , java.lang.String caidNumeroSeguiment) {
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
  public EventBean(long eventID , java.sql.Timestamp dataEvent , int tipus , java.lang.String persona , boolean noLlegit) {
    this.eventID=eventID;
    this.dataEvent=dataEvent;
    this.tipus=tipus;
    this.persona=persona;
    this.noLlegit=noLlegit;
}
  public EventBean(Event __bean) {
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
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
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



  // ======================================

  public static EventBean toBean(Event __bean) {
    if (__bean == null) { return null;}
    EventBean __tmp = new EventBean();
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
    __tmp.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
		return __tmp;
	}

  protected FitxerBean fitxer;
  public FitxerBean getFitxer() {
    return fitxer;
  }
  public void setFitxer(FitxerBean __field) {
    this. fitxer = __field;
  }


}
