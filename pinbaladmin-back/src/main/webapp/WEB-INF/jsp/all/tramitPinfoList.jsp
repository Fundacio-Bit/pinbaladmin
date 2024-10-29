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

.pinfodata-user {
	margin-left: 1rem;
}

.pinfodata-procediment {
	margin-left: 3rem;
}

.pinfodata-servei {
	margin-left: 5rem;
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
		</div>

		<div id="includedContentLlistatPinfoData">
			<%-- 			<%@ include file="/WEB-INF/jsp/webdb/pinfoDataList.jsp"%> --%>

			<form:form name="pinfoData" cssClass="form-search"
				modelAttribute="pinfoDataFilterForm"
				method="${(empty method)?'post':method}"
				enctype="multipart/form-data">

				<%@include file="/WEB-INF/jsp/webdb/pinfoDataListCommon.jsp"%>

				<c:if test="${empty pinfoDataItems}">
					<div>
						<p class="lead">Afegir dades al PINFO</p>
						<%@include file="/WEB-INF/jsp/webdb/pinfoDataListHeaderButtons.jsp"%>
					</div>
				</c:if>

				<c:if test="${not empty pinfoDataItems}">
					<div id="${formName}_listheader" class="filterLine lead"
						style="margin-bottom: 10px">
						<%@include file="/WEB-INF/jsp/webdb/pinfoDataListHeaderButtons.jsp"%>
					</div>



					<div id="pinfo-data-content">
						<c:set var="lastPinfoData"/>
						<c:forEach var="pinfoData" items="${pinfoDataItems}">	
							<c:if test="${empty lastPinfoData || lastPinfoData.usuariid != pinfoData.usuariid}">
								<div class="pinfodata-user">
									<p>${pinfoData.usuariid}</p>
								</div>
							</c:if>

							<c:if test="${empty lastPinfoData || lastPinfoData.procedimentID != pinfoData.procedimentID}">
								<div class="pinfodata-procediment">
									<p>
									<c:set var="tmp">${pinfoData.procedimentID}</c:set>
									${__theFilterForm.mapOfSolicitudForProcedimentID[tmp]}
									</p>
								</div>
							</c:if>

							<div class="pinfodata-servei">
								<p>
									<c:set var="tmp">${pinfoData.serveiID}</c:set>
									${__theFilterForm.mapOfServeiForServeiID[tmp]}
								</p>
							</div>
							
							<c:set var="lastPinfoData" value="${pinfoData}"/>
						</c:forEach>
					</div>



					<%-- <div id="table-container">
						<table id="tablePinfoData"
							class="table table-sm table-bordered table-striped table-genapp table-genapp-list">
							<thead>
								<tr>
									<%@include
										file="/WEB-INF/jsp/webdb/pinfoDataListCoreHeaderMultipleSelect.jsp"%>
									<%@include
										file="/WEB-INF/jsp/webdb/pinfoDataListCoreHeader.jsp"%>

									ADD HERE NEW COLUMNS HEADER 
									<%@include
										file="/WEB-INF/jsp/webdb/pinfoDataListButtonsHeader.jsp"%>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="pinfoData" items="${pinfoDataItems}">

									<tr id="pinfoData_rowid_${pinfoData.pinfodataID}">
										<%@include
											file="/WEB-INF/jsp/webdb/pinfoDataListCoreMultipleSelect.jsp"%>

										<td>${pinfoData.usuariid}</td>
										<td>
											<c:set var="tmp">${pinfoData.procedimentID}</c:set>
											${__theFilterForm.mapOfSolicitudForProcedimentID[tmp]}
										</td>
										
										<td>
											<c:set var="tmp">${pinfoData.serveiID}</c:set>
											${__theFilterForm.mapOfServeiForServeiID[tmp]}
										</td>
										
										<td>
											<c:set var="tmp">${pinfoData.alta}</c:set>
											${__theFilterForm.mapOfValuesForAlta[tmp]}
										</td>
										
										 ADD HERE NEW COLUMNS CONTENT
										<%@include file="/WEB-INF/jsp/webdb/pinfoDataListButtons.jsp"%>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div> --%>
					

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