package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitBDadesSoliController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitBDadesSoliForm;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitBDadesSoliLogicaService;
import org.fundaciobit.pinbaladmin.persistence.TramitBDadesSoliJPA;
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
@RequestMapping(value = TramitBOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitBDadesSoliForm.class, TramitBDadesSoliFilterForm.class })
public class TramitBOperadorController extends TramitBDadesSoliController {

    public static final String CONTEXT_WEB = "/operador/tramitb";

    @EJB(mappedName = TramitBDadesSoliLogicaService.JNDI_NAME)
    protected TramitBDadesSoliLogicaService tramitBDadesSoliLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @Override
    public String getTileForm() {
        return "tramitBFormOperador";
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
    public String getRedirectWhenCreated(HttpServletRequest request, TramitBDadesSoliForm tramitBDadesSoliForm) {
        Long tramitId = tramitBDadesSoliForm.getTramitBDadesSoli().getTramitid();

        //Al form del seguent, getParameter del tramitid, i utilitzar-ho per crear el tramitB
        return "redirect:" + TramitCOperadorController.CONTEXT_WEB + "/new?tramitid=" + tramitId;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long _ID_) {
        try {
            Long tramitID = (Long) request.getSession().getAttribute("tramitid");
            log.info("Estamos en B, vamos a borrar. TramitID=" + tramitID);

            tramitAPersAutLogicEjb.deleteFull(tramitID);
            request.getSession().removeAttribute("tramitid");

            HtmlUtils.saveMessageError(request, "Tramit Cancelat (taules borrades");
        } catch (I18NException e) {
            HtmlUtils.saveMessageError(request, "Error esborrant les taules del tramit sistra");
        }
        return "redirect:" + TramitAOperadorController.RETURN_URL;
    }

    @Override
    public TramitBDadesSoliForm getTramitBDadesSoliForm(TramitBDadesSoliJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitBDadesSoliForm tramitForm = super.getTramitBDadesSoliForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.b");

        
        if (__isView) {

        } else if (tramitForm.isNou()) {
            TramitBDadesSoliJPA tramitB = tramitForm.getTramitBDadesSoli();

            String tramitIDStr = request.getParameter("tramitid");
            Long tramitID = Long.parseLong(tramitIDStr);
            tramitB.setTramitid(tramitID);
            tramitForm.addHiddenField(TRAMITID);
            request.getSession().setAttribute("tramitid", tramitID);

            tramitB.setEntorn("pro");
        } else {

        }
        return tramitForm;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipussolicitud(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {

        return tramitBDadesSoliLogicEjb.getReferenceListForTipussolicitud();
    }

    @Override
    public TramitBDadesSoliJPA create(HttpServletRequest request, TramitBDadesSoliJPA tramitBDadesSoli)
            throws I18NException, I18NValidationException {
        return (TramitBDadesSoliJPA) tramitBDadesSoliLogicEjb.create(tramitBDadesSoli);
    }

    @Override
    public List<StringKeyValue> getReferenceListForEntorn(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("", "..."));
        __tmp.add(new StringKeyValue("pre", "Preproducció"));
        __tmp.add(new StringKeyValue("pro", "Producció"));
        return __tmp;
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitBDadesSoliForm tramitBDadesSoliForm,
            Throwable __e) {
        if (__e == null) {
            return "redirect:" + TramitAOperadorController.CONTEXT_WEB + "/list";
        } else {
            return getTileForm();
        }
    }

    //    @Override
    //    public void delete(HttpServletRequest request, TramitBDadesSoli tramitBDadesSoli) throws I18NException {
    //        tramitBDadesSoliLogicEjb.deleteFull(tramitBDadesSoli);
    //      }
}
