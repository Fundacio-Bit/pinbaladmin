
package org.fundaciobit.pinbaladmin.jpa;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.events.ModificationManager;


public class CampSolicitudJPAManager
		 extends AbstractPinbalAdminJPAManager<CampSolicitud, Long>
		 implements ICampSolicitudManager, CampSolicitudFields {




  private static final long serialVersionUID = -1709957963L;

	 public static final TableName<CampSolicitud> _TABLENAME =  new TableName<CampSolicitud>("CampSolicitudJPA");



  static final ModificationManager<CampSolicitud> __eventManager = new ModificationManager<CampSolicitud>();


  @PersistenceContext
  protected EntityManager __em;
  public CampSolicitudJPAManager() {
  }
  protected CampSolicitudJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return CampSolicitudJPA. class;
	}



	public ModificationManager<CampSolicitud> getEventManager() {
	return __eventManager;
	}


	public TableName<CampSolicitud> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public CampSolicitud[] listToArray(List<CampSolicitud> list)  {
		if(list == null) { return null; };
		return list.toArray(new CampSolicitud[list.size()]);
	};

	public CampSolicitud create( long _campFormulariID_, long _solicitudServeiID_, java.lang.String _valor_) throws I18NException {
		CampSolicitudJPA __bean =  new CampSolicitudJPA(_campFormulariID_,_solicitudServeiID_,_valor_);
		return create(__bean);
	}



 public void delete(long _campSolicitudID_) {
   delete(findByPrimaryKey(_campSolicitudID_));
 }




	public CampSolicitud findByPrimaryKey(long _campSolicitudID_) {
	  return __em.find(CampSolicitudJPA.class, _campSolicitudID_);  
	}
	@Override
	protected CampSolicitud getJPAInstance(CampSolicitud __bean) {
		return convertToJPA(__bean);
	}


	public static CampSolicitudJPA convertToJPA(CampSolicitud __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof CampSolicitudJPA) {
	    return (CampSolicitudJPA)__bean;
	  }
	  
	  return CampSolicitudJPA.toJPA(__bean);
	}


}