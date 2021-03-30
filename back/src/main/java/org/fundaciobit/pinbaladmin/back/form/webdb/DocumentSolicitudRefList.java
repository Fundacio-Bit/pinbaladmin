
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class DocumentSolicitudRefList extends RefListBase
    implements DocumentSolicitudFields {

  @EJB(mappedName = DocumentSolicitudLocal.JNDI_NAME)
  private DocumentSolicitudLocal documentSolicitudEjb;

  public DocumentSolicitudRefList(DocumentSolicitudRefList __clone) {
    super(__clone);
    this.documentSolicitudEjb = __clone.documentSolicitudEjb;
  }
  public DocumentSolicitudRefList() {
    setSelects(new Select<?>[] { DOCUMENTID.select, SOLICITUDID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = documentSolicitudEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
