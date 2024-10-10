package org.fundaciobit.pinbaladmin.back.utils.email;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeUtility;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailAttachmentInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;


/**
 * 
 * @author anadal
 *
 */
public class EmailEmlFormatParser {
  
  public static final Logger log = Logger.getLogger(EmailEmlFormatParser.class);

  public static EmailMessageInfo parserEml(InputStream inEmlData) throws Exception {

    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    Message msg = new MimeMessage(session, inEmlData);
    return parseEml(msg, true);
  }

	public static EmailMessageInfo parseEml(Message msg, boolean includeAttachments) throws Exception {

		if (msg == null) {
			return null;
		}

		EmailMessageInfo emi = new EmailMessageInfo();
//		log.info("parseEml: " + msg.getMessageNumber());
		emi.setNumber(msg.getMessageNumber());

		// Este hace referencia a la columna Origen (message2email()),i a correo de contacto en Incidencia, y Solicitud
		String replyTo = getRecipients(msg.getReplyTo());
		// Este hace referencia a nombre de persona de contacto en Incidencia,y Solicitud
		String replyToName;
		
//		log.info("UEP replyTo: " + replyTo);
		if (replyTo == null || replyTo.equals("null") || replyTo.trim().equals("")) {
			replyTo = getRecipients(msg.getFrom());
			replyToName = getNames(msg.getFrom());
		}else {
			replyToName = getNames(msg.getReplyTo());
			if (replyToName == null || replyToName.equals("null") || replyToName.trim().equals("")) {
				replyToName = getNames(msg.getFrom());
			}
		}
		emi.setDisplayFrom(replyTo); 
		emi.setNameFrom(replyToName); 
		
		// Informacion de los destinatarios
		emi.setDisplayTo(getRecipients(msg.getRecipients(RecipientType.TO)));
		emi.setDisplayCC(getRecipients(msg.getRecipients(RecipientType.CC)));
		emi.setDisplayBCC(getRecipients(msg.getRecipients(RecipientType.BCC)));

		emi.setNameTo(getNames(msg.getRecipients(RecipientType.TO)));
		emi.setNameCC(getNames(msg.getRecipients(RecipientType.CC)));
		emi.setNameBCC(getNames(msg.getRecipients(RecipientType.BCC)));

//    emi.set
//    msg.getReplyTo()

		emi.setSubject(msg.getSubject());
		emi.setSentDate(msg.getSentDate());

		List<EmailAttachmentInfo> attachments = new ArrayList<EmailAttachmentInfo>();
		emi.setAttachments(attachments);

		// true en dos ocasiones. Ver mensajes en el listado, y obtener un mensaje con
		// er.getMessage()
		if (includeAttachments) {

			// getContent () es obtener el contenido del paquete, Part es equivalente al
			// paquete externo
			Object o = msg.getContent();
			if (o instanceof Multipart) {
				Multipart multipart = (Multipart) o;
				log.info("Es multipart. " + multipart.getCount() + " parts" );
				reMultipart(multipart, emi);
			} else if (o instanceof Part) {
				log.info("Es part");
				Part part = (Part) o;
				rePart(part, emi);
			} else if (o instanceof String) {
				if (msg.getContentType().indexOf("text/plain") != -1) {
					log.info("Es String text/plain");
					emi.setBody((String) o);
				} else if (msg.getContentType().indexOf("text/html") != -1) {
					log.info("Es String text/html");
					emi.setBody((String) o);
				} else {
					log.info("Format String extrany " + msg.getContentType());
				}
			} else {
				log.info("Es algo diferent");
				System.err.println("---- Attachemnt Desconegut ----");
				System.err.println("Class: " + o.getClass());
				System.err.println("mime: " + msg.getContentType());
				System.err.println("Contingut: " + msg.getContent());
			}
		} else {
			// Aqui solo llegamos cuando estamos en listado sin adjuntos.
			Object o = msg.getContent();

			String mime = msg.getContentType();
			int num = msg.getMessageNumber();
			if (o instanceof String) {
				
				if (mime.indexOf("text/plain") != -1) {
					log.info("emi " + num + " -> text/plain");
				} else if (mime.indexOf("text/html") != -1) {
					log.info("emi " + num + " -> text/html");
				}
				emi.setBody((String) o);
			} else if (o instanceof Multipart) {
				int pos = mime.indexOf(';');
				if (pos != -1) {
					mime = mime.substring(0, pos);
				}
				log.info("emi " + num + " -> multipart: " + mime);
			} else {
				log.info("emi " + num + " -> mime: " + mime);
			}
		}
		return emi;
	}

  private static String getRecipients(Address[] froms) {
    String fromsStr = "";
    if (froms != null) {
      // System.out.println ("Información del remitente:" + froms [0]);
      InternetAddress addr = (InternetAddress) froms[0];

      if (fromsStr.length() != 0) {
        fromsStr = fromsStr + ",";
      }

      fromsStr = fromsStr + addr.getAddress();

      // System.out.println ("Dirección del remitente:" + addr.getAddress());
      // System.out.println ("Nombre para mostrar del remitente:" +
      // addr.getPersonal());
    }
    return fromsStr;
  }

