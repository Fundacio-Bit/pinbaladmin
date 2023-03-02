package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEstatSolicitudServeiManager extends org.fundaciobit.genapp.common.query.ITableManager<EstatSolicitudServei, Long> {


	public EstatSolicitudServei create( long _estatSolicitudServeiID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException;

	public EstatSolicitudServei findByPrimaryKey(long _estatSolicitudServeiID_);

	public void delete(long _estatSolicitudServeiID_);

}
