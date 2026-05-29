package org.fundaciobit.pinbaladmin.back.controller.operador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pinbaladmin.commons.utils.Constants;
import org.fundaciobit.pinbaladmin.model.entity.Contacte;
import org.fundaciobit.pinbaladmin.model.entity.Event;
import org.fundaciobit.pinbaladmin.model.entity.Organ;
import org.fundaciobit.pinbaladmin.model.entity.Solicitud;
import org.fundaciobit.pinbaladmin.model.fields.ContacteFields;
import org.fundaciobit.pinbaladmin.model.fields.EventFields;
import org.fundaciobit.pinbaladmin.model.fields.OrganFields;
import org.fundaciobit.pinbaladmin.model.fields.SolicitudFields;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controlador para mostrar las solicitudes locales pendientes en 4 estados específicos
 * en una vista tipo kanban
 * 
 * @author pinbaladmin
 */
@Controller
@RequestMapping(value = SolicitudLocalPendentOperadorController.CONTEXTWEB)
public class SolicitudLocalPendentOperadorController extends SolicitudLocalOperadorController {

    public static final String CONTEXTWEB = "/operador/solicitudlocalpendent";

    /**
     * Clase interna para representar una columna del kanban
     */
    public static class KanbanColumn {
        private final long estatId;
        private final List<Solicitud> solicituds;
        
        public KanbanColumn(long estatId, List<Solicitud> solicituds) {
            this.estatId = estatId;
            this.solicituds = solicituds;
        }
        
        public long getEstatId() {
            return estatId;
        }
        
        public List<Solicitud> getSolicituds() {
            return solicituds;
        }
    }

    /**
     * Muestra las solicitudes locales pendientes agrupadas por estado en un formato tipo kanban
     */
    @RequestMapping(value = "/kanban", method = RequestMethod.GET)
    public ModelAndView kanban(HttpServletRequest request) throws I18NException {
        
        ModelAndView mav = new ModelAndView("solicitudLocalPendentKanban");
        
        String currentUser = request.getRemoteUser();
        
        // Definir los estados a mostrar (fácil añadir nuevos estados aquí)
        long[] estats = {
            Constants.SOLI_ESTAT_PENDENT_DISTRIBUCIO,      // 5
            Constants.SOLI_ESTAT_PENDENT_Enviar_Director,  // 11
            Constants.SOLI_ESTAT_PENDENT_ENVIAR_MADRID,    // 19
            Constants.SOLI_ESTAT_ESMENES,                  // 30
            Constants.SOLI_ESTAT_CANVI_PENDENT_REVISAR,     // 33
            Constants.SOLI_ESTAT_PENDENT_AUTORITZAR        // 20
        };
        
        // Crear lista de columnas
        List<KanbanColumn> columns = new ArrayList<>();
        for (long estatId : estats) {
            List<Solicitud> solicituds = getSolicitudsByEstat(estatId);
            columns.add(new KanbanColumn(estatId, solicituds));
        }
        
        // Crear mapa de organid -> nombre del órgano (optimizado con una sola consulta)
        Map<Long, String> organMap = createOrganMap(columns);
        
        // Crear mapa de contacteSolicitantID -> info del contacto (optimizado con una sola consulta)
        Map<Long, ContacteInfo> contacteMap = createContacteMap(columns);
        
        // Crear mapa de solicitudID -> Info de eventos no leídos (optimizado)
        Map<Long, EventBadgeInfo> eventsMap = createEventsMap(columns, currentUser);
        
        mav.addObject("columns", columns);
        mav.addObject("organMap", organMap);
        mav.addObject("contacteMap", contacteMap);
        mav.addObject("eventsMap", eventsMap);
        
        return mav;
    }
    
    /**
     * Clase interna para almacenar información del contacto solicitante
     */
    public static class ContacteInfo {
        private final String nom;
        private final String mail;
        
        public ContacteInfo(String nom, String mail) {
            this.nom = nom;
            this.mail = mail;
        }
        
        public String getNom() {
            return nom;
        }
        
        public String getMail() {
            return mail;
        }
    }
    
    /**
     * Clase interna para almacenar información sobre eventos no leídos
     */
    public static class EventBadgeInfo {
        private final long count;
        private final boolean isMine;
        private final Long lastMessageDate;
        
        public EventBadgeInfo(long count, boolean isMine, Long lastMessageDate) {
            this.count = count;
            this.isMine = isMine;
            this.lastMessageDate = lastMessageDate;
        }
        
        public long getCount() {
            return count;
        }
        
        public boolean isMine() {
            return isMine;
        }
        
        public Long getLastMessageDate() {
            return lastMessageDate;
        }
        
        public String getBadgeColor() {
            return isMine ? "danger" : "warning";
        }
    }
    
    /**
     * Obtiene las solicitudes locales filtradas por estado
     */
    private List<Solicitud> getSolicitudsByEstat(long estatId) throws I18NException {
        try {
            // Condición: solicitudes locales (no estatales) con el estado específico
            Where condition = Where.AND(
                SolicitudFields.ORGANID.isNotNull(),
                ESTATSOLICITUD.equal(estatId)
            );
            
            List<Solicitud> result = solicitudLogicaEjb.select(condition);
            return result != null ? result : new ArrayList<Solicitud>();
        } catch (Exception e) {
            log.error("Error obteniendo solicitudes con estatId: " + estatId, e);
            return new ArrayList<Solicitud>();
        }
    }
    
