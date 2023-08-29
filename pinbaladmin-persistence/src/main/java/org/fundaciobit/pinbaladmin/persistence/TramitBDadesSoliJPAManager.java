
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TramitBDadesSoliJPAManager
         extends AbstractJPAManager<TramitBDadesSoli, Long>
         implements TramitBDadesSoliIJPAManager, ITramitBDadesSoliManager, TramitBDadesSoliFields {



    public static final TableName<TramitBDadesSoli> _TABLENAME =  new TableName<TramitBDadesSoli>("TramitBDadesSoliJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TramitBDadesSoliJPAManager() {
    }

    protected TramitBDadesSoliJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TramitBDadesSoliJPA. class;
    }



    public TableName<TramitBDadesSoli> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TramitBDadesSoli[] listToArray(List<TramitBDadesSoli> list)  {
        if(list == null) { return null; };
        return list.toArray(new TramitBDadesSoli[list.size()]);
    };

    public TramitBDadesSoli create( long _tramitid_, long _tipussolicitud_, java.lang.String _entorn_) throws I18NException {
        TramitBDadesSoliJPA __bean =  new TramitBDadesSoliJPA(_tramitid_,_tipussolicitud_,_entorn_);
        return create(__bean);
    }



 public void delete(long _dadessoliid_) {
   delete(findByPrimaryKey(_dadessoliid_));
 }




    public TramitBDadesSoli findByPrimaryKey(long _dadessoliid_) {
        return __em.find(TramitBDadesSoliJPA.class, _dadessoliid_);  
    }
    @Override
    protected TramitBDadesSoli getJPAInstance(TramitBDadesSoli __bean) {
        return convertToJPA(__bean);
    }


    public static TramitBDadesSoliJPA convertToJPA(TramitBDadesSoli __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TramitBDadesSoliJPA) {
        return (TramitBDadesSoliJPA)__bean;
      }
      
      return TramitBDadesSoliJPA.toJPA(__bean);
    }


}