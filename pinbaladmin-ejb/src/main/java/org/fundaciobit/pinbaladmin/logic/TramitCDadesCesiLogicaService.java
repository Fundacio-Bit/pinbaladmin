package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitCDadesCesiService;
import org.fundaciobit.pinbaladmin.persistence.TramitCDadesCesiJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface TramitCDadesCesiLogicaService extends TramitCDadesCesiService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitCDadesCesiLogicaEJB!org.fundaciobit.pinbaladmin.logic.TramitCDadesCesiLogicaService";

    @Override
    @PermitAll
    public TramitCDadesCesiJPA findByPrimaryKey(Long _ID_);

    public void deleteFull(Long _ID) throws I18NException;

    String getMunicipiValue(String municipi);

    List<StringKeyValue> getReferenceListForMunicipi();

    String getDenominacioValue(String denominacio) throws I18NException;

    List<StringKeyValue> getReferenceListForDenominacio() throws I18NException;

}
