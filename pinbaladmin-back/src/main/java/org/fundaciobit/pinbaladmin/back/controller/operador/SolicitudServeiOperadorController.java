package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.SolicitudServeiController;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiForm;
import org.fundaciobit.pinbaladmin.back.utils.CrearExcelDeServeis;
import org.fundaciobit.pinbaladmin.ejb.FitxerService;
import org.fundaciobit.pinbaladmin.persistence.DocumentSolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pinbaladmin.persistence.SolicitudServeiJPA;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudServeiLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Document;
import org.fundaciobit.pinbaladmin.model.entity.Fitxer;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.ServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.ServeiQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd_HH.mm_");

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.DocumentSolicitudService documentSolicitudEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.DocumentService documentEjb;

    @EJB(mappedName = FitxerService.JNDI_NAME)
    protected FitxerService fitxerEjb;

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @EJB(mappedName = SolicitudServeiLogicaService.JNDI_NAME)
    protected SolicitudServeiLogicaService solicitudServeiLogicaEjb;

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
        SolicitudServeiForm solicitudServeiForm = super.getSolicitudServeiForm(_jpa, __isView, request, mav);

        if (solicitudServeiForm.isNou()) {
            Long soli = getSolicitudID(request);

            if (soli == null) {

                // TODO traduir
                HtmlUtils.saveMessageError(request, "No puc associar el servei a la solicitud ja que no s'ha passat "
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
    public SolicitudServeiFilterForm getSolicitudServeiFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        SolicitudServeiFilterForm solicitudServeiFilterForm = super.getSolicitudServeiFilterForm(pagina, mav, request);

        Long soli = getSolicitudID(request);
        if (soli == null) {

            // TODO traduir
            HtmlUtils.saveMessageError(request, "No puc trobar serveis de la solicitud ja que no s'ha passat "
                    + "l´identificador de la solicitud !!!!");
            // XYZ ZZZ No se on enviar: local o estatal
            mav.setView(new RedirectView("/operador/solicitudlocal/list", true));
        } else {

            List<String> codi = solicitudLogicaEjb.executeQuery(SolicitudFields.PROCEDIMENTCODI,
                    SolicitudFields.SOLICITUDID.equal(soli));
            // TODO Traduir
            solicitudServeiFilterForm
                    .setSubTitleCode("=Serveis de la Solicitud amb Codi de Procediment " + codi.get(0));

            if (solicitudServeiFilterForm.isNou()) {

                // if (!Configuracio.isDesenvolupament()) {
                //solicitudServeiFilterForm.addHiddenField(ID);
                // }

                solicitudServeiFilterForm.addHiddenField(SOLICITUDID);
                solicitudServeiFilterForm.addHiddenField(NORMALEGAL);

                solicitudServeiFilterForm.setVisibleMultipleSelection(false);

                solicitudServeiFilterForm.setFilterByFields(new ArrayList<Field<?>>());

                solicitudServeiFilterForm.setGroupByFields(new ArrayList<Field<?>>());

                solicitudServeiFilterForm.setAddButtonVisible(false);

            }

            // Sempre s'ha d'executar

            solicitudServeiFilterForm.getAdditionalButtons().clear();

            solicitudServeiFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_PLUS_SIGN,
                    "solicitudservei.afegirservei", getContextWeb() + "/new", "btn-success"));

            String entEstatal = solicitudLogicaEjb.executeQueryOne(SolicitudFields.ENTITATESTATAL,
                    SolicitudFields.SOLICITUDID.equal(soli));

            if (entEstatal == null) {
                solicitudServeiFilterForm.addAdditionalButton(
                        new AdditionalButton(IconUtils.ICON_RELOAD, "solicitudservei.generarplantillaexcel",
                                getContextWeb() + "/generaplantillaexcelserveis", "btn-warning"));
            }

            Long serveisNoAutoritzats = solicitudServeiLogicaEjb
                    .count(Where.AND(SolicitudServeiFields.SOLICITUDID.equal(soli),
                            SolicitudServeiFields.ESTATSOLICITUDSERVEIID.notEqual(50L)));
            
            if (serveisNoAutoritzats > 0) {
                solicitudServeiFilterForm.addAdditionalButton(new AdditionalButton(IconUtils.ICON_PLUS_SIGN,
                        "solicitudservei.autoritzartots", getContextWeb() + "/autoritzartots", "btn-primary"));
            }
        }

        log.info("Passa per getDocumentFilterForm:" + soli);

        return solicitudServeiFilterForm;

    }

    @RequestMapping(value = "/generaplantillaexcelserveis", method = RequestMethod.GET)
    public String generaPlantillaExcelDeServeis(HttpServletRequest request) throws Exception {

        Long solicitudID = getSolicitudID(request);

        if (solicitudID == null) {
            throw new Exception("generaPlantillaExcelDeServeis() :: El valor per soli val null");
        }

        try {

            log.info("generaPlantillaExcelDeServeis(); => SOLI = " + solicitudID);

            SolicitudJPA soli = solicitudLogicaEjb.findByPrimaryKeyFull(solicitudID);

            //String baseFile = "D:\\dades\\dades\\CarpetesPersonals\\Programacio\\pinbaladmin\\back\\serveis";

            File plantillaXLSX = new File(Configuracio.getTemplateServeisExcel()); // baseFile, "Plantilla-Procedimientos.xlsx");

            //File dest = new File(baseFile, "generat.xlsx");

            byte[] data = CrearExcelDeServeis.crearExcelDeServeis(plantillaXLSX, soli);
            String nom = SDF.format(new Date()) + plantillaXLSX.getName();
            Fitxer f = fitxerEjb.create(nom, data.length,
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", null);

            FileSystemManager.crearFitxer(new ByteArrayInputStream(data), f.getFitxerID());

            Document doc = documentEjb.create(nom, f.getFitxerID(), null, null);

            DocumentSolicitudJPA ds = new DocumentSolicitudJPA(doc.getDocumentID(), solicitudID);

            documentSolicitudEjb.create(ds);

            HtmlUtils.saveMessageSuccess(request, "Generat el fitxer de serveis. Revisar un document titulat " + nom
                    + " dins de l'apartat de 'Llistat de Documents-Sol·licitud'");

        } catch (I18NException ie) {

            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(ie));

        }
        return getRedirectWhen(solicitudID);
    }

    @RequestMapping(value = "/autoritzartots", method = RequestMethod.GET)
    public String autoritzartots(HttpServletRequest request) throws Exception {

        Long solicitudID = getSolicitudID(request);

        try {

            log.info("autoritzartots(); => AUTORITZARTOTS= " + solicitudID);

            Long estatSolicitudServeiID = 50L;
            solicitudServeiLogicaEjb.update(SolicitudServeiFields.ESTATSOLICITUDSERVEIID, estatSolicitudServeiID,
                    SolicitudServeiFields.SOLICITUDID.equal(solicitudID));

            HtmlUtils.saveMessageSuccess(request, "S'han autoritzat tots els serveis");

        } catch (I18NException ie) {
            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(ie));
        }
        return getRedirectWhen(solicitudID);
    }

    
    @RequestMapping(value = "/autoritzarservei/{solicitudserveiid}", method = RequestMethod.GET)
    public String autoritzarServei(HttpServletRequest request,
            @PathVariable("solicitudserveiid") Long solicitudserveiid) throws Exception {

        SolicitudServeiJPA soliSer = solicitudServeiLogicaEjb.findByPrimaryKey(solicitudserveiid);
        try {

            log.info("autoritzarservei(); => SOLICITUDSERVEIID= " + solicitudserveiid);

            Long estatSolicitudServeiID = 50L;
            soliSer.setEstatSolicitudServeiID(estatSolicitudServeiID );
            solicitudServeiLogicaEjb.update(soliSer);

            HtmlUtils.saveMessageSuccess(request, "S'ha autoritzat el servei");

        } catch (I18NException ie) {
            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(ie));
        }
        return getRedirectWhen(soliSer.getSolicitudID());
    }

    
    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        Long soli = getSolicitudID(request);

        if (soli == null) {
            throw new I18NException("error", "getAdditionalCondition() :: El valor de solicitudID es null");
        }

        log.info("getAdditionalCondition(); => SOLI = " + soli);

        return SOLICITUDID.equal(soli);
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, SolicitudServeiFilterForm filterForm,
            List<SolicitudServei> list) throws I18NException {

        boolean error = false;

        filterForm.getAdditionalButtonsByPK().clear();

        for (SolicitudServei solicitudServei : list) {
            if (solicitudServei.getEstatSolicitudServeiID() == -1) {
                error = true;
                filterForm.addAdditionalButtonByPK(solicitudServei.getId(),
                        new AdditionalButton(IconUtils.getWhite(IconUtils.ICON_WARNING), "solicitudservei.senseestat",
                                "javascript:alert('Revisi estat')", "btn-danger"));
            } else {
                if (solicitudServei.getEstatSolicitudServeiID() != 50) {
                    filterForm.addAdditionalButtonByPK(solicitudServei.getId(),
                            new AdditionalButton(IconUtils.getWhite(IconUtils.ICON_CHECK), "solicitudservei.autoritzarservei",
                                    getContextWeb() + "/autoritzarservei/" + solicitudServei.getId(), "btn-primary"));
                }
            }
        }

        if (error) {
            HtmlUtils.saveMessageError(request, "Hi ha serveis associats a les sol·licituds amb estat incorrecte");
        }

    }

    @Override
    public void delete(HttpServletRequest request, SolicitudServei solicitudServei) throws I18NException {
        solicitudServeiLogicaEjb.deleteFull(solicitudServei.getServeiID(), solicitudServei.getSolicitudID(), true);
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, SolicitudServeiForm solicitudServeiForm) {

        Long solicitudID = solicitudServeiForm.getSolicitudServei().getSolicitudID();

        return getRedirectWhen(solicitudID);
    }

    protected String getRedirectWhen(Long solicitudID) {
        return "redirect:/operador/solicitudfullview/view/" + solicitudID;
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, SolicitudServeiForm solicitudServeiForm,
            Throwable __e) {
        if (__e == null) {
            return getRedirectWhenCreated(request, solicitudServeiForm);
        } else {
            return getTileForm();
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

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.ServeiService serveiEjb;

    @Override
    public List<StringKeyValue> getReferenceListForServeiID(HttpServletRequest request, ModelAndView mav,
            SolicitudServeiForm solicitudServeiForm, Where where) throws I18NException {

        Long soliID = solicitudServeiForm.getSolicitudServei().getSolicitudID();

        Long departamentID = solicitudLogicaEjb.executeQueryOne(SolicitudFields.DEPARTAMENTID,
                SolicitudFields.SOLICITUDID.equal(soliID));

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