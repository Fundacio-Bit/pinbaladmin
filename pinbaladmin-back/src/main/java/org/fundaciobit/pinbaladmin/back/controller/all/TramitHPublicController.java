package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitEOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitHOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitHProcController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitHProcLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
import org.fundaciobit.pinbaladmin.model.fields.TramitHProcFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitHProcFields;
import org.fundaciobit.pinbaladmin.persistence.TramitHProcJPA;
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
@RequestMapping(value = TramitHPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitHProcForm.class, TramitHProcFilterForm.class })
public class TramitHPublicController extends TramitHOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitGPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramith";
    public static final String CONTEXT_WEB_NEXT = TramitIPublicController.CONTEXT_WEB;
   
    @Override
    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }

    @Override
    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }

    @Override
    public String getTileForm() {
        return "tramitHFormPublic";
    }

}