package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitCDadesCesiController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.pinbaladmin.ejb.EntitatService;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.EntitatLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitCDadesCesiLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.fields.EntitatFields;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitCDadesCesiFields;
import org.fundaciobit.pinbaladmin.persistence.EntitatJPA;
import org.fundaciobit.pinbaladmin.persistence.OrganJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.fundaciobit.pinbaladmin.persistence.TramitCDadesCesiJPA;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = TramitCOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitCDadesCesiForm.class, TramitCDadesCesiFilterForm.class })
public class TramitCOperadorController extends TramitCDadesCesiController {

    public static final String CONTEXT_WEB_PREV = TramitBOperadorController.CONTEXT_WEB;
    public static final String CONTEXT_WEB = "/operador/tramitc";
    public static final String CONTEXT_WEB_NEXT = TramitDOperadorController.CONTEXT_WEB;

    @EJB(mappedName = TramitCDadesCesiLogicaService.JNDI_NAME)
    protected TramitCDadesCesiLogicaService tramitCDadesCesiLogicEjb;

    @EJB(mappedName = OrganLogicaService.JNDI_NAME)
    protected OrganLogicaService organLogicaEjb;
    
    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    public boolean isPublic() {
        return false;
    }
    public long actual() {
        return 2;
    }
    
    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }

    public String getContextWebPrev() {
        return CONTEXT_WEB_PREV;
    }
    
    @Override
    public String getTileForm() {
        return "tramitCFormOperador";
    }

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public boolean isActiveFormNew() {
        return true;
    }

    @Override
    public boolean isActiveFormEdit() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public TramitCDadesCesiForm getTramitCDadesCesiForm(TramitCDadesCesiJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitCDadesCesiForm tramitForm = super.getTramitCDadesCesiForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.c");

        Long tramitID; 

        if (tramitForm.isNou()) {
            tramitID = TramitAOperadorController.getTramitIDFromRequest(request);
            TramitCDadesCesiJPA tramitC = tramitForm.getTramitCDadesCesi();

            tramitC.setTramitid(tramitID);
            request.getSession().setAttribute("tramitid", tramitID);
        }else {
            tramitID = tramitForm.getTramitCDadesCesi().getTramitid();
        }
        
        String uuid = HibernateFileUtil.encryptFileID(tramitID);
        
        tramitForm.setCancelButtonVisible(false);
        tramitForm.setDeleteButtonVisible(false);
        tramitForm.setSaveButtonVisible(false);

		tramitForm.addAdditionalButton(
				new AdditionalButton("", "genapp.continue", "javascript: $('form').submit();", "btn-primary"));

        tramitForm.addAdditionalButton(new AdditionalButton("", "genapp.pagination.anterior",
                getContextWebPrev() + "/back/" + uuid, "btn-info"));

        tramitForm.addAdditionalButton(
                new AdditionalButton("", "genapp.delete", getContextWeb() + "/delete/" + uuid, "btn-danger"));

        {
        	String anotacions = "Per fer la sol·licitud de cessió de dades, cal que ompliu el formulari següent amb les dades de la persona o entitat que cedeix les dades. Important: cal que adjunteu la documentació necessària per acreditar la cessió de dades.";

			request.setAttribute("desplegableOrgans", true);

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
            tramitForm.setAttachedAdditionalJspCode(true);
        }

        return tramitForm;
    }

    @Override
    public TramitCDadesCesiJPA create(HttpServletRequest request, TramitCDadesCesiJPA tramitCDadesCesi)
            throws I18NException, I18NValidationException {
        return (TramitCDadesCesiJPA) tramitCDadesCesiLogicEjb.create(tramitCDadesCesi);
    }
    
    @Override
    public TramitCDadesCesiJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
        return (TramitCDadesCesiJPA) tramitCDadesCesiLogicEjb.findByPrimaryKey(id);
    }

    @Override
    public TramitCDadesCesiJPA update(HttpServletRequest request, TramitCDadesCesiJPA tramitJPA)
            throws I18NException, I18NValidationException {
        return (TramitCDadesCesiJPA) tramitCDadesCesiLogicEjb.update(tramitJPA);
    }

    @Override
    public List<StringKeyValue> getReferenceListForDenominacio(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        return tramitCDadesCesiLogicEjb.getReferenceListForDenominacio();
    }

    @Override
    public List<StringKeyValue> getReferenceListForMunicipi(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        return tramitCDadesCesiLogicEjb.getReferenceListForMunicipi();
    }
    
    @Override
    public List<StringKeyValue> getReferenceListForOrganID(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        
        List<Organ> organs = organLogicaEjb.select(where);
        for (Organ organ : organs) {
            String dades = "(" + organ.getDir3() + ") " + organ.getNom();
            __tmp.add(new StringKeyValue(String.valueOf(organ.getOrganid()), dades ));
        }

        return __tmp;
    }
    
    @Override
    public List<StringKeyValue> getReferenceListForOrganArrelID(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        List<Organ> organs = organLogicaEjb.select(where);
		for (Organ organ : organs) {
			Entitat entitat = entitatLogicaEjb.findByPrimaryKey(organ.getEntitatid());
			// (A04003003 - S0711001H) Govern de les Illes Balears
			if (entitat != null) {
				String arrel = "(" + organ.getDir3() + " - " + entitat.getCIF() + ") " + entitat.getNom();
				__tmp.add(new StringKeyValue(String.valueOf(organ.getOrganid()), arrel));
			}

//		    // Guardar la entitat a la que pertany un organ		
//			List<Entitat> entitats = entitatEjb.select(EntitatFields.DIR3.equal(aux.getDir3()));
//			if (entitats.size() == 0) {
//				log.error("No s'ha trobat cap entitat amb dir3 = " + aux.getDir3());
//				continue;
//			}
//			
//			Entitat entitat = entitats.get(0);
//			Long entitatID = entitat.getEntitatID();
//			organLogicaEjb.update(OrganFields.ENTITATID, entitatID, OrganFields.ORGANID.equal(organ.getOrganid()));
			
		}
        return __tmp;
    }

    //===========================================================================================================================

    //Si estamos en D, cuando le damos a /next, E comprueba si existe o no, y le saca el new o el edit.
    @RequestMapping(value = "/next/{uuid}", method = RequestMethod.GET)
    public String getNextTramitFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);

        Long creats = tramitCDadesCesiLogicEjb.count(TramitCDadesCesiFields.TRAMITID.equal(tramitID));

        if (creats == 0) {
            return "redirect:" + getContextWeb() + "/new?tramitid=" + uuid;
        }else {
            return "redirect:" + getContextWeb() + "/edit/" + uuid;
        }
    }

    //Si estamos en D, miramos el back de C, y que nos de su /edit
    @RequestMapping(value = "/back/{uuid}", method = RequestMethod.GET)
    public String getEditUrlFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return "redirect:" + getContextWeb() + "/edit/" + uuid;
    }
    
    
    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.GET)
    public ModelAndView editarTramitC2(@PathVariable("uuid") java.lang.String uuid, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {
        
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);
        Long id = tramitCDadesCesiLogicEjb.executeQueryOne(TramitCDadesCesiFields.DADESCESIID,
                TramitCDadesCesiFields.TRAMITID.equal(tramitID));
    
        return super.editarTramitCDadesCesiGet(id, request, response);
    }
    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.POST)
    public String editarTramitCDadesCesiPost(@ModelAttribute TramitCDadesCesiForm tramitForm,
            BindingResult result, SessionStatus status, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {
        
        String ret =  super.editarTramitCDadesCesiPost(tramitForm, result, status, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitForm.getTramitCDadesCesi().getTramitid();

            log.info("tramitID: " + tramitID);
            log.info("actual: " + actual());
            log.info("isPublic: " + isPublic());

			request.setAttribute("desplegableOrgans", true);

			String anotacions = "Per fer la sol·licitud de cessió de dades, cal que ompliu el formulari següent amb les dades de la persona o entitat que cedeix les dades. Important: cal que adjunteu la documentació necessària per acreditar la cessió de dades.";
            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
            tramitForm.setAttachedAdditionalJspCode(true);
        }
        return ret;
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitCDadesCesiForm tramitCDadesCesiForm) {

        TramitCDadesCesiJPA tramitC = tramitCDadesCesiForm.getTramitCDadesCesi();
        request.getSession().setAttribute("tramitC", tramitC);

        Long tramitId = tramitC.getTramitid();
        String uuid =  HibernateFileUtil.encryptFileID(tramitId);

        return "redirect:" + getContextWebNext() + "/next/" + uuid;
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitCDadesCesiForm tramitForm,
            Throwable __e) {
        log.info("Esteim a getRedirectWhenModified de TramitC");

        if (__e == null) {
            return getRedirectWhenCreated(request, tramitForm);
        } else {
            return getTileForm();
        }
    }
    
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public String deleteFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return TramitAOperadorController.getRedirectWhenDeleted(request, uuid, tramitAPersAutLogicEjb);
    }
    
    @Override
    public String crearTramitCDadesCesiPost(TramitCDadesCesiForm tramitCDadesCesiForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ret = super.crearTramitCDadesCesiPost(tramitCDadesCesiForm, result, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitCDadesCesiForm.getTramitCDadesCesi().getTramitid();

            log.info("tramitID: " + tramitID);
            log.info("actual: " + actual());
            log.info("isPublic: " + isPublic());
            
			request.setAttribute("desplegableOrgans", true);

			String anotacions = "Per fer la sol·licitud de cessió de dades, cal que ompliu el formulari següent amb les dades de la persona o entitat que cedeix les dades. Important: cal que adjunteu la documentació necessària per acreditar la cessió de dades.";
            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
            tramitCDadesCesiForm.setAttachedAdditionalJspCode(true);
        }
        return ret;
    }
}
