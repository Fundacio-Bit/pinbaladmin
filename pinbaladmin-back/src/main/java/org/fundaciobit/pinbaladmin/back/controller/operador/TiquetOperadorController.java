package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TiquetController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TiquetFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TiquetForm;
import org.fundaciobit.pinbaladmin.persistence.TiquetJPA;
import org.fundaciobit.pinbaladmin.model.entity.Tiquet;
import org.fundaciobit.pinbaladmin.model.fields.TiquetFields;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/tiquet")
@SessionAttributes(types = { TiquetForm.class, TiquetFilterForm.class })
public class TiquetOperadorController extends TiquetController implements Constants {

  @Override
  public String getTileForm() {
    return "tiquetFormWebDB_operador";
  }

  @Override
  public String getTileList() {
    return "tiquetListWebDB_operador";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "TiquetWebDB_operador_FilterForm";
  }

  @Override
  public TiquetForm getTiquetForm(TiquetJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
   TiquetForm tiquetForm = super.getTiquetForm(_jpa, __isView, request, mav);
   
   // Comu
   tiquetForm.addReadOnlyField(INFORMADOR);
   tiquetForm.addReadOnlyField(DATAALTA);
   tiquetForm.addReadOnlyField(ESTATTIQUETID);
   
   Tiquet tiquet = tiquetForm.getTiquet();
   
   if(tiquetForm.isNou()) {
     
     Timestamp now = new Timestamp(new Date().getTime());
     
     tiquet.setDataAlta(now);
     
     tiquet.setInformador(request.getRemoteUser());
     
     tiquet.setEstatTiquetID(ESTATTIQUET_PENDENT);
     tiquet.setVersioPinbal(Configuracio.getVersioPinbal());
     tiquet.setDataIncidencia(now);
     
     tiquet.setEntorn(3); // Producció
     tiquet.setTipusTiquetID(4); // suport
     
     tiquetForm.addHiddenField(DATAFI);
     tiquetForm.addHiddenField(DATAINICI);
     tiquetForm.addHiddenField(SOLUCIONATPER);
   } else {
     int estat = (int)tiquet.getEstatTiquetID();
     

     
     switch(estat) {
       case ESTATTIQUET_PENDENT:
         tiquetForm.addAdditionalButton(new AdditionalButton("fas fa-hand-point-right", "assignar",
             getContextWeb() + "/assignar/" + tiquet.getTiquetID() , "btn-success"));
         tiquetForm.addHiddenField(DATAINICI);
         tiquetForm.addHiddenField(SOLUCIONATPER);
         
       break;

       
       case  ESTATTIQUET_ASSIGNAT:
         tiquetForm.addAdditionalButton(new AdditionalButton("fas fa-thumbs-up", "arreglar",
             getContextWeb() + "/arreglar/" + tiquet.getTiquetID() , "btn-success"));
         tiquetForm.addLabel(SOLUCIONATPER, "assignar");
         tiquetForm.addReadOnlyField(SOLUCIONATPER);
         tiquetForm.addReadOnlyField(DATAINICI);
       break;
     //public static final Long ESTATTIQUET_PAUSAT = 3L;
     
       case ESTATTIQUET_ARREGLAT:
       case ESTATTIQUET_NO_ARREGLAT:
         Collection<Field<?>> col =  Arrays.asList(TiquetFields.ALL_TIQUET_FIELDS);
         HashSet<Field<?>> hs = new HashSet<Field<?>>(col);
         //hs.remove(NOTES);
         //hs.remove(DESCRIPCIO);
         tiquetForm.setReadOnlyFields(hs);
         tiquetForm.setSaveButtonVisible(false);
         break;
     
     }
     
     if (estat != ESTATTIQUET_ARREGLAT && estat != ESTATTIQUET_NO_ARREGLAT) {

       tiquetForm.addAdditionalButton(new AdditionalButton("fas fa-thumbs-down", "noarreglat",
         getContextWeb() + "/noarreglat/" + tiquet.getTiquetID() , "btn-warning"));
       
       tiquetForm.addHiddenField(DATAFI);
     }
     
     
     
     
   }
   return tiquetForm;
  }

  
  @RequestMapping(value = "/arreglar/{tiquetID}", method = RequestMethod.GET)
  public String arreglar(@PathVariable("tiquetID") java.lang.Long tiquetID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
    
    Tiquet tiquet = tiquetEjb.findByPrimaryKey(tiquetID);
    
    tiquet.setSolucionatPer(request.getRemoteUser());
    
    tiquet.setDatafi(new Timestamp(new Date().getTime()));
    
    tiquet.setEstatTiquetID(ESTATTIQUET_ARREGLAT);
    
    tiquetEjb.update(tiquet);
    
    return "redirect:" + getContextWeb() + "/"  + tiquetID + "/edit/";
  }
  
  
  
  @RequestMapping(value = "/noarreglat/{tiquetID}", method = RequestMethod.GET)
  public String noarreglat(@PathVariable("tiquetID") java.lang.Long tiquetID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
    
    Tiquet tiquet = tiquetEjb.findByPrimaryKey(tiquetID);
    
    tiquet.setSolucionatPer(request.getRemoteUser());
    
    tiquet.setEstatTiquetID(ESTATTIQUET_NO_ARREGLAT);
    
    tiquet.setDatafi(new Timestamp(new Date().getTime()));
    
    tiquetEjb.update(tiquet);
    
    
    
    return "redirect:" + getContextWeb() + "/"  + tiquetID + "/edit/";
  }
  
  
  
  @RequestMapping(value = "/assignar/{tiquetID}", method = RequestMethod.GET)
  public String assignar(@PathVariable("tiquetID") java.lang.Long tiquetID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
    
    Tiquet tiquet = tiquetEjb.findByPrimaryKey(tiquetID);
    
    tiquet.setSolucionatPer(request.getRemoteUser());
    
    tiquet.setEstatTiquetID(ESTATTIQUET_ASSIGNAT);
    
    tiquet.setDataInici(new Timestamp(new Date().getTime()));
    
    tiquetEjb.update(tiquet);
    
    
    
    return "redirect:" + getContextWeb() + "/"  + tiquetID + "/edit/";
  }
  
  @Override
  public TiquetFilterForm getTiquetFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
      TiquetFilterForm tiquetFilterForm = super.getTiquetFilterForm(pagina, mav, request);
      
      
      if( tiquetFilterForm.isNou() ) {
        
        tiquetFilterForm.addHiddenField(TIQUETID);
        tiquetFilterForm.addHiddenField(NOTES);
        tiquetFilterForm.addHiddenField(DESCRIPCIO);
        tiquetFilterForm.addHiddenField(INFORMADOR);
        tiquetFilterForm.addHiddenField(SOLUCIONATPER);
        tiquetFilterForm.addHiddenField(DATAINCIDENCIA);
        tiquetFilterForm.addHiddenField(DATAINICI);
        tiquetFilterForm.addHiddenField(ADJUNT1ID);
        tiquetFilterForm.addHiddenField(ADJUNT2ID);
        
        tiquetFilterForm.setOrderBy(DATAINCIDENCIA.javaName);
        tiquetFilterForm.setOrderAsc(false);
        
        
      }
      
      return tiquetFilterForm;
  }
  
  @Override
  public List<StringKeyValue> getReferenceListForEntorn(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1", "Desenvolup."));
    __tmp.add(new StringKeyValue("2", "PreProduc."));
    __tmp.add(new StringKeyValue("3", "Producció"));
    __tmp.add(new StringKeyValue("4", "PinbalAdmin"));
    return __tmp;
  }
  
}
