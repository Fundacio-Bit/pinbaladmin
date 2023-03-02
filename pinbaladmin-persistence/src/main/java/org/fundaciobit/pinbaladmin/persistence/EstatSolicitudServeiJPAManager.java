
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class EstatSolicitudServeiJPAManager
         extends AbstractJPAManager<EstatSolicitudServei, Long>
         implements EstatSolicitudServeiIJPAManager, IEstatSolicitudServeiManager, EstatSolicitudServeiFields {



    public static final TableName<EstatSolicitudServei> _TABLENAME =  new TableName<EstatSolicitudServei>("EstatSolicitudServeiJPA");


    @PersistenceContext
    protected EntityManager __em;

    public EstatSolicitudServeiJPAManager() {
    }

    protected EstatSolicitudServeiJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return EstatSolicitudServeiJPA. class;
    }



    public TableName<EstatSolicitudServei> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public EstatSolicitudServei[] listToArray(List<EstatSolicitudServei> list)  {
        if(list == null) { return null; };
        return list.toArray(new EstatSolicitudServei[list.size()]);
    };

    public EstatSolicitudServei create( long _estatSolicitudServeiID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException {
        EstatSolicitudServeiJPA __bean =  new EstatSolicitudServeiJPA(_estatSolicitudServeiID_,_nom_,_descripcio_);
        return create(__bean);
    }



 public void delete(long _estatSolicitudServeiID_) {
   delete(findByPrimaryKey(_estatSolicitudServeiID_));
 }




    public EstatSolicitudServei findByPrimaryKey(long _estatSolicitudServeiID_) {
        return __em.find(EstatSolicitudServeiJPA.class, _estatSolicitudServeiID_);  
    }
    @Override
    protected EstatSolicitudServei getJPAInstance(EstatSolicitudServei __bean) {
        return convertToJPA(__bean);
    }


    public static EstatSolicitudServeiJPA convertToJPA(EstatSolicitudServei __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof EstatSolicitudServeiJPA) {
        return (EstatSolicitudServeiJPA)__bean;
      }
      
      return EstatSolicitudServeiJPA.toJPA(__bean);
    }


}