package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
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

                String operador = request.getRemoteUser();
                crearSolicitudsDesDeEmail(request, emi, operador, log, serveiEjb, solicitudLogicaEjb);
            }

            long end = System.currentTimeMillis();

            return new ResponseEntity<String>(start + "/" + end, HttpStatus.OK);

        } catch (Exception e) {
            String msg = "Error processant fitxers: " + e.getMessage();
            log.error(msg, e);
            //HtmlUtils.saveMessageError(request, msg);
            return new ResponseEntity<String>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @SuppressWarnings("resource")
	public static List<SolicitudJPA> crearSolicitudsDesDeEmail(HttpServletRequest request, EmailMessageInfo emi,
            String operador, Logger log, ServeiService serveiEjb, SolicitudLogicaService solicitudLogicaEjb)
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

			if (".zip".equalsIgnoreCase(ext)) {
				// Descomprimir, i afegir tots els fitxers a la llista d'attachs
				
				log.info("Cercam .xlsx al ZIP: " + eai.getFileName());

				String fileZip = eai.getFileName();
				ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
				ZipEntry zipEntry = zis.getNextEntry();
				
				while (zipEntry != null) {
					if (!zipEntry.isDirectory()) {
						String zipEntryExt = Utils.getExtension(zipEntry.getName());
						if (!".xlsx".equalsIgnoreCase(zipEntryExt)) {
							log.info("No es un fitxer .xlsx, no el processem: " + zipEntry.getName());
						} else {
							log.info("Fitxer .xlsx trobat: " + zipEntry.getName());
							// Hem d'assignar a xlsx el fitxer .xlsx

							String fileName = zipEntry.getName();
							String contentType = XLSX_MIME;
							byte[] data = zis.readAllBytes();

							EmailAttachmentInfo fitxerExcel = new EmailAttachmentInfo(fileName, contentType, data);

							xlsx = fitxerExcel;
							break;
						}
					} else {
						log.info("Directori: " + zipEntry.getName());
						File destDir = new File("/uncompressed/" + System.currentTimeMillis() + "/");
						File newFile = newFile(destDir, zipEntry);
						if (!newFile.isDirectory() && !newFile.mkdirs()) {
							throw new IOException("Failed to create directory " + newFile);
						}
					}
					zipEntry = zis.getNextEntry();
				}
				zis.closeEntry();
				zis.close();

			}
		}
        
        if (xlsx == null) {
            String msg = "El document enviat " + emi.getSubject() + " no conté cap fitxer .xlsx";
            log.error(msg);
            throw new Exception(msg);
        }

        List<SolicitudJPA> solicituds;
        try {
            solicituds = processSolicitud(request, emi, xlsx, operador, log, serveiEjb);
        } catch (Throwable e) {
            String msg = "Error processant solicitud o serveis del fitxer " + emi.getSubject() + ": " + e.getMessage();
            log.error(msg, e);
            throw new Exception(msg);
        }

        if (solicituds.size() == 0) {
            HtmlUtils.saveMessageError(request,
                    "No s'ha pogut processar cap petició del fitxer enviat. Revisar classe TipusProcediments per inclore-ho");
        }

        try {
            solicitudLogicaEjb.crearSolicituds(solicituds, xlsx, attachs, emi.getBody());
        } catch (I18NException e) {
            String msg = "Error creant Sol·lictuds: " + I18NUtils.getMessage(e);
            log.error(msg, e);
            throw new Exception(msg);
        }

        return solicituds;

    }

    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }
    
    protected static List<SolicitudJPA> processSolicitud(HttpServletRequest request, EmailMessageInfo emi,
            EmailAttachmentInfo xlsx, String operador, Logger log, ServeiService serveiEjb) throws Exception, I18NException {

        InputStream xlsxIS = new ByteArrayInputStream(xlsx.getData());

        final boolean debug = true;
        //Aquesta funció extreu la informació del fitxer excel
        SolicitudInfo info = ParserSolicitudXLSX.extreureInfo(xlsxIS, debug);
        
        String expedientID = getPIDFromSubject(emi.getSubject());
        info.setExpedientPID(expedientID);

        log.info(" #Procediments de Solicitud = " + info.getProcediments().size());

        List<SolicitudJPA> solicituds = new ArrayList<SolicitudJPA>();
        List<String> serveisNoTrobats = new ArrayList<String>();

        String nomContacte = emi.getNameFrom();
        if (nomContacte == null || nomContacte.trim().length() == 0) {
            nomContacte = info.getEntitat();
        }

        String mailContacte = emi.getDisplayFrom();
//        if (mailContacte == null || mailContacte.trim().length() == 0) {
//            mailContacte = info.getEntitat();
//        }

        
        // El primer de la llista ...
        for (ProcedimentInfo proc : info.getProcediments().values()) {

            SolicitudJPA solicitud = new SolicitudJPA();

            solicitud.setProcedimentCodi(proc.getCodi());

            solicitud.setPersonaContacte(nomContacte );
            solicitud.setPersonaContacteEmail(mailContacte);

            // solicitud.setCodiDescriptiu(null);
            solicitud.setCreador(request.getRemoteUser());
            solicitud.setOperador(operador);

            solicitud.setProcedimentNom(proc.getNom());
            solicitud.setDataInici(new Timestamp(System.currentTimeMillis()));

            solicitud.setEstatID(Constants.SOLICITUD_ESTAT_PENDENT);
            solicitud.setEntitatEstatal(info.getEntitat());
            solicitud.setExpedientPid(info.getExpedientPID());

			{
				String tpOrig = proc.getTipusProcediment();
				if (tpOrig != null) {
					tpOrig = tpOrig.trim().replace("  ", " ");
				}
				String tp = TipusProcediments.getTipusProcedimentByLabel(tpOrig);

				if (tp == null) {
					HtmlUtils.saveMessageError(request, "No he trobat el Tipus de Procediment per l'etiqueta ]" + tpOrig
							+ "[, Revisi si l'ha de posar en la descripció com un àlies.");
				} else {
					solicitud.setProcedimentTipus(tp);
				}
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
                log.info("Servei: " + servei.getNom());
                
                //servei.getNom = Consulta de datos de discapacidad por el real decreto 888/2022 # SVDCCAADISCAPACIDADWS01
                String[] parts = servei.getNom().split(" # ");
                Long id = null;
				for (String part : parts) {
					log.info("Part: " + part);
					
					id = serveiEjb.executeQueryOne(ServeiFields.SERVEIID, Where.OR(
							ServeiFields.CODI.equal(part),
							ServeiFields.NOM.equal(part), 
							ServeiFields.DESCRIPCIO.like("%" + part.trim() + "%")
							));
					
					if (id != null) {
						break;
					}

				}
                

                if (id == null) {
                    serveisNoTrobats.add("|" + servei.getNom() + "|");
                    continue;
                }

                // ja s'assignarà quan solicitud es crei
                long solicitudID = 0;
                long serveiID = id;
                java.lang.Long estatSolicitudServeiID = 20L; // Produccio

                String normaLegal = null;
                String articles = null;
                String enllazNormaLegal = null;
                
                String norma2 = null;
                String articles2 = null;
                
                String norma3 = null;
                String articles3 = null;

                List<NormativaInfo> normes = servei.getNormatives();
                
				for (int i = 0; i < normes.size(); i++) {
					NormativaInfo norma = normes.get(i);
					if (i == 0) {
						normaLegal = norma.getNormaLegal();
						articles = norma.getArticles();
						enllazNormaLegal = norma.getEnllaz();
					} else if (i == 1) {
						norma2 = norma.getNormaLegal();
						articles2 = norma.getArticles();
					} else if (i == 2) {
						norma3 = norma.getNormaLegal();
						articles3 = norma.getArticles();
					}
				}
                
				Long fitxerIDNorma= null;
				Long fitxerIDNorma2= null;
				Long fitxerIDNorma3= null;
                

                // Truncate a 250 chars
                normaLegal = Utils.crop(normaLegal);
                enllazNormaLegal = Utils.crop(enllazNormaLegal);
                articles = Utils.crop(articles);

                java.lang.String tipusConsentiment = servei.getTipusConsentiment();
                java.lang.String enllazConsentiment = servei.getEnllazConsentiment();
                java.lang.String notes = servei.getNotes();
                java.lang.String caduca = servei.getCaduca();
                java.lang.String fechaCaduca = servei.getFechaCaduca();

                //XXX CONSENT: Estatal, no apliquen els canvis
                java.lang.String consentiment = servei.getConsentiment();
                String consentimentAux = SolicitudFullViewOperadorController.normalize(consentiment).replaceAll("\\p{M}", "");
                log.info("consentiment: ]" + consentiment + "[ Normalitzam: ]" + consentimentAux + "[ toLowerCase: ]" + consentimentAux.toLowerCase() + "[");
                switch (consentimentAux.toLowerCase()) {
                    case "sí": 
                    case "si": 
                        consentiment = Constants.CONSENTIMENT_TIPUS_SI;
                        break;

                    case "nooposicio":
                    case "nooposicion":
                    case "nooposició":
                    case "nooposición":
                    case "noop":
                    case "noopo":

                    case "no oposicio":
                    case "no oposicion":
                    case "no oposició":
                    case "no oposición":
                    case "no op":
                    case "no opo":

                    case "no_oposicio":
                    case "no_oposicion":
                    case "no_oposició":
                    case "no_oposición":
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
				
				SolicitudServeiJPA ss = new SolicitudServeiJPA(solicitudID, serveiID, estatSolicitudServeiID,
						enllazNormaLegal, tipusConsentiment, consentiment, enllazConsentiment, notes, caduca,
						fechaCaduca, normaLegal, fitxerIDNorma, articles, norma2, fitxerIDNorma2, articles2, norma3,
						fitxerIDNorma3, articles3);

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

    private static String getPIDFromSubject(String subject) {

        Pattern patron = Pattern.compile("\\[(\\d+)\\]");
        
        // Crear un objeto Matcher para encontrar coincidencias en el texto
        Matcher matcher = patron.matcher(subject);
        
        // Buscar el número entre corchetes y mostrarlo
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

}
