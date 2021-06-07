package org.fundaciobit.pinbaladmin.model.entity;

public interface Event extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getEventID();
	public void setEventID(long _eventID_);

	public java.lang.Long getSolicitudID();
	public void setSolicitudID(java.lang.Long _solicitudID_);

	public java.lang.Long getIncidenciaTecnicaID();
	public void setIncidenciaTecnicaID(java.lang.Long _incidenciaTecnicaID_);

	public java.sql.Timestamp getDataEvent();
	public void setDataEvent(java.sql.Timestamp _dataEvent_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.String getPersona();
	public void setPersona(java.lang.String _persona_);

	public java.lang.String getComentari();
	public void setComentari(java.lang.String _comentari_);

	public java.lang.Long getFitxerID();
	public void setFitxerID(java.lang.Long _fitxerID_);

	public boolean isNoLlegit();
	public void setNoLlegit(boolean _noLlegit_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();


  // ======================================

}
