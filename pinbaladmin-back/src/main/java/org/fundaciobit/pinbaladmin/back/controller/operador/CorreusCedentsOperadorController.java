package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.email.MailCedentInfo;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.springframework.core.annotation.AnnotationUtils;
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
		
		MailCedentInfo discapacitat = new MailCedentInfo("DISCAPACITAT");
		MailCedentInfo famNombrosa = new MailCedentInfo("FAM_NOMBROSA");
		MailCedentInfo intervencio = new MailCedentInfo("INTERVENCIO");
		MailCedentInfo padro = new MailCedentInfo("PADRO");
		
		MailCedentInfo[] mails = { discapacitat, famNombrosa, intervencio, padro };

		String[] codis = {"SVDSCTFNWS01", "SVDSCDDWS01", "SVDCCAACPCWS01", "SVDCCAACPASWS01", "SCDCPAJU"};
		
		for (String codi : codis) {
			Where wCodi = ServeiFields.CODI.equal(codi);
			List<Servei> llistat = serveiLogicaEjb.select(wCodi);
			if (llistat.size() != 1) {
				continue;
			}
			Servei serv = llistat.get(0);	
			
			switch (codi) {
			case "SVDSCTFNWS01":
				famNombrosa.afegirServei(serv);
				break;
			case "SVDSCDDWS01":
				discapacitat.afegirServei(serv);
				break;
			case "SVDCCAACPCWS01":
			case "SVDCCAACPASWS01":
				intervencio.afegirServei(serv);
				break;
			case "SCDCPAJU":
				padro.afegirServei(serv);
				break;
			}
		}
        
		mav.addObject("items", mails);
		mav.addObject("contexte", getContextWeb());
        
        return mav;
    }
}
