package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
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
public class TramitAPersAutBeanValidator 
      extends AbstractBeanValidator<TramitAPersAutJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager;


  public final TramitAPersAutValidator<TramitAPersAutJPA> _validator;


  public TramitAPersAutBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager) { 
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    _validator = new TramitAPersAutValidator<TramitAPersAutJPA>();
  }

  public TramitAPersAutBeanValidator(TramitAPersAutValidator<TramitAPersAutJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager) {
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TramitAPersAutJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TramitAPersAutJPA> _bvr_ = new BeanValidatorResult<TramitAPersAutJPA>();
    _validator.validate(_bvr_, target, isNou, __tramitAPersAutManager);
    return _bvr_.getErrors();
  }
}
