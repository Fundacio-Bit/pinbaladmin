package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.EventController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.fields.EmailFields;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = EventsEnviatsOperadorController.CONTEXTWEB)
@SessionAttributes(types = { EventForm.class, EventFilterForm.class })
public class EventsEnviatsOperadorController extends EventController implements Constants {

    public static final String CONTEXTWEB = "/operador/eventsenviats";
    
    public static final int POS_DESTINATARI = -1;

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public boolean isActiveFormNew() {
    	return false;
    }
    
    @Override
    public boolean isActiveList() {
    	return true;
    }
    
    @Override
    public String getTileForm() {
        return "eventEnviatOperadorForm";
    }

    @Override
    public String getTileList() {
        return "eventEnviatOperadorList";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "EventEnviatOperador_FilterForm";
    }

    @Override
    public EventFilterForm getEventFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
    		throws I18NException {
    	EventFilterForm filterForm = new EventFilterForm();

        Set<Field<?>> hiddenFields = new HashSet<Field<?>>(Arrays.asList(EventFields.ALL_EVENT_FIELDS));
        
        hiddenFields.remove(EventFields.ASUMPTE);
        hiddenFields.remove(EventFields.COMENTARI);
        hiddenFields.remove(EventFields.DATAEVENT);
//        hiddenFields.remove(EventFields.DESTINATARI);
//        hiddenFields.remove(EventFields.DESTINATARIMAIL);
        
        filterForm.setHiddenFields(hiddenFields);
        
        filterForm.setOrderBy(EventFields.DATAEVENT.javaName);
        filterForm.setOrderAsc(false);
        
        filterForm.setEditButtonVisible(false);
        filterForm.setDeleteButtonVisible(false);
        filterForm.setAddButtonVisible(false);
        filterForm.setDeleteSelectedButtonVisible(false);
        
        {
			AdditionalField<Long, String> adfield = new AdditionalField<Long, String>();
			adfield.setCodeName(EmailFields.MESSAGE.fullName);
			adfield.setPosition(POS_DESTINATARI);
			// Els valors s'ompliran al mètode postList()
			adfield.setValueMap(new HashMap<Long, String>());
			adfield.setEscapeXml(false);

			filterForm.addAdditionalField(adfield);
		}
        
        mav.addObject("eventsEnviats", true);
        filterForm.setAttachedAdditionalJspCode(true);
        
    	return filterForm;
    }
    
    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		Integer[] tipus = { Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC,
				Constants.EVENT_TIPUS_CONSULTA_A_CEDENT };
		return EventFields.TIPUS.in(tipus);
    }
    
    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, EventFilterForm filterForm, List<Event> list)
    		throws I18NException {
    	super.postList(request, mav, filterForm, list);
    	
    	Map<Long, String> map;
		map = (Map<Long, String>) filterForm.getAdditionalField(POS_DESTINATARI).getValueMap();
		map.clear();
    	
		for (Event event : list) {
			map.put(event.getEventID(), event.getDestinatari() + " - " +  event.getDestinatarimail());
			
			if (event.getSolicitudID() != null) {
				
				filterForm.addAdditionalButtonByPK(event.getEventID(),
						new AdditionalButton("fas fa-bullhorn", "events.titol",
			                    EventSolicitudOperadorController.CONTEXTWEB + "/veureevents/" + event.getSolicitudID(), AdditionalButtonStyle.SUCCESS));
				
			}else {
				filterForm.addAdditionalButtonByPK(event.getEventID(),
						new AdditionalButton("fas fa-bullhorn", "events.titol",
			                    EventIncidenciaTecnicaOperadorController.CONTEXT_PATH + "/veureevents/" + event.getIncidenciaTecnicaID(), AdditionalButtonStyle.SUCCESS));
			}
			
			
		}
    }
}
