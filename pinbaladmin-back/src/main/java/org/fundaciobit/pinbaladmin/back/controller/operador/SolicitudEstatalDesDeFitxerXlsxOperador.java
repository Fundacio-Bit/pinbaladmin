package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
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
import org.fundaciobit.pinbaladmin.back.utils.ParserSolicitudXLSX;
import org.fundaciobit.pinbaladmin.back.utils.ProcedimentInfo;
import org.fundaciobit.pinbaladmin.back.utils.SolicitudInfo;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
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
@RequestMapping(value = "/operador/solicitudestataldesdefitxerxlsx")
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudEstatalDesDeFitxerXlsxOperador extends SolicitudEstatalOperadorController {

    public static final String NOMES_FITXERS = "NOMES_FITXERS_ESTATAL";

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

        log.info(" ==========  ENTRA SOLICI ESTATAL FORM ========");

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
                all.remove(TICKETASSOCIAT);
                //all.remove(PROCEDIMENTTIPUS);
                //all.remove(DEPARTAMENTID);

                form.addLabel(SOLICITUDXMLID, "fitxer.xlsx");

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

        log.info(" ==========  SURT SOLICI ESTATALFORM ========");

        return form;

    }

    @Override
    public void preValidate(HttpServletRequest request, SolicitudForm solicitudForm, BindingResult result)
            throws I18NException {

        log.info(" =========== ENTRA A PRE VALIDATE  ESTATAL " + request.getSession().getAttribute(NOMES_FITXERS)
                + "===============");

        log.info(" =========== XXXXXXXXXXXX PV => " + solicitudForm.getSolicitud().getTicketNumeroSeguiment());

        if (Boolean.TRUE.equals(request.getSession().getAttribute(NOMES_FITXERS))
                && !solicitudForm.getSolicitudXmlID().isEmpty()) {

            try {
                InputStream is;
                if (solicitudForm.getSolicitud().getSolicitudXmlID() == null) {
                    is = new ByteArrayInputStream(solicitudForm.getSolicitudXmlID().getBytes());
                } else {
                    is = new FileInputStream(
                            FileSystemManager.getFile(solicitudForm.getSolicitud().getSolicitudXmlID()));
                }

                processSolicitud(request, solicitudForm.getSolicitud(), is);

                //solicitud.setNotes("");

                request.getSession().setAttribute(NOMES_FITXERS, false);

                solicitudForm.setHiddenFields(new HashSet<Field<?>>());

            } catch (Exception e) {
                String msg = "Error desconegut processant fitxer: " + e.getMessage();
                log.error(msg, e);
                result.reject("", msg);
            }

        }

        log.info(" =========== SURT DE PRE VALIDATE ESTATAL===============");

    }

    public void processSolicitud(HttpServletRequest request, SolicitudJPA solicitud, InputStream xlsx)
            throws Exception {
        final boolean debug = false;
        SolicitudInfo info = ParserSolicitudXLSX.extreureInfo(xlsx, debug);

        // El primer de la llista ...
        ProcedimentInfo proc = info.getProcediments().values().iterator().next();

        // String procedimentCodi = null;
        solicitud.setProcedimentCodi(proc.getCodi());

        //solicitud.setCodiDescriptiu(null);

        solicitud.setProcedimentNom(proc.getNom());

        solicitud.setEstatID(10L);
        solicitud.setEntitatEstatal(info.getEntitat());

        String tp = proc.getTipusProcediment();

        // XYZ ZZZ
        log.info("\n\n XXXXXXXXXXXXXXXXX\n ESTATAL TP ORIGINAL => ]" + tp + "[\nZZZZZZZZZZZZZZZZZZ\n\n");

        tp = TipusProcediments.getTipusProcedimentByLabel(tp);

        if (tp == null) {
            HtmlUtils.saveMessageError(request, "No he trobat el Tipus de Procediment per l'etiqueta ]" + tp + "[");
        } else {
            solicitud.setProcedimentTipus(tp);
        }
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
        return "redirect:/operador/solicitudestatal/" + solicitudForm.getSolicitud().getSolicitudID() + "/edit";
        //return "redirect:/operador/solicitudfullview/generarserveis/" + solicitudForm.getSolicitud().getSolicitudID();
    }

    @Override
    public SolicitudJPA create(HttpServletRequest request, SolicitudJPA solicitud)
            throws I18NException, I18NValidationException {

        log.info(" =========== XXXXXXXXXXXX CREA => " + solicitud.getTicketNumeroSeguiment());

        SolicitudJPA soli = (SolicitudJPA) solicitudEjb.create(solicitud);

        try {
            java.lang.Long _solicitudID_ = soli.getSolicitudID();
            java.lang.Long _incidenciaTecnicaID_ = null;
            java.sql.Timestamp _dataEvent_ = new Timestamp(System.currentTimeMillis());
            int _tipus_ = Constants.EVENT_TIPUS_COMENTARI_TRAMITADOR_PRIVAT;
            java.lang.String _persona_ = request.getUserPrincipal().getName();
            java.lang.String _comentari_ = "S'ha creat la sol·licitud a partir de fitxer XLSX";
            java.lang.Long _fitxerID_ = null;
            boolean _noLlegit_ = false;
            java.lang.String _destinatari_ = null;
            java.lang.String _destinatariMail_ = null;

            eventEjb.create(_solicitudID_, _incidenciaTecnicaID_, _dataEvent_, _tipus_, _persona_, _destinatari_, _destinatariMail_, _comentari_,
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
