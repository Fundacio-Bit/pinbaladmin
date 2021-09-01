package org.fundaciobit.pinbaladmin.back.preparer;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.logic.EventLogicaLocal;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.EventQueryPath;
import org.fundaciobit.pinbaladmin.utils.Constants;



/**
 * @author anadal
 *
 */
@RunAs("PAD_USER")
@Component
public class BasePreparer extends ViewPreparerSupport implements Constants {
  

  protected final Logger log = Logger.getLogger(getClass());
  
  @EJB(mappedName = EventLogicaLocal.JNDI_NAME)
  protected EventLogicaLocal eventLogicaEjb;

  
	@Override
	public void execute(TilesRequestContext tilesContext, 
	    AttributeContext attributeContext) throws PreparerException {

	  Map<String, Object> request = tilesContext.getRequestScope();

	  
   	// URL 
	  // TODO ficarho dins cache (veure Capperpare.java)
	  Object[] requestObjects = tilesContext.getRequestObjects();
	  HttpServletRequest httpRequest = null;
	  if (requestObjects[0] instanceof HttpServletRequest) {
	    httpRequest = (HttpServletRequest) requestObjects[0];

	    
	    request.put("urlActual", httpRequest.getServletPath());

      // Compatibilitat IE8
	    String userAgent = httpRequest.getHeader("User-Agent");
	    if (userAgent != null) {
	      int index = userAgent.toUpperCase().indexOf("MSIE");
	      if (index != -1) {
	        try {
	           String ieversion = userAgent.substring(index + 4,userAgent.indexOf(";", index + 4));
	           if (Float.parseFloat(ieversion) < 9.0f) {
	             request.put("IE8", true);
	           }
	        } catch(Throwable e) {
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
    String pipella = (attributeContext.getAttribute("pipella") == null)? null :attributeContext.getAttribute("pipella").toString();
    request.put("pipella", pipella);

    //log.info("\n\n PIPELLA => " +  pipella + "\n\n");
    
    // Cerca d'Events no llegits
    if ("operador".equals(pipella)) {
      try {
        // solicituds locals meves
        StringField creador = new EventQueryPath().SOLICITUD().CREADOR();

        Where wComu = Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE),
            EventFields.SOLICITUDID.isNotNull());

        Long solicitudsLocalsMeves = eventLogicaEjb
            .count(Where.AND(wComu, creador.equal(httpRequest.getRemoteUser())));

        // solicituds locals No Meves
        Long solicitudsLocalsNoMeves = eventLogicaEjb
            .count(Where.AND(wComu, creador.notEqual(httpRequest.getRemoteUser())));

        // incidencies meves
        
        creador = new EventQueryPath().INCIDENCIATECNICA().CREADOR();
        
        wComu = Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE),
            EventFields.INCIDENCIATECNICAID.isNotNull());

        Long incidenciesMeves = eventLogicaEjb
            .count(Where.AND(wComu , creador.equal(httpRequest.getRemoteUser()) ));

        // incidencies No Meves

        Long incidenciesNoMeves = eventLogicaEjb
            .count(Where.AND(wComu, creador.notEqual(httpRequest.getRemoteUser())));

        request.put("solicitudsLocalsMeves", solicitudsLocalsMeves);
        request.put("solicitudsLocalsNoMeves", solicitudsLocalsNoMeves);
        request.put("incidenciesMeves", incidenciesMeves);
        request.put("incidenciesNoMeves", incidenciesNoMeves);

      } catch (I18NException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }

    // TODO GENAPP
    // Warning for each ROLE 
    
    // Avisos
    Map<String,Long> avisos = new HashMap<String, Long>(); 
    //avisos.put(rol, <<Number of warnings>>);
    request.put("avisos", avisos); 

    

    
    if (attributeContext.getAttribute("menu") != null) {
      request.put("menu_tile", attributeContext.getAttribute("menu").toString());
    }
    request.put("contingut_tile", attributeContext.getAttribute("contingut").toString());


    
	}

}
