package org.fundaciobit.pinbaladmin.model.dao;

import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IModificacioSoliServManager extends org.fundaciobit.genapp.common.query.ITableManager<ModificacioSoliServ, Long> {


	public ModificacioSoliServ create( long _soliServID_, long _modSoliID_, java.lang.String _estat_, java.lang.String _norma1_, java.lang.String _articles1_, java.lang.Long _fitxerNorma1ID_, java.lang.String _norma2_, java.lang.String _articles2_, java.lang.Long _fitxerNorma2ID_, java.lang.String _norma3_, java.lang.String _articles3_, java.lang.Long _fitxerNorma3ID_) throws I18NException;

	public ModificacioSoliServ findByPrimaryKey(long _modsoliservid_);

	public void delete(long _modsoliservid_);

}
