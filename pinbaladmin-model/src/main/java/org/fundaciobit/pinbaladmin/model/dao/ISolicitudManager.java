package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ISolicitudManager extends org.fundaciobit.genapp.common.query.ITableManager<Solicitud, Long> {


	public Solicitud create( java.lang.String _procedimentCodi_, java.lang.String _codiDescriptiu_, java.lang.String _codiSiaConv_, java.lang.String _procedimentNom_, java.lang.String _procedimentTipus_, java.lang.Long _organid_, java.lang.Long _estatSolicitud_, java.lang.String _expedientPid_, java.lang.String _entitatEstatal_, java.lang.String _pinfo_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, java.lang.String _personaContacte_, java.lang.String _personaContacteEmail_, java.lang.String _responsableProcNom_, java.lang.String _responsableProcEmail_, java.lang.String _notes_, java.lang.Long _documentSolicitudID_, java.lang.Long _solicitudXmlID_, boolean _firmatDocSolicitud_, boolean _produccio_, java.lang.String _denominacio_, java.lang.String _dir3_, java.lang.String _nif_, java.lang.String _creador_, java.lang.String _operador_, java.lang.Long _estatpinbal_, java.lang.String _consentiment_, java.lang.String _urlconsentiment_, java.lang.String _consentimentadjunt_, java.lang.Long _portafibID_, java.lang.Long _infomadridid_, java.sql.Timestamp _dataCaducitat_, java.lang.Long _fitxerConsentimentID_, java.lang.Long _contacteTitularID_, java.lang.String _titularFirmaNif_, java.lang.String _titularFirmaNom_, java.lang.String _titularFirmaLlinatges_, java.lang.String _titularFirmaEmail_, java.lang.Long _solicitudFusionadaID_, java.lang.Long _contactePersonaID_, java.lang.Long _contacteResponsableID_, java.lang.Long _contacteSolicitantID_, java.lang.Long _contacteGestAutID_, java.lang.Long _contacteAuditoriaID_, java.lang.Long _contacteTecnicID_) throws I18NException;

	public Solicitud findByPrimaryKey(long _solicitudID_);

	public void delete(long _solicitudID_);

}
