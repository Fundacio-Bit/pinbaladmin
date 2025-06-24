package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.ModificacioSoliServJPA;
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
public class ModificacioSoliServBeanValidator 
      extends AbstractBeanValidator<ModificacioSoliServJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IModificacioSoliServManager __modificacioSoliServManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IModificacioSolicitudManager __modificacioSolicitudManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager;


  public final ModificacioSoliServValidator<ModificacioSoliServJPA> _validator;


  public ModificacioSoliServBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IModificacioSoliServManager __modificacioSoliServManager,
     org.fundaciobit.pinbaladmin.model.dao.IModificacioSolicitudManager __modificacioSolicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager) { 
    this.__modificacioSoliServManager = __modificacioSoliServManager;
    this.__modificacioSolicitudManager = __modificacioSolicitudManager;
    this.__solicitudServeiManager = __solicitudServeiManager;
    _validator = new ModificacioSoliServValidator<ModificacioSoliServJPA>();
  }

  public ModificacioSoliServBeanValidator(ModificacioSoliServValidator<ModificacioSoliServJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IModificacioSoliServManager __modificacioSoliServManager,
     org.fundaciobit.pinbaladmin.model.dao.IModificacioSolicitudManager __modificacioSolicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudServeiManager __solicitudServeiManager) {
    this.__modificacioSoliServManager = __modificacioSoliServManager;
    this.__modificacioSolicitudManager = __modificacioSolicitudManager;
    this.__solicitudServeiManager = __solicitudServeiManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(ModificacioSoliServJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<ModificacioSoliServJPA> _bvr_ = new BeanValidatorResult<ModificacioSoliServJPA>();
    _validator.validate(_bvr_, target, isNou, __modificacioSoliServManager, __modificacioSolicitudManager, __solicitudServeiManager);
    return _bvr_.getErrors();
  }
}
