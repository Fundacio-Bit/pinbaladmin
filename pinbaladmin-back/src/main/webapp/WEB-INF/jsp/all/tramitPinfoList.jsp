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
	/* 	width: fit-content; */
	max-width: 45rem;
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
	margin: 6px;
}

.taula-procediment {
	margin-bottom: 1rem;
	width: 100%;
	/* 	display: flex;
	  flex-direction: column;
 */
}

.pinfodata-procediment {
	background: #e6e6e6;
	font-weight: bold;
	padding: 5px 10px;
	align-items: center;
	display: flex;
	border-radius: 5px 00px 00px 5px;
}

.llista-serveis {
	padding: 6px;
	display: flex;
	justify-content: space-between;
	min-width: 20rem;
}

.pinfodata-serveis {
	display: flex;
	flex-direction: column;
	justify-content: center;
	margin-left: .5rem;
	margin-top: .5rem;
}

#msgAfegirPinfoData {
	text-align: center;
	margin: 2rem;
	display: flex;
	justify-content: center;
	gap: 16px;
}

.btn-delete-container {
	
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
								<div class="pinfodata-user">Usuari: ${usuari.usuariNom} - ${usuari.usuariNif} - ${usuari.usuariCodi} </div>
								
								<c:forEach var="procediment" items="${usuari.procediments}">
									<c:set var="altes" value="${procediment.altes}" />
									<c:set var="baixes" value="${procediment.baixes}"/>
									
									<div class="taula-procediment">

										<div class="pinfodata-procediment">${procediment.codi}
											<br> ${procediment.procediment}
										</div>
										<div class="pinfodata-serveis">
											<c:if test="${altes.size() > 0}">
												<c:forEach var="servei" items="${altes}" varStatus="status">
													<div class="llista-serveis">
														<div class="servei-item tipus${servei.alta}">
															${servei.servei}
														</div>
														<div class="btn-delete-container">
															<a href="<c:url value="/public/pinfodata/${servei.pinfoDataID}/delete" />"
																class="btn btn-danger"><i class="fas fa-times"></i>
															</a>
														</div>
													</div>
												</c:forEach>
											</c:if>

											<c:if test="${baixes.size() > 0}">
												<c:forEach var="servei" items="${baixes}" varStatus="status">
													<div class="llista-serveis">
														<div class="servei-item tipus${servei.alta}">
															${servei.servei}
														</div>
														<div class="btn-delete-container">
															<a href="<c:url value="/public/pinfodata/${servei.pinfoDataID}/delete" />"
																class="btn btn-danger"><i class="fas fa-times"></i>
															</a>
														</div>
													</div>
												</c:forEach>
											</c:if>
										</div>
									</div>
									
									
									<%-- <table class="taula-procediment">
										<tr>
											<td class="pinfodata-procediment" rowspan="${altes.size() + baixes.size()}">
												${procediment.codi} <br> ${procediment.procediment}
											</td>

											<c:if test="${altes.size() > 0">
												<td class="llista-serveis tipus1">Altes</td>
											</c:if>
										</tr>
										
										<c:forEach var="servei" items="${altes}" varStatus="status">
											<tr>
												<td class="llista-serveis tipus${servei.alta}">${servei.servei}</td>
												<td class="btn-delete-container">
													<a href="<c:url value="/public/pinfodata/${servei.pinfoDataID}/delete" />" class="btn btn-danger">
														<i class="fas fa-times"></i>
													</a>
												</td>
											</tr>
										</c:forEach>
										
										<c:if test="${baixes.size() > 0">
											<td class="llista-serveis tipus0">Baixes</td>
										</c:if>
											
										<c:forEach var="servei" items="${baixes}" varStatus="status">
											<tr>
												<td class="llista-serveis tipus${servei.alta}">${servei.servei}</td>
												<td class="btn-delete-container">
													<a href="<c:url value="/public/pinfodata/${servei.pinfoDataID}/delete" />" class="btn btn-danger">
														<i class="fas fa-times"></i>
													</a>
												</td>
											</tr>
										</c:forEach>
									</table> --%>
									
									
									
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