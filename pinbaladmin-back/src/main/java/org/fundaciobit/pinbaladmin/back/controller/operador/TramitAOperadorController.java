package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.pinbaladmin.back.controller.all.TramitAPublicController;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitAPersAutController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitCDadesCesiForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.hibernate.HibernateFileUtil;
import org.fundaciobit.pinbaladmin.logic.DocumentLogicaService;
import org.fundaciobit.pinbaladmin.logic.DocumentSolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.FitxerPublicLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitIServLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
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
@RequestMapping(value = TramitAOperadorController.CONTEXT_WEB)
@SessionAttributes(types = { TramitAPersAutForm.class, TramitAPersAutFilterForm.class })
public class TramitAOperadorController extends TramitAPersAutController {

    public static final String CONTEXT_WEB = "/operador/tramita";
    public static final String RETURN_URL = "/operador/tramita/list";
    public static final String RETURN_TO_SISTRA = RETURN_URL;
    public static final String CONTEXT_WEB_NEXT = TramitBOperadorController.CONTEXT_WEB;


    public static final int ADDITONAL_FIELD_START = 1;

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd_HH.mm_");

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;
    
    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @EJB(mappedName = TramitIServLogicaService.JNDI_NAME)
    protected TramitIServLogicaService tramitIServLogicEjb;

    @EJB(mappedName = OrganLogicaService.JNDI_NAME)
    protected OrganLogicaService organLogicaEjb;
    
    @EJB(mappedName = FitxerPublicLogicaService.JNDI_NAME)
    protected FitxerPublicLogicaService fitxerPublicLogicaEjb;
    @EJB(mappedName = DocumentSolicitudLogicaService.JNDI_NAME)
    protected DocumentSolicitudLogicaService documentSolicitudLogicaEjb;
    @EJB(mappedName = DocumentLogicaService.JNDI_NAME)
    protected DocumentLogicaService documentLogicaEjb;

    public String getContextWebNext() {
        return CONTEXT_WEB_NEXT;
    }
 
    public boolean isPublic() {
        return false;
    }
    public long actual() {
        return 0;
    }
    

    @Override
    public String getTileForm() {
        return "tramitAFormOperador";
    }

    @Override
    public String getTileList() {
        return "tramitSistraListOperador";
    }

