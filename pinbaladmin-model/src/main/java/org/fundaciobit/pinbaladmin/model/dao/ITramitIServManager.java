package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITramitIServManager extends org.fundaciobit.genapp.common.query.ITableManager<TramitIServ, Long> {


	public TramitIServ create( long _tramitid_, java.lang.String _nom_, java.lang.String _codi_, java.lang.String _norma_, java.lang.String _urlnorma_, java.lang.String _articles_, java.lang.String _consentiment_, java.lang.String _consentimentpublicat_, java.lang.String _urlconsentiment_) throws I18NException;

	public TramitIServ findByPrimaryKey(long _servid_);

	public void delete(long _servid_);

}
