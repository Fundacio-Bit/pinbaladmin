package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.pinbaladmin.back.controller.webdb.SolicitudServeiController;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudServeiForm;
import org.fundaciobit.pinbaladmin.model.entity.Servei;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.entity.SolicitudServei;
import org.fundaciobit.pinbaladmin.model.fields.EntitatFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudQueryPath;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudServeiQueryPath;
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
@RequestMapping(value = ExportFullSolicitudServeiInfoOperatorController.CONTEXT_WEB)
@SessionAttributes(types = { SolicitudServeiForm.class, SolicitudServeiFilterForm.class })
public class ExportFullSolicitudServeiInfoOperatorController extends SolicitudServeiController {

    public static final String CONTEXT_WEB = "/operador/solicitudserveiexportfull";

    public static final String SOLI_LIST_ATTRIBUTE = "SOLI_LIST_ATTRIBUTE";

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.EntitatService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.EntitatService entitatEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.ServeiService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.ServeiService serveiEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;

    public static final int SOLI_CODI = -15;

    public static final int SOLI_NOM = -14;

    public static final int SOLI_ESTAT = -13;

    // public static final int SOLI_DATA = -12;

    public static final int SOLI_ENTITAT_LOCAL = -12;

    public static final int SOLI_ENTITAT_ESTATAL = -11;

    public static final int SERV_CODI = -4;

    public static final int SERV_NOM = -3;

    public static final int SERV_ESTAT = -2;

    public static final int SOLI_SERV_ESTAT = 1;

    @Override
    public SolicitudServeiFilterForm getSolicitudServeiFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        SolicitudServeiFilterForm solicitudServeiFilterForm;
        solicitudServeiFilterForm = super.getSolicitudServeiFilterForm(pagina, mav, request);
        if (solicitudServeiFilterForm.isNou()) {

            solicitudServeiFilterForm.setDefaultOrderBy(new OrderBy[] {
                    new OrderBy(new SolicitudServeiQueryPath().SOLICITUD().DATAINICI().javaName, OrderType.DESC) });

            final String[][] addFields = { { String.valueOf(SOLI_ENTITAT_LOCAL), "solicitud.entitatLocalID" },
                    { String.valueOf(SOLI_ENTITAT_ESTATAL), "solicitud.entitatEstatal" },

                    // { String.valueOf(SOLI_DATA), "solicitud.dataInici" },
                    { String.valueOf(SOLI_ESTAT), "solicitud.estatID" },
                    { String.valueOf(SOLI_NOM), "solicitud.procedimentNom" },
                    { String.valueOf(SOLI_CODI), "solicitud.procedimentCodi" },

                    { String.valueOf(SERV_CODI), "servei.codi" }, { String.valueOf(SERV_NOM), "servei.nom" },
                    { String.valueOf(SERV_ESTAT), "estat.servei" },

                    { String.valueOf(SOLI_SERV_ESTAT), "estat.soliservei" },

            };

            for (int i = 0; i < addFields.length; i++) {
                AdditionalField<Long, String> addFieldName = new AdditionalField<Long, String>();
                addFieldName.setCodeName(addFields[i][1]);
                addFieldName.setPosition(Integer.parseInt(addFields[i][0]));
                // Els valors s'ompliran al mètode postList()
                addFieldName.setValueMap(new HashMap<Long, String>());
                addFieldName.setEscapeXml(false);

                solicitudServeiFilterForm.addAdditionalField(addFieldName);
            }

            solicitudServeiFilterForm.setItemsPerPage(-1);

            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(
                    Arrays.asList(SolicitudServeiFields.ALL_SOLICITUDSERVEI_FIELDS));

            // hiddenFields.remove(NOTES);

            solicitudServeiFilterForm.setHiddenFields(hiddenFields);

        }
        return solicitudServeiFilterForm;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        List<Solicitud> list = (List<Solicitud>) request.getSession().getAttribute(SOLI_LIST_ATTRIBUTE);

        Long[] soliIDs = new Long[list.size()];
        int count = 0;
        for (Solicitud soli : list) {
            soliIDs[count++] = soli.getSolicitudID();
        }

