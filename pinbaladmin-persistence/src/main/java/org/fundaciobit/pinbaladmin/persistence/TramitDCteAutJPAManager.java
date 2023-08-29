
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TramitDCteAutJPAManager
         extends AbstractJPAManager<TramitDCteAut, Long>
         implements TramitDCteAutIJPAManager, ITramitDCteAutManager, TramitDCteAutFields {



    public static final TableName<TramitDCteAut> _TABLENAME =  new TableName<TramitDCteAut>("TramitDCteAutJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TramitDCteAutJPAManager() {
    }

    protected TramitDCteAutJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TramitDCteAutJPA. class;
    }



    public TableName<TramitDCteAut> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TramitDCteAut[] listToArray(List<TramitDCteAut> list)  {
        if(list == null) { return null; };
        return list.toArray(new TramitDCteAut[list.size()]);
    };

    public TramitDCteAut create( long _tramitid_, java.lang.String _nif_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _carrec_, java.lang.String _telefon_, java.lang.String _mail_) throws I18NException {
        TramitDCteAutJPA __bean =  new TramitDCteAutJPA(_tramitid_,_nif_,_nom_,_llinatge1_,_llinatge2_,_carrec_,_telefon_,_mail_);
        return create(__bean);
    }



 public void delete(long _cteautid_) {
   delete(findByPrimaryKey(_cteautid_));
 }




    public TramitDCteAut findByPrimaryKey(long _cteautid_) {
        return __em.find(TramitDCteAutJPA.class, _cteautid_);  
    }
    @Override
    protected TramitDCteAut getJPAInstance(TramitDCteAut __bean) {
        return convertToJPA(__bean);
    }


    public static TramitDCteAutJPA convertToJPA(TramitDCteAut __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TramitDCteAutJPA) {
        return (TramitDCteAutJPA)__bean;
      }
      
      return TramitDCteAutJPA.toJPA(__bean);
    }


}