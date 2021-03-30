
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


public class DocumentJPAManager
		 extends AbstractPinbalAdminJPAManager<Document, Long>
		 implements IDocumentManager, DocumentFields {




  private static final long serialVersionUID = 1878628077L;

	 public static final TableName<Document> _TABLENAME =  new TableName<Document>("DocumentJPA");



  static final ModificationManager<Document> __eventManager = new ModificationManager<Document>();


  @PersistenceContext
  protected EntityManager __em;
  public DocumentJPAManager() {
  }
  protected DocumentJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return DocumentJPA. class;
	}



	public ModificationManager<Document> getEventManager() {
	return __eventManager;
	}


	public TableName<Document> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public Document[] listToArray(List<Document> list)  {
		if(list == null) { return null; };
		return list.toArray(new Document[list.size()]);
	};

	public synchronized Document create( java.lang.String _nom_, long _fitxerOriginalID_, java.lang.Long _fitxerFirmatID_, java.lang.String _notes_) throws I18NException {
		DocumentJPA __bean =  new DocumentJPA(_nom_,_fitxerOriginalID_,_fitxerFirmatID_,_notes_);
		return create(__bean);
	}



 public void delete(long _documentID_) {
   delete(findByPrimaryKey(_documentID_));
 }




	public Document findByPrimaryKey(long _documentID_) {
	  return __em.find(DocumentJPA.class, _documentID_);  
	}
	@Override
	protected Document getJPAInstance(Document __bean) {
		return convertToJPA(__bean);
	}


	public static DocumentJPA convertToJPA(Document __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof DocumentJPA) {
	    return (DocumentJPA)__bean;
	  }
	  
	  return DocumentJPA.toJPA(__bean);
	}


}