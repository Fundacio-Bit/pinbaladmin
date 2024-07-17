package org.fundaciobit.pinbaladmin.back.controller.common;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.all.EventSolicitudPublicController;
import org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudLocalOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractEventSolicitudController extends AbstractEventController<Solicitud> {

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @Override
    public boolean isSolicitud() {
        return true;
    }

    @Override
    public String redirectWhenSessionItemIDNotDefined() {
        return SolicitudLocalOperadorController.CONTEXTWEB + "/list";
    }

    @Override
    public String getOperador(Solicitud item) {
        return item.getOperador();
    }

    @Override
    public String getCreador(Solicitud item) {
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
    public String getUrlToChangeOperadorItem(Solicitud item) {
        return SolicitudLocalOperadorController.CONTEXTWEB + "/changeOperador/" + item.getSolicitudID();
    }
    @Override
    public boolean isClosed(Solicitud item) {
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
    public String getPersonaContacteByItemID(Long itemID) throws I18NException {
        return solicitudLogicaEjb.executeQueryOne(SolicitudFields.PERSONACONTACTE,
                SolicitudFields.SOLICITUDID.equal(itemID));
    }

    @Override
    public String getPublicContextPath() {
        return EventSolicitudPublicController.CONTEXT_PATH;
    }

    @Override
    public String getEstat(Solicitud item) throws I18NException {
    	return I18NUtils.tradueix("solicitud.estat." + item.getEstatID());
    }
    
    @Override
    public String getCodiProc(Solicitud item) {
        return item.getProcedimentCodi();
    }
    
    @Override
    	public void postValidate(HttpServletRequest request, EventForm eventForm, BindingResult result)
    			throws I18NException {
    		super.postValidate(request, eventForm, result);
    		
    	}
}
