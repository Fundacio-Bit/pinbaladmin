package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.CustomField;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.GroupByValueItem;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.SolicitudController;
import org.fundaciobit.pinbaladmin.back.form.webdb.AreaRefList;
import org.fundaciobit.pinbaladmin.back.form.webdb.DepartamentRefList;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.PinbalAdminUtils;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;
import org.fundaciobit.pinbaladmin.logic.EventLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.utils.LogicUtils;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.DepartamentFields;
import org.fundaciobit.pinbaladmin.model.fields.DepartamentQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.EventQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.OperadorFields;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.persistence.EventJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


/**
 * 
 * @author anadal
 *
 */
public abstract class SolicitudOperadorController extends SolicitudController {

    public static final Field<?> AREA_NOVA_ID = new SolicitudQueryPath().DEPARTAMENT().AREA().AREAID();

    public static final int FILTRE_AVANZAT_COLUMN = -1;

    public static final int COLUMNA_ORGAN = 1;
    public static final int MISSATGES_SENSE_LLEGIR_COLUMN = 2;
    public static final int ENTITAT_COLUMN = 1;




    public static final Field<?> ENTITAT_NOVA_ID = new SolicitudQueryPath().DEPARTAMENT().AREA().ENTITAT().ENTITATID();


    public static final StringField FILTRE_AVANZAT_FIELD = CODIDESCRIPTIU; // new
                                                                           // StringField("filtreavtable",
                                                                           // "filtreavjava",
                                                                           // "filtreavsql");

    public static final CustomField BALEARS;

    static {
        BALEARS = new CustomField("entitatServei.balears");
    }

    // EJB's
    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.ServeiService serveiEjb;

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.SolicitudServeiService solicitudServeiEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.AreaService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.AreaService areaEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OrganService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.OrganService organEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DepartamentService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.DepartamentService departamentEjb;

    @EJB(mappedName = EventLogicaService.JNDI_NAME)
    protected EventLogicaService eventLogicaEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.OperadorService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.OperadorService operadorEjb;

    @Autowired
    protected AreaRefList areaNovaRefList;

    // public static final int SERVEIS = 1;

    // NULL => Els dos
    // true => Estatal
    // false => Local
    public abstract Boolean isEstatal();

    // NULL => Els dos
    // true => Estatal
    // false => Local
    public abstract boolean showAdvancedFilter();

    @Override
    public String getTileForm() {
        return "solicitudFormWebDB_operador";
    }

    @Override
    public String getTileList() {
        return "solicitudListWebDB_operador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "SolicitudWeb" + isEstatal() + "DB_FilterForm_Operador_";
    }

    // protected DepartamentRefList departamentRefListPerLlistat;

    // protected AreaRefList areaRefListPerForm;

    /* falta metode init() per convertir departament a NOMENTITAT - DEPARTAMENT */
    @PostConstruct
    public void init() {

        /*
         * AreaQueryPath aqp = new AreaQueryPath();
         * 
         * this.areaRefList = new AreaRefList(this.areaRefList);
         * areaRefList.setSelects(new Select[] { aqp.ENTITAT().NOM().select,
         * AreaFields.NOM.select }); areaRefList.setSeparator(" - ");
         */

        // PER LLISTAT
        /*
         * departamentRefListPerLlistat = new
         * DepartamentRefList(this.departamentRefList);
         * departamentRefList.setSelects(new Select[] { DepartamentFields.NOM.select
         * , new SelectConstant(" ["), DepartamentFields.DEPARTAMENTID.select,new
         * SelectConstant("]") }); departamentRefList.setSeparator("");
         */

        // PER FORMULARI
        DepartamentQueryPath dqp = new DepartamentQueryPath();

        // Configura com es mostra el departament
        this.departamentRefList = new DepartamentRefList(this.departamentRefList);
        departamentRefList.setSelects(new Select[] { dqp.AREA().ENTITAT().NOM().select, dqp.AREA().NOM().select,
                DepartamentFields.NOM.select });
        departamentRefList.setSeparator(" - ");

    }

    @Override
    public String getEntityNameCode() {

        Boolean isestatal = isEstatal();

        if (isestatal == null) {
            return "solicitud.solicitudactiva";
        }

        if (isestatal) {
            return "solicitud.estatal";
        } else {
            return "solicitud.local";
        }
    }

    @Override
    public String getEntityNameCodePlural() {
        return getEntityNameCode() + ".plural";
    }

    @Override
    public void delete(HttpServletRequest request, Solicitud solicitud) throws I18NException {
        Set<Long> deleteFiles = solicitudLogicaEjb.deleteFull(solicitud.getSolicitudID(), true);

        // Si tot ha anat be llavors borram els fitxers
        if (deleteFiles.size() != 0) {
            LogicUtils.deleteFiles(deleteFiles, fitxerEjb);
        }
    }

