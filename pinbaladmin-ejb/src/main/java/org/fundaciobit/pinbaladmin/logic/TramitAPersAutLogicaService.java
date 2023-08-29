package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitAPersAutService;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface TramitAPersAutLogicaService extends TramitAPersAutService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitAPersAutLogicaEJB!org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService";

    @Override
    @PermitAll
    public TramitAPersAutJPA findByPrimaryKey(Long _ID_);

    public void deleteFull(Long _ID_) throws I18NException;

    public void generaXml(Long tramitID) throws I18NException;

    public Long[] getPartsTramitIDs(long tramitID) throws I18NException;

}
