package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitFCteTecController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitFCteTecForm;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitFCteTecLogicaService;
import org.fundaciobit.pinbaladmin.persistence.TramitFCteTecJPA;
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
@RequestMapping(value = TramitFOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitFCteTecForm.class, TramitFCteTecFilterForm.class })
public class TramitFOperadorController extends TramitFCteTecController {

    public static final String CONTEXT_WEB = "/operador/tramitf";

    @EJB(mappedName = TramitFCteTecLogicaService.JNDI_NAME)
    protected TramitFCteTecLogicaService tramitFCteTecLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @Override
    public String getTileForm() {
        return "tramitFFormOperador";
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
    public String getRedirectWhenCreated(HttpServletRequest request, TramitFCteTecForm tramitFCteTecForm) {
        Long tramitId = tramitFCteTecForm.getTramitFCteTec().getTramitid();

        //Al form del seguent, getParameter del tramitid, i utilitzar-ho per crear el tramitB
        return "redirect:" + TramitGOperadorController.CONTEXT_WEB + "/new?tramitid=" + tramitId;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long _ID_) {
        try {
            Long tramitID = (Long) request.getSession().getAttribute("tramitid");
            log.info("Estamos en F, vamos a borrar. TramitID=" + tramitID);

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
    public TramitFCteTecForm getTramitFCteTecForm(TramitFCteTecJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitFCteTecForm tramitForm = super.getTramitFCteTecForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.f");

        if (__isView) {

        } else if (tramitForm.isNou()) {
            TramitFCteTecJPA tramitF = tramitForm.getTramitFCteTec();

            String tramitIDStr = request.getParameter("tramitid");
            Long tramitID = Long.parseLong(tramitIDStr);
            tramitF.setTramitid(tramitID);
            tramitForm.addHiddenField(TRAMITID);
            request.getSession().setAttribute("tramitid", tramitID);

            tramitF.setNif("33333333I");
            tramitF.setNom("Toni");
            tramitF.setLlinatge1("Nadal");
            tramitF.setLlinatge2("Bennassar");
            tramitF.setCarrec("Tecnic: L'amo dels Tecnics");
            tramitF.setTelefon("971745318");
            tramitF.setMail("tecnic@fbit.org");
        }
        return tramitForm;
    }

    @Override
    public TramitFCteTecJPA create(HttpServletRequest request, TramitFCteTecJPA tramitFCteTec)
            throws I18NException, I18NValidationException {
        return (TramitFCteTecJPA) tramitFCteTecLogicEjb.create(tramitFCteTec);
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitFCteTecForm tramitFCteTecForm,
            Throwable __e) {
        if (__e == null) {
            return "redirect:" + TramitAOperadorController.CONTEXT_WEB + "/list";
        } else {
            return getTileForm();
        }
    }
}
