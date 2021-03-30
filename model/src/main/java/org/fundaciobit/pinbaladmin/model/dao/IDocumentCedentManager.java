package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IDocumentCedentManager extends org.fundaciobit.genapp.common.query.ITableManager<DocumentCedent, Long> {


	public DocumentCedent create( java.lang.String _titol_, java.lang.String _descripcio_, long _entitatServeiID_, java.sql.Timestamp _dataCreacio_, java.lang.Long _fitxerID_) throws I18NException;

	public DocumentCedent findByPrimaryKey(long _documentCedentID_);

	public void delete(long _documentCedentID_);

}
