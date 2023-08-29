package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.TramitHProcJPA;
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
public class TramitHProcBeanValidator 
      extends AbstractBeanValidator<TramitHProcJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitHProcManager __tramitHProcManager;


  public final TramitHProcValidator<TramitHProcJPA> _validator;


  public TramitHProcBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitHProcManager __tramitHProcManager) { 
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitHProcManager = __tramitHProcManager;
    _validator = new TramitHProcValidator<TramitHProcJPA>();
  }

  public TramitHProcBeanValidator(TramitHProcValidator<TramitHProcJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitHProcManager __tramitHProcManager) {
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitHProcManager = __tramitHProcManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TramitHProcJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TramitHProcJPA> _bvr_ = new BeanValidatorResult<TramitHProcJPA>();
    _validator.validate(_bvr_, target, isNou, __tramitAPersAutManager, __tramitHProcManager);
    return _bvr_.getErrors();
  }
}
