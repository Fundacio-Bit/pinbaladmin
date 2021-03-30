
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


public class DocumentEntitatJPAManager
		 extends AbstractPinbalAdminJPAManager<DocumentEntitat, Long>
		 implements IDocumentEntitatManager, DocumentEntitatFields {




  private static final long serialVersionUID = 571315776L;

	 public static final TableName<DocumentEntitat> _TABLENAME =  new TableName<DocumentEntitat>("DocumentEntitatJPA");



  static final ModificationManager<DocumentEntitat> __eventManager = new ModificationManager<DocumentEntitat>();


  @PersistenceContext
  protected EntityManager __em;
  public DocumentEntitatJPAManager() {
  }
  protected DocumentEntitatJPAManager(EntityManager __em) {
    this.__em = __em;
  }


  protected EntityManager getEntityManager() {
    return this.__em;
  }
	public Class<?> getJPAClass() {
		return DocumentEntitatJPA. class;
	}



	public ModificationManager<DocumentEntitat> getEventManager() {
	return __eventManager;
	}


	public TableName<DocumentEntitat> getTableName() {
		return _TABLENAME;
	}


	@Override
	protected String getTableNameVariable() {
		return _TABLE_MODEL;
	}


	public DocumentEntitat[] listToArray(List<DocumentEntitat> list)  {
		if(list == null) { return null; };
		return list.toArray(new DocumentEntitat[list.size()]);
	};

	public synchronized DocumentEntitat create( java.lang.String _titol_, java.lang.String _descripcio_, long _entitatID_, java.lang.Long _fitxerID_, java.sql.Timestamp _dataAlta_) throws I18NException {
		DocumentEntitatJPA __bean =  new DocumentEntitatJPA(_titol_,_descripcio_,_entitatID_,_fitxerID_,_dataAlta_);
		return create(__bean);
	}



 public void delete(long _documentEntitatID_) {
   delete(findByPrimaryKey(_documentEntitatID_));
 }




	public DocumentEntitat findByPrimaryKey(long _documentEntitatID_) {
	  return __em.find(DocumentEntitatJPA.class, _documentEntitatID_);  
	}
	@Override
	protected DocumentEntitat getJPAInstance(DocumentEntitat __bean) {
		return convertToJPA(__bean);
	}


	public static DocumentEntitatJPA convertToJPA(DocumentEntitat __bean) {
	  if (__bean == null) {
	    return null;
	  }
	  if(__bean instanceof DocumentEntitatJPA) {
	    return (DocumentEntitatJPA)__bean;
	  }
	  
	  return DocumentEntitatJPA.toJPA(__bean);
	}


}