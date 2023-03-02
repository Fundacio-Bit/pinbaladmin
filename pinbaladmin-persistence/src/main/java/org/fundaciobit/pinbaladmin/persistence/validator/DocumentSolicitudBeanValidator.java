package org.fundaciobit.pinbaladmin.persistence.validator;

import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
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
public class DocumentSolicitudBeanValidator 
      extends AbstractBeanValidator<DocumentSolicitudJPA> {


  // EJB's
  protected final org.fundaciobit.pinbaladmin.model.dao.IDocumentManager __documentManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.IDocumentSolicitudManager __documentSolicitudManager;

  protected final org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager;


  public final DocumentSolicitudValidator<DocumentSolicitudJPA> _validator;


  public DocumentSolicitudBeanValidator(org.fundaciobit.pinbaladmin.model.dao.IDocumentManager __documentManager,
     org.fundaciobit.pinbaladmin.model.dao.IDocumentSolicitudManager __documentSolicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) { 
    this.__documentManager = __documentManager;
    this.__documentSolicitudManager = __documentSolicitudManager;
    this.__solicitudManager = __solicitudManager;
    _validator = new DocumentSolicitudValidator<DocumentSolicitudJPA>();
  }

  public DocumentSolicitudBeanValidator(DocumentSolicitudValidator<DocumentSolicitudJPA> _validator,
     org.fundaciobit.pinbaladmin.model.dao.IDocumentManager __documentManager,
     org.fundaciobit.pinbaladmin.model.dao.IDocumentSolicitudManager __documentSolicitudManager,
     org.fundaciobit.pinbaladmin.model.dao.ISolicitudManager __solicitudManager) {
    this.__documentManager = __documentManager;
    this.__documentSolicitudManager = __documentSolicitudManager;
    this.__solicitudManager = __solicitudManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(DocumentSolicitudJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<DocumentSolicitudJPA> _bvr_ = new BeanValidatorResult<DocumentSolicitudJPA>();
    _validator.validate(_bvr_, target, isNou, __documentManager, __documentSolicitudManager, __solicitudManager);
    return _bvr_.getErrors();
  }
}
