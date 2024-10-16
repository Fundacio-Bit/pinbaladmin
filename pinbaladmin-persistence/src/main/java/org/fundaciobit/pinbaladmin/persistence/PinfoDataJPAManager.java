
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class PinfoDataJPAManager
         extends AbstractJPAManager<PinfoData, Long>
         implements PinfoDataIJPAManager, IPinfoDataManager, PinfoDataFields {



    public static final TableName<PinfoData> _TABLENAME =  new TableName<PinfoData>("PinfoDataJPA");


    @PersistenceContext
    protected EntityManager __em;

    public PinfoDataJPAManager() {
    }

    protected PinfoDataJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return PinfoDataJPA. class;
    }



    public TableName<PinfoData> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public PinfoData[] listToArray(List<PinfoData> list)  {
        if(list == null) { return null; };
        return list.toArray(new PinfoData[list.size()]);
    };

    public PinfoData create( java.lang.Long _pinfoID_, java.lang.Long _estat_, java.lang.String _usuariid_, java.lang.Long _procedimentID_, java.lang.Long _serveiID_, java.lang.Long _alta_) throws I18NException {
        PinfoDataJPA __bean =  new PinfoDataJPA(_pinfoID_,_estat_,_usuariid_,_procedimentID_,_serveiID_,_alta_);
        return create(__bean);
    }



 public void delete(long _pinfodataID_) {
   delete(findByPrimaryKey(_pinfodataID_));
 }




    public PinfoData findByPrimaryKey(long _pinfodataID_) {
        return __em.find(PinfoDataJPA.class, _pinfodataID_);  
    }
    @Override
    protected PinfoData getJPAInstance(PinfoData __bean) {
        return convertToJPA(__bean);
    }


    public static PinfoDataJPA convertToJPA(PinfoData __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof PinfoDataJPA) {
        return (PinfoDataJPA)__bean;
      }
      
      return PinfoDataJPA.toJPA(__bean);
    }


}