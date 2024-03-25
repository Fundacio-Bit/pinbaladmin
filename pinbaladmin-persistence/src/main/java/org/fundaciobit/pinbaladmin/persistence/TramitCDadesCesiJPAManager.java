
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TramitCDadesCesiJPAManager
         extends AbstractJPAManager<TramitCDadesCesi, Long>
         implements TramitCDadesCesiIJPAManager, ITramitCDadesCesiManager, TramitCDadesCesiFields {



    public static final TableName<TramitCDadesCesi> _TABLENAME =  new TableName<TramitCDadesCesi>("TramitCDadesCesiJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TramitCDadesCesiJPAManager() {
    }

    protected TramitCDadesCesiJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TramitCDadesCesiJPA. class;
    }



    public TableName<TramitCDadesCesi> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TramitCDadesCesi[] listToArray(List<TramitCDadesCesi> list)  {
        if(list == null) { return null; };
        return list.toArray(new TramitCDadesCesi[list.size()]);
    };

    public TramitCDadesCesi create( long _tramitid_, java.lang.Long _organID_, java.lang.Long _organArrelID_, java.lang.String _denominacio_, java.lang.String _nif_, java.lang.String _responsable_, java.lang.String _dir3responsable_, java.lang.String _dir3arrel_, java.lang.String _direccio_, java.lang.String _codipostal_, java.lang.String _municipi_) throws I18NException {
        TramitCDadesCesiJPA __bean =  new TramitCDadesCesiJPA(_tramitid_,_organID_,_organArrelID_,_denominacio_,_nif_,_responsable_,_dir3responsable_,_dir3arrel_,_direccio_,_codipostal_,_municipi_);
        return create(__bean);
    }



 public void delete(long _dadescesiid_) {
   delete(findByPrimaryKey(_dadescesiid_));
 }




    public TramitCDadesCesi findByPrimaryKey(long _dadescesiid_) {
        return __em.find(TramitCDadesCesiJPA.class, _dadescesiid_);  
    }
    @Override
    protected TramitCDadesCesi getJPAInstance(TramitCDadesCesi __bean) {
        return convertToJPA(__bean);
    }


    public static TramitCDadesCesiJPA convertToJPA(TramitCDadesCesi __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TramitCDadesCesiJPA) {
        return (TramitCDadesCesiJPA)__bean;
      }
      
      return TramitCDadesCesiJPA.toJPA(__bean);
    }


}