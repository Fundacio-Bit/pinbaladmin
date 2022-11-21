package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.StringField;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;
import org.fundaciobit.pinbaladmin.back.controller.webdb.IncidenciaTecnicaController;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaForm;
import org.fundaciobit.pinbaladmin.jpa.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.logic.EventLogicaLocal;
import org.fundaciobit.pinbaladmin.logic.IncidenciaTecnicaLogicaLocal;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.IncidenciaTecnica;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.IncidenciaTecnicaFields;
import org.fundaciobit.pinbaladmin.utils.Constants;
import org.fundaciobit.pinbaladmin.utils.PinbalAdminUtils;
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
public class IncidenciaTecnicaOperadorController extends IncidenciaTecnicaController
    implements Constants {

  public static final String WEBCONTEXT = "/operador/incidencia";

  public static final int FILTRE_AVANZAT_COLUMN = -1;

  public static final StringField FILTRE_AVANZAT_FIELD = DESCRIPCIO;

  public static final String SESSION_SUBFILTRE_NO_LLEGIT = "SESSION_SUBFILTRE_NO_LLEGIT";

  @EJB(mappedName = IncidenciaTecnicaLogicaLocal.JNDI_NAME)
  protected IncidenciaTecnicaLogicaLocal incidenciaTecnicaLogicaEjb;

  @EJB(mappedName = EventLogicaLocal.JNDI_NAME)
  protected EventLogicaLocal eventLogicaEjb;

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
  public IncidenciaTecnicaFilterForm getIncidenciaTecnicaFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {
    IncidenciaTecnicaFilterForm incidenciaTecnicaFilterForm;
    incidenciaTecnicaFilterForm = super.getIncidenciaTecnicaFilterForm(pagina, mav, request);

    if (incidenciaTecnicaFilterForm.isNou()) {

      StringBuilder str = new StringBuilder();

      for (StringKeyValue skv : getReferenceListForEstat(request, mav, null)) {
        str.append(skv.value).append("=").append(skv.key).append(" | ");

      }

      incidenciaTecnicaFilterForm.setSubTitleCode("=Valors Estat => " + str.toString());

      incidenciaTecnicaFilterForm.addHiddenField(INCIDENCIATECNICAID);
      incidenciaTecnicaFilterForm.addHiddenField(NOMENTITAT);
      incidenciaTecnicaFilterForm.addHiddenField(DESCRIPCIO);
      incidenciaTecnicaFilterForm.addHiddenField(CONTACTEEMAIL);
      incidenciaTecnicaFilterForm.addHiddenField(CONTACTETELEFON);
      incidenciaTecnicaFilterForm.addHiddenField(CAIDIDENTIFICADORCONSULTA);
      incidenciaTecnicaFilterForm.addHiddenField(CAIDNUMEROSEGUIMENT);

      // incidenciaTecnicaFilterForm.setGroupBy(ESTAT.fullName);
      // incidenciaTecnicaFilterForm.setGroupValue(String.valueOf(Constants.ESTAT_INCIDENCIA_TECNICA_OBERTA));

      incidenciaTecnicaFilterForm
          .setFilterByFields(incidenciaTecnicaFilterForm.getDefaultFilterByFields());

      // Valors Inicials Filtre
      if (getVistaIncidencia() != VistaIncidencia.NOLLEGITSNOMEUS) {
        incidenciaTecnicaFilterForm.setCreador(request.getRemoteUser());
      }
      incidenciaTecnicaFilterForm.getFilterByFields().add(CREADOR);

      if (getVistaIncidencia() == VistaIncidencia.NORMAL) {
        incidenciaTecnicaFilterForm.setEstatDesde(Constants.ESTAT_INCIDENCIA_OBERTA);
        incidenciaTecnicaFilterForm.setEstatFins(Constants.ESTAT_INCIDENCIA_PENDENT_DE_TERCER);
      }
      incidenciaTecnicaFilterForm.getFilterByFields().add(ESTAT);

      incidenciaTecnicaFilterForm
          .addAdditionalButtonForEachItem(new AdditionalButton("icon-bullhorn", "Comentaris",
              EventIncidenciaTecnicaOperadorController.CONTEXT_PATH + "/veureevents/{0}",
              "btn-success"));

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
        incidenciaTecnicaFilterForm.getFilterByFields().remove(CREADOR);
        incidenciaTecnicaFilterForm.getFilterByFields().remove(NOMENTITAT);
        incidenciaTecnicaFilterForm.getFilterByFields().remove(CONTACTEEMAIL);

      }
      incidenciaTecnicaFilterForm.getFilterByFields().remove(TIPUS);

      if (getVistaIncidencia() != VistaIncidencia.NORMAL) {
        incidenciaTecnicaFilterForm.setAddButtonVisible(false);
      }

    }

    return incidenciaTecnicaFilterForm;
  }

  public boolean showAdvancedFilter() {
    return true;
  }

  @Override
  public IncidenciaTecnicaForm getIncidenciaTecnicaForm(IncidenciaTecnicaJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    IncidenciaTecnicaForm incidenciaTecnicaForm = super.getIncidenciaTecnicaForm(_jpa,
        __isView, request, mav);

    if (incidenciaTecnicaForm.isNou()) {

      incidenciaTecnicaForm.getIncidenciaTecnica()
          .setDataInici(new Timestamp(System.currentTimeMillis()));
      incidenciaTecnicaForm.getIncidenciaTecnica().setEstat(ESTAT_INCIDENCIA_OBERTA);

      incidenciaTecnicaForm.addReadOnlyField(DATAINICI);
      incidenciaTecnicaForm.addReadOnlyField(ESTAT);
      incidenciaTecnicaForm.addReadOnlyField(CREADOR);

      incidenciaTecnicaForm.getIncidenciaTecnica().setCreador(request.getRemoteUser());
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

      SubQuery<Event, Long> subQuery = eventLogicaEjb.getSubQuery(
          EventFields.INCIDENCIATECNICAID, Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE),
              EventFields.INCIDENCIATECNICAID.isNotNull()));
      w1 = Where.AND(CREADOR.equal(request.getRemoteUser()), INCIDENCIATECNICAID.in(subQuery));
    }
      break;
    case NOLLEGITSNOMEUS: {
      // incidencies No Meves
      SubQuery<Event, Long> subQuery = eventLogicaEjb.getSubQuery(
          EventFields.INCIDENCIATECNICAID, Where.AND(EventFields.NOLLEGIT.equal(Boolean.TRUE),
              EventFields.INCIDENCIATECNICAID.isNotNull()));
      w1 = Where.AND(CREADOR.notEqual(request.getRemoteUser()),
          INCIDENCIATECNICAID.in(subQuery));
    }

    }
    ;

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

      // Dades general
      Where w = Where.OR(TITOL.like(likeStr), DESCRIPCIO.like(likeStr),
          NOMENTITAT.like(likeStr), CONTACTENOM.like(likeStr), CONTACTEEMAIL.like(likeStr));

      // identificador de consulta o numero seguiment de la incidència
      if (isNumber) {
        w = Where.OR(w, CAIDIDENTIFICADORCONSULTA.like(likeStr),
            CAIDNUMEROSEGUIMENT.like(likeStr));
      }

      // Comentari dels Events
      SubQuery<Event, Long> subquery1 = eventLogicaEjb.getSubQuery(
          EventFields.INCIDENCIATECNICAID,
          Where.AND(EventFields.INCIDENCIATECNICAID.isNotNull(),
              EventFields.COMENTARI.like(likeStr)));
      w = Where.OR(w, IncidenciaTecnicaFields.INCIDENCIATECNICAID.in(subquery1));

      // identificador de consulta o numero seguiment dels events
      if (isNumber) {
        SubQuery<Event, Long> subquery2a = eventLogicaEjb.getSubQuery(
            EventFields.INCIDENCIATECNICAID,
            Where.AND(EventFields.INCIDENCIATECNICAID.isNotNull(),
                EventFields.CAIDIDENTIFICADORCONSULTA.like(likeStr)));
        SubQuery<Event, Long> subquery2b = eventLogicaEjb.getSubQuery(
            EventFields.INCIDENCIATECNICAID,
            Where.AND(EventFields.INCIDENCIATECNICAID.isNotNull(),
                EventFields.CAIDNUMEROSEGUIMENT.like(likeStr)));
        w = Where.OR(w, IncidenciaTecnicaFields.INCIDENCIATECNICAID.in(subquery2a),
            IncidenciaTecnicaFields.INCIDENCIATECNICAID.in(subquery2b));
      }

      log.info("getAdditionalCondition::FILTRAM AVANZAT !!!!!!!!!!");

      return w;
    }

  }

  @Override
  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue(String.valueOf(ESTAT_INCIDENCIA_OBERTA), "Oberta"));
    __tmp.add(new StringKeyValue(String.valueOf(ESTAT_INCIDENCIA_PENDENT_DE_TERCER),
        "Pendent de Tercer"));
    __tmp.add(new StringKeyValue(String.valueOf(ESTAT_INCIDENCIA_TANCADA), "Tancada"));
    return __tmp;
  }

  @Override
  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

    __tmp.add(
        new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_TECNICA), "Tècnica"));
    __tmp.add(
        new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_CONSULTA), "Consulta"));
    __tmp.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_INTEGRACIONS),
        "Integracions"));
    __tmp.add(new StringKeyValue(String.valueOf(Constants.INCIDENCIA_TIPUS_ROLEPERMISOS),
        "Roles de permisos"));
    return __tmp;
  }

  @Override
  public IncidenciaTecnicaJPA create(HttpServletRequest request,
      IncidenciaTecnicaJPA incidenciaTecnica)
      throws Exception, I18NException, I18NValidationException {

    IncidenciaTecnicaJPA it;
    it = (IncidenciaTecnicaJPA) incidenciaTecnicaEjb.create(incidenciaTecnica);

    try {
      java.lang.Long _solicitudID_ = null;
      java.lang.Long _incidenciaTecnicaID_ = it.getIncidenciaTecnicaID();
      java.sql.Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());
      int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
      java.lang.String _persona_ = request.getUserPrincipal().getName();
      java.lang.String _comentari_ = "S'ha creat la Incidència";
      java.lang.Long _fitxerID_ = null;
      boolean _noLlegit_ = false;
      eventLogicaEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_,
          _persona_, _comentari_, _fitxerID_, _noLlegit_, null, null);
    } catch (Throwable th) {
      log.error("Error creant el primer event de la incidència tecnica: " + th.getMessage(),
          th);
    }

    return it;
  }

  @Override
  protected List<IncidenciaTecnica> processarLlistat(
      ITableManager<IncidenciaTecnica, Long> ejb, BaseFilterForm filterForm, int pagina,
      Where whereAdditionalCondition, ModelAndView mav) throws I18NException {
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
  public void delete(HttpServletRequest request, IncidenciaTecnica incidenciaTecnica)
      throws Exception, I18NException {
    incidenciaTecnicaLogicaEjb.deleteFull(incidenciaTecnica.getIncidenciaTecnicaID());
  }

  @RequestMapping(value = "/close/{incidenciaTecnicaID}", method = RequestMethod.GET)
  public String closeIncidenciaTecnicaGet(
      @PathVariable("incidenciaTecnicaID") java.lang.Long incidenciaTecnicaID,
      HttpServletRequest request, HttpServletResponse response) throws I18NException {

    IncidenciaTecnicaJPA i = this.findByPrimaryKey(request, incidenciaTecnicaID);

    i.setEstat(Constants.ESTAT_INCIDENCIA_TANCADA);

    try {
      this.update(request, i);

      HtmlUtils.saveMessageSuccess(request, "Tancada Incidència correctament.");
    } catch (Throwable e) {
      String msg = "Error tancant incidència " + e.getMessage();
      log.error(msg, e);
      HtmlUtils.saveMessageError(request, msg);
    }

    return "redirect:" + WEBCONTEXT + "/" + incidenciaTecnicaID + "/edit";
  }

}
