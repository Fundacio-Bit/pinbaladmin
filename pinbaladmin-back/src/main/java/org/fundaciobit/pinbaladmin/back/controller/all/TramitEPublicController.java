package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitAOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitEOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.persistence.TramitECteAudJPA;
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
@RequestMapping(value = TramitEPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitECteAudForm.class, TramitECteAudFilterForm.class })
public class TramitEPublicController extends TramitEOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitDPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramite";
    public static final String CONTEXT_WEB_NEXT = TramitFPublicController.CONTEXT_WEB;
   
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
        return "tramitEFormPublic";
    }

    @Override
    public TramitECteAudForm getTramitECteAudForm(TramitECteAudJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitECteAudForm tramitForm = super.getTramitECteAudForm(_jpa, __isView, request, mav);

        tramitForm.addHiddenField(TRAMITID);

        if (tramitForm.isNou()) {
            TramitECteAudJPA tramitE = tramitForm.getTramitECteAud();

            tramitE.setNif("45186147W");
            tramitE.setNom("Paco");
            tramitE.setLlinatge1("Gaita");
            tramitE.setLlinatge2("Sureda");
            tramitE.setCarrec("Auditor: Petats Gaita's Leader");
            tramitE.setTelefon("971213458");
            tramitE.setMail("auditor@fbit.org");
        }
       return tramitForm;
    }
}