package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IOrganManager extends org.fundaciobit.genapp.common.query.ITableManager<Organ, Long> {


	public Organ create( java.lang.String _nom_, java.lang.String _dir3_, java.lang.String _dir3pare_, java.lang.Long _entitatid_, java.lang.String _cif_) throws I18NException;

	public Organ findByPrimaryKey(long _organid_);

	public void delete(long _organid_);

}
