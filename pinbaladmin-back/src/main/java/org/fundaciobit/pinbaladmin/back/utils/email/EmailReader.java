package org.fundaciobit.pinbaladmin.back.utils.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;

/**
 * 
 * @author anadal
 *
 */
public class EmailReader {

  protected final Properties properties;

  /**
   * @param properties
   */
  public EmailReader(boolean enableCertificationCheck) {
      this(Configuracio.getFilesProperties(),  enableCertificationCheck);
  }
  
  
  public EmailReader(Properties properties, boolean enableCertificationCheck) {
    super();
    this.properties = properties;
    
    if (!enableCertificationCheck) {
      this.properties.setProperty("mail.imap.socketFactory.class", DummySSLSocketFactory.class.getName());
    }
    
  }
  
  
  public int getCountMessages() throws Exception {

    EmailSession session = null;
    final boolean readOnly = true;
    try {
      session = EmailSession.connectToServer(this.properties, readOnly);
      return session.getFolder().getMessageCount();
    } finally {
      // disconnect
      if (session != null) {
        session.close();
      }
    }
  }
  

  public EmailMessageInfo getMessage(int numberMessage)
      throws Exception {

    EmailSession session = null;
    final boolean readOnly = true;
    try {

      session = EmailSession.connectToServer(this.properties, readOnly);

      Message msg;
      try {
        msg = session.getFolder().getMessage(numberMessage);
        EmailMessageInfo e = EmailEmlFormatParser.parseEml(msg, true);
        return e;
      } catch (java.lang.IndexOutOfBoundsException e) {
         e.printStackTrace(System.err);
         return null;
      }
    } finally {
      // disconnect
      if (session != null) {
        session.close();
      }

    }
  }

  public void deleteMessage(int numberMessage) throws Exception {

    EmailSession session = null;
    final boolean readOnly = false;
    try {

      session = EmailSession.connectToServer(this.properties, readOnly);

      Message msg = session.getFolder().getMessage(numberMessage);

      msg.setFlag(Flags.Flag.DELETED, true);

    } finally {
      // disconnect
      if (session != null) {
        session.close();
      }

    }
  }

  public List<EmailMessageInfo> list(int start, int end, boolean includeAttachements) throws Exception {

    EmailSession session = null;
    final boolean readOnly = true;
    try {

      session = EmailSession.connectToServer(this.properties, readOnly);

      Message[] messages = session.getFolder().getMessages(start,end);

      List<EmailMessageInfo> list = new ArrayList<EmailMessageInfo>();
      for (int i = 0; i < messages.length; i++) {
        Message msg = messages[i];
        EmailMessageInfo e = EmailEmlFormatParser.parseEml(msg, includeAttachements);
        list.add(e);
      }

      return list;

    } finally {
      // disconnect
      if (session != null) {
        session.close();
      }

    }
  }

  /**
   * 
   * @author anadal
   *
   */
  protected static class EmailSession {

    final Store store;

    final Folder folder;

    final boolean readOnly;

    /**
     * @param store
     * @param folder
     */
    public EmailSession(Store store, Folder folder, boolean readOnly) {
      super();
      this.store = store;
      this.folder = folder;
      this.readOnly = readOnly;
    }

    public Store getStore() {
      return store;
    }

    public Folder getFolder() {
      return folder;
    }

    public void close() throws Exception {
      folder.close(readOnly ? false : true);
      store.close();
    }

    protected static EmailSession connectToServer(Properties properties, boolean readOnly)
        throws Exception {
      final String storeType = properties.getProperty("mail.store.protocol");
      // imap
      // //"imaps";
      // //"pop3";

      Session session = Session.getDefaultInstance(properties, null);
      // session.setDebug(true);

      // connects to the message store
      Store store = session.getStore(storeType);
      {
        String userName = properties.getProperty("mail." + storeType + ".username");
        String password = properties.getProperty("mail." + storeType + ".password");
        // System.out.println("USR: ]" + userName + "[");
        // System.out.println("PWD: ]" + password + "[");
        store.connect(userName, password);
      }

      // opens the inbox folder
      Folder folderInbox = store.getFolder("INBOX");
      if (readOnly) {
        folderInbox.open(Folder.READ_ONLY);
      } else {
        folderInbox.open(Folder.READ_WRITE);
      }

      return new EmailSession(store, folderInbox, readOnly);

    }

  }

}
