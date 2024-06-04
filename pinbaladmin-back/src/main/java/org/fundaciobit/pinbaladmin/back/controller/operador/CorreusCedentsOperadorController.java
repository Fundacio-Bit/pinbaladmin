package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.operador.SolicitudEstatalOperadorController.MailCedentInfo;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.ServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
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

    public static Date atEndOfDay(Date date) {
        return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
    }

    public static Date atStartOfDay(Date date) {
        return DateUtils.truncate(date, Calendar.DATE);
    }

    public void afegirEventTipus(Map<String, String[]> fullevents, String event, Long id) {

        String tipus = null;
        String titol = null;

        switch (event) {
            case "SOL":
                Solicitud soli = solicitudEjb.findByPrimaryKey(id);

                if (soli.getEntitatEstatal() != null) {
                    tipus = "Estatal [" + id + "]";
                } else if (soli.getDepartamentID() != null || soli.getOrganid() != null) {
                    tipus = "Local [" + id + "]";
                }
                titol = soli.getProcedimentNom();
            break;
            case "INC":
                IncidenciaTecnica ic = incidenciaTecnicaLogicaEjb.findByPrimaryKey(id);

                tipus = "Incidencia [" + id + "]";
                titol = ic.getTitol();
            break;
        }

        String hash = encode(tipus + ": " + titol);

        String[] missatge = { tipus, titol, hash };
        fullevents.put(event + id, missatge);
    }

    @RequestMapping(value = "/afegirEntrada/{usuari}/{dateStr}/{msgEnc}", method = RequestMethod.GET)
    public void afegeixEntrada(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("usuari") String usuari, @PathVariable("dateStr") String dateStr,
            @PathVariable("msgEnc") String msgEnc) {

        try {
            String login = Configuracio.getQueEsticFentUser();
            String password = Configuracio.getQueEsticFentPassword();
            String url = Configuracio.getQueEsticFentBDURL();
            
            Connection con = DriverManager.getConnection(url, login, password);
            String SQL_INSERT = "INSERT INTO qef_modificacionsqueesticfent (accioID, usuariID, projecteID, data, dada1) VALUES (?,?,?,?,?)";

            PreparedStatement preparedStatement = con.prepareStatement(SQL_INSERT);

            int accioID = -3;
            String usuariID = usuari;
            int projecteID = 1021; //PINBAL

            Date date;
            try {
                date = SDF.parse(dateStr);
            } catch (Throwable e) {
                date = new Date();
                dateStr = SDF.format(date);
            }
            Timestamp data = new Timestamp(date.getTime());

            String dada1 = decode(msgEnc);

            preparedStatement.setInt(1, accioID);
            preparedStatement.setString(2, usuariID);
            preparedStatement.setInt(3, projecteID);
            preparedStatement.setTimestamp(4, data);
            preparedStatement.setString(5, dada1);

            int row = preparedStatement.executeUpdate();
            // rows affected
            if (row == 1) {
                log.info("Afegit missatge: " + dada1);
            }
            
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encode(String originalInput) {

        int MAX_LENGTH = 230;
        if (originalInput.length() > MAX_LENGTH) {
            originalInput = originalInput.substring(0, MAX_LENGTH);
        }
        String encodedString;
        encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        return encodedString;
    }

    public String decode(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String missatge = new String(decodedBytes);
        return missatge;
    }
}
