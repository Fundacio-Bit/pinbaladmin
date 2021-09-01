
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


public class IncidenciaTecnicaJPAManager
		 extends AbstractPinbalAdminJPAManager<IncidenciaTecnica, Long>
		 implements IIncidenciaTecnicaManager, IncidenciaTecnicaFields {




  private static final long serialVersionUID = -473994396L;

	 public static final TableName<IncidenciaTecnica> _TABLENAME =  new TableName<IncidenciaTecnica>("IncidenciaTecnicaJPA");



  static final ModificationManager<IncidenciaTecnica> __eventManager = new ModificationManager<IncidenciaTecnica>();


  @PersistenceContext
  protected EntityManager __em;
  public IncidenciaTecnicaJPAManager() {
  }
  protected IncidenciaTecnicaJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return IncidenciaTecnicaJPA. class;
	}



	public ModificationManager<IncidenciaTecnica> getEventManager() {
	return __eventManager;
	}


	public TableName<IncidenciaTecnica> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public IncidenciaTecnica[] listToArray(List<IncidenciaTecnica> list)  {
		if(list == null) { return null; };
		return list.toArray(new IncidenciaTecnica[list.size()]);
	};

	public IncidenciaTecnica create( java.lang.String _titol_, java.lang.String _descripcio_, java.sql.Timestamp _dataInici_, int _estat_, java.lang.String _creador_, int _tipus_, java.lang.String _nomEntitat_, java.lang.String _contacteNom_, java.lang.String _contacteEmail_, java.lang.String _contacteTelefon_, java.lang.String _caidIdentificadorConsulta_, java.lang.String _caidNumeroSeguiment_) throws I18NException {
		IncidenciaTecnicaJPA __bean =  new IncidenciaTecnicaJPA(_titol_,_descripcio_,_dataInici_,_estat_,_creador_,_tipus_,_nomEntitat_,_contacteNom_,_contacteEmail_,_contacteTelefon_,_caidIdentificadorConsulta_,_caidNumeroSeguiment_);
		return create(__bean);
	}



 public void delete(long _incidenciaTecnicaID_) {
   delete(findByPrimaryKey(_incidenciaTecnicaID_));
 }




	public IncidenciaTecnica findByPrimaryKey(long _incidenciaTecnicaID_) {
	  return __em.find(IncidenciaTecnicaJPA.class, _incidenciaTecnicaID_);  
	}
	@Override
	protected IncidenciaTecnica getJPAInstance(IncidenciaTecnica __bean) {
		return convertToJPA(__bean);
	}


	public static IncidenciaTecnicaJPA convertToJPA(IncidenciaTecnica __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof IncidenciaTecnicaJPA) {
	    return (IncidenciaTecnicaJPA)__bean;
	  }
	  
	  return IncidenciaTecnicaJPA.toJPA(__bean);
	}


}