package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IDepartamentManager extends org.fundaciobit.genapp.common.query.ITableManager<Departament, Long> {


	public Departament create( java.lang.String _nom_, long _areaID_) throws I18NException;

	public Departament findByPrimaryKey(long _departamentID_);

	public void delete(long _departamentID_);

}
