package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.PINFOJPA;
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
public class PINFOBeanValidator 
      extends AbstractBeanValidator<PINFOJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IPINFOManager __pINFOManager;


  public final PINFOValidator<PINFOJPA> _validator;


  public PINFOBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager,
     org.fundaciobit.pinbaladmin.model.dao.IPINFOManager __pINFOManager) { 
    this.__incidenciaTecnicaManager = __incidenciaTecnicaManager;
    this.__pINFOManager = __pINFOManager;
    _validator = new PINFOValidator<PINFOJPA>();
  }

  public PINFOBeanValidator(PINFOValidator<PINFOJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager,
     org.fundaciobit.pinbaladmin.model.dao.IPINFOManager __pINFOManager) {
    this.__incidenciaTecnicaManager = __incidenciaTecnicaManager;
    this.__pINFOManager = __pINFOManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PINFOJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PINFOJPA> _bvr_ = new BeanValidatorResult<PINFOJPA>();
    _validator.validate(_bvr_, target, isNou, __incidenciaTecnicaManager, __pINFOManager);
    return _bvr_.getErrors();
  }
}
