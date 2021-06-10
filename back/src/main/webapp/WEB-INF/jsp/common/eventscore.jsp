<%@ page contentType="text/html; charset=UTF-8" language="java"%><%@ include
    file="/WEB-INF/jsp/moduls/includes.jsp"%>



<c:if test="${showOnlyPublic==false}">

    <c:set var="tipusevent" value="${isSolicitud?'solicitud':'incidenciatecnica' }" />    
    <a class="minibutton" href="<c:url value="/operador/event${tipusevent}/${event.eventID}/edit"/>"
        onclick="" title="Modificar">
        <div style="color: #FFFFFF">
            <i class="iconpencil"></i>Editar
        </div>
    </a>

</c:if>



<br />

<c:out escapeXml="false" value="${event.comentari}" />


<c:if test="${event.tipus == -2 }">
    <a class="minibutton1" target="_blank"
        href="https://ssweb.seap.minhap.es/ayuda/seguimiento" onclick="" title="Seguiment">
        <div style="color: #FFFFFF">Seguiment CAID</div>
    </a>


    <form id="form1" target="_blank"
        action="https://ssweb.seap.minhap.es/ayuda/seguimiento" method="post">
        <a class="minibutton1" href="javascript:;"
            onclick="document.getElementById('form1').submit();">
            <div style="color: #FFFFFF">OMPLIR FORM PERO NO FUNCIONA</div>
        </a> <input type="hidden" name="data[email]" value="anadal@fundaciobit.org"
            id="data_email"> <input type="hidden" name="data[incidencia]"
            value="939027" id="data_incidencia"> <input type="hidden"
            name="data[seguimiento]" value="58604420212105" id="data_seguimiento">
    </form>

</c:if>

<c:if test="${not empty event.fitxer}">


    <a class="minibutton1" target="_blank"
        href="<c:url value="${pad:fileUrl(event.fitxer)}"/>" onclick=""
        title="Descarregar Fitxer"> <i class="icon-download-alt"></i>
        <div style="color: #FFFFFF">${event.fitxer.nom}</div>
    </a>


</c:if>