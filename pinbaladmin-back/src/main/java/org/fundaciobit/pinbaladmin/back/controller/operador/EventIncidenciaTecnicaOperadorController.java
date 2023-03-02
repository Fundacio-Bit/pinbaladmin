package org.fundaciobit.pinbaladmin.back.controller.operador;

import org.fundaciobit.pinbaladmin.back.controller.common.AbstractEventIncidenciaTecnicaController;
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
@RequestMapping(value = EventIncidenciaTecnicaOperadorController.CONTEXT_PATH)
@SessionAttributes(types = { EventForm.class, EventFilterForm.class })
public class EventIncidenciaTecnicaOperadorController extends AbstractEventIncidenciaTecnicaController  {

  public static final String CONTEXT_PATH = "/operador/eventincidenciatecnica";

  @Override
  public boolean isPublic() {
    return false;
  }
}
