
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TramitAPersAutJPAManager
         extends AbstractJPAManager<TramitAPersAut, Long>
         implements TramitAPersAutIJPAManager, ITramitAPersAutManager, TramitAPersAutFields {



    public static final TableName<TramitAPersAut> _TABLENAME =  new TableName<TramitAPersAut>("TramitAPersAutJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TramitAPersAutJPAManager() {
    }

    protected TramitAPersAutJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TramitAPersAutJPA. class;
    }



    public TableName<TramitAPersAut> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TramitAPersAut[] listToArray(List<TramitAPersAut> list)  {
        if(list == null) { return null; };
        return list.toArray(new TramitAPersAut[list.size()]);
    };

    public TramitAPersAut create( long _tramitid_, java.sql.Timestamp _datatramit_, java.lang.String _nif_, java.lang.String _mail_, java.lang.String _telefon_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _urlsistra_, java.lang.String _idsesionformulario_, java.lang.String _idsesiontramite_) throws I18NException {
        TramitAPersAutJPA __bean =  new TramitAPersAutJPA(_tramitid_,_datatramit_,_nif_,_mail_,_telefon_,_nom_,_llinatge1_,_llinatge2_,_urlsistra_,_idsesionformulario_,_idsesiontramite_);
        return create(__bean);
    }



 public void delete(long _persautid_) {
   delete(findByPrimaryKey(_persautid_));
 }




    public TramitAPersAut findByPrimaryKey(long _persautid_) {
        return __em.find(TramitAPersAutJPA.class, _persautid_);  
    }
    @Override
    protected TramitAPersAut getJPAInstance(TramitAPersAut __bean) {
        return convertToJPA(__bean);
    }


    public static TramitAPersAutJPA convertToJPA(TramitAPersAut __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TramitAPersAutJPA) {
        return (TramitAPersAutJPA)__bean;
      }
      
      return TramitAPersAutJPA.toJPA(__bean);
    }


}