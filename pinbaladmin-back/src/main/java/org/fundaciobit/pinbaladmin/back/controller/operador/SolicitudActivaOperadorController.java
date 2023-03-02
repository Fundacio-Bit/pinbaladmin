package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/solicitudactiva")
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudActivaOperadorController extends SolicitudOperadorController {
  
  @Override
  public Where getAdditionalConditionFine(HttpServletRequest request) throws I18NException {
    return SolicitudFields.ESTATID.lessThan(60L); // 60 == ESTAT TANCAT
  }
  
  
  @Override
  public String getSessionAttributeFilterForm() {
    return "SolicitudWebDB_Activa_FilterForm_Operador";
  }
  
  @Override
  public String getEntityNameCode() {
    return "solicitud.solicitudactiva";
  }

  @Override
  public String getEntityNameCodePlural() {
    return "solicitud.solicitudactiva.plural";
  }


  @Override
  public Boolean isEstatal() {
    return null; // Significa que gestiona els dos tipus
  }


  @Override
  public boolean showAdvancedFilter() {    
    return false;
  }

}
