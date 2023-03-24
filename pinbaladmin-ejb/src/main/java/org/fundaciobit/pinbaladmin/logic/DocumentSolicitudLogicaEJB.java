package org.fundaciobit.pinbaladmin.logic;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.ejb.DocumentService;
import org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudEJB;
import org.fundaciobit.pinbaladmin.logic.utils.LogicUtils;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "DocumentSolicitudLogicaEJB")
public class DocumentSolicitudLogicaEJB extends DocumentSolicitudEJB implements DocumentSolicitudLogicaService {

    @EJB(mappedName = DocumentService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.DocumentService documentEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FitxerService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.FitxerService fitxerEjb;

    @Override
    public Set<Long> deleteFull(Long documentId, Long solicitudId, boolean deleteFiles) throws I18NException {

        Set<Long> files = new HashSet<Long>();

        DocumentJPA document = documentEjb.findByPrimaryKey(documentId);

        if (document == null) {
            return files;
        }

        this.delete(Where.AND(DocumentSolicitudFields.SOLICITUDID.equal(solicitudId),
                DocumentSolicitudFields.DOCUMENTID.equal(documentId)));

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