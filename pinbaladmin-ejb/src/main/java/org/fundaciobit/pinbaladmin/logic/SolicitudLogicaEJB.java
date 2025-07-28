package org.fundaciobit.pinbaladmin.logic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Columns;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.FitxerService;
import org.fundaciobit.pinbaladmin.ejb.SolicitudEJB;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.dto.SolicitudDTO;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailAttachmentInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsAlta;
import org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsConsulta;
import org.fundaciobit.pinbaladmin.logic.utils.pinbalutils.PinbalUtilsModificacio;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.InfoMadrid;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.entity.TramitJConsent;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentJPA;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.InfoMadridJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.hibernate.Hibernate;
import org.jboss.ejb3.annotation.TransactionTimeout;

import es.caib.pinbal.client.recobriment.model.ScspFuncionario;
import es.caib.pinbal.client.recobriment.model.ScspTitular;
import es.caib.pinbal.client.recobriment.model.ScspTitular.ScspTipoDocumentacion;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Consulta;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.EstadoProcedimiento;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno;
import es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Servicio;

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

    @EJB(mappedName = EventLogicaService.JNDI_NAME)
    protected EventLogicaService eventLogicaEjb;

    @EJB(mappedName = DocumentLogicaService.JNDI_NAME)
    protected DocumentLogicaService documentLogicaEjb;

	@EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
	protected SolicitudServeiLogicaService solicitudServeiLogicaEjb;

	@EJB(mappedName = ServeiLogicaService.JNDI_NAME)
	protected ServeiLogicaService serveiLogicaEjb;

    @EJB(mappedName = TramitJConsentLogicaService.JNDI_NAME)
    protected TramitJConsentLogicaService tramitJEjb;

    @EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
    protected InfoMadridLogicaService infoMadridLogicaJEjb;

    
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
            List<Long> list = solicitudServeiLogicaEjb.executeQuery(SolicitudServeiFields.SERVEIID.select,
                    SolicitudServeiFields.SOLICITUDID.equal(solicitudId));

            for (Long ss : list) {
                files.addAll(solicitudServeiLogicaEjb.deleteFull(ss, solicitudId, false));
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
                solicitudServeiLogicaEjb.create(ss);
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
		
		soli.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_Firma_Director);
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
	public es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta altaSolicitudApiPinbal(
			ScspTitular titular, ScspFuncionario funcionario,
			es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud solicitud) throws Exception {

		PinbalUtilsAlta alta = new PinbalUtilsAlta();
		return alta.altaSolicitudApiPinbal(titular, funcionario, solicitud);
	}

	@Override
	public es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta modificacioSolicitudApiPinbal(
			ScspTitular titular, ScspFuncionario funcionario,
			es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud solicitud) throws Exception {

		PinbalUtilsModificacio mod = new PinbalUtilsModificacio();
		return mod.modificacioSolicitudApiPinbal(titular, funcionario, solicitud);
	}

	@Override
	public es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Solicitud getDadesAltaSolicitudApiPinbal(
			Long solicitudID) throws Exception {

		PinbalUtilsAlta alta = new PinbalUtilsAlta();
		SolicitudJPA soli = this.findByPrimaryKey(solicitudID);

		return alta.getDadesSolicitudApiPinbalAlta(soli);
	}

	@Override
	public es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Solicitud getDadesModificarSolicitudApiPinbal(
			Long solicitudID) throws Exception {

		SolicitudJPA soli = this.findByPrimaryKey(solicitudID);

		PinbalUtilsModificacio mod = new PinbalUtilsModificacio();
		return mod.getDadesSolicitudApiPinbalMod(soli);
	}

	@Override
	public Retorno consultaEstatApiPinbal(ScspTitular titular, ScspFuncionario funcionario, Long soliID)
			throws Exception {

		SolicitudJPA solicitud = this.findByPrimaryKey(soliID);
		PinbalUtilsConsulta cons = new PinbalUtilsConsulta();
		Retorno retorno = null;
		try {
			retorno = cons.consultaEstatApiPinbal(titular, funcionario, solicitud.getProcedimentCodi());
			InfoMadridJPA infoMadJpa = cons.actualitzarSolicitud(retorno, titular, solicitud);
			
			if (infoMadJpa != null) {

				InfoMadrid infoMad = infoMadridLogicaJEjb.create(infoMadJpa);

				Long id = infoMad.getInfoMadridID();
				log.info("Info Mad Creat: " + id);
				solicitud.setInfomadridid(id);
			}
			
			String estado = retorno.getEstado().getCodigoEstado();

			final String SOLICITUD_TROBADA = "0";

			if (estado.equals(SOLICITUD_TROBADA)) {
				actualitzarDadesServeisSolicitud(solicitud, retorno);
			}
			
		} catch (Throwable e) {
			solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_ERROR);
			log.error("Error fent consulta: " + e.getMessage(), e);
		}
		
		this.update(solicitud);

		
		return retorno;
	}

	/*
	 * 

	Estado de la autorización en la plataforma de intermediación.
	Puede tomar los valores:
	• 0 → Pendiente de tramitar
	• 1 → Desistido
	• 2 → Aprobado
	• 3 → No aprobado
	• 6 → Pendiente de autorización por parte del cedente
	• 7 → Autorizado
	• 8 → Desestimado

	 */
	
	public final int ESTADO_SOLI_SERV_PENDIENTE = 0;
	public final int ESTADO_SOLI_SERV_DESISTIDO = 1;
	public final int ESTADO_SOLI_SERV_APROBADO = 2;
	public final int ESTADO_SOLI_SERV_NO_APROBADO = 3;
	public final int ESTADO_SOLI_SERV_PENDIENTE_AUTORIZACION_CEDENTE = 6;
	public final int ESTADO_SOLI_SERV_AUTORIZADO = 7;
	public final int ESTADO_SOLI_SERV_DESESTIMADO = 8;

	private void actualitzarDadesServeisSolicitud(Solicitud solicitud,
			es.caib.scsp.esquemas.SVDPIDESTADOAUTWS01.consulta.datosespecificos.Retorno retorno) throws I18NException {

		// Agafar els serveis de la solicitud, i assignar a cada un l'estat que hi ha a
		// retorno.

		List<SolicitudServei> listSoliServ = solicitudServeiLogicaEjb
				.select(SolicitudServeiFields.SOLICITUDID.equal(solicitud.getSolicitudID()));

		List<Servicio> listServeis = retorno.getProcedimiento().getServicios().getServicio();

		for (Servicio servicio : listServeis) {
			for (SolicitudServei soliServ : listSoliServ) {

				Long serveiId = soliServ.getServeiID();
				Servei servei = serveiLogicaEjb.findByPrimaryKey(serveiId);

//				log.info("Test servei: " + servei.getCodi() + " -> " + servicio.getCodigoCertificado());

				if (servei.getCodi().equals(servicio.getCodigoCertificado())) {
//					log.info("Servei trobat: " + servei.getCodi());
					Long nouEstat = null;

					int nouEstatPinbal = servicio.getEstadoAutorizacion().getEstado();
					switch (nouEstatPinbal) {
					case ESTADO_SOLI_SERV_PENDIENTE:
					case ESTADO_SOLI_SERV_DESISTIDO:
					case ESTADO_SOLI_SERV_APROBADO:
					case ESTADO_SOLI_SERV_PENDIENTE_AUTORIZACION_CEDENTE:
						nouEstat = Constants.ESTAT_SOLICITUD_SERVEI_PENDENT_AUTORITZAR;
						break;

					case ESTADO_SOLI_SERV_NO_APROBADO:
					case ESTADO_SOLI_SERV_DESESTIMADO:
						nouEstat = Constants.ESTAT_SOLICITUD_SERVEI_DESESTIMAT;
						break;

					case ESTADO_SOLI_SERV_AUTORIZADO:
						nouEstat = Constants.ESTAT_SOLICITUD_SERVEI_AUTORITZAT;
						break;

					default:
						nouEstat = Constants.ESTAT_SOLICITUD_SERVEI_NO_DISPONIBLE;

					}

					soliServ.setEstatSolicitudServeiID(nouEstat);
					solicitudServeiLogicaEjb.update(soliServ);
				} else {
//					log.info("Servei no trobat: " + servei.getCodi());
				}
			}
		}
	}
	
    @Override
    public List<Solicitud> getSolicitudFromTramitID(String ticketGFE) {
        
        Long tramitID = HibernateFileUtil.decryptFileID(ticketGFE);
        
        final String likeStr = "%TramitID[" + tramitID + "]%";

        Where w = SolicitudFields.NOTES.like(likeStr);

        Long[] estats = {Constants.SOLI_ESTAT_TANCAT};
        Where wEstat = SolicitudFields.ESTATSOLICITUD.notIn(estats);
        log.info("Where: "  + w.toSQL() + " - " + likeStr);
        
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
    
	public void updateDocumentsConsentiment() {

		try {
			// Obtenir totes les solicituds locals amb consentiment adjunt.
			Where wLocal = SolicitudFields.ORGANID.isNotNull();
			Where wConsAdj = SolicitudFields.CONSENTIMENTADJUNT.equal(Constants.CONSENTIMENT_ADJUNT);
			Where wFetAmbTramit = SolicitudFields.NOTES.isNotNull();
			
			List<Solicitud> llistat = this.select(Where.AND(wLocal, wConsAdj, wFetAmbTramit));
			
			Where wTramitConsAdj = TramitJConsentFields.ADJUNTID.isNotNull();
			
			//Comprovar si realment tenen el consentiment als documents.
			for(Solicitud soli : llistat) {
				boolean teDocument = false;
				List<DocumentSolicitud> documentsSoli = documentSolicitudLogicaEjb.select(DocumentSolicitudFields.SOLICITUDID.equal(soli.getSolicitudID()));
				for (DocumentSolicitud docSol :documentsSoli ) {
					Document doc = documentLogicaEjb.findByPrimaryKey(docSol.getDocumentID());
					
					if (doc.getTipus() == Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI || doc.getTipus() == Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_NOOP ) {
						teDocument = true;
						break;
					}
				}
				
				if (teDocument) {
					log.info("Solicitud " + soli.getSolicitudID() + " ja te consentiment. No feim res");
				}else {
					//Hem d'obtenir el tramitJ i crear una copia del document de consentiment.
					List<TramitJConsent> tramitsJ = tramitJEjb.select(Where.AND(wTramitConsAdj));
					TramitJConsent consentiment = null;
					
					for (TramitJConsent tramitJ : tramitsJ) {
						if (soli.getNotes().indexOf("[" + tramitJ.getTramitid() + "]") > 0) {
							consentiment = tramitJ;
							break;
						}
					}
					
					if (consentiment != null) {
						Long fitxerConsentimentID = consentiment.getAdjuntID();
						String tipusCons = soli.getConsentiment();
						Long soliID = soli.getSolicitudID();
						log.info("Farem una copia del document de consentiment (" + fitxerConsentimentID + ") a la soliciud " + soliID + ". TramitID: " + consentiment.getTramitid());
						
						afegirDocumentConsentiment(fitxerConsentimentID,tipusCons, soliID);
						
						
					}else {
						log.warn("Solicitud " + soli.getSolicitudID() + "no te tramitJ. No feim res");
					}
				}
			}
			
		} catch (I18NException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private Fitxer afegirDocumentConsentiment(Long fitxerConsentimentID, String consentiment, Long soliID)
			throws I18NException {

		if (fitxerConsentimentID != null) {

			log.info("Tenim document de consentiment: " + fitxerConsentimentID + " - " + consentiment);

			FitxerJPA cons = fitxerEjb.findByPrimaryKey(fitxerConsentimentID);
			File consFile = FileSystemManager.getFile(cons.getFitxerID());

			// Copiar fitxer de consentiment
			FitxerJPA fitxerCopia = new FitxerJPA(cons.getNom(), cons.getTamany(), cons.getMime(),
					cons.getDescripcio());
			fitxerCopia = (FitxerJPA) fitxerEjb.create(fitxerCopia);

			File consFilePdf = FileSystemManager.getFile(fitxerCopia.getFitxerID());
			FileSystemManager.copy(consFile, consFilePdf);

			Long tipus = consentiment.equals(Constants.CONSENTIMENT_TIPUS_SI)
					? Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_SI
					: Constants.DOCUMENT_SOLICITUD_CONSENTIMENT_NOOP;
			String nom = "Document Consentiment";
			afegirDocumentSolicitudAmbFitxer(fitxerCopia, nom, tipus, soliID);
			return fitxerCopia;
		} else {
			log.info("No tenim document de consentiment");
			return null;
		}

	}

	private void afegirDocumentSolicitudAmbFitxer(FitxerJPA fitxer, String nom, Long tipus, Long soliID)
			throws I18NException {

		Document doc = documentLogicaEjb.create(nom, fitxer.getFitxerID(), null, null, tipus);

		DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), soliID);

		documentSolicitudLogicaEjb.create(ds);
		log.info("Afegit document: " + nom + " a la solicitud: " + soliID);
	}

	@Override
	public void processarRespostaPinbalAlta(Solicitud solicitud,
			es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Respuesta resposta, ScspTitular titular,
			ScspFuncionario funcionario) throws Exception {

		final String ESTAT_REGISTRADA_OK = "0";
		final String ESTAT_NO_REGISTRADA = "1";
		final String ESTAT_REGISTRADA_SUBSANAR = "2";
		final String ESTAT_VALIDACION_KO = "0228";
		final String ERROR_PROCEDIMIENTO_DUPLICADO = "01";

		String codiEstat = resposta.getEstado().getCodigoEstado();
		String descripcioEstat = resposta.getEstado().getDescripcion();

		log.info("Resposta PRE-ALTA: codi=" + codiEstat + ", descripció=" + descripcioEstat);

		switch (codiEstat) {
		case ESTAT_REGISTRADA_OK:
			solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
			solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_PENDENT_TRAMITAR);
			afegirEventSolicitudEnviada(solicitud, descripcioEstat);
			break;

		case ESTAT_REGISTRADA_SUBSANAR:
			solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);

			Retorno retorno = this.consultaEstatApiPinbal(titular, funcionario, solicitud.getSolicitudID());
//			EstadoProcedimiento estat = retorno.getProcedimiento().getEstadoProcedimiento();
//			solicitud.setEstatpinbal(estat.getEstado());

			afegirEventSolicitudEnviada(solicitud, descripcioEstat);
			break;

		case ESTAT_NO_REGISTRADA:
		case ESTAT_VALIDACION_KO:
			boolean duplicat = false;

			if (resposta.getErrores() != null) {
				for (es.caib.scsp.esquemas.SVDPIDSOLAUTWS01.alta.datosespecificos.Error error : resposta.getErrores()
						.getError()) {
					if (ERROR_PROCEDIMIENTO_DUPLICADO.equals(error.getCodigo())) {
						duplicat = true;
						break;
					}
				}
			}

			if (duplicat) {
				solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
				Retorno retornoDup = this.consultaEstatApiPinbal(titular, funcionario, solicitud.getSolicitudID());
//				EstadoProcedimiento estatDup = retornoDup.getProcedimiento().getEstadoProcedimiento();
//				solicitud.setEstatpinbal(estatDup.getEstado());

				afegirEventSolicitudEnviada(solicitud, "Procediment ja donat d'alta. Estat actualitzat.");
			} else {
				solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID);
			}
			break;

		default:
			log.warn("Codi d'estat no controlat: " + codiEstat);
			solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_ERROR_ENVIANT_MADRID);
			break;
		}
	}

	// Para modificació
	@Override
	public void processarRespostaPinbalModificacio(Solicitud solicitud,
			es.caib.scsp.esquemas.SVDPIDACTPROCWS01.modificacio.datosespecificos.Respuesta resposta,
			ScspTitular titular, ScspFuncionario funcionario) throws Exception {

		final String ESTAT_ACTUALITZADA_OK = "0";
		final String ESTAT_NO_REGISTRADA = "1";
		final String ESTAT_SUBSANAR = "2";
		final String ESTAT_VALIDACION_KO = "0228";

		String codiEstat = resposta.getEstado().getCodigoEstado();
		String descripcioEstat = resposta.getEstado().getDescripcion();

		log.info("Resposta MODIFICACIÓ: codi=" + codiEstat + ", descripció=" + descripcioEstat);

		switch (codiEstat) {
		case ESTAT_ACTUALITZADA_OK:
			solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);
			solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_PENDENT_TRAMITAR);
			afegirEventSolicitudEnviada(solicitud, descripcioEstat);
			break;

		case ESTAT_SUBSANAR:
			solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_PENDENT_AUTORITZAR);

			Retorno retorno = this.consultaEstatApiPinbal(titular, funcionario, solicitud.getSolicitudID());
