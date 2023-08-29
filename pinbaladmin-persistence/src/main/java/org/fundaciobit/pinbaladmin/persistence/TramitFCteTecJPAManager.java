
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TramitFCteTecJPAManager
         extends AbstractJPAManager<TramitFCteTec, Long>
         implements TramitFCteTecIJPAManager, ITramitFCteTecManager, TramitFCteTecFields {



    public static final TableName<TramitFCteTec> _TABLENAME =  new TableName<TramitFCteTec>("TramitFCteTecJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TramitFCteTecJPAManager() {
    }

    protected TramitFCteTecJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TramitFCteTecJPA. class;
    }



    public TableName<TramitFCteTec> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TramitFCteTec[] listToArray(List<TramitFCteTec> list)  {
        if(list == null) { return null; };
        return list.toArray(new TramitFCteTec[list.size()]);
    };

    public TramitFCteTec create( long _tramitid_, java.lang.String _nif_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _carrec_, java.lang.String _telefon_, java.lang.String _mail_) throws I18NException {
        TramitFCteTecJPA __bean =  new TramitFCteTecJPA(_tramitid_,_nif_,_nom_,_llinatge1_,_llinatge2_,_carrec_,_telefon_,_mail_);
        return create(__bean);
    }



 public void delete(long _ctetecid_) {
   delete(findByPrimaryKey(_ctetecid_));
 }




    public TramitFCteTec findByPrimaryKey(long _ctetecid_) {
        return __em.find(TramitFCteTecJPA.class, _ctetecid_);  
    }
    @Override
    protected TramitFCteTec getJPAInstance(TramitFCteTec __bean) {
        return convertToJPA(__bean);
    }


    public static TramitFCteTecJPA convertToJPA(TramitFCteTec __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TramitFCteTecJPA) {
        return (TramitFCteTecJPA)__bean;
      }
      
      return TramitFCteTecJPA.toJPA(__bean);
    }


}