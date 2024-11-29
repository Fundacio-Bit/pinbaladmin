package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA;
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
public class IncidenciaTecnicaBeanValidator 
      extends AbstractBeanValidator<IncidenciaTecnicaJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager;


  public final IncidenciaTecnicaValidator<IncidenciaTecnicaJPA> _validator;


  public IncidenciaTecnicaBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager,
     org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager) { 
    this.__incidenciaTecnicaManager = __incidenciaTecnicaManager;
    this.__organManager = __organManager;
    _validator = new IncidenciaTecnicaValidator<IncidenciaTecnicaJPA>();
  }

  public IncidenciaTecnicaBeanValidator(IncidenciaTecnicaValidator<IncidenciaTecnicaJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager,
     org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager) {
    this.__incidenciaTecnicaManager = __incidenciaTecnicaManager;
    this.__organManager = __organManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(IncidenciaTecnicaJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<IncidenciaTecnicaJPA> _bvr_ = new BeanValidatorResult<IncidenciaTecnicaJPA>();
    _validator.validate(_bvr_, target, isNou, __incidenciaTecnicaManager, __organManager);
    return _bvr_.getErrors();
  }
}
