
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

    public TramitIServ create( long _tramitid_, java.lang.String _nom_, java.lang.String _codi_, java.lang.String _norma_, java.lang.Long _fitxernormaID_, java.lang.String _urlnorma_, java.lang.String _articles_, java.lang.String _norma2_, java.lang.Long _fitxernorma2ID_, java.lang.String _articles2_, java.lang.String _norma3_, java.lang.Long _fitxernorma3ID_, java.lang.String _articles3_) throws I18NException {
        TramitIServJPA __bean =  new TramitIServJPA(_tramitid_,_nom_,_codi_,_norma_,_fitxernormaID_,_urlnorma_,_articles_,_norma2_,_fitxernorma2ID_,_articles2_,_norma3_,_fitxernorma3ID_,_articles3_);
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