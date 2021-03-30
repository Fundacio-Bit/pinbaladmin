package org.fundaciobit.pinbaladmin.jpa.validator;

import org.fundaciobit.pinbaladmin.jpa.DocumentEntitatJPA;
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
public class DocumentEntitatBeanValidator 
      extends AbstractBeanValidator<DocumentEntitatJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IDocumentEntitatManager __documentEntitatManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager;


  public final DocumentEntitatValidator<DocumentEntitatJPA> _validator;


  public DocumentEntitatBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IDocumentEntitatManager __documentEntitatManager,
     org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager) { 
    this.__documentEntitatManager = __documentEntitatManager;
    this.__entitatManager = __entitatManager;
    _validator = new DocumentEntitatValidator<DocumentEntitatJPA>();
  }

  public DocumentEntitatBeanValidator(DocumentEntitatValidator<DocumentEntitatJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IDocumentEntitatManager __documentEntitatManager,
     org.fundaciobit.pinbaladmin.model.dao.IEntitatManager __entitatManager) {
    this.__documentEntitatManager = __documentEntitatManager;
    this.__entitatManager = __entitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(DocumentEntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<DocumentEntitatJPA> _bvr_ = new BeanValidatorResult<DocumentEntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __documentEntitatManager, __entitatManager);
    return _bvr_.getErrors();
  }
}
