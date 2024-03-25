package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITramitCDadesCesiManager extends org.fundaciobit.genapp.common.query.ITableManager<TramitCDadesCesi, Long> {


	public TramitCDadesCesi create( long _tramitid_, java.lang.Long _organID_, java.lang.Long _organArrelID_, java.lang.String _denominacio_, java.lang.String _nif_, java.lang.String _responsable_, java.lang.String _dir3responsable_, java.lang.String _dir3arrel_, java.lang.String _direccio_, java.lang.String _codipostal_, java.lang.String _municipi_) throws I18NException;

	public TramitCDadesCesi findByPrimaryKey(long _dadescesiid_);

	public void delete(long _dadescesiid_);

}
