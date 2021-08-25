<%@page import="org.fundaciobit.pinbaladmin.utils.Configuracio"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%><%@ include
    file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:if test="${showOnlyPublic==false}">

    <c:set var="tipusevent" value="${isSolicitud?'solicitud':'incidenciatecnica' }" />
    
    <c:url var="theurl" value="/operador/event${tipusevent}/${event.eventID}/edit"/>
    <c:set var="urlnou" value="javascript:document.location.href='${theurl}'"/>
    <c:if test="${not isEstatal}"> 
        <c:if test="${empty personaContacteEmail}">                       
           <c:set var="urlnou" value="javascript:alert('La sol·licitud no te definit el correu del contacte EC');" />
        </c:if>
    </c:if>

    <button class="btn btn-mini btn-danger" onclick="${urlnou}"
        onclick="" title="Modificar">
            <i class="icon-pencil iconpencil"></i>Editar
    </button>
    <br />

</c:if>


<c:out value="${event.comentari}" />


<c:if test="${event.tipus == -2 }">

 <form id ="form_${event}" action="<%=Configuracio.getCAIDSeleniumUrl()%>/RemoteSeleniumConsulta" target="_blank">

  <input type="hidden" id="email" name="email" value="gd.pinbal@fundaciobit.org"><br>
  <input type="hidden" id="incidencia" name="incidencia" value="${event.caidIdentificadorConsulta}">
  <input type="hidden" id="seguimiento" name="seguimiento" value="${event.caidNumeroSeguiment}">
  
    <table border="0">
    <tr>
    <td>
        <input class="btn btn-mini btn-warning" type="submit" value="Seguiment">
    </td>
    <td> 
    <small>
    CAID::Correu = <b>gd.pinbal@fundaciobit.org</b><br/>
    CAID::Identificador = <b>${event.caidIdentificadorConsulta}</b><br/>
    CAID::N&uacute;m. Seguiment = <b>${event.caidNumeroSeguiment}</b>
    </small>
    </td>
    </tr>
    
    </table>
    
    </form> 
<%--
    <form id="form${event.eventID}" target="_blank"
        action="https://ssweb.seap.minhap.es/ayuda/seguimiento" method="post">
        <a class="minibutton1 btn btn-mini btn-warning" href="javascript:;"
            onclick="document.getElementById('form${event.eventID}').submit();">
            <div style="color: #FFFFFF">OMPLIR FORM PERO NO FUNCIONA</div>
        </a> <input type="hidden" name="data[email]" value="anadal@fundaciobit.org"
            id="data_email"> <input type="hidden" name="data[incidencia]"
            value="939027" id="data_incidencia"> <input type="hidden"
            name="data[seguimiento]" value="58604420212105" id="data_seguimiento">
    </form>
     --%>

</c:if>

<c:if test="${not empty event.fitxer}">

    <br/>
    <a class="btn btn-mini btn-success" target="_blank"
        href="<c:url value="${pad:fileUrl(event.fitxer)}"/>" onclick=""
        title="Descarregar Fitxer"> <i class="icon-download-alt"></i>
        <div style="color: #FFFFFF">${event.fitxer.nom}</div>
    </a>


</c:if>