
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


public class ServeiJPAManager
		 extends AbstractPinbalAdminJPAManager<Servei, Long>
		 implements IServeiManager, ServeiFields {




  private static final long serialVersionUID = 498311756L;

	 public static final TableName<Servei> _TABLENAME =  new TableName<Servei>("ServeiJPA");



  static final ModificationManager<Servei> __eventManager = new ModificationManager<Servei>();


  @PersistenceContext
  protected EntityManager __em;
  public ServeiJPAManager() {
  }
  protected ServeiJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return ServeiJPA. class;
	}



	public ModificationManager<Servei> getEventManager() {
	return __eventManager;
	}


	public TableName<Servei> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Servei[] listToArray(List<Servei> list)  {
		if(list == null) { return null; };
		return list.toArray(new Servei[list.size()]);
	};

	public synchronized Servei create( java.lang.String _codi_, java.lang.String _nom_, java.lang.String _descripcio_, java.lang.Long _formulariID_, java.lang.Long _entitatServeiID_, java.lang.Long _estatServeiID_, int _tipusConsentiment_, boolean _ocult_) throws I18NException {
		ServeiJPA __bean =  new ServeiJPA(_codi_,_nom_,_descripcio_,_formulariID_,_entitatServeiID_,_estatServeiID_,_tipusConsentiment_,_ocult_);
		return create(__bean);
	}



 public void delete(long _serveiID_) {
   delete(findByPrimaryKey(_serveiID_));
 }




	public Servei findByPrimaryKey(long _serveiID_) {
	  return __em.find(ServeiJPA.class, _serveiID_);  
	}
	@Override
	protected Servei getJPAInstance(Servei __bean) {
		return convertToJPA(__bean);
	}


	public static ServeiJPA convertToJPA(Servei __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof ServeiJPA) {
	    return (ServeiJPA)__bean;
	  }
	  
	  return ServeiJPA.toJPA(__bean);
	}


}