package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.ModificacioSolicitudJPA;
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
public class ModificacioSolicitudBeanValidator 
      extends AbstractBeanValidator<ModificacioSolicitudJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IModificacioSolicitudManager __modificacioSolicitudManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager;


  public final ModificacioSolicitudValidator<ModificacioSolicitudJPA> _validator;


  public ModificacioSolicitudBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IModificacioSolicitudManager __modificacioSolicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) { 
    this.__modificacioSolicitudManager = __modificacioSolicitudManager;
    this.__organManager = __organManager;
    this.__solicitudManager = __solicitudManager;
    _validator = new ModificacioSolicitudValidator<ModificacioSolicitudJPA>();
  }

  public ModificacioSolicitudBeanValidator(ModificacioSolicitudValidator<ModificacioSolicitudJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IModificacioSolicitudManager __modificacioSolicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.IOrganManager __organManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) {
    this.__modificacioSolicitudManager = __modificacioSolicitudManager;
    this.__organManager = __organManager;
    this.__solicitudManager = __solicitudManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(ModificacioSolicitudJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<ModificacioSolicitudJPA> _bvr_ = new BeanValidatorResult<ModificacioSolicitudJPA>();
    _validator.validate(_bvr_, target, isNou, __modificacioSolicitudManager, __organManager, __solicitudManager);
    return _bvr_.getErrors();
  }
}
