package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.AreaJPA;
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
public class AreaBeanValidator 
      extends AbstractBeanValidator<AreaJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IAreaManager __areaManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager;


  public final AreaValidator<AreaJPA> _validator;


  public AreaBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IAreaManager __areaManager,
     org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager) { 
    this.__areaManager = __areaManager;
    this.__entitatManager = __entitatManager;
    _validator = new AreaValidator<AreaJPA>();
  }

  public AreaBeanValidator(AreaValidator<AreaJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IAreaManager __areaManager,
     org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager) {
    this.__areaManager = __areaManager;
    this.__entitatManager = __entitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(AreaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<AreaJPA> _bvr_ = new BeanValidatorResult<AreaJPA>();
    _validator.validate(_bvr_, target, isNou, __areaManager, __entitatManager);
    return _bvr_.getErrors();
  }
}
