package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
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
public class InfoMadridBeanValidator 
      extends AbstractBeanValidator<InfoMadridJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IInfoMadridManager __infoMadridManager;


  public final InfoMadridValidator<InfoMadridJPA> _validator;


  public InfoMadridBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IInfoMadridManager __infoMadridManager) { 
    this.__infoMadridManager = __infoMadridManager;
    _validator = new InfoMadridValidator<InfoMadridJPA>();
  }

  public InfoMadridBeanValidator(InfoMadridValidator<InfoMadridJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IInfoMadridManager __infoMadridManager) {
    this.__infoMadridManager = __infoMadridManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(InfoMadridJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<InfoMadridJPA> _bvr_ = new BeanValidatorResult<InfoMadridJPA>();
    _validator.validate(_bvr_, target, isNou, __infoMadridManager);
    return _bvr_.getErrors();
  }
}
