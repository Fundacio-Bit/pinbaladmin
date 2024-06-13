package org.fundaciobit.pinbaladmin.logic.utils.email;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.EmailUtil;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;


public class MailCedentInfo {
    
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
		this.serveis = new ArrayList<Servei>();

		List<String> dests = new ArrayList<String>();

		String msg = "" + "<div class=\"correu_cedent\" style=\"margin: .5rem;\">" + "		Bon dia,<br>"
				+ "		<br>"
				+ "		Us enviam els formularis que ens han fet arribar des de <b>#ENTITAT#</b> per donar d'alta el següent servei:"
				+ "</div>"

				+ "<div class=\"serveis\"  style=\"margin: .5rem;\">" + "		<b>#SERVEIS#</b>" + "</div>";

		switch (id) {
		case "DISCAPACITAT":
			this.subject = "Discapacitat - Cedent";
			this.message = msg;
			dests.add("amalorda@dgad.caib.es");
			break;

		case "FAM_NOMBROSA":
			this.subject = "Família Numerosa - Cedent";
			this.message = msg;
			dests.add("etid@imas.conselldemallorca.net");
			dests.add("cmp.sac@cime.es");
			break;

		case "INTERVENCIO":
			this.subject = "Intervenció CAIB - Cedent";
			this.message = msg;
			dests.add("cjimenez@interven.caib.es");
			break;

		case "PADRO":
			this.subject = "Padró - Cedent";
			this.message = "<div id=\"correu_padro\"  style=\"margin: .5rem;\" >"

					+ "Benvolgut/da,<br>" + "<br>"
					+ "A la comissió de seguiment del Conveni d'Interoperabilitat de dia 16 de juny de 2016 es va aprovar la modificació"
					+ "del <a href=\"https://www.caib.es/sites/interoperabilitat/ca/archivopub.do?ctrl=MCRST12306ZI359290&id=359290\""
					+ "target=\"_blank\" rel=\"noreferrer\">protocol d'adhesió a un servei propi</a> per incorporar el següent canvi:<br>"

					+ "<br>Quan es demani accés a un servei propi (en aquest cas a un servei de padró):<em> \"Després de comprovar la"
					+ "        sol·licitud d'adhesió, la DGTIC enviarà la sol·licitud a la persona designada de cada entitat cedent de dades"
					+ "        del servei sol·licitat.&nbsp; S'assignaran els permisos a consultar segons la resolució de cada cedent. En el"
					+ "        cas que no hi hagi resposta per part d'algun o tots el cedents s'acceptarà la sol·licitud passat el termini de 7"
					+ "        dies naturals”.</em><br>"

					+ "<br>Li escrivim aquest correu on podrà veure la petició feta pel cessionari (entitat que vol accedir a la informació) i"
					+ "    el motiu de la consulta.<br>"

					+ "<br>Per això, li demanam que ens notifiqui si accedeix a que <b>#ENTITAT#</b>, si escau, accedeixi a"
					+ "    les seves dades de padró (sempre amb el consentiment exprés del ciutadà) pel Servei de consulta de dades"
					+ "    històriques sobre els padrons municipals per la tramitació del seu procediment: <b>#PROCEDIMENT#</b>.<br>"

					+ "<br>Així com marca el protocol després de 7 dies naturals sense resposta s'acceptarà la sol·licitud.</div>";

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

		this.dests = getDestinatarisDefault(dests);
	}

