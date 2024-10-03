package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.EnumUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;

import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.utils.email.MailCedentInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.MailCedentInfo.CEDENTS_LOCALS;
import org.fundaciobit.pinbaladmin.logic.utils.email.MailCedentInfo.CODIS_SERVEIS_LOCALS;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
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
@RequestMapping(value = SolicitudEstatalOperadorController.CONTEXTWEB)
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudEstatalOperadorController extends SolicitudOperadorController {

	public static final String CONTEXTWEB = "/operador/solicitudestatal";

	@Override
	public Boolean isEstatal() {
		return true;
	}

	@Override
	public boolean showAdvancedFilter() {
		return true;
	}

	/**
	 * Llistat de totes Solicitud
	 */
	@RequestMapping(value = "/list/{start}/{end}", method = RequestMethod.GET)
	public String llistat(HttpServletRequest request, HttpServletResponse response, @PathVariable("start") Long start,
			@PathVariable("end") Long end) throws I18NException {

		int pagina = 1;

		log.info("START => " + start);
		log.info("END   => " + end);

		log.info("START => " + new Timestamp(start));
		log.info("END   => " + new Timestamp(end));

		SolicitudFilterForm ff;
		ModelAndView mav = new ModelAndView(getTileList());
		ff = getSolicitudFilterForm(pagina, mav, request);

		request.getSession().setAttribute(getSessionAttributeFilterForm(), ff);

		ff.setDataIniciDesde(new Timestamp(start - 2000));
		ff.setDataIniciFins(new Timestamp(end + 2000));
		ff.setVisibleFilterBy(true);

		// return mav;
		return "redirect:" + getContextWeb() + "/list/" + pagina;
	}


	@RequestMapping(value = "/enviarcorreucedents/{soliID}", method = RequestMethod.GET)
	public String enviarcorreucedents(HttpServletRequest request, @PathVariable Long soliID) throws Exception {

		// Obtenir el codi dels serveis de la sol·licitud, i depenent del servei, enviar
		// un correu o un altre
		List<SolicitudServei> serveisSolicitud = solicitudServeiEjb.select(SolicitudServeiFields.SOLICITUDID.equal(soliID));

		Map<CEDENTS_LOCALS, MailCedentInfo> mails = new HashMap<CEDENTS_LOCALS, MailCedentInfo>();
		for (CEDENTS_LOCALS cedent : CEDENTS_LOCALS.values()) {
			mails.put(cedent, new MailCedentInfo(cedent));
		}
		
		for (SolicitudServei servei : serveisSolicitud) {
			Servei serv = serveiEjb.findByPrimaryKey(servei.getServeiID());
			String codi = serv.getCodi();
			if (EnumUtils.isValidEnum(CODIS_SERVEIS_LOCALS.class, codi)) {
				CODIS_SERVEIS_LOCALS codiEnum = CODIS_SERVEIS_LOCALS.valueOf(codi);
				switch (codiEnum) {
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
		}

		SolicitudJPA soli = solicitudEjb.findByPrimaryKey(soliID);
		Long excelID = soli.getSolicitudXmlID();
		FitxerJPA excel = fitxerEjb.findByPrimaryKey(excelID);
		
		int errors = 0;
		
		for (MailCedentInfo mail : mails.values()) {
			if (mail.getServeis().size() > 0) {
				try {
//					mail.sendMail(soli, excel);
					mail.crearEvent(soli, excel, eventLogicaEjb);
					mail.actualitzarEstatServei(soliID, solicitudServeiEjb);
					String missatge = "Correu enviat a " + mail.getId();
					log.info(missatge);
					HtmlUtils.saveMessageSuccess(request, missatge);
				} catch (Exception e) {
					errors++;
					String missatge = "Error al enviar correu a " + mail.getId();
					log.error(missatge, e);
					HtmlUtils.saveMessageError(request, missatge);
				}
			}
		}
		
		if (errors > 0) {
			HtmlUtils.saveMessageError(request, "Hi ha hagut errors en l'enviament de correus");
		} else {
			soli.setEstatID(Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR);
			solicitudEjb.update(soli);
			HtmlUtils.saveMessageSuccess(request, "Correus enviats correctament");
		}
		return "redirect:" + "/operador/solicitudfullview" + "/view/" + soliID;
	}
	
	@Override
    public String getEntityNameCode() {
        switch (getVistaIncidencia()) {
            default:
            case NORMAL:
                return "solicitud.estatal";
            case NOLLEGITSMEUS:
                return "solicitud.estatal.nollegitsmeus";
            case NOLLEGITSNOMEUS:
                return "solicitud.estatal.nollegitsnomeus";
        }
    }

    @Override
    public String getSessionAttributeFilterForm() {

        switch (getVistaIncidencia()) {
            default:
            case NORMAL:
                return super.getSessionAttributeFilterForm();
            case NOLLEGITSMEUS:
                return "solicitud.estatal.nollegitsmeus" + super.getSessionAttributeFilterForm();
            case NOLLEGITSNOMEUS:
                return "solicitud.estatal.nollegitsnomeus" + super.getSessionAttributeFilterForm();
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
                
                w1 = Where.AND(OPERADOR.equal(request.getRemoteUser()), SOLICITUDID.in(subQuery), ENTITATESTATAL.isNotNull());
            }
            break;
            case NOLLEGITSNOMEUS: {
                // incidencies No Meves
                SubQuery<Event, Long> subQuery = eventLogicaEjb.getSubQuery(EventFields.SOLICITUDID,
                        Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE), EventFields.SOLICITUDID.isNotNull()));
                
                w1 = Where.AND(OPERADOR.notEqual(request.getRemoteUser()), SOLICITUDID.in(subQuery), ENTITATESTATAL.isNotNull());
            }
            break;

        }

//        log.info("\n\n SQL W1 = " + ((w1 == null) ? "NULL" : w1.toSQL()));

        Where w2 = super.getAdditionalCondition(request);
//        log.info("\n\n SQL W2 = " + ((w2 == null) ? "NULL" : w2.toSQL()));

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
//          solicitudFilterForm.getHiddenFields().remove(ORGANID);

			if (getVistaIncidencia() == VistaIncidencia.NORMAL) {
				// solicitudFilterForm.setEstatIDDesde(-1L);
				// solicitudFilterForm.setEstatIDFins(50L);
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
}
