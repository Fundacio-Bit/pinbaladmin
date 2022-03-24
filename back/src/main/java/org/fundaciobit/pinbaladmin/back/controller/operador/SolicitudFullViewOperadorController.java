package org.fundaciobit.pinbaladmin.back.controller.operador;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Base64;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.all.CallbackSeleniumController;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.jpa.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.jpa.FitxerJPA;
import org.fundaciobit.pinbaladmin.jpa.SolicitudJPA;
import org.fundaciobit.pinbaladmin.jpa.SolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.utils.Configuracio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import fr.opensagres.odfdom.converter.core.utils.ByteArrayOutputStream;

/**
 * 
 * @author anadal
 * @author areus
 */
@Controller
@RequestMapping(value = "/operador/solicitudfullview")
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudFullViewOperadorController extends SolicitudOperadorController {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudLocal documentSolicitudEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentLocal documentEjb;

  @Override
  public String getTileForm() {
    return "solicitudListWebDB_FullView_operador";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "SolicitudWebDB_FilterForm_Operador_FullView";
  }

  private static final String SESSIO_SOLICITUD_REFERER = "SESSIO_SOLICITUD_REFERER_";

  private static String getSessioSolicitudRefererWithId(Long id) {
    return SESSIO_SOLICITUD_REFERER + id;
  }

  @RequestMapping(value = "/viewsessio", method = RequestMethod.GET)
  public ModelAndView veureSolicitudGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    Long solicitudID = (Long) request.getSession()
        .getAttribute(SolicitudDocumentOperadorController.SESSIO_SOLIID_MANAGE_DOCUMENTS);

    if (solicitudID == null) {
      return new ModelAndView(new RedirectView("list", true));
    } else {
      return editAndViewSolicitudGet(solicitudID, request, response, true);
    }
  }

  @Override
  public SolicitudForm getSolicitudForm(SolicitudJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    SolicitudForm solicitudForm = super.getSolicitudForm(_jpa, __isView, request, mav);

    if (__isView) {
      // Canviam el cancel·lar per un tornar.....
      solicitudForm.setCancelButtonVisible(false);
      Long soliID = +solicitudForm.getSolicitud().getSolicitudID();
      String urlTornar = "/operador/solicitudfullview/" + soliID + " /cancel";
      solicitudForm.addAdditionalButton(
          new AdditionalButton("icon-arrow-left", "tornar", urlTornar, "btn-info"));

      solicitudForm.addAdditionalButton(new AdditionalButton(" icon-pencil", "solicitud.edit",
          "/operador/solicitud" + (_jpa.getDepartamentID() == null ? "estatal" : "local") + "/"
              + soliID + "/edit",
          ""));

      if (solicitudForm.getSolicitud().getEntitatEstatal() == null) {
        solicitudForm.addAdditionalButton(
            new AdditionalButton("icon-repeat", "solicitud.generarformularidirectorgeneral",
                getContextWeb() + "/generarformularidirectorgeneral/" + soliID, ""));

        if (solicitudForm.getSolicitud().getTicketNumeroSeguiment() == null) {
          solicitudForm.addAdditionalButton(new AdditionalButton(" icon-envelope",
              "solicitud.caid", getContextWeb() + "/formularicaidfitxers/"
                  + solicitudForm.getSolicitud().getSolicitudID(),
              ""));
        } else {

          String url = Configuracio.getCAIDSeleniumUrl() + "/RemoteSeleniumConsulta?"
              + "email=gd.pinbal@fundaciobit.org" + "&incidencia="
              + solicitudForm.getSolicitud().getTicketAssociat() + "&seguimiento="
              + solicitudForm.getSolicitud().getTicketNumeroSeguiment();

          solicitudForm.addAdditionalButton(new AdditionalButton(" icon-envelope",
              "consulta.caid", "javascript:window.open('" + url + "', '_blank')", ""));
        }

      }

      //

      /*
       * solicitudForm.addAdditionalButton(new AdditionalButton( "icon-play",
       * "solicitud.generarfitxes", getContextWeb() +
       * "/generarserveis/"+solicitudForm.getSolicitud().getSolicitudID() ,
       * ""));
       */
    }

    HttpSession sessio = request.getSession();
    Long id = _jpa.getSolicitudID();
    sessio.setAttribute(SolicitudDocumentOperadorController.SESSIO_SOLIID_MANAGE_DOCUMENTS,
        id);
    sessio.setAttribute(SolicitudServeiOperadorController.SESSIO_SOLIID_MANAGE_SERVEIS, id);

    log.info("Set attibute [" + SolicitudServeiOperadorController.SESSIO_SOLIID_MANAGE_SERVEIS
        + "] = " + _jpa.getSolicitudID());

    return solicitudForm;
  }

  @RequestMapping(value = "/formularicaidfitxers/{soliID}", method = RequestMethod.GET)
  public ModelAndView generarFormulariCaidFitxers(HttpServletRequest request,
      HttpServletResponse response, @PathVariable Long soliID) throws I18NException {

    ModelAndView mav = new ModelAndView("formularicaidfitxersOperador");

    mav.addObject("action", request.getContextPath() + getContextWeb() + "/formularicaid/" + soliID);

    SubQuery<DocumentSolicitud, Long> subQueryDocSoli = documentSolicitudEjb.getSubQuery(
        DocumentSolicitudFields.DOCUMENTID, DocumentSolicitudFields.SOLICITUDID.equal(soliID));

    List<Document> docs = documentEjb.select(DocumentFields.DOCUMENTID.in(subQueryDocSoli));

    mav.addObject("documents", docs);

    return mav;

  }

  @RequestMapping(value = "/formularicaid/{soliID}", method = RequestMethod.POST)
  public ModelAndView generarFormulariCaid(HttpServletRequest request,
      HttpServletResponse response, @PathVariable Long soliID) throws I18NException {

    final String backurl = getContextWeb() + "/view/" + soliID;

    String[] fitxersID = request.getParameterValues("fitxerID");

    // Cercar fitxer

    List<Fitxer> fitxers = new ArrayList<Fitxer>();

    for (String fid : fitxersID) {
      log.info("Fitxer Seleccionats => " + fid);

      Fitxer f = fitxerEjb.findByPrimaryKey(Long.parseLong(fid));

      fitxers.add(f);

    }

    if (fitxersID == null || fitxersID.length == 0) {
      HtmlUtils.saveMessageError(request,
          "Es necessita sellecionar com a mínim un Document-Solicitud per poder crear la incidència.");
      return new ModelAndView(new RedirectView(backurl, true));
    }

    // Fer zip
    byte[] fitxersContent;
    try {

      // File zipFile = File.createTempFile("pinbaladmin_", ".zip");

      ByteArrayOutputStream fos = new ByteArrayOutputStream();
      ZipOutputStream zos = new ZipOutputStream(fos);

      for (Fitxer aFile : fitxers) {

        zos.putNextEntry(new ZipEntry(aFile.getNom()));

        byte[] bytes = FileSystemManager.getFileContent(aFile.getFitxerID());
        zos.write(bytes, 0, bytes.length);
        zos.closeEntry();
      }

      zos.close();

      fitxersContent = fos.toByteArray();

    } catch (Exception ex) {
      String msg = "Error creant zip: " + ex.getMessage();
      log.error(msg, ex);
      HtmlUtils.saveMessageError(request, msg);
      return new ModelAndView(new RedirectView(backurl, true));
    }

    ModelAndView mav = new ModelAndView("formularicaidOperador");

    mav.addObject("fitxers", fitxers);

    String fitxerB64 = Base64.encode(fitxersContent);
    mav.addObject("fitxerB64", fitxerB64);

    SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);

    String callback = Configuracio.getAppUrl()
        + CallbackSeleniumController.CALLBACK_SELENIUM_CONTEXT + "/" + soli.getSolicitudID();

    mav.addObject("backurl", request.getContextPath() + backurl);
    mav.addObject("callback", callback);
    mav.addObject("action", Configuracio.getCAIDSeleniumUrl() + "/RemoteSeleniumAlta");

    String username = request.getUserPrincipal().getName();

    String nom;
    String llinatge1;
    String llinatge2;
    String email = "gd.pinbal@fundaciobit.org";

    if ("pvico".equals(username)) {
      nom = "Pilar";
      llinatge1 = "Vico";
      llinatge2 = "Hervas";
    } else if ("mcapo".equals(username)) {
      nom = "Maria Antonia";
      llinatge1 = "Capo";
      llinatge2 = "Santandreu";
    } else if ("anadal".equals(username)) {
      nom = "Antoni";
      llinatge1 = "Nadal";
      llinatge2 = "Bennasar";
      //email = "anadal@fundaciobit.org";
    } else {
      HtmlUtils.saveMessageError(request,
          "L'username " + username + " no està mapejat a cap nom i llinatges");
      nom = "Pilar";
      llinatge1 = "Vico";
      llinatge2 = "";
    }

    mav.addObject("nombre", nom);
    mav.addObject("apellido1", llinatge1);
    mav.addObject("apellido2", llinatge2);
    mav.addObject("organismo",
        "Consejería de Administraciones Públicas y Modernización » (A04027005) Dirección General de Modernización y Administración Digital");
    mav.addObject("email", email);
    mav.addObject("asunto", "Alta Servicios");
    mav.addObject("produccio", soli.isProduccio());

    if (soli.getEntitatEstatal() == null) {
      // Es entitat local
      mav.addObject("comentario", "Buenos dias, \r\n"
          + "Adjunto formulario para dar de alta unos servicios en el procedimiento ...\r\n"
          + "Esperamos respuesta.");
    } else {
      // Es entitat estatal
      mav.addObject("comentario", "Buenos dias, \r\n"
          + "Adjunto formulario para dar de alta unos servicios en el procedimiento .... Le recordamos que en este caso el Govern actuar&aacute; como Nodo de Interoperabilidad.\r\n"
          + "Esperamos respuesta.");
    }

    return mav;
  }

  /**
   * 
   * @param request
   * @param response
   * @param soliID
   * @return
   */
  @RequestMapping(value = "/generarserveis/{soliID}", method = RequestMethod.GET)
  public String generarServeisAndFormulari(HttpServletRequest request,
      HttpServletResponse response, @PathVariable Long soliID) {

    SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);

    Long fitxerID = soli.getSolicitudXmlID();

    log.info(" FITXER ID => " + fitxerID);

    if (fitxerID == null) {

      HtmlUtils.saveMessageError(request,
          "NO ES PODEN GENERAR ELS SERVEIS JA QUE NO HI HA EL FITXER DE XML !!!!!!");

    } else {

      if (!isEstatal()) {
        try {
  
          String xml;
          {
            byte[] xmlData = FileSystemManager.getFileContent(fitxerID);
  
            xml = new String(xmlData, "UTF-8");
          }
  
          Properties prop = ParserFormulariXML.getPropertiesFromFormulario(xml);
  
          generarServeis(request, soliID, prop);
  
          generarFormulari(request, soliID, prop, "");
  
        } catch (I18NException ie) {
          String msg = I18NUtils.getMessage(ie);
          log.error(msg, ie);
          HtmlUtils.saveMessageError(request, msg);
  
        } catch (Exception e) {
          log.error(e.getMessage(), e);
          HtmlUtils.saveMessageError(request, "Error" + e.getMessage());
        }
      }
    }

    return "redirect:" + getContextWeb() + "/view/" + soliID;
  }

  @RequestMapping(value = "/generarformularidirectorgeneral/{soliID}", method = RequestMethod.GET)
  public String generarFormulariDirectorGeneral(HttpServletRequest request,
      @PathVariable Long soliID) throws Exception {

    SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKey(soliID);

    Long fitxerID = soli.getSolicitudXmlID();

    log.info(" FITXER ID => " + fitxerID);

    if (fitxerID == null) {

      HtmlUtils.saveMessageError(request,
          "NO ES PODEN GENERAR ELS SERVEIS JA QUE NO HI HA EL FITXER DE XML !!!!!!");

    } else {

      if (!isEstatal()) {
        try {
  
          String xml;
          {
            byte[] xmlData = FileSystemManager.getFileContent(fitxerID);
  
            xml = new String(xmlData, "UTF-8");
          }
  
          Properties prop = ParserFormulariXML.getPropertiesFromFormulario(xml);
  
          String cp = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CP");
  
          if (cp == null) {
            prop.setProperty("FORMULARIO.DATOS_SOLICITUD.CP", "");
          }
  
          generarFormulari(request, soliID, prop,
              SolicitudServeiOperadorController.SDF.format(new Date()));
  
        } catch (I18NException ie) {
          String msg = I18NUtils.getMessage(ie);
          log.error(msg, ie);
          HtmlUtils.saveMessageError(request, msg);
  
        } catch (Exception e) {
          log.error(e.getMessage(), e);
          HtmlUtils.saveMessageError(request, "Error" + e.getMessage());
        }
      }
    }

    return "redirect:" + getContextWeb() + "/view/" + soliID;
  }

  public void generarFormulari(HttpServletRequest request, Long solicitudID, Properties prop,
      String prefix) throws Exception, I18NException {

    File outputPDF = File.createTempFile("pinbaladmin_formulari", ".pdf");
    File outputODT = File.createTempFile("pinbaladmin_formulari", ".odt");

    File plantilla = new File(Configuracio.getTemplateFormulari());

    ParserFormulariXML.creaDocFormulari(prop, plantilla, outputPDF, outputODT);

    {
      FitxerJPA fitxer = new FitxerJPA("Formulario_Director_General.pdf", outputPDF.length(),
          "application/pdf", "");

      fitxer = (FitxerJPA) fitxerEjb.create(fitxer);

      FileSystemManager.sobreescriureFitxer(outputPDF, fitxer.getFitxerID());

      Document doc = documentEjb.create(prefix + "Formulario_Director_General (PDF)",
          fitxer.getFitxerID(), null, null);

      DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), solicitudID);

      documentSolicitudEjb.create(ds);
    }

    {
      FitxerJPA fitxer = new FitxerJPA(prefix + "Formulario_Director_General.odt",
          outputODT.length(), "application/vnd.oasis.opendocument.text", "");

      fitxer = (FitxerJPA) fitxerEjb.create(fitxer);

      FileSystemManager.sobreescriureFitxer(outputODT, fitxer.getFitxerID());

      Document doc = documentEjb.create(prefix + "Formulario_Director_General (ODT)",
          fitxer.getFitxerID(), null, null);

      DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), solicitudID);

      documentSolicitudEjb.create(ds);
    }

  }

  protected void generarServeis(HttpServletRequest request, Long soliID, Properties prop)
      throws I18NException {
    int x = 1;

    while (true) {
      String codi = prop
          .getProperty("FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".CODISERV");

      log.info(" CODI PER [" + x + "]  => " + codi);

      if (codi == null) {
        break;
      }

      // TODO XYZ ZZZ
      Long serveiID = serveiEjb.executeQueryOne(ServeiFields.SERVEIID,
          ServeiFields.CODI.equal(codi.trim()));

      // XYZ ZZZ
      java.lang.Long estatSolicitudServeiID = 10L;
      // StringBuffer str = new StringBuffer();
      // for (Object k : prop.keySet()) {
      // String kk = (String) k;
      //
      // if (kk.startsWith("FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x +
      // ".")) {
      // str.append(kk + "=" + prop.getProperty(kk) + "\r\n");
      // }
      //
      // }

      java.lang.String notes = ""; // str.toString();
      java.lang.String normaLegal = prop
          .getProperty("FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".NORMALEGAL");

      java.lang.String enllazNormaLegal = prop
          .getProperty("FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".ENLACENOR");

      java.lang.String articles = prop
          .getProperty("FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".ARTICULOS");
      java.lang.String tipusConsentiment = prop.getProperty(
          "FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".LDECONSENTIMIENTO");
      java.lang.String consentiment = prop
          .getProperty("FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".CONSENTIMIENTO");
      java.lang.String enllazConsentiment = prop
          .getProperty("FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".ENLACECON");

      String caducafecha = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.FECHACAD");
      String caduca = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CADUCA");

      Long count = solicitudServeiEjb
          .count(Where.AND(SolicitudServeiFields.SOLICITUDID.equal(soliID),
              SolicitudServeiFields.SERVEIID.equal(serveiID)));

      if (count == 0) {

        SolicitudServeiJPA ss = new SolicitudServeiJPA(soliID, serveiID,
            estatSolicitudServeiID, normaLegal, enllazNormaLegal, articles, tipusConsentiment,
            consentiment, enllazConsentiment, notes, caduca, caducafecha);

        solicitudServeiEjb.create(ss);

      } else {

        HtmlUtils.saveMessageWarning(request,
            "El servei [" + x + "] ja existeix. L'ignoram ...");

      }

      x++;

    }
  }

  @Override
  protected ModelAndView editAndViewSolicitudGet(Long solicitudID, HttpServletRequest request,
      HttpServletResponse response, boolean __isView) throws I18NException {
    if (__isView) {
      // Guaram el referer, ja que a la vista de solicitud podem venir de moltes
      // bandes:
      // de les solicituts actives/ estatals / locals, o de la llista de
      // sol·licituds d'un servei
      String referer = request.getHeader("referer");
      request.getSession().setAttribute(getSessioSolicitudRefererWithId(solicitudID), referer);
    }
    return super.editAndViewSolicitudGet(solicitudID, request, response, __isView);
  }

  @Override
  public String getRedirectWhenCancel(HttpServletRequest request, Long solicitudID) {
    // Si tenim guardat el referer per aquesta sol·licitud l'empram.
    String refererAttribute = getSessioSolicitudRefererWithId(solicitudID);
    String referer = (String) request.getSession().getAttribute(refererAttribute);
    if (referer != null) {
      request.getSession().removeAttribute(refererAttribute);
      return "redirect:" + referer;
    } else {
      return super.getRedirectWhenCancel(request, solicitudID);
    }
  }

  @Override
  public boolean isActiveList() {
    return false;
  }

  @Override
  public boolean isActiveFormNew() {
    return false;
  }

  @Override
  public boolean isActiveFormEdit() {
    return false;
  }

  @Override
  public boolean isActiveDelete() {
    return false;
  }

  @Override
  public boolean isActiveFormView() {
    return true;
  }

  @Override
  public Boolean isEstatal() {
    // Només gestionam la vista form (no hi ha llistat)
    return null;
  }

  @Override
  public boolean showAdvancedFilter() {
    return false;
  }

}
