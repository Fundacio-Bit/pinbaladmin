package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitAOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitCOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.pinbaladmin.persistence.TramitCDadesCesiJPA;
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
@RequestMapping(value = TramitCPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitCDadesCesiForm.class, TramitCDadesCesiFilterForm.class })
public class TramitCPublicController extends TramitCOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitBPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitc";
    public static final String CONTEXT_WEB_NEXT = TramitDPublicController.CONTEXT_WEB;

    @Override
    public boolean isPublic() {
        return true;
    }

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
        return "tramitCFormPublic";
    }
    
    @Override
    public TramitCDadesCesiForm getTramitCDadesCesiForm(TramitCDadesCesiJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitCDadesCesiForm tramitForm = super.getTramitCDadesCesiForm(_jpa, __isView, request, mav);

        tramitForm.addHiddenField(TRAMITID);
        if (tramitForm.isNou()) {
            TramitCDadesCesiJPA tramitC = tramitForm.getTramitCDadesCesi();

            tramitC.setResponsable("Dirección General de Primera Infancia, Innovación y Comunidad Educativa");
            tramitC.setDir3responsable("A04026925");
            tramitC.setDir3arrel("A04026923");
            tramitC.setDireccio("Carrer de la direcció");
            tramitC.setCodipostal("07003");
            tramitC.setMunicipi("9");
        }
        
        return tramitForm;
    }
}
