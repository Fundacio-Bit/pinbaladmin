package org.fundaciobit.pinbaladmin.logic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.ModificacioSolicitudEJB;
import org.fundaciobit.pinbaladmin.ejb.ServeiService;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.ModificacioSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.ModificacioSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "ModificacioSolicitudLogicaEJB")
public class ModificacioSolicitudLogicaEJB extends ModificacioSolicitudEJB implements ModificacioSolicitudLogicaService {

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;
    @EJB(mappedName = EventLogicaService.JNDI_NAME)
    protected EventLogicaService eventLogicaEjb;
    @EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
    protected SolicitudServeiLogicaService solicitudServeiLogicaEjb;
    @EJB(mappedName = ServeiService.JNDI_NAME)
    protected ServeiService serveiEjb;
    
    @EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
    protected DocumentSolicitudLogicaService documentSolicitudLogicEjb;
    @EJB(mappedName = DocumentLogicaService.JNDI_NAME)
    protected DocumentLogicaService documentLogicaEjb;
    @EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
    protected FitxerPublicLogicaService fitxerPublicLogicaEjb;
    
    @Override
    @PermitAll
    public ModificacioSolicitud create(ModificacioSolicitud instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public ModificacioSolicitudJPA findByPrimaryKey(Long _ID_) {
        return (ModificacioSolicitudJPA)super.findByPrimaryKey(_ID_);
    }
    
	@Override
	@PermitAll
	public ModificacioSolicitud update(ModificacioSolicitud instance) throws I18NException {
		return super.update(instance);
	}

	@Override
	public void acceptarModificacio(ModificacioSolicitudJPA modSoli) throws I18NException {
		Long soliID = modSoli.getSolicitudID();
		log.info("Modificant dades de la solicitud " + modSoli.getSolicitudID() + " - ModSoliID: " + soliID);

		// 1. Recuperar solicitud original
		SolicitudJPA solicitud = (SolicitudJPA) solicitudLogicaEjb.findByPrimaryKey(soliID);

		// 2. Actualizar siempre los campos de solicitud con los de modSoli
		solicitud.setProcedimentNom(modSoli.getProcedimentNom());
//		solicitud.setResponsableProcNom(modSoli.getResponsableProcNom());
//		solicitud.setResponsableProcEmail(modSoli.getResponsableProceMail());
		solicitud.setConsentiment(modSoli.getConsentiment());
		solicitud.setProcedimentTipus(modSoli.getProcedimentTipus());
		solicitud.setDataFi(modSoli.getDataFi());
		
		//Afegir el nou codi SIA a la Convocatoria, si no es null.
		log.info("Codi SIA Anterior: " + solicitud.getCodiSiaConv());
		String codiSia =modSoli.getCodiSiaNou() ; 
		if (codiSia!= null && codiSia.trim().length() > 0) {
			solicitud.setCodiSiaConv(solicitud.getCodiSiaConv() + ", " + codiSia);
		}
		log.info("Codi SIA Posterior: " + solicitud.getCodiSiaConv());
		
		
		// 3. Actualizar Doc Consentimiento. Convertir un FitxerJPA en un DocumentSolicitudJPA

		if (modSoli.getDoCconsentimentID() != null) {
			FitxerJPA docConsentiment = fitxerPublicLogicaEjb.findByPrimaryKey(modSoli.getDoCconsentimentID());
			Long tipus = Constants.DOCUMENT_SOLICITUD_CONSENTIMENT;

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			String nom = "Document Consentiment " + LocalDateTime.now().format(formatter);

			afegirDocumentSolicitudAmbFitxer(docConsentiment, nom, tipus, soliID);
		}

		// 3. Cambiar estado de la solicitud. Despues de un cambio, hay que enviarlo a Madrid.
		solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_ENVIAR_MADRID);

		// 4. Guardar cambios de la solicitud
		solicitudLogicaEjb.update(solicitud);

		// 5. Actualizar serveis en estado de modificació
		Where wSoli = SolicitudServeiFields.SOLICITUDID.equal(soliID);
		Long[] estatsModificacio = {
		    Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_NORMA,
		    Constants.ESTAT_SOLICITUD_SERVEI_MODIFICACIO_SERVEI
		};
		Where wEstats = SolicitudServeiFields.ESTATSOLICITUDSERVEIID.in(estatsModificacio);

		List<SolicitudServei> serveisPerModificar = solicitudServeiLogicaEjb.select(Where.AND(wSoli, wEstats));

		for (SolicitudServei ss : serveisPerModificar) {
		    ss.setEstatSolicitudServeiID(Constants.ESTAT_SOLICITUD_SERVEI_PENDENT_AUTORITZAR);
		    solicitudServeiLogicaEjb.update(ss);
		}
		
		//Actualitzar estat de la modificació a Solicitada.
		modSoli.setEstatModificacio(Constants.ESTAT_MODIFICACIO_SOLICITUD_ACEPTADA);

		log.info("Modificació de la solicitud [" + soliID + "] acceptada correctament.");
	}
	
    private void afegirDocumentSolicitudAmbFitxer(FitxerJPA fitxer, String nom, Long tipus, Long soliID) throws I18NException  {
        
        Document doc = documentLogicaEjb.create(nom, fitxer.getFitxerID(), null, null, tipus);

        DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), soliID);

        documentSolicitudLogicEjb.create(ds);
        log.info("Afegit document: " + nom + " a la solicitud: " + soliID );
    }

}