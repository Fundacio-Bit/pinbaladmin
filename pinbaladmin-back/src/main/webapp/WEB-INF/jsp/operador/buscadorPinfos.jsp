<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<tiles:insertDefinition name="operador">

<tiles:putAttribute name="body">
	<h2>Buscador de PINFOs</h2>

	<!-- Formulario de búsqueda -->
	<form method="GET" action="<c:url value="/operador/dadespinbal/buscadorpinfos"/>">
		<table class="formTable">
			<tr>
				<td>Usuario:</td>
				<td>
					<input type="text" name="searchUsuari" value="${searchUsuari}" size="40" 
					       placeholder="NIF, código, nombre o apellidos" />
				</td>
			</tr>
			<tr>
				<td>Procedimiento:</td>
				<td>
					<input type="text" name="searchProcediment" value="${searchProcediment}" size="40"
					       placeholder="ID, código o nombre del procedimiento" />
				</td>
			</tr>
			<tr>
				<td>Servicio:</td>
				<td>
					<input type="text" name="searchServei" value="${searchServei}" size="40"
					       placeholder="ID, código o nombre del servicio" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit" class="btn btn-primary">Buscar</button>
					<a href="<c:url value="/operador/dadespinbal/buscadorpinfos"/>" class="btn btn-default">Limpiar</a>
				</td>
			</tr>
		</table>
	</form>

	<br/>
	
	<!-- Información de ayuda -->
	<div class="alert alert-info">
		<strong>Ayuda:</strong> Puedes buscar por uno o varios criterios. Los campos aceptan:<br/>
		- <strong>Usuario:</strong> NIF, código de usuario, nombre o apellidos<br/>
		- <strong>Procedimiento:</strong> ID numérico, código o nombre del procedimiento<br/>
		- <strong>Servicio:</strong> ID numérico, código o nombre del servicio
	</div>

	<!-- Mensajes -->
	<c:if test="${not empty error}">
		<div class="alert alert-danger">${error}</div>
	</c:if>
	
	<c:if test="${not empty info}">
		<div class="alert alert-warning">${info}</div>
	</c:if>

	<!-- Resultados -->
	<c:if test="${not empty pinfos}">
		<h3>PINFOs encontrados: ${fn:length(pinfos)}</h3>
		
		<table class="table table-striped">
			<thead>
				<tr>
					<th>PINFO ID</th>
					<th>Entidad</th>
					<th>Solicitante NIF</th>
					<th>Destinatario</th>
					<th>Estado</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pinfos}" var="pinfo">
					<tr>
						<td>${pinfo.pinfoID}</td>
						<td>${pinfo.entitat}</td>
						<td>${pinfo.solicitantNIF}</td>
						<td>
							${pinfo.destinatariNom}<br/>
							<small>${pinfo.destinatariNIF}</small>
						</td>
						<td>
							<c:choose>
								<c:when test="${pinfo.estat == 1}">Enviado</c:when>
								<c:when test="${pinfo.estat == 0}">Pendiente</c:when>
								<c:when test="${pinfo.estat == 2}">Firmado</c:when>
								<c:otherwise>Estado ${pinfo.estat}</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="<c:url value='/operador/pinfo/view/${pinfo.pinfoID}'/>" class="btn btn-sm btn-info">Ver</a>
							<c:if test="${not empty pinfo.fitxerfirmatID}">
								<a href="<c:url value='/common/download/fitxer/${pinfo.fitxerfirmatID}'/>" class="btn btn-sm btn-success">PDF Firmado</a>
							</c:if>
							<c:if test="${not empty pinfo.fitxerID}">
								<a href="<c:url value='/common/download/fitxer/${pinfo.fitxerID}'/>" class="btn btn-sm btn-default">PDF</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>

</tiles:putAttribute>

</tiles:insertDefinition>