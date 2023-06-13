package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
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
public class SolicitudServeiBeanValidator 
      extends AbstractBeanValidator<SolicitudServeiJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager;


  public final SolicitudServeiValidator<SolicitudServeiJPA> _validator;


  public SolicitudServeiBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager) { 
    this.__serveiManager = __serveiManager;
    this.__solicitudManager = __solicitudManager;
    this.__solicitudServeiManager = __solicitudServeiManager;
    _validator = new SolicitudServeiValidator<SolicitudServeiJPA>();
  }

  public SolicitudServeiBeanValidator(SolicitudServeiValidator<SolicitudServeiJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager) {
    this.__serveiManager = __serveiManager;
    this.__solicitudManager = __solicitudManager;
    this.__solicitudServeiManager = __solicitudServeiManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(SolicitudServeiJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<SolicitudServeiJPA> _bvr_ = new BeanValidatorResult<SolicitudServeiJPA>();
    _validator.validate(_bvr_, target, isNou, __serveiManager, __solicitudManager, __solicitudServeiManager);
    return _bvr_.getErrors();
  }
}
