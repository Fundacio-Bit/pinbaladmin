package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
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
public class DocumentBeanValidator 
      extends AbstractBeanValidator<DocumentJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IDocumentManager __documentManager;


  public final DocumentValidator<DocumentJPA> _validator;


  public DocumentBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IDocumentManager __documentManager) { 
    this.__documentManager = __documentManager;
    _validator = new DocumentValidator<DocumentJPA>();
  }

  public DocumentBeanValidator(DocumentValidator<DocumentJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IDocumentManager __documentManager) {
    this.__documentManager = __documentManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(DocumentJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<DocumentJPA> _bvr_ = new BeanValidatorResult<DocumentJPA>();
    _validator.validate(_bvr_, target, isNou, __documentManager);
    return _bvr_.getErrors();
  }
}
