package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.TramitDCteAutJPA;
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
public class TramitDCteAutBeanValidator 
      extends AbstractBeanValidator<TramitDCteAutJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitDCteAutManager __tramitDCteAutManager;


  public final TramitDCteAutValidator<TramitDCteAutJPA> _validator;


  public TramitDCteAutBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitDCteAutManager __tramitDCteAutManager) { 
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitDCteAutManager = __tramitDCteAutManager;
    _validator = new TramitDCteAutValidator<TramitDCteAutJPA>();
  }

  public TramitDCteAutBeanValidator(TramitDCteAutValidator<TramitDCteAutJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitDCteAutManager __tramitDCteAutManager) {
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitDCteAutManager = __tramitDCteAutManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TramitDCteAutJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TramitDCteAutJPA> _bvr_ = new BeanValidatorResult<TramitDCteAutJPA>();
    _validator.validate(_bvr_, target, isNou, __tramitAPersAutManager, __tramitDCteAutManager);
    return _bvr_.getErrors();
  }
}
