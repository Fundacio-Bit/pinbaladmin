package org.fundaciobit.pinbaladmin.logic.utils.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author anadal
 *
 */
public class EmailMessageInfo {

  protected int number;

  protected String displayFrom;
  protected String displayTo;
  protected String displayCC;
  protected String displayBCC;

  protected String nameFrom;
  protected String nameTo;
  protected String nameCC;
  protected String nameBCC;

  protected String subject;
  protected String body;

  protected Date sentDate;

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

  public String getNameFrom() {
    return nameFrom;
  }

  public String getNameTo() {
    return nameTo;
  }

  public String getNameCC() {
    return nameCC;
  }

  public String getNameBCC() {
    return nameBCC;
  }

  public void setNameFrom(String nameFrom) {
    this.nameFrom = nameFrom;
  }

  public void setNameTo(String nameTo) {
    this.nameTo = nameTo;
  }

  public void setNameCC(String nameCC) {
    this.nameCC = nameCC;
  }

  public void setNameBCC(String nameBCC) {
    this.nameBCC = nameBCC;
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

  public Date getSentDate() {
    return sentDate;
  }

  public void setSentDate(Date sentDate) {
    this.sentDate = sentDate;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  @Override
  public String toString() {

    StringBuilder str = new StringBuilder();

    str.append("================ " + this.number + " ================").append('\n');

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

}
