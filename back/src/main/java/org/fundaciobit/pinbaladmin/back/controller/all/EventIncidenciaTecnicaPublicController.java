package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesFormManager;
import org.fundaciobit.pinbaladmin.back.controller.common.AbstractEventIncidenciaTecnicaController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.ejb.FitxerLocal;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaLocal;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
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
  
  @EJB(mappedName = FitxerPublicLogicaLocal.JNDI_NAME)
  protected FitxerPublicLogicaLocal fitxerPublicLogicaEjb;
  
  @EJB(mappedName = FitxerPublicLogicaLocal.JNDI_NAME)
  protected FitxerPublicLogicaLocal fitxerEjb;

  @Override
  public boolean isPublic() {
    return true;
  }
  
  @Override
  protected FilesFormManager<Fitxer> getFilesFormManager() {
    return new PinbalAdminFilesFormManager(fitxerPublicLogicaEjb);
  }


}