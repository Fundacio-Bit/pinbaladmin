package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IDocumentManager extends org.fundaciobit.genapp.common.query.ITableManager<Document, Long> {


	public Document create( java.lang.String _nom_, long _fitxerOriginalID_, java.lang.Long _fitxerFirmatID_, java.lang.String _notes_) throws I18NException;

	public Document findByPrimaryKey(long _documentID_);

	public void delete(long _documentID_);

}
