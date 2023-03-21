<%@page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@page import="org.fundaciobit.pinbaladmin.commons.utils.Configuracio"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>

<c:if test="${showOnlyPublic==false}">

	<c:set var="tipusevent"
		value="${isSolicitud?'solicitud':'incidenciatecnica' }" />

	<c:url var="theurl"
		value="/operador/event${tipusevent}/${event.eventID}/edit" />
	<c:set var="urlEdit"
		value="javascript:document.location.href='${theurl}'" />
	<c:if test="${not isEstatal}">
		<c:if test="${empty personaContacteEmail}">
			<c:set var="urlEdit"
				value="javascript:alert('La sol·licitud no te definit el correu del contacte EC');" />
		</c:if>
	</c:if>

	<button class="btn btn-sm btn-danger" onclick="${urlEdit}"
		style="color: white" onclick="" title="Modificar">
		<i class="<%=IconUtils.ICON_EDIT%>"></i>Editar
	</button>
	<c:if test="${event.noLlegit}">
		<span
			style='font-size: 40px; color: red; float: right; -webkit-text-fill-color: red;'>&#8859;</span>
	</c:if>
	<br />

</c:if>


<c:choose>
	<c:when test="${fn:startsWith(event.comentari, '<')}">
		<div>${event.comentari}</div>
	</c:when>
	<c:otherwise>
		<textarea readonly class="form-control event">${event.comentari}</textarea>
	</c:otherwise>
</c:choose>

<c:if test="${event.tipus == -2 }">

	<form id="form_${event}" style="margin: 0 0 0 0"
		action="<%=Configuracio.getCAIDSeleniumUrl()%>/RemoteSeleniumConsulta"
		target="_blank">

		<input type="hidden" id="email" name="email"
			value="gd.pinbal@fundaciobit.org"> <input type="hidden"
			id="incidencia" name="incidencia"
			value="${event.caidIdentificadorConsulta}"> <input
			type="hidden" id="seguimiento" name="seguimiento"
			value="${event.caidNumeroSeguiment}">

		<table border="0">
			<tr>
				<td><input class="btn btn-sm btn-warning" type="submit"
					value="Seguiment"></td>
				<td><small> CAID::Correu = <b>gd.pinbal@fundaciobit.org</b><br />
						CAID::Identificador = <b>${event.caidIdentificadorConsulta}</b><br />
						CAID::N&uacute;m. Seguiment = <b>${event.caidNumeroSeguiment}</b>
				</small></td>
			</tr>

		</table>

	</form>
	<%--
    <form id="form${event.eventID}" target="_blank"
        action="https://ssweb.seap.minhap.es/ayuda/seguimiento" method="post">
        <a class="minibutton1 btn btn-sm btn-warning" href="javascript:;"
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

	<br />
	<a class="btn btn-sm btn-success" target="_blank"
		href="<c:url value="${pad:fileUrl(event.fitxer)}"/>" onclick=""
		title="Descarregar Fitxer"> <i
		class="<%=IconUtils.ICON_FILE_DOWNLOAD%>"></i>
		<div style="color: #FFFFFF">${event.fitxer.nom}</div>
	</a>


</c:if>