
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TramitGDadesTitJPAManager
         extends AbstractJPAManager<TramitGDadesTit, Long>
         implements TramitGDadesTitIJPAManager, ITramitGDadesTitManager, TramitGDadesTitFields {



    public static final TableName<TramitGDadesTit> _TABLENAME =  new TableName<TramitGDadesTit>("TramitGDadesTitJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TramitGDadesTitJPAManager() {
    }

    protected TramitGDadesTitJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TramitGDadesTitJPA. class;
    }



    public TableName<TramitGDadesTit> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TramitGDadesTit[] listToArray(List<TramitGDadesTit> list)  {
        if(list == null) { return null; };
        return list.toArray(new TramitGDadesTit[list.size()]);
    };

    public TramitGDadesTit create( long _tramitid_, java.lang.String _nif_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _carrec_) throws I18NException {
        TramitGDadesTitJPA __bean =  new TramitGDadesTitJPA(_tramitid_,_nif_,_nom_,_llinatge1_,_llinatge2_,_carrec_);
        return create(__bean);
    }



 public void delete(long _dadestitid_) {
   delete(findByPrimaryKey(_dadestitid_));
 }




    public TramitGDadesTit findByPrimaryKey(long _dadestitid_) {
        return __em.find(TramitGDadesTitJPA.class, _dadestitid_);  
    }
    @Override
    protected TramitGDadesTit getJPAInstance(TramitGDadesTit __bean) {
        return convertToJPA(__bean);
    }


    public static TramitGDadesTitJPA convertToJPA(TramitGDadesTit __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TramitGDadesTitJPA) {
        return (TramitGDadesTitJPA)__bean;
      }
      
      return TramitGDadesTitJPA.toJPA(__bean);
    }


}