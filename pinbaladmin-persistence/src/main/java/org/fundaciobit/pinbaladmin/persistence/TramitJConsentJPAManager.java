
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TramitJConsentJPAManager
         extends AbstractJPAManager<TramitJConsent, Long>
         implements TramitJConsentIJPAManager, ITramitJConsentManager, TramitJConsentFields {



    public static final TableName<TramitJConsent> _TABLENAME =  new TableName<TramitJConsent>("TramitJConsentJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TramitJConsentJPAManager() {
    }

    protected TramitJConsentJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TramitJConsentJPA. class;
    }



    public TableName<TramitJConsent> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TramitJConsent[] listToArray(List<TramitJConsent> list)  {
        if(list == null) { return null; };
        return list.toArray(new TramitJConsent[list.size()]);
    };

    public TramitJConsent create( long _tramitid_, java.lang.String _consentiment_, java.lang.String _consentimentadjunt_, java.lang.String _urlconsentiment_, java.lang.Long _adjuntID_) throws I18NException {
        TramitJConsentJPA __bean =  new TramitJConsentJPA(_tramitid_,_consentiment_,_consentimentadjunt_,_urlconsentiment_,_adjuntID_);
        return create(__bean);
    }



 public void delete(long _consentid_) {
   delete(findByPrimaryKey(_consentid_));
 }




    public TramitJConsent findByPrimaryKey(long _consentid_) {
        return __em.find(TramitJConsentJPA.class, _consentid_);  
    }
    @Override
    protected TramitJConsent getJPAInstance(TramitJConsent __bean) {
        return convertToJPA(__bean);
    }


    public static TramitJConsentJPA convertToJPA(TramitJConsent __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TramitJConsentJPA) {
        return (TramitJConsentJPA)__bean;
      }
      
      return TramitJConsentJPA.toJPA(__bean);
    }


}