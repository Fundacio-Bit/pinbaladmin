package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ICampFormulariManager extends org.fundaciobit.genapp.common.query.ITableManager<CampFormulari, Long> {


	public CampFormulari create( java.lang.String _nom_, java.lang.String _campPDF_, long _formulariID_) throws I18NException;

	public CampFormulari findByPrimaryKey(long _campFormulariID_);

	public void delete(long _campFormulariID_);

}
