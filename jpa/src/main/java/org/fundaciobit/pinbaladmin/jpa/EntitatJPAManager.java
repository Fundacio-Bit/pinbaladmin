
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


public class EntitatJPAManager
		 extends AbstractPinbalAdminJPAManager<Entitat, Long>
		 implements IEntitatManager, EntitatFields {




  private static final long serialVersionUID = 1660227099L;

	 public static final TableName<Entitat> _TABLENAME =  new TableName<Entitat>("EntitatJPA");



  static final ModificationManager<Entitat> __eventManager = new ModificationManager<Entitat>();


  @PersistenceContext
  protected EntityManager __em;
  public EntitatJPAManager() {
  }
  protected EntitatJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return EntitatJPA. class;
	}



	public ModificationManager<Entitat> getEventManager() {
	return __eventManager;
	}


	public TableName<Entitat> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Entitat[] listToArray(List<Entitat> list)  {
		if(list == null) { return null; };
		return list.toArray(new Entitat[list.size()]);
	};

	public Entitat create( java.lang.String _nom_, java.lang.String _personaContacte_, java.lang.String _CIF_, long _grupEntitatID_) throws I18NException {
		EntitatJPA __bean =  new EntitatJPA(_nom_,_personaContacte_,_CIF_,_grupEntitatID_);
		return create(__bean);
	}



 public void delete(long _entitatID_) {
   delete(findByPrimaryKey(_entitatID_));
 }




	public Entitat findByPrimaryKey(long _entitatID_) {
	  return __em.find(EntitatJPA.class, _entitatID_);  
	}
	@Override
	protected Entitat getJPAInstance(Entitat __bean) {
		return convertToJPA(__bean);
	}


	public static EntitatJPA convertToJPA(Entitat __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof EntitatJPA) {
	    return (EntitatJPA)__bean;
	  }
	  
	  return EntitatJPA.toJPA(__bean);
	}


}