package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.webdb.IncidenciaTecnicaController;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.IncidenciaTecnicaForm;
import org.fundaciobit.pinbaladmin.jpa.IncidenciaTecnicaJPA;
import org.fundaciobit.pinbaladmin.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/incidenciatecnica")
@SessionAttributes(types = { IncidenciaTecnicaForm.class, IncidenciaTecnicaFilterForm.class })
public class IncidenciaTecnicaOperadorController extends IncidenciaTecnicaController
    implements Constants {

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EventLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.EventLocal eventEjb;

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
    return "IncidenciaTecnicaOperador_FilterForm";
  }

  @Override
  public IncidenciaTecnicaFilterForm getIncidenciaTecnicaFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {
    IncidenciaTecnicaFilterForm incidenciaTecnicaFilterForm;
    incidenciaTecnicaFilterForm = super.getIncidenciaTecnicaFilterForm(pagina, mav, request);

    if (incidenciaTecnicaFilterForm.isNou()) {
      incidenciaTecnicaFilterForm.addHiddenField(DESCRIPCIO);
      incidenciaTecnicaFilterForm.addHiddenField(CONTACTEEMAIL);
      incidenciaTecnicaFilterForm.addHiddenField(CONTACTETELEFON);
      
      incidenciaTecnicaFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("icon-bullhorn",
          "conversa", /*"javascript:window.open('" +  request.getContextPath() +*/ EventIncidenciaTecnicaOperadorController.CONTEXT_PATH + "/veureevents/{0}" /*','_blank');"*/,
          "btn-success"));
    }

    return incidenciaTecnicaFilterForm;
  }

  @Override
  public IncidenciaTecnicaForm getIncidenciaTecnicaForm(IncidenciaTecnicaJPA _jpa,
      boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    IncidenciaTecnicaForm incidenciaTecnicaForm = super.getIncidenciaTecnicaForm(_jpa,
        __isView, request, mav);

    if (incidenciaTecnicaForm.isNou()) {

      incidenciaTecnicaForm.getIncidenciaTecnica()
          .setDataInici(new Timestamp(System.currentTimeMillis()));
      incidenciaTecnicaForm.getIncidenciaTecnica().setEstat(ESTAT_INCIDENCIA_TECNICA_OBERTA);

      incidenciaTecnicaForm.addReadOnlyField(DATAINICI);
      incidenciaTecnicaForm.addReadOnlyField(ESTAT);
    }

    return incidenciaTecnicaForm;
  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }

  @Override
  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
      ModelAndView mav, Where where) throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue(String.valueOf(ESTAT_INCIDENCIA_TECNICA_OBERTA), "Oberta"));
    __tmp.add(new StringKeyValue(String.valueOf(ESTAT_INCIDENCIA_TECNICA_TANCADA), "Tancada"));
    __tmp.add(new StringKeyValue(String.valueOf(ESTAT_INCIDENCIA_TECNICA_PENDENT_DE_TERCER),
        "Pendent de Tercer"));
    return __tmp;
  }

  /*
   * FALTA ADAPTAR EVENTS PER SUPORTAR INCIDENCIES TECNIQUES!!!!!
   */

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
      java.lang.String _comentari_ = "S'ha creat la Incidència Tècnica";
      java.lang.Long _fitxerID_ = null;
      boolean _noLlegit_ = false;
      eventEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_,
          _comentari_, _fitxerID_, _noLlegit_);
    } catch (Throwable th) {
      log.error("Error creant el primer event de la incidència tecnica: " + th.getMessage(),
          th);
    }

    return it;
  }

}
