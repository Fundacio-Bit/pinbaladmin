package org.fundaciobit.pinbaladmin.back.controller.operador;

import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/solicitudlocal")
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudLocalOperadorController extends SolicitudOperadorController {

  @Override
  public Boolean isEstatal() {
    return false;
  }

  
}
