package org.fundaciobit.pinbaladmin.jpa.validator;

import org.fundaciobit.pinbaladmin.jpa.EstatTiquetJPA;
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
public class EstatTiquetBeanValidator 
      extends AbstractBeanValidator<EstatTiquetJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEstatTiquetManager __estatTiquetManager;


  public final EstatTiquetValidator<EstatTiquetJPA> _validator;


  public EstatTiquetBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEstatTiquetManager __estatTiquetManager) { 
    this.__estatTiquetManager = __estatTiquetManager;
    _validator = new EstatTiquetValidator<EstatTiquetJPA>();
  }

  public EstatTiquetBeanValidator(EstatTiquetValidator<EstatTiquetJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEstatTiquetManager __estatTiquetManager) {
    this.__estatTiquetManager = __estatTiquetManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EstatTiquetJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EstatTiquetJPA> _bvr_ = new BeanValidatorResult<EstatTiquetJPA>();
    _validator.validate(_bvr_, target, isNou, __estatTiquetManager);
    return _bvr_.getErrors();
  }
}