  private static String getNames(Address[] froms) {
    String fromsStr = "";
    if (froms != null) {
      // System.out.println ("Información del remitente:" + froms [0]);
      InternetAddress addr = (InternetAddress) froms[0];

      if (fromsStr.length() != 0) {
        fromsStr = fromsStr + ",";
      }

      fromsStr = fromsStr + addr.getPersonal();

      // System.out.println ("Dirección del remitente:" + addr.getAddress());
      // System.out.println ("Nombre para mostrar del remitente:" +
      // addr.getPersonal());
    }
    return fromsStr;
  }

  /**
   * @param part
   *          Analizar el contenido
   * @throws Exception
   */
	private static void rePart(Part part, EmailMessageInfo emi) throws Exception {

		String disposition = part.getDisposition();
		if (disposition == null) {
            // Si el contenido no tiene una disposición, se considera como texto
			log.info("rePart: No disposition. " + part.getContentType());
			if (part.getContentType().startsWith("text/plain") || part.getContentType().startsWith("text/html")){
				emi.setBody(part.getContent().toString());
//				log.info("\n\n" + emi.getBody() + "\n\n");
			}
            return;
		} else if (disposition.equals(Part.ATTACHMENT)) {
			// Si es un archivo adjunto, lo descargamos y lo añadimos a la lista de archivos adjuntos
			
			List<EmailAttachmentInfo> attachments = emi.getAttachments();
			
			String fileName = MimeUtility.decodeText(part.getFileName());

			byte[] data = IOUtils.toByteArray(part.getInputStream());
			
			String mime = MimeUtility.decodeText(part.getContentType());
			int pos = mime.indexOf(';');
			if (pos != -1) {
				mime = mime.substring(0, pos);
			}
			
			log.info("rePart: Adjunto ]" + fileName + "; " + mime + "; " + data.length + " bytes[");
			
			//Log headers per veure que podem treure: part.getAllHeaders()
//			Iterator<Header> ite = part.getAllHeaders().asIterator();
//			while (ite.hasNext()) {
//				Header header = ite.next();
//				log.info("rePart: Header: " + header.getName() + ": " + header.getValue());
//			}
			
			EmailAttachmentInfo attachment = new EmailAttachmentInfo(fileName, mime, data);
			attachments.add(attachment);
			return;
		} else {
			// Un caso como este es de un correo con lleva adjnutos dentro del cuerpo del
			// correo, como imagenes, logos, etc
			if (part.getContentType().startsWith("text/plain") || part.getContentType().startsWith("text/html")) {
				if (emi.getBody() == null || emi.getBody().trim().equals("")) {
					emi.setBody(part.getContent().toString());
				}
			}else {
				
				String mime = part.getContentType(); //MimeUtility.decodeText(part.getContentType());
				byte[] data = IOUtils.toByteArray(part.getInputStream());

				if (mime.startsWith("image")) {
					String cid = part.getHeader("Content-Id")[0].replaceAll("[<>]", "");
					log.info("rePart: Imagen incrustada. " + part.getFileName() + "cid: " + cid);
//					log.info("rePart: cid: " + cid);
					
		            String base64Image = fileToBase64(data);
		            
		            String body = emi.getBody();
		            
		            //<img src="cid:part5.vjdBNrcs.e1KHVQwz@fundaciobit.org"
		            //<img src="data:image/png;base64, iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==" 
		            
		            String newBody = body.replace("cid:"+cid, "data:" + mime + ";base64, " + base64Image);
	                emi.setBody(newBody);
				} else {
					log.info("rePart: Adjunto incrustado, no imagen. " + mime);
					
					List<EmailAttachmentInfo> attachments = emi.getAttachments();
					String fileName = "internal-" + MimeUtility.decodeText(part.getFileName());
					int pos = mime.indexOf(';');
					if (pos != -1) {
						mime = mime.substring(0, pos);
					}
					EmailAttachmentInfo attachment = new EmailAttachmentInfo(fileName, mime, data);
					attachments.add(attachment);
				}
			}
			return;
		}
	}

	protected static String fileToBase64(byte[] data) throws FileNotFoundException, IOException {
		String base64 = Base64.getEncoder().encodeToString(data);
		return base64;
	}
	
	
	
  /**
   * @param multipart
   *          Recibir y descargar paquetes (incluido todo el contenido del
   *          correo (paquete + cuerpo + archivo adjunto))
   * @throws Exception
   */
	private static void reMultipart(Multipart multipart, EmailMessageInfo emi) throws Exception {

		/*
		 * Este método se encarga de dividir el correo en varias partes y analizar cada
		 * una de ellas. El metodo rePart se encarga de analizar cada estas partes del
		 * correo. Que puede ser tanto el texto del correo, como archivos adjuntos. Hay
		 * paquetes que pueden ser parte de otros paquetes, por lo que se debe analizar
		 * recursivamente.
		 */
		
		for (int j = 0, n = multipart.getCount(); j < n; j++) {
			Part part = multipart.getBodyPart(j);
			if (part.getContent() instanceof Multipart) {
				Multipart p = (Multipart) part.getContent();
				log.info("reMultipart: " + p.getCount() + " parts");
				reMultipart(p, emi);
			} else {
				rePart(part, emi);
			}
		}
	}

}
