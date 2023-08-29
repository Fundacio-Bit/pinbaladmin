package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITramitBDadesSoliManager extends org.fundaciobit.genapp.common.query.ITableManager<TramitBDadesSoli, Long> {


	public TramitBDadesSoli create( long _tramitid_, long _tipussolicitud_, java.lang.String _entorn_) throws I18NException;

	public TramitBDadesSoli findByPrimaryKey(long _dadessoliid_);

	public void delete(long _dadessoliid_);

}
