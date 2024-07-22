package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitAOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitGOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitJPA;
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
@RequestMapping(value = TramitGPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitGDadesTitForm.class, TramitGDadesTitFilterForm.class })
public class TramitGPublicController extends TramitGOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitFPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitg";
    public static final String CONTEXT_WEB_NEXT = TramitHPublicController.CONTEXT_WEB;
   
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
        return "tramitGFormPublic";
    }

    
    @Override
    public TramitGDadesTitForm getTramitGDadesTitForm(TramitGDadesTitJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitGDadesTitForm tramitForm = super.getTramitGDadesTitForm(_jpa, __isView, request, mav);

        tramitForm.addHiddenField(TRAMITID);

        if (tramitForm.isNou()) {
            TramitGDadesTitJPA tramitG = tramitForm.getTramitGDadesTit();

//            tramitG.setNif("45186147W");
//            tramitG.setNom("Isi");
//            tramitG.setLlinatge1("Palazón");
//            tramitG.setLlinatge2("Rayo");
//            tramitG.setCarrec("Titular: Jugador del Rayo titularisimo");            
        }
       
        return tramitForm;
    }
    
}