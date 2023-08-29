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
	public IEstatTiquetManager getEstatTiquetManager();
	public IEventManager getEventManager();
	public IFitxerManager getFitxerManager();
	public IFormulariManager getFormulariManager();
	public IGrupEntitatManager getGrupEntitatManager();
	public IGrupEntitatCedentManager getGrupEntitatCedentManager();
	public IIdiomaManager getIdiomaManager();
	public IIncidenciaTecnicaManager getIncidenciaTecnicaManager();
	public IOperadorManager getOperadorManager();
	public IOrganManager getOrganManager();
	public IServeiManager getServeiManager();
	public ISolicitudManager getSolicitudManager();
	public ISolicitudServeiManager getSolicitudServeiManager();
	public ITipusTiquetManager getTipusTiquetManager();
	public ITiquetManager getTiquetManager();
	public ITraduccioManager getTraduccioManager();
	public ITramitAPersAutManager getTramitAPersAutManager();
	public ITramitBDadesSoliManager getTramitBDadesSoliManager();
	public ITramitCDadesCesiManager getTramitCDadesCesiManager();
	public ITramitDCteAutManager getTramitDCteAutManager();
	public ITramitECteAudManager getTramitECteAudManager();
	public ITramitFCteTecManager getTramitFCteTecManager();
	public ITramitGDadesTitManager getTramitGDadesTitManager();
	public ITramitHProcManager getTramitHProcManager();
	public ITramitIServManager getTramitIServManager();
	public ITramitJConsentManager getTramitJConsentManager();

}