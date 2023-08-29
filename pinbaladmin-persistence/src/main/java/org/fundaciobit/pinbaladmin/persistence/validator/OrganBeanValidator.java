package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.OrganJPA;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import java.util.List;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class OrganBeanValidator 
      extends AbstractBeanValidator<OrganJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager;


  public final OrganValidator<OrganJPA> _validator;


  public OrganBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager,
     org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager) { 
    this.__entitatManager = __entitatManager;
    this.__organManager = __organManager;
    _validator = new OrganValidator<OrganJPA>();
  }

  public OrganBeanValidator(OrganValidator<OrganJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager,
     org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager) {
    this.__entitatManager = __entitatManager;
    this.__organManager = __organManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(OrganJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<OrganJPA> _bvr_ = new BeanValidatorResult<OrganJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __organManager);
    return _bvr_.getErrors();
  }
}
