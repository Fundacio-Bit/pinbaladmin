package org.fundaciobit.pinbaladmin.back.preparer;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.security.RunAs;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.fundaciobit.pinbaladmin.ejb.IdiomaService;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Idioma;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.EventQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.IdiomaFields;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectCount;
import org.fundaciobit.genapp.common.query.SelectDistinct;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.back.security.LoginInfo;

import org.fundaciobit.pinbaladmin.commons.utils.Constants;

/**
 * @author anadal
 *
 */
@RunAs(Constants.PAD_USER)
@Component
public class BasePreparer implements ViewPreparer, Constants {
    /*
    	protected final Logger log = Logger.getLogger(getClass());
    
    	protected static IdiomaService idiomaService;
    
    	@Override
    	public void execute(Request tilesRequest, AttributeContext attributeContext) throws PreparerException {
    
    		Map<String, Object> request = tilesRequest.getContext("request");
    
    		// Informació de Login
    		try {
    		LoginInfo loginInfo = LoginInfo.getInstance();
    		// Posa dins la sessió la informació de Login
    		request.put("loginInfo", loginInfo);
    		} catch(Exception e) {
    		    // Anònim
    		}
    
    		// URL
    		// TODO ficarho dins cache (veure Capperpare.java)
    		HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
    				.getRequest();
    		{
    
    			request.put("urlActual", httpRequest.getServletPath());
    
    			// Compatibilitat IE8
    			String userAgent = httpRequest.getHeader("User-Agent");
    			if (userAgent != null) {
    				int index = userAgent.toUpperCase().indexOf("MSIE");
    				if (index != -1) {
    					try {
    						String ieversion = userAgent.substring(index + 4, userAgent.indexOf(";", index + 4));
    						if (Float.parseFloat(ieversion) < 9.0f) {
    							request.put("IE8", true);
    						}
    					} catch (Throwable e) {
    						log.debug(e);
    					}
    				}
    			}
    		}
    
    		// Language
    		Locale loc = LocaleContextHolder.getLocale();
    		request.put("lang", loc.toString()); // LANG i si es necessari el country
    		request.put("onlylang", loc.getLanguage()); // només el LANG
    
    		// Pipella
    		request.put("pipella", attributeContext.getAttribute("pipella"));
    
    		// TODO GENAPP
    		// Warning for each ROLE
    
    		// Avisos
    		Map<String, Long> avisos = new HashMap<String, Long>();
    		// avisos.put(rol, <<Number of warnings>>);
    		request.put("avisos", avisos);
    
    		// Idiomes
    		try {
    			if (idiomaService == null) {
    				idiomaService = (IdiomaService) new InitialContext().lookup(IdiomaService.JNDI_NAME);
    			}
    
    			List<Idioma> idiomes = idiomaService.select(IdiomaFields.SUPORTAT.equal(true));
    			httpRequest.getSession().setAttribute("idiomes", idiomes);
    
    		} catch (Throwable e) {
    			log.error("Preparer:: Error agafant idiomes de la base de dades: " + e.getMessage(), e);
    		}
    		
    		if (attributeContext.getAttribute("menu") != null) {
    			request.put("menu_tile", attributeContext.getAttribute("menu").toString());
    		}
    
    		// attributeContext.putAttribute("menu", new
    		// Attribute("/WEB-INF/jsp/moduls/menu_inici.jsp"));
    
    		request.put("contingut_tile", attributeContext.getAttribute("contingut").toString());
    
    	}
    
    */

    protected static IdiomaService idiomaService;

    protected final Logger log = Logger.getLogger(getClass());

    protected static EventLogicaService eventLogicaEjb;

