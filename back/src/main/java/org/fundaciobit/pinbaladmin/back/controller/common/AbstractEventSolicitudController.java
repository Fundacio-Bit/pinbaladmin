package org.fundaciobit.pinbaladmin.back.controller.common;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.controller.all.EventSolicitudPublicController;

import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaLocal;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractEventSolicitudController
    extends AbstractEventController<Solicitud> {

  @EJB(mappedName = SolicitudLogicaLocal.JNDI_NAME)
  protected SolicitudLogicaLocal solicitudLogicaEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatSolicitudLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatSolicitudLocal estatSolicitudEjb;

  @Override
  public boolean isSolicitud() {
    return true;
  }

  @Override
  public String redirectWhenSessionItemIDNotDefined() {
    return "/operador/solicitudlocal/list";
  }

  @Override
  public String getPersonaContacteNom(Solicitud item) {
    return item.getPersonaContacte();
  }

  @Override
  public String getPersonaContacteEmail(Solicitud item) {
    return item.getPersonaContacteEmail();
  }

  @Override
  public Solicitud findItemByPrimaryKey(Long itemID) {
    return solicitudLogicaEjb.findByPrimaryKey(itemID);
  }

  @Override
  public String getTitol(Solicitud item) {
    return item.getProcedimentNom();
  }

  @Override
  public String getPersonaContacteEmailByItemID(Long itemID) throws I18NException {
    return solicitudLogicaEjb.executeQueryOne(SolicitudFields.PERSONACONTACTEEMAIL,
        SolicitudFields.SOLICITUDID.equal(itemID));
  }

  @Override
  public String getPublicContextPath() {
    return EventSolicitudPublicController.CONTEXT_PATH;
  }

  @Override
  public String getEstat(Solicitud item) throws I18NException {
    return estatSolicitudEjb.executeQueryOne(EstatSolicitudFields.NOM,
        EstatSolicitudFields.ESTATSOLICITUDID.equal(item.getEstatID()));
  }

}
