package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.LongField;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitDOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitDCteAutController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitDCteAutLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitDCteAut;
import org.fundaciobit.pinbaladmin.model.fields.TramitDCteAutFields;
import org.fundaciobit.pinbaladmin.persistence.TramitDCteAutJPA;
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
@RequestMapping(value = TramitDPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitDCteAutForm.class, TramitDCteAutFilterForm.class })
public class TramitDPublicController extends TramitDOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitCPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitd";
    public static final String CONTEXT_WEB_NEXT = TramitEPublicController.CONTEXT_WEB;

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
        return "tramitDFormPublic";
    }
}
