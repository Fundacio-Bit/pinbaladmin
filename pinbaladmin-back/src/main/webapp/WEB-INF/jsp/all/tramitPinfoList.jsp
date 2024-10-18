<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Dades solicitant</h2>
	<div id="dades-incidencia-container">
		<!--
	|			|Usuari	 | Usuari					|
	|SOLICITANT	|Nom 	 | Nom						|
	|			|Telefon | Telefon | Correu | Correu|	
 -->
		<table id="dades-solicitant">
			<tr>
				<td class="label" rowspan="3" style="text-align: center;">DADES<br>SOLICITANT</td>
				<td class="label">Usuari</td>
				<td class="value" colspan="3">${usuariData}</td>
			</tr>
			<tr>
				<td class="label">Nom</td>
				<td class="value" colspan="3">${incidencia.contacteNom}</td>
			</tr>
			<tr>
				<td class="label">Telefon</td>
				<td class="value">${incidencia.contacteTelefon}</td>
				<td class="label">Correu</td>
				<td class="value">${incidencia.contacteEmail}</td>
		</table>
	</div>

	<div>Aqui la informacion de los permisos a solicitar.</div>

	<style>
#dades-incidencia-container {
	margin: auto;
	width: fit-content;
	background: lightgray;
	border-radius: 6px;
	padding: 5px  1rem;
}

#dades-solicitant {
	
}

#dades-solicitant td {
	padding: 5px;
}

#dades-solicitant .label {
	font-weight: bold;
	padding-right: 1rem;
}

#dades-solicitant .value {
	
}
</style>
</body>
</html>