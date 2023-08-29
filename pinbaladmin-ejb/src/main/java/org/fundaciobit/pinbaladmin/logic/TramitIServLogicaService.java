package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitIServService;
import org.fundaciobit.pinbaladmin.persistence.TramitIServJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface TramitIServLogicaService extends TramitIServService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitIServLogicaEJB!org.fundaciobit.pinbaladmin.logic.TramitIServLogicaService";

    @Override
    @PermitAll
    public TramitIServJPA findByPrimaryKey(Long _ID_);

    public void deleteFull(Long _ID) throws I18NException;

    public String getServeiValue(String servei) throws I18NException;

    public List<StringKeyValue> getReferenceListForServei() throws I18NException;

}
