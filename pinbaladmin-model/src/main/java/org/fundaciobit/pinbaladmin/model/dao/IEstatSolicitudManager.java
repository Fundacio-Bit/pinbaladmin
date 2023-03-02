package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEstatSolicitudManager extends org.fundaciobit.genapp.common.query.ITableManager<EstatSolicitud, Long> {


	public EstatSolicitud create( long _estatSolicitudID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException;

	public EstatSolicitud findByPrimaryKey(long _estatSolicitudID_);

	public void delete(long _estatSolicitudID_);

}
