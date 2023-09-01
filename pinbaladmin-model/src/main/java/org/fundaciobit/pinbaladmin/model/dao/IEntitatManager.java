package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<Entitat, Long> {


	public Entitat create( java.lang.String _nom_, java.lang.String _personaContacte_, java.lang.String _CIF_, long _grupEntitatID_, boolean _convenipmsbae_, java.lang.String _dir3_) throws I18NException;

	public Entitat findByPrimaryKey(long _entitatID_);

	public void delete(long _entitatID_);

}
