package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITramitHProcManager extends org.fundaciobit.genapp.common.query.ITableManager<TramitHProc, Long> {


	public TramitHProc create( long _tramitid_, java.lang.String _tipus_, java.lang.String _nom_, java.lang.String _codi_, java.lang.String _urlseu_, boolean _caducitat_, java.sql.Timestamp _caducitatdata_, java.lang.String _descripcio_, long _peticionsaldia_, long _peticionsalmes_, boolean _periodico_, boolean _automatizado_) throws I18NException;

	public TramitHProc findByPrimaryKey(long _procid_);

	public void delete(long _procid_);

}
