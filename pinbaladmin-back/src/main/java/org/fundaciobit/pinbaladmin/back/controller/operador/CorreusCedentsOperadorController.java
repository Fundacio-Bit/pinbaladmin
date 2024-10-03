package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.email.MailCedentInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.MailCedentInfo.CEDENTS_LOCALS;
import org.fundaciobit.pinbaladmin.logic.utils.email.MailCedentInfo.CODIS_SERVEIS_LOCALS;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/operador/correucedents")
@SessionAttributes(types = { EventForm.class, EventFilterForm.class })
public class CorreusCedentsOperadorController {

    protected static final Logger log = Logger.getLogger(CorreusCedentsOperadorController.class);

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EventService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.EventService eventEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;

    @EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
    protected IncidenciaTecnicaLogicaService incidenciaTecnicaLogicaEjb;

    @EJB(mappedName = ServeiLogicaService.JNDI_NAME)
    protected ServeiLogicaService serveiLogicaEjb;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listGet(HttpServletRequest request, HttpServletResponse response) throws I18NException {

        return listPost(request, response);
    }

    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        return rm.value()[0];
    }

    /**
     * Guardar un nou Event
     * @throws I18NException 
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView listPost(HttpServletRequest request, HttpServletResponse response) throws I18NException {

		log.info("Entra a infocorreuscedents");

		ModelAndView mav = new ModelAndView("infocorreuscedents");
		
		Map<CEDENTS_LOCALS, MailCedentInfo> mails = new HashMap<CEDENTS_LOCALS, MailCedentInfo>();
		for (CEDENTS_LOCALS cedent : CEDENTS_LOCALS.values()) {
			mails.put(cedent, new MailCedentInfo(cedent));
		}
		
		for (CODIS_SERVEIS_LOCALS codi : CODIS_SERVEIS_LOCALS.values()) {
			Where wCodi = ServeiFields.CODI.equal(codi.name());
			List<Servei> llistat = serveiLogicaEjb.select(wCodi);
			if (llistat.size() != 1) {
				continue;
			}
			Servei serv = llistat.get(0);	
			
			switch (codi) {
			case SVDSCTFNWS01:
				mails.get(CEDENTS_LOCALS.FAM_NOMBROSA).afegirServei(serv);
				break;
			case SVDCCAADISCAPACIDADWS01:
				mails.get(CEDENTS_LOCALS.DISCAPACITAT).afegirServei(serv);
				break;
			case SVDCCAACPCWS01:
			case SVDCCAACPASWS01:
				mails.get(CEDENTS_LOCALS.INTERVENCIO).afegirServei(serv);
				break;
			case SCDCPAJU:
				mails.get(CEDENTS_LOCALS.PADRO).afegirServei(serv);
				break;
			}
		}
        
		mav.addObject("items", mails.values());
		mav.addObject("contexte", getContextWeb());
        
        return mav;
    }
}
