package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitFOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecForm;
import org.fundaciobit.pinbaladmin.persistence.TramitFCteTecJPA;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    public static final String CONTEXT_WEB_NEXT = TramitGPublicController.CONTEXT_WEB;;

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
//            TramitFCteTecJPA tramitF = tramitForm.getTramitFCteTec();
//
//            tramitF.setNif("45186147W");
//            tramitF.setNom("Toni");
//            tramitF.setLlinatge1("Nadal");
//            tramitF.setLlinatge2("Bennassar");
//            tramitF.setCarrec("Tecnic: L'amo dels Tecnics");
//            tramitF.setTelefon("971745318");
//            tramitF.setMail("tecnic@fbit.org");
        }
       return tramitForm;
    }
    
    @Override
    public void preValidate(HttpServletRequest request, TramitFCteTecForm tramitForm, BindingResult result)
    		throws I18NException {
    	super.preValidate(request, tramitForm, result);
    	
    	String carrec = tramitForm.getTramitFCteTec().getCarrec();
		if (carrec == null || carrec.trim().length() == 0) {
			carrec = "---";
		}
    	tramitForm.getTramitFCteTec().setCarrec(carrec);

		String telefon = tramitForm.getTramitFCteTec().getTelefon();
		if (telefon != null && !telefon.matches("\\d{9,10}")) {
			result.rejectValue(get(TELEFON), "genapp.validation.invalidFormat",
					new Object[] { I18NUtils.tradueix(TELEFON.fullName) },
					"El número de teléfono debe tener 9 o 10 dígitos.");
		}

    }
}