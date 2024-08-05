package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.IncidenciaTecnicaController;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaForm;
import org.fundaciobit.pinbaladmin.persistence.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.EventQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.model.fields.OperadorFields;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.PinbalAdminUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = IncidenciaTecnicaOperadorController.WEBCONTEXT)
@SessionAttributes(types = { IncidenciaTecnicaForm.class, IncidenciaTecnicaFilterForm.class })
public class IncidenciaTecnicaOperadorController extends IncidenciaTecnicaController implements Constants {

    public static final String WEBCONTEXT = "/operador/incidencia";

    public static final int FILTRE_AVANZAT_COLUMN = -1;

    public static final int MISSATGES_SENSE_LLEGIR_COLUMN = 1;

    public static final StringField FILTRE_AVANZAT_FIELD = DESCRIPCIO;

    public static final String SESSION_SUBFILTRE_NO_LLEGIT = "SESSION_SUBFILTRE_NO_LLEGIT";

    @EJB(mappedName = IncidenciaTecnicaLogicaService.JNDI_NAME)
    protected IncidenciaTecnicaLogicaService incidenciaTecnicaLogicaEjb;

    @EJB(mappedName = EventLogicaService.JNDI_NAME)
    protected EventLogicaService eventLogicaEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OperadorService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.OperadorService operadorEjb;

    @Override
    public String getTileForm() {
        return "incidenciaTecnicaFormOperador";
    }

    @Override
    public String getTileList() {
        return "incidenciaTecnicaListOperador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return this.getClass().getName() + "_FilterForm";
    }

    @Override
    public String getEntityNameCode() {
        switch (getVistaIncidencia()) {
            default:
            case NORMAL:
                return "incidenciaTecnica.incidenciaTecnica";
            case NOLLEGITSMEUS:
                return "incidenciaNoLlegitsMeus";
            case NOLLEGITSNOMEUS:
                return "incidenciaNoLlegitsNoMeus";

        }
    }

    @Override
    public String getEntityNameCodePlural() {
        return getEntityNameCode() + ".plural";
    }

    public enum VistaIncidencia {
        NORMAL, NOLLEGITSMEUS, NOLLEGITSNOMEUS,
    }

    public VistaIncidencia getVistaIncidencia() {
        return VistaIncidencia.NORMAL;
    }

    /*
     * @RequestMapping(value = "/list", method = RequestMethod.GET)
     * 
     * @Override public String llistat(HttpServletRequest request,
     * HttpServletResponse response) throws I18NException {
     * 
     * 
     * //request.getSession().setAttribute(SESSION_SUBFILTRE_NO_LLEGIT, response);
     * **
     * 
     * request.getSession().removeAttribute(SESSION_SUBFILTRE_NO_LLEGIT);
     * 
     * return super.llistat(request, response); }
     * 
     * @RequestMapping(value = "/listnollegitsmeus", method = RequestMethod.GET)
     * public String llistatNoLlegitsMeus(HttpServletRequest request,
     * HttpServletResponse response) throws I18NException {
     * 
     * 
     * //request.getSession().setAttribute(SESSION_SUBFILTRE_NO_LLEGIT, response);
     * **
     * 
     * request.getSession().removeAttribute(SESSION_SUBFILTRE_NO_LLEGIT);
     * 
     * return super.llistat(request, response); }
     * 
     * 
     * @RequestMapping(value = "/listnollegitnomeus", method = RequestMethod.GET)
     * public String llistatNoLlegitsNoMeus(HttpServletRequest request,
     * HttpServletResponse response) throws I18NException {
     * 
     * 
     * //request.getSession().setAttribute(SESSION_SUBFILTRE_NO_LLEGIT, response);
     * **
     * 
     * request.getSession().removeAttribute(SESSION_SUBFILTRE_NO_LLEGIT);
     * 
     * return super.llistat(request, response); }
     */

    @Override
    public IncidenciaTecnicaFilterForm getIncidenciaTecnicaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        IncidenciaTecnicaFilterForm incidenciaTecnicaFilterForm;
        incidenciaTecnicaFilterForm = super.getIncidenciaTecnicaFilterForm(pagina, mav, request);

