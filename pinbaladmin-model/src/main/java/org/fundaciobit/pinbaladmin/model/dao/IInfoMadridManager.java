package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IInfoMadridManager extends org.fundaciobit.genapp.common.query.ITableManager<InfoMadrid, Long> {


	public InfoMadrid create( java.lang.String _codi_, long _estatProcediment_, long _estatAutoritzacio_, java.lang.String _missatge_, java.lang.String _consulta_, java.lang.String _titularNom_, java.lang.String _titularNif_, java.sql.Timestamp _dataAutoritzacio_, java.sql.Timestamp _dataEnviament_, long _intents_) throws I18NException;

	public InfoMadrid findByPrimaryKey(long _infoMadridID_);

	public void delete(long _infoMadridID_);

}
