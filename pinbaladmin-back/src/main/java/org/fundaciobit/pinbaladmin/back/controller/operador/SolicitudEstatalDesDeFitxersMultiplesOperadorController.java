package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.back.utils.NormativaInfo;
import org.fundaciobit.pinbaladmin.back.utils.ParserSolicitudXLSX;
import org.fundaciobit.pinbaladmin.back.utils.ProcedimentInfo;
import org.fundaciobit.pinbaladmin.back.utils.ServeiInfo;
import org.fundaciobit.pinbaladmin.back.utils.SolicitudInfo;
import org.fundaciobit.pinbaladmin.back.utils.Utils;
import org.fundaciobit.pinbaladmin.back.utils.email.EmailEmlFormatParser;
import org.fundaciobit.pinbaladmin.back.utils.email.EmailMsgFormatParser;
import org.fundaciobit.pinbaladmin.ejb.ServeiService;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailAttachmentInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/solicitudestataldesdefitxers")
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudEstatalDesDeFitxersMultiplesOperadorController extends SolicitudEstatalOperadorController {

    public static final String XLSX_MIME = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @RequestMapping(value = "/nou", method = RequestMethod.GET)
    public String nou() {
        return "solicitudestataldesdefitxersOperador";
    }

    @RequestMapping(value = "/uploadMultiFile", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> uploadMultiFile(HttpServletRequest request,
            @RequestParam("files[]") MultipartFile[] files) {

        try {

            long start = System.currentTimeMillis();

            // Get the uploaded files and store them
            if (files == null || files.length == 0) {
                String msg = "No s'han rebut fitxers";

                log.error("No s'han rebut Fitxers ...");

                return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);

            }

            for (MultipartFile multipartFile : files) {

                if (multipartFile.isEmpty()) {
                    log.info("==========================");
                    log.info("Fitxer buit ... ");
                    continue;
                }

                String fileName = multipartFile.getOriginalFilename();

                // servletRequest.getServletContext().getRealPath("/image")
                // File imageFile = File.createTempFile("umf_", "_" + fileName);
                // new File(File.TempDirectory.get , fileName);

                String mime = multipartFile.getContentType();

                log.info("==========================");
                log.info("FILE NAME => " + fileName);
                log.info("FILE MIME => " + mime);
                log.info("FILE SIZE => " + multipartFile.getSize() + " bytes");
                String extension = Utils.getExtension(fileName);
                // Parsejar
                EmailMessageInfo emi;

                if ("message/rfc822".equals(mime) || ".eml".equals(extension)) {
                    log.info(" FORMAT 1");
                    emi = EmailEmlFormatParser.parserEml(multipartFile.getInputStream());
                } else if (".msg".equals(extension)) {
                    log.info(" FORMAT 2");
                    emi = EmailMsgFormatParser.processMessage(multipartFile.getInputStream());
                } else if (XLSX_MIME.equals(mime) || ".xlsx".equals(extension)) {
                    log.info(" FORMAT 3");
                    emi = new EmailMessageInfo();
                    emi.getAttachments().add(new EmailAttachmentInfo(fileName, XLSX_MIME, multipartFile.getBytes()));
                } else {
                    log.info(" FORMAT 4");
                    String msg = "Tipus de fitxer amb extensió desconeguda: " + fileName
                            + ". Només suportam .eml, .msg o .xmls";
                    log.error(msg);
                    return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);

                }

                crearSolicitudsDesDeEmail(request, emi, fileName, log, serveiEjb, solicitudLogicaEjb);
            }

            long end = System.currentTimeMillis();

            return new ResponseEntity<String>(start + "/" + end, HttpStatus.OK);

        } catch (Exception e) {
            String msg = "Error processant fitxers: " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
            return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public static List<SolicitudJPA> crearSolicitudsDesDeEmail(HttpServletRequest request, EmailMessageInfo emi,
            String fileName, Logger log, ServeiService serveiEjb, SolicitudLogicaService solicitudLogicaEjb)
            throws Exception {
        // Cercar XLSX dins dels attachments

        log.info(" Cercar XLSX dins dels attachments");

        List<EmailAttachmentInfo> attachs = emi.getAttachments();

        EmailAttachmentInfo xlsx = null;

        for (EmailAttachmentInfo eai : attachs) {

            if (XLSX_MIME.equalsIgnoreCase(eai.getContentType())) {
                log.info("trobat: " + eai.getFileName());
                xlsx = eai;
                break;
            }

            String ext = Utils.getExtension(eai.getFileName());

            log.info("Cercant Extensio: " + ext);

            if (".xlsx".equalsIgnoreCase(ext)) {
                xlsx = eai;
                break;
            }

        }

        if (xlsx == null) {
            String msg = "El document enviat " + fileName + " no conté cap fitxer .xlsx";
            log.error(msg);
            throw new Exception(msg);
        }

        // Convertir xlsx a Sol·licitud (inclou serveis i fitxers)
        String pid = Utils.getPidFromSubject(emi.getSubject());

        log.info("PID => " + pid);
        if (pid == null) {
            HtmlUtils.saveMessageWarning(request, "Les següents sol.licituds no tenen el PID assignat. ");
        }

        List<SolicitudJPA> solicituds;
        try {
            log.info("processSolicitud  start ");
            solicituds = processSolicitud(request, emi, xlsx, pid, log, serveiEjb);

            log.info("processSolicitud  end " + solicituds.size());
        } catch (Throwable e) {
            String msg = "Error processant solicitud o serveis del fitxer " + fileName + ": " + e.getMessage();
            log.error(msg, e);
            throw new Exception(msg);
        }

        if (solicituds.size() == 0) {
            HtmlUtils.saveMessageError(request,
                    "No s'ha pogut processar cap petició del fitxer enviat. Revisar classe TipusProcediments per inclore-ho");
        }

        try {
            solicitudLogicaEjb.crearSolicituds(solicituds, xlsx, attachs);
        } catch (I18NException e) {
            String msg = "Error creant Sol·lictuds: " + I18NUtils.getMessage(e);
            log.error(msg, e);
            throw new Exception(msg);
        }

        return solicituds;

    }

    protected static List<SolicitudJPA> processSolicitud(HttpServletRequest request, EmailMessageInfo emi,
            EmailAttachmentInfo xlsx, String pid, Logger log, ServeiService serveiEjb) throws Exception, I18NException {

        InputStream xlsxIS = new ByteArrayInputStream(xlsx.getData());

        final boolean debug = false;
        SolicitudInfo info = ParserSolicitudXLSX.extreureInfo(xlsxIS, debug);

        log.info(" #Procediments de Solicitud = " + info.getProcediments().size());

        List<SolicitudJPA> solicituds = new ArrayList<SolicitudJPA>();

        List<String> serveisNoTrobats = new ArrayList<String>();

        // El primer de la llista ...
        for (ProcedimentInfo proc : info.getProcediments().values()) {

            SolicitudJPA solicitud = new SolicitudJPA();

            solicitud.setTicketAssociat(pid);

            // String procedimentCodi = null;
            solicitud.setProcedimentCodi(proc.getCodi());

            solicitud.setPersonaContacte(emi.getNameFrom());
            solicitud.setPersonaContacteEmail(emi.getDisplayFrom());

            // solicitud.setCodiDescriptiu(null);
            solicitud.setCreador(request.getRemoteUser());

            solicitud.setProcedimentNom(proc.getNom());
            solicitud.setDataInici(new Timestamp(System.currentTimeMillis()));

            solicitud.setEstatID(10L);
            solicitud.setEntitatEstatal(info.getEntitat());

            String tpOrig = proc.getTipusProcediment();

            // XYZ ZZZ
            // log.info("\n\n XXXXXXXXXXXXXXXXX\n ESTATAL TP ORIGINAL => ]" + tp +
            // "[\nZZZZZZZZZZZZZZZZZZ\n\n" );

            String tp = TipusProcediments
                    .getTipusProcedimentByLabel(tpOrig == null ? tpOrig : tpOrig.trim().replace("  ", " "));

            if (tp == null) {
                HtmlUtils.saveMessageError(request, "No he trobat el Tipus de Procediment per l'etiqueta ]" + tpOrig
                        + "[, Revisi si l'ha de posar en la descripció com un àlies.");
            } else {
                solicitud.setProcedimentTipus(tp);
            }

            // Serveis
            Set<SolicitudServeiJPA> ssList = new HashSet<SolicitudServeiJPA>();

            List<ServeiInfo> serveis = proc.getServeis();

            for (ServeiInfo servei : serveis) {

                if (!"CCAA".equalsIgnoreCase(servei.getCedent())) {
                    String msg = "El servei \"" + servei.getNom() + "\" amb cedent '" + servei.getCedent()
                            + "' el descartam per no ser de CCAA.";
                    log.warn(msg);
                    HtmlUtils.saveMessageWarning(request, msg);
                    continue;
                }

                Long id = serveiEjb.executeQueryOne(ServeiFields.SERVEIID,
                        Where.OR(ServeiFields.NOM.equal(servei.getNom()),
                                ServeiFields.DESCRIPCIO.like("%" + servei.getNom().trim() + "%")));

                if (id == null) {
                    serveisNoTrobats.add("|" + servei.getNom() + "|");
                    continue;
                }

                // ja s'assignarà quan solicitud es crei
                long solicitudID = 0;
                long serveiID = id;
                java.lang.Long estatSolicitudServeiID = 20L; // Produccio

                java.lang.String normaLegal = "";
                java.lang.String enllazNormaLegal = "";
                java.lang.String articles = "";

                final String SEP = "\n-------\n";

                List<NormativaInfo> normes = servei.getNormatives();
                for (NormativaInfo norma : normes) {
                    normaLegal = normaLegal + norma.getNormaLegal() + SEP;
                    enllazNormaLegal = enllazNormaLegal + norma.getEnllaz() + SEP;
                    articles = articles + norma.getArticles() + SEP;
                }

                normaLegal = Utils.crop(normaLegal);
                enllazNormaLegal = Utils.crop(enllazNormaLegal);
                articles = Utils.crop(articles);

                java.lang.String tipusConsentiment = null;
                java.lang.String consentiment = null;
                java.lang.String enllazConsentiment = null;
                java.lang.String notes = null;
                java.lang.String caduca = null;
                java.lang.String fechaCaduca = null;

                SolicitudServeiJPA ss = new SolicitudServeiJPA(solicitudID, serveiID, estatSolicitudServeiID,
                        normaLegal, enllazNormaLegal, articles, tipusConsentiment, consentiment, enllazConsentiment,
                        notes, caduca, fechaCaduca);

                ssList.add(ss);
            }

            if (ssList.size() != 0) {
                solicitud.setSolicitudServeis(ssList);
                solicituds.add(solicitud);
            }
        }

        if (serveisNoTrobats.size() != 0) {
            throw new Exception("No s'han trobat els serveis següents " + serveisNoTrobats.toString()
                    + ". Les ha d'afegir a la descripció dels serveis corresponents.");
        }

        return solicituds;
    }

}
