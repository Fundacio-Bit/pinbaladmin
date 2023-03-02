package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.OperadorJPA;
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
public class OperadorBeanValidator 
      extends AbstractBeanValidator<OperadorJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IOperadorManager __operadorManager;


  public final OperadorValidator<OperadorJPA> _validator;


  public OperadorBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IOperadorManager __operadorManager) { 
    this.__operadorManager = __operadorManager;
    _validator = new OperadorValidator<OperadorJPA>();
  }

  public OperadorBeanValidator(OperadorValidator<OperadorJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IOperadorManager __operadorManager) {
    this.__operadorManager = __operadorManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(OperadorJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<OperadorJPA> _bvr_ = new BeanValidatorResult<OperadorJPA>();
    _validator.validate(_bvr_, target, isNou, __operadorManager);
    return _bvr_.getErrors();
  }
}
