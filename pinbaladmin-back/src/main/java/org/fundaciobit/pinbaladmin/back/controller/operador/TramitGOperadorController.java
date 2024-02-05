package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitGDadesTitController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitGDadesTitForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitGDadesTitLogicaService;
import org.fundaciobit.pinbaladmin.persistence.TramitGDadesTitJPA;
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
@RequestMapping(value = TramitGOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitGDadesTitForm.class, TramitGDadesTitFilterForm.class })
public class TramitGOperadorController extends TramitGDadesTitController {

    public static final String CONTEXT_WEB = "/operador/tramitg";

    @EJB(mappedName = TramitGDadesTitLogicaService.JNDI_NAME)
    protected TramitGDadesTitLogicaService tramitGDadesTitLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @Override
    public String getTileForm() {
        return "tramitGFormOperador";
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
    public String getRedirectWhenCreated(HttpServletRequest request, TramitGDadesTitForm tramitGDadesTitForm) {
        Long tramitId = tramitGDadesTitForm.getTramitGDadesTit().getTramitid();

        String uuid =  HibernateFileUtil.encryptFileID(tramitId);

        //Al form del seguent, getParameter del tramitid, i utilitzar-ho per crear el tramitB
        return "redirect:" + TramitHOperadorController.CONTEXT_WEB + "/new?tramitid=" + uuid;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long _ID_) {
        try {
            Long tramitID = (Long) request.getSession().getAttribute("tramitid");
            log.info("Estamos en G, vamos a borrar. TramitID=" + tramitID);

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
    public TramitGDadesTitForm getTramitGDadesTitForm(TramitGDadesTitJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitGDadesTitForm tramitForm = super.getTramitGDadesTitForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.g");

        if (__isView) {

        } else if (tramitForm.isNou()) {
            TramitGDadesTitJPA tramitG = tramitForm.getTramitGDadesTit();

            Long tramitID = getTramitIDFromRequest(request);

            tramitG.setTramitid(tramitID);
            tramitForm.addHiddenField(TRAMITID);
            request.getSession().setAttribute("tramitid", tramitID);

            tramitG.setNif("444444444O");
            tramitG.setNom("Isi");
            tramitG.setLlinatge1("Palazón");
            tramitG.setLlinatge2("Rayo");
            tramitG.setCarrec("Titular: Jugador del Rayo titularisimo");
        }
        return tramitForm;
    }

    @Override
    public TramitGDadesTitJPA create(HttpServletRequest request, TramitGDadesTitJPA tramitGDadesTit)
            throws I18NException, I18NValidationException {
        return (TramitGDadesTitJPA) tramitGDadesTitLogicEjb.create(tramitGDadesTit);
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitGDadesTitForm tramitGDadesTitForm,
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
