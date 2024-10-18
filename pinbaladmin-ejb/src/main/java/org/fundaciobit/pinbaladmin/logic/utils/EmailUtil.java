package org.fundaciobit.pinbaladmin.logic.utils;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

//import javax.jms.ObjectMessage;
//import javax.jms.Queue;
//import javax.jms.QueueConnection;
//import javax.jms.QueueConnectionFactory;
//import javax.jms.QueueSender;
//import javax.jms.QueueSession;
import javax.mail.Session;
import javax.mail.Transport;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;

//import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
//import org.fundaciobit.genapp.common.i18n.I18NException;

import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;

/**
 * 
 * @author anadal
 * 
 */
public class EmailUtil {

    /**
      * Envia un email a un {@link List} de emails
      * 
      * @param subject
      *          Asunto del Mensaje
      * @param message
      *          Contenido a enviar
      * @param from
      *          Indica la procedencia del mensaje
      * @param type
      *          Indica el con que de que tipo es el destinatario, Copia, Copia
      *          Oculta, etc
      * @param isHtml
      *          Decide si el contenido del mensaje a de ser visualizado en html o
      *          no
      * @param adjunt
      *          El fichero adjunto en caso de que lo hubiera
      * @param recipients
      *          Conjunto de emails para los que va dirigido el mensaje
      * @throws Exception
      */
    public static void postMail(String subject, String message, boolean isHtml, String from, FitxerJPA adjunt, String... recipients)
			throws Exception {

		boolean debug = Configuracio.isDesenvolupament();
		final Logger log = Logger.getLogger(EmailUtil.class);

		if (debug) {
			log.info("Provant log");
		}
		Context ctx = new InitialContext();
		if (debug) {
			log.info("ctx: " + ctx);
		}

		Session session = (javax.mail.Session) ctx.lookup(Constants.MAIL_SERVICE);

		if (debug) {
			log.info("session: " + session);
		}
		Set<Entry<Object, Object>> set = session.getProperties().entrySet();
		for (Entry<Object, Object> entry : set) {
			log.info(entry.getKey() + " : " + entry.getValue());
		}

		// Creamos el mensaje
		MimeMessage msg = new MimeMessage(session);

		if (debug) {
			log.info("msg: " + msg);
			log.info("PRE-from: " + from);
		}
//        from = "governdigital.pinbaladmin@fundaciobit.org";

		String noReply = "do-not-reply@fundaciobit.org";
		InternetAddress[] replyTo = { new InternetAddress(noReply) };
		msg.setReplyTo(replyTo);

		InternetAddress addressFrom = new InternetAddress(from);
		if (debug) {
			log.info("addressFrom: " + addressFrom);
		}
		msg.setFrom(addressFrom);

		// Indicamos los destinatarios
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
			if (debug) {
				log.info("addressTo[" + i + "]: " + addressTo[i]);
			}
		}

		final RecipientType type = RecipientType.TO;
		if (debug) {
			log.info("type: " + type);
		}
		msg.setRecipients(type, addressTo);

		// Configuramos el asunto
		msg.setSubject(subject, "UTF-8");
		msg.setSentDate(new Date());

		// Configuramos el contenido
		if (isHtml) {
			msg.setHeader("Content-Type", "text/html;charset=utf-8");

			Multipart multipart = new MimeMultipart();

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(message, "text/html; charset=utf-8");

			multipart.addBodyPart(messageBodyPart);

			if (adjunt != null) {

				if (debug) {
					log.info("fitxerJPA: " + adjunt.getNom() + " - mime: " + adjunt.getMime());
				}
				MimeBodyPart attachmentPart = new MimeBodyPart();
				File fileAdjunt = FileSystemManager.getFile(adjunt.getFitxerID());

				attachmentPart.setDataHandler(new DataHandler(new FileDataSource(fileAdjunt)));
				attachmentPart.setFileName(adjunt.getNom());

				multipart.addBodyPart(attachmentPart);
			}

			msg.setContent(multipart);
		} else {
			msg.setContent(message, "text/plain");
		}

		// Mandamos el mail
		try {
			if (debug) {
				log.info("Mandamos el mail: " + msg);
			}
			Transport.send(msg);
			if (debug) {
				log.info("Mail mandado: " + msg);
			}
		} catch (Throwable th) {
			log.error("Error amb correu: " + th.getMessage(), th);
			throw th;
		}
	}
    
    public static String getPeuCorreu(Long itemID, String tipus, String destinatari) {
    	
		if (destinatari == "SOPORTE") {
			destinatari = "Soporte Intermediación";
		}
		if (destinatari == "SUPORT") {
			destinatari = "Suport CAIB";
		}
    	
		//tipus pot ser "solicitud" o "incidencia"
		String url = Configuracio.getAppBackUrl() + "/public/event" + tipus + "/veureevents/"
				+ HibernateFileUtil.encryptFileID(itemID) + (destinatari == null ? "" : ("/" + HibernateFileUtil.encryptString(destinatari)));
		
		//Si es un correo a soporte, debe estar en castellano
		if (destinatari.equals("Soporte Intermediación")) {
			String msgEsp = "<div id=\"peu_correu\">" + "  <div id=\"reObrir\">"
					+ "     Para responder, por favor, utilice el siguiente enlace: <a" + "     href=\"" + url
					+ "\"> Acceder a solicitud</a>" + "  </div>" + "  <div id=\"firma\">"
					+ "		Saludos<br /> <i>Área de Gobierno Digital - Fundación BIT</i>" + "  </div>"
					+ "  <div id=\"noContestar\">"
					+ "		Por favor, NO CONTESTE directamente a este correo, para hacer cualquier consulta sobre la "
					+ tipus + " acceda al enlace aportado en este correo." + "  </div>" + "  <style>"
					+ "     #peu_correu {margin: .5rem;}" + "     #peu_correu div {padding: .5rem 0;}"
					+ "     #noContestar {color: #868686; border: 4px double #868686; border-left: none; border-right: none;}"
					+ "  </style>" + "</div>";
			return msgEsp;
		}
		
		String msg = "<div id=\"peu_correu\">"
				
				+ "  <div id=\"reObrir\">"
				+ "     Per respondre, contesteu, per favor, utilitzant el següent enllaç: <a"
				+ "     href=\"" + url + "\"> Accedir a solicitud</a>"
				+ "  </div>"

				+ "  <div id=\"firma\">"
				+ "		Salutacions<br /> <i>Àrea de Govern Digital - Fundació BIT</i>"
				+ "  </div>"

				+ "  <div id=\"noContestar\">"
				+ "		Per favor, NO CONTESTEU directament aquest correu, per fer qualsevol consulta sobre la " + tipus
				+ " accediu a l'enllaç aportat en aquest correu."
				+ "  </div>" 
				
				+ "  <style>" 
				+ "     #peu_correu {margin: .5rem;}"
				+ "     #peu_correu div {padding: .5rem 0;}"
				+ "     #noContestar {color: #868686; border: 4px double #868686; border-left: none; border-right: none;}" 
				+ "  </style>"
				
				+ "</div>";

		return msg;
	}
}
