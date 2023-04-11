package org.fundaciobit.pinbaladmin.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaService;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface IncidenciaTecnicaLogicaService extends IncidenciaTecnicaService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/IncidenciaTecnicaLogicaEJB!org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService";

    @Override
    @PermitAll
    public org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA findByPrimaryKey(Long _ID_);

    public IncidenciaTecnica createFromEmail(EmailMessageInfo emi, String creador, String operador, int tipus) throws I18NException;

    public void deleteFull(Long _ID) throws I18NException;

}
