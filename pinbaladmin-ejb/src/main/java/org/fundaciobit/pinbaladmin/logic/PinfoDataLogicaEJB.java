package org.fundaciobit.pinbaladmin.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.PinfoDataEJB;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager.TipusPluginUserInfo;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;
import org.fundaciobit.pinbaladmin.persistence.PinfoJPA;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.UniformInterfaceException;

import es.caib.pinbal.client.comu.LogLevel;
import es.caib.pinbal.client.comu.Page;
import es.caib.pinbal.client.procediments.Procediment;
import es.caib.pinbal.client.procediments.ProcedimentClient;
import es.caib.pinbal.client.serveis.Servei;
import es.caib.pinbal.client.serveis.ServeiClient;
import es.caib.pinbal.client.usuaris.FiltreUsuaris;
import es.caib.pinbal.client.usuaris.PermisosServei;
import es.caib.pinbal.client.usuaris.ProcedimentServei;
import es.caib.pinbal.client.usuaris.UsuariClient;
import es.caib.pinbal.client.usuaris.UsuariEntitat;

//import org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PinfoDataLogicaEJB")
public class PinfoDataLogicaEJB extends PinfoDataEJB implements PinfoDataLogicaService {

	@EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
	protected SolicitudLogicaService solicitudLogicaEjb;

	@EJB(mappedName = ServeiLogicaService.JNDI_NAME)
	protected ServeiLogicaService serveiLogicaEjb;

	@EJB(mappedName = PinfoLogicaService.JNDI_NAME)
	protected PinfoLogicaService pinfoLogicaEjb;

	@EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
	protected IncidenciaTecnicaLogicaService incidenciaLogicaEjb;

