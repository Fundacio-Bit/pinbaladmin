package org.fundaciobit.pinbaladmin.back.controller.operador;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.Normalizer;
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
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.all.CallbackSeleniumController;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.utils.FileInfo;
import org.fundaciobit.pinbaladmin.logic.utils.PdfDownloader;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.DocumentSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.fields.DocumentFields;
import org.fundaciobit.pinbaladmin.model.fields.DocumentSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.FitxerJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @author anadal
 * @author areus
 */
@Controller
@RequestMapping(value = SolicitudFullViewOperadorController.CONTEXTWEB)
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudFullViewOperadorController extends SolicitudOperadorController {

    public static final String CONTEXTWEB = "/operador/solicitudfullview";

    
  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService documentSolicitudEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentService.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DocumentService documentEjb;

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

    
    SolicitudJPA solicitud = solicitudForm.getSolicitud();
    
    mav.addObject("isView", __isView);
    
    if (__isView) {
    	final boolean isEstatal = solicitud.getEntitatEstatal() != null && solicitud.getEntitatEstatal().trim().length() > 0;

    	
      // Canviam el cancel·lar per un tornar.....
      solicitudForm.setCancelButtonVisible(false);
      Long soliID = solicitud.getSolicitudID();
      String urlTornar = "/operador/solicitudfullview/" + soliID + " /cancel";
      
      solicitudForm.addAdditionalButton(
          new AdditionalButton("fas fa-arrow-left", "tornar", urlTornar, AdditionalButtonStyle.INFO));

		solicitudForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_EDIT, "solicitud.edit",
				"/operador/solicitud" + (isEstatal ? "estatal" : "local") + "/" + soliID + "/edit",
				AdditionalButtonStyle.WARNING));
		
      String urlBackToEvents = EventSolicitudOperadorController.CONTEXTWEB + "/veureevents/"
              + soliID + (isEstatal() == null ? "" : ("/" + isEstatal));

      solicitudForm.addAdditionalButton(
              new AdditionalButton("fas fa-bullhorn", "events.titol", urlBackToEvents, AdditionalButtonStyle.SUCCESS));
      
		if (!isEstatal) {
			// Si és local
			solicitudForm.addAdditionalButton(
					new AdditionalButton(IconUtils.ICON_RELOAD, "solicitud.generarformularidirectorgeneral",
							getContextWeb() + "/generarformularidirectorgeneral/" + soliID, AdditionalButtonStyle.WARNING));
			
			if (solicitud.getEstatID() == Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Director) {
				solicitudForm.addAdditionalButton(new AdditionalButton("fas fa-file-signature", "firmar.director.portafib",
						getContextWeb() + "/enviarAFirmar/" + soliID, AdditionalButtonStyle.PRIMARY));
			}
			
			if (!isFirmatPelDirector(solicitud)) {
				solicitudForm.addAdditionalButton(new AdditionalButton("fas fa-file-upload", "afegir.formulari.firmat",
						getContextWeb() + "/afegirFormulariFirmat/" + soliID, AdditionalButtonStyle.WARNING));
			}

			if (solicitud.getEstatID() == Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR
					|| solicitud.getEstatID() == Constants.SOLICITUD_ESTAT_PENDENT_ENVIAR_MADRID) {
				log.info("Estat PBL: " + solicitud.getEstatpinbal());

				AdditionalButton alta = new AdditionalButton("fas fa-cloud-upload-alt", "alta.pinbal.madrid",
						"/operador/altapinbal/vistaprevia/alta/" + soliID, AdditionalButtonStyle.PRIMARY);

				AdditionalButton consulta = new AdditionalButton("fas fa-eye", "consulta.pinbal.madrid",
						"/operador/altapinbal/consultaestado/" + soliID, AdditionalButtonStyle.SECONDARY);

				AdditionalButton modificacio = new AdditionalButton("fas fa-tools", "modificacio.pinbal.madrid",
						"/operador/altapinbal/vistaprevia/modificacio/" + soliID, AdditionalButtonStyle.SUCCESS);

				if (solicitud.getEstatpinbal() == null) {
					solicitud.setEstatpinbal(Constants.ESTAT_PINBAL_NO_SOLICITAT);
				}

				if (solicitud.getEstatpinbal() >= 0) {
					solicitudForm.addAdditionalButton(consulta);
				}

				switch (solicitud.getEstatpinbal()) {
				case Constants.ESTAT_PINBAL_ERROR:
				case Constants.ESTAT_PINBAL_NO_SOLICITAT:
				case Constants.ESTAT_PINBAL_NO_APROVAT:
				case Constants.ESTAT_PINBAL_PENDENT_SUBSANACIO:
				case Constants.ESTAT_PINBAL_DESESTIMAT:
					solicitudForm.addAdditionalButton(alta);
					break;

				case Constants.ESTAT_PINBAL_APROVAT:
				case Constants.ESTAT_PINBAL_SUBSANAT:
					solicitudForm.addAdditionalButton(modificacio);
					break;

				case Constants.ESTAT_PINBAL_PENDENT_TRAMITAR:
				case Constants.ESTAT_PINBAL_DESISTIT:
				case Constants.ESTAT_PINBAL_PENDENT_AUTORITZACIO_CEDENT:
				case Constants.ESTAT_PINBAL_AUTORITZAT:
				case Constants.ESTAT_PINBAL_AUTORITZAT_SOLICITUTS_PENDENTS_SUBSANACIO:
					break;

				default:
					break;
				}
			}
		} else {
			// Si és estatal
			
			if (solicitud.getEstatID() == Constants.SOLICITUD_ESTAT_PENDENT_Enviar_Cedents) {
				// Boto per enviar correus als cedents
				solicitudForm.addAdditionalButton(new AdditionalButton("fas fa-envelope", "estatal.enviarcorreucedents",
						"/operador/solicitudestatal/enviarcorreucedents/" + soliID, AdditionalButtonStyle.WARNING));
			}
			
			//Boto per enviar correus als cedents
			solicitudForm.addAdditionalButton(new AdditionalButton("fas fa-envelope", "estatal.enviarcorreucedents",
					"/operador/solicitudestatal/enviarcorreucedents/" + soliID, AdditionalButtonStyle.WARNING));
		}
      
      
      
      solicitudForm.setAttachedAdditionalJspCode(true);
    }

    HttpSession sessio = request.getSession();
    Long id = solicitud.getSolicitudID();
    sessio.setAttribute(SolicitudDocumentOperadorController.SESSIO_SOLIID_MANAGE_DOCUMENTS,
        id);
    sessio.setAttribute(SolicitudServeiOperadorController.SESSIO_SOLIID_MANAGE_SERVEIS, id);

    log.info("Set attibute [" + SolicitudServeiOperadorController.SESSIO_SOLIID_MANAGE_SERVEIS
        + "] = " + solicitud.getSolicitudID());

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
      
      // Si és local
      if ("application/xml".equals(soli.getSolicitudXml().getMime())) {
        try {
          Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
  
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

      // Si és local
      if ("application/xml".equals(soli.getSolicitudXml().getMime())) {

        try {
  
          Properties prop = ParserFormulariXML.getPropertiesFromFormulario(fitxerID);
  
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

	  log.info("Generant formulari per la sol·licitud [" + solicitudID + "]");
	  
    File outputPDF = File.createTempFile("pinbaladmin_formulari", ".pdf");
    File outputODT = File.createTempFile("pinbaladmin_formulari", ".odt");

    File plantilla = new File(Configuracio.getTemplateFormulari());

    SolicitudJPA soli = solicitudEjb.findByPrimaryKey(solicitudID);
    
    Organ organGestor = organEjb.findByPrimaryKey(soli.getOrganid());
	while (organGestor.getDir3pare() != null && !organGestor.getDir3pare().equals(organGestor.getDir3())) {
        List<Organ> organ = organEjb.select(OrganFields.DIR3.equal(organGestor.getDir3pare()));
        if (organ.size() == 1) {
            organGestor = organ.get(0);
        }
    }
    
    if (organGestor.getCif().equals("S0711001H")) {
        String dir3Dgtic = "A04027005";
        List<Organ> organ = organEjb.select(OrganFields.DIR3.equal(dir3Dgtic));
        if (organ.size() == 1) {
            Organ dgtic = organ.get(0);
            prop.setProperty("FORMULARIO.DATOS_SOLICITUD.UNIDAD", dgtic.getNom());
            prop.setProperty("FORMULARIO.DATOS_SOLICITUD.CODIUR", dgtic.getDir3());
        }
        
    }

    //Validador de apellido2 mientras en Madrid no funcione.
    String propApe2Base = "FORMULARIO.DATOS_SOLICITUD.APE2SEC";
    String[] partes = {"D", "E", "F", "G"};
    
    boolean faltaApe2 = false;
	for (String parte : partes) {
		String propApe2 = propApe2Base + parte;
		if (prop.getProperty(propApe2) == null) {
			faltaApe2 = true;
			log.info("Añadiendo apellido2 vacío: " + propApe2);
			prop.setProperty(propApe2, "");
		}
	}

	if (faltaApe2) {
		HtmlUtils.saveMessageWarning(request, "NOTA: Falta el apellido 2 en el fomulario XML. Ignoramos porque aun no enviamos a Madrid.");
	}
    
    ParserFormulariXML.creaDocFormulari(prop, plantilla, outputPDF, outputODT);

    {
      FitxerJPA fitxer = new FitxerJPA("Formulario_Director_General.pdf", outputPDF.length(),
          "application/pdf", "");

      fitxer = (FitxerJPA) fitxerEjb.create(fitxer);

      FileSystemManager.sobreescriureFitxer(outputPDF, fitxer.getFitxerID());

      Long tipus = Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF;
      Document doc = documentEjb.create(prefix + "Formulario_Director_General (PDF)",
          fitxer.getFitxerID(), null, null, tipus);

      DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), solicitudID);

      documentSolicitudEjb.create(ds);
    }

    {
      FitxerJPA fitxer = new FitxerJPA(prefix + "Formulario_Director_General.odt",
          outputODT.length(), "application/vnd.oasis.opendocument.text", "");

      fitxer = (FitxerJPA) fitxerEjb.create(fitxer);

      FileSystemManager.sobreescriureFitxer(outputODT, fitxer.getFitxerID());

      Long tipus = Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_ODT;
      Document doc = documentEjb.create(prefix + "Formulario_Director_General (ODT)",
          fitxer.getFitxerID(), null, null, tipus);

      DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), solicitudID);

      documentSolicitudEjb.create(ds);
    }

  }

  protected void generarServeis(HttpServletRequest request, Long soliID, Properties prop)
			throws I18NException {
		int x = 1;

		while (true) {
			String codi = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".CODISERV");
			log.info(" CODI PER [" + x + "]  => " + codi);
			if (codi == null || codi.trim().isEmpty()) {
				log.info("No hi ha mes serveis");
				break;
			}

			// TODO XYZ ZZZ
			Long serveiID = serveiEjb.executeQueryOne(ServeiFields.SERVEIID, ServeiFields.CODI.equal(codi.trim()));

			if (serveiID == null) {
                HtmlUtils.saveMessageWarning(request, "El servei [" + codi + "] no existeix. L'ignoram ...");
                x++;
                continue;
            }
			
			// XYZ ZZZ
			java.lang.Long estatSolicitudServeiID = 10L;
			java.lang.String notes = ""; // str.toString();

			String base = "FORMULARIO.DATOS_SOLICITUD.LELSERVICIOS.ID" + x + ".";

			String normaLegal = prop.getProperty(base + "NORMALEGAL");
			String enllazNormaLegal = prop.getProperty(base + "ENLACENOR");
			String articles = prop.getProperty(base + "ARTICULOS");

			String consAdj = prop.getProperty(base + "LDECONSENTIMIENTO");
			String consUrl = prop.getProperty(base + "ENLACECON");
			String consentiment = prop.getProperty(base + "CONSENTIMIENTO");

			String consentimentAux = normalize(consentiment).replaceAll("\\p{M}", "");
			log.info("consentiment: ]" + consentiment + "[ Normalitzam: ]" + consentimentAux + "[ toLowerCase: ]"
					+ consentimentAux.toLowerCase() + "[");
			switch (consentimentAux.toLowerCase()) {
			case "sí":
			case "si":
				consentiment = Constants.CONSENTIMENT_TIPUS_SI;
				break;

			case "nooposicio":
			case "nooposicion":
			case "noop":
			case "noopo":

			case "no oposicio":
			case "no oposicion":
			case "no op":
			case "no opo":

			case "no_oposicio":
			case "no_oposicion":
			case "no_op":
			case "no_opo":
				consentiment = Constants.CONSENTIMENT_TIPUS_NOOP;
				break;
			case "llei":
			case "ley":
				consentiment = Constants.CONSENTIMENT_TIPUS_LLEI;
				break;
			}
			log.info("Consentiment despues: " + consentiment);

			String caducafecha = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.FECHACAD");
			String caduca = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CADUCA");

			Long count = solicitudServeiEjb.count(Where.AND(SolicitudServeiFields.SOLICITUDID.equal(soliID),
					SolicitudServeiFields.SERVEIID.equal(serveiID)));

			if (count == 0) {

				//XXX YYY ZZZ Tornar a posar obtenir PDF quan estigui en marxa api alta pinbal madrid.
//				Long fitxerIDNorma = crearFitxerNormaFromURL(enllazNormaLegal);
//				if (fitxerIDNorma == null) {
//					HtmlUtils.saveMessageWarning(request, "No s'ha pogut crear el fitxer de la norma [" + normaLegal
//							+ "] amb URL [" + enllazNormaLegal + "]");
//				}
				
				
				
				Long fitxerIDNorma = null;
				
				String norma2 = null;
				String articles2 = null;
				Long fitxerIDNorma2 = null;

				String norma3 = null;
				String articles3 = null;
				Long fitxerIDNorma3 = null;

				SolicitudServeiJPA ss = new SolicitudServeiJPA(soliID, serveiID, estatSolicitudServeiID,
						enllazNormaLegal, consAdj, consentiment, consUrl, notes, caduca, caducafecha, normaLegal,
						fitxerIDNorma, articles, norma2, fitxerIDNorma2, articles2, norma3, fitxerIDNorma3, articles3);

				log.info("ss: " + ss);

				solicitudServeiEjb.create(ss);

			} else {
				HtmlUtils.saveMessageWarning(request, "El servei [" + codi + "] ja existeix. L'ignoram ...");
			}
			
			log.info("Servei [" + x + "][" + +serveiID + "] => " + codi);
			x++;
		}
	}

	public static String normalize(String input) {
		return input == null ? null : Normalizer.normalize(input, Normalizer.Form.NFKD);
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
  
  
  public boolean isFirmatPelDirector(SolicitudJPA soli) throws I18NException {

      List<Long> listDocumentsSolicitud = documentSolicitudEjb.executeQuery(DocumentSolicitudFields.DOCUMENTID,
              DocumentSolicitudFields.SOLICITUDID.equal(soli.getSolicitudID()));

      List<Document> documentsPDF = documentEjb.select(Where.AND(DocumentFields.DOCUMENTID.in(listDocumentsSolicitud),
              DocumentFields.TIPUS.equal(Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF)));

      for (Document document : documentsPDF) {
          if (document.getFitxerFirmatID() != null) {
              return true;
          }
      }
      
      return false;
  }

  @RequestMapping(value = "/afegirFormulariFirmat/{soliID}", method = RequestMethod.GET)
  public String afegirFormulariFirmat(HttpServletRequest request, HttpServletResponse response,
          @PathVariable Long soliID) throws I18NException {
      
      List<DocumentSolicitud> listDocumentsSolicitud = documentSolicitudEjb.select(DocumentSolicitudFields.SOLICITUDID.equal(soliID));
      for (DocumentSolicitud docSol: listDocumentsSolicitud) {
          Document document = documentEjb.findByPrimaryKey(docSol.getDocumentID());
          
          if (document.getTipus() == Constants.DOCUMENT_SOLICITUD_FORMULARI_DIRECTOR_PDF) {
              return "redirect:" + "/operador" +  "/solicituddocumentonlycontent/" + docSol.getDocumentSolicitudID() + "/edit";
          }
      }
      return null;
  }
  
	@RequestMapping(value = "/enviarAFirmar/{soliID}", method = RequestMethod.GET)
	public String enviarDocumentAFirmar(HttpServletRequest request, HttpServletResponse response,
			@PathVariable Long soliID) throws I18NException {
		
		try {
			log.info("Enviem a firmar la sol·licitud [" + soliID + "]");
			
//	        String nifDestinatari = "45186147W";
	        String nifDestinatari = Configuracio.getNIFDirectorGeneral();
	        String remitent = request.getRemoteUser();
	        
			solicitudLogicaEjb.enviarFormulariDGPortaFIB(soliID, nifDestinatari, remitent);
			
			log.info("S'ha enviat a firmar la sol·licitud [" + soliID + "]");
			HtmlUtils.saveMessageInfo(request, "S'ha enviat a firmar la sol·licitud [" + soliID + "]");
		} catch (Exception e) {
			String msg = "Error enviant a firmar la sol·licitud [" + soliID + "]: " + e.getMessage();
			log.error(msg, e);
			HtmlUtils.saveMessageError(request, msg);
		}
		return "redirect:" + getContextWeb() + "/view/" + soliID;
	}
  
	public Long crearFitxerNormaFromURL(String url) {
		try {
			final boolean debug = false;
			FileInfo fileInfo = PdfDownloader.downloadPDFFromBoeBoibUrl(url, debug);

			String nom = fileInfo.getFileName();
			long tamany = fileInfo.getSize();
			String mime = "application/pdf";
			String descripcio = "Fitxer de norma legal descarregat des de la URL [" + url + "]";

			Fitxer fitxer = fitxerEjb.create(nom, tamany, mime, descripcio);

			Long fitxerID = fitxer.getFitxerID();

			FileSystemManager.crearFitxer(new ByteArrayInputStream(fileInfo.getContent()), fitxerID);

			return fitxerID;
		} catch (Exception e) {
			String errorMsg;
			if (e instanceof I18NException) {
				errorMsg = I18NUtils.getMessage((I18NException) e);
			}else {
				errorMsg = e.getMessage();
			}
			errorMsg = "Error creant fitxer de norma legal des de URL [" + url + "]: " + errorMsg;
			log.warn(errorMsg, e);
			return null;
		}
	}
}
