
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import org.fundaciobit.pinbaladmin.ejb.IncidenciaTecnicaLocal;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class IncidenciaTecnicaRefList extends RefListBase
    implements IncidenciaTecnicaFields {

  @EJB(mappedName = IncidenciaTecnicaLocal.JNDI_NAME)
  private IncidenciaTecnicaLocal incidenciaTecnicaEjb;

  public IncidenciaTecnicaRefList(IncidenciaTecnicaRefList __clone) {
    super(__clone);
    this.incidenciaTecnicaEjb = __clone.incidenciaTecnicaEjb;
  }
  public IncidenciaTecnicaRefList() {
    setSelects(new Select<?>[] { TITOL.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = incidenciaTecnicaEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
