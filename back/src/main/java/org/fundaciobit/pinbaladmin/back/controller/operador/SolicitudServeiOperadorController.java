package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.pinbaladmin.back.controller.webdb.SolicitudServeiController;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiForm;
import org.fundaciobit.pinbaladmin.jpa.SolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaLocal;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.utils.Configuracio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/operador/solicitudservei")
@SessionAttributes(types = { SolicitudServeiForm.class, SolicitudServeiFilterForm.class })
public class SolicitudServeiOperadorController extends SolicitudServeiController {

  public static final String SESSIO_SOLIID_MANAGE_SERVEIS = "SESSIO_SOLIID_MANAGE_SERVEIS";

  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.SolicitudLocal solicitudEjb;
  
  
  @EJB(mappedName = SolicitudServeiLogicaLocal.JNDI_NAME)
  protected SolicitudServeiLogicaLocal solicitudServeiLogicaEjb;
  
  
  

  @Override
  public String getTileForm() {
    return "solicitudServeiFormWebDB_operador";
  }

  @Override
  public String getTileList() {
    return "solicitudServeiListWebDB_operador";
  }

  @Override
  public String getSessionAttributeFilterForm() {
    return "SolicitudServeiWebDB_FilterForm_Operador";
  }

  @Override
  public String getEntityNameCode() {
    return "servei.servei";
  }

  @Override
  public String getEntityNameCodePlural() {
    return "servei.servei.plural";
  }


  @Override
  public SolicitudServeiForm getSolicitudServeiForm(SolicitudServeiJPA _jpa, boolean __isView,
      HttpServletRequest request, ModelAndView mav) throws I18NException {
    SolicitudServeiForm solicitudServeiForm = super.getSolicitudServeiForm(_jpa, __isView,
        request, mav);

    if (solicitudServeiForm.isNou()) {
      Long soli = getSolicitudID(request);

      if (soli == null) {

        // TODO traduir
        HtmlUtils.saveMessageError(request,
            "No puc associar el servei a la solicitud ja que no s'ha passat "
                + "l´identificador de la solicitud (ni per paràmetre ni per sessio) !!!!");

        // XYZ ZZZ No se on enviar: local o estatal
        mav.setView(new RedirectView("/operador/solicitudlocal/list", true));

      }

      solicitudServeiForm.getSolicitudServei().setSolicitudID(soli);
      solicitudServeiForm.getSolicitudServei().setEstatSolicitudServeiID(10L); // REBUT

    } else {
      // Edicio
      solicitudServeiForm.addReadOnlyField(SERVEIID);
    }

    solicitudServeiForm.addReadOnlyField(SOLICITUDID);

    return solicitudServeiForm;

  }

  public Long getSolicitudID(HttpServletRequest request) {
    Long soli;
    // Ens han de passar la sol·licitud per paràmetre o ja ha d'estar en sessio
    String soliStr = request.getParameter(SolicitudFields.SOLICITUDID.javaName);

    log.info("Get parameter [" + SolicitudFields.SOLICITUDID.javaName + "] = " + soliStr);
    if (soliStr == null) {
      Long sessio = (Long) request.getSession().getAttribute(SESSIO_SOLIID_MANAGE_SERVEIS);
      log.info("Get attibute [" + SESSIO_SOLIID_MANAGE_SERVEIS + "] = " + sessio);
      soli = sessio;
    } else {
      try {
        soli = Long.parseLong(soliStr);
      } catch (Exception e) {
        log.error("Error: " + e.getMessage(), e);
        soli = null;
      }

    }
    return soli;
  }

