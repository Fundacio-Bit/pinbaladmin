package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitJPA;
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
public class TramitGDadesTitBeanValidator 
      extends AbstractBeanValidator<TramitGDadesTitJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitGDadesTitManager __tramitGDadesTitManager;


  public final TramitGDadesTitValidator<TramitGDadesTitJPA> _validator;


  public TramitGDadesTitBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitGDadesTitManager __tramitGDadesTitManager) { 
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitGDadesTitManager = __tramitGDadesTitManager;
    _validator = new TramitGDadesTitValidator<TramitGDadesTitJPA>();
  }

  public TramitGDadesTitBeanValidator(TramitGDadesTitValidator<TramitGDadesTitJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitGDadesTitManager __tramitGDadesTitManager) {
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitGDadesTitManager = __tramitGDadesTitManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TramitGDadesTitJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TramitGDadesTitJPA> _bvr_ = new BeanValidatorResult<TramitGDadesTitJPA>();
    _validator.validate(_bvr_, target, isNou, __tramitAPersAutManager, __tramitGDadesTitManager);
    return _bvr_.getErrors();
  }
}
