package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.CustomField;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.GroupByValueItem;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
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
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.SolicitudController;
import org.fundaciobit.pinbaladmin.back.form.webdb.AreaRefList;
import org.fundaciobit.pinbaladmin.back.form.webdb.DepartamentRefList;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.jpa.SolicitudJPA;
import org.fundaciobit.pinbaladmin.logic.EventLogicaLocal;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaLocal;
import org.fundaciobit.pinbaladmin.logic.utils.LogicUtils;
import org.fundaciobit.pinbaladmin.model.entity.EstatSolicitud;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.DepartamentFields;
import org.fundaciobit.pinbaladmin.model.fields.DepartamentQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.EstatSolicitudServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.EventQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.utils.Constants;
import org.fundaciobit.pinbaladmin.utils.PinbalAdminUtils;
import org.fundaciobit.pinbaladmin.utils.TipusProcediments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @author anadal
 *
 */
public abstract class SolicitudOperadorController extends SolicitudController {

  public static final Field<?> AREA_NOVA_ID = new SolicitudQueryPath().DEPARTAMENT().AREA()
      .AREAID();

  public static final int ENTITAT_COLUMN = 1;
  
  public static final int MISSATGES_SENSE_LLEGIR_COLUMN = 2;

  public static final Field<?> ENTITAT_NOVA_ID = new SolicitudQueryPath().DEPARTAMENT().AREA()
      .ENTITAT().ENTITATID();

  public static final int FILTRE_AVANZAT_COLUMN = -1;

  public static final StringField FILTRE_AVANZAT_FIELD = CODIDESCRIPTIU; // new
                                                                         // StringField("filtreavtable",
                                                                         // "filtreavjava",
                                                                         // "filtreavsql");

  public static final CustomField BALEARS;

  static {
    BALEARS = new CustomField("entitatServei.balears");
  }

  // EJB's
  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ServeiLocal serveiEjb;

  @EJB(mappedName = SolicitudLogicaLocal.JNDI_NAME)
  protected SolicitudLogicaLocal solicitudLogicaEjb;

  @javax.ejb.EJB(mappedName = "pinbaladmin/EstatSolicitudServeiEJB/local")
  protected org.fundaciobit.pinbaladmin.ejb.EstatSolicitudServeiLocal estatSolicitudServeiEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudServeiLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudServeiLocal solicitudServeiEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EstatSolicitudLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EstatSolicitudLocal estatSolicitudEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.AreaLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.AreaLocal areaEjb;

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DepartamentLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.DepartamentLocal departamentEjb;

  @EJB(mappedName = EventLogicaLocal.JNDI_NAME)
  protected EventLogicaLocal eventLogicaEjb;

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
    departamentRefList.setSelects(new Select[] { dqp.AREA().ENTITAT().NOM().select,
        dqp.AREA().NOM().select, DepartamentFields.NOM.select });
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
  public void delete(HttpServletRequest request, Solicitud solicitud)
      throws Exception, I18NException {
    Set<Long> deleteFiles = solicitudLogicaEjb.deleteFull(solicitud.getSolicitudID(), true);

    // Si tot ha anat be llavors borram els fitxers
    if (deleteFiles.size() != 0) {
      LogicUtils.deleteFiles(deleteFiles, fitxerEjb);
    }
  }

