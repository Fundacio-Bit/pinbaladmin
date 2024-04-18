<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<style>
th {
	text-align: center;
	padding: 0.2rem;
}

.item {
	max-width: 45rem;
	padding: 0.25rem 0.5rem;
}

.table-rol {
	margin-bottom: 0.75rem;
	padding: 0.25rem 0.5rem;
}

.table-rol th:nth-child(1), .table-rol td:nth-child(1) {
	width: 7rem;
}

.table-rol th:nth-child(2), .table-rol td:nth-child(2) {
	width: 7rem;
}

.table-rol th:nth-child(3), .table-rol td:nth-child(3) {
	width: 25rem;
}

#button-alta-solicitud {
	color: white;
	float: right;
	margin-right: 10rem;
}

#servicios-container {
	width: fit-content;
}

table.solicitud.servicio.item {
	width: 100%;
}
</style>

<a id="button-alta-solicitud" class="btn btn-primary"
	onclick="altaSolicitud()"> <i class="fas fa-cloud-upload-alt"></i>
	&nbsp; Alta Solicitud
</a>


<h3>Vista previa alta solicitud</h3>

<form id="formulari"
	action="<c:url value="/operador/altapinbal/altasolicitud"/>"
	method="post" enctype="multipart/form-data">

	<input type="hidden" name="soliID" value="${soliID}" />

	<div class="solicitud item">
		<table class="table-rol titular" border="1">
			<tr>
				<th>Titular</th>
				<td class="titular item">${titular.documentacion}</td>
				<td class="titular item">${titular.nombreCompleto}</td>
			</tr>
		</table>
	</div>

	<div class="solicitud item">
		<table class="table-rol funcionario" border="1">
			<tr>
				<th>Funcionario</th>
				<td class="funcionario item">${funcionario.nifFuncionario}</td>
				<td class="funcionario item">${funcionario.nombreCompletoFuncionario}</td>
			</tr>
		</table>
	</div>

	<div>
		<div class="solicitud item">
			<h4>Datos de la Solicitud:</h4>
			<div class="solicitud item">
				<b>Asunto: </b>${solicitud.asunto}</div>
			<div class="solicitud item">
				<b>Consulta:</b> <br>
				<textarea id="consulta" name="consulta" rows="6" cols="50">Consulta TEST</textarea>

			</div>
		</div>
	</div>


	<div class="solicitud item">
		<h4>Contactos:</h4>
		<table border="1">
			<tr>
				<th class="solicitud contacto th">Nombre</th>
				<th class="solicitud contacto th">Correo</th>
				<th class="solicitud contacto th">Teléfono</th>
			</tr>
			<c:forEach var="contacto" items="${solicitud.contactos.contacto}">
				<tr>
					<td class="solicitud contacto item">${contacto.nombre}${" "}${contacto.apellido1}${" "}${contacto.apellido2}</td>
					<td class="solicitud contacto item">${contacto.email}</td>
					<td class="solicitud contacto item">${contacto.telefono}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<br>
	<div class="solicitud item">
		<c:set var="procedimiento" value="${solicitud.procedimiento}" />
		<h4>Procedimiento:</h4>
		<table border="1">
			<tr>
				<th class="solicitud procedimiento titulo">Código</th>
				<td class="solicitud procedimiento item">${procedimiento.codigo}</td>
			</tr>
			<tr>
				<th class="solicitud procedimiento titulo">Nombre</th>
				<td class="solicitud procedimiento item">${procedimiento.nombre}</td>
			</tr>
			<tr>
				<th class="solicitud procedimiento titulo">Descripción</th>
				<td class="solicitud procedimiento item">${procedimiento.descripcion}</td>
			</tr>
			<tr>
				<th class="solicitud procedimiento titulo">Clase Tramite</th>
				<td id="clase-tramite" class="solicitud procedimiento item">${procedimiento.claseTramite}</td>
			</tr>
			<tr>
				<th class="solicitud procedimiento titulo">Peticiones Estimadas</th>
				<td class="solicitud procedimiento item">${procedimiento.peticionesEstimadas}</td>
			</tr>
			<tr>
				<th class="solicitud procedimiento titulo">Periodico</th>
				<td id="periodico" class="solicitud procedimiento item">${procedimiento.periodico}</td>
			</tr>
			<tr>
				<th class="solicitud procedimiento titulo">Automatizado</th>
				<td id="automatizado" class="solicitud procedimiento item">${procedimiento.automatizado}</td>
			</tr>
			<tr>
				<th class="solicitud procedimiento titulo">Fecha Caducidad</th>
				<td class="solicitud procedimiento item">${procedimiento.fechaCaducidad}</td>
			</tr>
			<tr>
				<th class="solicitud procedimiento titulo">Observaciones</th>
				<td class="solicitud procedimiento item">${procedimiento.observaciones}</td>
			</tr>
		</table>

		<br>
		<div class="solicitud procedimiento item">
			<c:set var="consentimiento" value="${procedimiento.consentimiento}" />

			<h5>Consentimiento:</h5>
			<table border="1">
				<tr data-type="tipo">
					<th>Tipo</th>
					<td id="consentimiento-tipo" class="solicitud consentimiento item">${consentimiento.tipo}</td>
				</tr>
				<tr data-type="enlace">
					<th>Enlace:</th>
					<td class="solicitud consentimiento item"><a
						id="consentimiento-enlace" href="${consentimiento.enlace}">${consentimiento.enlace}</a></td>
				</tr>
				<tr data-type="documento">
					<th>Documento</th>
					<td id="consentimiento-doc-nombre"
						class="solicitud consentimiento item">${consentimiento.documento.nombre}</td>
				</tr>
				<tr data-type="documento">
					<th>Descripción</th>
					<td id="consentimiento-doc-desc"
						class="solicitud consentimiento item">${consentimiento.documento.descripcion}</td>
				</tr>
				<tr data-type="documento">
					<th>Contenido</th>
					<td id="consentimiento-doc-contenido"
						class="solicitud consentimiento item contenido">${fn:length(consentimiento.documento.contenido)}</td>
				</tr>
			</table>

		</div>

		<br>
		<div class="solicitud procedimiento item">
			<h5>Documentos Autorizacion:</h5>
			<c:forEach var="docauth"
				items="${procedimiento.documentosAutorizacion.documentoAutorizacion}">
				<table border="1">
					<tr>
						<th>Documento</th>
						<td class="solicitud docsauth item">${docauth.nombre}</td>
					</tr>
					<tr>
						<th>Descripción</th>
						<td class="solicitud docsauth item">${docauth.descripcion}</td>
					</tr>
					<tr>
						<th>Tipo</th>
						<td class="solicitud docsauth item">${docauth.tipo}</td>
					</tr>
					<tr>
						<th>Contenido</th>
						<td class="solicitud docsauth item contenido">${fn:length(docauth.contenido)}</td>
					</tr>
				</table>
				<br>
			</c:forEach>

		</div>

		<br>
		<div id="servicios-container" class="solicitud procedimiento item">
			<h5>Servicios:</h5>
			<c:forEach var="servicio" items="${procedimiento.servicios.servicio}">
				<table class="solicitud servicio item" border="1">
					<tr>
						<th>Código Certificado: ${servicio.codigoCertificado}</th>
					</tr>
					<c:forEach var="norma" items="${servicio.normas.norma}">
						<tr>
							<td>
								<!-- Span two columns for nested content -->
								<table>
