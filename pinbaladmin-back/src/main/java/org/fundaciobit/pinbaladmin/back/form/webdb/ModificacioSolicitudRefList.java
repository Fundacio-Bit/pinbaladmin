
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import org.fundaciobit.pinbaladmin.ejb.ModificacioSolicitudService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.fields.ModificacioSolicitudFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class ModificacioSolicitudRefList extends RefListBase
    implements ModificacioSolicitudFields {

  @EJB(mappedName = ModificacioSolicitudService.JNDI_NAME)
  private ModificacioSolicitudService modificacioSolicitudEjb;

  public ModificacioSolicitudRefList(ModificacioSolicitudRefList __clone) {
    super(__clone);
    this.modificacioSolicitudEjb = __clone.modificacioSolicitudEjb;
  }
  public ModificacioSolicitudRefList() {
    setSelects(new Select<?>[] { MODSOLIID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = modificacioSolicitudEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
