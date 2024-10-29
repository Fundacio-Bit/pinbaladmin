package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
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
public class PinfoBeanValidator 
      extends AbstractBeanValidator<PinfoJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IPinfoManager __pinfoManager;


  public final PinfoValidator<PinfoJPA> _validator;


  public PinfoBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager,
     org.fundaciobit.pinbaladmin.model.dao.IPinfoManager __pinfoManager) { 
    this.__incidenciaTecnicaManager = __incidenciaTecnicaManager;
    this.__pinfoManager = __pinfoManager;
    _validator = new PinfoValidator<PinfoJPA>();
  }

  public PinfoBeanValidator(PinfoValidator<PinfoJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager,
     org.fundaciobit.pinbaladmin.model.dao.IPinfoManager __pinfoManager) {
    this.__incidenciaTecnicaManager = __incidenciaTecnicaManager;
    this.__pinfoManager = __pinfoManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PinfoJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PinfoJPA> _bvr_ = new BeanValidatorResult<PinfoJPA>();
    _validator.validate(_bvr_, target, isNou, __incidenciaTecnicaManager, __pinfoManager);
    return _bvr_.getErrors();
  }
}
