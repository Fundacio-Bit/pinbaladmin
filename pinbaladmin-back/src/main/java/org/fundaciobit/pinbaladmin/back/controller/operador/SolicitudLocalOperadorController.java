package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
                w1 = Where.AND(OPERADOR.equal(request.getRemoteUser()), SOLICITUDID.in(subQuery));
            }
            break;
            case NOLLEGITSNOMEUS: {
                // incidencies No Meves
                SubQuery<Event, Long> subQuery = eventLogicaEjb.getSubQuery(EventFields.SOLICITUDID,
                        Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE), EventFields.SOLICITUDID.isNotNull()));
                w1 = Where.AND(OPERADOR.notEqual(request.getRemoteUser()), SOLICITUDID.in(subQuery));
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
        request.setAttribute("desplegableOrgans", true);

        List<Organ> organs = organEjb.select();
        mav.addObject("organs", organs);

        String organidStr = request.getParameter("solicitud.organid");
        if (organidStr != null && organidStr.trim().length() != 0) {
            long organID = Long.parseLong(organidStr);
            Organ selected = organEjb.findByPrimaryKey(organID);
            
            log.info(selected.getNom());
            mav.addObject("organSelected", organID);
        }

//        1. afegir llistat de organs
//        2. Afegir organ acutal (quan toqui) a mav.addObject('organid-selected');
        
        if (solicitudFilterForm.isNou()) {
//            solicitudFilterForm.getHiddenFields().remove(ORGANID);

            if (getVistaIncidencia() == VistaIncidencia.NORMAL) {
                //solicitudFilterForm.setEstatIDDesde(-1L);
                //solicitudFilterForm.setEstatIDFins(50L);
            } else {
                if (getVistaIncidencia() == VistaIncidencia.NOLLEGITSMEUS) {
                    solicitudFilterForm.getGroupByFields().remove(CREADOR);
                    solicitudFilterForm.getGroupByFields().remove(OPERADOR);
                }
                solicitudFilterForm.setAddButtonVisible(false);
            }
        }
        return solicitudFilterForm;
    }

//    @Override
//    public void postList(HttpServletRequest request, ModelAndView mav, SolicitudFilterForm filterForm,
//            List<Solicitud> list) throws I18NException {
//
//        super.postList(request, mav, filterForm, list);
//
//        Map<Long, String> map;
//        map = (Map<Long, String>) filterForm.getAdditionalField(COLUMNA_ORGAN).getValueMap();
//        map.clear();
//
//        for (Solicitud sol : list) {
//            Long organid = sol.getOrganid();
//            Organ aux = organEjb.findByPrimaryKey(organid);
//
//            String html = "";
//            html += "<td class='elemOrgan' onclick='toggleJerarquia(this);'>";
//            html += "<p class='pOrganClose'>";
//            html += "(" + aux.getDir3() + ") " + aux.getNom();
//            html += "<span class='spanOrganClose'>";
//            
//            while (aux.getCif() == null && aux.getDir3pare() != null) {
//                List<Organ> listAux = organEjb.select(OrganFields.DIR3.equal(aux.getDir3pare()));
//                aux = listAux.get(0);
//                String linea = "<br>" + "&nbsp;".repeat(3) + '└' + "(" + aux.getDir3() + ") " + aux.getNom();
//                html += linea;
//                log.info(linea);
////                jerarquia.add("(" + aux.getDir3() + ") " + aux.getNom());
//            }
//            html += "</span>";
//            html += "</p>";
//            html += "</td>";
//
//            
////            log.info("Organ Gestor: " + "(" + aux.getDir3() + ") " + aux.getNom());
////            List<String> jerarquia = new ArrayList<String>();
////            jerarquia.add("(" + aux.getDir3() + ") " + aux.getNom());
//
//            
////            String text = String.join("|", jerarquia);
//            map.put(sol.getSolicitudID(), html);
//
//        }
//    }
    
    
    @RequestMapping(value = "/getAllOrgansGestors", method = RequestMethod.GET)
    public void mostrarJerarquia(HttpServletRequest request, HttpServletResponse response) throws I18NException, IOException {

        List<Organ> organs = organEjb.select();
        List<String> jsSelect = new ArrayList<String>();

        jsSelect.add("<option selected=\"true\" value=\"\"></option>");
        for (Organ organ : organs) {
            String jsOption = "<option value='" + organ.getOrganid() + "'>(" + organ.getDir3() + ") " + organ.getNom() + "</option>";
            jsSelect.add(jsOption);
        }
        
        String str = String.join("|", jsSelect);
        
        response.getWriter().write(str);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
