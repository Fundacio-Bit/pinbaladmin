package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.EmailController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EmailFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EmailForm;
import org.fundaciobit.pinbaladmin.back.security.LoginInfo;
import org.fundaciobit.pinbaladmin.back.utils.email.EmailReader;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailAttachmentInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.model.entity.Email;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.EmailFields;
import org.fundaciobit.pinbaladmin.model.fields.OperadorFields;
import org.fundaciobit.pinbaladmin.persistence.EmailJPA;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/llistacorreus")
@SessionAttributes(types = { EmailForm.class, EmailFilterForm.class })
public class LlistaCorreusOperadorController extends EmailController {

    public static final String CACHE_DE_EMAILS_LLEGITS = "__CACHE_DE_EMAILS_LLEGITS__";

    public static final String CACHE_SIZE_DE_EMAILS_LLEGITS = "__CACHE_SIZE_DE_EMAILS_LLEGITS__";

    public static final String MOSTRAR_MISSATGE_ARXIU = "__mostrarMissatgeArxiu__";

    public static final int MISSATGE22 = 1;

    public static final int ATTACHMENTS = 2;

    @EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
    protected IncidenciaTecnicaLogicaService incidenciaTecnicaLogicaEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.ServeiService serveiEjb;

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @EJB(mappedName = EventLogicaService.JNDI_NAME)
    protected EventLogicaService eventLogicaEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OperadorService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.OperadorService operadorEjb;

    @Override
    public String getTileForm() {
        return "emailFormOperador";
    }

    @Override
    public String getTileList() {
        return "emailListOperador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "LlistatCorreus_FilterForm";
    }

    @Override
    public EmailFilterForm getEmailFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        EmailFilterForm emailFilterForm = super.getEmailFilterForm(pagina, mav, request);

