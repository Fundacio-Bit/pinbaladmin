package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.EntitatJPA;
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
public class EntitatBeanValidator 
      extends AbstractBeanValidator<EntitatJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager __grupEntitatManager;


  public final EntitatValidator<EntitatJPA> _validator;


  public EntitatBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager,
     org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager __grupEntitatManager) { 
    this.__entitatManager = __entitatManager;
    this.__grupEntitatManager = __grupEntitatManager;
    _validator = new EntitatValidator<EntitatJPA>();
  }

  public EntitatBeanValidator(EntitatValidator<EntitatJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager,
     org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager __grupEntitatManager) {
    this.__entitatManager = __entitatManager;
    this.__grupEntitatManager = __grupEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EntitatJPA> _bvr_ = new BeanValidatorResult<EntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __grupEntitatManager);
    return _bvr_.getErrors();
  }
}
