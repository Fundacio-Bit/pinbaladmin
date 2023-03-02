package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ICampSolicitudManager extends org.fundaciobit.genapp.common.query.ITableManager<CampSolicitud, Long> {


	public CampSolicitud create( long _campFormulariID_, long _solicitudServeiID_, java.lang.String _valor_) throws I18NException;

	public CampSolicitud findByPrimaryKey(long _campSolicitudID_);

	public void delete(long _campSolicitudID_);

}
