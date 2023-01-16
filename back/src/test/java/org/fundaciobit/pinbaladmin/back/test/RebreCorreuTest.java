package org.fundaciobit.pinbaladmin.back.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.fundaciobit.pinbaladmin.back.utils.email.EmailReader;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;

/**
 * Ejemplo de recepción de mensajes con JavaMail
 *
 * @author anadal
 *
 */
public class RebreCorreuTest {
  /**
   * main de la clase.
   * 
   */
  public static void main(String[] args) {
    try {

      Properties prop = new Properties();
      prop.load(new FileInputStream(new File("servidordecorreu.properties")));

      final boolean enableCertificationCheck = false;
      EmailReader er = new EmailReader(prop, enableCertificationCheck);

      long start = System.currentTimeMillis();
      List<EmailMessageInfo> emails = er.list(1,10);
      
      System.out.println("Time: " + ((System.currentTimeMillis() - start) /1000) );
      

      for (EmailMessageInfo emailMessageInfo : emails) {
        System.out.println(emailMessageInfo.toString());

        emailMessageInfo = er.getMessage(emailMessageInfo.getNumber());

        System.out.println(emailMessageInfo);

      }

      System.out.println("FINAL !!!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
