package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;
import org.fundaciobit.pinbaladmin.back.utils.ParserFormulariXML;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.commons.utils.TipusProcediments;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value = "/operador/solicituddesdefitxer")
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudLocalDesDeFitxerXmlOperador extends SolicitudLocalOperadorController {

    public static final String NOMES_FITXERS = "NOMES_FITXERS_LOCAL";

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EventService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.EventService eventEjb;

    @RequestMapping(value = "/nou", method = RequestMethod.GET)
    public String nou(HttpServletRequest request) throws Exception {
        request.getSession().removeAttribute(NOMES_FITXERS);
        return "redirect:" + getContextWeb() + "/new";
    }

    @Override
    public SolicitudForm getSolicitudForm(SolicitudJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {

        log.info(" ==========  ENTRA SOLICI LOCAL FORM ========");

        SolicitudForm form = super.getSolicitudForm(_jpa, __isView, request, mav);

        if (form.isNou()) {

            boolean nomesFitxers;
            if (request.getSession().getAttribute(NOMES_FITXERS) == null) {
                nomesFitxers = true;
            } else {
                nomesFitxers = (Boolean) request.getSession().getAttribute(NOMES_FITXERS);
            }

            request.getSession().setAttribute(NOMES_FITXERS, nomesFitxers);

            if (nomesFitxers == true) {

                Set<Field<?>> all = new HashSet<Field<?>>(Arrays.asList(SolicitudFields.ALL_SOLICITUD_FIELDS));
                all.remove(SOLICITUDXMLID);
                all.remove(DOCUMENTSOLICITUDID);
                all.remove(ESTATID);
                //all.remove(PROCEDIMENTTIPUS);
                all.remove(DEPARTAMENTID);

                form.setHiddenFields(all);

            } else {
                form.setHiddenFields(new HashSet<Field<?>>());
                form.addHiddenField(ENTITATESTATAL);
                form.addReadOnlyField(DATAINICI);
                form.addReadOnlyField(CREADOR);
                form.addReadOnlyField(OPERADOR);
            }

            log.info(" ES NOU !!!!!!!!!!!!!!!!!!!");

        } else {

            log.info(" ES EDIT !!!!!!!!!!!!!!!!!!!");

        }

        log.info(" ==========  SURT SOLICI FORM ========");

        return form;

    }

    @Override
    public void preValidate(HttpServletRequest request, SolicitudForm solicitudForm, BindingResult result)
            throws I18NException {

        log.info(" =========== ENTRA A PRE VALIDATE " + request.getSession().getAttribute(NOMES_FITXERS)
                + "===============");

        if (Boolean.TRUE.equals(request.getSession().getAttribute(NOMES_FITXERS))
                && !solicitudForm.getSolicitudXmlID().isEmpty()) {

            try {
                byte[] xmlData;
                if (solicitudForm.getSolicitud().getSolicitudXmlID() == null) {
                    InputStream is = solicitudForm.getSolicitudXmlID().getInputStream();

                    xmlData = FileUtils.toByteArray(is);

                    is.close();
                } else {
                    xmlData = FileSystemManager.getFileContent(solicitudForm.getSolicitud().getSolicitudXmlID());
                }

                String xml = new String(xmlData, "UTF-8");

                Properties prop = ParserFormulariXML.getPropertiesFromFormulario(xml);

                // XYZ ZZZ
                //prop.store(new java.io.FileOutputStream("d://tmp//formulario.properties"), "PINBAL_TRAMIT");

                SolicitudJPA solicitud = solicitudForm.getSolicitud();

                // String procedimentCodi = null;
                solicitud.setProcedimentCodi(prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CODIPROC"));

                solicitud.setCodiDescriptiu(null);

                {
                    java.lang.String nomProcediment = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMBREPROC");
                    if (nomProcediment != null && nomProcediment.length() > 250) {
                        nomProcediment = nomProcediment.substring(0, 249);
                    }
                    solicitud.setProcedimentNom(nomProcediment);
                }
                {
                    String codiDescriptiu = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.DESCRIPCION");
                    if (codiDescriptiu != null && codiDescriptiu.length() > 250) {
                        codiDescriptiu = codiDescriptiu.substring(0, 250);
                    }
                    solicitud.setCodiDescriptiu(codiDescriptiu);
                }

                // java.lang.Long estatID = null;
                solicitud.setEstatID(10L);
                // java.lang.String ticketAssociat = null;
                // java.lang.String ticketNumeroSeguiment = null;
                // java.lang.Long departamentID = null;
                // java.lang.String entitatEstatal = null;
                // java.lang.String pinfo = null;
                // solicitud.setDataInici(new Timestamp(System.currentTimeMillis()));
                // java.sql.Timestamp dataFi = null;
                // java.lang.String personaContacte =
                solicitud.setResponsableProcNom(prop.getProperty("FORMULARIO.DATOS_SOLICITUD.NOMOCULSECE"));
                // java.lang.String personaContacteEmail =
                solicitud.setResponsableProcEmail(prop.getProperty("FORMULARIO.DATOS_SOLICITUD.MAILSECE"));

                solicitud.setPersonaContacte(prop.getProperty("FORMULARIO.DATOS_REGISTRO.NOMBRECOMPLETO"));
                solicitud.setPersonaContacteEmail(prop.getProperty("FORMULARIO.DATOS_REGISTRO.EMAIL"));

                solicitud.setDenominacio(prop.getProperty("FORMULARIO.DATOS_SOLICITUD.DENOMINACION"));
                solicitud.setDir3(prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CODIUR"));
                solicitud.setNif(prop.getProperty("FORMULARIO.DATOS_SOLICITUD.CIF"));

                String tp = prop.getProperty("FORMULARIO.DATOS_SOLICITUD.TIPOPROCEDIMIENTO");

                // XYZ ZZZ
                log.info("\n\n XXXXXXXXXXXXXXXXX\n ORIGINAL => ]" + tp + "[\nZZZZZZZZZZZZZZZZZZ\n\n");

                tp = TipusProcediments.getTipusProcedimentByLabel(tp);

                if (tp == null) {
                    HtmlUtils.saveMessageError(request,
                            "No he trobat el Tipus de Procediment per l'etiqueta ]" + tp + "[");
                } else {
                    solicitud.setProcedimentTipus(tp);
                }

                {
                    //        StringWriter writer = new StringWriter();
                    //        prop.store(writer, "NO NODIFICAR");
                    //        java.lang.String notes = writer.getBuffer().toString();
                    //        solicitud.setNotes(notes.substring(0, 2300));
                    solicitud.setNotes("");
                }

                // java.lang.Long documentSolicitudID = null;
                // java.lang.Long documentSolicitudXmlID = null;
                // boolean firmatDocSolicitud = false;
                // boolean produccio = false;

                request.getSession().setAttribute(NOMES_FITXERS, false);

                // request.getSession().setAttribute(DEPARTAMENT,  prop.getProperty("FORMULARIO.DATOS_SOLICITUD.UNIDAD"));

                solicitudForm.setHiddenFields(new HashSet<Field<?>>());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        //    String dep = (String)request.getSession().getAttribute(DEPARTAMENT);
        //    if (dep != null) {
        //      log.info(" =========== DEPARTAMENT !!!!!!!  ===============");
        //      HtmlUtils.saveMessageInfo(request, "Departament: " + dep);
        //    }

        log.info(" =========== SURT DE PRE VALIDATE ===============");

    }

    @Override
    public void postValidate(HttpServletRequest request, SolicitudForm solicitudForm, BindingResult result)
            throws I18NException {

        if (result.hasErrors()) {

            HtmlUtils.saveMessageError(request, "S'HAN DE TORNAR A CARREGAR ELS FITXERS !!!!!!");

        }

    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, SolicitudForm solicitudForm) {

        return "redirect:/operador/solicitudfullview/generarserveis/" + solicitudForm.getSolicitud().getSolicitudID();
    }

    @Override
    public SolicitudJPA create(HttpServletRequest request, SolicitudJPA solicitud)
            throws I18NException, I18NValidationException {
        SolicitudJPA soli = (SolicitudJPA) solicitudEjb.create(solicitud);

        try {
            java.lang.Long _solicitudID_ = soli.getSolicitudID();
            java.lang.Long _incidenciaTecnicaID_ = null;
            java.sql.Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
            java.lang.String _persona_ = request.getUserPrincipal().getName();
            java.lang.String _comentari_ = "S'ha creat la sol·licitud a partir de fitxer XML";
            java.lang.Long _fitxerID_ = null;
            boolean _noLlegit_ = false;
            eventEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_, null, null, _comentari_,
                    _fitxerID_, _noLlegit_, null, null);
        } catch (Throwable th) {
            log.error("Error creant el primer event de la solicitud: " + th.getMessage(), th);
        }
        return soli;
    }

    @Override
    public String getTileForm() {
        return "solicitudFormWebDB_operador";
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
        return false;
    }

}
