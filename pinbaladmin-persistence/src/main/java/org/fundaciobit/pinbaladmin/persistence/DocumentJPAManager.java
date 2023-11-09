
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class DocumentJPAManager
         extends AbstractJPAManager<Document, Long>
         implements DocumentIJPAManager, IDocumentManager, DocumentFields {



    public static final TableName<Document> _TABLENAME =  new TableName<Document>("DocumentJPA");


    @PersistenceContext
    protected EntityManager __em;

    public DocumentJPAManager() {
    }

    protected DocumentJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return DocumentJPA. class;
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

    public Document create( java.lang.String _nom_, long _fitxerOriginalID_, java.lang.Long _fitxerFirmatID_, java.lang.String _notes_, java.lang.Long _tipus_) throws I18NException {
        DocumentJPA __bean =  new DocumentJPA(_nom_,_fitxerOriginalID_,_fitxerFirmatID_,_notes_,_tipus_);
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