<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<style>
body {
	display: flex;
	/* align-items: center; */
	justify-content: center;
	min-height: 100vh;
	background: #e9faff;
}

#dades-incidencia-container {
	margin: auto;
	width: fit-content;
	background: lightgray;
	border-radius: 6px;
	padding: 5px 1rem;
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

#includedContentLlistatPinfoData {
	margin: 1rem;
}

.myContainer {
	background: white;
	padding: 0 1rem;
	border-radius: 6px;
	border: 2px solid black;
	margin: 2rem 7rem;
	min-width: 75rem;
	
}

#title {
	text-align: center;
	margin: 1rem;
}
</style>

</head>
<body>
	<div class="myContainer">
		<h4 id="title" style="margin: 1rem auto;">Dades solicitant</h4>

		<div id="dades-incidencia-container">
			<!--
	|Usuari	 | Usuari					|
	|Nom 	 | Nom						|
	|Telefon | Telefon | Correu | Correu|	
 -->
			<table id="dades-solicitant">
				<tr>
					<!-- 					<td class="label" rowspan="3" style="text-align: center;">DADES<br>SOLICITANT</td> -->
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

		<div id="includedContentLlistatPinfoData">
			<%@ include file="/WEB-INF/jsp/webdb/pinfoDataList.jsp"%>
		</div>
	</div>
</body>
</html>