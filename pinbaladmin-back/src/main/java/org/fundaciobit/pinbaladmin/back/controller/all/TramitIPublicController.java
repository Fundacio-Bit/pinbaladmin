package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import org.fundaciobit.genapp.common.web.form.Section;
import org.fundaciobit.pinbaladmin.back.controller.PinbalAdminFilesFormManager;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitIOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitIServForm;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
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
   
    @EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
    protected FitxerPublicLogicaService fitxerPublicLogicaEjb;

    @Override
    protected FilesFormManager<Fitxer> getFilesFormManager() {
        return new PinbalAdminFilesFormManager(fitxerPublicLogicaEjb);
    }

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

		tramitForm.addHiddenField(TramitIServFields.TRAMITID);
		tramitForm.addHiddenField(TramitIServFields.URLNORMA);

		Section norma1 = new Section("norma1", "tramitIServ.normalegal.1", TramitIServFields.NORMA, TramitIServFields.FITXERNORMAID, TramitIServFields.ARTICLES);
		Section norma2 = new Section("norma2", "tramitIServ.normalegal.2", TramitIServFields.NORMA2, TramitIServFields.FITXERNORMA2ID, TramitIServFields.ARTICLES2);
		Section norma3 = new Section("norma3", "tramitIServ.normalegal.3", TramitIServFields.NORMA3, TramitIServFields.FITXERNORMA3ID, TramitIServFields.ARTICLES3);

		tramitForm.addSection(norma1);
		tramitForm.addSection(norma2);
		tramitForm.addSection(norma3);

		if (tramitForm.isNou()) {
			TramitIServJPA tramitI = tramitForm.getTramitIServ();
//
			tramitI.setNorma("Norma" + System.currentTimeMillis());
//			tramitI.setUrlnorma("https://www.boe.es/buscar/act.php?id=BOE-A-2021-9347");
//			// tramitI.setConsentimentpublicat("1");
			tramitI.setArticles("Art1, 2 i Art 5");
		}

		return tramitForm;
	}
    
}