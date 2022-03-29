package org.fundaciobit.pinbaladmin.back.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.fundaciobit.pinbaladmin.back.utils.EmailEmlFormatParser;
import org.fundaciobit.pinbaladmin.back.utils.EmailMessageInfo;

/**
 * 
 * @author anadal
 *
 */
public class ParseEmailEmlTest {

  public static void main(String args[]) throws Exception {

    File f = new File("./[governdigital.pinbal][PID][1164074]_Alta_Ayuntamiento_Alcala.eml");

    InputStream inMsg = new FileInputStream(f);

    EmailMessageInfo emi =  EmailEmlFormatParser.parserEml(inMsg);

    inMsg.close();
    
    System.out.println(emi.toString());
    
    String pid;
    pid = EmailMessageInfo.getPidFromSubject(emi.getSubject());
    System.out.println(" PID => |" + pid + "|");

  }

  /*
   * public static void test(String emlPath) { try {
   * 
   * System.out.println(emlPath); Properties props = new Properties(); Session
   * session = Session.getDefaultInstance(props, null); InputStream inMsg; inMsg
   * = new FileInputStream(emlPath); Message msg = new MimeMessage(session,
   * inMsg);
   * 
   * String[] date = msg.getHeader("Date"); Address[] from = msg.getFrom(); for
   * (Address address : from) { InternetAddress internetAddress =
   * (InternetAddress) address;
   * System.out.println(internetAddress.getAddress());
   * System.out.println(internetAddress.getPersonal()); }
   * System.out.println(msg.getSubject());
   * 
   * Address[] to = msg.getReplyTo();
   * 
   * Object o = msg.getContent();
   * 
   * if (msg.isMimeType("multipart/*") || msg.isMimeType("MULTIPART/*")) {
   * System.out.println("multipart"); Multipart mp = (Multipart) o;
   * 
   * int totalAttachments = mp.getCount(); if (totalAttachments > 0) { for (int
   * i = 0; i < totalAttachments; i++) { Part part = mp.getBodyPart(i); String s
   * = getMailContent(part); String attachFileName = part.getFileName(); String
   * disposition = part.getDisposition(); String contentType =
   * part.getContentType(); if ((attachFileName != null && attachFileName
   * .endsWith(".ics")) || contentType.indexOf("text/calendar") >= 0) { String[]
   * dateHeader = msg.getHeader("date"); }
   * 
   * System.out.println(s); System.out.println(attachFileName);
   * System.out.println(disposition); System.out.println(contentType);
   * System.out.println("=============="); } inMsg.close(); } } else if (o
   * instanceof Part) { Part part = (Part) o; rePart(part); } else {
   * System.out.println ("Tipo" + msg.getContentType ()); System.out.println
   * ("Contenido" + msg.getContent ()); } } catch (Exception e) {
   * e.printStackTrace(); }
   * 
   * 
   * }
   * 
   * 
   * public static String getMailContent(Part part) throws Exception { String
   * contenttype = part.getContentType(); int nameindex =
   * contenttype.indexOf("name"); boolean conname = false; if (nameindex != -1)
   * { conname = true; } StringBuilder bodytext = new StringBuilder(); if
   * (part.isMimeType("text/plain") && !conname) { bodytext.append((String)
   * part.getContent()); } else if (part.isMimeType("text/html") && !conname) {
   * bodytext.append((String) part.getContent()); } else if
   * (part.isMimeType("multipart/*")) { Multipart multipart = (Multipart)
   * part.getContent(); int counts = multipart.getCount(); for (int i = 0; i <
   * counts; i++) { getMailContent(multipart.getBodyPart(i)); } } else if
   * (part.isMimeType("message/rfc822")) { getMailContent((Part)
   * part.getContent()); } else { } return bodytext.toString(); }
   */

}