package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPinfoDataManager extends org.fundaciobit.genapp.common.query.ITableManager<PinfoData, Long> {


	public PinfoData create( java.lang.Long _pinfoID_, java.lang.Long _estat_, java.lang.String _usuariid_, java.lang.Long _procedimentID_, java.lang.Long _serveiID_, java.lang.Long _alta_) throws I18NException;

	public PinfoData findByPrimaryKey(long _pinfodataID_);

	public void delete(long _pinfodataID_);

}
