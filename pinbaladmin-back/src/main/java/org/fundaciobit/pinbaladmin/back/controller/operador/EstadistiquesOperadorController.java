package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ptrias
 */

@Controller
@RequestMapping(value = "/operador/estadistiques")

public class EstadistiquesOperadorController {

    protected static final Logger log = Logger.getLogger(QueEsticFentOperadorController.class);

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat SDF2 = new SimpleDateFormat("EEEE d/MM/yyyy" ,  Locale.forLanguageTag("ca-ES"));

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
    protected IncidenciaTecnicaLogicaService incidenciaTecnicaLogicaEjb;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listGet(HttpServletRequest request, HttpServletResponse response) {

        return listPost(request, response);
    }

    public String getContextWeb() {
        RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
        return rm.value()[0];
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView listPost(HttpServletRequest request, HttpServletResponse response) {
    	//DATA | SOLICITUDS TOTALS | LOCALS | ESTATALS | INCIDENCIES TOTALS | ROLS I PERMISOS | CONSULTES | TECNIQUES

        log.info("Entra a estadistiques");
        ModelAndView mav = new ModelAndView("estadistiques");

        
//        Date date;
//        String dateStr = request.getParameter("data");
//        if (dateStr == null || dateStr.trim().length() == 0) {
//            date = new Date();
//            dateStr = SDF.format(date);
//        } else {
//            try {
//                date = SDF.parse(dateStr);
//            } catch (ParseException e) {
//                HtmlUtils.saveMessageError(request, "Error en el format de la data: " + dateStr);
//                mav.addObject("data", SDF.format(new Date()));
//                return mav;
//            }
//        }
//
//        mav.addObject("data", dateStr);
        
		Date dataInici = new Date();
		Date dataFi = new Date();
		
		String dataIniciStr = request.getParameter("dataInici");
		String dataFiStr = request.getParameter("dataFinal");
		
		if (dataIniciStr == null || dataIniciStr.trim().length() == 0) {
			dataInici = new Date();
			dataIniciStr = SDF.format(dataInici);
		} else {
			try {
				dataInici = SDF.parse(dataIniciStr);
			} catch (ParseException e) {
				HtmlUtils.saveMessageError(request, "Error en el format de la data d'inici: " + dataIniciStr);
				mav.addObject("dataInici", SDF.format(new Date()));
				return mav;
			}
		}
		if (dataFiStr == null || dataFiStr.trim().length() == 0) {
			dataFi = new Date();
			dataFiStr = SDF.format(dataFi);
		} else {
			try {
				dataFi = SDF.parse(dataFiStr);
			} catch (ParseException e) {
				HtmlUtils.saveMessageError(request, "Error en el format de la data de fi: " + dataFiStr);
				mav.addObject("dataFi", SDF.format(new Date()));
				return mav;
			}
		}
        
		mav.addObject("dataInici", dataIniciStr);
		mav.addObject("dataFinal", dataFiStr);
		        
        List<Registre> registres = new java.util.ArrayList<Registre>();
        List<Registre2> registres2 = new java.util.ArrayList<Registre2>();
        
		Timestamp timeInici = new Timestamp(atStartOfDay(dataInici).getTime());
        Timestamp timeFi = new Timestamp(atEndOfDay(dataFi).getTime());
        
        List<Solicitud> solicituds;
        List<IncidenciaTecnica> incidencies;
        try {
			log.info("Data inici: " + timeInici);
			log.info("Data fi: " + timeFi);
			solicituds = solicitudLogicaEjb.select(Where.AND(SolicitudFields.DATAINICI.between(timeInici, timeFi)));
			incidencies = incidenciaTecnicaLogicaEjb.select(Where.AND(IncidenciaTecnicaFields.DATAINICI.between(timeInici, timeFi)));

			for (Solicitud solicitud : solicituds) {
				if (solicitud.getOrganid() != null) {
					registres2.add(new Registre2("LOCAL", solicitud.getDataInici()));
				} else if (solicitud.getEntitatEstatal() != null) {
					registres2.add(new Registre2("ESTATAL", solicitud.getDataInici()));
				}
			}
			
			for (IncidenciaTecnica incidencia : incidencies) {
				switch (incidencia.getTipus()) {
				
				case Constants.INCIDENCIA_TIPUS_ROLEPERMISOS:
					registres2.add(new Registre2("ROL", incidencia.getDataInici()));
					break;
				case Constants.INCIDENCIA_TIPUS_CONSULTA:
					registres2.add(new Registre2("CONSULTA", incidencia.getDataInici()));
					break;
				case Constants.INCIDENCIA_TIPUS_TECNICA:
					registres2.add(new Registre2("TECNICA", incidencia.getDataInici()));
					break;
				case Constants.INCIDENCIA_TIPUS_INTEGRACIONS:
					registres2.add(new Registre2("INTEGRACIONS", incidencia.getDataInici()));
					break;
				}
			}
			
			//Quan tenim les dades, les ordenem per data i les agrupem per dia
			registres2.sort((r1, r2) -> r1.getData().compareTo(r2.getData()));
			
			Date auxDate; 
			if (registres2.size() == 0) {
				HtmlUtils.saveMessageError(request, "No hi ha dades per a les dates seleccionades");
				return mav;
			} else {
				auxDate = atEndOfDay(registres2.get(0).getData());
			}
			
			Registre registre = new Registre(auxDate);
			Registre totals = new Registre(auxDate);
			for (Registre2 reg2 : registres2) {
				if (reg2.getData().after(auxDate)) {
					registres.add(registre);
					auxDate = atEndOfDay(reg2.getData());
					registre = new Registre(auxDate);
				}
				registre.afegirDada(reg2.getTipus());
				totals.afegirDada(reg2.getTipus());
			}
			//afegim l'ultim registre
			registres.add(registre);
			
			mav.addObject("registres", registres);
			mav.addObject("totals", totals);
		} catch (I18NException e) {
			log.error("Error al obtenir les incidencies", e);
			HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
		}
    	return mav;
    }
    
    public static Date atStartOfDay(Date date) {
        return DateUtils.truncate(date, Calendar.DATE);
    }
    public static Date atEndOfDay(Date date) {
        return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
    }
    
    public class Registre2 {
    	private String tipus;
    	private Date data;
    	
		public Registre2(String tipus, Date data) {
			this.tipus = tipus;
			this.data = data;
		}
		
		public String getTipus() {
			return tipus;
		}
		public void setTipus(String tipus) {
			this.tipus = tipus;
		}
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}
    }
    
    
    public class Registre {
		private String data;
		private int solicitudsTotals;
		private int locals;
		private int estatals;
		private int incidenciesTotals;
		private int rolsIPermisos;
		private int consultes;
		private int tecniques;
		private int integracions;

