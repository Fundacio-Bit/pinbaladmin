package org.fundaciobit.pinbaladmin.logic.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.jersey.ApiFirmaAsyncSimpleJersey;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.jersey.ApiFlowTemplateSimpleJersey;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.FitxerService;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;

public class PortafibUtils {

	public static ApiFirmaAsyncSimple getApiFirmaAsyncSimple() throws I18NException {

		String host = Configuracio.getPortafibGatewayV2();
		String username = Configuracio.getPortafibUsername();
		String password = Configuracio.getPortafibPassword();

		ApiFirmaAsyncSimpleJersey api;

		try {
			new URL(host);
			api = new ApiFirmaAsyncSimpleJersey(host, username, password);

		} catch (MalformedURLException urle) {
			String errorMsg = "Error a la URL de conexió amb PortaFIB. Revisar la URL de la propietat "
					+ Constants.PINBALADMIN_PROPERTY_BASE + "portafib.apifirmaasync.url" + " de l'arxiu: "
					+ Constants.PINBALADMIN_PROPERTY_BASE + "system.properties.";

			throw new I18NException(errorMsg + "   -   " + urle.getMessage());
		} catch (Exception e) {
			throw new I18NException("error.portafib.conexio.api",
					Constants.PINBALADMIN_PROPERTY_BASE + "system.properties.", e.getMessage());
		}

		// api.setConnectionTimeoutMs(20000); // 20 segons
		// api.setReadTimeoutMs(20000); // 20 segons

		return api;
	}


	public static ApiFlowTemplateSimple getApiFlowTemplateSimple() {

		String url = Configuracio.getPortaFIBApiFlowUrl();
		String username = Configuracio.getPortaFIBApiFlowUsername();
		String password = Configuracio.getPortaFIBApiFlowPassword();
		// log.info(" Connectant amb " + url + " emprant l'usuari " + username);

		return new ApiFlowTemplateSimpleJersey(url, username, password);

	}

	public static FirmaAsyncSimpleSignedFile getFitxerSignat(long portafibID) throws I18NException {
		String languageUI = "ca";

		FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
		rinfo = new FirmaAsyncSimpleSignatureRequestInfo(portafibID, languageUI);

		ApiFirmaAsyncSimple api;
		FirmaAsyncSimpleSignedFile fitxerSignat = null;
		try {
			api = getApiFirmaAsyncSimple();
			fitxerSignat = api.getSignedFileOfSignatureRequest(rinfo);
		} catch (Throwable t) {
			throw new I18NException("error.portafib.fitxersignat", String.valueOf(portafibID), t.getMessage());
		}

		return fitxerSignat;
	}
	

	public static long guardarFitxer(FirmaAsyncSimpleSignedFile firma, FitxerService fitxerEjb) throws I18NException {
		
		// Guarda fitxer signat a FileSystemManager i a la BD. Retorna el ID del fitxer
		String nom = firma.getSignedFile().getNom();
		String mime = firma.getSignedFile().getMime();
		byte[] data = firma.getSignedFile().getData();

		Fitxer fdb = fitxerEjb.create(nom, data.length, mime, null);

		Long fitxerID = fdb.getFitxerID();

		try {
			File fitxersignat = FileSystemManager.getFile(fitxerID);
			FileOutputStream fos = new FileOutputStream(fitxersignat);
			fos.write(data);
			fos.flush();
			fos.close();

		} catch (Throwable t) {
			throw new I18NException("error.fitxer.guardar.fsm", String.valueOf(fitxerID), t.getMessage());
		}

		return fitxerID;
	}
}
