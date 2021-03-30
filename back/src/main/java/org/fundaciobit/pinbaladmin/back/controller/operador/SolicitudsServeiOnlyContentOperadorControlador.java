package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/solicitudserveionlycontent")
@SessionAttributes(types = { SolicitudServeiForm.class, SolicitudServeiFilterForm.class })
public class SolicitudsServeiOnlyContentOperadorControlador extends
    SolicitudServeiOperadorController {

  @Override
  public boolean isActiveFormNew() {
    return false;
  }

  @Override
  public boolean isActiveFormEdit() {
    return true;
  }

  @Override
  public boolean isActiveDelete() {
    return true;
  }

  @Override
  public boolean isActiveFormView() {
    return false;
  }

  @Override
  public String getTileList() {
    return "solicitudServeiListWebDB_onlycontent_operador";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "SolicitudServeiWebDB_FilterForm_OnlyContent_Operador";
  }
  
  @Override
  public SolicitudServeiFilterForm getSolicitudServeiFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {
    SolicitudServeiFilterForm solicitudServeiFilterForm = super.getSolicitudServeiFilterForm(
        pagina, mav, request);
    
    if (solicitudServeiFilterForm.isNou()) {
      solicitudServeiFilterForm.setItemsPerPage(-1);
      
      solicitudServeiFilterForm.addHiddenField(ARTICLES);
      solicitudServeiFilterForm.addHiddenField(CONSENTIMENT);
      solicitudServeiFilterForm.addHiddenField(ENLLAZCONSENTIMENT);
      solicitudServeiFilterForm.addHiddenField(TIPUSCONSENTIMENT);
      solicitudServeiFilterForm.addHiddenField(ENLLAZNORMALEGAL);

    }
    
    
    return solicitudServeiFilterForm;
  }

}
