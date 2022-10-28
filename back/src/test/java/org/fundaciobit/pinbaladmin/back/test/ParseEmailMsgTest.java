package org.fundaciobit.pinbaladmin.back.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.fundaciobit.pinbaladmin.back.utils.Utils;
import org.fundaciobit.pinbaladmin.back.utils.email.EmailMsgFormatParser;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;

/**
 * 
 * @author anadal
 *
 */
public class ParseEmailMsgTest {

  public static void main(String[] args) {

    try {
      //File file = new File(".//PID_1173329_Alta_Ayuntamiento_Norena.msg");
      File file = new File(".//PID_1182046_AYUNTAMIENTO_CORVERA_ASTURIAS.msg");
      

      FileInputStream fis = new FileInputStream(file);
      try {
        EmailMessageInfo emi = EmailMsgFormatParser.processMessage(fis);

        System.out.println(emi.toString());

        String pid;
        pid = Utils.getPidFromSubject(emi.getSubject());
        System.out.println(" PID => |" + pid + "|");

      } finally {
        fis.close();
      }

    } catch (IOException e) {
      System.err.println("Could not process " + e.getMessage());
      e.printStackTrace();
    }

  }

}