    /**
     * Crea un mapa de organid -> nombre del órgano para todas las solicitudes.
     * Optimizado: hace una sola consulta a BD para obtener todos los órganos necesarios.
     */
    private Map<Long, String> createOrganMap(List<KanbanColumn> columns) {
        Map<Long, String> organMap = new HashMap<>();
        Set<Long> organIds = new HashSet<>();
        
        // Recopilar todos los organid únicos de todas las columnas
        for (KanbanColumn column : columns) {
            if (column.getSolicituds() != null) {
                for (Solicitud sol : column.getSolicituds()) {
                    if (sol.getOrganid() != null) {
                        organIds.add(sol.getOrganid());
                    }
                }
            }
        }
        
        // Si no hay organids, retornar mapa vacío
        if (organIds.isEmpty()) {
            return organMap;
        }
        
        try {
            // Una sola consulta para obtener todos los órganos necesarios
            List<Long> organIdList = new ArrayList<>(organIds);
            Where condition = OrganFields.ORGANID.in(organIdList);
            List<Organ> organs = organEjb.select(condition);
            
            // Poblar el mapa
            if (organs != null) {
                for (Organ organ : organs) {
                    organMap.put(organ.getOrganid(), organ.getNom());
                }
            }
        } catch (Exception e) {
            log.error("Error obteniendo órganos", e);
        }
        
        return organMap;
    }
    
    /**
     * Crea un mapa de contacteSolicitantID -> información del contacto.
     * Optimizado: hace una sola consulta a BD para obtener todos los contactos necesarios.
     */
    private Map<Long, ContacteInfo> createContacteMap(List<KanbanColumn> columns) {
        Map<Long, ContacteInfo> contacteMap = new HashMap<>();
        Set<Long> contacteIds = new HashSet<>();
        
        // Recopilar todos los contacteSolicitantID únicos de todas las columnas
        for (KanbanColumn column : columns) {
            if (column.getSolicituds() != null) {
                for (Solicitud sol : column.getSolicituds()) {
                    if (sol.getContacteSolicitantID() != null) {
                        contacteIds.add(sol.getContacteSolicitantID());
                    }
                }
            }
        }
        
        // Si no hay contacteIds, retornar mapa vacío
        if (contacteIds.isEmpty()) {
            return contacteMap;
        }
        
        try {
            // Una sola consulta para obtener todos los contactos necesarios
            List<Long> contacteIdList = new ArrayList<>(contacteIds);
            Where condition = ContacteFields.CONTACTEID.in(contacteIdList);
            List<Contacte> contactes = contacteLogicaEjb.select(condition);
            
            // Poblar el mapa
            if (contactes != null) {
                for (Contacte contacte : contactes) {
                    String nom = contacte.getNombrecompleto();
                    if (nom == null || nom.trim().isEmpty()) {
                        // Si no hay nombrecompleto, construirlo con nom + llinatges
                        StringBuilder sb = new StringBuilder();
                        if (contacte.getNom() != null) sb.append(contacte.getNom());
                        if (contacte.getLlinatge1() != null) {
                            if (sb.length() > 0) sb.append(" ");
                            sb.append(contacte.getLlinatge1());
                        }
                        if (contacte.getLlinatge2() != null) {
                            if (sb.length() > 0) sb.append(" ");
                            sb.append(contacte.getLlinatge2());
                        }
                        nom = sb.toString();
                    }
                    contacteMap.put(contacte.getContacteID(), new ContacteInfo(nom, contacte.getMail()));
                }
            }
        } catch (Exception e) {
            log.error("Error obteniendo contactos", e);
        }
        
        return contacteMap;
    }
    
    /**
     * Crea un mapa de solicitudID -> información de eventos no leídos.
     * Optimizado: cuenta los eventos no leídos para cada solicitud.
     */
    private Map<Long, EventBadgeInfo> createEventsMap(List<KanbanColumn> columns, String currentUser) {
        Map<Long, EventBadgeInfo> eventsMap = new HashMap<>();
        
        // Recorrer todas las solicitudes en todas las columnas
        for (KanbanColumn column : columns) {
            if (column.getSolicituds() != null) {
                for (Solicitud sol : column.getSolicituds()) {
                    try {
                        // Contar eventos no leídos para esta solicitud
                        Long count = eventLogicaEjb.count(
                            Where.AND(
                                EventFields.NOLLEGIT.equal(Boolean.TRUE),
                                EventFields.SOLICITUDID.equal(sol.getSolicitudID())
                            )
                        );
                        
                        // Si hay eventos no leídos, añadirlos al mapa
                        if (count != null && count > 0) {
                            boolean isMine = currentUser.equals(sol.getOperador());
                            
                            // Obtener fecha del último mensaje
                            Long lastMessageDate = null;
                            try {
                                OrderBy orderByDate = new OrderBy(EventFields.DATAEVENT, OrderType.DESC);
                                List<Event> lastEvents = eventLogicaEjb.select(
                                    EventFields.SOLICITUDID.equal(sol.getSolicitudID()),
                                    orderByDate
                                );
                                if (lastEvents != null && !lastEvents.isEmpty()) {
                                    Event lastEvent = lastEvents.get(0);
                                    if (lastEvent.getDataEvent() != null) {
                                        lastMessageDate = lastEvent.getDataEvent().getTime();
                                    }
                                }
                            } catch (Exception ex) {
                                log.warn("Error obteniendo fecha último mensaje para solicitud " + sol.getSolicitudID(), ex);
                            }
                            
                            eventsMap.put(sol.getSolicitudID(), new EventBadgeInfo(count, isMine, lastMessageDate));
                        }
                    } catch (Exception e) {
                        log.error("Error contando eventos para solicitud " + sol.getSolicitudID(), e);
                    }
                }
            }
        }
        
        return eventsMap;
    }
}
