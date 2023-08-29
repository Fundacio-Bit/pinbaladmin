package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.pinbaladmin.back.controller.webdb.TramitAPersAutController;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.TramitAPersAutForm;
import org.fundaciobit.pinbaladmin.logic.TramitAPersAutLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitIServLogicaService;
import org.fundaciobit.pinbaladmin.logic.TramitJConsentLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.TramitAPersAut;
import org.fundaciobit.pinbaladmin.model.fields.TramitAPersAutFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitIServFields;
import org.fundaciobit.pinbaladmin.model.fields.TramitJConsentFields;
import org.fundaciobit.pinbaladmin.persistence.TramitAPersAutJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
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

    public static final int ADDITONAL_FIELD_START = 1;

    @EJB(mappedName = TramitAPersAutLogicaService.JNDI_NAME)
    protected TramitAPersAutLogicaService tramitAPersAutLogicEjb;

    @EJB(mappedName = TramitIServLogicaService.JNDI_NAME)
    protected TramitIServLogicaService tramitIServLogicEjb;

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
    public String getRedirectWhenCreated(HttpServletRequest request, TramitAPersAutForm tramitAPersAutForm) {
        //Cada vegada que es faci create, anar al tramit B
        Long tramitId = tramitAPersAutForm.getTramitAPersAut().getTramitid();

        //Al form del seguent, getParameter del tramitid, i utilitzar-ho per crear el tramitB
        return "redirect:" + TramitBOperadorController.CONTEXT_WEB + "/new?tramitid=" + tramitId;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long _ID_) {
        try {
            Long tramitID = (Long) request.getSession().getAttribute("tramitid");
            log.info("Estamos en A, vamos a borrar. TramitID=" + tramitID);

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
    public TramitAPersAutForm getTramitAPersAutForm(TramitAPersAutJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        TramitAPersAutForm tramitForm = super.getTramitAPersAutForm(_jpa, __isView, request, mav);

        if (__isView) {

        } else if (tramitForm.isNou()) {
            TramitAPersAutJPA tramitA = tramitForm.getTramitAPersAut();
            tramitA.setTramitid(tramitA.getPersautid());
            tramitForm.addHiddenField(TRAMITID);
            tramitForm.addHiddenField(DATATRAMIT);

            tramitA.setNif("45186147W");
            tramitA.setNom("Pau");
            tramitA.setTelefon("971123132");
            tramitA.setMail("mail@fbit.org");
            tramitA.setLlinatge1("Trias");
            tramitA.setLlinatge2("Segura");
            tramitA.setDatatramit(new Timestamp(System.currentTimeMillis()));
        }
        tramitForm.setTitleCode("tramit.sistra.titol.a");

        return tramitForm;
    }

    @Override
    public TramitAPersAutJPA create(HttpServletRequest request, TramitAPersAutJPA tramitAPersAut)
            throws I18NException, I18NValidationException {
        return (TramitAPersAutJPA) tramitAPersAutLogicEjb.create(tramitAPersAut);
    }

    @RequestMapping(value = "/generaxml/{tramitid}", method = RequestMethod.GET)
    public String generarXml(HttpServletRequest request, @PathVariable Long tramitid)
            throws I18NException, I18NValidationException {
        log.info("Generador del fitxer XML amb tramitID=" + tramitid);

        tramitAPersAutLogicEjb.generaXml(tramitid);
        log.info("Generat");

        return "redirect:" + TramitAOperadorController.RETURN_URL;
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
            Long[] identificadorsTramit = tramitAPersAutLogicEjb.getPartsTramitIDs(tramitID);
            boolean permetFinalitzar = true;

            for (int i = 0; i < lista.size(); i++) {
                Map<Long, String> map = lista.get(i);
                String url = request.getContextPath() + contextsWeb[i];

                String msg = letras[i];

                if (letras[i].equals("I")) {
                    long serveisEfegits = identificadorsTramit[i];
                    if (serveisEfegits > 0) {
                        msg = "List";
                        permetFinalitzar &= true;
                    } else {
                        permetFinalitzar &= false;
                        msg = "Add";
                    }
                    url += "/list/1?tramitid=" + tramitID;

                } else if (letras[i].equals("J")) {
                    Where w = Where.AND(TramitIServFields.TRAMITID.equal(tramitID),
                            TramitIServFields.CONSENTIMENT.equal("noop"),
                            TramitIServFields.CONSENTIMENTPUBLICAT.equal("2"));
                    
                    Long consentimentNecessari = tramitIServLogicEjb.count(w);
                    
                    if (consentimentNecessari > 0) {
                        if (identificadorsTramit[i] != null) {
                            url += "/" + identificadorsTramit[i] + "/edit";
                            permetFinalitzar &= true;
                        } else {
                            url += "/new?tramitid=" + tramitID;
                            msg += "*";
                            permetFinalitzar &= false;
                        }
                    }else {
                        msg = "";
                    }

                } else {
                    if (identificadorsTramit[i] != null) {
                        url += "/" + identificadorsTramit[i] + "/edit";
                        permetFinalitzar &= true;
                    } else {
                        url += "/new?tramitid=" + tramitID;
                        msg += "*";
                        permetFinalitzar &= false;
                    }
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
    public void delete(HttpServletRequest request, TramitAPersAut tramitAPersAut) throws I18NException {
        tramitAPersAutLogicEjb.deleteFull(tramitAPersAut.getTramitid());
    }

}