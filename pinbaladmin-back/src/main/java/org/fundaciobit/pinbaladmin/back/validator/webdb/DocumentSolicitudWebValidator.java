package org.fundaciobit.pinbaladmin.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import org.fundaciobit.pinbaladmin.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.fundaciobit.pinbaladmin.persistence.validator.DocumentSolicitudValidator;

import org.fundaciobit.pinbaladmin.back.form.webdb.DocumentSolicitudForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class DocumentSolicitudWebValidator extends AbstractWebValidator<DocumentSolicitudForm, DocumentSolicitud>
     implements Validator, DocumentSolicitudFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected DocumentSolicitudValidator<DocumentSolicitud> validator = new DocumentSolicitudValidator<DocumentSolicitud>();

  // EJB's
  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentService documentEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService documentSolicitudEjb;

  @javax.ejb.EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;



  public DocumentSolicitudWebValidator() {
    super();    
  }
  
  @Override
  public DocumentSolicitud getBeanOfForm(DocumentSolicitudForm form) {
    return  form.getDocumentSolicitud();
  }

  @Override
  public Class<DocumentSolicitudForm> getClassOfForm() {
    return DocumentSolicitudForm.class;
  }

  @Override
  public void validate(DocumentSolicitudForm __form, DocumentSolicitud __bean, Errors errors) {

    WebValidationResult<DocumentSolicitudForm> wvr;
    wvr = new WebValidationResult<DocumentSolicitudForm>(errors);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean(String.valueOf(objNou));
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(DocumentSolicitudForm __form, DocumentSolicitud __bean, Errors errors,
    WebValidationResult<DocumentSolicitudForm> wvr, boolean isNou) {

    BeanValidatorResult<DocumentSolicitud> __vr = new BeanValidatorResult<DocumentSolicitud>();
    validator.validate(__vr, __bean,
      isNou, documentEjb, documentSolicitudEjb, solicitudEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public DocumentSolicitudValidator<DocumentSolicitud> getValidator() {
    return validator;
  }

  public void setValidator(DocumentSolicitudValidator<DocumentSolicitud> validator) {
    this.validator = validator;
  }

}