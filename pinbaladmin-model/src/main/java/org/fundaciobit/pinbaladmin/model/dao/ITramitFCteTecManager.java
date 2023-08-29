package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITramitFCteTecManager extends org.fundaciobit.genapp.common.query.ITableManager<TramitFCteTec, Long> {


	public TramitFCteTec create( long _tramitid_, java.lang.String _nif_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _carrec_, java.lang.String _telefon_, java.lang.String _mail_) throws I18NException;

	public TramitFCteTec findByPrimaryKey(long _ctetecid_);

	public void delete(long _ctetecid_);

}
