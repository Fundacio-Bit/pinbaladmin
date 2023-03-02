package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IGrupEntitatCedentManager extends org.fundaciobit.genapp.common.query.ITableManager<GrupEntitatCedent, Long> {


	public GrupEntitatCedent create( long _grupEntitatID_, long _cedentID_) throws I18NException;

	public GrupEntitatCedent findByPrimaryKey(long _grupEntitatCedentID_);

	public void delete(long _grupEntitatCedentID_);

}
