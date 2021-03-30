package org.fundaciobit.pinbaladmin.jpa.validator;

import org.fundaciobit.pinbaladmin.jpa.DocumentCedentJPA;
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
public class DocumentCedentBeanValidator 
      extends AbstractBeanValidator<DocumentCedentJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IDocumentCedentManager __documentCedentManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager;


  public final DocumentCedentValidator<DocumentCedentJPA> _validator;


  public DocumentCedentBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IDocumentCedentManager __documentCedentManager,
     org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager) { 
    this.__documentCedentManager = __documentCedentManager;
    this.__entitatServeiManager = __entitatServeiManager;
    _validator = new DocumentCedentValidator<DocumentCedentJPA>();
  }

  public DocumentCedentBeanValidator(DocumentCedentValidator<DocumentCedentJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IDocumentCedentManager __documentCedentManager,
     org.fundaciobit.pinbaladmin.model.dao.IEntitatServeiManager __entitatServeiManager) {
    this.__documentCedentManager = __documentCedentManager;
    this.__entitatServeiManager = __entitatServeiManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(DocumentCedentJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<DocumentCedentJPA> _bvr_ = new BeanValidatorResult<DocumentCedentJPA>();
    _validator.validate(_bvr_, target, isNou, __documentCedentManager, __entitatServeiManager);
    return _bvr_.getErrors();
  }
}
