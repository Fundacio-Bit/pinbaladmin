package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITipusTiquetManager extends org.fundaciobit.genapp.common.query.ITableManager<TipusTiquet, Long> {


	public TipusTiquet create( long _tipusTiquetID_, java.lang.String _nom_) throws I18NException;

	public TipusTiquet findByPrimaryKey(long _tipusTiquetID_);

	public void delete(long _tipusTiquetID_);

}
