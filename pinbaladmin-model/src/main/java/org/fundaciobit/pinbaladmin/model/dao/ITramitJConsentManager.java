package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITramitJConsentManager extends org.fundaciobit.genapp.common.query.ITableManager<TramitJConsent, Long> {


	public TramitJConsent create( long _tramitid_, java.lang.Long _adjuntID_, java.lang.String _consentiment_, java.lang.String _urlconsentiment_, java.lang.String _consentimentadjunt_) throws I18NException;

	public TramitJConsent findByPrimaryKey(long _consentid_);

	public void delete(long _consentid_);

}
