package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ISolicitudServeiManager extends org.fundaciobit.genapp.common.query.ITableManager<SolicitudServei, Long> {


	public SolicitudServei create( long _solicitudID_, long _serveiID_, java.lang.Long _estatSolicitudServeiID_, java.lang.String _normaLegal_, java.lang.String _enllazNormaLegal_, java.lang.String _articles_, java.lang.String _tipusConsentiment_, java.lang.String _consentiment_, java.lang.String _enllazConsentiment_, java.lang.String _notes_) throws I18NException;

	public SolicitudServei findByPrimaryKey(long _id_);

	public void delete(long _id_);

}
