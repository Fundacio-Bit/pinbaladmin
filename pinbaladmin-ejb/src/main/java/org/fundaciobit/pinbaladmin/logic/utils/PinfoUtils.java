package org.fundaciobit.pinbaladmin.logic.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.PinbalClientConnection;

import es.caib.pinbal.client.comu.LogLevel;
import es.caib.pinbal.client.procediments.Procediment;
import es.caib.pinbal.client.procediments.ProcedimentClient;
import es.caib.pinbal.client.recobriment.v2.ClientRecobriment;
import es.caib.pinbal.client.serveis.ServeiBasic;
import es.caib.pinbal.client.serveis.ServeiClient;
import es.caib.pinbal.client.usuaris.UsuariClient;

public final class PinfoUtils {

	private PinfoUtils() {
	}

	public static List<ServeiBasic> getServeisDelProcediment(String codiProc, PinbalContext pinbal)
			throws I18NException {
		String entitatCif = pinbal.getEntitatCif();
		try {

			String key = entitatCif + "-" + codiProc;
			System.out.println("Buscant serveis del procediment " + codiProc + " de l'entitat " + entitatCif + " a la cache");
			
			if (pinbal.getCacheServeisProcedimentsEntitat().containsKey(key)) {
				System.out.println("Serveis del procediment " + codiProc + " de l'entitat " + entitatCif + " trobats a la cache");
				return pinbal.getCacheServeisProcedimentsEntitat().get(key);
			}
			
			System.out.println("Serveis del procediment " + codiProc + " de l'entitat " + entitatCif + " NO trobats a la cache. Obtenint de Pinbal...");
			List<ServeiBasic> serveis = pinbal.getClientRecobriment().getServeisPerProcediment(entitatCif, codiProc);
			
			System.out.println("Serveis del procediment " + codiProc + " de l'entitat " + entitatCif + " obtinguts de Pinbal. Afegint a la cache...");
			pinbal.afegirServeisProcEntitat(codiProc, entitatCif, serveis);
			
			return serveis;

		} catch (IOException e) {
			throw new I18NException(e, "Error obtenint serveis del procediment " + codiProc + " de l'entitat "
					+ entitatCif + " des de Pinbal");
		}
	}
	
	
	
	public static class ProcessamentContext {

		private final StringBuilder logTecnic = new StringBuilder();
		private final StringBuilder logTramitador = new StringBuilder();

		public void logTecnic(String msg) {
		    logTecnic.append(msg).append("\n");
		}

		public void logTramitador(String msg) {
		    logTramitador.append(msg).append("\n");
		}

		public String getLogTecnic() {
		    return logTecnic.toString();
		}

		public String getLogTramitador() {
		    return logTramitador.toString();
		}
		private int totalUsuaris;
		private int usuarisProcessatsOK;

		private int totalPermisosConcedits;

		private int totalPinfodatasProcessats;
		private int pinfodatasOK;
		private int pinfodatasError;

		private final Set<String> procedimentsNoExisteixen = new LinkedHashSet<>();

		private final Set<String> serveisNoExisteixen = new LinkedHashSet<>();

		private final Map<String, Set<String>> serveisNoAutoritzats = new LinkedHashMap<>();

		private final Map<String, Set<String>> serveisNoAutoritzables = new LinkedHashMap<>();
		
		private List<Long> pinfoDatasOk = new java.util.ArrayList<>();
		private List<Long> pinfoDatasError = new java.util.ArrayList<>();
		
		private int autoritzacionsTramitades;
		private int autoritzacionsError;


		public int getAutoritzacionsTramitades() {
			return autoritzacionsTramitades;
		}
		
		public int getAutoritzacionsError() {
			return autoritzacionsError;
		}
		
		public void incPinfodataProcessat() {
			totalPinfodatasProcessats++;
		}
		
		public void incPinfodataOK() {
			pinfodatasOK++;
		}
		
		public void incPinfodataError() {
			pinfodatasError++;
		}
		
		public void addProcedimentNoExisteix(String codiProc) {
			procedimentsNoExisteixen.add(codiProc);
		}
		
		public void addServeiNoExisteix(String codiServei) {
			serveisNoExisteixen.add(codiServei);
		}
		
		public Map<String, Set<String>> getServeisNoAutoritzables() {
			return serveisNoAutoritzables;
		}
		
		public void incAutoritzacio() {
		    autoritzacionsTramitades++;
		}

		public void incError() {
		    autoritzacionsError++;
		}
		

		public int getTotalUsuaris() {
			return totalUsuaris;
		}

		public void setTotalUsuaris(int totalUsuaris) {
			this.totalUsuaris = totalUsuaris;
		}

		public int getUsuarisProcessatsOK() {
			return usuarisProcessatsOK;
		}

		public void incrementarUsuarisOK() {
			this.usuarisProcessatsOK++;
		}

		public int getTotalPermisosConcedits() {
			return totalPermisosConcedits;
		}

		public void sumarPermisos(int quantitat) {
			this.totalPermisosConcedits += quantitat;
		}

		public int getTotalPinfodatasProcessats() {
			return totalPinfodatasProcessats;
		}

