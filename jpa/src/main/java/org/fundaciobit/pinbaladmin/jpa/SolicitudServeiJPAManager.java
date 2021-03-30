
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


public class SolicitudServeiJPAManager
		 extends AbstractPinbalAdminJPAManager<SolicitudServei, Long>
		 implements ISolicitudServeiManager, SolicitudServeiFields {




  private static final long serialVersionUID = 580057296L;

	 public static final TableName<SolicitudServei> _TABLENAME =  new TableName<SolicitudServei>("SolicitudServeiJPA");



  static final ModificationManager<SolicitudServei> __eventManager = new ModificationManager<SolicitudServei>();


  @PersistenceContext
  protected EntityManager __em;
  public SolicitudServeiJPAManager() {
  }
  protected SolicitudServeiJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return SolicitudServeiJPA. class;
	}



	public ModificationManager<SolicitudServei> getEventManager() {
	return __eventManager;
	}


	public TableName<SolicitudServei> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public SolicitudServei[] listToArray(List<SolicitudServei> list)  {
		if(list == null) { return null; };
		return list.toArray(new SolicitudServei[list.size()]);
	};

	public synchronized SolicitudServei create( long _solicitudID_, long _serveiID_, java.lang.Long _estatSolicitudServeiID_, java.lang.String _normaLegal_, java.lang.String _enllazNormaLegal_, java.lang.String _articles_, java.lang.String _tipusConsentiment_, java.lang.String _consentiment_, java.lang.String _enllazConsentiment_, java.lang.String _notes_) throws I18NException {
		SolicitudServeiJPA __bean =  new SolicitudServeiJPA(_solicitudID_,_serveiID_,_estatSolicitudServeiID_,_normaLegal_,_enllazNormaLegal_,_articles_,_tipusConsentiment_,_consentiment_,_enllazConsentiment_,_notes_);
		return create(__bean);
	}



 public void delete(long _id_) {
   delete(findByPrimaryKey(_id_));
 }




	public SolicitudServei findByPrimaryKey(long _id_) {
	  return __em.find(SolicitudServeiJPA.class, _id_);  
	}
	@Override
	protected SolicitudServei getJPAInstance(SolicitudServei __bean) {
		return convertToJPA(__bean);
	}


	public static SolicitudServeiJPA convertToJPA(SolicitudServei __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof SolicitudServeiJPA) {
	    return (SolicitudServeiJPA)__bean;
	  }
	  
	  return SolicitudServeiJPA.toJPA(__bean);
	}


}