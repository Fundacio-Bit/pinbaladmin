
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


public class OperadorJPAManager
		 extends AbstractPinbalAdminJPAManager<Operador, Long>
		 implements IOperadorManager, OperadorFields {




  private static final long serialVersionUID = 1098477350L;

	 public static final TableName<Operador> _TABLENAME =  new TableName<Operador>("OperadorJPA");



  static final ModificationManager<Operador> __eventManager = new ModificationManager<Operador>();


  @PersistenceContext
  protected EntityManager __em;
  public OperadorJPAManager() {
  }
  protected OperadorJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return OperadorJPA. class;
	}



	public ModificationManager<Operador> getEventManager() {
	return __eventManager;
	}


	public TableName<Operador> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Operador[] listToArray(List<Operador> list)  {
		if(list == null) { return null; };
		return list.toArray(new Operador[list.size()]);
	};

	public Operador create( java.lang.String _username_, java.lang.String _nom_, java.lang.String _email_) throws I18NException {
		OperadorJPA __bean =  new OperadorJPA(_username_,_nom_,_email_);
		return create(__bean);
	}



 public void delete(long _operadorID_) {
   delete(findByPrimaryKey(_operadorID_));
 }




	public Operador findByPrimaryKey(long _operadorID_) {
	  return __em.find(OperadorJPA.class, _operadorID_);  
	}
	@Override
	protected Operador getJPAInstance(Operador __bean) {
		return convertToJPA(__bean);
	}


	public static OperadorJPA convertToJPA(Operador __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof OperadorJPA) {
	    return (OperadorJPA)__bean;
	  }
	  
	  return OperadorJPA.toJPA(__bean);
	}


}