    @Override
    public SolicitudForm getSolicitudForm(SolicitudJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {

        SolicitudForm solicitudForm = super.getSolicitudForm(_jpa, __isView, request, mav);

        SolicitudJPA soli = solicitudForm.getSolicitud();
        Boolean isestatal = isEstatal();
        if (solicitudForm.isNou()) {
            solicitudForm.addHiddenField(DATAFI);

            solicitudForm.setAttachedAdditionalJspCode(true);
            
            soli.setEstatID(10L);
            soli.setProduccio(true);
            soli.setDataInici(new Timestamp(System.currentTimeMillis()));
            soli.setCreador(request.getRemoteUser());
            soli.setOperador(request.getRemoteUser());
        }

        if (!__isView) {
            log.info("desplegableOrgans = true");
            mav.addObject("desplegableOrgans", true);
            solicitudForm.setAttachedAdditionalJspCode(true);
        } else {
            String tipusProc = solicitudForm.getSolicitud().getProcedimentTipus();
            if (tipusProc != null) {
                String minusc = tipusProc.toLowerCase();
                if (!tipusProc.equals(minusc)) {
                    solicitudForm.getSolicitud().setProcedimentTipus(minusc);
                }
            }
        }
        
        if (isestatal == null) {
            // NO SABEM SI ES ESTATAL O LOCAL
            // log.info("XYZ ZZZ __isView = " + __isView);
            if (__isView) {

                // log.info("XYZ ZZZ _jpa.getDepartamentID() = " +
                // _jpa.getDepartamentID());

                if (_jpa.getDepartamentID() == null && _jpa.getOrganid() == null) {
                    // Es Estatal

                    amagarCampsEstatal(solicitudForm);
                    
                } else {
                    // Es Local
                    solicitudForm.addHiddenField(ENTITATESTATAL);
                }
            } else {

                if (!solicitudForm.isNou()) {
                    if (_jpa.getDepartamentID() == null  && _jpa.getOrganid() == null) {
                        // solicitudForm.addHiddenField(ENTITATLOCALID);
                        solicitudForm.addHiddenField(DEPARTAMENTID);
                        solicitudForm.addHiddenField(ORGANID);
                    } else {
                        solicitudForm.addHiddenField(ENTITATESTATAL);
                    }
                }

                // solicitudForm.addReadOnlyField(ENTITATLOCALID);
                solicitudForm.addReadOnlyField(ENTITATESTATAL);
            }
        } else {
            if (isestatal) {

                // solicitudForm.addHiddenField(ENTITATLOCALID);
                amagarCampsEstatal(solicitudForm);

            } else {
                solicitudForm.addHiddenField(ENTITATESTATAL);
                // solicitudForm.addHiddenField(AREAID);
            }
        }

        final long creacio = soli.getDataInici().getTime();
        final long usuarischeck = 1434089678706L;

        if (creacio > usuarischeck) {
            solicitudForm.addReadOnlyField(DATAINICI);
            solicitudForm.addReadOnlyField(CREADOR);
        }

        /*
         * solicitudForm.addHiddenField(CANVIECHOESTAT);
         * 
         * solicitudForm.addHiddenField( ESTAT);
         * solicitudForm.addHiddenField(SITUACIODISCAPACITAT);
         * solicitudForm.addHiddenField(TICKETASSOCIAT);
         * solicitudForm.addHiddenField( CONSULTADADES);
         * solicitudForm.addHiddenField( CANVIECHOESTAT);
         * solicitudForm.addHiddenField( MARILEN);
         * solicitudForm.addHiddenField(DANI);
         */

        return solicitudForm;
    }

    private void amagarCampsEstatal(SolicitudForm solicitudForm) {
        
        solicitudForm.addHiddenField(ORGANID);
        solicitudForm.addHiddenField(DEPARTAMENTID);
        solicitudForm.addHiddenField(PINFO);
        solicitudForm.addHiddenField(SolicitudFields.PERSONACONTACTE);
        solicitudForm.addHiddenField(SolicitudFields.PERSONACONTACTEEMAIL);

        solicitudForm.addHiddenField(SolicitudFields.TICKETNUMEROSEGUIMENT);

        solicitudForm.addHiddenField(SolicitudFields.RESPONSABLEPROCEMAIL);
        solicitudForm.addHiddenField(SolicitudFields.RESPONSABLEPROCNOM);

        solicitudForm.addHiddenField(SolicitudFields.DENOMINACIO);
        solicitudForm.addHiddenField(SolicitudFields.DIR3);
        solicitudForm.addHiddenField(SolicitudFields.NIF);

        solicitudForm.addHiddenField(SolicitudFields.CODIDESCRIPTIU);
    }

    @Override
    public void postValidate(HttpServletRequest request, SolicitudForm solicitudForm, BindingResult result)
            throws I18NException {

        // log.info(" XYZ ZZZ postValidate");

        Boolean isestatal = isEstatal();

        // log.info(" XYZ ZZZ postValidate => isestatal = " + isestatal);

        if (isestatal != null) {

            if (isestatal) {
                String nom = solicitudForm.getSolicitud().getEntitatEstatal();
                if (nom == null || nom.trim().length() == 0) {
                    ValidationUtils.rejectIfEmptyOrWhitespace(result, get(ENTITATESTATAL), "genapp.validation.required",
                            new Object[] { I18NUtils.tradueix(ENTITATESTATAL.fullName) });
                }
            } else {

                // ======== ENTITAT
                /*
                 * Long entitatid = solicitudForm.getSolicitud().getEntitatLocalID();
                 * 
                 * log.info(" XYZ ZZZ postValidate => entitatid = " + entitatid);
                 * 
                 * if (entitatid == null) {
                 * ValidationUtils.rejectIfEmptyOrWhitespace(result,
                 * get(ENTITATLOCALID), "genapp.validation.required", new Object[] {
                 * I18NUtils.tradueix(ENTITATLOCALID.fullName) }); return; }
                 * 
                 */

                // ======== AREA
                /*
                 * Long areaID = solicitudForm.getSolicitud().getAreaID();
                 * 
                 * if (areaID == null) {
                 * ValidationUtils.rejectIfEmptyOrWhitespace(result, get(AREAID),
                 * "genapp.validation.required", new Object[] {
                 * I18NUtils.tradueix(AREAID.fullName) }); return; }
                 * 
                 * long entitatOfArea = areaEjb.executeQueryOne(AreaFields.ENTITATID,
                 * AreaFields.AREAID.equal(areaID));
                 * 
                 * if (entitatid != entitatOfArea) { result.rejectValue(get(AREAID),
                 * "error.areaincorrecta",
                 * "L´area seleccionada no forma part de l´entitat elegida"); return; }
                 */
                // ===== DEPARTAMENT

                Long organID = solicitudForm.getSolicitud().getOrganid();

                if (organID == null) {
                    ValidationUtils.rejectIfEmptyOrWhitespace(result, get(ORGANID), "genapp.validation.required",
                            new Object[] { I18NUtils.tradueix(ORGANID.fullName) });
                    return;
                }

                /*
                 * log.info(" XYZ ZZZ postValidate => departamentID = " +
                 * departamentID);
                 * 
                 * long areaOfDepartament =
                 * departamentEjb.executeQueryOne(DepartamentFields.AREAID,
                 * DepartamentFields.DEPARTAMENTID.equal(departamentID));
                 * 
                 * log.info(" XYZ ZZZ postValidate => areaOfDepartament = " +
                 * areaOfDepartament);
                 * 
                 * if (areaOfDepartament != areaID) {
                 * 
                 * log.info(" XYZ ZZZ postValidate => ERRORRRRR !!!!! ");
                 * 
                 * // =El departament seleccionat no forma part de l'entitat elegida.
                 * result.rejectValue(get(DEPARTAMENTID), "error.departamentincorrecte",
                 * "El departament seleccionat no forma part de l'àrea elegida"); }
                 */

            }
        }

    }

    @Override
    public SolicitudFilterForm getSolicitudFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        SolicitudFilterForm solicitudFilterForm = super.getSolicitudFilterForm(pagina, mav, request);

        
        log.info("------------------------------------\n\n"
         + "      agruparPerCamp(groupBy) = |" + request.getParameter("groupBy") + "|\n"
         + "  agruparPerValor(groupValue) = |" + request.getParameter("groupValue") + "|\n"
         + "\n"
         + "     solicitudFilterForm.getGroupBy() = |" + solicitudFilterForm.getGroupBy() + "|\n"
         + "  solicitudFilterForm.getGroupValue() = |" + solicitudFilterForm.getGroupValue() + "|\n"
         + "\n\n\n"   
             
             );        
        
        
        if (solicitudFilterForm.isNou()) {

            {

                AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
                adfield4.setCodeName("=Sense Llegir");
                adfield4.setPosition(MISSATGES_SENSE_LLEGIR_COLUMN);

                adfield4.setEscapeXml(false);
                adfield4.setValueMap(new HashMap<Long, String>());

                solicitudFilterForm.addAdditionalField(adfield4);

            }

            Boolean isestatal = isEstatal();

            // ocultam tot
            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(Arrays.asList(SolicitudFields.ALL_SOLICITUD_FIELDS));

            hiddenFields.remove(SolicitudFields.PROCEDIMENTCODI);
            hiddenFields.remove(SolicitudFields.PROCEDIMENTNOM);
            hiddenFields.remove(SolicitudFields.ESTATID);
            hiddenFields.remove(SolicitudFields.DATAINICI);

            // hiddenFields.remove(SolicitudFields.AREAID);

            if (isestatal == null) {
                // XYZ ZZZ
                // solicitudFilterForm.getHiddenFields().remove(ENTITATLOCALID);
                // solicitudFilterForm.getHiddenFields().remove(ENTITATESTATAL);
                log.info("Afegim la columna ENTITAT_COLUMN");
                AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
                adfield4.setCodeName("=Entitat");
                adfield4.setPosition(ENTITAT_COLUMN);
                // Els valors s'ompliran al mètode postList()
                adfield4.setValueMap(new HashMap<Long, String>());
                adfield4.setEscapeXml(false);

                solicitudFilterForm.addAdditionalField(adfield4);

                solicitudFilterForm.setAddButtonVisible(false);

            } else {
                List<Field<?>> filterList = new ArrayList<Field<?>>(solicitudFilterForm.getDefaultFilterByFields());
                List<Field<?>> groupList = new ArrayList<Field<?>>(solicitudFilterForm.getDefaultGroupByFields());
                if (isestatal) {
                    hiddenFields.remove(ENTITATESTATAL);
                    hiddenFields.add(SolicitudFields.DEPARTAMENTID);
                    // filterList.remove(ENTITATLOCALID);
                    filterList.remove(PINFO);
                    // groupList.remove(ENTITATLOCALID);
//                    groupList.add(ESTATID);
//
                } else {
                    AdditionalField<Long, String> adfield = new AdditionalField<Long, String>();
                    adfield.setCodeName("=Organ Gestor");
                    adfield.setPosition(COLUMNA_ORGAN);
                    adfield.setEscapeXml(false);
                    adfield.setValueMap(new HashMap<Long, String>());

                    solicitudFilterForm.addAdditionalField(adfield);

                    
                    
//                     hiddenFields.remove(ORGANID);
                  //   hiddenFields.remove(DEPARTAMENTID);
                  //   hiddenFields.remove(NOTES);
                    filterList.remove(ENTITATESTATAL);
                    groupList.remove(ENTITATESTATAL);
                    
                    groupList.add(ORGANID);

//                    groupList.add(AREA_NOVA_ID);
//                    groupList.add(ENTITAT_NOVA_ID);

                }
                solicitudFilterForm.setFilterByFields(filterList);
                solicitudFilterForm.setGroupByFields(groupList);

            }

            if (showAdvancedFilter()) {

                AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
                adfield4.setCodeName("solicitud.filtreavanzat");
                adfield4.setPosition(FILTRE_AVANZAT_COLUMN);

                adfield4.setEscapeXml(false);
                adfield4.setSearchBy(FILTRE_AVANZAT_FIELD);

                solicitudFilterForm.addAdditionalField(adfield4);

                hiddenFields.add(FILTRE_AVANZAT_FIELD);

                // solicitudFilterForm.getFilterByFields().add(FILTRE_AVANZAT_FIELD);

            }

            // solicitudFilterForm.setAddButtonVisible(false);

            solicitudFilterForm.setHiddenFields(hiddenFields);

            {
                List<Field<?>> list = solicitudFilterForm.getGroupByFields();
                if (list == null) {
                    list = solicitudFilterForm.getDefaultGroupByFields();
                    solicitudFilterForm.setGroupByFields(new ArrayList<Field<?>>(list));
                }
                solicitudFilterForm.getGroupByFields().add(BALEARS);
            }

            solicitudFilterForm.setAttachedAdditionalJspCode(true);

            // solicitudFilterForm.setActionsRenderer(2);

            solicitudFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_LIST,
                    "servei.servei.plural", "javascript:$('#modal_infoservei_{0}').modal('show');", "btn-info"));

            solicitudFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_EYE,
                    "solicitud.vistacompleta", "/operador/solicitudfullview/view/{0}", "btn-info"));

            solicitudFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_FILE, "exportacio.soli_servei",
                    getContextWeb() + "/fullexport", "btn-info"));

            solicitudFilterForm
                    .addAdditionalButtonForEachItem(new AdditionalButton(
                            "fas fa-bullhorn", "events.titol", EventSolicitudOperadorController.CONTEXTWEB
                                    + "/veureevents/{0}" + (isestatal == null ? "" : ("/" + isestatal)),
                            "btn-success"));

            solicitudFilterForm.setVisibleMultipleSelection(true);

            solicitudFilterForm.setOrderBy(SolicitudFields.DATAINICI.fullName);
            solicitudFilterForm.setOrderAsc(false);
        }
        
        String departamentIDDesde = request.getParameter("departamentIDDesde");
        String departamentIDFins = request.getParameter("departamentIDFins");

        if (departamentIDDesde != null && departamentIDDesde.trim().length() != 0 && departamentIDFins != null
                && departamentIDFins.trim().length() != 0) {

            solicitudFilterForm.setDepartamentIDDesde(Long.parseLong(departamentIDDesde));
            solicitudFilterForm.setDepartamentIDFins(Long.parseLong(departamentIDFins));

        }
        
        TreeMap<Integer, AdditionalField<?, ?>> fi = solicitudFilterForm.getAdditionalFields();
        
        for (int i = 0; i < fi.size(); i++) {
            log.info(solicitudFilterForm.getAdditionalFields().get(i));
            
        }
        
        
        return solicitudFilterForm;
    }

    @RequestMapping(value = "/fullexport", method = RequestMethod.GET)
    public ModelAndView exportFull(HttpServletRequest request, HttpServletResponse response,
            SolicitudFilterForm filterForm) throws Exception, I18NException {

        ModelAndView mavTmp = new ModelAndView(getTileList());
        List<Solicitud> list = llistat(mavTmp, request, filterForm);

        String url = ExportFullSolicitudServeiInfoOperatorController.CONTEXT_WEB
                + "/exportFull/org_fundaciobit_plugins_exportdata_ods_ODSPlugin";

        request.getSession().setAttribute(ExportFullSolicitudServeiInfoOperatorController.SOLI_LIST_ATTRIBUTE, list);

        ModelAndView mav = new ModelAndView(new RedirectView(url, true));

        return mav;

        /*
         * Field<?>[] allFields = ALL_SOLICITUD_FIELDS;
         * 
         * java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
         * __mapping = new java.util.HashMap<Field<?>, java.util.Map<String,
         * String>>(); __mapping.put(ESTATID,
         * filterForm.getMapOfEstatSolicitudForEstatID());
         * __mapping.put(ENTITATLOCALID,
         * filterForm.getMapOfEntitatForEntitatLocalID()); exportData(request,
         * response, dataExporterID, filterForm, list, allFields, __mapping,
         * PRIMARYKEY_FIELDS);
         * 
         */
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, SolicitudFilterForm filterForm,
            List<Solicitud> list) throws I18NException {

        {
            Map<Long, String> map;
            map = (Map<Long, String>) filterForm.getAdditionalField(MISSATGES_SENSE_LLEGIR_COLUMN).getValueMap();
            map.clear();

            final StringField operador = new EventQueryPath().SOLICITUD().OPERADOR();

            final String loginUserName = request.getRemoteUser();

            for (Solicitud inc : list) {

                final String user = inc.getOperador();

                // incidencies

                Long incidencies = eventLogicaEjb.count(Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE),
                        EventFields.SOLICITUDID.equal(inc.getSolicitudID()), operador.equal(user)));

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

                    map.put(inc.getSolicitudID(), text);

                }
            }
        }

        filterForm.getAdditionalButtonsByPK().clear();
        boolean error = false;

        Map<Long, String> mapEntitat = null;
        Map<Long, String> mapOrgan = null;
        log.info("isEstatal(): " + isEstatal());
        
        if (isEstatal() == null) {
            mapEntitat = (Map<Long, String>) filterForm.getAdditionalField(ENTITAT_COLUMN).getValueMap();
            mapEntitat.clear();
        }else {
            if (!isEstatal()) {
                mapOrgan = (Map<Long, String>) filterForm.getAdditionalField(COLUMNA_ORGAN).getValueMap();
                mapOrgan.clear();
            }
        }

        Map<String, String> departamentLocalMap = filterForm.getMapOfDepartamentForDepartamentID();

        for (Solicitud soli : list) {

            if (soli.getEstatID() == -1L || soli.getProcedimentCodi().startsWith("CODI_")) {
                error = true;
                filterForm.addAdditionalButtonByPK(soli.getSolicitudID(),
                        new AdditionalButton(IconUtils.getWhite(IconUtils.ICON_WARNING), "solicitud.senseestat",
                                "javascript:alert('Revisi estat o codi de procediment.')", "btn-danger"));

            } else {
                Long count = solicitudServeiEjb
                        .count(Where.AND(SolicitudServeiFields.SOLICITUDID.equal(soli.getSolicitudID()),
                                SolicitudServeiFields.ESTATSOLICITUDSERVEIID.equal(-1L)));
                if (count != 0) {
                    filterForm.addAdditionalButtonByPK(soli.getSolicitudID(),
                            new AdditionalButton(IconUtils.ICON_WARNING, "solicitudservei.senseestat",
                                    "javascript:alert('Revisi els estats dels serveis associats.')", "btn-danger"));
                    error = true;
                }
            }

            
            // COLUMNA ENTITAT
            if (mapEntitat != null) {
                Long organId = soli.getOrganid();
                String tipus, nom;
                if (organId == null) {
                    // Estatal
                    tipus = "es";
                    nom = soli.getEntitatEstatal();
                } else {
                    // Catala
                    tipus = "ca";
                    nom = departamentLocalMap.get(String.valueOf(organId));
                }
                String img;
                img = "<img src=\"" + request.getContextPath() + "/img/" + tipus + "_petit_on.gif\" alt=\"" + tipus
                        + "\" width=\"17\" height=\"14\" border=\"0\" />";
                mapEntitat.put(soli.getSolicitudID(), img + " " + nom);
            }

            if (mapOrgan != null) {
                Long organid = soli.getOrganid();
                if (organid != null) {

                    Organ aux = organEjb.findByPrimaryKey(organid);

                    String html = "";
                    html += "<p class='elemOrgan pOrganClose' onclick='toggleJerarquia(this);'>";
                    html += "(" + aux.getDir3() + ") " + aux.getNom();
                    html += "<span class='spanOrganClose'>";
                    int i = 0;
                    while (aux.getCif() == null && aux.getDir3pare() != null) {
                        i++;
                        List<Organ> listAux = organEjb.select(OrganFields.DIR3.equal(aux.getDir3pare()));
                        aux = listAux.get(0);
                        String linea = "<br>" + "&nbsp;".repeat(3 * i) + '└' + " (" + aux.getDir3() + ") "
                                + aux.getNom();
                        html += linea;
                    }
                    html += "</span>";
                    html += "</p>";
                    mapOrgan.put(soli.getSolicitudID(), html);
                }
            }           

        }

        if (error) {
            HtmlUtils.saveMessageError(request, "Hi ha sol·licituds amb estat incorrecte");
        }

        // Map<Long, String> map;
        // map = (Map<Long,
        // String>)filterForm.getAdditionalField(SERVEIS).getValueMap();
        // map.clear();
        Long key;
        // String value;

        // String llistat = I18NUtils.tradueix("servei.llistat");
        Map<Long, String> divServeis = new HashMap<Long, String>();
        Map<Long, String> notesSolicitud = new HashMap<Long, String>();
        Map<Long, String> codiDescriptiuSolicitud = new HashMap<Long, String>();

        Map<String, String> estatsSoliServ;
        {
            estatsSoliServ = Utils
                    .listToMap(SolicitudServeiOperadorController.getReferenceListForEstatSolicitudServeiIDStatic());
        }

        for (Solicitud solicitud : list) {

            if (solicitud.getNotes() != null) {
                notesSolicitud.put(solicitud.getSolicitudID(), solicitud.getNotes());
            } else {
                notesSolicitud.put(solicitud.getSolicitudID(), "");
            }

            if (solicitud.getCodiDescriptiu() == null) {
                codiDescriptiuSolicitud.put(solicitud.getSolicitudID(), solicitud.getCodiDescriptiu());
            } else {
                codiDescriptiuSolicitud.put(solicitud.getSolicitudID(), solicitud.getCodiDescriptiu());
            }

            // log.info(" ---------------- SOLI = " + solicitud.getSolicitudID());

            // ServeiQueryPath sqp = new ServeiQueryPath();

            List<SolicitudServei> solicitudServeis = solicitudServeiEjb
                    .select(SolicitudServeiFields.SOLICITUDID.equal(solicitud.getSolicitudID()));

            key = solicitud.getSolicitudID();
            // value = ""; // StringUtils.join(nomServeis.toArray());

            // TODO Traduir
            String valuediv = "<table class=\"table table-bordered  table-striped\">\n"
                    + "<tr><td>Codi</td><td>Nom</td><td>Estat</td><td>Notes</td></tr>\n";
            if (solicitudServeis.size() != 0) {
                Map<String, String> infoServeis;
                {

                    List<Long> serveisID = new ArrayList<Long>();
                    for (SolicitudServei solicitudServei : solicitudServeis) {
                        serveisID.add(solicitudServei.getServeiID());
                    }

                    // log.info(" - serveis IDs: ]" + Arrays.toString(serveisID.toArray())
                    // + "[");
                    SelectMultipleStringKeyValue smskv;
                    smskv = new SelectMultipleStringKeyValue(ServeiFields.SERVEIID.select, "*",
                            ServeiFields.CODI.select, ServeiFields.NOM.select);

                    List<StringKeyValue> idsnomServeis = serveiEjb.executeQuery(smskv,
                            ServeiFields.SERVEIID.in(serveisID));
                    // log.info(" - serveis NOMS: ]" +
                    // Arrays.toString(idsnomServeis.toArray()) + "[");

                    infoServeis = Utils.listToMap(idsnomServeis);
                }

                for (SolicitudServei ss : solicitudServeis) {
                    String skvvalue = infoServeis.get(String.valueOf(ss.getServeiID()));

                    int pos = skvvalue.indexOf('*');
                    String code = skvvalue.substring(0, pos);
                    String nom = skvvalue.substring(pos + 1);
                    valuediv = valuediv + "<tr>\n";
                    String hrefServ = "<a href=\"" + request.getContextPath() + "/operador/servei/" + ss.getServeiID()
                            + "/edit\" >";
                    valuediv = valuediv + "<td>" + hrefServ + code + "</a></td>\n";
                    valuediv = valuediv + "<td>" + hrefServ + nom + "</a></td>\n";
                    // String hrefSoliServ = "<a href=\"" + request.getContextPath()+
                    // "/operador/solicitudservei/" + ss.getSolicitudID() + "/edit\" >";
                    valuediv = valuediv + "<td>" + estatsSoliServ.get(String.valueOf(ss.getEstatSolicitudServeiID()))
                            + "</td>\n";
                    final String notes = ss.getNotes();
                    valuediv = valuediv + "<td>" + ((notes == null) ? "&nbsp;" : notes) + "</a></td>\n";

                    valuediv = valuediv + "</tr>\n";
                }

            }
            valuediv = valuediv + "</table>\n";

            divServeis.put(key, valuediv);

            // map.put(key, value);
        }

        mav.addObject("divServeis", divServeis);
        mav.addObject("notesSolicitud", notesSolicitud);
        mav.addObject("codiDescriptiuSolicitud", codiDescriptiuSolicitud);

    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, SolicitudForm solicitudForm) {
        // return "redirect:" + getContextWeb() + "/list/1";
        return "redirect:/operador/solicitudfullview/view/" + solicitudForm.getSolicitud().getSolicitudID();
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, SolicitudForm solicitudForm, Throwable __e) {
        return getRedirectWhenCreated(request, solicitudForm);
        
//        if (__e == null) {
//          return "redirect:" + getContextWeb() + "/list";
//        } else {
//          return  getTileForm();
//        }
      }
    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, Long solicitudID) {

        return "redirect:" + getContextWeb() + "/list";
    }

    @Override
    public Map<Field<?>, GroupByItem> fillReferencesForList(SolicitudFilterForm filterForm, HttpServletRequest request,
            ModelAndView mav, List<Solicitud> list, List<GroupByItem> groupItems) throws I18NException {

        Map<Field<?>, GroupByItem> groupByItemsMap = super.fillReferencesForList(filterForm, request, mav, list,
                groupItems);

        /*
         * log.info("SolicitudOperadorController::GroupBy = " +
         * filterForm.getGroupBy()); log.info(
         * "SolicitudOperadorController::GroupByValue = ]" +
         * filterForm.getGroupValue() + "[");
         */
        // GROUP BY BALEARS

        final boolean selected = BALEARS.javaName.equals(filterForm.getGroupBy());
        final String valGroup = filterForm.getGroupValue();

        final String[] valuesStr = { "true", "false" };
        ArrayList<GroupByValueItem> values = new ArrayList<GroupByValueItem>();

        for (String va : valuesStr) {
            Boolean valBool = Boolean.parseBoolean(va);

            List<Long> idsServei = serveiEjb.executeQuery(ServeiFields.SERVEIID,
                    new ServeiQueryPath().ENTITATSERVEI().BALEARS().equal(valBool));

            Long count = solicitudEjb.count(SOLICITUDID.in(solicitudServeiEjb
                    .getSubQuery(SolicitudServeiFields.SOLICITUDID, SolicitudServeiFields.SERVEIID.in(idsServei))));

            values.add(new GroupByValueItem(BALEARS, I18NUtils.tradueix(BALEARS.javaName + "." + va), va,
                    selected && va.equals(valGroup), count));
        }

        GroupByItem balearsGroupByItem = new GroupByItem(BALEARS, BALEARS.javaName, BALEARS.javaName, selected, values);
        groupByItemsMap.put(BALEARS, balearsGroupByItem);

        groupItems.add(balearsGroupByItem);

        if (selected) {
            filterForm.setVisibleGroupBy(true);
        }

        // AREA NOVA

        Boolean estatal = isEstatal();
        if (estatal != null && estatal == false) {

            Map<String, String> _tmp;
            List<StringKeyValue> _listSKV;
            /*
             * GroupByItem novaAreaGroupByItem = new GroupByItem(AREA_NOVA_ID,
             * AREA_NOVA_ID.javaName, AREA_NOVA_ID.javaName, selected, new
             * ArrayList<GroupByValueItem>());
             * groupByItemsMap.put(AREA_NOVA_ID,novaAreaGroupByItem);
             * 
             * 
             * 
             * // Field AREA NOVA _listSKV = this.areaRefList.getReferenceList(
             * AreaFields.NOM, null);
             * 
             * for(StringKeyValue skv : _listSKV) { log.info(" XYZ ZZZ   " +
             * skv.getKey() + " => ]" + skv.getValue() + "["); }
             * 
             * _tmp = org.fundaciobit.genapp.common.utils.Utils.listToMap(_listSKV);
             * groupByItemsMap.get(AREA_NOVA_ID).setCodeLabel(
             * AreaFields._TABLE_TRANSLATION); fillValuesToGroupByItems(_tmp,
             * groupByItemsMap, AREA_NOVA_ID, false);
             * 
             * 
             * 
             * List<GroupByValueItem> lgbvi =
             * groupByItemsMap.get(AREA_NOVA_ID).getValues();
             * 
             * for(GroupByValueItem item : lgbvi) {
             * 
             * log.info(" XXXXXXXXXXXXX  " + item.getCodeLabel() + " ===> " +
             * item.getValue()); log.info(" XXXXXXXXXXXXX  " + item.getCodeLabel() +
             * " ===> " + _tmp.get(item.getValue())); log.info(" "); }
             * 
             * 
             * 
             */

            {
                _listSKV = getReferenceListForAreaNOVAID(request, mav, filterForm, list, groupByItemsMap, null);
                _tmp = Utils.listToMap(_listSKV);
                // filterForm.setMapOfAreaForAreaID(_tmp);
                if (filterForm.getGroupByFields().contains(AREA_NOVA_ID)) {
                    fillValuesToGroupByItems(_tmp, groupByItemsMap, AREA_NOVA_ID, false);
                    groupByItemsMap.get(AREA_NOVA_ID).setCodeLabel("area.area");
                }
                ;
            }

            {
                _listSKV = getReferenceListForEntitatNOVAID(request, mav, filterForm, list, groupByItemsMap, null);
                _tmp = Utils.listToMap(_listSKV);
                // filterForm.setMapOfAreaForAreaID(_tmp);
                if (filterForm.getGroupByFields().contains(ENTITAT_NOVA_ID)) {
                    fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITAT_NOVA_ID, false);
                    groupByItemsMap.get(ENTITAT_NOVA_ID).setCodeLabel("entitat");
                }
                ;
            }

        }

        return groupByItemsMap;
    }

    public List<StringKeyValue> getReferenceListForEntitatNOVAID(HttpServletRequest request, ModelAndView mav,
            SolicitudFilterForm solicitudFilterForm, List<Solicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap,
            Where where) throws I18NException {

        // if (!_groupByItemsMap.containsKey(AREAID))

        // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències
        // d'aquestes PK
        /*
         * java.util.Set<java.lang.Long> _pkList = new
         * java.util.HashSet<java.lang.Long>(); for (Solicitud _item : list) {
         * if(_item.getDepartamentID() == null) { continue; };
         * _pkList.add(_item.getDepartamentID()); }
         * 
         * _w = DepartamentFields.DEPARTAMENTID.in(_pkList);
         */
        /*
         * Where _w = null; SubQuery<Solicitud, Long> sub = new SubQuery<Solicitud,
         * Long>(SolicitudFields.DEPARTAMENTID.select, solicitudEjb, null); _w =
         * DepartamentFields.DEPARTAMENTID.in(sub);
         */
        DepartamentQueryPath dqp = new DepartamentQueryPath();
        SelectMultipleStringKeyValue smkv = new SelectMultipleStringKeyValue(dqp.AREA().ENTITATID().select,
                dqp.AREA().ENTITAT().NOM().select, dqp.AREA().ENTITAT().CONVENIPMSBAE().select);

        List<StringKeyValue> listSKV = departamentEjb.executeQuery(smkv);

        for (StringKeyValue skv : listSKV) {
            String v = skv.value;
            if (v.endsWith(" true")) {
                skv.setValue("<span style=\"color:red\"><b>" + v.replace(" true", " - PMSBAE</b></span>"));
            } else {
                skv.setValue(v.replace(" false", ""));
            }
        }

        // for (StringKeyValue skv : listSKV) {
        // log.info("XYZ ZZZ " + skv.getKey() + " => " + skv.getValue());
        // }

        return listSKV;

        // List<Long> areasOfAllDeps =
        // departamentEjb.executeQuery(DepartamentFields.AREAID, where, orderBy)

        // List<Departament> depList = departamentEjb.select(_w);

        /*
         * List<Departament> depList = departamentEjb.select(_w);
         * 
         * List<StringKeyValue> listSKV = new ArrayList<StringKeyValue>();
         * 
         * for(Departament dep : depList) { listSKV.add(new
         * StringKeyValue(String.valueOf(dep.getAreaID()), "Area " +
         * dep.getAreaID())); }
         * 
         * 
         * return listSKV;
         */
        // return getReferenceListForAreaNOVAID(request, mav, Where.AND(where,_w));
    }

    public List<StringKeyValue> getReferenceListForAreaNOVAID(HttpServletRequest request, ModelAndView mav,
            SolicitudFilterForm solicitudFilterForm, List<Solicitud> list, Map<Field<?>, GroupByItem> _groupByItemsMap,
            Where where) throws I18NException {

        // Where _w = null;
        // if (!_groupByItemsMap.containsKey(AREAID))

        // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències
        // d'aquestes PK
        /*
         * java.util.Set<java.lang.Long> _pkList = new
         * java.util.HashSet<java.lang.Long>(); for (Solicitud _item : list) {
         * if(_item.getDepartamentID() == null) { continue; };
         * _pkList.add(_item.getDepartamentID()); }
         * 
         * _w = DepartamentFields.DEPARTAMENTID.in(_pkList);
         */

        // SubQuery<Solicitud, Long> sub = new SubQuery<Solicitud,
        // Long>(SolicitudFields.DEPARTAMENTID.select, solicitudEjb, null);
        // _w = DepartamentFields.DEPARTAMENTID.in(sub);

        DepartamentQueryPath dqp = new DepartamentQueryPath();
        SelectMultipleStringKeyValue smkv = new SelectMultipleStringKeyValue(DepartamentFields.AREAID.select,
                dqp.AREA().NOM().select);

        return departamentEjb.executeQuery(smkv);

        // List<Long> areasOfAllDeps =
        // departamentEjb.executeQuery(DepartamentFields.AREAID, where, orderBy)

        // List<Departament> depList = departamentEjb.select(_w);

        /*
         * List<Departament> depList = departamentEjb.select(_w);
         * 
         * List<StringKeyValue> listSKV = new ArrayList<StringKeyValue>();
         * 
         * for(Departament dep : depList) { listSKV.add(new
         * StringKeyValue(String.valueOf(dep.getAreaID()), "Area " +
         * dep.getAreaID())); }
         * 
         * 
         * return listSKV;
         */
        // return getReferenceListForAreaNOVAID(request, mav, Where.AND(where,_w));
    }

    // public List<StringKeyValue>
    // getReferenceListForAreaNOVAID(HttpServletRequest request,
    // ModelAndView mav, Where where) throws I18NException {
    // return areaRefList.getReferenceList(AreaFields.AREAID, where );
    // }

    public Where getAdditionalConditionFine(HttpServletRequest request) throws I18NException {
        return null;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        SolicitudFilterForm filterForm = (SolicitudFilterForm) request.getSession()
                .getAttribute(getSessionAttributeFilterForm());

        final boolean selected = BALEARS.javaName.equals(filterForm.getGroupBy());

        Where wBalears = null;

        if (selected) {

            final String vaStr = filterForm.getGroupValue();

            Boolean valBool = Boolean.parseBoolean(vaStr);

            List<Long> idsServei = serveiEjb.executeQuery(ServeiFields.SERVEIID,
                    new ServeiQueryPath().ENTITATSERVEI().BALEARS().equal(valBool));

            wBalears = SOLICITUDID.in(solicitudServeiEjb.getSubQuery(SolicitudServeiFields.SOLICITUDID,
                    SolicitudServeiFields.SERVEIID.in(idsServei)));
        }

        // TIPUS
        Boolean esestatal = isEstatal();
        Where tipusEstatalService = null;
        if (esestatal != null) {
            if (esestatal) {
                tipusEstatalService = SolicitudFields.ENTITATESTATAL.isNotNull();
            } else {
                tipusEstatalService =  Where.OR(SolicitudFields.DEPARTAMENTID.isNotNull(), SolicitudFields.ORGANID.isNotNull());
            }
        }

        // FILTRE AVANÇAT PER CERCA

        return Where.AND(getAdditionalConditionFine(request), wBalears, super.getAdditionalCondition(request),
                tipusEstatalService, getAdditionaConditionAdvancedFilter(request));

    }

    protected Where getAdditionaConditionAdvancedFilter(HttpServletRequest request) throws I18NException {

        String af = request.getParameter(FILTRE_AVANZAT_FIELD.getFullName());
        log.info(" Valor Filtre Avanzat FilterBY => ]" + af + "[");

        if (af == null || af.trim().length() == 0) {
            log.info("getAdditionalCondition::NO FILTRAM AVANZAT !!!!");
            return null;
        } else {

            final String likeStr = "%" + af + "%";

            final boolean isNumber = PinbalAdminUtils.isNumber(af);

            // PInfo, Departament, Area o Entitat
            Where w = Where.OR(PINFO.like(likeStr), new SolicitudQueryPath().DEPARTAMENT().NOM().like(likeStr),
                    new SolicitudQueryPath().DEPARTAMENT().AREA().NOM().like(likeStr),
                    new SolicitudQueryPath().DEPARTAMENT().AREA().ENTITAT().NOM().like(likeStr));

            // identificador de consulta o numero seguiment de la solicitud
            if (isNumber) {
                w = Where.OR(w, SolicitudFields.TICKETASSOCIAT.like(likeStr),
                        SolicitudFields.TICKETNUMEROSEGUIMENT.like(likeStr));
            }

            // Procediment: codi i nom
            w = Where.OR(w, SolicitudFields.PROCEDIMENTNOM.like(likeStr),
                    SolicitudFields.PROCEDIMENTCODI.like(likeStr));

            // Comentari dels Events
            SubQuery<Event, Long> subquery1 = eventLogicaEjb.getSubQuery(EventFields.SOLICITUDID,
                    Where.AND(EventFields.SOLICITUDID.isNotNull(), EventFields.COMENTARI.like(likeStr)));
            w = Where.OR(w, SolicitudFields.SOLICITUDID.in(subquery1));

            // identificador de consulta o numero seguiment dels events
            if (isNumber) {
                SubQuery<Event, Long> subquery2a = eventLogicaEjb.getSubQuery(EventFields.SOLICITUDID, Where
                        .AND(EventFields.SOLICITUDID.isNotNull(), EventFields.CAIDIDENTIFICADORCONSULTA.like(likeStr)));
                SubQuery<Event, Long> subquery2b = eventLogicaEjb.getSubQuery(EventFields.SOLICITUDID,
                        Where.AND(EventFields.SOLICITUDID.isNotNull(), EventFields.CAIDNUMEROSEGUIMENT.like(likeStr)));
                w = Where.OR(w, SolicitudFields.SOLICITUDID.in(subquery2a), SolicitudFields.SOLICITUDID.in(subquery2b));
            }

            log.info("getAdditionalCondition::FILTRAM AVANZAT !!!!!!!!!!");

            return w;
        }

    }

    @Override
    protected List<Solicitud> processarLlistat(ITableManager<Solicitud, Long> ejb, BaseFilterForm filterForm,
            int pagina, Where whereAdditionalCondition, ModelAndView mav) throws I18NException {
        if (filterForm == null) {
            throw new NullPointerException("FilterForm mai pot ser NULL !!!!");
        }

        // Eliminam temporalment el filtre especial, per a que no doni problemes
        // internament.

        AdditionalField<?, ?> filtreAvanzatField = null;
        if (showAdvancedFilter()) {
            filtreAvanzatField = filterForm.getAdditionalFields().remove(FILTRE_AVANZAT_COLUMN);
        }

        List<Solicitud> list = super.processarLlistat(ejb, filterForm, pagina, whereAdditionalCondition, mav);

        if (filtreAvanzatField != null) {

            filterForm.getAdditionalFields().put(FILTRE_AVANZAT_COLUMN, filtreAvanzatField);

            String valorFA = filtreAvanzatField.getSearchByValue();

            if (valorFA != null && valorFA.trim().length() != 0) {
                filterForm.setVisibleFilterBy(true);
            }

        }

        return list;

    }

    @Override
    public List<StringKeyValue> getReferenceListForProcedimentTipus(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        /*
         * List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
         * __tmp.add(new StringKeyValue("Aduanero", "Aduanero")); __tmp.add(new
         * StringKeyValue("Afiliación y cotización a la Seguridad Social",
         * "Afiliación y cotización a la Seguridad Social")); __tmp.add(new
         * StringKeyValue("Autorizaciones, licencias y homologaciones",
         * "Autorizaciones, licencias y homologaciones")); __tmp.add(new
         * StringKeyValue("Ayudas, Becas y Subvenciones",
         * "Ayudas, Becas y Subvenciones")); __tmp.add(new
         * StringKeyValue("Certificados", "Certificados")); __tmp.add(new
         * StringKeyValue("Contratación pública", "Contratación pública"));
         * __tmp.add(new
         * StringKeyValue("Convenios de colaboración y comunicaciones administrativas"
         * , "Convenios de colaboración y comunicaciones administrativas"));
         * __tmp.add(new StringKeyValue("Gestión Económica y Patrimonial",
         * "Gestión Económica y Patrimonial")); __tmp.add(new
         * StringKeyValue("Declaraciones y comunicaciones de los interesados",
         * "Declaraciones y comunicaciones de los interesados")); __tmp.add(new
         * StringKeyValue("Inspector", "Inspector")); __tmp.add(new
         * StringKeyValue("Premios", "Premios")); __tmp.add(new
         * StringKeyValue("Prestaciones", "Prestaciones")); __tmp.add(new
         * StringKeyValue("Recursos Humanos", "Recursos Humanos")); __tmp.add(new
         * StringKeyValue("Registros y Censos", "Registros y Censos"));
         * __tmp.add(new
         * StringKeyValue("Responsabilidad patrimonial y otras solicitudes de indemnización"
         * , "Responsabilidad patrimonial y otras solicitudes de indemnización"));
         * __tmp.add(new
         * StringKeyValue("Revisión de Actos administrativos y Recursos",
         * "Revisión de Actos administrativos y Recursos")); __tmp.add(new
         * StringKeyValue("Sancionador", "Sancionador")); __tmp.add(new
         * StringKeyValue("Sugerencias, Quejasy Denuncias",
         * "Sugerencias, Quejasy Denuncias")); __tmp.add(new
         * StringKeyValue("Tributario", "Tributario"));
         */

        Set<String> tp = TipusProcediments.getAllTipusDeProcediment();

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (String s : tp) {
            String tipus = TipusProcediments.getTipusProcedimentByLabel(s);
            __tmp.add(new StringKeyValue(s, tipus));
        }

        return __tmp;
    }

    @RequestMapping(value = "/close/{solicitudID}", method = RequestMethod.GET)
    public String closeSolicitudGet(@PathVariable("solicitudID") java.lang.Long solicitudID, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {

        SolicitudJPA soli = this.findByPrimaryKey(request, solicitudID);

        soli.setEstatID(Constants.SOLICITUD_ESTAT_TANCAT);
        soli.setDataFi(new Timestamp(System.currentTimeMillis()));

        try {
            this.update(request, soli);

            HtmlUtils.saveMessageSuccess(request, "Tancada Sol·licitud correctament.");
        } catch (Throwable e) {
            String msg = "Error tancant Sol·licitud: " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:/operador/solicitudfullview/view/" + solicitudID;
    }

    @RequestMapping(value = "/changeOperador/{solicitudID}/{operador}", method = RequestMethod.GET)
    public String changeOperadorIncidenciaTecnicaGet(@PathVariable("solicitudID") java.lang.Long solicitudID,
            @PathVariable("operador") java.lang.String operador, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {

        SolicitudJPA soli = this.findByPrimaryKey(request, solicitudID);

        String operador_old = soli.getOperador();
        soli.setOperador(operador);

        SelectMultipleStringKeyValue smskv;
        smskv = new SelectMultipleStringKeyValue(OperadorFields.USERNAME.select, OperadorFields.NOM.select);
        List<StringKeyValue> operadors = operadorEjb.executeQuery(smskv,
                Where.OR(OperadorFields.USERNAME.equal(operador_old), OperadorFields.USERNAME.equal(operador)));

        Map<String, String> operadors_map;
        operadors_map = Utils.listToMap(operadors);

        String nom_operador = operadors_map.get(operador);
        String nom_operador_old = operadors_map.get(operador_old);

        try {
            this.update(request, soli);

            Timestamp data = new Timestamp(System.currentTimeMillis());
            int tipus = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
            String persona = request.getUserPrincipal().getName();

            String comentari = I18NUtils.tradueix("missatge.canvi.operador", "solicitud", operador, nom_operador,
                    operador_old, nom_operador_old);

            EventJPA evt = new EventJPA();
            evt.setSolicitudID(solicitudID);
            evt.setDataEvent(data);
            evt.setTipus(tipus);
            evt.setPersona(persona);
            evt.setComentari(comentari);
            evt.setNoLlegit(true);

            eventLogicaEjb.create(evt);

            HtmlUtils.saveMessageSuccess(request,
                    "Operador canviat correctament.(" + operador_old + " -> " + operador + ")");

        } catch (Throwable e) {
            String msg = "Error canviant operador: " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:/operador/solicitudfullview/view/" + solicitudID;
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
    public SolicitudJPA update(HttpServletRequest request, SolicitudJPA solicitud)
            throws I18NException, I18NValidationException {

        if (solicitud.getEstatID() == Constants.SOLICITUD_ESTAT_TANCAT) {
            solicitud.setDataFi(new Timestamp(System.currentTimeMillis()));
        }

        return (SolicitudJPA) solicitudEjb.update(solicitud);
    }

    @Override
    public List<StringKeyValue> getReferenceListForOperador(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        return getReferenceListForCreador(request, mav, where);
    }

    @Override
    public List<StringKeyValue> getReferenceListForEstatID(HttpServletRequest request, ModelAndView mav,
            SolicitudForm solicitudForm, Where where) throws I18NException {

        Boolean estatal = isEstatal();

        if (estatal == null) {
            estatal = (solicitudForm.getSolicitud().getDepartamentID() == null && solicitudForm.getSolicitud().getOrganid() == null);
        }

        List<StringKeyValue> __tmp;
        if (estatal) {
            __tmp = new java.util.ArrayList<StringKeyValue>();
            for (Map.Entry<Long, String> entry : ESTATS_SOLICITUD.entrySet()) {
                Long key = entry.getKey();
                if (key == 40L || key == 10L || key == 60L || key == 20L) {
                    String val = entry.getValue();
                    __tmp.add(new StringKeyValue(String.valueOf(key), val));
                }
            }
        } else {
            __tmp = super.getReferenceListForEstatID(request, mav, solicitudForm, where);
        }

        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForEstatID(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (Map.Entry<Long, String> entry : ESTATS_SOLICITUD.entrySet()) {
            Long key = entry.getKey();
            String val = entry.getValue();
            __tmp.add(new StringKeyValue(String.valueOf(key), val));
        }

        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForOrganid(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {

        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        
        log.info("where: " + where);
        if (where != null) {
            log.info("where.toSQL: " + where.toSQL());
        }
        
        List<Organ> organs = organEjb.select(where);

        for (Organ organ : organs) {

            Organ aux = organ;
            List<String> jerarquia = new ArrayList<String>();
//            log.info("Organ Gestor: " + "(" + aux.getDir3() + ") " + aux.getNom());
            jerarquia.add("(" + aux.getDir3() + ") " + aux.getNom());

            if (where != null) {
                while (aux.getCif() == null && aux.getDir3pare() != null) {
                    List<Organ> listAux = organEjb.select(OrganFields.DIR3.equal(aux.getDir3pare()));
                    aux = listAux.get(0);
//                    log.info("pare: " + "(" + aux.getDir3() + ") " + aux.getNom());
                    jerarquia.add("(" + aux.getDir3() + ") " + aux.getNom());
                }
            }
            String str = String.join("|", jerarquia);

            __tmp.add(new StringKeyValue(String.valueOf(organ.getOrganid()), str));
        }

        return __tmp;
        //        return organRefList.getReferenceList(OrganFields.ORGANID, where);
    }    
    
    public static final Map<Long, String> ESTATS_SOLICITUD = new HashMap<Long, String>();

    static {
        ESTATS_SOLICITUD.put(-1L, "Sense Estat");
        ESTATS_SOLICITUD.put(10L, "Pendent");
        ESTATS_SOLICITUD.put(15L, "Pendent Firma Director");
        ESTATS_SOLICITUD.put(20L, "Pendent d'autoritzar");
        ESTATS_SOLICITUD.put(30L, "Esmenes");
        ESTATS_SOLICITUD.put(40L, "Autoritzat");
        ESTATS_SOLICITUD.put(50L, "Pendent pinfo");
        ESTATS_SOLICITUD.put(60L, "Tancat");
    }

    /*
     * 
     * public ThreadLocal<DepartamentRefList> referenciaDepartament = new
     * ThreadLocal<DepartamentRefList>();
     * 
     * @Override public List<StringKeyValue>
     * getReferenceListForDepartamentID(HttpServletRequest request, ModelAndView
     * mav, SolicitudForm solicitudForm, Where where) throws I18NException {
     * 
     * referenciaDepartament.set(this.departamentRefList);
     * 
     * return super.getReferenceListForDepartamentID(request, mav, solicitudForm,
     * where); }
     * 
     * 
     * @Override public List<StringKeyValue>
     * getReferenceListForDepartamentID(HttpServletRequest request, ModelAndView
     * mav, SolicitudFilterForm solicitudFilterForm, List<Solicitud> list,
     * Map<Field<?>, GroupByItem> _groupByItemsMap, Where where) throws
     * I18NException {
     * 
     * referenciaDepartament.set(departamentRefListPerLlistat);
     * 
     * return super.getReferenceListForDepartamentID(request, mav,
     * solicitudFilterForm, list, _groupByItemsMap, where); }
     * 
     * @Override public List<StringKeyValue>
     * getReferenceListForDepartamentID(HttpServletRequest request, ModelAndView
     * mav, Where where) throws I18NException { return
     * referenciaDepartament.get().getReferenceList(DepartamentFields.
     * DEPARTAMENTID, where ); }
     * 
     */

    @Override
    @RequestMapping(value = "/{solicitudID}/edit", method = RequestMethod.POST)
    public String editarSolicitudPost(@ModelAttribute SolicitudForm solicitudForm, BindingResult result,
            SessionStatus status, HttpServletRequest request, HttpServletResponse response) throws I18NException {
        
        String ret = super.editarSolicitudPost(solicitudForm, result, status, request, response);
        
        if (result.hasErrors()) {
            request.setAttribute("desplegableOrgans", true);
        }
        return ret;
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String crearSolicitudPost(@ModelAttribute SolicitudForm solicitudForm,
        BindingResult result, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        
        String ret = super.crearSolicitudPost(solicitudForm, result, request, response);
        
        if (result.hasErrors()) {
            request.setAttribute("desplegableOrgans", true);
        }

        return ret;
    }
    
    @Override
    public List<StringKeyValue> getReferenceListForEstatpinbal(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int estat : Constants.ESTATS_PINBAL) {
            String key = String.valueOf(estat);
            __tmp.add(new StringKeyValue(key, I18NUtils.tradueix("estat.pinbal." + key)));
        }
        return __tmp;
    }

}
