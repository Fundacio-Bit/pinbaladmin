
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class ModificacioSoliServJPAManager
         extends AbstractJPAManager<ModificacioSoliServ, Long>
         implements ModificacioSoliServIJPAManager, IModificacioSoliServManager, ModificacioSoliServFields {



    public static final TableName<ModificacioSoliServ> _TABLENAME =  new TableName<ModificacioSoliServ>("ModificacioSoliServJPA");


    @PersistenceContext
    protected EntityManager __em;

    public ModificacioSoliServJPAManager() {
    }

    protected ModificacioSoliServJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return ModificacioSoliServJPA. class;
    }



    public TableName<ModificacioSoliServ> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public ModificacioSoliServ[] listToArray(List<ModificacioSoliServ> list)  {
        if(list == null) { return null; };
        return list.toArray(new ModificacioSoliServ[list.size()]);
    };

    public ModificacioSoliServ create( long _soliServID_, long _modSoliID_, java.lang.String _estat_, java.lang.String _norma1_, java.lang.String _articles1_, java.lang.Long _fitxerNorma1ID_, java.lang.String _norma2_, java.lang.String _articles2_, java.lang.Long _fitxerNorma2ID_, java.lang.String _norma3_, java.lang.String _articles3_, java.lang.Long _fitxerNorma3ID_) throws I18NException {
        ModificacioSoliServJPA __bean =  new ModificacioSoliServJPA(_soliServID_,_modSoliID_,_estat_,_norma1_,_articles1_,_fitxerNorma1ID_,_norma2_,_articles2_,_fitxerNorma2ID_,_norma3_,_articles3_,_fitxerNorma3ID_);
        return create(__bean);
    }



 public void delete(long _modsoliservid_) {
   delete(findByPrimaryKey(_modsoliservid_));
 }




    public ModificacioSoliServ findByPrimaryKey(long _modsoliservid_) {
        return __em.find(ModificacioSoliServJPA.class, _modsoliservid_);  
    }
    @Override
    protected ModificacioSoliServ getJPAInstance(ModificacioSoliServ __bean) {
        return convertToJPA(__bean);
    }


    public static ModificacioSoliServJPA convertToJPA(ModificacioSoliServ __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof ModificacioSoliServJPA) {
        return (ModificacioSoliServJPA)__bean;
      }
      
      return ModificacioSoliServJPA.toJPA(__bean);
    }


}