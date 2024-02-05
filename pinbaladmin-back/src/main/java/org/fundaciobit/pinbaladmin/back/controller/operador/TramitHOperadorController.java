package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitHProcController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitHProcForm;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments.TipusProcediment;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitHProcLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitHProc;
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
@RequestMapping(value = TramitHOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitHProcForm.class, TramitHProcFilterForm.class })
public class TramitHOperadorController extends TramitHProcController {

    public static final String CONTEXT_WEB = "/operador/tramith";

    @EJB(mappedName = TramitHProcLogicaService.JNDI_NAME)
    protected TramitHProcLogicaService tramitHProcLogicEjb;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @Override
    public String getTileForm() {
        return "tramitHFormOperador";
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
    public String getRedirectWhenCreated(HttpServletRequest request, TramitHProcForm tramitHProcForm) {
        Long tramitId = tramitHProcForm.getTramitHProc().getTramitid();

        String uuid =  HibernateFileUtil.encryptFileID(tramitId);

        //Al form del seguent, getParameter del tramitid, i utilitzar-ho per crear el tramitB
        return "redirect:" + TramitIOperadorController.CONTEXT_WEB + "/list/1?tramitid=" + uuid;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long _ID_) {
        try {
            Long tramitID = (Long) request.getSession().getAttribute("tramitid");
            log.info("Estamos en H, vamos a borrar. TramitID=" + tramitID);

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
    public TramitHProcForm getTramitHProcForm(TramitHProcJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        TramitHProcForm tramitForm = super.getTramitHProcForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.h");

        if (__isView) {

        } else if (tramitForm.isNou()) {
            TramitHProcJPA tramitH = tramitForm.getTramitHProc();

            Long tramitID = getTramitIDFromRequest(request);

            tramitH.setTramitid(tramitID);
            tramitForm.addHiddenField(TRAMITID);
            request.getSession().setAttribute("tramitid", tramitID);

            tramitH.setTipus("1");
            tramitH.setNom("Subvencions que m'acab d'inventar");
            tramitH.setCodi("2874870");
            tramitH.setUrlseu("https://www.google.es");
            tramitH.setDescripcio("Proceso ayudas para Asociaciones de Famílias de Alumnos");
            tramitH.setPeticionsaldia(12);
            tramitH.setPeticionsalmes(450);
            tramitH.setAutomatizado(true);
            //tramitH.setCaducitatdata(new Timestamp(System.currentTimeMillis()));
        }
        tramitForm.setAttachedAdditionalJspCode(true);
        return tramitForm;
    }

    @Override
    public TramitHProcJPA create(HttpServletRequest request, TramitHProcJPA tramitHProc)
            throws I18NException, I18NValidationException {
        return (TramitHProcJPA) tramitHProcLogicEjb.create(tramitHProc);
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        String lang = "ca";
        log.info("lang = " + lang);
        
        List<TipusProcediment> tipus = TipusProcediments.getAllTipusProcediments();
        for (TipusProcediment tipusProcediment : tipus) {
            String key = String.valueOf(tipusProcediment.id);
            String val;
            if (lang.equals("es")) {
                val = tipusProcediment.castella;
            } else {
                val = tipusProcediment.catala;
            }
            __tmp.add(new StringKeyValue(key, val));

        }
        return __tmp;
    }

    public String getTipusProcediment(Long key) {
        String tp = null;
        String lang = "es";

        List<TipusProcediment> tipus = TipusProcediments.getAllTipusProcediments();
        for (TipusProcediment tipusProcediment : tipus) {
            if (tipusProcediment.id == key) {
                if (lang.equals("es")) {
                    tp = tipusProcediment.castella;
                } else {
                    tp = tipusProcediment.catala;
                }

                break;
            }
        }

        return tp;
    }
    
    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitHProcForm tramitHProcForm,
            Throwable __e) {
        if (__e == null) {
            return "redirect:" + TramitAOperadorController.CONTEXT_WEB + "/list";
        } else {
            return getTileForm();
        }
    }
    
    
    
    public void postValidate(HttpServletRequest request, TramitHProcForm tramitHProcForm, BindingResult result)
            throws I18NException {

        //Comprovació de la caducitat. Si caduca i no te data, error.
        {
            TramitHProc tramitH = tramitHProcForm.getTramitHProc();

            if (tramitH.isCaducitat()) {
                if (tramitH.getCaducitatdata() == null) {
                    result.rejectValue(get(CADUCITATDATA), "genapp.validation.required",
                            new String[] { I18NUtils.tradueix(CADUCITATDATA.fullName) }, null);

                }
                if (tramitH.getCaducitatdata().compareTo(new Date(System.currentTimeMillis())) < 0) {
                    result.rejectValue(get(CADUCITATDATA), "genapp.comodi",
                            new String[] { I18NUtils.tradueix(CADUCITATDATA.fullName) + " ha de ser posterior a la actual"}, null);
                }

            }
        }
    }
    
    public Long getTramitIDFromRequest(HttpServletRequest request) {
        return HibernateFileUtil.decryptFileID(request.getParameter("tramitid")); 
    }

}
