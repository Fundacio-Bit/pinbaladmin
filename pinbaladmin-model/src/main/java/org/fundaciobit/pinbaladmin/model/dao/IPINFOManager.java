package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPINFOManager extends org.fundaciobit.genapp.common.query.ITableManager<PINFO, Long> {


	public PINFO create( java.lang.Long _IncidenciaID_, java.lang.Long _estat_, java.lang.Long _fitxerID_, java.lang.Long _fitxerfirmatID_, java.lang.String _portafibid_) throws I18NException;

	public PINFO findByPrimaryKey(long _PinfoID_);

	public void delete(long _PinfoID_);

}
