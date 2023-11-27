<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.fundaciobit.genapp.common.web.html.IconUtils"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<style>
th {
	text-align: center;
	padding: 0.2rem;
}

.item {
	max-width: 52rem;
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

.w-100{
    width: 100%;

}
</style>

<a id="button-alta-solicitud" class="btn btn-primary"
	href="${returnUrl}" > Tornar a Solicitud
</a>


<h3>Estado de la solicitud</h3>

<div id="procedimiento-container" class="solicitud item">
	<c:set var="procedimiento" value="${retorno.procedimiento}" />
	<h5>Datos Procedimiento:</h5>
	<table border="1">
		<tr>
			<th class="solicitud procedimiento titulo">Código</th>
			<td class="solicitud procedimiento item">${procedimiento.codigo}</td>
		</tr>
		<tr>
			<th class="solicitud procedimiento titulo">Estado</th>
			<td class="solicitud procedimiento item">${procedimiento.estadoProcedimiento.descripcion}</td>
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
			<th class="solicitud procedimiento titulo">Consentimiento</th>
			<td id="consentimiento-tipo" class="solicitud procedimiento item">${procedimiento.consentimiento.tipo}</td>
		</tr>
		<tr>
			<th class="solicitud procedimiento titulo">Automatizado</th>
			<td id="automatizado" class="solicitud procedimiento item">${procedimiento.automatizado}</td>
		</tr>
		<tr>
			<th class="solicitud procedimiento titulo">Periodico</th>
			<td id="periodico" class="solicitud procedimiento item">${procedimiento.periodico}</td>
		</tr>
	</table>
</div>
<br>

<div id="servicios-container" class="solicitud procedimiento item">
	<h5>Servicios:</h5>
	<c:forEach var="servicio"
		items="${retorno.procedimiento.servicios.servicio}">
		<table class="solicitud servicio item" border="1">
			<tr>
				<th>Código Certificado: ${servicio.codigoCertificado}</th>
			</tr>
			<tr>
				<td>
					<table class="w-100">
						<tr>
							<th>Servicio</th>
							<td class="item">${servicio.nombre}</td>
						</tr>
						<tr>
							<th>Estado Autorización</th>
							<td class="item">${servicio.estadoAutorizacion.descripcion}</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr>
                <th>Estado autorización cedentes</th>
            </tr>


            <tr>
                <td>
                    <table class="w-100">
                        <tr>
                            <th>Cedente</th>
                            <th>Estado</th>
                            <th>Servicio</th>
                        </tr>
                        <c:forEach var="estadoAutorizacionCedente"
                            items="${servicio.estadoAutorizacionCedentes.estadoAutorizacionCedente}">
                            <tr>
                                <td class="solicitud servicio estado-aut-ced item">${estadoAutorizacionCedente.cedente}</td>
                                <td class="solicitud servicio estado-aut-ced item">${estadoAutorizacionCedente.descripcion}</td>
                                <td class="solicitud servicio estado-aut-ced item">${estadoAutorizacionCedente.nombreServicio}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
            
			<tr>
				<th>Normas</th>
			</tr>
			<tr>
				<td>
					<table class="w-100">
						<c:forEach var="norma" items="${servicio.normas.norma}">
							<tr>
								<th>Norma</th>
								<td class="item">${norma.normaLegal}</td>
								<th>Articulos</th>
								<td class="item"><c:forEach var="articulo"
										items="${norma.articulos.articulo}" varStatus="status">
                                                ${articulo}<c:if
											test="${!status.last}">, </c:if>
									</c:forEach></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
		<br>
		<br>
	</c:forEach>
</div>



<script type="text/javascript">
	actualizarElemento("periodico");
	actualizarElemento("automatizado");

	function actualizarElemento(id) {
		var elem = document.getElementById(id);
		var value = elem.innerHTML;
		elem.innerHTML = value == "N" ? "No" : "Si";
	}

	var FILES_REQUIRED = 0;
	customConsentimiento();
	function customConsentimiento() {
		var tipo = document.getElementById("consentimiento-tipo");
		var tipoValue = tipo.innerHTML;

		if (tipoValue === "NoOpo")
			tipo.innerHTML = "No Oposició";
		if (tipoValue === "Ley")
			tipo.innerHTML = "Llei";
		if (tipoValue === "Si")
			tipo.innerHTML = "Si";
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

		var file = document.getElementById("consentimiento-doc-fichero").children[0];
		var nFiles = file.files.length;

		if (nFiles == FILES_REQUIRED) {
			document.getElementById("formulari").submit();
		} else {
			alert("Faltan ficheros: " + nFiles + " - " + FILES_REQUIRED);
		}

		//	document.getElementById("formulari").submit();
	}
</script>
