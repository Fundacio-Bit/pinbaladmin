
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class PINFOJPAManager
         extends AbstractJPAManager<PINFO, Long>
         implements PINFOIJPAManager, IPINFOManager, PINFOFields {



    public static final TableName<PINFO> _TABLENAME =  new TableName<PINFO>("PINFOJPA");


    @PersistenceContext
    protected EntityManager __em;

    public PINFOJPAManager() {
    }

    protected PINFOJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return PINFOJPA. class;
    }



    public TableName<PINFO> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public PINFO[] listToArray(List<PINFO> list)  {
        if(list == null) { return null; };
        return list.toArray(new PINFO[list.size()]);
    };

    public PINFO create( java.lang.Long _IncidenciaID_, java.lang.Long _estat_, java.lang.Long _fitxerID_, java.lang.Long _fitxerfirmatID_, java.lang.String _portafibid_) throws I18NException {
        PINFOJPA __bean =  new PINFOJPA(_IncidenciaID_,_estat_,_fitxerID_,_fitxerfirmatID_,_portafibid_);
        return create(__bean);
    }



 public void delete(long _PinfoID_) {
   delete(findByPrimaryKey(_PinfoID_));
 }




    public PINFO findByPrimaryKey(long _PinfoID_) {
        return __em.find(PINFOJPA.class, _PinfoID_);  
    }
    @Override
    protected PINFO getJPAInstance(PINFO __bean) {
        return convertToJPA(__bean);
    }


    public static PINFOJPA convertToJPA(PINFO __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof PINFOJPA) {
        return (PINFOJPA)__bean;
      }
      
      return PINFOJPA.toJPA(__bean);
    }


}