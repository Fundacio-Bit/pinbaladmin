package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEmailManager extends org.fundaciobit.genapp.common.query.ITableManager<Email, Long> {


	public Email create( java.lang.String _destinataris_, java.lang.String _subject_, java.lang.String _message_, java.sql.Timestamp _dataEnviament_, java.lang.String _enviador_) throws I18NException;

	public Email findByPrimaryKey(long _emailID_);

	public void delete(long _emailID_);

}
