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
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;
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

    public static final int FILTRE_AVANZAT_COLUMN = -2;
    public static final StringField FILTRE_AVANZAT_FIELD = EventFields.CAIDNUMEROSEGUIMENT;
    
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
    public EventFilterForm getEventFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		EventFilterForm filterForm = super.getEventFilterForm(pagina, mav, request);

		if(filterForm.isNou()) {
			Set<Field<?>> hiddenFields = new HashSet<Field<?>>(Arrays.asList(EventFields.ALL_EVENT_FIELDS));

			hiddenFields.remove(EventFields.ASUMPTE);
			hiddenFields.remove(EventFields.COMENTARI);
			hiddenFields.remove(EventFields.DATAEVENT);
			
			filterForm.setHiddenFields(hiddenFields);

			filterForm.setOrderBy(EventFields.DATAEVENT.javaName);
			filterForm.setOrderAsc(false);

			filterForm.setEditButtonVisible(false);
			filterForm.setDeleteButtonVisible(false);
			filterForm.setAddButtonVisible(false);
			filterForm.setDeleteSelectedButtonVisible(false);

			{
				AdditionalField<Long, String> adfield = new AdditionalField<Long, String>();
				adfield.setCodeName(EmailFields.DESTINATARIS.fullName);
				adfield.setPosition(POS_DESTINATARI);
				// Els valors s'ompliran al mètode postList()
				adfield.setValueMap(new HashMap<Long, String>());
				adfield.setEscapeXml(false);

				filterForm.addAdditionalField(adfield);
			}

			{
				//Advanced Filter
				AdditionalField<Long, String> advancedFilterField = new AdditionalField<Long, String>();
				advancedFilterField.setCodeName("solicitud.filtreavanzat");
				advancedFilterField.setPosition(FILTRE_AVANZAT_COLUMN);

				advancedFilterField.setEscapeXml(false);
				advancedFilterField.setSearchBy(FILTRE_AVANZAT_FIELD);

				filterForm.addAdditionalField(advancedFilterField);

				hiddenFields.add(FILTRE_AVANZAT_FIELD);
			}
			
			//Eliminar asumpte i destinatari dels filterBy
			List<Field<?>> filterBy = filterForm.getDefaultFilterByFields();
			filterBy.remove(EventFields.ASUMPTE);
			filterBy.remove(EventFields.DESTINATARI);
			filterForm.setFilterByFields(filterBy);
		}
		
		mav.addObject("eventsEnviats", true);
		filterForm.setAttachedAdditionalJspCode(true);
		
		return filterForm;
	}
    
	protected boolean showAdvancedFilter() {
		return true;
	}
    
    @Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		final Integer[] tipus = { Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC,
				Constants.EVENT_TIPUS_CONSULTA_A_CEDENT };

		Where w1 = EventFields.TIPUS.in(tipus);

		
        Where w2 = getAdditionaConditionAdvancedFilter(request);
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
            	Where w = Where.AND(w1, w2);
            	log.info("Where ]" + w.toSQL() + "[");
                return  w;
            }
        }
	}
    
	protected Where getAdditionaConditionAdvancedFilter(HttpServletRequest request) throws I18NException {
		String af = request.getParameter(FILTRE_AVANZAT_FIELD.getFullName());
//		log.info(" Valor Filtre Avanzat FilterBY => ]" + af + "[");

		if (af == null || af.trim().length() == 0) {
			return null;
		} else {
			String likeStr = "%" + af + "%";
			
			Where w = Where.OR(EventFields.DESTINATARI.like(likeStr), EventFields.DESTINATARIMAIL.like(likeStr),
					EventFields.ASUMPTE.like(likeStr), EventFields.COMENTARI.like(likeStr));
			return w;
		}
	}
	
	@Override
    protected List<Event> processarLlistat(ITableManager<Event, Long> ejb, BaseFilterForm filterForm,
            int pagina, Where whereAdditionalCondition, ModelAndView mav) throws I18NException {
        if (filterForm == null) {
            throw new NullPointerException("FilterForm mai pot ser NULL !!!!");
        }

        AdditionalField<?, ?> filtreAvanzatField = null;
        if (showAdvancedFilter()) {
            filtreAvanzatField = filterForm.getAdditionalFields().remove(FILTRE_AVANZAT_COLUMN);
        }

        List<Event> list = super.processarLlistat(ejb, filterForm, pagina, whereAdditionalCondition, mav);

        if (filtreAvanzatField != null) {

            filterForm.getAdditionalFields().put(FILTRE_AVANZAT_COLUMN, filtreAvanzatField);

            String valorFA = filtreAvanzatField.getSearchByValue();

            if (valorFA != null && valorFA.trim().length() != 0) {
                filterForm.setVisibleFilterBy(true);
            }
        }
        return list;
    }
	
    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, EventFilterForm filterForm, List<Event> list)
			throws I18NException {
		super.postList(request, mav, filterForm, list);

		Map<Long, String> map;
		map = (Map<Long, String>) filterForm.getAdditionalField(POS_DESTINATARI).getValueMap();
		map.clear();

		filterForm.getAdditionalButtonsByPK().clear();

		for (Event event : list) {
			map.put(event.getEventID(), event.getDestinatari() + " - " + event.getDestinatarimail());

			if (event.getSolicitudID() != null) {

				filterForm.addAdditionalButtonByPK(event.getEventID(),
						new AdditionalButton("fas fa-bullhorn", "events.titol",
								EventSolicitudOperadorController.CONTEXTWEB + "/veureevents/" + event.getSolicitudID(),
								AdditionalButtonStyle.SUCCESS));

			} else {
				filterForm.addAdditionalButtonByPK(event.getEventID(),
						new AdditionalButton(
								"fas fa-bullhorn", "events.titol", EventIncidenciaTecnicaOperadorController.CONTEXT_PATH
										+ "/veureevents/" + event.getIncidenciaTecnicaID(),
								AdditionalButtonStyle.SUCCESS));
			}

		}
	}
}
