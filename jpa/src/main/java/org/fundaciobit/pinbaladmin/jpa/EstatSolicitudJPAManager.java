
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


public class EstatSolicitudJPAManager
		 extends AbstractPinbalAdminJPAManager<EstatSolicitud, Long>
		 implements IEstatSolicitudManager, EstatSolicitudFields {




  private static final long serialVersionUID = 1930759229L;

	 public static final TableName<EstatSolicitud> _TABLENAME =  new TableName<EstatSolicitud>("EstatSolicitudJPA");



  static final ModificationManager<EstatSolicitud> __eventManager = new ModificationManager<EstatSolicitud>();


  @PersistenceContext
  protected EntityManager __em;
  public EstatSolicitudJPAManager() {
  }
  protected EstatSolicitudJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return EstatSolicitudJPA. class;
	}



	public ModificationManager<EstatSolicitud> getEventManager() {
	return __eventManager;
	}


	public TableName<EstatSolicitud> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public EstatSolicitud[] listToArray(List<EstatSolicitud> list)  {
		if(list == null) { return null; };
		return list.toArray(new EstatSolicitud[list.size()]);
	};

	public synchronized EstatSolicitud create( long _estatSolicitudID_, java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException {
		EstatSolicitudJPA __bean =  new EstatSolicitudJPA(_estatSolicitudID_,_nom_,_descripcio_);
		return create(__bean);
	}



 public void delete(long _estatSolicitudID_) {
   delete(findByPrimaryKey(_estatSolicitudID_));
 }




	public EstatSolicitud findByPrimaryKey(long _estatSolicitudID_) {
	  return __em.find(EstatSolicitudJPA.class, _estatSolicitudID_);  
	}
	@Override
	protected EstatSolicitud getJPAInstance(EstatSolicitud __bean) {
		return convertToJPA(__bean);
	}


	public static EstatSolicitudJPA convertToJPA(EstatSolicitud __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof EstatSolicitudJPA) {
	    return (EstatSolicitudJPA)__bean;
	  }
	  
	  return EstatSolicitudJPA.toJPA(__bean);
	}


}