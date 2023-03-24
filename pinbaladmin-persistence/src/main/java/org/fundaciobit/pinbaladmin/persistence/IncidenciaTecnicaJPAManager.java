
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class IncidenciaTecnicaJPAManager
         extends AbstractJPAManager<IncidenciaTecnica, Long>
         implements IncidenciaTecnicaIJPAManager, IIncidenciaTecnicaManager, IncidenciaTecnicaFields {



    public static final TableName<IncidenciaTecnica> _TABLENAME =  new TableName<IncidenciaTecnica>("IncidenciaTecnicaJPA");


    @PersistenceContext
    protected EntityManager __em;

    public IncidenciaTecnicaJPAManager() {
    }

    protected IncidenciaTecnicaJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return IncidenciaTecnicaJPA. class;
    }



    public TableName<IncidenciaTecnica> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public IncidenciaTecnica[] listToArray(List<IncidenciaTecnica> list)  {
        if(list == null) { return null; };
        return list.toArray(new IncidenciaTecnica[list.size()]);
    };

    public IncidenciaTecnica create( java.lang.String _titol_, java.lang.String _descripcio_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _datafi_, int _estat_, java.lang.String _creador_, int _tipus_, java.lang.String _nomEntitat_, java.lang.String _contacteNom_, java.lang.String _contacteEmail_, java.lang.String _contacteTelefon_, java.lang.String _caidIdentificadorConsulta_, java.lang.String _caidNumeroSeguiment_) throws I18NException {
        IncidenciaTecnicaJPA __bean =  new IncidenciaTecnicaJPA(_titol_,_descripcio_,_dataInici_,_datafi_,_estat_,_creador_,_tipus_,_nomEntitat_,_contacteNom_,_contacteEmail_,_contacteTelefon_,_caidIdentificadorConsulta_,_caidNumeroSeguiment_);
        return create(__bean);
    }



 public void delete(long _incidenciaTecnicaID_) {
   delete(findByPrimaryKey(_incidenciaTecnicaID_));
 }




    public IncidenciaTecnica findByPrimaryKey(long _incidenciaTecnicaID_) {
        return __em.find(IncidenciaTecnicaJPA.class, _incidenciaTecnicaID_);  
    }
    @Override
    protected IncidenciaTecnica getJPAInstance(IncidenciaTecnica __bean) {
        return convertToJPA(__bean);
    }


    public static IncidenciaTecnicaJPA convertToJPA(IncidenciaTecnica __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof IncidenciaTecnicaJPA) {
        return (IncidenciaTecnicaJPA)__bean;
      }
      
      return IncidenciaTecnicaJPA.toJPA(__bean);
    }


}