package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.EntitatController;
import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatFilterForm;
import org.fundaciobit.pinbaladmin.back.form.webdb.EntitatForm;
import org.fundaciobit.pinbaladmin.ejb.DocumentEntitatService;
import org.fundaciobit.pinbaladmin.model.entity.Entitat;
import org.fundaciobit.pinbaladmin.model.fields.DocumentEntitatFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudQueryPath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/operador/entitat")
@SessionAttributes(types = { EntitatForm.class, EntitatFilterForm.class })
public class EntitatOperadorController extends EntitatController {

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.SolicitudService.JNDI_NAME)
    protected org.fundaciobit.pinbaladmin.ejb.SolicitudService solicitudEjb;

    @EJB(mappedName = org.fundaciobit.pinbaladmin.ejb.DocumentEntitatService.JNDI_NAME)
    protected DocumentEntitatService documentEntitatEjb;

    //public static final int SOLICITUDS = 1;
    public static final int DOCS = 1;

    @Override
    public String getTileForm() {
        return "entitatFormWebDB_operador";
    }

    @Override
    public String getTileList() {
        return "entitatListWebDB_operador";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "EntitatWebDB_FilterForm_operador";
    }

    @Override
    public EntitatFilterForm getEntitatFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {

        EntitatFilterForm entitatFilterForm = super.getEntitatFilterForm(pagina, mav, request);

        /*
        AdditionalField<Long,String> adfield4 = new AdditionalField<Long,String>(); 
        adfield4.setCodeName("solicitud.solicitud.plural");
        adfield4.setPosition(SOLICITUDS);
        // Els valors s'ompliran al mètode postList()
        adfield4.setValueMap(new HashMap<Long, String>());
        adfield4.setEscapeXml(false);
        
        entitatFilterForm.addAdditionalField(adfield4);
        */

        if (entitatFilterForm.isNou()) {

            entitatFilterForm.setVisibleMultipleSelection(false);

            entitatFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_LIST,
                    "solicitud.llistat", "javascript:$('#modal_infosoli_{0}').modal('show');", AdditionalButtonStyle.INFO));

            AdditionalField<Long, String> adfield4 = new AdditionalField<Long, String>();
            adfield4.setCodeName("=Docs.");
            adfield4.setPosition(DOCS);
            // Els valors s'ompliran al mètode postList()
            adfield4.setValueMap(new HashMap<Long, String>());
            adfield4.setEscapeXml(false);
            entitatFilterForm.addAdditionalField(adfield4);

            // Boto per afegir, editar o modificar documents de l'entitat
            entitatFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(IconUtils.ICON_FILE,
                    "documentsentitatlocal", "/operador/entitat/documents/{0}", AdditionalButtonStyle.SUCCESS));

            entitatFilterForm.setAttachedAdditionalJspCode(true);
        }

        return entitatFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, EntitatFilterForm filterForm, List<Entitat> list)
            throws I18NException {

        /*
        Map<Long, String> map;
        map = (Map<Long, String>)filterForm.getAdditionalField(SOLICITUDS).getValueMap(); 
        map.clear();
        */
        Long key;
        //String value;

        //String llistat = I18NUtils.tradueix("solicitud.llistat");
        Map<Long, String> divSolicituds = new HashMap<Long, String>();

        Map<Long, String> map;
        map = (Map<Long, String>) filterForm.getAdditionalField(DOCS).getValueMap();
        map.clear();

        for (Entitat entitat : list) {

            // DOCUMENTS

            long count = documentEntitatEjb.count(DocumentEntitatFields.ENTITATID.equal(entitat.getEntitatID()));

            if (count == 0) {
                map.put(entitat.getEntitatID(), "");
            } else {
                map.put(entitat.getEntitatID(), String.valueOf(count));
            }

            // SOLICITUDS

            SelectMultipleStringKeyValue smskv;
            smskv = new SelectMultipleStringKeyValue(SolicitudFields.SOLICITUDID.select, "*",
                    SolicitudFields.PROCEDIMENTCODI.select, SolicitudFields.PROCEDIMENTNOM.select);

            // TODO XYZ 
            SolicitudQueryPath sqp = new SolicitudQueryPath();
            // ORIG XXXX List<StringKeyValue> id_nom_Solis = solicitudEjb.executeQuery(smskv,SolicitudFields.ENTITATLOCALID.equal(entitat.getEntitatID())); 
            List<StringKeyValue> id_nom_Solis = solicitudEjb.executeQuery(smskv,
                    sqp.DEPARTAMENT().AREA().ENTITATID().equal(entitat.getEntitatID()));
            //log.info("          - solis NOMS: ]" + Arrays.toString(id_nom_Solis.toArray())  + "["); 

            key = entitat.getEntitatID();

            //value = "";
            String valuediv = "";
            if (id_nom_Solis.size() != 0) {

                valuediv = "<ul>";
                for (StringKeyValue skv : id_nom_Solis) {
                    int pos = skv.value.indexOf('*');
                    String code = skv.value.substring(0, pos);
                    String nom = skv.value.substring(pos + 1);

                    // NOTA: Les entitats locals tenen sol3licituds locals !!!!
                    valuediv = valuediv + "<li><a href=\"" + request.getContextPath() + "/operador/solicitudlocal/"
                            + skv.key + "/edit\" >" + code + ": " + nom + "</a></li>";

                }
                valuediv += "</ul>";

            }

            divSolicituds.put(key, valuediv);

            //value = StringUtils.join(nomSolis.toArray()) + "<br/><a href=\"www.google.cat\">GOOGLE</a>";

            //map.put(key, value);
        }

        mav.addObject("divSolicituds", divSolicituds);

    }

    @RequestMapping(value = "/documents/{entitatLocalID}", method = RequestMethod.GET)
    public ModelAndView veureDocumentCedentGet(@PathVariable("entitatLocalID") java.lang.Long entitatLocalID,
            HttpServletRequest request, HttpServletResponse response) throws I18NException {

        request.getSession().setAttribute(DocumentEntitatLocalOperatorController.ENTITATLOCALID_SESSION_PROPERTY,
                entitatLocalID);

        return new ModelAndView(new RedirectView("/operador/documententitatlocal/list", true));

    }

}
