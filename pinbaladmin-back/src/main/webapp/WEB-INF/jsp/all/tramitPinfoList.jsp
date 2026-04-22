<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>


<style>
/* Contenedor principal */
.myContainer {
	background: white;
	padding: 3rem 4rem;
	border-radius: 12px;
	margin: 2rem 7rem;
	min-width: 75rem;
	box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
	min-height: 500px;
	display: flex;
	flex-direction: column;
}

/* Ocultar elementos autogenerados */
#pinfoData_listheader {
	display: none !important;
}

/* Botón Següent */
.btn-seguent {
	background: linear-gradient(135deg, #4DBA79 0%, #3a9e65 100%);
	color: white;
	padding: 10px 28px;
	border-radius: 8px;
	font-weight: 600;
	font-size: 14px;
	border: none;
	cursor: pointer;
	transition: all 0.2s;
	text-decoration: none;
	display: inline-flex;
	align-items: center;
	gap: 8px;
	box-shadow: 0 2px 8px rgba(77, 186, 121, 0.2);
}

.btn-seguent:hover {
	background: linear-gradient(135deg, #3a9e65 0%, #2d7a4e 100%);
	transform: translateY(-1px);
	box-shadow: 0 4px 12px rgba(77, 186, 121, 0.3);
	color: white;
	text-decoration: none;
}

/* Cards de usuario - diseño moderno sin bordes duros */
.info-usuari-full {
	max-width: 65rem;
	margin: 1.5rem auto;
	padding: 0;
	border-radius: 12px;
	background: white;
	box-shadow: 0 1px 8px rgba(0, 0, 0, 0.06);
	overflow: hidden;
	transition: all 0.3s;
}

.info-usuari-full:hover {
	box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
	transform: translateY(-2px);
}

/* Header de usuario - con acento verde sutil */
.pinfodata-user {
	font-size: 16px;
	font-weight: 600;
	margin: 0;
	padding: 16px 20px;
	color: white;
	background: linear-gradient(135deg, #4DBA79 0%, #3a9e65 100%);
	border-bottom: none;
}

/* Tabla de procedimientos - diseño limpio */
.taula-procediment {
	margin-bottom: 0;
	width: 100%;
}

/* Headers de procedimiento - más sutiles */
.pinfodata-procediment {
	background: #f8f9fa;
	color: #265d3c;
	font-weight: 600;
	font-size: 14px;
	padding: 12px 20px;
	display: flex;
	align-items: center;
	border-left: 4px solid #4DBA79;
	margin: 0;
}

/* Contenedor de servicios */
.pinfodata-serveis {
	display: flex;
	flex-direction: column;
	padding: 8px 20px 16px 20px;
	background: white;
}

/* Items de servicio - sin bordes, solo hover */
.llista-serveis {
	padding: 12px 16px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 4px 0;
	border-radius: 6px;
	background: white;
	transition: all 0.2s;
}

.llista-serveis:hover {
	background: #f8f9fa;
}

.servei-item {
	flex: 1;
}



/* Botón de eliminar - más discreto */
.btn-delete-container {
	margin-left: 12px;
}

.btn.btn-danger {
	padding: 6px 10px;
	transition: all 0.3s;
	border-radius: 6px;
	font-size: 14px;
}

.btn.btn-danger:hover {
	background-color: #c82333;
	transform: scale(1.05);
}

/* Contenedor de permisos */
#pinfo-data-content {
	margin-top: 1rem;
}

/* Sistema de headers limpios */
#includedContentLlistatPinfoData {
	margin: 1rem 0;
}
</style>

</head>
<body>
	<div class="myContainer">
		<div id="includedContentLlistatPinfoData">
			<form:form name="pinfoData" cssClass="form-search"
				modelAttribute="pinfoDataFilterForm"
				method="${(empty method)?'post':method}"
				enctype="multipart/form-data">

				<%@include file="/WEB-INF/jsp/webdb/pinfoDataListCommon.jsp"%>

				<!-- Título y botón Següent -->
				<div class="titol-tramit-pinfo-header">
					<div class="titol-tramit-pinfo-container">
						<h3 class="titol-tramit-pinfo">
							<fmt:message key="tramit.pinfo.permisos.solicitats"/>
							<c:if test="${altaBaixa == 1}"> - <span style="color: #4DBA79; font-weight: 600;">ALTA PERMISOS</span></c:if>
							<c:if test="${altaBaixa == 0}"> - <span style="color: #dc3545; font-weight: 600;">BAIXA PERMISOS</span></c:if>
						</h3>
					</div>
					<div class="titol-tramit-pinfo-botonera">
						<a href="<c:url value="/public/pinfodata/seleccionarResponsable"/>" class="btn-seguent">
							<span>Següent</span>
							<i class="fas fa-arrow-right"></i>
						</a>
					</div>
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
											<c:forEach var="servei" items="${altes}" varStatus="status">
												<div class="llista-serveis">
													<div class="servei-item">
														${servei.nom} 
													</div>
													<div class="btn-delete-container">
														<a href="<c:url value="/public/pinfodata/${servei.pinfoDataID}/delete" />"
															class="btn btn-danger"><i class="fas fa-times"></i>
														</a>
													</div>
												</div>
											</c:forEach>

											<c:forEach var="servei" items="${baixes}" varStatus="status">
												<div class="llista-serveis">
													<div class="servei-item">
														${servei.nom} 
													</div>
													<div class="btn-delete-container">
															<a href="<c:url value="/public/pinfodata/${servei.pinfoDataID}/delete" />"
																class="btn btn-danger"><i class="fas fa-times"></i>
															</a>
														</div>
													</div>
												</c:forEach>
										</div>
									</div>
								</c:forEach>
							</div>
						</c:forEach>
					</div>

					<c:if test="${__theFilterForm.attachedAdditionalJspCode}">
						<%@include
							file="/WEB-INF/jsp/webdbmodificable/pinfoDataListModificable.jsp"%>
					</c:if>
			</form:form>
		</div>
	</div>
</body>
</html>