package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitAOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitBOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
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

    public static final String CONTEXT_WEB_PREV = TramitAPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitb";
    public static final String CONTEXT_WEB_NEXT = TramitCPublicController.CONTEXT_WEB;

    @Override
    public boolean isPublic() {
        return true;
    }
    
    @Override
    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }

    @Override
    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }
    
    @Override
    public String getTileForm() {
        return "tramitBFormPublic";
    }

    @Override
    public TramitBDadesSoliForm getTramitBDadesSoliForm(TramitBDadesSoliJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitBDadesSoliForm tramitForm = super.getTramitBDadesSoliForm(_jpa, __isView, request, mav);

        tramitForm.addHiddenField(TRAMITID);
        if (tramitForm.isNou()) {
            TramitBDadesSoliJPA tramitB = tramitForm.getTramitBDadesSoli();
            tramitB.setEntorn("pro");
        }
        return tramitForm;
    }
}
