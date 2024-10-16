package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;
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
public class PinfoDataBeanValidator 
      extends AbstractBeanValidator<PinfoDataJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IPINFOManager __pINFOManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IPinfoDataManager __pinfoDataManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager;


  public final PinfoDataValidator<PinfoDataJPA> _validator;


  public PinfoDataBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IPINFOManager __pINFOManager,
     org.fundaciobit.pinbaladmin.model.dao.IPinfoDataManager __pinfoDataManager,
     org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) { 
    this.__pINFOManager = __pINFOManager;
    this.__pinfoDataManager = __pinfoDataManager;
    this.__serveiManager = __serveiManager;
    this.__solicitudManager = __solicitudManager;
    _validator = new PinfoDataValidator<PinfoDataJPA>();
  }

  public PinfoDataBeanValidator(PinfoDataValidator<PinfoDataJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IPINFOManager __pINFOManager,
     org.fundaciobit.pinbaladmin.model.dao.IPinfoDataManager __pinfoDataManager,
     org.fundaciobit.pinbaladmin.model.dao.IServeiManager __serveiManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) {
    this.__pINFOManager = __pINFOManager;
    this.__pinfoDataManager = __pinfoDataManager;
    this.__serveiManager = __serveiManager;
    this.__solicitudManager = __solicitudManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PinfoDataJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PinfoDataJPA> _bvr_ = new BeanValidatorResult<PinfoDataJPA>();
    _validator.validate(_bvr_, target, isNou, __pINFOManager, __pinfoDataManager, __serveiManager, __solicitudManager);
    return _bvr_.getErrors();
  }
}
