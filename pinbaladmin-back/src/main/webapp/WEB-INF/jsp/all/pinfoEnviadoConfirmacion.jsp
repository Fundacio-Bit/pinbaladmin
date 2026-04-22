<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/all/tramitPinfoPublic.jsp"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title><fmt:message key="pinfo.enviado.titulo"/></title>

<style>
.container-confirmacio {
	max-width: 50rem;
	margin: 3rem auto;
	padding: 2rem;
	background: white;
	border-radius: 6px;
	border: 2px solid #4DBA79;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.icono-exito {
	text-align: center;
	margin-bottom: 1.5rem;
	font-size: 4rem;
	color: #4DBA79;
}

.mensaje-principal {
	text-align: center;
	margin-bottom: 1.5rem;
}

.mensaje-principal h2 {
	color: #265d3c;
	margin-bottom: 1rem;
}

.mensaje-principal p {
	font-size: 1.1rem;
	line-height: 1.6;
	color: #333;
}

.info-pinfo {
	background: #f8f9fa;
	padding: 1rem;
	border-radius: 4px;
	margin: 1.5rem 0;
	border-left: 4px solid #4DBA79;
}

.info-pinfo p {
	margin: 0.5rem 0;
}

.info-pinfo strong {
	color: #265d3c;
}

.botonera-confirmacio {
	display: flex;
	justify-content: center;
	gap: 1rem;
	margin-top: 2rem;
}

.btn-primary-custom {
	background-color: #4DBA79;
	border-color: #4DBA79;
	color: white;
	padding: 12px 28px;
	font-size: 15px;
	font-weight: 500;
	border-radius: 5px;
	text-decoration: none;
	display: inline-block;
	transition: all 0.3s;
	border: none;
	cursor: pointer;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.btn-primary-custom:hover {
	background-color: #265d3c;
	color: white;
	text-decoration: none;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
	transform: translateY(-1px);
}

.btn-secondary-custom {
	background-color: #6c757d;
	border-color: #6c757d;
	color: white;
	padding: 12px 28px;
	font-size: 15px;
	font-weight: 500;
	border-radius: 5px;
	text-decoration: none;
	display: inline-block;
	transition: all 0.3s;
	border: none;
	cursor: pointer;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.btn-secondary-custom:hover {
	background-color: #5a6268;
	color: white;
	text-decoration: none;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
	transform: translateY(-1px);
}

.instrucciones {
	margin-top: 2rem;
	padding: 1rem;
	background: #fff3cd;
	border: 1px solid #ffecb5;
	border-radius: 4px;
	border-left: 4px solid #ffbe00;
}

.instrucciones h4 {
	color: #856404;
	margin-top: 0;
}

.instrucciones p {
	color: #856404;
	margin-bottom: 0.5rem;
}
</style>

</head>
<body>

	<div class="container-confirmacio">
		<div class="icono-exito">
			<i class="fas fa-check-circle"></i>
		</div>
		
		<div class="mensaje-principal">
			<h2><fmt:message key="pinfo.enviado.titulo"/></h2>
			<p><fmt:message key="pinfo.enviado.mensaje"/></p>
		</div>
		
		<div class="info-pinfo">
			<p><strong><fmt:message key="pinfo.enviado.numero"/>:</strong> ${incidenciaID}</p>
			<p><strong><fmt:message key="pinfo.enviado.titulo.solicitud"/>:</strong> ${titolIncidencia}</p>
			<p><strong><fmt:message key="pinfo.enviado.responsable"/>:</strong> ${destinatariNom} (${destinatariNIF})</p>
			<p><strong><fmt:message key="pinfo.enviado.email.enviado"/>:</strong> ${emailSolicitant}</p>
		</div>
		
		<div class="instrucciones">
			<h4><fmt:message key="pinfo.enviado.seguimiento.titulo"/></h4>
			<p><fmt:message key="pinfo.enviado.seguimiento.texto"/></p>
		</div>
		
		<div class="botonera-confirmacio">
			<a href="${urlEvents}" class="btn-primary-custom">
				<i class="fas fa-list"></i> <fmt:message key="pinfo.enviado.ver.eventos"/>
			</a>
			<a href="${urlMisPinfos}" class="btn-secondary-custom">
				<i class="fas fa-arrow-left"></i> <fmt:message key="tramitpinfo.exit"/>
			</a>
		</div>
	</div>

</body>
</html>