        // TODO falten botons laterals
        if (emailFilterForm.isNou()) {

            emailFilterForm.setAddButtonVisible(false);
            emailFilterForm.setEditButtonVisible(false);

            emailFilterForm.setVisibleMultipleSelection(true);            
            emailFilterForm.setDeleteSelectedButtonVisible(true);

            emailFilterForm.setOrderBy(DATAENVIAMENT.javaName);
            emailFilterForm.setOrderAsc(false);

            emailFilterForm.setGroupByFields(new ArrayList<Field<?>>());
            emailFilterForm.setFilterByFields(new ArrayList<Field<?>>());

            emailFilterForm.addHiddenField(DESTINATARIS);
            emailFilterForm.addHiddenField(MESSAGE);
            emailFilterForm.addHiddenField(EMAILID);

            emailFilterForm.setItemsPerPage(5);
            //emailFilterForm.setAllItemsPerPage(new int[] { 5 });

            emailFilterForm.setVisibleExportList(false);

            emailFilterForm.setAttachedAdditionalJspCode(true);

            emailFilterForm.setActionsRenderer(EmailFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);

            emailFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_WARNING,
                    "incidencia.convertir", "javascript:crearIncidencia({0})", AdditionalButtonStyle.WARNING)); // getContextWeb() + "/incidencia/{0}"

            // Afegir boto per Solicitud
            emailFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_LIST,
                    "solicitud.convertir", "javascript:crearSolicitud({0})", AdditionalButtonStyle.SUCCESS)); // getContextWeb() + \"/solicitud/{0}\"
        }

        // Tramitadors
        {
            SelectMultipleStringKeyValue smskv;
            smskv = new SelectMultipleStringKeyValue(OperadorFields.USERNAME.select, OperadorFields.NOM.select);

            List<StringKeyValue> operadors = operadorEjb.executeQuery(smskv);
            mav.addObject("operadors", operadors);
        }

        {
        	// Tipus d'Incidencies
            List<StringKeyValue> tipusIncidencies = new java.util.ArrayList<StringKeyValue>();

			int[] tipus = { Constants.INCIDENCIA_TIPUS_TECNICA, Constants.INCIDENCIA_TIPUS_CONSULTA,
					Constants.INCIDENCIA_TIPUS_INTEGRACIONS, Constants.INCIDENCIA_TIPUS_ROLEPERMISOS };
			String[] tipusNames = { "Tècnica", "Consulta", "Integracions", "Roles de permisos" };
			
			for (int i = 0; i < tipus.length; i++) {
				tipusIncidencies.add(new StringKeyValue(String.valueOf(tipus[i]), tipusNames[i]));
			}
            
//            tipusIncidencies.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_TECNICA), "Tècnica"));
//            tipusIncidencies.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_CONSULTA), "Consulta"));
//            tipusIncidencies
//                    .add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_INTEGRACIONS), "Integracions"));
//            tipusIncidencies.add(
//                    new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_ROLEPERMISOS), "Roles de permisos"));

            mav.addObject("tipusIncidencies", tipusIncidencies);
        }
        
        {
        	// Tipus de Solicituds
            List<StringKeyValue> estatSolicituds = new java.util.ArrayList<StringKeyValue>();

			long[] estats = { Constants.SOLICITUD_ESTAT_SENSE_ESTAT, Constants.SOLICITUD_ESTAT_PENDENT,
					Constants.SOLICITUD_ESTAT_PENDENT_Firma_Director, Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR,
					Constants.SOLICITUD_ESTAT_ESMENES, Constants.SOLICITUD_ESTAT_AUTORITZAT,
					Constants.SOLICITUD_ESTAT_PENDENT_PINFO, Constants.SOLICITUD_ESTAT_TANCAT };
			
			String[] estatsNames = { "Sense Estat", "Pendent", "Pendent Firma Director", "Pendent d'autoritzar", "Esmenes", "Autoritzat", "Pendent pinfo", "Tancat" };
            
			for (int i = 0; i < estats.length; i++) {
				estatSolicituds.add(new StringKeyValue(String.valueOf(estats[i]), estatsNames[i]));
			}
			
//            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_SENSE_ESTAT), "Sense Estat"));
//            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_PENDENT), "Pendent"));
//            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_PENDENT_Firma_Director), "Pendent Firma Director"));
//            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR), "Pendent d'autoritzar"));
//            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_ESMENES), "Esmenes"));
//            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_AUTORITZAT), "Autoritzat"));
//            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_PENDENT_PINFO), "Pendent pinfo"));
//            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_TANCAT), "Tancat"));

            mav.addObject("estatSolicituds", estatSolicituds);
        }

        Boolean mostrarMissatgeArxiu = (Boolean) request.getSession().getAttribute(MOSTRAR_MISSATGE_ARXIU);

        if (mostrarMissatgeArxiu == null) {
            mostrarMissatgeArxiu = false;
            request.getSession().setAttribute(MOSTRAR_MISSATGE_ARXIU, false);
        }

        emailFilterForm.getAdditionalButtons().clear();
		if (mostrarMissatgeArxiu) {
			emailFilterForm.addAdditionalButton(new AdditionalButton("fas fa-eye-slash", "amagarmissatgearxiu",
					getContextWeb() + "/amagarmissatgearxiu", AdditionalButtonStyle.INFO));

			{
				AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
				adfield4.setCodeName(EmailFields.MESSAGE.fullName);
				adfield4.setPosition(MISSATGE22);
				// Els valors s'ompliran al mètode postList()
				adfield4.setValueMap(new HashMap<Long, String>());
				adfield4.setEscapeXml(false);

				emailFilterForm.addAdditionalField(adfield4);
			}

			{
				AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
				adfield4.setCodeName("arxiu");
				adfield4.setPosition(ATTACHMENTS);
				// Els valors s'ompliran al mètode postList()
				adfield4.setValueMap(new HashMap<Long, String>());
				adfield4.setEscapeXml(false);

				emailFilterForm.addAdditionalField(adfield4);
			}
		} else {
            emailFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_EYE, "mostrarmissatgearxiu",
                    getContextWeb() + "/mostrarmissatgearxiu", AdditionalButtonStyle.INFO));
            
            emailFilterForm.getAdditionalFields().remove(ATTACHMENTS);
            emailFilterForm.getAdditionalFields().remove(MISSATGE22);
            
        }

        return emailFilterForm;

    }

    @RequestMapping(value = "/amagarmissatgearxiu", method = RequestMethod.GET)
    public String amagarmissatgearxiu(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute(MOSTRAR_MISSATGE_ARXIU, false);

        return "redirect:" + getContextWeb() + "/list";

    }

    @RequestMapping(value = "/mostrarmissatgearxiu", method = RequestMethod.GET)
    public String mostrarmissatgearxiu(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute(MOSTRAR_MISSATGE_ARXIU, true);

        return "redirect:" + getContextWeb() + "/list";

    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, EmailFilterForm filterForm, List<Email> list)
			throws I18NException {

		Boolean mostrarMissatgeArxiu = (Boolean) request.getSession().getAttribute(MOSTRAR_MISSATGE_ARXIU);

		log.info("XYZ ZZZ postList(" + mostrarMissatgeArxiu + ")");
		if (mostrarMissatgeArxiu == null || !mostrarMissatgeArxiu) {
			log.info("No mostrar missatge arxiu");
		} else {
			log.info("Mostrar missatge arxiu");

			Map<Long, String> map;
			map = (Map<Long, String>) filterForm.getAdditionalField(MISSATGE22).getValueMap();
			map.clear();

			Map<Long, String> mapAttach;
			mapAttach = (Map<Long, String>) filterForm.getAdditionalField(ATTACHMENTS).getValueMap();
			mapAttach.clear();

			Map<Long, EmailMessageInfo> cache = (Map<Long, EmailMessageInfo>) request.getSession()
					.getAttribute(CACHE_DE_EMAILS_LLEGITS);

			for (Email email : list) {
				Long emailID = email.getEmailID();
				
				
				if (email.getMessage() != null) {
				//	String msg = email.getMessage().trim();

					//if ((msg.startsWith("<") && msg.endsWith(">")) || msg.endsWith("</html>")) {
						// map.put(email.getEmailID(), "<div
						// style=\"max-width:500px;max-height:400px;overflow:scroll;\">" + msg
						// + "</div>");
						// width=\"500px\" height=\"350px\"

						log.info("afegim iframe per missatge HTML");
						map.put(emailID, "<iframe src=\"" + request.getContextPath()
								+ getContextWeb() + "/message/" + emailID + "\"></iframe>");
				//	} else {
				//		log.info("afegim textarea per missatge TEXT");
				//		map.put(emailID,
				//				"<textarea readonly" + computeRowsCols(msg) + ">" + msg + "</textarea>");
				//	}
				}

				EmailMessageInfo emi = cache.get(email.getEmailID());

				if (emi.getAttachments() != null) {
					StringBuffer str = new StringBuffer();

					str.append("<div class=\"adjuntsContainer\">");

					for (EmailAttachmentInfo ads : emi.getAttachments()) {

						str.append("<div class=\"adjuntDiv\" style=\"border-style: solid;border-width:1px;\">");
						str.append("<small>-Nom: " + ads.getFileName() + "<br/>" + "-Mida: " + ads.getData().length
								+ " bytes<br/>" + "-Tipus: " + ads.getContentType());
						str.append("</small></div>");
					}
					str.append("</div>");

					mapAttach.put(email.getEmailID(), str.toString());
				}
			}
		}
		filterForm.setSubTitleCode("=Tens " + cachesize(request) + " correus sense processar ...");
	}

    protected String computeRowsCols(String msg) {
        int rows = 1;
        int cols = 1;
        if (msg != null) {
            String[] lines = msg.split("\n");
            rows = lines.length + 1;
            for (int i = 0; i < lines.length; i++) {
                cols = Math.max(cols, lines[i].length());
                if (cols > 80) {
                    cols = 80;
                    break;
                }
            }
        }

        return " rows=\"" + Math.min(rows, 70) + "\" cols=\"" + cols + "\" ";
    }

    @RequestMapping(value = "/incidencia/{emailID}/{operador}/{tipusIncidencia}", method = RequestMethod.GET)
    public String incidencia(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("emailID") Long emailID, @PathVariable("operador") String operador,
            @PathVariable("tipusIncidencia") int tipusIncidencia) {
        try {

            //Map<Long, EmailMessageInfo> cache
            // cache= (Map<Long, EmailMessageInfo>) request.getSession().getAttribute(CACHE_DE_EMAILS_LLEGITS);

            final boolean enableCertificationCheck = false;
            EmailReader er = new EmailReader(enableCertificationCheck);

            if (er.getCountMessages() == cachesize(request)) {

                //EmailMessageInfo emi = cache.get(emailID);
                EmailMessageInfo emi = er.getMessage((int) (long) emailID);

                String creador = LoginInfo.getInstance().getUsername();
                IncidenciaTecnica it = incidenciaTecnicaLogicaEjb.createFromEmail(emi, creador, operador, tipusIncidencia);
                er.deleteMessage((int) (long) emailID);

                enviarCorreusIncidencia(it);
                
        		return "redirect:/operador/eventincidenciatecnica/veureevents/" + it.getIncidenciaTecnicaID();

//                return "redirect:" + EventIncidenciaTecnicaOperadorController.CONTEXT_PATH
//                        + AbstractEventController.ENVIAR_ENLLAZ + it.getIncidenciaTecnicaID();

                // return "redirect:" + IncidenciaTecnicaOperadorController.WEBCONTEXT +
                // "/"
                // + it.getIncidenciaTecnicaID() + "/edit";

            } else {
                HtmlUtils.saveMessageWarning(request, "Ha rebut altres correus. Torni a intentar-ho.");
            }
        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);

        } catch (Exception e) {
            String msg = "Error convertint a incidència: " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

	@RequestMapping(value = "/incidenciaExistent/{emailID}/{incidenciaID}", method = RequestMethod.GET)
	public String incidenciaExistent(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("emailID") Long emailID, @PathVariable("incidenciaID") Long incidenciaID) {

        try {
            final boolean enableCertificationCheck = false;
            EmailReader er = new EmailReader(enableCertificationCheck);

            if (er.getCountMessages() == cachesize(request)) {

                EmailMessageInfo emi = er.getMessage((int) (long) emailID);

                IncidenciaTecnica it = incidenciaTecnicaLogicaEjb.afegirMailAIncidencia(emi, incidenciaID);
                
                er.deleteMessage((int) (long) emailID);

//                enviarCorreusIncidencia(it);
                
        		return "redirect:/operador/eventincidenciatecnica/veureevents/" + it.getIncidenciaTecnicaID();

            } else {
                HtmlUtils.saveMessageWarning(request, "Ha rebut altres correus. Torni a intentar-ho.");
            }
        } catch (Exception e) {
            String msg;
			if (e instanceof I18NException) {
				msg = I18NUtils.getMessage((I18NException) e);
			} else {
				msg = "Error convertint la incidencia: "  + e.getMessage();
			}
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }
		return "redirect:" + getContextWeb() + "/list";
	}

	public int cachesize(HttpServletRequest request) {
        Integer i = (Integer) request.getSession().getAttribute(CACHE_SIZE_DE_EMAILS_LLEGITS);

        if (i == null) {
            return 0;
        } else {
            return i;
        }
    }

    @RequestMapping(value = "/solicitud/{emailID}/{operador}/{estatSoli}", method = RequestMethod.GET)
    public String solicitud(HttpServletRequest request, HttpServletResponse response, @PathVariable Long emailID,
            @PathVariable("operador") String operador, @PathVariable Long estatSoli) {

        try {

            long start = System.currentTimeMillis();
            //Map<Long, EmailMessageInfo> cache;
            //cache = (Map<Long, EmailMessageInfo>) request.getSession().getAttribute(CACHE_DE_EMAILS_LLEGITS);

            final boolean enableCertificationCheck = false;
            EmailReader er = new EmailReader(enableCertificationCheck);

            if (er.getCountMessages() == cachesize(request)) {
                EmailMessageInfo emi = er.getMessage((int) (long) emailID);

                List<SolicitudJPA> solicituds = SolicitudEstatalDesDeFitxersMultiplesOperadorController.crearSolicitudsDesDeEmail(request, emi,
                        operador, log, serveiEjb, solicitudLogicaEjb);

                er.deleteMessage((int) (long) emailID);

                long end = System.currentTimeMillis();

                String titol = emi.getSubject();
				for (SolicitudJPA soli : solicituds) {
	                enviarCorreusSolicituds(soli, titol);
				}
                
                return "redirect:/operador/solicitudestatal/list/" + start + "/" + end;

            } else {
                HtmlUtils.saveMessageWarning(request, "Ha rebut altres correus. Torni a intentar-ho.");
            }
        } catch (Exception e) {
            String msg;
			if (e instanceof I18NException) {
				msg = I18NUtils.getMessage((I18NException) e);
			} else {
				msg = "Error convertint a solicitud: " + e.getMessage();
			}
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }
        return "redirect:" + getContextWeb() + "/list";
    }

	@RequestMapping(value = "/solicitudExistent/{emailID}/{solicitudID}", method = RequestMethod.GET)
	public String solicitudExistent(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("emailID") Long emailID, @PathVariable("solicitudID") Long solicitudID) {

        try {
            final boolean enableCertificationCheck = false;
            EmailReader er = new EmailReader(enableCertificationCheck);

            if (er.getCountMessages() == cachesize(request)) {

                EmailMessageInfo emi = er.getMessage((int) (long) emailID);

                Solicitud soli = solicitudLogicaEjb.afegirMailASolicitud(emi, solicitudID);
                
                er.deleteMessage((int) (long) emailID);

//                enviarCorreusSolicitud(soli);
                
        		return "redirect:/operador/eventsolicitud/veureevents/" + soli.getSolicitudID();

            } else {
                HtmlUtils.saveMessageWarning(request, "Ha rebut altres correus. Torni a intentar-ho.");
            }
        } catch (Exception e) {
            String msg;
			if (e instanceof I18NException) {
				msg = I18NUtils.getMessage((I18NException) e);
			} else {
				msg = "Error convertint la incidencia: "  + e.getMessage();
			}
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }
		return "redirect:" + getContextWeb() + "/list";
	}

	
	@Override
    public List<Email> executeSelect(ITableManager<Email, Long> ejb, Where where, final OrderBy[] orderBy,
            Integer itemsPerPage, final int inici) throws I18NException {

    	log.info("XYZ ZZZ executeSelect(" + itemsPerPage + ", " + inici + ")");
    	
        List<Email> list = new ArrayList<Email>();

        Map<Long, EmailMessageInfo> cache = new HashMap<Long, EmailMessageInfo>();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        int size = 0;

        try {

            final boolean enableCertificationCheck = false;
            EmailReader er = new EmailReader(enableCertificationCheck);

            size = er.getCountMessages();

            if (itemsPerPage == null || itemsPerPage == -1) {
                itemsPerPage = size;
            }

            final int start = 1;
            final int end = Math.min(size, itemsPerPage);
            Boolean includeAttachments = (Boolean) request.getSession().getAttribute(MOSTRAR_MISSATGE_ARXIU);

            List<EmailMessageInfo> emails = er.list(start, end, includeAttachments);

            for (EmailMessageInfo emi : emails) {
            	log.info("XYZ ZZZ email: " + emi.getDisplayFrom());
                EmailJPA e = message2email(emi);
            	log.info("XYZ ZZZ email: " + e.getEnviador());

                list.add(e);

                cache.put((long) emi.getNumber(), emi);

            }

        } catch (Exception e) {

            String msg = "Error llistant correus: " + e.getMessage();

            log.error(msg, e);

            HtmlUtils.saveMessageError(request, msg);
        }

        /*
        if (orderBy == null || orderBy.length == 0) {
          log.info("OrderBy NULL o buit");
        } else {
          for (OrderBy o : orderBy) {
        log.info("   - OrderBy[" + o.javaName + "]: " + o.orderType);
        
        if (o.javaName.equals(DATAENVIAMENT.javaName)) {
          Collections.sort(list, new EmailDateComparator(orderBy[0].orderType));
          break;
        }
          }
        }
        */

        request.getSession().setAttribute(CACHE_DE_EMAILS_LLEGITS, cache);
        request.getSession().setAttribute(CACHE_SIZE_DE_EMAILS_LLEGITS, size);
        return list;
    }

    /**
     * 
     * @author anadal
     *
     */
    public class EmailDateComparator implements Comparator<Email> {

        private final int orderType;

        public EmailDateComparator(OrderType asc) {
            this.orderType = asc.equals(OrderType.ASC) ? 1 : -1;
        }

        @Override
        public int compare(Email o1, Email o2) {
            return orderType * o1.getDataEnviament().compareTo(o2.getDataEnviament());
        }

    }

    private EmailJPA message2email(EmailMessageInfo emi) {
        if (emi == null) {
            return null;
        }

        EmailJPA e;
        long emailID = emi.getNumber();
        java.sql.Timestamp dataEnviament = new Timestamp(emi.getSentDate().getTime());
//        java.lang.String enviador = emi.getDisplayFrom();
        java.lang.String origen = emi.getNameFrom() + " (" + emi.getDisplayFrom() + ")";
        java.lang.String destinataris = emi.getDisplayTo();
        java.lang.String subject = emi.getSubject();
        java.lang.String message = emi.getBody();
        e = new EmailJPA(emailID, dataEnviament, origen, destinataris, subject, message);
        return e;
    }

    @RequestMapping(value = "/message/{emailID}")
    public void getMessageEmail(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("emailID") java.lang.Long emailID) {

        try {
//        	log.info("XYZ ZZZ getMessageEmail(" + emailID + ")");
        	//Si tenim el missatge a la cache, no el cercam.
			Map<Long, EmailMessageInfo> cache = (Map<Long, EmailMessageInfo>) request.getSession()
					.getAttribute(CACHE_DE_EMAILS_LLEGITS);
			
			EmailMessageInfo emi = cache.get(emailID);
			String msg = emi.getBody();
//        	log.info("msg length =  " + msg.length() );
			
//            email = this.findByPrimaryKey(request, emailID);

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            PrintWriter os = response.getWriter();
            os.print("<html><body>");
            os.print(msg);
            os.print("</body></html>");
            os.flush();
            os.close();

        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void delete(HttpServletRequest request, Email email) throws I18NException {
        final boolean enableCertificationCheck = false;
        EmailReader er = new EmailReader(enableCertificationCheck);
        // TODO Check si el numero de emails ha canviat
        try {
            er.deleteMessage((int) email.getEmailID());
        } catch (Exception e) {
            String msg = "Error esborrant Missatge de Correu: " + e.getMessage();
            log.error(msg, e);
            throw new I18NException("comodi", msg);
        }
    }

    @Override
    public EmailJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long emailID) throws I18NException {

        /*
        Map<Long, EmailMessageInfo> cache = (Map<Long, EmailMessageInfo>) request.getSession()
        .getAttribute(CACHE_DE_EMAILS_LLEGITS);
        
        EmailMessageInfo emi = cache.get(emailID);
        */
        final boolean enableCertificationCheck = false;
        EmailReader er = new EmailReader(enableCertificationCheck);

        EmailMessageInfo emi;
        int pos = (int) (long) emailID;
        try {
            emi = er.getMessage(pos);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new I18NException("genapp.comodi", e.getMessage());
        }

        log.info("XYZ ZZZ findByPrimaryKey(" + pos + ") => " + emi.getSubject());

        return message2email(emi);
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
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public String deleteSelected(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute EmailFilterForm filterForm) throws Exception {

        if (!isActiveDelete()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        try {
            final boolean enableCertificationCheck = false;
            EmailReader er = new EmailReader(enableCertificationCheck);

            if (er.getCountMessages() == cachesize(request)) {

                String[] seleccionats = filterForm.getSelectedItems();
                String redirect = null;
                if (seleccionats != null && seleccionats.length != 0) {
                    for (int i = seleccionats.length; i > 0; i--) {
                        redirect = eliminarEmail(stringToPK(seleccionats[i - 1]), request, response);
                    }
                }
                if (redirect == null) {
                    redirect = getRedirectWhenDelete(request, null, null);
                }

                return redirect;
            } else {
                HtmlUtils.saveMessageWarning(request, "Ha rebut altres correus. Torni a intentar-ho.");
            }
        } catch (Exception e) {
            String msg = "Error esborrant correus: " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }
        return getRedirectWhenDelete(request, null, null);
    }

	private void enviarCorreusSolicituds(SolicitudJPA soli, String titol) throws I18NException {
		
		final Long solicitudID = soli.getSolicitudID();
		final Long incidenciaTecnicaID = null;
		final String tipus = "solicitud";
		
		Long itemID = solicitudID;
//		String titol = soli.getProcedimentNom();
		
		String asumpte = "PINBAL [" + itemID + "] - ALTA " + tipus.toUpperCase() + " - " + titol;
		if (soli.getExpedientPid() != null) {
			asumpte = "PID [" + soli.getExpedientPid() + "] - " + asumpte;
		}
		String msg = "Bon dia;<br/><br/><b>Número " + tipus + ": " + itemID + "</b><br/><br/>"
				+ "    Des de la Fundació Bit l'informam que la seva " + tipus + " titulada <br><b>'" + titol
				+ "</b>'<br>" + " ha estat rebuda correctament i es troba en estudi.<br/><br/>";
		
		
		enviarCorreu(soli.getPersonaContacteEmail(), soli.getPersonaContacte(), asumpte, msg, solicitudID, incidenciaTecnicaID, Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC);

		boolean condition = true;
		if (condition) {
			enviarCorreu(Configuracio.getCorreuSuport(), "Suport CAIB", asumpte, msg, solicitudID, incidenciaTecnicaID, Constants.EVENT_TIPUS_COMENTARI_SUPORT);
		}
	}
	
	private void enviarCorreusIncidencia(IncidenciaTecnica it) throws I18NException {
		
		final Long solicitudID = null;
		final Long incidenciaTecnicaID = it.getIncidenciaTecnicaID();
		final String tipus = "incidencia";

		Long itemID = incidenciaTecnicaID;
		String titol = it.getTitol();

		final String subject = "PINBAL [" + itemID + "] - ALTA " + tipus.toUpperCase() + " - " + titol;

		String msg = "Bon dia;<br/><br/><b>Número " + tipus + ": " + itemID + "</b><br/><br/>"
				+ "    Des de la Fundació Bit l'informam que la seva " + tipus + " titulada <br><b>'" + titol
				+ "</b>'<br>" + " ha estat rebuda correctament i es troba en estudi.<br/><br/>";

		enviarCorreu(it.getContacteEmail(), it.getContacteNom(), subject, msg, solicitudID, incidenciaTecnicaID, Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC);

		if (titol.indexOf("CAI-") > 0) {
			enviarCorreu(Configuracio.getCorreuSuport(), "Suport CAIB", subject, msg, solicitudID, incidenciaTecnicaID, Constants.EVENT_TIPUS_COMENTARI_SUPORT);
		}
	}

	private void crearEventPerCorreu(String mail, String destinatariNom, String subject, String msg, Long solicitudID,
			Long incidenciaTecnicaID, int tipus) throws I18NException {

		final Timestamp data = new Timestamp(System.currentTimeMillis());
		final String caidIdentificadorConsulta = null;
		final String caidNumeroSeguiment = null;

		String _persona_ = "PinbalAdmin"; // Quien envia el mensaje

		Long _fitxerID_ = null;
		boolean _noLlegit_ = false;

		EventJPA event = new EventJPA();
		event.setSolicitudID(solicitudID);
		event.setIncidenciaTecnicaID(incidenciaTecnicaID);
		event.setDataEvent(data);
		event.setTipus(tipus);
		event.setPersona(_persona_);
		event.setDestinatari(destinatariNom);
		event.setDestinatarimail(mail);
		event.setAsumpte(subject);
		event.setComentari(msg);
		event.setFitxerID(_fitxerID_);
		event.setNoLlegit(_noLlegit_);
		event.setCaidIdentificadorConsulta(caidIdentificadorConsulta);
		event.setCaidNumeroSeguiment(caidNumeroSeguiment);

		eventLogicaEjb.create(event);
	}

	private void enviarCorreu(String mail, String destinatari, String asumpte, String msg, Long soliID, Long incidenciaID, int tipus)
			throws I18NException {

		log.info("Cream Event Public i enviam un correu a " + destinatari + " <" + mail + ">");
		msg = "<div>" + msg + "</div>";

		if (mail.equals("pinbal@fundaciobit.org")) {
			log.error("No enviam correu a " + destinatari);
			return;
		}
		if (asumpte.length() > 255) {
			asumpte = asumpte.substring(0, 255);
		}

		crearEventPerCorreu(mail, destinatari, asumpte, msg, soliID, incidenciaID, tipus);
	}
}
