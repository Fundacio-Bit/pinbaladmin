package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.ContacteJPA;
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
public class ContacteBeanValidator 
      extends AbstractBeanValidator<ContacteJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IContacteManager __contacteManager;


  public final ContacteValidator<ContacteJPA> _validator;


  public ContacteBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IContacteManager __contacteManager) { 
    this.__contacteManager = __contacteManager;
    _validator = new ContacteValidator<ContacteJPA>();
  }

  public ContacteBeanValidator(ContacteValidator<ContacteJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IContacteManager __contacteManager) {
    this.__contacteManager = __contacteManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(ContacteJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<ContacteJPA> _bvr_ = new BeanValidatorResult<ContacteJPA>();
    _validator.validate(_bvr_, target, isNou, __contacteManager);
    return _bvr_.getErrors();
  }
}
