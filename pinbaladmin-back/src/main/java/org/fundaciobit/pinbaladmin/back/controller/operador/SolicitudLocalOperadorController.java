package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = SolicitudLocalOperadorController.CONTEXTWEB)
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudLocalOperadorController extends SolicitudOperadorController {

    public static final String CONTEXTWEB = "/operador/solicitudlocal";

    @Override
    public Boolean isEstatal() {
        return false;
    }

    @Override
    public boolean showAdvancedFilter() {
        return true;
    }

    @Override
    public String getEntityNameCode() {
        switch (getVistaIncidencia()) {
            default:
            case NORMAL:
                return "solicitud.local";
            case NOLLEGITSMEUS:
                return "solicitud.local.nollegitsmeus";
            case NOLLEGITSNOMEUS:
                return "solicitud.local.nollegitsnomeus";
        }
    }

    @Override
    public String getSessionAttributeFilterForm() {

        switch (getVistaIncidencia()) {
            default:
            case NORMAL:
                return super.getSessionAttributeFilterForm();
            case NOLLEGITSMEUS:
                return "solicitud.local.nollegitsmeus" + super.getSessionAttributeFilterForm();
            case NOLLEGITSNOMEUS:
                return "solicitud.local.nollegitsnomeus" + super.getSessionAttributeFilterForm();
        }

    }

    public enum VistaIncidencia {
        NORMAL, NOLLEGITSMEUS, NOLLEGITSNOMEUS,
    }

    public VistaIncidencia getVistaIncidencia() {
        return VistaIncidencia.NORMAL;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        Where w1;

        switch (getVistaIncidencia()) {
            default:
            case NORMAL: {
                w1 = null;
            }
            break;
            case NOLLEGITSMEUS: {
                // incidencies meves
                SubQuery<Event, Long> subQuery = eventLogicaEjb.getSubQuery(EventFields.SOLICITUDID,
                        Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE), EventFields.SOLICITUDID.isNotNull()));
                w1 = Where.AND(CREADOR.equal(request.getRemoteUser()), SOLICITUDID.in(subQuery));
            }
            break;
            case NOLLEGITSNOMEUS: {
                // incidencies No Meves
                SubQuery<Event, Long> subQuery = eventLogicaEjb.getSubQuery(EventFields.SOLICITUDID,
                        Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE), EventFields.SOLICITUDID.isNotNull()));
                w1 = Where.AND(CREADOR.notEqual(request.getRemoteUser()), SOLICITUDID.in(subQuery));
            }
            break;

        }

        log.info("\n\n SQL W1 = " + ((w1 == null) ? "NULL" : w1.toSQL()));

        Where w2 = super.getAdditionalCondition(request);
        log.info("\n\n SQL W2 = " + ((w2 == null) ? "NULL" : w2.toSQL()));

        if (w1 == null) {
            if (w2 == null) {
                return null;
            } else {
                return w2;
            }
        } else {
            if (w2 == null) {
                return w1;
            } else {
                return Where.AND(w1, w2);
            }
        }

    }

    @Override
    public SolicitudFilterForm getSolicitudFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        SolicitudFilterForm solicitudFilterForm = super.getSolicitudFilterForm(pagina, mav, request);

        if (solicitudFilterForm.isNou()) {

            solicitudFilterForm.getHiddenFields().remove(DEPARTAMENTID);

            if (getVistaIncidencia() == VistaIncidencia.NORMAL) {
                //solicitudFilterForm.setEstatIDDesde(-1L);
                //solicitudFilterForm.setEstatIDFins(50L);
            } else {

                if (getVistaIncidencia() == VistaIncidencia.NOLLEGITSMEUS) {
                    solicitudFilterForm.getGroupByFields().remove(CREADOR);
                }
                solicitudFilterForm.setAddButtonVisible(false);
            }

        }

        return solicitudFilterForm;

    }

}
