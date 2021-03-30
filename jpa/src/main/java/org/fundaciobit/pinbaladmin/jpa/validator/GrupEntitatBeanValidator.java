package org.fundaciobit.pinbaladmin.jpa.validator;

import org.fundaciobit.pinbaladmin.jpa.GrupEntitatJPA;
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
public class GrupEntitatBeanValidator 
      extends AbstractBeanValidator<GrupEntitatJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager __grupEntitatManager;


  public final GrupEntitatValidator<GrupEntitatJPA> _validator;


  public GrupEntitatBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager __grupEntitatManager) { 
    this.__grupEntitatManager = __grupEntitatManager;
    _validator = new GrupEntitatValidator<GrupEntitatJPA>();
  }

  public GrupEntitatBeanValidator(GrupEntitatValidator<GrupEntitatJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager __grupEntitatManager) {
    this.__grupEntitatManager = __grupEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(GrupEntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<GrupEntitatJPA> _bvr_ = new BeanValidatorResult<GrupEntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __grupEntitatManager);
    return _bvr_.getErrors();
  }
}
