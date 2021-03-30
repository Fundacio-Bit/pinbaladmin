
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


public class DocumentCedentJPAManager
		 extends AbstractPinbalAdminJPAManager<DocumentCedent, Long>
		 implements IDocumentCedentManager, DocumentCedentFields {




  private static final long serialVersionUID = 707210486L;

	 public static final TableName<DocumentCedent> _TABLENAME =  new TableName<DocumentCedent>("DocumentCedentJPA");



  static final ModificationManager<DocumentCedent> __eventManager = new ModificationManager<DocumentCedent>();


  @PersistenceContext
  protected EntityManager __em;
  public DocumentCedentJPAManager() {
  }
  protected DocumentCedentJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return DocumentCedentJPA. class;
	}



	public ModificationManager<DocumentCedent> getEventManager() {
	return __eventManager;
	}


	public TableName<DocumentCedent> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public DocumentCedent[] listToArray(List<DocumentCedent> list)  {
		if(list == null) { return null; };
		return list.toArray(new DocumentCedent[list.size()]);
	};

	public synchronized DocumentCedent create( java.lang.String _titol_, java.lang.String _descripcio_, long _entitatServeiID_, java.sql.Timestamp _dataCreacio_, java.lang.Long _fitxerID_) throws I18NException {
		DocumentCedentJPA __bean =  new DocumentCedentJPA(_titol_,_descripcio_,_entitatServeiID_,_dataCreacio_,_fitxerID_);
		return create(__bean);
	}



 public void delete(long _documentCedentID_) {
   delete(findByPrimaryKey(_documentCedentID_));
 }




	public DocumentCedent findByPrimaryKey(long _documentCedentID_) {
	  return __em.find(DocumentCedentJPA.class, _documentCedentID_);  
	}
	@Override
	protected DocumentCedent getJPAInstance(DocumentCedent __bean) {
		return convertToJPA(__bean);
	}


	public static DocumentCedentJPA convertToJPA(DocumentCedent __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof DocumentCedentJPA) {
	    return (DocumentCedentJPA)__bean;
	  }
	  
	  return DocumentCedentJPA.toJPA(__bean);
	}


}