package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.TramitBDadesSoliService;
import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface TramitBDadesSoliLogicaService extends TramitBDadesSoliService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/TramitBDadesSoliLogicaEJB!org.fundaciobit.pinbaladmin.logic.TramitBDadesSoliLogicaService";

    @Override
    @PermitAll
    public TramitBDadesSoliJPA findByPrimaryKey(Long _ID_);

    public void deleteFull(Long _ID_) throws I18NException;

    public List<StringKeyValue> getReferenceListForTipussolicitud();

    public String getTipussolicitudValue(long tipussolicitud);

}