  @Override
  public SolicitudForm getSolicitudForm(SolicitudJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {

    SolicitudForm solicitudForm = super.getSolicitudForm(_jpa, __isView, request, mav);

    SolicitudJPA soli = solicitudForm.getSolicitud();
    Boolean isestatal = isEstatal();
    if (solicitudForm.isNou()) {
      solicitudForm.addHiddenField(DATAFI);
      soli.setEstatID(10L);
      soli.setProduccio(true);
      soli.setDataInici(new Timestamp(System.currentTimeMillis()));
      soli.setCreador(request.getRemoteUser());
    } else {
      // solicitudForm.addReadOnlyField(ENTITATID);
    }

    if (isestatal == null) {
      // NO SABEM SI ES ESTATAL O LOCAL
      // log.info("XYZ ZZZ __isView = " + __isView);
      if (__isView) {

        // log.info("XYZ ZZZ _jpa.getDepartamentID() = " +
        // _jpa.getDepartamentID());

        if (_jpa.getDepartamentID() == null) {
          // Es Estatal

          amagarCampsEstatal(solicitudForm);
        } else {
          // Es Local
          solicitudForm.addHiddenField(ENTITATESTATAL);
        }
      } else {

        if (!solicitudForm.isNou()) {
          if (_jpa.getDepartamentID() == null) {
            // solicitudForm.addHiddenField(ENTITATLOCALID);
            solicitudForm.addHiddenField(DEPARTAMENTID);
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
  public void postValidate(HttpServletRequest request, SolicitudForm solicitudForm,
      BindingResult result) throws I18NException {

    // log.info(" XYZ ZZZ postValidate");

    Boolean isestatal = isEstatal();

    // log.info(" XYZ ZZZ postValidate => isestatal = " + isestatal);

    if (isestatal != null) {

      if (isestatal) {
        String nom = solicitudForm.getSolicitud().getEntitatEstatal();
        if (nom == null || nom.trim().length() == 0) {
          ValidationUtils.rejectIfEmptyOrWhitespace(result, get(ENTITATESTATAL),
              "genapp.validation.required",
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

        Long departamentID = solicitudForm.getSolicitud().getDepartamentID();

        if (departamentID == null) {
          ValidationUtils.rejectIfEmptyOrWhitespace(result, get(DEPARTAMENTID),
              "genapp.validation.required",
              new Object[] { I18NUtils.tradueix(DEPARTAMENTID.fullName) });
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
  public SolicitudFilterForm getSolicitudFilterForm(Integer pagina, ModelAndView mav,
      HttpServletRequest request) throws I18NException {
    SolicitudFilterForm solicitudFilterForm = super.getSolicitudFilterForm(pagina, mav,
        request);

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
      Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
          Arrays.asList(SolicitudFields.ALL_SOLICITUD_FIELDS));

      hiddenFields.remove(SolicitudFields.PROCEDIMENTCODI);
      hiddenFields.remove(SolicitudFields.PROCEDIMENTNOM);
      hiddenFields.remove(SolicitudFields.ESTATID);
      hiddenFields.remove(SolicitudFields.DATAINICI);

      // hiddenFields.remove(SolicitudFields.AREAID);

      if (isestatal == null) {
        // XYZ ZZZ
        // solicitudFilterForm.getHiddenFields().remove(ENTITATLOCALID);
        // solicitudFilterForm.getHiddenFields().remove(ENTITATESTATAL);

        AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
        adfield4.setCodeName("=Entitat");
        adfield4.setPosition(ENTITAT_COLUMN);
        // Els valors s'ompliran al mètode postList()
        adfield4.setValueMap(new HashMap<Long, String>());
        adfield4.setEscapeXml(false);

        solicitudFilterForm.addAdditionalField(adfield4);

        solicitudFilterForm.setAddButtonVisible(false);

      } else {
        List<Field<?>> filterList = new ArrayList<Field<?>>(
            solicitudFilterForm.getDefaultFilterByFields());
        List<Field<?>> groupList = new ArrayList<Field<?>>(
            solicitudFilterForm.getDefaultGroupByFields());
        if (isestatal) {
          hiddenFields.remove(ENTITATESTATAL);
          hiddenFields.add(SolicitudFields.DEPARTAMENTID);
          // filterList.remove(ENTITATLOCALID);
          filterList.remove(PINFO);
          // groupList.remove(ENTITATLOCALID);

        } else {
          // hiddenFields.remove(ENTITATLOCALID);
          // hiddenFields.remove(DEPARTAMENTID);
          // hiddenFields.remove(AREAID);
          filterList.remove(ENTITATESTATAL);
          groupList.remove(ENTITATESTATAL);

          groupList.add(AREA_NOVA_ID);
          groupList.add(ENTITAT_NOVA_ID);

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

      solicitudFilterForm.addAdditionalButtonForEachItem(
          new AdditionalButton("icon-th-list", "servei.servei.plural",
              "javascript:$('#modal_infoservei_{0}').modal('show');", "btn-info"));

      solicitudFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-eye-open",
          "solicitud.vistacompleta", "/operador/solicitudfullview/view/{0}", "btn-info"));

      solicitudFilterForm.addAdditionalButton(new AdditionalButton("icon-file",
          "exportacio.soli_servei", getContextWeb() + "/fullexport", "btn-info"));

      solicitudFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-bullhorn",
          "Events", /*
                       * "javascript:window.open('" + request.getContextPath() +
                       */
          EventSolicitudOperadorController.CONTEXT_PATH + "/veureevents/{0}"
              + (isestatal==null?"": ("/" + isestatal)) /* ','_blank');" */,
          "btn-success"));

      solicitudFilterForm.setVisibleMultipleSelection(true);

      solicitudFilterForm.setOrderBy(SolicitudFields.DATAINICI.fullName);
      solicitudFilterForm.setOrderAsc(false);

      Boolean estatal = isEstatal();
      if (estatal != null && estatal) {
        if (solicitudFilterForm.getFilterByFields() == null) {
          solicitudFilterForm.setFilterByFields(
              new ArrayList<Field<?>>(solicitudFilterForm.getDefaultFilterByFields()));
        }
        solicitudFilterForm.getFilterByFields().remove(DEPARTAMENTID);
        solicitudFilterForm.getGroupByFields().remove(DEPARTAMENTID);
        // solicitudFilterForm.getFilterByFields().remove(AREAID);
        // solicitudFilterForm.getGroupByFields().remove(AREAID);
      }

    }

    List<EstatSolicitud> estatsSolicitud = estatSolicitudEjb
        .select(new OrderBy(EstatSolicitudFields.ESTATSOLICITUDID));
    StringBuffer subTitle = new StringBuffer();
    for (EstatSolicitud estatSolicitud : estatsSolicitud) {
      if (subTitle.length() != 0) {
        subTitle.append(" | ");
      }
      subTitle.append(estatSolicitud.getEstatSolicitudID() + "=" + estatSolicitud.getNom());
    }

    solicitudFilterForm.setSubTitleCode("=" + subTitle.toString());

    String departamentIDDesde = request.getParameter("departamentIDDesde");
    String departamentIDFins = request.getParameter("departamentIDFins");

    if (departamentIDDesde != null && departamentIDDesde.trim().length() != 0
        && departamentIDFins != null && departamentIDFins.trim().length() != 0) {

      solicitudFilterForm.setDepartamentIDDesde(Long.parseLong(departamentIDDesde));
      solicitudFilterForm.setDepartamentIDFins(Long.parseLong(departamentIDFins));

    }

    // solicitudFilterForm.setItemsPerPage(10);

    return solicitudFilterForm;
  }

  @RequestMapping(value = "/fullexport", method = RequestMethod.GET)
  public ModelAndView exportFull(HttpServletRequest request, HttpServletResponse response,
      SolicitudFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mavTmp = new ModelAndView(getTileList());
    List<Solicitud> list = llistat(mavTmp, request, filterForm);

    String url = ExportFullSolicitudServeiInfoOperatorController.CONTEXT_WEB
        + "/exportFull/org_fundaciobit_plugins_exportdata_ods_ODSPlugin";

    request.getSession().setAttribute(
        ExportFullSolicitudServeiInfoOperatorController.SOLI_LIST_ATTRIBUTE, list);

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
  public void postList(HttpServletRequest request, ModelAndView mav,
      SolicitudFilterForm filterForm, List<Solicitud> list) throws I18NException {

    {


      Map<Long, String> map;
      map = (Map<Long, String>) filterForm.getAdditionalField(MISSATGES_SENSE_LLEGIR_COLUMN)
          .getValueMap();
      map.clear();

      final StringField creador = new EventQueryPath().SOLICITUD().CREADOR();

      final String loginUserName = request.getRemoteUser();

      for (Solicitud inc : list) {

        final String user = inc.getCreador();

        // incidencies

        Long incidencies = eventLogicaEjb
            .count(Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE),
                EventFields.SOLICITUDID.equal(inc.getSolicitudID()),
                creador.equal(user)));

        if (incidencies != 0) {

          final String color;
          if (loginUserName.equals(user)) {
            color = "important";
          } else {
            color = "warning";
          }

          final String text = "<span title=\"Events no llegits\" class=\"badge badge-" + color + " \">" + incidencies
              + "</span>" + "<span title=\"Events no llegits\" class=\"label label-" + color + "\"><b>&#9888;</b></span>";

          map.put(inc.getSolicitudID(), text);

        }

      }

    }
    
    

    filterForm.getAdditionalButtonsByPK().clear();
    boolean error = false;

    Map<Long, String> map = null;

    if (isEstatal() == null) {
      map = (Map<Long, String>) filterForm.getAdditionalField(ENTITAT_COLUMN).getValueMap();
      map.clear();
    }

    Map<String, String> departamentLocalMap = filterForm.getMapOfDepartamentForDepartamentID();

    for (Solicitud soli : list) {

      if (soli.getEstatID() == -1L || soli.getProcedimentCodi().startsWith("CODI_")) {
        error = true;
        filterForm.addAdditionalButtonByPK(soli.getSolicitudID(),
            new AdditionalButton("icon-warning-sign icon-white", "solicitud.senseestat",
                "javascript:alert('Revisi estat o codi de procediment.')", "btn-danger"));

      } else {
        Long count = solicitudServeiEjb
            .count(Where.AND(SolicitudServeiFields.SOLICITUDID.equal(soli.getSolicitudID()),
                SolicitudServeiFields.ESTATSOLICITUDSERVEIID.equal(-1L)));
        if (count != 0) {
          filterForm.addAdditionalButtonByPK(soli.getSolicitudID(),
              new AdditionalButton("icon-warning-sign", "solicitudservei.senseestat",
                  "javascript:alert('Revisi els estats dels serveis associats.')",
                  "btn-danger"));
          error = true;
        }
      }

      // COLUMNA ENTITAT
      if (map != null) {
        Long departamentId = soli.getDepartamentID();
        String tipus, nom;
        if (departamentId == null) {
          // Estatal
          tipus = "es";
          nom = soli.getEntitatEstatal();
        } else {
          // Catala
          tipus = "ca";
          nom = departamentLocalMap.get(String.valueOf(departamentId));
        }
        String img;
        img = "<img src=\"" + request.getContextPath() + "/img/" + tipus
            + "_petit_on.gif\" alt=\"" + tipus
            + "\" width=\"17\" height=\"14\" border=\"0\" />";
        map.put(soli.getSolicitudID(), img + " " + nom);
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

      SelectMultipleStringKeyValue smskv;
      smskv = new SelectMultipleStringKeyValue(
          EstatSolicitudServeiFields.ESTATSOLICITUDSERVEIID.select,
          EstatSolicitudServeiFields.NOM.select);

      List<StringKeyValue> listEstats = estatSolicitudServeiEjb.executeQuery(smskv, null,
          new OrderBy(EstatSolicitudServeiFields.NOM));

      estatsSoliServ = Utils.listToMap(listEstats);

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
          String hrefServ = "<a href=\"" + request.getContextPath() + "/operador/servei/"
              + ss.getServeiID() + "/edit\" >";
          valuediv = valuediv + "<td>" + hrefServ + code + "</a></td>\n";
          valuediv = valuediv + "<td>" + hrefServ + nom + "</a></td>\n";
          // String hrefSoliServ = "<a href=\"" + request.getContextPath()+
          // "/operador/solicitudservei/" + ss.getSolicitudID() + "/edit\" >";
          valuediv = valuediv + "<td>"
              + estatsSoliServ.get(String.valueOf(ss.getEstatSolicitudServeiID())) + "</td>\n";
          final String notes = ss.getNotes();
          valuediv = valuediv + "<td>" + ((notes == null) ? "&nbsp;" : notes) + "</a></td>\n";

          valuediv = valuediv + "</tr>\n";
        }

        // value = "<a href=\"#modal_infoservei_" + solicitud.getSolicitudID() +
        // "\" role=\"button\" class=\"btn btn-mini\" data-toggle=\"modal\">" +
        // llistat + "</a>";

      }
      valuediv = valuediv + "</table>\n";

      divServeis.put(key, valuediv);

      // map.put(key, value);
    }

    mav.addObject("divServeis", divServeis);
    mav.addObject("notesSolicitud", notesSolicitud);
    mav.addObject("codiDescriptiuSolicitud", codiDescriptiuSolicitud);

    /*
     * { for (Solicitud soli : list) { if (soli.getPersonaContacteEmail() !=
     * null) { filterForm.addAdditionalButtonByPK(soli.getSolicitudID(), new
     * AdditionalButton( "icon-envelope", "enviar enllaç conversa",
     * getContextWeb() + "/enviarcorreu/" + soli.getSolicitudID(),
     * "btn-danger")); } } }
     */
    
    

  }

  @Override
  public String getRedirectWhenCreated(HttpServletRequest request,
      SolicitudForm solicitudForm) {
    // return "redirect:" + getContextWeb() + "/list/1";
    return "redirect:/operador/solicitudfullview/view/"
        + solicitudForm.getSolicitud().getSolicitudID();
  }

  @Override
  public String getRedirectWhenCancel(HttpServletRequest request, Long solicitudID) {

    return "redirect:" + getContextWeb() + "/list";
  }

  @Override
  public Map<Field<?>, GroupByItem> fillReferencesForList(SolicitudFilterForm filterForm,
      HttpServletRequest request, ModelAndView mav, List<Solicitud> list,
      List<GroupByItem> groupItems) throws I18NException {

    Map<Field<?>, GroupByItem> groupByItemsMap = super.fillReferencesForList(filterForm,
        request, mav, list, groupItems);

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
      Boolean valBool = new Boolean(va);

      List<Long> idsServei = serveiEjb.executeQuery(ServeiFields.SERVEIID,
          new ServeiQueryPath().ENTITATSERVEI().BALEARS().equal(valBool));

      Long count = solicitudEjb.count(SOLICITUDID.in(solicitudServeiEjb.getSubQuery(
          SolicitudServeiFields.SOLICITUDID, SolicitudServeiFields.SERVEIID.in(idsServei))));

      values.add(new GroupByValueItem(BALEARS, I18NUtils.tradueix(BALEARS.javaName + "." + va),
          va, selected && va.equals(valGroup), count));
    }

    GroupByItem balearsGroupByItem = new GroupByItem(BALEARS, BALEARS.javaName,
        BALEARS.javaName, selected, values);
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
        _listSKV = getReferenceListForAreaNOVAID(request, mav, filterForm, list,
            groupByItemsMap, null);
        _tmp = Utils.listToMap(_listSKV);
        // filterForm.setMapOfAreaForAreaID(_tmp);
        if (filterForm.getGroupByFields().contains(AREA_NOVA_ID)) {
          fillValuesToGroupByItems(_tmp, groupByItemsMap, AREA_NOVA_ID, false);
          groupByItemsMap.get(AREA_NOVA_ID).setCodeLabel("area.area");
        }
        ;
      }

      {
        _listSKV = getReferenceListForEntitatNOVAID(request, mav, filterForm, list,
            groupByItemsMap, null);
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

  public List<StringKeyValue> getReferenceListForEntitatNOVAID(HttpServletRequest request,
      ModelAndView mav, SolicitudFilterForm solicitudFilterForm, List<Solicitud> list,
      Map<Field<?>, GroupByItem> _groupByItemsMap, Where where) throws I18NException {

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
    SelectMultipleStringKeyValue smkv = new SelectMultipleStringKeyValue(
        dqp.AREA().ENTITATID().select, dqp.AREA().ENTITAT().NOM().select,
        dqp.AREA().ENTITAT().CONVENIPMSBAE().select);

    List<StringKeyValue> listSKV = departamentEjb.executeQuery(smkv, null);

    for (StringKeyValue skv : listSKV) {
      String v = skv.value;
      if (v.endsWith(" true")) {
        skv.setValue(
            "<span style=\"color:red\"><b>" + v.replace(" true", " - PMSBAE</b></span>"));
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

  public List<StringKeyValue> getReferenceListForAreaNOVAID(HttpServletRequest request,
      ModelAndView mav, SolicitudFilterForm solicitudFilterForm, List<Solicitud> list,
      Map<Field<?>, GroupByItem> _groupByItemsMap, Where where) throws I18NException {

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
    SelectMultipleStringKeyValue smkv = new SelectMultipleStringKeyValue(
        DepartamentFields.AREAID.select, dqp.AREA().NOM().select);

    return departamentEjb.executeQuery(smkv, null);

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

      Boolean valBool = new Boolean(vaStr);

      List<Long> idsServei = serveiEjb.executeQuery(ServeiFields.SERVEIID,
          new ServeiQueryPath().ENTITATSERVEI().BALEARS().equal(valBool));

      wBalears = SOLICITUDID.in(solicitudServeiEjb.getSubQuery(
          SolicitudServeiFields.SOLICITUDID, SolicitudServeiFields.SERVEIID.in(idsServei)));
    }

    // TIPUS
    Boolean esestatal = isEstatal();
    Where tipusEstatalLocal = null;
    if (esestatal != null) {
      if (esestatal) {
        tipusEstatalLocal = SolicitudFields.ENTITATESTATAL.isNotNull();
      } else {
        tipusEstatalLocal = SolicitudFields.DEPARTAMENTID.isNotNull();
      }
    }

    // FILTRE AVANÇAT PER CERCA

    return Where.AND(getAdditionalConditionFine(request), wBalears,
        super.getAdditionalCondition(request), tipusEstatalLocal,
        getAdditionaConditionAdvancedFilter(request));

  }

  protected Where getAdditionaConditionAdvancedFilter(HttpServletRequest request)
      throws I18NException {

    String af = request.getParameter(FILTRE_AVANZAT_FIELD.getFullName());
    log.info(" Valor Filtre Avanzat FilterBY => ]" + af + "[");

    if (af == null || af.trim().length() == 0) {
      log.info("getAdditionalCondition::NO FILTRAM AVANZAT !!!!");
      return null;
    } else {

      final String likeStr = "%" + af + "%";

      final boolean isNumber = PinbalAdminUtils.isNumber(af);

      // PInfo, Departament, Area o Entitat
      Where w = Where.OR(PINFO.like(likeStr),
          new SolicitudQueryPath().DEPARTAMENT().NOM().like(likeStr),
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
        SubQuery<Event, Long> subquery2a = eventLogicaEjb.getSubQuery(EventFields.SOLICITUDID,
            Where.AND(EventFields.SOLICITUDID.isNotNull(),
                EventFields.CAIDIDENTIFICADORCONSULTA.like(likeStr)));
        SubQuery<Event, Long> subquery2b = eventLogicaEjb.getSubQuery(EventFields.SOLICITUDID,
            Where.AND(EventFields.SOLICITUDID.isNotNull(),
                EventFields.CAIDNUMEROSEGUIMENT.like(likeStr)));
        w = Where.OR(w, SolicitudFields.SOLICITUDID.in(subquery2a),
            SolicitudFields.SOLICITUDID.in(subquery2b));
      }

      log.info("getAdditionalCondition::FILTRAM AVANZAT !!!!!!!!!!");

      return w;
    }

  }

  @Override
  protected List<Solicitud> processarLlistat(ITableManager<Solicitud, Long> ejb,
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

    List<Solicitud> list = super.processarLlistat(ejb, filterForm, pagina,
        whereAdditionalCondition, mav);

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
  public List<StringKeyValue> getReferenceListForEstatID(HttpServletRequest request,
      ModelAndView mav, SolicitudForm solicitudForm, Where where) throws I18NException {

    Boolean estatal = isEstatal();

    if (estatal == null) {
      estatal = (solicitudForm.getSolicitud().getDepartamentID() == null);
    }

    if (estatal) {
      where = Where.AND(where,
          EstatSolicitudFields.ESTATSOLICITUDID.in(new Long[] { 40L, 10L, 60L, 20L }));
    }

    return super.getReferenceListForEstatID(request, mav, solicitudForm, where);

  }

  @Override
  public List<StringKeyValue> getReferenceListForProcedimentTipus(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
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

      __tmp.add(new StringKeyValue(s, s));
    }

    return __tmp;
  }
  
  
  
  
  @RequestMapping(value = "/close/{solicitudID}", method = RequestMethod.GET)
  public String closeSolicitudGet(@PathVariable("solicitudID") java.lang.Long solicitudID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
    
      SolicitudJPA soli = this.findByPrimaryKey(request, solicitudID);
      
      soli.setEstatID(Constants.SOLICITUD_ESTAT_TANCAT);
      
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

}
