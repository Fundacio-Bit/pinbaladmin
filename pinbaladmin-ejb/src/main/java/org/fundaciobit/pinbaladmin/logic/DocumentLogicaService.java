package org.fundaciobit.pinbaladmin.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.ejb.DocumentService;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface DocumentLogicaService extends DocumentService {

    public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/DocumentLogicaEJB!org.fundaciobit.pinbaladmin.logic.DocumentLogicaService";
    
	public void enviarDocumentDGPortaFIB(Long docID, Contacte destinatari, String remitent) throws I18NException;
	
//	public Long crearIEnviarPeticioDeFirma(Long documentID, String destinatariNif, String titolPeticio, String description, String reason, String remitent) throws Exception;

	public Long cosesAFerDocumentFirmat(Long portafibID) throws I18NException;
}
