package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IServeiManager extends org.fundaciobit.genapp.common.query.ITableManager<Servei, Long> {


	public Servei create( java.lang.String _codi_, java.lang.String _nom_, java.lang.String _descripcio_, java.lang.Long _formulariID_, java.lang.Long _entitatServeiID_, java.lang.Long _estatServeiID_, int _tipusConsentiment_, boolean _ocult_) throws I18NException;

	public Servei findByPrimaryKey(long _serveiID_);

	public void delete(long _serveiID_);

}
