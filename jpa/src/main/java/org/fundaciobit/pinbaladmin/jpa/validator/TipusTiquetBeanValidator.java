package org.fundaciobit.pinbaladmin.jpa.validator;

import org.fundaciobit.pinbaladmin.jpa.TipusTiquetJPA;
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
public class TipusTiquetBeanValidator 
      extends AbstractBeanValidator<TipusTiquetJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ITipusTiquetManager __tipusTiquetManager;


  public final TipusTiquetValidator<TipusTiquetJPA> _validator;


  public TipusTiquetBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ITipusTiquetManager __tipusTiquetManager) { 
    this.__tipusTiquetManager = __tipusTiquetManager;
    _validator = new TipusTiquetValidator<TipusTiquetJPA>();
  }

  public TipusTiquetBeanValidator(TipusTiquetValidator<TipusTiquetJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ITipusTiquetManager __tipusTiquetManager) {
    this.__tipusTiquetManager = __tipusTiquetManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TipusTiquetJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TipusTiquetJPA> _bvr_ = new BeanValidatorResult<TipusTiquetJPA>();
    _validator.validate(_bvr_, target, isNou, __tipusTiquetManager);
    return _bvr_.getErrors();
  }
}
