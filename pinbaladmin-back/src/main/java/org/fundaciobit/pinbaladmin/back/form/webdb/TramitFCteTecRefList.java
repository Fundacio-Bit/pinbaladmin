
package org.fundaciobit.pinbaladmin.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import org.fundaciobit.pinbaladmin.ejb.TramitFCteTecService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.model.fields.TramitFCteTecFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class TramitFCteTecRefList extends RefListBase
    implements TramitFCteTecFields {

  @EJB(mappedName = TramitFCteTecService.JNDI_NAME)
  private TramitFCteTecService tramitFCteTecEjb;

  public TramitFCteTecRefList(TramitFCteTecRefList __clone) {
    super(__clone);
    this.tramitFCteTecEjb = __clone.tramitFCteTecEjb;
  }
  public TramitFCteTecRefList() {
    setSelects(new Select<?>[] { CTETECID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = tramitFCteTecEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
