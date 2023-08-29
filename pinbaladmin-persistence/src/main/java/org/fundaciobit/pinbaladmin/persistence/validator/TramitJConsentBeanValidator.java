package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.TramitJConsentJPA;
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
public class TramitJConsentBeanValidator 
      extends AbstractBeanValidator<TramitJConsentJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitJConsentManager __tramitJConsentManager;


  public final TramitJConsentValidator<TramitJConsentJPA> _validator;


  public TramitJConsentBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitJConsentManager __tramitJConsentManager) { 
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitJConsentManager = __tramitJConsentManager;
    _validator = new TramitJConsentValidator<TramitJConsentJPA>();
  }

  public TramitJConsentBeanValidator(TramitJConsentValidator<TramitJConsentJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitJConsentManager __tramitJConsentManager) {
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitJConsentManager = __tramitJConsentManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TramitJConsentJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TramitJConsentJPA> _bvr_ = new BeanValidatorResult<TramitJConsentJPA>();
    _validator.validate(_bvr_, target, isNou, __tramitAPersAutManager, __tramitJConsentManager);
    return _bvr_.getErrors();
  }
}
