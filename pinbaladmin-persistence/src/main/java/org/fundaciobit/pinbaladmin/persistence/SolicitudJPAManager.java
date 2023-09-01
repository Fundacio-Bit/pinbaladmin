
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class SolicitudJPAManager
         extends AbstractJPAManager<Solicitud, Long>
         implements SolicitudIJPAManager, ISolicitudManager, SolicitudFields {



    public static final TableName<Solicitud> _TABLENAME =  new TableName<Solicitud>("SolicitudJPA");


    @PersistenceContext
    protected EntityManager __em;

    public SolicitudJPAManager() {
    }

    protected SolicitudJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return SolicitudJPA. class;
    }



    public TableName<Solicitud> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Solicitud[] listToArray(List<Solicitud> list)  {
        if(list == null) { return null; };
        return list.toArray(new Solicitud[list.size()]);
    };

    public Solicitud create( java.lang.String _procedimentCodi_, java.lang.String _codiDescriptiu_, java.lang.String _procedimentNom_, java.lang.String _procedimentTipus_, java.lang.Long _estatID_, java.lang.String _ticketAssociat_, java.lang.String _ticketNumeroSeguiment_, java.lang.Long _departamentID_, java.lang.Long _organid_, java.lang.String _entitatEstatal_, java.lang.String _pinfo_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataFi_, java.lang.String _personaContacte_, java.lang.String _personaContacteEmail_, java.lang.String _responsableProcNom_, java.lang.String _responsableProcEmail_, java.lang.String _notes_, java.lang.Long _documentSolicitudID_, java.lang.Long _solicitudXmlID_, boolean _firmatDocSolicitud_, boolean _produccio_, java.lang.String _denominacio_, java.lang.String _dir3_, java.lang.String _nif_, java.lang.String _creador_, java.lang.String _operador_) throws I18NException {
        SolicitudJPA __bean =  new SolicitudJPA(_procedimentCodi_,_codiDescriptiu_,_procedimentNom_,_procedimentTipus_,_estatID_,_ticketAssociat_,_ticketNumeroSeguiment_,_departamentID_,_organid_,_entitatEstatal_,_pinfo_,_dataInici_,_dataFi_,_personaContacte_,_personaContacteEmail_,_responsableProcNom_,_responsableProcEmail_,_notes_,_documentSolicitudID_,_solicitudXmlID_,_firmatDocSolicitud_,_produccio_,_denominacio_,_dir3_,_nif_,_creador_,_operador_);
        return create(__bean);
    }



 public void delete(long _solicitudID_) {
   delete(findByPrimaryKey(_solicitudID_));
 }




    public Solicitud findByPrimaryKey(long _solicitudID_) {
        return __em.find(SolicitudJPA.class, _solicitudID_);  
    }
    @Override
    protected Solicitud getJPAInstance(Solicitud __bean) {
        return convertToJPA(__bean);
    }


    public static SolicitudJPA convertToJPA(Solicitud __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof SolicitudJPA) {
        return (SolicitudJPA)__bean;
      }
      
      return SolicitudJPA.toJPA(__bean);
    }


}