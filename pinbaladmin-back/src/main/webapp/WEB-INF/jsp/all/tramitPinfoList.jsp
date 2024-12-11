<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<style>
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
	padding: 1rem;
	border-radius: 6px;
	border: 2px solid black;
	margin: 2rem 7rem;
	min-width: 75rem;
}

#title {
	text-align: center;
	margin: 1rem;
}

.info-usuari-full {
	width: fit-content;
	margin: 2rem auto;
	border: 1px black solid;
	padding: 15px;
	border-radius: 3px;
	box-shadow: 1px 2px 3px 1px #888;
}

.btn.btn-danger {
	padding-left: 0.5rem;
	padding-right: 0.5rem;
}

.pinfodata-user {
	font-style: italic;
	font-size: 18px;
	font-weight: bold;
}

.taula-procediment {
	margin-top: 1rem;
	width: 100%;
}

.pinfodata-procediment {
	background: lightgray;
	font-weight: bold;
	padding: 0 10px;
	width: 25rem;
}

.llista-serveis {
	padding: 6px;
}

.pinfodata-servei {
	
}

#msgAfegirPinfoData {
    text-align: center;
    margin: 2rem;
    display: flex;
    justify-content: center;
    gap: 16px;
}

.btn-delete-container {
	width: 0;
}

.tipus1 {
	color: black;
}

.tipus0 {
	color: red;
}
</style>

</head>
<body>
	<div class="myContainer">
<%-- 		<h4 id="title" style="margin: 1rem auto;">Dades solicitant</h4>

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
					<td class="value" colspan="3">${usuariNIF}&nbsp;${usuariNom}</td>
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
		</div> --%>

		<div id="includedContentLlistatPinfoData">
			<form:form name="pinfoData" cssClass="form-search"
				modelAttribute="pinfoDataFilterForm"
				method="${(empty method)?'post':method}"
				enctype="multipart/form-data">

				<%@include file="/WEB-INF/jsp/webdb/pinfoDataListCommon.jsp"%>

				<c:if test="${empty pinfoDataFull.usuaris}">
					<div>
						<%@include
							file="/WEB-INF/jsp/webdb/pinfoDataListHeaderButtons.jsp"%>
							
							
						<div id="msgAfegirPinfoData">
							<p class="lead">Afegir dades al PINFO...</p>
						</div>
					</div>

					<script type="text/javascript">
						var elements = $('a[href*="/pinfodata/crear"]');
						let alta = elements[1];
						elements[1] = elements[0];
						elements[0] = alta;
						
						elements.removeClass("float-right");
						$("#msgAfegirPinfoData").html(elements);
					</script>
				</c:if>

				<c:if test="${not empty pinfoDataFull.usuaris}">
					<div id="${formName}_listheader" class="filterLine lead"
						style="margin-bottom: 10px">
						<%@include
							file="/WEB-INF/jsp/webdb/pinfoDataListHeaderButtons.jsp"%>
					</div>


					<div id="pinfo-data-content">
<%-- 						<p>PinfoID : ${pinfoDataFull.pinfoID}</p> --%>
						<c:forEach var="usuari" items="${pinfoDataFull.usuaris}">
							<div class="info-usuari-full">
								<div class="pinfodata-user">Usuari: ${usuari.usuariID}</div>
								
								<c:forEach var="procediment" items="${usuari.procediments}">
									<table class="taula-procediment">
										<tr>
											<td class="pinfodata-procediment" rowspan="${procediment.serveis.size()}">
												${procediment.codi} <br> ${procediment.procediment}
											</td>
												
											<td class="llista-serveis tipus${procediment.serveis.get(0).alta}">${procediment.serveis.get(0).servei}</td>
											
											<td class="btn-delete-container">
												<a href="<c:url value="/public/pinfodata/${procediment.serveis.get(0).pinfoDataID}/delete" />" class="btn btn-danger">
													<i class="fas fa-times"></i>
												</a>
											</td>
										</tr>
										<c:forEach var="servei" items="${procediment.serveis}" varStatus="status">
											<c:if test="${!status.first}">
												<tr>
													<td class="llista-serveis tipus${procediment.serveis.get(0).alta}">${servei.servei}</td>
													<td class="btn-delete-container">
														<a href="<c:url value="/public/pinfodata/${servei.pinfoDataID}/delete" />" class="btn btn-danger">
															<i class="fas fa-times"></i>
														</a>
													</td>
												</tr>
											</c:if>
										</c:forEach>
									</table>
								</c:forEach>
							</div>
						</c:forEach>
					</div>


					<c:if test="${__theFilterForm.attachedAdditionalJspCode}">
						<%@include
							file="/WEB-INF/jsp/webdbmodificable/pinfoDataListModificable.jsp"%>
					</c:if>
				</c:if>
			</form:form>
		</div>
	</div>
</body>
</html>