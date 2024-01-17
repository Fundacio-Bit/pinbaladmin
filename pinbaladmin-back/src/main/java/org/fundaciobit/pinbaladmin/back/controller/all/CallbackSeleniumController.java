package org.fundaciobit.pinbaladmin.back.controller.all;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 * @author anadal
 *
 */
@Controller
public class CallbackSeleniumController implements Constants {

    protected final Logger log = Logger.getLogger(this.getClass());

    public static final String CALLBACK_SELENIUM_CONTEXT = "/public/callbackselenium";

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @RequestMapping(value = CALLBACK_SELENIUM_CONTEXT
            + "/{soliID}/{incidencia}/{seguiment}", method = RequestMethod.GET)
    public void generarFormulariCaidGet(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("soliID") Long soliID, @PathVariable("incidencia") String incidencia,
            @PathVariable("seguiment") String seguiment) throws I18NException {

        try {

            log.info("\n\n  REBUT CALLBACK: soliID => " + soliID + " | incidencia => " + incidencia + " | seguiment => "
                    + seguiment + "\n\n");

       //     solicitudLogicaEjb.updateCAID(soliID, incidencia, seguiment);

            response.getWriter().append("OK");
        } catch (IOException e) {
            log.error("Error processant el callbak de Selenium: " + e.getMessage(), e);
        }

    }
}