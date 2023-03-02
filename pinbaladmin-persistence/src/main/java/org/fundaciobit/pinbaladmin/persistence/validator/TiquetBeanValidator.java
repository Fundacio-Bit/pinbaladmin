package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.TiquetJPA;
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
public class TiquetBeanValidator 
      extends AbstractBeanValidator<TiquetJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEstatTiquetManager __estatTiquetManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITipusTiquetManager __tipusTiquetManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ITiquetManager __tiquetManager;


  public final TiquetValidator<TiquetJPA> _validator;


  public TiquetBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEstatTiquetManager __estatTiquetManager,
     org.fundaciobit.pinbaladmin.model.dao.ITipusTiquetManager __tipusTiquetManager,
     org.fundaciobit.pinbaladmin.model.dao.ITiquetManager __tiquetManager) { 
    this.__estatTiquetManager = __estatTiquetManager;
    this.__tipusTiquetManager = __tipusTiquetManager;
    this.__tiquetManager = __tiquetManager;
    _validator = new TiquetValidator<TiquetJPA>();
  }

  public TiquetBeanValidator(TiquetValidator<TiquetJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEstatTiquetManager __estatTiquetManager,
     org.fundaciobit.pinbaladmin.model.dao.ITipusTiquetManager __tipusTiquetManager,
     org.fundaciobit.pinbaladmin.model.dao.ITiquetManager __tiquetManager) {
    this.__estatTiquetManager = __estatTiquetManager;
    this.__tipusTiquetManager = __tipusTiquetManager;
    this.__tiquetManager = __tiquetManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TiquetJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TiquetJPA> _bvr_ = new BeanValidatorResult<TiquetJPA>();
    _validator.validate(_bvr_, target, isNou, __estatTiquetManager, __tipusTiquetManager, __tiquetManager);
    return _bvr_.getErrors();
  }
}
