package org.fundaciobit.pinbaladmin.model;

import org.fundaciobit.pinbaladmin.model.dao.*;

public interface IPinbalAdminDaoManagers {
	public IAreaManager getAreaManager();
	public ICampFormulariManager getCampFormulariManager();
	public ICampSolicitudManager getCampSolicitudManager();
	public IDepartamentManager getDepartamentManager();
	public IDocumentManager getDocumentManager();
	public IDocumentCedentManager getDocumentCedentManager();
	public IDocumentEntitatManager getDocumentEntitatManager();
	public IDocumentSolicitudManager getDocumentSolicitudManager();
	public IEmailManager getEmailManager();
	public IEntitatManager getEntitatManager();
	public IEntitatServeiManager getEntitatServeiManager();
	public IEstatServeiManager getEstatServeiManager();
	public IEstatSolicitudManager getEstatSolicitudManager();
	public IEstatSolicitudServeiManager getEstatSolicitudServeiManager();
	public IEstatTiquetManager getEstatTiquetManager();
	public IFitxerManager getFitxerManager();
	public IFormulariManager getFormulariManager();
	public IGrupEntitatManager getGrupEntitatManager();
	public IGrupEntitatCedentManager getGrupEntitatCedentManager();
	public IIdiomaManager getIdiomaManager();
	public IServeiManager getServeiManager();
	public ISolicitudManager getSolicitudManager();
	public ISolicitudServeiManager getSolicitudServeiManager();
	public ITipusTiquetManager getTipusTiquetManager();
	public ITiquetManager getTiquetManager();
	public ITraduccioManager getTraduccioManager();

}