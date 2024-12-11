package org.fundaciobit.pinbaladmin.logic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Schedules;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.xml.datatype.XMLGregorianCalendar;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsApi;
import org.fundaciobit.pinbaladmin.apiclientpeticions.PinbalAdminSolicitudsConfiguration;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.ejb.FitxerService;
import org.fundaciobit.pinbaladmin.ejb.SolicitudEJB;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.dto.SolicitudDTO;
import org.fundaciobit.pinbaladmin.logic.utils.FileInfo;
import org.fundaciobit.pinbaladmin.logic.utils.PdfDownloader;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailAttachmentInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsAlta;
import org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsCommon;
import org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsConsulta;
import org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsModificacio;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.fundaciobit.pluginsib.core.v3.utils.FileUtils;
import org.fundaciobit.pluginsib.utils.commons.GregorianCalendars;
import org.hibernate.Hibernate;
import org.jboss.ejb3.annotation.TransactionTimeout;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.EstadoProcedimiento;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;
import es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta;

/**
 * 
 * @author anadal
 * @author areus
 */
@Stateless(name = "SolicitudLogicaEJB")
//@SecurityDomain("seycon")
@PermitAll
public class SolicitudLogicaEJB extends SolicitudEJB implements SolicitudLogicaService {

    protected static final long TRANSACTION_TIMEOUT_IN_SEC = 180;
    protected static final long TRANSACTION_EXIT_IN_MILI = (TRANSACTION_TIMEOUT_IN_SEC * 2 / 3) * 1000;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.FitxerService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.FitxerService fitxerEjb;

    @EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
    protected DocumentSolicitudLogicaService documentSolicitudLogicaEjb;

    @EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
    protected SolicitudServeiLogicaService solicitudServeiLogicaEJB;

    @EJB(mappedName = EventLogicaService.JNDI_NAME)
    protected EventLogicaService eventLogicaEjb;

    @EJB(mappedName = DocumentLogicaService.JNDI_NAME)
    protected DocumentLogicaService documentLogicaEjb;

    @Override
    public Map<Long, List<SolicitudDTO>> getSolicitudsByServei(Collection<Long> serveiIds) {

        if (serveiIds.isEmpty()) {
            return Collections.emptyMap();
        }

//		Query query = __em.createQuery("select " + "s.serveiID, " + "solser.solicitudID, "
//				+ "solser.solicitud.procedimentCodi,  " + "solser.solicitud.procedimentNom "
//				+ "from ServeiJPA s join s.solicitudServeis solser "
//				+ "where s.serveiID in (:serveiIds) " + "order by s.serveiID, solser.solicitud.dataInici DESC");
		
		
		String queryStr = ""
				+ "SELECT "
				+ "		s.serveiID, solser.solicitudID, solser.solicitud.procedimentCodi, solser.solicitud.procedimentNom, solser.solicitud.organid "
				+ "FROM " 
				+ "		ServeiJPA s join s.solicitudServeis solser " 
				+ "WHERE "
				+ "		s.serveiID in (:serveiIds) " 
				+ "ORDER BY "
				+ "		s.serveiID, solser.solicitud.dataInici DESC";

		Query query = __em.createQuery(queryStr);
		
        query.setParameter("serveiIds", serveiIds);
        List<Object[]> resultList = (List<Object[]>) query.getResultList();

        Map<Long, List<SolicitudDTO>> resultMap = new HashMap<Long, List<SolicitudDTO>>();
        for (Long serveiId : serveiIds) {
            resultMap.put(serveiId, new ArrayList<SolicitudDTO>());
        }

        for (Object[] result : resultList) {
            Long serveiId = (Long) result[0];
            Long solicitudId = (Long) result[1];
            String procedimentCodi = (String) result[2];
            String procedimentNom = (String) result[3];
            Long organGestor = (Long) result[4];
            
            SolicitudDTO solicitudDTO = new SolicitudDTO(solicitudId, procedimentCodi, procedimentNom, organGestor);
            resultMap.get(serveiId).add(solicitudDTO);
        }

        return resultMap;
    }

