package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.EntitatServeiJPA;
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
public class EntitatServeiBeanValidator 
      extends AbstractBeanValidator<EntitatServeiJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager;


  public final EntitatServeiValidator<EntitatServeiJPA> _validator;


  public EntitatServeiBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager) { 
    this.__entitatServeiManager = __entitatServeiManager;
    _validator = new EntitatServeiValidator<EntitatServeiJPA>();
  }

  public EntitatServeiBeanValidator(EntitatServeiValidator<EntitatServeiJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager) {
    this.__entitatServeiManager = __entitatServeiManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EntitatServeiJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EntitatServeiJPA> _bvr_ = new BeanValidatorResult<EntitatServeiJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatServeiManager);
    return _bvr_.getErrors();
  }
}
