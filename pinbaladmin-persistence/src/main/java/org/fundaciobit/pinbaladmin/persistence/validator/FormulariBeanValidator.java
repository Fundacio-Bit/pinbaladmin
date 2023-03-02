package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.FormulariJPA;
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
public class FormulariBeanValidator 
      extends AbstractBeanValidator<FormulariJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IFormulariManager __formulariManager;


  public final FormulariValidator<FormulariJPA> _validator;


  public FormulariBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IFormulariManager __formulariManager) { 
    this.__formulariManager = __formulariManager;
    _validator = new FormulariValidator<FormulariJPA>();
  }

  public FormulariBeanValidator(FormulariValidator<FormulariJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IFormulariManager __formulariManager) {
    this.__formulariManager = __formulariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(FormulariJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<FormulariJPA> _bvr_ = new BeanValidatorResult<FormulariJPA>();
    _validator.validate(_bvr_, target, isNou, __formulariManager);
    return _bvr_.getErrors();
  }
}
