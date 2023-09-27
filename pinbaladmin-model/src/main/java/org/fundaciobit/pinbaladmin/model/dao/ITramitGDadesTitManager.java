package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITramitGDadesTitManager extends org.fundaciobit.genapp.common.query.ITableManager<TramitGDadesTit, Long> {


	public TramitGDadesTit create( long _tramitid_, java.lang.String _nif_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _carrec_) throws I18NException;

	public TramitGDadesTit findByPrimaryKey(long _dadestitid_);

	public void delete(long _dadestitid_);

}