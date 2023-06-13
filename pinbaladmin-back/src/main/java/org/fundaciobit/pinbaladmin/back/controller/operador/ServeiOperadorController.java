package org.fundaciobit.pinbaladmin.back.controller.operador;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.ServeiController;
import org.fundaciobit.pinbaladmin.back.form.webdb.ServeiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.ServeiForm;
import org.fundaciobit.pinbaladmin.persistence.ServeiJPA;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.logic.dto.SolicitudDTO;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.commons.utils.Configuracio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author anadal
 * @author areus
 */
@Controller
@RequestMapping(value = "/operador/servei")
@SessionAttributes(types = { ServeiForm.class, ServeiFilterForm.class })
public class ServeiOperadorController extends ServeiController {

    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @Override
    public ServeiForm getServeiForm(ServeiJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        ServeiForm serveiForm = super.getServeiForm(_jpa, __isView, request, mav);

        if (serveiForm.isNou()) {
            serveiForm.getServei().setEstatServeiID(10L);
        }
        return serveiForm;
    }

    @Override
    public ServeiFilterForm getServeiFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        ServeiFilterForm serveiFilterForm = super.getServeiFilterForm(pagina, mav, request);

        if (serveiFilterForm.isNou()) {

            if (!Configuracio.isDesenvolupament()) {
                serveiFilterForm.addHiddenField(SERVEIID);
            }

            serveiFilterForm.addHiddenField(DESCRIPCIO);
            serveiFilterForm.addHiddenField(FORMULARIID);

            serveiFilterForm.setAttachedAdditionalJspCode(true);

            serveiFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_LIST,
                    "solicitud.llistat", "javascript:$('#modal_infosoli_{0}').modal('show');", "btn-info"));

            serveiFilterForm.setVisibleMultipleSelection(false);
        }

        return serveiFilterForm;
    }

    @Override
    public String getTileForm() {
        return "serveiFormWebDB_operador";
    }

    @Override
    public String getTileList() {
        return "serveiListWebDB_operador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "ServeiWebDB_FilterForm_operador";
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, ServeiFilterForm filterForm,
            List<Servei> serveiList) throws I18NException {

        Map<Long, String> divSolicituds = new HashMap<Long, String>();
        boolean error = false;

        filterForm.getAdditionalButtonsByPK().clear();

        // Agafam tots els ids de servei que anam a mostrar
        Set<Long> idsServei = new HashSet<Long>();
        for (Servei servei : serveiList) {
            idsServei.add(servei.getServeiID());
        }

        Map<Long, List<SolicitudDTO>> solicitudsByServei = solicitudLogicaEjb.getSolicitudsByServei(idsServei);

        for (Servei servei : serveiList) {

            if (servei.getEntitatServeiID() == -1 || servei.getEstatServeiID() == -1) {
                filterForm.addAdditionalButtonByPK(servei.getServeiID(),
                        new AdditionalButton(IconUtils.getWhite(IconUtils.ICON_WARNING), "servei.senseestat.plural",
                                "javascript:alert('revisi estat o cedent')", "btn-danger"));
                error = true;
            }

            List<SolicitudDTO> solicituds = solicitudsByServei.get(servei.getServeiID());

            StringBuilder valuedivBuffer = new StringBuilder();

            if (solicituds.size() > 0) {

                valuedivBuffer.append("<ul>");
                for (SolicitudDTO solicitud : solicituds) {

                    String tipus = (solicitud.getDepartamentID() == null ? "es" : "ca");

                    String imgUrl = request.getContextPath() + "/img/" + tipus + "_petit_on.gif";
                    String img = "<img src=\"" + imgUrl + "\" alt=\"" + tipus
                            + "\" width=\"17\" height=\"14\" border=\"0\" />";

                    String urlSolicitud = request.getContextPath() + "/operador/solicitudfullview/view/"
                            + solicitud.getSolicitudID();

                    valuedivBuffer.append("<li><a href=\"").append(urlSolicitud).append("\" >").append(img).append(" ")
                            .append(solicitud.getProcedimentCodi()).append(": ").append(solicitud.getProcedimentNom())
                            .append("</a></li>");
                }
                valuedivBuffer.append("</ul>");
            }

            divSolicituds.put(servei.getServeiID(), valuedivBuffer.toString());
        }

        mav.addObject("divSolicituds", divSolicituds);

        if (error) {
            HtmlUtils.saveMessageError(request, "Hi ha serveis amb estat o cedent incorrecte");
        }
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipusConsentiment(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("0", I18NUtils.tradueix("servei.tipusconsentiment.0")));
        __tmp.add(new StringKeyValue("1", I18NUtils.tradueix("servei.tipusconsentiment.1")));
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForEstatServeiID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (Map.Entry<Long, String> entry : ESTATS_SERVEI.entrySet()) {
            Long key = entry.getKey();
            String val = entry.getValue();
            __tmp.add(new StringKeyValue(String.valueOf(key), val));
        }

        return __tmp;
    }
    
    
    public static final Map<Long, String> ESTATS_SERVEI = new HashMap<Long, String>();

    static {
        ESTATS_SERVEI.put(-1L, "Sense Estat");
        ESTATS_SERVEI.put(10L, "Pendent");
        ESTATS_SERVEI.put(20L, "Producció");
        ESTATS_SERVEI.put(30L, "A extingir");
        ESTATS_SERVEI.put(40L, "Extingit");
        ESTATS_SERVEI.put(151200L, "PRE-Producció");
        ESTATS_SERVEI.put(151201L, "No intermediat");
    }
}
