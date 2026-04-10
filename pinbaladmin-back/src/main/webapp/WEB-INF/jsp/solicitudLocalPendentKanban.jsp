<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<style>
.kanban-container {
    display: flex;
    gap: 12px;
    padding: 15px;
    overflow-x: auto;
    width: 100%;
}

.kanban-column {
    flex: 1 1 0;
    min-width: 220px;
    background-color: #f5f5f5;
    border-radius: 8px;
    padding: 10px;
}

.kanban-column-header {
    background-color: #007bff;
    color: white;
    padding: 8px;
    border-radius: 5px;
    margin-bottom: 12px;
    text-align: center;
    font-weight: bold;
    font-size: 0.9em;
}

.kanban-card {
    background-color: white;
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 10px;
    margin-bottom: 8px;
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    cursor: pointer;
    transition: all 0.2s;
    position: relative;
}

.kanban-card:hover {
    box-shadow: 0 4px 8px rgba(0,0,0,0.15);
    transform: translateY(-2px);
}

.kanban-card-title {
    font-weight: bold;
    margin-bottom: 8px;
    color: #007bff;
    font-size: 0.9em;
    word-break: break-word;
}

.kanban-card-info {
    font-size: 0.8em;
    color: #666;
    margin-bottom: 4px;
    line-height: 1.3;
    word-wrap: break-word;
}

.kanban-badge {
    display: inline-block;
    padding: 3px 8px;
    border-radius: 3px;
    font-size: 0.8em;
    margin-top: 5px;
}

.badge-count {
    background-color: #17a2b8;
    color: white;
    padding: 3px 10px;
    border-radius: 12px;
    font-size: 0.9em;
    margin-left: 8px;
}

.empty-column {
    text-align: center;
    color: #999;
    padding: 20px;
    font-style: italic;
}

.event-badge {
    position: absolute;
    top: -8px;
    right: -8px;
    min-width: 22px;
    height: 22px;
    padding: 2px 6px;
    border-radius: 50%;
    font-size: 0.75em;
    font-weight: bold;
    color: white;
    display: flex;
    align-items: center;
    justify-content: center;
    box-shadow: 0 2px 4px rgba(0,0,0,0.3);
    z-index: 10;
}

.event-badge.badge-danger {
    background-color: #dc3545;
}

.event-badge.badge-warning {
    background-color: #ffc107;
    color: #333;
}

.sort-controls {
    background-color: #f8f9fa;
    border: 1px solid #dee2e6;
    border-radius: 5px;
    padding: 15px;
    margin-bottom: 20px;
    display: flex;
    align-items: center;
    gap: 15px;
    flex-wrap: wrap;
}

.sort-controls label {
    font-weight: 600;
    margin-bottom: 0;
}

.sort-controls select {
    padding: 5px 10px;
    border: 1px solid #ced4da;
    border-radius: 4px;
    background-color: white;
}
</style>

