package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEstatServeiManager extends org.fundaciobit.genapp.common.query.ITableManager<EstatServei, Long> {


	public EstatServei create( long _estatServeiID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException;

	public EstatServei findByPrimaryKey(long _estatServeiID_);

	public void delete(long _estatServeiID_);

}