		public Registre(Date data) {
			this.data = SDF2.format(data);
			
			this.solicitudsTotals = 0;
			this.locals = 0;
			this.estatals = 0;
			this.incidenciesTotals = 0;
			this.rolsIPermisos = 0;
			this.consultes = 0;
			this.tecniques = 0;
			this.integracions = 0;
		}

		public void afegirDada(String tipus) {
			switch (tipus) {
			case "LOCAL":
			case "ESTATAL":
				afegirSolicitud(tipus);
				break;
			case "ROL":
			case "CONSULTA":
			case "TECNICA":
			case "INTEGRACIONS":
				afegirIncidencia(tipus);
				break;
			}
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

		public int getSolicitudsTotals() {
			return solicitudsTotals;
		}

		public int getLocals() {
			return locals;
		}

		public int getEstatals() {
			return estatals;
		}

		public int getIncidenciesTotals() {
			return incidenciesTotals;
		}

		public int getRolsIPermisos() {
			return rolsIPermisos;
		}

		public int getConsultes() {
			return consultes;
		}

		public int getTecniques() {
			return tecniques;
		}

		public int getIntegracions() {
			return integracions;
        }
		
		public void afegirSolicitud(String tipus) {
			switch (tipus) {
            case "LOCAL":
                this.locals++;
                break;
            case "ESTATAL":
                this.estatals++;
                break;
            }
            this.solicitudsTotals++;
		}
		
		public void afegirIncidencia(String tipus) {
			switch (tipus) {
            case "ROL":
                this.rolsIPermisos++;
                break;
            case "CONSULTA":
                this.consultes++;
                break;
            case "TECNICA":
                this.tecniques++;
                break;
            case "INTEGRACIONS":
                this.integracions++;
                break;
            }
			this.incidenciesTotals++;
		}
    }
}
