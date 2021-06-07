package org.fundaciobit.pinbaladmin.back.controller.operador;

import org.fundaciobit.pinbaladmin.back.controller.common.AbstractEventSolicitudController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = EventSolicitudOperadorController.CONTEXT_PATH)
@SessionAttributes(types = { EventForm.class, EventFilterForm.class })
public class EventSolicitudOperadorController extends AbstractEventSolicitudController  {

  public static final String CONTEXT_PATH = "/operador/eventsolicitud";

  @Override
  public boolean isPublic() {
    return false;
  }
}
