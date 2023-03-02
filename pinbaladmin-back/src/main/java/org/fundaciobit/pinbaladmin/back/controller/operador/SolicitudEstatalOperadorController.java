package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
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
@RequestMapping(value = "/operador/solicitudestatal")
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudEstatalOperadorController extends SolicitudOperadorController {

  @Override
  public Boolean isEstatal() {
    return true;
  }

  @Override
  public boolean showAdvancedFilter() {
    return false;
  }


  /**
   * Llistat de totes Solicitud
   */
  @RequestMapping(value = "/list/{start}/{end}", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response, @PathVariable("start") Long start, @PathVariable("end") Long end) throws I18NException {

    int pagina = 1;
    
    log.info("START => " + start);
    log.info("END   => " + end);
    
    log.info("START => " + new Timestamp(start));
    log.info("END   => " + new Timestamp(end));
    
    SolicitudFilterForm ff;
    ModelAndView mav = new ModelAndView(getTileList());
    ff = getSolicitudFilterForm(pagina, mav, request);
    
    request.getSession().setAttribute(getSessionAttributeFilterForm(), ff);
    
    
    ff.setDataIniciDesde(new Timestamp(start - 2000));
    ff.setDataIniciFins(new Timestamp(end + 2000));
    ff.setVisibleFilterBy(true);
    
    
    //return mav;
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }
  
  

}