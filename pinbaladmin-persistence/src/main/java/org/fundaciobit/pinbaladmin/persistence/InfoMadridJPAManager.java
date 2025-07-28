
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class InfoMadridJPAManager
         extends AbstractJPAManager<InfoMadrid, Long>
         implements InfoMadridIJPAManager, IInfoMadridManager, InfoMadridFields {



    public static final TableName<InfoMadrid> _TABLENAME =  new TableName<InfoMadrid>("InfoMadridJPA");


    @PersistenceContext
    protected EntityManager __em;

    public InfoMadridJPAManager() {
    }

    protected InfoMadridJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return InfoMadridJPA. class;
    }



    public TableName<InfoMadrid> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public InfoMadrid[] listToArray(List<InfoMadrid> list)  {
        if(list == null) { return null; };
        return list.toArray(new InfoMadrid[list.size()]);
    };

    public InfoMadrid create( java.lang.String _codi_, long _estatProcediment_, long _estatAutoritzacio_, java.lang.String _missatge_, java.lang.String _consulta_, java.lang.String _titularNom_, java.lang.String _titularNif_, java.sql.Timestamp _dataAutoritzacio_, java.sql.Timestamp _dataEnviament_, long _intents_) throws I18NException {
        InfoMadridJPA __bean =  new InfoMadridJPA(_codi_,_estatProcediment_,_estatAutoritzacio_,_missatge_,_consulta_,_titularNom_,_titularNif_,_dataAutoritzacio_,_dataEnviament_,_intents_);
        return create(__bean);
    }



 public void delete(long _infoMadridID_) {
   delete(findByPrimaryKey(_infoMadridID_));
 }




    public InfoMadrid findByPrimaryKey(long _infoMadridID_) {
        return __em.find(InfoMadridJPA.class, _infoMadridID_);  
    }
    @Override
    protected InfoMadrid getJPAInstance(InfoMadrid __bean) {
        return convertToJPA(__bean);
    }


    public static InfoMadridJPA convertToJPA(InfoMadrid __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof InfoMadridJPA) {
        return (InfoMadridJPA)__bean;
      }
      
      return InfoMadridJPA.toJPA(__bean);
    }


}