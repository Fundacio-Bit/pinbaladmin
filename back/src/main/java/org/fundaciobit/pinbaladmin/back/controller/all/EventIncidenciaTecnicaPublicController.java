package org.fundaciobit.pinbaladmin.back.controller.all;

import org.fundaciobit.pinbaladmin.back.controller.common.AbstractEventIncidenciaTecnicaController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;



/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = EventIncidenciaTecnicaPublicController.CONTEXT_PATH)
@SessionAttributes(types = { EventForm.class, EventFilterForm.class })
public class EventIncidenciaTecnicaPublicController extends AbstractEventIncidenciaTecnicaController implements Constants {

  public static final String CONTEXT_PATH = "/public/eventincidenciatecnica";

  @Override
  public boolean isPublic() {
    return true;
  }


}