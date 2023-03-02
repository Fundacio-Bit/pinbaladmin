
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class EstatTiquetJPAManager
         extends AbstractJPAManager<EstatTiquet, Long>
         implements EstatTiquetIJPAManager, IEstatTiquetManager, EstatTiquetFields {



    public static final TableName<EstatTiquet> _TABLENAME =  new TableName<EstatTiquet>("EstatTiquetJPA");


    @PersistenceContext
    protected EntityManager __em;

    public EstatTiquetJPAManager() {
    }

    protected EstatTiquetJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return EstatTiquetJPA. class;
    }



    public TableName<EstatTiquet> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public EstatTiquet[] listToArray(List<EstatTiquet> list)  {
        if(list == null) { return null; };
        return list.toArray(new EstatTiquet[list.size()]);
    };

    public EstatTiquet create( long _estatTiquetID_, java.lang.String _nom_) throws I18NException {
        EstatTiquetJPA __bean =  new EstatTiquetJPA(_estatTiquetID_,_nom_);
        return create(__bean);
    }



 public void delete(long _estatTiquetID_) {
   delete(findByPrimaryKey(_estatTiquetID_));
 }




    public EstatTiquet findByPrimaryKey(long _estatTiquetID_) {
        return __em.find(EstatTiquetJPA.class, _estatTiquetID_);  
    }
    @Override
    protected EstatTiquet getJPAInstance(EstatTiquet __bean) {
        return convertToJPA(__bean);
    }


    public static EstatTiquetJPA convertToJPA(EstatTiquet __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof EstatTiquetJPA) {
        return (EstatTiquetJPA)__bean;
      }
      
      return EstatTiquetJPA.toJPA(__bean);
    }


}