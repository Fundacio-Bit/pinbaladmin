package org.fundaciobit.pinbaladmin.jpa.validator;

import org.fundaciobit.pinbaladmin.jpa.GrupEntitatCedentJPA;
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
public class GrupEntitatCedentBeanValidator 
      extends AbstractBeanValidator<GrupEntitatCedentJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager __grupEntitatManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatCedentManager __grupEntitatCedentManager;


  public final GrupEntitatCedentValidator<GrupEntitatCedentJPA> _validator;


  public GrupEntitatCedentBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager,
     org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager __grupEntitatManager,
     org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatCedentManager __grupEntitatCedentManager) { 
    this.__entitatServeiManager = __entitatServeiManager;
    this.__grupEntitatManager = __grupEntitatManager;
    this.__grupEntitatCedentManager = __grupEntitatCedentManager;
    _validator = new GrupEntitatCedentValidator<GrupEntitatCedentJPA>();
  }

  public GrupEntitatCedentBeanValidator(GrupEntitatCedentValidator<GrupEntitatCedentJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager,
     org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatManager __grupEntitatManager,
     org.fundaciobit.pinbaladmin.model.dao.IGrupEntitatCedentManager __grupEntitatCedentManager) {
    this.__entitatServeiManager = __entitatServeiManager;
    this.__grupEntitatManager = __grupEntitatManager;
    this.__grupEntitatCedentManager = __grupEntitatCedentManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(GrupEntitatCedentJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<GrupEntitatCedentJPA> _bvr_ = new BeanValidatorResult<GrupEntitatCedentJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatServeiManager, __grupEntitatManager, __grupEntitatCedentManager);
    return _bvr_.getErrors();
  }
}