<%--  									<tr>
										<th>Servicio</th>
										<td class="solicitud servicio norma item">${norma.documento.descripcion}</td>
									</tr> --%>
									<tr>
										<th>Norma Legal</th>
										<td class="solicitud servicio norma item">${norma.normaLegal}</td>
									</tr>
									<tr>
										<th>Documento</th>
										<td class="solicitud servicio norma item">${norma.documento.nombre}</td>
									</tr>
									<tr>
										<th>Contenido</th>
										<td class="solicitud servicio norma item contenido">${fn:length(norma.documento.contenido)}</td>
									</tr>
 									<tr>
										<th>Articulos</th>
										<td class="solicitud servicio norma item"><c:forEach
												var="articulo" items="${norma.articulos.articulo}"
												varStatus="status">
                                                ${articulo}<c:if
													test="${!status.last}">, </c:if>
											</c:forEach></td>
									</tr>
								</table>
							</td>
						</tr>
					</c:forEach>
				</table>
				<br>
			</c:forEach>
		</div>
	</div>
</form>

<script type="text/javascript">
	actualizarElemento("periodico");
	actualizarElemento("automatizado");

	function actualizarElemento(id) {
		var elem = document.getElementById(id);
		var value = elem.innerHTML;
		elem.innerHTML = value == "N" ? "No" : "Si";
	}

	customConsentimiento();
	function customConsentimiento() {
		var tipo = document.getElementById("consentimiento-tipo");
		var tipoValue = tipo.innerHTML;

		var enlace = document.getElementById("consentimiento-enlace").innerHTML;
		var documento = document.getElementById("consentimiento-doc-nombre").innerHTML;
		if (tipoValue === "NoOpo")
			tipo.innerHTML = "No Oposició";
		if (tipoValue === "Ley")
			tipo.innerHTML = "Llei";
		if (tipoValue === "Si")
			tipo.innerHTML = "Si";

		if (tipoValue === "Ley") {
			$("tr[data-type='enlace']").hide();
			$("tr[data-type='documento']").hide();
		} else {
			if (enlace != "") {
				$("tr[data-type='documento']").hide();
			} else {
				$("tr[data-type='enlace']").hide();
				if (documento == "") {
					$("tr[data-type='documento']").hide();
				}
			}
		}
	}

	getClaseTramitFromProcedimiento();
	function getClaseTramitFromProcedimiento() {
		var claseTramit = document.getElementById("clase-tramite");

		switch (claseTramit.innerHTML) {
		case "0":
			claseTramit.innerHTML = "Pruebas";
			break;
		case "2":
			claseTramit.innerHTML = "Recursos Humanos";
			break;
		case "3":
			claseTramit.innerHTML = "Tributario";
			break;
		case "14":
			claseTramit.innerHTML = "Sancionador";
			break;
		case "19":
			claseTramit.innerHTML = "Afiliación y cotización a la Seguridad Social";
			break;
		case "20":
			claseTramit.innerHTML = "Autorizaciones, licencias, concesiones y homologaciones";
			break;
		case "21":
			claseTramit.innerHTML = "Ayudas, Becas y Subvenciones";
			break;
		case "22":
			claseTramit.innerHTML = "Certificados";
			break;
		case "23":
			claseTramit.innerHTML = "Contratación pública";
			break;
		case "24":
			claseTramit.innerHTML = "Convenios de Colaboración y Comunicaciones administrativas";
			break;
		case "25":
			claseTramit.innerHTML = "Gestión Económica y Patrimonial";
			break;
		case "26":
			claseTramit.innerHTML = "Declaraciones y comunicaciones de los interesados";
			break;
		case "27":
			claseTramit.innerHTML = "Inspectora";
			break;
		case "28":
			claseTramit.innerHTML = "Premios";
			break;
		case "29":
			claseTramit.innerHTML = "Prestaciones";
			break;
		case "30":
			claseTramit.innerHTML = "Registros y Censos";
			break;
		case "31":
			claseTramit.innerHTML = "Responsabilidad patrimonial y otras solicitudes de indemnización";
			break;
		case "32":
			claseTramit.innerHTML = "Revisión de Actos administrativos y Recursos";
			break;
		case "33":
			claseTramit.innerHTML = "Sugerencias, Quejas, Denuncias e Información a los ciudadanos";
			break;
		case "34":
			claseTramit.innerHTML = "Aduanero";
			break;
		case "99":
			claseTramit.innerHTML = "Resolución de incidencias";
			break;
		default:
			claseTramit.innerHTML = "";
			break;
		}
	}

	function altaSolicitud() {
		var msg = document.getElementById("consulta").value;
		
		document.getElementById("formulari").submit();
	}

	setTamanyFitxersOk();
	function setTamanyFitxersOk() {
		var contenidos = document.getElementsByClassName("contenido");
		for (let i = 0; i < contenidos.length; i++) {
			const bytes = contenidos[i].innerHTML;
			const resultado = convertirBytes(bytes);
			contenidos[i].innerHTML = resultado;
		}
	}

	function convertirBytes(bytes) {
		if (bytes < 1024) {
			return bytes + " bytes";
		} else if (bytes < 1048576) {
			const kilobytes = (bytes / 1024).toFixed(2);
			return kilobytes + " KB";
		} else {
			const megabytes = (bytes / 1048576).toFixed(2);
			return megabytes + " MB";
		}
	}
</script>