  @Override
  public SolicitudServeiFilterForm getSolicitudServeiFilterForm(Integer pagina,
      ModelAndView mav, HttpServletRequest request) throws I18NException {
    SolicitudServeiFilterForm solicitudServeiFilterForm = super.getSolicitudServeiFilterForm(
        pagina, mav, request);

    Long soli = getSolicitudID(request);
    if (soli == null) {

      // TODO traduir
      HtmlUtils.saveMessageError(request,
          "No puc trobar serveis de la solicitud ja que no s'ha passat "
              + "l´identificador de la solicitud !!!!");
      // XYZ ZZZ No se on enviar: local o estatal
      mav.setView(new RedirectView("/operador/solicitudlocal/list", true));
    } else {

      List<String> codi = solicitudEjb.executeQuery(SolicitudFields.PROCEDIMENTCODI,
          SolicitudFields.SOLICITUDID.equal(soli));
      // TODO Traduir
      solicitudServeiFilterForm
          .setSubTitleCode("=Serveis de la Solicitud amb Codi de Procediment " + codi.get(0));

      if (solicitudServeiFilterForm.isNou()) {
        
        if (!Configuracio.isDesenvolupament()) {
          solicitudServeiFilterForm.addHiddenField(ID);
        }

        solicitudServeiFilterForm.addHiddenField(SOLICITUDID);
        solicitudServeiFilterForm.addHiddenField(NORMALEGAL);

        solicitudServeiFilterForm.setVisibleMultipleSelection(false);

        solicitudServeiFilterForm.setFilterByFields(new ArrayList<Field<?>>());

        solicitudServeiFilterForm.setGroupByFields(new ArrayList<Field<?>>());

        solicitudServeiFilterForm.setAddButtonVisible(false);
        solicitudServeiFilterForm.addAdditionalButton(new AdditionalButton("icon-plus-sign",
            "solicitudservei.afegirservei", "/operador/solicitudservei/new", ""));

      }

    }

    log.info("Passa per getDocumentFilterForm:" + soli);

    return solicitudServeiFilterForm;

  }

  @Override
  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    Long soli = getSolicitudID(request);

    if (soli == null) {
      throw new I18NException("error",
          "getAdditionalCondition() :: El valor per soli val null");
    }

    log.info("getAdditionalCondition(); => SOLI = " + soli);

    return SOLICITUDID.equal(soli);
  }

  @Override
  public void postList(HttpServletRequest request, ModelAndView mav,
      SolicitudServeiFilterForm filterForm, List<SolicitudServei> list) throws I18NException {

    boolean error = false;

    filterForm.getAdditionalButtonsByPK().clear();

    for (SolicitudServei solicitudServei : list) {
      if (solicitudServei.getEstatSolicitudServeiID() == -1) {
        error = true;
        filterForm.addAdditionalButtonByPK(solicitudServei.getId(), new AdditionalButton(
            "icon-warning-sign icon-white", "solicitudservei.senseestat",
            "javascript:alert('Revisi estat')", "btn-danger"));
      }
    }

    if (error) {
      HtmlUtils.saveMessageError(request,
          "Hi ha serveis associats a les sol·licituds amb estat incorrecte");
    }

  }
  
  
  @Override
  public void delete(HttpServletRequest request, SolicitudServei solicitudServei) throws Exception,I18NException {
    solicitudServeiLogicaEjb.deleteFull(solicitudServei.getServeiID(), solicitudServei.getSolicitudID(), true);
  }

  
  
  
  @Override
  public String getRedirectWhenCreated(HttpServletRequest request, SolicitudServeiForm solicitudServeiForm) {
    return "redirect:/operador/solicitudfullview/view/" + solicitudServeiForm.getSolicitudServei().getSolicitudID();
  }

  @Override
  public String getRedirectWhenModified(HttpServletRequest request, SolicitudServeiForm solicitudServeiForm, Throwable __e) {
    if (__e == null) {
      return  getRedirectWhenCreated(request, solicitudServeiForm);
    } else {
      return  getTileForm();
    }
  }

  @Override
  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long id, Throwable __e) {
    return "redirect:/operador/solicitudfullview/viewsessio";
  }

  @Override
  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long id) {
    return "redirect:/operador/solicitudfullview/viewsessio";
  }
  
  @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiLocal.JNDI_NAME)
  protected org.fundaciobit.pinbaladmin.ejb.ServeiLocal serveiEjb;
  
  @Override
  public List<StringKeyValue> getReferenceListForServeiID(HttpServletRequest request,
      ModelAndView mav, SolicitudServeiForm solicitudServeiForm, Where where)  throws I18NException {
    
    Long soliID = solicitudServeiForm.getSolicitudServei().getSolicitudID();
    
    Long departamentID = solicitudEjb.executeQueryOne(SolicitudFields.DEPARTAMENTID, SolicitudFields.SOLICITUDID.equal(soliID));

    // Filtrar pels serveis que estan en producció (codi 20)
    Where w2 = ServeiFields.ESTATSERVEIID.equal(20L);
    
    // Només per solicituds estatals
    Where w3 = null;
    if (departamentID == null) {
      ServeiQueryPath sqp = new ServeiQueryPath();
      w3 = sqp.ENTITATSERVEI().BALEARS().equal(true);
    }

    return super.getReferenceListForServeiID(request, mav, solicitudServeiForm, Where.AND(where, w2, w3));
  }
  
}
