package org.fundaciobit.pinbaladmin.jpa.validator;

import org.fundaciobit.pinbaladmin.jpa.SolicitudJPA;
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
public class SolicitudBeanValidator 
      extends AbstractBeanValidator<SolicitudJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IDepartamentManager __departamentManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudManager __estatSolicitudManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager;


  public final SolicitudValidator<SolicitudJPA> _validator;


  public SolicitudBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IDepartamentManager __departamentManager,
     org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudManager __estatSolicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) { 
    this.__departamentManager = __departamentManager;
    this.__estatSolicitudManager = __estatSolicitudManager;
    this.__solicitudManager = __solicitudManager;
    _validator = new SolicitudValidator<SolicitudJPA>();
  }

  public SolicitudBeanValidator(SolicitudValidator<SolicitudJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IDepartamentManager __departamentManager,
     org.fundaciobit.pinbaladmin.model.dao.IEstatSolicitudManager __estatSolicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) {
    this.__departamentManager = __departamentManager;
    this.__estatSolicitudManager = __estatSolicitudManager;
    this.__solicitudManager = __solicitudManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(SolicitudJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<SolicitudJPA> _bvr_ = new BeanValidatorResult<SolicitudJPA>();
    _validator.validate(_bvr_, target, isNou, __departamentManager, __estatSolicitudManager, __solicitudManager);
    return _bvr_.getErrors();
  }
}
