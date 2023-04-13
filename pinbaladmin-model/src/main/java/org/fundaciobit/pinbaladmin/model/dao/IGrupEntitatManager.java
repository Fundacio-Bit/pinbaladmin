package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IGrupEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<GrupEntitat, Long> {


	public GrupEntitat create( java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException;

	public GrupEntitat findByPrimaryKey(long _grupEntitatID_);

	public void delete(long _grupEntitatID_);

}