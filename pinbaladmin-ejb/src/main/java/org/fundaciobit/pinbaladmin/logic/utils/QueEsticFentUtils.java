package org.fundaciobit.pinbaladmin.logic.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.queesticfent.apiexterna.client.api.ModificacionsApi;
import org.fundaciobit.queesticfent.apiexterna.client.model.AddModificacioRequest;
import org.fundaciobit.queesticfent.apiexterna.client.model.GetModificacionsResponse;
import org.fundaciobit.queesticfent.apiexterna.client.model.ModificacioRest;
import org.fundaciobit.queesticfent.apiexterna.client.services.ApiClient;
import org.fundaciobit.queesticfent.apiexterna.client.services.ApiException;
import org.fundaciobit.queesticfent.apiexterna.client.services.auth.HttpBasicAuth;

public class QueEsticFentUtils {

	final static Logger log = Logger.getLogger(EmailUtil.class);

	final static Long projectePINBAL = 1021L; // PINBAL
	public static Long afegirModificacioQEF(String usuari, String dada, OffsetDateTime data) throws I18NException {

		try {

			final ModificacionsApi api = getApi();

			AddModificacioRequest modificacio = new AddModificacioRequest();

			String usuariID = usuari;
			String language = "ca";

			modificacio.setUsuariID(usuariID);
			modificacio.setProjecteID(projectePINBAL);
			modificacio.setData(data);
			modificacio.setDada1(dada);
			modificacio.setLanguage(language);

			Long response = api.add(modificacio);
			return response;
		} catch (ApiException e) {
			String msg = "Error API al afegir entrada: " + e.getMessage();
			log.error(msg, e);
            throw new I18NException("genapp.comodi", msg);
		}

	}

	public static List<String> getModificacionsQEF(String username, String dateStr) throws I18NException {
		try {
			final ModificacionsApi api = getApi();
			GetModificacionsResponse responseApi = api.getmodificacions(username, dateStr, "ca");
			List<ModificacioRest> modificaciones = responseApi.getModificacions();

			List<String> dadesEvents = new java.util.ArrayList<String>();

			for (ModificacioRest mod : modificaciones) {
				String dada = mod.getDada1();
				if (mod.getProjecteID() == 1021 && dada.contains(":")) {
					dadesEvents.add(dada);
				}
			}
			return dadesEvents;
		} catch (ApiException e) {
			String msg = "Error API al obtenir les modificacions: " + e.getMessage();
			log.error(msg, e);
			throw new I18NException("genapp.comodi", msg);
		}
	}

	private static ModificacionsApi getApi() {

		String username = Configuracio.getQueEsticFentUser();
		String password = Configuracio.getQueEsticFentPassword();
		String host = Configuracio.getQueEsticFentBDURL();

		log.info("host: " + host + ", username: " + username + ", password " + password);

		ApiClient client = new ApiClient();
		client.setBasePath(host);
		client.setUsername(username);
		client.setPassword(password);

		HttpBasicAuth basicAuth = (HttpBasicAuth) client.getAuthentication("BasicAuth");
		basicAuth.setUsername(username);
		basicAuth.setPassword(password);

		ModificacionsApi api = new ModificacionsApi(client);
		log.info("api : " + api);
		return api;
	}
}
