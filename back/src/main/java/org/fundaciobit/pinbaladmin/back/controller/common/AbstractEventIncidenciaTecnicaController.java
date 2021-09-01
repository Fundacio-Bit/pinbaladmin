package org.fundaciobit.pinbaladmin.back.controller.common;

import javax.ejb.EJB;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.controller.all.EventIncidenciaTecnicaPublicController;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaLocal;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractEventIncidenciaTecnicaController
    extends AbstractEventController<IncidenciaTecnica> {

  @EJB(mappedName = IncidenciaTecnicaLogicaLocal.JNDI_NAME)
  protected IncidenciaTecnicaLogicaLocal incidenciaTecnicaLogicaEjb;

  @Override
  public boolean isSolicitud() {
    return false;
  }

  @Override
  public String redirectWhenSessionItemIDNotDefined() {
    return "/operador/incidencia/list";
  }
  
  
  
  @Override
  public String getPersonaContacteNom(IncidenciaTecnica item) {
    return item.getContacteNom();
  }

  @Override
  public String getPersonaContacteEmail(IncidenciaTecnica item) {
    return item.getContacteEmail();
  }

  @Override
  public IncidenciaTecnica findItemByPrimaryKey(Long itemID) {
    return incidenciaTecnicaLogicaEjb.findByPrimaryKey(itemID);
  }

  @Override
  public String getTitol(IncidenciaTecnica item) {
    return item.getTitol();
  }

  @Override
  public String getPersonaContacteEmailByItemID(Long itemID) throws I18NException {
    return incidenciaTecnicaLogicaEjb.executeQueryOne(IncidenciaTecnicaFields.CONTACTEEMAIL,
        IncidenciaTecnicaFields.INCIDENCIATECNICAID.equal(itemID));
  }

  @Override
  public String getPublicContextPath() {
    return EventIncidenciaTecnicaPublicController.CONTEXT_PATH;
  }

  @Override
  public String getEstat(IncidenciaTecnica item) throws I18NException {

    switch (item.getEstat()) {
    case ESTAT_INCIDENCIA_TECNICA_OBERTA:
      return "Oberta";
    case ESTAT_INCIDENCIA_TECNICA_TANCADA:
      return "Tancada";
    case ESTAT_INCIDENCIA_TECNICA_PENDENT_DE_TERCER:
      return "Pendent de Tercer";

    }
    return "Estat desconegut " + item.getEstat();
  }

}
