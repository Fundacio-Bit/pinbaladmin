
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class ContacteJPAManager
         extends AbstractJPAManager<Contacte, Long>
         implements ContacteIJPAManager, IContacteManager, ContacteFields {



    public static final TableName<Contacte> _TABLENAME =  new TableName<Contacte>("ContacteJPA");


    @PersistenceContext
    protected EntityManager __em;

    public ContacteJPAManager() {
    }

    protected ContacteJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return ContacteJPA. class;
    }



    public TableName<Contacte> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Contacte[] listToArray(List<Contacte> list)  {
        if(list == null) { return null; };
        return list.toArray(new Contacte[list.size()]);
    };

    public Contacte create( java.lang.String _nif_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _carrec_, java.lang.String _telefon_, java.lang.String _mail_, java.lang.String _username_) throws I18NException {
        ContacteJPA __bean =  new ContacteJPA(_nif_,_nom_,_llinatge1_,_llinatge2_,_carrec_,_telefon_,_mail_,_username_);
        return create(__bean);
    }



 public void delete(long _ContacteID_) {
   delete(findByPrimaryKey(_ContacteID_));
 }




    public Contacte findByPrimaryKey(long _ContacteID_) {
        return __em.find(ContacteJPA.class, _ContacteID_);  
    }
    @Override
    protected Contacte getJPAInstance(Contacte __bean) {
        return convertToJPA(__bean);
    }


    public static ContacteJPA convertToJPA(Contacte __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof ContacteJPA) {
        return (ContacteJPA)__bean;
      }
      
      return ContacteJPA.toJPA(__bean);
    }


}