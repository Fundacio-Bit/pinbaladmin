package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

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
                
                w1 = Where.AND(OPERADOR.equal(request.getRemoteUser()), SOLICITUDID.in(subQuery), ENTITATESTATAL.isNull());
            }
            break;
            case NOLLEGITSNOMEUS: {
                // incidencies No Meves
                SubQuery<Event, Long> subQuery = eventLogicaEjb.getSubQuery(EventFields.SOLICITUDID,
                        Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE), EventFields.SOLICITUDID.isNotNull()));
                
                w1 = Where.AND(OPERADOR.notEqual(request.getRemoteUser()), SOLICITUDID.in(subQuery), ENTITATESTATAL.isNull());
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
    
//	@GetMapping("/jsonSolicitudEvents")
//	@ResponseBody
//	public List<SolicitudConEventos> obtenirJsonSolicitudEvents() throws I18NException {
//		log.info("jsonSolicitudEvents: INICIO");
//
//		
//	    List<Long> solicitudsIDs = solicitudLogicaEjb.executeQuery(
//	        SolicitudFields.SOLICITUDID,
//	        SolicitudFields.ESTATSOLICITUD.equal(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR_Manual)
//	    );
//		log.info("solicitudsIDs: " + solicitudsIDs.size());
//
//	    
//
//	    List<SolicitudConEventos> items = new ArrayList<>();
//
//	    for (Long soliID : solicitudsIDs) {
//	        List<Event> events = eventLogicaEjb.select(EventFields.SOLICITUDID.equal(soliID));
//			log.info("Solicitud " + soliID + " amb " + events.size() + " events");
//
//	        items.add(new SolicitudConEventos(soliID, events));
//	    }
//
//		log.info("Items totals: " + items.size());
//
//	    
//	    return items;
//	}

	public class SolicitudConEventos {

		private Long soliID;
		private String codi;
		private String nom;
		
		private List<MyEvent> events;

		public Long getSoliID() {
			return soliID;
		}

		public void setSoliID(Long soliID) {
			this.soliID = soliID;
		}

		public List<MyEvent> getEvents() {
			return events;
		}

		public void setEvents(List<MyEvent> events) {
			this.events = events;
		}

		public String getCodi() {
			return codi;
		}

		public String getNom() {
			return nom;
		}

		public SolicitudConEventos(Solicitud soli, List<MyEvent> events) {
			this.soliID = soli.getSolicitudID();
			this.nom = soli.getProcedimentNom();
			this.codi = soli.getProcedimentCodi();
			this.events = events;
		}
	}
	
	public class MyEvent{
		
		Long id;
		int tipus;
		String persona;
		String destinatari;
		String comentari;
		Timestamp dataEvent;
		
		
		public MyEvent(Event evt) {
			this.id = evt.getEventID();
			this.tipus = evt.getTipus();
			this.persona = evt.getPersona();
			this.destinatari = evt.getDestinatari();
			this.comentari = evt.getComentari();
			this.dataEvent = evt.getDataEvent();
		}

		public Long getId() {
			return id;
		}

		public int getTipus() {
			return tipus;
		}

		public String getPersona() {
			return persona;
		}

		public String getDestinatari() {
			return destinatari;
		}

		public String getComentari() {
			return comentari;
		}

		public Timestamp getDataEvent() {
			return dataEvent;
		}

	}
	
    
	@RequestMapping(value = {"/jsonSolicitudEvents"},  method = RequestMethod.GET)
	public void obtenirJsonSolicitudEvents(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.info("jsonSolicitudEvents: INICIO");
		
		
		
		List<Solicitud> solicituds = solicitudLogicaEjb.select(SolicitudFields.ESTATSOLICITUD.equal(Constants.SOLI_ESTAT_REVISIO));		
		
		log.info("solicitudsIDs: " + solicituds.size());

		List<SolicitudConEventos> items = new java.util.ArrayList<SolicitudConEventos>();

		Integer[] tipusEvents = {Constants.EVENT_TIPUS_COMENTARI_CONTACTE, Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC, Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT};
		
		Where wTipusEvents = EventFields.TIPUS.in(tipusEvents);
		OrderBy order = new OrderBy(EventFields.DATAEVENT, OrderType.DESC);
		
		for (Solicitud soli : solicituds) {
//, wTipusEvents
			List<Event> events = eventLogicaEjb.select(Where.AND(EventFields.SOLICITUDID.equal(soli.getSolicitudID())), order);
	
			List<MyEvent> myEvents = new ArrayList<SolicitudLocalOperadorController.MyEvent>();
			for (Event event : events) {
				myEvents.add(new MyEvent(event));
			}
			
			log.info("Solicitud " + soli.getSolicitudID() + " amb " + events.size() + " events");
			
			SolicitudConEventos item = new SolicitudConEventos(soli, myEvents);
			items.add(item);
		}

		log.info("Items totals: " + items.size());
		
		Gson g = new Gson();
		String serveisJsonString = g.toJson(items);

//		log.info(serveisJsonString );

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(serveisJsonString );
		out.flush();
	}
    
	
}
