package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IOperadorManager extends org.fundaciobit.genapp.common.query.ITableManager<Operador, Long> {


	public Operador create( java.lang.String _username_, java.lang.String _nom_, java.lang.String _email_) throws I18NException;

	public Operador findByPrimaryKey(long _operadorID_);

	public void delete(long _operadorID_);

}
