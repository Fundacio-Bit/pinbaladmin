package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitAOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitDOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.persistence.TramitDCteAutJPA;
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
@RequestMapping(value = TramitDPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitDCteAutForm.class, TramitDCteAutFilterForm.class })
public class TramitDPublicController extends TramitDOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitCPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitd";
    public static final String CONTEXT_WEB_NEXT = TramitEPublicController.CONTEXT_WEB;

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
        return "tramitDFormPublic";
    }
    
    @Override
    public TramitDCteAutForm getTramitDCteAutForm(TramitDCteAutJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitDCteAutForm tramitForm = super.getTramitDCteAutForm(_jpa, __isView, request, mav);

        tramitForm.addHiddenField(TRAMITID);

        if (tramitForm.isNou()) {
            TramitDCteAutJPA tramitD = tramitForm.getTramitDCteAut();

//            tramitD.setNif("45186147W");
//            tramitD.setNom("Toni");
//            tramitD.setLlinatge1("Mesquida");
//            tramitD.setLlinatge2("Mestre");
//            tramitD.setCarrec("Gestio: Profesor de Gestió");
//            tramitD.setTelefon("971456789");
//            tramitD.setMail("gestio@fbit.org");            
        }
        
        return tramitForm;
    }
}
