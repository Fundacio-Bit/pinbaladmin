
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class FormulariJPAManager
         extends AbstractJPAManager<Formulari, Long>
         implements FormulariIJPAManager, IFormulariManager, FormulariFields {



    public static final TableName<Formulari> _TABLENAME =  new TableName<Formulari>("FormulariJPA");


    @PersistenceContext
    protected EntityManager __em;

    public FormulariJPAManager() {
    }

    protected FormulariJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return FormulariJPA. class;
    }



    public TableName<Formulari> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Formulari[] listToArray(List<Formulari> list)  {
        if(list == null) { return null; };
        return list.toArray(new Formulari[list.size()]);
    };

    public Formulari create( java.lang.String _nom_, java.lang.String _descripcio_, java.lang.Long _fitxerID_) throws I18NException {
        FormulariJPA __bean =  new FormulariJPA(_nom_,_descripcio_,_fitxerID_);
        return create(__bean);
    }



 public void delete(long _formulariid_) {
   delete(findByPrimaryKey(_formulariid_));
 }




    public Formulari findByPrimaryKey(long _formulariid_) {
        return __em.find(FormulariJPA.class, _formulariid_);  
    }
    @Override
    protected Formulari getJPAInstance(Formulari __bean) {
        return convertToJPA(__bean);
    }


    public static FormulariJPA convertToJPA(Formulari __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof FormulariJPA) {
        return (FormulariJPA)__bean;
      }
      
      return FormulariJPA.toJPA(__bean);
    }


}