    @Override
    public void execute(Request tilesRequest, AttributeContext attributeContext) throws PreparerException {

        Map<String, Object> request = tilesRequest.getContext("request");

        // Informació de Login
        try {
            LoginInfo loginInfo = LoginInfo.getInstance();
            // Posa dins la sessió la informació de Login
            request.put("loginInfo", loginInfo);
        } catch (Exception e) {
            // Anònim
        }

        // URL
        // TODO ficarho dins cache (veure Capperpare.java)
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        {

            request.put("urlActual", httpRequest.getServletPath());

            // Compatibilitat IE8
            String userAgent = httpRequest.getHeader("User-Agent");
            if (userAgent != null) {
                int index = userAgent.toUpperCase().indexOf("MSIE");
                if (index != -1) {
                    try {
                        String ieversion = userAgent.substring(index + 4, userAgent.indexOf(";", index + 4));
                        if (Float.parseFloat(ieversion) < 9.0f) {
                            request.put("IE8", true);
                        }
                    } catch (Throwable e) {
                        log.debug(e);
                    }
                }
            }
        }

        // Language
        Locale loc = LocaleContextHolder.getLocale();
        request.put("lang", loc.toString()); // LANG i si es necessari el country
        request.put("onlylang", loc.getLanguage()); // només el LANG

        // Pipella
        Attribute pipellaAttr = attributeContext.getAttribute("pipella");
        String pipella = (pipellaAttr == null) ? null : pipellaAttr.toString();
        request.put("pipella", pipella);

        // TODO GENAPP
        // Warning for each ROLE

        // Avisos
        Map<String, Long> avisos = new HashMap<String, Long>();
        // avisos.put(rol, <<Number of warnings>>);
        request.put("avisos", avisos);

        // Idiomes
        try {
            if (idiomaService == null) {
                idiomaService = (IdiomaService) new InitialContext().lookup(IdiomaService.JNDI_NAME);
            }

            List<Idioma> idiomes = idiomaService.select(IdiomaFields.SUPORTAT.equal(true));
            httpRequest.getSession().setAttribute("idiomes", idiomes);

        } catch (Throwable e) {
            log.error("Preparer:: Error agafant idiomes de la base de dades: " + e.getMessage(), e);
        }

        if (attributeContext.getAttribute("menu") != null) {
            request.put("menu_tile", attributeContext.getAttribute("menu").toString());
        }

        // attributeContext.putAttribute("menu", new
        // Attribute("/WEB-INF/jsp/moduls/menu_inici.jsp"));

        request.put("contingut_tile", attributeContext.getAttribute("contingut").toString());

        //log.info("\n\n PIPELLA => " +  pipella + "\n\n");

        // Cerca d'Events no llegits
        if ("operador".equals(pipella)) {
            try {
                if (eventLogicaEjb == null) {
                    eventLogicaEjb = (EventLogicaService) new InitialContext().lookup(EventLogicaService.JNDI_NAME);
                }

                String user = httpRequest.getRemoteUser();

                log.info("BasePreparer => httpRequest:  " + httpRequest);
                log.info("BasePreparer => httpRequest.getRemoteUser():  " + user);
                log.info("BasePreparer => eventLogicaEjb:  " + eventLogicaEjb);

                Where wNoLlegit = EventFields.NOLLEGIT.equal(Boolean.TRUE);
                
                //SOLICITUDS
                {
                    StringField operador = new EventQueryPath().SOLICITUD().OPERADOR();
                    Where wComu = Where.AND(wNoLlegit, EventFields.SOLICITUDID.isNotNull());
                    SelectDistinct<Long> sd = new SelectDistinct<Long>(EventFields.SOLICITUDID);
                    SelectCount sc = new SelectCount(sd);

                    // solicituds locals meves i no meves
                    Long solicitudsLocalsMeves = eventLogicaEjb.executeQueryOne(sc, Where.AND(wComu, operador.equal(user)));
                    Long solicitudsLocalsNoMeves = eventLogicaEjb.executeQueryOne(sc, Where.AND(wComu, operador.notEqual(user)));

                    request.put("solicitudsLocalsMeves", solicitudsLocalsMeves);
                    request.put("solicitudsLocalsNoMeves", solicitudsLocalsNoMeves);
                }

                //INCIDENCIES
                {
                    StringField operador = new EventQueryPath().INCIDENCIATECNICA().OPERADOR();

                    Where wComu = Where.AND(wNoLlegit, EventFields.INCIDENCIATECNICAID.isNotNull());
                    SelectDistinct<Long> sd = new SelectDistinct<Long>(EventFields.INCIDENCIATECNICAID);
                    SelectCount sc = new SelectCount(sd);

                    // incidencies meves i no meves
                    Long incidenciesMeves = eventLogicaEjb.executeQueryOne(sc, Where.AND(wComu, operador.equal(user)));
                    Long incidenciesNoMeves = eventLogicaEjb.executeQueryOne(sc, Where.AND(wComu, operador.notEqual(user)));

                    request.put("incidenciesMeves", incidenciesMeves);
                    request.put("incidenciesNoMeves", incidenciesNoMeves);
                }

            } catch (I18NException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Throwable e) {
                log.error("Preparer:: Error agafant idiomes de la base de dades: " + e.getMessage(), e);
            }

        }

    }

}