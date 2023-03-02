package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IFormulariManager extends org.fundaciobit.genapp.common.query.ITableManager<Formulari, Long> {


	public Formulari create( java.lang.String _nom_, java.lang.String _descripcio_, java.lang.Long _fitxerID_) throws I18NException;

	public Formulari findByPrimaryKey(long _formulariid_);

	public void delete(long _formulariid_);

}