	@Override
	@PermitAll
	public PinfoData create(PinfoData instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public PinfoDataJPA findByPrimaryKey(Long _ID_) {
		return (PinfoDataJPA) super.findByPrimaryKey(_ID_);
	}

	@Override
	@PermitAll
	public PinfoData update(PinfoData instance) throws I18NException {
		return super.update(instance);
	}

	@Override
	@PermitAll
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@PermitAll
	public void delete(PinfoData instance) {
		super.delete(instance);
	}

	@Override
	public PinfoDataFull getEstructuraUsuarisProcedimentServeis(Long pinfoID) throws I18NException {
//		final String ENTITAT_CIF = pinfoLogicaEjb.executeQueryOne(PinfoFields.ENTITAT, PinfoFields.PINFOID.equal(pinfoID));
//		log.info("ENTITAT_CIF: " + ENTITAT_CIF);
		
		 //= "GOVERN"; // "S0711001H";
		// final String CODI_USUARI = "e45186147w"; //"S0711001H";

		final boolean debug = false;
    	// boolean caib = true;
		IUserInformationPlugin pluginUserInfo =  PinbalAdminPluginsManager.getUserInformationPluginInstance(debug, TipusPluginUserInfo.LDAP);
/*
		final String baseUrl = Configuracio.getApiPinbalClientUrl();
		final String username = Configuracio.getApiPinbalClientUsername();
		final String password = Configuracio.getApiPinbalClientPassword();
		final LogLevel logLevel = LogLevel.INFO;
*/
		//UsuariClient usuariClient = new UsuariClient(baseUrl, username, password, logLevel);

		//ClientRecobriment clientRecobriment = new ClientRecobriment(baseUrl, username, password, logLevel);
//		clientRecobriment.getEntitats().get(0).get
		
		log.info("getEstructuraUsuarisProcedimentServeis per PINFO" + pinfoID);
		
		OrderBy orderByServ = new OrderBy(PinfoDataFields.SERVEIID, OrderType.ASC);
		OrderBy orderByProc = new OrderBy(PinfoDataFields.PROCEDIMENTID, OrderType.ASC);
		OrderBy orderByAlta = new OrderBy(PinfoDataFields.ALTA, OrderType.DESC);
		OrderBy orderByUser = new OrderBy(PinfoDataFields.USUARIID, OrderType.ASC);

		OrderBy[] orderBy = { orderByUser, orderByProc, orderByAlta, orderByServ };
		List<PinfoData> llista = this.select(PinfoDataFields.PINFOID.equal(pinfoID), orderBy);

		String lastUsuariID = null;
		Long lastProcedimentID = null;

		List<UsuariData> usuarisList = new ArrayList<UsuariData>();

		List<ProcedimentData> procedimentsList;
		List<ServeiData> altaList;
		List<ServeiData> baixaList;

		UsuariData lastUsuariData = null;
		ProcedimentData lastProcedimentData = null;

		for (PinfoData pinfoData : llista) {
			String usuariID = pinfoData.getUsuariid();
			Long procedimentID = pinfoData.getProcedimentID();
			Long serveiID = pinfoData.getServeiID();

//			log.info("UsuariID: " + usuariID + " lastUsuariID: " + lastUsuariID);
//			log.info("ProcedimentID: " + procedimentID + " lastProcedimentID: " + lastProcedimentID);
			boolean nouUsuari = !usuariID.equals(lastUsuariID);
			boolean nouProcediment = !procedimentID.equals(lastProcedimentID);
			
			// Si es distinto, uno nuevo, sino, cojemos el anterior.
			if (nouUsuari) {
//				log.info("Creant nou usuari amb nova llista de procediments");
				try {
//					UsuariEntitat usuariEntitat = usuariClient.getUsuari(usuariID, ENTITAT_CIF);
					UserInfo usuari = pluginUserInfo.getUserInfoByUserName(usuariID);
					UsuariData usuariData = new UsuariData(usuari.getUsername(), usuari.getAdministrationID(), usuari.getFullName(), new ArrayList<ProcedimentData>());
					procedimentsList = usuariData.getProcediments();
					lastUsuariData = usuariData;
					lastUsuariID = usuariID;
					usuarisList.add(usuariData);
				} catch (Exception e) {
					String msg = "Error obtenint usuari " + usuariID + ": " + e.getMessage();
					log.error(msg, e);
					throw new I18NException("genapp.comodi", msg);
				}
			} else {
//				log.info("Usuari " + usuariID + " ja existent, afegirem al seu procediment.");
				procedimentsList = lastUsuariData.getProcediments();
			}

			// Puede ser que el codigo de procedimiento sea igual, pero haya cambiado el
			// usuario. En ese caso, se crea un nuevo procedimiento.
			if (nouProcediment || nouUsuari) {
//				log.info("Creant nou procediment");
				SolicitudJPA solicitud = solicitudLogicaEjb.findByPrimaryKey(procedimentID);
				ProcedimentData procedimentData = new ProcedimentData(procedimentID,
						solicitud.getProcedimentNom(),solicitud.getProcedimentCodi(), 
						new ArrayList<ServeiData>(), new ArrayList<ServeiData>());
				altaList = procedimentData.getAltes();
				baixaList = procedimentData.getBaixes();
				lastProcedimentData = procedimentData;
				lastProcedimentID = procedimentID;
				procedimentsList.add(procedimentData);
			} else {
//				log.info("Procediment " + procedimentID + " ja existent, afegirem al seu serveiList.");
				altaList = lastProcedimentData.getAltes();
				baixaList = lastProcedimentData.getBaixes();
			}

			ServeiJPA servei = serveiLogicaEjb.findByPrimaryKey(serveiID);
			ServeiData serveiData = new ServeiData(serveiID, servei.getCodi(), servei.getNom(), pinfoData.getPinfodataID(), pinfoData.getAlta());
			if (pinfoData.getAlta() == 1) {
				altaList.add(serveiData);
			} else {
				baixaList.add(serveiData);
			}
		}

		PinfoDataFull pinfoDataFull = new PinfoDataFull(pinfoID, usuarisList);
		printPinfoDataFull(pinfoDataFull);

		log.info(llista.size() + " registres de PinfoData per PINFO" + pinfoID);
		return pinfoDataFull;

	}

	private void printPinfoDataFull(PinfoDataFull pinfoDataFull) {
		log.info("PinfoDataFull: " + pinfoDataFull.getPinfoID());
		for (UsuariData usuariData : pinfoDataFull.getUsuaris()) {
			log.info("Usuari: " + usuariData.getUserInfo());
			for (ProcedimentData procedimentData : usuariData.getProcediments()) {
				log.info("\tProcediment: " + procedimentData.getProcedimentID());

				if (procedimentData.getAltes().size() > 0) {
					log.info("\t\tALTA");
					for (ServeiData serveiData : procedimentData.getAltes()) {
						log.info("\t\tServei: " + serveiData.getServeiID() + " - " + serveiData.getServei() + " - "
								+ serveiData.getAlta());
					}
				}

				if (procedimentData.getBaixes().size() > 0) {
					log.info("\t\tBAIXA");
					for (ServeiData serveiData : procedimentData.getBaixes()) {
						log.info("\t\tServei: " + serveiData.getServeiID() + " - " + serveiData.getServei() + " - "
								+ serveiData.getAlta());
					}
				}
			}
		}
	}

	public class ServeiData {
		private Long serveiID;
		private String servei;
		private String nom;
		private Long pinfoDataID;
		private Long alta;

		public ServeiData(Long serveiID, String servei, String nom, Long pinfoDataID, Long alta) {
			this.serveiID = serveiID;
			this.servei = servei;
			this.nom = nom;
			this.pinfoDataID = pinfoDataID;
			this.alta = alta;
		}

		public Long getServeiID() {
			return this.serveiID;
		}

		public String getServei() {
			return this.servei;
		}

		public String getNom() {
			return this.nom;
		}

		public Long getPinfoDataID() {
			return this.pinfoDataID;
		}

		public Long getAlta() {
			return this.alta;
		}

		public void setServeiID(Long serveiID) {
			this.serveiID = serveiID;
		}

		public void setServei(String servei) {
			this.servei = servei;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public void setPinfoDataID(Long pinfoDataID) {
			this.pinfoDataID = pinfoDataID;
		}

		public void setAlta(Long alta) {
			this.alta = alta;
		}
	}

	public class ProcedimentData {
		private Long procedimentID;
		private String procediment;
		private String codi;
		private List<ServeiData> altes = new ArrayList<ServeiData>();
		private List<ServeiData> baixes = new ArrayList<ServeiData>();

		public ProcedimentData(Long procedimentID, String procediment, String codi, List<ServeiData> altes,
				List<ServeiData> baixes) {
			this.procedimentID = procedimentID;
			this.procediment = procediment;
			this.codi = codi;
			this.altes = altes;
			this.baixes = baixes;
		}

		public Long getProcedimentID() {
			return this.procedimentID;
		}

		public String getProcediment() {
			return this.procediment;
		}

		public String getCodi() {
			return this.codi;
		}

		public List<ServeiData> getAltes() {
			return this.altes;
		}

		public List<ServeiData> getBaixes() {
			return this.baixes;
		}

		public void setProcedimentID(Long procedimentID) {
			this.procedimentID = procedimentID;
		}

		public void setProcediment(String procediment) {
			this.procediment = procediment;
		}

		public void setCodi(String codi) {
			this.codi = codi;
		}

		public void setAltes(List<ServeiData> altes) {
			this.altes = altes;
		}

		public void setBaixes(List<ServeiData> baixes) {
			this.baixes = baixes;
		}
	}

	public class UsuariData {
		private String usuariCodi;
		private String usuariNif;
		private String usuariNom;

		private List<ProcedimentData> procediments = new ArrayList<ProcedimentData>();

		public UsuariData(String usuariCodi, String usuariNif, String usuariNom, List<ProcedimentData> procediments) {
			this.usuariCodi = usuariCodi;
			this.usuariNif = usuariNif;
			this.usuariNom = usuariNom;

			this.procediments = procediments;
		}

		public List<ProcedimentData> getProcediments() {
			return this.procediments;
		}

		public void setProcediments(List<ProcedimentData> procediments) {
			this.procediments = procediments;
		}

		public String getUsuariCodi() {
			return usuariCodi;
		}

		public void setUsuariCodi(String usuariCodi) {
			this.usuariCodi = usuariCodi;
		}

		public String getUsuariNif() {
			return usuariNif;
		}

		public void setUsuariNif(String usuariNif) {
			this.usuariNif = usuariNif;
		}

		public String getUsuariNom() {
			return usuariNom;
		}

		public void setUsuariNom(String usuariNom) {
			this.usuariNom = usuariNom;
		}

		public String getUserInfo() {
			return this.usuariCodi + " - " + this.usuariNif + " - " + this.usuariNom;
		}
	}

	public class PinfoDataFull {
		private Long pinfoID;
		private List<UsuariData> usuaris = new ArrayList<UsuariData>();

		public PinfoDataFull(Long pinfoID, List<UsuariData> usuaris) {
			this.pinfoID = pinfoID;
			this.usuaris = usuaris;
		}

		public Long getPinfoID() {
			return this.pinfoID;
		}

		public List<UsuariData> getUsuaris() {
			return this.usuaris;
		}

		public void setPinfoID(Long pinfoID) {
			this.pinfoID = pinfoID;
		}

		public void setUsuaris(List<UsuariData> usuaris) {
			this.usuaris = usuaris;
		}
	}

	public void test() throws Exception {
		boolean debug = true;
		boolean caib = true;

		IEstructuraOrganitzativaPlugin plugin = PinbalAdminPluginsManager.getEstructuraOrganitzativaPlugin(debug, caib);

		String username = "e45186147w";
		String cap = plugin.getCapAreaConsellerName(username);

		log.info("El cap de " + username + " es " + cap);
//		
//		LDAPUser[] usuaris = getLDAPUserManager().getUserArray();
//		for (LDAPUser ldapUser : usuaris) {
//			log.info(ldapUser.getAdministrationID() + " - " + ldapUser.getName());
//		}
	}

	@Override
	public List<String> getResponsablesProcedimentsPinfos(Long pinfoID) throws I18NException {

		List<String> responsablesList = new ArrayList<String>();

		List<PinfoData> pinfoDatas = this.select(PinfoDataFields.PINFOID.equal(pinfoID));

		for (PinfoData pinfoData : pinfoDatas) {
			Long procedimentID = pinfoData.getProcedimentID();
			SolicitudJPA procediment = solicitudLogicaEjb.findByPrimaryKey(procedimentID);
			String responsable = procediment.getResponsableProcNom() + " - " + procediment.getResponsableProcEmail();

			if (!responsablesList.contains(responsable)) {
				responsablesList.add(responsable);
			}
		}

		return responsablesList;
	}

//	@Override
	public void procesarPermisosPinfoOld(Long pinfoID) throws I18NException {

		// Cambiar todo esto por un select where pinfoID = pinfoID.
//		PinfoDataFull pinfoDataFull = this.getEstructuraUsuarisProcedimentServeis(pinfoID);
//		log.info("Procesant PinfoDatas " + pinfoDataFull);
//
//		for (UsuariData user : pinfoDataFull.getUsuaris()) {
//			for (ProcedimentData proc : user.getProcediments()) {
//				for (ServeiData serv : proc.getAltes()) {
//					log.info("ALTA: " + user.getUsuariID() + " - " + proc.getProcediment() + " - "
//							+ serv.getServei());
//				}
//				for (ServeiData serv : proc.getBaixes()) {
//					log.info("BAIXA: " + user.getUsuariID() + " - " + proc.getProcediment() + " - "
//							+ serv.getServei());
//				}
//			}
//		}

		Where where = PinfoDataFields.PINFOID.equal(pinfoID);
		List<PinfoData> pinfoDatas = this.select(where);

		for (PinfoData pinfoData : pinfoDatas) {

			String usuariID = pinfoData.getUsuariid();

			String procedimentCodi = solicitudLogicaEjb.executeQueryOne(SolicitudFields.PROCEDIMENTCODI,
					SolicitudFields.SOLICITUDID.equal(pinfoData.getProcedimentID()));

			String serveiCodi = serveiLogicaEjb.executeQueryOne(ServeiFields.CODI,
					ServeiFields.SERVEIID.equal(pinfoData.getServeiID()));

			String action = pinfoData.getAlta() == Constants.PINFO_ALTA ? "ALTA" : "BAIXA";

			cridadaPinbalPermisos(usuariID, procedimentCodi, serveiCodi, action);
		}
	}

	@Override
	public void procesarPermisosPinfo(Long pinfoID) throws I18NException {
		// ========== 1. INICIALITZACIÓ ==========
		PinfoJPA pinfo = pinfoLogicaEjb.findByPrimaryKey(pinfoID);
		final String ENTITAT_CIF = pinfo.getEntitat();
		
		StringBuilder logDetallat = new StringBuilder();
		StringBuilder missatgeTramitador = new StringBuilder();
		
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataHoraInici = sdf.format(new java.util.Date());
		
		logDetallat.append("=== INICI PROCESSAMENT PINFO #").append(pinfoID).append(" ===\n");
		logDetallat.append("Data/Hora: ").append(dataHoraInici).append("\n");
		logDetallat.append("Entitat CIF: ").append(ENTITAT_CIF).append("\n");
		
		log.info("INICI processament PINFO #" + pinfoID + " per entitat: " + ENTITAT_CIF);

		// ========== 2. CREAR CLIENTS API PINBAL I CACHÉS D'OPTIMITZACIÓ ==========
		final String baseUrl = Configuracio.getApiPinbalClientUrl();
		final String username = Configuracio.getApiPinbalClientUsername();
		final String password = Configuracio.getApiPinbalClientPassword();
		final LogLevel logLevel = LogLevel.INFO;

		logDetallat.append("API Pinbal URL: ").append(baseUrl).append("\n\n");
		log.info("Creant clients API Pinbal");

		ServeiClient serveiClient = new ServeiClient(baseUrl, username, password, logLevel);
		UsuariClient usuariClient = new UsuariClient(baseUrl, username, password, logLevel);
		ProcedimentClient procedimentClient = new ProcedimentClient(baseUrl, username, password, logLevel);

		logDetallat.append("✓ Clients API Pinbal creats\n\n");

		// Cachés per optimitzar validacions repetides
		java.util.Map<String, Procediment> cacheProcediments = new java.util.HashMap<>();
		java.util.Map<String, Servei> cacheServeis = new java.util.HashMap<>();
		java.util.Map<String, Boolean> cacheHabilitacions = new java.util.HashMap<>();

		// Estructures per categoritzar errors únics
		java.util.Set<String> procedimentsNoExisteixen = new java.util.LinkedHashSet<>();
		java.util.Set<String> serveisNoExisteixen = new java.util.LinkedHashSet<>();
		java.util.Map<String, java.util.Set<String>> serveisNoAutoritzats = new java.util.LinkedHashMap<>(); // procediment -> serveis
		java.util.Map<Long, String> pinfodataErrors = new java.util.HashMap<>(); // pinfoDataID -> missatge error

		// ========== 3. CARREGAR DADES A PROCESSAR ==========
		int totalUsuaris = 0, usuarisProcessatsOK = 0, totalPermisosConcedits = 0;
		int totalPinfodatasProcessats = 0, pinfodatasOK = 0, pinfodatasError = 0;

		PinfoDataFull pinfoDataFull = getEstructuraUsuarisProcedimentServeis(pinfoID);
		totalUsuaris = pinfoDataFull.getUsuaris().size();
		
		logDetallat.append("--- USUARIS A PROCESSAR: ").append(totalUsuaris).append(" ---\n\n");
		log.info("Total usuaris a processar: " + totalUsuaris);
		
		// ========== 4. PROCESSAR CADA USUARI ==========
		for (UsuariData usuariData : pinfoDataFull.getUsuaris()) {
			try {
				String codiUsuari = usuariData.getUsuariCodi();
				String nomUsuari = usuariData.getUsuariNom() != null ? usuariData.getUsuariNom() : codiUsuari;
				
				logDetallat.append("┌─ USUARI: ").append(nomUsuari).append(" (").append(codiUsuari).append(")\n");
				logDetallat.append("│  Procediments: ").append(usuariData.getProcediments().size()).append("\n");
				log.info("→ Processant usuari: " + codiUsuari);

				List<ProcedimentServei> procedimentServeiList = new ArrayList<ProcedimentServei>();
				int serveisUsuari = 0;

				// --- Processar procediments de l'usuari ---
				for (ProcedimentData procedimentData : usuariData.getProcediments()) {
					String procedimentCodi = procedimentData.getCodi();
					String procedimentNom = procedimentData.getProcediment() != null ? procedimentData.getProcediment() : procedimentCodi;
					
					logDetallat.append("│\n│  ├─ PROCEDIMENT: ").append(procedimentNom).append(" (").append(procedimentCodi).append(")\n");
					
					// Validar procediment a Pinbal (amb caché)
					Procediment procediment = validarProcedimentPinbal(procedimentCodi, ENTITAT_CIF, procedimentClient, 
							logDetallat, procedimentsNoExisteixen, cacheProcediments);
					if (procediment == null) {
						// Marcar tots els serveis d'aquest procediment com a error
						for (ServeiData sd : procedimentData.getAltes()) {
							totalPinfodatasProcessats++;
							pinfodatasError++;
							pinfodataErrors.put(sd.getPinfoDataID(), "Procediment " + procedimentCodi + " no existeix a Pinbal");
							actualitzarEstatPinfoData(sd.getPinfoDataID(), Constants.ESTAT_PINFODATA_ERROR);
						}
						continue; // Procediment no trobat, passar al següent
					}
					
					logDetallat.append("│  │  Serveis a processar: ").append(procedimentData.getAltes().size()).append("\n");

					// --- Processar serveis d'alta ---
					for (ServeiData serveiData : procedimentData.getAltes()) {
						totalPinfodatasProcessats++;
						String serveiNom = serveiData.getNom() != null ? serveiData.getNom() : serveiData.getServei();
						logDetallat.append("│  │\n│  │  ├─ SERVEI: ").append(serveiNom).append(" # ").append(serveiData.getServei()).append(" (").append(serveiData.getServei()).append(")\n");
						
						// Validar servei a Pinbal (amb caché)
						Servei servei = validarServeiPinbal(serveiData.getServei(), serveiClient, logDetallat, serveisNoExisteixen, cacheServeis);
						if (servei == null) {
							pinfodatasError++;
							pinfodataErrors.put(serveiData.getPinfoDataID(), "Servei " + serveiData.getServei() + " no existeix a Pinbal");
							actualitzarEstatPinfoData(serveiData.getPinfoDataID(), Constants.ESTAT_PINFODATA_ERROR);
							continue; // Servei no trobat, passar al següent
						}
						
						// Habilitar servei al procediment (amb caché)
						boolean habilitat = habilitarServeiProcediment(procediment.getId(), servei.getCodi(), procedimentCodi, 
								serveiData.getServei(), procedimentClient, logDetallat, serveisNoAutoritzats, cacheHabilitacions);
						
						if (habilitat) {
							procedimentServeiList.add(new ProcedimentServei(procedimentCodi, servei.getCodi()));
							serveisUsuari++;
							pinfodatasOK++;
							actualitzarEstatPinfoData(serveiData.getPinfoDataID(), Constants.ESTAT_PINFODATA_OK);
						} else {
							pinfodatasError++;
							pinfodataErrors.put(serveiData.getPinfoDataID(), "Servei " + serveiData.getServei() + " no es pot autoritzar per al procediment " + procedimentCodi);
							actualitzarEstatPinfoData(serveiData.getPinfoDataID(), Constants.ESTAT_PINFODATA_ERROR);
						}
					}
				}

				// --- Concedir permisos a l'usuari ---
				if (procedimentServeiList.isEmpty()) {
					logDetallat.append("│\n│  ⚠ Cap servei a concedir per aquest usuari\n");
					logDetallat.append("└─ Fi usuari (sense permisos)\n\n");
					log.warn("Cap servei per usuari " + codiUsuari);
					continue;
				}

				logDetallat.append("│\n│  → Concedint ").append(serveisUsuari).append(" permisos a Pinbal...\n");
				PermisosServei permisosServei = new PermisosServei(codiUsuari, ENTITAT_CIF, procedimentServeiList);

				try {
					usuariClient.grantPermissions(codiUsuari, permisosServei);
					logDetallat.append("│  ✓ PERMISOS CONCEDITS\n");
					logDetallat.append("└─ Fi usuari [OK]\n\n");
					
					usuarisProcessatsOK++;
					totalPermisosConcedits += serveisUsuari;
					log.info("✓ Permisos concedits a " + codiUsuari + ": " + serveisUsuari + " serveis");
					
				} catch (Throwable t) {
					String msg = "Error concedint permisos a usuari " + codiUsuari + ": " + t.getMessage();
					logDetallat.append("│  ✗ ERROR CRÍTIC: ").append(msg).append("\n");
					logDetallat.append("│    ").append(t.getMessage()).append("\n");
					logDetallat.append("└─ Fi usuari [ERROR]\n\n");
					log.error(msg, t);
					throw new I18NException("genapp.comodi", msg);
				}

			} catch (I18NException e) {
				throw e; // Propagar error crític
			} catch (Throwable t) {
				String msg = "Error inesperat processant usuari";
				logDetallat.append("└─ ERROR FATAL: ").append(msg).append("\n\n");
				log.error(msg, t);
				throw new I18NException("genapp.comodi", msg + " - " + t.getMessage());
			}
		}

		// ========== 5. GENERAR RESUM AMB ERRORS ÚNICS I INSTRUCCIONS ==========
		String dataHoraFi = sdf.format(new java.util.Date());
		
		// Calcular estadístiques
		int totalProcedimentsDistints = cacheProcediments.size();
		int totalServeisDistints = cacheServeis.size();
		int totalHabilitacionsDistintes = cacheHabilitacions.size();
		int totalErrorsUnics = procedimentsNoExisteixen.size() + serveisNoExisteixen.size() + serveisNoAutoritzats.size();
		
		// === LOG DETALLAT ===
		logDetallat.append("=== RESUM PROCESSAMENT ===\n");
		logDetallat.append("Data/Hora Fi: ").append(dataHoraFi).append("\n");
		logDetallat.append("Usuaris processats: ").append(usuarisProcessatsOK).append("/").append(totalUsuaris).append("\n");
		logDetallat.append("Línies processades: ").append(pinfodatasOK).append("/").append(totalPinfodatasProcessats).append(" (").append(pinfodatasError).append(" errors)\n");
		logDetallat.append("Permisos concedits: ").append(totalPermisosConcedits).append("\n");
		logDetallat.append("Errors únics: ").append(totalErrorsUnics).append("\n");
		logDetallat.append("\nOptimització (validacions úniques):\n");
		logDetallat.append("  • Procediments diferents: ").append(totalProcedimentsDistints).append("\n");
		logDetallat.append("  • Serveis diferents: ").append(totalServeisDistints).append("\n");
		logDetallat.append("  • Habilitacions diferents: ").append(totalHabilitacionsDistintes).append("\n");
		
		if (totalErrorsUnics > 0) {
			logDetallat.append("\n=== ERRORS ÚNICS DETECTATS ===\n");
			if (!procedimentsNoExisteixen.isEmpty()) {
				logDetallat.append("\nProcediments que no existeixen a Pinbal (").append(procedimentsNoExisteixen.size()).append("):\n");
				for (String proc : procedimentsNoExisteixen) {
					logDetallat.append("  • ").append(proc).append("\n");
				}
			}
			if (!serveisNoExisteixen.isEmpty()) {
				logDetallat.append("\nServeis que no existeixen a Pinbal (").append(serveisNoExisteixen.size()).append("):\n");
				for (String servei : serveisNoExisteixen) {
					logDetallat.append("  • ").append(servei).append("\n");
				}
			}
			if (!serveisNoAutoritzats.isEmpty()) {
				logDetallat.append("\nServeis que no es poden autoritzar (").append(serveisNoAutoritzats.size()).append(" procediments afectats):\n");
				for (java.util.Map.Entry<String, java.util.Set<String>> entry : serveisNoAutoritzats.entrySet()) {
					logDetallat.append("  • Procediment: ").append(entry.getKey()).append("\n");
					for (String servei : entry.getValue()) {
						logDetallat.append("    - ").append(servei).append("\n");
					}
				}
			}
		}
		logDetallat.append("\n=== FI PROCESSAMENT ===\n");

		// === MISSATGE PINBAL (per al tramitador - info tècnica) ===
		missatgeTramitador.append("RESULTAT DEL PROCESSAMENT\n");
		missatgeTramitador.append("=========================\n\n");
		missatgeTramitador.append("Data: ").append(dataHoraFi).append("\n\n");
		
		missatgeTramitador.append("RESUM GENERAL:\n");
		missatgeTramitador.append("• Usuaris processats: ").append(usuarisProcessatsOK).append("/").append(totalUsuaris).append("\n");
		missatgeTramitador.append("• Autoritzacions tramitades: ").append(pinfodatasOK).append("/").append(totalPinfodatasProcessats).append("\n");
		missatgeTramitador.append("• Permisos concedits: ").append(totalPermisosConcedits).append("\n");
		missatgeTramitador.append("• Autoritzacions amb error: ").append(pinfodatasError).append("\n");
		missatgeTramitador.append("• Errors únics detectats: ").append(totalErrorsUnics).append("\n\n");
		
		if (totalErrorsUnics > 0) {
			missatgeTramitador.append("ERRORS DETECTATS I COM CORREGIR-LOS:\n");
			missatgeTramitador.append("=====================================\n\n");
			
			if (!procedimentsNoExisteixen.isEmpty()) {
				missatgeTramitador.append("❌ PROCEDIMENTS QUE NO EXISTEIXEN A PINBAL (").append(procedimentsNoExisteixen.size()).append("):\n");
				for (String proc : procedimentsNoExisteixen) {
					missatgeTramitador.append("  • ").append(proc).append("\n");
				}
				missatgeTramitador.append("\n  ⚠️ ACCIÓ REQUERIDA:\n");
				missatgeTramitador.append("     - Verificar que el codi del procediment sigui correcte\n");
				missatgeTramitador.append("     - Crear el procediment a Pinbal si no existeix\n");
				missatgeTramitador.append("     - Revisar que l'entitat CIF sigui correcta\n\n");
			}
			
			if (!serveisNoExisteixen.isEmpty()) {
				missatgeTramitador.append("❌ SERVEIS QUE NO EXISTEIXEN A PINBAL (").append(serveisNoExisteixen.size()).append("):\n");
				for (String servei : serveisNoExisteixen) {
					missatgeTramitador.append("  • ").append(servei).append("\n");
				}
				missatgeTramitador.append("\n  ⚠️ ACCIÓ REQUERIDA:\n");
				missatgeTramitador.append("     - Verificar que el codi del servei sigui correcte\n");
				missatgeTramitador.append("     - Contactar amb l'administrador de Pinbal per donar d'alta el servei\n");
				missatgeTramitador.append("     - Revisar el catàleg de serveis disponibles\n\n");
			}
			
			if (!serveisNoAutoritzats.isEmpty()) {
				missatgeTramitador.append("❌ SERVEIS NO AUTORITZABLES (").append(serveisNoAutoritzats.size()).append(" procediments afectats):\n");
				for (java.util.Map.Entry<String, java.util.Set<String>> entry : serveisNoAutoritzats.entrySet()) {
					missatgeTramitador.append("  Procediment: ").append(entry.getKey()).append("\n");
					for (String servei : entry.getValue()) {
						missatgeTramitador.append("    • ").append(servei).append("\n");
					}
				}
				missatgeTramitador.append("\n  ⚠️ ACCIÓ REQUERIDA:\n");
				missatgeTramitador.append("     - Verificar que el servei estigui donat d'alta a Pinbal\n");
				missatgeTramitador.append("     - Comprovar que el servei estigui habilitat per aquesta entitat\n");
				missatgeTramitador.append("     - Contactar amb l'administrador de Pinbal per autoritzar el servei\n\n");
			}
			
			missatgeTramitador.append("\n⚠️ IMPORTANT:\n");
			missatgeTramitador.append("Després de corregir els errors, utilitzeu l'opció 'REPROCESSAR PINFO'\n");
			missatgeTramitador.append("per tornar a tramitar només les autoritzacions amb error.\n\n");
		} else {
			missatgeTramitador.append("\n✓ Processament completat sense errors\n");
		}
		missatgeTramitador.append("\n\nATENCIÓ TRAMITADOR: Revisar el resultat i marcar com tramitat si tot és correcte.");

		// ========== 6. GUARDAR RESULTAT (sense canviar estat a TRAMITAT) ==========
		log.info("Guardant resultat del processament");
		pinfo.setMissatgePinbal(missatgeTramitador.toString());
		pinfo.setLogpPnbal(logDetallat.toString());
		pinfo.setMissatgeSolicitant(null); // Es generarà al tramitar
		// NO canviem l'estat aquí - l'operador ho farà manualment després de revisar
		pinfoLogicaEjb.update(pinfo);

		log.info("FI processament PINFO #" + pinfoID + " - Usuaris: " + usuarisProcessatsOK + "/" + totalUsuaris 
				+ ", Permisos: " + totalPermisosConcedits + ", PinfoDatas OK: " + pinfodatasOK + ", Errors: " + pinfodatasError 
				+ ", Errors únics: " + totalErrorsUnics);
		log.info("IMPORTANT: L'operador ha de revisar els resultats i tramitar manualment.");
	}
	
	/**
	 * Actualitza l'estat d'un PinfoData individual després de processar-lo
	 */
	private void actualitzarEstatPinfoData(Long pinfodataID, Long nouEstat) {
		try {
			PinfoData pinfoData = this.findByPrimaryKey(pinfodataID);
			if (pinfoData != null) {
				pinfoData.setEstat(nouEstat);
				this.update(pinfoData);
			}
		} catch (Throwable t) {
			log.warn("No s'ha pogut actualitzar estat de PinfoData #" + pinfodataID + ": " + t.getMessage());
		}
	}
	
	/**
	 * Marca un Pinfo com a TRAMITAT després de revisar els resultats del processament.
	 * Aquest mètode s'invoca manualment per l'operador i genera el missatge per al solicitant.
	 */
	@Override
	public void marcarPinfoComTramitat(Long pinfoID) throws I18NException {
		log.info("Marcant PINFO #" + pinfoID + " com a TRAMITAT manualment");
		
		PinfoJPA pinfo = pinfoLogicaEjb.findByPrimaryKey(pinfoID);
		if (pinfo == null) {
			throw new I18NException("genapp.comodi", "Pinfo no trobat: " + pinfoID);
		}
		
		// Carregar totes les línies de PinfoData per generar el missatge
		List<PinfoData> pinfodatas = this.select(PinfoDataFields.PINFOID.equal(pinfoID), null);
		
		// Estructura jeràrquica: Usuari -> Procediment -> Servei -> Estat
		// Map<usuariID, Map<procedimentNom, Map<serveiNom, estat>>>
		java.util.Map<String, java.util.Map<String, java.util.Map<String, String>>> estructuraJerarquica = new java.util.LinkedHashMap<>();
		
		// Conjunts per errors únics (per al resum final)
		java.util.Set<String> serveisNoExisteixen = new java.util.LinkedHashSet<>();
		java.util.Set<String> procedimentsNoExisteixen = new java.util.LinkedHashSet<>();
		java.util.Map<String, java.util.Set<String>> serveisNoAutoritzats = new java.util.LinkedHashMap<>(); // procediment -> serveis
		
		int totalLinies = pinfodatas.size();
		int liniesOK = 0;
		int liniesError = 0;
		
		// Processar cada PinfoData i construir l'estructura jeràrquica
		for (PinfoData pd : pinfodatas) {
			String usuariID = pd.getUsuariid();
			Long estat = pd.getEstat();
			
			String estatText;
			String errorDetall = null;
			
			if (estat != null && estat.equals(Constants.ESTAT_PINFODATA_OK)) {
				estatText = "✓ AUTORIZADO";
				liniesOK++;
			} else if (estat != null && estat.equals(Constants.ESTAT_PINFODATA_ERROR)) {
				estatText = "✗ ERROR";
				liniesError++;
			} else {
				estatText = "⚠ PENDIENTE";
			}
			
			// Obtenir noms de procediment i servei
			String procedimentNom = "Procediment desconegut";
			String procedimentCodi = "";
			String serveiNom = "Servei desconegut";
			String serveiCodi = "";
			
			try {
				SolicitudJPA procediment = solicitudLogicaEjb.findByPrimaryKey(pd.getProcedimentID());
				if (procediment != null) {
					procedimentNom = procediment.getProcedimentNom();
					procedimentCodi = procediment.getProcedimentCodi();
				}
				
				ServeiJPA servei = serveiLogicaEjb.findByPrimaryKey(pd.getServeiID());
				if (servei != null) {
					serveiNom = servei.getNom();
					serveiCodi = servei.getCodi();
				}
				
				// Si hi ha error, intentar determinar el tipus d'error del log de Pinbal
				if (estat != null && estat.equals(Constants.ESTAT_PINFODATA_ERROR)) {
					// Analitzar el missatge de Pinbal per determinar el tipus d'error
					String logPinbal = pinfo.getLogpPnbal();
					if (logPinbal != null) {
						if (logPinbal.contains("Servei " + serveiCodi + " no existeix") || 
							logPinbal.contains("VALIDACIÓ [✗]: Servei " + serveiCodi)) {
							serveisNoExisteixen.add(serveiCodi + " (" + serveiNom + ")");
							errorDetall = "El servei no existeix a Pinbal";
						} else if (logPinbal.contains("Procediment " + procedimentCodi + " no existeix") ||
								   logPinbal.contains("VALIDACIÓ [✗]: Procediment " + procedimentCodi)) {
							procedimentsNoExisteixen.add(procedimentCodi + " (" + procedimentNom + ")");
							errorDetall = "El procediment no existeix a Pinbal";
						} else if (logPinbal.contains("Servei " + serveiCodi + " no autoritzable") ||
								   logPinbal.contains("HABILITACIÓ [✗]:")) {
							if (!serveisNoAutoritzats.containsKey(procedimentCodi + " (" + procedimentNom + ")")) {
								serveisNoAutoritzats.put(procedimentCodi + " (" + procedimentNom + ")", new java.util.LinkedHashSet<>());
							}
							serveisNoAutoritzats.get(procedimentCodi + " (" + procedimentNom + ")").add(serveiCodi + " (" + serveiNom + ")");
							errorDetall = "El servei no es pot autoritzar per aquest procediment";
						}
					}
				}
				
			} catch (Exception e) {
				log.warn("Error obtenint detalls per PinfoData #" + pd.getPinfodataID() + ": " + e.getMessage());
			}
			
			// Afegir a l'estructura jeràrquica
			if (!estructuraJerarquica.containsKey(usuariID)) {
				estructuraJerarquica.put(usuariID, new java.util.LinkedHashMap<>());
			}
			
			java.util.Map<String, java.util.Map<String, String>> procedimentsUsuari = estructuraJerarquica.get(usuariID);
			if (!procedimentsUsuari.containsKey(procedimentNom)) {
				procedimentsUsuari.put(procedimentNom, new java.util.LinkedHashMap<>());
			}
			
			java.util.Map<String, String> serveisProcediment = procedimentsUsuari.get(procedimentNom);
			String estatComplet = errorDetall != null ? estatText + " (" + errorDetall + ")" : estatText;
			serveisProcediment.put(serveiNom, estatComplet);
		}
		
		// ========== GENERAR MISSATGE PER AL SOLICITANT ==========
		StringBuilder missatge = new StringBuilder();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
		
		missatge.append("Estimat/da sol·licitant,<br><br>");
		missatge.append("S'ha tramitat la seva sol·licitud d'autorització amb la següent informació:<br><br>");
		
		missatge.append("<strong>RESUM:</strong><br>");
		missatge.append("• Total d'autoritzacions: ").append(totalLinies).append("<br>");
		missatge.append("• Tramitades correctament: ").append(liniesOK).append("<br>");
		missatge.append("• Amb incidències: ").append(liniesError).append("<br>");
		missatge.append("• Data de tramitació: ").append(sdf.format(new java.util.Date())).append("<br><br>");
		
		missatge.append("<strong>DETALL PER USUARI:</strong><br><br>");
		
		// Recórrer l'estructura jeràrquica i generar el llistat
		for (java.util.Map.Entry<String, java.util.Map<String, java.util.Map<String, String>>> entryUsuari : estructuraJerarquica.entrySet()) {
			String usuariID = entryUsuari.getKey();
			java.util.Map<String, java.util.Map<String, String>> procediments = entryUsuari.getValue();
			
			missatge.append("<strong>• Usuari: ").append(usuariID).append("</strong><br>");
			
			for (java.util.Map.Entry<String, java.util.Map<String, String>> entryProcediment : procediments.entrySet()) {
				String procedimentNom = entryProcediment.getKey();
				java.util.Map<String, String> serveis = entryProcediment.getValue();
				
				missatge.append("&nbsp;&nbsp;&nbsp;&nbsp;→ Procediment: ").append(procedimentNom).append("<br>");
				
				for (java.util.Map.Entry<String, String> entryServei : serveis.entrySet()) {
					String serveiNom = entryServei.getKey();
					String estat = entryServei.getValue();
					
					missatge.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- ").append(serveiNom).append(": <strong>").append(estat).append("</strong><br>");
				}
			}
			missatge.append("<br>");
		}
		
		// Si hi ha errors, afegir resum d'errors al final
		if (liniesError > 0) {
			missatge.append("<br><strong>ERRORS DETECTATS:</strong><br>");
			
			if (!serveisNoExisteixen.isEmpty()) {
				missatge.append("<br>• Serveis que no existeixen a la Plataforma d'Intermediació:<br>");
				for (String servei : serveisNoExisteixen) {
					missatge.append("&nbsp;&nbsp;&nbsp;&nbsp;- ").append(servei).append("<br>");
				}
			}
			
			if (!procedimentsNoExisteixen.isEmpty()) {
				missatge.append("<br>• Procediments que no existeixen a la Plataforma d'Intermediació:<br>");
				for (String procediment : procedimentsNoExisteixen) {
					missatge.append("&nbsp;&nbsp;&nbsp;&nbsp;- ").append(procediment).append("<br>");
				}
			}
			
			if (!serveisNoAutoritzats.isEmpty()) {
				missatge.append("<br>• Serveis no autoritzables:<br>");
				for (java.util.Map.Entry<String, java.util.Set<String>> entry : serveisNoAutoritzats.entrySet()) {
					String procediment = entry.getKey();
					java.util.Set<String> serveis = entry.getValue();
					missatge.append("&nbsp;&nbsp;&nbsp;&nbsp;Per al procediment <strong>").append(procediment).append("</strong>:<br>");
					for (String servei : serveis) {
						missatge.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- ").append(servei).append("<br>");
					}
				}
			}
			
			missatge.append("<br><em>Si necessita més informació sobre les incidències detectades, ");
			missatge.append("si us plau, posis en contacte amb el servei de suport tècnic.</em><br>");
		} else {
			missatge.append("<br><strong>✓ Tots els permisos s'han tramitat correctament.</strong><br>");
			missatge.append("Els usuaris ja poden accedir als serveis sol·licitats.<br>");
		}
		
		// Actualitzar Pinfo amb el missatge i l'estat
		pinfo.setEstat(Constants.ESTAT_PINFO_TRAMITAT);
		pinfo.setMissatgeSolicitant(missatge.toString());
		pinfoLogicaEjb.update(pinfo);
		log.info("Estat Pinfo actualitzat a TRAMITAT i missatge al solicitant generat");
		
		// Actualitzar estat de la incidència tècnica associada
		if (pinfo.getIncidenciaID() != null) {
			IncidenciaTecnica incidencia = incidenciaLogicaEjb.findByPrimaryKey(pinfo.getIncidenciaID());
			incidencia.setEstat(Constants.ESTAT_INCIDENCIA_PINFO_TRAMITAT.intValue());
			incidenciaLogicaEjb.update(incidencia);
			log.info("Estat IncidenciaTecnica actualitzat a PINFO_TRAMITAT");
		}
		
		log.info("PINFO #" + pinfoID + " marcat com a TRAMITAT correctament. Linies OK: " + liniesOK + ", Errors: " + liniesError);
	}
	
	// Mètodes auxiliars per validació i operacions Pinbal
	
	private Procediment validarProcedimentPinbal(String codi, String entitat, ProcedimentClient client,
			StringBuilder log, java.util.Set<String> procedimentsNoExisteixen, java.util.Map<String, Procediment> cache) {
		String clauCache = codi + "|" + entitat;
		
		// Comprovar si ja està al caché
		if (cache.containsKey(clauCache)) {
			Procediment cached = cache.get(clauCache);
			if (cached != null) {
				log.append("│  │  ✓ Procediment trobat (ID: ").append(cached.getId()).append(") [CACHE]\n");
			} else {
				log.append("│  │  ✗ ERROR: Procediment ").append(codi).append(" no existeix a Pinbal [CACHE]\n");
			}
			return cached;
		}
		
		// No està al caché, validar i afegir
		try {
			Procediment proc = client.getProcediment(codi, entitat);
			cache.put(clauCache, proc);
			log.append("│  │  ✓ Procediment trobat (ID: ").append(proc.getId()).append(")\n");
			return proc;
		} catch (Throwable t) {
			cache.put(clauCache, null); // Cachear el resultat negatiu
			procedimentsNoExisteixen.add(codi);
			log.append("│  │  ✗ ERROR: Procediment ").append(codi).append(" no existeix a Pinbal\n");
			return null;
		}
	}
	
	private Servei validarServeiPinbal(String codi, ServeiClient client, StringBuilder log, 
			java.util.Set<String> serveisNoExisteixen, java.util.Map<String, Servei> cache) {
		// Comprovar si ja està al caché
		if (cache.containsKey(codi)) {
			Servei cached = cache.get(codi);
			if (cached != null) {
				log.append("│  │  │  ✓ Servei trobat [CACHE]\n");
			} else {
				log.append("│  │  │  ✗ ERROR: Servei ").append(codi).append(" no existeix a Pinbal [CACHE]\n");
			}
			return cached;
		}
		
		// No està al caché, validar i afegir
		try {
			Servei servei = client.getServei(codi);
			cache.put(codi, servei);
			log.append("│  │  │  ✓ Servei trobat\n");
			return servei;
		} catch (Throwable t) {
			cache.put(codi, null); // Cachear el resultat negatiu
			serveisNoExisteixen.add(codi);
			log.append("│  │  │  ✗ ERROR: Servei ").append(codi).append(" no existeix a Pinbal\n");
			return null;
		}
	}
	
	private boolean habilitarServeiProcediment(Long procId, String serveiCodi, String procCodi, 
			String serveiOriginal, ProcedimentClient client, StringBuilder log, 
			java.util.Map<String, java.util.Set<String>> serveisNoAutoritzats,
			java.util.Map<String, Boolean> cache) {
		String clauCache = procId + "|" + serveiCodi;
		
		// Comprovar si ja està al caché
		if (cache.containsKey(clauCache)) {
			Boolean cached = cache.get(clauCache);
			if (cached) {
				log.append("│  │  │  ✓ Servei habilitat [CACHE]\n");
			} else {
				log.append("│  │  │  ✗ ERROR: No es pot autoritzar servei ").append(serveiOriginal)
					.append(" per procediment ").append(procCodi).append(" [CACHE]\n");
			}
			return cached;
		}
		
		// No està al caché, habilitar i afegir
		try {
			client.enableServeiToProcediment(procId, serveiCodi);
			cache.put(clauCache, true);
			log.append("│  │  │  ✓ Servei habilitat\n");
			return true;
		} catch (Throwable t) {
			cache.put(clauCache, false); // Cachear el resultat negatiu
			// Afegir a la llista de serveis no autoritzats
			if (!serveisNoAutoritzats.containsKey(procCodi)) {
				serveisNoAutoritzats.put(procCodi, new java.util.LinkedHashSet<>());
			}
			serveisNoAutoritzats.get(procCodi).add(serveiOriginal);
			log.append("│  │  │  ✗ ERROR: No es pot autoritzar servei ").append(serveiOriginal)
				.append(" per procediment ").append(procCodi).append("\n");
			return false;
		}
	}

	@Override
	public void llistatUsuarisPinbal() {

		final String baseUrl = Configuracio.getApiPinbalClientUrl();
		final String username = Configuracio.getApiPinbalClientUsername();
		final String password = Configuracio.getApiPinbalClientPassword();
		final LogLevel logLevel = LogLevel.INFO;

		log.info("Creant Clients");
		UsuariClient usuariClient = new UsuariClient(baseUrl, username, password, logLevel);
		log.info("Clients creats");

		final String ENTITAT_CIF = "GOVERN"; // "S0711001H";

		int page = 0;
		int size = -1;
		String sort = null;
		FiltreUsuaris filter = null;

		try {
			Page<UsuariEntitat> usuariPage = usuariClient.getUsuaris(ENTITAT_CIF, filter, page, size, sort);
			log.info(objectToJsonString(usuariPage));

		} catch (UniformInterfaceException | ClientHandlerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cridadaPinbalPermisos(String username, String procediment, String servei, String action) {

		username = "e45186147w";
//		procediment = "CODSVDR_GBA_20121107";
		procediment = "CODSVDR_20121107";

		log.info("Cridada a Pinbal per permisos: " + username + " - " + procediment + " - " + servei + " - " + action);

		try {
			peticionSincrona(username, procediment, servei, action);
		} catch (Exception e) {
			String msg = "Error cridant a Pinbal per permisos. " + e.getMessage();
			log.error(msg, e);
		}
	}

	public void peticionSincrona(String codiUsuari, String codiProcediment, String codiServei, String action)
			throws UniformInterfaceException, IOException {

		log.info("Cridant a Pinbal per permisos");

		final String baseUrl = Configuracio.getApiPinbalClientUrl();
		final String username = Configuracio.getApiPinbalClientUsername();
		final String password = Configuracio.getApiPinbalClientPassword();

		final String ENTITAT_CIF = "GOVERN"; // "S0711001H";
		/*
		final String CODIGO_PROCEDIMIENTO = "CODSVDR_GBA_20121107";
		final String PETICION_SCSP_ID = "PINBAL00000000000000265474";
		final boolean ENABLE_LOGGING = true;
		final boolean BASIC_AUTH = true;
		*/

		LogLevel logLevel = LogLevel.INFO;
		log.info("Creant Clients");

		ServeiClient serveiClient = new ServeiClient(baseUrl, username, password, logLevel);
		UsuariClient usuariClient = new UsuariClient(baseUrl, username, password, logLevel);
		ProcedimentClient procedimentClient = new ProcedimentClient(baseUrl, username, password, logLevel);

		log.info("Clients creats");

		UsuariEntitat usuari = usuariClient.getUsuari(codiUsuari, ENTITAT_CIF);
		log.info(objectToJsonString(usuari));

		Procediment procediment = procedimentClient.getProcediment(codiProcediment, ENTITAT_CIF);
		log.info(objectToJsonString(procediment));

		Servei servei = serveiClient.getServei(codiServei);
		log.info(objectToJsonString(servei));

		procedimentClient.enableServeiToProcediment(procediment.getId(), codiServei);
		// AFEGIR PERMISOS.

		PermisosServei permisos1 = usuariClient.getUserPermissions(codiUsuari, ENTITAT_CIF);
		log.info(objectToJsonString(permisos1));

		ProcedimentServei procedimentServei = new ProcedimentServei(codiProcediment, codiServei);
		log.info(objectToJsonString(procedimentServei));

		List<ProcedimentServei> procedimentServeiList = new ArrayList<ProcedimentServei>();
		procedimentServeiList.add(procedimentServei);
//        
		PermisosServei permisosServei = new PermisosServei(codiUsuari, ENTITAT_CIF, procedimentServeiList);
		log.info(objectToJsonString(permisosServei));

		try {
			usuariClient.grantPermissions(codiUsuari, permisosServei);

		} catch (Throwable t) {

		}

		PermisosServei permisos2 = usuariClient.getUserPermissions(codiUsuari, ENTITAT_CIF);
		log.info(objectToJsonString(permisos2));

	}

	private String objectToJsonString(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
		mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		return "\n" + mapper.writeValueAsString(obj);
	}
}