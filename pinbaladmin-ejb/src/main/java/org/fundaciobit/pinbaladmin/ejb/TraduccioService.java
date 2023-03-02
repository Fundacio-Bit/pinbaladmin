
package org.fundaciobit.pinbaladmin.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.pinbaladmin.persistence.TraduccioJPA;
import org.fundaciobit.pinbaladmin.persistence.TraduccioIJPAManager;
import org.fundaciobit.pinbaladmin.model.dao.ITraduccioManager;

import org.fundaciobit.pinbaladmin.model.entity.Traduccio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TraduccioService extends TraduccioIJPAManager,ITraduccioManager {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TraduccioEJB!org.fundaciobit.pinbaladmin.ejb.TraduccioService";

    public TraduccioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Traduccio instance, FitxerService fitxerEjb) throws I18NException;
}
