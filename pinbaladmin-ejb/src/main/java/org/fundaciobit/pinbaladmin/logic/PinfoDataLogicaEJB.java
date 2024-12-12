package org.fundaciobit.pinbaladmin.logic;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.pinbaladmin.ejb.PinfoDataEJB;
import org.fundaciobit.pinbaladmin.model.entity.PinfoData;
import org.fundaciobit.pinbaladmin.model.fields.PinfoDataFields;
import org.fundaciobit.pinbaladmin.persistence.PinfoDataJPA;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pluginsib.core.v3.IPluginIB;
import org.fundaciobit.pluginsib.core.v3.utils.PluginsManager;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;

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
		
		log.info("getEstructuraUsuarisProcedimentServeis per PINFO" + pinfoID);
		
		OrderBy orderByServ = new OrderBy(PinfoDataFields.SERVEIID, OrderType.ASC);
		OrderBy orderByProc = new OrderBy(PinfoDataFields.PROCEDIMENTID, OrderType.ASC);
		OrderBy orderByUser = new OrderBy(PinfoDataFields.USUARIID, OrderType.ASC);

		OrderBy[] orderBy = { orderByUser, orderByProc, orderByServ };
		List<PinfoData> llista = this.select(PinfoDataFields.PINFOID.equal(pinfoID), orderBy);

		String lastUsuariID = null;
		Long lastProcedimentID = null;

		List<UsuariData> usuarisList = new ArrayList<UsuariData>();

		List<ProcedimentData> procedimentsList;
		List<ServeiData> serveisList;

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
				UsuariData usuariData = new UsuariData(usuariID, new ArrayList<ProcedimentData>());
				procedimentsList = usuariData.getProcediments();
				lastUsuariData = usuariData;
				lastUsuariID = usuariID;
				usuarisList.add(usuariData);
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
						new ArrayList<ServeiData>());
				serveisList = procedimentData.getServeis();
				lastProcedimentData = procedimentData;
				lastProcedimentID = procedimentID;
				procedimentsList.add(procedimentData);
			} else {
//				log.info("Procediment " + procedimentID + " ja existent, afegirem al seu serveiList.");
				serveisList = lastProcedimentData.getServeis();
			}

			ServeiJPA servei = serveiLogicaEjb.findByPrimaryKey(serveiID);
			ServeiData serveiData = new ServeiData(serveiID, servei.getCodi(), pinfoData.getPinfodataID(), pinfoData.getAlta());
			serveisList.add(serveiData);
		}

		PinfoDataFull pinfoDataFull = new PinfoDataFull(pinfoID, usuarisList);
		printPinfoDataFull(pinfoDataFull);

		log.info(llista.size() + " registres de PinfoData per PINFO" + pinfoID);
		return pinfoDataFull;

	}
	

	private void printPinfoDataFull(PinfoDataFull pinfoDataFull) {
		log.info("PinfoDataFull: " + pinfoDataFull.getPinfoID());
		for (UsuariData usuariData : pinfoDataFull.getUsuaris()) {
			log.info("Usuari: " + usuariData.getUsuariID());
			for (ProcedimentData procedimentData : usuariData.getProcediments()) {
				log.info("\tProcediment: " + procedimentData.getProcedimentID());
				for (ServeiData serveiData : procedimentData.getServeis()) {
					log.info("\t\tServei: " + serveiData.getServeiID() + " - " + serveiData.getServei());
				}
			}
		}
	}

	

	public class ServeiData {
		private Long serveiID;
		private String servei;
		private Long pinfoDataID;
		private Long alta;
		

		public ServeiData(Long serveiID, String servei, Long pinfoDataID, Long alta) {
			this.serveiID = serveiID;
			this.servei = servei;
			this.pinfoDataID = pinfoDataID;
			this.alta = alta;
		}

		public Long getServeiID() {
			return this.serveiID;
		}

		public String getServei() {
			return this.servei;
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
		private List<ServeiData> serveis = new ArrayList<ServeiData>();

		public ProcedimentData(Long procedimentID, String procediment, String codi,  List<ServeiData> serveis) {
			this.procedimentID = procedimentID;
			this.procediment = procediment;
			this.codi = codi;
			this.serveis = serveis;
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
		
		public List<ServeiData> getServeis() {
			return this.serveis;
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
		
		public void setServeis(List<ServeiData> serveis) {
			this.serveis = serveis;
		}
	}

	public class UsuariData {
		private String usuariID;
		private List<ProcedimentData> procediments = new ArrayList<ProcedimentData>();

		public UsuariData(String usuariID, List<ProcedimentData> procediments) {
			this.usuariID = usuariID;
			this.procediments = procediments;
		}

		public String getUsuariID() {
			return this.usuariID;
		}

		public List<ProcedimentData> getProcediments() {
			return this.procediments;
		}

		public void setUsuariID(String usuariID) {
			this.usuariID = usuariID;
		}

		public void setProcediments(List<ProcedimentData> procediments) {
			this.procediments = procediments;
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

	
	@Override
	public IEstructuraOrganitzativaPlugin getPluginEstructuraOrganitzativa() throws I18NException{

		IPluginIB pluginInstance = null;

		String clase = "org.fundaciobit.pluginsib.estructuraorganitzativa.ldapcaib.LdapCaibEstructuraOrganitzativaPlugin";
		String propertyBase = Constants.PINBALADMIN_PROPERTY_BASE;
		Properties prop = new Properties();
		String propertiesString = "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.users_context_dn=dc\\=caib,dc\\=es\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.host_url=ldap\\://spreauthlin1.caib.es\\:389\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.security_authentication=simple\r\n"
				+ "\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.search_scope=subtree\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.security_principal=cn=lectorenviafib,dc=caib,dc=es\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.security_credentials=fib$2803\r\n"
				+ "\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.username=cn\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.mail=mail\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.administration_id=nif\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.name=givenName\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.surname=sn\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.surname1=sn1\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.surname2=sn2\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.telephone=\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.department=departmentNumber\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.attribute.memberof=memberOf\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.prefix_role_match_memberof=cn=\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.ldap.suffix_role_match_memberof=,dc=caib,dc=es\r\n"
				+ "\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.dir3host=https://se.caib.es/dir3caib/rest\r\n"
				+ "\r\n"
				+ "# S'ha de definir una de les dues propietats: o mappingdir3consellerusername o rolcaparea\r\n"
				+ "#org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.mappingdir3consellerusername=D:/dades/dades/CarpetesPersonals/ProjecteBase/jboss7/standalone/deploy_enviafib/mappingdir3consellerusername.properties\r\n"
				+ "#org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.rolcaparea=IBK_CONSELLER\r\n"
				+ "\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.rolcapdepartament=EFI_DIRECTOR\r\n"
				+ "#IBK_DIRECTOR\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.rolsecretari=EFI_SECRETARI\r\n"
				+ "#DIS_IBSALUT_RRHH\r\n"
				+ "\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.gerentpresident.nom=Margalida Prohens Rigo\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.gerentpresident.username=e78213313l\r\n"
				+ "\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.organitzacio.nom=Govern de les Illes Balears\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.organitzacio.dir3=A04003003\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.organitzacio.nif=S0711001H\r\n"
				+ "\r\n"
				+ "# S'ha de definir una de les dues propietats: o conselleria.dir o rolcaparea\r\n"
				+ "#org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.rolcaparea=IBK_CONSELLER\r\n"
				+ "\r\n"
				+ "#Mapeig de DIR3 de Conselleries i Consellers\r\n"
				+ "\r\n"
				+ "# Conselleria de Turisme, Cultura i esports\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026906=e18219772t\r\n"
				+ "\r\n"
				+ "#Conselleria d'Economia, Hisenda i Innovació\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026911=u99853\r\n"
				+ "\r\n"
				+ "#Conselleria de Presidència i Administracions Públiques\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04027007=u03439\r\n"
				+ "\r\n"
				+ "#Conselleria d'Empresa, Ocupació i Energia\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A040043816=e25386104s\r\n"
				+ "\r\n"
				+ "#Conselleria de Salut\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026919=u100793\r\n"
				+ "\r\n"
				+ "#Conselleria d'Educació i Universitats\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026923=u05679\r\n"
				+ "\r\n"
				+ "#Conselleria d'Habitatge, Territori i Mobilitat\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026937=u105606\r\n"
				+ "\r\n"
				+ "#Conselleria de Famílies i Afers Socials\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026929=u151993\r\n"
				+ "\r\n"
				+ "#Conselleria de la Mar i del Cicle de l'Aigua\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026953=u06160\r\n"
				+ "\r\n"
				+ "#Conselleria d'Agricultura, Pesca i Medi Natural\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.conselleria.A04026949=u100030\r\n"
				+ "\r\n"
				+ "#Mapeig dels grups que no tenen DIR3 associat a grups que si el tenen\r\n"
				+ "org.fundaciobit.pinbaladmin.pluginsib.estructuraorganitzativa.ldapcaib.group.externs=dgtic";
		

        try {

            // Exemple:
            // [=SP["es.caib.digitalib.plugins.signatureserver.afirmaserver.authorization.password"]]

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("SP", Configuracio.getSystemAndFileProperties());

            String plantilla = propertiesString;
            String generat = TemplateEngine.processExpressionLanguageSquareBrackets(plantilla, map,
                    new Locale("ca"));

            // final String generat = plantilla;
            // log.error("PROPIETATS DESPRES DE generat:\n" + generat + "\n");

            prop.load(new StringReader(generat));

        } catch (Exception e) {
            throw new I18NException(e, "genapp.comodi", new I18NArgumentString(
                    "Error desconegut processant propietats del plugin d'estructura organitzativa: " + e.getMessage()));
        }
		
		
		pluginInstance = (IPluginIB) PluginsManager.instancePluginByClassName(clase, propertyBase, prop);

		return (IEstructuraOrganitzativaPlugin) pluginInstance;
	}
	
	
	
	
}