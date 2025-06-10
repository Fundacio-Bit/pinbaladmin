<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ include
	file="/WEB-INF/jsp/all/tramitModificacioSolicitudsPublic.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificar solicitud</title>


<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background: #f4f6f8;
	margin: 0;
	padding: 20px;
	color: #333;
}

.container {
	background: #fff;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
	position: absolute;
	left: 26rem;
	right: 26rem;
}

h3 {
	text-align: center;
	color: #2c3e50;
	margin-bottom: 30px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 40px;
}

th, td {
	text-align: left;
	padding: 12px 15px;
}

th {
	background-color: #2c3e50;
	font-weight: 600;
	
	width: min-content;
}

tr:hover {
	background-color: #f1f1f1;
}

.data-block {
	margin-bottom: 30px;
	background: #f9fafb;
	padding: 20px;
	border-radius: 10px;
}

.data-block label {
	font-weight: bold;
	margin-right: 10px;
	color: #555;
}

.data-block span {
	color: #666;
}

.button {
	display: inline-block;
	background-color: #3498db;
	color: white;
	padding: 12px 24px;
	text-align: center;
	text-decoration: none;
	font-size: 16px;
	border-radius: 8px;
	transition: background-color 0.3s ease;
}

.button:hover {
	background-color: #2980b9;
}

#myTextArea {
	width: 100%;
	border: none;
	padding: 12px 15px;
}

#menuContainer {
	display: flex;
	flex-flow: column;
	text-align: center;
	background: #fff;
	padding: 1rem;
	border-radius: 10px;
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
	position: fixed;
	left: 3rem;
	width: 20rem;
}

.boton {
	color: white;
	margin: 6px;
	padding: 10px;
	text-align: center;
	border-radius: 5px;
}

#modifSoli {
	background: blue;
}

#modifServ {
	background: green;
}

#modifCons {
	background: red;
}

.boton:hover {
	opacity: .7;
}
</style>


</head>

<body>

 	<div id="menuContainer">
		<a class="boton" id="modifSoli"	href="modificarSolicitud/${solicitud.solicitudID}">Modificar dades solicitud</a> 
		<a class="boton" id="modifServ"	href="modificarServeis/${solicitud.solicitudID}">Modificar serveis / nomres</a>
 	 	<a class="boton" id="modifCons"	href="modificarConsentiment/${solicitud.solicitudID}">Modificar	document Consentiment</a>
	</div>

	<div class="container">
		<h3>Editar Procediment All</h3>

		<!-- Datos del Procedimiento -->
		<h4>Dades del Procediment</h4>
		<table border="1">
			<tr>
				<th>Procediment Codi</th>
				<td><b>${solicitud.codi}</b></td>
			</tr>
			<tr>
				<th>Procediment Nom</th>
				<td>${solicitud.nom}</td>
			</tr>
			<tr>
				<th>Estat</th>
				<td>${solicitud.estat}</td>
			</tr>
			<tr>
				<th>Organ Gestor</th>
				<td>${solicitud.organGestor}</td>
			</tr>
			<tr>
				<th>Responsable Procediment</th>
				<td>${solicitud.responsable}</td>
			</tr>
			<tr>
				<th>Email Responsable</th>
				<td>${solicitud.responsableMail}</td>
			</tr>
			<tr>
				<th>Data Inici</th>
				<td>${solicitud.dataInici}</td>
			</tr>
			<tr>
				<th>Data Fi</th>
				<td>${solicitud.dataFi}</td>
			</tr>
			<tr>
				<th>Consentiment</th>
				<td>${solicitud.consentiment}</td>
			</tr>
			<tr>
				<th>Notes</th>
				<td style="padding: 0;"><textarea id="myTextArea" rows="3"
						readonly="true">${solicitud.notes}</textarea></td>
			</tr>
		</table>

		<br />

		<!-- Tabla de Servicios -->
		<h4>Serveis</h4>
		<c:if test="${empty serveis}">
			<p>No hi ha serveis disponibles.</p>
		</c:if>
		<c:if test="${not empty serveis}">
			<table border="1">
				<thead>
					<tr>
						<th>Codi</th>
						<th>Nom</th>
						<th>Estat</th>
						<th>Normes</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="servei" items="${serveis}">
						<tr>
							<td>${servei.codi}</td>
							<td>${servei.nom}</td>
							<td>${servei.estat}</td>
							<td>${servei.normes}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

		<br />

		<!-- Tabla de Documentos -->
		<h4>Consentiment</h4>
		<c:if test="${empty docInfos}">
			<p>No hi ha documents de consentiment.</p>
		</c:if>
		<c:if test="${not empty docInfos}">
			<table border="1" >
				<thead>
					<tr>
						<th>Tipus</th>
						<th>Nom</th>
						<th>Fitxer</th>
						<th>Download</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="doc" items="${docInfos}">
						<tr>
							<td>${doc.tipus}</td>
							<td>${doc.nom}</td>
							<td>${doc.nomFitxer}</td>
							<td>${doc.urlDownload}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

	</div>
</body>

</html>