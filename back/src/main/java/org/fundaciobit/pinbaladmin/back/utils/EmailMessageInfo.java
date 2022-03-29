package org.fundaciobit.pinbaladmin.back.utils;

import java.util.ArrayList;
import java.util.List;

import org.fundaciobit.pinbaladmin.logic.utils.EmailAttachmentInfo;

/**
 * 
 * @author anadal
 *
 */
public class EmailMessageInfo {

  protected String displayFrom;
  protected String displayTo;
  protected String displayCC;

  protected String displayBCC;
  protected String subject;
  protected String body;

  protected List<EmailAttachmentInfo> attachments = new ArrayList<EmailAttachmentInfo>();

  public String getDisplayFrom() {
    return displayFrom;
  }

  public String getDisplayTo() {
    return displayTo;
  }

  public String getDisplayCC() {
    return displayCC;
  }

  public String getDisplayBCC() {
    return displayBCC;
  }

  public String getSubject() {
    return subject;
  }

  public String getBody() {
    return body;
  }

  public List<EmailAttachmentInfo> getAttachments() {
    return attachments;
  }

  public void setDisplayFrom(String displayFrom) {
    this.displayFrom = displayFrom;
  }

  public void setDisplayTo(String displayTo) {
    this.displayTo = displayTo;
  }

  public void setDisplayCC(String displayCC) {
    this.displayCC = displayCC;
  }

  public void setDisplayBCC(String displayBCC) {
    this.displayBCC = displayBCC;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public void setAttachments(List<EmailAttachmentInfo> attachments) {
    this.attachments = attachments;
  }

  @Override
  public String toString() {

    StringBuilder str = new StringBuilder();

    str.append("From: " + displayFrom).append('\n');

    str.append("To: " + displayTo).append('\n');
    str.append("CC: " + displayCC).append('\n');
    str.append("BCC: " + displayBCC).append('\n');
    str.append("Subject: " + subject).append('\n');

    str.append("----------- body ----------").append('\n');
    if (body == null) {
      str.append("--- No message body ---").append('\n');
    } else {
      str.append(body).append('\n');
    }
    str.append("---------------------------").append('\n');

    for (EmailAttachmentInfo ads : attachments) {

      str.append("Attachement[" + ads.getFileName() + "](" + ads.getContentType() + ") => "
          + ads.getData().length + " bytes").append('\n');
    }

    return str.toString();
  }

  public static String getPidFromSubject(String subject) {
    
    if (subject == null || subject.length() == 0) {
      return null;
    }
    
    String pid;

    final String match = "[PID][";
    int pos = subject.indexOf(match);
    int pos2 = subject.indexOf("]", pos + match.length() + 1);
    pid = subject.substring(pos + match.length(), pos2);

    return pid;
  }

}
