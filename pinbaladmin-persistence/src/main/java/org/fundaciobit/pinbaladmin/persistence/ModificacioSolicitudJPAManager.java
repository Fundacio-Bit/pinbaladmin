
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class ModificacioSolicitudJPAManager
         extends AbstractJPAManager<ModificacioSolicitud, Long>
         implements ModificacioSolicitudIJPAManager, IModificacioSolicitudManager, ModificacioSolicitudFields {



    public static final TableName<ModificacioSolicitud> _TABLENAME =  new TableName<ModificacioSolicitud>("ModificacioSolicitudJPA");


    @PersistenceContext
    protected EntityManager __em;

    public ModificacioSolicitudJPAManager() {
    }

    protected ModificacioSolicitudJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return ModificacioSolicitudJPA. class;
    }



    public TableName<ModificacioSolicitud> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public ModificacioSolicitud[] listToArray(List<ModificacioSolicitud> list)  {
        if(list == null) { return null; };
        return list.toArray(new ModificacioSolicitud[list.size()]);
    };

    public ModificacioSolicitud create( long _solicitudID_, java.lang.String _procedimentCodi_, java.lang.String _procedimentNom_, java.lang.String _codiSiaNou_, java.lang.Long _estatID_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, java.lang.String _procedimentTipus_, java.lang.String _notes_, java.lang.Long _organID_, java.lang.String _responsableProcNom_, java.lang.String _responsableProceMail_, java.lang.String _consentiment_, java.lang.Long _doCconsentimentID_, java.lang.String _solicitantNom_, java.lang.String _solicitantNif_, java.lang.String _solicitantMail_, java.lang.String _solicitantUsername_, java.lang.Long _estatModificacio_, java.lang.String _contactenom_, java.lang.String _contactemail_, boolean _esmena_) throws I18NException {
        ModificacioSolicitudJPA __bean =  new ModificacioSolicitudJPA(_solicitudID_,_procedimentCodi_,_procedimentNom_,_codiSiaNou_,_estatID_,_dataInici_,_dataFi_,_procedimentTipus_,_notes_,_organID_,_responsableProcNom_,_responsableProceMail_,_consentiment_,_doCconsentimentID_,_solicitantNom_,_solicitantNif_,_solicitantMail_,_solicitantUsername_,_estatModificacio_,_contactenom_,_contactemail_,_esmena_);
        return create(__bean);
    }



 public void delete(long _modsoliID_) {
   delete(findByPrimaryKey(_modsoliID_));
 }




    public ModificacioSolicitud findByPrimaryKey(long _modsoliID_) {
        return __em.find(ModificacioSolicitudJPA.class, _modsoliID_);  
    }
    @Override
    protected ModificacioSolicitud getJPAInstance(ModificacioSolicitud __bean) {
        return convertToJPA(__bean);
    }


    public static ModificacioSolicitudJPA convertToJPA(ModificacioSolicitud __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof ModificacioSolicitudJPA) {
        return (ModificacioSolicitudJPA)__bean;
      }
      
      return ModificacioSolicitudJPA.toJPA(__bean);
    }


}