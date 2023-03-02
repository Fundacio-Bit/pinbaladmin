package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesFormManager;
import org.fundaciobit.pinbaladmin.back.controller.common.AbstractEventIncidenciaTecnicaController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
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
  
  @EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
  protected FitxerPublicLogicaService fitxerPublicLogicaEjb;
  
  @EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
  protected FitxerPublicLogicaService fitxerEjb;

  @Override
  public boolean isPublic() {
    return true;
  }
  
  @Override
  protected FilesFormManager<Fitxer> getFilesFormManager() {
    return new PinbalAdminFilesFormManager(fitxerPublicLogicaEjb);
  }


}