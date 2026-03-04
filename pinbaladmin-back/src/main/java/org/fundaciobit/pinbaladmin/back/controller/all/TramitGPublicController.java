package org.fundaciobit.pinbaladmin.back.controller.all;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pinbaladmin.back.controller.operador.TramitGOperadorController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitForm;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.fundaciobit.pinbaladmin.logic.EntitatLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager;
import org.fundaciobit.pinbaladmin.logic.utils.PinbalAdminPluginsManager.TipusPluginUserInfo;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.pinbaladmin.model.entity.TramitCDadesCesi;
import org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields;
import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitJPA;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
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
@RequestMapping(value = TramitGPublicController.CONTEXT_WEB)
@SessionAttributes(types = { TramitGDadesTitForm.class, TramitGDadesTitFilterForm.class })
public class TramitGPublicController extends TramitGOperadorController {

    public static final String CONTEXT_WEB_PREV = TramitFPublicController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/public/tramitg";
    public static final String CONTEXT_WEB_NEXT = TramitHPublicController.CONTEXT_WEB;
    
    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;
    
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
        	
        	omplirDadesResponsableEntitat(tramitG);
        	

//            tramitG.setNif("45186147W");
//            tramitG.setNom("Isi");
//            tramitG.setLlinatge1("Palazón");
//            tramitG.setLlinatge2("Rayo");
//            tramitG.setCarrec("Titular: Jugador del Rayo titularisimo");            
        }
       
        return tramitForm;
    }
    
	private void omplirDadesResponsableEntitat(TramitGDadesTitJPA tramitG) throws I18NException {
		// Obtenir tramitC per obtenir entitat.
		// Si entitat es govern, omplir amb dades del DG.

		String CIF_GOVERN = "S0711001H";
		Long tramitID = tramitG.getTramitid();

		Entitat entitat = getEntitatTramit(tramitID);

		// Si es GOVERN, omplir amb dades de DG
		if (entitat != null && entitat.getCIF().equals(CIF_GOVERN)) {
			String nifDG = Configuracio.getNIFDirectorGeneral();
			final boolean debug = true;
	    	//boolean caib = true;
			UserInfo infoDG  = null;
			
			try {
				IUserInformationPlugin pluginUserInfo =  PinbalAdminPluginsManager.getUserInformationPluginInstance(debug, TipusPluginUserInfo.LDAP);
				infoDG = pluginUserInfo.getUserInfoByAdministrationID(nifDG);
			} catch (Exception e) {
				log.error("Error obtenint dades DG Innovació i Transformació Digital amb NIF " + nifDG, e);
			}
			
			if (infoDG == null) {
				try {
					//caib = false;
					IUserInformationPlugin pluginUserInfo =  PinbalAdminPluginsManager.getUserInformationPluginInstance(debug, TipusPluginUserInfo.OTAE);
					infoDG = pluginUserInfo.getUserInfoByAdministrationID(nifDG);
				} catch (Exception e) {
					log.error("Error obtenint dades del DG  NIF " + nifDG, e);
				}
			}

			if (infoDG == null) {
				return;
			}
			
			infoDG.getAdministrationID();
			
			tramitG.setNif(infoDG.getAdministrationID());
			tramitG.setNom(infoDG.getName());

			String ape1 = infoDG.getSurname1();
			String ape2 = infoDG.getSurname2();
			
			//Si ape1 te els dos llinatges i ape2 es buit, repartir-los
			if (ape1.indexOf(" ") > 0 && (ape2 == null  || ape2.trim().length() == 0)) {
				ape1 = ape1.substring(0, ape1.indexOf(" "));
				ape2 = infoDG.getSurname1().substring(infoDG.getSurname1().indexOf(" ") + 1);
			}
			
			if (ape2 == null || ape2.trim().length() == 0) {
				ape2 = "---";
			}
			
			tramitG.setLlinatge1(ape1);
			tramitG.setLlinatge2(ape2);
			
			tramitG.setCarrec("Director General d'Estrategia Digital i Desenvolupament Tecnològic");
			tramitG.setMail(infoDG.getEmail());
			tramitG.setTelefon(infoDG.getPhoneNumber());
		}
	}
	
	private Entitat getEntitatTramit(Long tramitID) throws I18NException {

		List<TramitCDadesCesi> tramitCList = tramitCDadesCesiLogicEjb
				.select(TramitCDadesCesiFields.TRAMITID.equal(tramitID));

		if (tramitCList != null && tramitCList.size() > 0) {
			TramitCDadesCesi tramitC = tramitCList.get(0);
			Long entitatID = tramitC.getOrganArrelID();
			Entitat entitat = entitatLogicaEjb.findByPrimaryKey(entitatID);
			return entitat;
		}
		
		return null;
	}
    
    @Override
    public void preValidate(HttpServletRequest request, TramitGDadesTitForm tramitForm, BindingResult result)
			throws I18NException {

		super.preValidate(request, tramitForm, result);

		String carrec = tramitForm.getTramitGDadesTit().getCarrec();
		if (carrec == null || carrec.trim().length() == 0) {
			carrec = "---";
		}
		tramitForm.getTramitGDadesTit().setCarrec(carrec);

		// Igual amb telefon i mail
		String telefon = tramitForm.getTramitGDadesTit().getTelefon();
		if (telefon == null || telefon.trim().length() == 0) {
			telefon = "---------";
		}
		tramitForm.getTramitGDadesTit().setTelefon(telefon);

		String mail = tramitForm.getTramitGDadesTit().getMail();
		if (mail == null || mail.trim().length() == 0) {
			mail = "---";
		}
		tramitForm.getTramitGDadesTit().setMail(mail);

	}
    
}