    @Override
    public boolean isActiveList() {
        return true;
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
        return true;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public TramitAPersAutForm getTramitAPersAutForm(TramitAPersAutJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitAPersAutForm tramitForm = super.getTramitAPersAutForm(_jpa, __isView, request, mav);
        tramitForm.setTitleCode("tramit.sistra.titol.a");
        
        tramitForm.setDeleteButtonVisible(true);
        
        Long tramitID = null;
        if (!tramitForm.isNou()) {
            tramitID = tramitForm.getTramitAPersAut().getTramitid();
        }
        
        String uuid = HibernateFileUtil.encryptFileID(tramitID);

        tramitForm.setCancelButtonVisible(false);
        tramitForm.setDeleteButtonVisible(false);
        tramitForm.setSaveButtonVisible(false);

		tramitForm.addAdditionalButton(
				new AdditionalButton("", "genapp.continue", "javascript: $('form').submit();", "btn-primary"));

		tramitForm.addAdditionalButton(
				new AdditionalButton("", "genapp.delete", getContextWeb() + "/delete/" + uuid, "btn-danger"));
        
		String anotacions = null;
        TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
        tramitForm.setAttachedAdditionalJspCode(true);

        return tramitForm;
    }

    public TramitAPersAutFilterForm getTramitAPersAutFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        TramitAPersAutFilterForm tramitAPersAutFilterForm = super.getTramitAPersAutFilterForm(pagina, mav, request);
        if (tramitAPersAutFilterForm.isNou()) {

            Set<Field<?>> hiddens = new HashSet<Field<?>>(
                    Arrays.asList(TramitAPersAutFields.ALL_TRAMITAPERSAUT_FIELDS));
            hiddens.remove(TramitAPersAutFields.TRAMITID);
            hiddens.remove(TramitAPersAutFields.DATATRAMIT);
            tramitAPersAutFilterForm.setHiddenFields(hiddens);

            tramitAPersAutFilterForm.setAddButtonVisible(false);
            tramitAPersAutFilterForm.addAdditionalButton(new AdditionalButton("fas fa-plus-circle",
                    "tramir.sistra.start", getContextWeb() + "/new", "btn-primary"));

            OrderBy[] orderBy = { new OrderBy(TramitAPersAutFields.DATATRAMIT) };
            tramitAPersAutFilterForm.setDefaultOrderBy(orderBy);
            tramitAPersAutFilterForm.setOrderBy(TramitAPersAutFields.DATATRAMIT.fullName);
            tramitAPersAutFilterForm.setOrderAsc(false);

            tramitAPersAutFilterForm.setEditButtonVisible(false);

            String[] addFields = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
            for (int i = 0; i < addFields.length; i++) {
                AdditionalField<Long, String> addFieldName = new AdditionalField<Long, String>();
                addFieldName.setCodeName("=Tramit" + addFields[i]);
                addFieldName.setPosition(ADDITONAL_FIELD_START + i);
                // Els valors s'ompliran al mètode postList()
                addFieldName.setValueMap(new HashMap<Long, String>());
                addFieldName.setEscapeXml(false);

                tramitAPersAutFilterForm.addAdditionalField(addFieldName);
            }
        }

        return tramitAPersAutFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, TramitAPersAutFilterForm filterForm,
            List<TramitAPersAut> list) throws I18NException {
        super.postList(request, mav, filterForm, list);
        filterForm.getAdditionalButtonsByPK().clear();

        String[] contextsWeb = { TramitAOperadorController.CONTEXT_WEB, TramitBOperadorController.CONTEXT_WEB,
                TramitCOperadorController.CONTEXT_WEB, TramitDOperadorController.CONTEXT_WEB,
                TramitEOperadorController.CONTEXT_WEB, TramitFOperadorController.CONTEXT_WEB,
                TramitGOperadorController.CONTEXT_WEB, TramitHOperadorController.CONTEXT_WEB,
                TramitIOperadorController.CONTEXT_WEB, TramitJOperadorController.CONTEXT_WEB };
        List<Map<Long, String>> lista = new ArrayList<Map<Long, String>>();

        for (int i = 0; i < contextsWeb.length; i++) {
            Map<Long, String> map;
            map = (Map<Long, String>) filterForm.getAdditionalField(ADDITONAL_FIELD_START + i).getValueMap();
            map.clear();
            lista.add(map);
        }

        String[] letras = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

        for (TramitAPersAut tramitA : list) {
            long tramitID = tramitA.getTramitid();
            String uuid =  HibernateFileUtil.encryptFileID(tramitID);
            
            Long[] identificadorsTramit = tramitAPersAutLogicEjb.getPartsTramitIDs(tramitID);
            boolean permetFinalitzar = true;

            for (int i = 0; i < lista.size(); i++) {
                Map<Long, String> map = lista.get(i);
                
                String url = request.getContextPath() + contextsWeb[i];
                String msg = letras[i];

                switch (letras[i]) {
                    case "I":
                        if (identificadorsTramit[i] != null) {
                            msg = "List";
                            permetFinalitzar &= true;
                        } else {
                            permetFinalitzar &= false;
                            msg = "Add";
                        }
                        url += "/list/1?tramitid=" + uuid;
                    break;
                    default :

                        if (identificadorsTramit[i] != null) {
                            url += "/" + identificadorsTramit[i] + "/edit" ;
                            permetFinalitzar &= true;
                        } else {
                            url += "/new?tramitid=" + uuid;
                            msg += "*";
                            permetFinalitzar &= false;
                        }
                    break;

                }
                String div = "<a href='" + url + "'/> " + msg;
                map.put(tramitID, div);
            }
            if (permetFinalitzar) {
                filterForm.addAdditionalButtonByPK(tramitA.getPersautid(),
                        new AdditionalButton("fas fa-check icon-white", "generar.xml",
                                getContextWeb() + "/generaxml/" + tramitID, "btn-info"));
            }
        }
    }

    @Override
    public TramitAPersAutJPA create(HttpServletRequest request, TramitAPersAutJPA tramitAPersAut)
            throws I18NException, I18NValidationException {
        return (TramitAPersAutJPA) tramitAPersAutLogicEjb.create(tramitAPersAut);
    }

