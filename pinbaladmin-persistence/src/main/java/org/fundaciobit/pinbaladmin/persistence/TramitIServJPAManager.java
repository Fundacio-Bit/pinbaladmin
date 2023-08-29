
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TramitIServJPAManager
         extends AbstractJPAManager<TramitIServ, Long>
         implements TramitIServIJPAManager, ITramitIServManager, TramitIServFields {



    public static final TableName<TramitIServ> _TABLENAME =  new TableName<TramitIServ>("TramitIServJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TramitIServJPAManager() {
    }

    protected TramitIServJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TramitIServJPA. class;
    }



    public TableName<TramitIServ> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public TramitIServ[] listToArray(List<TramitIServ> list)  {
        if(list == null) { return null; };
        return list.toArray(new TramitIServ[list.size()]);
    };

    public TramitIServ create( long _tramitid_, java.lang.String _nom_, java.lang.String _codi_, java.lang.String _norma_, java.lang.String _articles_, java.lang.String _consentiment_, java.lang.String _urlnorma_, java.lang.String _consentimentpublicat_, java.lang.String _urlconsentiment_) throws I18NException {
        TramitIServJPA __bean =  new TramitIServJPA(_tramitid_,_nom_,_codi_,_norma_,_articles_,_consentiment_,_urlnorma_,_consentimentpublicat_,_urlconsentiment_);
        return create(__bean);
    }



 public void delete(long _servid_) {
   delete(findByPrimaryKey(_servid_));
 }




    public TramitIServ findByPrimaryKey(long _servid_) {
        return __em.find(TramitIServJPA.class, _servid_);  
    }
    @Override
    protected TramitIServ getJPAInstance(TramitIServ __bean) {
        return convertToJPA(__bean);
    }


    public static TramitIServJPA convertToJPA(TramitIServ __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TramitIServJPA) {
        return (TramitIServJPA)__bean;
      }
      
      return TramitIServJPA.toJPA(__bean);
    }


}