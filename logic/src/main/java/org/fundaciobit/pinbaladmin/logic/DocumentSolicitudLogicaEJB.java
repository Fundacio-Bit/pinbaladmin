package org.fundaciobit.pinbaladmin.logic;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudEJB;
import org.fundaciobit.pinbaladmin.jpa.DocumentJPA;
import org.fundaciobit.pinbaladmin.logic.utils.LogicUtils;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.jboss.ejb3.annotation.SecurityDomain;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "DocumentSolicitudLogicaEJB")
@SecurityDomain("seycon")
public class DocumentSolicitudLogicaEJB extends DocumentSolicitudEJB implements DocumentSolicitudLogicaLocal {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentLocal documentEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FitxerLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.FitxerLocal fitxerEjb;

  @Override
  public Set<Long> deleteFull(Long documentId, Long solicitudId, boolean deleteFiles)
      throws I18NException {

    Set<Long> files = new HashSet<Long>();

    DocumentJPA document = documentEjb.findByPrimaryKey(documentId);
    
    if (document == null) {
      return files;
    }
    
    this.delete(
        Where.AND(
            DocumentSolicitudFields.SOLICITUDID.equal(solicitudId),
            DocumentSolicitudFields.DOCUMENTID.equal(documentId)
            ));
    
    if (document.getFitxerFirmatID() != null) {
      files.add(document.getFitxerFirmatID());
    }
    
    files.add(document.getFitxerOriginalID());

    documentEjb.delete(document);
    
    
    if (deleteFiles) {
      LogicUtils.deleteFiles(files, fitxerEjb);
    }

    return files;
  }

}