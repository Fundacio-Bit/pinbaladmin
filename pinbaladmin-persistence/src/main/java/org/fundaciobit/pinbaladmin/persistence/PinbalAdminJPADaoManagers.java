package org.fundaciobit.pinbaladmin.persistence;

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
   private final EstatTiquetJPAManager pad_estattiquet;
   private final EventJPAManager pad_event;
   private final FitxerJPAManager pad_fitxer;
   private final FormulariJPAManager pad_formulari;
   private final GrupEntitatJPAManager pad_grupentitat;
   private final GrupEntitatCedentJPAManager pad_grupentitatcedent;
   private final IdiomaJPAManager pad_idioma;
   private final IncidenciaTecnicaJPAManager pad_incidenciatecnica;
   private final OperadorJPAManager pad_operador;
   private final OrganJPAManager pad_organ;
   private final ServeiJPAManager pad_servei;
   private final SolicitudJPAManager pad_solicitud;
   private final SolicitudServeiJPAManager pad_solicitudservei;
   private final TipusTiquetJPAManager pad_tipustiquet;
   private final TiquetJPAManager pad_tiquet;
   private final TraduccioJPAManager pad_traduccio;
   private final TramitAPersAutJPAManager pad_tramit_a_pers_aut;
   private final TramitBDadesSoliJPAManager pad_tramit_b_dades_soli;
   private final TramitCDadesCesiJPAManager pad_tramit_c_dades_cesi;
   private final TramitDCteAutJPAManager pad_tramit_d_cte_aut;
   private final TramitECteAudJPAManager pad_tramit_e_cte_aud;
   private final TramitFCteTecJPAManager pad_tramit_f_cte_tec;
   private final TramitGDadesTitJPAManager pad_tramit_g_dades_tit;
   private final TramitHProcJPAManager pad_tramit_h_proc;
   private final TramitIServJPAManager pad_tramit_i_serv;
   private final TramitJConsentJPAManager pad_tramit_j_consent;

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
    this.pad_estattiquet = new EstatTiquetJPAManager(__em);
    this.pad_event = new EventJPAManager(__em);
    this.pad_fitxer = new FitxerJPAManager(__em);
    this.pad_formulari = new FormulariJPAManager(__em);
    this.pad_grupentitat = new GrupEntitatJPAManager(__em);
    this.pad_grupentitatcedent = new GrupEntitatCedentJPAManager(__em);
    this.pad_idioma = new IdiomaJPAManager(__em);
    this.pad_incidenciatecnica = new IncidenciaTecnicaJPAManager(__em);
    this.pad_operador = new OperadorJPAManager(__em);
    this.pad_organ = new OrganJPAManager(__em);
    this.pad_servei = new ServeiJPAManager(__em);
    this.pad_solicitud = new SolicitudJPAManager(__em);
    this.pad_solicitudservei = new SolicitudServeiJPAManager(__em);
    this.pad_tipustiquet = new TipusTiquetJPAManager(__em);
    this.pad_tiquet = new TiquetJPAManager(__em);
    this.pad_traduccio = new TraduccioJPAManager(__em);
    this.pad_tramit_a_pers_aut = new TramitAPersAutJPAManager(__em);
    this.pad_tramit_b_dades_soli = new TramitBDadesSoliJPAManager(__em);
    this.pad_tramit_c_dades_cesi = new TramitCDadesCesiJPAManager(__em);
    this.pad_tramit_d_cte_aut = new TramitDCteAutJPAManager(__em);
    this.pad_tramit_e_cte_aud = new TramitECteAudJPAManager(__em);
    this.pad_tramit_f_cte_tec = new TramitFCteTecJPAManager(__em);
    this.pad_tramit_g_dades_tit = new TramitGDadesTitJPAManager(__em);
    this.pad_tramit_h_proc = new TramitHProcJPAManager(__em);
    this.pad_tramit_i_serv = new TramitIServJPAManager(__em);
    this.pad_tramit_j_consent = new TramitJConsentJPAManager(__em);
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

    public IOperadorManager getOperadorManager() {
        return this.pad_operador;
    };

    public IOrganManager getOrganManager() {
        return this.pad_organ;
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

    public ITramitAPersAutManager getTramitAPersAutManager() {
        return this.pad_tramit_a_pers_aut;
    };

    public ITramitBDadesSoliManager getTramitBDadesSoliManager() {
        return this.pad_tramit_b_dades_soli;
    };

    public ITramitCDadesCesiManager getTramitCDadesCesiManager() {
        return this.pad_tramit_c_dades_cesi;
    };

    public ITramitDCteAutManager getTramitDCteAutManager() {
        return this.pad_tramit_d_cte_aut;
    };

    public ITramitECteAudManager getTramitECteAudManager() {
        return this.pad_tramit_e_cte_aud;
    };

    public ITramitFCteTecManager getTramitFCteTecManager() {
        return this.pad_tramit_f_cte_tec;
    };

    public ITramitGDadesTitManager getTramitGDadesTitManager() {
        return this.pad_tramit_g_dades_tit;
    };

    public ITramitHProcManager getTramitHProcManager() {
        return this.pad_tramit_h_proc;
    };

    public ITramitIServManager getTramitIServManager() {
        return this.pad_tramit_i_serv;
    };

    public ITramitJConsentManager getTramitJConsentManager() {
        return this.pad_tramit_j_consent;
    };


}