<div class="container-fluid">
    <h3 style="margin-bottom: 20px;">
        <fmt:message key="solicitud.local.pendents" />
    </h3>
    
    <!-- Controles de ordenamiento -->
    <div class="sort-controls">
        <label>Ordenar por:</label>
        <select id="sortBy" onchange="sortCards()">
            <option value="id">ID de Solicitud</option>
            <option value="codigo">Código de Procedimiento</option>
            <option value="fecha" selected>Fecha de Inicio</option>
            <option value="contacto">Persona de Contacto</option>
            <option value="mensajes">Mensajes Pendientes</option>
            <option value="ultimoMensaje">Fecha Último Mensaje</option>
        </select>
        
        <select id="sortOrder" onchange="sortCards()">
            <option value="asc">Ascendente</option>
            <option value="desc" selected>Descendente</option>
        </select>
    </div>
    
    <div class="kanban-container">
        <!-- Iterar sobre cada columna/estado -->
        <c:forEach var="column" items="${columns}">
            <div class="kanban-column">
                <div class="kanban-column-header">
                    <fmt:message key="solicitud.estat.${column.estatId}" />
                    <span class="badge-count">${fn:length(column.solicituds)}</span>
                </div>
                <c:choose>
                    <c:when test="${empty column.solicituds}">
                        <div class="empty-column">
                            <i class="fas fa-check-circle" style="font-size: 2em;"></i><br><br>
                            No hay solicitudes
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="solicitud" items="${column.solicituds}">
                            <c:url var="solicitudUrl" value="/operador/solicitudfullview/view/${solicitud.solicitudID}"/>
                            <c:set var="messageCount" value="${not empty eventsMap[solicitud.solicitudID] ? eventsMap[solicitud.solicitudID].count : 0}"/>
                            <c:set var="lastMessageDate" value="${not empty eventsMap[solicitud.solicitudID] ? eventsMap[solicitud.solicitudID].lastMessageDate : 0}"/>
                            <div class="kanban-card" 
                                 onclick="window.location.href='${solicitudUrl}'"
                                 data-solicitud-id="${solicitud.solicitudID}"
                                 data-procediment-codi="${solicitud.procedimentCodi}"
                                 data-data-inici="${solicitud.dataInici.time}"
                                 data-persona-contacte="${solicitud.personaContacte}"
                                 data-message-count="${messageCount}"
                                 data-last-message-date="${lastMessageDate}">
                                <!-- Badge de eventos no leídos - estilo iOS -->
                                <c:if test="${not empty eventsMap[solicitud.solicitudID]}">
                                    <span class="event-badge badge-${eventsMap[solicitud.solicitudID].badgeColor}" 
                                          title="Mensajes no leídos">
                                        ${eventsMap[solicitud.solicitudID].count}
                                    </span>
                                </c:if>
                                
                                <div class="kanban-card-title">
                                    <c:if test="${not empty solicitud.procedimentCodi}">${solicitud.procedimentCodi}</c:if>
                                    <c:if test="${empty solicitud.procedimentCodi}">Sin código</c:if>
                                    - Sol. #${solicitud.solicitudID}
                                </div>
                                <div class="kanban-card-info" style="font-weight: 500; margin-bottom: 8px;">
                                    <c:if test="${not empty solicitud.procedimentNom}">
                                        <i class="fas fa-file-alt"></i> ${solicitud.procedimentNom}
                                    </c:if>
                                </div>
                                <div class="kanban-card-info">
                                    <c:if test="${not empty solicitud.personaContacte}">
                                        <i class="fas fa-user"></i> <strong>Contacto:</strong> ${solicitud.personaContacte}
                                    </c:if>
                                </div>
                                <div class="kanban-card-info">
                                    <c:if test="${not empty solicitud.organid}">
                                        <i class="fas fa-building"></i> <strong>Órgano:</strong> ${organMap[solicitud.organid]}
                                    </c:if>
                                </div>
                                <div class="kanban-card-info">
                                    <c:if test="${not empty solicitud.dataInici}">
                                        <i class="fas fa-calendar"></i> <fmt:formatDate value="${solicitud.dataInici}" pattern="dd/MM/yyyy" />
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
    </div>
</div>

<script>
function sortCards() {
    const sortBy = document.getElementById('sortBy').value;
    const sortOrder = document.getElementById('sortOrder').value;
    
    const columns = document.querySelectorAll('.kanban-column');
    
    columns.forEach((column, index) => {
        // Obtener todas las tarjetas de esta columna
        const cards = Array.from(column.querySelectorAll('.kanban-card'));
        
        if (cards.length === 0) return;
        
        // Ordenar las tarjetas
        cards.sort((a, b) => {
            let valueA, valueB;
            
            switch(sortBy) {
                case 'id':
                    valueA = parseInt(a.getAttribute('data-solicitud-id'));
                    valueB = parseInt(b.getAttribute('data-solicitud-id'));
                    break;
                case 'codigo':
                    valueA = (a.getAttribute('data-procediment-codi') || '').toLowerCase();
                    valueB = (b.getAttribute('data-procediment-codi') || '').toLowerCase();
                    break;
                case 'fecha':
                    valueA = parseInt(a.getAttribute('data-data-inici'));
                    valueB = parseInt(b.getAttribute('data-data-inici'));
                    break;
                case 'contacto':
                    valueA = (a.getAttribute('data-persona-contacte') || '').toLowerCase();
                    valueB = (b.getAttribute('data-persona-contacte') || '').toLowerCase();
                    break;
                case 'mensajes':
                    valueA = parseInt(a.getAttribute('data-message-count'));
                    valueB = parseInt(b.getAttribute('data-message-count'));
                    break;
                case 'ultimoMensaje':
                    valueA = parseInt(a.getAttribute('data-last-message-date'));
                    valueB = parseInt(b.getAttribute('data-last-message-date'));
                    break;
            }
            
            // Comparar valores
            let comparison = 0;
            if (valueA > valueB) comparison = 1;
            if (valueA < valueB) comparison = -1;
            
            // Aplicar orden ascendente o descendente
            return sortOrder === 'asc' ? comparison : -comparison;
        });
        
        // Reordenar las tarjetas en el DOM
        const parent = cards[0].parentElement;
        cards.forEach(card => {
            parent.appendChild(card);
        });
    });
}

// Ordenar al cargar la página
document.addEventListener('DOMContentLoaded', function() {
    sortCards();
});
</script>