    @Override
    public TramitAPersAutJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long id) throws I18NException {
        return (TramitAPersAutJPA) tramitAPersAutLogicEjb.findByPrimaryKey(id);
    }

    @Override
    public TramitAPersAutJPA update(HttpServletRequest request, TramitAPersAutJPA tramitJPA)
            throws I18NException, I18NValidationException {
        return (TramitAPersAutJPA) tramitAPersAutLogicEjb.update(tramitJPA);
    }

    @Override
    public void delete(HttpServletRequest request, TramitAPersAut tramitAPersAut) throws I18NException {
        tramitAPersAutLogicEjb.deleteFull(tramitAPersAut.getTramitid());
    }

    //===========================================================================================================================

    //Si estamos en D, miramos el back de C, y que nos de su /edit
    @RequestMapping(value = "/back/{uuid}", method = RequestMethod.GET)
    public String getEditUrlFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
        return "redirect:" + getContextWeb() + "/edit/" + uuid;
    }
    
    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.GET)
    public ModelAndView editarTramitA2(@PathVariable("uuid") java.lang.String uuid, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {
        
        Long tramitID = HibernateFileUtil.decryptFileID(uuid);
        Long id = tramitAPersAutLogicEjb.executeQueryOne(TramitAPersAutFields.PERSAUTID,
                TramitAPersAutFields.TRAMITID.equal(tramitID));
    
        return super.editarTramitAPersAutGet(id, request, response);
    }
    @RequestMapping(value = "/edit/{uuid}", method = RequestMethod.POST)
    public String editarTramitAPersAutPost(@ModelAttribute TramitAPersAutForm tramitForm,
            BindingResult result, SessionStatus status, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {
        String ret =  super.editarTramitAPersAutPost(tramitForm, result, status, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitForm.getTramitAPersAut().getTramitid();

            log.info("tramitID: " + tramitID);
            log.info("actual: " + actual());
            log.info("isPublic: " + isPublic());
            
    		String anotacions = null;

            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
            tramitForm.setAttachedAdditionalJspCode(true);
        }
        return ret;
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, TramitAPersAutForm tramitAPersAutForm) {

        TramitAPersAutJPA tramitA = tramitAPersAutForm.getTramitAPersAut();
        request.getSession().setAttribute("tramitA", tramitA);

        Long tramitId = tramitA.getTramitid();
        String uuid =  HibernateFileUtil.encryptFileID(tramitId);

        return "redirect:" + getContextWebNext() + "/next/" + uuid;
    }
    
    @Override
    public String getRedirectWhenModified(HttpServletRequest request, TramitAPersAutForm tramitForm,
            Throwable __e) {
        log.info("Esteim a getRedirectWhenModified de TramitA");

        if (__e == null) {
            return getRedirectWhenCreated(request, tramitForm);
        } else {
            return getTileForm();
        }
    }
    
//--------------------------------------------------------------------------------------------------------------------------------------------
    
    public static Long getTramitIDFromRequest(HttpServletRequest request) {

        String param = request.getParameter("tramitid");
        try {
            Long tramitID = Long.parseLong(param);
            return tramitID;
        } catch (Throwable th) {
            return HibernateFileUtil.decryptFileID(param);
        }
    }

    public static void dadesWizard(HttpServletRequest request, Long tramitID, long actual, Boolean isPublic, TramitAPersAutLogicaService tramitAEjb, String anotacions) throws I18NException {
        
        
        Long[] identificadorsTramit = tramitAEjb.getPartsTramitIDs(tramitID);
        request.setAttribute("identificadorsTramit", identificadorsTramit);
        request.setAttribute("tramitActual", actual);
        request.setAttribute("anotacions", anotacions);
        
        String uuid = HibernateFileUtil.encryptFileID(tramitID);
        request.setAttribute("uuid", uuid);
        request.setAttribute("isPublic", isPublic);
    }
    
    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, Long persautid) {
        // TODO Auto-generated method stub
        return "redirect:" + TramitAOperadorController.RETURN_URL;
    }

    public static String getRedirectWhenDeleted(HttpServletRequest request, String uuid,
            TramitAPersAutLogicaService tramitAEjb) {
        try {
            Long tramitID = HibernateFileUtil.decryptFileID(uuid);
            tramitAEjb.deleteFull(tramitID);

            request.getSession().removeAttribute("tramitid");
            HtmlUtils.saveMessageError(request, "Tramit Cancelat (taules borrades)");
        } catch (I18NException e) {
            HtmlUtils.saveMessageError(request, "Error esborrant les taules del tramit sistra");
        }
        return "redirect:" + TramitAOperadorController.RETURN_URL;
    }
    
    @Override
    public String crearTramitAPersAutPost(TramitAPersAutForm tramitAPersAutForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ret = super.crearTramitAPersAutPost(tramitAPersAutForm, result, request, response);
        if (result.hasErrors()) {
            Long tramitID = tramitAPersAutForm.getTramitAPersAut().getTramitid();

            String anotacions = null;
            TramitAOperadorController.dadesWizard(request, tramitID, actual(), isPublic(), tramitAPersAutLogicEjb, anotacions);
            tramitAPersAutForm.setAttachedAdditionalJspCode(true);
        }
        return ret;
    }
    
    @RequestMapping(value = "/delete/{uuid}", method = RequestMethod.GET)
    public String deleteFromUuid(HttpServletRequest request, @PathVariable String uuid)
            throws I18NException, I18NValidationException {
//        return TramitAOperadorController.getRedirectWhenDeleted(request, uuid, tramitAPersAutLogicEjb);
    	return "redirect:" + TramitAPublicController.CONTEXT_WEB + "/cancelarTramit/" + uuid;
    }

}
