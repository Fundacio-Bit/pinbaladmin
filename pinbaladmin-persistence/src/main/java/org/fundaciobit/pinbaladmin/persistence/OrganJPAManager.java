
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class OrganJPAManager
         extends AbstractJPAManager<Organ, Long>
         implements OrganIJPAManager, IOrganManager, OrganFields {



    public static final TableName<Organ> _TABLENAME =  new TableName<Organ>("OrganJPA");


    @PersistenceContext
    protected EntityManager __em;

    public OrganJPAManager() {
    }

    protected OrganJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return OrganJPA. class;
    }



    public TableName<Organ> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Organ[] listToArray(List<Organ> list)  {
        if(list == null) { return null; };
        return list.toArray(new Organ[list.size()]);
    };

    public Organ create( java.lang.String _nom_, java.lang.String _dir3_, java.lang.String _dir3pare_, java.lang.Long _entitatid_) throws I18NException {
        OrganJPA __bean =  new OrganJPA(_nom_,_dir3_,_dir3pare_,_entitatid_);
        return create(__bean);
    }



 public void delete(long _organid_) {
   delete(findByPrimaryKey(_organid_));
 }




    public Organ findByPrimaryKey(long _organid_) {
        return __em.find(OrganJPA.class, _organid_);  
    }
    @Override
    protected Organ getJPAInstance(Organ __bean) {
        return convertToJPA(__bean);
    }


    public static OrganJPA convertToJPA(Organ __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof OrganJPA) {
        return (OrganJPA)__bean;
      }
      
      return OrganJPA.toJPA(__bean);
    }


}