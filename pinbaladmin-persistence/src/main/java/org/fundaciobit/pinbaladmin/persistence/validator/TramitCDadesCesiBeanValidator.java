package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.TramitCDadesCesiJPA;
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
public class TramitCDadesCesiBeanValidator 
      extends AbstractBeanValidator<TramitCDadesCesiJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITramitCDadesCesiManager __tramitCDadesCesiManager;


  public final TramitCDadesCesiValidator<TramitCDadesCesiJPA> _validator;


  public TramitCDadesCesiBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitCDadesCesiManager __tramitCDadesCesiManager) { 
    this.__organManager = __organManager;
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitCDadesCesiManager = __tramitCDadesCesiManager;
    _validator = new TramitCDadesCesiValidator<TramitCDadesCesiJPA>();
  }

  public TramitCDadesCesiBeanValidator(TramitCDadesCesiValidator<TramitCDadesCesiJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitAPersAutManager __tramitAPersAutManager,
     org.fundaciobit.pinbaladmin.model.dao.ITramitCDadesCesiManager __tramitCDadesCesiManager) {
    this.__organManager = __organManager;
    this.__tramitAPersAutManager = __tramitAPersAutManager;
    this.__tramitCDadesCesiManager = __tramitCDadesCesiManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TramitCDadesCesiJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TramitCDadesCesiJPA> _bvr_ = new BeanValidatorResult<TramitCDadesCesiJPA>();
    _validator.validate(_bvr_, target, isNou, __organManager, __tramitAPersAutManager, __tramitCDadesCesiManager);
    return _bvr_.getErrors();
  }
}
