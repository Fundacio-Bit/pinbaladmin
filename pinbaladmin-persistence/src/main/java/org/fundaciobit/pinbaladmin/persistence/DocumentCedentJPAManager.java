
package org.fundaciobit.pinbaladmin.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.fundaciobit.pinbaladmin.model.entity.*;
import org.fundaciobit.pinbaladmin.model.fields.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class DocumentCedentJPAManager
         extends AbstractJPAManager<DocumentCedent, Long>
         implements DocumentCedentIJPAManager, IDocumentCedentManager, DocumentCedentFields {



    public static final TableName<DocumentCedent> _TABLENAME =  new TableName<DocumentCedent>("DocumentCedentJPA");


    @PersistenceContext
    protected EntityManager __em;

    public DocumentCedentJPAManager() {
    }

    protected DocumentCedentJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return DocumentCedentJPA. class;
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

    public DocumentCedent create( java.lang.String _titol_, java.lang.String _descripcio_, long _entitatServeiID_, java.sql.Timestamp _dataCreacio_, java.lang.Long _fitxerID_) throws I18NException {
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