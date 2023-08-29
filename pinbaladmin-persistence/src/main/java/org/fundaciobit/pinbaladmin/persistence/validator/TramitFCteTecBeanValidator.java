package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.TramitFCteTecJPA;
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
public class TramitFCteTecBeanValidator 
      extends AbstractBeanValidator<TramitFCteTecJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitFCteTecManager __tramitFCteTecManager;


  public final TramitFCteTecValidator<TramitFCteTecJPA> _validator;


  public TramitFCteTecBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitFCteTecManager __tramitFCteTecManager) { 
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitFCteTecManager = __tramitFCteTecManager;
    _validator = new TramitFCteTecValidator<TramitFCteTecJPA>();
  }

  public TramitFCteTecBeanValidator(TramitFCteTecValidator<TramitFCteTecJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitFCteTecManager __tramitFCteTecManager) {
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitFCteTecManager = __tramitFCteTecManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TramitFCteTecJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TramitFCteTecJPA> _bvr_ = new BeanValidatorResult<TramitFCteTecJPA>();
    _validator.validate(_bvr_, target, isNou, __tramitAPersAutManager, __tramitFCteTecManager);
    return _bvr_.getErrors();
  }
}
