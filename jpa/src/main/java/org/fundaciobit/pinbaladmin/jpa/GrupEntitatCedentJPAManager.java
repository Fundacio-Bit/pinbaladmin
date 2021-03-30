
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


public class GrupEntitatCedentJPAManager
		 extends AbstractPinbalAdminJPAManager<GrupEntitatCedent, Long>
		 implements IGrupEntitatCedentManager, GrupEntitatCedentFields {




  private static final long serialVersionUID = -1630509570L;

	 public static final TableName<GrupEntitatCedent> _TABLENAME =  new TableName<GrupEntitatCedent>("GrupEntitatCedentJPA");



  static final ModificationManager<GrupEntitatCedent> __eventManager = new ModificationManager<GrupEntitatCedent>();


  @PersistenceContext
  protected EntityManager __em;
  public GrupEntitatCedentJPAManager() {
  }
  protected GrupEntitatCedentJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return GrupEntitatCedentJPA. class;
	}



	public ModificationManager<GrupEntitatCedent> getEventManager() {
	return __eventManager;
	}


	public TableName<GrupEntitatCedent> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public GrupEntitatCedent[] listToArray(List<GrupEntitatCedent> list)  {
		if(list == null) { return null; };
		return list.toArray(new GrupEntitatCedent[list.size()]);
	};

	public synchronized GrupEntitatCedent create( long _grupEntitatID_, long _cedentID_) throws I18NException {
		GrupEntitatCedentJPA __bean =  new GrupEntitatCedentJPA(_grupEntitatID_,_cedentID_);
		return create(__bean);
	}



 public void delete(long _grupEntitatCedentID_) {
   delete(findByPrimaryKey(_grupEntitatCedentID_));
 }




	public GrupEntitatCedent findByPrimaryKey(long _grupEntitatCedentID_) {
	  return __em.find(GrupEntitatCedentJPA.class, _grupEntitatCedentID_);  
	}
	@Override
	protected GrupEntitatCedent getJPAInstance(GrupEntitatCedent __bean) {
		return convertToJPA(__bean);
	}


	public static GrupEntitatCedentJPA convertToJPA(GrupEntitatCedent __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof GrupEntitatCedentJPA) {
	    return (GrupEntitatCedentJPA)__bean;
	  }
	  
	  return GrupEntitatCedentJPA.toJPA(__bean);
	}


}