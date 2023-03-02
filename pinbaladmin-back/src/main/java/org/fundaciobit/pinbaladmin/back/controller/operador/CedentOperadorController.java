package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.EntitatServeiController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatServeiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatServeiForm;
import org.fundaciobit.pinbaladmin.model.entity.EntitatServei;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.fundaciobit.pinbaladmin.model.fields.DocumentCedentFields;


/**
 * 
 * @author anadal
 *  Llistat de Cedents
 *
 */
@Controller
@RequestMapping(value = "/operador/cedent")
@SessionAttributes(types = { EntitatServeiForm.class, EntitatServeiFilterForm.class })
public class CedentOperadorController extends EntitatServeiController {
  
  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentCedentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentCedentService documentCedentEjb;
  
  
  public static final int DOCS = 1;

  @Override
  public String getSessionAttributeFilterForm() {
    return "EntitatServeiWebDB_FilterForm_operador";
  }
  
  @Override
  public String getTileForm() {
    return "entitatServeiFormWebDB_operador";
  }

  @Override
  public String getTileList() {
    return "entitatServeiListWebDB_operador";
  }
 
  @Override
  public EntitatServeiFilterForm getEntitatServeiFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
      EntitatServeiFilterForm entitatServeiFilterForm = super.getEntitatServeiFilterForm(pagina, mav, request);
      if (entitatServeiFilterForm.isNou()) {
        entitatServeiFilterForm.setVisibleMultipleSelection(false);
        
        entitatServeiFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_FILE, "documentscedent",
            "/operador/cedent/documents/{0}", "btn-info"));
        
        entitatServeiFilterForm.setItemsPerPage(20);
        
        entitatServeiFilterForm.addLabel(BALEARS, "=Illes");
        
        
        AdditionalField<Long,String> adfield4 = new AdditionalField<Long,String>(); 
        adfield4.setCodeName("=Docs.");
        adfield4.setPosition(DOCS);
        // Els valors s'ompliran al mètode postList()
        adfield4.setValueMap(new HashMap<Long, String>());
        adfield4.setEscapeXml(false);

        entitatServeiFilterForm.addAdditionalField(adfield4);
        
      }
      return entitatServeiFilterForm;
  }
 
  @Override
  public void postList(HttpServletRequest request, ModelAndView mav, EntitatServeiFilterForm filterForm,  List<EntitatServei> list) throws I18NException {

      Map<Long, String> map;
      map = (Map<Long, String>)filterForm.getAdditionalField(DOCS).getValueMap(); 
      map.clear();
      
      for (EntitatServei entitatServei : list) {
        
        long count = documentCedentEjb.count(
            DocumentCedentFields.ENTITATSERVEIID.equal(entitatServei.getEntitatServeiID()));
        
        if (count == 0) {
          map.put(entitatServei.getEntitatServeiID(), "");
        } else {
          map.put(entitatServei.getEntitatServeiID(), String.valueOf(count));
        }
        
      }
      
  }
  
  
  @RequestMapping(value = "/documents/{entitatSerceiID}", method = RequestMethod.GET)
  public ModelAndView veureDocumentCedentGet(@PathVariable("entitatSerceiID") java.lang.Long entitatSerceiID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
    
    request.getSession().setAttribute(DocumentCedentOperatorController.CEDENTID_SESSION_PROPERTY, entitatSerceiID);
    
    return new ModelAndView(new RedirectView("/operador/documentcedent/list", true));
    
  }
  
  
  
  @Override
  public void delete(HttpServletRequest request, EntitatServei entitatServei) throws I18NException {
    
    documentCedentEjb.delete(DocumentCedentFields.ENTITATSERVEIID.equal(entitatServei.getEntitatServeiID()));
    
    entitatServeiEjb.delete(entitatServei);
  }
  
  
}
