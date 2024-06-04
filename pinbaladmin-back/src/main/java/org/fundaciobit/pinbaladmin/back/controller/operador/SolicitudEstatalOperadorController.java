package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.EmailUtil;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
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

	private static List<String> getDestinatarisDefault() {
        List<String> dests = new ArrayList<String>();
		dests.add("ptrias@fundaciobit.org");
		
		
		 dests.add("joanpau@triassegura.com");
//		 dests.add("atrobat@fundaciobit.org");
		// dests.add("pautrias2@gmail.com");
//		 dests.add("pvico@fundaciobit.org");
		return dests;
	}	
	
	public static class MailCedentInfo {
		private String id;
		private String subject;
		private String message;
		private List<String> dests;
		private List<Servei> serveis;

		public String getId() {
			return this.id;
		}
		public String getSubject() {
			return subject;
		}

		public String getMessage() {
			return message;
		}

		public List<String> getDests() {
			return dests;
		}

		public List<Servei> getServeis() {
			return serveis;
		}

		public MailCedentInfo(String id) {
			this.id = id;
			this.dests = new ArrayList<String>();
			this.serveis = new ArrayList<Servei>();

			String msg = "<div id=\"correu_cedent\">Bon dia,<br><br>Us enviam els formularis que ens han fet arribar des de <b>#ENTITAT#</b> per donar d'alta el següent servei:<br><b>#SERVEIS#</b><br>Esperam la vostra resposta.</div><style> #correu_cedent {margin: .5rem;} </style> ";
			switch (id) {
			case "DISCAPACITAT":
				this.subject = "Discapacitat - Cedent";
				this.message = msg;
				this.dests.add("amalorda@dgad.caib.es");

				this.dests = getDestinatarisDefault();
				break;
			case "FAM_NOMBROSA":
				this.subject = "Família Numerosa - Cedent";
				this.message = msg;
				this.dests.add("etid@imas.conselldemallorca.net");
				this.dests.add("cmp.sac@cime.es");

				this.dests = getDestinatarisDefault();
				break;
			case "INTERVENCIO":
				this.subject = "Intervenció CAIB - Cedent";
				this.message = msg;
				this.dests.add("cjimenez@interven.caib.es");

				this.dests = getDestinatarisDefault();
				break;
			case "PADRO":
				this.subject = "Padró - Cedent";
				this.message = "<div id=\"correu_padro\">" + "Benvolgut/da,<br>"
						+ "<br>A la comissió de seguiment del Conveni d'Interoperabilitat de dia 16 de juny de 2016 es va aprovar la modificació\r\n"
						+ "    del <a href=\"https://www.caib.es/sites/interoperabilitat/ca/archivopub.do?ctrl=MCRST12306ZI359290&id=359290\"\r\n"
						+ "        target=\"_blank\" rel=\"noreferrer\">protocol d'adhesió a un servei propi</a> per incorporar el següent canvi:<br>"

						+ "<br>Quan es demani accés a un servei propi (en aquest cas a un servei de padró):<em> \"Després de comprovar la\r\n"
						+ "        sol·licitud d'adhesió, la DGTIC enviarà la sol·licitud a la persona designada de cada entitat cedent de dades\r\n"
						+ "        del servei sol·licitat.&nbsp; S'assignaran els permisos a consultar segons la resolució de cada cedent. En el\r\n"
						+ "        cas que no hi hagi resposta per part d'algun o tots el cedents s'acceptarà la sol·licitud passat el termini de 7\r\n"
						+ "        dies naturals”.</em><br>"

						+ "<br>Li escrivim aquest correu on podrà veure la petició feta pel cessionari (entitat que vol accedir a la informació) i\r\n"
						+ "    el motiu de la consulta.<br>"

						+ "<br>Per això, li demanam que ens notifiqui si accedeix a que <b>#ENTITAT#</b>, si escau, accedeixi a\r\n"
						+ "    les seves dades de padró (sempre amb el consentiment exprés del ciutadà) pel Servei de consulta de dades\r\n"
						+ "    històriques sobre els padrons municipals per la tramitació del seu procediment: <b>#PROCEDIMENT#</b>.<br>"

						+ "<br>Així com marca el protocol després de 7 dies naturals sense resposta s'acceptarà la sol·licitud.</div>"

						+ "<style> #correu_padro {margin: .5rem;} </style>";

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

				dests = getDestinatarisDefault();
				break;
			}
		}

		public void afegirServei(Servei servei) {
			serveis.add(servei);
		}

		public void sendMail(SolicitudJPA soli, FitxerJPA excel) throws Exception {
			try {
//				log.info("CORREU PER ENVIAR");

				String subject = this.subject;
				boolean html = true;
				String from = Configuracio.getAppEmail();
				String[] dests = this.dests.toArray(new String[this.dests.size()]);
				String msg = null;

				if (subject.equals("Padró - Cedent")) {
					String procediment = soli.getProcedimentNom();
					msg = this.message.replaceAll("#PROCEDIMENT#", procediment);
				} else {
					String cadenaServeis = "<ul>";
					for (Servei servei : this.serveis) {
						cadenaServeis += "<li>" + "(CCAA) " + servei.getCodi() + ": " + servei.getNom() + "</li>";
					}
					cadenaServeis += "</ul>";

//					log.info("Serveis: \n" + cadenaServeis);
					msg = this.message.replaceAll("#SERVEIS#", cadenaServeis);
				}

				String entitat = soli.getEntitatEstatal();
				msg = msg.replaceAll("#ENTITAT#", entitat);

				msg += getPeuCorreu(soli.getSolicitudID());
				this.message = msg;

				EmailUtil.postMail(subject, msg, html, from, excel, dests);
//				log.info("CORREU ENVIAT");
			} catch (Exception e) {
				throw new Exception("Error al enviar correu", e);
			}
		}

		public void crearEvent(Long soliID, FitxerJPA excel, EventLogicaService eventLogicaEjb ) throws Exception {
			try {

				// Afegir events de correu enviat
				Timestamp data = new Timestamp(System.currentTimeMillis());
				int tipus = Constants.EVENT_TIPUS_CONSULTA_A_CEDENT;
				String cedent = this.id;

				EventJPA evt = new EventJPA();
				evt.setSolicitudID(soliID);
				evt.setDataEvent(data);
				evt.setTipus(tipus);
				evt.setPersona(cedent);

				String msg = this.message;// .replaceAll("<br>", "\n");
				evt.setComentari(msg);

				evt.setNoLlegit(false);
				evt.setDestinatari(this.dests.toString());
				evt.setDestinatarimail(this.dests.toString());

				evt.setFitxer(excel);

				eventLogicaEjb.create(evt);
			} catch (Exception e) {
				throw new Exception("Error al crear event", e);
			}

		}

		public void actualitzarEstatServei(Long soliID, SolicitudServeiService solicitudServeiEjb) throws Exception {
			try {
				Where wSoli = SolicitudServeiFields.SOLICITUDID.equal(soliID);
				
				Long estat = 40L; //ESTATS_SOLICITUD_SERVEI - 40L - Pendent d'autoritzar
				
				for (Servei servei : this.serveis) {
					Where wServei = ServeiFields.CODI.equal(servei.getCodi());

					List<SolicitudServei> list = solicitudServeiEjb.select(Where.AND(wSoli, wServei));
					if (list.size() == 1) {
						SolicitudServeiJPA solSer = (SolicitudServeiJPA) list.get(0);
						System.out.println("Actualitzant estat servei: " + servei.getCodi() + " - " + solSer.getEstatSolicitudServeiID() + " -> " + estat);
						
						solSer.setEstatSolicitudServeiID(estat);
						solicitudServeiEjb.update(solSer);
						
						Long nouEstat = solicitudServeiEjb.findByPrimaryKey(solSer.getId()).getEstatSolicitudServeiID();
						System.out.println("Actualitzat estat servei: " + nouEstat);
					}
				}
			} catch (Exception e) {
				throw new Exception("Error al actualitzar estat servei", e);
			}
		}
	}

	private static String getPeuCorreu(Long soliID) {
		String msg = "<div id=\"peu_correu\">"
				
				+ "<div id=\"reObrir\">"
				+ "    Podrà reobrir aquesta incidència o aportar més informació utilitzant el següent enllaç: <a"
				+ "        href=\"" + getLinkPublic(soliID) + "\"> Accedir a solicitud</a>"
				+ "</div>"

				+ "<div id=\"firma\">"
				+ "    Salutacions<br /> <i>Àrea de Govern Digital - Fundació BIT</i>"
				+ "</div>"

				+ "<div id=\"noContestar\">"
				+ "    Per favor, NO CONTESTEU directament aquest correu, per fer qualsevol consulta sobre la incidència accediu a l'enllaç"
				+ "    aportat en aquest correu."
				+ "</div>" 
				
				+ "  <style>" 
				+ "      #peu_correu {margin: .5rem;}"
				+ "      #peu_correu div {padding: .5rem 0;}"
				+ "      #noContestar {color: #868686; border: 4px double #868686; border-left: none; border-right: none;}" 
				+ "  </style>"
				
				+ "</div>";

		return msg;
	}

	private static String getLinkPublic(Long itemID) {
		String url = Configuracio.getAppUrl() + "/public/eventsolicitud" + "/veureevents/"
				+ HibernateFileUtil.encryptFileID(itemID);
		return url;
	}

	
	
	@RequestMapping(value = "/enviarcorreucedents/{soliID}", method = RequestMethod.GET)
	public String enviarcorreucedents(HttpServletRequest request, @PathVariable Long soliID) throws Exception {

		// Obtenir el codi dels serveis de la sol·licitud, i depenent del servei, enviar
		// un correu o un altre
		List<SolicitudServei> serveisSolicitud = solicitudServeiEjb.select(SolicitudServeiFields.SOLICITUDID.equal(soliID));

		MailCedentInfo discapacitat = new MailCedentInfo("DISCAPACITAT");
		MailCedentInfo famNombrosa = new MailCedentInfo("FAM_NOMBROSA");
		MailCedentInfo intervencio = new MailCedentInfo("INTERVENCIO");
		MailCedentInfo padro = new MailCedentInfo("PADRO");

		MailCedentInfo[] mails = { discapacitat, famNombrosa, intervencio, padro };

		for (SolicitudServei servei : serveisSolicitud) {
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

		SolicitudJPA soli = solicitudEjb.findByPrimaryKey(soliID);
		Long excelID = soli.getSolicitudXmlID();
		FitxerJPA excel = fitxerEjb.findByPrimaryKey(excelID);
		
		int errors = 0;
		
		for (MailCedentInfo mail : mails) {
			if (mail.serveis.size() > 0) {
				try {
					mail.sendMail(soli, excel);
					mail.crearEvent(soliID, excel, eventLogicaEjb);
					mail.actualitzarEstatServei(soliID, solicitudServeiEjb);
					String missatge = "Correu enviat a " + mail.id;
					log.info(missatge);
					HtmlUtils.saveMessageSuccess(request, missatge);
				} catch (Exception e) {
					errors++;
					String missatge = "Error al enviar correu a " + mail.id;
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
}
