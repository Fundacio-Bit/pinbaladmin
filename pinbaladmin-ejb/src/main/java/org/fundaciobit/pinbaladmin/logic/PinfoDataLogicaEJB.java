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
		PinfoJPA pinfo = pinfoLogicaEjb.findByPrimaryKey(pinfoID);

		final String ENTITAT_CIF = pinfo.getEntitat();
		;

		log.info("ENTITAT_CIF: " + ENTITAT_CIF);

		List<String> missatges = new ArrayList<String>();

		final String baseUrl = Configuracio.getApiPinbalClientUrl();
		final String username = Configuracio.getApiPinbalClientUsername();
		final String password = Configuracio.getApiPinbalClientPassword();
		final LogLevel logLevel = LogLevel.INFO;

		log.info("Creant Clients");

		ServeiClient serveiClient = new ServeiClient(baseUrl, username, password, logLevel);
		UsuariClient usuariClient = new UsuariClient(baseUrl, username, password, logLevel);
		ProcedimentClient procedimentClient = new ProcedimentClient(baseUrl, username, password, logLevel);

		log.info("Clients creats");

		PinfoDataFull pinfoDataFull = getEstructuraUsuarisProcedimentServeis(pinfoID);
		for (UsuariData usuariData : pinfoDataFull.getUsuaris()) {

			try {
				String codiUsuari = usuariData.getUsuariCodi();
				log.info("Usuari: " + usuariData.getUserInfo());
//				UsuariEntitat usuariEntitat = usuariClient.getUsuari(codiUsuari, ENTITAT_CIF);
//				log.info(objectToJsonString(usuariEntitat));

				List<ProcedimentServei> procedimentServeiList = new ArrayList<ProcedimentServei>();

				for (ProcedimentData procedimentData : usuariData.getProcediments()) {
					String procedimentCodi = procedimentData.getCodi();
					log.info("procedimentCodi: " + procedimentCodi);
					Long procedimentId = null;
					try {
						Procediment procediment = procedimentClient.getProcediment(procedimentCodi, ENTITAT_CIF);
//						log.info(objectToJsonString(procediment));
						procedimentId = procediment.getId();

					} catch (Throwable t) {
						// El procediment no existeix a Pinbal. No fem res.
						String msg = "El procediment " + procedimentCodi + " no existeix a Pinbal.";
						missatges.add(msg);
						continue;
					}

					for (ServeiData serveiData : procedimentData.getAltes()) {
						String serveiCodi = null;

						try {
							log.info("serveiCodi: " + serveiData.getServei());
							Servei servei = serveiClient.getServei(serveiData.getServei());
							log.info(objectToJsonString(servei));
							serveiCodi = servei.getCodi();

						} catch (Throwable t) {
							// El procediment no existeix a Pinbal. No fem res.
							String msg = "El servei " + serveiData.getServei() + " no existeix a Pinbal.";
							missatges.add(msg);
							log.error(msg);
							continue;
						}
						// Afegim el servei al procediment perque nomes demanaran permisos que a
						// PinbalAdmin estan autoritzats.
						if (procedimentId != null && serveiCodi != null) {
							log.info("ProcemintId: " + procedimentId + " - ServeiCodi: " + serveiCodi);
							try {
								procedimentClient.enableServeiToProcediment(procedimentId, serveiCodi);
								log.info("Servei afegit al procediment.");
								procedimentServeiList.add(new ProcedimentServei(procedimentCodi, serveiCodi));

							} catch (Throwable t) {
								// El procediment no existeix a Pinbal. No fem res.
								String msg = "El servei " + serveiData.getServei()
										+ " no es pot autoritzar per el procediment " + procedimentCodi + ".";
								missatges.add(msg);
								log.error(msg);
							}
						}
					}
				}

				if (procedimentServeiList.isEmpty()) {
					String msg = "No hi ha serveis a afegir. Usuari: " + codiUsuari;
					missatges.add(msg);
					continue;
				}

				PermisosServei permisosServei = new PermisosServei(codiUsuari, ENTITAT_CIF, procedimentServeiList);
				log.info(objectToJsonString(permisosServei));

				usuariClient.grantPermissions(codiUsuari, permisosServei);
				String msg = "Permisos afegits correctament per usuari " + codiUsuari;
				missatges.add(msg);
				log.info(msg);
				log.info(objectToJsonString(permisosServei));

			} catch (Throwable t) {
				String msg = "Error processant permisos per usuari " + usuariData.getUserInfo() + " - "
						+ t.getMessage();
				log.error(msg, t);
				throw new I18NException("genapp.comodi", msg);
			}
		}

		log.info("Actualitzant estat Pinfo i IncidenciaTecnica");

		pinfo.setMissatgePinbal(String.join("\n", missatges));
		pinfo.setEstat(Constants.ESTAT_PINFO_TRAMITAT);
		log.info("Missatge Pinbal: " + pinfo.getMissatgePinbal());
		pinfoLogicaEjb.update(pinfo);
		log.info("Pinfo actualitzat correctament.");

		log.info("Actualitzant estat IncidenciaTecnica associada.");
		IncidenciaTecnica in = incidenciaLogicaEjb.findByPrimaryKey(pinfo.getIncidenciaID());
		in.setEstat(Constants.ESTAT_INCIDENCIA_PINFO_TRAMITAT.intValue());

		log.info("IncidenciaTecnica Missatge Pinbal: " + pinfo.getMissatgePinbal());
		incidenciaLogicaEjb.update(in);
		log.info("IncidenciaTecnica actualitzada correctament.");

		log.info("Permisos solicitats afegits correctament.");
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