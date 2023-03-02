package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IDocumentSolicitudManager extends org.fundaciobit.genapp.common.query.ITableManager<DocumentSolicitud, Long> {


	public DocumentSolicitud create( long _documentID_, long _solicitudID_) throws I18NException;

	public DocumentSolicitud findByPrimaryKey(long _documentSolicitudID_);

	public void delete(long _documentSolicitudID_);

}
