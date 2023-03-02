package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.EstatServeiJPA;
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
public class EstatServeiBeanValidator 
      extends AbstractBeanValidator<EstatServeiJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEstatServeiManager __estatServeiManager;


  public final EstatServeiValidator<EstatServeiJPA> _validator;


  public EstatServeiBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEstatServeiManager __estatServeiManager) { 
    this.__estatServeiManager = __estatServeiManager;
    _validator = new EstatServeiValidator<EstatServeiJPA>();
  }

  public EstatServeiBeanValidator(EstatServeiValidator<EstatServeiJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEstatServeiManager __estatServeiManager) {
    this.__estatServeiManager = __estatServeiManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EstatServeiJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EstatServeiJPA> _bvr_ = new BeanValidatorResult<EstatServeiJPA>();
    _validator.validate(_bvr_, target, isNou, __estatServeiManager);
    return _bvr_.getErrors();
  }
}
