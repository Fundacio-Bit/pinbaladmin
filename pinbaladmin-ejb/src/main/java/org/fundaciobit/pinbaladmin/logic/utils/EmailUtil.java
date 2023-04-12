package org.fundaciobit.pinbaladmin.logic.utils;

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
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

//import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
//import org.fundaciobit.genapp.common.i18n.I18NException;

import org.fundaciobit.pinbaladmin.commons.utils.Constants;

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
      * @param recipients
      *          Conjunto de emails para los que va dirigido el mensaje
      * @throws Exception
      */
    public static void postMail(String subject, String message, boolean isHtml, String from, String... recipients)
            throws Exception {

        
        final Logger log = Logger.getLogger(EmailUtil.class);

        log.info("Provant log");
        
        Context ctx = new InitialContext();
        log.info("ctx: " + ctx);
        
        Session session = (javax.mail.Session) ctx.lookup(Constants.MAIL_SERVICE);
        log.info("session: " + session);
        
        Set<Entry<Object, Object>> set = session.getProperties().entrySet();
        for (Entry<Object, Object> entry : set) {
            log.info(entry.getKey() + " : " + entry.getValue());
        }
        
        // Creamos el mensaje
        MimeMessage msg = new MimeMessage(session);
        log.info("msg: " + msg);

        log.info("PRE-from: " + from);
        
//        from = "governdigital.pinbaladmin@fundaciobit.org";
        
        String noReply = "do-not-reply@fundaciobit.org";
        InternetAddress[] replyTo = {new InternetAddress(noReply)};
        msg.setReplyTo(replyTo);
        
        InternetAddress addressFrom = new InternetAddress(from);
        log.info("addressFrom: " + addressFrom);
        msg.setFrom(addressFrom);

        // Indicamos los destinatarios
        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
            log.info("addressTo[" + i + "]: " + addressTo[i]);
        }

        final RecipientType type = RecipientType.TO;
        log.info("type: " + type);

        msg.setRecipients(type, addressTo);

        // Configuramos el asunto
        msg.setSubject(subject, "UTF-8");
        msg.setSentDate(new Date());

        // Configuramos el contenido
        if (isHtml) {
            msg.setHeader("Content-Type", "text/html;charset=utf-8");
            /*
            URL urlToAdd = new URL(url);
            msg.setDataHandler(new DataHandler(urlToAdd));
            */
            msg.setContent(message, "text/html;charset=utf-8");
        } else {
            msg.setContent(message, "text/plain" /*; charset=UTF-8"*/);
        }

        // Mandamos el mail
        try {
            log.info("Mandamos el mail: " + msg);
            Transport.send(msg);
            log.info("Mail mandado: " + msg);
        }catch(Throwable th) {
            log.error("Error amb correu: " + th.getMessage(), th);
            throw th;
        }

    }

}
