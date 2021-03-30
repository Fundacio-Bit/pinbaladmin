
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


public class AreaJPAManager
		 extends AbstractPinbalAdminJPAManager<Area, Long>
		 implements IAreaManager, AreaFields {




  private static final long serialVersionUID = -349012001L;

	 public static final TableName<Area> _TABLENAME =  new TableName<Area>("AreaJPA");



  static final ModificationManager<Area> __eventManager = new ModificationManager<Area>();


  @PersistenceContext
  protected EntityManager __em;
  public AreaJPAManager() {
  }
  protected AreaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return AreaJPA. class;
	}



	public ModificationManager<Area> getEventManager() {
	return __eventManager;
	}


	public TableName<Area> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Area[] listToArray(List<Area> list)  {
		if(list == null) { return null; };
		return list.toArray(new Area[list.size()]);
	};

	public synchronized Area create( java.lang.String _nom_, long _entitatID_) throws I18NException {
		AreaJPA __bean =  new AreaJPA(_nom_,_entitatID_);
		return create(__bean);
	}



 public void delete(long _areaID_) {
   delete(findByPrimaryKey(_areaID_));
 }




	public Area findByPrimaryKey(long _areaID_) {
	  return __em.find(AreaJPA.class, _areaID_);  
	}
	@Override
	protected Area getJPAInstance(Area __bean) {
		return convertToJPA(__bean);
	}


	public static AreaJPA convertToJPA(Area __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof AreaJPA) {
	    return (AreaJPA)__bean;
	  }
	  
	  return AreaJPA.toJPA(__bean);
	}


}