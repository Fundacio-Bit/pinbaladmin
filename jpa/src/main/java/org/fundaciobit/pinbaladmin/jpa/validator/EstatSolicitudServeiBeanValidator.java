package org.fundaciobit.pinbaladmin.jpa.validator;

import org.fundaciobit.pinbaladmin.jpa.EstatSolicitudServeiJPA;
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
public class EstatSolicitudServeiBeanValidator 
      extends AbstractBeanValidator<EstatSolicitudServeiJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudServeiManager __estatSolicitudServeiManager;


  public final EstatSolicitudServeiValidator<EstatSolicitudServeiJPA> _validator;


  public EstatSolicitudServeiBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudServeiManager __estatSolicitudServeiManager) { 
    this.__estatSolicitudServeiManager = __estatSolicitudServeiManager;
    _validator = new EstatSolicitudServeiValidator<EstatSolicitudServeiJPA>();
  }

  public EstatSolicitudServeiBeanValidator(EstatSolicitudServeiValidator<EstatSolicitudServeiJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudServeiManager __estatSolicitudServeiManager) {
    this.__estatSolicitudServeiManager = __estatSolicitudServeiManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EstatSolicitudServeiJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EstatSolicitudServeiJPA> _bvr_ = new BeanValidatorResult<EstatSolicitudServeiJPA>();
    _validator.validate(_bvr_, target, isNou, __estatSolicitudServeiManager);
    return _bvr_.getErrors();
  }
}
