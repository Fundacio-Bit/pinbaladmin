
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


public class EntitatServeiJPAManager
		 extends AbstractPinbalAdminJPAManager<EntitatServei, Long>
		 implements IEntitatServeiManager, EntitatServeiFields {




  private static final long serialVersionUID = -626270667L;

	 public static final TableName<EntitatServei> _TABLENAME =  new TableName<EntitatServei>("EntitatServeiJPA");



  static final ModificationManager<EntitatServei> __eventManager = new ModificationManager<EntitatServei>();


  @PersistenceContext
  protected EntityManager __em;
  public EntitatServeiJPAManager() {
  }
  protected EntitatServeiJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return EntitatServeiJPA. class;
	}



	public ModificationManager<EntitatServei> getEventManager() {
	return __eventManager;
	}


	public TableName<EntitatServei> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public EntitatServei[] listToArray(List<EntitatServei> list)  {
		if(list == null) { return null; };
		return list.toArray(new EntitatServei[list.size()]);
	};

	public synchronized EntitatServei create( java.lang.String _nom_, java.lang.String _descripcio_, boolean _balears_) throws I18NException {
		EntitatServeiJPA __bean =  new EntitatServeiJPA(_nom_,_descripcio_,_balears_);
		return create(__bean);
	}



 public void delete(long _entitatServeiID_) {
   delete(findByPrimaryKey(_entitatServeiID_));
 }




	public EntitatServei findByPrimaryKey(long _entitatServeiID_) {
	  return __em.find(EntitatServeiJPA.class, _entitatServeiID_);  
	}
	@Override
	protected EntitatServei getJPAInstance(EntitatServei __bean) {
		return convertToJPA(__bean);
	}


	public static EntitatServeiJPA convertToJPA(EntitatServei __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof EntitatServeiJPA) {
	    return (EntitatServeiJPA)__bean;
	  }
	  
	  return EntitatServeiJPA.toJPA(__bean);
	}


}