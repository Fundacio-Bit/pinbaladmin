package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ISolicitudManager extends org.fundaciobit.genapp.common.query.ITableManager<Solicitud, Long> {


	public Solicitud create( java.lang.String _procedimentCodi_, java.lang.String _codiDescriptiu_, java.lang.String _procedimentNom_, java.lang.String _procedimentTipus_, java.lang.Long _estatID_, java.lang.String _ticketAssociat_, java.lang.String _ticketNumeroSeguiment_, java.lang.Long _departamentID_, java.lang.String _entitatEstatal_, java.lang.String _pinfo_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, java.lang.String _personaContacte_, java.lang.String _personaContacteEmail_, java.lang.String _responsableProcNom_, java.lang.String _responsableProcEmail_, java.lang.String _notes_, java.lang.Long _documentSolicitudID_, java.lang.Long _solicitudXmlID_, boolean _firmatDocSolicitud_, boolean _produccio_, java.lang.String _creador_) throws I18NException;

	public Solicitud findByPrimaryKey(long _solicitudID_);

	public void delete(long _solicitudID_);

}