    @Override
    @PermitAll
    public Solicitud create(Solicitud instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public Solicitud update(Solicitud instance) throws I18NException {
        return super.update(instance);
    }
    
    
    @Override
    public Set<Long> deleteFull(Long solicitudId, boolean deleteFiles) throws I18NException {

        Set<Long> files = new HashSet<Long>();

        SolicitudJPA solicitud = this.findByPrimaryKey(solicitudId);

        if (solicitud == null) {
            return files;
        }

        // Borram Solicituds Serveis
        {
            List<Long> list = solicitudServeiLogicaEJB.executeQuery(SolicitudServeiFields.SERVEIID.select,
                    SolicitudServeiFields.SOLICITUDID.equal(solicitudId));

            for (Long ss : list) {
                files.addAll(solicitudServeiLogicaEJB.deleteFull(ss, solicitudId, false));
            }
        }

        // Borram Documents de Solicitud
        {
            List<Long> documentsIds = documentSolicitudLogicaEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID.select,
                    DocumentSolicitudFields.SOLICITUDID.equal(solicitudId));

            for (Long ds : documentsIds) {
                files.addAll(documentSolicitudLogicaEjb.deleteFull(ds, solicitudId, false));
            }
        }

        // Borram solicitud
        if (solicitud.getDocumentSolicitudID() != null) {
            files.add(solicitud.getDocumentSolicitudID());
        }

        // Esborram events
        files.addAll(eventLogicaEjb.deleteFullBySolicitantID(solicitudId));

        this.delete(solicitud);

        /*
          Si tot ha anat be llavors borram els fitxers
         if (deleteFiles) {
           LogicUtils.deleteFiles(files, fitxerEjb);
         }
         */

        return files;
    }

