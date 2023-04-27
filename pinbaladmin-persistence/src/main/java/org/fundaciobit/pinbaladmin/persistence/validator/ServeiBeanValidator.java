package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
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
public class ServeiBeanValidator 
      extends AbstractBeanValidator<ServeiJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IFormulariManager __formulariManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager;


  public final ServeiValidator<ServeiJPA> _validator;


  public ServeiBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager,
     org.fundaciobit.pinbaladmin.model.dao.IFormulariManager __formulariManager,
     org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager) { 
    this.__entitatServeiManager = __entitatServeiManager;
    this.__formulariManager = __formulariManager;
    this.__serveiManager = __serveiManager;
    _validator = new ServeiValidator<ServeiJPA>();
  }

  public ServeiBeanValidator(ServeiValidator<ServeiJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager,
     org.fundaciobit.pinbaladmin.model.dao.IFormulariManager __formulariManager,
     org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager) {
    this.__entitatServeiManager = __entitatServeiManager;
    this.__formulariManager = __formulariManager;
    this.__serveiManager = __serveiManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(ServeiJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<ServeiJPA> _bvr_ = new BeanValidatorResult<ServeiJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatServeiManager, __formulariManager, __serveiManager);
    return _bvr_.getErrors();
  }
}
