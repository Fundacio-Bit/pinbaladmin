package org.fundaciobit.pinbaladmin.jpa.validator;

import org.fundaciobit.pinbaladmin.jpa.CampFormulariJPA;
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
public class CampFormulariBeanValidator 
      extends AbstractBeanValidator<CampFormulariJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.ICampFormulariManager __campFormulariManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IFormulariManager __formulariManager;


  public final CampFormulariValidator<CampFormulariJPA> _validator;


  public CampFormulariBeanValidator(org.fundaciobit.pinbaladmin.model.dao.ICampFormulariManager __campFormulariManager,
     org.fundaciobit.pinbaladmin.model.dao.IFormulariManager __formulariManager) { 
    this.__campFormulariManager = __campFormulariManager;
    this.__formulariManager = __formulariManager;
    _validator = new CampFormulariValidator<CampFormulariJPA>();
  }

  public CampFormulariBeanValidator(CampFormulariValidator<CampFormulariJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.ICampFormulariManager __campFormulariManager,
     org.fundaciobit.pinbaladmin.model.dao.IFormulariManager __formulariManager) {
    this.__campFormulariManager = __campFormulariManager;
    this.__formulariManager = __formulariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(CampFormulariJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<CampFormulariJPA> _bvr_ = new BeanValidatorResult<CampFormulariJPA>();
    _validator.validate(_bvr_, target, isNou, __campFormulariManager, __formulariManager);
    return _bvr_.getErrors();
  }
}
