package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.TramitECteAudJPA;
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
public class TramitECteAudBeanValidator 
      extends AbstractBeanValidator<TramitECteAudJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitECteAudManager __tramitECteAudManager;


  public final TramitECteAudValidator<TramitECteAudJPA> _validator;


  public TramitECteAudBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitECteAudManager __tramitECteAudManager) { 
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitECteAudManager = __tramitECteAudManager;
    _validator = new TramitECteAudValidator<TramitECteAudJPA>();
  }

  public TramitECteAudBeanValidator(TramitECteAudValidator<TramitECteAudJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitECteAudManager __tramitECteAudManager) {
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitECteAudManager = __tramitECteAudManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TramitECteAudJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TramitECteAudJPA> _bvr_ = new BeanValidatorResult<TramitECteAudJPA>();
    _validator.validate(_bvr_, target, isNou, __tramitAPersAutManager, __tramitECteAudManager);
    return _bvr_.getErrors();
  }
}