    @Override
    @PermitAll
    public SolicitudJPA findByPrimaryKey(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

    @Override
    public SolicitudJPA findByPrimaryKeyFull(Long _ID_) throws I18NException {

        SolicitudJPA s = this.findByPrimaryKey(_ID_);

        Hibernate.initialize(s.getSolicitudServeis());

        Set<SolicitudServeiJPA> ssList = s.getSolicitudServeis();

        for (SolicitudServeiJPA solicitudServeiJPA : ssList) {
            Hibernate.initialize(solicitudServeiJPA.getServei());
            Hibernate.initialize(solicitudServeiJPA.getServei().getEntitatServei());
        }

        return s;
    }

//    @Override
//    @PermitAll
//    public void updateCAID(Long soliID, String incidencia, String seguiment) throws I18NException {
//
//        SolicitudJPA soli = this.findByPrimaryKey(soliID);
//
//        soli.setTicketAssociat(incidencia);
//        soli.setTicketNumeroSeguiment(seguiment);
//
//        this.update(soli);
//    }

    protected Fitxer createFile(FitxerService fitxerEJB, String fileName, String mime, String descripcio, byte[] data)
            throws I18NException {

        Fitxer f = fitxerEJB.create(fileName, data.length, mime, descripcio);

        FileSystemManager.crearFitxer(new ByteArrayInputStream(data), f.getFitxerID());

        return f;
    }
    
    @Override
    public void crearSolicituds(List<SolicitudJPA> solicituds, EmailAttachmentInfo xlsx,
            List<EmailAttachmentInfo> attachs, String msg) throws I18NException {

    	log.info("Creant Solicituds");
        for (SolicitudJPA soli : solicituds) {

            // Desvincular Serveis
            Set<SolicitudServeiJPA> ssSet = soli.getSolicitudServeis();

            soli.setSolicitudServeis(null);

            // Crear Fitxer XLSX i afegir-ho a solicitud
            Fitxer xlsxFile = createFile(fitxerEjb, xlsx.getFileName(), xlsx.getContentType(), null, xlsx.getData());
            soli.setSolicitudXmlID(xlsxFile.getFitxerID());
            
            // Crear Solicitud
            this.create(soli);
            
            Long soliID = soli.getSolicitudID();

            // Afegir Serveis a la Solicitud
            for (SolicitudServeiJPA ss : ssSet) {
            	//articles, consentiment, enllazConsentiment, enllazNormaLegal, tipusconsentiment, caduca, fechacaduca
            	ss.setArticles(reduceString255(ss.getArticles()));
            	ss.setConsentiment(reduceString255(ss.getConsentiment()));
            	ss.setEnllazNormaLegal(reduceString255(ss.getEnllazNormaLegal()));
            	ss.setEnllazConsentiment(reduceString255(ss.getEnllazConsentiment()));
            	ss.setTipusConsentiment(reduceString255(ss.getTipusConsentiment()));
            	ss.setCaduca(reduceString255(ss.getCaduca()));
            	ss.setFechaCaduca(ss.getFechaCaduca());
            	
                ss.setSolicitudID(soliID);
                solicitudServeiLogicaEJB.create(ss);
            }

            //Afegim event de Solicitud Creada
            java.lang.Long _incidenciaTecnicaID_ = null;
            java.lang.Long _solicitudID_ = soliID;

            boolean isEstatal = soli.getEntitatEstatal() != null;
            String asumpte = "Solicitud " + (isEstatal ? "estatal" : "local") + " creada correctament";
            
            String missatge = "<div>" + msg + "</div>";

            java.sql.Timestamp _dataEvent_ = soli.getDataInici();
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_CONTACTE;
            java.lang.String _persona_ = soli.getPersonaContacte();
            boolean _noLlegit_ = false;

            java.lang.String _caidIdentificadorConsulta_ = null;
            java.lang.String _caidNumeroSeguiment_ = null;

            java.lang.String _destinatari_ = null;
            java.lang.String _destinatariEmail_ = null;

            java.lang.Long _fitxerID_ = null;
            
            eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_, _destinatari_,
                    _destinatariEmail_, asumpte, missatge, _fitxerID_, _noLlegit_, _caidIdentificadorConsulta_,
                    _caidNumeroSeguiment_);
            
            // Afegir Documents per cada Solicitud
            for (EmailAttachmentInfo attach : attachs) {

            	if (attach != xlsx ) {
            		//Si el  nombre es mayor a 255 chars, reducir a 255.
            		String newFileName = attach.getFileName();
            		if (newFileName.length() > 255) {
            			newFileName = newFileName.substring(0, 255);
            		}
            		
            		
                    Fitxer attachFile = createFile(fitxerEjb, newFileName, attach.getContentType(), null,
                            attach.getData());
                    Long tipus = Constants.DOCUMENT_SOLICITUD_ALTRES;
                    DocumentJPA doc = new DocumentJPA(newFileName, attachFile.getFitxerID(), null, null,
                            tipus);

                    documentLogicaEjb.create(doc);

                    DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), soliID);
                    documentSolicitudLogicaEjb.create(ds);
                    
                    java.lang.String _comentari_ = "Afegit fitxer";
                    java.lang.String _asumpte_ = "Afegit fitxer";
                    
                    eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_,
                            _destinatari_, _destinatariEmail_, _asumpte_, _comentari_, attachFile.getFitxerID(), _noLlegit_,
                            _caidIdentificadorConsulta_, _caidNumeroSeguiment_);
                }
            }
        }
        log.info("Solicituds creades");
    }
    

    @Override
    public Solicitud afegirMailASolicitud(EmailMessageInfo emi, Long soliID) throws I18NException {

    	java.lang.String asumpte = emi.getSubject();
        java.lang.String missatge = emi.getBody(); // TODO limit tamany

        java.sql.Timestamp data= new Timestamp(System.currentTimeMillis());
        java.lang.String contacteNom = emi.getNameFrom();
        java.lang.String caidIdentificadorConsulta = null;
        java.lang.String caidNumeroSeguiment = null;
        java.lang.String destinatari = null;
        java.lang.String destinatariEmail = null;

        Solicitud soli = this.findByPrimaryKey(soliID);
        
		if (soli == null) {
            throw new I18NException("genapp.comodi", "Solicitud " + soliID + " no trobada" );
		}
        
        java.lang.Long incidenciaTecnicaID = null;
        java.lang.Long solicitudID = soli.getSolicitudID();
        
        // Afegir event de peticio
        {
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_CONTACTE;
            boolean _noLlegit_ = true;
            Long _fitxerID_ = null;
            missatge = "<div>" + missatge + "</div>";

            //Reduir string asumpte a 255 chars si es mes llarg.
			if (asumpte.length() > 255) {
				asumpte = asumpte.substring(0, 255);
			}
            
            
            eventLogicaEjb.create(solicitudID, incidenciaTecnicaID, data, _tipus_, contacteNom, destinatari,
                    destinatariEmail, asumpte, missatge, _fitxerID_, _noLlegit_, caidIdentificadorConsulta, caidNumeroSeguiment);
        }

        // Si el correu te fitxers, afegir-los
        {
            java.lang.String _missatge_ = "Afegit fitxer";
            java.lang.String _asumpte_ = "Afegit fitxer";
            
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_CONTACTE;
            boolean _noLlegit_ = true;
            
            log.info("attachements " + emi.getAttachments() + " " + emi.getAttachments().size());
            for (EmailAttachmentInfo ads : emi.getAttachments()) {
            	
            	if (ads.getFileName() == null || ads.getFileName().equals("null")) {
            		continue;
				}
            	
                FitxerJPA fitxer = new FitxerJPA(ads.getFileName(), ads.getData().length, ads.getContentType(), null);
                fitxerEjb.create(fitxer);
                FileSystemManager.crearFitxer(new ByteArrayInputStream(ads.getData()), fitxer.getFitxerID());

                java.lang.Long _fitxerID_ = fitxer.getFitxerID();

                eventLogicaEjb.create(solicitudID, incidenciaTecnicaID, data, _tipus_, contacteNom, destinatari,
                        destinatariEmail, _asumpte_, _missatge_, _fitxerID_, _noLlegit_, caidIdentificadorConsulta,
                        caidNumeroSeguiment);
            }
        }
        return soli;
    }

    //------------ Enviar Solicitud a Firmar -------------------
	@Override
	public void enviarFormulariDGPortaFIB(Long soliID, String destinatariNif, String remitent) throws I18NException {
		Solicitud soli = this.findByPrimaryKey(soliID);
		
//		String titolPeticio = "Solicitud Autorització PINBAL Procediment " + soli.getProcedimentCodi();
//		String description = soli.getProcedimentCodi() + " - " + soli.getProcedimentNom();
//		String reason = "Solitud d'autorització als Serveis de la Plataforma d'Intermediació: SVD";
		
		Long documentID = getDocIDFormulariDGPDF(soliID);

		documentLogicaEjb.enviarDocumentDGPortaFIB(documentID, destinatariNif, remitent);
		
		soli.setEstatID(Constants.SOLICITUD_ESTAT_PENDENT_Firma_Director);
		this.update(soli);
		
//		try {
//			Long idPortafib = documentLogicaEjb.crearIEnviarPeticioDeFirma(documentID, destinatariNif, titolPeticio, description, reason, remitent);
//
//			log.info("Peticio de firma creada: " + idPortafib);
//			soli.setPortafibID(idPortafib);
//
//			String msg = "Peticio de firma enviada a Portafib.\n"
//					+ "Remitent: " + remitent + "\n"
//					+ "Destinatari: " + destinatariNif + "\n"
//					+ "Procediment: " + soli.getProcedimentCodi() + " - " + soli.getProcedimentNom();
//			
//			afegirEventSolicitudEnviada(soliID, remitent, msg);
//
//		} catch (Throwable e) {
//			log.error("Error creant peticio de firma: " + e.getMessage(), e);
//			throw new I18NException("error.portafib.creacio", e.getMessage());
//		}
	}

	protected Long getDocIDFormulariDGPDF(Long soliID) throws I18NException {

		List<Long> listDocumentsSolicitud = documentSolicitudLogicaEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
				DocumentSolicitudFields.SOLICITUDID.equal(soliID));

		Long documentsDirectorPDF = documentLogicaEjb.executeQueryOne(DocumentFields.DOCUMENTID,
				Where.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud),
						DocumentFields.TIPUS.equal(Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF)));

		return documentsDirectorPDF;
	}

    //------------ API PINBAL ALTA, CONSULTA I MODICACIO DE SERVEIS AL PID  -------------------
    public enum TipusCridada{
        ALTA, CONSULTA, MODIFICACIO,
    }
    
	private String reduceString255(String string) {
		if (string == null) {
			return null;
		}
		
		if (string.length() > 255) {
			return string.substring(0, 255);
		}
		return string;
	}
	
	
	@Override
	public es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta altaSolicitudApiPinbal(ScspTitular titular, ScspFuncionario funcionario,
			es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud solicitud) throws Exception {

		PinbalUtilsAlta alta = new PinbalUtilsAlta();
		return alta.altaSolicitudApiPinbal(titular, funcionario, solicitud);
	}
   
	@Override
	public Retorno consultaEstatApiPinbal(ScspTitular titular, ScspFuncionario funcionario, Consulta consulta)
			throws Exception {

		PinbalUtilsConsulta cons = new PinbalUtilsConsulta();
		return cons.consultaEstatApiPinbal(titular, funcionario, consulta);
	}

	@Override
	public es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta modificacioSolicitudApiPinbal(
			ScspTitular titular, ScspFuncionario funcionario,
			es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud solicitud) throws Exception {

		PinbalUtilsModificacio mod = new PinbalUtilsModificacio();
		return mod.modificacioSolicitudApiPinbal(titular, funcionario, solicitud);
	}

    @Override
    public es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud getDadesAltaSolicitudApiPinbal(Long solicitudID, Properties prop) throws Exception {

    	PinbalUtilsAlta alta = new PinbalUtilsAlta();
        SolicitudJPA soli = this.findByPrimaryKey(solicitudID);

		return alta.getDadesSolicitudApiPinbal(soli, prop);
    }

    @Override
    public es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud getDadesModificarSolicitudApiPinbal(Long solicitudID, Properties prop) throws Exception {

        SolicitudJPA soli = this.findByPrimaryKey(solicitudID);

        PinbalUtilsModificacio mod = new PinbalUtilsModificacio();
        return mod.getDadesSolicitudApiPinbal(soli, prop);
    }

    

    @Override
    public List<Solicitud> getSolicitudFromTramitID(String ticketGFE) {
        
        Long tramitID = HibernateFileUtil.decryptFileID(ticketGFE);
        
        final String likeStr = "%tramitid[" + tramitID + "]%";

        Where w = SolicitudFields.NOTES.like(likeStr);
        
        log.info("Where: "  + w.toSQL());
        
        OrderBy order = new OrderBy(SolicitudFields.DATAINICI);
        
        
        List<Solicitud> llistat;
		try {
			llistat = this.select(w, order);
			return llistat;
		} catch (I18NException e) {
			return null;
		}
        
//        int size = llistat.size();
//        
//        if (size == 1) {
//            return llistat.get(0);
//        }else if (size == 0) {
//            throw new Exception("No s'ha trobat la solicitud [" + tramitID + "]a la BBDD");
//        } else {
//            throw new Exception("Hi ha mes d'una solicitud amb tramitID " + tramitID + " a la BBDD");
//        }
    }

    /**
     * Funció que s'executa cada vespre a les 5:00 i actualitza l'estat de les solicituds a pinbal
     */
    @TransactionTimeout(value = TRANSACTION_TIMEOUT_IN_SEC)
	@Schedules({ 
		@Schedule(hour = "07", minute = "00", persistent = false),
		@Schedule(hour = "10", minute = "00", persistent = false),
		@Schedule(hour = "13", minute = "00", persistent = false),
		@Schedule(hour = "15", minute = "00", persistent = false),
		@Schedule(hour = "17", minute = "00", persistent = false)
	})
	protected void obtenirEstatsSolicitudsPinbal() {
		log.info("Comença obtenirEstatsSolicitudsPinbal()");

		long startTime = System.currentTimeMillis();
		try {
			ScspFuncionario funcionario = new ScspFuncionario();
			funcionario.setNifFuncionario("45186147W");
			funcionario.setNombreCompletoFuncionario("Juan Pablo Trias Segura");

			ScspTitular titular = new ScspTitular();
			titular.setTipoDocumentacion(ScspTipoDocumentacion.NIF);
			titular.setDocumentacion("45186147W");
			titular.setNombre("Juan Pablo");
			titular.setApellido1("Trias");
			titular.setApellido2("Segura");
			titular.setNombreCompleto("Juan Pablo Trias Segura");

			Where wSolicitudLocals = SolicitudFields.ORGANID.isNotNull();
			Where wEstatsPinbal = SolicitudFields.ESTATPINBAL.greaterThan(Constants.ESTAT_PINBAL_NO_SOLICITAT);
			Where wPendentMadrid = SolicitudFields.ESTATID.equal(Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR);
			List<Solicitud> solicituds = this.select(Where.AND(wSolicitudLocals, Where.OR(wPendentMadrid, wEstatsPinbal)));
			log.info("Solicituds a procesasr: " + solicituds.size());

			final String SOLICITUD_TROBADA = "0";
//			final String TICKET_NO_TROBAT = "1";
			final String PROCEDIMENT_NO_TROBAT = "2";
//			final String SOLICITANT_SENSE_SOLICITUTS = "3";
				
			List<String> solicitudConsultades = new ArrayList<String>();
			
			for (Solicitud solicitud : solicituds) {
				
				String codi = solicitud.getProcedimentCodi();
				if (solicitudConsultades.contains(codi)) {
					log.info("Solicitud " + codi + " ja consultada.");
					continue;
				} else {
					solicitudConsultades.add(codi);
				}
				
				Consulta consulta = new Consulta();
				consulta.setCodigoProcedimiento(codi);

				try {
					String msg = null;
					
					Retorno retorno = this.consultaEstatApiPinbal(titular, funcionario, consulta);
					String codigoEstado = retorno.getEstado().getCodigoEstado();
					switch (codigoEstado) {
					case SOLICITUD_TROBADA:
						EstadoProcedimiento estado = retorno.getProcedimiento().getEstadoProcedimiento();
						int estadoAnterior = solicitud.getEstatpinbal();
						int estadoActual = estado.getEstado();

						msg = "Solicitud " + codi + ": (" + estadoAnterior + " -> " + estadoActual + ") "
								+ estado.getDescripcion();
						log.info(msg);

						if (estadoAnterior != estadoActual) {
							solicitud.setEstatpinbal(estado.getEstado());

							// afegir event a la solicitud indicant el canvi d'estat
							Long _incidenciaTecnicaID_ = null;
							Long _solicitudID_ = solicitud.getSolicitudID();

							String estadoAnteriorStr = getEstatString(estadoAnterior);
							String estadoActualStr = getEstatString(estadoActual);
							
							String descripcio = "<div style=\"margin: 0.5rem;font-size: 15px;\">Actualització de l'estat de la solicitud a Pinbal. <br><br>Estat anterior: <b>"
									+ estadoAnteriorStr + "</b>. Estat actual: <b>" + estadoActualStr + "</b></div>";

							String asumpte = "Actualització de l'estat de la solicitud a Pinbal";
							
							Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());
							int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
							String _persona_ = "PinbalAdmin";
							boolean _noLlegit_ = true;

							String _caidIdentificadorConsulta_ = null;
							String _caidNumeroSeguiment_ = null;
							String _destinatari_ = null;
							String _destinatariEmail_ = null;

							log.info("Afegint event a la solicitud. Descripció: " + descripcio);
							try {
								eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_,
										_destinatari_, _destinatariEmail_, asumpte, descripcio, null, _noLlegit_,
										_caidIdentificadorConsulta_, _caidNumeroSeguiment_);
							} catch (I18NException e) {
								log.error("Error creant event de canvi de solicitud " + codi + ": " + e.getMessage());
							}
						}
						
						
						switch (estadoActual) {
						case Constants.ESTAT_PINBAL_AUTORITZAT:
							solicitud.setEstatID(Constants.SOLICITUD_ESTAT_AUTORITZAT);
							break;
						case Constants.ESTAT_PINBAL_PENDENT_SUBSANACIO:
							solicitud.setEstatID(Constants.SOLICITUD_ESTAT_ESMENES);
							break;
						}
						
						break;
					case PROCEDIMENT_NO_TROBAT:
						msg = "No s'ha trobat la solicitud " + codi + " a pinbal. Revisar s'ha enviat manualment a Madrid." ;
						log.warn(msg);
						//Aquest es el cas de solicituds canviades d'estat manualment. Enviades a Madrid "pendents de tramitar", pero sense utilitzar api PINBAL.
//						solicitud.setEstatID(Constants.SOLICITUD_ESTAT_PENDENT_ENVIAR_MADRID);
//						solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_NO_SOLICITAT);
						break;
						
					default:
						msg = "Error al consultar l'estat de la solicitud " + codi + ": " + retorno.getEstado().getLiteralError();
						log.error(msg);
						solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_ERROR);
						solicitud.setEstatID(Constants.SOLICITUD_ESTAT_PENDENT_ENVIAR_MADRID);
						break;
						
					}
				} catch (Exception e) {
					log.error("Error al consultar l'estat de la solicitud " + codi + ": " + e.getMessage(), e);
					solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_ERROR);
				}
				
				this.update(solicitud);
				
				// Si el CRON s'executa durant 2 min, surt del for i acaba la funció.
				if ((System.currentTimeMillis() - startTime) > TRANSACTION_EXIT_IN_MILI) {
					log.warn("Timeout.");
					break;
				}
			}

		} catch (I18NException e) {
			final String msg = "Error al cron obtenirEstatsSolicitudsPinbal():: " + e.getMessage();
			log.error(msg, e);
		}

		long endTime = System.currentTimeMillis();
		log.info("Total time: " + (endTime - startTime));
		log.info("Acaba obtenirEstatsSolicitudsPinbal()");
	}

	private String getEstatString(int estado) {
		String estadoActualStr;
		if (estado == Constants.ESTAT_PINBAL_NO_SOLICITAT)
			estadoActualStr = "NO_SOLICITAT";
		else if (estado == Constants.ESTAT_PINBAL_PENDENT_TRAMITAR)
			estadoActualStr = "PENDENT_TRAMITAR";
		else if (estado == Constants.ESTAT_PINBAL_DESISTIT)
			estadoActualStr = "DESISTIT";
		else if (estado == Constants.ESTAT_PINBAL_APROVAT)
			estadoActualStr = "APROVAT";
		else if (estado == Constants.ESTAT_PINBAL_NO_APROVAT)
			estadoActualStr = "NO_APROVAT";
		else if (estado == Constants.ESTAT_PINBAL_PENDENT_SUBSANACIO)
			estadoActualStr = "PENDENT_SUBSANACIO";
		else if (estado == Constants.ESTAT_PINBAL_SUBSANAT)
			estadoActualStr = "SUBSANAT";
		else if (estado == Constants.ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT)
			estadoActualStr = "PENDENT_AUTORITZACIO_CEDENT";
		else if (estado == Constants.ESTAT_PINBAL_AUTORITZAT)
			estadoActualStr = "AUTORITZAT";
		else if (estado == Constants.ESTAT_PINBAL_DESESTIMAT)
			estadoActualStr = "DESESTIMAT";
		else if (estado == Constants.ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO)
			estadoActualStr = "AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO";
		else
			estadoActualStr = "ERROR";
		return estadoActualStr;
	}
}