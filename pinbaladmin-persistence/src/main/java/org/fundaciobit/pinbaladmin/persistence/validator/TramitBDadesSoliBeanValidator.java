package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPA;
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
public class TramitBDadesSoliBeanValidator 
      extends AbstractBeanValidator<TramitBDadesSoliJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitBDadesSoliManager __tramitBDadesSoliManager;


  public final TramitBDadesSoliValidator<TramitBDadesSoliJPA> _validator;


  public TramitBDadesSoliBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitBDadesSoliManager __tramitBDadesSoliManager) { 
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitBDadesSoliManager = __tramitBDadesSoliManager;
    _validator = new TramitBDadesSoliValidator<TramitBDadesSoliJPA>();
  }

  public TramitBDadesSoliBeanValidator(TramitBDadesSoliValidator<TramitBDadesSoliJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitBDadesSoliManager __tramitBDadesSoliManager) {
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitBDadesSoliManager = __tramitBDadesSoliManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TramitBDadesSoliJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TramitBDadesSoliJPA> _bvr_ = new BeanValidatorResult<TramitBDadesSoliJPA>();
    _validator.validate(_bvr_, target, isNou, __tramitAPersAutManager, __tramitBDadesSoliManager);
    return _bvr_.getErrors();
  }
}
