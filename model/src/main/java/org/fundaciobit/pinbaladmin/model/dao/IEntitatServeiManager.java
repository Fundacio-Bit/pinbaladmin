package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEntitatServeiManager extends org.fundaciobit.genapp.common.query.ITableManager<EntitatServei, Long> {


	public EntitatServei create( java.lang.String _nom_, java.lang.String _descripcio_, boolean _balears_) throws I18NException;

	public EntitatServei findByPrimaryKey(long _entitatServeiID_);

	public void delete(long _entitatServeiID_);

}
