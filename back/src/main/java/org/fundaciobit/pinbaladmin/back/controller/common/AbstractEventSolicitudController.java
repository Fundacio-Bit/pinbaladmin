package org.fundaciobit.pinbaladmin.back.controller.common;

import java.sql.Timestamp;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.controller.all.EventSolicitudPublicController;
import org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudLocalOperadorController;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaLocal;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.utils.Constants;

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
    return SolicitudLocalOperadorController.CONTEXTWEB + "/list";
  }

  
  @Override
  public String getTramitador(Solicitud item) {
    return item.getCreador();
  }
  
  @Override
  public Timestamp getDataCreacio(Solicitud item) {
    return item.getDataInici();
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
  public Long getItemID(Solicitud item) {
    return item.getSolicitudID();
  }

  @Override
  public Solicitud findItemByPrimaryKey(Long itemID) {
    return solicitudLogicaEjb.findByPrimaryKey(itemID);
  }
  
  @Override
  public String getUrlToEditItem(Solicitud item) {
    return SolicitudLocalOperadorController.CONTEXTWEB + "/" + item.getSolicitudID() + "/edit";
  }
  
  @Override
  public String getUrlToCloseItem(Solicitud item) {
    return SolicitudLocalOperadorController.CONTEXTWEB + "/close/" + item.getSolicitudID();
  }

  @Override
  public  boolean isClosed(Solicitud item) {
    return item.getEstatID() == Constants.SOLICITUD_ESTAT_TANCAT;
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
