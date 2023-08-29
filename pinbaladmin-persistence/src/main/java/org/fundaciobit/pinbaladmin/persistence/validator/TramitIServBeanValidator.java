package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.TramitIServJPA;
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
public class TramitIServBeanValidator 
      extends AbstractBeanValidator<TramitIServJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitIServManager __tramitIServManager;


  public final TramitIServValidator<TramitIServJPA> _validator;


  public TramitIServBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitIServManager __tramitIServManager) { 
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitIServManager = __tramitIServManager;
    _validator = new TramitIServValidator<TramitIServJPA>();
  }

  public TramitIServBeanValidator(TramitIServValidator<TramitIServJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitIServManager __tramitIServManager) {
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitIServManager = __tramitIServManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TramitIServJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TramitIServJPA> _bvr_ = new BeanValidatorResult<TramitIServJPA>();
    _validator.validate(_bvr_, target, isNou, __tramitAPersAutManager, __tramitIServManager);
    return _bvr_.getErrors();
  }
}
