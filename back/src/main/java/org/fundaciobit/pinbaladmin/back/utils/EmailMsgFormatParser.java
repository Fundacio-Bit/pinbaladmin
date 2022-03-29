package org.fundaciobit.pinbaladmin.back.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hsmf.MAPIMessage;
import org.apache.poi.hsmf.datatypes.AttachmentChunks;
import org.apache.poi.hsmf.exceptions.ChunkNotFoundException;
import org.fundaciobit.pinbaladmin.logic.utils.EmailAttachmentInfo;

/**
 * 
 * @author anadal
 *
 */
public class EmailMsgFormatParser {

  /**
   * Processes the message.
   * 
   * @throws IOException
   *           if an exception occurs while writing the message out
   */
  public static EmailMessageInfo processMessage(InputStream is) throws IOException {

    EmailMessageInfo emi = new EmailMessageInfo();

    MAPIMessage msg;
    msg = new MAPIMessage(is);

    try {
      emi.setDisplayFrom(msg.getDisplayFrom());

    } catch (ChunkNotFoundException e) {
      // ignore
    }
    try {
      emi.setDisplayTo(msg.getDisplayTo());

    } catch (ChunkNotFoundException e) {
      // ignore
    }
    try {
      emi.setDisplayCC(msg.getDisplayCC());

    } catch (ChunkNotFoundException e) {
      // ignore
    }
    try {
      emi.setDisplayBCC(msg.getDisplayBCC());

    } catch (ChunkNotFoundException e) {
      // ignore
    }
    try {
      emi.setSubject(msg.getSubject());

    } catch (ChunkNotFoundException e) {
      // ignore
    }
    try {
      emi.setBody(msg.getTextBody());

    } catch (ChunkNotFoundException e) {

    }

    List<EmailAttachmentInfo> attachments = new ArrayList<EmailAttachmentInfo>();

    AttachmentChunks[] attachmentMap = msg.getAttachmentFiles();
    if (attachmentMap != null && attachmentMap.length != 0) {

      for (AttachmentChunks attach : attachmentMap) {

        String fileName = attach.getAttachFileName().getValue();
        String contentType = attach.getAttachMimeTag().getValue();
        byte[] data = attach.getAttachData().getValue();

        attachments.add(new EmailAttachmentInfo(fileName, contentType, data));

      }
    }

    emi.setAttachments(attachments);

    msg.close();

    return emi;
  }

}
