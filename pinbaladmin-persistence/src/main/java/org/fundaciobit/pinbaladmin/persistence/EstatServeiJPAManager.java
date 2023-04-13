
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class EstatServeiJPAManager
         extends AbstractJPAManager<EstatServei, Long>
         implements EstatServeiIJPAManager, IEstatServeiManager, EstatServeiFields {



    public static final TableName<EstatServei> _TABLENAME =  new TableName<EstatServei>("EstatServeiJPA");


    @PersistenceContext
    protected EntityManager __em;

    public EstatServeiJPAManager() {
    }

    protected EstatServeiJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return EstatServeiJPA. class;
    }



    public TableName<EstatServei> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public EstatServei[] listToArray(List<EstatServei> list)  {
        if(list == null) { return null; };
        return list.toArray(new EstatServei[list.size()]);
    };

    public EstatServei create( long _estatServeiID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException {
        EstatServeiJPA __bean =  new EstatServeiJPA(_estatServeiID_,_nom_,_descripcio_);
        return create(__bean);
    }



 public void delete(long _estatServeiID_) {
   delete(findByPrimaryKey(_estatServeiID_));
 }




    public EstatServei findByPrimaryKey(long _estatServeiID_) {
        return __em.find(EstatServeiJPA.class, _estatServeiID_);  
    }
    @Override
    protected EstatServei getJPAInstance(EstatServei __bean) {
        return convertToJPA(__bean);
    }


    public static EstatServeiJPA convertToJPA(EstatServei __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof EstatServeiJPA) {
        return (EstatServeiJPA)__bean;
      }
      
      return EstatServeiJPA.toJPA(__bean);
    }


}