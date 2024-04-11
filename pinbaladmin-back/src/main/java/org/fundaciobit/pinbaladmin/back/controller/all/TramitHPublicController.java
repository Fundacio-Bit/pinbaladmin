package org.fundaciobit.pinbaladmin.back.controller.all;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitAOperadorController;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitHOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.persistence.TramitHProcJPA;
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
@RequestMapping(value = TramitHPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitHProcForm.class, TramitHProcFilterForm.class })
public class TramitHPublicController extends TramitHOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitGPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramith";
    public static final String CONTEXT_WEB_NEXT = TramitIPublicController.CONTEXT_WEB;
   
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
        return "tramitHFormPublic";
    }


    @Override
    public TramitHProcForm getTramitHProcForm(TramitHProcJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitHProcForm tramitForm = super.getTramitHProcForm(_jpa, __isView, request, mav);

        tramitForm.addHiddenField(TRAMITID);

        String ajudaNom = I18NUtils.tradueix("tramitHProc.nom.help");
        tramitForm.addHelpToField(NOM, ajudaNom);
        
        String ajudaDesc = I18NUtils.tradueix("tramitHProc.desc.help");
        tramitForm.addHelpToField(DESCRIPCIO, ajudaDesc);
        
        if (tramitForm.isNou()) {
            TramitHProcJPA tramitH = tramitForm.getTramitHProc();

            tramitH.setTipus("1");
            tramitH.setNom("Subvencions que m'acab d'inventar");
            tramitH.setCodi("2874870");
            tramitH.setUrlseu("https://www.google.es");
            tramitH.setDescripcio("Proceso ayudas para Asociaciones de Famílias de Alumnos");
            tramitH.setPeticionsaldia(12);
            tramitH.setPeticionsalmes(450);
            tramitH.setAutomatizado(true);
        }
       return tramitForm;
    }
    
    
    
    @Override
    	public void postValidate(HttpServletRequest request, TramitHProcForm tramitHProcForm, BindingResult result)
				throws I18NException {
			super.postValidate(request, tramitHProcForm, result);

			TramitHProcJPA tramitH = tramitHProcForm.getTramitHProc();
			if (tramitH.getNom().length() > 100) {
				result.rejectValue(get(NOM), "genapp.validation.sizeexceeds",
						new String[] { I18NUtils.tradueix(NOM.fullName), "100" }, null);
			}

			if (tramitH.getDescripcio().length() > 200) {
				result.rejectValue(get(DESCRIPCIO), "genapp.validation.sizeexceeds",
						new String[] { I18NUtils.tradueix(DESCRIPCIO.fullName), "200" }, null);
			}

		}
}