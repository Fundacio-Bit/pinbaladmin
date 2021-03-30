package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAreaManager extends org.fundaciobit.genapp.common.query.ITableManager<Area, Long> {


	public Area create( java.lang.String _nom_, long _entitatID_) throws I18NException;

	public Area findByPrimaryKey(long _areaID_);

	public void delete(long _areaID_);

}