        if (incidenciaTecnicaFilterForm.isNou()) {

            incidenciaTecnicaFilterForm.addLabel(INCIDENCIATECNICAID, "=#Incidència");

            incidenciaTecnicaFilterForm.addHiddenField(NOMENTITAT);
            incidenciaTecnicaFilterForm.addHiddenField(DESCRIPCIO);
            incidenciaTecnicaFilterForm.addHiddenField(CONTACTEEMAIL);
            incidenciaTecnicaFilterForm.addHiddenField(CONTACTETELEFON);
            incidenciaTecnicaFilterForm.addHiddenField(CAIDIDENTIFICADORCONSULTA);
            incidenciaTecnicaFilterForm.addHiddenField(CAIDNUMEROSEGUIMENT);

            // incidenciaTecnicaFilterForm.setGroupBy(ESTAT.fullName);
            // incidenciaTecnicaFilterForm.setGroupValue(String.valueOf(Constants.ESTAT_INCIDENCIA_TECNICA_OBERTA));

            if (getVistaIncidencia() != VistaIncidencia.NORMAL) {
                incidenciaTecnicaFilterForm.setAddButtonVisible(false);
            }

            incidenciaTecnicaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-bullhorn", "events.titol",
                    EventIncidenciaTecnicaOperadorController.CONTEXT_PATH + "/veureevents/{0}", AdditionalButtonStyle.SUCCESS));


            // Valors Inicials Filtre
            incidenciaTecnicaFilterForm.setFilterByFields(incidenciaTecnicaFilterForm.getDefaultFilterByFields());
