
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TramitECteAudJPAManager
         extends AbstractJPAManager<TramitECteAud, Long>
         implements TramitECteAudIJPAManager, ITramitECteAudManager, TramitECteAudFields {



    public static final TableName<TramitECteAud> _TABLENAME =  new TableName<TramitECteAud>("TramitECteAudJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TramitECteAudJPAManager() {
    }

    protected TramitECteAudJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TramitECteAudJPA. class;
    }



    public TableName<TramitECteAud> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TramitECteAud[] listToArray(List<TramitECteAud> list)  {
        if(list == null) { return null; };
        return list.toArray(new TramitECteAud[list.size()]);
    };

    public TramitECteAud create( long _tramitid_, java.lang.String _nif_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _carrec_, java.lang.String _telefon_, java.lang.String _mail_) throws I18NException {
        TramitECteAudJPA __bean =  new TramitECteAudJPA(_tramitid_,_nif_,_nom_,_llinatge1_,_llinatge2_,_carrec_,_telefon_,_mail_);
        return create(__bean);
    }



 public void delete(long _cteaudid_) {
   delete(findByPrimaryKey(_cteaudid_));
 }




    public TramitECteAud findByPrimaryKey(long _cteaudid_) {
        return __em.find(TramitECteAudJPA.class, _cteaudid_);  
    }
    @Override
    protected TramitECteAud getJPAInstance(TramitECteAud __bean) {
        return convertToJPA(__bean);
    }


    public static TramitECteAudJPA convertToJPA(TramitECteAud __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TramitECteAudJPA) {
        return (TramitECteAudJPA)__bean;
      }
      
      return TramitECteAudJPA.toJPA(__bean);
    }


}