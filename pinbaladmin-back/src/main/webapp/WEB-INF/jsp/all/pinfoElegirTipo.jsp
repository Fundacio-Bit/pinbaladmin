<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title><fmt:message key="tramit.pinfo.permisos.solicitar"/></title>

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

/* Contenedor de nota informativa */
#nota-container {
	display: flex;
	align-items: center;
	gap: 12px;
	background: linear-gradient(135deg, #e8f5e9 0%, #f1f8f4 100%);
	padding: 16px 24px;
	border-radius: 8px;
	border-left: 4px solid #4DBA79;
	width: fit-content;
	margin: 0 auto 3rem auto;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.05);
}

#nota {
	font-style: normal;
	color: #265d3c;
	font-size: 15px;
	margin: 0;
	line-height: 1.5;
}

/* Contenedor de botones de elección */
#msgAfegirPinfoData {
	text-align: center;
	margin: 3rem auto;
	display: flex;
	justify-content: center;
	gap: 24px;
	flex: 1;
	align-items: center;
}

/* Botones de elección Alta/Baja */
.btn-choice {
	color: white !important;
	padding: 18px 48px;
	border-radius: 10px;
	font-weight: 600;
	font-size: 16px;
	border: none;
	cursor: pointer;
	transition: all 0.2s;
	text-decoration: none !important;
	display: inline-flex;
	align-items: center;
	justify-content: center;
	gap: 10px;
	min-width: 220px;
}

.btn-choice i {
	font-size: 18px;
}

.btn-choice.btn-alta {
	background: linear-gradient(135deg, #4DBA79 0%, #3a9e65 100%);
	box-shadow: 0 2px 8px rgba(77, 186, 121, 0.25);
}

.btn-choice.btn-alta:hover {
	background: linear-gradient(135deg, #3a9e65 0%, #2d7a4e 100%);
	box-shadow: 0 2px 8px rgba(77, 186, 121, 0.3);
	color: white !important;
	text-decoration: none !important;
}

.btn-choice.btn-alta:active {
	background: linear-gradient(135deg, #2d7a4e 0%, #1f5838 100%);
	box-shadow: 0 1px 4px rgba(77, 186, 121, 0.2);
}

.btn-choice.btn-baixa {
	background: linear-gradient(135deg, #e74c3c 0%, #c0392b 100%);
	box-shadow: 0 2px 8px rgba(231, 76, 60, 0.25);
}

.btn-choice.btn-baixa:hover {
	background: linear-gradient(135deg, #c0392b 0%, #a93226 100%);
	box-shadow: 0 2px 8px rgba(231, 76, 60, 0.3);
	color: white !important;
	text-decoration: none !important;
}

.btn-choice.btn-baixa:active {
	background: linear-gradient(135deg, #a93226 0%, #8b1d1d 100%);
	box-shadow: 0 1px 4px rgba(231, 76, 60, 0.2);
}
</style>

</head>
<body>
	<div class="myContainer">
		<div class="titol-tramit-pinfo-header">
			<div class="titol-tramit-pinfo-container">
				<h3 class="titol-tramit-pinfo"><fmt:message key="tramit.pinfo.solicitar"/></h3>
			</div>
		</div>
		
		<div id="nota-container">
			<i class="fas fa-info-circle" style="color: #4DBA79; font-size: 20px;"></i>
			<p id="nota">
				<fmt:message key="tramit.pinfo.nota" />
			</p>
		</div>

		<div id="msgAfegirPinfoData">
			<a href="<c:url value="/public/pinfodata/crearalta"/>" class="btn-choice btn-alta">
				<i class="fas fa-user-plus"></i>
				<fmt:message key="tramitpinfo.alta"/>
			</a>
			<a href="<c:url value="/public/pinfodata/crearbaixa"/>" class="btn-choice btn-baixa">
				<i class="fas fa-user-minus"></i>
				<fmt:message key="tramitpinfo.baixa"/>
			</a>
		</div>
	</div>
</body>
</html>
