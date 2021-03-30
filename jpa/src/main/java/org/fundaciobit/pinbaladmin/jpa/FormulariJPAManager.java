
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


public class FormulariJPAManager
		 extends AbstractPinbalAdminJPAManager<Formulari, Long>
		 implements IFormulariManager, FormulariFields {




  private static final long serialVersionUID = -1882548945L;

	 public static final TableName<Formulari> _TABLENAME =  new TableName<Formulari>("FormulariJPA");



  static final ModificationManager<Formulari> __eventManager = new ModificationManager<Formulari>();


  @PersistenceContext
  protected EntityManager __em;
  public FormulariJPAManager() {
  }
  protected FormulariJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return FormulariJPA. class;
	}



	public ModificationManager<Formulari> getEventManager() {
	return __eventManager;
	}


	public TableName<Formulari> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Formulari[] listToArray(List<Formulari> list)  {
		if(list == null) { return null; };
		return list.toArray(new Formulari[list.size()]);
	};

	public synchronized Formulari create( java.lang.String _nom_, java.lang.String _descripcio_, java.lang.Long _fitxerID_) throws I18NException {
		FormulariJPA __bean =  new FormulariJPA(_nom_,_descripcio_,_fitxerID_);
		return create(__bean);
	}



 public void delete(long _formulariid_) {
   delete(findByPrimaryKey(_formulariid_));
 }




	public Formulari findByPrimaryKey(long _formulariid_) {
	  return __em.find(FormulariJPA.class, _formulariid_);  
	}
	@Override
	protected Formulari getJPAInstance(Formulari __bean) {
		return convertToJPA(__bean);
	}


	public static FormulariJPA convertToJPA(Formulari __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof FormulariJPA) {
	    return (FormulariJPA)__bean;
	  }
	  
	  return FormulariJPA.toJPA(__bean);
	}


}