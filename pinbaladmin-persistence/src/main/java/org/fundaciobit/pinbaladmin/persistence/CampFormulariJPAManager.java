
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class CampFormulariJPAManager
         extends AbstractJPAManager<CampFormulari, Long>
         implements CampFormulariIJPAManager, ICampFormulariManager, CampFormulariFields {



    public static final TableName<CampFormulari> _TABLENAME =  new TableName<CampFormulari>("CampFormulariJPA");


    @PersistenceContext
    protected EntityManager __em;

    public CampFormulariJPAManager() {
    }

    protected CampFormulariJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return CampFormulariJPA. class;
    }



    public TableName<CampFormulari> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public CampFormulari[] listToArray(List<CampFormulari> list)  {
        if(list == null) { return null; };
        return list.toArray(new CampFormulari[list.size()]);
    };

    public CampFormulari create( java.lang.String _nom_, java.lang.String _campPDF_, long _formulariID_) throws I18NException {
        CampFormulariJPA __bean =  new CampFormulariJPA(_nom_,_campPDF_,_formulariID_);
        return create(__bean);
    }



 public void delete(long _campFormulariID_) {
   delete(findByPrimaryKey(_campFormulariID_));
 }




    public CampFormulari findByPrimaryKey(long _campFormulariID_) {
        return __em.find(CampFormulariJPA.class, _campFormulariID_);  
    }
    @Override
    protected CampFormulari getJPAInstance(CampFormulari __bean) {
        return convertToJPA(__bean);
    }


    public static CampFormulariJPA convertToJPA(CampFormulari __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof CampFormulariJPA) {
        return (CampFormulariJPA)__bean;
      }
      
      return CampFormulariJPA.toJPA(__bean);
    }


}