		public void incrementarPinfodatasProcessats() {
			this.totalPinfodatasProcessats++;
		}

		public int getPinfodatasOK() {
			return pinfodatasOK;
		}

		public void incrementarOK() {
			this.pinfodatasOK++;
		}

		public int getPinfodatasError() {
			return pinfodatasError;
		}

		public void incrementarError() {
			this.pinfodatasError++;
		}

		public Set<String> getProcedimentsNoExisteixen() {
			return procedimentsNoExisteixen;
		}

		public Set<String> getServeisNoExisteixen() {
			return serveisNoExisteixen;
		}

		public Map<String, Set<String>> getServeisNoAutoritzats() {
			return serveisNoAutoritzats;
		}
		
		public List<Long> getPinfoDatasOk() {
			return pinfoDatasOk;
		}
		
		public List<Long> getPinfoDatasError() {
			return pinfoDatasError;
		}
		
		public void addPinfoDataOk(Long pinfoDataId) {
			pinfoDatasOk.add(pinfoDataId);
		}
		
		public void addPinfoDataError(Long pinfoDataId) {
			pinfoDatasError.add(pinfoDataId);
		}
	}

	public static class ResultatProcessament {

		private String logDetallat;
		private String missatgeTramitador;

		public String getLogDetallat() {
			return logDetallat;
		}

		public void setLogDetallat(String logDetallat) {
			this.logDetallat = logDetallat;
		}

		public String getMissatgeTramitador() {
			return missatgeTramitador;
		}

		public void setMissatgeTramitador(String missatgeTramitador) {
			this.missatgeTramitador = missatgeTramitador;
		}
	}

	public static class PinbalContext {
		
		private String entitatCif;

		private final ServeiClient serveiClient;
		private final UsuariClient usuariClient;
		private final ProcedimentClient procedimentClient;
		private final ClientRecobriment clientRecobriment;

		private final Map<String, Procediment> cacheProcediments = new HashMap<>();
		private final Map<String, List<ServeiBasic>> cacheServeisProcedimentsEntitat = new HashMap<>();

		private final Map<String, Boolean> cacheHabilitacions = new HashMap<>();

		private List<ServeiBasic> serveisPinbalEntitat;

		public PinbalContext(String entitatCif) throws I18NException {

			this.entitatCif = entitatCif;
			
			PinbalClientConnection c = PinbalClientConnection.getDefaultConnection();
			final LogLevel logLevel = LogLevel.INFO;

			ServeiClient serveiClient = new ServeiClient(c.baseUrl, c.username, c.password, logLevel);
			UsuariClient usuariClient = new UsuariClient(c.baseUrl, c.username, c.password, logLevel);
			ProcedimentClient procedimentClient = new ProcedimentClient(c.baseUrl, c.username, c.password, logLevel);
			ClientRecobriment clientRecobriment = new ClientRecobriment(c.baseUrl, c.username, c.password, logLevel);

			this.serveiClient = serveiClient;
			this.usuariClient = usuariClient;
			this.procedimentClient = procedimentClient;
			this.clientRecobriment = clientRecobriment;

			List<ServeiBasic> serveisPinbalEntitat = null;
			try {
				serveisPinbalEntitat = clientRecobriment.getServeisPerEntitat(entitatCif);
				this.serveisPinbalEntitat = serveisPinbalEntitat;
			} catch (IOException e) {
				throw new I18NException(e, "Error obtenint serveis de l'entitat " + entitatCif + " des de Pinbal");
			}
		}

		public String getEntitatCif() {
			return entitatCif;
		}
		
		public void setEntitatCif(String entitatCif) {
			this.entitatCif = entitatCif;
		}
		
		public ServeiClient getServeiClient() {
			return serveiClient;
		}

		public UsuariClient getUsuariClient() {
			return usuariClient;
		}

		public ProcedimentClient getProcedimentClient() {
			return procedimentClient;
		}

		public ClientRecobriment getClientRecobriment() {
			return clientRecobriment;
		}

		public Map<String, Procediment> getCacheProcediments() {
			return cacheProcediments;
		}
		
		public Map<String, List<ServeiBasic>> getCacheServeisProcedimentsEntitat() {
			return cacheServeisProcedimentsEntitat;
		}
		
		public List<ServeiBasic> getServeisProcediment(String codiProc, String entitatCif) {
			return cacheServeisProcedimentsEntitat.get(entitatCif + "-" + codiProc);
		}
		
		public void afegirServeisProcEntitat(String codiProc, String entitatCif, List<ServeiBasic> serveis) {
			cacheServeisProcedimentsEntitat.put(entitatCif + "-" + codiProc, serveis);
		}

		public Map<String, Boolean> getCacheHabilitacions() {
			return cacheHabilitacions;
		}

		public List<ServeiBasic> getServeisPinbalEntitat() {
			return serveisPinbalEntitat;
		}

		public void setServeisPinbalEntitat(List<ServeiBasic> serveisPinbalEntitat) {
			this.serveisPinbalEntitat = serveisPinbalEntitat;
		}
	}
}