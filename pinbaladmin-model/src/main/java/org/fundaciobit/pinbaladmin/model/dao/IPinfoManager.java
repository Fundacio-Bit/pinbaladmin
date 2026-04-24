package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPinfoManager extends org.fundaciobit.genapp.common.query.ITableManager<Pinfo, Long> {


	public Pinfo create( java.lang.Long _incidenciaID_, java.lang.String _entitat_, java.lang.String _solicitantNIF_, java.lang.Long _estat_, java.lang.Long _fitxerID_, java.lang.Long _fitxerfirmatID_, java.lang.String _portafibid_, java.lang.String _destinatariNIF_, java.lang.String _destinatariNom_, java.lang.String _missatgePinbal_, java.lang.String _logpPnbal_, java.lang.String _missatgeSolicitant_) throws I18NException;

	public Pinfo findByPrimaryKey(long _pinfoID_);

	public void delete(long _pinfoID_);

}