        return SOLICITUDID.in(soliIDs);
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return CONTEXT_WEB;
    }

    @RequestMapping(value = "/exportFull/{dataExporterID}", method = RequestMethod.GET)
    public void exportFull(@PathVariable("dataExporterID") String dataExporterID, HttpServletRequest request,
            HttpServletResponse response) throws Exception, I18NException {

        SolicitudServeiFilterForm filterForm = getSolicitudServeiFilterForm(1, new ModelAndView(), request);
        super.exportList(dataExporterID, request, response, filterForm);
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, SolicitudServeiFilterForm filterForm,
            List<SolicitudServei> list) throws I18NException {

        Map<Long, String> mapNom;
        mapNom = (Map<Long, String>) filterForm.getAdditionalField(SOLI_NOM).getValueMap();
        mapNom.clear();

        Map<Long, String> mapCodi;
        mapCodi = (Map<Long, String>) filterForm.getAdditionalField(SOLI_CODI).getValueMap();
        mapCodi.clear();

        Map<Long, String> mapEntitatLocal;
        mapEntitatLocal = (Map<Long, String>) filterForm.getAdditionalField(SOLI_ENTITAT_LOCAL).getValueMap();
        mapEntitatLocal.clear();

        Map<Long, String> mapEntitatEstatal;
        mapEntitatEstatal = (Map<Long, String>) filterForm.getAdditionalField(SOLI_ENTITAT_ESTATAL).getValueMap();
        mapEntitatEstatal.clear();

        // Map<Long, String> mapData;
        // mapData = (Map<Long,
        // String>)filterForm.getAdditionalField(SOLI_DATA).getValueMap();
        // mapData.clear();

        Map<Long, String> mapEstatSoli;
        mapEstatSoli = (Map<Long, String>) filterForm.getAdditionalField(SOLI_ESTAT).getValueMap();
        mapEstatSoli.clear();

        Map<Long, String> mapServCodi;
        mapServCodi = (Map<Long, String>) filterForm.getAdditionalField(SERV_CODI).getValueMap();
        mapServCodi.clear();

        Map<Long, String> mapServNom;
        mapServNom = (Map<Long, String>) filterForm.getAdditionalField(SERV_NOM).getValueMap();
        mapServNom.clear();

        Map<Long, String> mapServEstat;
        mapServEstat = (Map<Long, String>) filterForm.getAdditionalField(SERV_ESTAT).getValueMap();
        mapServEstat.clear();

        Map<Long, String> mapSoliServEstat;
        mapSoliServEstat = (Map<Long, String>) filterForm.getAdditionalField(SOLI_SERV_ESTAT).getValueMap();
        mapSoliServEstat.clear();

        List<Solicitud> listSoli = (List<Solicitud>) request.getSession().getAttribute(SOLI_LIST_ATTRIBUTE);

        Map<Long, Solicitud> mapSoli = new HashMap<Long, Solicitud>();

        Map<Long, Servei> mapServei = new HashMap<Long, Servei>();

        for (Solicitud solicitud : listSoli) {
            mapSoli.put(solicitud.getSolicitudID(), solicitud);
        }

        //I18NDateTimeFormat df = new I18NDateTimeFormat();

        for (SolicitudServei solicitudServei : list) {

            long id = solicitudServei.getId();

            // SOLICITUD-SERVEI
            mapSoliServEstat.put(id,
                    SolicitudServeiOperadorController.ESTATS_SOLICITUD_SERVEI.get(solicitudServei.getEstatSolicitudServeiID()));

            // SERVEI
            long serveiID = solicitudServei.getServeiID();
            Servei servei = mapServei.get(serveiID);

            if (servei == null) {
                servei = serveiEjb.findByPrimaryKey(serveiID);
                mapServei.put(serveiID, servei);
            }

            mapServCodi.put(id, servei.getCodi());

            mapServNom.put(id, servei.getNom());

            // XYZ ZZZ TODO fer cache !!!!
            mapServEstat.put(id, ServeiOperadorController.ESTATS_SERVEI.get(servei.getEstatServeiID()));

            // SOLICITUD
            Solicitud solicitud = mapSoli.get(solicitudServei.getSolicitudID());

            mapNom.put(id, solicitud.getProcedimentNom());

            mapCodi.put(id, solicitud.getProcedimentCodi());

            if (solicitud.getEntitatEstatal() == null) {
                mapEntitatEstatal.put(id, "");

                Long entitatID = solicitudEjb.executeQueryOne(new SolicitudQueryPath().DEPARTAMENT().AREA().ENTITATID(),
                        SolicitudFields.SOLICITUDID.equal(solicitud.getSolicitudID()));

                String nom = entitatEjb.executeQueryOne(EntitatFields.NOM, EntitatFields.ENTITATID.equal(entitatID));
                mapEntitatLocal.put(id, nom);
            } else {
                mapEntitatEstatal.put(id, solicitud.getEntitatEstatal());
                mapEntitatLocal.put(id, "");
            }

            // mapData.put(id, df.format(solicitud.getDataInici()));

            mapEstatSoli.put(id, SolicitudOperadorController.ESTATS_SOLICITUD.get(solicitud.getEstatID()));

        }

    }

    @Override
    public List<StringKeyValue> getReferenceListForEstatSolicitudServeiID(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {

        return SolicitudServeiOperadorController.getReferenceListForEstatSolicitudServeiIDStatic();
    }

}
