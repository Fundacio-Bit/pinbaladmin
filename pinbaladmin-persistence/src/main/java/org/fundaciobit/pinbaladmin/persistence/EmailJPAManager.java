
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class EmailJPAManager
         extends AbstractJPAManager<Email, Long>
         implements EmailIJPAManager, IEmailManager, EmailFields {



    public static final TableName<Email> _TABLENAME =  new TableName<Email>("EmailJPA");


    @PersistenceContext
    protected EntityManager __em;

    public EmailJPAManager() {
    }

    protected EmailJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return EmailJPA. class;
    }



    public TableName<Email> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Email[] listToArray(List<Email> list)  {
        if(list == null) { return null; };
        return list.toArray(new Email[list.size()]);
    };

    public Email create( java.sql.Timestamp _dataEnviament_, java.lang.String _enviador_, java.lang.String _destinataris_, java.lang.String _subject_, java.lang.String _message_) throws I18NException {
        EmailJPA __bean =  new EmailJPA(_dataEnviament_,_enviador_,_destinataris_,_subject_,_message_);
        return create(__bean);
    }



 public void delete(long _emailID_) {
   delete(findByPrimaryKey(_emailID_));
 }




    public Email findByPrimaryKey(long _emailID_) {
        return __em.find(EmailJPA.class, _emailID_);  
    }
    @Override
    protected Email getJPAInstance(Email __bean) {
        return convertToJPA(__bean);
    }


    public static EmailJPA convertToJPA(Email __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof EmailJPA) {
        return (EmailJPA)__bean;
      }
      
      return EmailJPA.toJPA(__bean);
    }


}