package org.fundaciobit.pinbaladmin.jpa.validator;

import org.fundaciobit.pinbaladmin.jpa.CampSolicitudJPA;
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
public class CampSolicitudBeanValidator 
      extends AbstractBeanValidator<CampSolicitudJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ICampFormulariManager __campFormulariManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ICampSolicitudManager __campSolicitudManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager;


  public final CampSolicitudValidator<CampSolicitudJPA> _validator;


  public CampSolicitudBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ICampFormulariManager __campFormulariManager,
     org.fundaciobit.pinbaladmin.model.dao.ICampSolicitudManager __campSolicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager) { 
    this.__campFormulariManager = __campFormulariManager;
    this.__campSolicitudManager = __campSolicitudManager;
    this.__solicitudServeiManager = __solicitudServeiManager;
    _validator = new CampSolicitudValidator<CampSolicitudJPA>();
  }

  public CampSolicitudBeanValidator(CampSolicitudValidator<CampSolicitudJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ICampFormulariManager __campFormulariManager,
     org.fundaciobit.pinbaladmin.model.dao.ICampSolicitudManager __campSolicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager) {
    this.__campFormulariManager = __campFormulariManager;
    this.__campSolicitudManager = __campSolicitudManager;
    this.__solicitudServeiManager = __solicitudServeiManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(CampSolicitudJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<CampSolicitudJPA> _bvr_ = new BeanValidatorResult<CampSolicitudJPA>();
    _validator.validate(_bvr_, target, isNou, __campFormulariManager, __campSolicitudManager, __solicitudServeiManager);
    return _bvr_.getErrors();
  }
}
