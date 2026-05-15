package org.fundaciobit.pinbaladmin.back.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.GroupByItem;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pinbaladmin.back.controller.webdb.SolicitudController;
import org.fundaciobit.pinbaladmin.back.form.webdb.SolicitudFilterForm;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.ejb.OperadorService;
import org.fundaciobit.pinbaladmin.logic.ContacteLogicaService;
import org.fundaciobit.pinbaladmin.logic.InfoMadridLogicaService;
import org.fundaciobit.pinbaladmin.logic.OrganLogicaService;
import org.fundaciobit.pinbaladmin.logic.SolicitudLogicaService;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.entity.Operador;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.fundaciobit.pinbaladmin.persistence.SolicitudJPA;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    // Ejb de operador.
    @EJB(mappedName = OperadorService.JNDI_NAME)
    protected OperadorService operadorEjb;

    // Ejb de organ.
    @EJB(mappedName = OrganLogicaService.JNDI_NAME)
    protected OrganLogicaService organLogicaEjb;

    // Ejb de contacte.
    @EJB(mappedName = ContacteLogicaService.JNDI_NAME)
    protected ContacteLogicaService contacteLogicaEjb;

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
    public List<StringKeyValue> getReferenceListForEstatSolicitud(HttpServletRequest request,
            ModelAndView mav, Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (long estat : Constants.ESTATS_SOLI) {
            String key = String.valueOf(estat);
            __tmp.add(new StringKeyValue(key, I18NUtils.tradueix("solicitud.estat." + key)));
        }

        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForOperador(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        List<Operador> operadores = operadorEjb.select();
        for (Operador operador : operadores) {
            __tmp.add(new StringKeyValue(operador.getUsername(), operador.getNom()));
        }

        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForOrganid(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        List<Organ> organids = organLogicaEjb.select();
        for (Organ organ : organids) {
            __tmp.add(new StringKeyValue(organ.getOrganid() + "", organ.getNom()));
        }
        return __tmp;
    }

    @Override
    public SolicitudFilterForm getSolicitudFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {

        SolicitudFilterForm solicitudFilterForm = super.getSolicitudFilterForm(pagina, mav, request);

        if (solicitudFilterForm.isNou()) {
            solicitudFilterForm.setDeleteSelectedButtonVisible(false);
            solicitudFilterForm.setAddButtonVisible(false);
            // solicitudFilterForm.setEditButtonVisible(false);
            solicitudFilterForm.setDeleteButtonVisible(false);

            // Ocultar campos que no nos interesan en el listado
            Set<Field<?>> hiddenFields = new HashSet<Field<?>>(Arrays.asList(SolicitudFields.ALL_SOLICITUD_FIELDS));
            hiddenFields.remove(SolicitudFields.PROCEDIMENTCODI);
            hiddenFields.remove(SolicitudFields.PROCEDIMENTNOM);
            hiddenFields.remove(SolicitudFields.ESTATSOLICITUD);
            hiddenFields.remove(SolicitudFields.DATAINICI);
            hiddenFields.remove(SolicitudFields.ORGANID);

            solicitudFilterForm.setHiddenFields(hiddenFields);

            // BOTONES PERSONALIZADOS - Aquí puedes añadir todos los que quieras para
            // pruebas

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

            // Botón de migración para rellenar solicitudFusionadaID desde notas históricas
            solicitudFilterForm.addAdditionalButton(new AdditionalButton(
                    "fas fa-database",
                    "Migrar Fusiones Históricas",
                    "javascript:if(confirm('ATENCIÓN: Esta es una operación de migración masiva.\\n\\n" +
                            "Se rellenará el campo solicitudFusionadaID usando información histórica del campo notas.\\n\\n"
                            +
                            "¿Desea continuar?')) { window.location.href='/pinbaladmin" + CONTEXTWEB
                            + "/migrarFusionesHistoricas'; }",
                    AdditionalButtonStyle.DANGER));

            // Botón para migrar datos de titulares a contactos
            solicitudFilterForm.addAdditionalButton(new AdditionalButton(
                    "fas fa-user",
                    I18NUtils.tradueix("solicitud.contacteTitularID") + ": Migrar Contactos",
                    "javascript:if(confirm('ATENCIÓN: Esta operación migrará los datos del titular de todas las solicitudes a contactos.\\n\\n"
                            +
                            "- Se crearán contactos con los datos del titular (NIF, nombre, apellidos, email)\\n" +
                            "- Si el contacto ya existe, no se creará duplicado\\n" +
                            "- Se asociará el contacto al campo contacteTitularID de la solicitud\\n\\n" +
                            "¿Desea continuar?')) { window.location.href='/pinbaladmin" + CONTEXTWEB
                            + "/migrarContactosTitulares'; }",
                    AdditionalButtonStyle.INFO));
            
            // Botón para migrar datos de responsables a contactos
            solicitudFilterForm.addAdditionalButton(new AdditionalButton(
                    "fas fa-user-tie",
                    I18NUtils.tradueix("solicitud.contacteResponsableID") + ": Migrar Contactos",
                    "javascript:if(confirm('ATENCIÓN: Esta operación migrará los datos del responsable de todas las solicitudes a contactos.\\n\\n"
                            +
                            "- Se crearán contactos con los datos del responsable (nombre, email)\\n" +
                            "- Si el contacto ya existe, no se creará duplicado\\n" +
                            "- Se asociará el contacto al campo contacteResponsableID de la solicitud\\n\\n" +
                            "¿Desea continuar?')) { window.location.href='/pinbaladmin" + CONTEXTWEB
                            + "/migrarContactosResponsables'; }",
                    AdditionalButtonStyle.INFO));
            
            // Botón para migrar datos de personas de contacto a contactos
            solicitudFilterForm.addAdditionalButton(new AdditionalButton(
                    "fas fa-user-circle",
                    I18NUtils.tradueix("solicitud.contactePersonaID") + ": Migrar Contactos",
                    "javascript:if(confirm('ATENCIÓN: Esta operación migrará los datos de la persona de contacto de todas las solicitudes a contactos.\\n\\n"
                            +
                            "- Se crearán contactos con los datos de la persona de contacto (nombre, email)\\n" +
                            "- Si el contacto ya existe, no se creará duplicado\\n" +
                            "- Se asociará el contacto al campo contactePersonaID de la solicitud\\n\\n" +
                            "¿Desea continuar?')) { window.location.href='/pinbaladmin" + CONTEXTWEB
                            + "/migrarContactosPersonas'; }",
                    AdditionalButtonStyle.INFO));

            // Ordenar por fecha de inicio descendente
            solicitudFilterForm.setOrderBy(SolicitudFields.DATAINICI.fullName);
            solicitudFilterForm.setOrderAsc(false);

            // Habilitar selección múltiple para acciones por lotes
            solicitudFilterForm.setVisibleMultipleSelection(true);

            // Adjuntar código JSP adicional si es necesario
            solicitudFilterForm.setAttachedAdditionalJspCode(true);

            // Quitar todos los campos de filtro y agrupacion.
            solicitudFilterForm.setFilterByFields(null);
            solicitudFilterForm.setGroupByFields(null);
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

    /**
     * Método de migración temporal para rellenar el campo solicitudFusionadaID
     * usando información histórica del campo notas.
     * 
     * Este método parsea las notas de solicitudes que contienen información de
     * fusiones
     * en formato "Procediment fusionat de : 50074, 50077" y crea relaciones
     * DIRECTAS
     * entre las solicitudes originales y la solicitud que las absorbió.
     * 
     * IMPORTANTE: Solo crea relaciones directas, no recursivas. El método
     * obtenerSolicitudFinalActiva() ya resuelve cadenas de fusiones
     * automáticamente.
     * 
     * @param request
     * @param response
     * @return Redirección al listado con mensaje de resultado
     * @throws I18NException
     */
    /**
     * Método de migración para rellenar el campo solicitudFusionadaID desde las
     * notas históricas.
     * 
     * CONTEXTO:
     * - Antes de implementar el campo solicitudFusionadaID, la información de
     * fusión se guardaba en las notas
     * - Ejemplo de nota: "Procediment fusionat de : 50074, 50630, 50077"
     * - Esto significa que las solicitudes 50074, 50630 y 50077 fueron fusionadas
     * en la solicitud actual
     * 
     * OBJETIVO:
     * - Todas las solicitudes en estado FUSIONADA (Constants.SOLI_ESTAT_FUSIONADA =
     * -5)
     * deben tener el campo solicitudFusionadaID rellenado
     * - Este método parsea las notas y rellena automáticamente ese campo
     * 
     * PROCESO:
     * 1. Busca solicitudes que tienen "Procediment fusionat de" en sus notas
     * 2. Parsea los IDs de las solicitudes originales fusionadas
     * 3. Verifica que no haya conflictos ni autoreferencias
     * 4. Muestra vista previa de qué se actualizaría
     * 5. (Cuando se descomente) Actualiza el campo solicitudFusionadaID de las
     * solicitudes originales
     */
    @RequestMapping(value = "/migrarFusionesHistoricas", method = RequestMethod.GET)
    public String migrarFusionesHistoricas(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {

        log.info("=== INICIO MIGRACIÓN FUSIONES HISTÓRICAS ===");

        try {
            // =====================================================================
            // CONFIGURACIÓN Y BÚSQUEDA INICIAL
            // =====================================================================

            // Patrón regex para detectar líneas de fusión en las notas
            // Captura: "Procediment fusionat de : 50074, 50630, 50077"
            // El grupo (1) captura la lista de IDs separados por comas
            Pattern pattern = Pattern.compile("Procediment fusionat de\\s*:\\s*([0-9,\\s]+)", Pattern.CASE_INSENSITIVE);

            // Buscar todas las solicitudes que contengan información de fusión en las notas
            // Estas son las solicitudes DESTINO (las que absorbieron otras solicitudes)
            Where whereFusionada = Where.AND(
                    SolicitudFields.NOTES.isNotNull(),
                    SolicitudFields.NOTES.like("%Procediment fusionat de%"));

            List<Solicitud> solicitudesConFusiones = solicitudLogicaEjb.select(whereFusionada);

            log.info("Encontradas " + solicitudesConFusiones.size()
                    + " solicitudes con información de fusiones en notas");

            if (solicitudesConFusiones.isEmpty()) {
                HtmlUtils.saveMessageInfo(request,
                        "No se encontraron solicitudes con información de fusiones históricas.");
                return "redirect:" + CONTEXTWEB + "/list";
            }

            // =====================================================================
            // ESTRUCTURAS DE DATOS PARA EL PROCESAMIENTO
            // =====================================================================

            // Mapa para almacenar las relaciones encontradas y detectar conflictos
            // Clave: ID de la solicitud ORIGINAL (la que fue fusionada)
            // Valor: ID de la solicitud DESTINO (la que absorbió a la original)
            // Ejemplo: Si 50074 fue fusionada en 60000, el mapa tendrá: 50074 -> 60000
            Map<Long, Long> relacionesEncontradas = new HashMap<>();

            // Lista para almacenar información detallada de lo que se actualizaría
            // Esto nos permite mostrar una vista previa antes de hacer cambios
            List<String> vistaPreviaActualizaciones = new ArrayList<>();

            // Contadores para el informe final
            int totalSolicitudesProcesadas = 0; // Solicitudes destino analizadas
            int totalRelacionesEncontradas = 0; // Total de IDs encontrados en las notas
            int totalActualizadas = 0; // Solicitudes que SE ACTUALIZARÍAN
            int totalConflictos = 0; // IDs que aparecen en múltiples destinos
            int totalIdsInexistentes = 0; // IDs que no existen en BD
            int totalAutoreferencias = 0; // IDs que se referencian a sí mismos
            int totalYaRellenadas = 0; // Solicitudes que ya tienen el campo rellenado

            // =====================================================================
            // FASE 1: RECOLECCIÓN Y ANÁLISIS DE RELACIONES
            // =====================================================================
            // En esta fase NO se modifica nada en la base de datos
            // Solo se leen y validan las relaciones encontradas en las notas
            log.info("=== FASE 1: Recolectando relaciones ===");

            for (Solicitud solicitud : solicitudesConFusiones) {
                totalSolicitudesProcesadas++;

                // ID de la solicitud DESTINO (la que tiene la nota "Procediment fusionat de")
                Long destinoId = solicitud.getSolicitudID();
                String notas = solicitud.getNotes();

                // Saltar si no hay notas
                if (notas == null || notas.trim().isEmpty()) {
                    continue;
                }

                // Aplicar el patrón regex para buscar TODAS las líneas de fusión
                // Una solicitud puede tener múltiples líneas si ha habido varias fusiones
                Matcher matcher = pattern.matcher(notas);

                while (matcher.find()) {
                    // Extraer la parte de los IDs: "50074, 50630, 50077"
                    String idsStr = matcher.group(1);

                    // Separar por comas: puede haber múltiples IDs en una sola línea
                    String[] ids = idsStr.split(",");

                    for (String idStr : ids) {
                        idStr = idStr.trim(); // Limpiar espacios

                        if (idStr.isEmpty()) {
                            continue;
                        }

                        try {
                            Long originalId = Long.parseLong(idStr);
                            totalRelacionesEncontradas++;

                            // VALIDACIÓN 1: Verificar autoreferencia
                            // No tiene sentido que una solicitud se fusione en sí misma
                            if (originalId.equals(destinoId)) {
                                log.warn("Autoreferencia detectada: solicitud " + originalId
                                        + " se fusiona en sí misma. Ignorando.");
                                totalAutoreferencias++;
                                continue;
                            }

                            // VALIDACIÓN 2: Detectar y resolver conflictos
                            // Si un ID original ya está en el mapa con un destino diferente,
                            // significa que aparece fusionada en múltiples solicitudes.
                            // SOLUCIÓN: Mantener siempre el ID MÁS GRANDE (más reciente)
                            if (relacionesEncontradas.containsKey(originalId)) {
                                Long destinoAnterior = relacionesEncontradas.get(originalId);
                                if (!destinoAnterior.equals(destinoId)) {
                                    // CONFLICTO: La solicitud aparece en múltiples destinos
                                    // Priorizar el ID más grande (fusión más reciente)
                                    Long destinoFinal = Math.max(destinoAnterior, destinoId);
                                    Long destinoDescartado = Math.min(destinoAnterior, destinoId);

                                    log.warn("CONFLICTO RESUELTO AUTOMÁTICAMENTE: Solicitud " + originalId +
                                            " aparece fusionada en múltiples destinos: " +
                                            destinoAnterior + " y " + destinoId +
                                            ". Se mantiene el más reciente (ID mayor): " + destinoFinal +
                                            " (se descarta: " + destinoDescartado + ")");
                                    totalConflictos++;

                                    // Actualizar el mapa con el ID más grande (más reciente)
                                    relacionesEncontradas.put(originalId, destinoFinal);
                                    continue; // Ya procesado, pasar al siguiente ID
                                }
                                // Si es el mismo destino, no hacer nada (ya está en el mapa)
                            } else {
                                // Relación nueva: añadir al mapa
                                // Ejemplo: solicitud 50074 fue fusionada en solicitud 60000
                                relacionesEncontradas.put(originalId, destinoId);
                            }

                        } catch (NumberFormatException e) {
                            log.warn(
                                    "ID no válido encontrado en notas de solicitud " + destinoId + ": '" + idStr + "'");
                        }
                    }
                }
            }

            log.info("Fase 1 completada. Relaciones válidas encontradas: " + relacionesEncontradas.size());

            // =====================================================================
            // FASE 2: VALIDACIÓN Y VISTA PREVIA DE ACTUALIZACIONES
            // =====================================================================
            // En esta fase verificamos qué solicitudes se actualizarían
            // y mostramos una vista previa detallada ANTES de hacer cambios
            log.info("=== FASE 2: Validando y preparando actualizaciones ===");
            log.info("*** MODO VISTA PREVIA: No se harán cambios reales en la BD ***");

            for (Map.Entry<Long, Long> relacion : relacionesEncontradas.entrySet()) {
                Long originalId = relacion.getKey(); // ID de la solicitud que fue fusionada
                Long destinoId = relacion.getValue(); // ID de la solicitud final activa

                try {
                    // ---------------------------------------------------------------
                    // VERIFICACIÓN 1: ¿Existe la solicitud original en la BD?
                    // ---------------------------------------------------------------
                    SolicitudJPA solicitudOriginal = solicitudLogicaEjb.findByPrimaryKey(originalId);

                    if (solicitudOriginal == null) {
                        log.warn("Solicitud original " + originalId + " no existe en la base de datos. Ignorando.");
                        totalIdsInexistentes++;
                        continue;
                    }

                    // ---------------------------------------------------------------
                    // VERIFICACIÓN 2: ¿Ya tiene el campo rellenado?
                    // ---------------------------------------------------------------
                    // IMPORTANTE: No sobrescribimos datos existentes
                    // Si ya está rellenado, respetamos el valor actual
                    if (solicitudOriginal.getSolicitudFusionadaID() != null) {
                        log.info("Solicitud " + originalId + " ya tiene solicitudFusionadaID = " +
                                solicitudOriginal.getSolicitudFusionadaID() + ". No se sobrescribe.");
                        totalYaRellenadas++;
                        continue;
                    }

                    // ---------------------------------------------------------------
                    // VERIFICACIÓN 3: ¿Existe la solicitud destino en la BD?
                    // ---------------------------------------------------------------
                    SolicitudJPA solicitudDestino = solicitudLogicaEjb.findByPrimaryKey(destinoId);

                    if (solicitudDestino == null) {
                        log.warn("Solicitud destino " + destinoId
                                + " no existe en la base de datos. Ignorando relación " +
                                originalId + " -> " + destinoId);
                        totalIdsInexistentes++;
                        continue;
                    }

                    // ---------------------------------------------------------------
                    // VERIFICACIÓN 4: ¿Está en estado FUSIONADA?
                    // ---------------------------------------------------------------
                    // El objetivo es que TODAS las solicitudes fusionadas tengan el campo rellenado
                    Long estadoOriginal = solicitudOriginal.getEstatSolicitud();
                    boolean esFusionada = estadoOriginal != null
                            && estadoOriginal.equals(Constants.SOLI_ESTAT_FUSIONADA);

                    // ---------------------------------------------------------------
                    // PREPARAR INFORMACIÓN PARA VISTA PREVIA
                    // ---------------------------------------------------------------
                    String estadoTexto = esFusionada ? "[FUSIONADA=-5]" : "[Estado=" + estadoOriginal + "]";
                    String avisoEstado = esFusionada ? "" : " ⚠️ NO está en estado FUSIONADA";

                    String infoDetallada = String.format(
                            "  %d %s → %d%s",
                            originalId,
                            estadoTexto,
                            destinoId,
                            avisoEstado);

                    vistaPreviaActualizaciones.add(infoDetallada);

                    // ---------------------------------------------------------------
                    // ACTUALIZACIÓN (ACTUALMENTE COMENTADA PARA VISTA PREVIA)
                    // ---------------------------------------------------------------
                    // Cuando se descomente, esto hará la actualización real en BD:
                    solicitudOriginal.setSolicitudFusionadaID(destinoId);
                    solicitudLogicaEjb.update(solicitudOriginal);

                    log.info("✓ SE ACTUALIZARÍA: solicitud " + originalId + " " + estadoTexto + " -> " + destinoId
                            + avisoEstado);
                    totalActualizadas++;

                } catch (Exception e) {
                    log.error("Error validando solicitud " + originalId + " -> " + destinoId + ": " + e.getMessage(),
                            e);
                }
            }

            // =====================================================================
            // VERIFICACIÓN ADICIONAL: Estado actual de solicitudes fusionadas
            // =====================================================================
            // Verificar cuántas solicitudes en estado FUSIONADA no tienen el campo
            // rellenado
            log.info("=== VERIFICACIÓN: Estado de solicitudes fusionadas ===");

            Where whereFusionadasSinRelacion = Where.AND(
                    SolicitudFields.ESTATSOLICITUD.equal(Constants.SOLI_ESTAT_FUSIONADA),
                    SolicitudFields.SOLICITUDFUSIONADAID.isNull());

            List<Solicitud> fusionadasSinRelacion = solicitudLogicaEjb.select(whereFusionadasSinRelacion);

            log.info("Total de solicitudes en estado FUSIONADA sin solicitudFusionadaID: "
                    + fusionadasSinRelacion.size());
            log.info("Después de esta migración quedarían: " + (fusionadasSinRelacion.size() - totalActualizadas));

            // =====================================================================
            // INFORME FINAL Y VISTA PREVIA
            // =====================================================================
            log.info("=== FIN MIGRACIÓN FUSIONES HISTÓRICAS (VISTA PREVIA) ===");
            log.info("Solicitudes procesadas: " + totalSolicitudesProcesadas);
            log.info("Relaciones encontradas: " + totalRelacionesEncontradas);
            log.info("Relaciones válidas: " + relacionesEncontradas.size());
            log.info("Solicitudes que SE ACTUALIZARÍAN: " + totalActualizadas);
            log.info("Ya rellenadas (no se tocarían): " + totalYaRellenadas);
            log.info("Conflictos detectados: " + totalConflictos);
            log.info("Autoreferencias ignoradas: " + totalAutoreferencias);
            log.info("IDs inexistentes: " + totalIdsInexistentes);
            log.info("");
            log.info("=== VISTA PREVIA DE ACTUALIZACIONES ===");
            if (vistaPreviaActualizaciones.isEmpty()) {
                log.info("No hay actualizaciones pendientes.");
            } else {
                log.info("Se actualizarían " + vistaPreviaActualizaciones.size() + " solicitudes:");
                for (String info : vistaPreviaActualizaciones) {
                    log.info(info);
                }
            }
            log.info("");
            log.info("=== VERIFICACIÓN OBJETIVO ===");
            log.info("Solicitudes FUSIONADAS sin relación ANTES: " + fusionadasSinRelacion.size());
            log.info("Solicitudes FUSIONADAS sin relación DESPUÉS: "
                    + (fusionadasSinRelacion.size() - totalActualizadas));

            // Mensaje para el usuario con vista previa
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("VISTA PREVIA - No se han hecho cambios reales\\n\\n");
            mensaje.append("=== RESUMEN ===\\n");
            mensaje.append(String.format("Solicitudes procesadas: %d\\n", totalSolicitudesProcesadas));
            mensaje.append(String.format("Relaciones encontradas: %d\\n", totalRelacionesEncontradas));
            mensaje.append(String.format("SE ACTUALIZARÍAN: %d\\n", totalActualizadas));
            mensaje.append(String.format("Ya rellenadas: %d\\n", totalYaRellenadas));
            mensaje.append(String.format("Conflictos: %d\\n", totalConflictos));
            mensaje.append(String.format("Autoreferencias: %d\\n", totalAutoreferencias));
            mensaje.append(String.format("IDs inexistentes: %d\\n\\n", totalIdsInexistentes));

            mensaje.append("=== OBJETIVO: Solicitudes FUSIONADAS con campo rellenado ===\\n");
            mensaje.append(String.format("Sin rellenar ANTES: %d\\n", fusionadasSinRelacion.size()));
            mensaje.append(
                    String.format("Sin rellenar DESPUÉS: %d\\n\\n", fusionadasSinRelacion.size() - totalActualizadas));

            if (!vistaPreviaActualizaciones.isEmpty()) {
                mensaje.append("=== VISTA PREVIA (primeras 20) ===\\n");
                int limite = Math.min(20, vistaPreviaActualizaciones.size());
                for (int i = 0; i < limite; i++) {
                    mensaje.append(vistaPreviaActualizaciones.get(i)).append("\\n");
                }
                if (vistaPreviaActualizaciones.size() > 20) {
                    mensaje.append(String.format("... y %d más\\n", vistaPreviaActualizaciones.size() - 20));
                }
                mensaje.append("\\nVer log para detalles completos");
            }

            HtmlUtils.saveMessageSuccess(request, mensaje.toString());

        } catch (Exception e) {
            log.error("Error durante la migración de fusiones históricas: " + e.getMessage(), e);
            HtmlUtils.saveMessageError(request, "Error durante la migración: " + e.getMessage());
        }

        return "redirect:" + CONTEXTWEB + "/list";
    }

    /**
     * Enum para los diferentes tipos de contacto en una solicitud
     */
    private enum TipoContacto {
        TITULAR, RESPONSABLE, PERSONA_CONTACTO
    }
    
    /**
     * Clase para encapsular los datos de un contacto
     */
    private static class DatosContacto {
        String nif;
        String nom;
        String llinatge1;
        String llinatge2;
        String carrec;
        String telefon;
        String mail;
        String username;
        
        public DatosContacto(String nif, String nom, String llinatge1, String llinatge2, 
                             String carrec, String telefon, String mail, String username) {
            this.nif = nif;
            this.nom = nom;
            this.llinatge1 = llinatge1;
            this.llinatge2 = llinatge2;
            this.carrec = carrec;
            this.telefon = telefon;
            this.mail = mail;
            this.username = username;
        }
    }
    
    /**
     * Extrae los datos del contacto de una solicitud según el tipo de contacto
     * 
     * @param tipoContacto Tipo de contacto a extraer (TITULAR, RESPONSABLE, PERSONA_CONTACTO)
     * @param solicitud Solicitud de donde extraer los datos
     * @return DatosContacto con los campos correspondientes según el tipo
     */
    private DatosContacto extraerDatosContacto(TipoContacto tipoContacto, Solicitud solicitud) {
        String nif = null;
        String nom = null;
        String llinatge1 = null;
        String llinatge2 = null;
        String carrec = null;
        String telefon = null;
        String mail = null;
        String username = null;
        
        switch (tipoContacto) {
            case TITULAR:
                // El titular tiene todos los datos básicos
                nif = solicitud.getTitularFirmaNif();
                nom = solicitud.getTitularFirmaNom();
                llinatge1 = solicitud.getTitularFirmaLlinatges();
                llinatge2 = null; // Los titulares solo tienen un campo de apellidos
                carrec = null;
                telefon = null;
                mail = solicitud.getTitularFirmaEmail();
                username = null;
                break;
                
            case RESPONSABLE:
                // El responsable solo tiene nombre y email en la solicitud
                nif = null; // No hay NIF para responsable
                nom = solicitud.getResponsableProcNom();
                llinatge1 = null;
                llinatge2 = null;
                carrec = null;
                telefon = null;
                mail = solicitud.getResponsableProcEmail();
                username = null;
                break;
                
            case PERSONA_CONTACTO:
                // La persona de contacto solo tiene nombre y email en la solicitud
                nif = null; // No hay NIF para persona de contacto
                nom = solicitud.getPersonaContacte();
                llinatge1 = null;
                llinatge2 = null;
                carrec = null;
                telefon = null;
                mail = solicitud.getPersonaContacteEmail();
                username = null;
                break;
                
            default:
                break;
        }
        
        return new DatosContacto(nif, nom, llinatge1, llinatge2, carrec, telefon, mail, username);
    }

    /**
     * Migra los datos del titular de todas las solicitudes a contactos.
     * 
     * @param request
     * @param response
     * @return Redirección al listado con mensaje de resultado
     * @throws I18NException
     */
    @RequestMapping(value = "/migrarContactosTitulares", method = RequestMethod.GET)
    public String migrarContactosTitulares(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {
        return migrarContactos(request, response, TipoContacto.TITULAR);
    }
    
    /**
     * Migra los datos del responsable de todas las solicitudes a contactos.
     * 
     * @param request
     * @param response
     * @return Redirección al listado con mensaje de resultado
     * @throws I18NException
     */
    @RequestMapping(value = "/migrarContactosResponsables", method = RequestMethod.GET)
    public String migrarContactosResponsables(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {
        return migrarContactos(request, response, TipoContacto.RESPONSABLE);
    }
    
    /**
     * Migra los datos de la persona de contacto de todas las solicitudes a contactos.
     * 
     * @param request
     * @param response
     * @return Redirección al listado con mensaje de resultado
     * @throws I18NException
     */
    @RequestMapping(value = "/migrarContactosPersonas", method = RequestMethod.GET)
    public String migrarContactosPersonas(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {
        return migrarContactos(request, response, TipoContacto.PERSONA_CONTACTO);
    }
    
    /**
     * Método genérico para migrar contactos de cualquier tipo.
     * 
     * Para cada solicitud:
     * - Busca o crea un contacto con los datos correspondientes según el tipo
     * - Si el contacto ya existe (mismos datos exactos), lo reutiliza
     * - Asocia el contacto al campo correspondiente de la solicitud
     * 
     * @param request
     * @param response
     * @param tipoContacto Tipo de contacto a migrar (TITULAR, RESPONSABLE, PERSONA_CONTACTO)
     * @return Redirección al listado con mensaje de resultado
     * @throws I18NException
     */
    private String migrarContactos(HttpServletRequest request, HttpServletResponse response, TipoContacto tipoContacto)
            throws I18NException {

        String tipoNombre = getTipoContactoNombre(tipoContacto);
        log.info("=== INICIO MIGRACIÓN CONTACTOS " + tipoNombre.toUpperCase() + " ===");

        int procesadas = 0;
        int actualizadas = 0;
        int errores = 0;
        int sinNif = 0;

        try {
            // Obtener todas las solicitudes que tienen datos de titular
            Where where = whereTipoContacto(tipoContacto);

            List<Solicitud> solicitudes = solicitudLogicaEjb.select(where);
            log.info("Encontradas " + solicitudes.size() + " solicitudes de tipo " + tipoNombre + " sin contacto asociado");

            for (Solicitud solicitud : solicitudes) {
                procesadas++;

                try {
                    // Extraer los datos del contacto según el tipo
                    DatosContacto datos = extraerDatosContacto(tipoContacto, solicitud);

                    // Buscar o crear contacto usando el método genérico del EJB
                    // Pasa todos los campos de Contacte, los que sean null se tratarán correctamente
                    Contacte contacte = contacteLogicaEjb.buscarOCrearContacte(
                            datos.nif,
                            datos.nom,
                            datos.llinatge1,
                            datos.llinatge2,
                            datos.carrec,
                            datos.telefon,
                            datos.mail,
                            datos.username);

                    if (contacte != null && contacte.getContacteID() > 0) {
                        // Asociar el contacto a la solicitud
                        actualizarContactoSolicitud(tipoContacto, solicitud, contacte);
                        actualizadas++;

                        if (actualizadas % 100 == 0) {
                            log.info("Procesadas " + actualizadas + " de " + solicitudes.size() + " solicitudes");
                        }
                    } else {
                        errores++;
                        log.warn("No se pudo crear contacto para solicitud ID: " + solicitud.getSolicitudID());
                    }

                } catch (Exception e) {
                    errores++;
                    log.error("Error procesando solicitud ID " + solicitud.getSolicitudID() + ": " + e.getMessage(), e);
                }
            }

            // Preparar mensaje de resultado
            String mensaje = String.format(
                    "Migración de contactos (%s) completada.%n%n" +
                            "Total procesadas: %d%n" +
                            "Solicitudes actualizadas: %d%n" +
                            "Sin datos: %d%n" +
                            "Errores: %d%n%n" +
                            "Ver log del servidor para más detalles.",
                    tipoNombre, procesadas, actualizadas, sinNif, errores);

            if (errores > 0) {
                HtmlUtils.saveMessageWarning(request, mensaje);
            } else {
                HtmlUtils.saveMessageSuccess(request, mensaje);
            }

            log.info("=== FIN MIGRACIÓN CONTACTOS " + tipoNombre.toUpperCase() + " ===");
            log.info("Total procesadas: " + procesadas);
            log.info("Total actualizadas: " + actualizadas);
            log.info("Sin datos: " + sinNif);
            log.info("Total errores: " + errores);

        } catch (Exception e) {
            log.error("Error durante la migración de contactos " + tipoNombre + ": " + e.getMessage(), e);
            HtmlUtils.saveMessageError(request, "Error durante la migración: " + e.getMessage());
        }

        return "redirect:" + CONTEXTWEB + "/list";
    }
    
    /**
     * Obtiene el nombre del tipo de contacto para mensajes y logs
     * 
     * @param tipoContacto Tipo de contacto
     * @return Nombre del tipo de contacto
     */
    private String getTipoContactoNombre(TipoContacto tipoContacto) {
        switch (tipoContacto) {
            case TITULAR:
                return "Titulares";
            case RESPONSABLE:
                return "Responsables";
            case PERSONA_CONTACTO:
                return "Personas de Contacto";
            default:
                return "Desconocido";
        }
    }

    /**
     * Construye el WHERE para buscar solicitudes según el tipo de contacto
     * 
     * @param tipoContacto Tipo de contacto
     * @return Condición WHERE que busca solicitudes sin contacto asociado para ese tipo
     */
    private Where whereTipoContacto(TipoContacto tipoContacto) {
        Where where = null;
        
        switch (tipoContacto) {
            case TITULAR:
                where = Where.AND(
                    SolicitudFields.TITULARFIRMANIF.isNotNull()
                );
                break;
                
            case RESPONSABLE:
                where = Where.AND(
                    SolicitudFields.RESPONSABLEPROCNOM.isNotNull()
                );
                break;
                
            case PERSONA_CONTACTO:
                where = Where.AND(
                    SolicitudFields.PERSONACONTACTE.isNotNull()
                );
                break;
                
            default:
                break;
        }
        
        return where;
    }

    /**
     * Actualiza el campo de contacto correspondiente en la solicitud
     * 
     * @param tipoContacto Tipo de contacto
     * @param soli Solicitud a actualizar
     * @param contacto Contacto a asociar
     * @throws I18NException Si hay error al actualizar
     */
    private void actualizarContactoSolicitud(TipoContacto tipoContacto, Solicitud soli, Contacte contacto) 
            throws I18NException {
        switch (tipoContacto) {
            case TITULAR:
                soli.setContacteTitularID(contacto.getContacteID());
                break;
                
            case RESPONSABLE:
                soli.setContacteResponsableID(contacto.getContacteID());
                break;
                
            case PERSONA_CONTACTO:
                soli.setContactePersonaID(contacto.getContacteID());
                break;
                
            default:
                break;
        }
        solicitudLogicaEjb.update(soli);
    }
}