package org.fundaciobit.pinbaladmin.jpa.validator;

import org.fundaciobit.pinbaladmin.jpa.EventJPA;
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
public class EventBeanValidator 
      extends AbstractBeanValidator<EventJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IEventManager __eventManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager;


  public final EventValidator<EventJPA> _validator;


  public EventBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IEventManager __eventManager,
     org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) { 
    this.__eventManager = __eventManager;
    this.__incidenciaTecnicaManager = __incidenciaTecnicaManager;
    this.__solicitudManager = __solicitudManager;
    _validator = new EventValidator<EventJPA>();
  }

  public EventBeanValidator(EventValidator<EventJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IEventManager __eventManager,
     org.fundaciobit.pinbaladmin.model.dao.IIncidenciaTecnicaManager __incidenciaTecnicaManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) {
    this.__eventManager = __eventManager;
    this.__incidenciaTecnicaManager = __incidenciaTecnicaManager;
    this.__solicitudManager = __solicitudManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(EventJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<EventJPA> _bvr_ = new BeanValidatorResult<EventJPA>();
    _validator.validate(_bvr_, target, isNou, __eventManager, __incidenciaTecnicaManager, __solicitudManager);
    return _bvr_.getErrors();
  }
}
