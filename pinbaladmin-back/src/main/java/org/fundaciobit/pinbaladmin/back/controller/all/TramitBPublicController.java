package org.fundaciobit.pinbaladmin.back.controller.all;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitBOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitCOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitBDadesSoliController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitBDadesSoliLogicaService;
import org.fundaciobit.pinbaladmin.model.fields.TramitBDadesSoliFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitBDadesSoliFields;
import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPA;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitBPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitBDadesSoliForm.class, TramitBDadesSoliFilterForm.class })
public class TramitBPublicController extends TramitBOperadorController {

    public static final String CONTEXT_WEB = "/public/tramitb";
    public static final String CONTEXT_WEB_NEXT = TramitCPublicController.CONTEXT_WEB;

    @Override
    public String getTileForm() {
        return "tramitBFormPublic";
    }
    
    @Override
    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }
    
}
