package org.fundaciobit.pinbaladmin.jpa;

import org.fundaciobit.pinbaladmin.model.*;
import org.fundaciobit.pinbaladmin.model.dao.*;
import javax.persistence.EntityManager;

public final class PinbalAdminJPADaoManagers implements IPinbalAdminDaoManagers{

   private final AreaJPAManager pad_area;
   private final CampFormulariJPAManager pad_campformulari;
   private final CampSolicitudJPAManager pad_campsolicitud;
   private final DepartamentJPAManager pad_departament;
   private final DocumentJPAManager pad_document;
   private final DocumentCedentJPAManager pad_documentcedent;
   private final DocumentEntitatJPAManager pad_documententitat;
   private final DocumentSolicitudJPAManager pad_documentsolicitud;
   private final EmailJPAManager pad_email;
   private final EntitatJPAManager pad_entitat;
   private final EntitatServeiJPAManager pad_entitatservei;
   private final EstatServeiJPAManager pad_estatservei;
   private final EstatSolicitudJPAManager pad_estatsolicitud;
   private final EstatSolicitudServeiJPAManager pad_estatsolicitudservei;
   private final EstatTiquetJPAManager pad_estattiquet;
   private final EventJPAManager pad_event;
   private final FitxerJPAManager pad_fitxer;
   private final FormulariJPAManager pad_formulari;
   private final GrupEntitatJPAManager pad_grupentitat;
   private final GrupEntitatCedentJPAManager pad_grupentitatcedent;
   private final IdiomaJPAManager pad_idioma;
   private final IncidenciaTecnicaJPAManager pad_incidenciatecnica;
   private final ServeiJPAManager pad_servei;
   private final SolicitudJPAManager pad_solicitud;
   private final SolicitudServeiJPAManager pad_solicitudservei;
   private final TipusTiquetJPAManager pad_tipustiquet;
   private final TiquetJPAManager pad_tiquet;
   private final TraduccioJPAManager pad_traduccio;

  public  PinbalAdminJPADaoManagers(EntityManager __em) {
    this.pad_area = new AreaJPAManager(__em);
    this.pad_campformulari = new CampFormulariJPAManager(__em);
    this.pad_campsolicitud = new CampSolicitudJPAManager(__em);
    this.pad_departament = new DepartamentJPAManager(__em);
    this.pad_document = new DocumentJPAManager(__em);
    this.pad_documentcedent = new DocumentCedentJPAManager(__em);
    this.pad_documententitat = new DocumentEntitatJPAManager(__em);
    this.pad_documentsolicitud = new DocumentSolicitudJPAManager(__em);
    this.pad_email = new EmailJPAManager(__em);
    this.pad_entitat = new EntitatJPAManager(__em);
    this.pad_entitatservei = new EntitatServeiJPAManager(__em);
    this.pad_estatservei = new EstatServeiJPAManager(__em);
    this.pad_estatsolicitud = new EstatSolicitudJPAManager(__em);
    this.pad_estatsolicitudservei = new EstatSolicitudServeiJPAManager(__em);
    this.pad_estattiquet = new EstatTiquetJPAManager(__em);
    this.pad_event = new EventJPAManager(__em);
    this.pad_fitxer = new FitxerJPAManager(__em);
    this.pad_formulari = new FormulariJPAManager(__em);
    this.pad_grupentitat = new GrupEntitatJPAManager(__em);
    this.pad_grupentitatcedent = new GrupEntitatCedentJPAManager(__em);
    this.pad_idioma = new IdiomaJPAManager(__em);
    this.pad_incidenciatecnica = new IncidenciaTecnicaJPAManager(__em);
    this.pad_servei = new ServeiJPAManager(__em);
    this.pad_solicitud = new SolicitudJPAManager(__em);
    this.pad_solicitudservei = new SolicitudServeiJPAManager(__em);
    this.pad_tipustiquet = new TipusTiquetJPAManager(__em);
    this.pad_tiquet = new TiquetJPAManager(__em);
    this.pad_traduccio = new TraduccioJPAManager(__em);
  }

	public IAreaManager getAreaManager() {
	  return this.pad_area;
	};

	public ICampFormulariManager getCampFormulariManager() {
	  return this.pad_campformulari;
	};

	public ICampSolicitudManager getCampSolicitudManager() {
	  return this.pad_campsolicitud;
	};

	public IDepartamentManager getDepartamentManager() {
	  return this.pad_departament;
	};

	public IDocumentManager getDocumentManager() {
	  return this.pad_document;
	};

	public IDocumentCedentManager getDocumentCedentManager() {
	  return this.pad_documentcedent;
	};

	public IDocumentEntitatManager getDocumentEntitatManager() {
	  return this.pad_documententitat;
	};

	public IDocumentSolicitudManager getDocumentSolicitudManager() {
	  return this.pad_documentsolicitud;
	};

	public IEmailManager getEmailManager() {
	  return this.pad_email;
	};

	public IEntitatManager getEntitatManager() {
	  return this.pad_entitat;
	};

	public IEntitatServeiManager getEntitatServeiManager() {
	  return this.pad_entitatservei;
	};

	public IEstatServeiManager getEstatServeiManager() {
	  return this.pad_estatservei;
	};

	public IEstatSolicitudManager getEstatSolicitudManager() {
	  return this.pad_estatsolicitud;
	};

	public IEstatSolicitudServeiManager getEstatSolicitudServeiManager() {
	  return this.pad_estatsolicitudservei;
	};

	public IEstatTiquetManager getEstatTiquetManager() {
	  return this.pad_estattiquet;
	};

	public IEventManager getEventManager() {
	  return this.pad_event;
	};

	public IFitxerManager getFitxerManager() {
	  return this.pad_fitxer;
	};

	public IFormulariManager getFormulariManager() {
	  return this.pad_formulari;
	};

	public IGrupEntitatManager getGrupEntitatManager() {
	  return this.pad_grupentitat;
	};

	public IGrupEntitatCedentManager getGrupEntitatCedentManager() {
	  return this.pad_grupentitatcedent;
	};

	public IIdiomaManager getIdiomaManager() {
	  return this.pad_idioma;
	};

	public IIncidenciaTecnicaManager getIncidenciaTecnicaManager() {
	  return this.pad_incidenciatecnica;
	};

	public IServeiManager getServeiManager() {
	  return this.pad_servei;
	};

	public ISolicitudManager getSolicitudManager() {
	  return this.pad_solicitud;
	};

	public ISolicitudServeiManager getSolicitudServeiManager() {
	  return this.pad_solicitudservei;
	};

	public ITipusTiquetManager getTipusTiquetManager() {
	  return this.pad_tipustiquet;
	};

	public ITiquetManager getTiquetManager() {
	  return this.pad_tiquet;
	};

	public ITraduccioManager getTraduccioManager() {
	  return this.pad_traduccio;
	};


}