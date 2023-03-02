
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TipusTiquetJPAManager
         extends AbstractJPAManager<TipusTiquet, Long>
         implements TipusTiquetIJPAManager, ITipusTiquetManager, TipusTiquetFields {



    public static final TableName<TipusTiquet> _TABLENAME =  new TableName<TipusTiquet>("TipusTiquetJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TipusTiquetJPAManager() {
    }

    protected TipusTiquetJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TipusTiquetJPA. class;
    }



    public TableName<TipusTiquet> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TipusTiquet[] listToArray(List<TipusTiquet> list)  {
        if(list == null) { return null; };
        return list.toArray(new TipusTiquet[list.size()]);
    };

    public TipusTiquet create( long _tipusTiquetID_, java.lang.String _nom_) throws I18NException {
        TipusTiquetJPA __bean =  new TipusTiquetJPA(_tipusTiquetID_,_nom_);
        return create(__bean);
    }



 public void delete(long _tipusTiquetID_) {
   delete(findByPrimaryKey(_tipusTiquetID_));
 }




    public TipusTiquet findByPrimaryKey(long _tipusTiquetID_) {
        return __em.find(TipusTiquetJPA.class, _tipusTiquetID_);  
    }
    @Override
    protected TipusTiquet getJPAInstance(TipusTiquet __bean) {
        return convertToJPA(__bean);
    }


    public static TipusTiquetJPA convertToJPA(TipusTiquet __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TipusTiquetJPA) {
        return (TipusTiquetJPA)__bean;
      }
      
      return TipusTiquetJPA.toJPA(__bean);
    }


}