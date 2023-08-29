
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import org.fundaciobit.pinbaladmin.ejb.TramitCDadesCesiService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TramitCDadesCesiRefList extends RefListBase
    implements TramitCDadesCesiFields {

  @EJB(mappedName = TramitCDadesCesiService.JNDI_NAME)
  private TramitCDadesCesiService tramitCDadesCesiEjb;

  public TramitCDadesCesiRefList(TramitCDadesCesiRefList __clone) {
    super(__clone);
    this.tramitCDadesCesiEjb = __clone.tramitCDadesCesiEjb;
  }
  public TramitCDadesCesiRefList() {
    setSelects(new Select<?>[] { TRAMITID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = tramitCDadesCesiEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
