package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitCDadesCesiController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitCDadesCesiLogicaService;
import org.fundaciobit.pinbaladmin.persistence.TramitCDadesCesiJPA;
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
@RequestMapping(value = TramitCOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitCDadesCesiForm.class, TramitCDadesCesiFilterForm.class })
public class TramitCOperadorController extends TramitCDadesCesiController {

    public static final String CONTEXT_WEB = "/operador/tramitc";

    @EJB(mappedName = TramitCDadesCesiLogicaService.JNDI_NAME)
    protected TramitCDadesCesiLogicaService tramitCDadesCesiLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

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
    public String getRedirectWhenCreated(HttpServletRequest request, TramitCDadesCesiForm tramitCDadesCesiForm) {
        //Cada vegada que es faci create, anar al tramit B
        Long tramitId = tramitCDadesCesiForm.getTramitCDadesCesi().getTramitid();

        String uuid =  HibernateFileUtil.encryptFileID(tramitId);

        //Al form del seguent, getParameter del tramitid, i utilitzar-ho per crear el tramitB
        return "redirect:" + TramitDOperadorController.CONTEXT_WEB + "/new?tramitid=" + uuid;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long _ID_) {
        try {
            Long tramitID = (Long) request.getSession().getAttribute("tramitid");
            log.info("Estamos en C, vamos a borrar. TramitID=" + tramitID);

            if (tramitID == null) {
                log.info("No se borran tablas porque estás en edit o en view");
            } else {
                tramitAPersAutLogicEjb.deleteFull(tramitID);
                request.getSession().removeAttribute("tramitid");
                HtmlUtils.saveMessageError(request, "Tramit Cancelat (taules borrades)");
            }
        } catch (I18NException e) {
            HtmlUtils.saveMessageError(request, "Error esborrant les taules del tramit sistra");
        }
        return "redirect:" + TramitAOperadorController.RETURN_URL;
    }

    @Override

    public TramitCDadesCesiForm getTramitCDadesCesiForm(TramitCDadesCesiJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitCDadesCesiForm tramitForm = super.getTramitCDadesCesiForm(_jpa, __isView, request, mav);

        if (__isView) {

        } else if (tramitForm.isNou()) {
            TramitCDadesCesiJPA tramitC = tramitForm.getTramitCDadesCesi();

            Long tramitID = getTramitIDFromRequest(request);

            tramitC.setTramitid(tramitID);
            tramitForm.addHiddenField(TRAMITID);
            request.getSession().setAttribute("tramitid", tramitID);

//            String nifDenom = "S0711001H";
//            tramitC.setDenominacio("P0700900D");
//            tramitC.setNif("P0700900D");
//            tramitForm.addReadOnlyField(NIF);
            tramitC.setResponsable("Dirección General de Primera Infancia, Innovación y Comunidad Educativa");
            tramitC.setDir3responsable("A04026925");
            tramitC.setDir3arrel("A04026923");
            tramitC.setDireccio("Carrer de la direcció");
            tramitC.setCodipostal("07003");
            tramitC.setMunicipi("9");
            
            tramitForm.setTitleCode("tramit.sistra.titol.c");
        }
        tramitForm.setAttachedAdditionalJspCode(true);
        return tramitForm;
    }

    @Override
    public TramitCDadesCesiJPA create(HttpServletRequest request, TramitCDadesCesiJPA tramitCDadesCesi)
            throws I18NException, I18NValidationException {
        return (TramitCDadesCesiJPA) tramitCDadesCesiLogicEjb.create(tramitCDadesCesi);
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
    public String getRedirectWhenModified(HttpServletRequest request, TramitCDadesCesiForm tramitCDadesCesiForm,
            Throwable __e) {
        if (__e == null) {
            return "redirect:" + TramitAOperadorController.CONTEXT_WEB + "/list";
        } else {
            return getTileForm();
        }
    }
    
    public Long getTramitIDFromRequest(HttpServletRequest request) {
        return HibernateFileUtil.decryptFileID(request.getParameter("tramitid")); 
    }

}
