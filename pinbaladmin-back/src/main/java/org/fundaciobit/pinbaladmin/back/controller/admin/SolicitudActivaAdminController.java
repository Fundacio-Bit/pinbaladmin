package org.fundaciobit.pinbaladmin.back.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.SolicitudController;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudForm;

/**
 * Controlador de pruebas para administrador - Solicitudes Activas
 * Cajón de sastre para experimentar con funcionalidades
 * 
 * @author anadal
 */
@Controller
@RequestMapping(value = SolicitudActivaAdminController.CONTEXTWEB)
@SessionAttributes(types = { SolicitudForm.class, SolicitudFilterForm.class })
public class SolicitudActivaAdminController extends SolicitudController {

	public static final String CONTEXTWEB = "/admin/solicitudactiva";

    @EJB(mappedName = InfoMadridLogicaService.JNDI_NAME)
    protected InfoMadridLogicaService infoMadridLogicaEjb;
    
    @EJB(mappedName = SolicitudLogicaService.JNDI_NAME)
    protected SolicitudLogicaService solicitudLogicaEjb;

    @Override
    public String getTileForm() {
        return "solicitudFormWebDB_admin";
    }

    @Override
    public String getTileList() {
        return "solicitudListWebDB_admin";
    }
    
    @Override
    public String getSessionAttributeFilterForm() {
        return "SolicitudWebDB_FilterForm_admin";
    }

    @Override
    public String getEntityNameCode() {
        return "solicitud.solicitudactiva";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "solicitud.solicitudactiva.plural";
    }

    @Override
    public SolicitudFilterForm getSolicitudFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {

        SolicitudFilterForm solicitudFilterForm = super.getSolicitudFilterForm(pagina, mav, request);

        if (solicitudFilterForm.isNou()) {
            
            // Ocultar campos que no nos interesan en el listado
            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(Arrays.asList(SolicitudFields.ALL_SOLICITUD_FIELDS));
            hiddenFields.remove(SolicitudFields.PROCEDIMENTCODI);
            hiddenFields.remove(SolicitudFields.PROCEDIMENTNOM);
            hiddenFields.remove(SolicitudFields.ESTATSOLICITUD);
            hiddenFields.remove(SolicitudFields.DATAINICI);
            hiddenFields.remove(SolicitudFields.ENTITATESTATAL);
            hiddenFields.remove(SolicitudFields.ORGANID);
            
            solicitudFilterForm.setHiddenFields(hiddenFields);
            
            // Configurar filtros y agrupaciones
            List<Field<?>> filterList = new ArrayList<Field<?>>();
            filterList.add(PROCEDIMENTCODI);
            filterList.add(PROCEDIMENTNOM);
            filterList.add(ESTATSOLICITUD);
            filterList.add(DATAINICI);
            
            List<Field<?>> groupList = new ArrayList<Field<?>>();
            groupList.add(ESTATSOLICITUD);
            groupList.add(PROCEDIMENTCODI);
            
            solicitudFilterForm.setFilterByFields(filterList);
            solicitudFilterForm.setGroupByFields(groupList);
            
            // BOTONES PERSONALIZADOS - Aquí puedes añadir todos los que quieras para pruebas
            
            // Botón para ver solicitud completa
            solicitudFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
                    IconUtils.ICON_EYE,
                    "veure.complet", 
                    "/admin/solicitudactiva/view/{0}", 
                    AdditionalButtonStyle.PRIMARY));
            
            // Botón de ejemplo para pruebas
            solicitudFilterForm.addAdditionalButton(new AdditionalButton(
                    IconUtils.ICON_FILE, 
                    "Test Button",
                    "javascript:alert('Botón de prueba - puedes añadir más aquí')", 
                    AdditionalButtonStyle.SUCCESS));
            
            // Otro botón de ejemplo
            solicitudFilterForm.addAdditionalButtonForEachItem(new AdditionalButton(
                    "fas fa-flask", 
                    "Probar Acción",
                    "javascript:console.log('Solicitud ID: {0}'); alert('Probando con ID: {0}');", 
                    AdditionalButtonStyle.WARNING));
            
            // Ordenar por fecha de inicio descendente
            solicitudFilterForm.setOrderBy(SolicitudFields.DATAINICI.fullName);
            solicitudFilterForm.setOrderAsc(false);
            
            // Habilitar selección múltiple para acciones por lotes
            solicitudFilterForm.setVisibleMultipleSelection(true);
            
            // Adjuntar código JSP adicional si es necesario
            solicitudFilterForm.setAttachedAdditionalJspCode(true);
        }

        return solicitudFilterForm;
    }
    
    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, SolicitudFilterForm filterForm,
            List<Solicitud> list) throws I18NException {
        super.postList(request, mav, filterForm, list);
        
        // Aquí puedes añadir lógica personalizada después de cargar el listado
        // Por ejemplo, añadir datos adicionales al modelo
        
        mav.addObject("customMessage", "Zona de pruebas - Solicitudes Activas");
        mav.addObject("totalSolicituds", list.size());
        
        // Añadir cualquier otra información que necesites para tus pruebas
    }
}
