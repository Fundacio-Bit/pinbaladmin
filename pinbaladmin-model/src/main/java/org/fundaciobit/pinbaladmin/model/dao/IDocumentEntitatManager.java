package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IDocumentEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<DocumentEntitat, Long> {


	public DocumentEntitat create( java.lang.String _titol_, java.lang.String _descripcio_, long _entitatID_, java.lang.Long _fitxerID_, java.sql.Timestamp _dataAlta_) throws I18NException;

	public DocumentEntitat findByPrimaryKey(long _documentEntitatID_);

	public void delete(long _documentEntitatID_);

}
