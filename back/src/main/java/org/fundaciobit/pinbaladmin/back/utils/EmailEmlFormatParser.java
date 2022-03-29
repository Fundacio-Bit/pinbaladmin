package org.fundaciobit.pinbaladmin.back.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.fundaciobit.pinbaladmin.logic.utils.EmailAttachmentInfo;

/**
 * 
 * @author anadal
 *
 */
public class EmailEmlFormatParser {

  public static EmailMessageInfo parserEml(InputStream inEmlData) throws Exception {

    Properties props = new Properties();
    Session session = Session.getDefaultInstance(props, null);

    Message msg = new MimeMessage(session, inEmlData);
    return parseEml(msg);
  }

  private static EmailMessageInfo parseEml(Message msg) throws Exception {

    EmailMessageInfo emi = new EmailMessageInfo();

    // información del remitente
    emi.setDisplayFrom(getRecipients(msg.getFrom()));

    emi.setDisplayTo(getRecipients(msg.getRecipients(RecipientType.TO)));
    emi.setDisplayCC(getRecipients(msg.getRecipients(RecipientType.CC)));
    emi.setDisplayBCC(getRecipients(msg.getRecipients(RecipientType.BCC)));

    emi.setSubject(msg.getSubject());

    List<EmailAttachmentInfo> attachments = new ArrayList<EmailAttachmentInfo>();
    emi.setAttachments(attachments);

    // getContent () es obtener el contenido del paquete, Part es equivalente al
    // paquete externo
    Object o = msg.getContent();
    if (o instanceof Multipart) {
      Multipart multipart = (Multipart) o;
      reMultipart(multipart, emi);
    } else if (o instanceof Part) {
      Part part = (Part) o;
      rePart(part, emi);
    } else {
      System.out.println("Tipo" + msg.getContentType());
      System.out.println("Contenido" + msg.getContent());
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

  /**
   * @param part
   *          Analizar el contenido
   * @throws Exception
   */
  private static void rePart(Part part, EmailMessageInfo emi) throws Exception {

    if (part.getDisposition() != null) {

      String strFileNmae = part.getFileName();
      if (!StringUtils.isEmpty(strFileNmae)) {// MimeUtility.decodeText resuelve
                                              // el problema de los nombres de
                                              // archivos adjuntos confusos
        strFileNmae = MimeUtility.decodeText(strFileNmae);
        // System.out.println ("Adjunto encontrado:" + strFileNmae);

        InputStream in = part.getInputStream(); // Abrir el flujo de entrada del
                                                // adjunto
        // Leer los bytes adjuntos y almacenarlos en el archivo
        java.io.FileOutputStream out = new FileOutputStream(strFileNmae);
        int data;
        while ((data = in.read()) != -1) {
          out.write(data);
        }
        in.close();
        out.close();

      }

      // System.out.println ("Tipo de contenido:" + MimeUtility.decodeText
      // (part.getContentType ()));
      // System.out.println ("Contenido del adjunto:" + part.getContent ());

      List<EmailAttachmentInfo> attachments = emi.getAttachments();
     
      String mime = MimeUtility.decodeText(part.getContentType());
      int pos =  mime.indexOf(';');
      if (pos != -1) {
        mime = mime.substring(0, pos);
      }
      
      attachments.add(
          new EmailAttachmentInfo(strFileNmae, mime,
              IOUtils.toByteArray(part.getInputStream())));

    } else {

      if (part.getContentType().startsWith("text/plain")) {
        // Contenido de texto
        emi.setBody(part.getContent().toString());
      } else {
        // Contenido HTML
        if (emi.getBody() == null) {
          emi.setBody(part.getContent().toString());
        }
      }

    }
  }

  /**
   * @param multipart
   *          // Recibir y descargar paquetes (incluido todo el contenido del
   *          correo (paquete + cuerpo + archivo adjunto))
   * @throws Exception
   */
  private static void reMultipart(Multipart multipart, EmailMessageInfo emi) throws Exception {
    // System.out.println ("correo total" + multipart.getCount () + "composición
    // parcial");
    // Procesar cada parte por turno
    for (int j = 0, n = multipart.getCount(); j < n; j++) {
      // System.out.println ("Sección de procesamiento" + j + "parte");
      Part part = multipart.getBodyPart(j); // Desempaquetar, sacar las partes
                                            // de MultiPart,
      // Cada parte puede ser el contenido del correo electrónico,
      // También puede ser otro paquete pequeño (MultipPart)
      // Determine si el contenido de este paquete es un paquete pequeño,
      // generalmente esta parte es el cuerpo Content-Type: multipart /
      // Alternative
      if (part.getContent() instanceof Multipart) {
        Multipart p = (Multipart) part.getContent(); // Convertir a paquete
                                                     // pequeño
        // iteración recursiva
        reMultipart(p, emi);
      } else {
        rePart(part, emi);
      }
    }
  }

}