//			EstadoProcedimiento estat = retorno.getProcedimiento().getEstadoProcedimiento();
//			solicitud.setEstatpinbal(estat.getEstado());

			afegirEventSolicitudEnviada(solicitud, descripcioEstat);
			break;

		case ESTAT_NO_REGISTRADA:
		case ESTAT_VALIDACION_KO:
		default:
			solicitud.setEstatSolicitud(Constants.SOLI_ESTAT_AUTORITZAT_ERROR_ENVIANT_MADRID);
			break;
		}
	}

	private void afegirEventSolicitudEnviada(Solicitud soli, String mensaje) {

		final Timestamp data = new Timestamp(System.currentTimeMillis());
		int tipus = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
		String persona = soli.getOperador();
		String subject = "Solicitud enviada a PINBAL. " + soli.getProcedimentCodi();
		String msg = "S'ha enviat la sol·licitud a PINBAL. " + mensaje;

		EventJPA event = new EventJPA();
		event.setSolicitudID(soli.getSolicitudID());
		event.setIncidenciaTecnicaID(null);
		event.setDataEvent(data);
		event.setTipus(tipus);
		event.setPersona(persona);
		event.setDestinatari(null);
		event.setDestinatarimail(null);
		event.setAsumpte(subject);
		event.setComentari(msg);
		event.setFitxerID(null);
		event.setNoLlegit(true);
		event.setCaidIdentificadorConsulta(null);
		event.setCaidNumeroSeguiment(null);

		try {
			eventLogicaEjb.create(event);
		} catch (I18NException e) {
			// TODO Auto-generated catch block
			log.error("No s'ha pogut crear l'event de solicitud enviada: " + e.getMessage(), e);
		}
	}

	@Override
	public void crearInfoMadridFromSolicitud(Solicitud solicitud) {
		
		

	
	}
    
    
}