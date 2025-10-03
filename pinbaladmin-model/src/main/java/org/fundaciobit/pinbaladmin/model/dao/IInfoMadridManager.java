package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IInfoMadridManager extends org.fundaciobit.genapp.common.query.ITableManager<InfoMadrid, Long> {


	public InfoMadrid create( java.lang.String _codi_, java.lang.Long _estatProcediment_, java.lang.Long _estatAutoritzacio_, java.lang.String _missatge_, java.lang.String _consulta_, java.lang.String _titularNom_, java.lang.String _titularNif_, java.sql.Timestamp _dataAutoritzacio_, java.sql.Timestamp _dataEnviament_, java.lang.Long _intents_, java.sql.Timestamp _dataConsulta_) throws I18NException;

	public InfoMadrid findByPrimaryKey(long _infoMadridID_);

	public void delete(long _infoMadridID_);

}
