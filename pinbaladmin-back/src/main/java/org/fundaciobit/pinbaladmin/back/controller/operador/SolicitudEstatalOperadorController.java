package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.utils.EmailUtil;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
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
		return false;
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

	
	private class MailCedentInfo {
		private String id;
		private String subject;
		private String message;
		private List<String> dests;
		private List<Servei> serveis;
		
		public MailCedentInfo(String id) {
			this.id = id;
			this.dests = new ArrayList<String>();
            this.serveis = new ArrayList<Servei>();

            String msg = "Bon dia,<br><br>Us envio formularis que ens han fet arribar des de Madrid per donar d'alta el següent servei:<br> <br>#SERVEI#<br> <br> Espero la vostra resposta. <br> <br> Salutacions.";
            String cadenaServeis;
            switch (id) {
			case "DISCAPACITAT":
				this.subject = "Discapacitat - Cedent";
				
				cadenaServeis = "(CCAA) SVDSCDDWS01: Consulta de Datos de Discapacidad";
				this.message = msg.replaceAll("#SERVEI#", cadenaServeis);
				
				//dests.add("amalorda@dgad.caib.es");
				dests.add("joanpau@triassegura.com");
				break;
			case "FAM_NOMBROSA":
				this.subject = "Família Numerosa - Cedent";
				
				cadenaServeis = "(CCAA) SVDSCTFNWS01: Consulta de Datos de Familia Numerosa";
				this.message = msg.replaceAll("#SERVEI#", cadenaServeis);

//				dests.add("etid@imas.conselldemallorca.net");
//				dests.add("cmp.sac@cime.es");
				dests.add("ptrias@fundaciobit.org");
				dests.add("pautrias2@gmail.com");
				break;
			case "INTERVENCIO":
				this.subject = "ATIB - Cedent";
				
				cadenaServeis = "(CCAA) SVDCCAACPCWS01: Corriente de pago para contratación";
				cadenaServeis += "(CCAA) SVDCCAACPASWS01: Corriente de pago para ayudas y subvenciones";
				
				this.message = msg.replaceAll("#SERVEI#", cadenaServeis);
				dests.add("cjimenez@interven.caib.es");
				break;
			case "PADRO":
				this.subject = "Padró - Cedent";
				this.message = "Benvolgut/da, <br> A la comissió de seguiment del Conveni d'Interoperabilitat de dia 16 de juny de 2016 es va aprovar la modificació del protocol d'adhesió a un servei propi per incorporar el següent canvi: <br> Quan es demani accés a un servei propi (en aquest cas a un servei de padró): \"Després de comprovar la sol·licitud d'adhesió, la DGTIC enviarà la sol·licitud a la persona designada de cada entitat cedent de dades del servei sol·licitat.  S'assignaran els permisos a consultar segons la resolució de cada cedent. En el cas que no hi hagi resposta per part d'algun o tots el cedents s'acceptarà la sol·licitud passat el termini de 7 dies naturals”. <br> Li escrivim aquest correu on podrà veure la petició feta pel cessionari (entitat que vol accedir a la informació) i el motiu de la consulta. <br> Per això, li demanam que ens notifiqui si accedeix a que l'Institut Balear de la Joventut, si escau, accedeixi a les seves dades de padró (sempre amb el consentiment exprés del ciutadà) pel servei Servei de consulta de dades històriques sobre els padrons municipals per la tramitació del seu procediment d'Inscripcions Art Jove. <br> Així com marca el protocol després de 7 dies naturals sense resposta s'acceptarà la sol·licitud. <br> Salutacions";
				dests.add("secretaria@ajferreries.org");
				dests.add("alcaldia@aj-alaior.org");
				dests.add("jnadal@ajalaro.net");
				dests.add("jcastaner@calvia.com");
				dests.add("padro@aj-escastell.org");
				dests.add("estadistica@ajciutadella.org");
				dests.add("aaznar@ajmao.org");
				dests.add("secretaria@ajmariadelasalut.net");
				dests.add("secretaria@esmercadal.es");
				dests.add("cpons@ajmigjorngran.org");
				dests.add("secretaria@ajmigjorngran.org");
				dests.add("tllado@palma.es");
				dests.add("alcaldia@ajsantlluis.org");
				dests.add("secretaria@ajllubi.net");
				dests.add("secretaria@ajsineu.net");
				dests.add("xisca@valldemossa.es");
				dests.add("imurillo@ajlloseta.net");
				break;
			}
		}
		
		public void afegirServei(Servei servei) {
			serveis.add(servei);
		}
		
		public void sendMail() throws Exception {
			log.info("CORREU PER ENVIAR");
			
			String subject = this.subject;
			String message = this.message;
			boolean html = true;
			String from = Configuracio.getAppEmail();
			FitxerJPA fitxers = null;
			String[] dests = this.dests.toArray(new String[this.dests.size()]);
			
			EmailUtil.postMail(subject, message, html, from, fitxers, dests);
			log.info("CORREU ENVIAT");
		}
		
		public void crearEvent(Long soliID) throws I18NException {
			// Afegir events de correu enviat
			Timestamp data = new Timestamp(System.currentTimeMillis());
			int tipus = Constants.EVENT_TIPUS_CONSULTA_A_CEDENT;
			String persona = "PinbalAdmin";

			EventJPA evt = new EventJPA();
			evt.setSolicitudID(soliID);
			evt.setDataEvent(data);
			evt.setTipus(tipus);
			evt.setPersona(persona);
			
			String msg = this.message.replaceAll("<br>", "\n");
			evt.setComentari(msg);
			
			evt.setNoLlegit(false);
			evt.setDestinatari(this.dests.toString());
			evt.setDestinatarimail(this.dests.toString());

			eventLogicaEjb.create(evt);
		}
	}
	
	@RequestMapping(value = "/enviarcorreucedents/{soliID}", method = RequestMethod.GET)
	public String enviarcorreucedents(HttpServletRequest request, @PathVariable Long soliID) throws Exception {

		// Obtenir el codi dels serveis de la sol·licitud, i depenent del servei, enviar
		// un correu o un altre
		List<SolicitudServei> serveis = solicitudServeiEjb.select(SolicitudServeiFields.SOLICITUDID.equal(soliID));

		MailCedentInfo discapacitat = new MailCedentInfo("DISCAPACITAT");
		MailCedentInfo famNombrosa = new MailCedentInfo("FAM_NOMBROSA");
		MailCedentInfo intervencio = new MailCedentInfo("INTERVENCIO");
		MailCedentInfo padro = new MailCedentInfo("PADRO");
		
		MailCedentInfo[] mails = {discapacitat, famNombrosa, intervencio, padro};
		
		for (SolicitudServei servei : serveis) {
			Servei serv = serveiEjb.findByPrimaryKey(servei.getServeiID());
			String codi = serv.getCodi();
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
		
		for (MailCedentInfo mail : mails) {
			if (mail.serveis.size() > 0) {
				try {
					mail.sendMail();
					mail.crearEvent(soliID);
				} catch (Exception e) {
					log.error("Error al enviar correu", e);
				}
			}
		}
		String missatje = "Enviats correus als cedents";
		HtmlUtils.saveMessageSuccess(request, missatje);

		return "redirect:" + "/operador/solicitudfullview" + "/view/" + soliID;
	}
}
