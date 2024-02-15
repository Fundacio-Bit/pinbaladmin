package org.fundaciobit.pinbaladmin.logic;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.ejb.DocumentEJB;
import org.fundaciobit.pinbaladmin.ejb.DocumentService;
import org.fundaciobit.pinbaladmin.logic.utils.LogicUtils;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "DocumentLogicaEJB")
public class DocumentLogicaEJB extends DocumentEJB implements DocumentLogicaService {

    @Override
    @PermitAll
    public Document create(Document instance) throws I18NException {
        return super.create(instance);
    }
}