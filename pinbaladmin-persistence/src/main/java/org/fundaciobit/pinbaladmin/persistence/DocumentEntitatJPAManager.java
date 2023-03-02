
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class DocumentEntitatJPAManager
         extends AbstractJPAManager<DocumentEntitat, Long>
         implements DocumentEntitatIJPAManager, IDocumentEntitatManager, DocumentEntitatFields {



    public static final TableName<DocumentEntitat> _TABLENAME =  new TableName<DocumentEntitat>("DocumentEntitatJPA");


    @PersistenceContext
    protected EntityManager __em;

    public DocumentEntitatJPAManager() {
    }

    protected DocumentEntitatJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return DocumentEntitatJPA. class;
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

    public DocumentEntitat create( java.lang.String _titol_, java.lang.String _descripcio_, long _entitatID_, java.lang.Long _fitxerID_, java.sql.Timestamp _dataAlta_) throws I18NException {
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