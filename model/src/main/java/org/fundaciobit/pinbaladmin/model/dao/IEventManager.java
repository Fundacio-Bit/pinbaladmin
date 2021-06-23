package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEventManager extends org.fundaciobit.genapp.common.query.ITableManager<Event, Long> {


	public Event create( java.lang.Long _solicitudID_, java.lang.Long _incidenciaTecnicaID_, java.sql.Timestamp _dataEvent_, int _tipus_, java.lang.String _persona_, java.lang.String _comentari_, java.lang.Long _fitxerID_, boolean _noLlegit_, java.lang.String _caidIdentificadorConsulta_, java.lang.String _caidNumeroSeguiment_) throws I18NException;

	public Event findByPrimaryKey(long _eventID_);

	public void delete(long _eventID_);

}
