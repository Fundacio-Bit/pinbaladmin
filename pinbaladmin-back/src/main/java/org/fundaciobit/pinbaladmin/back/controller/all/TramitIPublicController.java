package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.Section;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitAOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitIOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;
import org.fundaciobit.pinbaladmin.persistence.TramitIServJPA;
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
@RequestMapping(value = TramitIPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitIServForm.class, TramitIServFilterForm.class })
public class TramitIPublicController extends TramitIOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitHPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramiti";
    public static final String CONTEXT_WEB_NEXT = TramitJPublicController.CONTEXT_WEB;
   
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
        return "tramitIFormPublic";
    }

    @Override
    public String getTileList() {
        return "tramitIListPublic";
    }

    @Override
    public TramitIServForm getTramitIServForm(TramitIServJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitIServForm tramitForm = super.getTramitIServForm(_jpa, __isView, request, mav);

        tramitForm.addHiddenField(TRAMITID);

        if (tramitForm.isNou()) {
            TramitIServJPA tramitI = tramitForm.getTramitIServ();

            tramitI.setNorma("Norma Legal inventada");
            tramitI.setUrlnorma("https://www.boe.es/buscar/act.php?id=BOE-A-2021-9347");
            tramitI.setConsentimentpublicat("1");
            tramitI.setArticles("Art1, 2 i Art 5");
        }

        return tramitForm;
    }
    
}