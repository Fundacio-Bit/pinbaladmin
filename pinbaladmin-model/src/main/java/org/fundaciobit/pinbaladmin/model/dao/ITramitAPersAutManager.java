package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITramitAPersAutManager extends org.fundaciobit.genapp.common.query.ITableManager<TramitAPersAut, Long> {


	public TramitAPersAut create( long _tramitid_, java.sql.Timestamp _datatramit_, java.lang.String _nif_, java.lang.String _mail_, java.lang.String _telefon_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _urlsistra_, java.lang.String _idsesionformulario_) throws I18NException;

	public TramitAPersAut findByPrimaryKey(long _persautid_);

	public void delete(long _persautid_);

}
