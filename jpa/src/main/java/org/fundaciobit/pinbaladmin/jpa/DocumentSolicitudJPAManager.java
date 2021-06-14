
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


public class DocumentSolicitudJPAManager
		 extends AbstractPinbalAdminJPAManager<DocumentSolicitud, Long>
		 implements IDocumentSolicitudManager, DocumentSolicitudFields {




  private static final long serialVersionUID = -577369125L;

	 public static final TableName<DocumentSolicitud> _TABLENAME =  new TableName<DocumentSolicitud>("DocumentSolicitudJPA");



  static final ModificationManager<DocumentSolicitud> __eventManager = new ModificationManager<DocumentSolicitud>();


  @PersistenceContext
  protected EntityManager __em;
  public DocumentSolicitudJPAManager() {
  }
  protected DocumentSolicitudJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return DocumentSolicitudJPA. class;
	}



	public ModificationManager<DocumentSolicitud> getEventManager() {
	return __eventManager;
	}


	public TableName<DocumentSolicitud> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public DocumentSolicitud[] listToArray(List<DocumentSolicitud> list)  {
		if(list == null) { return null; };
		return list.toArray(new DocumentSolicitud[list.size()]);
	};

	public DocumentSolicitud create( long _documentID_, long _solicitudID_) throws I18NException {
		DocumentSolicitudJPA __bean =  new DocumentSolicitudJPA(_documentID_,_solicitudID_);
		return create(__bean);
	}



 public void delete(long _documentSolicitudID_) {
   delete(findByPrimaryKey(_documentSolicitudID_));
 }




	public DocumentSolicitud findByPrimaryKey(long _documentSolicitudID_) {
	  return __em.find(DocumentSolicitudJPA.class, _documentSolicitudID_);  
	}
	@Override
	protected DocumentSolicitud getJPAInstance(DocumentSolicitud __bean) {
		return convertToJPA(__bean);
	}


	public static DocumentSolicitudJPA convertToJPA(DocumentSolicitud __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof DocumentSolicitudJPA) {
	    return (DocumentSolicitudJPA)__bean;
	  }
	  
	  return DocumentSolicitudJPA.toJPA(__bean);
	}


}