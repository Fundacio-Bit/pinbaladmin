package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.EstatSolicitudJPA;
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
public class EstatSolicitudBeanValidator 
      extends AbstractBeanValidator<EstatSolicitudJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudManager __estatSolicitudManager;


  public final EstatSolicitudValidator<EstatSolicitudJPA> _validator;


  public EstatSolicitudBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudManager __estatSolicitudManager) { 
    this.__estatSolicitudManager = __estatSolicitudManager;
    _validator = new EstatSolicitudValidator<EstatSolicitudJPA>();
  }

  public EstatSolicitudBeanValidator(EstatSolicitudValidator<EstatSolicitudJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudManager __estatSolicitudManager) {
    this.__estatSolicitudManager = __estatSolicitudManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EstatSolicitudJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EstatSolicitudJPA> _bvr_ = new BeanValidatorResult<EstatSolicitudJPA>();
    _validator.validate(_bvr_, target, isNou, __estatSolicitudManager);
    return _bvr_.getErrors();
  }
}
