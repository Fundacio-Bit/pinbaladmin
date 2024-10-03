package org.fundaciobit.pinbaladmin.back.controller.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.EnumUtils;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.EventController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EventForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaEJB;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.email.EmailMessageInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.MailCedentInfo;
import org.fundaciobit.pinbaladmin.logic.utils.email.MailCedentInfo.CEDENTS_LOCALS;
import org.fundaciobit.pinbaladmin.logic.utils.email.MailCedentInfo.CODIS_SERVEIS_LOCALS;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.OperadorFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbstractEventController<T> extends EventController implements Constants {

    public static final String SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID = "SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID";

   // public static final String SESSION_EVENT_IS_ESTATAL = "SESSION_EVENT_IS_ESTATAL";

    public static final String SESSION_EVENT_CEDENT = "SESSION_EVENT_CEDENT";
    public static final String SESSION_EVENT_DESTINATARI = "SESSION_EVENT_DESTINATARI";

    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @EJB(mappedName = EventLogicaService.JNDI_NAME)
    protected EventLogicaService eventLogicaEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OperadorService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.OperadorService operadorEjb;

    @EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
    protected SolicitudServeiLogicaService soliServLogicEjb;
    
    @EJB(mappedName = org.fundaciobit.pinbaladmin.logic.ServeiLogicaService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.logic.ServeiLogicaService serveiLogicEjb;
    
    public abstract boolean isPublic();

    public abstract boolean isSolicitud();

    @Override
    public boolean isActiveFormEdit() {
        return !isPublic();
    }

    @Override
    public boolean isActiveDelete() {
        return !isPublic();
    }

    @Override
    public boolean isActiveFormView() {
        return !isPublic();
    }

    @Override
    public String getTileForm() {
        return isPublic() ? "eventFormOperadorPublic" : "eventFormOperador";
    }

    @Override
    public String getTileList() {
        return isPublic() ? "eventListOperadorIframePublic" : "eventListOperadorIframe"; // "eventListOperador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "EventOperador_FilterForm_" + isPublic() + "_" + isSolicitud();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView crearEventGet(HttpServletRequest request, HttpServletResponse response) throws I18NException {

        log.info("\n\n ENTRA A NEW");

        Long itemID = (Long) request.getSession().getAttribute(SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);
        if (itemID == null) {

            String itemNom = isSolicitud() ? "solicituID" : "incidenciaTecnicaID";

            HtmlUtils.saveMessageError(request,
                    "XXXXXXXXXX S'ha intentat editar o crear un Event però no s'ha definit el " + itemNom
                            + " a través de la sessió " + SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);

            return new ModelAndView(
                    new RedirectView(redirectWhenSessionItemIDNotDefined().replace("redirect:", ""), true));
        }

//        try {
//            T item = findItemByPrimaryKey(itemID);
//
//            String cedent = (String) request.getSession().getAttribute(SESSION_EVENT_CEDENT);
//
//            String email;
//           // String nom;
//            if (cedent != null) {
//
//                email = cedent;
//            } else {
//
//                email = getPersonaContacteEmail(item);
//                //nom = getPersonaContacteNom(item);
//
//                if (email == null || email.trim().length() == 0) {
//                    String itemNom = isSolicitud() ? "solicitud" : "incidència";
//
//                    Boolean isEstatal = (Boolean) request.getSession().getAttribute(SESSION_EVENT_IS_ESTATAL);
//                    if (!Boolean.TRUE.equals(isEstatal)) {
//
//                        log.info("\n\n Passa per NEW AMB ERROR");
//
//                        HtmlUtils.saveMessageError(request,
//                                "XXXXXXXX No s'ha definit el email de la persona de contacte dins de la " + itemNom);
//                        return new ModelAndView(new RedirectView(
//                                getRedirectWhenCancel(request, itemID).replace("redirect:", ""), true));
//                    }
//                }
//            }
//        } finally {
//            //      request.getSession().removeAttribute(SESSION_EVENT_IS_ESTATAL);
//            //      request.getSession().removeAttribute(SESSION_EVENT_CEDENT);
//            //      request.getSession().removeAttribute(SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);
//        }

        ModelAndView mav = super.crearEventGet(request, response);

        return mav;
    }

    @Override
    public EventForm getEventForm(EventJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {

        EventForm eventForm = super.getEventForm(_jpa, __isView, request, mav);

        String itemNom = isSolicitud() ? "solicituID" : "incidenciaTecnicaID";
        Long itemID = (Long) request.getSession().getAttribute(SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);
        
        if (itemID == null) {
            HtmlUtils.saveMessageError(request, "S'ha intentat editar o crear un Event però no s'ha definit el "
                    + itemNom + " a traves de la sessió " + SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);

            mav.setView(new RedirectView(redirectWhenSessionItemIDNotDefined().replace("redirect:", ""), true));
            return eventForm;
        }

        T item = findItemByPrimaryKey(itemID);
        
        if (item == null) {
            HtmlUtils.saveMessageError(request, "S'ha intentat editar o crear un Event però el ID de " + itemNom + " ( "
                    + itemID + ") retorna un element null.");

            mav.setView(new RedirectView(redirectWhenSessionItemIDNotDefined().replace("redirect:", ""), true));
            return eventForm;
        }

        if (isSolicitud()) {
            eventForm.addHiddenField(INCIDENCIATECNICAID);
            eventForm.addHiddenField(SOLICITUDID);
        } else {
            // Incidència
            eventForm.addHiddenField(SOLICITUDID);
            eventForm.addHiddenField(INCIDENCIATECNICAID);
        }

        if (eventForm.isNou()) {

            eventForm.setCancelButtonVisible(false);

            eventForm.setTitleCode("=Nova Entrada");

            /*
             * if (isPublic()) { mav.setViewName("eventFormOperadorPublic"); } else {
             * mav.setViewName("eventFormOperador"); }
             */
            
            EventJPA ev = eventForm.getEvent();
            
            ev.setDataEvent(new Timestamp(System.currentTimeMillis()));
            if (isSolicitud()) {
                ev.setSolicitudID(itemID);
            } else {
                ev.setIncidenciaTecnicaID(itemID);
            }

            eventForm.addHiddenField(CAIDIDENTIFICADORCONSULTA);
            eventForm.addHiddenField(CAIDNUMEROSEGUIMENT);
            eventForm.addHiddenField(DATAEVENT);
            eventForm.addHiddenField(NOLLEGIT);

            if (isPublic()) {

                String cadenaDestinatari = (String) request.getSession().getAttribute(SESSION_EVENT_DESTINATARI);
                log.info("cadenaDestinatari => " + cadenaDestinatari);
                if (cadenaDestinatari != null) {
                	String[] parts = cadenaDestinatari.split("\\|");
                	String tipus = parts[0];
                	String persona = parts[1]; //Anterior destinatari del correu del que ve
					log.info("persona => " + persona + " - tipus => " + tipus);
					
					switch (tipus) {
					case "CEDENT":
						ev.setTipus(EVENT_TIPUS_CEDENT_RESPOSTA);
						ev.setPersona(persona);
						break;
					case "CONTACTE":
						ev.setTipus(EVENT_TIPUS_COMENTARI_CONTACTE);
						ev.setPersona(persona);
						break;
					}
                } else {
//                    email = getPersonaContacteEmail(item);
                    ev.setPersona(getPersonaContacteEmail(item));
                }

//                ev.setPersona(email);
                ev.setNoLlegit(true);
            } else {
            	String asumpte = "PINBAL [" + itemID + "] - ACTUALITZACIÓ " + itemNom.toUpperCase() + " - " + getTitolItem(itemID);
				if (isSolicitud()) {
					Solicitud soli = ((Solicitud) item);
					if (soli.getExpedientPid() != null) {
						asumpte = "PID [" + soli.getExpedientPid() + "] - " + asumpte;
					}
				}
				if (asumpte.length() > 255) {
					asumpte = asumpte.substring(0, 255);
				}
				ev.setAsumpte(asumpte);

            	ev.setPersona(request.getUserPrincipal().getName());
                ev.setTipus(EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT);
                ev.setNoLlegit(false);
            }
        }

        if (!isPublic()) {
            mav.addObject("persona_operador", request.getUserPrincipal().getName());
            request.getSession().setAttribute("persona_operador", request.getUserPrincipal().getName());
        }

        String email;
        String nom;

        if (eventForm.isNou()) {
            email = getPersonaContacteEmail(item);
            if (eventForm.getEvent().getTipus() == Constants.EVENT_TIPUS_COMENTARI_CONTACTE) {
            	String personaQueEraDestinatari = eventForm.getEvent().getPersona();
            	log.info("persona contacte que era destinatari=> " + personaQueEraDestinatari);
            	if (personaQueEraDestinatari != null && personaQueEraDestinatari.trim().length() > 0) {
            		nom = personaQueEraDestinatari;
            	}else {
    				nom = getPersonaContacteNom(item);
            	}
			}else {
				nom = getPersonaContacteNom(item);
			}
        }else {
            email = eventForm.getEvent().getDestinatarimail();
            nom = eventForm.getEvent().getDestinatari();
        }

        mav.addObject("persona_contacte", nom);
        request.getSession().setAttribute("persona_contacte", nom);

        mav.addObject("persona_contacte_mail", email);
        request.getSession().setAttribute("persona_contacte_mail", email);

		if (isSolicitud()) {
			Solicitud soli = ((Solicitud) item);
			if (soli.getEntitatEstatal() != null) {

				List<String[]> cedents = new java.util.ArrayList<String[]>();
				// [0] = id, [1] = nom, [2] = correu

			    				//Crear un Map con CEDENTS_LOCALS y i MailCedentInfo
				Map<CEDENTS_LOCALS, MailCedentInfo> mails = new HashMap<CEDENTS_LOCALS, MailCedentInfo>();
				for (CEDENTS_LOCALS cedent : CEDENTS_LOCALS.values()) {
					mails.put(cedent, new MailCedentInfo(cedent));
				}
				
				//Obtenir llistat de cedents de la solicitud, i guardar-los a la sessió per al select de cedents
				List<Long> serveisSolicitud = soliServLogicEjb
						.executeQuery(SolicitudServeiFields.SERVEIID, SolicitudServeiFields.SOLICITUDID.equal(itemID));
				
				for (Long serveiID : serveisSolicitud) {
					Servei serv = serveiLogicEjb.findByPrimaryKey(serveiID);
					String codi = serv.getCodi();

					if (EnumUtils.isValidEnum(CODIS_SERVEIS_LOCALS.class, codi)) {
						CODIS_SERVEIS_LOCALS codiEnum = CODIS_SERVEIS_LOCALS.valueOf(codi);
						switch (codiEnum) {
						case SVDSCTFNWS01:
							mails.get(CEDENTS_LOCALS.FAM_NOMBROSA).afegirServei(serv);
							break;
						case SVDCCAADISCAPACIDADWS01:
							mails.get(CEDENTS_LOCALS.DISCAPACITAT).afegirServei(serv);
							break;
						case SVDCCAACPCWS01:
						case SVDCCAACPASWS01:
							mails.get(CEDENTS_LOCALS.INTERVENCIO).afegirServei(serv);
							break;
						case SCDCPAJU:
							mails.get(CEDENTS_LOCALS.PADRO).afegirServei(serv);
							break;
						}
					}
				}
				
				for (MailCedentInfo mail : mails.values()) {
					if (mail.getServeis().size() > 0) {
						String dests = String.join("; ", mail.getDests());
						cedents.add(new String[] { mail.getId(), mail.getSubject(), dests });
					}
				}
				
				log.info("cedents: " + cedents.toString());
				mav.addObject("cedents", cedents);
            }
		}
		
        mav.addObject("isPublic", isPublic());
        
        eventForm.setAttachedAdditionalJspCode(true);
        return eventForm;
    }

    public abstract T findItemByPrimaryKey(Long itemID);

    public abstract String redirectWhenSessionItemIDNotDefined();

    public abstract String getPersonaContacteEmail(T item);

    public abstract String getPersonaContacteNom(T item);

    public abstract Timestamp getDataCreacio(T item);

    public abstract String getOperador(T item);

    public abstract String getCreador(T item);

    public abstract String getUrlToEditItem(T item);

    public abstract String getUrlToCloseItem(T item);

    public abstract String getUrlToChangeOperadorItem(T item);

    public abstract String getTitol(T item);

    public abstract String getCodiProc(T item);

    public abstract boolean isClosed(T item);

    @RequestMapping(value = "/veureevents/{itemStrID}", method = RequestMethod.GET)
    public String veureEvents(HttpServletRequest request, HttpServletResponse response, @PathVariable String itemStrID)
            throws I18NException {

//        Boolean isEstatal = Boolean.FALSE;
        String cedent = null;
        
        return veureEvents(request, response, itemStrID, cedent);

    }

    @RequestMapping(value = "/veureevents/{itemStrID}/{destinatari}", method = RequestMethod.GET)
    public String veureEvents(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("itemStrID") String itemStrID, @PathVariable("destinatari") String destinataritEnc)
            throws I18NException {

        Long itemID;
        if (isPublic()) {
            itemID = HibernateFileUtil.decryptFileID(itemStrID);
        } else {
            itemID = Long.parseLong(itemStrID);
        }

       // String cedent = request.getParameter("cedent");
        if (destinataritEnc == null || destinataritEnc.trim().length() == 0) {
        	log.info("/veureevents: DESTINATARI NULL");
            request.getSession().removeAttribute(SESSION_EVENT_DESTINATARI);
        } else {
            try {
            	String destinatari = HibernateFileUtil.decryptString(destinataritEnc);
            	log.info("/veureevents: CEDENT => " + destinatari);
                request.getSession().setAttribute(SESSION_EVENT_DESTINATARI, destinatari);
//                        HibernateFileUtil.getEncrypter().decrypt(cedent));
            } catch (Throwable t) {
                request.getSession().removeAttribute(SESSION_EVENT_DESTINATARI);
                log.error("Error desencriptant cedent: " + destinataritEnc + "-" + t.getMessage());
//                HtmlUtils.saveMessageWarning(request, "NO hem pogut obtenir el cedent: " + destinataritEnc);
            }
        }

        request.getSession().setAttribute(SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID, itemID);
//        request.getSession().setAttribute(SESSION_EVENT_IS_ESTATAL, isEstatal);

        return "redirect:" + getContextWeb() + "/list";

    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, EventForm eventForm) {

        log.info("Entra a getRedirectWhenCreated ... Princial => " + request.getUserPrincipal());

//        if (request.getUserPrincipal() != null) {
//            // Accés loguejat
//            EventJPA ev = eventForm.getEvent();
//
//            if (ev.getTipus() == Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC) {
//
//                Long itemID;
//                String tipus;
//                if (isSolicitud()) {
//
//                    itemID = ev.getSolicitudID();
//                    tipus = "sol·licitud";
//                } else {
//
//                    itemID = ev.getIncidenciaTecnicaID();
//                    tipus = "incidència";
//                }
//                try {
//                    final String subject = "PINBAL [" + itemID + "] - ACTUALITZACIÓ " + tipus.toUpperCase() + " - "
//                            + getTitolItem(itemID);
//                    
//                    final String from = Configuracio.getAppEmail();
//                    
//					final String message = "Bon dia;<br/><br/><b>Número " + tipus + ": " + itemID + "</b>"
//							+ "<br/><br/>"
//							+ "<div style=\"background-color:#f6f6f6;\">"
//                            + eventForm.getEvent().getComentari().replace("\n", "<br/>")
//                            + (eventForm.getEvent().getFitxerID() == null ? ""
//                                    : "<br/><br/><b>S'han adjuntat fitxers.</b>")
//                            + "</div>"
//                            
//                            + SolicitudEstatalOperadorController.getPeuCorreu(itemID, tipus);
////                            + "Podrà reobrir aquesta incidència o aportar més informació utilitzant el següent enllaç: <a href=\""
////                            +  getLinkPublic(itemID) + "\" > Accedir a " + tipus + "</a><br/><br/>" + getPeuCorreu();
//
//                    final boolean isHtml = true;
//
//                    //eventForm.getEvent().getDestinatarimail();
//                    
//                    String email = eventForm.getEvent().getDestinatarimail();
//                    ev.setDestinatarimail(email);
//                    
//                    String persona = eventForm.getEvent().getDestinatari();
//                    ev.setDestinatari(persona);
//                    
//                    FitxerJPA adjunt = ev.getFitxer();
//
//                    eventLogicaEjb.update(ev);
//                    
//                    log.info("CORREU PER ENVIAR");
//                    EmailUtil.postMail(subject, message, isHtml, from, adjunt, email);
//                    log.info("CORREU ENVIAT");
//                    
//                } catch (I18NException e) {
//                    String msg;
//                    msg = I18NUtils.getMessage(e);
//
//                    HtmlUtils.saveMessageError(request, "Error I18N enviant correu: " + msg);
//                } catch (Throwable th) {
//
//                    String msg;
//                    msg = th.getMessage();
//                    HtmlUtils.saveMessageError(request, "Error enviant correu: " + msg);
//                }
//            }
//        }

//        return getRedirectWhenCancel(request, eventForm.getEvent().getEventID());
        return "redirect:" + getContextWeb() + "/postcreateiframe";
    }
    

    @RequestMapping(value = "/postcreateiframe", method = RequestMethod.GET)
    public ModelAndView postCreateIFrame(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("postCreateIframe" + (isPublic()? "Public":"Operador"));
        if (isPublic()) {
            //XYZ ZZZ Malament, no sabem quina URL es la que ha d'anar aquí
            mav.addObject("redirect", this.getContextWeb() + "/list");
        }else {
            mav.addObject("redirect", this.getContextWeb() + "/list");
        }
        return mav;
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, EventForm eventForm, Throwable __e) {
        if (__e == null) {
            return getRedirectWhenCancel(request, eventForm.getEvent().getEventID());
        } else {
            return getTileForm();
        }
    }

    @Override
    public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long eventID, Throwable __e) {
        return getRedirectWhenCancel(request, eventID);
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long eventID) {
        return "redirect:" + getContextWeb() + "/list";
    }

    private String getTitolItem(Long itemID) throws I18NException {
        T item = findItemByPrimaryKey(itemID);
        String titol;
        log.info("item getClass" + item.getClass());
        if (item instanceof Solicitud) {
            Solicitud soli = ((Solicitud) item);
            titol = soli.getProcedimentCodi() + " " + soli.getProcedimentNom();
        } else if (item instanceof IncidenciaTecnica) {
            IncidenciaTecnica inci = ((IncidenciaTecnica) item);
            titol = inci.getTitol();
        } else {
            throw new I18NException("genapp.comodi", "No puc processar tipus " + item.getClass());
        }
        return titol;
    }

    public static final String SESSION_ENVIARCORREU_DEST = "__SESSION_ENVIARCORREU_DEST__";

    public static final String SESSION_ENVIARCORREU_ASSUMPTE = "__SESSION_ENVIARCORREU_ASSUMPTE__";

    public static final String SESSION_ENVIARCORREU_MISSATGE = "__SESSION_ENVIARCORREU_MISSATGE__";

    public static final String SESSION_ENVIARCORREU_CALLBACK = "__SESSION_ENVIARCORREU_CALLBACK__";

    public abstract String getPublicContextPath();

    public abstract String getPersonaContacteEmailByItemID(Long itemID) throws I18NException;

    public abstract String getPersonaContacteByItemID(Long itemID) throws I18NException;

    @Override
    public EventFilterForm getEventFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {

        EventFilterForm eventFilterForm = super.getEventFilterForm(pagina, mav, request);

        Long itemID = (Long) request.getSession().getAttribute(SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);
        if (itemID == null) {
            String itemNom = isSolicitud() ? "solicitud" : "incidència";
            HtmlUtils.saveMessageError(request, "S'ha intentat editar o crear un Event però no s'ha definit el "
                    + itemNom + " a traves de la sessio " + SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);
            mav.setView(new RedirectView(redirectWhenSessionItemIDNotDefined().replace("redirect:", ""), true));
            return eventFilterForm;
        }

        T item = findItemByPrimaryKey(itemID);

        String cedent = (String) request.getSession().getAttribute(SESSION_EVENT_CEDENT);

        if (cedent != null) {
            log.info("list: CEDENT => " + cedent);
            mav.addObject("cedent", cedent);
        }


        mav.addObject("operador", getOperador(item));
        mav.addObject("creador", getCreador(item));
        mav.addObject("datacreacio", SDF.format(getDataCreacio(item)));
        mav.addObject("personaContacte", getPersonaContacteNom(item));
        mav.addObject("personaContacteEmail", getPersonaContacteEmail(item));

		boolean isEstatal = false;
		if (isSolicitud()) {
			Solicitud soli = ((Solicitud) item);
			if (soli.getEntitatEstatal() != null && soli.getEntitatEstatal().trim().length() > 0) {
				isEstatal = true;
			}
		}
		mav.addObject("isEstatal", isEstatal);

        mav.addObject("urlToEditItem", getUrlToEditItem(item));

        
        if (!isClosed(item)) {
            mav.addObject("urlToCloseItem", getUrlToCloseItem(item));
        }
        
        
        mav.addObject("urlToChangeOperator", getUrlToChangeOperadorItem(item));


        mav.addObject("ID", itemID);
        mav.addObject("tipus", isSolicitud() ? "Sol·licitud" : "Incidència");
        mav.addObject("titol", getTitol(item));
        mav.addObject("iframe", request.getContextPath() + getContextWeb() + "/list");

        mav.addObject("isPublic", isPublic());

        mav.addObject("isSolicitud", isSolicitud());
        mav.addObject("procedimentCodi", getCodiProc(item));
        
        mav.addObject("contextweb", getContextWeb());

        String estat = getEstat(item);
        mav.addObject("estat", estat);

        mav.addObject("showOnlyPublic", isPublic());

        if (eventFilterForm.isNou()) {
            eventFilterForm.setOrderBy(DATAEVENT.fullName);
            eventFilterForm.setOrderAsc(true);

            eventFilterForm.addHiddenField(INCIDENCIATECNICAID);
            eventFilterForm.addHiddenField(EVENTID);
            eventFilterForm.addHiddenField(FITXERID);
            eventFilterForm.addHiddenField(NOLLEGIT);

            eventFilterForm.setItemsPerPage(null);
        }

        
        
        // Tramitadors
        {

            SelectMultipleStringKeyValue smskv;
            smskv = new SelectMultipleStringKeyValue(OperadorFields.USERNAME.select, OperadorFields.NOM.select);

            List<StringKeyValue> operadors= operadorEjb.executeQuery(smskv);

            mav.addObject("operadors", operadors);
        }

        // Tipus d'Incidencies
        {

            List<StringKeyValue> tipusIncidencies = new java.util.ArrayList<StringKeyValue>();

            tipusIncidencies.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_TECNICA), "Tècnica"));
            tipusIncidencies.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_CONSULTA), "Consulta"));
            tipusIncidencies.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_INTEGRACIONS), "Integracions"));
            tipusIncidencies.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_ROLEPERMISOS), "Roles de permisos"));

            mav.addObject("tipusIncidencies", tipusIncidencies);
        }

        // Tipus de Solicituds
        {

            List<StringKeyValue> estatSolicituds = new java.util.ArrayList<StringKeyValue>();

            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_SENSE_ESTAT), "Sense Estat"));
            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_PENDENT), "Pendent"));
            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_PENDENT_Firma_Director), "Pendent Firma Director"));
            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_PENDENT_AUTORITZAR), "Pendent d'autoritzar"));
            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_ESMENES), "Esmenes"));
            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_AUTORITZAT), "Autoritzat"));
            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_PENDENT_PINFO), "Pendent pinfo"));
            estatSolicituds.add(new StringKeyValue(String.valueOf(Constants.SOLICITUD_ESTAT_TANCAT), "Tancat"));

            mav.addObject("estatSolicituds", estatSolicituds);
        }

        return eventFilterForm;
    }

    public abstract String getEstat(T item) throws I18NException;

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        Long itemID = (Long) request.getSession().getAttribute(SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);

        Where w;
        if (itemID == null) {
            w = EventFields.DATAEVENT.isNull();
            String itemNom = isSolicitud() ? "solicitud" : "incidència";
            HtmlUtils.saveMessageError(request,
                    "S'ha cridat a veure event d'una sol·licitud però no s'ha registrat en la sessio el " + itemNom
                            + " emprant la sessio " + SESSION_EVENT_SOLICITUD_INCIDENCIATECNICA_ID);

        } else {
            w = isSolicitud() ? EventFields.SOLICITUDID.equal(itemID) : EventFields.INCIDENCIATECNICAID.equal(itemID);
        }

        return w;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        // Privat - Tramitador
        __tmp.add(new StringKeyValue(String.valueOf(EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT),
                "Tramitador: Comentari Privat"));

        // Public - Tramitador
        __tmp.add(new StringKeyValue(String.valueOf(EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC),
                "Tramitador: Enviar Correu"));

        // Public - Contacte
        __tmp.add(new StringKeyValue(String.valueOf(EVENT_TIPUS_COMENTARI_CONTACTE), "Contacte (Públic)"));

        // Public - Tramitador - Suport
        __tmp.add(new StringKeyValue(String.valueOf(EVENT_TIPUS_COMENTARI_SUPORT), "Suport CAIB"));

        // PRIVAT_TRAMITADOR CAP A CEDENT
        __tmp.add(new StringKeyValue(String.valueOf(EVENT_TIPUS_CONSULTA_A_CEDENT), "Consulta a Cedent"));

        // PRIVAT_RESPOSTA DE CEDENT
        __tmp.add(new StringKeyValue(String.valueOf(EVENT_TIPUS_CEDENT_RESPOSTA), "Resposta de Cedent"));

        return __tmp;

    }

    @Override
    public void postValidate(HttpServletRequest request, EventForm eventForm, BindingResult result)
            throws I18NException {

//        if (eventForm.getEvent().getTipus() == EVENT_TIPUS_COMENTARI_SUPORT) {
//            org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace(result,
//                    get(CAIDIDENTIFICADORCONSULTA), "genapp.validation.required",
//                    new Object[] { I18NUtils.tradueix(CAIDIDENTIFICADORCONSULTA.fullName) });
//            org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace(result, get(CAIDNUMEROSEGUIMENT),
//                    "genapp.validation.required", new Object[] { I18NUtils.tradueix(CAIDNUMEROSEGUIMENT.fullName) });
//        }

//        boolean inclouFitxer = false;
        if (eventForm.getFitxerID() == null) {
            log.info("eventForm.getFitxerID() == null");
        } else {
            CommonsMultipartFile cmf = eventForm.getFitxerID();
            log.info("cmf.getContentType() == " + cmf.getContentType());
            log.info("cmf.getOriginalFilename() == ]" + cmf.getOriginalFilename() + "[");
            log.info("cmf.getSize() == ]" + cmf.getSize() + "[");
            log.info("cmf.getFileItem() == ]" + cmf.getFileItem() + "[");
        }

        String comentari = eventForm.getEvent().getComentari();
        if (comentari == null || comentari.trim().length() == 0) {
            org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace(result, get(COMENTARI),
                    "genapp.validation.required", new Object[] { I18NUtils.tradueix(COMENTARI.fullName) });
        }
//        if (inclouFitxer == false) {
//            org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace(result, get(PERSONA),
//                    "genapp.validation.required", new Object[] { I18NUtils.tradueix(PERSONA.fullName) });
//
//        }
        
        int tipus = eventForm.getEvent().getTipus();
        
        if (tipus == EVENT_TIPUS_COMENTARI_TRAMITADOR_PUBLIC || tipus == EVENT_TIPUS_COMENTARI_SUPORT || tipus == EVENT_TIPUS_CONSULTA_A_CEDENT) {
			EventJPA event = eventForm.getEvent();
			final String itemNom = isSolicitud() ? "solicitud" : "incidencia";
			Long itemID = isSolicitud() ? event.getSolicitudID() : event.getIncidenciaTecnicaID();


			String msg = "<div>Bon dia;<br/><br/><b>Número " + itemNom  + ": " + itemID + "</b>" + "<br/><br/>"
					+ "<div style=\"background-color:#f6f6f6;\">" + event.getComentari().replace("\n", "<br/>")
					+ (event.getFitxerID() == null ? "" : "<br/><br/><b>S'han adjuntat fitxers.</b>") + "</div></div>";
			
			event.setComentari(msg);
			
//			String subject = "PINBAL [" + itemID + "] - ACTUALITZACIÓ " + tipus.toUpperCase() + " - " + getTitolItem(itemID);
//			event.setAsumpte(subject);
        }
    }

    @Override
    public EventJPA create(HttpServletRequest request, EventJPA event) throws I18NException, I18NValidationException {
        return (EventJPA) eventLogicaEjb.create(event);
    }

    public abstract Long getItemID(T item);

    @RequestMapping(value = "/marcarcomllegida/{itemID}", method = RequestMethod.GET)
    public String marcarEntradesComLlegides(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long itemID) {

        //Where per obtenir els events de la incidencia/solicitud especifica
        Where whereComu;

        if (isSolicitud()) {
            whereComu = EventFields.SOLICITUDID.equal(itemID);
        } else {
            whereComu = EventFields.INCIDENCIATECNICAID.equal(itemID);
        }

        List<Event> eventsNoLlegits;
        try {
            eventsNoLlegits = eventLogicaEjb.select(Where.AND(whereComu, EventFields.NOLLEGIT.equal(Boolean.TRUE)));

            for (Event event : eventsNoLlegits) {
                event.setNoLlegit(false);
                eventLogicaEjb.update(event);
            }

        } catch (I18NException e) {
            // TODO Auto-generated catch block
            String msg = "Error marcant com a llegits les entrades de " + itemID + ": " + I18NUtils.getMessage(e);
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }
        return "redirect:" + getContextWeb() + "/veureevents/" + itemID;
    }

    @RequestMapping(value = "/marcarcomnollegida/{itemID}", method = RequestMethod.GET)
    public String marcarEntradesComNoLlegides(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long itemID) {

        //Where per obtenir els events de la incidencia/solicitud especifica
        Where whereComu;

        if (isSolicitud()) {
            whereComu = EventFields.SOLICITUDID.equal(itemID);
        } else {
            whereComu = EventFields.INCIDENCIATECNICAID.equal(itemID);
        }

        List<Event> eventsNoLlegits;
        try {
            eventsNoLlegits = eventLogicaEjb.select(whereComu);

            for (Event event : eventsNoLlegits) {
                event.setNoLlegit(true);
                eventLogicaEjb.update(event);
            }

        } catch (I18NException e) {
            // TODO Auto-generated catch block
            String msg = "Error marcant com a no llegits les entrades de " + itemID + ": " + I18NUtils.getMessage(e);
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }
        return "redirect:" + getContextWeb() + "/veureevents/" + itemID;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, EventFilterForm filterForm, List<Event> list)
			throws I18NException {

		if (list.size() > 0) {
			int noLlegits = 0;
			for (Event event : list) {
				if (event.isNoLlegit()) {
					noLlegits++;
				}
			}

			long itemID;

			if (isSolicitud()) {
				itemID = list.get(0).getSolicitudID();
			} else {
				itemID = list.get(0).getIncidenciaTecnicaID();
			}

			if (noLlegits == 0) {
				mav.addObject("urlMarcarComNoLlegides",
						request.getContextPath() + getContextWeb() + "/marcarcomnollegida/" + itemID);
			} else {
				mav.addObject("urlMarcarComLlegides",
						request.getContextPath() + getContextWeb() + "/marcarcomllegida/" + itemID);
			}
		}
	}
}
