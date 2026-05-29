package org.fundaciobit.pinbaladmin.logic.utils;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.StringKeyValue;

@Local
public interface GenerarDocumentsDGLogicaService {

	public static final String JNDI_NAME = "java:app/pinbaladmin-ejb/GenerarDocumentsDGLogicaEJB!org.fundaciobit.pinbaladmin.logic.utils.GenerarDocumentsDGLogicaService";

	/**
	 * Genera el formulari del Director General en format PDF i ODT
	 * @param solicitudID ID de la sol·licitud
	 * @throws Exception
	 */
	public void generarFormulariDirectorGeneralPDFODT(Long solicitudID) throws Exception;
	
}