	public void afegirServei(Servei servei) {
		serveis.add(servei);
	}

//	public void sendMail(SolicitudJPA soli, FitxerJPA excel) throws Exception {
//		try {
//			String subject = this.subject;
//			boolean html = true;
//			String from = Configuracio.getAppEmail();
//			String[] dests = this.dests.toArray(new String[this.dests.size()]);
//			String msg = this.message;
//
//			if (subject.equals("Padró - Cedent")) {
//				String procediment = soli.getProcedimentNom();
//				msg = msg.replaceAll("#PROCEDIMENT#", procediment);
//			} else {
//				String cadenaServeis = "<ul>";
//				for (Servei servei : this.serveis) {
//					cadenaServeis += "<li>" + "(CCAA) " + servei.getCodi() + ": " + servei.getNom() + "</li>";
//				}
//				cadenaServeis += "</ul>";
//				
//				msg = msg.replaceAll("#SERVEIS#", cadenaServeis);
//			}
//
//			String entitat = soli.getEntitatEstatal();
//			msg = msg.replaceAll("#ENTITAT#", entitat);
//
//			msg += getPeuCorreu(soli.getSolicitudID(), "solicitud");
//			
//			this.message = msg;
//
//			EmailUtil.postMail(subject, msg, html, from, excel, dests);
//		} catch (Exception e) {
//			throw new Exception("Error al enviar correu", e);
//		}
//	}
	
	public void crearEvent(SolicitudJPA soli, FitxerJPA excel, EventLogicaService eventLogicaEjb) throws Exception {
		try {
			Timestamp data = new Timestamp(System.currentTimeMillis());
			int tipus = Constants.EVENT_TIPUS_CONSULTA_A_CEDENT;
			String cedent = this.id;

			EventJPA evt = new EventJPA();
			evt.setSolicitudID(soli.getSolicitudID());
			evt.setDataEvent(data);
			evt.setTipus(tipus);
			evt.setPersona(cedent);
			evt.setNoLlegit(false);

			// Camps per enviar el correu
			String subject = this.subject;

			String destinataris = ""; // = String.join(";", dests);
			for (String dest : this.dests) {
				destinataris += dest + ";";
			}

			String msg = this.message;

			if (subject.equals("Padró - Cedent")) {
				String procediment = soli.getProcedimentNom();
				msg = msg.replaceAll("#PROCEDIMENT#", procediment);
			} else {
				String cadenaServeis = "<ul>";
				for (Servei servei : this.serveis) {
					cadenaServeis += "<li>" + "(CCAA) " + servei.getCodi() + ": " + servei.getNom() + "</li>";
				}
				cadenaServeis += "</ul>";

				msg = msg.replaceAll("#SERVEIS#", cadenaServeis);
			}

			String entitat = soli.getEntitatEstatal();
			msg = msg.replaceAll("#ENTITAT#", entitat);

			evt.setComentari(msg);
			evt.setFitxer(excel);
			evt.setDestinatarimail(destinataris);
			evt.setAsumpte(subject);

			eventLogicaEjb.create(evt);
		} catch (Exception e) {
			throw new Exception("Error al crear event", e);
		}

	}

	public void actualitzarEstatServei(Long soliID, SolicitudServeiService solicitudServeiEjb) throws Exception {
		try {
			Where wSoli = SolicitudServeiFields.SOLICITUDID.equal(soliID);

			Long estat = 40L; // ESTATS_SOLICITUD_SERVEI - 40L - Pendent d'autoritzar

			for (Servei servei : this.getServeis()) {
				Where wServei = ServeiFields.CODI.equal(servei.getCodi());

				List<SolicitudServei> list = solicitudServeiEjb.select(Where.AND(wSoli, wServei));
				if (list.size() == 1) {
					SolicitudServeiJPA solSer = (SolicitudServeiJPA) list.get(0);
					System.out.println("Actualitzant estat servei: " + servei.getCodi() + " - "
							+ solSer.getEstatSolicitudServeiID() + " -> " + estat);

					solSer.setEstatSolicitudServeiID(estat);
					solicitudServeiEjb.update(solSer);

				}
			}
		} catch (Exception e) {
			throw new Exception("Error al actualitzar estat servei", e);
		}
	}

	private List<String> getDestinatarisDefault(List<String> dests) {

		String correus = Configuracio.getCorreusCedentsDefault();
		if (correus == null || correus.trim().length()== 0) {
			return dests;
		}
		String[] mails = correus.split(";");
		dests = new ArrayList<String>();
		for (int i = 0; i < mails.length; i++) {
			System.out.println("Afegint correu cedent per defecte: " + mails[i]);
			dests.add(mails[i]);
		}
		return dests;
	}	
	
}
