package org.fundaciobit.pinbaladmin.logic.utils;

/**
 * 
 * @author anadal
 *
 */
public class EmailAttachmentInfo {

  final String fileName;
  final String contentType;
  final byte[] data;

  /**
   * @param fileName
   * @param contentType
   * @param data
   */
  public EmailAttachmentInfo(String fileName, String contentType, byte[] data) {
    super();
    this.fileName = fileName;
    this.contentType = contentType;
    this.data = data;
  }

  public String getFileName() {
    return fileName;
  }

  public String getContentType() {
    return contentType;
  }

  public byte[] getData() {
    return data;
  }

}
