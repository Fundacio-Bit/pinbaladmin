
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TramitHProcJPAManager
         extends AbstractJPAManager<TramitHProc, Long>
         implements TramitHProcIJPAManager, ITramitHProcManager, TramitHProcFields {



    public static final TableName<TramitHProc> _TABLENAME =  new TableName<TramitHProc>("TramitHProcJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TramitHProcJPAManager() {
    }

    protected TramitHProcJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TramitHProcJPA. class;
    }



    public TableName<TramitHProc> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TramitHProc[] listToArray(List<TramitHProc> list)  {
        if(list == null) { return null; };
        return list.toArray(new TramitHProc[list.size()]);
    };

    public TramitHProc create( long _tramitid_, java.lang.String _nom_, java.lang.String _codi_, java.lang.String _tipus_, java.lang.String _urlseu_, boolean _caducitat_, java.sql.Timestamp _caducitatdata_, java.lang.String _descripcio_, long _peticionsaldia_, long _peticionsalmes_, boolean _periodico_, boolean _automatizado_) throws I18NException {
        TramitHProcJPA __bean =  new TramitHProcJPA(_tramitid_,_nom_,_codi_,_tipus_,_urlseu_,_caducitat_,_caducitatdata_,_descripcio_,_peticionsaldia_,_peticionsalmes_,_periodico_,_automatizado_);
        return create(__bean);
    }



 public void delete(long _procid_) {
   delete(findByPrimaryKey(_procid_));
 }




    public TramitHProc findByPrimaryKey(long _procid_) {
        return __em.find(TramitHProcJPA.class, _procid_);  
    }
    @Override
    protected TramitHProc getJPAInstance(TramitHProc __bean) {
        return convertToJPA(__bean);
    }


    public static TramitHProcJPA convertToJPA(TramitHProc __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TramitHProcJPA) {
        return (TramitHProcJPA)__bean;
      }
      
      return TramitHProcJPA.toJPA(__bean);
    }


}