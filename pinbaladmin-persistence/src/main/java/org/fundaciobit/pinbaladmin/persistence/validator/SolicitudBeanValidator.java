package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
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
  protected final org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager;


  public final SolicitudValidator<SolicitudJPA> _validator;


  public SolicitudBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) { 
    this.__organManager = __organManager;
    this.__solicitudManager = __solicitudManager;
    _validator = new SolicitudValidator<SolicitudJPA>();
  }

  public SolicitudBeanValidator(SolicitudValidator<SolicitudJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) {
    this.__organManager = __organManager;
    this.__solicitudManager = __solicitudManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(SolicitudJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<SolicitudJPA> _bvr_ = new BeanValidatorResult<SolicitudJPA>();
    _validator.validate(_bvr_, target, isNou, __organManager, __solicitudManager);
    return _bvr_.getErrors();
  }
}
