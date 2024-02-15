package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitEOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitFOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitECteAudController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitECteAudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.entity.TramitECteAud;
import org.fundaciobit.pinbaladmin.model.fields.TramitECteAudFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitECteAudFields;
import org.fundaciobit.pinbaladmin.persistence.TramitECteAudJPA;
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
@RequestMapping(value = TramitFPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitFCteTecForm.class, TramitFCteTecFilterForm.class })
public class TramitFPublicController extends TramitFOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitEPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitf";
    public static final String CONTEXT_WEB_NEXT = TramitGPublicController.CONTEXT_WEB;;
   
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
        return "tramitFFormPublic";
    }

}