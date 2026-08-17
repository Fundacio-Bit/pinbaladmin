package org.fundaciobit.pinbaladmin.logic.utils;


import javax.ejb.Local;


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
