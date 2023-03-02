
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class EntitatServeiJPAManager
         extends AbstractJPAManager<EntitatServei, Long>
         implements EntitatServeiIJPAManager, IEntitatServeiManager, EntitatServeiFields {



    public static final TableName<EntitatServei> _TABLENAME =  new TableName<EntitatServei>("EntitatServeiJPA");


    @PersistenceContext
    protected EntityManager __em;

    public EntitatServeiJPAManager() {
    }

    protected EntitatServeiJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return EntitatServeiJPA. class;
    }



    public TableName<EntitatServei> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public EntitatServei[] listToArray(List<EntitatServei> list)  {
        if(list == null) { return null; };
        return list.toArray(new EntitatServei[list.size()]);
    };

    public EntitatServei create( java.lang.String _nom_, java.lang.String _descripcio_, boolean _balears_) throws I18NException {
        EntitatServeiJPA __bean =  new EntitatServeiJPA(_nom_,_descripcio_,_balears_);
        return create(__bean);
    }



 public void delete(long _entitatServeiID_) {
   delete(findByPrimaryKey(_entitatServeiID_));
 }




    public EntitatServei findByPrimaryKey(long _entitatServeiID_) {
        return __em.find(EntitatServeiJPA.class, _entitatServeiID_);  
    }
    @Override
    protected EntitatServei getJPAInstance(EntitatServei __bean) {
        return convertToJPA(__bean);
    }


    public static EntitatServeiJPA convertToJPA(EntitatServei __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof EntitatServeiJPA) {
        return (EntitatServeiJPA)__bean;
      }
      
      return EntitatServeiJPA.toJPA(__bean);
    }


}