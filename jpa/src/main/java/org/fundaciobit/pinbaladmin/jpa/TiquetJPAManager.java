
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


public class TiquetJPAManager
		 extends AbstractPinbalAdminJPAManager<Tiquet, Long>
		 implements ITiquetManager, TiquetFields {




  private static final long serialVersionUID = -64616614L;

	 public static final TableName<Tiquet> _TABLENAME =  new TableName<Tiquet>("TiquetJPA");



  static final ModificationManager<Tiquet> __eventManager = new ModificationManager<Tiquet>();


  @PersistenceContext
  protected EntityManager __em;
  public TiquetJPAManager() {
  }
  protected TiquetJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return TiquetJPA. class;
	}



	public ModificationManager<Tiquet> getEventManager() {
	return __eventManager;
	}


	public TableName<Tiquet> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Tiquet[] listToArray(List<Tiquet> list)  {
		if(list == null) { return null; };
		return list.toArray(new Tiquet[list.size()]);
	};

	public synchronized Tiquet create( java.lang.String _titol_, java.lang.String _descripcio_, java.lang.String _informador_, java.sql.Timestamp _dataAlta_, long _estatTiquetID_, long _tipusTiquetID_, java.lang.String _versioPinbal_, java.sql.Timestamp _dataInici_, java.sql.Timestamp _dataIncidencia_, java.lang.String _solucionatPer_, java.sql.Timestamp _datafi_, java.lang.String _notes_, int _entorn_, java.lang.Long _adjunt1ID_, java.lang.Long _adjunt2ID_) throws I18NException {
		TiquetJPA __bean =  new TiquetJPA(_titol_,_descripcio_,_informador_,_dataAlta_,_estatTiquetID_,_tipusTiquetID_,_versioPinbal_,_dataInici_,_dataIncidencia_,_solucionatPer_,_datafi_,_notes_,_entorn_,_adjunt1ID_,_adjunt2ID_);
		return create(__bean);
	}



 public void delete(long _tiquetID_) {
   delete(findByPrimaryKey(_tiquetID_));
 }




	public Tiquet findByPrimaryKey(long _tiquetID_) {
	  return __em.find(TiquetJPA.class, _tiquetID_);  
	}
	@Override
	protected Tiquet getJPAInstance(Tiquet __bean) {
		return convertToJPA(__bean);
	}


	public static TiquetJPA convertToJPA(Tiquet __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof TiquetJPA) {
	    return (TiquetJPA)__bean;
	  }
	  
	  return TiquetJPA.toJPA(__bean);
	}


}