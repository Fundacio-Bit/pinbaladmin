package org.fundaciobit.pinbaladmin.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.DocumentService;
import org.fundaciobit.pinbaladmin.logic.dto.ContactePortaFIB;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface DocumentLogicaService extends DocumentService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/DocumentLogicaEJB!org.fundaciobit.pinbaladmin.logic.DocumentLogicaService";

    public void enviarDocumentPortaFIB(Long docID, List<ContactePortaFIB> destinatari, String remitent)
            throws I18NException;

    //	public Long crearIEnviarPeticioDeFirma(Long documentID, String destinatariNif, String titolPeticio, String description, String reason, String remitent) throws Exception;

    public Long cosesAFerDocumentFirmat(Long portafibID) throws I18NException;
}
