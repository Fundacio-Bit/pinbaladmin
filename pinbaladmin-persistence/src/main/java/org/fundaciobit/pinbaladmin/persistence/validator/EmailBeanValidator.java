package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.EmailJPA;
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
public class EmailBeanValidator 
      extends AbstractBeanValidator<EmailJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEmailManager __emailManager;


  public final EmailValidator<EmailJPA> _validator;


  public EmailBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEmailManager __emailManager) { 
    this.__emailManager = __emailManager;
    _validator = new EmailValidator<EmailJPA>();
  }

  public EmailBeanValidator(EmailValidator<EmailJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEmailManager __emailManager) {
    this.__emailManager = __emailManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EmailJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EmailJPA> _bvr_ = new BeanValidatorResult<EmailJPA>();
    _validator.validate(_bvr_, target, isNou, __emailManager);
    return _bvr_.getErrors();
  }
}
