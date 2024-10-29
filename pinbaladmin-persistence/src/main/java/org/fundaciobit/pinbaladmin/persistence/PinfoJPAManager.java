
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class PinfoJPAManager
         extends AbstractJPAManager<Pinfo, Long>
         implements PinfoIJPAManager, IPinfoManager, PinfoFields {



    public static final TableName<Pinfo> _TABLENAME =  new TableName<Pinfo>("PinfoJPA");


    @PersistenceContext
    protected EntityManager __em;

    public PinfoJPAManager() {
    }

    protected PinfoJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return PinfoJPA. class;
    }



    public TableName<Pinfo> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Pinfo[] listToArray(List<Pinfo> list)  {
        if(list == null) { return null; };
        return list.toArray(new Pinfo[list.size()]);
    };

    public Pinfo create( java.lang.Long _incidenciaID_, java.lang.String _solicitantNIF_, java.lang.Long _estat_, java.lang.Long _fitxerID_, java.lang.Long _fitxerfirmatID_, java.lang.String _portafibid_, java.lang.String _destinatariNIF_) throws I18NException {
        PinfoJPA __bean =  new PinfoJPA(_incidenciaID_,_solicitantNIF_,_estat_,_fitxerID_,_fitxerfirmatID_,_portafibid_,_destinatariNIF_);
        return create(__bean);
    }



 public void delete(long _pinfoID_) {
   delete(findByPrimaryKey(_pinfoID_));
 }




    public Pinfo findByPrimaryKey(long _pinfoID_) {
        return __em.find(PinfoJPA.class, _pinfoID_);  
    }
    @Override
    protected Pinfo getJPAInstance(Pinfo __bean) {
        return convertToJPA(__bean);
    }


    public static PinfoJPA convertToJPA(Pinfo __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof PinfoJPA) {
        return (PinfoJPA)__bean;
      }
      
      return PinfoJPA.toJPA(__bean);
    }


}