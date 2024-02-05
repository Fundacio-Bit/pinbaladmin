package org.fundaciobit.pinbaladmin.back.controller.operador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitECteAudController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitECteAudForm;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitECteAudLogicaService;
import org.fundaciobit.pinbaladmin.persistence.TramitECteAudJPA;
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
@RequestMapping(value = TramitEOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitECteAudForm.class, TramitECteAudFilterForm.class })
public class TramitEOperadorController extends TramitECteAudController {

    public static final String CONTEXT_WEB = "/operador/tramite";

    @EJB(mappedName = TramitECteAudLogicaService.JNDI_NAME)
    protected TramitECteAudLogicaService tramitECteAudLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @Override
    public String getTileForm() {
        return "tramitEFormOperador";
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
    public String getRedirectWhenCreated(HttpServletRequest request, TramitECteAudForm tramitECteAudForm) {
        Long tramitId = tramitECteAudForm.getTramitECteAud().getTramitid();

        String uuid =  HibernateFileUtil.encryptFileID(tramitId);

        //Al form del seguent, getParameter del tramitid, i utilitzar-ho per crear el tramitB
        return "redirect:" + TramitFOperadorController.CONTEXT_WEB + "/new?tramitid=" + uuid;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long _ID_) {
        try {
            Long tramitID = (Long) request.getSession().getAttribute("tramitid");
            log.info("Estamos en E, vamos a borrar. TramitID=" + tramitID);

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
    public TramitECteAudForm getTramitECteAudForm(TramitECteAudJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitECteAudForm tramitForm = super.getTramitECteAudForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.e");

        if (__isView) {

        } else if (tramitForm.isNou()) {
            TramitECteAudJPA tramitE = tramitForm.getTramitECteAud();

            Long tramitID = getTramitIDFromRequest(request);

            tramitE.setTramitid(tramitID);
            tramitForm.addHiddenField(TRAMITID);
            request.getSession().setAttribute("tramitid", tramitID);

            tramitE.setNif("22222222E");
            tramitE.setNom("Paco");
            tramitE.setLlinatge1("Gaita");
            tramitE.setLlinatge2("Sureda");
            tramitE.setCarrec("Auditor: Petats Gaita's Leader");
            tramitE.setTelefon("971213458");
            tramitE.setMail("auditor@fbit.org");
        }
        return tramitForm;
    }

    @Override
    public TramitECteAudJPA create(HttpServletRequest request, TramitECteAudJPA tramitECteAud)
            throws I18NException, I18NValidationException {
        return (TramitECteAudJPA) tramitECteAudLogicEjb.create(tramitECteAud);
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitECteAudForm tramitECteAudForm,
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
