package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitFOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecForm;
import org.fundaciobit.pinbaladmin.persistence.TramitFCteTecJPA;
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
@RequestMapping(value = TramitFPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitFCteTecForm.class, TramitFCteTecFilterForm.class })
public class TramitFPublicController extends TramitFOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitEPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitf";
    public static final String CONTEXT_WEB_NEXT = TramitHPublicController.CONTEXT_WEB;;

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
        return "tramitFFormPublic";
    }

    @Override
    public TramitFCteTecForm getTramitFCteTecForm(TramitFCteTecJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitFCteTecForm tramitForm = super.getTramitFCteTecForm(_jpa, __isView, request, mav);

        tramitForm.addHiddenField(TRAMITID);

        if (tramitForm.isNou()) {
            TramitFCteTecJPA tramitF = tramitForm.getTramitFCteTec();

            tramitF.setNif("45186147W");
            tramitF.setNom("Toni");
            tramitF.setLlinatge1("Nadal");
            tramitF.setLlinatge2("Bennassar");
            tramitF.setCarrec("Tecnic: L'amo dels Tecnics");
            tramitF.setTelefon("971745318");
            tramitF.setMail("tecnic@fbit.org");
        }
       return tramitForm;
    }
}