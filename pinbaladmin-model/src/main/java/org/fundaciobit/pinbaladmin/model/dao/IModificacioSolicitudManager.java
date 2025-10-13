package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IModificacioSolicitudManager extends org.fundaciobit.genapp.common.query.ITableManager<ModificacioSolicitud, Long> {


	public ModificacioSolicitud create( long _solicitudID_, java.lang.String _procedimentCodi_, java.lang.String _procedimentNom_, java.lang.String _codiSiaNou_, java.lang.Long _estatID_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, java.lang.String _procedimentTipus_, java.lang.String _notes_, java.lang.Long _organID_, java.lang.String _responsableProcNom_, java.lang.String _responsableProceMail_, java.lang.String _consentiment_, java.lang.Long _doCconsentimentID_, java.lang.String _solicitantNom_, java.lang.String _solicitantNif_, java.lang.String _solicitantMail_, java.lang.String _solicitantUsername_, java.lang.Long _estatModificacio_, java.lang.String _contactenom_, java.lang.String _contactemail_, boolean _esmena_) throws I18NException;

	public ModificacioSolicitud findByPrimaryKey(long _modsoliID_);

	public void delete(long _modsoliID_);

}
