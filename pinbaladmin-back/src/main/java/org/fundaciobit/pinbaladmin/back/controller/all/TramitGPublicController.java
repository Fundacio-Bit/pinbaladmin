package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitEOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitGOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitGDadesTitController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitGDadesTitLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.entity.TramitGDadesTit;
import org.fundaciobit.pinbaladmin.model.fields.TramitGDadesTitFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitGDadesTitFields;
import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitJPA;
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
@RequestMapping(value = TramitGPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitGDadesTitForm.class, TramitGDadesTitFilterForm.class })
public class TramitGPublicController extends TramitGOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitFPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitg";
    public static final String CONTEXT_WEB_NEXT = TramitHPublicController.CONTEXT_WEB;
   
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
        return "tramitGFormPublic";
    }

}