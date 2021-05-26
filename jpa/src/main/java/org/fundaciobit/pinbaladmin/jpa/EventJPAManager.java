
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


public class EventJPAManager
		 extends AbstractPinbalAdminJPAManager<Event, Long>
		 implements IEventManager, EventFields {




  private static final long serialVersionUID = 466723180L;

	 public static final TableName<Event> _TABLENAME =  new TableName<Event>("EventJPA");



  static final ModificationManager<Event> __eventManager = new ModificationManager<Event>();


  @PersistenceContext
  protected EntityManager __em;
  public EventJPAManager() {
  }
  protected EventJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return EventJPA. class;
	}



	public ModificationManager<Event> getEventManager() {
	return __eventManager;
	}


	public TableName<Event> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Event[] listToArray(List<Event> list)  {
		if(list == null) { return null; };
		return list.toArray(new Event[list.size()]);
	};

	public synchronized Event create( java.lang.Long _solicitudID_, java.lang.Long _tascaTecnicaID_, java.sql.Timestamp _dataEvent_, int _tipus_, java.lang.String _persona_, java.lang.String _comentari_, java.lang.Long _fitxerID_, boolean _noLlegit_) throws I18NException {
		EventJPA __bean =  new EventJPA(_solicitudID_,_tascaTecnicaID_,_dataEvent_,_tipus_,_persona_,_comentari_,_fitxerID_,_noLlegit_);
		return create(__bean);
	}



 public void delete(long _eventID_) {
   delete(findByPrimaryKey(_eventID_));
 }




	public Event findByPrimaryKey(long _eventID_) {
	  return __em.find(EventJPA.class, _eventID_);  
	}
	@Override
	protected Event getJPAInstance(Event __bean) {
		return convertToJPA(__bean);
	}


	public static EventJPA convertToJPA(Event __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof EventJPA) {
	    return (EventJPA)__bean;
	  }
	  
	  return EventJPA.toJPA(__bean);
	}


}