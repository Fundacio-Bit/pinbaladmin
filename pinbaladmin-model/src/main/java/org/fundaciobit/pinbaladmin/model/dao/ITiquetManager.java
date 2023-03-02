package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITiquetManager extends org.fundaciobit.genapp.common.query.ITableManager<Tiquet, Long> {


	public Tiquet create( java.lang.String _titol_, java.lang.String _descripcio_, java.lang.String _informador_, java.sql.Timestamp _dataAlta_, long _estatTiquetID_, long _tipusTiquetID_, java.lang.String _versioPinbal_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataIncidencia_, java.lang.String _solucionatPer_, java.sql.Timestamp _datafi_, java.lang.String _notes_, int _entorn_, java.lang.Long _adjunt1ID_, java.lang.Long _adjunt2ID_) throws I18NException;

	public Tiquet findByPrimaryKey(long _tiquetID_);

	public void delete(long _tiquetID_);

}
