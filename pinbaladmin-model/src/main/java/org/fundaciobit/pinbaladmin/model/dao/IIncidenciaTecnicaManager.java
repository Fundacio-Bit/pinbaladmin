package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IIncidenciaTecnicaManager extends org.fundaciobit.genapp.common.query.ITableManager<IncidenciaTecnica, Long> {


	public IncidenciaTecnica create( java.lang.String _titol_, java.lang.String _descripcio_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, int _estat_, int _tipus_, java.lang.String _nomEntitat_, java.lang.String _contacteNom_, java.lang.String _contacteEmail_, java.lang.String _contacteTelefon_, java.lang.String _caidIdentificadorConsulta_, java.lang.String _caidNumeroSeguiment_, java.lang.String _creador_, java.lang.String _operador_) throws I18NException;

	public IncidenciaTecnica findByPrimaryKey(long _incidenciaTecnicaID_);

	public void delete(long _incidenciaTecnicaID_);

}
