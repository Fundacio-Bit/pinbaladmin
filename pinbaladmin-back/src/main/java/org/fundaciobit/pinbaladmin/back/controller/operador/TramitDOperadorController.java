package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitDCteAutController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitDCteAutForm;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitDCteAutLogicaService;
import org.fundaciobit.pinbaladmin.persistence.TramitDCteAutJPA;
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
@RequestMapping(value = TramitDOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitDCteAutForm.class, TramitDCteAutFilterForm.class })
public class TramitDOperadorController extends TramitDCteAutController {

    public static final String CONTEXT_WEB = "/operador/tramitd";

    @EJB(mappedName = TramitDCteAutLogicaService.JNDI_NAME)
    protected TramitDCteAutLogicaService tramitDCteAutLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @Override
    public String getTileForm() {
        return "tramitDFormOperador";
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
    public String getRedirectWhenCreated(HttpServletRequest request, TramitDCteAutForm tramitDCteAutForm) {
        Long tramitId = tramitDCteAutForm.getTramitDCteAut().getTramitid();

        //Al form del seguent, getParameter del tramitid, i utilitzar-ho per crear el tramitB
        return "redirect:" + TramitEOperadorController.CONTEXT_WEB + "/new?tramitid=" + tramitId;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long _ID_) {
        try {
            Long tramitID = (Long) request.getSession().getAttribute("tramitid");
            log.info("Estamos en D, vamos a borrar. TramitID=" + tramitID);

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
    public TramitDCteAutForm getTramitDCteAutForm(TramitDCteAutJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitDCteAutForm tramitForm = super.getTramitDCteAutForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.d");

        if (__isView) {

        } else if (tramitForm.isNou()) {
            TramitDCteAutJPA tramitD = tramitForm.getTramitDCteAut();

            String tramitIDStr = request.getParameter("tramitid");
            Long tramitID = Long.parseLong(tramitIDStr);
            tramitD.setTramitid(tramitID);
            tramitForm.addHiddenField(TRAMITID);
            request.getSession().setAttribute("tramitid", tramitID);

            tramitD.setNif("11111111A");
            tramitD.setNom("Toni");
            tramitD.setLlinatge1("Mesquida");
            tramitD.setLlinatge2("Mestre");
            tramitD.setCarrec("Gestio: Profesor de Gestió");
            tramitD.setTelefon("971456789");
            tramitD.setMail("gestio@fbit.org");
        }
        return tramitForm;
    }

    @Override
    public TramitDCteAutJPA create(HttpServletRequest request, TramitDCteAutJPA tramitDCteAut)
            throws I18NException, I18NValidationException {
        return (TramitDCteAutJPA) tramitDCteAutLogicEjb.create(tramitDCteAut);
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitDCteAutForm tramitDCteAutForm,
            Throwable __e) {
        if (__e == null) {
            return "redirect:" + TramitAOperadorController.CONTEXT_WEB + "/list";
        } else {
            return getTileForm();
        }
    }
}
