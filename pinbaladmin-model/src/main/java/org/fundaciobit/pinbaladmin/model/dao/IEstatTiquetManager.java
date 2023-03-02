package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEstatTiquetManager extends org.fundaciobit.genapp.common.query.ITableManager<EstatTiquet, Long> {


	public EstatTiquet create( long _estatTiquetID_, java.lang.String _nom_) throws I18NException;

	public EstatTiquet findByPrimaryKey(long _estatTiquetID_);

	public void delete(long _estatTiquetID_);

}