//            incidenciaTecnicaFilterForm.getFilterByFields().add(INCIDENCIATECNICAID);
//            incidenciaTecnicaFilterForm.getFilterByFields().add(ESTAT);
//            incidenciaTecnicaFilterForm.getFilterByFields().add(DATAINICI);
            incidenciaTecnicaFilterForm.getFilterByFields().add(TITOL);
            
            incidenciaTecnicaFilterForm.getFilterByFields().remove(TIPUS);

            if (showAdvancedFilter()) {

                AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
                adfield4.setCodeName("solicitud.filtreavanzat");
                adfield4.setPosition(FILTRE_AVANZAT_COLUMN);

                adfield4.setEscapeXml(false);
                adfield4.setSearchBy(FILTRE_AVANZAT_FIELD);

                incidenciaTecnicaFilterForm.addAdditionalField(adfield4);

                incidenciaTecnicaFilterForm.getHiddenFields().add(FILTRE_AVANZAT_FIELD);

                incidenciaTecnicaFilterForm.getFilterByFields().remove(TITOL);
                incidenciaTecnicaFilterForm.getFilterByFields().remove(DESCRIPCIO);
                incidenciaTecnicaFilterForm.getFilterByFields().remove(NOMENTITAT);
                incidenciaTecnicaFilterForm.getFilterByFields().remove(CONTACTEEMAIL);
            }

            {
                AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
                adfield4.setCodeName("=Sense Llegir");
                adfield4.setPosition(MISSATGES_SENSE_LLEGIR_COLUMN);

                adfield4.setEscapeXml(false);
                adfield4.setValueMap(new HashMap<Long, String>());

                incidenciaTecnicaFilterForm.addAdditionalField(adfield4);
            }
            
            incidenciaTecnicaFilterForm.setGroupByFields(incidenciaTecnicaFilterForm.getDefaultGroupByFields());
            incidenciaTecnicaFilterForm.getGroupByFields().add(DATAINICI);

            incidenciaTecnicaFilterForm.setOrderBy(DATAINICI.javaName);
            incidenciaTecnicaFilterForm.setOrderAsc(false);

            
            switch (getVistaIncidencia()) {

                case NORMAL:
                    incidenciaTecnicaFilterForm.getHiddenFields().add(IncidenciaTecnicaFields.CREADOR);
                    incidenciaTecnicaFilterForm.setGroupBy(OPERADOR.javaName);
                    incidenciaTecnicaFilterForm.setGroupValue(request.getRemoteUser());
                break;

                case NOLLEGITSNOMEUS:
//                    incidenciaTecnicaFilterForm.setEstatDesde(Constants.ESTAT_INCIDENCIA_OBERTA);
//                    incidenciaTecnicaFilterForm.setEstatFins(Constants.ESTAT_INCIDENCIA_PENDENT_DE_TERCER);
                    incidenciaTecnicaFilterForm.getHiddenFields().add(IncidenciaTecnicaFields.DATAFI);
                    incidenciaTecnicaFilterForm.setGroupBy(OPERADOR.javaName);
                    incidenciaTecnicaFilterForm.setGroupValue(request.getRemoteUser());
                break;

                case NOLLEGITSMEUS:
                    incidenciaTecnicaFilterForm.setOperador(request.getRemoteUser());
                    incidenciaTecnicaFilterForm.getHiddenFields().add(IncidenciaTecnicaFields.OPERADOR);
                    incidenciaTecnicaFilterForm.getHiddenFields().add(IncidenciaTecnicaFields.DATAFI);
                    break;
            }
            
            incidenciaTecnicaFilterForm.setAttachedAdditionalJspCode(true);
        }

        return incidenciaTecnicaFilterForm;
    }

    public boolean showAdvancedFilter() {
        return true;
    }

    @Override
    public IncidenciaTecnicaForm getIncidenciaTecnicaForm(IncidenciaTecnicaJPA _jpa, boolean __isView,
            HttpServletRequest request, ModelAndView mav) throws I18NException {
        IncidenciaTecnicaForm incidenciaTecnicaForm = super.getIncidenciaTecnicaForm(_jpa, __isView, request, mav);

        if (incidenciaTecnicaForm.isNou()) {

            incidenciaTecnicaForm.getIncidenciaTecnica().setDataInici(new Timestamp(System.currentTimeMillis()));
            incidenciaTecnicaForm.getIncidenciaTecnica().setEstat(ESTAT_INCIDENCIA_OBERTA);

            incidenciaTecnicaForm.addReadOnlyField(DATAINICI);
            incidenciaTecnicaForm.addReadOnlyField(ESTAT);
            //incidenciaTecnicaForm.addReadOnlyField(CREADOR);

            incidenciaTecnicaForm.getIncidenciaTecnica().setOperador(request.getRemoteUser());
        }

        return incidenciaTecnicaForm;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        Where w1;

        switch (getVistaIncidencia()) {
            default:
            case NORMAL: {
                w1 = null;
            }
            break;
            case NOLLEGITSMEUS: {

                // incidencies meves
                SubQuery<Event, Long> subQuery = eventLogicaEjb.getSubQuery(EventFields.INCIDENCIATECNICAID, Where
                        .AND(EventFields.NOLLEGIT.equal(Boolean.TRUE), EventFields.INCIDENCIATECNICAID.isNotNull()));
                w1 = Where.AND(OPERADOR.equal(request.getRemoteUser()), INCIDENCIATECNICAID.in(subQuery));
            }
            break;
            case NOLLEGITSNOMEUS: {
                // incidencies No Meves
                SubQuery<Event, Long> subQuery = eventLogicaEjb.getSubQuery(EventFields.INCIDENCIATECNICAID, Where
                        .AND(EventFields.NOLLEGIT.equal(Boolean.TRUE), EventFields.INCIDENCIATECNICAID.isNotNull()));
                w1 = Where.AND(OPERADOR.notEqual(request.getRemoteUser()), INCIDENCIATECNICAID.in(subQuery));
            }
        }

        Where w2 = getAdditionaConditionAdvancedFilter(request);

        if (w1 == null) {
            if (w2 == null) {
                return null;
            } else {
                return w2;
            }
        } else {
            if (w2 == null) {
                return w1;
            } else {
                return Where.AND(w1, w2);
            }
        }

    }

    protected Where getAdditionaConditionAdvancedFilter(HttpServletRequest request) throws I18NException {

        String af = request.getParameter(FILTRE_AVANZAT_FIELD.getFullName());
        log.info(" Valor Filtre Avanzat FilterBY => ]" + af + "[");

        if (af == null || af.trim().length() == 0) {
//            log.info("getAdditionalCondition::NO FILTRAM AVANZAT !!!!");
            return null;
        } else {

            final String likeStr = "%" + af + "%";

            final boolean isNumber = PinbalAdminUtils.isNumber(af);

            // Dades general
            Where w = Where.OR(TITOL.like(likeStr), DESCRIPCIO.like(likeStr), NOMENTITAT.like(likeStr),
                    CONTACTENOM.like(likeStr), CONTACTEEMAIL.like(likeStr));

            // identificador de consulta o numero seguiment de la incidència
            if (isNumber) {
                w = Where.OR(w, CAIDIDENTIFICADORCONSULTA.like(likeStr), CAIDNUMEROSEGUIMENT.like(likeStr),
                        INCIDENCIATECNICAID.equal(Long.parseLong(af)));
            }

            // Comentari dels Events
            SubQuery<Event, Long> subquery1 = eventLogicaEjb.getSubQuery(EventFields.INCIDENCIATECNICAID,
                    Where.AND(EventFields.INCIDENCIATECNICAID.isNotNull(), EventFields.COMENTARI.like(likeStr)));
            w = Where.OR(w, IncidenciaTecnicaFields.INCIDENCIATECNICAID.in(subquery1));

            // identificador de consulta o numero seguiment dels events
            if (isNumber) {
                SubQuery<Event, Long> subquery2a = eventLogicaEjb.getSubQuery(EventFields.INCIDENCIATECNICAID,
                        Where.AND(EventFields.INCIDENCIATECNICAID.isNotNull(),
                                EventFields.CAIDIDENTIFICADORCONSULTA.like(likeStr)));
                SubQuery<Event, Long> subquery2b = eventLogicaEjb.getSubQuery(EventFields.INCIDENCIATECNICAID,
                        Where.AND(EventFields.INCIDENCIATECNICAID.isNotNull(),
                                EventFields.CAIDNUMEROSEGUIMENT.like(likeStr)));
                w = Where.OR(w, IncidenciaTecnicaFields.INCIDENCIATECNICAID.in(subquery2a),
                        IncidenciaTecnicaFields.INCIDENCIATECNICAID.in(subquery2b));
            }

//            log.info("getAdditionalCondition::FILTRAM AVANZAT !!!!!!!!!!");

            log.info("Where ]" + w.toSQL() + "[");
            return w;
        }

    }

    @Override
    public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue(String.valueOf(ESTAT_INCIDENCIA_OBERTA), "Oberta"));
        __tmp.add(new StringKeyValue(String.valueOf(ESTAT_INCIDENCIA_PENDENT_DE_TERCER), "Pendent de Tercer"));
        __tmp.add(new StringKeyValue(String.valueOf(ESTAT_INCIDENCIA_TANCADA), "Tancada"));
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        __tmp.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_TECNICA), "Tècnica"));
        __tmp.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_CONSULTA), "Consulta"));
        __tmp.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_INTEGRACIONS), "Integracions"));
        __tmp.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_ROLEPERMISOS), "Roles de permisos"));
        return __tmp;
    }

    @Override
    public IncidenciaTecnicaJPA create(HttpServletRequest request, IncidenciaTecnicaJPA incidenciaTecnica)
            throws I18NException, I18NValidationException {

        IncidenciaTecnicaJPA it;
        it = (IncidenciaTecnicaJPA) incidenciaTecnicaEjb.create(incidenciaTecnica);

        String username = request.getUserPrincipal().getName();
        Long incidenciaID = it.getIncidenciaTecnicaID();
        String msg = "S'ha creat la Incidència";
        boolean noLlegit = false;
        
        afegirEventIncidencia(incidenciaID, username, msg, noLlegit);
        
        return it;
    }

    @Override
    protected List<IncidenciaTecnica> processarLlistat(ITableManager<IncidenciaTecnica, Long> ejb,
            BaseFilterForm filterForm, int pagina, Where whereAdditionalCondition, ModelAndView mav)
            throws I18NException {
        if (filterForm == null) {
            throw new NullPointerException("FilterForm mai pot ser NULL !!!!");
        }

        // Eliminam temporalment el filtre especial, per a que no doni problemes
        // internament.

        AdditionalField<?, ?> filtreAvanzatField = null;
        if (showAdvancedFilter()) {
            filtreAvanzatField = filterForm.getAdditionalFields().remove(FILTRE_AVANZAT_COLUMN);
        }

        List<IncidenciaTecnica> list;
        try {
            list = super.processarLlistat(ejb, filterForm, pagina, whereAdditionalCondition, mav);
        } finally {
            if (filtreAvanzatField != null) {

                filterForm.getAdditionalFields().put(FILTRE_AVANZAT_COLUMN, filtreAvanzatField);

                String valorFA = filtreAvanzatField.getSearchByValue();

                if (valorFA != null && valorFA.trim().length() != 0) {
                    filterForm.setVisibleFilterBy(true);
                }

            }
        }

        return list;

    }

    @Override
    public void delete(HttpServletRequest request, IncidenciaTecnica incidenciaTecnica) throws I18NException {
        incidenciaTecnicaLogicaEjb.deleteFull(incidenciaTecnica.getIncidenciaTecnicaID());
    }

    @RequestMapping(value = "/close/{incidenciaTecnicaID}", method = RequestMethod.GET)
    public String closeIncidenciaTecnicaGet(@PathVariable("incidenciaTecnicaID") java.lang.Long incidenciaTecnicaID,
            HttpServletRequest request, HttpServletResponse response) throws I18NException {

        IncidenciaTecnicaJPA it = this.findByPrimaryKey(request, incidenciaTecnicaID);

        it.setEstat(Constants.ESTAT_INCIDENCIA_TANCADA);
        it.setDataFi(new Timestamp(System.currentTimeMillis()));
        
        try {
            this.update(request, it);

            
            String username = request.getUserPrincipal().getName();
            Long incidenciaID = it.getIncidenciaTecnicaID();
            java.lang.String msg = "S'ha tancat la Incidència";
            boolean noLlegit = false;
            
            afegirEventIncidencia(incidenciaID, username, msg, noLlegit);
            
            
            HtmlUtils.saveMessageSuccess(request, "Tancada Incidència correctament.");
            
        } catch (Throwable e) {
            String msg = "Error tancant incidència " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + WEBCONTEXT + "/" + incidenciaTecnicaID + "/edit";
    }


    @RequestMapping(value = "/changeOperador/{incidenciaTecnicaID}/{operador}", method = RequestMethod.GET)
    public String changeOperadorIncidenciaTecnicaGet(
            @PathVariable("incidenciaTecnicaID") java.lang.Long incidenciaTecnicaID,
            @PathVariable("operador") java.lang.String operador, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {

        IncidenciaTecnicaJPA i = this.findByPrimaryKey(request, incidenciaTecnicaID);

        String operador_old = i.getOperador();
        i.setOperador(operador);

        SelectMultipleStringKeyValue smskv;
        smskv = new SelectMultipleStringKeyValue(OperadorFields.USERNAME.select, OperadorFields.NOM.select);
        List<StringKeyValue> operadors = operadorEjb.executeQuery(smskv, Where.OR(OperadorFields.USERNAME.equal(operador_old), OperadorFields.USERNAME.equal(operador)));

        Map<String, String> operadors_map;
        operadors_map = Utils.listToMap(operadors);
        
        String nom_operador = operadors_map.get(operador);
        String nom_operador_old  = operadors_map.get(operador_old);
                
        try {
            this.update(request, i);

            String username = request.getUserPrincipal().getName();
            Long incidenciaID = incidenciaTecnicaID;
            boolean noLlegit = true;
            
            String msg = I18NUtils.tradueix("missatge.canvi.operador", "incidencia", operador, nom_operador,
                    operador_old, nom_operador_old);

            afegirEventIncidencia(incidenciaID, username, msg, noLlegit);

            HtmlUtils.saveMessageSuccess(request,
                    "Operador canviat correctament. (" + operador_old + " -> " + operador + ")");

        } catch (Throwable e) {
            String msg = "Error canviant operador: " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + WEBCONTEXT + "/list";
    }

    
    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, IncidenciaTecnicaFilterForm filterForm,
            List<IncidenciaTecnica> list) throws I18NException {

        super.postList(request, mav, filterForm, list);

        Map<Long, String> map;
        map = (Map<Long, String>) filterForm.getAdditionalField(MISSATGES_SENSE_LLEGIR_COLUMN).getValueMap();
        map.clear();

        final StringField operador = new EventQueryPath().INCIDENCIATECNICA().OPERADOR();

        final String loginUserName = request.getRemoteUser();

        for (IncidenciaTecnica inc : list) {

            final String user = inc.getOperador();

            // incidencies

            Long incidencies = eventLogicaEjb.count(Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE),
                    EventFields.INCIDENCIATECNICAID.equal(inc.getIncidenciaTecnicaID()), operador.equal(user)));

            if (incidencies != 0) {

                final String color;
                if (loginUserName.equals(user)) {
                    color = "danger";
                } else {
                    color = "warning";
                }

                final String text = "<span title=\"Events no llegits\" class=\"badge badge-" + color + " \">"
                        + incidencies + "</span>" + "<span title=\"Events no llegits\" class=\"label label-" + color
                        + "\"><b>&#9888;</b></span>";

                map.put(inc.getIncidenciaTecnicaID(), text);

            }

        }

    }

    @Override
    public List<StringKeyValue> getReferenceListForCreador(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        SelectMultipleStringKeyValue smskv;
        smskv = new SelectMultipleStringKeyValue(OperadorFields.USERNAME.select, OperadorFields.NOM.select);

        List<StringKeyValue> operadors = operadorEjb.executeQuery(smskv);

        for (StringKeyValue skv : operadors) {
            __tmp.add(new StringKeyValue(skv.getKey(), skv.getValue()));
        }

        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForOperador(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        return getReferenceListForCreador(request, mav, where);
    }
    
    private void afegirEventIncidencia(Long incidenciaID, String username, String msg, boolean noLlegit) {
        try {
            java.lang.Long _solicitudID_ = null;
            java.lang.Long _incidenciaTecnicaID_ = incidenciaID;
            java.sql.Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
            java.lang.String _persona_ = username;
            java.lang.String _comentari_ = msg;
            java.lang.String _asumpte_ = "Actualització Incidencia " + incidenciaID;
            
            java.lang.Long _fitxerID_ = null;
            boolean _noLlegit_ = noLlegit;
            java.lang.String _destinatari_ = null;
            java.lang.String _destinatariMail_ = null;

            eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_, _destinatari_, _destinatariMail_, _asumpte_, _comentari_,
                    _fitxerID_, _noLlegit_, null, null);
        } catch (Throwable th) {
            log.error("Error afegint event a la incidència tecnica: " + th.getMessage(), th);
        }
    }

    
    //A la página d'edició s'arriba desde el llistat d'incidencies i desde la pàgina d'events.
    @Override
    public String getRedirectWhenModified(HttpServletRequest request, IncidenciaTecnicaForm incidenciaTecnicaForm,
    		Throwable __e) {
    	Long incidenciaTecnicaID = incidenciaTecnicaForm.getIncidenciaTecnica().getIncidenciaTecnicaID();
        return redirectToLlistaEvents(incidenciaTecnicaID);
    }
    
    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, Long incidenciaTecnicaID) {
    	// TODO Auto-generated method stub
    	return redirectToLlistaEvents(incidenciaTecnicaID);
    }
    
    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, IncidenciaTecnicaForm incidenciaTecnicaForm) {
    	
    	Long indicendiaID = incidenciaTecnicaForm.getIncidenciaTecnica().getIncidenciaTecnicaID();
        return redirectToLlistaEvents(indicendiaID);
    }
    
	public String redirectToLlistaEvents(Long incidenciaTecnicaID) {
		return "redirect:/operador/eventincidenciatecnica/veureevents/" + incidenciaTecnicaID;
	}
}
