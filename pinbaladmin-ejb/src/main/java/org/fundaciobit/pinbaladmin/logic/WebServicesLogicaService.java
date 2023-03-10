package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.model.entity.Servei;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface WebServicesLogicaService  {

  public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/WebServicesLogicaEJB!org.fundaciobit.pinbaladmin.logic.WebServicesLogicaService";

  public List<Servei> serveiEjbSelect(Where w) throws I18NException;
  
}

